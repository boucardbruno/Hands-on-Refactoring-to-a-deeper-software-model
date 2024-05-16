package org.weaveit.seatssuggestions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

//    *** Test for Second Lab ***
//    @Test
//    void offerSeatingPlacesFromTheMiddleOfTheRowWhenTheRowSizeIsEvenAndPartySizeIsGreaterThanOne() {
//        int partySize = 2;
//
//        SeatingPlace a1 = new SeatingPlace("A", 1, PricingCategory.Second, SeatingPlaceAvailability.Available);
//        SeatingPlace a2 = new SeatingPlace("A", 2, PricingCategory.Second, SeatingPlaceAvailability.Available);
//        SeatingPlace a3 = new SeatingPlace("A", 3, PricingCategory.First, SeatingPlaceAvailability.Available);
//        SeatingPlace a4 = new SeatingPlace("A", 4, PricingCategory.First, SeatingPlaceAvailability.Available);
//        SeatingPlace a5 = new SeatingPlace("A", 5, PricingCategory.First, SeatingPlaceAvailability.Available);
//        SeatingPlace a6 = new SeatingPlace("A", 6, PricingCategory.First, SeatingPlaceAvailability.Available);
//        SeatingPlace a7 = new SeatingPlace("A", 7, PricingCategory.First, SeatingPlaceAvailability.Available);
//        SeatingPlace a8 = new SeatingPlace("A", 8, PricingCategory.First, SeatingPlaceAvailability.Available);
//        SeatingPlace a9 = new SeatingPlace("A", 9, PricingCategory.Second, SeatingPlaceAvailability.Available);
//        SeatingPlace a10 = new SeatingPlace("A", 10, PricingCategory.Second, SeatingPlaceAvailability.Available);
//
//        Row row = new Row("A", Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));
//
//        List<SeatingPlace> seatingPlaces = offerSeatsNearerTheMiddleOfTheRow(row, PricingCategory.Mixed)
//                .stream()
//                .limit(partySize)
//                .collect(Collectors.toList());
//
//        assertEquals(Arrays.asList(a5, a6), seatingPlaces);
//    }
//
//    private List<SeatingPlace> offerSeatsNearerTheMiddleOfTheRow(Row row, PricingCategory pricingCategory) {
//        // Implement your method logic here
//        return new ArrayList<>();
//    }

}
