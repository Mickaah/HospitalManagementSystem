/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem.logic;
public class doctors {
    private String NAME;
    private String SPEC;
    private String USERNAME;
    private String PASSWORD;

    public doctors(String NAME, String SPEC, String USERNAME, String PASSWORD){
        this.NAME = NAME;
        this.SPEC = SPEC;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public String getName(){
        return NAME;
    }
    public String getSpec(){
        return SPEC;
    }
    public String getUser(){
        return USERNAME;
    }
    public String getPass(){
        return PASSWORD;
    }
}

