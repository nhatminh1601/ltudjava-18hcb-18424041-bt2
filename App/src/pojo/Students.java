package pojo;
// Generated Aug 17, 2019 10:44:46 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;


/**
 * Students generated by hbm2java
 */
public class Students implements java.io.Serializable {

    private Integer id;
    private Classroom classroom;
    private String code;
    private String name;
    private Byte sex;
    private String indentityCard;
    private String password;
    private Set<Transcripts> transcriptses=new HashSet<Transcripts>(0);

    public Students() {
    }

    public Students(Integer id, Classroom classroom, String code, String name, Byte sex, String indentityCard, String password) {
        this.classroom = classroom;
        this.id = id;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.indentityCard = indentityCard;
        this.password = password;
    }

    public Students(Classroom classroom, String code, String name, Byte sex, String indentityCard, String password) {
        this.classroom = classroom;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.indentityCard = indentityCard;
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Classroom getClassroom() {
        return this.classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return this.sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getIndentityCard() {
        return this.indentityCard;
    }

    public void setIndentityCard(String indentityCard) {
        this.indentityCard = indentityCard;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Transcripts> getTranscriptses() {
        return transcriptses;
    }

    public void setTranscriptses(Set<Transcripts> transcriptses) {
        this.transcriptses = transcriptses;
    }
    
}
