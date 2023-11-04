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
    
     private String codigo = "39af7";
     private List<Questao> questoes;
     
     private Questao questaoAtual;
     private Integer indexListQuestoes;
     
     @PostConstruct
     public void init(){
         questoes = new ArrayList<>();
         questoes = new QuestaoDao().buscarQuestoesPorCodigoAtividade(codigo);
         indexListQuestoes = 0;
     }
     
     public Questao getQuestaoAtual(){
         return questoes.get(indexListQuestoes);
     }
    
     public void proxQuestao(){
         if(questoes.isEmpty()){
             System.out.println("A lista está vazia");
             return;
         }else{
             if(questaoAtual.getTipoQuestao() == true){
                 //colocar a lógica aqui
             }else{
                //colocar a outra lógica
             }
             indexListQuestoes++;
             if(questoes.get(indexListQuestoes)!= null){
                 this.questaoAtual=questoes.get(indexListQuestoes);
             }
         }
     }
     
     public void antQuestao(){
         if(indexListQuestoes <= 0){
             return;
         }else{
             indexListQuestoes--;
             this.questaoAtual=questoes.get(indexListQuestoes);
         }
     }
     
//    
    
}