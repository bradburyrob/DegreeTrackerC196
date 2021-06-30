package C196.net;


import java.util.Date;

public class TermModel {

    private int id;
    private String name;
    private Date startTerm;
    private Date endTerm;


    public TermModel(int id, String name, Date startTerm, Date endTerm) {
        this.id = id;
        this.name = name;
        this.startTerm = startTerm;
        this.endTerm = endTerm;
    }

    public TermModel(String name, Date startTerm, Date endTerm) {
        this.name = name;
        this.startTerm = startTerm;
        this.endTerm = endTerm;
    }

    ///Print Method

    @Override
    public String toString() {
        return  name + "\n" +
                "Start of Term  =  " + startTerm + "\n" +
                "End of Term    =  " + endTerm
                ;
    }
//------------

    public TermModel() {
    }

    public TermModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTerm(Date startTerm) {
        this.startTerm = startTerm;
    }

    public void setEndTerm(Date endTerm) {
        this.endTerm = endTerm;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartTerm() {
        return startTerm;
    }

    public Date getEndTerm() {
        return endTerm;
    }


}
