package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.utils.Criptografar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Getter;
import lombok.Setter;


@ManagedBean
@SessionScoped
@Getter
@Setter
public class BeanProfessor implements Serializable {

    private List<Professor> professores = new ArrayList<>();
    private Professor professor;
    private boolean editando = false;

    public BeanProfessor() {
        professor = new Professor();

    }

    
    public void salvar() {
        var senha = professor.getSenha();
        Criptografar criptografar = new Criptografar();
        var senhaCriptografada = criptografar.encryp(senha);
        professor.setSenha(senhaCriptografada);
        new ProfessorDao().save(professor);
        cancelar();
        buscar();
        editando = false;
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
        editando = true;
    }
}
