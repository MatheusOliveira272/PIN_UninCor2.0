package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.model.domain.Questao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class QuestaoDao extends GenericDao<Questao, Long> {

    public List<Questao> buscarQuestaosProfessorPorLogin(Professor professor) {
    String sql = "SELECT q FROM Questao q JOIN q.atividades a JOIN a.disciplinas d WHERE d.professor = :professor";

    Query query = getEntityManager().createQuery(sql, Questao.class)
            .setParameter("professor", professor);
        return query.getResultList();


}
    public List<Questao> buscarQuestoesPorCodigoAtividade(String codigoAtividade) {
    EntityManager em = getEntityManager();
    try {
        String jpql = "SELECT q FROM Questao q JOIN q.atividades a WHERE a.codigo = :codigoAtividade";
        TypedQuery<Questao> query = em.createQuery(jpql, Questao.class);
        query.setParameter("codigoAtividade", codigoAtividade);
        return query.getResultList();
    } finally {
        em.close();
    }
}



}
