package org.weaveit.seatssuggestions;

import java.util.Map;

public class AuditoriumSeating {

    private final Map<String, Row> rows;

    public AuditoriumSeating(Map<String, Row> rows) {
        this.rows = Map.copyOf(rows);
    }

    public SeatingOptionSuggested suggestSeatingOptionFor(int partyRequested, PricingCategory pricingCategory) {
        for (Row row : rows.values()) {
            SeatingOptionSuggested seatingOptionSuggested = row.suggestSeatingOption(partyRequested, pricingCategory);

            if (seatingOptionSuggested.matchExpectation()) {
                // Cool, we mark the seat as Allocated (that we turns into a SuggestionMode)
                return seatingOptionSuggested;
            }
        }

        return new SeatingOptionNotAvailable(partyRequested, pricingCategory);
    }

    public Map<String, Row> rows() {
        return this.rows;
    }
}
