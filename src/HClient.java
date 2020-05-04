public class HClient{
    private int brewT;
    private String id;
    private int i = 1;
    private int number;
    private int dispenser;
    private int time;

    public HClient(String id, int brewT, int number, int dispenser, int time) {
        this.id = id;
        this.brewT = brewT;
        this.number = number;
        this.dispenser = dispenser;
        this.time = time;
    }

    //getter
    public String getId(){
        return id;
    }
    public int getBrewT(){
        return brewT;
    }
    public int getDispenser(){
        return dispenser;
    }
    public int getTime(){
        return time;
    }

    //setter
    public void setId(String id){
        this.id = id;
    }
    public void setBrewT(int brewT){
        this.brewT = brewT;
    }
    public void setDispenser(int dispenser){
        this.dispenser = dispenser;
    }
    public void setTime(int time){
        this.time = time;
    }


    public void output() {
        System.out.println("(" + getTime() + ") " + getId() + " uses dispenser " + getDispenser() + " (time: " + getBrewT() + ")");
    }
}
