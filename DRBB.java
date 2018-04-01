//package baseball;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
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
    Elements allTodaysGames, linesML, pitchers, hitters, playerStats, handed, delays;
    int minusGamesH=0;
    int minusGamesP=0;
    
    ArrayList<Integer> skipGames = new ArrayList<Integer>(); 
    
    //for file 
    String nameFile = "dailyReport";
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	File theFile = new File(nameFile + "_" + date + ".txt"); 

	PrintWriter printWriter; 
	FileWriter fileOut; 
    //end of for file info
    
            
    public static void main(String[] args) throws IOException { 
    
        DailyReport theDay = new DailyReport(); 
        
        for(int i=0; i< args.length; i++){
        	theDay.skipGames.add(Integer.parseInt(args[i]));
        }
   
        
        
            theDay.games = Jsoup.connect("https://www.rotowire.com/baseball/daily_lineups.htm").userAgent("mozilla/17.0").get();
            theDay.lines = Jsoup.connect("https://www.rotowire.com/baseball/mlb-odds.php?odds=Stats").userAgent("mozilla/17.0").get(); 
            
            
            
            
            theDay.createFile();
            theDay.headerinfo();
            theDay.closeFile();

            
    } //end of main
    
    
    public void lineUp(int gameNumber) throws IOException{
        hitters = games.select("div.span15").select("div.span15").select("div.dlineups-half"); 
        Element theHitter; 
        Boolean delayFlag = false;
       
    
       
       // 0 , 1 , 2 , 3 , 4 
       //lineUp(j-1);
       
       
       
       //check for delays
       //set the delay flag from a command line prompt
       for(int delayLoop=0; delayLoop< skipGames.size(); delayLoop++){
      // System.out.print(skipGames.get(0));
       int temp = gameNumber; 
       	if((temp+1) == skipGames.get(delayLoop)){
       		delayFlag = true;
       		minusGamesH++;
       	}
       }
       
       //end check
//       System.out.println("gamenumber is" +gameNumber);
//       System.out.println("delay flag is " + delayFlag);
       
       
       if (delayFlag != true){
       gameNumber = gameNumber - minusGamesH;
       
       
       
       
       
        //System.out.println("-------------------------------------"); 
        System.out.print("\n--------");
        printWriter.print("\n--------");
        
        System.out.print("Away");
        printWriter.print("Away");
        
        System.out.println("--------");
        printWriter.println("--------");
        

        
            for (int f =0; f < 9; f++){
	
         
            	
	
                String position = hitters.get((gameNumber*2)).getElementsByClass("dlineups-pos").get(f).text();
                System.out.printf("%s.", f+1);
                printWriter.printf("%s.", f+1);    
                
                
            String lastName = (" " + hitters.get((gameNumber*2)).getElementsByTag("a").get(f).text());
            int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
            
            if(lengthL > 10){
                lengthL = 10; 
            }
            
            lastName =(lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
            System.out.print(String.format("%-11s", lastName)); 
            printWriter.printf("%-11s", lastName); 
 
            
            
            
            System.out.printf(" %-2s ", position);
            printWriter.format(" %-2s ", position);
            
                                                
            //where you will get playerID                                           
            String playerID = hitters.get((gameNumber*2)).getElementsByTag("a").get(f).attr("href");	
            playerID = playerID.substring(playerID.lastIndexOf('=')+1);
            
            //So you don't get hitters stats for the NL pitchers in the lineup
            if(!position.equalsIgnoreCase("P")){  
//            	System.out.print(playerID);
             System.out.print(hitterStats(playerID));  //where you will send playerID to get playStats
             printWriter.print(hitterStats(playerID));
            	
            }
            
            System.out.println();
            printWriter.println();
						
	}//end of Lineup1
            
            
            
        System.out.print("\n--------");
        printWriter.print("\n--------");
        
        System.out.print("Home");
        printWriter.print("Home");
        
        System.out.println("--------");
        printWriter.println("--------");
        
        
            for (int f =0; f < 9; f++){
	
              //  theHitter = hitters.get((gameNumber*8)+3); 
	
                String position = hitters.get((gameNumber*2)+1).getElementsByClass("dlineups-pos").get(f).text();
                System.out.printf("%s.", f+1);
                printWriter.printf("%s.", f+1);             
 
                
            String lastName = (" " + hitters.get((gameNumber*2)+1).getElementsByTag("a").get(f).text());
            int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
            
            if(lengthL > 10){
                lengthL = 10; 
            }
            
            lastName =(lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
            System.out.print(String.format("%-11s", lastName)); 
            printWriter.print(String.format("%-11s", lastName)); 
            
            System.out.printf(" %-2s ", position);
            printWriter.printf(" %-2s ", position);
                                                
            //where you will get playerID                                           
            String playerID = hitters.get((gameNumber*2)+1).getElementsByTag("a").get(f).attr("href");	
            playerID = playerID.substring(playerID.lastIndexOf('=')+1);

            //So you don't get hitters stats for the NL pitchers in the lineup
            if(!position.equalsIgnoreCase("P")){  
            	
            System.out.print(hitterStats(playerID));   //where you will send playerID to get playStats
            printWriter.print(hitterStats(playerID));
            	
            }
            
            System.out.println();
            printWriter.println();
            
            
	 }//end of Lineup2     
    
        }//end delayIF
    }
    
    
    
    
    public void pitcherInfo(int gameNumber) throws IOException{
    	
    	pitchers = games.select("div.span15").select("div.span15").select("div.span11.dlineups-pitchers"); 
		Element thePitcher;
		Boolean delayFlag = false;
	//	pitcherInfo(j-1); 
		

		
		
		
	       //check for delays
	       //set the delay flag from a command line prompt
	       for(int delayLoop=0; delayLoop< skipGames.size(); delayLoop++){
	      // System.out.print(skipGames.get(0));
	       int temp = gameNumber; 
	       	if((temp+1) == skipGames.get(delayLoop)){
	       		delayFlag = true;
	       		minusGamesP++;
	       	}
	       }
	       
	       //end check

	       
	       if (delayFlag != true){
	       gameNumber = gameNumber - minusGamesP;
	       


				
				//First pitcher - with ID
	       thePitcher = pitchers.get((gameNumber)); 
            
                String lastName = (" " + thePitcher.getElementsByTag("a").get(0).text());
                int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
                
                    if(lengthL > 10){
                    lengthL = 10; 
                    }
                    
                lastName =(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
                System.out.print(String.format("%-11s", lastName)); 
                printWriter.format("%-11s", lastName);
				
                //where you will send PitcherID to get pitcherStats
                String pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	

                System.out.print(pitcherStats(pitcherID));
                 printWriter.print(pitcherStats(pitcherID));
                
                
                
                
                //Second pitcher - with ID
           thePitcher = pitchers.get((gameNumber)); 
                System.out.println("\t vs ");
                printWriter.println("\t vs ");
                
                lastName = (" " + thePitcher.getElementsByTag("a").get(1).text());
                lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
                
                    if(lengthL > 10){
                    lengthL = 10; 
                    }
                    
                lastName =(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
                System.out.print(String.format("%-11s", lastName)); 
                printWriter.print(String.format("%-11s", lastName)); 
                
                //where you will send PitcherID to get pitcherStats
                pitcherID = " " + thePitcher.getElementsByTag("a").get(1).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	

                System.out.print(pitcherStats(pitcherID));
                 printWriter.print(pitcherStats(pitcherID));
	       }         
                          

    	
    	
    }//end of pitcherInfo
    

    
    
    public void headerinfo() throws IOException{
     
        
        allTodaysGames = games.select("div.span15.dlineups-topbox");
        int j = 0;
        

        
        for (Element today:allTodaysGames){ 
            j++;
	    System.out.println("\n\n");
	    printWriter.println("\n\n");
	    
	    System.out.println();
	    printWriter.println();
	    
            System.out.println("Game " + j); 
            printWriter.println("Game " + j); 
            
            System.out.println(today.getElementsByTag("div").first().text());
            printWriter.println(today.getElementsByTag("div").first().text());
            
            System.out.println("------------------- ");	
            printWriter.println("------------------- ");	
            
            overUnder(j-1);
            System.out.println("------------------- ");	
            printWriter.println("------------------- ");	
            
            moneyLines(j); 
            pitcherInfo(j-1); 
            lineUp(j-1);
           
        }//end of for loop
        
        
    }//end of headerInfo
    
    
    
    
    public void overUnder(int overUnder){
    //	overUnder = overUnder +1; 
    	linesML = lines.select("div.row").select("tbody");
    	
    	for (Element names:linesML){ 
    	if (overUnder ==0){
    			System.out.println("Over/Under " + names.getElementsByTag("td").get(5).text() + " runs.");
    			printWriter.println("Over/Under " + names.getElementsByTag("td").get(5).text() + " runs.");
    	} else{
    			overUnder = overUnder +1; 
    			System.out.println("Over/Under " + names.getElementsByTag("td").get((overUnder*5)+(overUnder-1)).text() + " runs.");  //logic for string line
    			printWriter.println("Over/Under " + names.getElementsByTag("td").get((overUnder*5)+(overUnder-1)).text() + " runs.");  //logic for string line
    		}
    	}
    	
    }
    
    
    
    
    public void moneyLines(int gameNumber){
                
		// gives money lines. odds and over/under for todays games
        gameNumber = gameNumber-1;
        //do need to do this twice. I moved the overUnder so it will hit that first
        // linesML = lines.select("div.row").select("tbody");
 
            for (Element names:linesML){ 
                if (gameNumber ==0){
                System.out.println(" " + names.getElementsByTag("td").get(4).text() + " " + names.getElementsByTag("td").get(3).text() );
                printWriter.println(" " + names.getElementsByTag("td").get(4).text() + " " + names.getElementsByTag("td").get(3).text() );
                
                System.out.println(" " + names.getElementsByTag("td").get(1).text() + " " + names.getElementsByTag("td").get(2).text() );
                printWriter.println(" " + names.getElementsByTag("td").get(1).text() + " " + names.getElementsByTag("td").get(2).text() );
                } else {
                System.out.println(" " + names.getElementsByTag("td").get((gameNumber*6) + 4).text() + " " + names.getElementsByTag("td").get((gameNumber*6) + 3).text() );
                printWriter.println(" " + names.getElementsByTag("td").get((gameNumber*6) + 4).text() + " " + names.getElementsByTag("td").get((gameNumber*6) + 3).text() );
                
                System.out.println(" " + names.getElementsByTag("td").get((gameNumber*6) + 1).text() + " " + names.getElementsByTag("td").get((gameNumber*6)+ 2).text() );
                printWriter.println(" " + names.getElementsByTag("td").get((gameNumber*6) + 1).text() + " " + names.getElementsByTag("td").get((gameNumber*6)+ 2).text() );
                }
            }
            System.out.println("------------------- ");	
            printWriter.println("------------------- ");	
            
            System.out.println("------------------- ");
            printWriter.println("------------------- ");
            
	 	
    }//end moneyLines
    
    
    
    public String hitterStats(String playerID) throws IOException{
    	
    	String last4Games = "";
    	String theStats = "";
    	String homeStats, awayStats, vsLeft, vsRight, totalStats;
    	Document player = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=" + playerID).userAgent("mozilla/17.0").get();


    	
    	
    	
    	
    	
		//last 4 games.
    	playerStats = player.select("div#gamelog.span49.gamelog-box");

    	try{
    	
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
    		
    		}catch(Exception e){}	
    	
    		
    		
    		
    		
    		
    		
    		
    		
    		

    	//Get splits. Home/Away/vLeft/vRight
		playerStats = player.select("div#splitstats.span49.mlb-player-splitsbox");

			//Get splits. Home/Away/vLeft/vRight
		    for (Element splitStats:playerStats){ 

		                vsLeft = "-VL:(" + splitStats.getElementsByTag("td").get(6).text() + "|" + splitStats.getElementsByTag("td").get(3).text() + "HRs|" + splitStats.getElementsByTag("td").get(1).text() + "ABs)" ;
		                vsRight = "/VR:("  + splitStats.getElementsByTag("td").get((9*1)+6).text() + "|" + splitStats.getElementsByTag("td").get((9*1)+3).text() + "HRs|" + splitStats.getElementsByTag("td").get((9*1)+1).text() + "ABs)";
		                homeStats = "H:("  + splitStats.getElementsByTag("td").get((9*2)+6).text() + "|" + splitStats.getElementsByTag("td").get((9*2)+3).text() + "HRs|" + splitStats.getElementsByTag("td").get((9*2)+1).text() + "ABs)";
		                awayStats = "/A:("  + splitStats.getElementsByTag("td").get((9*3)+6).text() + "|" + splitStats.getElementsByTag("td").get((9*3)+3).text() + "HRs|" + splitStats.getElementsByTag("td").get((9*3)+1).text() + "ABs)";
		                
		                theStats = last4Games + "vP(add|Stats|here)-" + homeStats + awayStats + vsLeft + vsRight;

		    }//end of hitter splitStats line
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		        //players yearlyStats    
		    int yearLines = player.select("div#basicstats.span49.mlb-player-basicstatsbox").select("tr").size();
		    playerStats = player.select("div#basicstats.span49.mlb-player-basicstatsbox").select("tr").select("td"); //worked for long string

		    
		    for (int i =0; i< yearLines -1; i++){
		    	
		    	try{
		    	
		    	//check to see if has 2018 major league stats
		    	//if(playerStats.get(i*25).text().matches("2018") && playerStats.get(i*25 +2).text().matches("MAJ")){
		    	if(playerStats.get(i*25).text().matches("2017")){

		        	//determine K rate by dividing to find a float then convert back to an int
		        	int percent = (int)((float)(Integer.parseInt(playerStats.get(i*25 + 17).text()))/(Integer.parseInt(playerStats.get(i*25 + 5).text()))* 100);
		        	totalStats = "-T:("+ playerStats.get(i*25 + 21).text() +"|" + playerStats.get(i*25 + 12).text() + "HR|" + playerStats.get(i*25 + 9).text() +"EBH|" + percent + "%K|" + playerStats.get(i*25 + 6).text() +"AB" + ")";
		        	theStats = theStats + totalStats;

		    	}//end if has major league 2018 stats.
		    	
		    	}catch (Exception e){}
		    	
		    	
		    }//end check of each year.
		    
		    
		    
		
		
    return theStats;}
    
    
    
    public String pitcherStats(String playerID) throws IOException{
    	
     
        
        Document player = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=" + playerID).userAgent("mozilla/17.0").get();

        
        
        String last4Games = "";
    	String theStats = "";
    	String pitcherSplits = "";
        String pitcherHanded, homeStats, awayStats, vsLeft, vsRight, totalStats; 
   
    	        
        //Direction hitter hits Left Right or Switch.
        handed = player.select("div.span49.mlb-player-otherinfo").select("b");
        
        pitcherHanded = handed.get(3).nextSibling().toString();
        pitcherHanded = pitcherHanded.substring(1,2) + "HP"; 
        pitcherHanded = "/" + pitcherHanded + " ";
        theStats = theStats + pitcherHanded;
        
        printWriter.print(theStats);
        
        
        
        
        
        //players yearlyStats    
	    int yearLines = player.select("div#advancedstats.span49.mlb-player-advstatbox").select("tr").size();
	    playerStats = player.select("div#advancedstats.span49.mlb-player-advstatbox").select("tr").select("td"); //worked for long string

	    for (int i =0; i< yearLines-1; i++){
	    	
	    	//check to see if has 2018 major league stats
	    	//if(playerStats.get(i*25).text().matches("2018") && playerStats.get(i*25 +2).text().matches("MAJ")){
	    	if(playerStats.get(i*17).text().matches("2017") && playerStats.get(i*17 +2).text().matches("MAJ")){

	    		
	        	totalStats = "T:("+ playerStats.get(i*17 + 6).text() +"IP|" + playerStats.get(i*17 + 7).text() + "(K/9)|" + playerStats.get(i*17 + 8).text() 
	        			+ "(BB/9)|" + playerStats.get(i*17 + 10).text() +"(HR/9)|" + playerStats.get(i*17 +11).text() + "(GB/FB)|" + playerStats.get(i*17 + 13).text() 
	        			+ "(FBavg)|" + playerStats.get(i*17 + 14).text() + "(ERA)|" + playerStats.get(i*17 + 15).text() + "(FIP))";
	        	theStats = theStats + totalStats;
	        	printWriter.println(totalStats);
	        	
	    	}//end if has major league 2018 stats.
	    	
	    	
	    }//end check of each year.
        
	    
 //       System.out.println(theStats);

        
        

        
        
        
    	//Get splits. Home/Away/vLeft/vRight
		playerStats = player.select("div#splitstats.span49.mlb-player-splitsbox");

			//Get splits. Home/Away/vLeft/vRight
		    for (Element splitStats:playerStats){ 
            try{
	        //determine K rate by dividing to find a float then convert back to an int
	        	int percentL = (int)((float)(Integer.parseInt(splitStats.getElementsByTag("td").get(2).text()))/(Integer.parseInt(splitStats.getElementsByTag("td").get(1).text()))* 100);
	        	int percentR = (int)((float)(Integer.parseInt(splitStats.getElementsByTag("td").get((18*1)+2).text()))/(Integer.parseInt(splitStats.getElementsByTag("td").get((18*1)+1).text()))* 100);
		    	
		                vsLeft = "-VL:(" + splitStats.getElementsByTag("td").get(8).text() + "|" + splitStats.getElementsByTag("td").get(4).text() + "Hs|" + percentL + "K%|" + splitStats.getElementsByTag("td").get(3).text() + "BBs|" + splitStats.getElementsByTag("td").get(7).text() + "HRs|" + splitStats.getElementsByTag("td").get(1).text() + "ABs)"  ;       
		                vsRight = "/VR:("  + splitStats.getElementsByTag("td").get((18*1)+8).text() + "|" + splitStats.getElementsByTag("td").get((18*1)+4).text() + "Hs|" + percentR + "K%|" + splitStats.getElementsByTag("td").get((18*1)+3).text() + "BBs|" + splitStats.getElementsByTag("td").get((18*1)+7).text() + "HRs|" + splitStats.getElementsByTag("td").get((18*1)+1).text() + "ABs)";
		                   
		                
		                homeStats = "H:("  + splitStats.getElementsByTag("td").get((18*2)+1).text() + "IP|" + splitStats.getElementsByTag("td").get((18*2)+5).text() + "Ks|" + splitStats.getElementsByTag("td").get((18*2)+6).text() + "BBs|" + splitStats.getElementsByTag("td").get((18*2)+7).text() + "HRs|" + splitStats.getElementsByTag("td").get((18*2)+8).text() + "|" + splitStats.getElementsByTag("td").get((18*2)+9).text() + ")";
		                //added an extra two to all the add statements because homeStats for pitchers had a couple extra fields. 
		                awayStats = "/A:("  + splitStats.getElementsByTag("td").get((18*3)+3).text() + "IP|" + splitStats.getElementsByTag("td").get((18*3)+7).text() + "Ks|" + splitStats.getElementsByTag("td").get((18*3)+8).text() + "BBs|" + splitStats.getElementsByTag("td").get((18*3)+9).text() + "HRs|" + splitStats.getElementsByTag("td").get((18*3)+10).text() + "|" + splitStats.getElementsByTag("td").get((18*3)+11).text() + ")";

		                pitcherSplits = "\t\t"+ homeStats + awayStats + vsLeft + vsRight;
            }catch (Exception e){}         
		                
		    }//end of hitter splitStats line
        
		    
		    theStats = theStats + "\n" + pitcherSplits + "\n" ; 
		    printWriter.println(pitcherSplits);
//		    System.out.println(pitcherSplits);
        
        
        

        
        
        
        
        
        
        //last 4 games.
    	playerStats = player.select("div#gamelog.span49.gamelog-box");
        


    		for (Element eachLast:playerStats){ 

                        
                // IP, H, R, BB, K, ERA/WHIP 
                        

    			
    			try {
    				
    				for (int ee = 1; ee < 5; ee++){
    					if (ee == 1){

                    last4Games = last4Games + "(" + (eachLast.getElementsByTag("td").get(0).text()) + "-";  //date               
                    last4Games =last4Games +  (eachLast.getElementsByTag("td").get(1).text()) + ")(";  //opponent                  
                    last4Games =last4Games +  (eachLast.getElementsByTag("td").get(2).text()) + "IP" + "/";  // IP                   
                    last4Games = last4Games + (eachLast.getElementsByTag("td").get(3).text()) + "H" + "/";   //H               
                    last4Games = last4Games + (eachLast.getElementsByTag("td").get(4).text()) + "R" + "/";       //R            
                    last4Games = last4Games + (eachLast.getElementsByTag("td").get(6).text()) + "HR" + "/";  //HR              
                    last4Games = last4Games + (eachLast.getElementsByTag("td").get(7).text()) + "BB" + "/";  //BB                   
                    last4Games = last4Games + (eachLast.getElementsByTag("td").get(8).text()) + "K" + "-";    // K                     
                    last4Games = last4Games + (eachLast.getElementsByTag("td").get(16).text()) +"/";  //ERA                   
                    last4Games = last4Games + (eachLast.getElementsByTag("td").get(17).text()) + ") ";//WHIP

    					} else {
                                            

    				last4Games = last4Games + "(" + (eachLast.getElementsByTag("td").get((((ee-1)*18))).text()) + "-";
    				last4Games = last4Games +  (eachLast.getElementsByTag("td").get((((ee-1)*18)+1)).text()) + ")("; 
    				last4Games = last4Games +  (eachLast.getElementsByTag("td").get((((ee-1)*18)+2)).text()) + "IP" + "/";
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
    						last4Games = last4Games + "\r\n" +  "\t\t" ;}
    					else if(ee == 1){
    						last4Games =  "\t\t" +last4Games;}
    					else if( ee == 3){
    						// last4Games = last4Games +  "\t" ;
    					}

                                        
    				}//end ee for	
                          
    				
    				
    				theStats = theStats + last4Games +"\n"; 
    				 printWriter.println(last4Games);
 //   				System.out.println(last4Games); 
    			}//end try 
    			catch (Exception e){}

    		}//end last 4 games


    	
    return theStats;}
    
    
    
    public void weather(){}
    //city info??
    
    
    
    public void createFile() throws IOException{
    	
    	//where the file will be sent
    	fileOut = new FileWriter("C:\\Users\\Gavin.Susca\\Desktop\\" + theFile); 
    	printWriter = new PrintWriter(fileOut); 
    }
    
    public void closeFile() throws IOException{
    	fileOut.close();
        printWriter.close();
    }
    
}