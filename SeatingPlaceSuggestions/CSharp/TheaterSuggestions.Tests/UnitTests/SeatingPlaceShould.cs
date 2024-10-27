using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class SeatingPlaceShould
{
    [Test]
    public void Be_a_Value_Type()
    {
        var firstInstance = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        var secondInstance = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);

        // Two different instances with same values should be equals
        Check.That(secondInstance).IsEqualTo(firstInstance);

        // Should not mutate existing instance 
        var newSeatingPlace = secondInstance.Allocate();
        Check.That(newSeatingPlace).IsNotEqualTo(secondInstance);
        Check.That(secondInstance).IsEqualTo(firstInstance);
    }
}