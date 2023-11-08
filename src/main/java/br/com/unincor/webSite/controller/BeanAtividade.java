package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AlunoAtividadeDao;
import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.dao.DisciplinaDao;
import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.dao.RespostaAlunoDao;
import br.com.unincor.webSite.model.domain.Aluno;
import br.com.unincor.webSite.model.domain.AlunoAtividade;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Disciplina;
import br.com.unincor.webSite.model.domain.RespostaAluno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class BeanAtividade implements Serializable {

    private List<Atividade> atividades = new ArrayList<>();
    private Atividade atividade;
    Random rand = new Random();
    private Disciplina disciplinaSelecionada;

    @PostConstruct
    public void init() {
        buscar();
    }

    public void salvar() {
        // Verifica se uma disciplina foi selecionada
        if (disciplinaSelecionada != null) {
            atividade.getDisciplinas().clear(); // Remove todas as disciplinas associadas
            atividade.getDisciplinas().add(disciplinaSelecionada); // Adiciona a nova disciplina selecionada
        }

        atividade.setCodigo(gerarCodigoAleatorio(5));

        new AtividadeDao().save(atividade);
        cancelar();
        buscar();
    }

    public void limparTabela() {
        atividades.clear();
    }

    public void editar(Atividade atividade) {
        this.atividade = atividade;
        // Obtem a primeira disciplina associada à atividade
        this.disciplinaSelecionada = atividade.getDisciplinas().get(0);
    }

    public void novo() {
        this.atividade = new Atividade();

    }

    public void cancelar() {
        this.atividade = null;
    }

    public void remover(Atividade atividade) {
        System.out.println(atividade.getNome());
        new AtividadeDao().delete(atividade.getId());

        buscar();

    }

    public String gerarCodigoAleatorio(int tamanho) {
        Random rand = new Random();
        StringBuilder codigo = new StringBuilder();
        String caracteres = "123456789abcdef"; // Caracteres hexadecimais

        for (int i = 0; i < tamanho; i++) {
            int indice = rand.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }

        return codigo.toString();
    }

    private List<Disciplina> disciplinas = new ArrayList<>();

    public List<Disciplina> getDisciplinas() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        return this.disciplinas = new DisciplinaDao().buscarDisciplinasProfessorPorLogin(professorLogado);

    }

    public void buscar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        this.atividades = new AtividadeDao().buscarAtividadesProfessorPorLogin(professorLogado);
    }

    private String codigo;

    public List<AlunoAtividade> getAluno() {
        var atividadeCodigo = new AtividadeDao().buscarAtividadeCodigo(codigo);
        if (atividadeCodigo != null) {
            var alunos = new AlunoAtividadeDao().retornaAlunoAtividadePorAtividade(atividadeCodigo);
            return (List<AlunoAtividade>) alunos;
        }
        return null;
    }
    
   private List<RespostaAluno> respostasAlunoSelecionado;

   public List<RespostaAluno> recuperaRespostasAluno(Atividade atividade, Aluno aluno){
       System.out.println("cai aqui");
    respostasAlunoSelecionado = new RespostaAlunoDao().recuperaAtividadeAluno(atividade, aluno);
       System.out.println("lista" + respostasAlunoSelecionado);
    return respostasAlunoSelecionado;  
}

}