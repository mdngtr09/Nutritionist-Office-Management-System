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
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class NewConsultFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JFormattedTextField txtCpf, txtPhone, txtDate;
    private JTextField txtName;
    private JCheckBox chkIsPatient;
    private JButton btnSave;

    public NewConsultFrame() {
        windowConfig();
        startComponents();
    }

    private void windowConfig() {
        setTitle("Nova Consulta");
        setSize(450, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void startComponents() {
        JLabel lblTitleRegister = new JLabel("Cadastrar Consulta");
        lblTitleRegister.setBounds(20, 10, 350, 30);
        lblTitleRegister.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblName = new JLabel("Nome do(a) Paciente:");
        lblName.setBounds(20, 50, 200, 30);
        txtName = new JTextField();
        txtName.setBounds(220, 50, 200, 30);

        JLabel lblPhone = new JLabel("Telefone:");
        lblPhone.setBounds(20, 100, 100, 30);
        txtPhone = createFormattedField("(##) #####-####");
        txtPhone.setBounds(220, 100, 200, 30);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 140, 100, 30);
        txtCpf = createFormattedField("###.###.###-##");
        txtCpf.setBounds(220, 140, 200, 30);

        JLabel lblDate = new JLabel("Data da consulta:");
        lblDate.setBounds(20, 180, 200, 30);
        txtDate = createFormattedField("##/##/####");
        txtDate.setBounds(220, 180, 200, 30);

        chkIsPatient = new JCheckBox("Já é paciente?");
        chkIsPatient.setBounds(20, 220, 200, 30);

        btnSave = new JButton("Cadastrar");
        btnSave.setBounds(20, 260, 120, 40);

        add(lblTitleRegister);
        add(lblName);
        add(txtName);
        add(lblPhone);
        add(txtPhone);
        add(lblCpf);
        add(txtCpf);
        add(lblDate);
        add(txtDate);
        add(chkIsPatient);
        add(btnSave);

        btnSave.addActionListener(e -> validateAndSave());
    }

    private void validateAndSave() {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String cpf = txtCpf.getText().trim();
        String date = txtDate.getText().trim();
        boolean isPatient = chkIsPatient.isSelected();

        
        if (name.isEmpty() || phone.isEmpty() || cpf.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

      
        if (!isValidCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido! Verifique o formato: 000.000.000-00", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Telefone inválido! Verifique o formato: (99) 99999-9999", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidDate(date)) {
            JOptionPane.showMessageDialog(this, "Data inválida! Verifique o formato: dd/MM/yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

      
        saveAction.run(name, phone, cpf, date, isPatient);
        JOptionPane.showMessageDialog(this, "Consulta cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private JFormattedTextField createFormattedField(String mask) {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
            return new JFormattedTextField(formatter);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar campo formatado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return new JFormattedTextField();
        }
    }

   
    private boolean isValidCPF(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}");
    }

    private boolean isValidDate(String date) {
        return date.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private SaveAction saveAction;

    public void setSaveAction(SaveAction action) {
        this.saveAction = action;
    }

    public interface SaveAction {

        void run(String name, String phone, String cpf, String date, boolean isPatient);
    }
}
