package utils;

import dto.Car;
import enums.Fuel;
import net.datafaker.Faker;

import java.time.LocalDate;

public class CarFactory {
    static Faker faker = new Faker();

    public static Car positiveCar(){
        return Car.builder()
                .serialNumber(faker.vehicle().licensePlate())
                .city("Rehovot")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween(0,
                        LocalDate.now().getYear())))
                .fuel(faker.options().option(Fuel.values()))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .pricePerDay(faker.number()
                        .randomDouble(2,0,1000))
                .about(faker.text().text(0,500))
                .build();
    }

    public static Car negativeBlankAllFieldsCar(){
        return Car.builder()
                .serialNumber("")
                .city("")
                .manufacture("")
                .model("")
                .year("")
                .seats(null)
                .carClass("")
                .pricePerDay(null)
                .about("")
                .build();
    }
}
