<%@page import="analysis.*"%>
<!doctype html>
<!-- Darstellung der Auswertung der CSV dateien in Form von Diagrammen -->
<html lang="de">
	<head>     

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Verlinken der CSS Dateien -->
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/fontawesome-all.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">

		<!-- Hinzufügen der Skripte -->
		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.js"></script>

		<title>Kauf Dort - Analyse</title>
		
		<jsp:useBean id = "list" class = "analysis.AnalysisList" scope="session" />
		
	</head>
	<!-- Grundgerüst der Analyse Seite -->
	<body>
	<!-- Logo der Firma Kauf Dort -->
		<div class="wrapper">
			<div class="sidenav bg-light">
				<div class="sidebar-top">
					<img class="logo" src="img/kauf_dort_logo.png">
				</div>
				
				<!-- Menü -->
				<div class="sidebar-item sidebar-block">
					<a class="sidebar-link" href="DateiHochladen.jsp"><i class="fa fa-file"></i> Datei hochladen</a>
				</div>
				<div class="sidebar-block">
					<div class="sidebar-heading">Letzte Ergebnisse</div>
					<% for(int i=0;i<list.getList().size(); i++){ %>
					
						<form action="/TeamData/LoadAnalysis" method="post">
							<input type="hidden" name="index" value="<%= i %>">
							<div class="sidebar-item">
								<button type="submit" class="sidebar-link" style="background: none; color: inherit; border: none; display: block; width:100%; text-align: left;"><i class="fa fa-angle-right"></i> <%=(i+1) + ". " + list.getList().get(i).getName() %></button>
							</div>
						</form>
					
					<% } %>
				</div>
				<div class="sidebar-item sidebar-block">
					<a class="sidebar-link" href="/TeamData/Login.jsp"><i class="fa fa-power-off"></i> Logout</a>
				</div>
			</div>
		
			<!-- Begin der Analyse --> 
			<div class="main bg-white">
				<div class="content">
					<div>
						<h1><span class="badge badge-secondary">Ergebnisse der Analyse</span></h1>	
					</div>
					<!-- Erstes Diagram, Die top 5 am häufigsten gekauften Waren -->
					<ul class="list-group" style="padding-bottom: 20px;">
						<li class="list-group-item list-group-item-secondary">
							<h4>Top 5 am häufigsten gekaufte Waren</h4>
						</li>
						<% int index = (Integer)request.getSession().getAttribute("index"); %>
						<% Analysis analysis = list.getAnalysisAt(index); %>
						<% for(int i=0;i<analysis.getTopItems().size(); i++){ %>
						
							<li class="list-group-item">
								<h5><%= (i+1) + ". " + analysis.getTopItems().get(i) %></h5>
							</li>
						
						<% } %>
					</ul>

					<!-- Zweites Diagram, die Kundenanzahl am jeweiligen Wochentag -->
					<ul class="list-group" style="padding-bottom: 20px;">
						<li class="list-group-item list-group-item-secondary">
							<h4>Kundenanzahl am jeweiligen Wochentag</h4>
						</li>
						<li class="list-group-item">
							<img alt="" src="BarChartWeekday">
						</li>
					</ul>

					<!-- Drittes Diagram, die Kundenanzahl am jeweiligen Wochentag -->
					<ul class="list-group" style="padding-bottom: 20px;">
						<li class="list-group-item list-group-item-secondary">
							<h4>Kundenanzahl um die jeweiligen Uhrzeit</h4>
						</li>
						<li class="list-group-item">
							<img src="BarChartDaytime">
						</li>
					</ul>
					
					<!-- Viertes Diagram, Häufig zusammen gekaufte Waren -->
					<ul class="list-group" style="padding-bottom: 20px;">
						<li class="list-group-item list-group-item-secondary">
							<h4>Häufig zusammen gekaufte Waren</h4>
						</li>
						<% for(int i=0;i<analysis.getItemSets().size(); i++){ %>
						
							<li class="list-group-item">
								<h5><%=analysis.getItemSets().get(i) %></h5>
							</li>
						
						<% } %>
					</ul>
					
					<!-- Fünftes Diagram, Marketing Empfehlungen -->
					<ul class="list-group" style="padding-bottom: 20px;">
						<li class="list-group-item list-group-item-secondary">
							<h4>Marketing Empfehlungen</h4>
						</li>
						<% for(int i=0;i<analysis.getRecommendations().size(); i++){ %>
						
							<li class="list-group-item">
								<h5><%=analysis.getRecommendations().get(i) %></h5>
							</li>
						
						<% } %>
					</ul>
					
				</div>
			</div>
		</div>	
	</body>
</html>