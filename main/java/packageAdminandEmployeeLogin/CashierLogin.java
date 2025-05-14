


package packageAdminandEmployeeLogin;
import java.sql.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
public class CashierLogin extends javax.swing.JFrame {

    public CashierLogin() {
          initComponents();
            JPanel shadowPanel = new JPanel();
    shadowPanel.setBackground(new Color(120, 100, 90, 130)); 
    shadowPanel.setBounds(jPanel2.getX() + 7, jPanel2.getY() + 7, 
                          jPanel2.getWidth() + jPanel3.getWidth(), 
                          Math.max(jPanel2.getHeight(), jPanel3.getHeight()));
    shadowPanel.setLayout(null);

  
    jPanel1.add(shadowPanel);
    jPanel1.setComponentZOrder(shadowPanel, jPanel1.getComponentZOrder(jPanel2) + 1);


    jPanel1.setComponentZOrder(jPanel2, 0);
    jPanel1.setComponentZOrder(jPanel3, 1);


    jPanel1.repaint();
    jPanel1.revalidate();
    
     jbtnConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jbtnConfirm.setBackground(new java.awt.Color(150, 130, 110)); 
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            jbtnConfirm.setBackground(new java.awt.Color(236, 223, 207)); 
        }
    });
   
          
    }
 

 




  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jbtnConfirm = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jbtn_Close = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        Admin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frame");
        setName("frame"); // NOI18N
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(163, 134, 114));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(883, 487));

        jPanel2.setBackground(new java.awt.Color(54, 40, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(1600, 900));

        jLabel6.setFont(new java.awt.Font("Microsoft New Tai Lue", 2, 15)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(523, 523, 523)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(658, 658, 658)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(236, 223, 207));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setText("Password");

        txtEmail.setBackground(new java.awt.Color(236, 223, 207));
        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtEmail.setBorder(null);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtPassword.setBackground(new java.awt.Color(236, 223, 207));
        txtPassword.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPassword.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel8.setText("Log in Account");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setText("Full Name");

        jbtnConfirm.setBackground(new java.awt.Color(66, 51, 34));
        jbtnConfirm.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jbtnConfirm.setForeground(new java.awt.Color(236, 223, 207));
        jbtnConfirm.setText("Log in");
        jbtnConfirm.setBorder(null);
        jbtnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnConfirmActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(236, 223, 207));
        jLabel12.setFont(new java.awt.Font("Microsoft New Tai Lue", 2, 15)); // NOI18N
        jLabel12.setText("Need help? Click Here");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jbtn_Close.setBackground(new java.awt.Color(236, 223, 207));
        jbtn_Close.setBorder(null);
        jbtn_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_CloseActionPerformed(evt);
            }
        });

        jSeparator4.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator4.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator5.setBackground(new java.awt.Color(51, 51, 51));
        jSeparator5.setForeground(new java.awt.Color(51, 51, 51));

        Admin.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        Admin.setText("Admin? Click here");
        Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPassword))
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbtnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 77, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtn_Close)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGap(156, 156, 156))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jbtn_Close)
                .addGap(48, 48, 48)
                .addComponent(jLabel8)
                .addGap(45, 45, 45)
                .addComponent(jLabel9)
                .addGap(0, 0, 0)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jbtnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Admin)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void jbtnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnConfirmActionPerformed
                                                 

    String fullname = txtEmail.getText(); // txtEmail is still used for input
    String password = new String(txtPassword.getPassword());
    boolean success = false;

    try {
        // Load JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to database
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/javabeans_db", "root", "");

        // Update query to use fullname
        String sql = "SELECT * FROM cashier_account WHERE fullname = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, fullname);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            success = true;
        }

        rs.close();
        stmt.close();
        conn.close();

    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
    }

    if (success) {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
    JOptionPane.showMessageDialog(parentWindow, "Login Successful!");
        MainCashierDashboard orders = new MainCashierDashboard();
        orders.setVisible(true);
        orders.setLocationRelativeTo(null);
        this.dispose();
    } else {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
    JOptionPane.showMessageDialog(parentWindow, "Incorrect Credentials.");
    }


    }//GEN-LAST:event_jbtnConfirmActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
          
        JOptionPane.showMessageDialog(this,
        "Please contact your administrator to register your full name and give you some password.\nEmail: javabeans@gmail.com\nPhone: +999-212-7160",
        "Contact Admin",
        JOptionPane.INFORMATION_MESSAGE); 
       
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jbtn_CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_CloseActionPerformed
      System.exit(0);
    }//GEN-LAST:event_jbtn_CloseActionPerformed

    private void AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminMouseClicked
        AdminLogin add =new  AdminLogin();
        add.setVisible(true);
        add.setLocationRelativeTo(null);
                this.dispose();
    }//GEN-LAST:event_AdminMouseClicked

  public static void main(String args[]) {

   
     
     

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierLogin().setVisible(true);
               

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Admin;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton jbtnConfirm;
    private javax.swing.JButton jbtn_Close;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
