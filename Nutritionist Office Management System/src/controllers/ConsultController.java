

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controllers;
/**
 *
 * @author oneide
 */

import models.ConsultModel;
import views.MainFrame;
import views.NewConsultFrame;

public class ConsultController {
    private ConsultModel model;
    private MainFrame mainFrame;

    public ConsultController(ConsultModel model, MainFrame mainFrame) {
        this.model = model;
        this.mainFrame = mainFrame;
        initialize();
    }

    private void initialize() {
        mainFrame.setNewConsultAction(() -> openNewConsultFrame());
    }

    private void openNewConsultFrame() {
        NewConsultFrame newConsultFrame = new NewConsultFrame();
        newConsultFrame.setSaveAction((name, phone, cpf, date, isPatient) -> {
            model.addConsultation(name, phone, cpf, date, isPatient);
            mainFrame.updateTable(model.getConsultations());
            newConsultFrame.dispose();
        });
        newConsultFrame.setVisible(true);
    }
}
