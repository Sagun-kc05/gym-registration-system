import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GymGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField idField, nameField, locationField, phoneField, emailField,
    referralSourceField, paidAmountField, removalReasonField, trainerNameField, priceField, amountField;
    private JComboBox<String> dobYearComboBox, dobMonthComboBox, dobDayComboBox;
    private JComboBox<String> msYearComboBox, msMonthComboBox, msDayComboBox;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JComboBox<String> planComboBox;
    private JTextField regularPlanPriceField, premiumPlanChargeField, discountAmountField;
    private ArrayList<GymMember> members = new ArrayList<>();

    // main method
    public static void main(String[] args) {
        new GymGUI();
    }

    public GymGUI() {

        // Creating main frame
        frame = new JFrame("Gym Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Creating panel
        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        // ID Field
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 80, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 160, 25);
        panel.add(idField);

        // Add Regular Member Button
        JButton addRegularMemberButton = new JButton("Add Regular Member");
        addRegularMemberButton.setBounds(20, 60, 180, 30);
        panel.add(addRegularMemberButton);
        addRegularMemberButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    addRegularMember();
                }
            });

        // Add Premium Member Button
        JButton addPremiumMemberButton = new JButton("Add Premium Member");
        addPremiumMemberButton.setBounds(220, 60, 180, 30);
        panel.add(addPremiumMemberButton);
        addPremiumMemberButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    addPremiumMember();
                }
            });
        // Activate Membership Button
        JButton activateMembershipButton = new JButton("Activate Membership");
        activateMembershipButton.setBounds(420, 60, 180, 30);
        panel.add(activateMembershipButton);
        activateMembershipButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    activateMembership();
                }
            });

        // Deactivate Membership Button
        JButton deactivateMembershipButton = new JButton("Deactivate Membership");
        deactivateMembershipButton.setBounds(20, 110, 180, 30);
        panel.add(deactivateMembershipButton);
        deactivateMembershipButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    deactivateMembership();
                }
            });
        // Mark Attendance Button
        JButton markAttendanceButton = new JButton("Mark Attendance");
        markAttendanceButton.setBounds(220, 110, 180, 30);
        panel.add(markAttendanceButton);
        markAttendanceButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    markAttendance();
                }
            });

        // Revert Regular Member Button
        JButton revertRegularButton = new JButton("Revert Regular Member");
        revertRegularButton.setBounds(420, 110, 180, 30);
        panel.add(revertRegularButton);
        revertRegularButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    revertRegularMember();
                }
            });

        // Revert Premium Member Button
        JButton revertPremiumButton = new JButton("Revert Premium Member");
        revertPremiumButton.setBounds(20, 160, 180, 30);
        panel.add(revertPremiumButton);
        revertPremiumButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    revertPremiumMember();
                }

            });

        // Display Members Button (Regular)
        JButton displayRegularButton = new JButton("Display Regular Member");
        displayRegularButton.setBounds(220, 160, 180, 30);
        panel.add(displayRegularButton);

        displayRegularButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Create a new frame to display regular member info
                    JFrame displayFrame = new JFrame("Regular Members");
                    displayFrame.setSize(500, 400);
                    displayFrame.setLocationRelativeTo(null);
                    displayFrame.setLayout(null); // No layout manager

                    // Create a text area inside a scroll pane
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setBounds(10, 10, 470, 340); // Set bounds for the scroll pane
                    displayFrame.add(scrollPane);

                    // Collect and show only regular member info
                    StringBuilder sb = new StringBuilder();
                    for (GymMember member : members) {
                        if (!(member instanceof PremiumMember)) {
                            sb.append(member.toString()).append("\n\n");
                        }
                    }

                    textArea.setText(sb.toString().isEmpty() ? "No regular members found." : sb.toString());
                    displayFrame.setVisible(true);
                }
            });

        // Display Members Button (Premium)
        JButton displayPremiumButton = new JButton("Display Premium Member");
        displayPremiumButton.setBounds(420, 160, 180, 30);
        panel.add(displayPremiumButton);

        displayPremiumButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Create a new frame to display premium member info
                    JFrame displayFrame = new JFrame("Premium Members");
                    displayFrame.setSize(500, 400);
                    displayFrame.setLocationRelativeTo(null);
                    displayFrame.setLayout(null); // No layout manager

                    // Create a text area inside a scroll pane
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setBounds(10, 10, 470, 340); // Set bounds for the scroll pane
                    displayFrame.add(scrollPane);

                    // Collect and show only premium member info
                    StringBuilder sb = new StringBuilder();
                    for (GymMember member : members) {
                        if (member instanceof PremiumMember) {
                            sb.append(member.toString()).append("\n\n");
                        }
                    }

                    textArea.setText(sb.toString().isEmpty() ? "No premium members found." : sb.toString());
                    displayFrame.setVisible(true);
                }
            });

        // Plan Selection ComboBox
        JLabel upgradePlanLabel = new JLabel("Upgrade Plan:");
        upgradePlanLabel.setBounds(20, 210, 100, 25);
        panel.add(upgradePlanLabel);

        String[] plans = {"Basic", "Standard", "Deluxe"};
        JComboBox<String> upgradePlanComboBox = new JComboBox<>(plans);
        upgradePlanComboBox.setBounds(130, 210, 120, 25);
        panel.add(upgradePlanComboBox);

        // Upgrade Plan Button
        JButton upgradePlanButton = new JButton("Upgrade Plan");
        upgradePlanButton.setBounds(270, 250, 150, 30);
        panel.add(upgradePlanButton);

        upgradePlanButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String enteredId = idField.getText();
                    String selectedPlan = (String) upgradePlanComboBox.getSelectedItem();
                    boolean memberFound = false; // Declare memberFound as a boolean

                    int enteredIdInt;
                    try {
                        enteredIdInt = Integer.parseInt(enteredId);  // Convert String to int
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a numeric ID.");
                        return;
                    }

                    for (GymMember member : members) {
                        if (member.getId() == enteredIdInt && member instanceof RegularMember) {
                            memberFound = true;
                            RegularMember regularMember = (RegularMember) member;
                            if (regularMember.isActive()) {
                                regularMember.upgradePlan(selectedPlan);
                                JOptionPane.showMessageDialog(frame, "Plan upgraded to " + selectedPlan );
                            } else {
                                JOptionPane.showMessageDialog(frame, "Cannot upgrade plan. Member is not active.");
                            }
                            break;
                        }
                    }

                    if (!memberFound) {
                        JOptionPane.showMessageDialog(null, "Regular member with ID " + enteredId + " not found.");
                    }
                }
            });

        // Price Label
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 250, 100, 25);
        panel.add(priceLabel);

        // Price TextField (non-editable)
        JTextField priceField = new JTextField();
        priceField.setBounds(130, 250, 120, 25);
        priceField.setEditable(false); 
        panel.add(priceField);

        upgradePlanComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedPlan = (String) upgradePlanComboBox.getSelectedItem();
                    int price = 0;

                    switch (selectedPlan) {
                        case "Basic":
                            price = 6500;
                            break;
                        case "Standard":
                            price = 12500;
                            break;
                        case "Deluxe":
                            price = 18500;
                            break;
                    }

                    priceField.setText(String.valueOf(price));
                }
            });

        upgradePlanComboBox.setSelectedItem("Basic");
        priceField.setText("6500");

        // Discount Amount Label
        JLabel discountAmountLabel = new JLabel("Discount Amount:");
        discountAmountLabel.setBounds(20, 290, 120, 25);
        panel.add(discountAmountLabel);

        // Discount Amount Field
        discountAmountField = new JTextField();
        discountAmountField.setBounds(150, 290, 120, 25);
        discountAmountField.setEditable(false);
        panel.add(discountAmountField);

        discountAmountField.setText("0");

        // Discount Amount Button
        JButton discountAmountButton = new JButton("Get Discount");
        discountAmountButton.setBounds(280, 290, 120, 25); 
        panel.add(discountAmountButton);

        discountAmountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String enteredId = idField.getText().trim();
                    int enteredIdInt;

                    try {
                        enteredIdInt = Integer.parseInt(enteredId); // Convert to int
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid ID. Please enter a numeric value.");
                        return;
                    }

                    boolean memberFound = false;

                    for (GymMember member : members) {
                        if (member.getId() == enteredIdInt && member instanceof PremiumMember) {
                            memberFound = true;
                            PremiumMember premiumMember = (PremiumMember) member;
                            double discount = premiumMember.calculateDiscount();
                            discountAmountField.setText(String.valueOf(discount));
                            JOptionPane.showMessageDialog(frame, "Discount amount: " + discount);
                            break;
                        }
                    }

                    if (!memberFound) {
                        JOptionPane.showMessageDialog(frame, "No premium member found with ID: " + enteredIdInt);
                    }
                }
            });

        JLabel amountLabel = new JLabel("Amount to Pay:");
        amountLabel.setBounds(20, 330, 120, 25); 
        panel.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 330, 120, 25);
        panel.add(amountField);

        amountField.setText("0");

        JButton payButton = new JButton("Pay Due");
        payButton.setBounds(280, 330, 120, 25); 
        panel.add(payButton);

        payButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int enteredId = Integer.parseInt(idField.getText());
                        double amountToPay = Double.parseDouble(amountField.getText());
                        boolean memberFound = false;

                        for (GymMember member : members) {
                            if (member.getId() == enteredId && member instanceof PremiumMember) {
                                PremiumMember premium = (PremiumMember) member;
                                premium.payDueAmount(amountToPay); // Call method
                                JOptionPane.showMessageDialog(null, "Payment successful!");
                                memberFound = true;
                                break;
                            }
                        }

                        if (!memberFound) {
                            JOptionPane.showMessageDialog(null, "Invalid Member ID or not a Premium Member.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter valid numeric values.");
                    }
                }
            });

        // Save to File Button
        JButton saveToFileButton = new JButton("Save to File");
        saveToFileButton.setBounds(430, 370, 150, 30);
        panel.add(saveToFileButton);

        saveToFileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveMembersToFile();
                }
            });

        // Read from File Button (placed just below Save to File button)
        JButton readFromFileButton = new JButton("Read from File");
        readFromFileButton.setBounds(430, 410, 150, 30); // Y-position increased to 410
        panel.add(readFromFileButton);

        readFromFileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    readMembersFromFile();
                }
            });

        // Clear Button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(430, 325, 150, 30);
        panel.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clearFields();
                }
            });

        frame.setVisible(true);
    }
    

    private void saveMembersToFile() {
        try (FileWriter writer = new FileWriter("MemberDetails.txt")) {
            // Write header
            writer.write(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s\n",
                    "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", "Plan", "Price", "Attendance", "Loyalty Points", "Active Status", "Full Payment", "Discount Amount", "Net Amount Paid"));

            // Write member details
            for (GymMember member : members) {
                if (member instanceof RegularMember) {
                    RegularMember regMember = (RegularMember) member;
                    writer.write(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10b %-15s %-15.2f %-15.2f\n",
                            regMember.getId(), regMember.getName(), regMember.getLocation(), regMember.getPhone(), regMember.getEmail(),
                            regMember.getmsYear() + "-" + regMember.getmsMonth() + "-" + regMember.getmsDay(),
                            regMember.getplan(), regMember.getprice(), regMember.getAttendance(), regMember.getLoyaltyPoints(),
                            regMember.isActive(), "N/A", 0.0, regMember.getprice()));
                } else if (member instanceof PremiumMember) {
                    PremiumMember premMember = (PremiumMember) member;
                    writer.write(String.format("%-5d %-15s %-15s %-15s %-25s %-20s %-10s %-10.2f %-10d %-15.2f %-10b %-15b %-15.2f %-15.2f\n",
                            premMember.getId(), premMember.getName(), premMember.getLocation(), premMember.getPhone(), premMember.getEmail(),
                            premMember.getmsYear() + "-" + premMember.getmsMonth() + "-" + premMember.getmsDay(),
                            "Premium", premMember.getPremiumCharge(), premMember.getAttendance(), premMember.getLoyaltyPoints(),
                            premMember.isActive(), premMember.getisFullPayment(), premMember.getDiscountAmount(),
                            premMember.getPremiumCharge() - premMember.getDiscountAmount()));
                }
            }
            System.out.println("Members saved to MemberDetails.txt");
        } catch (IOException e) {

        }
    }

    private void readMembersFromFile() {
        try {
            java.io.File file = new java.io.File("MemberDetails.txt");
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();

            // Display content in a JTextArea or JTable
            javax.swing.JFrame frame = new javax.swing.JFrame("Member Details");
            javax.swing.JTextArea textArea = new javax.swing.JTextArea(content.toString());
            textArea.setEditable(false);
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
            frame.add(scrollPane);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (IOException e) {

        }
    }

    private void clearFields() {
        // Clear the ID field
        idField.setText("");

        // Clear the Discount Amount field
        discountAmountField.setText("0");

        // Clear the Amount to Pay field
        amountField.setText("0");
    }

    private int getIntegerFromField(JTextField field, String fieldName) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input for " + fieldName);
            return -1;
        }
    }

    private long getPhoneNumberFromField(JTextField field, String fieldName) {
        try {
            return Long.parseLong(field.getText()); // use long for phone numbers
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input for " + fieldName);
            return -1;
        }
    }

    private void addRegularMember() {
        JFrame addRegularMemberFrame = new JFrame("Add Regular Member");
        addRegularMemberFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addRegularMemberFrame.setSize(500, 800);

        // Create the panel
        JPanel addRegularMemberPanel = new JPanel();
        addRegularMemberPanel.setLayout(null);
        addRegularMemberFrame.add(addRegularMemberPanel);

        // ID Field
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 80, 25);
        addRegularMemberPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 160, 25);
        addRegularMemberPanel.add(idField);

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(280, 20, 80, 25);
        addRegularMemberPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(360, 20, 160, 25);
        addRegularMemberPanel.add(nameField);

        // Location Field
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(20, 60, 80, 25);
        addRegularMemberPanel.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(100, 60, 160, 25);
        addRegularMemberPanel.add(locationField);

        // Phone Field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(280, 60, 80, 25);
        addRegularMemberPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(360, 60, 160, 25);
        addRegularMemberPanel.add(phoneField);

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 100, 80, 25);
        addRegularMemberPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 100, 160, 25);
        addRegularMemberPanel.add(emailField);

        // Gender Radio Buttons
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(280, 100, 80, 25);
        addRegularMemberPanel.add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(360, 100, 80, 25);
        addRegularMemberPanel.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(440, 100, 80, 25);
        addRegularMemberPanel.add(femaleRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Date of Birth Combo Boxes
        JLabel dobLabel = new JLabel("DOB:");
        dobLabel.setBounds(20, 140, 80, 25);
        addRegularMemberPanel.add(dobLabel);

        dobDayComboBox = new JComboBox<>();
        dobDayComboBox.setBounds(100, 140, 80, 25);
        for (int i = 1; i<=31; i++){
            dobDayComboBox.addItem(String.valueOf(i));
        }
        addRegularMemberPanel.add(dobDayComboBox);

        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        dobMonthComboBox = new JComboBox<>(months);
        dobMonthComboBox.setBounds(190, 140, 120, 25);
        addRegularMemberPanel.add(dobMonthComboBox);

        dobYearComboBox = new JComboBox<>();
        dobYearComboBox.setBounds(330, 140, 130, 25);
        for (int i = 2025; i >= 1900; i--) {
            dobYearComboBox.addItem(String.valueOf(i));
        }
        addRegularMemberPanel.add(dobYearComboBox);

        // Membership Start Date Combo Boxes
        JLabel msLabel = new JLabel("Membership Start Date:");
        msLabel.setBounds(20, 180, 180, 25);
        addRegularMemberPanel.add(msLabel);

        msDayComboBox = new JComboBox<>();
        msDayComboBox.setBounds(200, 180, 80, 25);
        for (int i = 1; i <= 31; i++) {
            msDayComboBox.addItem(String.valueOf(i));
        }
        addRegularMemberPanel.add(msDayComboBox);

        msMonthComboBox = new JComboBox<>(months);
        msMonthComboBox.setBounds(290, 180, 120, 25);
        addRegularMemberPanel.add(msMonthComboBox);

        msYearComboBox = new JComboBox<>();
        msYearComboBox.setBounds(420, 180, 130, 25);
        for (int i = 2025; i >= 1900; i--) {
            msYearComboBox.addItem(String.valueOf(i));
        }
        addRegularMemberPanel.add(msYearComboBox);

        // Referral Source Field
        JLabel referralSourceLabel = new JLabel("Referral Source:");
        referralSourceLabel.setBounds(20, 220, 120, 25);
        addRegularMemberPanel.add(referralSourceLabel);

        referralSourceField = new JTextField();
        referralSourceField.setBounds(140, 220, 160, 25);
        addRegularMemberPanel.add(referralSourceField);

        // Clear Button
        JButton clearRegularButton = new JButton("Clear");
        clearRegularButton.setBounds(310, 300, 100, 30); 
        addRegularMemberPanel.add(clearRegularButton);

        clearRegularButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    clearRegularFields();
                }
            });

        // Add Member Button
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.setBounds(180, 300, 120, 30);
        addRegularMemberPanel.add(addMemberButton);

        addMemberButton.addActionListener(e -> {
                    int id = getIntegerFromField(idField, "ID");
                    if (id == -1) return;
                    long phone = getPhoneNumberFromField(phoneField, "Phone");
                    if (phone == -1) return; 
                    String name = nameField.getText();
                    String location = locationField.getText();
                    String email = emailField.getText();
                    String referralSource = referralSourceField.getText();
                    String dobYear = (String) dobYearComboBox.getSelectedItem();
                    String dobMonth = (String) dobMonthComboBox.getSelectedItem();
                    String dobDay = (String) dobDayComboBox.getSelectedItem();
                    String msYear = (String) msYearComboBox.getSelectedItem();
                    String msMonth = (String) msMonthComboBox.getSelectedItem();
                    String msDay = (String) msDayComboBox.getSelectedItem();
                    String gender;
                    if (maleRadioButton.isSelected()) {
                        gender = "Male";
                    } else if (femaleRadioButton.isSelected()) {
                        gender = "Female";
                    } else {
                        gender = " "; // Default value if no gender is selected
                        JOptionPane.showMessageDialog(null, "Please select a gender");
                        return;
                    }

                    boolean idExists = false;
                    for (GymMember m : members) {
                        if (m.getId() == id) {
                            idExists = true;
                            break;
                        }
                    }

                    if (idExists) {
                        JOptionPane.showMessageDialog(null, "Member ID already exists");
                    } else {
                        RegularMember newMember = new RegularMember(id, name, location, String.valueOf(phone), email, gender, dobYear, dobMonth, dobDay, msYear,
                                msMonth, msDay, referralSource);
                        members.add(newMember);
                        JOptionPane.showMessageDialog(null, "Regular Member added successfully");
                        addRegularMemberFrame.dispose();

                    }
            });

        addRegularMemberFrame.setVisible(true);
    }

    private void clearRegularFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralSourceField.setText("");
        priceField.setText("");
        dobYearComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobDayComboBox.setSelectedIndex(0);
        msYearComboBox.setSelectedIndex(0);
        msMonthComboBox.setSelectedIndex(0);
        msDayComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        planComboBox.setSelectedIndex(0);
    }

    private void addPremiumMember() {
        JFrame addPremiumMemberFrame = new JFrame("Add Premium Member");
        addPremiumMemberFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addPremiumMemberFrame.setSize(500, 800);

        // Create the panel
        JPanel addPremiumMemberPanel = new JPanel();
        addPremiumMemberPanel.setLayout(null);
        addPremiumMemberFrame.add(addPremiumMemberPanel);

        // ID Field
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 80, 25);
        addPremiumMemberPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 160, 25);
        addPremiumMemberPanel.add(idField);

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(280, 20, 80, 25);
        addPremiumMemberPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(360, 20, 160, 25);
        addPremiumMemberPanel.add(nameField);

        // Location Field
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(20, 60, 80, 25);
        addPremiumMemberPanel.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(100, 60, 160, 25);
        addPremiumMemberPanel.add(locationField);

        // Phone Field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(280, 60, 80, 25);
        addPremiumMemberPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(360, 60, 160, 25);
        addPremiumMemberPanel.add(phoneField);

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 100, 80, 25);
        addPremiumMemberPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 100, 160, 25);
        addPremiumMemberPanel.add(emailField);

        // Gender Radio Buttons
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(280, 100, 80, 25);
        addPremiumMemberPanel.add(genderLabel);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(360, 100, 80, 25);
        addPremiumMemberPanel.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(440, 100, 80, 25);
        addPremiumMemberPanel.add(femaleRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Date of Birth Combo Boxes
        JLabel dobLabel = new JLabel("DOB:");
        dobLabel.setBounds(20, 140, 80, 25);
        addPremiumMemberPanel.add(dobLabel);

        dobDayComboBox = new JComboBox<>();
        dobDayComboBox.setBounds(100, 140, 80, 25);
        for (int i = 1; i <= 31; i++) {
            dobDayComboBox.addItem(String.valueOf(i));
        }
        addPremiumMemberPanel.add(dobDayComboBox);

        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        dobMonthComboBox = new JComboBox<>(months);
        dobMonthComboBox.setBounds(190, 140, 120, 25);
        addPremiumMemberPanel.add(dobMonthComboBox);

        dobYearComboBox = new JComboBox<>();
        dobYearComboBox.setBounds(320, 140, 130, 25);
        for (int i = 2025; i >= 1900; i--) {
            dobYearComboBox.addItem(String.valueOf(i));
        }
        addPremiumMemberPanel.add(dobYearComboBox);

        // Membership Start Date Combo Boxes
        JLabel msLabel = new JLabel("Membership Start Date:");
        msLabel.setBounds(20, 180, 180, 25);
        addPremiumMemberPanel.add(msLabel);

        msDayComboBox = new JComboBox<>();
        msDayComboBox.setBounds(200, 180, 80, 25);
        for (int i = 1; i <= 31; i++) {
            msDayComboBox.addItem(String.valueOf(i));
        }
        addPremiumMemberPanel.add(msDayComboBox);

        msMonthComboBox = new JComboBox<>(months);
        msMonthComboBox.setBounds(290, 180, 120, 25);
        addPremiumMemberPanel.add(msMonthComboBox);

        msYearComboBox = new JComboBox<>();
        msYearComboBox.setBounds(420, 180, 130, 25);
        for (int i = 2025; i >= 1900; i--) {
            msYearComboBox.addItem(String.valueOf(i));
        }
        addPremiumMemberPanel.add(msYearComboBox);

        // Trainer Name Field
        JLabel trainerLabel = new JLabel("Trainer Name:");
        trainerLabel.setBounds(20, 220, 120, 25);
        addPremiumMemberPanel.add(trainerLabel);

        trainerNameField = new JTextField();
        trainerNameField.setBounds(140, 220, 160, 25);
        addPremiumMemberPanel.add(trainerNameField);

        // Clear Button
        JButton clearPremiumButton = new JButton("Clear");
        clearPremiumButton.setBounds(310, 300, 100, 30);
        addPremiumMemberPanel.add(clearPremiumButton);

        clearPremiumButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    clearPremiumFields();
                }
            });

        // Add Member Button
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.setBounds(180, 300, 120, 30);
        addPremiumMemberPanel.add(addMemberButton);

        addMemberButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    int id = getIntegerFromField(idField, "ID");
                    if (id == -1) return;
                    String name = nameField.getText();
                    String location = locationField.getText();
                    String phone = phoneField.getText();
                    String email = emailField.getText();
                    String trainerName = trainerNameField.getText();
                    String dobYear = (String) dobYearComboBox.getSelectedItem();
                    String dobMonth = (String) dobMonthComboBox.getSelectedItem();
                    String dobDay = (String) dobDayComboBox.getSelectedItem();
                    String msYear = (String) msYearComboBox.getSelectedItem();
                    String msMonth = (String) msMonthComboBox.getSelectedItem();
                    String msDay = (String) msDayComboBox.getSelectedItem();
                    String gender;
                    if (maleRadioButton.isSelected()) {
                        gender = "Male";
                    } else if (femaleRadioButton.isSelected()) {
                        gender = "Female";
                    } else {
                        gender = " "; // Default value if no gender is selected
                        JOptionPane.showMessageDialog(null, "Please select a gender");
                        return;
                    }

                    boolean idExists = false;
                    for (GymMember m : members) {
                        if (m.getId() == id) {
                            idExists = true;
                            break;
                        }
                    }

                    if (idExists) {
                        JOptionPane.showMessageDialog(null, "Member ID already exists");
                    } else {
                        PremiumMember newPremiumMember = new PremiumMember(id, name, location, phone, email, gender, dobYear, dobMonth, dobDay,
                                msYear, msMonth, msDay, trainerName);
                        members.add(newPremiumMember);
                        JOptionPane.showMessageDialog(null, "Premium Member added successfully");
                        addPremiumMemberFrame.dispose();
                    }
                }
            });

        addPremiumMemberFrame.setVisible(true);
    }

    private void clearPremiumFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralSourceField.setText("");
        trainerNameField.setText("");
        priceField.setText("");
        dobYearComboBox.setSelectedIndex(0);
        dobMonthComboBox.setSelectedIndex(0);
        dobDayComboBox.setSelectedIndex(0);
        msYearComboBox.setSelectedIndex(0);
        msMonthComboBox.setSelectedIndex(0);
        msDayComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        planComboBox.setSelectedIndex(0);
    }

    private void activateMembership() {
        int id = getIntegerFromField(idField, "ID");
        if (id == -1)
            return;

        boolean idExists = false;
        for (GymMember m : members) {
            if (m.getId() == id) {
                idExists = true;
                if (m.isActive()) {
                    JOptionPane.showMessageDialog(null, "Membership is already active");
                } else {
                    m.activateMembership();
                    JOptionPane.showMessageDialog(null, "Membership activated successfully");
                }
                break;
            }
        }
        if (!idExists) {
            JOptionPane.showMessageDialog(null, "Member ID does not exist");
        }
    }

    private void deactivateMembership() {
        int id = getIntegerFromField(idField, "ID");
        if (id == -1)
            return;

        boolean idExists = false;
        for (GymMember m : members) {
            if (m.getId() == id) {
                idExists = true;
                if (!m.isActive()) {
                    JOptionPane.showMessageDialog(null, "Membership is already deactive");
                } else {
                    m.deactivateMembership();
                    JOptionPane.showMessageDialog(null, "Membership deactivated successfully");
                }
                break;
            }
        }
        if (!idExists) {
            JOptionPane.showMessageDialog(null, "Member ID does not exist");
        }
    }

    private void markAttendance() {
        int id = getIntegerFromField(idField, "ID");
        if (id == -1) return;  // Exit if ID is invalid

        boolean memberFound = false;
        for (GymMember m : members) {
            if (m.getId() == id) {
                memberFound = true;
                if (m.isActive()) {
                    m.markAttendance();  // Calls the overridden method (attendance++ and loyaltyPoints += 10)
                    JOptionPane.showMessageDialog(
                        null, 
                        "Attendance marked for ID: " + id + 
                        "\nLoyalty Points Added: 10" + 
                        "\nTotal Points: " + m.getLoyaltyPoints()
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "Member is not active");
                }
                break;
            }
        }
        if (!memberFound) {
            JOptionPane.showMessageDialog(null, "Member ID not found");
        }
    }

    private void revertRegularMember() {
        int id = getIntegerFromField(idField, "ID");
        if (id == -1) return;

        for (GymMember m : members) {
            if (m.getId() == id && m instanceof RegularMember) {
                ((RegularMember) m).revertRegularMember("Membership Expired"); // Revert without any message
                members.remove(m);
                JOptionPane.showMessageDialog(null, "Regular Member reverted");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Regular Member not found");
    }

    private void revertPremiumMember() {
        int id = getIntegerFromField(idField, "ID");
        if (id == -1) return;

        for (GymMember m : members) {
            if (m.getId() == id && m instanceof PremiumMember) {
                ((PremiumMember) m).revertPremiumMember(); // Revert without any message
                members.remove(m);
                JOptionPane.showMessageDialog(null, "Premium Member reverted");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Premium Member not found");
    }

    private void displayRegularMembers() {
        JTextArea textArea = new JTextArea();
        for (GymMember member : members) {
            if (member instanceof RegularMember) {
                textArea.append(member.toString() + "\n");
            }
        }
        JOptionPane.showMessageDialog(null, "Regular Members");
    }

    private void displayPremiumMembers() {
        JTextArea textArea = new JTextArea();
        for (GymMember member : members) {
            if (member instanceof PremiumMember) {
                textArea.append(member.toString() + "\n");
            }
        }
        JOptionPane.showMessageDialog(null,"Premium Members");
    }
}

