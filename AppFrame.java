import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AppFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private MainFrame mainFrame;
private String[][] HOTELSData;
    private String[][] priceData;
    public AppFrame() {
        setTitle("تسجيل الدخول");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 220);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        // تعديل خصائص الألوان وحجم الخط
        Color backgroundColor = new Color(60, 140, 170);
        Color labelColor = new Color(60,60, 60);
        Font font = new Font("Arial", Font.PLAIN, 19);

        getContentPane().setBackground(backgroundColor);

        JLabel hotelsLabel = new JLabel("HOTELS");
        hotelsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hotelsLabel.setForeground(labelColor);
        hotelsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(hotelsLabel);

        JLabel emptyLabel1 = new JLabel();
        add(emptyLabel1);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel usernameLabel = new JLabel("اسم المستخدم");
        usernameLabel.setForeground(labelColor);
        usernameLabel.setFont(font);
        usernameField = new JTextField(20);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        add(usernamePanel);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel passwordLabel = new JLabel("كلمة المرور");
        passwordLabel.setForeground(labelColor);
        passwordLabel.setFont(font);
        passwordField = new JPasswordField(20);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        JLabel emptyLabel2 = new JLabel();
        add(emptyLabel2);

        JButton loginButton = new JButton("تسجيل الدخول");
        loginButton.setFont(font);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("abdo") && password.equals("1230")) {
                    mainFrame = new MainFrame();
                    setVisible(false);
                    mainFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(AppFrame.this, "خطأ في اسم المستخدم أو كلمة المرور", "خطأ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(loginButton);

        setVisible(true);
    }

    public class MainFrame extends JFrame {
        public MainFrame() {
            setTitle("Main Frame");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(450, 220);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1));

            // تعديل خصائص الألوان وحجم الخط
            Color backgroundColor = new Color(60, 140, 170);
            Color labelColor = new Color(50, 50, 50);
            Font font = new Font("Arial", Font.PLAIN, 19);

            getContentPane().setBackground(backgroundColor);

            JButton insertButton = new JButton("Insert");
            insertButton.setFont(font);
            insertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int numberOfRows = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows:"));
                    int numberOfColumns = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of columns:"));

                     HOTELSData = new String[numberOfRows][numberOfColumns];
                    priceData = new String[numberOfRows][numberOfColumns];

                    for (int i = 0; i < numberOfRows; i++) {
                        for (int j = 0; j < numberOfColumns; j++) {
                            String HotelName = JOptionPane.showInputDialog("Enter name for Hotel at position (" + (i + 1) + ", " + (j + 1) + "):");
                            HOTELSData[i][j] = HotelName;

                            String HotelPrice = JOptionPane.showInputDialog("Enter price for price at position (" + (i + 1) + ", " + (j + 1) + "):");
                            priceData[i][j] = HotelPrice;
                        }
                    }
                     JOptionPane.showMessageDialog(MainFrame.this, "تم دخول البيانات بنجاح");
                }
            });
            add(insertButton);

            JButton updateButton = new JButton("Update");
            updateButton.setFont(font);
            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                     if (HOTELSData != null && priceData != null) {
            int rowToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the row number to update:")) - 1;
            int colToUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the column number to update:")) - 1;

            if (rowToUpdate >= 0 && rowToUpdate < HOTELSData.length &&
                colToUpdate >= 0 && colToUpdate < HOTELSData[rowToUpdate].length) {

                String updatedHotelName = JOptionPane.showInputDialog("Enter updated name for Hotel at position (" + (rowToUpdate + 1) + ", " + (colToUpdate + 1) + "):");
                HOTELSData[rowToUpdate][colToUpdate] = updatedHotelName;

                String updatedHotelPrice = JOptionPane.showInputDialog("Enter updated price for price at position (" + (rowToUpdate + 1) + ", " + (colToUpdate + 1) + "):");
                priceData[rowToUpdate][colToUpdate] = updatedHotelPrice;

                JOptionPane.showMessageDialog(MainFrame.this, "تم تحديث البيانات بنجاح");
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "الرجاء إدخال أرقام صحيحة للصف والعمود", "خطأ", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(MainFrame.this, "لم يتم إدخال بيانات بعد.", "تنبيه", JOptionPane.WARNING_MESSAGE);
        }
                }
            });
            add(updateButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.setFont(font);
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                     if (HOTELSData != null && priceData != null) {
            int rowToDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter the row number to delete:")) - 1;
            int colToDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter the column number to delete:")) - 1;

            if (rowToDelete >= 0 && rowToDelete < HOTELSData.length &&
                colToDelete >= 0 && colToDelete < HOTELSData[rowToDelete].length) {

                // Shift elements to fill the deleted position
                for (int i = rowToDelete; i < HOTELSData.length - 1; i++) {
                    HOTELSData[i] = HOTELSData[i + 1].clone();
                    priceData[i] = priceData[i + 1].clone();
                }

                // Remove the last row
                HOTELSData = Arrays.copyOf(HOTELSData, HOTELSData.length - 1);
                priceData = Arrays.copyOf(priceData, priceData.length - 1);

                JOptionPane.showMessageDialog(MainFrame.this, "تم حذف البيانات بنجاح");
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "الرجاء إدخال أرقام صحيحة للصف والعمود", "خطأ", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(MainFrame.this, "لم يتم إدخال بيانات بعد.", "تنبيه", JOptionPane.WARNING_MESSAGE);
        }
                }
            });
            add(deleteButton);

           JButton showButton = new JButton("Show");
            showButton.setFont(font);
            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                         if (HOTELSData != null && priceData != null) {
                        // Display the entered data
                        StringBuilder message = new StringBuilder("البيانات المدخلة:\n\n");
                        for (int i = 0; i < HOTELSData.length; i++) {
                            for (int j = 0; j < HOTELSData[i].length; j++) {
                                message.append("Hotel at position (").append(i + 1).append(", ").append(j + 1).append("): ").append(HOTELSData[i][j]).append("\n");
                                message.append("Price at position (").append(i + 1).append(", ").append(j + 1).append("): ").append(priceData[i][j]).append("\n");
                            }
                        }
                        JOptionPane.showMessageDialog(MainFrame.this, message.toString());
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this, "لم يتم إدخال بيانات بعد.", "تنبيه", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            add(showButton);

            setVisible(true);
           
            
              JButton abdoButton = new JButton("abdo");
            showButton.setFont(font);
            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                       Color backgroundColor = new Color(60, 140, 170);
            Color labelColor = new Color(50, 50, 50);
            Font font = new Font("Arial", Font.PLAIN, 19);
  showButton.coloour red;
            getContentPane().setBackground(backgroundColor);

                                  }
            });
            add(abdoButton);

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppFrame();
            }
        });
    }
}