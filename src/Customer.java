public class Customer {
    private int arrT;
    private int eatT;
    private String id;
    private int wait;

    public Customer(String id, int arrT, int eatT, int wait){
        this.id = id;
        this.arrT = arrT;
        this.eatT = eatT;
        this.wait = wait;
    }
    //getter
    public String getId(){
        return id;
    }
    public int getArrT(){
        return arrT;
    }
    public int getWait(){
        return wait;
    }
    public int getEatT(){
        eatT = eatT + wait;
        return eatT;
    }

    //setter
    public void setId(String id){
        this.id = id;
    }
    public void setArrT(int arrT){
        this.arrT = arrT;
    }
    public void setWait1(int wait1){
        wait = wait1;
    }
    public void setEatT(int eatT){
        this.eatT = eatT;
    }

    public String output(){
        return(getId() + "     " + getArrT() + "     " + getWait() + "     " + getEatT());
    }
}
