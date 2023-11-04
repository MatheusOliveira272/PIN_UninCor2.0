package br.com.unincor.webSite.model.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "respostas_alunos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RespostaAluno implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private QuestaoFechada respQuestaoFechada;
    private String respQuestaoAberta;

//    @ManyToOne
//    @JoinColumn(name = "id_questao_aberta")
//    private QuestaoAberta questaoAberta;
    
    @ManyToOne
    @JoinColumn(name = "id_questao_fechada")
    private QuestaoFechada questaoFechada;
    
    
    @ManyToOne
    @JoinColumn(name = "id_resposta_aluno")
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "id_resposta_alunos")
    private Atividade atividade;
    
    @ManyToOne
    @JoinColumn(name = "id_questao")
    private Questao questao;
}
