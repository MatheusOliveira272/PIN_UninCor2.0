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
@ToString
@EqualsAndHashCode(of = "id")
public class RespostaAluno implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer respQuestaoFechada;
    private String respQuestaoAberta;

    @OneToMany
    private Set<QuestaoAberta> questoesAbertas = new HashSet<>();
    @OneToMany
    private Set<QuestaoFechada> questoesFechadas = new HashSet<>();
    
    
    @ManyToOne
    @JoinColumn(name = "id_resposta_aluno")
    private Aluno aluno;
    @OneToMany
    private Set<Atividade> atividades = new HashSet<>();
    @OneToMany
    private Set<Questao> questoes = new HashSet<>();
}
