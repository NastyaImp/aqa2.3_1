import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DataGenerate {
    private static Faker faker = new Faker(new Locale("ru"));

    public static String generateCity() {
        return faker.address().city();
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName() {
        return (faker.name().lastName() + " " + faker.name().firstName());
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }

}
