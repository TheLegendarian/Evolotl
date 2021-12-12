package com.example.evolotl;

public class Axolotl {
    private String name;
    //private String color;
    //private Integer lvl;
    private Integer id;

    public Axolotl(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    /*
    public String getColor() {
        return color;
    }

    public Integer getLvl() {
        return lvl;
    }
    */
    public void setName(String name) {
        this.name = name;
    }
    /*
    public void setColor(String color) {
        this.color = color;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
