package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AlunoDao;
import br.com.unincor.webSite.model.dao.QuestaoDao;
import br.com.unincor.webSite.model.dao.RespostaAlunoDao;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import br.com.unincor.webSite.model.domain.RespostaAluno;
import java.io.Serializable;
import java.util.ArrayList;
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
public class BeanRespostaAluno implements Serializable {

    private String codigo = "617ab";
    private List<Questao> questoes;

    private Questao questaoAtual;
    private Integer indexListQuestoes;
    private QuestaoFechada respQuestaoFechada;
    private String respQuestaoAberta;
    private List<RespostaAluno> respostaAlunosProvisoria;

    @PostConstruct
    public void init() {
        questoes = new ArrayList<>();
        questoes = new QuestaoDao().buscarQuestoesPorCodigoAtividade(codigo);
        indexListQuestoes = 0;
    }

    public Questao getQuestaoAtual() {
        return questoes.get(indexListQuestoes);
    }
//     

    public void adicionaAlunoResposta(Questao questao) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        var alunoLogado = new AlunoDao().findById((Integer) session.getAttribute("alunoId"));
        var atividade = questao.getAtividades().get(0);
        if (questao.getTipoQuestao() == true) {
            var respostaAluno = new RespostaAluno(null, null, respQuestaoFechada, alunoLogado, atividade, questao);
            respostaAlunosProvisoria.add(respostaAluno);
//             new RespostaAlunoDao().save(respostaAluno);
        } else {
            var respostaAluno = new RespostaAluno(null, respQuestaoAberta, null, alunoLogado, atividade, questao);
            respostaAlunosProvisoria.add(respostaAluno);
//             new RespostaAlunoDao().save(respostaAluno);

        }

    }

    public List<QuestaoFechada> getAlternativasQuestãoFechada(Questao questao) {
        return new RespostaAlunoDao().recuperaDescricaoQuestaoFechada(questao);
    }
     
    public void salvaRespostasAluno(){
        for (int i = 0; i < respostaAlunosProvisoria.size(); i++) {
            new RespostaAlunoDao().save(respostaAlunosProvisoria.get(i));
        }
    }





//     public void proxQuestao(){
//         if(questoes.isEmpty()){
//             System.out.println("A lista está vazia");
//             return;
//         }else{
//             if(questaoAtual.getTipoQuestao() == true){
//                 //colocar a lógica aqui
//             }else{
//                //colocar a outra lógica
//             }
//             indexListQuestoes++;
//             if(questoes.get(indexListQuestoes)!= null){
//                 this.questaoAtual=questoes.get(indexListQuestoes);
//             }
//         }
//     }
//     
//     public void antQuestao(){
//         if(indexListQuestoes <= 0){
//             return;
//         }else{
//             indexListQuestoes--;
//             this.questaoAtual=questoes.get(indexListQuestoes);
//         }
//     }
//    
}
