package com.company;

import java.util.Random;

public class Main {

    public static int [] heroesHealth = {270,280,250,260};
    public static int [] heroesDamage = {20,15,25,0};
    public static String [] heroesAttackType = {"Physical","Magical","Kinetic","Medic"};

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static String healing = "";
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()){
            round();
        }

    }

    public static void round(){
        roundNumber++;
        bossDefenceType = changeBossDefence();
        System.out.println("Boss chose: " + changeBossDefence());
        bossHits();
        heroesHit();
        medicTime();
        printStatistics();
    }

    public static boolean isGameFinished(){
        if (bossHealth <= 0){
            System.out.println("Heroes Won!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss Won!");
        }
        return allHeroesDead;
    }

    public static String changeBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        return heroesAttackType[randomIndex];
    }

    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
                if (heroesHealth[i] < 0){
                    heroesHealth[i] = 0;
                }
            }
        }
    }

    public static void heroesHit() {
        Random random = new Random();
        int coeff = random.nextInt(8) + 2;
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType){
                    bossHealth = bossHealth - heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff +" [" + coeff +"]");
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
                if (bossHealth < 0){
                    bossHealth = 0;
                }
            }
        }
    }

    public static void medicTime(){
        Random random = new Random();
        boolean choiceOfone = true;
        int medicRandom = random.nextInt(80) + 20;
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && choiceOfone){
                if (heroesAttackType[i] == "Medic"){
                continue;
                }
                heroesHealth[i] = heroesHealth[i] + medicRandom;
                choiceOfone = false;
                System.out.println("Medic gave live: " + medicRandom + " to " + heroesAttackType[i]);

            }
        }
    }

    public static void printStatistics(){
        System.out.println("________________________________ ROUND [" + roundNumber +"]");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("____________________________________________");

    }
}