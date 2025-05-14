
package packageAdminandEmployeeLogin;

import java.sql.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.TextField;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lj Diodos
 */
public class CashierOrdersMenu extends javax.swing.JPanel {
    private DefaultTableModel model;
    private int quantity = 0;
    
    private void saveOrderToDatabase() {
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();

        String sql = "INSERT INTO orders (product_name, quantity, price, total, `timestamp`) VALUES (?, ?, ?, ?, NOW())";

        try (Connection con = DatabaseConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < model.getRowCount(); i++) {
                Object productNameObj = model.getValueAt(i, 0);
                Object quantityObj = model.getValueAt(i, 1);
                Object priceObj = model.getValueAt(i, 2);
                Object totalObj = model.getValueAt(i, 3);

                if (productNameObj == null || quantityObj == null || priceObj == null || totalObj == null) {
                    System.out.println("Skipping row " + i + " due to null values.");
                    continue; 
                }

                String productName = productNameObj.toString();
                int quantity = Integer.parseInt(quantityObj.toString());
                double price = Double.parseDouble(priceObj.toString());
                double total = Double.parseDouble(totalObj.toString());

                System.out.println("Inserting: " + productName + ", " + quantity + ", " + price + ", " + total);

                stmt.setString(1, productName);
                stmt.setInt(2, quantity);
                stmt.setDouble(3, price);
                stmt.setDouble(4, total);

                stmt.executeUpdate();
                String updateStockSQL = "UPDATE product SET stock = stock - ? WHERE name = ?";
                try (PreparedStatement updateStmt = con.prepareStatement(updateStockSQL)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setString(2, productName);
                    updateStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public class DatabaseConnection {
        public static Connection getConnection() {
            Connection connection = null;
            String url = "jdbc:mysql://localhost:3306/javabeans_db"; 
            String username = "root"; 
            String password = ""; 

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); 
                connection = DriverManager.getConnection(url, username, password);

                System.out.println("Successfully connected to the database!");

            } catch (ClassNotFoundException e) {
                System.err.println("Error: MySQL JDBC driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error: Could not connect to the database!");
                e.printStackTrace();
            }

            return connection;
        }
    
    
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            try {
                conn.close(); // Close the connection when done
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
    
    public CashierOrdersMenu() {
        initComponents();
        model = (DefaultTableModel) tblOrder.getModel();
        updateTotals();
    }
    
    private void updateTotals() {
    double subtotal = 0;
    for (int i = 0; i < model.getRowCount(); i++) {
        Object value = model.getValueAt(i, 3); // column 3 = total

        if (value != null) {
            try {
                subtotal += Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                // Ignore or handle invalid format
            }
        }
    }

    txtstotal.setText(String.format("%.2f", subtotal));
    double discount = cbdiscount.isSelected() ? subtotal * 0.2 : 0;
    txtdiscount.setText(String.format("%.2f", discount));
    double finalTotal = subtotal - discount;
    txttotal.setText(String.format("%.2f", finalTotal));
}
    private void computeChange() {
        try {
            double total = Double.parseDouble(txttotal.getText());
            double payment = Double.parseDouble(txtpayment.getText());
            double change = payment - total;

            if (change < 0) {
                JOptionPane.showMessageDialog(this, "Insufficient payment.");
                return;
            }

            txtchange.setText(String.format("%.2f", change));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid payment amount.");
        }
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jButton5 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSpinner6 = new javax.swing.JSpinner();
        jButton6 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnEspreesso = new javax.swing.JButton();
        q1 = new javax.swing.JTextField();
        lbl_pic1 = new javax.swing.JLabel();
        minusq1 = new javax.swing.JButton();
        addq1 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lbl_pic2 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnAmericano = new javax.swing.JButton();
        q2 = new javax.swing.JTextField();
        addq2 = new javax.swing.JButton();
        minusq2 = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lbl_pic3 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        btnCappucino = new javax.swing.JButton();
        q3 = new javax.swing.JTextField();
        minusq3 = new javax.swing.JButton();
        addq3 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        btnIcedAmericano = new javax.swing.JButton();
        minusq5 = new javax.swing.JButton();
        q5 = new javax.swing.JTextField();
        addq5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        btnIcedMacchiato = new javax.swing.JButton();
        lbl_pic4 = new javax.swing.JLabel();
        minusq4 = new javax.swing.JButton();
        q4 = new javax.swing.JTextField();
        addq4 = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        lbl_pic6 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        btnIcedMocha = new javax.swing.JButton();
        minusq6 = new javax.swing.JButton();
        q6 = new javax.swing.JTextField();
        addq6 = new javax.swing.JButton();
        lbl_pic5 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        btnDripCoffee = new javax.swing.JButton();
        lbl_pic7 = new javax.swing.JLabel();
        addq7 = new javax.swing.JButton();
        q7 = new javax.swing.JTextField();
        minusq7 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        lbl_pic8 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        btnFrenchCoffee = new javax.swing.JButton();
        minusq8 = new javax.swing.JButton();
        addq8 = new javax.swing.JButton();
        q11 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        btnCCake = new javax.swing.JButton();
        lbl_pic9 = new javax.swing.JLabel();
        addq9 = new javax.swing.JButton();
        q9 = new javax.swing.JTextField();
        minusq9 = new javax.swing.JButton();
        jPanel45 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        btnRCake = new javax.swing.JButton();
        lbl_pic12 = new javax.swing.JLabel();
        addq12 = new javax.swing.JButton();
        q10 = new javax.swing.JTextField();
        minusq12 = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        lbl_pic13 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        btnFrenchCoffee1 = new javax.swing.JButton();
        minusq13 = new javax.swing.JButton();
        addq13 = new javax.swing.JButton();
        q13 = new javax.swing.JTextField();
        jPanel50 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        btnDripCoffee8 = new javax.swing.JButton();
        lbl_pic17 = new javax.swing.JLabel();
        addq17 = new javax.swing.JButton();
        q17 = new javax.swing.JTextField();
        minusq17 = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        btnDripCoffee9 = new javax.swing.JButton();
        lbl_pic18 = new javax.swing.JLabel();
        addq18 = new javax.swing.JButton();
        q14 = new javax.swing.JTextField();
        minusq18 = new javax.swing.JButton();
        jPanel55 = new javax.swing.JPanel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        btnDripCoffee13 = new javax.swing.JButton();
        lbl_pic22 = new javax.swing.JLabel();
        addq22 = new javax.swing.JButton();
        q22 = new javax.swing.JTextField();
        minusq22 = new javax.swing.JButton();
        jPanel56 = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        btnDripCoffee14 = new javax.swing.JButton();
        lbl_pic23 = new javax.swing.JLabel();
        addq23 = new javax.swing.JButton();
        q23 = new javax.swing.JTextField();
        minusq23 = new javax.swing.JButton();
        jPanel57 = new javax.swing.JPanel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        btnDripCoffee15 = new javax.swing.JButton();
        lbl_pic24 = new javax.swing.JLabel();
        addq24 = new javax.swing.JButton();
        q24 = new javax.swing.JTextField();
        minusq24 = new javax.swing.JButton();
        jPanel60 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        btnDripCoffee18 = new javax.swing.JButton();
        lbl_pic27 = new javax.swing.JLabel();
        addq27 = new javax.swing.JButton();
        q27 = new javax.swing.JTextField();
        minusq27 = new javax.swing.JButton();
        jPanel61 = new javax.swing.JPanel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        btnDripCoffee19 = new javax.swing.JButton();
        lbl_pic28 = new javax.swing.JLabel();
        addq28 = new javax.swing.JButton();
        q28 = new javax.swing.JTextField();
        minusq28 = new javax.swing.JButton();
        jPanel62 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        btnDripCoffee20 = new javax.swing.JButton();
        lbl_pic29 = new javax.swing.JLabel();
        addq29 = new javax.swing.JButton();
        q29 = new javax.swing.JTextField();
        minusq29 = new javax.swing.JButton();
        jPanel63 = new javax.swing.JPanel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        btnDripCoffee21 = new javax.swing.JButton();
        lbl_pic30 = new javax.swing.JLabel();
        addq30 = new javax.swing.JButton();
        q30 = new javax.swing.JTextField();
        minusq30 = new javax.swing.JButton();
        jPanel64 = new javax.swing.JPanel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        btnDripCoffee22 = new javax.swing.JButton();
        lbl_pic31 = new javax.swing.JLabel();
        addq31 = new javax.swing.JButton();
        q12 = new javax.swing.JTextField();
        minusq31 = new javax.swing.JButton();
        jPanel65 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        btnDripCoffee23 = new javax.swing.JButton();
        lbl_pic32 = new javax.swing.JLabel();
        addq32 = new javax.swing.JButton();
        q32 = new javax.swing.JTextField();
        minusq32 = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        txtdiscount = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        cbdiscount = new javax.swing.JCheckBox();
        txtstotal = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtchange = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtpayment = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnPay = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnReceipt = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1600, 900));

        jPanel1.setBackground(new java.awt.Color(236, 223, 207));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));
        jScrollPane2.setForeground(new java.awt.Color(255, 204, 204));

