package org.weaveit.seatssuggestions;

import org.weaveit.externaldependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import org.weaveit.externaldependencies.reservationsprovider.ReservationsProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

class AuditoriumSeatingTest {

    @Test
    void be_a_Value_Type() throws IOException {
        AuditoriumSeatingProvider auditoriumLayoutAdapter =
                new AuditoriumSeatingProvider(new AuditoriumLayoutRepository(), new ReservationsProvider());
        String showIdWithoutReservationYet = "18";
        AuditoriumSeating auditoriumSeatingFirstInstance =
                auditoriumLayoutAdapter.getAuditoriumSeating(showIdWithoutReservationYet);
        AuditoriumSeating auditoriumSeatingSecondInstance =
                auditoriumLayoutAdapter.getAuditoriumSeating(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        Assertions.assertEquals(auditoriumSeatingFirstInstance, auditoriumSeatingSecondInstance, "Instances with the same values should be equal");

        // Should not mutate existing instance
        auditoriumSeatingSecondInstance.rows().values().iterator().next().seats().iterator().next().allocate();
        Assertions.assertNotEquals(auditoriumSeatingFirstInstance, auditoriumSeatingSecondInstance, "Instance should not mutate existing instance");
    }
}
