package org.weaveit.seatssuggestions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class RowTest {

    @Test
    void be_a_Value_Type() {
        SeatingPlace a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
        SeatingPlace a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);

        // Two different instances with same values should be equals
        Row rowFirstInstance = new Row("A", Arrays.asList(a1, a2));
        Row rowSecondInstance = new Row("A", Arrays.asList(a1, a2));
        assertEquals(rowFirstInstance, rowSecondInstance, "Instances with the same values should be equal");

        // Should not mutate existing instance
        SeatingPlace a3 = new SeatingPlace("A", 3, PricingCategory.Second, SeatingPlaceAvailability.Available);
        rowSecondInstance.addSeatingPlace(a3);
        assertEquals(rowFirstInstance, rowSecondInstance, "Instance should not mutate existing instance");

    }
}
