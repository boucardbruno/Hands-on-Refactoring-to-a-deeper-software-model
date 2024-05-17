using System.Collections.Generic;
using Value;

namespace SeatsSuggestions;

public class SeatingPlace(
    string rowName,
    int number,
    PricingCategory pricingCategory,
    SeatingPlaceAvailability seatingPlaceAvailability,
    int distanceFromTheMiddleOfTheRow = -1)
    : ValueType<SeatingPlace>
{
    public string RowName { get; } = rowName;
    public int Number { get; } = number;
    public PricingCategory PricingCategory { get; } = pricingCategory;
    public SeatingPlaceAvailability SeatingPlaceAvailability { get; } = seatingPlaceAvailability;
    public int DistanceFromTheMiddleOfTheRow { get; } = distanceFromTheMiddleOfTheRow;

    public bool IsAvailable()
    {
        return SeatingPlaceAvailability == SeatingPlaceAvailability.Available;
    }

    public override string ToString()
    {
        return $"{RowName}{Number}";
    }

    public SeatingPlace Allocate()
    {
        if (SeatingPlaceAvailability == SeatingPlaceAvailability.Available)
            return new SeatingPlace(RowName, Number, PricingCategory, SeatingPlaceAvailability.Allocated);

        return this;
    }

    public bool SameSeatingPlace(SeatingPlace seatingPlace)
    {
        return RowName == seatingPlace.RowName && Number == seatingPlace.Number;
    }

    protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
    {
        return new object[] { RowName, Number, PricingCategory, SeatingPlaceAvailability };
    }

    public bool MatchCategory(PricingCategory pricingCategory)
    {
        if (PricingCategory.Mixed == pricingCategory)
        {
            return true;
        }
        return this.PricingCategory == pricingCategory;
    }
}