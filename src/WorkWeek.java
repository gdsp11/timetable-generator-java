public class WorkWeek {
    private WorkDay[] weekDays = new WorkDay[7];

    //default constructor which creates the array of days
    WorkWeek() {
        for (int i = 0; i < 7; i++) {
            weekDays[i] = new WorkDay(DayName.DayFromInt(i));
        }
    }

    public void SetEmployeeForDayAndShift(DayName day, Shift shift, Person personToUse) {
        weekDays[DayName.IntFromDay(day)].SetEmployeeForShift(personToUse, shift);
    }
    public Person GetEmployeeForDayAndShift(DayName day, Shift shift) {
        return weekDays[DayName.IntFromDay(day)].GetEmployeeForShift(shift);
    }
}
