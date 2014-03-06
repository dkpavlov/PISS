package fmi.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ThesisReview extends BaseEntity {

    @Column
    private Double theoretical;

    @Column
    private Double ownIdeas;

    @Column
    private Double execution;

    @Column
    private Double style;

    @Column
    private Double architecture;

    @Column
    private Double functionality;

    @Column
    private Double reliability;

    @Column
    private Double documentation;

    @Column
    private Double description;

    @Column
    private Double presentation;

    @Column
    private Double interpretation;

    @Column
    private String summary;

    @Column
    private String questions;

    @Column
    private String conclusion;

    public Double getTheoretical() {
        return theoretical;
    }

    public void setTheoretical(Double theoretical) {
        this.theoretical = theoretical;
    }

    public Double getOwnIdeas() {
        return ownIdeas;
    }

    public void setOwnIdeas(Double ownIdeas) {
        this.ownIdeas = ownIdeas;
    }

    public Double getExecution() {
        return execution;
    }

    public void setExecution(Double execution) {
        this.execution = execution;
    }

    public Double getStyle() {
        return style;
    }

    public void setStyle(Double style) {
        this.style = style;
    }

    public Double getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Double architecture) {
        this.architecture = architecture;
    }

    public Double getFunctionality() {
        return functionality;
    }

    public void setFunctionality(Double functionality) {
        this.functionality = functionality;
    }

    public Double getReliability() {
        return reliability;
    }

    public void setReliability(Double reliability) {
        this.reliability = reliability;
    }

    public Double getDocumentation() {
        return documentation;
    }

    public void setDocumentation(Double documentation) {
        this.documentation = documentation;
    }

    public Double getDescription() {
        return description;
    }

    public void setDescription(Double description) {
        this.description = description;
    }

    public Double getPresentation() {
        return presentation;
    }

    public void setPresentation(Double presentation) {
        this.presentation = presentation;
    }

    public Double getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(Double interpretation) {
        this.interpretation = interpretation;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
