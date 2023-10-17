package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Aluno;
import jakarta.persistence.Query;

public class AlunoDao extends GenericDao<Aluno, Integer> {
    
     public Aluno buscarAlunoPorLogin(String login) {
        String sql = "from Aluno a where a.email = :email";
        
        Query query = getEntityManager().createQuery(sql, Aluno.class)
                .setParameter("email", login);
        var resultado = query.getResultList();
        return resultado.isEmpty() ? null : (Aluno) resultado.get(0);
    }
}
