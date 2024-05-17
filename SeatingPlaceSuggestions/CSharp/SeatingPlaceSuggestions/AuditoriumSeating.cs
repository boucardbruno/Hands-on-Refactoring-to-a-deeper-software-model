using System.Collections.Generic;
using Value;
using Value.Shared;

namespace SeatsSuggestions;

public class AuditoriumSeating(Dictionary<string, Row> rows) : ValueType<AuditoriumSeating>
{
    public IReadOnlyDictionary<string, Row> Rows => rows;

    public SeatingOptionSuggested SuggestSeatingOptionFor(SuggestionRequest suggestionRequest)
    {
        foreach (var row in rows.Values)
        {
            var seatingOption = row.SuggestSeatingOption(suggestionRequest);

            if (seatingOption.MatchExpectation()) return seatingOption;
        }

        return new SeatingOptionNotAvailable(suggestionRequest);
    }

    public AuditoriumSeating Allocate(SeatingOptionSuggested seatingOptionSuggested)
    {
        // Update the seat references in the Auditorium
        var newVersionOfRows = new Dictionary<string, Row>(rows);

        foreach (var updatedSeat in (IEnumerable<SeatingPlace>)seatingOptionSuggested.Seats)
        {
            var formerRow = newVersionOfRows[updatedSeat.RowName];
            var newVersionOfRow = formerRow.Allocate(updatedSeat);
            newVersionOfRows[updatedSeat.RowName] = newVersionOfRow;
        }

        //rows = newVersionOfRows;

        return  new AuditoriumSeating(newVersionOfRows);
    }

    protected override IEnumerable<object> GetAllAttributesToBeUsedForEquality()
    {
        return new object[] { new DictionaryByValue<string, Row>(rows) };
    }
}