import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class DbConnector2 {
        // Connection details
        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost:3306/javaproject";
        static final String USER = "root";
        static final String PASS = "3495";
 ///--------------------------------------------------------------------------------------------------------------------------
    // Insert 
    public static class  ActionListener1  implements ActionListener{
     @Override
    public void actionPerformed(ActionEvent e) {
        JFrame window2 = new JFrame();
        window2.setTitle("Enter student details");
        window2.setLayout(null);

        //Label
        JLabel lb1 = new JLabel();
        lb1.setText("Roll No      : ");
        lb1.setBounds(30, 50, 150, 50);

        JLabel lb2 = new JLabel();
        lb2.setText("Name         : ");
        lb2.setBounds(30, 80, 150, 50);

        JLabel lb3 = new JLabel();
        lb3.setText("College     : ");
        lb3.setBounds(30,110, 150,50 );

        JLabel lb4 = new JLabel();
        lb4.setText("Age            : ");
        lb4.setBounds(30, 140, 150 , 50);

        JLabel lb5 = new JLabel();
        lb5.setText("Branch      : ");
        lb5.setBounds(30, 170, 150, 50);

        JLabel  lb6 = new JLabel();
        lb6.setText("Gender      : ");
        lb6.setBounds(30, 200, 150, 50);

        JTextField tf1 = new JTextField();
        tf1.setBounds(120, 65, 150, 25);
        
        JTextField tf2 = new JTextField();
        tf2.setBounds(120, 95, 150, 25);

        JTextField tf3 = new JTextField();
        tf3.setBounds(120, 125, 150, 25);
        
        JTextField tf4 = new JTextField();
        tf4.setBounds(120, 155, 150, 25);

        JTextField tf5 = new JTextField();
        tf5.setBounds(120, 185, 150, 25);

        JTextField tf6 = new JTextField();
        tf6.setBounds(120, 215, 150, 25);
        

        // Submit button
        JButton submit  = new JButton();
        submit.setText("Submit");
        submit.setBounds(100, 260, 100, 25);

        submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String rollNo = tf1.getText().trim();
                String name = tf2.getText().trim();
                String college = tf3.getText().trim();
                String age = tf4.getText().trim();
                String branch = tf5.getText().trim().toUpperCase();
                String gender = tf6.getText().trim();

                if(rollNo.isEmpty()) {
                    JOptionPane.showMessageDialog(tf1, "Please enter valid RollNo ","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(name.isEmpty()) {
                    JOptionPane.showMessageDialog(tf2, "Please enter valid Name ", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (college.isEmpty() || (!college.equalsIgnoreCase("AEC") && !college.equalsIgnoreCase("ACOE") && !college.equalsIgnoreCase("ACET"))) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid College", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else if(age.length() == 0) {
                    JOptionPane.showMessageDialog(tf4, " Please enter valid age (a positive integer)","Error", JOptionPane.ERROR_MESSAGE);

                }
                else if(age.length() > 0) {
                    int ag;
                    ag = Integer.parseInt(age);
                    if(ag <= 0) {
                        JOptionPane.showMessageDialog(tf4, " Please enter valid age (a positive integer)","Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!branch.equals("CSE") && !branch.equals("IT") && !branch.equals("AIML") && !branch.equals("DS") && !branch.equals("IOT") && !branch.equals("ECE")) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Branch", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    else if (gender.isEmpty() || (!gender.equalsIgnoreCase("MALE") && !gender.equalsIgnoreCase("FEMALE"))) {
                        JOptionPane.showMessageDialog(tf6, "Please enter a valid Gender", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    else {
                        String message =      "Roll No: "  + rollNo + "\n"
                                    + "Name   : " + name + "\n" 
                                    + "College: " + college + "\n"
                                    + "Age    : " + age+ "\n" 
                                    + "Branch : " + branch + "\n"
                                    + "Gender : " + gender; 
                                    
                        int choice = JOptionPane.showConfirmDialog(null, message, "Confirm Details", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            // User clicked "Confirm"
                            // Here you can perform actions like saving the details
                            // or closing the window, depending on your requirements
                            Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish a connection to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare the SQL statement for insertion
            String sql = "INSERT INTO student (rollno, name, college, age, branch, gender) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // Set the values for the prepared statement
            pstmt.setString(1, tf1.getText().trim()); // Roll No
            pstmt.setString(2, tf2.getText().trim()); // Name
            pstmt.setString(3, tf3.getText().trim()); // College
            pstmt.setInt(4, Integer.parseInt(tf4.getText().trim())); // Age
            pstmt.setString(5, tf5.getText().trim().toUpperCase()); // Branch
            pstmt.setString(6, tf6.getText().trim()); // Gender

            // Execute the INSERT statement
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Successfully inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
                window2.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
                JOptionPane.showMessageDialog(null, "Successfully inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
                window2.dispose();
                         } else {
                          // User clicked "No" or closed the dialog, no action required
                        }
                    }  
                }
        
            }
            
        });

        window2.add(lb1);
        window2.add(lb2);
        window2.add(lb3);
        window2.add(lb4);
        window2.add(lb5);
        window2.add(lb6);

        window2.add(tf1);
        window2.add(tf2);
        window2.add(tf3);
        window2.add(tf4);
        window2.add(tf5);
        window2.add(tf6);

        window2.add(submit);
        window2.setResizable(false);     // restric from resizing
        window2.setSize(350, 350);      // used to set the size of the window
        window2.setVisible(true);
        window2.setLocation(100, 200);
    }
}

 ///--------------------------------------------------------------------------------------------------------------------------
// DELETE
    public static class  ActionListener2  implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame window3 = new JFrame();
                window3.setTitle("Delete Student ");
                window3.setLayout(null);

                JLabel lb1 = new JLabel();
                lb1.setText("Enter Roll No: ");
                lb1.setBounds(50, 50, 150, 25);

                JTextField tf1 = new JTextField();
                tf1.setBounds(150, 50, 150 , 25);

                JButton delete = new JButton();
                delete.setText("Submit");
                delete.setBounds(100, 100, 150, 25);

                delete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        Connection conn = null;
                        PreparedStatement pstmt = null;
                
                        try {

                            String rollno = tf1.getText().trim();
                            // Load the MySQL JDBC driver
                            Class.forName(JDBC_DRIVER);
                
                            // Establish a connection to the database
                            conn = DriverManager.getConnection(DB_URL, USER, PASS);
                
                            // Prepare the SQL statement for insertion
                            String sql = "DELETE from student  where rollno = ?";
                            pstmt = conn.prepareStatement(sql);

                            // Set the roll number as a parameter
                            pstmt.setString(1, rollno);
                
                            // Execute the INSERT statement
                            int rowsAffected = pstmt.executeUpdate();
                
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Successfully deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
                                window3.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to delete data or enter valid rollno", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
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
                    
                }); 

                window3.add(lb1);
                window3.add(tf1);
                window3.add(delete);
                window3.setSize(400, 200);
                window3.setVisible(true);
                window3.setLocation(200, 200);
            }
       } 
 ///--------------------------------------------------------------------------------------------------------------------------
         //    // update
         public static  class ActionListener3 implements ActionListener {
            public static void execute(String selected, String text, String rollNo, JFrame window4) {

                Connection conn = null;
                PreparedStatement pstmt = null;

                try {
                    // Load the MySQL JDBC driver
                    Class.forName(JDBC_DRIVER);
        
                    // Establish a connection to the database
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
                    // Prepare the SQT statement for insertion
                    // String sql = "UPDATE student set " + selected + " =  ? where rollno = ? ";
                    String sql = "UPDATE student SET " + selected + " = ? WHERE rollno = ?";
        
                    pstmt = conn.prepareStatement(sql);
        
                    // Set the values for the prepared statement
                    // pstmt.setString(1, text); // Roll No
                    // pstmt.setString(2, rollNo); // Name
                    pstmt.setString(1, text); // Value to be updated
                    pstmt.setString(2, rollNo); // Roll number condition
        
                    // Execute the INSERT statement
                    int rowsAffected = pstmt.executeUpdate();
        
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Successfully updated", "Information", JOptionPane.INFORMATION_MESSAGE);
                        window4.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update data", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    // Close resources
                    try {
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                        // JOptionPane.showMessageDialog(null, "Successfully inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
                        window4.dispose();
                                //  } else {
                                //   // User clicked "No" or closed the dialog, no action required
                                // }                 

            }
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame window4 = new JFrame();
                window4.setVisible(true);
                window4.setSize(500,200);
                window4.setLayout(null);
                window4.setLocation(300, 200);
                window4.setTitle("Update Student details");

                JLabel lb1 = new JLabel();
                lb1.setText("Enter Roll No: ");
                lb1.setBounds(50, 50, 150, 25);

                JTextField tf1 = new JTextField();
                tf1.setBounds(180, 50, 100 , 25);

                
                JComboBox<String> jcb1 = new JComboBox<>();
                jcb1.setBounds(50, 100, 100, 25);
                window4.add(jcb1);

                jcb1.addItem("name");
                jcb1.addItem("age");
                jcb1.addItem("branch");
                jcb1.addItem("gender");
                jcb1.addItem("college");

                JTextField tf2 = new JTextField();
                tf2.setBounds(180, 100, 100, 25);
                window4.add(tf2);
                //jb2

                      JButton jb2 = new JButton();
                jb2.setText("View");
                jb2.setBounds(350, 50, 100,25);
                jb2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e1) {

                        String rollno = tf1.getText().trim();

                        if(rollno.isEmpty()) {
                            JOptionPane.showMessageDialog(jb2, "please enter valid rollno", "error", 0);
                        }
                        else {
    
                        JFrame frame = new JFrame();
                        frame.setTitle("Table data");
                        JPanel panel = new JPanel();
                        JTable table = new JTable();
                        DefaultTableModel model = new DefaultTableModel();
                        table.setModel(model);
                        try {
                            String url = "jdbc:mysql://localhost:3306/javaproject";
                            String user = "root";
                            String password = "3495";
                            Connection connection = DriverManager.getConnection(url, user, password);
                            Statement statement = connection.createStatement();
                            String query = "SELECT * FROM student WHERE rollno = '" + rollno + "'";

                            ResultSet resultSet = statement.executeQuery(query);
                            int columnCount = resultSet.getMetaData().getColumnCount();
                            for (int i = 1; i <= columnCount; i++) {
                                model.addColumn(resultSet.getMetaData().getColumnName(i));
    
                        }
                        while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                        e.printStackTrace();
            }
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            frame.add(panel);
            Font myFont = new Font("CoFo Sans",Font.BOLD,17);
            frame.setResizable(false);
            frame.setSize(600,600);
            frame.setVisible(true);
                    }}
                });
           

                JButton update = new JButton();
                update.setText("Submit");
                update.setBounds(350, 100, 100, 25);
                update.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                String selected = (String) jcb1.getSelectedItem();
                String rollNo = tf1.getText().trim();
                String text = tf2.getText().trim();
                
                
                if(rollNo.isEmpty()) {
                    JOptionPane.showMessageDialog(tf1, "Please enter valid RollNo ","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(text.isEmpty()) {
                    JOptionPane.showMessageDialog(tf2, "Please enter valid" + selected, "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if(selected.equals("name")) {
                        String name = text;
                        execute(selected, text, rollNo, window4);
                    }

                 if(selected.equals("branch")) {
                    String branch =  text;
                     if (!branch.equals("CSE") && !branch.equals("IT") && !branch.equals("AIML") && !branch.equals("DS") && !branch.equals("IOT") && !branch.equals("ECE")) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Branch", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        execute(selected, text, rollNo, window4);
                    }
                }
                 if(selected.equals("gender")) {
                    String gender = text;
                    if (gender.isEmpty() || (!gender.equalsIgnoreCase("MALE") && !gender.equalsIgnoreCase("FEMALE"))) {
                        JOptionPane.showMessageDialog(tf2, "Please enter a valid Gender", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        execute(selected, text, rollNo, window4);
                    }
                }

                 if(selected.equals("college")) {
                    String college = text;
                    if (college.isEmpty() || (!college.equalsIgnoreCase("AEC") && !college.equalsIgnoreCase("ACOE") && !college.equalsIgnoreCase("ACET"))) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid College", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    execute(selected, text, rollNo, window4);
                }
                }
                if(selected.equals("age")) {
                    String age = text;
                if(age.length() == 0) {
                    JOptionPane.showMessageDialog(tf2, " Please enter valid age (a positive integer)","Error", JOptionPane.ERROR_MESSAGE);

                }

                else if(age.length() > 0) {
                    int ag;
                    ag = Integer.parseInt(age);
                    if(ag <= 0) {
                        JOptionPane.showMessageDialog(tf2, " Please enter valid age (a positive integer)","Error", JOptionPane.ERROR_MESSAGE);
                    } 
                    else {
                        execute(selected, text, rollNo, window4);
                    }
                }
                }
                    }  
                }
                });


                window4.add(lb1);
                window4.add(tf1);
                window4.add(jb2);
                window4.add(update);
            }
        }

 ///--------------------------------------------------------------------------------------------------------------------------

    //    view
       public static class ActionListener4 implements ActionListener {
        @SuppressWarnings("unchecked")
        @Override

                public void actionPerformed(ActionEvent e1) {

                    JFrame frame = new JFrame();
                    frame.setTitle("Table data");
                    JPanel panel = new JPanel();
                    JTable table = new JTable();
                    DefaultTableModel model = new DefaultTableModel();
                    table.setModel(model);
                    try {
                        String url = "jdbc:mysql://localhost:3306/javaproject";
                        String user = "root";
                        String password = "3495";
                        Connection connection = DriverManager.getConnection(url, user, password);
                        Statement statement = connection.createStatement();
                        String query = "SELECT * FROM student";
                        ResultSet resultSet = statement.executeQuery(query);
                        int columnCount = resultSet.getMetaData().getColumnCount();
                        for (int i = 1; i <= columnCount; i++) {
                            model.addColumn(resultSet.getMetaData().getColumnName(i));

                    }
                    while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(row);
                }
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                    e.printStackTrace();
        }
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel);
        Font myFont = new Font("CoFo Sans",Font.BOLD,17);
        frame.setResizable(false);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setLocation(400, 200);
                }
            }

 ///--------------------------------------------------------------------------------------------------------------------------
       public static void main(String[] args) {
        // 1. Connecting to the database
        // URL, username, password

        // An object of JFrame class
         JFrame window = new JFrame();
         window.setTitle("Student data");
         window.setLayout(null);

         JLabel jb1 = new JLabel();
         jb1.setText("Aditya Educational Institutions");
         jb1.setLayout(null);
         jb1.setBounds(120 , 40, 400, 25);
         window.add(jb1);
         Font font = new Font(jb1.getFont().getName(), Font.PLAIN, 26);
         jb1.setFont(font);



         // INSERT
         JButton ins = new JButton();
         ins.setBounds(100, 100, 75, 50);
         ins.setText("insert");
         ins.addActionListener(new ActionListener1());

         // DELETE
         JButton del = new JButton();
         del.setBounds(200, 100, 75, 50);
         del.setText("delete");
         del.addActionListener(new ActionListener2());

           // UPDATE
           JButton upd = new JButton();
           upd.setBounds(300,100, 75, 50);
           upd.setText("update");
           upd.addActionListener(new ActionListener3());

         // VIEW
         JButton view = new JButton();
         view.setBounds(400, 100, 75, 50);
         view.setText("view");
         view.addActionListener(new ActionListener4());

  // Add component to window
 //        window.add(lb1);
         window.add(ins);
         window.add(del);
         window.add(upd);
         window.add(view);
         window.setResizable(false);     // restric from resizing
         window.setSize(600, 200);      // used to set the size of the window
         window.setVisible(true);                // Makes window visible

    }
}
