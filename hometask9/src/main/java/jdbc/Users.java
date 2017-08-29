package dataSets;

/**
 * Created by vasvasvlad on 07.06.17.
 */
@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long id;
    private String name;
    private int age;

    public UsersDataSet(long id, String name, int age ){
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
        return "UsersDataSet{" +
                "name= " + this.name +
                " age= " + this.age+"}";
    }

}
