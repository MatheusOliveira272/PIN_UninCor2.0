/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
//@Entity
//@Table(name = "questoes_aberta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //identifica o objeto por meio de um atributo
public class QuestaoAberta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resposta;

//    @ManyToOne
//    @JoinColumn(name = "id_questao")
//    private Questao questao;

//    @OneToMany(mappedBy = "questaoAberta")
//    private Set<RespostaAluno> respostaAluno = new HashSet<>();
}

