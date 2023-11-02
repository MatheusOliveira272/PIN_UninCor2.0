package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Aluno;
import br.com.unincor.webSite.model.domain.AlunoAtividade;
import br.com.unincor.webSite.model.domain.Atividade;
import jakarta.persistence.Query;
import java.util.List;

public class AlunoAtividadeDao extends GenericDao<AlunoAtividade, Long> {
    public AlunoAtividade retornaAlunoAtividadePorAtividade(Atividade atividade){
        String sql = "FROM AlunoAtividade av WHERE av.atividade = :atividade";
        
        Query query = getEntityManager().createQuery(sql, Atividade.class)
                .setParameter("atividade", atividade);
        var resultado = query.getResultList();
        return resultado.isEmpty() ? null : (AlunoAtividade) resultado.get(0);
    }

    public AlunoAtividade buscaAlunoAtividadeNota(Aluno aluno, Atividade atividade) {
        String sql = "FROM AlunoAtividade av WHERE av.atividade = :atividade AND av.aluno = :aluno";
        
        Query query = getEntityManager().createQuery(sql, Atividade.class)
                .setParameter("atividade", atividade)
                .setParameter("aluno", aluno);
        var resultado = query.getResultList();
        return resultado.isEmpty() ? null : (AlunoAtividade) resultado.get(0);
    }
}
