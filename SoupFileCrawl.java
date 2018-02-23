package baseball;




import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.select.*; 
import org.jsoup.nodes.*; 
import org.jsoup.*;


public class SoupFileCrawl {

//	Elements temp, games, pitchers, awayTeam;
//	Document doc; 
    static String last4Games, theStats;
	
    public static void main(String[] args) throws IOException {
    	
    	
//    	/*
    	
    	SoupFileCrawl dailyReport = new SoupFileCrawl(); 
	
    	Document doc = Jsoup.connect("https://www.rotowire.com/hockey/nhl_lineups.htm").userAgent("mozilla/17.0").get(); 
    
    	//stats
    	Document doc2 = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=12713").userAgent("mozilla/17.0").get(); 
    	Elements stats = doc2.select("table.tablesorter");
 //   	Elements stats = doc2.select("div#gamelog.span49.gamelog-box");
 //   	Elements stats = doc2.select("div.span49.gamelog-head");
    	
    	
    	
    	Document doc3 = Jsoup.connect("https://www.rotowire.com/hockey/nhl-odds.php").userAgent("mozilla/17.0").get(); 
//    	Elements linesML = doc3.select("div.span49");
   // 	Elements linesML = doc3.select("table.blines-table.headerfollows.tablesorter.tablesorter-default.hasStickyHeaders"); //didnt work
    	Elements linesML = doc3.select("div.row").select("tbody");
    			//select("table.blines-table.headerfollows.tablesorter.tablesorter-default.hasStickyHeaders");
    	
    	
		Elements temp = doc.select("div.span15");
		Elements games = doc.select("div.span15.dlineups-topbox");
		Elements pitchers = doc.select("div.span15").select("div.span15").select("div.dlineups-half"); 
		Elements awayTeam = doc.select("div.dlineups-vplayer");
	//	Elements infoLists = doc.select("div.span15").select("div.span15").select("div.dlineups-half");  
//		
		/*
		Document doc = Jsoup.connect("https://www.rotowire.com/hockey/nhl_lineups.htm").userAgent("mozilla/17.0").get();
		dailyReport.doc = Jsoup.connect("https://www.rotowire.com/hockey/nhl_lineups.htm").userAgent("mozilla/17.0").get(); 
		dailyReport.temp = doc.select("div.span15");
		dailyReport.games = doc.select("div.span15.dlineups-topbox");
		dailyReport.pitchers =  doc.select("div.span15").select("div.span15").select("div.dlineups-half");
		dailyReport.awayTeam = doc.select("div.dlineups-vplayer");
				
		*/
		int i = 0; 	
		int j = 0;

		//gets the player stats
	
		
		
		Document doc4 = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=6739").userAgent("mozilla/17.0").get(); 
   	Elements playerStats = doc4.select("div#gamelog.span49.gamelog-box"); //worked for long string
//    	Elements playerStats = doc4.select("div#gamelog.span49.gamelog-box").select("table.tablesorter").select("tbody"); //worked for long string
//    	Elements playerStats = doc4.select("tr.g17");
		
		
   	
 //String getID = "<a style="font-size:13px;padding-left:1px;" href="/hockey/player.htm?id=1671">Kari Lehtonen</a>"; 
   	
   	
   	
   	
   	
   	

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    	
 //   	/*
    	
 
 //game log section
    	//gets player stats.
   	//last 4 games for each player
   	// still need to manipulate the url to get the player id correctly 
   	//which is under holy shit good to know part
    	for (Element names:playerStats){ 
			i++; 
//			int ML = names.getElementsByTag("td").size();
			
			
//error?			System.out.println(" " + names.getElementsByTag("td").eachText()); 
			
//gets whole list
//		System.out.println(" " + names.getElementsByTag("tr").eachText()); 
		
//gets first of the list.. etc		

		System.out.println();
		
		
		// need to add exception control for when player "did not Play" 
		int AB =0;
		int H =0;
		double TB_ISO =0;
		float AVG = 0; 
		float ISO = 0;   // handle display decimals 
		int SO =0;
	 	
	
		
		
	//	AB = AB + Integer.parseInt(names.getElementsByTag("td").get(2).text()); 
	//	AB = AB + Integer.parseInt(names.getElementsByTag("td").get(22).text()); 
//		AB = AB + Integer.parseInt(names.getElementsByTag("td").get(42).text());
//		System.out.println(" " + names.getElementsByTag("td").get(2).text()); 
//System.out.println(" " + names.getElementsByTag("td").get(42).text()); // Throws error because equals "Did not play"
//		System.out.println("ABs: " + AB); 
		
		
		try {
			
			for (int ee = 1; ee < 5; ee++){
				if (ee == 1){
					AB = AB + Integer.parseInt(names.getElementsByTag("td").get(2).text());
					H = H + Integer.parseInt(names.getElementsByTag("td").get(4).text());
//					TB_ISO = TB_ISO + (Float.parseFloat(names.getElementsByTag("td").get(5).text()) * 1.5 or *2) + (Float.parseFloat(names.getElementsByTag("td").get(6).text()) * 1.5 or *2) + (Float.parseFloat(names.getElementsByTag("td").get(7).text()) * 2.2 or *4);
					TB_ISO = TB_ISO + (Float.parseFloat(names.getElementsByTag("td").get(5).text()) * .67) + (Float.parseFloat(names.getElementsByTag("td").get(6).text()) * .67) + (Float.parseFloat(names.getElementsByTag("td").get(7).text()));
					SO = SO + Integer.parseInt(names.getElementsByTag("td").get(10).text());
				}
				else {
					AB = AB + Integer.parseInt(names.getElementsByTag("td").get((((ee-1)*20)+2)).text()); 
					H = H + Integer.parseInt(names.getElementsByTag("td").get((((ee-1)*20)+4)).text());
					TB_ISO = TB_ISO + ((Integer.parseInt(names.getElementsByTag("td").get((((ee-1)*20)+5)).text())) * .67) + ((Integer.parseInt(names.getElementsByTag("td").get((((ee-1)*20)+6)).text())) * .67) + ((Integer.parseInt(names.getElementsByTag("td").get((((ee-1)*20)+7)).text())));
					SO = SO + Integer.parseInt(names.getElementsByTag("td").get((((ee-1)*20)+10)).text());
				}
			}
			
				
		} 
		catch (Exception e){
			
		}
		
		
		// System.out.printf("%.2f", val); 
		
		
		AVG = (float)H/AB; 
		
		ISO = (float)TB_ISO/AB; 
	
           /*     
		//AVG.replaceFirst("^0.", ".");
		System.out.println("last 4 games had " + AB + " at bats."); 
		System.out.println("last 4 games had " + H + " hits."); 
		System.out.println("last 4 games had " + SO + " strikeouts."); 
		System.out.println("last 4 games had " + TB_ISO + " tb_iso.");
		System.out.printf("last 4 games had %.3f avg. \n", AVG);
		System.out.printf("last 4 games had %.3f d_iso. \n", ISO);
            */	
                
		last4Games ="L4:"; 
		
	//	System.out.printf("(%dABs|", AB); 
	//	last4Games = last4Games + "|" + AB + "ABs)"; 
		String battingAverage = String.format("(%.3f" , AVG); 
		last4Games = last4Games + "("+ battingAverage.substring(battingAverage.indexOf('.')) + "|";
		String battingD_ISO = String.format("%.3f" , ISO); 
		last4Games = last4Games + battingD_ISO.substring(battingD_ISO.indexOf('.')) + "|"; 
		last4Games = last4Games + SO + "SO"; 
		last4Games = last4Games + "|" + AB + "ABs)-";
		
	//	System.out.println("last 4 games had a " + ISO + " iso.");
		System.out.println();
		System.out.println("last 4 string " +last4Games);
		System.out.println();
		
		System.out.println(" " + names.getElementsByTag("tr").get(0).text());
		System.out.println(" " + names.getElementsByTag("tr").get(1).text());
		System.out.println(" " + names.getElementsByTag("tr").get(2).text());
		System.out.println(" " + names.getElementsByTag("tr").get(3).text());
		System.out.println(" " + names.getElementsByTag("tr").get(4).text());
		System.out.println(" " + names.getElementsByTag("tr").get(5).text()); 
		System.out.println(" " + names.getElementsByTag("tr").get(6).text());
		System.out.println(" " + names.getElementsByTag("tr").get(7).text());
		
//list of all divs. 	
 //error?               System.out.println(" " + names.getElementsByTag("div").eachText());
//error?		System.out.println(" " + names.getElementsByTag("div").eachText());
//select from list		
	//	System.out.println(" " + names.getElementsByTag("div").first().text());
		

    	}
		//end of stats section

//    	*/
		
		








//      /* 
     
    //splits for a player section 
     String playerID = "12058"; 


    Document player = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=" + playerID).userAgent("mozilla/17.0").get(); 
    playerStats = player.select("div#splitstats.span49.mlb-player-splitsbox"); //worked for long string


    for (Element hitterStat:playerStats){ 
	
                String homeStats = "";
                String awayStats = "";
                String vsLeft = "";
                String vsRight = "";

		System.out.println("-------------");


                System.out.println();
     
                vsLeft = "-VL:(" + hitterStat.getElementsByTag("td").get(6).text() + "|" + hitterStat.getElementsByTag("td").get(3).text() + "HRs|" + hitterStat.getElementsByTag("td").get(1).text() + "ABs)" ;
                vsRight = "/VR:("  + hitterStat.getElementsByTag("td").get((27*1)+6).text() + "|" + hitterStat.getElementsByTag("td").get((27*1)+3).text() + "HRs|" + hitterStat.getElementsByTag("td").get((27*1)+1).text() + "ABs)";
                homeStats = "H:("  + hitterStat.getElementsByTag("td").get((27*2)+6).text() + "|" + hitterStat.getElementsByTag("td").get((27*2)+3).text() + "HRs|" + hitterStat.getElementsByTag("td").get((27*2)+1).text() + "ABs)";
                awayStats = "/A:("  + hitterStat.getElementsByTag("td").get((27*3)+6).text() + "|" + hitterStat.getElementsByTag("td").get((27*3)+3).text() + "HRs|" + hitterStat.getElementsByTag("td").get((27*3)+1).text() + "ABs)";
              
                String theStats = last4Games + "vP(add|Stats|here)-" + homeStats + awayStats + vsLeft + vsRight;
		System.out.println(theStats);
                System.out.println();
                System.out.println();
      
            }//end of hitter Stat line
            //end of splits for a player
            
//      */ 






















		
		
		
//		/*	
		// gives money lines. odds and over/under for todays games
	
	 	for (Element names:linesML){ 
			i++; 
			System.out.println();
                       
		//	System.out.println(i + " " + names.getElementsByClass("td.align0l.bl-d").eachText());
		//	System.out.println(i + " " + names.getElementsByTag("tr").first().text());
		//	System.out.println(i + " " + names.getElementsByTag("img").first().text());
		//	System.out.println(i + " " + names.getElementsByTag("td").eachText()); 	// worked with---  Elements linesML = doc3.select("div.span49");
//			System.out.println(i + " " + names.getElementsByTag("tr").eachText());
			// linesML.get(0); 
			int ML = names.getElementsByTag("td").size();
			System.out.println(ML); 
//Error?			System.out.println(i + " " + names.getElementsByTag("td").eachText());
			
			System.out.println(i + " " + names.getElementsByTag("td").get(0));   ///holy shit! good to know.
			System.out.println(i + " " + names.getElementsByTag("td").get(3));	///holy shit! good to know.	
			System.out.println(i + " " + names.getElementsByTag("td").get(4));
			
			
			System.out.println(" Over/Under " + names.getElementsByTag("td").get(5).text() + " runs.");
			System.out.println(" " + names.getElementsByTag("td").get(4).text() + " +" + names.getElementsByTag("td").get(3).text() );
			System.out.println(" " + names.getElementsByTag("td").get(1).text() + " " + names.getElementsByTag("td").get(2).text() );
			System.out.println(" Over/Under " + names.getElementsByTag("td").get(5).text() + " runs.");
			
			
		//	System.out.println(i + " " + names.getElementsByAttribute("td").eachText());
		//	System.out.println(i + " " + names.getElementsByTag("tr").eachText());
			
		//for loop that captures all the game odds and run Lines
		
			System.out.println(" ------------------- ");		
			for (int r = 2,  lineSize = linesML.size(); r < 10; r++ ){	
				// Element ww;
	
			//	ww = names.get((r - 1)*5 +1);  
				System.out.println(" Over/Under " + names.getElementsByTag("td").get((r*5)+(r-1)).text() + " runs.");  //logic for string line
			
		//		System.out.println(" " + names.getElementsByTag("td").get(4).text() + " +" + names.getElementsByTag("td").get(3).text() );
		//		System.out.println(" " + names.getElementsByTag("td").get(1).text() + " " + names.getElementsByTag("td").get(2).text() );

			
			} 
			
			
		//	System.out.println(" : " + linesML.get(0).text()); 
		//	System.out.println(" : " + linesML.get(0).text()); 
		}
	
//	*/
		
		
		
		
		//The info based on games and names. 
	/*	
	 	for (Element names:temp){ 
			i++; 
			
			
		//	System.out.println(i + " " + names.getElementsByTag("div").first().text());
			System.out.println(i + " " + names.getElementsByTag("div").eachText());
			
		
		}
	
		*/
		
	/*	
                for (int k =0; k < pitchers.size(); k++ ){
			Element gt = pitchers.get(k); 
			System.out.println(i + " " + gt.getElementsByTag("a").eachText());  
		}
		
	*/
		
		
		
		
		
		
		
		
		
		
		
   		/* 
		//Todays game 
		//goalies. 
		// lineups
	  	for (Element today:games){ 
	  		j++;	
	  		System.out.println();
			System.out.println("Game " + j); 
		
			System.out.println(today.getElementsByTag("div").first().text());
			

			for (int k = 0,  pitchLength = pitchers.size(); k < 1; k++ ){	
				Element ss;
				
			//	System.out.println(pitchLength + " is the pitchLength"); 
				
				if(j==1){
				ss = pitchers.get(k); 
				System.out.print(" " + ss.getElementsByTag("a").eachText());
				ss = pitchers.get(k+1); 
				System.out.println(" vs " + ss.getElementsByTag("a").eachText());}
				else {
				ss = pitchers.get((j - 1)*8); 
				System.out.println(" " + ss.getElementsByTag("a").eachText());

				
				System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));
		//gives you the output you need to get the pitcherID		
				
//				System.out.println(" " + ss.getElementsByAttribute("href").get(0));
//				System.out.println(" " + ss.getElementsByTag("a").get(0).outerHtml().substring(72));
//				System.out.println(" " + ss.getElementsByTag("a").get(0).outerHtml().substring(72, 76));  //gives you playerID if string format never changes
//				System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));
//			//	System.out.println(" " + ss.select("href").get(0).data());
//				System.out.println(" " + ss.getElementsByAttribute("href").get(0).wholeText());
			//	System.out.println(" " + ss.getElementById("id").text());
			
				
				
				
				ss = pitchers.get((j - 1)*8 +1); 
				System.out.println("\t vs ");
				System.out.println(" " + ss.getElementsByTag("a").eachText());
				}
					
//				System.out.println(); 
//				dailyReport.showLineup(); 
 
				//insert 
				//shows lineup for the day
				for (int p =0; p < 1; p++ ){
					Element gt = pitchers.get(0);
				
//					 int sinz =  gt.getElementsByTag("a").size();
					
						
					System.out.println("-------------------------------------"); 
						//by amount of players.
					for (int f =0; f < 9; f++){
						gt = pitchers.get(2); 
						System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t");
//gives you the output you need to get the playerID							System.out.print(" " + gt.getElementsByTag("a").get(f) + " \t \t");
						System.out.print(" " + gt.getElementsByTag("a").get(f).attr("href") + " \t \t");
						gt = pitchers.get(3); 
						System.out.println(" " + gt.getElementsByTag("a").get(f).text()); 
						
						
					}//end of forLoop Lineup	
                                        
                                        
				
					System.out.println("-------------------------------------"); 
				}
				//end show lineup
				
				//end of insert
				
				
				
				
				System.out.println(); 

				
			} //end of pitchers loop.
		
			

		}// end of for loop 
		  	*/		
		
		
	











		//todays game adjusted 
//   		/* 
		//Todays game 
		//goalies. 
		// lineups
	  	for (Element today:games){ 
	  		j++;	
	  		System.out.println();
			System.out.println("Game " + j); 
		
			System.out.println(today.getElementsByTag("div").first().text());
			

			for (int k = 0,  pitchLength = pitchers.size(); k < 1; k++ ){
				Element ss;
				
			//	System.out.println(pitchLength + " is the pitchLength"); 
				
				if(j==1){
				ss = pitchers.get(k); 
				System.out.print(" " + ss.getElementsByTag("a").eachText());
                                System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));
				ss = pitchers.get(k+1); 
                                System.out.println("\t vs ");
				System.out.print(" " + ss.getElementsByTag("a").eachText());
                                System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));}
				else {
				ss = pitchers.get((j - 1)*8); 
				System.out.print(" " + ss.getElementsByTag("a").eachText());

				
				System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));
		//gives you the output you need to get the pitcherID		
				
