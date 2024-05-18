using System.Collections.Generic;
using System.Linq;
using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class RowShould
{
    [Test]
    public void Be_a_Value_Type()
    {
        var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);

        // Two different instances with same values should be equals
        var rowFirstInstance = new Row("A", new List<SeatingPlace> { a1, a2 });
        var rowSecondInstance = new Row("A", new List<SeatingPlace> { a1, a2 });
        Check.That(rowSecondInstance).IsEqualTo(rowFirstInstance);

        // Should not mutate existing instance 
        var a3 = new SeatingPlace("A", 3, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var newRowWithNewSeatAdded = rowSecondInstance.AddSeatingPlace(a3);

        Check.That(rowSecondInstance).IsEqualTo(rowFirstInstance);
        Check.That(newRowWithNewSeatAdded).IsNotEqualTo(rowFirstInstance);
    }

    [Test]
    [Ignore("Lab2")]
    public void
       Suggest_places_options_closer_to_the_center_of_row_when_x_happened()
    {
        var partySize = 2;

        var a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.Reserved);
        var a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.Available);
        var a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.Reserved);
        var a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var a10 = new SeatingPlace("A", 10, PricingCategory.Second, SeatingPlaceAvailability.Available);

        var row = new Row("A", new List<SeatingPlace> { a1, a2, a3, a4, a5, a6, a7, a8, a9, a10 });
      
        var seatingPlaces = SuggestPlacesOptionsCloserTheCenterOfARow(row, PricingCategory.Mixed)
            .Take(partySize);

        Check.That(seatingPlaces)
            .ContainsExactly(a5, a6);
    }
    
    // Deep Modeling: probing the code should start with a prototype
    private IEnumerable<SeatingPlace> SuggestPlacesOptionsCloserTheCenterOfARow(Row row, PricingCategory pricingCategory)
    {
        // Implement your method logic here
        return new List<SeatingPlace>();
    }
  
}
