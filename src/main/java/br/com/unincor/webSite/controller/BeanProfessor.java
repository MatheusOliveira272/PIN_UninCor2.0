package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.utils.Criptografar;
import br.com.unincor.webSite.view.utils.Mensagens;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        if (!professor.getSenha().equals(confirmarSenha)) {
            Mensagens.erro(FacesContext.getCurrentInstance(), "As senhas informadas não conferem!");
            return;
        }
        var senha = professor.getSenha();
        var senhaCriptografada = Criptografar.encryp(senha);
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

    public Long retornaProfessorId() {
        return this.professor.getId();
    }

    public void editar(Professor professor) {
        this.professor = professor;
    }

    public void verificaSenha() {
        //Mudar nome do método para login
        var professorLogado = new ProfessorDao().buscarProfessorPorLogin(nomeLogin);
        if (!professorLogado.getSenha().equals(Criptografar.encryp(senhaLogin))) {
            Mensagens.erro(FacesContext.getCurrentInstance(), "Login e/ou senha incorretos!");
            return;
        }

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        request.getSession(true).setAttribute("professorLogado",
                professorLogado);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.setAttribute("professorId", professorLogado.getId());

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/pages/professor/inicio_professor.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BeanProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        session.removeAttribute("professorLogado");
        session.invalidate();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/pages/login.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BeanProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
