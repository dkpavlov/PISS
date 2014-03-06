package fmi.hibernate.model;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-2
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */
public enum ThesisProposalStatus {
    PENDING("Чакаща"),
    NOT_APPROVED("Неодобренo"),
    CONDITION("Условно одобрно"),
    APPROVED("Оробрено");

    private final String displayValue;

    private ThesisProposalStatus(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}
