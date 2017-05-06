package model;


import android.graphics.Bitmap;

public class Flower {

    private int flowerId;
    private String name;
    private String photo;
    private String instructions;
    private String price;
    private String category;
    private Bitmap imgBitmap;

    public Flower() {
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFlowerId() {
        return flowerId;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }

    @Override
    public String toString() {
        return flowerId + " " + name + "\nPrice:" + price;
    }
}
