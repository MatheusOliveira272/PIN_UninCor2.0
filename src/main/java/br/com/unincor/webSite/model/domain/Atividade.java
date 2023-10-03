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
@EqualsAndHashCode(of = "id")
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

    @ManyToMany /* Ajustar este relacionamento */
    private Set<Questao> questoes = new HashSet<>();

    @OneToMany(mappedBy = "atividade")
    private Set<AlunoAtividade> alunoAtividade;

    @ManyToOne
    @JoinColumn(name = "id_resposta_alunos")
    private RespostaAluno respostaAluno;
}
