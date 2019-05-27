package cc.stan.example.dgraph4j;

public class Call {
    private Person from;
    private Person to;
    private String datetime;
    private Integer longTime = 0;

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Person getTo() {
        return to;
    }

    public void setTo(Person to) {
        this.to = to;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getLongTime() {
        return longTime;
    }

    public void setLongTime(Integer longTime) {
        this.longTime = longTime;
    }
}
