# Java Quiz App GUI

This project is a simple Java-based quiz application with a graphical user interface (GUI) built using Swing. It allows users to answer multiple-choice questions, tracks their score, and displays the results at the end.

## Features

- **Multiple-choice questions**: Users are presented with questions and four possible answers to choose from.
- **Score tracking**: The application keeps track of the user's score as they progress through the quiz.
- **Results display**: After completing the quiz, the user's score is displayed, and they are given the option to close the application.

## How to Run

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/java-quiz-app-gui.git
    ```

2. **Compile the Code**:
    - Navigate to the directory where the code is located and compile the Java file using the following command:
    ```bash
    javac QuizAppGUI.java
    ```

3. **Run the application**:
    - Once compiled, run the application using:
    ```bash
    java QuizAppGUI
    ```

## Usage

- The application window will display one question at a time with four possible answers.
- Select your answer by clicking on the corresponding radio button.
- Click the "Submit" button to submit your answer and move to the next question.
- After the final question, your score will be displayed, and you will have the option to close the application.

## Questions

The quiz contains a set of predefined Java-related questions covering topics like access modifiers, exception handling, the Java Virtual Machine (JVM), and more.

## Dependencies

- Java Development Kit (JDK) 8 or higher

## Customization

To add or modify questions:

1. Locate the section in the `QuizAppGUI` constructor where the questions are added to the `questionBank`.
2. Add a new `Question` object with the question, a list of options, and the correct answer.

    Example:
    ```java
    questionBank.add(new Question(
        "What is the purpose of the 'final' keyword in Java?",
        List.of("A) It is used to declare a class as abstract.", 
                "B) It is used to prevent inheritance.", 
                "C) It indicates that a method cannot be overridden.", 
                "D) It is used to specify the entry point of a Java program"),
        'C'));
    ```

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

If you have any questions or feedback, please contact [taskforkarde@gmail.com].
