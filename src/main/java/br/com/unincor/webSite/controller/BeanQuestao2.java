package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.dao.QuestaoFechadaDao;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import br.com.unincor.webSite.model.domain.TipoQuestão;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
public class BeanQuestao2 implements Serializable {

    private List<Questao> questoes = new ArrayList<>();
    private Questao questao;
    private Atividade atividade;
//    private Questao questao = new Questao(); // Inicialize a questão corretamente

    private String tipoQuestao;
    private List<String> tiposQuestao;

    private QuestaoFechada questaoFechada;
    private List<QuestaoFechada> questoesFechadas;

    @PostConstruct
    public void init() {
        questaoFechada = new QuestaoFechada();
        questoesFechadas = new ArrayList<>();
        buscar();
    }

    public void salvar() {
//        questao.getAtividades().add(atividade);        
        var questaoSalva = new QuestaoDao().save(questao);
        if(!questoesFechadas.isEmpty()) {
            questoesFechadas.forEach(q -> q.setQuestao(questaoSalva));
        }
//        new QuestaoDao().save(questao);
//        new QuestaoFechadaDao().salvaAlernativasQuestoaFechada(questoesFechadas);
        
        
       
        new AtividadeDao().save(atividade);

        /*if (atividade != null) {
            questao.getAtividades().clear();
            questao.getAtividades().add(atividade);
        }*/

        //new QuestaoFechadaDao().salvaAlernativasQuestoaFechada(questoesFechadas);
        
        
        
        
        /* Primeiro vincular a questão na atividade e salvar a atividade */
        
        /* Se não salvar as questões fechadas vcs vão precisar fazer um for na lista de questao,
        fechadas e salvar vinculando a questão que está sendo criada,  */
        
//        new QuestaoFechadaDao().setaIdQuestao(questao, questoesFechadas); -> Tentativa de associar questão a questão fechada
        //Não estamos conseguindo associar as alternativas  à questão fechada
        /*
        Pensamos em colocar aqui um método que acesse as lista de questõesFechadas
        (lista de alternativas de uma questão de tipo fechada)  e adicione o ID da 
        questão na questão fechada
         */
        cancelar();
        buscar();
    }

    public void limparTabela() {
        questoes.clear();
    }

    public void editar(Questao questao) {
        this.questao = questao;
    }

    public void novo() {
        this.questao = new Questao();

    }

    public void cancelar() {
        this.questao = null;
    }
    
    public void cancelarAlternativa() {
        
    }

    public void remover(Questao questao) {

        new QuestaoDao().delete(questao.getId());
        buscar();

    }

    public void buscar() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        this.questoes = new QuestaoDao().buscarQuestaosProfessorPorLogin(professorLogado);
    }

    public List<TipoQuestão> getTipoQuestao() {
        return Arrays.asList(TipoQuestão.values());
    }

    // lista de atividade 
    private List<Atividade> atividades = new ArrayList<>();

    public List<Atividade> getAtividades() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var professorLogado = new ProfessorDao().findById((Long) session.getAttribute("professorId"));
        return this.atividades = new AtividadeDao().buscarAtividadesProfessorPorLoginComQuestoes(professorLogado);

    }

    //Aqui para baixo estou adicionando as alternativas da questão fechada à questão 
    public void adicionarQuestaoFechada() {
        questoesFechadas.add(questaoFechada);
        questaoFechada = new QuestaoFechada(); // Cria uma nova instância para a próxima questão fechada
    }

//    public void salvarQuestoesFechadas() {
//        // Salva as questões fechadas no banco de dados
//        for (QuestaoFechada qf : questoesFechadas) {
//            questao.setTipoQuestao(true); // Define o tipo de questão como fechada
//            qf.setDescricao(questao.getEnunciado());
//           
//            new QuestaoFechadaDao().save(qf);
//        }
//
//        cancelar();
//    }
}
