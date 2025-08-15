package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    //Permite crear una unica instance de CategoriaRepository automaticamente
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly=true)
    public List<Categoria> getCategoria(boolean activo) {
        var lista = categoriaRepository.findAll();
        //Se valida si esta activo
        if (activo) {
            lista.removeIf(c -> !c.isActivo());
        }
        
        return lista;
    }
}
