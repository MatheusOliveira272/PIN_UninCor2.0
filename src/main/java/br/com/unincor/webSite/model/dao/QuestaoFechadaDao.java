package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import jakarta.persistence.Query;
import java.util.List;

public class QuestaoFechadaDao extends GenericDao<QuestaoFechada, Long> {
    
    public void salvaAlernativasQuestoaFechada(List<QuestaoFechada> questoesFechadas){
        for (int i = 0; i < questoesFechadas.size(); i++) {
            var qf = questoesFechadas.get(i);
             new QuestaoFechadaDao().save(qf);
        }
    }
    
//    public void setaIdQuestao(Questao questao, List<QuestaoFechada> questoesFechadas){
//        for (int i = 0; i < questoesFechadas.size(); i++) {
//            var qf = questoesFechadas.get(i);
//            qf.setQuestao(questao);
//             new QuestaoFechadaDao().save(qf);
//        }
//    }

    public List<QuestaoFechada> buscaQuestaoFechadaPorProfessor(Professor professorLogado) {
        String sql = "FROM QuestaoFechada qf join qf.questao q join q.atividades a join a.disciplinas d where d.professor = :professor";
        
        Query query = getEntityManager().createQuery(sql, QuestaoFechada.class)
                .setParameter("professor", professorLogado);
        return query.getResultList();
    }
    
}
