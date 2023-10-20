package com.example.ucare;

public class putPDF {

    //public String uid;
    public String name;
    public String url;

    public putPDF() {
    }

    public putPDF(/*String uid,*/String name, String url) {
      //  this.uid=uid;
        this.name = name;
        this.url = url;
    }

   /* public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
