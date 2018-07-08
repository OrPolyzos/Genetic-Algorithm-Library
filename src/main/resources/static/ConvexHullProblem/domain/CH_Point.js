function CH_Point(label, x, y) {
    this.label = label;
    this.x = x;
    this.y = y;

    this.show = function (color) {
        stroke(color);
        strokeWeight(5);
        point(this.x, this.y);
        //textSize(10); // Set initial text size
        //fill(0, 102, 153);
        //text(this.label, this.x, this.y); // Draw text on baseline
    }

}