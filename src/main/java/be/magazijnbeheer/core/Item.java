package be.magazijnbeheer.core;

public class Item {
    private Integer id;
    private Type type;
    private Lender lender;

    public Item(){

    }

    public Item(Integer id){
        this.id = id;
    }

    public Item(Type type){
        this.type = type;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Lender getLender() {
        return lender;
    }

    public void setLender(Lender lender) {
        this.lender = lender;
    }
}
