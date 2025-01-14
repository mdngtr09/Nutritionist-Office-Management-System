
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package models;
/**
 *
 * @author oneide
 */

import java.util.ArrayList;
import java.util.List;

public class ConsultModel {
    private List<Object[]> consultations;

    public ConsultModel() {
        consultations = new ArrayList<>();
    }

    public List<Object[]> getConsultations() {
        return consultations;
    }

    public void addConsultation(String name, String phone, String cpf, String date, boolean isPatient) {
        Object[] newConsultation = {name, cpf, phone, date, isPatient ? "Sim" : "Não", "Pendente"};
        consultations.add(newConsultation);
    }
}
