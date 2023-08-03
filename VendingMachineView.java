import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
/*
 * VendingMachineView contains the GUI for the vending machine
 * It displays various user-friendly features 
 */
public class VendingMachineView {
    private JFrame frame;
    private JLabel infoLabel;
    private JDialog customOrderDialog;
    private RoundedCornerButton[] increaseButtons, decreaseButtons, itemButtons, moneyButtons, buttons, replenishButtons, decreaseCartButtons, increaseCartButtons, cartButtons; 
    private JComboBox<String> billDropdown, menuDropdown;
    private VendingMachine machine;
    private JTextField[] itemPriceTextFields;
    private String[] numberOfBills = {"5", "10", "15", "20", "25"};
    private String[] menu = {"Patties", "Add-ons", "Condiments"};
    
    /*
     * Constructs a VendingMachineView object. 
     * Initializes various Jcomponents to display vending machine features.
     */
    public VendingMachineView() {
        this.itemButtons = new RoundedCornerButton[20];
        this.infoLabel = new JLabel();
        this.buttons = new RoundedCornerButton[18];
        this.moneyButtons = new RoundedCornerButton[11]; 
        this.decreaseCartButtons = new RoundedCornerButton[20]; 
        this.increaseCartButtons = new RoundedCornerButton[20]; 
        this.billDropdown = new JComboBox<>(numberOfBills);
        this.menuDropdown = new JComboBox<>(menu);
        this.decreaseButtons = new RoundedCornerButton[20];
        this.increaseButtons = new RoundedCornerButton[20];
        this.replenishButtons = new RoundedCornerButton[20];
        this.itemPriceTextFields = new JTextField[20];
    
        initializeFrame();
        initializeButtons();
        initializeView();
    }

    /**
     * Updates the machine stored in the view
     * @param machine  the updated machine
     */
    public void updateMachine(VendingMachine machine) {
        this.machine = machine;
    }

    /**
     * Initializes the JFrame and sets up the initial frame properties.
     */
    private void initializeFrame() {
        frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(750, 500); 
    
        infoLabel = new JLabel();
    
        frame.add(infoLabel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    /**
     * Initializes the increaseButtons, decreaseButtons, 
     * decreaseCartButtons, increaseCartButtons, buttons with a sent font, font size
     */
    private void initializeButtons() {
        Font buttonFont = new Font("Helvetica", Font.PLAIN, 14);

        for (int i = 0; i < 12; i++) {
            increaseButtons[i] = new RoundedCornerButton();
            decreaseButtons[i] = new RoundedCornerButton();
        }
        for (int i = 0; i < 15; i++){

            decreaseCartButtons[i] = new RoundedCornerButton();
            increaseCartButtons[i] = new RoundedCornerButton();
        }
    
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new RoundedCornerButton();

            String[] buttonTitles = { 
                "Make a Vending Machine", "Test a Machine", "Exit",
                "Maintenance", "Replenish Money", "Replenish Stock", "Exit",
                "Set Item Price", "Collect Payment", "Print Summary", "Exit Maintenance",
                "Exit", "Regular Vending Machine", "Special Vending Machine", "Cart" , "Purchase Order",
                "Cancel Order", "Save"
            };

            buttons[i].setText(buttonTitles[i]);
            buttons[i].setFont(buttonFont);
            buttons[i].setBackground(new Color(0x060505));
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFocusPainted(false);
        }
    }    

    /**
     * Initializes and displays the initial options for the vending machine main menu.
     */
    public void initializeView() {
        frame.getContentPane().removeAll();
    
        Color backgroundColor = new Color(0xF6B94B);
    
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(73, 55, 22), 10));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel emptyLabel = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weighty = 1.0;
        mainPanel.add(emptyLabel, gbc);
    
        JPanel vendingLabelPanel = new JPanel();
        JLabel vendingLabel = new JLabel("The Burger Machine", SwingConstants.CENTER);
        Font vendingLabelFont = loadCustomFont("Anybody-Medium.ttf", Font.BOLD, 48);
        vendingLabel.setFont(vendingLabelFont);
        vendingLabel.setForeground(new Color(73, 55, 22));
        vendingLabelPanel.add(vendingLabel);
        vendingLabelPanel.setBackground(new Color(249, 213, 147));
        mainPanel.add(vendingLabelPanel, gbc);
    
        gbc.gridy = 1;
        mainPanel.add(emptyLabel, gbc);
    
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonsPanel.setBackground(new Color(192, 192, 192));
    
