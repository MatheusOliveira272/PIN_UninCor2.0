/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.model.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "atividades")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id") //identifica o objeto por meio de um atributo
public class Atividade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;

    @ManyToMany
    @JoinTable
            (
                    name = "atividades_disciplinas",
                    joinColumns = @JoinColumn(name = "atividade_id"),
                    inverseJoinColumns = @JoinColumn(name = "disciplina_id")
            )
    private List<Disciplina> disciplinas = new ArrayList<>();

    @ManyToMany
    private Set<Questao> questoes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_aluno_atividade")
    private AlunoAtividade alunoAtividade;

    @ManyToOne
    @JoinColumn(name = "id_resposta_alunos")
    private RespostaAluno respostaAluno;
}
