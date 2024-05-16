﻿using System.Linq;
using ExternalDependencies.AuditoriumLayoutRepository;
using ExternalDependencies.ReservationsProvider;
using NFluent;
using NUnit.Framework;

namespace SeatsSuggestions.Tests.UnitTests;

[TestFixture]
public class AuditoriumSeatingShould
{
    [Test]
    public void Be_a_Value_Type()
    {
        var auditoriumLayoutAdapter =
            new AuditoriumSeatingAdapter(new AuditoriumLayoutRepository(), new ReservationsProvider());
        var showIdWithoutReservationYet = "18";
        var auditoriumSeatingFirstInstance =
            auditoriumLayoutAdapter.FindByShowId(showIdWithoutReservationYet);
        var auditoriumSeatingSecondInstance =
            auditoriumLayoutAdapter.FindByShowId(showIdWithoutReservationYet);

        // Two different instances with same values should be equals
        Check.That(auditoriumSeatingSecondInstance).IsEqualTo(auditoriumSeatingFirstInstance);

        // Should not mutate existing instance 
        var seat = auditoriumSeatingSecondInstance.Rows.Values.First().SeatingPlaces.First().Allocate();
        Check.That(auditoriumSeatingSecondInstance).IsEqualTo(auditoriumSeatingFirstInstance);
    }
}