package com.example.homeworktask2infs;



import com.google.gson.annotations.SerializedName;

public class FoodItem {
    @SerializedName("id")
    private Integer menuId;

    @SerializedName("image")
    private String menuImage;

    @SerializedName("name")
    private String menuName;

    @SerializedName("price")
    private Double menuPrice;

    @SerializedName("description")
    private String menuDescription;

    public FoodItem(Integer id, String image, String name, Double price, String description) {
        menuId = id;
        menuImage = image;
        menuName = name;
        menuPrice = price;
        menuDescription = description;
    }

    public Integer getMenuId() {
        return menuId; }

    public String getMenuImage() {

        return menuImage;
    }

    public String getMenuName() {

        return menuName;
    }

    public Double getMenuPrice() {

        return menuPrice;
    }

    public String getMenuDescription() {

        return menuDescription;
    }
}
