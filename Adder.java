package numbers;
import java.util.List;
public class Adder implements Runnable{
    List<Integer> mainList;
    List<Integer> subList;
    Integer integer;

    public Adder(List<Integer> mainList, List<Integer> subList, Integer integer) {
        this.mainList = mainList;
        this.subList = subList;
        this.integer = integer;
    }

    @Override
    public void run() {
        for(int i : subList) {
            try {
                synchronized (mainList) {
                    if (i == 0) {
                        mainList.add(i);
                        System.out.println("added: " + i);
                        mainList.notify();
                    } else {
                        try {
                            if (!mainList.contains(i - 1)) {
                                System.out.println("waiting for adding: " + i);
                                mainList.wait();
                            }
                        }finally {
                            mainList.add(i);
                            System.out.println("added: " + i);
                            mainList.notify();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (integer) {
            if (mainList.size()==integer+1) {
                integer.notify();
            }
        }
    }
}
