using System.Collections.Generic;
using Value;

namespace SeatsSuggestions;

public class SuggestionRequest(int partyRequested, PricingCategory pricingCategory) : ValueType<SuggestionRequest>
{
    public int PartyRequested { get; } = partyRequested;
    public PricingCategory PricingCategory { get; } = pricingCategory;

    protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
    {
        return new object[] { PartyRequested, PricingCategory };
    }
}