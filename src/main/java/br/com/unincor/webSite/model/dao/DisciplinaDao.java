package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Disciplina;
import br.com.unincor.webSite.model.domain.Professor;
import jakarta.persistence.Query;
import java.util.List;

public class DisciplinaDao extends GenericDao<Disciplina, Long>{

    public List<Disciplina> buscarDisciplinasProfessorPorLogin(Professor prof) {
        String sql = "from Disciplina d where d.professor = :professor AND d.enable = true";
        
        Query query = getEntityManager().createQuery(sql, Disciplina.class)
                .setParameter("professor", prof);
        var resultado = query.getResultList();
        return resultado;
    }
    
    
}
