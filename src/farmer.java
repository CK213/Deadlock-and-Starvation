import java.util.concurrent.Semaphore;
class Shared{
    static int count = 0;
}
class neon{
    static int ne = 0;
}
public class farmer extends Thread{
    Semaphore sem;
    String name;
    int counter = 0;
    public farmer(Semaphore sem, String name){
        super(name);
        this.sem = sem;
        this.name = name;
    }
    @Override
    public void run(){
        //run by thread N_farmers
        if (this.getName().contains("N")){
            try{
                System.out.println(name + ": Waiting for bridge. Going towards South");
                //acquiring the lock
                sem.acquire();
                //accessing the shared resource
                for(int i = 0; i < 4;i++){
                    counter = counter + 5;
                    Shared.count = counter;
                    System.out.println(name + ": Crossing bridge for step " + Shared.count);
                   //allowing a context switch
                    Thread.sleep(10);
                }
            }catch (InterruptedException e){
                System.out.println(e);
            }
            //Release the permit
            System.out.println(name + ": Across the bridge");
            neon.ne++;
            System.out.println("NEON = " + neon.ne);
            sem.release();
        }//run by South farmers' threads
        else{
            try{
                System.out.println(name + ": Waiting for bridge. Going towards South");
                //acquiring the lock
                sem.acquire();
                //accessing the shared resource
                for(int i = 0; i < 4;i++){
                    counter = counter + 5;
                    Shared.count = counter;
                    System.out.println(name + ": Crossing bridge for step " + Shared.count);
                    //allowing a context switch
                    Thread.sleep(10);
                }
            }catch (InterruptedException e){
            System.out.println(e);
        }
        //Release the permit
        System.out.println(name + ": Across the bridge");
            neon.ne++;
            System.out.println("NEON = " + neon.ne);
        sem.release();
        }
    }
}
