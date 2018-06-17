function LoadingAnimation(cnvWidth, cnvHeight) {

    this.rollingRight = true;
    this.rollingUp = true;

    this.x1 = cnvWidth / 2;
    this.y1 = cnvHeight / 2;

    this.x2 = cnvWidth / 2;
    this.y2 = cnvHeight / 2;

    this.sideRadius = cnvHeight / 10;
    this.xMax = cnvWidth - this.sideRadius;
    this.xMin = this.sideRadius;
    this.yMax = cnvHeight - this.sideRadius;
    this.yMin = this.sideRadius;

    this.x = cnvWidth / 2;
    this.y = cnvHeight / 2;
    this.radius = cnvHeight / 5;
    this.radiusMin = this.sideRadius;
    this.radiusMax = cnvHeight;
    this.growing = true;

    this.show = function () {
        fill(0)
        strokeWeight(1);
        stroke(255);
        ellipse(this.x, this.y, this.radius, this.radius);


        if (dist(this.x2, this.y2, this.x, this.y) < this.radius) {
            fill(0, 191, 255);
            strokeWeight(1);
            stroke(0);
            ellipse(this.x1, this.y1, this.sideRadius, this.sideRadius);
            ellipse(this.x2, this.y2, this.sideRadius, this.sideRadius);
        }
        else {
            fill(255);
            strokeWeight(1);
            stroke(0);
            ellipse(this.x1, this.y1, this.sideRadius, this.sideRadius);
            ellipse(this.x2, this.y2, this.sideRadius, this.sideRadius);
        }
    }

    this.update = function () {
        if (this.x2 >= this.xMax) {
            this.rollingRight = false;
        }
        if (this.x2 <= this.xMin) {
            this.rollingRight = true;
        }

        if (this.y2 >= this.yMax) {
            this.rollingUp = false;
        }
        if (this.y2 <= this.yMin) {
            this.rollingUp = true;
        }

        if (this.rollingRight == true) {
            this.x1 -= 10;
            this.x2 += 10;
        }
        else {
            this.x1 += 10;
            this.x2 -= 10;
        }
        if (this.rollingUp == true) {
            this.y1 -= 10;
            this.y2 += 10;
        }
        else {
            this.y1 += 10;
            this.y2 -= 10;
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