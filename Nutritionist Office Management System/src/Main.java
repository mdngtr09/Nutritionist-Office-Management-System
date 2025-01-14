/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author oneide
 */

import controllers.ConsultController;
import models.ConsultModel;
import views.MainFrame;

public class Main {

    public static void main(String[] args) {
        ConsultModel model = new ConsultModel();
        MainFrame view = new MainFrame();
        new ConsultController(model, view);

        view.setVisible(true);
    }
}
