package ui;

public class bid {
String email;
int bid;
String name;
String messagereg;
 bid (){}


    public bid(String email, int bid, String name, String messagereg) {
        this.email = email;
        this.bid = bid;
        this.name = name;
        this.messagereg = messagereg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
//    public String getBid() {
//        return bid;
//    }
//
//    public void setBid(String bid) {
//        this.bid = bid;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessagereg() {
        return messagereg;
    }

    public void setMessagereg(String messagereg) {
        this.messagereg = messagereg;
    }
}
