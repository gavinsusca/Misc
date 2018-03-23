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
public class DailyReportT {
    Document games, lines;
    Elements allTodaysGames, linesML, pitchers, hitters, handed, playerStats;
            
    public static void main(String[] args) throws IOException { 
    
        DailyReportT theDay = new DailyReportT(); 
        
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
                                                
                if(!position.equalsIgnoreCase("P")){
                //So you don't get hitters stats for the NL pitchers in the lineup
                ;}
 
                
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
    
    
    
    
    public void pitcherInfo(int gameNumber) throws IOException{
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
  //              System.out.println(" "+pitcherID); 			//don't need this
//                System.out.print(" "+pitcherStats("10510")); 
                //pitcherStats(pitcherID); 
                pitcherStats("10510"); 
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
                pitcherStats("10510"); 
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
   //        lineUp(j-1);
           
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
    
    
    
    public String pitcherStats(String playerID) throws IOException{
        
        
        Document player = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=" + playerID).userAgent("mozilla/17.0").get();

        
        
        String last4Games = "";
    	String theStats = "";
    	String pitcherSplits = "";
        String pitcherHanded, homeStats, awayStats, vsLeft, vsRight, totalStats; 
   
    	        
        //Direction hitter hits Left Right or Switch.
        handed = player.select("div.span49.mlb-player-otherinfo").select("b");
        
        // System.out.println(handed.get(3).childNodes().get(0));
        //System.out.print(handed.get(2).nextSibling().toString()); //This is what you want! 
        
        pitcherHanded = handed.get(3).nextSibling().toString();
        pitcherHanded = pitcherHanded.substring(1,2) + "HP"; 
        pitcherHanded = "/" + pitcherHanded + " ";
        theStats = theStats + pitcherHanded;
        

        
        
        
        
        
        //players yearlyStats    
	    int yearLines = player.select("div#advancedstats.span49.mlb-player-advstatbox").select("tr").size();
	    playerStats = player.select("div#advancedstats.span49.mlb-player-advstatbox").select("tr").select("td"); //worked for long string

	    for (int i =0; i< yearLines-1; i++){
	    	
	    	//check to see if has 2018 major league stats
	    	//if(playerStats.get(i*25).text().matches("2018") && playerStats.get(i*25 +2).text().matches("MAJ")){
	    	if(playerStats.get(i*17).text().matches("2017")){

	    		
	        	totalStats = "T:("+ playerStats.get(i*17 + 6).text() +"IP|" + playerStats.get(i*17 + 7).text() + "(K/9)|" + playerStats.get(i*17 + 8).text() 
	        			+ "(BB/9)|" + playerStats.get(i*17 + 10).text() +"(HR/9)|" + playerStats.get(i*17 +11).text() + "(GB/FB)|" + playerStats.get(i*17 + 13).text() 
	        			+ "(FBavg)|" + playerStats.get(i*17 + 14).text() + "(ERA)|" + playerStats.get(i*17 + 15).text() + "(FIP))";
	        	theStats = theStats + totalStats;

	        	
	    	}//end if has major league 2018 stats.
	    	
	    	
	    }//end check of each year.
        
        
        
        
        System.out.println(theStats);

        
        
        
        
        
        
        
        
      
        
        
        
        
        
        
        
    	//Get splits. Home/Away/vLeft/vRight
		playerStats = player.select("div#splitstats.span49.mlb-player-splitsbox");

			//Get splits. Home/Away/vLeft/vRight
		    for (Element splitStats:playerStats){ 
            
	        //determine K rate by dividing to find a float then convert back to an int
	        	int percentL = (int)((float)(Integer.parseInt(splitStats.getElementsByTag("td").get(2).text()))/(Integer.parseInt(splitStats.getElementsByTag("td").get(1).text()))* 100);
	        	int percentR = (int)((float)(Integer.parseInt(splitStats.getElementsByTag("td").get((18*1)+2).text()))/(Integer.parseInt(splitStats.getElementsByTag("td").get((18*1)+1).text()))* 100);
		    	
		                vsLeft = "-VL:(" + splitStats.getElementsByTag("td").get(8).text() + "|" + splitStats.getElementsByTag("td").get(4).text() + "Hs|" + percentL + "K%|" + splitStats.getElementsByTag("td").get(3).text() + "BBs|" + splitStats.getElementsByTag("td").get(7).text() + "HRs|" + splitStats.getElementsByTag("td").get(1).text() + "ABs)"  ;
		                vsRight = "/VR:("  + splitStats.getElementsByTag("td").get((18*1)+8).text() + "|" + splitStats.getElementsByTag("td").get((18*1)+4).text() + "Hs|" + percentR + "K%|" + splitStats.getElementsByTag("td").get((18*1)+3).text() + "BBs|" + splitStats.getElementsByTag("td").get((18*1)+7).text() + "HRs|" + splitStats.getElementsByTag("td").get((18*1)+1).text() + "ABs)";
		                   
		                
		                homeStats = "H:("  + splitStats.getElementsByTag("td").get((18*2)+1).text() + "IP|" + splitStats.getElementsByTag("td").get((18*2)+5).text() + "Ks|" + splitStats.getElementsByTag("td").get((18*2)+6).text() + "BBs|" + splitStats.getElementsByTag("td").get((18*2)+7).text() + "HRs|" + splitStats.getElementsByTag("td").get((18*2)+8).text() + "|" + splitStats.getElementsByTag("td").get((18*2)+9).text() + ")";
		                //added an extra two to all the add statements because homeStats for pitchers had a couple extra fields. 
		                awayStats = "/A:("  + splitStats.getElementsByTag("td").get((18*3)+3).text() + "IP|" + splitStats.getElementsByTag("td").get((18*3)+7).text() + "Ks|" + splitStats.getElementsByTag("td").get((18*3)+8).text() + "BBs|" + splitStats.getElementsByTag("td").get((18*3)+9).text() + "HRs|" + splitStats.getElementsByTag("td").get((18*3)+10).text() + "|" + splitStats.getElementsByTag("td").get((18*3)+11).text() + ")";

		                pitcherSplits = "\t\t"+ homeStats + awayStats + vsLeft + vsRight;
		                

		    }//end of hitter splitStats line
        
        
                   
                    System.out.println( pitcherSplits);
        
        
        
        
        
        
        
        
                
        
        
        
        
        
        
                
        
        
        
        
        
        
        
        
        
        //last 4 games.
    	playerStats = player.select("div#gamelog.span49.gamelog-box");
        


    		for (Element eachLast:playerStats){ 
                    
  //                      System.out.println(" " + eachLast.getElementsByTag("tr").eachText()); 
 //              		System.out.println(" " + eachLast.getElementsByTag("td").eachText()); 
                        
                        // IP, R, H, BB, K, ERA/WHIP 
                        
                        String opponent; 
    			int R = 0;
    			int H =0;
    			int ER =0;
                        int HR =0;
                        int BB =0;
                        int K =0;
                        
    			float ERA = 0; 
    			float WHIP = 0;   // handle display decimals 
    			
    			try {
    				
    				for (int ee = 1; ee < 5; ee++){
    					if (ee == 1){
                                            
                                            
                                           // AB = AB + Integer.parseInt(eachLast.getElementsByTag("td").get(2).text());
                                            //System.out.println(Integer.parseInt(eachLast.getElementsByTag("td").get(2).text()));
                                            
                                            
                                           
                                          //date
                                            last4Games = last4Games + "(" + (eachLast.getElementsByTag("td").get(0).text()) + "-"; 
                                            
                                            //oppenent
                                            last4Games =last4Games +  (eachLast.getElementsByTag("td").get(1).text()) + ")("; 
                                            
                                           // IP 
                                            last4Games =last4Games +  (eachLast.getElementsByTag("td").get(2).text()) + "IP" + "/";
                                            
                                            //R 
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get(3).text()) + "H" + "/";
                                            
                                            
                                             //R 
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get(4).text()) + "R" + "/";
                                            
                                           //HR
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get(6).text()) + "HR" + "/";
                                            
                                              //BB
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get(7).text()) + "BB" + "/";
                                            
                                             // K 
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get(8).text()) + "K" + "-";
                                            
                                             //ERA 
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get(16).text()) +"/";
                                            
                                             //WHIP
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get(17).text()) + ") ";
                                            
