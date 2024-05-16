package org.weaveit.seatssuggestions;

import java.util.List;
import java.util.stream.Collectors;

public class SuggestionMade {

    private List<SeatingPlace> suggestedSeats;
    private int partyRequested;
    private PricingCategory pricingCategory;

    public SuggestionMade(SeatingOptionSuggested seatingOptionSuggested) {
        this.suggestedSeats = List.copyOf(seatingOptionSuggested.seats());
        this.partyRequested = seatingOptionSuggested.partyRequested();
        this.pricingCategory = seatingOptionSuggested.pricingCategory();
    }

    public List<String> seatNames() {
        return suggestedSeats.stream().map(SeatingPlace::toString).collect(Collectors.toList());
    }

    public boolean matchExpectation() {
        return suggestedSeats.size() == partyRequested;
    }

    public PricingCategory pricingCategory() {
        return pricingCategory;
    }
}
