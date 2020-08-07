package ua.com.foxminded.enums;

public enum Degree {
    
    PROFESSOR, DOCENT, DOCTOR;
    
    public String toString() {
        switch (this) {
        case PROFESSOR:
            return "Professor";
        case DOCENT:
            return "Docent";    
        case DOCTOR:
            return "Doctor";
        default:
            return "";
        }    
    } 
}
