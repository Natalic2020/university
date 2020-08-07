package ua.com.foxminded.enums;

public enum Department {
    ARCHITECTURE, CHEMISTRY, INFORMATICS, MECHANICAL_ENGINEERING, MATHEMATICS;
    
    public String toString() {
        switch (this) {
        case ARCHITECTURE:
            return "Department of Architecture";
        case CHEMISTRY:
            return "Department of Chemistry";    
        case INFORMATICS:
            return "Department of Informatics";
        case MECHANICAL_ENGINEERING:
            return "Department of Mechanical Engineering";
        case MATHEMATICS:
            return "Department of Mathematics";
        default:
            return "";
        }    
    } 
}
