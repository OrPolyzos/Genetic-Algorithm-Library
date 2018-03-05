function LoadingCircle (cnvWidth,cnvHeight) {
 this.x = cnvWidth/2;
 this.y = cnvHeight/2;
 this.radius = cnvWidth/10;
 this.max = 3 * this.radius;
 this.min = this.radius / 2;
 this.growing = true;

 this.show = function(){
    background(51);
    fill(0,191,255);
    ellipse(this.x,this.y,this.radius,this.radius);
 }

 this.update = function() {
    if (this.radius > this.max){
        this.growing = false;
    }
    if (this.radius < this.min) {
        this.growing = true;
    }
    if (this.growing === true){
        this.radius += this.radius / 10;
    } else {
        this.radius -= this.radius / 10;
    }
 }

}