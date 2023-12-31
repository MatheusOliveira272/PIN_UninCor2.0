package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AlunoDao;
import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.dao.RespostaAlunoDao;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import br.com.unincor.webSite.model.domain.RespostaAluno;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanRespostaAluno implements Serializable {

    private String codigo;
    private List<Questao> questoes;
    //private Questao questaoAtual;
    private Integer indexListQuestoes;
    private QuestaoFechada respQuestaoFechada;
    private String respQuestaoAberta;
    private List<RespostaAluno> respostaAlunosProvisoria;
    private RespostaAluno resp;
    //private String page;
     private boolean skip;

    @PostConstruct
    public void init() {
        // Recupera o valor de 'codigo' da sessão
        codigo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("codigo");
        System.out.println("codResp " + codigo);
        questoes = new ArrayList<>();
        questoes = new QuestaoDao().buscarQuestoesPorCodigoAtividade(codigo);
        indexListQuestoes = 0;
        respostaAlunosProvisoria = new ArrayList<>();
        respQuestaoAberta = "";

    }

    public Questao getQuestaoAtual() {
        return questoes.get(indexListQuestoes);
    }

    public void adicionaAlunoResposta(QuestaoFechada qf) {
        System.out.println("--------------");
        System.out.println(qf.getDescricao());
        System.out.println("--------------");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var alunoLogado = new AlunoDao().findById((Integer) session.getAttribute("alunoId"));
        var atividade = qf.getQuestao().getAtividades();

        if (qf.getQuestao().getTipoQuestao()) {
            // Obtém a alternativa selecionada
            QuestaoFechada alternativaSelecionada = respQuestaoFechada;

            var respostaAluno = new RespostaAluno(null, null, qf, alunoLogado, null, qf.getQuestao());
            System.out.println("resp aluno" + respostaAluno.getQuestaoFechada());
            respostaAlunosProvisoria.add(respostaAluno);
        }
//        } else {
//            var respostaAluno = new RespostaAluno(null, respQuestaoAberta, null, alunoLogado, atividade, questao);
//            respostaAlunosProvisoria.add(respostaAluno);
//        }
    }

    public void adicionaAlunoRespostaAberta(Questao ques) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var alunoLogado = new AlunoDao().findById((Integer) session.getAttribute("alunoId"));
        var atividade = ques.getAtividades();

        var respostaAluno = new RespostaAluno(null, respQuestaoAberta, null, alunoLogado, null, ques);

        System.out.println("----------------");
        System.out.println("resposta questão aberta " + respQuestaoAberta);
        System.out.println("----------------");
        respostaAlunosProvisoria.add(respostaAluno);
    }

    public List<QuestaoFechada> getAlternativasQuestãoFechada(Questao questao) {
        return new RespostaAlunoDao().recuperaDescricaoQuestaoFechada(questao);
    }

    public void salvaRespostasAluno() {
        System.out.println("cai no salva, lista: " + respostaAlunosProvisoria);
        for (int i = 0; i < respostaAlunosProvisoria.size(); i++) {

            new RespostaAlunoDao().save(respostaAlunosProvisoria.get(i));

        }
                 FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    try {
        externalContext.redirect(externalContext.getRequestContextPath() + "/pages/aluno/inicio_aluno.jsf");
    } catch (IOException e) {
        e.printStackTrace(); // Trate o erro conforme necessário
    }
       
    }
// 
       public String retorna(){
             System.out.println("Redirecionando para a página inicial.");
        return "/aluno/inicio_aluno.jsf?faces-redirect=true";
    }
    
     public String onFlowProcess(FlowEvent event) {
       
        if (skip) {
            skip = false; //reset in case user goes back
             System.out.println("oi1");
            return "confirm";
           
        }
        else {
         //   adicionaAlunoRespostaAberta(ques);
            return event.getNewStep();
        }
    }
    


}
