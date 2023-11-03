package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.model.domain.Questao;
import jakarta.persistence.Query;
import java.util.List;

public class QuestaoDao extends GenericDao<Questao, Long> {

    public List<Questao> buscarQuestaosProfessorPorLogin(Professor professor) {
    String sql = "SELECT q FROM Questao q JOIN q.atividades a JOIN a.disciplinas d WHERE d.professor = :professor";

    Query query = getEntityManager().createQuery(sql, Questao.class)
            .setParameter("professor", professor);
        return query.getResultList();


}


}
