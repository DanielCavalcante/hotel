package br.unipe.dsw.controller;

import br.unipe.dsw.entity.Categoria;
import br.unipe.dsw.entity.Hospede;
import br.unipe.dsw.entity.Quarto;
import br.unipe.dsw.entity.Reserva;
import br.unipe.dsw.enums.EnumFormaDePagamento;
import br.unipe.dsw.enums.EnumStatus;
import br.unipe.dsw.repository.HospedeRepository;
import br.unipe.dsw.repository.QuartoRepository;
import br.unipe.dsw.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    HospedeRepository hospedeRepository;

    @Autowired
    QuartoRepository quartoRepository;

    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap,
                       @RequestParam(value = "dataInicial", required = false) String dataInicial,
                       @RequestParam(value = "dataFinal", required = false) String dataFinal,
                       @RequestParam(value = "status", required = false) String status,
                       @RequestParam(value = "cliente_id", required = false) String clienteId,
                       @RequestParam(value = "quarto_id", required = false) String quartoId){

        List<Reserva> reservaList = new ArrayList<Reserva>();

        if(dataInicial != null
                && dataFinal != null
                && status != null
                && clienteId != null
                && quartoId != null){

            EnumStatus enumStatus = (status.equals(EnumStatus.Confirmada.name())) ?
                    EnumStatus.Confirmada : (status.equals(EnumStatus.Pendente.name()) ?
                    EnumStatus.Pendente : EnumStatus.Cancelada);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = new Date();
            Date dataFim = new Date();

            try {
                dataInicio = format.parse(dataInicial);
                dataFim = format.parse(dataFinal);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Hospede hospede = hospedeRepository.findOne(Long.parseLong(clienteId));
            Quarto quarto = quartoRepository.findOne(Long.parseLong(quartoId));

            reservaList = reservaRepository.findByDataInicialAndDataFinalAndStatusAndHospedeAndQuarto(dataInicio, dataFim, enumStatus, hospede, quarto);

        }else{
            reservaList = (List<Reserva>)reservaRepository.findAll();
        }

        List<Hospede> hospedeList = (List<Hospede>)hospedeRepository.findAll();
        List<Quarto> quartoList = (List<Quarto>)quartoRepository.findAll();

        modelMap.addAttribute("reservas", reservaList);
        modelMap.addAttribute("hospedes", hospedeList);
        modelMap.addAttribute("quartos", quartoList);
        return "reserva/list";
    }

    @RequestMapping(value = "/nova", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        Reserva reserva;
        reserva = new Reserva();
        reserva.setHospede(new Hospede());
        reserva.setQuarto(new Quarto());

        modelMap.addAttribute("reserva", reserva);
        modelMap.addAttribute("quartos", selectQuarto());
        modelMap.addAttribute("hospedes", selectHospede());

        return "reserva/nova";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("reserva") Reserva reserva){
        Reserva reservaSalvar = new Reserva();
        if(reserva.getId() != null){
            reservaSalvar = reservaRepository.findOne(reserva.getId());
            reservaSalvar.setQuarto(reserva.getQuarto());
            reservaSalvar.setHospede(reserva.getHospede());
            reservaSalvar.setDataInicial(reserva.getDataInicial());
            reservaSalvar.setDataFinal(reserva.getDataFinal());
        }else{
            reservaSalvar = reserva;
            reservaSalvar.setStatus(EnumStatus.Pendente);
        }
        reservaRepository.save(reservaSalvar);
        return"redirect:/reserva/list";
    }

    @RequestMapping(value = "/{id}/detalhe")
    public String detalhe(@PathVariable("id") String id, ModelMap modelMap){

        Reserva reserva = reservaRepository.findOne(Long.parseLong(id));
        modelMap.addAttribute("reserva", reserva);
        return "reserva/detalhe";

    }

    @RequestMapping(value = "/{id}/confirmar", method = RequestMethod.GET)
    public String confirmar(@PathVariable("id") String id, RedirectAttributes redirectAttributes){
        Reserva reserva = reservaRepository.findOne(Long.parseLong(id));
        if(reserva.getStatus() != EnumStatus.Pendente){
            redirectAttributes.addFlashAttribute("errorMessage", "Esta reserva está " + reserva.getStatus().name() + " e para confirmar precisa estar Pendente");
            return "redirect:/reserva/" + reserva.getId() + "/detalhe";
        }else{
            reserva.setStatus(EnumStatus.Confirmada);
            reservaRepository.save(reserva);
            redirectAttributes.addFlashAttribute("successMessage", "Esta reserva realizada com sucesso!");
            return "redirect:/reserva/list";
        }
    }

    @RequestMapping(value = "/{id}/cancelar", method = RequestMethod.GET)
    public String cancelar(@PathVariable("id") String id, RedirectAttributes redirectAttributes){
        Reserva reserva = reservaRepository.findOne(Long.parseLong(id));
        if(reserva.getStatus() != EnumStatus.Pendente){
            redirectAttributes.addFlashAttribute("errorMessage", "Esta reserva está " + reserva.getStatus().name() + " e para cancelar precisa estar Pendente");
            return "redirect:/reserva/" + reserva.getId() + "/detalhe";
        }else{
            reserva.setStatus(EnumStatus.Cancelada);
            reservaRepository.save(reserva);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva cancelada com sucesso!");
            return "redirect:/reserva/list";
        }
    }

    @RequestMapping(value = "/{id}/checkin", method = RequestMethod.GET)
    public String checkin(@PathVariable("id") String id, RedirectAttributes redirectAttributes){
        Reserva reserva = reservaRepository.findOne(Long.parseLong(id));
        if(reserva.getStatus() == EnumStatus.Confirmada && reserva.getDataCheckin() == null){
            reserva.setDataCheckin(new Date());
            reservaRepository.save(reserva);
            redirectAttributes.addFlashAttribute("successMessage", "Checkin realizado com sucesso!");
            return "redirect:/reserva/list";
        }else{
            if(reserva.getDataCheckin() == null){
                redirectAttributes.addFlashAttribute("errorMessage", "Esta reserva não está confirmada!");
                return "redirect:/reserva/" + reserva.getId() + "/detalhe";
            }else{
                redirectAttributes.addFlashAttribute("errorMessage", "Esta reserva já fez checkin!");
                return "redirect:/reserva/" + reserva.getId() + "/detalhe";
            }
        }
    }

    @RequestMapping(value = "/{id}/checkout", method = RequestMethod.GET)
    public String checkout(@PathVariable("id") String id, RedirectAttributes redirectAttributes){
        Reserva reserva = reservaRepository.findOne(Long.parseLong(id));
        if(reserva.getStatus() == EnumStatus.Confirmada && reserva.getDataCheckin() != null && reserva.getDataChekout() != null){
            reserva.setDataChekout(new Date());
            reservaRepository.save(reserva);
            redirectAttributes.addFlashAttribute("successMessage", "Chekout realizado com sucesso!");
            return "redirect:/reserva/list";
        }else{
            if(reserva.getDataChekout() != null){
                redirectAttributes.addFlashAttribute("errorMessage", "Esta reserva já fez checkout!");
                return "redirect:/reserva/" + reserva.getId() + "/detalhe";
            }else{
                redirectAttributes.addFlashAttribute("errorMessage", "Esta reserva primeiro precisa fazer checkin!");
                return "redirect:/reserva/" + reserva.getId() + "/detalhe";
            }
        }
    }

    @RequestMapping(value = "/{id}/pagar", method = RequestMethod.GET)
    public String pagar(@PathVariable("id") String id, RedirectAttributes redirectAttributes){
        Reserva reserva = reservaRepository.findOne(Long.parseLong(id));
        if(reserva.getStatus() != EnumStatus.Cancelada && !reserva.isPago()){
            reserva.setPago(true);
            reserva.setFormaPagamento(EnumFormaDePagamento.A_Vista);
            reservaRepository.save(reserva);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva paga com sucesso!");
        }else{
            redirectAttributes.addFlashAttribute("errorMessage", "A reserva está cancelada ou já foi paga!");
        }

        return "redirect:/reserva/list";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") String id, ModelMap modelMap){
        Reserva reserva = reservaRepository.findOne(Long.parseLong(id));
        modelMap.addAttribute("reserva", reserva);
        modelMap.addAttribute("quartos", selectQuarto());
        modelMap.addAttribute("hospedes", selectHospede());
        return "reserva/nova";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id){
        reservaRepository.delete(reservaRepository.findOne(Long.parseLong(id)));
        return "redirect:/reserva/list";
    }

    public Map<Long, String> selectHospede(){

        List<Hospede> hospedes = (List<Hospede>)hospedeRepository.findAll();
        Map<Long, String> mapa = new HashMap<Long, String>();

        for(Hospede hospede:hospedes){
            mapa.put(hospede.getId(), hospede.getNome());
        }
        return mapa;
    }
    public Map<Long, String> selectQuarto(){

        List<Quarto> quartos = (List<Quarto>)quartoRepository.findAll();
        Map<Long, String> mapa = new HashMap<Long, String>();

        for(Quarto quarto:quartos){
            mapa.put(quarto.getId(), quarto.getNumero());
        }
        return mapa;
    }
}