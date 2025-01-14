/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package views;
/**
 *
 * @author oneide
 */

import javax.swing.*;
import java.awt.*;

public class FinalizeConsultFrame extends JFrame {
    private JCheckBox chkRealized;
    private JTextArea txtObservations;
    private JButton btnFinalize;

    public FinalizeConsultFrame(String patientName, boolean isAlreadyRealized) {
        setTitle("Detalhes da Consulta - " + patientName);
        setSize(380, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        startComponents(isAlreadyRealized);
    }

    private void startComponents(boolean isAlreadyRealized) {
        JLabel lblTitle = new JLabel("Detalhes da Consulta");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBounds(20, 10, 360, 30);

        chkRealized = new JCheckBox("Consulta Realizada");
        chkRealized.setBounds(20, 50, 200, 30);
        chkRealized.setSelected(isAlreadyRealized);
        chkRealized.setEnabled(false); // Checkbox desativado

        JLabel lblObservations = new JLabel("Receitas e Observações:");
        lblObservations.setBounds(20, 90, 200, 30);

        txtObservations = new JTextArea();
        txtObservations.setBounds(20, 120, 340, 100);
        txtObservations.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txtObservations.setLineWrap(true);
        txtObservations.setWrapStyleWord(true);

        btnFinalize = new JButton("Finalizar");
        btnFinalize.setBounds(20, 250, 150, 30);
        btnFinalize.setVisible(!isAlreadyRealized); // Botão visvel apenas se não realizado

        add(lblTitle);
        add(chkRealized);
        add(lblObservations);
        add(txtObservations);
        add(btnFinalize);

        // Ação para finalizar consulta
        btnFinalize.addActionListener(e -> finalizeConsult());
    }

    private void finalizeConsult() {
        String observations = txtObservations.getText().trim();

        // Validações
        if (observations.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha as receitas e observações.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Chama a ação de salvar
        if (saveAction != null) {
            saveAction.run(observations);
        }
    }

    private SaveAction saveAction;

    public void setSaveAction(SaveAction action) {
        this.saveAction = action;
    }

    public interface SaveAction {
        void run(String observations);
    }
}
