<%@page import="analysis.*"%>
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

		<title>Kauf Dort - Analyse</title>
		
		<jsp:useBean id = "analysis" class = "analysis.Analysis" scope="request" />
		
	</head>
	<body>
		<div class="wrapper">
			<div class="sidenav bg-light">
				<div class="sidebar-top">
					<img class="logo" src="img/kauf_dort_logo.png">
				</div>
				
				<div class="sidebar-item sidebar-block">
					<a class="sidebar-link" href=""><i class="fa fa-file"></i> Datei hochladen</a>
				</div>
				<div class="sidebar-block">
					<div class="sidebar-heading">Letzte Ergebnisse</div>
					<div class="sidebar-item">
						<a class="sidebar-link" href=""><i class="fa fa-angle-right"></i> 1. Test</a>
					</div>
					<div class="sidebar-item">
						<a class="sidebar-link" href=""><i class="fa fa-angle-right"></i> 2. Test</a>
					</div>
					<div class="sidebar-item">
						<a class="sidebar-link" href=""><i class="fa fa-angle-right"></i> 3. Test</a>
					</div>
					<div class="sidebar-item">
						<a class="sidebar-link" href=""><i class="fa fa-angle-right"></i> 4. Test</a>
					</div>
					<div class="sidebar-item">
						<a class="sidebar-link" href=""><i class="fa fa-angle-right"></i> 5. Test</a>
					</div>
				</div>
				<div class="sidebar-item sidebar-block">
					<a class="sidebar-link" href=""><i class="fa fa-power-off"></i> Logout</a>
				</div>
			</div>
		
			<div class="main bg-white">
				<div class="content">
					<div>
						<h1><span class="badge badge-secondary">Ergebnisse der Analyse</span></h1>
					</div>

					<ul class="list-group" style="padding-bottom: 20px;">
						<li class="list-group-item list-group-item-secondary">
							<h4>Kundenanzahl am jeweiligen Wochentag</h4>
						</li>
						<li class="list-group-item">
							<img src="<%= analysis.getWeekDayChart().getAbsolutePath() %>" style="max-width: 100%">
						</li>
					</ul>

					<ul class="list-group" style="padding-bottom: 20px;">
						<li class="list-group-item list-group-item-secondary">
							<h4>Kundenanzahl um die jeweiligen Uhrzeit</h4>
						</li>
						<li class="list-group-item">
							<img src="<%= analysis.getDaytimeChart().getAbsolutePath() %>" style="max-width: 100%">
						</li>
					</ul>
				</div>
			</div>
		</div>	
	</body>
</html>