package br.com.unincor.webSite.model.dao;

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
    
}
