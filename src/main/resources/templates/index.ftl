<#import "/spring.ftl" as spring/>
<html>
	<head>
		<title>Genetic Algorithm on Convex-Hull</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- P5.js library -->
        <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
	    <!-- Custom CSS -->
        <link rel="stylesheet" type="text/css" media="screen" href="/../styles.css">
        <!-- P5 sketch -->
        <script type="text/javascript" src="../ch_ga.js"></script>
        <script type="text/javascript" src="../ConvexHullGA.js"></script>
        <script type="text/javascript" src="../ConvexHullGAPoint.js"></script>
        <script type="text/javascript" src="../ConvexHullChromosome.js"></script>
        <script type="text/javascript" src="../Segment.js"></script>
        <script type="text/javascript" src="../LoadingCircle.js"></script>
	</head>
	<body class = "main-body">
        <div class="sidenav" id="sidenav">
            <h4>Options</h4>
            <div>
                <label for="pointsCount">Points</label>
                <input type="number" min="3" max="50000" class="form-control" name="pointsInput" id="pointsInput" placeholder="500" value="" required/>
            </div>
            <br/>
            <div>
                <label for="mutationRate">Mutation Rate</label>
                <input type="number" min="0.01" max="1" step="0.01" class="form-control" name="mutationRateInput" id="mutationRateInput" placeholder="0.03" value="" required/>
            </div>
            <br/>
            <div>
                <label for="populationCount">Population</label>
                <input type="number" min="50" max="5000" class="form-control" name="populationInput" id="populationInput" placeholder="1000" value="" required/>
            </div>
            <br/>
        </div>
        <div class="canvasParent" id="canvasParent">
        </div>
	</body>
</html>
