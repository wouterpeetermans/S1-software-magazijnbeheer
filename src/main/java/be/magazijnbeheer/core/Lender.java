package be.magazijnbeheer.core;

public class Lender {
    private String name;
    private String address;

    public Lender(){

    }

    public Lender(String name, String address){
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return this.hashCode();
    }

    @Override
    public int hashCode(){
        return Math.abs(name.hashCode() * address.hashCode());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
