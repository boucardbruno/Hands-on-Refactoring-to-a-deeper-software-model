package org.weaveit.seatssuggestions;

import java.util.HashMap;
import java.util.Map;

public class AuditoriumSeating {

    private Map<String, Row> rows;

    public AuditoriumSeating(Map<String, Row> rows) {
        this.rows = rows;
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

    public AuditoriumSeating allocate(SeatingOptionSuggested seatingOptionSuggested) {
        // Update the seat references in the Auditorium
        Map<String, Row> newVersionOfRows = new HashMap<>(rows);

        for (SeatingPlace updatedSeat : seatingOptionSuggested.seats()) {
            Row formerRow = newVersionOfRows.get(updatedSeat.rowName());
            Row newVersionOfRow = formerRow.allocate(updatedSeat);
            newVersionOfRows.put(updatedSeat.rowName(), newVersionOfRow);
        }

        rows = newVersionOfRows;

        return this;
    }

}
