package com.example.e_book;

public class search_item_model
    {
        String mrp,name,pic;
        search_item_model()
        {

        }
        public search_item_model(String mrp, String name, String pic) {
            this.name = name;
            this.mrp = mrp;
            this.pic = pic;
        }

        public String getpic() {
            return pic;
        }

        public void setpic(String pic) {
            this.pic = pic;
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
