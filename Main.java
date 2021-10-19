package numbers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer number =sc.nextInt();

        List<Integer> mainList = Collections.synchronizedList(new ArrayList<Integer>());
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        for(int i=0; i<=number ; i++){
            if(i%2==0){
                evens.add(i);
            }else{
                odds.add(i);
            }
        }
        Thread thread1 = new Thread(new Adder( mainList, evens, number ));
        Thread thread2 = new Thread(new Adder( mainList, odds, number ));
        thread1.start();
        thread2.start();
        System.out.println("got here");
        try {
            synchronized (number) {
                number.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(mainList);


    }

}