package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.domain.Questao;
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

    private String codigo ="39af7";
    List<Questao> questoes = new QuestaoDao().buscarQuestoesPorCodigoAtividade(codigo);

    
    @PostConstruct
    public void init() {
        questoes = new ArrayList<>();
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
    
    public String onFlowProcess(FlowEvent event) {
        // Este método será chamado ao navegar entre as etapas do wizard
        // Aqui você pode adicionar lógica adicional, se necessário
        return event.getNewStep();
    }

    // Adicione getters e setters conforme necessário
    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }
    
}
