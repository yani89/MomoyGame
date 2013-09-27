package com.golden.momoyfindpatch;

/**
 *
 * @author cyber-blackhat
 */
public class HiScoreDat {
    public String score = "-1";
    public String name = "";
    public String level = "level1";

    public HiScoreDat(){
    }
    public HiScoreDat(int score,int level){
        //String[] levelDesc = new String [] {};
        this.score = String.valueOf(score);
        this.name = "";
        this.level = "level" + level;
    }
    public HiScoreDat(String score,String name,String level){
        this.score = score;
        this.name = name;
        this.level = level;
    }

    

}
