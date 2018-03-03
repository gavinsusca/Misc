




import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.select.*; 
import org.jsoup.nodes.*; 
import org.jsoup.*;


public class Soup2 {

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
    	
    								//?odds=Stats
    	
    	Document doc3 = Jsoup.connect("https://www.rotowire.com/hockey/nhl-odds.php?odds=Stats").userAgent("mozilla/17.0").get(); 
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
	
		
		
		//Document doc4 = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=6739").userAgent("mozilla/17.0").get(); 
		Document doc4 = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=11179").userAgent("mozilla/17.0").get(); 
		//Document doc4 = Jsoup.connect("https://www.rotowire.com/baseball/player.htm?id=11438").userAgent("mozilla/17.0").get(); 
		
   	Elements playerStats = doc4.select("div#gamelog.span49.gamelog-box"); //worked for long string
   	
   	
//    	Elements playerStats = doc4.select("div#gamelog.span49.gamelog-box").select("table.tablesorter").select("tbody"); //worked for long string
//    	Elements playerStats = doc4.select("tr.g17");
		
		
   	
 //String getID = "<a style="font-size:13px;padding-left:1px;" href="/hockey/player.htm?id=1671">Kari Lehtonen</a>"; 
   	
   	
   	
   	
   	
   	

 
   	//Elements handed = doc4.select("div.span49.mlb-player-otherinfo").select("b");
   	Elements handed = doc4.select("div.span49.mlb-player-otherinfo").select("b");
   	
   	System.out.println(handed.get(2).childNodes().get(0));
   	
   	System.out.print(handed.get(2).nextSibling());  ///This is what you want! 
   	
 for (Element getHand:handed){
	 
	 
//	 Element getHand; 
	 //System.out.print(getHand.getElementsByTag("b").eachText());
//	System.out.println(getHand.ownText());

//	System.out.print(getHand.nextSibling());
	//System.out.println(handed.first().childNodes().get(1));
	
	
	
	

//	 System.out.print(getHand.getElementsByTag("b").html()); 
//	 System.out.print(getHand.getElementsByTag("b")); 
	// System.out.print(getHand.getElementsByTag("b").html()); 
	//System.out.print(getHand.getElementsByTag("b").html()); 
	 

 }
 
 
 
 
 
 
 
 
 
 
 
 
    	
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
  //   String playerID = "12058"; 
     String playerID = "11179"; 
    // 11179

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
              
