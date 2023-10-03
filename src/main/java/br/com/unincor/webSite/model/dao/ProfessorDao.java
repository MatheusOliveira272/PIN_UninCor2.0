package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Professor;
import jakarta.persistence.Query;

public class ProfessorDao extends GenericDao<Professor, Long> {
    
     public Professor buscarProfessorPorLogin(String login) {
        String sql = "from Professor p where p.email = :email";
        
        Query query = getEntityManager().createQuery(sql, Professor.class)
                .setParameter("email", login);
        var resultado = query.getResultList();
        return resultado.isEmpty() ? null : (Professor) resultado.get(0);
    }
    
}
