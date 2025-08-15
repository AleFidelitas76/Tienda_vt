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
    
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }
    
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
    
    @Transactional
    public boolean delete(Categoria categoria) {
        try {
            categoriaRepository.delete(categoria);
            categoriaRepository.flush();
            return true;
        }catch (Exception e){
            return false;
        }
     }
}
