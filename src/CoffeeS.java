import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;


public class CoffeeS {
    int number;

    public CoffeeS(){
    }
    //create the variables
        private int count = 0;
        List<Integer> machine = new ArrayList<>();
        List<Integer> brew = new ArrayList<>();
    List<Integer> time = new ArrayList<>();
        List<String> client = new ArrayList<>();

    int wait = 0;
        int temp = 0;
        int temp2 = 0;
        int counter = 0;
        int occupied = 0;
        int c = 0;
        static Semaphore semaphore = new Semaphore(1);
        boolean full = false;

        //start running
        public void enter(String id, int brewT, int number) throws InterruptedException {
            this.number = number;
            ++occupied;
            //set the machine limit, if 3 of them are using then is occupied
            if(occupied == 3){
                full = true;
            }else{
                full = false;
            }
            try {
                //start semaphore
                    semaphore.acquire();
                    //if total occupied machine is smaller than 3
                    if (count < 3) {
                        client.add(id);
                        //if the overall round is larger than 3
                        if(counter > 3){
                            //machine already full
                            if(full == true) {
                                //calculate the waiting time
                                wait = temp + temp2;
                            }else{
                                //find the maximum time among all the previous brewing time
                                temp = Collections.max(brew);
                                wait = temp;
                            }
                            ++count;
                            brew.add(brewT);
                            machine.add(count);
                        }else{
                            wait = 0;
                            ++count;
                            brew.add(brewT);
                            machine.add(count);
                        }
                        counter++;
                        time.add(wait);
                    } else {
                        client.add(id);
                        //when the 4th client is also ordering cold coffee, then do the following calculations
                        if(client.get(counter - 1).contains("C")&& client.get(counter).contains("C")){
                            //find the minimum time among all the previous brewing time
                            temp = Collections.min(brew);
                            int index = brew.indexOf(temp);
                            //get the machine number for the one which did the coffee in the most minimum time
                            count = machine.get(index);
                            wait += temp;
                            occupied = 1;
                        }//when the 4th client is also ordering hot coffee, then do the following calculations
                        else if(client.get(counter - 1).contains("H")&& client.get(counter).contains("H")) {
                            //find the minimum time among all the previous brewing time
                            temp = Collections.min(brew);
                            int index = brew.indexOf(temp);
                            //get the machine number for the one which did the coffee in the most minimum time
                            count = machine.get(index);
                            wait += temp;
                            occupied = 1;
                        }//if the 4th client is ordering another type of coffee
                        else {
                            temp = Collections.max(brew);
                            temp2 = Collections.max(time);
                            wait = temp + temp2;
                            count = 1;
//                            occupied++;
                            time.add(wait);
                        }
//                        countTime();
                            machine.add(count);
                            counter++;
                        brew.add(brewT);
                    }
                } finally {
                //release the semaphore
                    semaphore.release();
                }
        }
//getters
        public int getWait(){
            return wait;
        }

    public int getCount(){
        return count;
    }
    }

