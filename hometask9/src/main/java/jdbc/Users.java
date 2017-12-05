package jdbc;

/**
 * Created by vasvasvlad on 07.06.17.
 */
@SuppressWarnings("UnusedDeclaration")
public class Users {
    private long id;
    private String name;
    private int age;

    public Users(long id, String name, int age ){
        this.id =id;
        this.name = name;
        this.age = age;
    }

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        return "Users{" +
                "name= " + this.name +
                " age= " + this.age+"}";
    }

}
