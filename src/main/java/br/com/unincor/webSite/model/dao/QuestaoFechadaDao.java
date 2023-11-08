package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.core.HibernateManager;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;

public class QuestaoFechadaDao extends GenericDao<QuestaoFechada, Long> {

    public void salvaAlernativasQuestoaFechada(List<QuestaoFechada> questoesFechadas, Questao questao) {
        EntityManager entityManager = HibernateManager.geEntityManager();// Obter o EntityManager
                EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Antes de persistir a questao, associe as respostas à questao
            for (QuestaoFechada questaoFechada : questoesFechadas) {
                questaoFechada.setQuestao(questao);
            }

            // Persista a questao
            entityManager.persist(questao);

            // Persista as respostas
            for (QuestaoFechada questaoFechada : questoesFechadas) {
                entityManager.persist(questaoFechada);
            }

            transaction.commit(); // Confirma as operações

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Em caso de falha, desfaz as operações
            }
            e.printStackTrace(); // Lida com a exceção, se necessário
        }
    }

    public List<QuestaoFechada> buscaQuestaoFechadaPorProfessor(Professor professorLogado) {
        String sql = "FROM QuestaoFechada qf join qf.questao q join q.atividades a join a.disciplinas d where d.professor = :professor";

        Query query = getEntityManager().createQuery(sql, QuestaoFechada.class)
                .setParameter("professor", professorLogado);
        return query.getResultList();
    }
    
    public List<QuestaoFechada> buscaQuestoesFechadasPorQuestao(Questao questao) {
        String sql = "FROM QuestaoFechada qf where qf.questao = :questao";

        Query query = getEntityManager().createQuery(sql, QuestaoFechada.class)
                .setParameter("questao", questao);
        return query.getResultList();
 
    }

}
