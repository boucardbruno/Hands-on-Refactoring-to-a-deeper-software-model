package org.weaveit.seatssuggestions;

import java.util.Objects;

public record SeatingPlace(String rowName, int number, PricingCategory pricingCategory, SeatingPlaceAvailability seatingPlaceAvailability) {

    public boolean isAvailable() {
        return seatingPlaceAvailability == SeatingPlaceAvailability.Available;
    }

    public boolean matchCategory(PricingCategory pricingCategory) {
        if (pricingCategory == PricingCategory.Mixed) {
            return true;
        }

        return this.pricingCategory == pricingCategory;
    }

    public SeatingPlace allocate() {
        if (seatingPlaceAvailability == SeatingPlaceAvailability.Available) {
            return new SeatingPlace(rowName, number, pricingCategory, SeatingPlaceAvailability.Allocated);
        }
        return this;
    }

    public boolean sameSeatingPlace(SeatingPlace seatingPlace) {
        return Objects.equals(rowName, seatingPlace.rowName) && number == seatingPlace.number;
    }

    @Override
    public String toString() {
        return rowName + number;
    }
}
