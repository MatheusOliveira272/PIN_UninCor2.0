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
@Table(name = "disciplinas")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //identifica o objeto por meio de um atributo
public class Disciplina implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private Boolean enable;

    @ManyToMany(mappedBy = "disciplinas")
    private Set<Aluno> alunos = new HashSet<>();

    @ManyToMany(mappedBy = "disciplinas")
    private Set<Atividade> atividades = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
//    private Set<Professor> professores = new HashSet<>();

    @Override
    public String toString() {
        return nome;
    }
    
    
 }
