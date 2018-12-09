package be.magazijnbeheer.core;

public class Type {
    private String typeName;

    public Type(){

    }

    public Type(String typeName){
        this.typeName = typeName;
    }

    public Integer getId() {

        return this.hashCode();
    }

    @Override
    public int hashCode(){
        return Math.abs(typeName.hashCode());
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