        Font buttonFont = loadCustomFont("Anybody-Medium.ttf", Font.PLAIN, 15);
        for (int i = 0; i < 3; i++) {
            buttons[i].setFont(buttonFont);
            buttons[i].setBackground(new Color(73, 55, 22));
            buttonsPanel.add(buttons[i]);
        }
    
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        mainPanel.add(buttonsPanel, gbc);
    
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(180, 2));
        separator.setForeground(backgroundColor);
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(separator, gbc);
    
        gbc.gridy = 4;
        JLabel productionLabel = new JLabel("Ken and Joseph Production", SwingConstants.CENTER);
        Font productionLabelFont = loadCustomFont("Anybody-Medium.ttf", Font.BOLD, 25);
        productionLabel.setFont(productionLabelFont);
        productionLabel.setForeground(new Color(73, 55, 22));
        mainPanel.add(productionLabel, gbc);
    
        for (int i = 5; i < 10; i++) {
            gbc.gridy = i;
            mainPanel.add(emptyLabel, gbc);
        }
    
        ImageIcon logoIcon = new ImageIcon("burgermachine.gif");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setPreferredSize(new Dimension(300, 300));
    
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 10;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(logoLabel, gbc);
    
        vendingLabelPanel.setBackground(backgroundColor);
        buttonsPanel.setBackground(backgroundColor);
        productionLabel.setBackground(backgroundColor);
    
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(789, 369));
        setupFrame();
    }
    /**
     * Loads a custom font and if it cannot be found, it loads a default font
     *
     * @param fontFileName The filename
     * @param style        The font style (bold or plain)
     * @param size         The font size
     * @return The custom or default Font
     * 
     * */
    private Font loadCustomFont(String fontFileName, int style, int size) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFileName));
            GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphics.registerFont(customFont);
            return customFont.deriveFont(style, size);
        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading custom font: " + e.getMessage());
            return new Font("Helvetica Neue", style, size);
        }
    }
    
    /**
     * Displays regular or special machine options
     */
    public void displayVendingOptions() {
        this.frame.getContentPane().removeAll();

        JPanel outerPanel = new  JPanel(new BorderLayout());
        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        innerPanel.setBackground(new Color(249,213,147));
        outerPanel.setBackground(new Color(249,213,147));

        buttons[12].setPreferredSize(new Dimension(180, 100));
        buttons[13].setPreferredSize(new Dimension(180, 100));

        innerPanel.add(buttons[12]);
        innerPanel.add(buttons[13]);

        outerPanel.add(innerPanel, BorderLayout.CENTER);
        this.frame.add(outerPanel, BorderLayout.CENTER);
        
        frame.setPreferredSize(new Dimension(391, 149)); 
        setupFrame();
    }

    /**
     * Creates and returns a panel with denominations of money as buttons.
     *
     * @param denominations an array of doubles that contains valid PHP danominations
     * @return The JPanel with denominations of money as buttons
     */
    private JPanel createMoneyButtonsPanel(double[] denominations) {
        JPanel moneyButtonsPanel = new JPanel(new GridBagLayout());
        moneyButtonsPanel.setBackground(new Color(6, 5, 5)); 

        moneyButtons = new RoundedCornerButton[denominations.length];

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < denominations.length; i++) { //Initialize Money Buttons
            double denomination = denominations[i];
            moneyButtons[i] = new RoundedCornerButton(String.valueOf(denomination));
            moneyButtons[i].setPreferredSize(new Dimension(100, 40));
            moneyButtons[i].setFont(new Font("Helvetica", Font.PLAIN, 12));
            moneyButtons[i].setBackground(new Color(192, 192, 192)); 

            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            if (i == denominations.length - 1) {
                gbc.gridwidth = 2;
            } else {
                gbc.gridwidth = 1;
            }
            moneyButtonsPanel.add(moneyButtons[i], gbc);
        }

        return moneyButtonsPanel;
    }

    /**
     * Creates and returns a panel with maintenance and exit button.
     *
     * @return The JPanel containing the maintenance and exit button.
     */
    private JPanel createMaintenanceExitPanel() {
        JPanel maintenanceExitPanel = new JPanel(new BorderLayout());
        Dimension buttonSize = new Dimension(110, 25); 
        
        JPanel exitPanel = new JPanel();
        buttons[3].setPreferredSize(buttonSize);
        buttons[3].setFont(new Font("Helvetica", Font.PLAIN, 12));
        exitPanel.add(buttons[3]);
        exitPanel.setBackground(Color.BLACK);
        buttons[6].setPreferredSize(buttonSize);
        buttons[6].setFont(new Font("Helvetica", Font.PLAIN, 12));
        exitPanel.add(buttons[6]);
        
        maintenanceExitPanel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
        maintenanceExitPanel.add(exitPanel, BorderLayout.SOUTH);
        maintenanceExitPanel.setBackground(Color.BLACK);
        
        return maintenanceExitPanel;
    }

    /**
     * Displays the vending machine with its testing features
     *
     * @param list An array of Items that are inside the vending machine
     */
    public void displayTestVM(Item[] list) {
        frame.getContentPane().removeAll();
        Color backgroundColor = new Color(246, 185, 75);
        JPanel mainPanel = new JPanel(new BorderLayout());
        Item[] itemList = list;
    
        itemButtons = createButtons("Buy", itemList); 
        JScrollPane itemsPanel = null;
    
        if (machine instanceof SpecialMachine) { //Display testing features in special machine
            cartButtons = createButtons("Add", itemList);
            JPanel dropdownPanel = createDropDown(menuDropdown, menu);
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            topPanel.add(dropdownPanel); 
            JPanel cartButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            buttons[14].setPreferredSize(new Dimension(90, 20));
            cartButtonPanel.add(buttons[14]);
            topPanel.add(cartButtonPanel);
    
            mainPanel.add(topPanel, BorderLayout.NORTH);
    
            itemsPanel = createItemsPanel(itemButtons, cartButtons, itemList, this::createStockPriceCaloriesInfoPanel);
        } else
            itemsPanel = createItemsPanel(itemButtons, null, itemList, this::createStockPriceCaloriesInfoPanel);
    
        JPanel maintenanceExitPanel = createMaintenanceExitPanel();
    
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);
        leftPanel.add(setupInsertMoneyPanel(), BorderLayout.CENTER);
        leftPanel.add(maintenanceExitPanel, BorderLayout.SOUTH);
    
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, itemsPanel);
        splitPane.setDividerLocation(250);
    
        int frameWidth = (300 * 3 + 300) + 100; 
        frame.add(splitPane, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(frameWidth, 650)); 
        leftPanel.setBackground(backgroundColor);
        maintenanceExitPanel.setBackground(backgroundColor);
        itemsPanel.setBackground(backgroundColor);
    
        mainPanel.setBackground(new Color(220, 230, 240));
        mainPanel.add(splitPane, BorderLayout.CENTER);
    
        frame.add(mainPanel, BorderLayout.CENTER);
        setupFrame();
    }

    /**
     * Sets up and returns the panel for inserting money with buttons for different denominations.
     *
     * @return The JPanel for inserting money.
     */
    private JPanel setupInsertMoneyPanel() {
        double[] moneyDenominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1, 0.5, 0.25};
        JPanel panel = new JPanel(new BorderLayout());
        infoLabel = new JLabel("Money: P");
        infoLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        infoLabel.setForeground(Color.white);

        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border roundedBorder = BorderFactory.createCompoundBorder(
                border, BorderFactory.createSoftBevelBorder(SoftBevelBorder.RAISED, Color.white, Color.white)
        );
        infoLabel.setBorder(roundedBorder);

        infoLabel.setOpaque(true);
        infoLabel.setBackground(new Color(6, 5, 5));

        JPanel insertMoneyPanel = createMoneyButtonsPanel(moneyDenominations);
        panel.add(infoLabel, BorderLayout.NORTH);
        panel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);
        panel.add(insertMoneyPanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates and returns a JScrollPane containing the shopping cart items for the special machine.
     *
     * @return JScrollPane containing the custom order in the shopping cart.
    */
    public JScrollPane shoppingCart() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        Color color = new Color(255, 255, 255);
        inventoryPanel.setBackground(color);
        inventoryPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    
        if (machine instanceof SpecialMachine) {
            SpecialMachine specialMachine = (SpecialMachine) machine;
            int customOrderIndex = 0; //Items counter
            for (Item item : machine.getItemList()) {
                int counter = 0;
                for (Item check : specialMachine.getCustomOrder()) {
                    if (check.getName().equals(item.getName()))
                        counter++;
                }
    
                if (counter != 0) {
                    JPanel itemPanel = new JPanel(new GridBagLayout()); 
                    itemPanel.setPreferredSize(new Dimension(400, 100));
                    itemPanel.setBackground(color);
    
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.fill = GridBagConstraints.BOTH;
                    constraints.weightx = 1.0;
                    constraints.weighty = 1.0;
    
                    ImageIcon itemImageIcon = new ImageIcon(item.getName() + ".png");
                    Image itemImage = itemImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    JLabel imageLabel = new JLabel(new ImageIcon(itemImage));
                    constraints.gridx = 0;
                    constraints.gridy = 0;
                    constraints.gridheight = 4; 
                    constraints.insets = new Insets(5, 15, 5, 15); 
                    itemPanel.add(imageLabel, constraints);
    
                    JLabel nameLabel = new JLabel(item.getName());
                    nameLabel.setHorizontalAlignment(JLabel.LEFT);
                    nameLabel.setVerticalAlignment(JLabel.CENTER);
                    nameLabel.setPreferredSize(new Dimension(200, 30)); 
                    constraints.gridx = 1;
                    constraints.gridy = 0;
                    constraints.gridheight = 1;
                    constraints.anchor = GridBagConstraints.WEST;
                    constraints.insets = new Insets(5, 5, 5, 15); 
                    itemPanel.add(nameLabel, constraints);
    
                    RoundedCornerButton minusButton = new RoundedCornerButton("-");
                    RoundedCornerButton plusButton = new RoundedCornerButton("+");
                    minusButton.setPreferredSize(new Dimension(40, 20)); 
                    plusButton.setPreferredSize(new Dimension(40, 20));  
                    minusButton.setFont(new Font("Helvetica", Font.BOLD, 10)); 
                    plusButton.setFont(new Font("Helvetica", Font.BOLD, 10));  
                    minusButton.setFocusPainted(false);
                    plusButton.setFocusPainted(false);
                    increaseCartButtons[customOrderIndex] = plusButton;
                    decreaseCartButtons[customOrderIndex] = minusButton;
    
                    JLabel quantityLabel = new JLabel("" + counter + "");
                    quantityLabel.setHorizontalAlignment(JLabel.CENTER);
                    quantityLabel.setVerticalAlignment(JLabel.CENTER);
    
                    JPanel quantityButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); 
                    quantityButtonPanel.setBackground(color); 
                    quantityButtonPanel.setPreferredSize(new Dimension(100, 30)); 
                    quantityButtonPanel.add(decreaseCartButtons[customOrderIndex]);
                    quantityButtonPanel.add(quantityLabel);
                    quantityButtonPanel.add(increaseCartButtons[customOrderIndex]);
                    constraints.gridx = 1;
                    constraints.gridy = 1;
                    constraints.gridheight = 1;
                    constraints.anchor = GridBagConstraints.WEST;
                    itemPanel.add(quantityButtonPanel, constraints);
    
                    double totalPrice = counter * item.getPrice();
                    double totalCalories = counter*  item.getCalories();
                    JLabel totalPriceLabel = new JLabel("P" + totalPrice + " | Calories: " + totalCalories);
                    totalPriceLabel.setHorizontalAlignment(JLabel.LEFT);
                    constraints.gridx = 2;
                    constraints.gridy = 0;
                    constraints.gridheight = 2; 
                    constraints.anchor = GridBagConstraints.WEST;
                    itemPanel.add(totalPriceLabel, constraints);
    
                    JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
                    separator.setBackground(new Color(0, 0, 0));
                    constraints.gridx = 0;
                    constraints.gridy = 2;
                    constraints.gridwidth = 3; 
                    constraints.fill = GridBagConstraints.HORIZONTAL;
                    constraints.insets = new Insets(5, 15, 5, 15); 
                    itemPanel.add(separator, constraints);
    
                    customOrderIndex++; //Increment items counter
    
                    inventoryPanel.add(itemPanel);
                }
            }
        }
    
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 5, 5, 5));
        buttonsPanel.add(buttons[15]);
        buttonsPanel.add(buttons[16]);
    
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(buttonsPanel, BorderLayout.NORTH);
        containerPanel.add(inventoryPanel, BorderLayout.CENTER);

        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JScrollPane scrollPane = new JScrollPane(containerPanel); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
        return scrollPane;
    }

    /**
     * Displays a JDialog of the custom order in the shopping cart.
     */
    public void showCustomOrderInventoryDialog() {
        JScrollPane customOrderPanel = shoppingCart();
    
        customOrderDialog = new JDialog();
        customOrderDialog.setTitle("Shopping Cart");
        customOrderDialog.setSize(600, 400);
        customOrderDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        customOrderDialog.add(customOrderPanel);
    
        customOrderDialog.setLocationRelativeTo(null);
    
        customOrderDialog.setVisible(true);
    }
    /**
     * Displays maintenance features
     *
     * @param currentMoneyStored Current amount of money in the machine
     */
    public void displayMaintenance(double currentMoneyStored) {
        frame.getContentPane().removeAll();
    
        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel infoLabel = new JLabel("Current Money Stored: P" + currentMoneyStored, SwingConstants.CENTER);
        infoLabel.setFont(new Font("Monospaced Bold", Font.PLAIN, 14));
        infoPanel.add(infoLabel, BorderLayout.EAST);
    
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBackground(new Color(249,213,147));
        Dimension buttonSize = new Dimension(250, 150);
    
        for (int i = 4; i <= 10; i++) {
            if (i != 6) {
                buttons[i].setPreferredSize(buttonSize);
                mainPanel.add(buttons[i]);
            }
        }
    
        frame.add(infoPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
    
        setupFrame();
    }
       
    /**
     * Displays a gif and a label with a specified time 
     *
     * @param gifPath           String for the file name of the gif
     * @param label             String for the label
     * @param durationInSeconds The duration in seconds for how long the message will last
     */
    public void displayMessage(String gifPath, String label, int durationInSeconds) {
        JDialog message = createGIF(gifPath, label);
        messageDuration(message, durationInSeconds);
    }
    /**
     * Creates and returns a JDialog displaying a GIF with a label 
     *
     * @param gifPath  String for the file name of the gif
     * @param label    String for the label
     * @return The JDialog.
     */
    private JDialog createGIF(String gifPath, String label) {
        JDialog message = new JDialog(frame);
        message.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        message.setLayout(new BorderLayout());
        message.setSize(300, 300);
        message.setLocationRelativeTo(frame);

        ImageIcon gifIcon = new ImageIcon(gifPath); //gif
        JLabel gifLabel = new JLabel(gifIcon, SwingConstants.CENTER);
        message.add(gifLabel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel(label, SwingConstants.CENTER);
        message.add(messageLabel, BorderLayout.SOUTH);

        return message;
    }

    /**
     * Sets the duration in seconds for how long the message will last
     *
     * @param message            The label
     * @param durationInSeconds  The duration in seconds for how long the message will last
     */
    private void messageDuration(JDialog message, int durationInSeconds) {
        message.setVisible(true);

        Timer timer = new Timer(durationInSeconds * 1000, e -> {
            message.dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Displays replenish money maintenance feature, where the money inside the machine can be set
     */
    public void displayReplenishMoney() {
        frame.getContentPane().removeAll();
        double moneystored = machine.displayMoneyStored();
    
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(49, 37, 15)); 
    
        infoLabel = new JLabel("Money: P " + moneystored);
        infoLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        infoLabel.setForeground(Color.WHITE); 
    
        topPanel.add(infoLabel);
        JPanel replenishMoneyPanel = createMoneyButtonsPanelWithAdjustment();
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
    
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel exitPanel = createExitPanel();
        centerPanel.add(replenishMoneyPanel, BorderLayout.CENTER);
        centerPanel.add(exitPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    
        mainPanel.setBackground(new Color(249,213,147)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        frame.add(mainPanel);
        setupFrame();
    }

    /**
     * Creates and returns a JPanel containing money denominations array with a pair of 
     * increase and decrease buttons for each denomination
     *
     * @return The JPanel containing the money denomination buttons.
     */
    private JPanel createMoneyButtonsPanelWithAdjustment() {
        JPanel moneyButtonsPanel = new JPanel(new GridLayout(2, 6, 10, 10)); 
        moneyButtonsPanel.setBackground(new Color(240, 240, 240));
    
        double[] customOrderDenominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1, 0.5, 0.25, 0.1};

        for (int i = 0; i < customOrderDenominations.length; i++) {
            JPanel denominationPanel = new JPanel(new BorderLayout());
            denominationPanel.setBackground(new Color(255, 255, 255));
            denominationPanel.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
            denominationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
            denominationPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(150, 150, 150), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            denominationPanel.setOpaque(true);
            denominationPanel.setBackground(new Color(220, 220, 220));

            RoundedCornerButton decreaseButton = createMoneyAdjustmentButton("-", "-" + i); //decrease button
            denominationPanel.add(decreaseButton, BorderLayout.WEST);
    
            RoundedCornerButton increaseButton = createMoneyAdjustmentButton("+", "+" + i); //increase button
            denominationPanel.add(increaseButton, BorderLayout.EAST);

            decreaseButtons[i] = decreaseButton;
            increaseButtons[i] = increaseButton;

            JLabel denominationLabel = new JLabel(String.valueOf(customOrderDenominations[i]));
            denominationLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
            denominationLabel.setHorizontalAlignment(SwingConstants.CENTER);
            denominationLabel.setVerticalAlignment(SwingConstants.CENTER);
            denominationPanel.add(denominationLabel, BorderLayout.CENTER);

            moneyButtonsPanel.add(denominationPanel);
        }

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Replenish Money");
        titledBorder.setTitleFont(new Font("Helvetica", Font.BOLD, 16));
        titledBorder.setTitleColor(Color.DARK_GRAY);
        moneyButtonsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20), 
                titledBorder
        ));

        return moneyButtonsPanel;
    }

    /**
     * Adjusts the decrease and increase buttons
     */
    private RoundedCornerButton createMoneyAdjustmentButton(String text, String actionCommand) {
        RoundedCornerButton button = new RoundedCornerButton(text);
        button.setBackground(new Color(54, 92, 160));
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(40, 30));
        button.setMargin(new Insets(5, 12, 5, 12));
        button.setActionCommand(actionCommand);
        return button;
    }

    /**
     * Creates and returns a JPanel that has a dropdown menu for selecting different options.
     *
     * @param dropdown The JComboBox for the dropdown menu.
     * @param options  The array of strings that displays different options.
     * @return The JPanel that has the dropdown menu.
     */
    private JPanel createDropDown(JComboBox<String> dropdown, String[] options) {
        JPanel billsButtonsPanel = new JPanel(new GridBagLayout());
        billsButtonsPanel.setBackground(new Color(240, 240, 240)); 
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
    
        dropdown.setPreferredSize(new Dimension(100, 20));
        dropdown.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        dropdown.setBackground(new Color(240, 240, 240));
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        billsButtonsPanel.add(dropdown, gbc);
    
        return billsButtonsPanel;
    }
 
    /**
     * Creates and returns a JScrollPane that displays the items in the vending machine with different JComponents to interact with them.
     * 
     *
     * @param buttons          An array of JComponents that can buy, replenish stock, or set the price of an item
     * @param cButtons         An optional array of JComponents that can make an item be added to the cart
     * @param itemList         An array of Items that will be displayed on the panel
     * @param infoPanelGenerator An optional function that displays info about the item
     * @return The JScrollPane displays the items in the vending machine.
     */
    private JScrollPane createItemsPanel(JComponent[] buttons, JComponent[] cButtons, Item[] itemList, Function<Item, JPanel> infoPanelGenerator) {
        JPanel itemsPanel = new JPanel(new GridBagLayout());
        itemsPanel.setBackground(new Color(246, 185, 75));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
    
        int itemsPerRow = 3;
    
        for (int i = 0; i < itemList.length; i++) {
            Item item = itemList[i];
    
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setPreferredSize(new Dimension(300, 380));
            itemPanel.setBackground(new Color(246, 185, 75));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    
            if (buttons != null) {
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.setOpaque(false);
                buttonPanel.add(buttons[i]);
    
                if (cButtons != null)
                    buttonPanel.add(cButtons[i]);
    
                itemPanel.add(buttonPanel, BorderLayout.NORTH);
            } else {
                JLabel itemNameLabel = new JLabel(item.getName());
                itemNameLabel.setHorizontalAlignment(JLabel.CENTER);
                itemNameLabel.setVerticalAlignment(JLabel.CENTER);
                itemPanel.add(itemNameLabel, BorderLayout.NORTH);
            }
    
            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.setBackground(new Color(246, 185, 75));
            String imagePath = item.getName() + ".png"; //image of the item
            ImageIcon itemIcon = new ImageIcon(imagePath);
            JLabel itemLabel = new JLabel(itemIcon);
            itemLabel.setHorizontalAlignment(JLabel.CENTER);
            itemLabel.setVerticalAlignment(JLabel.CENTER);
            imagePanel.add(itemLabel, BorderLayout.CENTER);
            itemPanel.add(imagePanel, BorderLayout.CENTER);
    
            if (infoPanelGenerator != null) {
                JPanel infoPanel = infoPanelGenerator.apply(item);
                itemPanel.add(infoPanel, BorderLayout.SOUTH);
            }
    
            int row = i / itemsPerRow;
            int column = i % itemsPerRow;
    
            gbc.gridx = column;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            itemsPanel.add(itemPanel, gbc);
        }
    
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        return scrollPane;
    }

    /**
     * Creates and returns a JPanel that has the name, stock, price, and calories information for an item with icons.
     *
     * @param item The Item that will be displayed with information
     * @return The JPanel that has the name, stock, price, and calories information for an item with icons.
     */
    private JPanel createStockPriceCaloriesInfoPanel(Item item) {
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 5, 2));
        infoPanel.setBackground(new Color(246, 185, 75));
    
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel stockIcon = new JLabel(new ImageIcon("stock_icon.png"));
        JLabel stockLabel = new JLabel("Stock: " + item.getStock().size());
        JLabel priceIcon = new JLabel(new ImageIcon("price_icon.png"));
        JLabel priceLabel = new JLabel("Price: P" + item.getPrice());
        JLabel caloriesIcon = new JLabel(new ImageIcon("calories_icon.png"));
        JLabel caloriesLabel = new JLabel("Calories: " + item.getCalories());
    
        Font detailsFont = new Font("Helvetica", Font.PLAIN, 12);
        stockLabel.setFont(detailsFont);
        priceLabel.setFont(detailsFont);
        caloriesLabel.setFont(detailsFont);
    
        infoPanel.add(nameLabel);
        infoPanel.add(new JLabel());
        infoPanel.add(stockIcon);
        infoPanel.add(stockLabel);
        infoPanel.add(priceIcon);
        infoPanel.add(priceLabel);
        infoPanel.add(caloriesIcon);
        infoPanel.add(caloriesLabel);
    
        return infoPanel;
    }
    
    /**
     * Creates and returns a JPanel that has the initial and final inventory for an item with icons.
     *
     * @param item The Item that will be displayed with information
     * @return The JPanel that has the initial and final inventory for an item with icons.
     */
    private JPanel createInitialFinalInventoryInfoPanel(Item item) {
        int temp = 0;
        Item[] itemList = machine.getItemList();
        for (int i = 0; i < itemList.length; i++){
            if(item == itemList[i]){
                temp = i;
            }
        }

        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 5, 2));
        infoPanel.setBackground(new Color(246, 185, 75));
    
        JLabel initialInventoryIcon = new JLabel(new ImageIcon("initial_inventory_icon.png"));
        JLabel initialInventoryLabel = new JLabel("Initial Inventory: " + machine.getInitialInventory().get(temp));
        JLabel finalInventoryIcon = new JLabel(new ImageIcon("final_inventory_icon.png"));
        JLabel finalInventoryLabel = new JLabel("Final Inventory: " + machine.getFinalInventory().get(temp));
    
        Font detailsFont = new Font("Helvetica", Font.PLAIN, 12);
        initialInventoryLabel.setFont(detailsFont);
        finalInventoryLabel.setFont(detailsFont);
    
        infoPanel.add(initialInventoryIcon);
        infoPanel.add(initialInventoryLabel);
        infoPanel.add(finalInventoryIcon);
        infoPanel.add(finalInventoryLabel);
    
        return infoPanel;
    } 
    
    /**
     * Displays replenish stock maintenance feature, where the stock of an item inside the machine can be set
     */
    public void displayReplenishStock() {
        frame.getContentPane().removeAll();
        Color bgcolor = new Color(249,213,147);
    
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(49, 37, 15));
    
        JLabel titleLabel = new JLabel("Replenish Stock");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
    
        topPanel.add(titleLabel);
        Item[] itemList = machine.getItemList();

        replenishButtons = createButtons("Replenish", itemList);
        JScrollPane itemsScrollPane = createItemsPanel(replenishButtons, null, itemList, this::createStockPriceCaloriesInfoPanel);
    
        JPanel exitPanel = createExitPanel();
        exitPanel.setBackground(bgcolor);
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(itemsScrollPane, BorderLayout.CENTER);
        mainPanel.add(exitPanel, BorderLayout.SOUTH);
    
        mainPanel.setBackground(bgcolor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        frame.add(mainPanel);
        setupFrame();
    }

    /**
     * Displays set item price maintenance feature, where the price of an item can be set
     */
    public void displaySetItemPrice() {
        frame.getContentPane().removeAll();
    
        Item[] itemList = machine.getItemList();
        Color bgcolor = new Color(249,213,147);
    
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(49, 37, 15)); 
    
        JLabel titleLabel = new JLabel("Set Item Price");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); 
        topPanel.add(titleLabel);
    
        JTextField[] priceTextFields = createEditableItemPriceFields(itemList); //textfields
        JScrollPane itemsScrollPane = createItemsPanel(priceTextFields, null, itemList, this::createStockPriceCaloriesInfoPanel);
    
        JPanel exitPanel = new JPanel(new FlowLayout());
        exitPanel.setBackground(bgcolor);
        
        buttons[11].setFont(new Font("Helvetica", Font.PLAIN, 14));
        buttons[11].setPreferredSize(new Dimension(100, 30));
        exitPanel.add(buttons[11]);
        
        buttons[17].setFont(new Font("Helvetica", Font.PLAIN, 14));
        buttons[17].setPreferredSize(new Dimension(100, 30));
        exitPanel.add(buttons[17]);
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(itemsScrollPane, BorderLayout.CENTER);
        mainPanel.add(exitPanel, BorderLayout.SOUTH);
    
        mainPanel.setBackground(bgcolor); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
        frame.add(mainPanel);
        setupFrame();
    
        frame.pack();
    }
    
    /**
     * Creates and returns an array of text fields to set item prices.
     *
     * @param itemList The array of Items to have text fields for
     * @return An array of JTextFields for setting item prices.
     */
    private JTextField[] createEditableItemPriceFields(Item[] itemList) {
        itemPriceTextFields = new JTextField[itemList.length];
    
        for (int i = 0; i < itemList.length; i++) {
            JTextField priceField = new JTextField();
            priceField.setFont(new Font("Helvetica", Font.PLAIN, 12));
            priceField.setPreferredSize(new Dimension(100, 20));
            itemPriceTextFields[i] = priceField;
        }
    
        return itemPriceTextFields;
    }

    /**
     * Creates and returns an array of RoundedCornerButtons with a label.
     *
     * @param labelPrefix The label of the button
     * @param itemList    The array of Items to create buttons for
     * @return An array of RoundedCornerButton objects with a label.
     */
    private RoundedCornerButton[] createButtons(String labelPrefix, Item[] itemList) {
        Font buttonFont = new Font("Helvetica", Font.PLAIN, 14);
        RoundedCornerButton[] buttons = new RoundedCornerButton[itemList.length];
    
        for (int i = 0; i < itemList.length; i++) {
            RoundedCornerButton button = new RoundedCornerButton(labelPrefix);
            button.setPreferredSize(new Dimension(100, 40));
            button.setFont(buttonFont);
            button.setBackground(new Color(246, 185, 75));
            button.setForeground(Color.BLACK);
            buttons[i] = button;
        }
    
        return buttons;
    }

    /**
     * Creates and returns a JPanel that has a dropdown and exit button
     *
     * @return The JPanel containing that has a dropdown and exit button
     */
    private JPanel createExitPanel() {
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel billsButtonsPanel = createDropDown(billDropdown, numberOfBills);
        exitPanel.add(billsButtonsPanel, BorderLayout.CENTER);

        exitPanel.setBackground(new Color(255, 228, 181)); 
        buttons[11].setPreferredSize(new Dimension(110, 25));
        exitPanel.add(buttons[11]); 
    
        return exitPanel;
    }

    /**
     * Displays a summary of the transactions made, including item names, quantities, and total prices.
     */
    public void printSummary() {
        frame.getContentPane().removeAll();
        Item[] itemList = machine.getItemList();
    
        int preferredWidth = 3 * 328;
    
        JScrollPane itemsPanelForSummary = createItemsPanel(null, null, itemList, this::createInitialFinalInventoryInfoPanel);
        itemsPanelForSummary.setPreferredSize(new Dimension(preferredWidth, itemsPanelForSummary.getPreferredSize().height));
    
        JPanel receiptPanel = new JPanel(new BorderLayout());
        receiptPanel.setBackground(Color.WHITE);
    
        JPanel receiptInfoPanel = new JPanel(new GridBagLayout());
        receiptInfoPanel.setBackground(Color.WHITE);
        GridBagConstraints gbcReceipt = new GridBagConstraints();
        gbcReceipt.anchor = GridBagConstraints.WEST;
        gbcReceipt.insets = new Insets(5, 10, 5, 10);
    
        JLabel companyNameLabel = new JLabel("K&J Enterprises");
        companyNameLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        receiptInfoPanel.add(companyNameLabel, gbcReceipt);
    
        JLabel websiteLabel = new JLabel("https//www.facebook.com/K&JEnterprises");
        websiteLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
        gbcReceipt.gridy = 1;
        receiptInfoPanel.add(websiteLabel, gbcReceipt);
    
        JLabel contactLabel = new JLabel("0917 - 727 - 8181");
        contactLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
        gbcReceipt.gridy = 2;
        receiptInfoPanel.add(contactLabel, gbcReceipt);
    
        receiptPanel.add(receiptInfoPanel, BorderLayout.NORTH);
    
        JPanel transactionListPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTransaction = new GridBagConstraints();
        gbcTransaction.anchor = GridBagConstraints.WEST;
        gbcTransaction.insets = new Insets(5, 5, 5, 5);
    
        HashMap<String, Integer> itemQuantities = new HashMap<>(); //Keep track of quantity
        HashMap<String, Double> itemTotals = new HashMap<>(); //Keep track of total sales per item
    
        Double grandTotal = 0.0;
        Font labelFont = new Font("Helvetica", Font.PLAIN, 12);
        gbcTransaction.gridx = 0;
        gbcTransaction.gridy = 0;
    
        JLabel itemHeaderLabel = new JLabel("Item Name");
        itemHeaderLabel.setFont(labelFont);
        transactionListPanel.add(itemHeaderLabel, gbcTransaction);
    
        JLabel priceHeaderLabel = new JLabel("Price");
        priceHeaderLabel.setFont(labelFont);
        gbcTransaction.gridx = 1;
        transactionListPanel.add(priceHeaderLabel, gbcTransaction);
    
        gbcTransaction.gridy++;
    
        for (Item item : machine.getTransactions()) {
            String itemName = item.getName();
            int quantity = itemQuantities.getOrDefault(itemName, 0) + 1; 
            itemQuantities.put(itemName, quantity);
    
            double itemPrice = item.getPrice();
            double total = itemTotals.getOrDefault(itemName, 0.0) + itemPrice;
            itemTotals.put(itemName, total);
        }
    
        for (Map.Entry<String, Integer> entry : itemQuantities.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
    
            double itemPrice = itemTotals.getOrDefault(itemName, 0.0);
    
            JLabel itemNameLabel = new JLabel(itemName + " (" + quantity + ")");
            itemNameLabel.setFont(labelFont);
            gbcTransaction.gridx = 0;
            gbcTransaction.insets = new Insets(5, 10, 5, 50);
            transactionListPanel.add(itemNameLabel, gbcTransaction);
    
            JLabel itemTotalLabel = new JLabel("P" + String.format("%.2f", itemPrice));
            itemTotalLabel.setFont(labelFont);
            gbcTransaction.gridx = 1;
            gbcTransaction.insets = new Insets(5, 10, 5, 10);
            transactionListPanel.add(itemTotalLabel, gbcTransaction);
    
            gbcTransaction.gridy++;
    
            grandTotal += itemPrice;
        }
    
        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        gbcTransaction.gridx = 0;
        gbcTransaction.insets = new Insets(5, 10, 5, 50);
        transactionListPanel.add(totalLabel, gbcTransaction);
    
        JLabel grandTotalLabel = new JLabel("P" + String.format("%.2f", grandTotal));
        grandTotalLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        gbcTransaction.gridx = 1;
        gbcTransaction.insets = new Insets(5, 10, 5, 10);
        transactionListPanel.add(grandTotalLabel, gbcTransaction);
    
        receiptPanel.add(transactionListPanel, BorderLayout.CENTER);

        buttons[11].setPreferredSize(new Dimension(40, 40));
        receiptPanel.add(buttons[11], BorderLayout.SOUTH);
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(itemsPanelForSummary, BorderLayout.WEST);
        mainPanel.add(receiptPanel, BorderLayout.CENTER);
    
        frame.add(mainPanel, BorderLayout.CENTER);
    
        setupFrame();
    }
    
    /**
     * Sets up the frame by packing and displaying it, setting its location, and making it non-resizable.
     */
    private void setupFrame() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.revalidate();
        frame.repaint();
    }
    
    /**
     * Sets an ActionListener for an item button at the index.
     *
     * @param itemIndex      The index of the item button.
     * @param actionListener ActionListener for the item button.
     */
    public void setItemButtonListener(int itemIndex, ActionListener actionListener) {
        itemButtons[itemIndex].addActionListener(actionListener);
    }
    
    /**
     * Sets an ActionListener for a button at the index.
     *
     * @param buttonIndex      The index of the item button.
     * @param actionListener ActionListener for the button.
     */
    public void setButtonListener(int buttonIndex, ActionListener actionListener) {
        buttons[buttonIndex].addActionListener(actionListener);
    }

    /**
     * Sets an ActionListener for a decrease button at the index.
     *
     * @param index      The index of the item button.
     * @param listener ActionListener for the decrease button button.
     */
    public void setDecreaseButtonListener(int index, ActionListener listener) {
        decreaseButtons[index].addActionListener(listener);
    }
    
    /**
     * Sets an ActionListener for a increase button at the index.
     *
     * @param index      The index of the item button.
     * @param listener ActionListener for the increase button button.
     */
    public void setIncreaseButtonListener(int index, ActionListener listener) {
        increaseButtons[index].addActionListener(listener);
    }

    /**
     * Sets an ActionListener for a decrease cart button at the index.
     *
     * @param index      The index of the item button.
     * @param listener ActionListener for the decrease cart button.
     */
    public void decreaseCartButtonsListeners(int index, ActionListener listener) {
        decreaseCartButtons[index].addActionListener(listener);
    }

    /**
     * Sets an ActionListener for a increase cart button at the index.
     *
     * @param index      The index of the item button.
     * @param listener ActionListener for the increase cart button.
     */
    public void increaseCartButtonsListeners(int index, ActionListener listener) {
        increaseCartButtons[index].addActionListener(listener);
    }

    /**
     * Sets an ActionListener for a cart button at the index.
     *
     * @param index      The index of the item button.
     * @param listener ActionListener for the cart button.
     */
    public void setCartButtons(int index, ActionListener listener) {
        cartButtons[index].addActionListener(listener);
    }

    /**
     * Gets an array of JTextFields to set item prices
     *
     * @return An array of JTextFields to set item prices
     */
    public JTextField[] getItemPriceTextFields() {
        return itemPriceTextFields;
    }
    
    /**
     * Sets an ActionListener for the menu dropdown.
     *
     * @param e ActionListener to be set for the menu dropdown.
     */
    public void setMenuDropDown(ActionListener e) {
        menuDropdown.addActionListener(e);
    }

    /**
     * Sets an ActionListener for an item button at the index.
     *
     * @param itemIndex      The index of the item button.
     * @param actionListener ActionListener for the item button.
     */
    public void setMoneyButtonListener(int itemIndex, ActionListener actionListener) {
        moneyButtons[itemIndex].addActionListener(actionListener);
    }

    /**
     * Gets the dropdown for replenishing money.
     *
     * @return The dropdown for replenishing money.
     */
    public JComboBox<String> getBillDropdown() {
        return this. billDropdown;
    }

     /**
     * Gets the dropdown for switching panels.
     *
     * @return The dropdown for switching panels.
     */
    public JComboBox<String> getMenuDropdown() {
        return this.menuDropdown;
    }

    /**
     * Updates the information label displaying the current amount of money.
     *
     * @param amount The new amount to be displayed in the information label.
     */
    public void updateInfoLabel(double amount) {
        infoLabel.setText("Money: P " + amount);
    }

    /**
     * Sets an ActionListener for a replenish button at the index.
     *
     * @param index      The index of the item button.
     * @param listener ActionListener for the replenish button.
     */
    public void setReplenishButtonListener(int index, ActionListener listener) {
        replenishButtons[index].addActionListener(listener);
    }    
    
    /**
     * Gets the array of RoundedCornerButtons for replenishing stocks
     */
    public RoundedCornerButton[] getReplenishButtons() {
        return replenishButtons;
    }

    /**
     * Returns the custom order dialog
     *
     * @return The custom order dialog
     */
    public JDialog getCustomOrderDialog() {
        return customOrderDialog;
    }
}