import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class c3291914A2 {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        boolean leave = false;
        while (leave == false) {
            //Create content list to let user choose which task they want to run
            System.out.println("Which task are you up to?");
            System.out.println("1. Task 1");
            System.out.println("2. Task 2");
            System.out.println("3. Task 3");
            System.out.println("4. Exit the program.");
            //read user's input
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int n = Integer.parseInt(input) - 1;
            String inpu = " ";
            String fi =" ";
            File file;
            Scanner scan;

            switch (n) {
                case 0:
                    //Ask user to type the file they want to run
                    System.out.println("Please input the address of your file.");
                    fi = scanner.nextLine();
                    //Read the file
                    System.out.println(fi);
                    file = new File(fi);
                    scan = new Scanner(file);
                    //task 1
                    System.out.println("Task 1.");
                    List<String> name = new ArrayList<>();
                    //Creating a Semaphore object with number of permits 1
                    Semaphore sem = new Semaphore(1);
                    //Read the file
                    inpu = scan.nextLine();
                    String[] farmers = inpu.split(", ");
                    String temp = " ";
                    temp = farmers[0];
                    //Seperate the line by the symbol "="
                    int index = temp.indexOf("=");
                    String num1 = "";
                    num1 = temp.substring(index+1);
                    //get the number of N_farmers, transfer the string into integer.
                    int N = Integer.parseInt(num1);
                    //get the number of S_farmers, transfer the string into integer.
                    temp = farmers[1];
                    num1 = temp.substring(index+1);
                    int S = Integer.parseInt(num1);
                    //set the farmers' names
                    for(int i = 1; i <= N; i++){
                        String t = "N_Farmer" + i;
                        name.add(t);
                    }
                    for(int i = 1; i <= S; i++){
                        String t = "S_Farmer" + i;
                        name.add(t);
                    }
                    //Call the function
                    farmer N_farmer1 = new farmer(sem, name.get(0));
                    farmer N_farmer2 = new farmer(sem, name.get(1));
                    farmer S_farmer1 = new farmer(sem, name.get(2));
                    farmer S_farmer2 = new farmer(sem, name.get(3));
                    //Start the thread
                    N_farmer1.start();
                    N_farmer2.start();
                    S_farmer1.start();
                    S_farmer2.start();
                    //Waiting for the threads
                    N_farmer1.join();
                    N_farmer2.join();
                    S_farmer1.join();
                    S_farmer2.join();

                    System.out.println(" ");
                    break;

                //Task 2
                case 1:
                    //Ask user to type the file they want to run
                    System.out.println("Please input the address of your file.");
                    fi = scanner.nextLine();
                    //Read the file
                    System.out.println(fi);
                    file = new File(fi);
                    scan = new Scanner(file);
                    //create the variables
                    int arrT = 0;
                    int eatT = 0;
                    String id = " ";
                    System.out.println(" ");
                    System.out.println("Task 2");
                    //create a dynamic array
                    List<Customer> customer = new ArrayList<>();
                    //create scanner
                    IceCreamShop shop = new IceCreamShop();
                    //make sure the file have next line to read
                    System.out.println("\n Customer arrives Seats Leaves");
                    //read file
                    if (scan.hasNextLine()) {
                        //Stop reading when it's END
                        while (inpu.compareToIgnoreCase("END") != 0) {
                            inpu = scan.nextLine();
                            String[] num = inpu.split(" ");
                            for (int a = 0; a < num.length - 1; ) {
                                //read the file and store it into different variables
                                arrT = Integer.parseInt(num[a]);
                                id = num[a + 1];
                                eatT = Integer.parseInt(num[a + 2]);
                                //start the simulation
                                shop.enter(eatT, arrT);
                                //get the waiting
                                int wait = shop.outputWait();
                                //call customer
                                Customer c = new Customer(id, arrT, eatT, wait);
                                customer.add(c);
                                a = a + 3;
                                //output
                                System.out.println(c.output());
                            }
                        }
                    }
                    //close scanner
                    scan.close();
                    System.out.println(" ");
                    break;

                //task 3
                case 2:
                    //Ask user to type the file they want to run
                    System.out.println("Please input the address of your file.");
                    fi = scanner.nextLine();
                    //Read the file
                    System.out.println(fi);
                    file = new File(fi);
                    scan = new Scanner(file);
                    //create variables
                    int time = 0;
                    String client = " ";
                    int number;
                    int counter = 0;
                    int a= 0;
                    System.out.println(" ");
                    System.out.println("Task 3");
                    CoffeeS coffeeS = new CoffeeS();
                    List<CClient> c = new ArrayList<>();
                    List<HClient> h = new ArrayList<>();
                    //read file
                    //id there is next line
                    if (scan.hasNextLine()) {
                        //get next line
                        inpu = scan.nextLine();
                        number = Integer.parseInt(inpu);
                        while (counter < number) {
                        inpu = scan.nextLine();
                        //customers who ordered cold coffee
                            if (inpu.contains("C")) {
                                //split the space bar and store the string into the string array
                                String[] data = inpu.split(" ");
                                    client = data[a];
                                    time = Integer.parseInt(data[a + 1]);
                                    coffeeS.enter(client, time, number);
                                    int dis = coffeeS.getCount();
                                    int wait = coffeeS.getWait();
                                    //call client
                                    CClient c1 = new CClient(client, time, number, dis, wait);
                                    c.add(c1);
                                    //output
                                    c1.output();
                                    //When it comes to the end output Done
                                    if(a >= number*2){
                                        System.out.println("(" + ") DONE");
                                    break;
                                    }
                                counter++;
                                //customers who ordered hot coffee
                            } else if (inpu.contains("H")) {
                                //split the space bar and store the string into the string array
                                String[] data = inpu.split(" ");
                                    client = data[a];
                                    time = Integer.parseInt(data[a + 1]);
                                    coffeeS.enter(client, time, number);
                                    int dis = coffeeS.getCount();
                                    int wait = coffeeS.getWait();
                                //call client
                                    HClient h1 = new HClient(client, time, number, dis, wait);
                                    h.add(h1);
                                    //output
                                    h1.output();
                                //When it comes to the end output Done
                                if(a >= number*2){
                                    System.out.println("(" + ") DONE");
                                    break;
                                }
                                counter++;
                            }
//                        s.makeCoffeeShop();
                        }
                    }
                    scan.close();
                    System.out.println(" ");
                    break;
                case 3:
                    //leave the system
                    leave = true;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }
}