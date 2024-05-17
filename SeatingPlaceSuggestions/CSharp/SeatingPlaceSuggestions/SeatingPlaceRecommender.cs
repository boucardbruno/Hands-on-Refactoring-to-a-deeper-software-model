using System.Collections.Generic;

namespace SeatsSuggestions;

public class SeatingPlaceRecommender(AuditoriumSeatingAdapter auditoriumSeatingAdapter)
{
    private const int NumberOfSuggestionsPerPricingCategory = 3;

    public SuggestionsMade MakeSuggestions(string showId, int partyRequested)
    {
        var auditoriumSeating = auditoriumSeatingAdapter.FindByShowId(showId);

        var suggestionsMade = new SuggestionsMade(showId, partyRequested);

        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.First));
        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.Second));
        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.Third));
        suggestionsMade.Add(GiveMeSuggestionsFor(auditoriumSeating, partyRequested, PricingCategory.Mixed));

        if (suggestionsMade.MatchExpectations()) return suggestionsMade;

        return new SuggestionNotAvailable(showId, partyRequested);
    }

    private static IEnumerable<SuggestionMade> GiveMeSuggestionsFor(
        AuditoriumSeating auditoriumSeating,
        int partyRequested,
        PricingCategory pricingCategory)
    {
        var suggestionRequest = new SuggestionRequest(partyRequested, pricingCategory);
        var foundedSuggestions = new List<SuggestionMade>();

        for (var i = 0; i < NumberOfSuggestionsPerPricingCategory; i++)
        {
            var seatOptionsSuggested = auditoriumSeating.SuggestSeatingOptionFor(suggestionRequest);

            if (seatOptionsSuggested.MatchExpectation())
            {
                // We get the new version of the Auditorium after the allocation
                auditoriumSeating = auditoriumSeating.Allocate(seatOptionsSuggested);

                foundedSuggestions.Add(new SuggestionMade(seatOptionsSuggested));
            }
        }

        return foundedSuggestions;
    }
}