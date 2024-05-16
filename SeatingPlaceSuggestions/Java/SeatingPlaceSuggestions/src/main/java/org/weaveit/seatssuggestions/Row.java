package org.weaveit.seatssuggestions;

import java.util.ArrayList;
import java.util.List;

public record Row(String name, List<SeatingPlace> seatingPlaces) {

    public Row addSeatingPlace(SeatingPlace seatingPlace) {
        List<SeatingPlace> newSeatingPlaces = new ArrayList<>(seatingPlaces);
        newSeatingPlaces.add(seatingPlace);
        return new Row(this.name, newSeatingPlaces);
    }

    public SeatingOptionSuggested suggestSeatingOption(int partyRequested, PricingCategory pricingCategory) {
        for (SeatingPlace seat : seatingPlaces) {
            if (seat.isAvailable() && seat.matchCategory(pricingCategory)) {
                SeatingOptionSuggested seatingOptionSuggested = new SeatingOptionSuggested(partyRequested, pricingCategory);
                seatingOptionSuggested.addSeat(seat);

                if (seatingOptionSuggested.matchExpectation()) {
                    return seatingOptionSuggested;
                }
            }
        }

        return new SeatingOptionNotAvailable(partyRequested, pricingCategory);
    }

    public Row allocate(SeatingPlace seatingPlace) {
        List<SeatingPlace> newVersionOfSeats = new ArrayList<>();

        for (SeatingPlace currentSeat : seatingPlaces) {
            if (currentSeat.sameSeatingPlace(seatingPlace)) {
                newVersionOfSeats.add(new SeatingPlace(seatingPlace.rowName(), seatingPlace.number(), seatingPlace.pricingCategory(), SeatingPlaceAvailability.Allocated));
            } else {
                newVersionOfSeats.add(currentSeat);
            }
        }

        return new Row(seatingPlace.rowName(), newVersionOfSeats);
    }

}
