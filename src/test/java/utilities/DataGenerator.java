package utilities;
import net.datafaker.Faker;

public class DataGenerator {

   static Faker faker = new Faker();

    public static String  getFirstName(){
        return faker.name().firstName();
    }
    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }
    public static String getPhoneNumber() {
        return faker.number().digits(10);
    }
    public static String getPassword() {
        return faker.internet().password(8,12,true,true,true);
    }

    public static String getUniqueEmail() {
        return faker.name().firstName().toLowerCase()
                +System.currentTimeMillis()+
                "@yopmail.com";
    }




}
