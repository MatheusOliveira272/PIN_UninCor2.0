package br.com.unincor.webSite.controller;

import br.com.unincor.webSite.model.dao.AtividadeDao;
import br.com.unincor.webSite.model.dao.DisciplinaDao;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.Disciplina;
import static jakarta.persistence.GenerationType.UUID;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class BeanAtividade implements Serializable{
    
    private List<Atividade> atividades = new ArrayList<>();
    private Atividade atividade;
    Random rand = new Random();

    @PostConstruct
    public void init() {
        buscar();
    }

    public void salvar() {
       atividade.setCodigo(gerarCodigoAleatorio(5));
         new AtividadeDao().save(atividade);
          cancelar();
        buscar();
    }

    public void limparTabela() {
        atividades.clear();
    }

    public void editar(Atividade atividade) {
        this.atividade = atividade;
    }

    public void novo() {
        this.atividade = new Atividade();

    }

    public void cancelar() {
        this.atividade = null;
    }

    public void remover(Atividade atividade) {
       
        new AtividadeDao().delete(atividade.getId());
        buscar();
      

    }
    
    public void buscar() {
        this.atividades = new AtividadeDao().findAll();

    }
    
    
 public String gerarCodigoAleatorio(int tamanho) {
    Random rand = new Random();
    StringBuilder codigo = new StringBuilder();
    String caracteres = "123456789abcdef"; // Caracteres hexadecimais

    for (int i = 0; i < tamanho; i++) {
        int indice = rand.nextInt(caracteres.length());
        codigo.append(caracteres.charAt(indice));
    }

    return codigo.toString();
}
    }
  
