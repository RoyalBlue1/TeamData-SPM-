<!doctype html>
<html lang="de">
	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/fontawesome-all.css">
		<link rel="stylesheet" type="text/css" href="css/style.css">

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.bundle.min.js"></script>

		<title>Kauf Dort - Login</title>	
	</head>
	<body>
		<div style="display: table; margin: auto; margin-top: 20px; max-width: 800px; width: 70%; min-width: 400px;">
			<div class="card" style="">
				<div class="card-header">
					<img src="img/kauf_dort_logo.png" style="height: 4em;">
				</div>
				<div class="card-body">
					Falsches Passwort
					<form method="post" action="/TeamData/Start">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<label class="input-group-text">Passwort</label>
							</div>
							<input type="password" name="password" class="form-control">
							<div class="input-group-append">
								<button class="btn btn-outline-secondary">Login</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>