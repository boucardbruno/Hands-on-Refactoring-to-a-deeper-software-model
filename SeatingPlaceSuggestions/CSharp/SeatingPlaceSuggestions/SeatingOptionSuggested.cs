using System.Collections.Generic;

namespace SeatsSuggestions;

public class SeatingOptionSuggested(SuggestionRequest suggestionRequest)
{
    public PricingCategory PricingCategory { get; } = suggestionRequest.PricingCategory;
    public List<SeatingPlace> Seats { get; } = new();
    public int PartyRequested { get; } = suggestionRequest.PartyRequested;

    public void AddSeatingPlace(SeatingPlace seatingPlace)
    {
        Seats.Add(seatingPlace);
    }

    public bool MatchExpectation()
    {
        return Seats.Count == PartyRequested;
    }
}