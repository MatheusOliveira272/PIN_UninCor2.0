package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.ProfessorDao;
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
public class BeanProfessor implements Serializable {

    private List<Professor> professores = new ArrayList<>();
    private Professor professor;
    
    private String confirmarSenha;
    private String senhaLogin;
    private String nomeLogin;

    public BeanProfessor() {
        professor = new Professor();

    }

    
    public void salvar() {
        if(!professor.getSenha().equals(confirmarSenha)) {
            Mensagens.erro(FacesContext.getCurrentInstance(), "As senhas informadas não conferem!");
            return;
        }
        var senha = professor.getSenha();
        Criptografar criptografar = new Criptografar();
        var senhaCriptografada = criptografar.encryp(senha);
        professor.setSenha(senhaCriptografada);
        new ProfessorDao().save(professor);
        professor = new Professor();
        buscar();
        PrimeFaces.current().executeScript("PF('dlg3').hide()");//fechar o dialog 
    }
    
    public void cancelar() {
        this.professor = null;
    }

    public void limparTabela() {
        professores.clear();
    }
    
    public void buscar() {
        this.professores = new ProfessorDao().findAll();

    }
    
    public void editar(Professor professor){
        this.professor = professor;
    }
    
    
    public void verificaSenha(String nome, String senha){
         if(!professor.getSenha().equals(senhaLogin) && professor.getName().equals(nomeLogin) ){
            Mensagens.erro(FacesContext.getCurrentInstance(), "os dados informadas não conferem!");
            return;
        }
      
        buscar();
        PrimeFaces.current().executeScript("PF('dlg3').hide()");//fechar o dialog 
    }
}
