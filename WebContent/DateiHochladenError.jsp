<!doctype html>
<html lang="de">
	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/fontawesome-all.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">

		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.js"></script>

		<title>Kauf Dort - Index</title>
		<jsp:useBean id = "list" class = "analysis.AnalysisList" scope="session" />
	</head>
	<body>
		<div class="wrapper">
			<div class="sidenav bg-light">
				<div class="sidebar-top">
					<img class="logo" src="img/kauf_dort_logo.png">
				</div>
				
				<div class="sidebar-item sidebar-block">
					<a class="sidebar-link" href="DateiHochladen.jsp"><i class="fa fa-file"></i> Datei hochladen</a>
				</div>
				<div class="sidebar-block">
					<div class="sidebar-heading">Letzte Ergebnisse</div>
					<% for(int i=0; i<list.getList().size(); i++){ %>
						<form action="/TeamData/LoadAnalysis" method="post">
							<input type="hidden" name="index" value="<%= i %>">
							<div class="sidebar-item">
								<button type="submit" class="sidebar-link" style="background: none; color: inherit; border: none; display: block; width:100%; text-align: left;"><i class="fa fa-angle-right"></i> <%=(i+1) + ". " + list.getList().get(i).getName() %></button>
							</div>
						</form>
					
					<% } %>
				</div>
				<div class="sidebar-item sidebar-block">
					<a class="sidebar-link" href=""><i class="fa fa-power-off"></i> Logout</a>
				</div>
			</div>
		
			<div class="main bg-white">
				<div class="content">
					<h2>Willkommen bei der Datenanalyse für Kauf Dort</h2>
					<div class="alert alert-danger">
     					Die Datei muss eine CSV Datei sein und darf nicht leer sein.
      				</div>
					
					<form method="post" action="/TeamData/DateiEmpfangen" enctype="multipart/form-data">
						<div class="input-group">
							<div class="custom-file">
								<input type="file" name="file" size="60" class="custom-file-input">
								<label class="custom-file-label">Datei auswählen</label>
							</div>
							<div class="input-group-append">
								<button class="btn btn-outline-primary" type="submit">Analyse starten</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>	
	</body>
</html>