package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//bilinmeyen bir değer varsa görmezden gel bu durum databaseden gelen taskte karsılıgı olmayan ıd için filan gerekli
public class ReqresPojo {

    private String name; //private atadık direk ulasamayız get ve setle ulasırız saklama islemi encapsilation

    private String job;

//    public ReqresPojo() {//bos const. de-serilizationa yarar
//    }
//
//    public ReqresPojo(String name, String job) {
//        this.name = name;
//        this.job = job;
//    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "ReqresPojo{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
