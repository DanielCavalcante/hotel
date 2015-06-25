package br.unipe.dsw.repository;

import br.unipe.dsw.entity.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoriaRepository extends CrudRepository <Categoria, Long> {
    public List<Categoria> findByDescricao(String descricao);
}
