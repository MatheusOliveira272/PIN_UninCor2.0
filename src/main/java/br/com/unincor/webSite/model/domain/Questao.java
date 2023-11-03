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

@Getter
@Setter
@Entity
@Table(name = "questoes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //identifica o objeto por meio de um atributo
public class Questao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enunciado;
    @Column (name = "tipo_questao")
    private Boolean tipoQuestao;
    private String tipo;

    @ManyToMany
    @JoinTable(
            name = "questoes_atividades",
            joinColumns = @JoinColumn(name = "questao_id"),
            inverseJoinColumns = @JoinColumn(name = "atividade_id")
    )
    private List<Atividade> atividades = new ArrayList<>();
    

    @ManyToMany
    @JoinTable
            (
                    name = "questoes_classificacoes",
                    joinColumns = @JoinColumn(name = "questao_id"),
                    inverseJoinColumns = @JoinColumn(name = "classificacoes_questoes_id")
            )
    private Set<ClassificaQuestao> classificaQuestoes = new HashSet<>();

    
    @OneToMany(mappedBy = "questao")
    private List<QuestaoFechada> questaoFechadas = new ArrayList<>();

    @OneToMany(mappedBy = "questao")
    private Set<RespostaAluno> respostasAlunos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_anexo")
    private Anexo anexo;
}