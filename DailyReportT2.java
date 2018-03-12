

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.select.*; 
import org.jsoup.nodes.*; 
import org.jsoup.*;

/**
 *
 * @author gavinsusca
 */
public class DailyReportT2 {
    Document games, lines;
    Elements allTodaysGames, linesML, pitchers;
    
    String nameFile = "dailyReport";
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	 File theFile = new File(nameFile + "_" + date + ".txt"); 

	 PrintWriter printWriter; 
	 FileWriter fileOut; 
            
    public static void main(String[] args) throws IOException { 
    
        DailyReportT2 theDay = new DailyReportT2(); 
        
        
            theDay.games = Jsoup.connect("https://www.rotowire.com/hockey/nhl_lineups.htm").userAgent("mozilla/17.0").get();
            
            // looks like ?odds=Stats has the earliest lines.
            theDay.lines = Jsoup.connect("https://www.rotowire.com/hockey/nhl-odds.php?odds=Stats").userAgent("mozilla/17.0").get(); 
            
            theDay.createFile();
            theDay.headerinfo();
            theDay.closeFile();
            
            
        //    theDay.pitcherInfo(gameNumber);
  //          theDay.fileOut.close();
  //          theDay.printWriter.close();
    }
    
    
    public void createFile() throws IOException{
    	fileOut = new FileWriter("C:\\Users\\Gavin.Susca\\Desktop\\bass\\" + theFile); 
    	printWriter = new PrintWriter(fileOut); 
    }
    
    public void closeFile() throws IOException{
    	fileOut.close();
        printWriter.close();
    }
    
    
    
    public void lineup(){}
    
    public void playerInfo(String playerID){}
    
    
    
    
    public void pitcherInfo(int gameNumber){
    	pitchers = games.select("div.span15").select("div.span15").select("div.dlineups-half"); 
		Element thePitcher;
	
		
		
			
			if(gameNumber==1){
				
				//First pitcher - with ID
				thePitcher = pitchers.get(gameNumber-1); 
				System.out.print(" " + thePitcher.getElementsByTag("a").eachText());
				
                //where you will send PitcherID to get pitcherStats
                String pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats
                
                
                //Second pitcher - with ID
                thePitcher = pitchers.get(gameNumber); 
                System.out.println("\t vs ");
                System.out.print(" " + thePitcher.getElementsByTag("a").eachText());
                
                //where you will send PitcherID to get pitcherStats
                pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats
                
                          
			} else {
				
				
				//First pitcher - with ID
				thePitcher = pitchers.get((gameNumber - 1)*8); 
				System.out.print(" " + thePitcher.getElementsByTag("a").eachText());
				
				//where you will send PitcherID to get pitcherStats
				String pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats

                
                
                //Second pitcher - with ID
				thePitcher = pitchers.get((gameNumber - 1)*8 +1); 
				System.out.println("\t vs ");
				System.out.print(" " + thePitcher.getElementsByTag("a").eachText());

				//where you will send PitcherID to get pitcherStats
				pitcherID = " " + thePitcher.getElementsByTag("a").get(0).attr("href");
                pitcherID = pitcherID.substring(pitcherID.indexOf('=') + 1);	
                System.out.println(pitcherID); 			//don't need this
                //pitcherStats(pitcherID); 
                //where you will send PitcherID to get pitcherStats

			}
    	
    	
    	
    }//end of pitcherInfo
    
    
    
    
    
    
    public void headerinfo() throws IOException{
     
        
        allTodaysGames = games.select("div.span15.dlineups-topbox");
        int j = 0;
        for (Element today:allTodaysGames){ 
            j++;
	    System.out.println();
	    System.out.println();
            System.out.println("Game " + j); 
            printWriter.println("Game " + j); 
            
            System.out.println(today.getElementsByTag("div").first().text());
            printWriter.println(today.getElementsByTag("div").first().text()); 
            
            System.out.println("------------------- ");	
            printWriter.println("------------------- "); 
            System.out.println("------------------- ");	
            printWriter.println("------------------- "); 
           moneyLines(j);
           overUnder(j-1); 
           pitcherInfo(j); 
            
        }//end of for loop
    }//end of headerInfo
    
    
    
    
    public void overUnder(int overUnder){
    //	overUnder = overUnder +1; 
    	
    	for (Element names:linesML){ 
    	if (overUnder ==0){
    			System.out.println(" Over/Under " + names.getElementsByTag("td").get(5).text() + " runs.");
    	} else{
    			overUnder = overUnder +1; 
    			System.out.println(" Over/Under " + names.getElementsByTag("td").get((overUnder*5)+(overUnder-1)).text() + " runs.");  //logic for string line
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
    
    public void weather(){}
    //city info??
    
    
}