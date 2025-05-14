package packageAdminandEmployeeLogin;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Component;
import javax.swing.table.TableColumn;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color; 
import javax.swing.AbstractCellEditor; 
import java.awt.event.MouseEvent;
import javax.swing.event.CellEditorListener; 
import javax.swing.event.ChangeEvent; 
import java.util.EventObject; 
import java.awt.Dimension; 
import javax.swing.JPanel;
import javax.swing.table.JTableHeader;


public class AdminMenuManagement extends javax.swing.JPanel {

    private boolean isAddPanelVisible = false;
    private boolean isEditPanelVisible = false; 
    private String selectedImagePath = null; 
    private String editingImagePath = null; 
    private int editingProductId = -1; 


    private final Color BUTTON_COLOR = new Color(128, 80, 30);


    public AdminMenuManagement() {
      
        initComponents();
         JTableHeader tableHeader = menuTable.getTableHeader();
        tableHeader.setBackground(new Color(67, 58, 47));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setOpaque(true);
        tableHeader.repaint();
        setupPopUpPanel(); 
        setupEditPopUpPanel(); 
        setupActionListeners();
        loadMenuData(null);
        setupImageColumnRenderer();
        setupActionColumnRendererAndEditor();
        updateRecordCountLabel();
    }

   
    public static class MySQLConnection {
        public static Connection getConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/javabeans_db", "root", "");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found. Add the Connector/J JAR to your project.", "Database Driver Error", JOptionPane.ERROR_MESSAGE);
                return null;
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database Connection Failed: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
    }
       
    
   
    private void setupPopUpPanel() {
      
        if (addMenuPanelSlide != null) {
            addMenuPanelSlide.setVisible(false);
            if (jPanel1 != null) {
                 addMenuPanelSlide.setLocation(-addMenuPanelSlide.getWidth(), (jPanel1.getHeight() - addMenuPanelSlide.getHeight()) / 2); // Centered vertically
            } else {
                addMenuPanelSlide.setLocation(-addMenuPanelSlide.getWidth(), 0);
            }
        } else {
            System.err.println("WARNING: addMenuPanelSlide component not found or not named correctly in designer!");
        }
    }

   private void setupEditPopUpPanel() {
    if (editMenuPanelSlide != null) {
        editMenuPanelSlide.setVisible(false);
        
        if (jPanel1 != null) {
           
            editMenuPanelSlide.setLocation(jPanel1.getWidth(), (jPanel1.getHeight() - editMenuPanelSlide.getHeight()) / 2); 
        } else {
          
            editMenuPanelSlide.setLocation(this.getWidth(), 0); 
        }
    } else {
        System.err.println("WARNING: editMenuPanelSlide component not found or not named correctly in designer!");
    }
}

    /**
     * Toggles the visibility and position of the pop-up ADD menu panel.
     * @param show true to show the panel, false to hide it.
     */
    private void toggleAddPopUpPanel(boolean show) {
        if (addMenuPanelSlide == null) {
            System.err.println("ERROR: addMenuPanelSlide component is null in toggleAddPopUpPanel!");
            return; 
        }

        
        if (show && isEditPanelVisible) {
            toggleEditPopUpPanel(false);
        }

        if (show) {
            
            if (addProduct_txt != null) addProduct_txt.setText("");
            if (addPrice_txt != null) addPrice_txt.setText("");
            if (Category_cmb != null) Category_cmb.setSelectedIndex(0);
            if (addQuantity_txt != null) addQuantity_txt.setText("");
            if (StockAvailability_txt != null) StockAvailability_txt.setText(""); 
            selectedImagePath = null; 
            if (ImagePath_txt != null) { 
                ImagePath_txt.setText("No image selected"); 
            }
          
            if (addProduct_txt != null) addProduct_txt.requestFocusInWindow();

           
             addMenuPanelSlide.setLocation(0, addMenuPanelSlide.getY()); 
            addMenuPanelSlide.setVisible(true);
        } else {
            addMenuPanelSlide.setVisible(false);
            addMenuPanelSlide.setLocation(-addMenuPanelSlide.getWidth(), addMenuPanelSlide.getY());
        }

       
        addMenuPanelSlide.revalidate();
        addMenuPanelSlide.repaint();
          if (jPanel1 != null) { 
              jPanel1.revalidate();
              jPanel1.repaint();
          }

        isAddPanelVisible = show; 
    }

    /**
     * Toggles the visibility and position of the pop-up EDIT menu panel.
     * @param show true to show the panel, false to hide it.
     */
    private void toggleEditPopUpPanel(boolean show) {
         if (editMenuPanelSlide == null || jPanel1 == null) {
        System.err.println("ERROR: editMenuPanelSlide or jPanel1 component is null in toggleEditPopUpPanel!");
        return;
    }

       
       if (show && isAddPanelVisible) {
        toggleAddPopUpPanel(false);
    }

        if (show) {
       
        int targetX = jPanel1.getWidth() - editMenuPanelSlide.getWidth();
        
         if (targetX < 0) targetX = 0;

      
         editMenuPanelSlide.setLocation(targetX, editMenuPanelSlide.getY()); 
        editMenuPanelSlide.setVisible(true);
       
         if (editProduct_txt != null) editProduct_txt.requestFocusInWindow();
        }  else {
       
        editMenuPanelSlide.setVisible(false);
       
         editMenuPanelSlide.setLocation(jPanel1.getWidth(), editMenuPanelSlide.getY());

        
        editingProductId = -1;
        editingImagePath = null;
        }

        
       editMenuPanelSlide.revalidate();
    editMenuPanelSlide.repaint();
     if (jPanel1 != null) {
         jPanel1.revalidate();
         jPanel1.repaint();
     }

    isEditPanelVisible = show;
}


     /**
     * Populates the edit panel with data from the selected row and shows the panel.
     * Called by the ActionPanelRendererEditor's "Edit" button.
     * @param productId The ID of the product to edit.
     */
    private void fetchAndShowEditPanel(int productId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // Added 'quantity' to the SELECT statement
        String sql = "SELECT product_id, name, price, category, quantity, stock_availability, image_path FROM menu_management WHERE product_id = ?";

        try {
            conn = MySQLConnection.getConnection();
            if (conn == null) return; // Connection failed, error message shown in getConnection()

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                 // Store editing product details
                 editingProductId = rs.getInt("product_id");
                 editingImagePath = rs.getString("image_path");
                 selectedImagePath = null; // Reset selectedImagePath for edit

                // Populate the edit panel fields
                if (editProduct_txt != null) editProduct_txt.setText(rs.getString("name"));
                if (editPrice_txt != null) editPrice_txt.setText(String.valueOf(rs.getDouble("price")));
                if (editCategory_cmb != null) editCategory_cmb.setSelectedItem(rs.getString("category"));
                int quantity = rs.getInt("quantity"); // Get quantity from ResultSet
                if (editQuantity_txt != null) editQuantity_txt.setText(String.valueOf(quantity)); // Set quantity field
                 // Calculate and set stock availability based on fetched quantity
                if (editStockAvailability_txt != null) editStockAvailability_txt.setText(calculateStockAvailability(quantity));
                if (editImagePath_txt != null) {
                    if (editingImagePath != null && !editingImagePath.isEmpty()) {
                         File imageFile = new File(editingImagePath);
                         editImagePath_txt.setText(imageFile.getName()); // Show just the file name
                    } else {
                        editImagePath_txt.setText("No image selected"); // Show default text if no image path
                    }
                }

                // Show the edit panel
                toggleEditPopUpPanel(true);

            } else {
                // Item not found in the database
                JOptionPane.showMessageDialog(this, "Error fetching data for editing. Item not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
             ex.printStackTrace();
             JOptionPane.showMessageDialog(this, "Database error fetching data for editing: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
             // Close resources
             try { if (rs != null) rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
             try { if (pstmt != null) pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
             try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

  private String calculateStockAvailability(int quantity) {
        if (quantity <= 0) {
            return "Out of Stock";
        } else if (quantity <= 10) {
            return "Low Stock";
        } else {
            return "In Stock";
        }
  }
   
     private void saveMenuItem() {
        // Check if essential components are initialized
        if (addProduct_txt == null || addPrice_txt == null || Category_cmb == null || addQuantity_txt == null || StockAvailability_txt == null || save_btn == null) {
            System.err.println("ERROR: One or more input components are null in saveMenuItem!");
            JOptionPane.showMessageDialog(this, "Internal error: Cannot save item.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get values from input fields
        String productName = addProduct_txt.getText().trim();
        String priceStr = addPrice_txt.getText().trim();
        String category = (String) Category_cmb.getSelectedItem();
        String quantityStr = addQuantity_txt.getText().trim(); // Get quantity string
        // Stock status is now calculated, not directly from a component
        String stockStatus = StockAvailability_txt.getText().trim();


        // Input validation
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Product Name cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            addProduct_txt.requestFocus();
            return;
        }
        if (priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Price cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            addPrice_txt.requestFocus();
            return;
        }
        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 0) {
                JOptionPane.showMessageDialog(this, "Price cannot be negative.", "Input Error", JOptionPane.WARNING_MESSAGE);
                addPrice_txt.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Price format. Please enter a number.", "Input Error", JOptionPane.WARNING_MESSAGE);
            addPrice_txt.requestFocus();
            return;
        }

        if (category == null || category.isEmpty() || "Select Category".equals(category)) { // Assuming "Select Category" is the default/placeholder text
            JOptionPane.showMessageDialog(this, "Please select a Category.", "Input Error", JOptionPane.WARNING_MESSAGE);
            Category_cmb.requestFocus();
            return;
        }

        if (quantityStr.isEmpty()) { // Validate quantity input
             JOptionPane.showMessageDialog(this, "Quantity cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
             addQuantity_txt.requestFocus();
             return;
        }
        int quantity;
        try {
             quantity = Integer.parseInt(quantityStr);
             if (quantity < 0) {
                 JOptionPane.showMessageDialog(this, "Quantity cannot be negative.", "Input Error", JOptionPane.WARNING_MESSAGE);
                 addQuantity_txt.requestFocus();
                 return;
             }
        } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(this, "Invalid Quantity format. Please enter an integer.", "Input Error", JOptionPane.WARNING_MESSAGE);
             addQuantity_txt.requestFocus();
             return;
        }

        // Stock status is now calculated based on quantity, no need for separate validation here


        Connection conn = null;
        PreparedStatement pstmt = null;
        // Added 'quantity' to the INSERT statement
        String sql = "INSERT INTO menu_management (name, price, category, quantity, stock_availability, image_path) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = MySQLConnection.getConnection();
            if (conn == null) {
                // Connection failed, error message shown in getConnection()
                return;
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);
            pstmt.setDouble(2, price);
            pstmt.setString(3, category);
            pstmt.setInt(4, quantity); // Set quantity parameter
            pstmt.setString(5, calculateStockAvailability(quantity)); // Calculate and set stock availability

            // Set image path parameter
            if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                pstmt.setString(6, selectedImagePath);
            } else {
                pstmt.setNull(6, java.sql.Types.VARCHAR); // Save as NULL if no image selected
            }

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Menu item saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                toggleAddPopUpPanel(false); // Hide the add panel
                selectedImagePath = null; // Clear selected image path after saving
                loadMenuData(null); // Reload data in the table
            } else {
                    JOptionPane.showMessageDialog(this, "Menu item could not be saved.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error during save: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    
   private void updateMenuItem() {
         // Check if essential components are initialized
        if (editProduct_txt == null || editPrice_txt == null || editCategory_cmb == null || editQuantity_txt == null || editStockAvailability_txt == null || update_btn == null) {
            System.err.println("ERROR: One or more input components are null in updateMenuItem!");
            JOptionPane.showMessageDialog(this, "Internal error: Cannot update item.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if an item is selected for editing
        if (editingProductId == -1) {
             JOptionPane.showMessageDialog(this, "Internal error: No item selected for editing.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }

        // Get values from input fields
        String productName = editProduct_txt.getText().trim();
        String priceStr = editPrice_txt.getText().trim();
        String category = (String) editCategory_cmb.getSelectedItem();
        String quantityStr = editQuantity_txt.getText().trim(); // Get quantity string
         // Stock status is now calculated, not directly from a component
        String stockStatus = editStockAvailability_txt.getText().trim();


        // Input validation
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Product Name cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            editProduct_txt.requestFocus();
            return;
        }
         if (priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Price cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            editPrice_txt.requestFocus();
            return;
        }
        double price;
        try {
            price = Double.parseDouble(priceStr);
             if (price < 0) {
                JOptionPane.showMessageDialog(this, "Price cannot be negative.", "Input Error", JOptionPane.WARNING_MESSAGE);
                editPrice_txt.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Price format. Please enter a number.", "Input Error", JOptionPane.WARNING_MESSAGE);
            editPrice_txt.requestFocus();
            return;
        }

        if (category == null || category.isEmpty() || "Select Category".equals(category)) { // Assuming "Select Category" is the default/placeholder text
            JOptionPane.showMessageDialog(this, "Please select a Category.", "Input Error", JOptionPane.WARNING_MESSAGE);
            editCategory_cmb.requestFocus();
            return;
        }

        if (quantityStr.isEmpty()) { // Validate quantity input
             JOptionPane.showMessageDialog(this, "Quantity cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
             editQuantity_txt.requestFocus();
             return;
        }
        int quantity;
        try {
             quantity = Integer.parseInt(quantityStr);
             if (quantity < 0) {
                 JOptionPane.showMessageDialog(this, "Quantity cannot be negative.", "Input Error", JOptionPane.WARNING_MESSAGE);
                 editQuantity_txt.requestFocus();
                 return;
             }
        } catch (NumberFormatException ex) {
             JOptionPane.showMessageDialog(this, "Invalid Quantity format. Please enter an integer.", "Input Error", JOptionPane.WARNING_MESSAGE);
             editQuantity_txt.requestFocus();
             return;
        }

        // Stock status is now calculated based on quantity, no need for separate validation here


        Connection conn = null;
        PreparedStatement pstmt = null;
        // Added 'quantity' to the UPDATE statement
        String sql = "UPDATE menu_management SET name = ?, price = ?, category = ?, quantity = ?, stock_availability = ?, image_path = ? WHERE product_id = ?";

        try {
            conn = MySQLConnection.getConnection();
            if (conn == null) {
                // Connection failed, error message shown in getConnection()
                return;
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);
            pstmt.setDouble(2, price);
            pstmt.setString(3, category);
            pstmt.setInt(4, quantity); // Set quantity parameter
            pstmt.setString(5, calculateStockAvailability(quantity)); // Calculate and set stock availability

            // Determine which image path to use (newly selected or original)
            if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                 pstmt.setString(6, selectedImagePath); // Use the newly selected image path
            } else if (editingImagePath != null && !editingImagePath.isEmpty()) {
                 pstmt.setString(6, editingImagePath); // Use the original image path
            }
            else {
                pstmt.setNull(6, java.sql.Types.VARCHAR); // Set to NULL if neither is available
            }

            pstmt.setInt(7, editingProductId); // Set the product ID for the WHERE clause

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Menu item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                toggleEditPopUpPanel(false); // Hide the edit panel
                editingProductId = -1; // Reset editing state
                editingImagePath = null; // Reset editing image path
                selectedImagePath = null; // Clear selected image path
                loadMenuData(null); // Reload data in the table
            } else {
                // Item not found or no changes made
                JOptionPane.showMessageDialog(this, "Menu item could not be updated. Item not found or no changes made.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error during update: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * Archives and then deletes a menu item from the database.
     * Uses a transaction for data integrity.
     * Called by the ActionPanelRendererEditor's "Delete" button.
     * @param productId The ID of the product to delete.
     */
    private void deleteMenuItem(int productId) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item? This action will also archive it.", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Connection conn = null;
            PreparedStatement selectPstmt = null;
            PreparedStatement archivePstmt = null;
            PreparedStatement deletePstmt = null;
            ResultSet rs = null;

            // SQL statements for selecting, archiving, and deleting
            // Added 'quantity' to the SELECT statement for archiving
            String selectSql = "SELECT product_id, name, price, category, quantity, stock_availability, image_path FROM menu_management WHERE product_id = ?";
            // Added 'quantity' to the INSERT statement for archiving
            String archiveSql = "INSERT INTO archive_menu_javabeans (product_id, name, price, category, quantity, stock_availability, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String deleteSql = "DELETE FROM menu_management WHERE product_id = ?";

            try {
                conn = MySQLConnection.getConnection();
                if (conn == null) {
                    return; // Connection failed, error message shown in getConnection()
                }

                // Start transaction
                conn.setAutoCommit(false);

                // 1. Select the item to be archived
                selectPstmt = conn.prepareStatement(selectSql);
                selectPstmt.setInt(1, productId);
                rs = selectPstmt.executeQuery();

                if (rs.next()) {
                    // 2. Archive the item
                    archivePstmt = conn.prepareStatement(archiveSql);
                    archivePstmt.setInt(1, rs.getInt("product_id"));
                    archivePstmt.setString(2, rs.getString("name"));
                    archivePstmt.setDouble(3, rs.getDouble("price"));
                    archivePstmt.setString(4, rs.getString("category"));
                    archivePstmt.setInt(5, rs.getInt("quantity")); // Archive quantity
                    archivePstmt.setString(6, rs.getString("stock_availability"));
                    archivePstmt.setString(7, rs.getString("image_path"));
                    int archiveRowsAffected = archivePstmt.executeUpdate();

                    if (archiveRowsAffected > 0) {
                        // 3. Delete the item from the main table if archiving was successful
                        deletePstmt = conn.prepareStatement(deleteSql);
                        deletePstmt.setInt(1, productId);
                        int deleteRowsAffected = deletePstmt.executeUpdate();

                         if (deleteRowsAffected > 0) {
                            // Commit the transaction if both archive and delete were successful
                            conn.commit();
                            JOptionPane.showMessageDialog(this, "Menu item deleted and archived successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            loadMenuData(null); // Reload data in the table
                        } else {
                            // Rollback if delete failed
                            conn.rollback();
                            JOptionPane.showMessageDialog(this, "Menu item could not be deleted from main table.", "Database Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Rollback if archiving failed
                        conn.rollback();
                        JOptionPane.showMessageDialog(this, "Menu item could not be archived.", "Database Error", JOptionPane.ERROR_MESSAGE);
                    }


                } else {
                    // Rollback if item not found for selection
                    conn.rollback();
                    JOptionPane.showMessageDialog(this, "Menu item not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
                // Rollback on any SQL exception
                try {
                    if (conn != null) conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Database error during delete/archive: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Close resources and reset auto-commit
                try { if (rs != null) rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                try { if (selectPstmt != null) selectPstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                try { if (archivePstmt != null) archivePstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                try { if (deletePstmt != null) deletePstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
                try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); } // Reset auto-commit
                try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }



    /**
     * Loads menu data into the JTable, optionally filtering by a search term.
     * It loads images into the table model and includes an "Action" column.
     * @param searchTerm The term to search for in product name or category, or null/empty to load all data.
     */
    private void loadMenuData(String searchTerm) {
        // Check if the table component is initialized
        if (menuTable == null) {
            System.err.println("ERROR: menuTable component is null in loadMenuData!");
            return;
        }

      
        Object[] columnNames = {"ID", "Product Name", "Price", "Category", "Quantity" ,"Stock Availability", "Image", "Action"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
             @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Specify the class for each column for proper rendering
                if (columnIndex == 0 || columnIndex == 4) { // ID and Quantity are Integers
                    return Integer.class;
                }
                if (columnIndex == 2) { // Price is a Double
                     return Double.class;
                }
                if (columnIndex == 6) { // Image column
                    return ImageIcon.class;
                }
                 if (columnIndex == 7) { // Action column (contains buttons)
                    // Return Object.class or a custom class if needed for renderer/editor
                     return Object.class;
                 }
                return super.getColumnClass(columnIndex); // Default to Object for others
            }

             @Override
            public boolean isCellEditable(int row, int column) {
                // Only the Action column is editable (contains buttons)
                 return column == 7;
            }

             // This method is needed for the Action column editor to work correctly
             @Override
            public void setValueAt(Object aValue, int row, int intColumn) {
                // The editor handles the action, so we don't need to set a value here
                 // This method is called by the DefaultCellEditor when editing stops
                 // For the Action column, the editor's action listener handles the logic
                 // We can leave this empty or call super if needed, but for button actions, it's often not necessary.
                 // if (isCellEditable(row, column)) {
                 //    super.setValueAt(aValue, row, column);
                 // }
             }

        };

        menuTable.setModel(model); // Set the new model to the table

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;

        // SQL query to select data, including 'quantity'
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            // Search by product name or category (case-insensitive)
            sql = "SELECT product_id, name, price, category, quantity, stock_availability, image_path FROM menu_management WHERE LOWER(name) LIKE ? OR LOWER(category) LIKE ? ORDER BY product_id";
        } else {
            // Load all data
            sql = "SELECT product_id, name, price, category, quantity, stock_availability, image_path FROM menu_management ORDER BY product_id";
        }


        try {
            conn = MySQLConnection.getConnection();
            if (conn == null) return; // Connection failed, error message shown in getConnection()

            pstmt = conn.prepareStatement(sql);

            // Set parameters for the search query
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                String searchPattern = "%" + searchTerm.trim().toLowerCase() + "%";
                pstmt.setString(1, searchPattern);
                pstmt.setString(2, searchPattern);
            }

            rs = pstmt.executeQuery();

            // Populate the table model with data from the database
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity"); // Get quantity from ResultSet
                String stock = rs.getString("stock_availability"); // Get stock from ResultSet
                String imagePath = rs.getString("image_path");

                ImageIcon itemImage = null;
                if (imagePath != null && !imagePath.isEmpty()) {
                    try {
                        // Load and scale the image
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                             ImageIcon originalIcon = new ImageIcon(imagePath);
                             Image originalImage = originalIcon.getImage();

                             // Scale the image to a desired size
                             int desiredWidth = 50;
                             int desiredHeight = 50;
                             Image scaledImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
                             itemImage = new ImageIcon(scaledImage);
                        } else {
                            System.err.println("Image file not found at path: " + imagePath);
                            itemImage = null; // Set to null if file not found
                        }

                    } catch (Exception e) {
                        System.err.println("Error loading or scaling image for path: " + imagePath + " - " + e.getMessage());
                        itemImage = null; // Set to null on error
                    }
                }

                // Add a row to the table model, including the quantity and image
                // The last column (Action) will be handled by the custom renderer/editor
                model.addRow(new Object[]{id, name, price, category, quantity, stock, itemImage, id}); // Added quantity and stock here
            }

            // Update the record count label
            updateRecordCountLabel();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading menu data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

         // Re-setup renderers and editors after loading new data
         setupImageColumnRenderer();
         setupActionColumnRendererAndEditor();
    }

   
    private void setupActionListeners() {
        // Listener for the "Add Menu" button to show the add panel
        if (addmenu_btn != null) {
              addmenu_btn.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      if (!isAddPanelVisible) {
                          toggleAddPopUpPanel(true); // Show the add panel
                      }
                  }
              });
        } else {
             System.err.println("WARNING: addmenu_btn component not found or not named correctly in designer!");
        }

        // Listener for the "Close" button on the add panel
        if (close_btn != null) {
              close_btn.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                       if (isAddPanelVisible) {
                           toggleAddPopUpPanel(false); // Hide the ADD panel
                       }
                  }
              });
        } else {
             System.err.println("WARNING: close_btn component not found or not named correctly in designer!");
        }

        // Listener for the "Close" button on the edit panel
        if (editClose_btn != null) {
              editClose_btn.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                       if (isEditPanelVisible) {
                           toggleEditPopUpPanel(false); // Hide the EDIT panel
                       }
                  }
              });
        } else {
             System.err.println("WARNING: editClose_btn component not found or not named correctly in designer!");
        }

        // Listener for the "Browse Image" button on the add panel
        if (browseImage_btn != null) {
            browseImage_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call browseImageFile and update the ImagePath_txt field
                    selectedImagePath = browseImageFile(ImagePath_txt);
                }
            });
        } else {
            System.err.println("WARNING: browseImage_btn component not found or not named correctly in designer!");
        }

        // Listener for the "Browse Image" button on the edit panel
        if (editBrowseImage_btn != null) {
            editBrowseImage_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     // Call browseImageFile and update the editImagePath_txt field
                    selectedImagePath = browseImageFile(editImagePath_txt);
                }
            });
        } else {
            System.err.println("WARNING: editBrowseImage_btn component not found or not named correctly in designer!");
        }

        // Listener for the "Save" button on the add panel
        if (save_btn != null) {
            save_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveMenuItem(); // Call the save method
                }
            });
        } else {
             System.err.println("WARNING: save_btn component not found or not named correctly in designer!");
        }

        // Listener for the "Update" button on the edit panel
        if (update_btn != null) {
            update_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateMenuItem(); // Call the update method
                }
            });
        } else {
             System.err.println("WARNING: update_btn component not found or not named correctly in designer!");
        }

        // Listener for the "Search" button
        if (search_btn != null) {
            search_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    performSearch(); // Call the search method
                }
            });
        } else {
             System.err.println("WARNING: search_btn component not found or not named correctly in designer!");
        }

        // Listener for pressing Enter in the search text field
          if (search_txt != null) {
              search_txt.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      performSearch(); // Call the search method
                  }
              });
          } else {
              System.err.println("WARNING: search_txt component not found or not named correctly in designer!");
          }
    }

    /**
     * Opens a file chooser dialog to select an image file.
     * @param displayField The JTextField where the selected file name should be displayed.
     * @return The absolute path of the selected file, or null if cancelled.
     */
    private String browseImageFile(JTextField displayField) {
        JFileChooser fileChooser = new JFileChooser();
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image Files (*.jpg, *.png, *.gif, *.jpeg, *.bmp)", "jpg", "png", "gif", "jpeg", "bmp");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

  
        int result = fileChooser.showOpenDialog(this); 

     
        if (result == JFileChooser.APPROVE_OPTION) {
          
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath(); 

          
            if (displayField != null) {
                 displayField.setText(selectedFile.getName());
            }
            System.out.println("Selected Image Path: " + path); 
            return path;
        } else {
          
            System.out.println("Image selection cancelled.");
           
            return null; 
        }
    }
    private void performSearch() {
        if (search_txt != null) {
            String searchTerm = search_txt.getText();
            loadMenuData(searchTerm); 
        } else {
             System.err.println("ERROR: search_txt component is null in performSearch!");
        }
    }

    private void setupImageColumnRenderer() {
        if (menuTable == null) {
            System.err.println("ERROR: menuTable component is null in setupImageColumnRenderer!");
            return;
        }

      
        int imageColumnIndex = -1;
         try {
             imageColumnIndex = menuTable.getColumnModel().getColumnIndex("Image");
         } catch (IllegalArgumentException e) {
              System.err.println("WARNING: 'Image' column not found in menuTable model.");
              return; 
         }


        if (imageColumnIndex != -1) {
            TableColumn imageColumn = menuTable.getColumnModel().getColumn(imageColumnIndex);
            imageColumn.setCellRenderer(new ImageRenderer());
              imageColumn.setPreferredWidth(60); 
              imageColumn.setMaxWidth(100); 
           
              menuTable.setRowHeight(60); 
        }
    }

    
    private void setupActionColumnRendererAndEditor() {
        if (menuTable == null) {
            System.err.println("ERROR: menuTable component is null in setupActionColumnRendererAndEditor!");
            return;
        }

        
        int actionColumnIndex = -1;
         try {
            actionColumnIndex = menuTable.getColumnModel().getColumnIndex("Action");
         } catch (IllegalArgumentException e) {
             System.err.println("WARNING: 'Action' column not found in menuTable model.");
             return; 
         }


        if (actionColumnIndex != -1) {
            TableColumn actionColumn = menuTable.getColumnModel().getColumn(actionColumnIndex);
        
            ActionPanelRendererEditor actionPanel = new ActionPanelRendererEditor(this, menuTable);
            actionColumn.setCellRenderer(actionPanel);
            actionColumn.setCellEditor(actionPanel);

            
             actionColumn.setPreferredWidth(120); 
             actionColumn.setMinWidth(120);
             actionColumn.setMaxWidth(150); 

        }
    }


  
    private class ImageRenderer extends JLabel implements TableCellRenderer {

        public ImageRenderer() {
            setOpaque(true); 
            setHorizontalAlignment(SwingConstants.CENTER); 
            setVerticalAlignment(SwingConstants.CENTER); 
            setBorder(new EmptyBorder(2, 2, 2, 2)); 
        }

        @Override
        public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
                setText(""); 
            } else {
              
                setIcon(null);
                setText("");
            }

            return this; 
        }
    }

 
    private class ActionPanelRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private JPanel panel;
        private JButton editButton;
        private JButton deleteButton;
        private AdminMenuManagement parentPanel;
        private javax.swing.JTable table; 
        private int buttonRowProductId;


        public ActionPanelRendererEditor(AdminMenuManagement parentPanel, javax.swing.JTable table) {
            this.parentPanel = parentPanel;
            this.table = table;

            panel = new JPanel();
            panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0)); 

            editButton = new JButton("Edit");
            deleteButton = new JButton("Delete");
            editButton.setBackground(BUTTON_COLOR);
            deleteButton.setBackground(BUTTON_COLOR);

          
             editButton.setForeground(Color.WHITE);
             deleteButton.setForeground(Color.WHITE);
             Dimension buttonSize = new Dimension(55, 25);
             editButton.setPreferredSize(buttonSize);
             deleteButton.setPreferredSize(buttonSize);
             editButton.setMinimumSize(buttonSize);
             deleteButton.setMinimumSize(buttonSize);
             editButton.setMaximumSize(buttonSize);
             deleteButton.setMaximumSize(buttonSize);
            editButton.setBorderPainted(false);
            editButton.setFocusPainted(false);
            deleteButton.setBorderPainted(false);
            deleteButton.setFocusPainted(false);
            editButton.setOpaque(true);
            deleteButton.setOpaque(true);
            panel.setOpaque(true);
            panel.add(editButton);
            panel.add(deleteButton);

           
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    fireEditingStopped();

                    if (buttonRowProductId != -1) {
                         parentPanel.fetchAndShowEditPanel(buttonRowProductId);
                    } else {
                         System.err.println("ERROR: Product ID not available for edit action.");
                         JOptionPane.showMessageDialog(parentPanel, "Internal error: Cannot retrieve item ID for editing.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped(); 
                    
                     if (buttonRowProductId != -1) {
                        
                         parentPanel.deleteMenuItem(buttonRowProductId);
                     } else {
                         System.err.println("ERROR: Product ID not available for delete action.");
                         JOptionPane.showMessageDialog(parentPanel, "Internal error: Cannot retrieve item ID for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
                     }
                }
            });
        }


       
        @Override
        public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           
             if (isSelected) {
                 panel.setBackground(table.getSelectionBackground());
             } else {
                 panel.setBackground(table.getBackground());
             }
            
            return panel; 
        }

     

        @Override
        public Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
            

           
             if (value instanceof Integer) {
                 buttonRowProductId = (int) value;
             } else {
                 buttonRowProductId = -1; 
                 System.err.println("WARNING: Value in Action column is not an Integer (Product ID): " + value);
             }

           
             if (isSelected) {
                 panel.setBackground(table.getSelectionBackground());
             } else {
                 panel.setBackground(table.getBackground());
             }

            return panel; 
        }

        @Override
        public Object getCellEditorValue() {
      
             return buttonRowProductId;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            
             if (anEvent instanceof MouseEvent) {
              
                 MouseEvent mouseEvent = (MouseEvent) anEvent;
                
                 if (mouseEvent.getClickCount() == 1) {
                    
                      int clickedColumn = table.columnAtPoint(mouseEvent.getPoint());
                     
                       try {
                           int actionColumnIndex = table.getColumnModel().getColumnIndex("Action");
                            return clickedColumn == actionColumnIndex;
                       } catch (IllegalArgumentException e) {
                            System.err.println("Error finding Action column index: " + e.getMessage());
                            return false; 
                       }
                 }
             }
             return false; 
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            
            return true;
        }

        @Override
        public boolean stopCellEditing() {
            
             fireEditingStopped();
            return true; 
        }

        @Override
        public void cancelCellEditing() {
          
             fireEditingCanceled();
        }
    }

    private int getRecordCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = -1; 

        String sql = "SELECT COUNT(*) AS total_records FROM menu_management";

        try {
            conn = MySQLConnection.getConnection();
            if (conn == null) {
             
                return -1;
            }

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("total_records");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error getting record count: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            count = -1;
        } finally {
          
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return count;
    }
   
    private void updateRecordCountLabel() {
        if (recordCountLabel != null) { 
            int count = getRecordCount();
            if (count >= 0) {
                recordCountLabel.setText("" + count);
            } else {
                recordCountLabel.setText("Total Records: Error"); 
            }
        } else {
            System.err.println("WARNING: recordCountLabel component not found or not named correctly!");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addmenu_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        addMenuPanelSlide = new javax.swing.JPanel();
        addProduct_txt = new java.awt.TextField();
        addPrice_txt = new java.awt.TextField();
        Category_cmb = new javax.swing.JComboBox<>();
        save_btn = new javax.swing.JButton();
        close_btn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        browseImage_btn = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        ImagePath_txt = new javax.swing.JTextField();
        StockAvailability_txt = new javax.swing.JTextField();
        addQuantity_txt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        editMenuPanelSlide = new javax.swing.JPanel();
        editProduct_txt = new java.awt.TextField();
        editPrice_txt = new java.awt.TextField();
        editCategory_cmb = new javax.swing.JComboBox<>();
        update_btn = new javax.swing.JButton();
        editClose_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        editBrowseImage_btn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        editImagePath_txt = new javax.swing.JTextField();
        editStockAvailability_txt = new javax.swing.JTextField();
        editQuantity_txt = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        search_txt = new javax.swing.JTextField();
        search_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        recordCountLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        addmenu_btn.setForeground(new java.awt.Color(255, 255, 255));
        addmenu_btn.setIcon(new javax.swing.ImageIcon("C:\\JavaBeans\\src\\main\\java\\packagePictures\\icons8-add-32.png")); // NOI18N
        addmenu_btn.setBorder(null);
        addmenu_btn.setBorderPainted(false);
        addmenu_btn.setContentAreaFilled(false);
        addmenu_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addmenu_btn.setFocusPainted(false);
        addmenu_btn.setRolloverIcon(new javax.swing.ImageIcon("C:\\JavaBeans\\src\\main\\java\\packagePictures\\icons8-add-32 (1).png")); // NOI18N
        addmenu_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmenu_btnActionPerformed(evt);
            }
        });

        menuTable.setBackground(new java.awt.Color(67, 58, 47));
        menuTable.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        menuTable.setForeground(new java.awt.Color(255, 255, 255));
        menuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ProductID", "Product", "Price", "Category", "Quantity", "Stock_Availability", "Image_Path", "Actions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuTable.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        menuTable.setGridColor(new java.awt.Color(67, 58, 47));
        menuTable.setSelectionBackground(new java.awt.Color(128, 80, 30));
        menuTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        menuTable.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(menuTable);

        addMenuPanelSlide.setBackground(new java.awt.Color(67, 58, 47));

        addProduct_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        addPrice_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        Category_cmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Espresso-Based Drinks", "Brewed & Specialty Coffee", "Iced & Flavored Coffee", "Classic Cakes", "Premium Cakes", "Mini Cakes & Pastries" }));

        save_btn.setBackground(new java.awt.Color(128, 80, 30));
        save_btn.setForeground(new java.awt.Color(255, 255, 255));
        save_btn.setText("Save");
        save_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        close_btn.setBackground(new java.awt.Color(128, 80, 30));
        close_btn.setForeground(new java.awt.Color(255, 255, 255));
        close_btn.setText("Close");
        close_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Product Name");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Price");

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Category");

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Stock Availability");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Add Product");

        browseImage_btn.setText("Browse Image");
        browseImage_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Image");

        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Quantity");

        javax.swing.GroupLayout addMenuPanelSlideLayout = new javax.swing.GroupLayout(addMenuPanelSlide);
        addMenuPanelSlide.setLayout(addMenuPanelSlideLayout);
        addMenuPanelSlideLayout.setHorizontalGroup(
            addMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMenuPanelSlideLayout.createSequentialGroup()
                .addGroup(addMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addMenuPanelSlideLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(close_btn))
                    .addGroup(addMenuPanelSlideLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(addMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(browseImage_btn)
                            .addGroup(addMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(addPrice_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)
                                .addComponent(jLabel20)
                                .addComponent(addProduct_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22)
                                .addComponent(Category_cmb, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26)
                                .addComponent(addQuantity_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23)
                                .addComponent(StockAvailability_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25)
                                .addComponent(ImagePath_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 72, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addMenuPanelSlideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(save_btn)
                .addGap(41, 41, 41))
            .addGroup(addMenuPanelSlideLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addMenuPanelSlideLayout.setVerticalGroup(
            addMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addMenuPanelSlideLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(close_btn)
                .addGap(26, 26, 26)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addProduct_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPrice_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Category_cmb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26)
                .addGap(4, 4, 4)
                .addComponent(addQuantity_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StockAvailability_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addGap(5, 5, 5)
                .addComponent(ImagePath_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseImage_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(save_btn)
                .addGap(52, 52, 52))
        );

        editMenuPanelSlide.setBackground(new java.awt.Color(67, 58, 47));

        editProduct_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        editPrice_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        editCategory_cmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Espresso-Based Drinks", "Brewed & Specialty Coffee", "Iced & Flavored Coffee", "Classic Cakes", "Premium Cakes", "Mini Cakes & Pastries" }));

        update_btn.setBackground(new java.awt.Color(128, 80, 30));
        update_btn.setForeground(new java.awt.Color(255, 255, 255));
        update_btn.setText("Update");
        update_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnActionPerformed(evt);
            }
        });

        editClose_btn.setBackground(new java.awt.Color(128, 80, 30));
        editClose_btn.setForeground(new java.awt.Color(255, 255, 255));
        editClose_btn.setText("Close");
        editClose_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editClose_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editClose_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Product Name");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Price");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Category");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Stock Availability");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Edit Product");

        editBrowseImage_btn.setText("Browse Image");
        editBrowseImage_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Image");

        jLabel27.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Quantity");

        javax.swing.GroupLayout editMenuPanelSlideLayout = new javax.swing.GroupLayout(editMenuPanelSlide);
        editMenuPanelSlide.setLayout(editMenuPanelSlideLayout);
        editMenuPanelSlideLayout.setHorizontalGroup(
            editMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editMenuPanelSlideLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(editMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(editPrice_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(editMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editMenuPanelSlideLayout.createSequentialGroup()
                            .addComponent(update_btn)
                            .addGap(41, 41, 41))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editMenuPanelSlideLayout.createSequentialGroup()
                            .addComponent(editClose_btn)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editMenuPanelSlideLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(143, 143, 143))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editMenuPanelSlideLayout.createSequentialGroup()
                            .addComponent(editProduct_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)))
                    .addComponent(jLabel6)
                    .addGroup(editMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(editStockAvailability_txt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(editImagePath_txt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editMenuPanelSlideLayout.createSequentialGroup()
                            .addGap(86, 86, 86)
                            .addComponent(editBrowseImage_btn)))
                    .addGroup(editMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(editQuantity_txt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(editCategory_cmb, javax.swing.GroupLayout.Alignment.LEADING, 0, 190, Short.MAX_VALUE))))
        );
        editMenuPanelSlideLayout.setVerticalGroup(
            editMenuPanelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editMenuPanelSlideLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(editClose_btn)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editProduct_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editPrice_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editCategory_cmb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editQuantity_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editStockAvailability_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editImagePath_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(editBrowseImage_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(update_btn)
                .addGap(44, 44, 44))
        );

        search_txt.setBackground(new java.awt.Color(255, 232, 207));

        search_btn.setBackground(new java.awt.Color(250, 196, 170));
        search_btn.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        search_btn.setText("Search");
        search_btn.setBorder(null);
        search_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 232, 207));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel7.setText("Products Count");

        recordCountLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        recordCountLabel.setText("Counts");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 75, Short.MAX_VALUE))
                    .addComponent(recordCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(recordCountLabel)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(editMenuPanelSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addMenuPanelSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addmenu_btn)
                                .addGap(34, 34, 34)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addmenu_btn)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(408, 408, 408))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addMenuPanelSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editMenuPanelSlide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1934, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1256, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addmenu_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenu_btnActionPerformed
     
    }//GEN-LAST:event_addmenu_btnActionPerformed

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_close_btnActionPerformed

    private void editClose_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editClose_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editClose_btnActionPerformed

    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_save_btnActionPerformed

    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_update_btnActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Category_cmb;
    private javax.swing.JTextField ImagePath_txt;
    private javax.swing.JTextField StockAvailability_txt;
    private javax.swing.JPanel addMenuPanelSlide;
    private java.awt.TextField addPrice_txt;
    private java.awt.TextField addProduct_txt;
    private javax.swing.JTextField addQuantity_txt;
    private javax.swing.JButton addmenu_btn;
    private javax.swing.JButton browseImage_btn;
    private javax.swing.JButton close_btn;
    private javax.swing.JButton editBrowseImage_btn;
    private javax.swing.JComboBox<String> editCategory_cmb;
    private javax.swing.JButton editClose_btn;
    private javax.swing.JTextField editImagePath_txt;
    private javax.swing.JPanel editMenuPanelSlide;
    private java.awt.TextField editPrice_txt;
    private java.awt.TextField editProduct_txt;
    private javax.swing.JTextField editQuantity_txt;
    private javax.swing.JTextField editStockAvailability_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable menuTable;
    private javax.swing.JLabel recordCountLabel;
    private javax.swing.JButton save_btn;
    private javax.swing.JButton search_btn;
    private javax.swing.JTextField search_txt;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}
