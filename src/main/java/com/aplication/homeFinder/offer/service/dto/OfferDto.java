package com.aplication.homeFinder.offer.service.dto;

import com.aplication.homeFinder.offer.model.Offer;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
public class OfferDto {
    @NotNull
    private Offer.KindOfProperty kindOfProperty;
    @Min(0)
    private double price;
    @NotBlank
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+", message = "nieprawidłowy tytuł")
    private String title;
    @NotBlank
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+", message = "nieprawidłowa lokalizacja")
    private String location;
    @Min(0)
    private int numberOfRooms;
    @Min(0)
    private double area;
    @Min(0)
    private double pricePerMeter;
    @Min(0)
    private int floor;
    @NotBlank
    @Size(max = 5000, message = "Maksymalna ilość znaków to 5000")
    private String description;
    @NotNull
    private OfferDetailsDto offerDetailsDto;
}
