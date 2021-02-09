package studio.demo.enums;

public enum DefaultProcedures {

    MANICURE("MANICURE" ),
    FULL_PEDICURE("FULL_PEDICURE"),
    PEDICURE("PEDICURE" );

    private String name;


    DefaultProcedures(String name){
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