        jPanel5.setBackground(new java.awt.Color(234, 221, 186));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(145, 90, 60));
        jLabel6.setText("Caramel Macchiato");

        jLabel7.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(145, 90, 60));
        jLabel8.setText("₱ 120");

        jSpinner1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(76, 121, 114));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(231, 231, 231))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(24, 24, 24))
        );

        jPanel9.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 155, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        jPanel31.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        jPanel32.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(145, 90, 60));
        jLabel10.setText("Cappuccino");

        jLabel11.setText("jLabel7");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(145, 90, 60));
        jLabel12.setText("₱ 115");

        jSpinner2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton2.setBackground(new java.awt.Color(76, 121, 114));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(231, 231, 231))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel10)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(24, 24, 24))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(145, 90, 60));
        jLabel14.setText("Latte");

        jLabel15.setText("jLabel7");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(145, 90, 60));
        jLabel16.setText("₱ 115");

        jSpinner3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton3.setBackground(new java.awt.Color(76, 121, 114));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(231, 231, 231))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel14)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(24, 24, 24))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(145, 90, 60));
        jLabel22.setText("Cappuccino");

        jLabel23.setText("jLabel7");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(145, 90, 60));
        jLabel24.setText("₱ 115");

        jSpinner5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton5.setBackground(new java.awt.Color(76, 121, 114));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(231, 231, 231))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel22)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(24, 24, 24))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(145, 90, 60));
        jLabel26.setText("Latte");

        jLabel27.setText("jLabel7");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(145, 90, 60));
        jLabel28.setText("₱ 115");

        jSpinner6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton6.setBackground(new java.awt.Color(76, 121, 114));
        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(231, 231, 231))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel26)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(24, 24, 24))
        );

        jPanel23.setBackground(new java.awt.Color(163, 135, 114));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Espresso");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("₱ 100");

        btnEspreesso.setBackground(new java.awt.Color(61, 43, 30));
        btnEspreesso.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEspreesso.setForeground(new java.awt.Color(255, 255, 255));
        btnEspreesso.setText("Add");
        btnEspreesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEspreessoActionPerformed(evt);
            }
        });

        q1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q1.setText("0");
        q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q1ActionPerformed(evt);
            }
        });

        lbl_pic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/1000012154 (1)_1.png"))); // NOI18N

        minusq1.setBackground(new java.awt.Color(204, 255, 204));
        minusq1.setText("-");
        minusq1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq1ActionPerformed(evt);
            }
        });

        addq1.setBackground(new java.awt.Color(255, 255, 204));
        addq1.setText("+");
        addq1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(addq1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minusq1)
                .addGap(18, 18, 18)
                .addComponent(btnEspreesso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_pic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_pic1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEspreesso)
                    .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minusq1)
                    .addComponent(addq1))
                .addGap(24, 24, 24))
        );

        jPanel24.setBackground(new java.awt.Color(163, 135, 114));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Americano");

        lbl_pic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/1000012155_2.png"))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("₱ 120");

        btnAmericano.setBackground(new java.awt.Color(61, 43, 30));
        btnAmericano.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAmericano.setForeground(new java.awt.Color(255, 255, 255));
        btnAmericano.setText("Add");
        btnAmericano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmericanoActionPerformed(evt);
            }
        });

        q2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q2.setText("0");

        addq2.setBackground(new java.awt.Color(255, 255, 204));
        addq2.setText("+");
        addq2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq2ActionPerformed(evt);
            }
        });

        minusq2.setBackground(new java.awt.Color(204, 255, 204));
        minusq2.setText("-");
        minusq2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(231, 231, 231))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(addq2)
                        .addGap(9, 9, 9)
                        .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq2)
                        .addGap(18, 18, 18)
                        .addComponent(btnAmericano)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_pic2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(lbl_pic2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minusq2)
                        .addComponent(addq2)
                        .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAmericano))
                .addGap(24, 24, 24))
        );

        jPanel26.setBackground(new java.awt.Color(163, 135, 114));

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Cappucino");

        lbl_pic3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/1000012156_1.png"))); // NOI18N
        lbl_pic3.setText("jLabel7");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("₱ 100");

        btnCappucino.setBackground(new java.awt.Color(61, 43, 30));
        btnCappucino.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCappucino.setForeground(new java.awt.Color(255, 255, 255));
        btnCappucino.setText("Add");
        btnCappucino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCappucinobtnLatteActionPerformed(evt);
            }
        });

        q3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q3.setText("0");

        minusq3.setBackground(new java.awt.Color(204, 255, 204));
        minusq3.setText("-");
        minusq3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq3ActionPerformed(evt);
            }
        });

        addq3.setBackground(new java.awt.Color(255, 255, 204));
        addq3.setText("+");
        addq3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(231, 231, 231))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(addq3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(q3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(minusq3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCappucino))
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lbl_pic3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minusq3)
                        .addComponent(addq3)
                        .addComponent(q3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCappucino))
                .addGap(24, 24, 24))
        );

        jPanel38.setBackground(new java.awt.Color(163, 135, 114));

        jLabel80.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Iced Americano");

        jLabel82.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("₱ 200");

        btnIcedAmericano.setBackground(new java.awt.Color(61, 43, 30));
        btnIcedAmericano.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnIcedAmericano.setForeground(new java.awt.Color(255, 255, 255));
        btnIcedAmericano.setText("Add");
        btnIcedAmericano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIcedAmericanoActionPerformed(evt);
            }
        });

        minusq5.setBackground(new java.awt.Color(204, 255, 204));
        minusq5.setText("-");
        minusq5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq5ActionPerformed(evt);
            }
        });

        q5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q5.setText("0");
        q5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q5ActionPerformed(evt);
            }
        });

        addq5.setBackground(new java.awt.Color(255, 255, 204));
        addq5.setText("+");
        addq5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq5ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/1000012159_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel38Layout.createSequentialGroup()
                                .addComponent(addq5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(q5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minusq5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnIcedAmericano)))
                        .addGap(36, 36, 36))))
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq5)
                        .addComponent(addq5))
                    .addComponent(btnIcedAmericano))
                .addGap(16, 16, 16))
        );

        jPanel27.setBackground(new java.awt.Color(163, 135, 114));

        jLabel77.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Iced Caramel Macchiato");

        jLabel78.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("₱ 180");

        btnIcedMacchiato.setBackground(new java.awt.Color(61, 43, 30));
        btnIcedMacchiato.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnIcedMacchiato.setForeground(new java.awt.Color(255, 255, 255));
        btnIcedMacchiato.setText("Add");
        btnIcedMacchiato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIcedMacchiatoActionPerformed(evt);
            }
        });

        lbl_pic4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/1000012157_1.png"))); // NOI18N

        minusq4.setBackground(new java.awt.Color(204, 255, 204));
        minusq4.setText("-");
        minusq4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq4ActionPerformed(evt);
            }
        });

        q4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q4.setText("0");
        q4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q4ActionPerformed(evt);
            }
        });

        addq4.setBackground(new java.awt.Color(255, 255, 204));
        addq4.setText("+");
        addq4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(231, 231, 231))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(addq4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIcedMacchiato))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lbl_pic4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_pic4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minusq4)
                    .addComponent(addq4)
                    .addComponent(btnIcedMacchiato))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel39.setBackground(new java.awt.Color(163, 135, 114));

        jLabel84.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Iced Mocha");

        jLabel86.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("₱ 135");

        btnIcedMocha.setBackground(new java.awt.Color(61, 43, 30));
        btnIcedMocha.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnIcedMocha.setForeground(new java.awt.Color(255, 255, 255));
        btnIcedMocha.setText("Add");
        btnIcedMocha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIcedMochaActionPerformed(evt);
            }
        });

        minusq6.setBackground(new java.awt.Color(204, 255, 204));
        minusq6.setText("-");
        minusq6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq6ActionPerformed(evt);
            }
        });

        q6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q6.setText("0");
        q6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q6ActionPerformed(evt);
            }
        });

        addq6.setBackground(new java.awt.Color(255, 255, 204));
        addq6.setText("+");
        addq6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq6ActionPerformed(evt);
            }
        });

        lbl_pic5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/1000012158_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel83)
                .addGap(231, 231, 231))
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbl_pic5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_pic6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(addq6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIcedMocha)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel39Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_pic6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_pic5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIcedMocha)
                    .addComponent(q6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addq6)
                    .addComponent(minusq6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        jPanel40.setBackground(new java.awt.Color(163, 135, 114));

        jLabel88.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("Drip Coffee");

        jLabel89.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("₱ 150");

        btnDripCoffee.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee.setText("Add");
        btnDripCoffee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffeeActionPerformed(evt);
            }
        });

        lbl_pic7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/1000012161_1.png"))); // NOI18N

        addq7.setBackground(new java.awt.Color(255, 255, 204));
        addq7.setText("+");
        addq7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq7ActionPerformed(evt);
            }
        });

        q7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q7.setText("0");
        q7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q7ActionPerformed(evt);
            }
        });

        minusq7.setBackground(new java.awt.Color(204, 255, 204));
        minusq7.setText("-");
        minusq7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addComponent(addq7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee)
                        .addGap(42, 42, 42))
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbl_pic7))))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic7, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq7)
                        .addComponent(addq7))
                    .addComponent(btnDripCoffee))
                .addGap(24, 24, 24))
        );

        jPanel41.setBackground(new java.awt.Color(163, 135, 114));
        jPanel41.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel91.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("Carrot Cake");

        lbl_pic8.setText("jLabel7");

        jLabel93.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("₱ 250");

        btnFrenchCoffee.setBackground(new java.awt.Color(61, 43, 30));
        btnFrenchCoffee.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnFrenchCoffee.setForeground(new java.awt.Color(255, 255, 255));
        btnFrenchCoffee.setText("Add");
        btnFrenchCoffee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrenchCoffeeActionPerformed(evt);
            }
        });

        minusq8.setBackground(new java.awt.Color(204, 255, 204));
        minusq8.setText("-");
        minusq8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq8ActionPerformed(evt);
            }
        });

        addq8.setBackground(new java.awt.Color(255, 255, 204));
        addq8.setText("+");
        addq8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq8ActionPerformed(evt);
            }
        });

        q11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q11.setText("0");
        q11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel90)
                .addGap(231, 231, 231))
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lbl_pic8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(addq8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(q11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minusq8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFrenchCoffee)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq8)
                        .addComponent(addq8))
                    .addComponent(btnFrenchCoffee))
                .addGap(24, 24, 24))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CAKE");

        jPanel42.setBackground(new java.awt.Color(163, 135, 114));
        jPanel42.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel94.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("Chocolate Cake");

        jLabel95.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("₱ 200");

        btnCCake.setBackground(new java.awt.Color(61, 43, 30));
        btnCCake.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCCake.setForeground(new java.awt.Color(255, 255, 255));
        btnCCake.setText("Add");
        btnCCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCCakeActionPerformed(evt);
            }
        });

        lbl_pic9.setText("jLabel38");

        addq9.setBackground(new java.awt.Color(255, 255, 204));
        addq9.setText("+");
        addq9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq9ActionPerformed(evt);
            }
        });

        q9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q9.setText("0");
        q9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q9ActionPerformed(evt);
            }
        });

        minusq9.setBackground(new java.awt.Color(204, 255, 204));
        minusq9.setText("-");
        minusq9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                        .addComponent(addq9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCCake)
                        .addGap(42, 42, 42))))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq9)
                        .addComponent(addq9))
                    .addComponent(btnCCake))
                .addGap(24, 24, 24))
        );

        jPanel45.setBackground(new java.awt.Color(163, 135, 114));

        jLabel103.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setText("Red Velvet Cake");

        jLabel104.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setText("₱ 210");

        btnRCake.setBackground(new java.awt.Color(61, 43, 30));
        btnRCake.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnRCake.setForeground(new java.awt.Color(255, 255, 255));
        btnRCake.setText("Add");
        btnRCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRCakeActionPerformed(evt);
            }
        });

        lbl_pic12.setText("jLabel38");

        addq12.setBackground(new java.awt.Color(255, 255, 204));
        addq12.setText("+");
        addq12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq12ActionPerformed(evt);
            }
        });

        q10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q10.setText("0");
        q10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q10ActionPerformed(evt);
            }
        });

        minusq12.setBackground(new java.awt.Color(204, 255, 204));
        minusq12.setText("-");
        minusq12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic12, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                        .addComponent(jLabel102)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                        .addComponent(addq12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRCake)
                        .addGap(42, 42, 42))))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel102)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel103)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic12, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel104)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq12)
                        .addComponent(addq12))
                    .addComponent(btnRCake))
                .addGap(24, 24, 24))
        );

        jPanel46.setBackground(new java.awt.Color(163, 135, 114));

        jLabel106.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("French Press Coffee");

        lbl_pic13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packagePictures/product/64.png"))); // NOI18N

        jLabel107.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel107.setText("₱ 140");

        btnFrenchCoffee1.setBackground(new java.awt.Color(61, 43, 30));
        btnFrenchCoffee1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnFrenchCoffee1.setForeground(new java.awt.Color(255, 255, 255));
        btnFrenchCoffee1.setText("Add");
        btnFrenchCoffee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrenchCoffee1ActionPerformed(evt);
            }
        });

        minusq13.setBackground(new java.awt.Color(204, 255, 204));
        minusq13.setText("-");
        minusq13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq13ActionPerformed(evt);
            }
        });

        addq13.setBackground(new java.awt.Color(255, 255, 204));
        addq13.setText("+");
        addq13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq13ActionPerformed(evt);
            }
        });

        q13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q13.setText("0");
        q13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel105)
                .addGap(231, 231, 231))
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(addq13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(q13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minusq13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFrenchCoffee1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_pic13, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel105)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel106)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel107)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq13)
                        .addComponent(addq13))
                    .addComponent(btnFrenchCoffee1))
                .addGap(24, 24, 24))
        );

        jPanel50.setBackground(new java.awt.Color(163, 135, 114));
        jPanel50.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel118.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("Mango Bravo");

        jLabel119.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel119.setText("₱ 350");

        btnDripCoffee8.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee8.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee8.setText("Add");
        btnDripCoffee8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee8ActionPerformed(evt);
            }
        });

        lbl_pic17.setText("jLabel38");

        addq17.setBackground(new java.awt.Color(255, 255, 204));
        addq17.setText("+");
        addq17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq17ActionPerformed(evt);
            }
        });

        q17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q17.setText("0");
        q17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q17ActionPerformed(evt);
            }
        });

        minusq17.setBackground(new java.awt.Color(204, 255, 204));
        minusq17.setText("-");
        minusq17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic17, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                        .addComponent(jLabel117)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                        .addComponent(addq17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee8)
                        .addGap(42, 42, 42))))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel118)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic17, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel119)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq17)
                        .addComponent(addq17))
                    .addComponent(btnDripCoffee8))
                .addGap(24, 24, 24))
        );

        jPanel51.setBackground(new java.awt.Color(163, 135, 114));
        jPanel51.setRequestFocusEnabled(false);

        jLabel121.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel121.setText("Strawberry Shortcake");

        jLabel122.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel122.setText("₱ 350");

        btnDripCoffee9.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee9.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee9.setText("Add");
        btnDripCoffee9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee9ActionPerformed(evt);
            }
        });

        lbl_pic18.setText("jLabel38");

        addq18.setBackground(new java.awt.Color(255, 255, 204));
        addq18.setText("+");
        addq18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq18ActionPerformed(evt);
            }
        });

        q14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q14.setText("0");
        q14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q14ActionPerformed(evt);
            }
        });

        minusq18.setBackground(new java.awt.Color(204, 255, 204));
        minusq18.setText("-");
        minusq18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic18, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                        .addComponent(jLabel120)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                        .addComponent(addq18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee9)
                        .addGap(42, 42, 42))))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel121)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel122)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq18)
                        .addComponent(addq18))
                    .addComponent(btnDripCoffee9))
                .addGap(24, 24, 24))
        );

        jPanel55.setBackground(new java.awt.Color(163, 135, 114));

        jLabel133.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 255, 255));
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel133.setText("Chocolate Cupcake");

        jLabel134.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(255, 255, 255));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("₱ 70");

        btnDripCoffee13.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee13.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee13.setText("Add");
        btnDripCoffee13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee13ActionPerformed(evt);
            }
        });

        lbl_pic22.setText("jLabel38");

        addq22.setBackground(new java.awt.Color(255, 255, 204));
        addq22.setText("+");
        addq22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq22ActionPerformed(evt);
            }
        });

        q22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q22.setText("0");
        q22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q22ActionPerformed(evt);
            }
        });

        minusq22.setBackground(new java.awt.Color(204, 255, 204));
        minusq22.setText("-");
        minusq22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addComponent(jLabel133, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel134, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic22, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                        .addComponent(jLabel132)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                        .addComponent(addq22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee13)
                        .addGap(42, 42, 42))))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel132)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel133)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic22, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel134)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq22)
                        .addComponent(addq22))
                    .addComponent(btnDripCoffee13))
                .addGap(24, 24, 24))
        );

        jPanel56.setBackground(new java.awt.Color(163, 135, 114));
        jPanel56.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel136.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel136.setText("Red Velvet Cupcake");

        jLabel137.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("₱ 70");

        btnDripCoffee14.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee14.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee14.setText("Add");
        btnDripCoffee14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee14ActionPerformed(evt);
            }
        });

        lbl_pic23.setText("jLabel38");

        addq23.setBackground(new java.awt.Color(255, 255, 204));
        addq23.setText("+");
        addq23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq23ActionPerformed(evt);
            }
        });

        q23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q23.setText("0");
        q23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q23ActionPerformed(evt);
            }
        });

        minusq23.setBackground(new java.awt.Color(204, 255, 204));
        minusq23.setText("-");
        minusq23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic23, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                        .addComponent(jLabel135)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                        .addComponent(addq23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee14)
                        .addGap(42, 42, 42))))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel135)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel136)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic23, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel137)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq23)
                        .addComponent(addq23))
                    .addComponent(btnDripCoffee14))
                .addGap(24, 24, 24))
        );

        jPanel57.setBackground(new java.awt.Color(163, 135, 114));

        jLabel139.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(255, 255, 255));
        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel139.setText("Vanilla Cupcake");

        jLabel140.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(255, 255, 255));
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("₱ 70");

        btnDripCoffee15.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee15.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee15.setText("Add");
        btnDripCoffee15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee15ActionPerformed(evt);
            }
        });

        lbl_pic24.setText("jLabel38");

        addq24.setBackground(new java.awt.Color(255, 255, 204));
        addq24.setText("+");
        addq24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq24ActionPerformed(evt);
            }
        });

        q24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q24.setText("0");
        q24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q24ActionPerformed(evt);
            }
        });

        minusq24.setBackground(new java.awt.Color(204, 255, 204));
        minusq24.setText("-");
        minusq24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic24, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel138)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addComponent(addq24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee15)
                        .addGap(42, 42, 42))))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel138)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel139)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic24, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel140)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq24)
                        .addComponent(addq24))
                    .addComponent(btnDripCoffee15))
                .addGap(24, 24, 24))
        );

        jPanel60.setBackground(new java.awt.Color(163, 135, 114));

        jLabel148.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(255, 255, 255));
        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel148.setText("Red Velvet Cupcake");

        jLabel149.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 255, 255));
        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel149.setText("₱ 70");

        btnDripCoffee18.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee18.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee18.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee18.setText("Add");
        btnDripCoffee18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee18ActionPerformed(evt);
            }
        });

        lbl_pic27.setText("jLabel38");

        addq27.setBackground(new java.awt.Color(255, 255, 204));
        addq27.setText("+");
        addq27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq27ActionPerformed(evt);
            }
        });

        q27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q27.setText("0");
        q27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q27ActionPerformed(evt);
            }
        });

        minusq27.setBackground(new java.awt.Color(204, 255, 204));
        minusq27.setText("-");
        minusq27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic27, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                        .addComponent(jLabel147)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                        .addComponent(addq27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee18)
                        .addGap(42, 42, 42))))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel147)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel148)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic27, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel149)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq27)
                        .addComponent(addq27))
                    .addComponent(btnDripCoffee18))
                .addGap(24, 24, 24))
        );

        jPanel61.setBackground(new java.awt.Color(163, 135, 114));
        jPanel61.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel151.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(255, 255, 255));
        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel151.setText("Banana Muffins");

        jLabel152.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(255, 255, 255));
        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel152.setText("₱ 75");

        btnDripCoffee19.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee19.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee19.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee19.setText("Add");
        btnDripCoffee19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee19ActionPerformed(evt);
            }
        });

        lbl_pic28.setText("jLabel38");

        addq28.setBackground(new java.awt.Color(255, 255, 204));
        addq28.setText("+");
        addq28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq28ActionPerformed(evt);
            }
        });

        q28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q28.setText("0");
        q28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q28ActionPerformed(evt);
            }
        });

        minusq28.setBackground(new java.awt.Color(204, 255, 204));
        minusq28.setText("-");
        minusq28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic28, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                        .addComponent(jLabel150)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                        .addComponent(addq28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee19)
                        .addGap(42, 42, 42))))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel150)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel151)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic28, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel152)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq28)
                        .addComponent(addq28))
                    .addComponent(btnDripCoffee19))
                .addGap(24, 24, 24))
        );

        jPanel62.setBackground(new java.awt.Color(163, 135, 114));
        jPanel62.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel154.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(255, 255, 255));
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setText("Blueberry Muffins");

        jLabel155.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(255, 255, 255));
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel155.setText("₱ 75");

        btnDripCoffee20.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee20.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee20.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee20.setText("Add");
        btnDripCoffee20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee20ActionPerformed(evt);
            }
        });

        lbl_pic29.setText("jLabel38");

        addq29.setBackground(new java.awt.Color(255, 255, 204));
        addq29.setText("+");
        addq29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq29ActionPerformed(evt);
            }
        });

        q29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q29.setText("0");
        q29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q29ActionPerformed(evt);
            }
        });

        minusq29.setBackground(new java.awt.Color(204, 255, 204));
        minusq29.setText("-");
        minusq29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic29, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                        .addComponent(jLabel153)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                        .addComponent(addq29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee20)
                        .addGap(42, 42, 42))))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel153)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel154)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic29, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel155)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq29)
                        .addComponent(addq29))
                    .addComponent(btnDripCoffee20))
                .addGap(24, 24, 24))
        );

        jPanel63.setBackground(new java.awt.Color(163, 135, 114));

        jLabel157.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(255, 255, 255));
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel157.setText("Choco Chip Muffins");

        jLabel158.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(255, 255, 255));
        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel158.setText("₱ 75");

        btnDripCoffee21.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee21.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee21.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee21.setText("Add");
        btnDripCoffee21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee21ActionPerformed(evt);
            }
        });

        lbl_pic30.setText("jLabel38");

        addq30.setBackground(new java.awt.Color(255, 255, 204));
        addq30.setText("+");
        addq30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq30ActionPerformed(evt);
            }
        });

        q30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q30.setText("0");
        q30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q30ActionPerformed(evt);
            }
        });

        minusq30.setBackground(new java.awt.Color(204, 255, 204));
        minusq30.setText("-");
        minusq30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic30, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                        .addComponent(jLabel156)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                        .addComponent(addq30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee21)
                        .addGap(42, 42, 42))))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel156)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel157)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic30, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel158)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq30)
                        .addComponent(addq30))
                    .addComponent(btnDripCoffee21))
                .addGap(24, 24, 24))
        );

        jPanel64.setBackground(new java.awt.Color(163, 135, 114));
        jPanel64.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel160.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(255, 255, 255));
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel160.setText("Mango Bravo");

        jLabel161.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(255, 255, 255));
        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel161.setText("₱ 350");

        btnDripCoffee22.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee22.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee22.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee22.setText("Add");
        btnDripCoffee22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee22ActionPerformed(evt);
            }
        });

        lbl_pic31.setText("jLabel38");

        addq31.setBackground(new java.awt.Color(255, 255, 204));
        addq31.setText("+");
        addq31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq31ActionPerformed(evt);
            }
        });

        q12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q12.setText("0");
        q12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q12ActionPerformed(evt);
            }
        });

        minusq31.setBackground(new java.awt.Color(204, 255, 204));
        minusq31.setText("-");
        minusq31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic31, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                        .addComponent(jLabel159)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                        .addComponent(addq31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee22)
                        .addGap(42, 42, 42))))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel159)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel160)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic31, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel161)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq31)
                        .addComponent(addq31))
                    .addComponent(btnDripCoffee22))
                .addGap(24, 24, 24))
        );

        jPanel65.setBackground(new java.awt.Color(163, 135, 114));
        jPanel65.setPreferredSize(new java.awt.Dimension(237, 284));

        jLabel163.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(255, 255, 255));
        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel163.setText("Blueberry Muffins");

        jLabel164.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(255, 255, 255));
        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel164.setText("₱ 75");

        btnDripCoffee23.setBackground(new java.awt.Color(61, 43, 30));
        btnDripCoffee23.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDripCoffee23.setForeground(new java.awt.Color(255, 255, 255));
        btnDripCoffee23.setText("Add");
        btnDripCoffee23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDripCoffee23ActionPerformed(evt);
            }
        });

        lbl_pic32.setText("jLabel38");

        addq32.setBackground(new java.awt.Color(255, 255, 204));
        addq32.setText("+");
        addq32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addq32ActionPerformed(evt);
            }
        });

        q32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        q32.setText("0");
        q32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q32ActionPerformed(evt);
            }
        });

        minusq32.setBackground(new java.awt.Color(204, 255, 204));
        minusq32.setText("-");
        minusq32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusq32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addComponent(jLabel163, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic32, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                        .addComponent(jLabel162)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                        .addComponent(addq32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minusq32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDripCoffee23)
                        .addGap(42, 42, 42))))
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel162)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel163)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pic32, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel164)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(q32, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minusq32)
                        .addComponent(addq32))
                    .addComponent(btnDripCoffee23))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(884, 884, 884))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 567, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(1099, 1099, 1099)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(1297, 1297, 1297)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(484, 484, 484)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(730, 730, 730)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1800, 1800, 1800))))
                .addGap(3057, 3057, 3057))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(3515, 3515, 3515)
                    .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, 220, Short.MAX_VALUE)
                    .addGap(3516, 3516, 3516)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(340, 340, 340)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(418, 418, 418)
                                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(420, 420, 420))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(406, 406, 406))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(1151, 1151, 1151)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(1152, 1152, 1152)))
        );

        jScrollPane2.setViewportView(jPanel5);

        panel1.setBackground(new java.awt.Color(61, 43, 30));

        txtdiscount.setEditable(false);
        txtdiscount.setBackground(new java.awt.Color(61, 43, 30));
        txtdiscount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtdiscount.setForeground(new java.awt.Color(255, 255, 255));
        txtdiscount.setBorder(null);
        txtdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiscountActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("Sub Total: ");

        cbdiscount.setBackground(new java.awt.Color(61, 43, 30));
        cbdiscount.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cbdiscount.setForeground(new java.awt.Color(255, 255, 255));
        cbdiscount.setText("Discount:");
        cbdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbdiscountActionPerformed(evt);
            }
        });

        txtstotal.setEditable(false);
        txtstotal.setBackground(new java.awt.Color(61, 43, 30));
        txtstotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtstotal.setForeground(new java.awt.Color(255, 255, 255));
        txtstotal.setBorder(null);
        txtstotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstotalActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("Change:");

        txttotal.setBackground(new java.awt.Color(61, 43, 30));
        txttotal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txttotal.setForeground(new java.awt.Color(255, 255, 255));
        txttotal.setBorder(null);
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });

        txtchange.setEditable(false);
        txtchange.setBackground(new java.awt.Color(61, 43, 30));
        txtchange.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtchange.setForeground(new java.awt.Color(255, 255, 255));
        txtchange.setBorder(null);
        txtchange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtchangeActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Payment:");

        txtpayment.setBackground(new java.awt.Color(61, 43, 30));
        txtpayment.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtpayment.setForeground(new java.awt.Color(255, 255, 255));
        txtpayment.setBorder(null);
        txtpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpaymentActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Total:");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel40))
                            .addComponent(cbdiscount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtdiscount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(txtstotal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtpayment)
                            .addComponent(txtchange)
                            .addComponent(txttotal, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(55, 55, 55)))
                .addGap(31, 31, 31))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtstotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbdiscount))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpayment, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtchange, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        tblOrder.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product ", "Quantity", "Price          ", "Total         "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblOrder);

        btnPay.setBackground(new java.awt.Color(61, 43, 30));
        btnPay.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnPay.setForeground(new java.awt.Color(255, 255, 255));
        btnPay.setText("Place Order");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(61, 43, 30));
        btnRemove.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setText("Remove a Product");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnReceipt.setBackground(new java.awt.Color(61, 43, 30));
        btnReceipt.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnReceipt.setText("Receipt");
        btnReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 344, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1456, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(837, 837, 837))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 2731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnEspreessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEspreessoActionPerformed

        String quantityText = q1.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Espresso  ";
            double pricePerItem = 100.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q1.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnEspreessoActionPerformed

    private void q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q1ActionPerformed

    private void minusq1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq1ActionPerformed
        if (quantity > 0) {
            quantity--;
            q1.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq1ActionPerformed

    private void addq1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq1ActionPerformed
        quantity++;
        q1.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq1ActionPerformed

    private void btnAmericanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmericanoActionPerformed
        String quantityText = q2.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Americano  ";
            double pricePerItem = 120.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q2.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnAmericanoActionPerformed

    private void addq2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq2ActionPerformed
        quantity++;
        q2.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq2ActionPerformed

    private void minusq2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq2ActionPerformed
        if (quantity > 0) {
            quantity--;
            q2.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq2ActionPerformed

    private void btnCappucinobtnLatteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCappucinobtnLatteActionPerformed
        String quantityText = q3.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Cappucino  ";
            double pricePerItem = 100.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q3.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnCappucinobtnLatteActionPerformed

    private void btnIcedAmericanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIcedAmericanoActionPerformed
        String quantityText = q5.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Iced Americano  ";
            double pricePerItem = 200.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q5.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnIcedAmericanoActionPerformed

    private void btnIcedMochaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIcedMochaActionPerformed
        String quantityText = q6.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Iced Mocha ";
            double pricePerItem = 135.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q6.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnIcedMochaActionPerformed

    private void btnDripCoffeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffeeActionPerformed
        String quantityText = q7.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Drip Coffee  ";
            double pricePerItem = 150.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q7.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnDripCoffeeActionPerformed

    private void btnFrenchCoffeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrenchCoffeeActionPerformed
        String quantityText = q11.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Drip Coffee  ";
            double pricePerItem = 150.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q11.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnFrenchCoffeeActionPerformed

    private void txtdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiscountActionPerformed

    }//GEN-LAST:event_txtdiscountActionPerformed

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void txtpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpaymentActionPerformed

    }//GEN-LAST:event_txtpaymentActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();

        try {
            // Get total from txttotal and payment from txtpayment
            double total = Double.parseDouble(txttotal.getText());
            double payment = Double.parseDouble(txtpayment.getText());

            // Calculate change
            double change = payment - total;

            // Check for insufficient payment
            if (change < 0) {
                txtchange.setText("Insufficient");
                JOptionPane.showMessageDialog(this, "Insufficient payment.");
            } else {
                // Display the change if the payment is sufficient
                txtchange.setText(String.format("%.2f", change));
                JOptionPane.showMessageDialog(this, "Payment successful!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers.");
        }
        saveOrderToDatabase();
        model.setRowCount(0);
        txtstotal.setText("");
        txtdiscount.setText("");
        txttotal.setText("");
        txtchange.setText("");
        txtpayment.setText("");
        cbdiscount.setSelected(false);
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed

        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();

        model.setRowCount(0);

        txtdiscount.setText("");
        txttotal.setText("");
        txtpayment.setText("");

    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptActionPerformed
        
    }//GEN-LAST:event_btnReceiptActionPerformed

    private void txtstotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstotalActionPerformed

    private void txtchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtchangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtchangeActionPerformed

    private void cbdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbdiscountActionPerformed
        updateTotals();
    }//GEN-LAST:event_cbdiscountActionPerformed

    private void btnIcedMacchiatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIcedMacchiatoActionPerformed
        String quantityText = q4.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Iced Caramel Macchiato  ";
            double pricePerItem = 180.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q4.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnIcedMacchiatoActionPerformed

    private void addq4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq4ActionPerformed
        quantity++;
        q4.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq4ActionPerformed

    private void q4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q4ActionPerformed

    private void minusq4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq4ActionPerformed
        if (quantity > 0) {
            quantity--;
            q4.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq4ActionPerformed

    private void addq5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq5ActionPerformed
        quantity++;
        q5.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq5ActionPerformed

    private void q5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q5ActionPerformed

    private void minusq5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq5ActionPerformed
        if (quantity > 0) {
            quantity--;
            q5.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq5ActionPerformed

    private void addq6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq6ActionPerformed
        quantity++;
        q6.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq6ActionPerformed

    private void q6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q6ActionPerformed

    private void minusq6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq6ActionPerformed
        if (quantity > 0) {
            quantity--;
            q6.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq6ActionPerformed

    private void addq7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq7ActionPerformed
        quantity++;
        q7.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq7ActionPerformed

    private void q7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q7ActionPerformed

    private void minusq7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq7ActionPerformed
        if (quantity > 0) {
            quantity--;
            q7.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq7ActionPerformed

    private void addq8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq8ActionPerformed
        quantity++;
        q11.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq8ActionPerformed

    private void q11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q11ActionPerformed

    private void minusq8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq8ActionPerformed
        if (quantity > 0) {
            quantity--;
            q11.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq8ActionPerformed

    private void addq3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq3ActionPerformed
        quantity++;
        q3.setText(String.valueOf(quantity));
    }//GEN-LAST:event_addq3ActionPerformed

    private void minusq3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq3ActionPerformed
        if (quantity > 0) {
            quantity--;
            q3.setText(String.valueOf(quantity));
        }
    }//GEN-LAST:event_minusq3ActionPerformed

    private void btnCCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCCakeActionPerformed
        String quantityText = q9.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Chocolate Cake  ";
            double pricePerItem = 200.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q9.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnCCakeActionPerformed

    private void addq9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq9ActionPerformed

    private void q9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q9ActionPerformed

    private void minusq9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq9ActionPerformed

    private void btnRCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRCakeActionPerformed
        String quantityText = q10.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Drip Coffee  ";
            double pricePerItem = 150.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q10.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
        
        
    }//GEN-LAST:event_btnRCakeActionPerformed

    private void addq12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq12ActionPerformed

    private void q10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q10ActionPerformed

    private void minusq12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq12ActionPerformed

    private void btnFrenchCoffee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrenchCoffee1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFrenchCoffee1ActionPerformed

    private void minusq13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq13ActionPerformed

    private void addq13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq13ActionPerformed

    private void q13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q13ActionPerformed

    private void btnDripCoffee8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee8ActionPerformed

    private void addq17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq17ActionPerformed

    private void q17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q17ActionPerformed

    private void minusq17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq17ActionPerformed

    private void btnDripCoffee9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee9ActionPerformed

    private void addq18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq18ActionPerformed

    private void q14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q14ActionPerformed

    private void minusq18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq18ActionPerformed

    private void btnDripCoffee13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee13ActionPerformed

    private void addq22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq22ActionPerformed

    private void q22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q22ActionPerformed

    private void minusq22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq22ActionPerformed

    private void btnDripCoffee14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee14ActionPerformed

    private void addq23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq23ActionPerformed

    private void q23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q23ActionPerformed

    private void minusq23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq23ActionPerformed

    private void btnDripCoffee15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee15ActionPerformed

    private void addq24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq24ActionPerformed

    private void q24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q24ActionPerformed

    private void minusq24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq24ActionPerformed

    private void btnDripCoffee18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee18ActionPerformed

    private void addq27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq27ActionPerformed

    private void q27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q27ActionPerformed

    private void minusq27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq27ActionPerformed

    private void btnDripCoffee19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee19ActionPerformed

    private void addq28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq28ActionPerformed

    private void q28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q28ActionPerformed

    private void minusq28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq28ActionPerformed

    private void btnDripCoffee20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee20ActionPerformed

    private void addq29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq29ActionPerformed

    private void q29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q29ActionPerformed

    private void minusq29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq29ActionPerformed

    private void btnDripCoffee21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee21ActionPerformed

    private void addq30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq30ActionPerformed

    private void q30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q30ActionPerformed

    private void minusq30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq30ActionPerformed

    private void btnDripCoffee22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee22ActionPerformed
        String quantityText = q12.getText(); 
    
        if (quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a quantity.");
            return;  
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                return;
            }

            String itemName = "Chocolate Cake  ";
            double pricePerItem = 200.0; 

            double totalPrice = pricePerItem * quantity;

            DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
            model.insertRow(0, new Object[]{itemName, quantity, pricePerItem, totalPrice});  

            q12.setText("");
            updateTotals();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for quantity.");
            }
    }//GEN-LAST:event_btnDripCoffee22ActionPerformed

    private void addq31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq31ActionPerformed

    private void q12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q12ActionPerformed

    private void minusq31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq31ActionPerformed

    private void btnDripCoffee23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDripCoffee23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDripCoffee23ActionPerformed

    private void addq32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addq32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addq32ActionPerformed

    private void q32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q32ActionPerformed

    private void minusq32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusq32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusq32ActionPerformed

    void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addq1;
    private javax.swing.JButton addq12;
    private javax.swing.JButton addq13;
    private javax.swing.JButton addq15;
    private javax.swing.JButton addq17;
    private javax.swing.JButton addq18;
    private javax.swing.JButton addq2;
    private javax.swing.JButton addq22;
    private javax.swing.JButton addq23;
    private javax.swing.JButton addq24;
    private javax.swing.JButton addq27;
    private javax.swing.JButton addq28;
    private javax.swing.JButton addq29;
    private javax.swing.JButton addq3;
    private javax.swing.JButton addq30;
    private javax.swing.JButton addq31;
    private javax.swing.JButton addq32;
    private javax.swing.JButton addq4;
    private javax.swing.JButton addq5;
    private javax.swing.JButton addq6;
    private javax.swing.JButton addq7;
    private javax.swing.JButton addq8;
    private javax.swing.JButton addq9;
    private javax.swing.JButton btnAmericano;
    private javax.swing.JButton btnCCake;
    private javax.swing.JButton btnCappucino;
    private javax.swing.JButton btnDripCoffee;
    private javax.swing.JButton btnDripCoffee13;
    private javax.swing.JButton btnDripCoffee14;
    private javax.swing.JButton btnDripCoffee15;
    private javax.swing.JButton btnDripCoffee18;
    private javax.swing.JButton btnDripCoffee19;
    private javax.swing.JButton btnDripCoffee20;
    private javax.swing.JButton btnDripCoffee21;
    private javax.swing.JButton btnDripCoffee22;
    private javax.swing.JButton btnDripCoffee23;
    private javax.swing.JButton btnDripCoffee6;
    private javax.swing.JButton btnDripCoffee8;
    private javax.swing.JButton btnDripCoffee9;
    private javax.swing.JButton btnEspreesso;
    private javax.swing.JButton btnFrenchCoffee;
    private javax.swing.JButton btnFrenchCoffee1;
    private javax.swing.JButton btnIcedAmericano;
    private javax.swing.JButton btnIcedMacchiato;
    private javax.swing.JButton btnIcedMocha;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnRCake;
    private javax.swing.JButton btnReceipt;
    private javax.swing.JButton btnRemove;
    private javax.swing.JCheckBox cbdiscount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JLabel lbl_pic1;
    private javax.swing.JLabel lbl_pic12;
    private javax.swing.JLabel lbl_pic13;
    private javax.swing.JLabel lbl_pic15;
    private javax.swing.JLabel lbl_pic17;
    private javax.swing.JLabel lbl_pic18;
    private javax.swing.JLabel lbl_pic2;
    private javax.swing.JLabel lbl_pic22;
    private javax.swing.JLabel lbl_pic23;
    private javax.swing.JLabel lbl_pic24;
    private javax.swing.JLabel lbl_pic27;
    private javax.swing.JLabel lbl_pic28;
    private javax.swing.JLabel lbl_pic29;
    private javax.swing.JLabel lbl_pic3;
    private javax.swing.JLabel lbl_pic30;
    private javax.swing.JLabel lbl_pic31;
    private javax.swing.JLabel lbl_pic32;
    private javax.swing.JLabel lbl_pic4;
    private javax.swing.JLabel lbl_pic5;
    private javax.swing.JLabel lbl_pic6;
    private javax.swing.JLabel lbl_pic7;
    private javax.swing.JLabel lbl_pic8;
    private javax.swing.JLabel lbl_pic9;
    private javax.swing.JButton minusq1;
    private javax.swing.JButton minusq12;
    private javax.swing.JButton minusq13;
    private javax.swing.JButton minusq15;
    private javax.swing.JButton minusq17;
    private javax.swing.JButton minusq18;
    private javax.swing.JButton minusq2;
    private javax.swing.JButton minusq22;
    private javax.swing.JButton minusq23;
    private javax.swing.JButton minusq24;
    private javax.swing.JButton minusq27;
    private javax.swing.JButton minusq28;
    private javax.swing.JButton minusq29;
    private javax.swing.JButton minusq3;
    private javax.swing.JButton minusq30;
    private javax.swing.JButton minusq31;
    private javax.swing.JButton minusq32;
    private javax.swing.JButton minusq4;
    private javax.swing.JButton minusq5;
    private javax.swing.JButton minusq6;
    private javax.swing.JButton minusq7;
    private javax.swing.JButton minusq8;
    private javax.swing.JButton minusq9;
    private java.awt.Panel panel1;
    private javax.swing.JTextField q1;
    private javax.swing.JTextField q10;
    private javax.swing.JTextField q11;
    private javax.swing.JTextField q12;
    private javax.swing.JTextField q13;
    private javax.swing.JTextField q14;
    private javax.swing.JTextField q15;
    private javax.swing.JTextField q17;
    private javax.swing.JTextField q2;
    private javax.swing.JTextField q22;
    private javax.swing.JTextField q23;
    private javax.swing.JTextField q24;
    private javax.swing.JTextField q27;
    private javax.swing.JTextField q28;
    private javax.swing.JTextField q29;
    private javax.swing.JTextField q3;
    private javax.swing.JTextField q30;
    private javax.swing.JTextField q32;
    private javax.swing.JTextField q4;
    private javax.swing.JTextField q5;
    private javax.swing.JTextField q6;
    private javax.swing.JTextField q7;
    private javax.swing.JTextField q9;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtchange;
    private javax.swing.JTextField txtdiscount;
    private javax.swing.JTextField txtpayment;
    private javax.swing.JTextField txtstotal;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables

   
}
