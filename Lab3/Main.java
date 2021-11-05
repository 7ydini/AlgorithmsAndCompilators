import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static File file;
    public static FileReader fileReader;
    public static BufferedReader bufferedReader;
    public static List<String> list;
    public static int wordCounter = 0;
    public static int operatorCounter = 0;

    public static void main(String[] args) throws IOException {
        initFile("src" + File.pathSeparator + "text.txt");//args: String - FilePath
        for (String str :
                list) {
            valid(str);
        }
    }

    private static void valid(String string) {
        char[] arr = string.replaceAll(" ", "").toCharArray();
        Stack stack = new Stack();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'A' || arr[i] == 'F' || arr[i] == 'T' || arr[i] == 'a' || arr[i] == 'E') {
                wordCounter++;
                if (wordCounter > 1) {
                    new ArithmeticException("2 words in a row");
                }
            } else if (arr[i] == '(') {
                checkOperatorCounter();
                checkWordCounter();
                stack.push(arr[i]);
            } else if (arr[i] == ')') {

                checkOperatorCounter();
                checkWordCounter();

                if ((char) stack.peek() == '(') {
                    stack.pop();
                }
            } else if (arr[i] == '|' || arr[i] == '*' || arr[i] == '+' || arr[i] == '-' || arr[i] == '/' || arr[i] == '~') {
                operatorCounter++;
                if (operatorCounter > 1) {
                    new ArithmeticException("2 operators in a row");
                }
                checkWordCounter();
            } else new ArithmeticException("wtf????");
        }
        if (!stack.empty()) {
            new ArithmeticException("Stack is not empty(()(((())))()()()(((()");
        }
    }

    private static void checkOperatorCounter() {
        if (operatorCounter > 0) {
            operatorCounter--;
        }
    }

    private static void checkWordCounter() {
        if (wordCounter > 0) {
            wordCounter--;
        }
    }

    private static void initFile(String path) throws IOException {
        file = new File(path);
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
        list = new ArrayList<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
    }
}
