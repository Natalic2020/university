package ua.com.foxminded.model.enums;

public enum DayOfWeek {
    MONDAY, TUESDAY, WENDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    
    public String toString() {
        switch (this) {
        case MONDAY:
            return "Monday";
        case TUESDAY:
            return "Tuesday";    
        case WENDNESDAY:
            return "Wendnesday";
        case THURSDAY:
            return "Thursday";
        case FRIDAY:
            return "Friday";
        case SATURDAY:
            return "Saturday";
        case SUNDAY:
            return "Sunday";
        default:
            return "";
        }    
    } 
}
