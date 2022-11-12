package org.amazon.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropsReader {
    public static String filepath;
    public static String configFilesPath="/Users/rishabhbisht/Desktop/Automation/RB_Amazon_Automation_101/src/test/resources/config/";

    //Urls
    public static String driver=readPropertiesFile(configFilesPath+"baseConfig.properties").getProperty("driver");

    public static String mainUrl=readPropertiesFile(configFilesPath+"baseConfig.properties").getProperty("mainUrl");

    public static String username=readPropertiesFile(configFilesPath+"baseConfig.properties").getProperty("username");

    public static String password=readPropertiesFile(configFilesPath+"baseConfig.properties").getProperty("password");

    public static int timeoutInSeconds= Integer.parseInt(readPropertiesFile(configFilesPath+"baseConfig.properties").getProperty("timeoutInSeconds"));

    public static Properties readPropertiesFile(String fileName){
        FileInputStream fis=null;
        Properties prop=null;
        try{
            fis=new FileInputStream(fileName);
            prop=new Properties();
            prop.load(fis);
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            try{
                fis.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return prop;
    }


}
