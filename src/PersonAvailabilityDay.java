public class PersonAvailabilityDay extends Day {
    boolean availableMorning = false;
    boolean availableMiddle = false;
    boolean availableEvening = false;

    PersonAvailabilityDay(DayName dayToSet) {
        super(dayToSet);
    }

    public boolean GetAvailability(Shift shiftToCheck) {
        return switch (shiftToCheck) {
            case MORNING -> availableMorning;
            case MIDDLE -> availableMiddle;
            case EVENING -> availableEvening;
            default -> false;
        };
    }

    public void SetAvailability(Shift shiftToSet, boolean valueToSet) {
        switch (shiftToSet) {
            case MORNING -> availableMorning = valueToSet;
            case MIDDLE -> availableMiddle = valueToSet;
            case EVENING -> availableEvening = valueToSet;
        }
    }
}
