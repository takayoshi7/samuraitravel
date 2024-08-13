package com.example.samuraitravel.form;

import java.beans.ConstructorProperties;

import lombok.Data;

@Data
// @AllArgsConstructor
public class ReservationRegisterForm {
    private Integer houseId;

    private Integer userId;

    private String checkinDate;

    private String checkoutDate;

    private Integer numberOfPeople;

    private Integer amount;

    @ConstructorProperties({"houseId", "userId", "checkinDate", "checkoutDate", "numberOfPeople", "amount"})
    public ReservationRegisterForm(int houseId, int userId, String checkinDate, String checkoutDate, int numberOfPeople, int amount) {
        this.houseId = houseId;
        this.userId = userId;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.numberOfPeople = numberOfPeople;
        this.amount = amount;
    }
}