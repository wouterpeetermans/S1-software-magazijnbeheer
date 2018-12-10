package be.magazijnbeheer.core;

public class Person implements Lender{
    private String name;
    private String address;

    public Person(){

    }

    public Person(String name, String address){
        this.name = name;
        this.address = address;
    }

    @Override
    public Integer getId() {
        return this.hashCode();
    }

    @Override
    public int hashCode(){
        return Math.abs(name.hashCode() * address.hashCode());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
}
