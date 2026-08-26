package dto;

import enums.Fuel;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String location;
    private String manufacture;
    private String model;
    private String year;
    private Fuel fuel;
    private String seats;
    private String carClass;
    private String registrationNumber;
    private String price;
    private String about;
}
