package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AlunoDao;
import br.com.unincor.webSite.model.domain.Aluno;
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
public class BeanAluno implements Serializable {

    private List<Aluno> alunos = new ArrayList<>();
    private Aluno aluno;
    private String confirmarSenha;
    private String senhaLogin;
    private String nomeLogin;

    public BeanAluno() {
        aluno = new Aluno();

    }

    public void salvar() {
        if (!aluno.getSenha().equals(confirmarSenha)) {
            Mensagens.erro(FacesContext.getCurrentInstance(), "As senhas informadas não conferem!");
            return;
        }
        var senha = aluno.getSenha();
        var senhaCriptografada = Criptografar.encryp(senha);
        aluno.setSenha(senhaCriptografada);
        new AlunoDao().save(aluno);
        aluno = new Aluno();
        buscar();
        PrimeFaces.current().executeScript("PF('dlg3').hide()");//fechar o dialog 
    }

    public void cancelar() {
        this.aluno = null;
    }

    public void limparTabela() {
        alunos.clear();
    }

    public void buscar() {
        this.alunos = new AlunoDao().findAll();

    }

    public void editar(Aluno aluno) {
        this.aluno = aluno;
    }

    public void verificaSenha() {
        var alunoLogado = new AlunoDao().buscarAlunoPorLogin(nomeLogin);
        if (!alunoLogado.getSenha().equals(Criptografar.encryp(senhaLogin))) {
            Mensagens.erro(FacesContext.getCurrentInstance(), "Login e/ou senha incorretos!");
            return;
        }

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        request.getSession(true).setAttribute("alunoLogado",
                alunoLogado);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.setAttribute("alunoId", alunoLogado.getId());

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/pages/aluno/inicio_aluno.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BeanAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        session.removeAttribute("alunoLogado");
        session.invalidate();
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/pages/login_aluno.jsf");
        } catch (IOException ex) {
            Logger.getLogger(BeanAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