//				System.out.println(" " + ss.getElementsByAttribute("href").get(0));
//				System.out.println(" " + ss.getElementsByTag("a").get(0).outerHtml().substring(72));
//				System.out.println(" " + ss.getElementsByTag("a").get(0).outerHtml().substring(72, 76));  //gives you playerID if string format never changes
//				System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));
//			//	System.out.println(" " + ss.select("href").get(0).data());
//				System.out.println(" " + ss.getElementsByAttribute("href").get(0).wholeText());
			//	System.out.println(" " + ss.getElementById("id").text());
			
				
				
				
				ss = pitchers.get((j - 1)*8 +1); 
				System.out.println("\t vs ");
				System.out.print(" " + ss.getElementsByTag("a").eachText());
                                System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));
				}
					
//				System.out.println(); 
//				dailyReport.showLineup(); 
 
				//insert 
				//shows lineup for the day
				for (int p =0; p < 1; p++ ){
					Element gt = pitchers.get(0);
				
//					 int sinz =  gt.getElementsByTag("a").size();
					
						
					System.out.println("-------------------------------------"); 
						//by amount of players.
                                                System.out.println("Lineup1");
					for (int f =0; f < 9; f++){
						gt = pitchers.get(2); 
						System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t");
						System.out.print(" " + gt.getElementsByTag("a").get(f).attr("href") + " \t \t\n");

						
						
					}//end of Lineup1
                                        System.out.println("--------");
                                       
                                        System.out.println("Lineup2");
                                        for (int f =0; f < 9; f++){
						gt = pitchers.get(3); 
						System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t"); 
                                                System.out.print(" " + gt.getElementsByTag("a").get(f).attr("href") + " \t \t\n");
	
                    				}//end of Lineup2
                                        
                                        
                                        
				
					System.out.println("-------------------------------------"); 
				}
				//end show lineup
				
				//end of insert
				
				
				
				
				System.out.println(); 

				
			} //end of pitchers loop.
		
			

		}// end of for loop 
		
