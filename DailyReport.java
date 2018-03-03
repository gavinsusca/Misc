//package baseball;

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
    Elements allTodaysGames, linesML, pitchers, hitters, playerStats;
            
    public static void main(String[] args) throws IOException { 
    
        DailyReport theDay = new DailyReport(); 
        
            theDay.games = Jsoup.connect("https://www.rotowire.com/hockey/nhl_lineups.htm").userAgent("mozilla/17.0").get();
            
            // looks like ?odds=Stats has the earliest lines.
            theDay.lines = Jsoup.connect("https://www.rotowire.com/hockey/nhl-odds.php?odds=Stats").userAgent("mozilla/17.0").get(); 
            
    		
            theDay.headerinfo();
            
        //    theDay.pitcherInfo(gameNumber);
            
    }
    
    
    public void lineUp(int gameNumber) throws IOException{
        hitters = games.select("div.span15").select("div.span15").select("div.dlineups-half"); 
        Element theHitter; 
    
        //System.out.println("-------------------------------------"); 
        System.out.print("\n--------");                             
        System.out.print("Away");
        System.out.println("--------");
            for (int f =0; f < 9; f++){
	
                theHitter = hitters.get((gameNumber*8)+2); 
	
                String position = theHitter.getElementsByClass("dlineups-pos").get(f).text();
                System.out.printf("%s.", f+1);

                
            String lastName = (" " + theHitter.getElementsByTag("a").get(f).text());
            int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
            
            if(lengthL > 10){
                lengthL = 10; 
            }
            
            lastName =(lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
            System.out.print(String.format("%-11s", lastName)); 
            System.out.printf(" %-2s ", position);
                                                
            //where you will get playerID                                           
            String playerID = theHitter.getElementsByTag("a").get(f).attr("href");	
            playerID = playerID.substring(playerID.lastIndexOf('=')+1);
            
            //So you don't get hitters stats for the NL pitchers in the lineup
            if(!position.equalsIgnoreCase("P")){  
//            System.out.print(playerID);
              System.out.print(hitterStats("11179"));
           // System.out.print(hitterStats(playerID));  //where you will send playerID to get playStats
            ;}
            
            System.out.println();

						
	}//end of Lineup1
            
            
            
        System.out.print("\n--------");                             
        System.out.print("Home");
        System.out.println("--------");
            for (int f =0; f < 9; f++){
	
                theHitter = hitters.get((gameNumber*8)+3); 
	
                String position = theHitter.getElementsByClass("dlineups-pos").get(f).text();
                System.out.printf("%s.", f+1);
                                                
 
                
            String lastName = (" " + theHitter.getElementsByTag("a").get(f).text());
            int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
            
            if(lengthL > 10){
                lengthL = 10; 
            }
            
            lastName =(lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
            System.out.print(String.format("%-11s", lastName)); 
            System.out.printf(" %-2s ", position);
                                                
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
    

    
    
    public void headerinfo() throws IOException{
     
        
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
    
    
    
    public String hitterStats(String playerID) throws IOException{
    	
    	String last4Games = "";
    	String theStats = "";
    	String homeStats, awayStats, vsLeft, vsRight, totalStats;
    	Document player = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=" + playerID).userAgent("mozilla/17.0").get();


    	
    	
    	
    	
    	
		//last 4 games.
    	playerStats = player.select("div#gamelog.span49.gamelog-box");

    		for (Element eachLast:playerStats){ 

    			int AB =0;
    			int H =0;
    			double TB_ISO =0;
    			float AVG = 0; 
    			float ISO = 0;   // handle display decimals 
    			int SO =0;

    			try {
    				
    				for (int ee = 1; ee < 5; ee++){
    					if (ee == 1){
    						AB = AB + Integer.parseInt(eachLast.getElementsByTag("td").get(2).text());
    						H = H + Integer.parseInt(eachLast.getElementsByTag("td").get(4).text());
    						TB_ISO = TB_ISO + (Float.parseFloat(eachLast.getElementsByTag("td").get(5).text()) * .67) + (Float.parseFloat(eachLast.getElementsByTag("td").get(6).text()) * .67) + (Float.parseFloat(eachLast.getElementsByTag("td").get(7).text()));
    						SO = SO + Integer.parseInt(eachLast.getElementsByTag("td").get(10).text());
						
    					} else {
    						AB = AB + Integer.parseInt(eachLast.getElementsByTag("td").get((((ee-1)*20)+2)).text()); 
    						H = H + Integer.parseInt(eachLast.getElementsByTag("td").get((((ee-1)*20)+4)).text());
    						TB_ISO = TB_ISO + ((Integer.parseInt(eachLast.getElementsByTag("td").get((((ee-1)*20)+5)).text())) * .67) + ((Integer.parseInt(eachLast.getElementsByTag("td").get((((ee-1)*20)+6)).text())) * .67) + ((Integer.parseInt(eachLast.getElementsByTag("td").get((((ee-1)*20)+7)).text())));
    						SO = SO + Integer.parseInt(eachLast.getElementsByTag("td").get((((ee-1)*20)+10)).text());
    					}
    				}			
    			}//end try 
    			catch (Exception e){}

    			AVG = (float)H/AB; 
    			ISO = (float)TB_ISO/AB; 
		
    			last4Games ="L4:"; 
    			String battingAverage = String.format("(%.3f" , AVG); 
    			last4Games = last4Games + "("+ battingAverage.substring(battingAverage.indexOf('.')) + "|";
    			String battingD_ISO = String.format("%.3f" , ISO); 
    			last4Games = last4Games + battingD_ISO.substring(battingD_ISO.indexOf('.')) + "|"; 
    			last4Games = last4Games + SO + "SO"; 
    			last4Games = last4Games + "|" + AB + "ABs)-";

    		}//end last 4 games
    	
    	
    		
    		
    		
    		
    		
    		
    		
    		
    		

    	//Get splits. Home/Away/vLeft/vRight
		playerStats = player.select("div#splitstats.span49.mlb-player-splitsbox");

			//Get splits. Home/Away/vLeft/vRight
		    for (Element splitStats:playerStats){ 

		                vsLeft = "-VL:(" + splitStats.getElementsByTag("td").get(6).text() + "|" + splitStats.getElementsByTag("td").get(3).text() + "HRs|" + splitStats.getElementsByTag("td").get(1).text() + "ABs)" ;
		                vsRight = "/VR:("  + splitStats.getElementsByTag("td").get((27*1)+6).text() + "|" + splitStats.getElementsByTag("td").get((27*1)+3).text() + "HRs|" + splitStats.getElementsByTag("td").get((27*1)+1).text() + "ABs)";
		                homeStats = "H:("  + splitStats.getElementsByTag("td").get((27*2)+6).text() + "|" + splitStats.getElementsByTag("td").get((27*2)+3).text() + "HRs|" + splitStats.getElementsByTag("td").get((27*2)+1).text() + "ABs)";
		                awayStats = "/A:("  + splitStats.getElementsByTag("td").get((27*3)+6).text() + "|" + splitStats.getElementsByTag("td").get((27*3)+3).text() + "HRs|" + splitStats.getElementsByTag("td").get((27*3)+1).text() + "ABs)";
		                
		                theStats = last4Games + "vP(add|Stats|here)-" + homeStats + awayStats + vsLeft + vsRight;

		    }//end of hitter splitStats line
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		        //players yearlyStats    
		    int yearLines = player.select("div#basicstats.span49.mlb-player-basicstatsbox").select("tr").size();
		    playerStats = player.select("div#basicstats.span49.mlb-player-basicstatsbox").select("tr").select("td"); //worked for long string

		    for (int i =0; i< yearLines-1; i++){
		    	
		    	//check to see if has 2018 major league stats
		    	//if(playerStats.get(i*25).text().matches("2018") && playerStats.get(i*25 +2).text().matches("MAJ")){
		    	if(playerStats.get(i*25).text().matches("2018 Spring Training")){

		        	//determine K rate by dividing to find a float then convert back to an int
		        	int percent = (int)((float)(Integer.parseInt(playerStats.get(i*25 + 17).text()))/(Integer.parseInt(playerStats.get(i*25 + 5).text()))* 100);
		        	totalStats = "-T:("+ playerStats.get(i*25 + 21).text() +"|" + playerStats.get(i*25 + 12).text() + "HR|" + playerStats.get(i*25 + 9).text() +"EBH|" + percent + "%K|" + playerStats.get(i*25 + 6).text() +"AB" + ")";
		        	theStats = theStats + totalStats;

		    	}//end if has major league 2018 stats.
		    	
		    	
		    }//end check of each year.
		    
		    
		    
		
		
    return theStats;}
    
    
    
    public String pitcherStats(String playerID){
    return "";}
    
    
    
    
    
    public void weather(){}
    //city info??
    
    
}