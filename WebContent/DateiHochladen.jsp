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
	</head>
	<body>
		<div class="wrapper">
			<div class="sidenav bg-light">
				<div class="sidebar-top">
					<img class="logo" src="img/kauf_dort_logo.png">
				</div>
				
				<div class="sidebar-item sidebar-block">
					<a class="sidebar-link" href="/TeamData/Start"><i class="fa fa-file"></i> Datei hochladen</a>
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
					<h2>Willkommen bei der Datenanalyse für Kauf Dort</h2>
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