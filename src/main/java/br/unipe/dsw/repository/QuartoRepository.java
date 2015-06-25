package br.unipe.dsw.repository;

import br.unipe.dsw.entity.Categoria;
import br.unipe.dsw.entity.Quarto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuartoRepository extends CrudRepository<Quarto, Long> {
    public List<Quarto> findByNumeroAndCategoria(String numero, Categoria categoria);
}
