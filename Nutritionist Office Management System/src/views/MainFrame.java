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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable queriesTableJT;
    private DefaultTableModel tableModel;

    public MainFrame() {
        windowConfig();
        startComponents();
    }

    private void windowConfig() {
        setTitle("NutriConsult - Consultas");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void startComponents() {
        JLabel lblTitleWelcome = new JLabel("Boas vindas ao sistema de agendamento");
        lblTitleWelcome.setBounds(20, 10, 350, 30);

        JPanel tablePanel = queriesTable();
        JPanel buttonPanel = buttonsPanel();

        add(lblTitleWelcome);
        add(tablePanel);
        add(buttonPanel);
    }

    private JPanel queriesTable() {
        JPanel tablePanel = new JPanel(null);
        tablePanel.setBounds(10, 50, 730, 400);
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        String[] columns = {"Pacientes", "CPF", "Telefone", "Data", "Já era paciente?", "Consulta Realizada"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        queriesTableJT = new JTable(tableModel);

        JScrollPane scrollBar = new JScrollPane(queriesTableJT);
        scrollBar.setBounds(0, 0, 730, 400);

        tablePanel.add(scrollBar);
        return tablePanel;
    }

    private JPanel buttonsPanel() {
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(10, 480, 730, 50);

        JButton btnNewConsult = new JButton("Nova Consulta");
        btnNewConsult.setBounds(20, 10, 200, 30);

        JButton btnDeleteConsult = new JButton("Excluir Consulta");
        btnDeleteConsult.setBounds(240, 10, 200, 30);

        JButton btnFinalizeConsult = new JButton("Finalizar Consulta");
        btnFinalizeConsult.setBounds(460, 10, 200, 30);

        buttonPanel.add(btnNewConsult);
        buttonPanel.add(btnDeleteConsult);
        buttonPanel.add(btnFinalizeConsult);

        btnNewConsult.addActionListener(e -> onNewConsult.run());
        btnDeleteConsult.addActionListener(e -> deleteSelectedConsult());
        btnFinalizeConsult.addActionListener(e -> finalizeSelectedConsult());

        return buttonPanel;
    }

    private Runnable onNewConsult;

    public void setNewConsultAction(Runnable action) {
        this.onNewConsult = action;
    }

    public void updateTable(List<Object[]> consultations) {
        tableModel.setRowCount(0); // Limpa a tabela
        for (Object[] row : consultations) {
            tableModel.addRow(row);
        }
    }

    private void deleteSelectedConsult() {
        int selectedRow = queriesTableJT.getSelectedRow();

       
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma consulta para excluir.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
  
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir a consulta selecionada?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
         
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Consulta excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void finalizeSelectedConsult() {
        int selectedRow = queriesTableJT.getSelectedRow();

        // Verificar se há uma linha selecionada
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma consulta para finalizar.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Recuperar dados da consulta selecionada
        String patientName = (String) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 5);

        // Abrir o frame de finalização
        FinalizeConsultFrame finalizeFrame = new FinalizeConsultFrame(patientName, status.equalsIgnoreCase("Sim"));
        finalizeFrame.setSaveAction(observations -> {
            // Atualizar tabela
            tableModel.setValueAt("Sim", selectedRow, 5); // Atualizar status para "Sim"
            JOptionPane.showMessageDialog(this, "Consulta finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            finalizeFrame.dispose();
        });
        finalizeFrame.setVisible(true);
    }
}
