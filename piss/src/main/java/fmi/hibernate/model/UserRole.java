package fmi.hibernate.model;

public enum UserRole {
    ADMINISTRATOR("Администратор"),
    STUDENT("Студенти"),
    TEACHER("Преподавател"),
    PHD("Докторант");

    private final String displayValue;

    private UserRole(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}
