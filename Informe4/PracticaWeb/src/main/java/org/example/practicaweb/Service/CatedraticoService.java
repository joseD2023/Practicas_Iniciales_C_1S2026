package org.example.practicaweb.Service;


import org.example.practicaweb.Model.Catedratico;
import org.example.practicaweb.Repository.CatedraticoReponsitory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatedraticoService {
    private final CatedraticoReponsitory catedraticoReponsitory;

    public CatedraticoService(CatedraticoReponsitory catedraticoReponsitory) {
        this.catedraticoReponsitory = catedraticoReponsitory;
    }

    public List<Catedratico> getAllCatedraticos(){
        return catedraticoReponsitory.findAll();
    }

    public Catedratico getCatedraticoById(Long id){
        return catedraticoReponsitory.findById(id).orElse(null);
    }

    public Catedratico createCatedratico(Catedratico catedratico){
        return catedraticoReponsitory.save(catedratico);
    }
}
