package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.DisciplinaDao;
import br.com.unincor.webSite.model.domain.Disciplina;
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
public class BeanDisciplina implements Serializable {

    private List<Disciplina> disciplinas = new ArrayList<>();
    private Disciplina disciplina;

    public BeanDisciplina() {
        disciplina = new Disciplina();

    }

    public void salvar() {
        /*  if(!professor.getSenha().equals(confirmarSenha)) {
            Mensagens.erro(FacesContext.getCurrentInstance(), "As senhas informadas não conferem!");
            return;
        }*/
        new DisciplinaDao().save(disciplina);
        cancelar();
        buscar();

    }

    public void cancelar() {
        this.disciplina = null;
    }

    public void limparTabela() {
        disciplinas.clear();
    }

    public void buscar() {
        this.disciplinas = new DisciplinaDao().findAll();

    }

    public void editar(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
