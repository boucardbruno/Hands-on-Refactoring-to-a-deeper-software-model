package org.weaveit.seatssuggestions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class SeatTest {

    @Test
    void Be_a_Value_Type() {
        SeatingPlaces firstInstance = new SeatingPlaces("A", 1, PricingCategory.Second, SeatAvailability.Available);
        SeatingPlaces secondInstance = new SeatingPlaces("A", 1, PricingCategory.Second, SeatAvailability.Available);

        // Two different instances with same values should be equals
        Assertions.assertEquals(firstInstance, secondInstance, "Instances with the same values should be equal");

        // Should not mutate existing instance
        secondInstance.allocate();
        Assertions.assertNotEquals(firstInstance, secondInstance, "Instance should not mutate existing instance");
    }
}
