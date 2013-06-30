package stats;

public class init{
	
	public static void initba(){
		
		for (int i = 0; i<stats.before.length; i++){stats.before[i] = "";}
		for (int i = 0; i<stats.after.length; i++){stats.after[i] = "";}
		for (int i = 0; i<stats.st.length; i++){stats.st[i] = "";}
		
		stats.before[1] = "<html>";
		stats.before[2] = "<head>";
		stats.before[3] = "<script type=^text/javascript^ src=^https://www.google.com/jsapi^></script>";
		stats.before[4] = "<script type=^text/javascript^>";
		stats.before[5] = "google.load(^visualization^, ^1^, {packages:[^corechart^]});";
		stats.before[6] = "google.setOnLoadCallback(drawChart);";
		stats.before[7] = "function drawChart() {";
		stats.before[8] = "var data = google.visualization.arrayToDataTable([";
		stats.before[9] = "['Step', 'BTC/USD', 'BTC/RUR', 'BTC/EUR', 'LTC/BTC',  'LTC/USD', 'LTC/RUR', 'NMC/BTC', 'NVC/BTC', 'USD/RUR', 'EUR/USD', 'TRC/BTC', 'PPC/BTC', 'FTC/BTC'],";
		
		stats.after[1] = "]);";
		stats.after[2] = "var options = {";
		stats.after[3] = "title: 'BTC-e sell price chart'";
		stats.after[4] = " };";
		stats.after[5] = "var chart = new google.visualization.LineChart(document.getElementById('chart_div'));";
		stats.after[6] = "chart.draw(data, options);";
		stats.after[7] = "}";
		stats.after[8] = "</script>";
		stats.after[9] = "</head>";
		stats.after[10] = "<body>";
		stats.after[11] = "<div id=^chart_div^ style=^width: 1500px; height: 750px;^></div>";
		stats.after[12] = "</body>";
		stats.after[13] = "</html>";
				
		stats.before[3] = stats.before[3].replace('^', '"');
		stats.before[4] = stats.before[4].replace('^', '"');
		stats.before[5] = stats.before[5].replace('^', '"');
 
		stats.after[11] = stats.after[11].replace('^', '"');

	}

}