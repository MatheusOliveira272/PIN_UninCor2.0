/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.webSite.view.utils;

import javax.ejb.MessageDriven;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author alunos
 */
@MessageDriven
public class Mensagens {
    
    public static void erro(FacesContext context, String message) {
        erro(context, message, null);
    }
    public static void erro(FacesContext context, String message, String detail) {
        context.addMessage(message, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                message, detail));
    }
    
}
