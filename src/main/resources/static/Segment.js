function Segment(coordinate1,coordinate2,color){
  this.coordinate1 = coordinate1;
  this.coordinate2 = coordinate2;
  this.color = color;
}

Segment.prototype.show = function (){
  stroke(this.color);
  strokeWeight(1);
  line(this.coordinate1.x,this.coordinate1.y,this.coordinate2.x,this.coordinate2.y);
}
