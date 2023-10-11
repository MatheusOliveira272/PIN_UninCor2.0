package br.com.unincor.webSite.model.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "alunos_atividades")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") 
public class AlunoAtividade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
        
    @ManyToOne
    @JoinColumn(name = "id_atividade")
    private Atividade atividade;
    
}
