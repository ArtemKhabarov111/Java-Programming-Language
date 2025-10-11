import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    private static JFrame createCardWindow = null;
    private static JFrame showCardsWindow = null;
    private static JFrame putCashCardWindow = null;
    private static JFrame useTurnstileWindow = null;
    private static JFrame statisticsWindow = null;

    private static final ArrayList<TravelCard> cards = new ArrayList<>();
    private static int nextCardId = 1;
    static int buttonWidth = 350;
    static int buttonHeight = 50;

    // Створюємо об'єкт статистики
    private static final TurnstileStatistics statistics = new TurnstileStatistics();

    // todo Вікно створення картки
    public static void makeCardWindow() {
        if (createCardWindow != null && createCardWindow.isDisplayable()) {
            createCardWindow.toFront();
            return;
        }

        createCardWindow = new JFrame("Меню створення картки");
        createCardWindow.setSize(420, 430);
        createCardWindow.setLocation(520, 50);
        createCardWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createCardWindow.setLayout(null);

        Container contentPane = createCardWindow.getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);

        JLabel label1 = new JLabel("Оберіть бажану картку", JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 28));
        label1.setBounds(25, 25, 350, 50);
        createCardWindow.add(label1);

        JLabel labelType = new JLabel("Тип картки:");
        labelType.setFont(new Font("Arial", Font.PLAIN, 18));
        labelType.setBounds(25, 80, 200, 30);
        createCardWindow.add(labelType);

        JComboBox<String> comboBoxType = new JComboBox<>(new String[]{
                "Учнівська", "Студентська", "Звичайна"
        });
        comboBoxType.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBoxType.setBounds(25, 110, 350, 35);
        createCardWindow.add(comboBoxType);

        JLabel labelSubType = new JLabel("Підтип картки:");
        labelSubType.setFont(new Font("Arial", Font.PLAIN, 18));
        labelSubType.setBounds(25, 160, 200, 30);
        createCardWindow.add(labelSubType);

        JRadioButton rbMonth = new JRadioButton("На місяць");
        rbMonth.setFont(new Font("Arial", Font.PLAIN, 18));
        rbMonth.setBounds(25, 190, 150, 30);
        rbMonth.setBackground(Color.LIGHT_GRAY);

        JRadioButton rbTenDays = new JRadioButton("На 10 днів");
        rbTenDays.setFont(new Font("Arial", Font.PLAIN, 18));
        rbTenDays.setBounds(225, 190, 150, 30);
        rbTenDays.setBackground(Color.LIGHT_GRAY);

        JRadioButton rbFiveTrips = new JRadioButton("5 поїздок");
        rbFiveTrips.setFont(new Font("Arial", Font.PLAIN, 18));
        rbFiveTrips.setBounds(25, 220, 150, 30);
        rbFiveTrips.setBackground(Color.LIGHT_GRAY);

        JRadioButton rbTenTrips = new JRadioButton("10 поїздок");
        rbTenTrips.setFont(new Font("Arial", Font.PLAIN, 18));
        rbTenTrips.setBounds(225, 220, 150, 30);
        rbTenTrips.setBackground(Color.LIGHT_GRAY);

        JRadioButton rbAccum = new JRadioButton("Накопичувальна");
        rbAccum.setFont(new Font("Arial", Font.PLAIN, 18));
        rbAccum.setBounds(25, 250, 200, 30);
        rbAccum.setBackground(Color.LIGHT_GRAY);

        ButtonGroup group = new ButtonGroup();
        group.add(rbMonth);
        group.add(rbTenDays);
        group.add(rbFiveTrips);
        group.add(rbTenTrips);
        group.add(rbAccum);

        createCardWindow.add(rbMonth);
        createCardWindow.add(rbTenDays);
        createCardWindow.add(rbFiveTrips);
        createCardWindow.add(rbTenTrips);
        createCardWindow.add(rbAccum);

        rbAccum.setEnabled(false);
        rbMonth.setSelected(true);
        comboBoxType.addActionListener(e -> {
            rbMonth.setSelected(true);
            String selectedType = (String) comboBoxType.getSelectedItem();

            if (!"Звичайна".equals(selectedType)) {
                rbAccum.setEnabled(false);
                if (rbAccum.isSelected()) {
                    group.clearSelection();
                }
            } else {
                rbAccum.setEnabled(true);
            }
        });

        JButton btnCreate = new JButton("Випустити картку");
        btnCreate.setFont(new Font("Arial", Font.BOLD, 20));
        btnCreate.setBackground(Color.GRAY);
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setBounds(25, 305, buttonWidth, buttonHeight);
        btnCreate.addActionListener(e -> {
            String typeStr = (String) comboBoxType.getSelectedItem();
            assert typeStr != null;
            CardType type = switch (typeStr) {
                case "Учнівська" -> CardType.STUDENT;
                case "Студентська" -> CardType.UNIVERSITY;
                default -> CardType.REGULAR;
            };

            CardSubType subType = null;
            if (rbMonth.isSelected()) subType = CardSubType.MONTHLY;
            else if (rbTenDays.isSelected()) subType = CardSubType.TEN_DAYS;
            else if (rbFiveTrips.isSelected()) subType = CardSubType.FIVE_TRIPS;
            else if (rbTenTrips.isSelected()) subType = CardSubType.TEN_TRIPS;
            else if (rbAccum.isSelected()) subType = CardSubType.ACCUMULATIVE;

            TravelCard card = new TravelCard(nextCardId++, type, subType);
            cards.add(card);
            JOptionPane.showMessageDialog(createCardWindow,
                    "Картку створено!\n\n" + card,
                    "Успішно", JOptionPane.INFORMATION_MESSAGE);
        });

        createCardWindow.add(btnCreate);

        createCardWindow.setVisible(true);
    }


    // todo Вікно перегляду всіх карток
    public static void showAllCardsWindow() {
        if (showCardsWindow != null && showCardsWindow.isDisplayable()) {
            showCardsWindow.toFront();
            return;
        }

        showCardsWindow = new JFrame("Меню перегляду карток");
        showCardsWindow.setSize(420, 860);
        showCardsWindow.setLocation(990, 50);
        showCardsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showCardsWindow.setLayout(null);

        Container contentPane = showCardsWindow.getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);

        JLabel label1 = new JLabel("Перегляд усіх карток", JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 28));
        label1.setBounds(25, 25, 350, 50);
        showCardsWindow.add(label1);

        JLabel labelType = new JLabel("Тип картки:");
        labelType.setFont(new Font("Arial", Font.PLAIN, 18));
        labelType.setBounds(25, 80, 200, 30);
        showCardsWindow.add(labelType);

        JComboBox<String> comboBoxType = new JComboBox<>(new String[]{
                "Всі картки", "Учнівська", "Студентська", "Звичайна"
        });
        comboBoxType.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBoxType.setBounds(25, 110, 350, 35);
        showCardsWindow.add(comboBoxType);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(25, 150, 350, 650);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        showCardsWindow.add(scrollPane);

        comboBoxType.addActionListener(e -> {
            String selected = (String) comboBoxType.getSelectedItem();
            StringBuilder sb = new StringBuilder();

            for (TravelCard card : cards) {
                if ("Всі картки".equals(selected) ||
                        card.getCardType().getDisplayName().equals(selected)) {
                    sb.append(card).append("---------------------------------------------------\n");
                }
            }

            textArea.setText(!sb.isEmpty() ? sb.toString() : "Немає карток для відображення.");
        });

        comboBoxType.setSelectedIndex(0);
        comboBoxType.getActionListeners()[0].actionPerformed(null);

        showCardsWindow.setVisible(true);
    }


    // todo Вікно поповнення карток
    public static void cashOnCardWindow() {
        if (putCashCardWindow != null && putCashCardWindow.isDisplayable()) {
            putCashCardWindow.toFront();
            return;
        }

        putCashCardWindow = new JFrame("Поповнення накопичувальної картки");
        putCashCardWindow.setSize(420, 430);
        putCashCardWindow.setLocation(50, 480);
        putCashCardWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        putCashCardWindow.setLayout(null);

        Container contentPane = putCashCardWindow.getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);

        JLabel label1 = new JLabel("Поповнення картки", JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 26));
        label1.setBounds(25, 25, 350, 40);
        putCashCardWindow.add(label1);

        JLabel labelId = new JLabel("Введіть ID картки:");
        labelId.setFont(new Font("Arial", Font.PLAIN, 18));
        labelId.setBounds(25, 80, 200, 30);
        putCashCardWindow.add(labelId);

        JTextField idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 18));
        idField.setBounds(25, 110, 150, 35);
        putCashCardWindow.add(idField);

        JLabel labelAmount = new JLabel("Сума поповнення:");
        labelAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        labelAmount.setBounds(225, 80, 200, 30);
        putCashCardWindow.add(labelAmount);

        JTextField amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 18));
        amountField.setBounds(225, 110, 150, 35);
        putCashCardWindow.add(amountField);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16));
        resultArea.setBounds(25, 175, 350, 100);
        resultArea.setBackground(Color.WHITE);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        putCashCardWindow.add(resultArea);

        JButton cashButton = new JButton("Поповнити картку");
        cashButton.setFont(new Font("Arial", Font.BOLD, 20));
        cashButton.setBackground(Color.GRAY);
        cashButton.setForeground(Color.WHITE);
        cashButton.setBounds(25, 305, buttonWidth, buttonHeight);
        cashButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                int amount = Integer.parseInt(amountField.getText().trim());

                TravelCard found = null;
                for (TravelCard card : cards) {
                    if (card.getCardId() == id) {
                        found = card;
                        break;
                    }
                }

                if (found == null) {
                    resultArea.setText("Картку з ID " + id + " не знайдено.");
                    return;
                }

                if (found.getCardSubType() != CardSubType.ACCUMULATIVE) {
                    resultArea.setText("Цю картку не можна поповнювати\n(вона не накопичувальна).");
                    return;
                }

                if (amount <= 0) {
                    resultArea.setText("Сума поповнення має бути більшою за 0.");
                    return;
                }

                found.addBalance(amount);
                resultArea.setText("Картку поповнено!\nНовий баланс: " + found.getBalance() + " грн");
            } catch (NumberFormatException ex) {
                resultArea.setText("Введіть коректні числа у поля.");
            }
        });

        putCashCardWindow.add(cashButton);

        putCashCardWindow.setVisible(true);
    }


    // todo Вікно використання турнікету
    public static void turnstileWindow() {
        if (useTurnstileWindow != null && useTurnstileWindow.isDisplayable()) {
            useTurnstileWindow.toFront();
            return;
        }

        useTurnstileWindow = new JFrame("Проходження через турнікет");
        useTurnstileWindow.setSize(420, 430);
        useTurnstileWindow.setLocation(520, 480);
        useTurnstileWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        useTurnstileWindow.setLayout(null);

        Container contentPane = useTurnstileWindow.getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);

        JLabel label1 = new JLabel("Турнікет на станції", JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 26));
        label1.setBounds(25, 25, 350, 40);
        useTurnstileWindow.add(label1);

        JLabel labelId = new JLabel("Введіть ID картки для використання:");
        labelId.setFont(new Font("Arial", Font.PLAIN, 18));
        labelId.setBounds(25, 80, 350, 30);
        useTurnstileWindow.add(labelId);

        JTextField idField = new JTextField();
        idField.setFont(new Font("Arial", Font.PLAIN, 18));
        idField.setBounds(25, 110, 350, 35);
        useTurnstileWindow.add(idField);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16));
        resultArea.setBounds(25, 175, 350, 100);
        resultArea.setBackground(Color.WHITE);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        useTurnstileWindow.add(resultArea);

        JButton turnstileButton = new JButton("Пройти через турнікет");
        turnstileButton.setFont(new Font("Arial", Font.BOLD, 20));
        turnstileButton.setBackground(Color.GRAY);
        turnstileButton.setForeground(Color.WHITE);
        turnstileButton.setBounds(25, 305, buttonWidth, buttonHeight);
        turnstileButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());

                TravelCard found = null;
                for (TravelCard card : cards) {
                    if (card.getCardId() == id) {
                        found = card;
                        break;
                    }
                }

                if (found == null) {
                    resultArea.setText("Картку з ID " + id + " не знайдено.\nПрохід заборонено.");
                    statistics.recordDeniedUnknown();
                    return;
                }

                if (found.isExpired()) {
                    resultArea.setText("Картка прострочена.\nПрохід заборонено.");
                    statistics.recordDenied(found.getCardType());
                    return;
                }

                if (found.getCardSubType() == CardSubType.ACCUMULATIVE) {
                    if (found.getBalance() != null && found.getBalance() >= found.getTripPrice()) {
                        found.useCard();
                        resultArea.setText("Прохід дозволено.\nБаланс після проходу: " + found.getBalance());
                        statistics.recordAllowed(found.getCardType());
                    } else {
                        resultArea.setText("Недостатньо коштів.\nПрохід заборонено.");
                        statistics.recordDenied(found.getCardType());
                    }
                    return;
                }

                if (found.getRemainingTrips() != null) {
                    if (found.getRemainingTrips() > 0) {
                        found.useCard();
                        resultArea.setText("Прохід дозволено.\nЗалишилось поїздок: " + found.getRemainingTrips());
                        statistics.recordAllowed(found.getCardType());
                    } else {
                        resultArea.setText("Поїздки закінчились.\nПрохід заборонено.");
                        statistics.recordDenied(found.getCardType());
                    }
                    return;
                }

                resultArea.setText("Прохід дозволено");
                statistics.recordAllowed(found.getCardType());

            } catch (NumberFormatException ex) {
                resultArea.setText("Некоректний ID картки.\nПрохід заборонено.");
                statistics.recordDeniedUnknown();
            }
        });

        useTurnstileWindow.add(turnstileButton);

        useTurnstileWindow.setVisible(true);
    }


    // todo Вікно перегляду статистики
    public static void statisticsWindow() {
        if (statisticsWindow != null && statisticsWindow.isDisplayable()) {
            statisticsWindow.toFront();
            return;
        }

        statisticsWindow = new JFrame("Статистика турнікету");
        statisticsWindow.setSize(420, 600);
        statisticsWindow.setLocation(990, 310);
        statisticsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statisticsWindow.setLayout(null);

        Container contentPane = statisticsWindow.getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);

        JLabel label1 = new JLabel("Статистика проходів", JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        label1.setBounds(25, 15, 350, 25);
        statisticsWindow.add(label1);

        JLabel labelView = new JLabel("Вибір типу статистики:");
        labelView.setFont(new Font("Arial", Font.PLAIN, 18));
        labelView.setBounds(25, 45, 350, 25);
        statisticsWindow.add(labelView);

        JComboBox<String> comboBoxView = new JComboBox<>(new String[]{
                "Загальна статистика",
                "Статистика по типах карток",
        });
        comboBoxView.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBoxView.setBounds(25, 70, 350, 35);
        statisticsWindow.add(comboBoxView);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(25, 115, 350, 360);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        statisticsWindow.add(scrollPane);

        // Функція оновлення текстового поля
        Runnable updateTextArea = () -> {
            String selected = (String) comboBoxView.getSelectedItem();
            String text = "";

            if ("Загальна статистика".equals(selected)) {
                text = statistics.getSummaryStatistics();
            } else if ("Статистика по типах карток".equals(selected)) {
                text = statistics.getDetailedStatisticsByCardType();
            }
            textArea.setText(text);
        };

        comboBoxView.addActionListener(e -> updateTextArea.run());

        JButton btnRefresh = new JButton("Оновити статистику");
        btnRefresh.setFont(new Font("Arial", Font.BOLD, 20));
        btnRefresh.setBackground(Color.GRAY);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setBounds(25, 495, buttonWidth, buttonHeight);
        btnRefresh.addActionListener(e -> updateTextArea.run());
        statisticsWindow.add(btnRefresh);

        // Показуємо загальну статистику при відкритті
        updateTextArea.run();

        statisticsWindow.setVisible(true);
    }


    // todo Вікно головного меню
    public static void main(String[] args) {
        JFrame frame = new JFrame("Головне меню");
        frame.setTitle("Мова програмування Java Lab3");
        frame.setSize(420, 430);
        frame.setLocation(50, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);

        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.LIGHT_GRAY);

        JLabel label1 = new JLabel("Меню керування", JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 28));
        label1.setBounds(25, 0, buttonWidth, buttonHeight);
        frame.add(label1);

        JButton myButton1 = new JButton("Випустити картку");
        myButton1.addActionListener(e -> {
            System.out.println("Відкрити меню 'Випустити картку'");
            makeCardWindow();
        });

        myButton1.setSize(buttonWidth, buttonHeight);
        myButton1.setLocation(25, 50);
        myButton1.setBackground(Color.GRAY);
        myButton1.setForeground(Color.WHITE);
        myButton1.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(myButton1);

        JButton myButton2 = new JButton("Показати всі картки");
        myButton2.addActionListener(e -> {
            System.out.println("Показати всі картки");
            showAllCardsWindow();
        });

        myButton2.setSize(buttonWidth, buttonHeight);
        myButton2.setLocation(25, 115);
        myButton2.setBackground(Color.GRAY);
        myButton2.setForeground(Color.WHITE);
        myButton2.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(myButton2);

        JButton myButton3 = new JButton("Поповнити накопич. картку");
        myButton3.addActionListener(e -> {
            System.out.println("Поповнити накопичувальну картку");
            cashOnCardWindow();
        });

        myButton3.setSize(buttonWidth, buttonHeight);
        myButton3.setLocation(25, 180);
        myButton3.setBackground(Color.GRAY);
        myButton3.setForeground(Color.WHITE);
        myButton3.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(myButton3);

        JButton myButton4 = new JButton("Пройти через турнікет");
        myButton4.addActionListener(e -> {
            System.out.println("Пройти через турнікет");
            turnstileWindow();
        });

        myButton4.setSize(buttonWidth, buttonHeight);
        myButton4.setLocation(25, 245);
        myButton4.setBackground(Color.GRAY);
        myButton4.setForeground(Color.WHITE);
        myButton4.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(myButton4);

        JButton myButton5 = new JButton("Переглянути статистику");
        myButton5.addActionListener(e -> {
            System.out.println("Переглянути статистику");
            statisticsWindow();
        });

        myButton5.setSize(buttonWidth, buttonHeight);
        myButton5.setLocation(25, 310);
        myButton5.setBackground(Color.GRAY);
        myButton5.setForeground(Color.WHITE);
        myButton5.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(myButton5);

        frame.setVisible(true);
    }
}