                                         //   System.out.println(last4Games); 
 	
    					} else {
                                            

                                            last4Games = last4Games + "(" + (eachLast.getElementsByTag("td").get((((ee-1)*18))).text()) + "-";
                                            last4Games =last4Games +  (eachLast.getElementsByTag("td").get((((ee-1)*18)+1)).text()) + ")("; 
                                            last4Games =last4Games +  (eachLast.getElementsByTag("td").get((((ee-1)*18)+2)).text()) + "IP" + "/";
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get((((ee-1)*18)+3)).text()) + "H" + "/";
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get((((ee-1)*18)+4)).text()) + "R" + "/";
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get((((ee-1)*18)+6)).text()) + "HR" + "/";
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get((((ee-1)*18)+7)).text()) + "BB" + "/";
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get((((ee-1)*18)+8)).text()) + "K" + "-";
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get((((ee-1)*18)+16)).text()) +"/";
                                            last4Games = last4Games + (eachLast.getElementsByTag("td").get((((ee-1)*18)+17)).text()) + ") ";
                                           
                                            
    					}//end else
                                        
                                        //add next line after second start for formatting
                                        if (ee ==2){
                                            last4Games = last4Games + "\n" +  "\t\t" ;}
                                        else if(ee == 1){
                                            last4Games =  "\t\t" +last4Games;}
                                        else if( ee == 3){
                                           // last4Games = last4Games +  "\t" ;
                                        }

                                        
    				}//end ee for	
                                
                                System.out.println(last4Games); 
    			}//end try 
    			catch (Exception e){}

    		}//end last 4 games

        
        
        
        
        
    return "";}
    
    
    
    
    
    public void weather(){}
    //city info??
    
    
}