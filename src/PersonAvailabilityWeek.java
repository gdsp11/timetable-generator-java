public class PersonAvailabilityWeek {
    private PersonAvailabilityDay[] availabilityDays = new PersonAvailabilityDay[7];
    PersonAvailabilityWeek() {
        for (int i = 0; i < 7; i++) {
            availabilityDays[i] = new PersonAvailabilityDay(DayName.DayFromInt(i));
        }
    }

    public void SetAvailability(DayName dayToSet, Shift shiftToSet, boolean valueToSet) {
        availabilityDays[DayName.IntFromDay(dayToSet)].SetAvailability(shiftToSet, valueToSet);
    }
    public boolean GetAvailability(DayName dayToGet, Shift shiftToGet) {
        return availabilityDays[DayName.IntFromDay(dayToGet)].GetAvailability(shiftToGet);
    }
}
