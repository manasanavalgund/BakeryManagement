package com.example.manasa.bakery;

/**
 * Created by manasa on 12/9/15.
 */
public class MenuData {


    private int images;
    private String menu_items;

    public int getImages() {
        return images;
    }

    public MenuData(int images,String menu_items)
    {
        this.setMenu_items(menu_items);
        this.setImages(images);
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(String menu_items) {
        this.menu_items = menu_items;
    }
}
