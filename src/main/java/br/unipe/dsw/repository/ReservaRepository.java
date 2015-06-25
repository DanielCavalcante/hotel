package br.unipe.dsw.repository;

import br.unipe.dsw.entity.Hospede;
import br.unipe.dsw.entity.Quarto;
import br.unipe.dsw.entity.Reserva;
import br.unipe.dsw.enums.EnumStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    public List<Reserva> findByDataInicialAndDataFinalAndStatusAndHospedeAndQuarto(Date dataInicial, Date dataFinal, EnumStatus status, Hospede hospede, Quarto quarto);
}
