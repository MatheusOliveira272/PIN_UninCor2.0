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

@Getter
@Setter
@Entity
@Table(name = "questoes_fechada")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //identifica o objeto por meio de um atributo
public class QuestaoFechada implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String ordem;
    private Boolean correto;
    private Boolean enable;

    @ManyToOne
    @JoinColumn(name = "id_questoes")
    private Questao questao;

    @OneToMany(mappedBy = "questaoFechada")
    private Set<RespostaAluno> respostasAlunos = new HashSet<>();
}
