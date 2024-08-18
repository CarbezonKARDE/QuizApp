import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class QuizAppGUI extends JFrame {

    private JLabel questionLabel;
    private List<JRadioButton> answerOptionButtons;
    private ButtonGroup answerOptionGroup;
    private JButton submitButton;

    private List<Question> questionBank;
    private int currentQuestionIndex;
    private int score;

    public static class Question {
        private String question;
        private List<String> options;
        private char correctAnswer;

        public Question(String question, List<String> options, char correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public List<String> getOptions() {
            return options;
        }

        public char getCorrectAnswer() {
            return correctAnswer;
        }
    }

    public QuizAppGUI() {
        super("Quiz App");

        setLayout(new BorderLayout());

        //main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        //questions and options
        JPanel questionOptionsPanel = new JPanel();
        questionOptionsPanel.setLayout(new BoxLayout(questionOptionsPanel, BoxLayout.Y_AXIS));
        mainPanel.add(questionOptionsPanel, BorderLayout.WEST);

        //questions label 
        questionLabel = new JLabel();
        questionOptionsPanel.add(questionLabel);
        questionOptionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        answerOptionButtons = new ArrayList<>();
        answerOptionGroup = new ButtonGroup();

        //options: buttons
        for (int i = 0; i < 4; i++) {
            JRadioButton button = new JRadioButton();
            answerOptionButtons.add(button);
            questionOptionsPanel.add(button);
            answerOptionGroup.add(button);
            questionOptionsPanel.add(Box.createVerticalStrut(5)); // Add spacing between options
        }

        //submit button panel
        JPanel submitButtonPanel = new JPanel(new BorderLayout());
        mainPanel.add(submitButtonPanel, BorderLayout.EAST);

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30)); // Adjust the width and height as needed

        submitButtonPanel.add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitQuiz();
            }
        });
        
        questionBank = new ArrayList<>();

        // Add your questions here
        questionBank.add(new Question("What is the main purpose of the 'public' access modifier in Java?", List.of("A) It restricts access to the class.", "B) It allows access from any class.", "C) It is used for local variables.", "D) It is not a valid access modifier in Java"), 'B'));
        questionBank.add(new Question("What is the difference between '==' and '.equals()' when comparing objects in Java?", List.of("A) '==' compares object references, while '.equals()' compares object contents.", "B) '==' compares object contents, while '.equals()' compares object references.", "C) They are used interchangeably.", "D) '==' is used for comparing primitive data types, while '.equals()' is used for objects"), 'A'));
        questionBank.add(new Question("What is a 'NullPointerException' in Java?", List.of("A) It occurs when a method is not defined in a class.", "B) It occurs when an object is used without being instantiated.", "C) It is a type of checked exception.", "D) It is a syntax error"), 'B'));
        questionBank.add(new Question("What is the 'this' keyword used for in Java?", List.of("A) It refers to the current instance of the class.", "B) It is used to declare a new object.", "C) It is used for array indexing.", "D) It represents a static method"), 'A'));
        questionBank.add(new Question("Which Java keyword is used to create a subclass?", List.of("A) extends", "B) implements", "C) super", "D) interface"), 'A'));
        questionBank.add(new Question("What is the purpose of the 'final' keyword in Java?", List.of("A) It is used to declare a class as abstract.", "B) It is used to prevent inheritance.", "C) It indicates that a method cannot be overridden.", "D) It is used to specify the entry point of a Java program"), 'C'));
        questionBank.add(new Question("What is a constructor in Java?", List.of("A) A method used to create objects of a class.", "B) A built-in class in Java.", "C) A reserved keyword in Java.", "D) An interface for defining constants"), 'A'));
        questionBank.add(new Question("What is the Java Virtual Machine (JVM)?", List.of("A) A virtual reality platform for game development.", "B) A compiler for Java programs.", "C) A hardware component of a computer.", "D) A runtime environment for executing Java bytecode"), 'D'));
        questionBank.add(new Question("Which data structure in Java is used to store key-value pairs?", List.of("A) Array", "B) ArrayList", "C) HashMap", "D) Stack"), 'C'));
        questionBank.add(new Question("What is the purpose of the 'static' keyword in Java?", List.of("A) It is used to define a class as abstract.", "B) It is used to declare a variable as an instance variable.", "C) It is used to create an object of a class.", "D) It indicates that a method or variable belongs to the class itself, not to instances of the class"), 'D'));
        questionBank.add(new Question("What is the 'try-catch' block used for in Java?", List.of("A) To define a loop in Java.", "B) To handle exceptions and perform error handling.", "C) To define a method in Java.", "D) To create new objects"), 'B'));

        currentQuestionIndex = 0;
        score = 0;

        displayNextQuestion();
        int windowWidth = 900;
        int windowHeight = 400;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - windowWidth) / 2;
        int y = (screenSize.height - windowHeight) / 2;
        setBounds(x, y, windowWidth, windowHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questionBank.size()) {
            Question question = questionBank.get(currentQuestionIndex);
            questionLabel.setText(question.getQuestion());

            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                JRadioButton button = answerOptionButtons.get(i);
                button.setText(options.get(i));
                button.setSelected(false);
            }

            currentQuestionIndex++;
        } else {
            submitButton.setEnabled(false); // Disable submit after all questions
            displayResults();
        }
    }

    private void submitQuiz() {
        for (int i = 0; i < answerOptionButtons.size(); i++) {
            JRadioButton button = answerOptionButtons.get(i);
            if (button.isSelected()) {
                char userAnswer = (char) ('A' + i);
                if (userAnswer == questionBank.get(currentQuestionIndex - 1).getCorrectAnswer()) {
                    score++;
                }
                displayNextQuestion();
                return;
            }
        }
    }

    private void displayResults() {
        int result = JOptionPane.showConfirmDialog(this, "Your score is: " + score + "/" + questionBank.size() + "\nDo you want to close the application?", "Quiz Results", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            //application close
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizAppGUI().setVisible(true);
            }
        });
    }
}