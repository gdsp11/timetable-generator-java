public class WorkDay extends Day {
    private Person morningEmployee;
    private Person middleEmployee;
    private Person eveningEmployee;

    WorkDay(DayName dayToSet) {
        super(dayToSet);
    }

    public void SetEmployeeForShift(Person personToSet, Shift shiftToSet) {
        switch (shiftToSet) {
            case MORNING -> morningEmployee = personToSet;
            case MIDDLE -> middleEmployee = personToSet;
            case EVENING -> eveningEmployee = personToSet;
        }
    }

    public Person GetEmployeeForShift(Shift shiftToGet) {
        return switch (shiftToGet) {
            case MORNING -> morningEmployee;
            case MIDDLE -> middleEmployee;
            case EVENING -> eveningEmployee;
            default -> null;
        };
    }
}
