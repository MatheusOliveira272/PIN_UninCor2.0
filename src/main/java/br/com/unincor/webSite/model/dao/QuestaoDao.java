package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.model.domain.Questao;
import jakarta.persistence.Query;
import java.util.List;

public class QuestaoDao extends GenericDao<Questao, Long> {

    public List<Questao> buscarQuestaosProfessorPorLogin(Professor prof) {
    String sql = "SELECT q FROM Questao q " +
                 "JOIN q.atividade a " +
                 "JOIN a.disciplina d " +
                 "WHERE d.professor = :professorId";

    Query query = getEntityManager().createQuery(sql, Questao.class)
            .setParameter("professorId", prof.getId());

    return query.getResultList();
}


}
