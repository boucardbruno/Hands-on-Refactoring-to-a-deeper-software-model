package org.weaveit.seatssuggestions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

class RowTest {

    @Test
    void be_a_Value_Type() {
        SeatingPlaces a1 = new SeatingPlaces("A", 1, PricingCategory.Second, SeatAvailability.Available);
        SeatingPlaces a2 = new SeatingPlaces("A", 2, PricingCategory.Second, SeatAvailability.Available);

        // Two different instances with same values should be equals
        Row rowFirstInstance = new Row("A", Arrays.asList(a1, a2));
        Row rowSecondInstance = new Row("A", Arrays.asList(a1, a2));
        Assertions.assertEquals(rowFirstInstance, rowSecondInstance, "Instances with the same values should be equal");
    }
}
