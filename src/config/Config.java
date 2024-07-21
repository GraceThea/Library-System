/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author bagon
 */
public class Config {
    public static Map<String,String> ENV = new HashMap<>();
    public static void readConfig() throws FileNotFoundException{
        File file = new File("src/config.txt");
        Scanner myReader = new Scanner(file);
        while(myReader.hasNextLine()){
            String[] env = myReader.nextLine().split("=");
            if(env.length == 2){
                Config.ENV.put(env[0], env[1]);
            }else{
                Config.ENV.put(env[0], "");
            }
        }
    }
}
