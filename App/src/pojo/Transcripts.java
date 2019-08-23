/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author nguye
 */
public class Transcripts implements java.io.Serializable{
    private Students studentId;
    private Schedules scheduleId;
    private float midtermScores;
    private float finalScores;
    private float otherScores;
    private float totalScores;
    private Byte status;

    public Transcripts() {
    }

    public Transcripts(float midtermScores, float finalScores, float otherScores, float totalScores) {
        this.midtermScores = midtermScores;
        this.finalScores = finalScores;
        this.otherScores = otherScores;
        this.totalScores = totalScores;
    }
    
    public Transcripts(Students studentId, Schedules scheduleId, float midtermScores, float finalScores, float otherScores, float totalScores, Byte status) {
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.midtermScores = midtermScores;
        this.finalScores = finalScores;
        this.otherScores = otherScores;
        this.totalScores = totalScores;
        this.status = status;
    }

    public Students getStudentId() {
        return studentId;
    }

    public void setStudentId(Students studentId) {
        this.studentId = studentId;
    }

    public Schedules getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Schedules scheduleId) {
        this.scheduleId = scheduleId;
    }

    public float getMidtermScores() {
        return midtermScores;
    }

    public void setMidtermScores(float midtermScores) {
        this.midtermScores = midtermScores;
    }

    public float getFinalScores() {
        return finalScores;
    }

    public void setFinalScores(float finalScores) {
        this.finalScores = finalScores;
    }

    public float getOtherScores() {
        return otherScores;
    }

    public void setOtherScores(float otherScores) {
        this.otherScores = otherScores;
    }

    public float getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(float totalScores) {
        this.totalScores = totalScores;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    
    
}
