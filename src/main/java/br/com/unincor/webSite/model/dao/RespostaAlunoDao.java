package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Aluno;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import br.com.unincor.webSite.model.domain.RespostaAluno;
import jakarta.persistence.Query;
import java.util.List;

public class RespostaAlunoDao extends GenericDao<RespostaAluno, Long> {
    
      public List<QuestaoFechada> recuperaDescricaoQuestaoFechada(Questao questao) {
        String sql = "From QuestaoFechada qf join qf.questao q where q= :questao";
        
        Query query = getEntityManager().createQuery(sql, QuestaoFechada.class)
                .setParameter("questao", questao);
        var resultado = query.getResultList();
        return resultado;
    }
      
      public List<RespostaAluno> recuperaAtividadeAluno(Atividade atividade, Aluno aluno) {
        String sql = "From RespostaAluno ra join ra.questao q join q.atividades a where a = :atividade AND ra.aluno = :aluno";
        
        Query query = getEntityManager().createQuery(sql, QuestaoFechada.class)
                .setParameter("atividade", atividade)
                .setParameter("aluno", aluno);
        var resultado = query.getResultList();
        return resultado;
    }
    
}
