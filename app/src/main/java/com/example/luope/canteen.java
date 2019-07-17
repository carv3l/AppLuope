package com.example.luope;

public class canteen {

    private String cantina_id,cantina_description;

    public canteen(String cantina_id,String cantina_description){


        this.setCantina_id(cantina_id);

    }
    public String getCantina_id(){

        return cantina_id;
    }

    public void setCantina_id(String cantina_id){
        this.cantina_id = cantina_id;
    }
    public String getCantina_description(){

        return cantina_description;
    }
    public void setCantina_description(String cantina_description){

       this.cantina_description = cantina_description;

    }

}