//	  	*/		



















                
                
                
		
		
		
		
		
		
		
		
	
		
	/*	
			for (int k =0; k < 1; k++ ){
				Element gt = pitchers.get(2); 

				
				System.out.println("   players are this many " + gt.getElementsByTag("a").size());
				System.out.println(); 
				System.out.println(" " + gt.getElementsByTag("a").eachText());  
				System.out.println(" " + gt.getElementsByTag("a").get(4).text()); 
				
				gt = pitchers.get(3); 
				System.out.println(" " + gt.getElementsByTag("a").eachText());  
				System.out.println(" " + gt.getElementsByTag("a").get(4).text()); 
				
				System.out.println("-------------------------------------"); 
			}
	*/		
			
		
	/*	
		//shows lineup for the day
			for (int k =0; k < 1; k++ ){
				Element gt = pitchers.get(0);
				
				 int sinz =  gt.getElementsByTag("a").size();
					System.out.println(sinz); 	

					//by amount of players.
				for (int f =0; f < 10; f++){
					gt = pitchers.get(2); 
					System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t"); 
				
					gt = pitchers.get(3); 
					System.out.println(" " + gt.getElementsByTag("a").get(f).text()); 
				}//end of forLoop Lineup				
			
				System.out.println("-------------------------------------"); 
			}
			//end show lineup
	*/		
		
			
    
}//end of main
    
    
    
   /* 
    public void showLineup(){
		//shows lineup for the day
		for (int k =0; k < 1; k++ ){
			Element gt = pitchers.get(0);
			
			
			 int sinz =  gt.getElementsByTag("a").size();
				System.out.println(sinz); 	
				
				
			System.out.println("-------------------------------------"); 
				//by amount of players.
			for (int f =0; f < 10; f++){
				gt = pitchers.get(2); 
				System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t"); 
			
				gt = pitchers.get(3); 
				System.out.println(" " + gt.getElementsByTag("a").get(f).text()); 
			}//end of forLoop Lineup				
		
			System.out.println("-------------------------------------"); 
		}
		//end show lineup
    } 
        */
    
    
    
    } //end of class 