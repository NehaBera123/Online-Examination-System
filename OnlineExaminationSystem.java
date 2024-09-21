import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {

    String questionText;
    List<String> options;
    int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class User {

    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class OnlineExaminationSystem {

    static List<Question> questions = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static User loggedInUser;
    static boolean sessionOpen = false;

    public static void main(String[] args) {

        initializeQuestions();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. LogOut");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {

                case 1:
                    login(scanner);
                    break;

                case 2:
                    register(scanner);
                    break;
 
                case 3:
                    System.out.println("Exiting the system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option!");
            }
        }
    }

    public static void initializeQuestions() {

        List<String> options1 = new ArrayList<>();
        options1.add("Guido van Rossum");
        options1.add("James Gosling");
        options1.add("Dennis Ritchie");
        options1.add("Bjarne Stroustrup");

        List<String> options2 = new ArrayList<>();
        options2.add("JRE");
        options2.add("JIT");
        options2.add("JDK");
        options2.add("JVM");

        List<String> options3 = new ArrayList<>();
        options3.add("Object-oriented");
        options3.add("Use of pointers");
        options3.add("Portable");
        options3.add("Dynamic and Extensible");

        List<String> options4 = new ArrayList<>();
        options4.add(".js");
        options4.add(".txt");
        options4.add(".class");
        options4.add(".java");

        List<String> options5 = new ArrayList<>();
        options5.add("Polymorphism");
        options5.add("Inheritance");
        options5.add("Compilation");
        options5.add("Encapsulation");

        List<String> options6 = new ArrayList<>();
        options6.add("MAVEN_Path");
        options6.add("JavaPATH");
        options6.add("JAVA");
        options6.add("JAVA_HOME");

        List<String> options7 = new ArrayList<>();
        options7.add("MemoryError");
        options7.add("OutOfMemoryError");
        options7.add("MemoryOutOfBoundsException");
        options7.add("MemoryFullException");
        
        List<String> options8 = new ArrayList<>();
        options8.add(" intf");
        options8.add("Intf");
        options8.add("interface");
        options8.add("Interface");

        List<String> options9 = new ArrayList<>();
        options9.add("ArrayList");
        options9.add(" Abstract class");
        options9.add("Object class");
        options9.add("String");

        List<String> options10 = new ArrayList<>();
        options10.add("Protected");
        options10.add(" Void");
        options10.add(" Public");
        options10.add("Private");
    
        Question question1 = new Question("Who invented Java Programming?", options1, 1);
        Question question2 = new Question("Which component is used to compile, debug and execute the java programs?", options2, 2);
        Question question3 = new Question("Which one of the following is not a Java feature?", options3, 1);
        Question question4 = new Question("What is the extension of Java code files?", options4, 3);
        Question question5 = new Question("Which of the following is not an OOPS concept in Java?", options5, 2);
        Question question6 = new Question("Which environment variable is used to set the java path?",options6, 3);
        Question question7 = new Question("Which exception is thrown when java is out of memory?", options7, 1);
        Question question8 = new Question( "Which of these keywords is used to define interfaces in Java?", options8, 1);
        Question question9 = new Question( "Which of the following is a superclass of every class in Java?", options9, 3);
        Question question10 = new Question("Which one of the following is not an access modifier?", options10,1 );
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
    }   

    public static void register(Scanner scanner) {

        System.out.print("Enter a Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a Password: ");
        String password = scanner.nextLine();

        // Check if the username is already taken
        for (User user : users) {
            if (user.username.equals(username)) {
                System.out.println("Username already taken. Please choose another one.");
                return;
            }
        }

        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("Registration successful! You can now log in now.");
    }

    public static void login(Scanner scanner) {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate credentials
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                loggedInUser = user;
                sessionOpen = true;
                startExam(scanner);
                return;
            }
        }

        System.out.println("Invalid credentials. Please try again!");
    }

    public static void startExam(Scanner scanner) {

        System.out.println("\nWelcome, " + loggedInUser.username + "!");
        for (int i = 0; i < questions.size(); i++) {

            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.questionText);
            for (int j = 0; j < question.options.size(); j++) {
                System.out.println((j + 1) + ". " + question.options.get(j));
            }

            System.out.print("Select your answer (1-" + question.options.size() + "): ");
            int userChoice = scanner.nextInt();
            if (userChoice == question.correctOption + 1) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("Exam completed!");
        sessionOpen = false;
    }
}