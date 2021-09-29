public class Main {
    public static void main(String[] args) {
        System.out.println(calculate("1 + 12 + 11 * ( 5 + 1 )"));
    }

    private static String calculate(String string) {
        string = string.replaceAll(" ", "");
        char[] arr = string.toCharArray();
        int countL = 0;
        int countR = 0;
        if (isOperator(arr[arr.length - 1]) || arr[arr.length - 1] == '('){
            return "Не корректный крайний правый символ.";
        }//В конце строки + - * / (
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == '('){
                if (isOperator(arr[i + 1])){
                    return "После \"(\" идёт оператор!";
                }// (+ (- (/ и.т.п.
                countL++;
            }
            else if (arr[i] == ')') countR++;

            if (i != arr.length - 1){
                if(isOperator(arr[i]) && (isOperator(arr[i + 1]) || arr[i + 1] == ')')){
                    return "Неправильное построение операторов!";
                }//Обрабатываем неправильную расстановку знаков (++ +- /+ +) и.т.п.)
                if(arr[i] == '/' && arr[i + 1] == '0'){
                    return "Деление на ноль";
                }// Деление на 0
            }
        }
        if (countL != countR){
            return "Кол-во \"(\" и \")\" разное!";
        }
        String result = solve(string);

        return result;
    }

    private static String solve(String string) {
        char[] arr = string.toCharArray();
        int ct = 0;
        for(int i = 0; i < arr.length; i++){
           if (isOperator(arr[i + 1]) || arr[i + 1] == ')'){
               StringBuilder parse = new StringBuilder();
               for (int j = ct; j < i + 1; j++){
                   parse.append(arr[j]);
               }
               try {
                   double d = Double.parseDouble(parse.toString());
               }catch (NumberFormatException ex){
                   return "Не корректные вводимые числа";
               }
               while (i + 1 < arr.length &&
                       ( arr[i + 1] == ')' || arr[i + 1] == '(' || isOperator(arr[i + 1]))) {
                   i++;
               }
               ct = i + 1;
           }
        }
        return "Введённые данные корректны!";
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
