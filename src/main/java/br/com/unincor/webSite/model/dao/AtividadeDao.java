package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.model.domain.Aluno;
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
    public List<Atividade> buscarAtividadesProfessorPorLoginComQuestoes(Professor prof) {
        String sql = "SELECT a FROM Atividade a JOIN a.disciplinas d  left join fetch a.questoes WHERE d.professor = :professor";

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

    public List<Aluno> buscarAlunoPorAtividade(String codigo) {
//        var atividade = buscarAtividadeCodigo(codigo);
        String sql = "select aa.aluno from AlunoAtividade aa where aa.atividade.codigo = :codigo";

        Query query = getEntityManager().createQuery(sql, Aluno.class)
                .setParameter("codigo", codigo);
        return query.getResultList();
    }
    
    public List<Aluno> buscarAlunoAtividadePorAlunoLogado(Aluno aluno) {
//        var atividade = buscarAtividadeCodigo(codigo);
        String sql = "from AlunoAtividade aa where aa.aluno = :aluno";

        Query query = getEntityManager().createQuery(sql, Aluno.class)
                .setParameter("aluno", aluno);
        return query.getResultList();
    }
    

}
