package ui;

public class asset {
    String name;
    String price;
    String key;
    String date;
    String description;

    public  asset(){}
    public asset(String name, String price, String key, String date, String description) {
        this.name = name;
        this.price = price;
        this.key = key;
        this.date = date;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
