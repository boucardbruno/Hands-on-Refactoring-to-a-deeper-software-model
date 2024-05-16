package org.weaveit.seatssuggestions;

import java.util.List;
import java.util.stream.Collectors;

public class SuggestionMade {

    private List<SeatingPlaces> suggestedSeats;
    private int partyRequested;
    private PricingCategory pricingCategory;

    public SuggestionMade(List<SeatingPlaces> suggestedSeats, int partyRequested, PricingCategory pricingCategory) {
        this.suggestedSeats = List.copyOf(suggestedSeats);
        this.partyRequested = partyRequested;
        this.pricingCategory = pricingCategory;
    }

    public List<String> seatNames() {
        return suggestedSeats.stream().map(SeatingPlaces::toString).collect(Collectors.toList());
    }

    public boolean matchExpectation() {
        return suggestedSeats.size() == partyRequested;
    }

    public PricingCategory pricingCategory() {
        return pricingCategory;
    }
}
