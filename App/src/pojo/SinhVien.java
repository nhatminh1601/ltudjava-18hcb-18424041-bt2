package pojo;
// Generated Aug 14, 2019 11:04:11 PM by Hibernate Tools 4.3.1



/**
 * SinhVien generated by hbm2java
 */
public class SinhVien  implements java.io.Serializable {


     private Integer id;
     private String code;
     private String name;
     private Byte sex;
     private String identityCard;
     private String password;

    public SinhVien() {
    }

    public SinhVien(String code, String name, Byte sex, String identityCard, String password) {
       this.code = code;
       this.name = name;
       this.sex = sex;
       this.identityCard = identityCard;
       this.password = password;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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
    public String getIdentityCard() {
        return this.identityCard;
    }
    
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


