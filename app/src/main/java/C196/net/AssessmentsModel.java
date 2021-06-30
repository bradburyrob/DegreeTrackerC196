package C196.net;

import java.util.Date;

public class AssessmentsModel {

    private int assessmentID;
    private String assessmentName;
    private String noteName;
    private String assessmentType;
    private Date assessmentStart;
    private Date assessmentEnd;
    private int courseID;


    public AssessmentsModel(int assessmentID, String assessmentName, String assessmentType, String noteName, Date assessmentStart, Date assessmentEnd, int courseID) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.noteName = noteName;
        this.assessmentType = assessmentType;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.courseID = courseID;
    }

    public AssessmentsModel() {
    }

    public AssessmentsModel(int assessmentID, String assessmentName, String noteName, Date assessmentStart, Date assessmentEnd, int courseID) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.noteName = noteName;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.courseID = courseID;
    }

    public AssessmentsModel(int assessmentID, String assessmentName, int courseID) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.courseID = courseID;
    }

    public AssessmentsModel(int assessmentID, String assessmentName) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
    }

    @Override
    public String toString() {
        return assessmentName;
           /*     "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName + '\'' +
                ", noteName='" + noteName + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                ", assessmentStart=" + assessmentStart +
                ", assessmentEnd=" + assessmentEnd +
                ", courseID=" + courseID +
                '}';

            */
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public Date getAssessmentStart() {
        return assessmentStart;
    }

    public Date getAssessmentEnd() {
        return assessmentEnd;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public void setAssessmentStart(Date assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public void setAssessmentEnd(Date assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
