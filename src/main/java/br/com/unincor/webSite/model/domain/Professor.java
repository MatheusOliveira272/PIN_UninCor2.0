package br.com.unincor.webSite.model.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
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
@Entity
@Table(name = "professores")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Professor implements Serializable, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "professor")
    private Set<Disciplina> disciplinas = new HashSet<>();

    @ManyToOne
    @JoinTable(name = "id_anexo")
    private Anexo imagemPerfil;

    @Override
    public String getName() {
        return email;
    }

    public Path getPathImagemPerfil() {
        String caminhoBase = "";        
        return Paths.get(caminhoBase, imagemPerfil.getNome());
    }
}
