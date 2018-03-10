function LoadingAnimation(cnvWidth, cnvHeight) {

	this.rollingRight = true;

	this.x1 = cnvWidth / 2;
	this.y1 = cnvHeight / 2;

	this.x2 = cnvWidth / 2;
	this.y2 = cnvHeight / 2;

	this.sideRadius = cnvHeight / 10;
    this.sideMax = cnvWidth - this.sideRadius;
    this.sideMin = this.sideRadius;

	this.x = cnvWidth / 2;
	this.y = cnvHeight / 2;
	this.radius = cnvHeight / 5;
	this.radiusMin = 1;
	this.radiusMax = cnvHeight;
	this.growing = true;

	this.show = function() {
		background(51);
		noFill();
		stroke(0, 191, 255)
		strokeWeight(4);
		ellipse(this.x, this.y, this.radius, this.radius);

		fill(25);
		noStroke();
		ellipse(this.x1, this.y1, this.sideRadius, this.sideRadius);
		ellipse(this.x2, this.y2, this.sideRadius, this.sideRadius);
	}

	this.update = function() {
	    if (this.x2 >= this.sideMax){
	        this.rollingRight = false;
	    }
	    if (this.x2 <= this.sideMin){
	        this.rollingRight = true;
	    }

	    if (this.rollingRight == true){
	        this.x1 -= 10;
	        this.x2 += 10;
	    }
	    else{
	        this.x1 += 10;
	        this.x2 -= 10;
	    }

		if (this.radius >= this.radiusMax) {
			this.growing = false;
		}
		if (this.radius <= this.radiusMin) {
			this.growing = true;
		}
		if (this.growing == true) {
			this.radius += this.radius / 10;
		} else {
			this.radius -= this.radius / 10;
		}
	}

}