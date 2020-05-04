import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class IceCreamShop{
    private int count = 0;
    //make a new integer
    int temp;
    //make a new dynamic array
    List<Integer> eat = new ArrayList<>();
    //make a new array
    int[] eatTime = new int[5];
    int wait = 0;
    static Semaphore semaphore = new Semaphore(1);
    //start running the program
    public void enter(int eatT, int arrT) throws InterruptedException {
            try {
                //start the semaphore
                semaphore.acquire();
                //count the seats if the seats are not all occupied
                    if (count < 5) {
                        wait = 0;
                        ++count;
                        //add the eating time into the dynamic array
                        eat.add(eatT);
                        //if arriving time larger than 0
                        if (arrT > 0) {
                            wait = arrT;
                        }
                        eatTime[count - 1] = eatT;
                    } else if (count >= 5) {
                        //find the maximum number in the dynamic array
                        temp = Collections.max(eat);
                        int index = eat.indexOf(temp);
                        //if the largest eating time larger than arriving time
                        if (temp > arrT) {
                            wait = temp;
                            //remove the largest eating time
                            eat.remove(index);
                        }
                        //if arriving time larger than the largest eating time
                        else if(arrT > temp){
                            wait = arrT;
                        }
                        ++count;
                    }
                }
            finally {
                //quit semaphore
                semaphore.release();
            }
        }
//getter
    public int outputWait(){
        return wait;
    }

}
