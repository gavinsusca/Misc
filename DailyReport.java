package baseball;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.select.*; 
import org.jsoup.nodes.*; 
import org.jsoup.*;
//
/**
 *
 * @author gavinsusca
 */
public class DailyReport {
    Document games, lines;
    Elements allTodaysGames, linesML, pitchers, hitters;
            
    public static void main(String[] args) throws IOException { 
    
        DailyReport theDay = new DailyReport(); 
        
            theDay.games = Jsoup.connect("https://www.rotowire.com/hockey/nhl_lineups.htm").userAgent("mozilla/17.0").get();
            
            // looks like ?odds=Stats has the earliest lines.
            theDay.lines = Jsoup.connect("https://www.rotowire.com/hockey/nhl-odds.php?odds=Stats").userAgent("mozilla/17.0").get(); 
            
    		
            theDay.headerinfo();
            
        //    theDay.pitcherInfo(gameNumber);
            
    }
    
    
    public void lineUp(int gameNumber){
        hitters = games.select("div.span15").select("div.span15").select("div.dlineups-half"); 
        Element theHitter; 
    
        //System.out.println("-------------------------------------"); 
        System.out.print("\n--------");                             
        System.out.print("Away");
        System.out.println("--------");
            for (int f =0; f < 9; f++){
	
                theHitter = hitters.get((gameNumber*8)+2); 
	
                String position = theHitter.getElementsByClass("dlineups-pos").get(f).text();
                System.out.printf("%s(%-2s) -", f+1, position);

                
            String lastName = (" " + theHitter.getElementsByTag("a").get(f).text());
            int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
            
            if(lengthL > 10){
                lengthL = 10; 
            }
            
            lastName =(lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
            System.out.print(String.format("%-11s", lastName)); 
                                                
            //where you will get playerID                                           
            String playerID = theHitter.getElementsByTag("a").get(f).attr("href");	
            playerID = playerID.substring(playerID.lastIndexOf('=')+1);
            
            //So you don't get hitters stats for the NL pitchers in the lineup
            if(!position.equalsIgnoreCase("P")){  
            System.out.print(playerID);
            //System.out.print(hitterStats(playerID));  //where you will send playerID to get playStats
            ;}
            
            System.out.println();

						
	}//end of Lineup1
            
            
            
        System.out.print("\n--------");                             
        System.out.print("Home");
        System.out.println("--------");
            for (int f =0; f < 9; f++){
	
                theHitter = hitters.get((gameNumber*8)+3); 
	
                String position = theHitter.getElementsByClass("dlineups-pos").get(f).text();
                System.out.printf("%s(%-2s) -", f+1, position);
                                                
 
                
            String lastName = (" " + theHitter.getElementsByTag("a").get(f).text());
            int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
            
            if(lengthL > 10){
                lengthL = 10; 
            }
            
            lastName =(lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
            System.out.print(String.format("%-11s", lastName)); 
                                                
            //where you will get playerID                                           
            String playerID = theHitter.getElementsByTag("a").get(f).attr("href");	
            playerID = playerID.substring(playerID.lastIndexOf('=')+1);

            //So you don't get hitters stats for the NL pitchers in the lineup
            if(!position.equalsIgnoreCase("P")){  
            System.out.print(playerID);
            //System.out.print(hitterStats(playerID));   //where you will send playerID to get playStats
            ;}
            
            System.out.println();
            
            
            
	 }//end of Lineup2     
    
    
    }
    
    
    
    
    public void pitcherInfo(int gameNumber){
    	pitchers = games.select("div.span15").select("div.span15").select("div.dlineups-half"); 
		Element thePitcher;
	
		
		
			
	if(gameNumber==1){
				
				//First pitcher - with ID
		thePitcher = pitchers.get(gameNumber-1); 
                
                String lastName = (" " + thePitcher.getElementsByTag("a").text());
                int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
                
                    if(lengthL > 10){
                    lengthL = 10; 
                    }
                    
                lastName =(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
                System.out.print(String.format("%-11s", lastName)); 

				
                //where you will send PitcherID to get pitcherStats
                String pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(" "+pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats
                
                
                //Second pitcher - with ID
                thePitcher = pitchers.get(gameNumber); 
                System.out.println("\t vs ");
                
                lastName = (" " + thePitcher.getElementsByTag("a").text());
                lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
                
                    if(lengthL > 10){
                    lengthL = 10; 
                    }
                    
                lastName =(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
                System.out.print(String.format("%-11s", lastName)); 
             
                
                //where you will send PitcherID to get pitcherStats
                pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(" "+pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats
                
                          
	} else {
				
				
		//First pitcher - with ID
		thePitcher = pitchers.get((gameNumber - 1)*8); 
		
                String lastName = (" " + thePitcher.getElementsByTag("a").text());
                int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
                
                    if(lengthL > 10){
                    lengthL = 10; 
                    }
                    
                lastName =(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
                System.out.print(String.format("%-11s", lastName));
				
		//where you will send PitcherID to get pitcherStats
		String pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(" "+pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats

                
                
                //Second pitcher - with ID
		thePitcher = pitchers.get((gameNumber - 1)*8 +1); 
		System.out.println("\t vs ");
		lastName = (" " + thePitcher.getElementsByTag("a").text());
                lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
                
                    if(lengthL > 10){
                    lengthL = 10; 
                    }
                    
                lastName =(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
                System.out.print(String.format("%-11s", lastName)); 

                
                
		//where you will send PitcherID to get pitcherStats
		pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(" "+pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats

			}
    	
    	
    	
    }//end of pitcherInfo
    

    
    
    public void headerinfo(){
     
        
        allTodaysGames = games.select("div.span15.dlineups-topbox");
        int j = 0;
        

        
        for (Element today:allTodaysGames){ 
            j++;
	    System.out.println("\n\n");
	    System.out.println();
            System.out.println("Game " + j); 
            System.out.println(today.getElementsByTag("div").first().text());
            System.out.println("------------------- ");	
            System.out.println("------------------- ");	
           moneyLines(j);
           overUnder(j-1); 
           pitcherInfo(j); 
           lineUp(j-1);
           
        }//end of for loop
        
        
    }//end of headerInfo
    
    
    
    
    public void overUnder(int overUnder){
    //	overUnder = overUnder +1; 
    	
    	for (Element names:linesML){ 
    	if (overUnder ==0){
    			System.out.println("Over/Under " + names.getElementsByTag("td").get(5).text() + " runs.\n");
    	} else{
    			overUnder = overUnder +1; 
    			System.out.println("Over/Under " + names.getElementsByTag("td").get((overUnder*5)+(overUnder-1)).text() + " runs.\n");  //logic for string line
    		}
    	}
    	
    }
    
    
    
    
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
            System.out.println("------------------- ");	
            System.out.println("------------------- ");
            
	 	
    }//end moneyLines
    
    
    
    public String hitterStats(String playerID){
    return "";}
    
    
    
    public String pitcherStats(String playerID){
    return "";}
    
    
    
    
    
    public void weather(){}
    //city info??
    
    
}