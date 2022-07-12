package com.example.e_book;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class cart_item_model
{
    String pic,name,mrp,docid;
    cart_item_model()
    {

    }
    public cart_item_model(String name, String mrp, String pic, String docid) {
        this.name = name;
        this.mrp = mrp;
        this.pic = pic;
        this.docid = docid;
    }

    public String getpic() {
        return pic;
    }

    public void setpic(String pic) {
        this.pic = pic;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getmrp() {
        return mrp;
    }

    public void setmrp(String mrp) {
        this.mrp = mrp;
    }
}

