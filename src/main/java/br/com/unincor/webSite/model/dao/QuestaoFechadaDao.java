package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import java.util.List;

public class QuestaoFechadaDao extends GenericDao<QuestaoFechada, Long> {
    
    public void salvaAlernativasQuestoaFechada(List<QuestaoFechada> questoesFechadas){
        for (int i = 0; i < questoesFechadas.size(); i++) {
            var qf = questoesFechadas.get(i);
//            qf.setQuestao(questao);
             new QuestaoFechadaDao().save(qf);
            
        }
    }
    
}
