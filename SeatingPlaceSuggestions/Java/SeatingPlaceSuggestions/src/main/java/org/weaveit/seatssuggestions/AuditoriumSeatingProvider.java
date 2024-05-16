package org.weaveit.seatssuggestions;

import org.weaveit.externaldependencies.auditoriumlayoutrepository.AuditoriumDto;
import org.weaveit.externaldependencies.auditoriumlayoutrepository.AuditoriumLayoutRepository;
import org.weaveit.externaldependencies.auditoriumlayoutrepository.SeatDto;
import org.weaveit.externaldependencies.reservationsprovider.ReservationsProvider;
import org.weaveit.externaldependencies.reservationsprovider.ReservedSeatsDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuditoriumSeatingProvider {

    private final ReservationsProvider reservedSeatsRepository;
    private final AuditoriumLayoutRepository auditoriumLayoutRepository;


    public AuditoriumSeatingProvider(AuditoriumLayoutRepository auditoriumLayoutRepository, ReservationsProvider reservationsProvider) {
        this.auditoriumLayoutRepository = auditoriumLayoutRepository;
        this.reservedSeatsRepository = reservationsProvider;
    }

    public AuditoriumSeating getAuditoriumSeating(String showId) {
        return adapt(auditoriumLayoutRepository.getAuditoriumSeatingFor(showId),
                reservedSeatsRepository.getReservedSeats(showId));

    }

    private AuditoriumSeating adapt(AuditoriumDto auditoriumDto, ReservedSeatsDto reservedSeatsDto) {

        Map<String, Row> rows = new HashMap<>();


        for (Map.Entry<String, List<SeatDto>> rowDto : auditoriumDto.rows().entrySet()) {
            List<SeatingPlaces> seats = new ArrayList<>();

            rowDto.getValue().forEach(seatDto -> {
                String rowName = rowDto.getKey();
                int number = extractNumber(seatDto.name());
                PricingCategory pricingCategory = convertCategory(seatDto.category());

                boolean isReserved = reservedSeatsDto.reservedSeats().contains(seatDto.name());

                seats.add(new SeatingPlaces(rowName, number, pricingCategory, isReserved ? SeatAvailability.Reserved : SeatAvailability.Available));
            });

            rows.put(rowDto.getKey(), new Row(rowDto.getKey(), seats));
        }

        return new AuditoriumSeating(rows);
    }

    private static PricingCategory convertCategory(int seatDtoCategory) {
        return PricingCategory.valueOf(seatDtoCategory);
    }

    private static int extractNumber(String name) {
        return Integer.parseUnsignedInt(name.substring(1));
    }

}
