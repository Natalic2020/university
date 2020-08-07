package ua.com.foxminded.enums;

public enum Specialty {
    
    INFORMATICS, ECONOMY, ART, JURIST;
    
    public String toString() {
        switch (this) {
        case INFORMATICS:
            return "Informatics";
        case ECONOMY:
            return "Economy";    
        case ART:
            return "Art";
        case JURIST:
            return "Jurist";
        default:
            return "";
        }    
    } 
}