                theStats = last4Games + "vP(add|Stats|here)-" + homeStats + awayStats + vsLeft + vsRight;
                System.out.println(theStats);
                System.out.println();
                System.out.println();
      
            }//end of hitter Stat line
            //end of splits for a player
   
    
    
    
    
    
    
    
  //This gives you the current year. or any year you choose. 
  //This gives you the current year. or any year you choose. 
  //This gives you the current year. or any year you choose. 
    
    
    int gameLines = player.select("div#basicstats.span49.mlb-player-basicstatsbox").select("tr").size();
    playerStats = player.select("div#basicstats.span49.mlb-player-basicstatsbox").select("tr").select("td"); //worked for long string

    for (int rr =0; rr< gameLines-1; rr++){
    //	System.out.println("Entire List" +playerStats.eachText());
    	
    	//if(playerStats.get(rr*25).text().matches("2016") && playerStats.get(rr*25 +2).text().matches("MAJ")){
    	if(playerStats.get(rr*25).text().matches("2018 Spring Training")){
 
    		System.out.println(playerStats.get(rr*25)); 
        	System.out.println(playerStats.get(rr*25 + 1)); 
        	System.out.println(playerStats.get(rr*25 + 2));
        	System.out.println(playerStats.get(rr*25 + 3));
        	System.out.println(playerStats.get(rr*25 + 4));  //G
        	System.out.println(playerStats.get(rr*25 + 5)); //PA
        	System.out.println(playerStats.get(rr*25 + 6)); //AB
        	System.out.println(playerStats.get(rr*25 + 7)); 
        	System.out.println(playerStats.get(rr*25 + 8)); 
        	System.out.println(playerStats.get(rr*25 + 9)); //XBH
        	System.out.println(playerStats.get(rr*25 + 10));
        	System.out.println(playerStats.get(rr*25 + 11));	
        	System.out.println(playerStats.get(rr*25 + 12)); // HRs
        	System.out.println(playerStats.get(rr*25 + 13));
        	System.out.println(playerStats.get(rr*25 + 14));
        	System.out.println(playerStats.get(rr*25 + 15));
        	System.out.println(playerStats.get(rr*25 + 16));  
        	System.out.println(playerStats.get(rr*25 + 17)); // SO's
        	System.out.println(playerStats.get(rr*25 + 19));
        	System.out.println(playerStats.get(rr*25 + 20));
        	System.out.println(playerStats.get(rr*25 + 21));  //AVG
        	System.out.println(playerStats.get(rr*25 + 22));
        	System.out.println(playerStats.get(rr*25 + 23));
        	System.out.println(playerStats.get(rr*25 + 24));
        	
        	String totalStats = "";
        	
        	// SO = ((Integer.parseInt(playerStats.get(rr*25 + 17).text()))/(Integer.parseInt(playerStats.get(rr*25 + 5).text())))*100;
        	
        	//determine K rate by dividing to find a float then convert back to an int
        	int percent = (int)((float)(Integer.parseInt(playerStats.get(rr*25 + 17).text()))/(Integer.parseInt(playerStats.get(rr*25 + 5).text()))* 100);

        	
        	totalStats = "-T:("+ playerStats.get(rr*25 + 21).text() +"|" + playerStats.get(rr*25 + 12).text() + "HR|" + playerStats.get(rr*25 + 9).text() +"EBH|" + percent + "%K|" + playerStats.get(rr*25 + 6).text() +"AB" + ")";
        	System.out.println(totalStats); 
        	
        	theStats = theStats + totalStats;
        	System.out.println(theStats); 
    	}
    	
    	
    }
  //This gives you the current year. or any year you choose. 
  //This gives you the current year. or any year you choose. 
    
    
    
    
    
    /*
    for (Element hitterHanded:playerStats){ 
  // for (Element hitterHanded:playerStats){ 
    
    	//Element hitterHanded = playerStats.get(0);
    	System.out.println("gg"); 
    	int MLs = playerStats.size();
    	System.out.println(MLs);
    	
    //	if (hitterHanded.getElementsByTag("td").get(2).text().matches("MAJ")){  }
    
    	
    	
    	
    //	if (hitterHanded.getElementsByTag("td").get(0).text().matches("2017")){ }
    	//System.out.println(hitterHanded.getElementsByTag("tr").get(0).text()); 
    	
    	System.out.println(hitterHanded.getElementsByTag("td").eachText()); 
    	System.out.println(hitterHanded.getElementsByTag("td").eachText()); 
    	
    	
    	
    //	System.out.println(hitterHanded.getElementsByTag("td").get(3).text()); 
    	
    //	System.out.println(hitterHanded.getElementsByAttributeValue("style", "display:none").eachText());
    	
    	
    	
   /* 	
    	System.out.println(hitterHanded.getElementsByTag("tr").get(0).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(2).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(3).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(4).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(5).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(6).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(7).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(8).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(9).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(10).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(11).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(12).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(13).text());
    	System.out.println(hitterHanded.getElementsByTag("tr").get(14).text());   */ 
    	
    		
    	
    	
    //}
    
            
   //  */ 






















		
		
		
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
		//	for (int r = 2,  lineSize = linesML.size(); r < 10; r++ ){	
			for (int r = 1,  lineSize = linesML.size(); r < 5; r++ ){	
				// Element ww;
				
			//	int SL = names.getElementsByTag("td").size();
			//	System.out.println(lineSize +" Each " + names.getElementsByTag("td").eachText());
			
				
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
		
		
		
		
		
		
		
		
		
		
		
 //  		/* 
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
				//
				if(j==1){
				ss = pitchers.get(k); 
				System.out.print(" " + ss.getElementsByTag("a").eachText());
				String pitcherID33 = " " + ss.getElementsByTag("a").get(0).attr("href");
				pitcherID33 = pitcherID33.substring(pitcherID33.indexOf('=') + 1);				//loading the pitcherID.
				System.out.println(pitcherID33.substring(pitcherID33.indexOf('=') + 1));		//dont need this to be printed.
				ss = pitchers.get(k+1); 
				//System.out.println(" vs " + ss.getElementsByTag("a").eachText());
				System.out.println("\t vs ");
				System.out.print(" " + ss.getElementsByTag("a").eachText());
				 pitcherID33 = " " + ss.getElementsByTag("a").get(0).attr("href");
				System.out.println(" " + pitcherID33.substring(pitcherID33.indexOf('=') + 1));
				}
				else {
				ss = pitchers.get((j - 1)*8); 
				System.out.print(" " + ss.getElementsByTag("a").eachText());
				
				//System.out.println(" " + ss.getElementsByTag("a").get(0).attr("href"));
				String pitcherID33 = " " + ss.getElementsByTag("a").get(0).attr("href");
				System.out.println(" " + pitcherID33.substring(pitcherID33.indexOf('=') + 1));
				
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
			//	System.out.print(" " + ss.getElementsByTag("a").eachText());
				System.out.print("ddd " + ss.getElementsByTag("a").text());
				
				pitcherID33 = " " + ss.getElementsByTag("a").get(0).attr("href");
				System.out.println(" " + pitcherID33.substring(pitcherID33.indexOf('=') + 1));
				}
					
//				System.out.println(); 
//				dailyReport.showLineup(); 
 
				
				/*					
				
				//insert 
				//shows lineup for the day
				for (int p =0; p < 1; p++ ){
					Element gt = pitchers.get(2);
				
				//	 int sinz =  gt.getElementsByTag("a").size();
			//		 System.out.println("sinz" + sinz); 
						
					System.out.println("-------------------------------------"); 
						//by amount of players.
					for (int f =0; f < 9; f++){
						gt = pitchers.get(2); 
						
						//int sinz = pitchers.size(); 
						 // System.out.println("sinz" + sinz);
						 
						 
			//before name substring	System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t");
						//gives you only the first 8 of the last name. for pitchers could increase
						String lastName = (" " + gt.getElementsByTag("a").get(f).text());
						int lengthL = lastName.substring(lastName.lastIndexOf(' ')).length();
						if(lengthL > 10){
							lengthL = 10; 
						}
		//				System.out.print(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
						lastName =(" " + lastName.substring(lastName.lastIndexOf(' '), (lastName.lastIndexOf(' ') + lengthL)));
						System.out.print(String.format("%-11s", lastName)); 
//gives you the output you need to get the playerID							System.out.print(" " + gt.getElementsByTag("a").get(f) + " \t \t");
	//					System.out.print(" " + gt.getElementsByTag("a").get(f).attr("href") + " \t \t");
						
						String playerID33 = " " + gt.getElementsByTag("a").get(f).attr("href") + " \t \t";		
						System.out.print(" " + playerID33.substring(playerID33.lastIndexOf('=')+1));
						
						gt = pitchers.get(3); 
						System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t"); 
						
						playerID33 = " " + gt.getElementsByTag("a").get(f).attr("href") + " \t \t";		
						System.out.println(" " + playerID33.substring(playerID33.lastIndexOf('=')+1));
						
					}//end of forLoop Lineup	
                                        
                                        
				
					System.out.println("-------------------------------------"); 
				}
				//end show lineup
				
				//end of insert
				
				*/				
				
				
				System.out.println(); 
				
			} //end of pitchers loop.
		
			
		}// end of for loop 
	//	  	*/		
		
		
	











		//todays game adjusted 
   		/* 
		//Todays game 
		//goalies. 
		// lineups
	  	for (Element today:games){ 
	  		j++;	
	  		System.out.println();
			System.out.println("Game " + j); 
		
			System.out.println(today.getElementsByTag("div").first().text()); //Game (
			

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
						gt = pitchers.get(3);  //<----- This is where i need to change to get the proper lineup to grab
					//	gt = pitchers.get(10);  //<----- This is second game lineup 1
					//	gt = pitchers.get(11);  //<----- This is second game lineup 2


						System.out.print(" " + gt.getElementsByTag("a").get(f).text() + " \t \t"); 
                                                System.out.print(" " + gt.getElementsByTag("a").get(f).attr("href") + " \t \t\n");
	
                    				}//end of Lineup2
                                        
                                        
                                        System.out.println(pitchers.eachText());       //here is the line for all the correct players
				
					System.out.println("-------------------------------------"); 
				}
				//end show lineup
				
				//end of insert
				
				
				
				
				System.out.println(); 

				
			} //end of pitchers loop.
		
			

		}// end of for loop 
		
	  	*/		



















                
                
                
		
		
		
		
		
		
		
		
	
		
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