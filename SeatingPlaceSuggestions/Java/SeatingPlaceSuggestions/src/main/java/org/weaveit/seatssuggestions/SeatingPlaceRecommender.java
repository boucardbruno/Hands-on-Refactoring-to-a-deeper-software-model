package org.weaveit.seatssuggestions;

import java.util.ArrayList;
import java.util.List;

public class SeatingPlaceRecommender {
    private static final int NUMBER_OF_SUGGESTIONS = 3;
    private final AuditoriumSeatingProvider auditoriumSeatingProvider;

    public SeatingPlaceRecommender(AuditoriumSeatingProvider auditoriumLayoutAdapter) {
        this.auditoriumSeatingProvider = auditoriumLayoutAdapter;
    }

    public SuggestionsMade makeSuggestion(String showId, int partyRequested) {

        AuditoriumSeating auditoriumSeating = auditoriumSeatingProvider.findByShowId(showId);

        SuggestionsMade suggestionsMade = new SuggestionsMade(showId, partyRequested);
        
        suggestionsMade.add(giveMeSuggestionsFor(auditoriumSeating, partyRequested,
                PricingCategory.FIRST));
        suggestionsMade.add(giveMeSuggestionsFor(auditoriumSeating,partyRequested,
                PricingCategory.SECOND));
        suggestionsMade.add(giveMeSuggestionsFor(auditoriumSeating,partyRequested,
                PricingCategory.THIRD));
        suggestionsMade.add(giveMeSuggestionsFor(auditoriumSeating,partyRequested,
                PricingCategory.MIXED));

        if (!suggestionsMade.matchExpectations()) {
            return new SuggestionNotAvailable(showId, partyRequested);
        }

        return suggestionsMade;
    }

    private static List<SuggestionMade> giveMeSuggestionsFor(
            AuditoriumSeating auditoriumSeating, int partyRequested,  PricingCategory pricingCategory) {

        List<SuggestionMade> foundedSuggestions = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SUGGESTIONS; i++) {
            SeatingOptionSuggested seatingOptionSuggested = auditoriumSeating.suggestSeatingOptionFor(partyRequested, pricingCategory);

            if (seatingOptionSuggested.matchExpectation()) {
                auditoriumSeating = auditoriumSeating.allocate(seatingOptionSuggested);
                foundedSuggestions.add(new SuggestionMade(seatingOptionSuggested));
            }
        }

        return List.copyOf(foundedSuggestions);
    }
}
