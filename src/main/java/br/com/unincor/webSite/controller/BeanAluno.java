package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AlunoDao;
import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.domain.Aluno;
import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.utils.Criptografar;
import br.com.unincor.webSite.view.utils.Mensagens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;


@ManagedBean
@SessionScoped
@Getter
@Setter
public class BeanAluno implements Serializable {

    private List<Aluno> alunos = new ArrayList<>();
    private Aluno aluno;
    private boolean editando = false;

    public BeanAluno() {
        aluno = new Aluno();

    }
  
        
     public void salvar() {
        new AlunoDao().save(aluno);
        cancelar();
        buscar();
        editando = false;
    }

  
    public void limparTabela() {
        alunos.clear();
    }

    public void editar(Aluno aluno) {
        this.aluno = aluno;
    }

    public void novo() {
        this.aluno = new Aluno();
     //  Aluno aluno = new Aluno(null, null, null, null, null, null);
      // AlunoDao alunoDao = new AlunoDao();
      // aluno = alunoDao.save(aluno);

    }

    public void cancelar() {
        this.aluno = null;
    }

   

    public void buscar() {
        this.alunos = new AlunoDao().findAll();

    }
    
    
    
}
