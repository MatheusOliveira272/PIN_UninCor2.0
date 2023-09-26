package br.com.unincor.webSite.core;


import br.com.unincor.webSite.model.domain.Aluno;
import br.com.unincor.webSite.model.domain.AlunoAtividade;
import br.com.unincor.webSite.model.domain.Anexo;
import br.com.unincor.webSite.model.domain.Atividade;
import br.com.unincor.webSite.model.domain.ClassificaQuestao;
import br.com.unincor.webSite.model.domain.Disciplina;
import br.com.unincor.webSite.model.domain.Professor;
import br.com.unincor.webSite.model.domain.Questao;
import br.com.unincor.webSite.model.domain.QuestaoAberta;
import br.com.unincor.webSite.model.domain.QuestaoFechada;
import br.com.unincor.webSite.model.domain.RespostaAluno;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;


/**
 *testesss
 * @author alunos
 * 
 * 
 */
public class HibernateManager {
   
    private static Session session;
    
    public static Session getSession(){
        if(session == null){
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            //se tiver mais classer só colocar .adde ir adicionadno
            Metadata md = new MetadataSources(ssr)
                    .addAnnotatedClass(Professor.class)
                    .addAnnotatedClass(Aluno.class)
                    .addAnnotatedClass(Anexo.class)
                    .addAnnotatedClass(AlunoAtividade.class)
                    .addAnnotatedClass(Atividade.class)
                    .addAnnotatedClass(ClassificaQuestao.class)
                    .addAnnotatedClass(Disciplina.class)
                    .addAnnotatedClass(Questao.class)
                    .addAnnotatedClass(QuestaoAberta.class)
                    .addAnnotatedClass(QuestaoFechada.class)
                    .addAnnotatedClass(RespostaAluno.class)
                    .getMetadataBuilder().build();
            SessionFactory sessionFactory = md.getSessionFactoryBuilder().build();
            session = sessionFactory.getCurrentSession();
        }
        return session;
    }
    
    public static EntityManager geEntityManager(){
        Session mySession = getSession();
        if(!mySession.getTransaction().isActive()){
            mySession.beginTransaction();
        }
        return mySession.getEntityManagerFactory().createEntityManager();
    }
    
    
    
}
