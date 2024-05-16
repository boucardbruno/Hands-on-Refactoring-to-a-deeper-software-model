package org.weaveit.seatssuggestionsacceptancetests;

import org.weaveit.externaldependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import org.weaveit.externaldependencies.reservationsprovider.ReservationsProvider;
import org.weaveit.seatssuggestions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class SeatingPlaceSuggestingMakerTest {


    @Test
    void should_suggest_one_seat_when_Auditorium_contains_one_available_seat_only() throws IOException {
        // Ford Auditorium-1
        //
        //       1   2   3   4   5   6   7   8   9  10
        //  A : (2) (2)  1  (1) (1) (1) (1) (1) (2) (2)
        //  B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
        final String showId = "1";
        final int partyRequested = 1;

        AuditoriumSeatingProvider auditoriumLayoutAdapter =
                new AuditoriumSeatingProvider(new AuditoriumLayoutRepository(), new ReservationsProvider());

        SeatingPlaceRecommender seatingPlaceRecommender = new SeatingPlaceRecommender(auditoriumLayoutAdapter);

        SuggestionsMade suggestionsMade = seatingPlaceRecommender.makeSuggestion(showId, partyRequested);

        assertIterableEquals(
                List.of("A3"),
                suggestionsMade.seatNames(PricingCategory.First),
                "Suggestions should contain exactly one available seat"
        );
    }

    @Test
    void should_return_SeatsNotAvailable_when_Auditorium_has_all_its_seats_already_reserved() throws IOException {
        // Madison Auditorium-5
        //
        //      1   2   3   4   5   6   7   8   9  10
        // A : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
        // B : (2) (2) (1) (1) (1) (1) (1) (1) (2) (2)
        final String showId = "5";
        final int partyRequested = 1;

        AuditoriumSeatingProvider auditoriumLayoutAdapter =
                new AuditoriumSeatingProvider(new AuditoriumLayoutRepository(), new ReservationsProvider());

        SeatingPlaceRecommender seatingPlaceRecommender = new SeatingPlaceRecommender(auditoriumLayoutAdapter);

        SuggestionsMade suggestionsMade = seatingPlaceRecommender.makeSuggestion(showId, partyRequested);

        assertEquals(partyRequested, suggestionsMade.partyRequested(), "Party requested should match");
        assertEquals(showId, suggestionsMade.showId(), "Show ID should match");
        assertInstanceOf(SuggestionNotAvailable.class, suggestionsMade, "Suggestions made should be an instance of SuggestionNotAvailable");
    }

    @Test
    void should_offer_several_suggestions_ie_1_per_PricingCategory_and_other_one_without_category_affinity() throws IOException {
        // New Amsterdam-18
        //
        //     1   2   3   4   5   6   7   8   9  10
        //  A: 2   2   1   1   1   1   1   1   2   2
        //  B: 2   2   1   1   1   1   1   1   2   2
        //  C: 2   2   2   2   2   2   2   2   2   2
        //  D: 2   2   2   2   2   2   2   2   2   2
        //  E: 3   3   3   3   3   3   3   3   3   3
        //  F: 3   3   3   3   3   3   3   3   3   3
        final String showId = "18";
        final int partyRequested = 1;

        AuditoriumSeatingProvider auditoriumLayoutAdapter =
                new AuditoriumSeatingProvider(new AuditoriumLayoutRepository(), new ReservationsProvider());

        SeatingPlaceRecommender seatingPlaceRecommender = new SeatingPlaceRecommender(auditoriumLayoutAdapter);

        SuggestionsMade suggestionsMade = seatingPlaceRecommender.makeSuggestion(showId, partyRequested);

//        assertIterableEquals(
//                List.of("A3", "A4", "A5"),
//                suggestionsMade.seatNames(PricingCategory.First),
//                "First pricing category should match"
//        );

        assertThat(suggestionsMade.seatNames(PricingCategory.First)).containsExactly("A3","A4","A5","A6");

//        assertIterableEquals(
//               List.of("A1", "A2", "A9"),
//                suggestionsMade.seatNames(PricingCategory.Second),
//                "Second pricing category should match"
//        );

        assertThat(suggestionsMade.seatNames(PricingCategory.Second)).containsExactly("A1", "A2", "A9");

//        assertIterableEquals(
//                List.of("E1", "E2", "E3"),
//                suggestionsMade.seatNames(PricingCategory.Third),
//                "Third pricing category should match"
//        );

        assertThat(suggestionsMade.seatNames(PricingCategory.Third)).containsExactly("E1", "E2", "E3");


        // BUG!!! => return A6, A7, A8 instead of the expected A1, A2, A3
//        assertIterableEquals(
//                List.of("A1", "A2", "A3"),
//                suggestionsMade.seatNames(PricingCategory.Mixed),
//                "Mixed pricing category should match"
//        );

        assertThat(suggestionsMade.seatNames(PricingCategory.Mixed)).containsExactly("A1", "A2", "A3");

    }
}
