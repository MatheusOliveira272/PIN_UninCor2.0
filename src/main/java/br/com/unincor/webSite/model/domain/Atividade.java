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
@Table(name = "atividades")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atividade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;
    private String codigo;
    private Boolean enable;

    @ManyToMany
    @JoinTable(
            name = "atividades_disciplinas",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();

    @ManyToMany(mappedBy = "atividades")
//    @JoinTable(
//            name = "questoes_atividades",
//            joinColumns = @JoinColumn(name = "atividade_id"),
//            inverseJoinColumns = @JoinColumn(name = "questoes_id")
//    )
    private Set<Questao> questoes = new HashSet<>();

    @OneToMany(mappedBy = "atividade")
    private Set<AlunoAtividade> alunoAtividade;

    @OneToMany(mappedBy = "atividade")
    private Set<RespostaAluno> respostasAlunos;
    
   
}
