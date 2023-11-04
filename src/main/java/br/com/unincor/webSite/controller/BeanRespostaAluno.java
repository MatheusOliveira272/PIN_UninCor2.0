package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import br.com.unincor.webSite.model.domain.RespostaAluno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanRespostaAluno implements Serializable {

    private String codigo = "243cc";
    List<Questao> questoes = new ArrayList<>();
    List<RespostaAluno> respostasAlunos = new ArrayList<>();
    private Boolean skip;
    public int currentStep = 0;
    private String respQuestaoAberta;
    private QuestaoFechada respQuestaoFechada; 
//            new QuestaoDao().buscarQuestoesPorCodigoAtividade(codigo);

    @PostConstruct
    public void init() {
        questoes = new ArrayList<>();
        respostasAlunos = new ArrayList<>();
    }

    //private List<Questao> questoes = new ArrayList<>();
    private List<String> respostas = new ArrayList<>(); // Lista para armazenar as respostas (você pode alterar o tipo conforme necessário)

    public List<Questao> listaQuestoes() {
        // Carregue aqui as questões para o aluno responder
        // Por exemplo:
        System.out.println("1");

        questoes = new QuestaoDao().buscarQuestoesPorCodigoAtividade(codigo); // Substitua pelo método apropriado para obter as questões
        System.out.println("Lista questoes" + questoes);
        return questoes;

    }

    public void next() {
        if (currentStep < questoes.size() - 1) {
            currentStep++;
        }
    }

    public void previous() {
        if (currentStep > 0) {
            currentStep--;
        }
    }

    // Adicione getters e setters conforme necessário
    public List<String> getRespostas() {
        return respostas;
    }

    public String getCurrentQuestion() {
        return questoes.get(currentStep).getEnunciado();
    }

    public RespostaAluno getCurrentAnswer() {
        return respostasAlunos.size() > currentStep ? respostasAlunos.get(currentStep) : null;
    }

    public void setCurrentAnswer(RespostaAluno rp) {
        var questao = new QuestaoDao().buscarQuestoesPorCodigoAtividade(codigo);
        if (questao.get(currentStep).getTipoQuestao() != true) {
            if (respostasAlunos.size() > currentStep) {
                rp.setRespQuestaoAberta(respQuestaoAberta);
            } else {
                respostasAlunos.add(rp);
            }
        } else {
           if (respostasAlunos.size() > currentStep) {
               rp.setRespQuestaoFechada(respQuestaoFechada);
           }else {
                respostasAlunos.add(rp);
            }
        }

    }

//    public void setRespostas(List<String> respostas) {
//        this.respostas = respostas;
//    }
    public String onFlowProcess(FlowEvent event) {
        List<Questao> listaQuestoes = listaQuestoes();
        System.out.println("aqui");
        System.out.println(listaQuestoes);
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();

        }
    }

}
