/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.model;

import br.com.unincor.webSite.model.dao.DisciplinaDao;
import br.com.unincor.webSite.model.dao.ProfessorDao;
import br.com.unincor.webSite.model.domain.Aluno;
import br.com.unincor.webSite.model.domain.Disciplina;
import br.com.unincor.webSite.model.domain.Professor;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luiz
 */
public class main {
    public static void main(String[] args) {
        DisciplinaDao disciplinaDao = new DisciplinaDao();
        Professor professor = new Professor(null, "Jorge", "teste", "123", null,null);
        ProfessorDao dao = new ProfessorDao();
        dao.save(professor);
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Disciplina mat = new Disciplina(null, "Matemática", null, null, professor);
        disciplinaDao.save(mat);
    }
}
