import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class HomeTask4 {
    
    public static void main(String[] args) {
        task();
    }

    // Даны два Deque, представляющие два целых числа. Цифры хранятся в обратном порядке и каждый из их узлов содержит одну цифру.
    // 1) Умножьте два числа и верните произведение в виде связанного списка.
    // 2) Сложите два числа и верните сумму в виде связанного списка. Одно или два числа должны быть отрицательными.


    static void task() {
        Scanner input = new Scanner(System.in);

        System.out.print("Input first number: ");
        Deque<String> numList1 = stringDeque(input.nextLine());
        System.out.println(numList1);
        Deque<Integer> numListInt1 = integerDeque(numList1);
       
        
        System.out.print("Input second number: ");
        Deque<String> numList2 = stringDeque(input.nextLine());
        System.out.println(numList2);
        Deque<Integer> numListInt2 = integerDeque(numList2);
        
    
        System.out.println("Enter 1 for multiplication or enter 2 for addition: ");
        int n = input.nextInt();
        
        input.close();
        
        if (n == 1) productOfNumbers(numListInt1, numListInt2);
        else if (n==2) summOfNumbers(numListInt1, numListInt2);
        

    }

    static Deque<String> stringDeque(String input){
        Deque<String> numList = new ArrayDeque<>();
        char[] charInput = input.toCharArray();
        for (char num : charInput) {
            numList.addFirst(String.valueOf(num));
            }
        return numList;
    }

    static Deque<Integer> integerDeque(Deque<String> numList){
        Deque<Integer> numListInt = new ArrayDeque<>();
        if (numList.getLast().equals("-")){
            numList.removeLast();
            int num = Integer.parseInt(String.valueOf(numList.getLast())) * -1;
            numList.removeLast();
            while (!numList.isEmpty()){
                numListInt.addFirst(Integer.parseInt(String.valueOf(numList.getLast())));
                numList.removeLast();
            }
            numListInt.addLast(num);
        } else {
        while (!numList.isEmpty()){
            numListInt.addFirst(Integer.parseInt(String.valueOf(numList.getLast())));
            numList.removeLast();
            }
        }
        return numListInt;
    }

    static Integer dequeToInt(Deque<Integer> numListInt){
        int result = 0;
        if (numListInt.getLast() > 0){
            for (int i = numListInt.size() - 1; i >= 0; i--) {
                int digit = (int) Math.pow(10, i);
                result += numListInt.pollLast() * digit;
            }
        } else {
            int num = Math.abs(numListInt.getLast());
            numListInt.removeLast();
            numListInt.addLast(num);
            for (int i = numListInt.size() - 1; i >= 0; i--) {
                int digit = (int) Math.pow(10, i);
                result += numListInt.pollLast() * digit;
            }
            result *= -1;
        }
        return result;
    }

    static void productOfNumbers(Deque<Integer> num1, Deque<Integer> num2){
        LinkedList<String> result = new LinkedList<>();
        int res = dequeToInt(num1) * dequeToInt(num2);
        if (res < 0){
            res = (int) Math.abs(res);
            while (res != 0) {
                result.addFirst(String.valueOf(res%10));
                res /= 10;
            }
            result.addFirst("-");
        } else {
            while (res != 0) {
                result.addFirst(String.valueOf(res%10));
                res /= 10;
            }
        }
        System.out.print("Multiplication result: ");
        System.out.println(result);
    }

    static void summOfNumbers(Deque<Integer> num1, Deque<Integer> num2){
        LinkedList<String> result = new LinkedList<>();
        int res = dequeToInt(num1) + dequeToInt(num2);
        if (res < 0){
            res = (int) Math.abs(res);
            while (res != 0) {
                result.addFirst(String.valueOf(res%10));
                res /= 10;
            }
            result.addFirst("-");
        } else {
            while (res != 0) {
                result.addFirst(String.valueOf(res%10));
                res /= 10;
            }
        }
        System.out.print("Addition result: ");
        System.out.println(result);
    }

}