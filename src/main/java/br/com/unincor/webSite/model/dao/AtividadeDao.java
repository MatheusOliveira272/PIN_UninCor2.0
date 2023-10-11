package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Professor;
import jakarta.persistence.Query;
import java.util.List;


public class AtividadeDao extends GenericDao<Atividade, Long> {
    
     public List<Atividade> buscarAtividadesProfessorPorLogin(Professor prof) {
         String sql = "SELECT a FROM Atividade a JOIN a.disciplinas d WHERE d.professor = :professor";
        
        Query query = getEntityManager().createQuery(sql, Atividade.class)
                .setParameter("professor", prof);
        var resultado = query.getResultList();
        return resultado;
    }
     
     public Atividade buscarAtividadeCodigo(String codigo) {
        String sql = "from Atividade a where a.codigo = :codigo";
        
        Query query = getEntityManager().createQuery(sql, Atividade.class)
                .setParameter("codigo", codigo);
        var resultado = query.getResultList();
        return resultado.isEmpty() ? null : (Atividade) resultado.get(0);
    }
}
