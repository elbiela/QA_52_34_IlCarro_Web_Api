package utils;

import dto.Car;
import enums.Fuel;

public class CarFactory {
    static Car car;

    public static Car positiveCar(){
        car = Car.builder()
                .location("Rehovot")
                .manufacture("Toyota")
                .model("Corolla")
                .year("2020")
                .fuel(Fuel.HYBRID)
                .seats("5")
                .carClass("Sedan")
                .registrationNumber("12345678")
                .price("50")
                .about("Good car")
                .build();
        return car;
    }
}
