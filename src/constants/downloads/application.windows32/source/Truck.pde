class Truck {
  int centerX=0; //This is the x value for the center of the truck body
  int centerY = 350; //This is the y value for the center of the truck body
  int tWidth = 80;  //This is the width of the truck body
  int tHeight = 60;  //This is the height of the truck body
  int frontEdge = centerX+tWidth/2; //This is the x value of the front edge of the truck body
  int bottomEdge = centerY+tHeight/2; //This is the y value of the bottom edge of the truck body.
  int headX = 60; //This is the x value of the front of the truck head
  int tLength = tWidth/2; //This is the full width of the truck from head to back
  int offscreenX = tLength+tWidth/2;

  void create() {
    //Add moving truck to street
    strokeWeight(2);
    if (left) {
      //Draw truck Body
      rectMode(CENTER);
      stroke(0);
      fill(200, 0, 0);
      rect(centerX, centerY, tWidth, tHeight);

      //Draw truck wheels
      //Left Wheel
      fill(170);
      ellipse(centerX-25, centerY+32, 23, 23);

      //Right Wheel
      ellipse(centerX+25, centerY+32, 23, 23);

      //Draw truck Face
      fill(130);
      quad(frontEdge, centerY-10, frontEdge-15, centerY-10, frontEdge-20, bottomEdge, frontEdge, bottomEdge);

      //So the truck face moves with the truck
      frontEdge = centerX - tWidth/2;
      bottomEdge = centerY + tHeight/2;
    }

    if (right) {
      //Draw truck Body
      rectMode(CENTER);
      stroke(0);
      fill(200, 0, 0);
      rect(centerX, centerY, tWidth, tHeight);

      //Draw truck wheels
      //Left Wheel
      fill(170);
      ellipse(centerX-25, centerY+32, 23, 23);

      //Right Wheel
      ellipse(centerX+25, centerY+32, 23, 23);

      //Draw truck Face
      fill(130);
      quad(frontEdge, centerY-10, frontEdge+15, centerY-10, frontEdge+20, bottomEdge, frontEdge, bottomEdge);

      //So the truck face moves with the truck
      frontEdge = centerX + tWidth/2;
      bottomEdge = centerY + tHeight/2;
    }

    if (up) {
      //Draw truck Body
      rectMode(CENTER);
      stroke(0);
      fill(200, 0, 0);
      rect(centerX, centerY, tHeight, tWidth);

      //Draw truck wheels
      //Left Wheel
      fill(170);
      ellipse(centerX+32, centerY+25, 23, 23);

      //Right Wheel
      ellipse(centerX+32, centerY-25, 23, 23);

      //Draw truck Face
      fill(130);
      quad(centerX-10, frontEdge, centerX-10, frontEdge-15, bottomEdge, frontEdge-20, bottomEdge, frontEdge);

      //So the truck face moves with the truck
      frontEdge = centerY - tWidth/2;
      bottomEdge = centerX + tHeight/2;
    }

    if (down) {
      //Draw truck Body
      rectMode(CENTER);
      stroke(0);
      fill(200, 0, 0);
      rect(centerX, centerY, tHeight, tWidth);

      //Draw truck wheels
      //Left Wheel
      fill(170);
      ellipse(centerX-32, centerY+25, 23, 23);

      //Right Wheel
      ellipse(centerX-32, centerY-25, 23, 23);

      //Draw truck Face
      fill(130);
      quad(centerX+10, frontEdge, centerX+10, frontEdge+15, bottomEdge, frontEdge+20, bottomEdge, frontEdge);

      //So the truck face moves with the truck
      frontEdge = centerY + tWidth/2;
      bottomEdge = centerX - tHeight/2;
    }
  }
  
  void move() {
    if (keyPressed && !loadingScreen) {
      if ((!arrowKeys && key == 'a')||(arrowKeys&& keyCode== LEFT)) {
        centerX = centerX - 1; //Move truck left
        left=true;
        right=false;
        up=false;
        down=false;
      }
    }

    if (keyPressed && !loadingScreen) {
      if ((!arrowKeys && key == 'd')||(arrowKeys&& keyCode== RIGHT)) {
        centerX = centerX + 1; //Move truck right
        right=true;
        left=false;
        up=false;
        down=false;
      }
    }

    if (keyPressed && !loadingScreen) {
      if ((!arrowKeys && key == 'w')||(arrowKeys&& keyCode== UP)) {
        centerY = centerY - 1; //Move truck up
        up=true;
        left=false;
        right=false;
        down=false;
      }
    }

    if (keyPressed && !loadingScreen) {
      if ((!arrowKeys && key == 's')||(arrowKeys&& keyCode== DOWN)) {
        centerY = centerY + 1; //Move truck down
        down=true;
        left=false;
        right=false;
        up=false;
      }
    }
  }
}
