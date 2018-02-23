/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.select.*; 
import org.jsoup.nodes.*; 
import org.jsoup.*;

/**
 *
 * @author gavinsusca
 */
public class DailyReport {
    Document games, lines;
    Elements allTodaysGames, linesML;
            
    public static void main(String[] args) throws IOException { 
    
        DailyReport theDay = new DailyReport(); 
        
            theDay.games = Jsoup.connect("https://www.rotowire.com/hockey/nhl_lineups.htm").userAgent("mozilla/17.0").get();
            theDay.lines = Jsoup.connect("https://www.rotowire.com/hockey/nhl-odds.php").userAgent("mozilla/17.0").get(); 
            theDay.headerinfo();
            
    }
    
    
    public void lineup(){}
    
    public void playerInfo(String playerID){}
    
    public void pitcherInfo(String pitcherID){}
    
    
    
    public void headerinfo(){
     
        
        allTodaysGames = games.select("div.span15.dlineups-topbox");
        int j = 0;
        for (Element today:allTodaysGames){ 
            j++;
	    System.out.println();
            System.out.println("Game " + j); 
            System.out.println(today.getElementsByTag("div").first().text());
            System.out.println(" ------------------- ");	
            System.out.println(" ------------------- ");	
           moneyLines(j);
           
            
        }//end of forloop
    }//end of headerInfo
    
    
    
    
    public void overUnder(){}
    
    
    
    
    public void moneyLines(int gameNumber){
                
		// gives money lines. odds and over/under for todays games
        gameNumber = gameNumber-1;
        linesML = lines.select("div.row").select("tbody");
 
            for (Element names:linesML){ 
                if (gameNumber ==0){
                System.out.println(" " + names.getElementsByTag("td").get(4).text() + " " + names.getElementsByTag("td").get(3).text() );
		System.out.println(" " + names.getElementsByTag("td").get(1).text() + " " + names.getElementsByTag("td").get(2).text() );
                } else {
                System.out.println(" " + names.getElementsByTag("td").get((gameNumber*6) + 4).text() + " " + names.getElementsByTag("td").get((gameNumber*6) + 3).text() );
                System.out.println(" " + names.getElementsByTag("td").get((gameNumber*6) + 1).text() + " " + names.getElementsByTag("td").get((gameNumber*6)+ 2).text() );
                }
            }
            System.out.println(" ------------------- ");	
            System.out.println(" ------------------- ");
            
	 	
    }//end moneyLines
    
    public void weather(){}
    //city info??
    
    
}
