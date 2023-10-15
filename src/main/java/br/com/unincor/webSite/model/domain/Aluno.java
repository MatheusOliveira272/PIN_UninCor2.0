package br.com.unincor.webSite.model.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") 
public class Aluno implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @ManyToMany
    @JoinTable
            (
                    name = "alunos_disciplinas",
                    joinColumns = @JoinColumn(name = "aluno_id"),
                    inverseJoinColumns = @JoinColumn(name = "disciplina_id")
            )
    private Set<Disciplina> disciplinas = new HashSet<>();

    @OneToMany(mappedBy = "aluno")
    private Set<AlunoAtividade> alunoAtividade;
    
    @OneToMany(mappedBy = "aluno")
    private Set<RespostaAluno> respostaAluno;

    
}
