package org.weaveit.seatssuggestions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    @Test
    void Be_a_Value_Type() {
        SeatingPlace firstInstance = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        SeatingPlace secondInstance = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);

        // Two different instances with same values should be equals
        assertEquals(firstInstance, secondInstance, "Instances with the same values should be equal");

        // Should not mutate existing instance
        secondInstance.allocate();
        assertEquals(firstInstance, secondInstance, "Instance should not mutate existing instance");
    }
}
