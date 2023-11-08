/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AlunoAtividadeDao;
import br.com.unincor.webSite.model.dao.AlunoDao;
import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.domain.Aluno;
import br.com.unincor.webSite.model.domain.AlunoAtividade;
import br.com.unincor.webSite.model.domain.Atividade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanAlunoAtividade implements Serializable {
    
    private String codigo = "";
    private List<Aluno> alunos;
    private Aluno aluno;
    private Double nota;
    private BeanRespostaAluno beanRespostaAluno;
    
    public List<Aluno> getAlunos() {
        return alunos;
    }

//    public void setBeanRespostaAluno(BeanRespostaAluno beanRespostaAluno) {
//        this.beanRespostaAluno = beanRespostaAluno;
//    }
   public String adicionarAlunoEAtualizarCodigo() {
    adicionaAlunoAtividade();
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codigo", codigo);
    return "/pages/aluno/resposta_atividade.jsf?faces-redirect=true";
}

    
    public void adicionaAlunoAtividade() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var alunoLogado = new AlunoDao().findById((Integer) session.getAttribute("alunoId"));
        var atividadeCodigo = new AtividadeDao().buscarAtividadeCodigo(codigo);
        var alunoAtividade = new AlunoAtividade(null, null, alunoLogado, atividadeCodigo);
        AlunoAtividadeDao alunoAtividadeDao = new AlunoAtividadeDao();
        alunoAtividadeDao.save(alunoAtividade);

//      
    }
    
    public void buscarAlunoAtividade(String codigo) {
        this.alunos = new AtividadeDao().buscarAlunoPorAtividade(codigo);
    }
    
    public Double buscaNotaAlunoAtividadePorAluno(Aluno aluno, Atividade atividade) {
        var alunoAtividade = new AlunoAtividadeDao().buscaAlunoAtividadeNota(aluno, atividade);
        return alunoAtividade.getNota();
    }
}
