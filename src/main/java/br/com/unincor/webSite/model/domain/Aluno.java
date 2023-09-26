/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.model.domain;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id") //identifica o objeto por meio de um atributo
public class Aluno implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String codigo;

    @ManyToMany
    @JoinTable
            (
                    name = "alunos_disciplinas",
                    joinColumns = @JoinColumn(name = "aluno_id"),
                    inverseJoinColumns = @JoinColumn(name = "disciplina_id")
            )
    private Set<Disciplina> disciplinas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_aluno_atividade")
    private AlunoAtividade alunoAtividade;
    @ManyToOne
    @JoinColumn(name = "id_resposta_alunos")
    private RespostaAluno respostaAluno;

    
}
