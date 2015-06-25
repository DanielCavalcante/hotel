package br.unipe.dsw.repository;

import br.unipe.dsw.entity.Hospede;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface HospedeRepository extends CrudRepository<Hospede, Long> {
    public List<Hospede> findByNomeAndCpf(String nome, String cpf);
}
