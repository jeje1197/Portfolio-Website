import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class JEv8_musicFix extends PApplet {









/* Welcome to JEv8, my first game! 
 
 The objective of this game is to follow the road and get the delivery 
 truck to the school building.
 
 Disclaimer: I am not the owner of any of the pictures or soundtracks!
 
 Controls:
 Use the 'W' 'A' 'S' 'D' buttons or arrow keys to move the truck!
 
 N - Change to night mode
 M - Change to day mode
 BACKSPACE('r' because the BACKSPACE key doesn't work online for some reason) - Return to main menu
 C - Close chat box
 
 Enjoy! - Jeje1197*/


Truck truck = new Truck(); //Create truck object

int startFlash = 255; //Change the color of the start button
int controlsFlash = 255; //Change the color of the controls button
int levelsFlash = 255; //Change the color of the levels button
int exitFlash = 255; //Change the color of the exit button
int musicFlash = 255; //Change the color of the exit button
int creditsFlash = 255; //Change the color of the credits button
int lvl1Flash = 255; //Change the color of the lvl1 button
int lvl2Flash = 255; //Change the color of the lvl2 button
int lvl3Flash = 255; //Change the color of the lvl3 button
int lvl4Flash = 255; //Change the color of the lvl4 button
int lvl4Flash2 = 255; //Change the color of the lvl4 button
int lvl5Flash = 255; //Change the color of the lvl5 button
int timer = +0; //Counts time (Use "timer/60" for seconds!)
int timelimit1 = 120; //Time limit#1 (Load screen time limit)
int movetolvl = 0; //Move to level (Value=Direction&Level# Ex: "-1" or "+2" or "2")
int bridge1X = 175;//Left bridge X value
int bridge2X = 225;//Right bridge X value
int bridge3Y = 210;//Upper bridge Y value
int levercolorR = 255;//R value for draw bridge lever
int levercolorG = 0;//G value for draw bridge lever
int medalY = 0; //The Y value of the gold medal


int bodyX = 110, bodyY = 340; //The X and Y Coordinates for the body
int bodyWidth = 20, bodyHeight = 40; //The width & height of the body
int nogginX = bodyX;//The X var of the head
int nogginY = bodyY-36;
int headRad = 35 ; //The diameter of the head
int legX = 100, legY = 355; //Leg coordinates
int legWidth = 10, legHeight = 20 ; //Leg width & height
int legX2 = 110, legY2 = legY; //Leg2 coordinates

int flashTimer = 0; //Timer for special level unlocked
int musicTimer = 0;
int endMusic = 60*123; //The time the music ends (60frames*numSeconds)

float dot1X =230; //X value of first loading dot
float dot1Y = 304; //Y value of first loading dot
float dot2Y = 294; //Y value of second loading dot
float dot3Y = 284; //Y value of third loading dot
float dotSpeed = 0.8f; //Loading dot move speed
float angle = 0; //Rotation variable for 
float angleSpeed = 0.03f; //Angle rotation speed

float angle1 = 0;//Leg1 angle
float angle2 = 0;//Leg2 angle
float rotateSpeed = .03f;//Leg movement speed
float walkX = -(bodyX+bodyWidth/2);
float walkY = 0;
float walkSpeed = .5f; //Jeje's Walk Speed

boolean mainMenu = true; //Main menu activation
boolean lvl1 = false; //Game screen (Level 1) activation
boolean lvl2 = false; //Level 2 activation
boolean lvl3 = false; //Level 2 activation
boolean lvl4 = false; //Level 3 activation
boolean lvl5 = false; //Level 3 activation
boolean levelSelect = false; //Level select screen
boolean controlScreen = false; //Control screen activation
boolean musicOn = true; //Music playing
boolean nightMode = false; //Night mode activation
boolean credits = false; //Congratulations screen
boolean lvl1unlocked = false; //Level 1 unlocked
boolean lvl2unlocked = false; //Level 2 unlocked
boolean lvl3unlocked = false; //Level 2 unlocked
boolean lvl4unlocked = false; //Level 2 unlocked
boolean lvl5unlocked = false; //Level 3 unlocked
boolean specialunlocked = false; //Special end unlocked
boolean specialEnd = false; // Special ending
boolean specialEvent = false;//Chat to return for post game
boolean creditsunlocked = false; //Credits unlocked
boolean hospitalScreen = false; //Hopstial screen activation
boolean bridgeConnect = false; //Bridge activation
boolean packageScreen = false; //Package event screen activation
boolean packageEvent = true; //Package event will happen
boolean drawBridge = false; //Draw Bridge activation
boolean loadingScreen = false; //Loading screen activation
boolean dot1Yup = false; //Move loading dot 1 up
boolean dot2Yup = false; //Move loading dot 2 up
boolean dot3Yup = false; //Move loading dot 3 up
boolean startTimer = false; //Timer activation
boolean angleInc = true; //Increase angle of medal
boolean angleDec = false;//Decrease angle of medal
boolean medalTime = false;//Time to award the gold medal

boolean rotate1 = true;//Move leg 1 in - direction
boolean rotate2 = false; //Move leg 2 in - direction

boolean walk1 = true; //First credits walk
boolean walk2 = false; //Second credits walk

boolean left = false;//Truck moving left
boolean right = true;//Truck moving right
boolean up = false;//Truck moving up
boolean down = false;//Truck moving down

boolean arrowKeys = false; //Use the arrow keys to control the truck
boolean startFlashTimer = false; //Timer for special lvl unlocked flash
boolean startMusicTimer = true;

PImage img; //Congratulation screen image setup
PImage img2; //Hospital Screen image
PImage img3; //Package screen image
PImage img4; //Special event image

Minim minim;
AudioPlayer player;

public void setup() {
  minim = new Minim(this);
  player = minim.loadFile("Coconut_Mall_-_Mario_Kart_Wii.mp3");
    
  img = loadImage("UCSC-Bridge.jpg"); //Adding in image variable
  img2 = loadImage("unova_nurse_joy.png"); //Adding in image2 variable
  img3 = loadImage("cardboard-box-296818_960_720.png"); //Adding in image3 variable
  img4 = loadImage("Gintoki_waving_goodbye.png"); //Adding in image4 variable
  player.loop();
}

public void draw() {
  mainScreen();
  keyControls();
  lScreen();
  levelActivation();
  stage1();
  stage2();
  stage3();
  stage4();
  stage5();
  creditos();
  cScreen();
  lSelect();
  sEvent();
  sEnd();
  minutero();
  flashMinutero();
}//**END OF DRAW BRACKET**

public void mainScreen() {
  if (mainMenu) {
    startFlash=255;
    controlsFlash=255;
    levelsFlash=255;
    exitFlash=255;
    musicFlash=255;
    background(185, 41, 229);

    //Game Title
    fill(0);
    textSize(28);
    text("JEv8 DELIVERY SIMULATOR", 22, 40);

    //Created By
    fill(0);
    textSize(18);
    text("Created by Jeje1197", 210, 380);

    //Add game box with options
    rectMode(CORNER);
    noStroke();
    fill(0);
    rect(0, 55, 400, 295);

    //Start Game Button
    if (mousePressed && 105<mouseY && mouseY<135 && 15<mouseX && mouseX<185) {
      loadingScreen = true;
      movetolvl=1;
      lvl1unlocked = true;
      mainMenu = false;
    }

    //Start Game Button Flash
    if (105<mouseY && mouseY<135 && 15<mouseX && mouseX<185) {
      startFlash=0;
    } 

    //Controls
    if (mousePressed && 165 < mouseY && mouseY < 190 && 15 < mouseX && mouseX < 145) {
      controlScreen = true;
      mainMenu = false;
    }

    //Controls Button Flash
    if ( 165 < mouseY && mouseY < 190 && 15 < mouseX && mouseX < 145) {
      controlsFlash=0;
    }

    //Levels 
    if (mousePressed && 225 < mouseY && mouseY < 255 && 15 < mouseX && mouseX < 115) {
      levelSelect = true;
      mainMenu = false;
    }

    //Levels Button Flash
    if (225 < mouseY && mouseY < 255 && 15 < mouseX && mouseX < 115) {
      levelsFlash=0;
    }

    //Exit
    if (mousePressed && 285 < mouseY && mouseY < 317 && 15 < mouseX && mouseX < 80) {
      exit();
    }

    if (285 < mouseY && mouseY < 317 && 15 < mouseX && mouseX < 80) {
      exitFlash=0;
    }

    //Music Button Flash
    if (105 < mouseY && mouseY < 135 && 275 < mouseX && mouseX < 370) {
      musicFlash=0;
    }

    //Start Game
    fill(startFlash, 255, startFlash);
    textSize(30);
    text("Start Game", 20, 130);

    //Controls
    fill(controlsFlash, 255, controlsFlash);
    textSize(30);
    text("Controls", 20, 190);

    //Levels
    fill(levelsFlash, 255, levelsFlash);
    textSize(30);
    text("Levels", 20, 250);

    //Exit Game
    fill(exitFlash, 255, exitFlash);
    textSize(30);
    text("Exit", 20, 315);

    //Turn Music On or Off
    fill(musicFlash, 255, musicFlash);
    textSize(30);
    text("Music", 280, 130);

    if (creditsunlocked) {
      creditsFlash = 255;

      //Credits
      if (mousePressed && 285 < mouseY && mouseY < 317 && 275 < mouseX && mouseX < 390) {
        credits = true;
      }

      if (285 < mouseY && mouseY < 317 && 275 < mouseX && mouseX < 390) {
        creditsFlash=0;
      }

      //Credits
      fill(creditsFlash, 255, creditsFlash);
      textSize(30);
      text("Credits", 280, 315);
    }//**END OF CREDITS UNLOCKED BRACKET
  }//**END OF MAIN MENU BRACKET
}
public void cScreen() {
  if (controlScreen) { //Draw the control screen
  
    int wasdFlash;
    int arrowsFlash;

    background(0);
    fill(255);
    textSize(30);
    text("Controls", 140, 40);

    textSize(20);

    text("N-Night Mode", 20, 220);

    text("M-Day Mode", 20, 250);

    text("C-Close chat", 20, 300);

    text("BACKSPACE-Main Menu", 20, 350);

    text("Move:", 20, 122);

    if (arrowKeys) { //Fill Selected controls with blue
      wasdFlash= color (0);
      arrowsFlash= color (153, 200, 216);
    } else {
      wasdFlash= color (153, 200, 216);
      arrowsFlash= color (0);
    }

    if (mouseX >100 && mouseX <180 && mouseY > 100 && mouseY < 130) {
      wasdFlash = color(0, 255, 0);
      if (mousePressed) {
        arrowKeys = false;
      }
    }

    fill(wasdFlash);
    stroke(255);
    rect(100, 100, 80, 30);
    fill(255);
    text("WASD", 110, 122);

    if (mouseX >236 && mouseX <338 && mouseY > 100 && mouseY < 130) {
      arrowsFlash = color(0, 255, 0);
      if (mousePressed) {
        arrowKeys = true;
      }
    }
    fill(arrowsFlash);
    stroke(255);
    rect(236, 100, 102, 30);
    fill(255);
    text("ARROWS", 246, 122);

    if (keyPressed) { //Return to previous screen
      if (key == BACKSPACE) {
        controlScreen = false;
        mainMenu = true;
      }
    }
  } //**END OF CONTROLS BRACKET
}
public void lSelect() {
  if (levelSelect) {

    background(0);
    fill(255);
    textSize(30);
    text("Levels", 155, 40);



    if (lvl1unlocked) { 
      lvl1Flash = 255;

      //Level1
      if (mousePressed && 100<mouseY && mouseY<135 && 35<mouseX && mouseX<110) {
        levelSelect = false;
        loadingScreen = true;
        movetolvl=1;
      }

      //Level1 Flash
      if (100<mouseY && mouseY<135 && 35<mouseX && mouseX<110) {
        lvl1Flash=0;
      }

      //Level 1
      fill(lvl1Flash, 255, lvl1Flash);
      textSize(30);
      text("Lvl 1", 40, 130);
    }

    if (lvl2unlocked) { 
      lvl2Flash = 255;

      //Level2
      if (mousePressed && 100<mouseY && mouseY<135 && 155<mouseX && mouseX<230) {
        levelSelect = false;
        loadingScreen = true;
        movetolvl=2;
      }

      //Level2 Flash
      if (100<mouseY && mouseY<135 && 155<mouseX && mouseX<230) {
        lvl2Flash=0;
      }
      //Level 2
      fill(lvl2Flash, 255, lvl2Flash);
      textSize(30);
      text("Lvl 2", 160, 130);
    }  

    if (lvl3unlocked) { 
      lvl3Flash = 255;

      //Level 3
      if (mousePressed && 100<mouseY && mouseY<135 && 275<mouseX && mouseX<350) {
        levelSelect = false;
        loadingScreen = true;
        movetolvl=3;
      }

      if (100<mouseY && mouseY<135 && 275<mouseX && mouseX<350) {
        lvl3Flash=0;
      }
      //Level 3
      fill(lvl3Flash, 255, lvl3Flash);
      textSize(30);
      text("Lvl 3", 280, 130);
    }  

    if (lvl4unlocked) { 
      lvl4Flash = 255; 
      lvl4Flash2 = 255;

      //Level4
      if (mousePressed && 170<mouseY && mouseY<205 && 35<mouseX && mouseX<110) {
        levelSelect = false;
        loadingScreen = true;
        movetolvl=4; 
        startTimer = false;
      }

      //Level4 Flash
      if (170<mouseY && mouseY<205 && 35<mouseX && mouseX<110) {
        lvl4Flash=0;
        lvl4Flash2=0;
      }

      if (specialunlocked) {
        startFlashTimer = true; 
        lvl4Flash2 = 255-(flashTimer/45)%2*255; /*Makes level4 flash yellow after every
         (timer/#) secs. */
      }

      //Level 4
      fill(lvl4Flash, 255, lvl4Flash2);
      textSize(30);
      text("Lvl 4", 40, 200);
    }

    if (lvl5unlocked) { 
      lvl5Flash = 255;

      //Level5
      if (mousePressed && 170<mouseY && mouseY<205 && 155<mouseX && mouseX<230) {
        levelSelect = false;
        loadingScreen = true;
        movetolvl=5;
      }

      //Level5 Flash
      if (170<mouseY && mouseY<205 && 155<mouseX && mouseX<230) {
        lvl5Flash=0;
      }

      //Level 5
      fill(lvl5Flash, 255, lvl5Flash);
      textSize(30);
      text("Lvl 5", 160, 200);
    }  
    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        levelSelect = false;
        mainMenu = true;
      }
    }
  }//**END OF LEVEL SELECT SCREEN BRACKET**
}
public void creditos() {
  if (credits) {
    creditsunlocked = true;
    specialunlocked = true;

    tint(100);
    image(img, 0, 0, 400, 400); //Inserting image

    //Congratulations text
    fill(0, 0, 255);
    textSize(36);
    text("CONGRATULATIONS!", 25, 70);

    //Creator's Note
    textSize(18);
    text("-Jeje1197", 300, 380);

    textSize(20);
    text("Thanks for playing my first game!", 20, 350);

    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        credits = false;
        mainMenu = true;
      }
    }

    if (keyPressed) { //Return to main menu
      if (key == 'c') {
        credits = false;
        movetolvl=4;
      }
    }
  }
}
public void sEvent() {
  if (specialEvent) {

    background(185, 41, 229);

    //Add image box
    rectMode(CORNER);
    noStroke();
    fill(0);
    rect(0, 55, 400, 295);
    //Add image
    image(img4, 0, 55, 400, 295);

    textSize(18);
    text("Ryan: You seem a bit busy. Come back when", 5, 370);
    text("you're done with your delivery!", 5, 390);
    truck.centerX=340; //Truck stopped at building exit during screen
    truck.centerY=150;

    if (keyPressed) {//Close chat box
      if (key == 'c' | key == 'C') {
        specialEvent = false;
        truck.centerX=200;
        truck.centerY=70;
      }
    }

    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        specialEvent = false;
        lvl1 = false;
        mainMenu = true;
      }
    }
  }//**END OF SPECIAL EVENT BRACKET**
}

public void sEnd() {
  if (specialEnd) {  
    rectMode(CORNER);
    background(244, 164, 66);

    //Draw rectangle base
    fill(0);
    stroke(238, 244, 66);
    rect(0, 339, 399, 60);

    //Draw Grass
    noStroke();
    fill(37, 209, 63);
    rect(0, 240, 400, 99);

    //Add text to base
    fill(66, 179, 244);
    textSize(40);
    text("WELCOME TO UCSC!", 7, 385);

    //***Building***
    //Draw Building Frame
    rectMode(CENTER);

    stroke(0, 150);
    fill(150);
    rect(200, 180, 300, 120);

    //Draw Building Door
    strokeWeight(2);
    fill(200);
    rect(200, 215, 70, 50);

    //Draw Building Windows

    //Left Window
    strokeWeight(4);
    fill(215);
    rect(110, 165, 45, 45);

    //Right Window
    fill(215);
    rect(290, 165, 45, 45);

    //Add Trees

    //Left Tree 2
    strokeWeight(1);
    stroke(0, 150);
    fill(153, 140, 59);
    rect(130, 235, 25, 70);
    fill(2, 102, 17);
    rect(130, 200, 50, 50);

    //Right Tree 2
    stroke(0, 150);
    fill(153, 140, 59);
    rect(270, 235, 25, 70);
    fill(2, 102, 17);
    rect(270, 200, 50, 50);

    //Left Tree 1
    stroke(0, 150);
    fill(153, 140, 59);
    rect(100, 280, 30, 80);
    fill(2, 102, 17);
    rect(100, 240, 55, 55);

    //Right Tree 1
    stroke(0, 150);
    fill(153, 140, 59);
    rect(300, 280, 30, 80);
    fill(2, 102, 17);
    rect(300, 240, 55, 55);

    //***Draw Road To Building***
    //Pavement
    stroke(0);
    fill(0);
    quad(165, 240, 235, 240, 280, 339, 120, 339);

    //Road Line 2
    noStroke();
    fill(224, 218, 40);
    rect(200, 265, 10, 35);

    //Road Line 1
    noStroke();
    fill(224, 218, 40);
    rect(200, 312, 10, 30);

    //Switch to night
    rectMode(CORNER);
    if (nightMode) {

      //**Night** Activate by pressing the "n" key

      //**Draw night sky**

      noStroke();
      fill(11, 15, 48);
      rect(0, 0, 400, 60);

      //This covers up the orange background & sun
      rect(0, 60, 50, 180);
      rect(350, 60, 50, 180);
      rect(50, 60, 300, 60);

      //Draw Stars (Line 1 & Line 2)
      fill(153, 158, 11);
      textSize(37);
      text("*  *  *  *  *  *  *  *  *", 22, 38);
      text("*  *  *  *  *  *  *  *", 43, 60);
    } else {
      //**Day** Activate by pressing the "m" key

      //Repaint over the night sky for when clicked
      fill(244, 164, 66);
      rect(0, 0, 400, 60);
      rect(0, 60, 50, 180);
      rect(350, 60, 50, 180);
      rect(50, 60, 300, 60);

      //Draw the sun
      noStroke();
      fill(230, 244, 68);
      ellipse(400, 0, 160, 160);

      //Draw sun rays
      fill(240, 252, 113);
      ellipse(400, 0, 200, 200);
    }

    if (keyPressed) { //Return to previous screen
      if (key == BACKSPACE) {
        lvl5 = false;
        mainMenu = true;
      }
    }

    //JE walking to school

    walkX=walkX+walkSpeed; //Make JE move

    if (walkX<95) {

      translate(walkX, 0);
      pushMatrix();

      //Draw legs
      pushMatrix();
      translate(legX, legY);
      rotate(angle1);
      rectMode(CORNER);
      fill(0, 0, 255);
      rect(0, 0, legWidth, legHeight);
      popMatrix();

      pushMatrix();
      translate(legX2, legY2);
      rotate(angle2);
      rect(0, 0, legWidth, legHeight);
      popMatrix();

      if (angle1>PI/6) {
        rotate1= true;
      } else if (angle1<-PI/6) {
        rotate1=!rotate1;
      }

      if (rotate1) {
        angle1=angle1-rotateSpeed;
      } else {
        angle1=angle1+rotateSpeed;
      }

      if (angle2>PI/6) {
        rotate2 = true;
      } else if (angle2<-PI/6) {
        rotate2=!rotate2;
      }


      if (rotate2) {
        angle2=angle2-rotateSpeed;
      } else {
        angle2=angle2+rotateSpeed;
      }

      //Draw Head
      fill(206, 161, 82);
      ellipse(nogginX, nogginY, headRad, headRad);

      //Draw body
      fill(175, 28, 21);
      rectMode(CENTER);
      rect(bodyX, bodyY, bodyWidth, bodyHeight);
      popMatrix();
    }

    if (walkX==95) {
      walk1=false;
      startTimer=true;
    }

    if (!walk1 && !walk2 && !medalTime) {
      bodyX=200;
      nogginX=bodyX;
      legX=190;
      legX2=200;

      rectMode(CORNER);
      fill(0, 0, 255);
      rect(legX, legY, legWidth, legHeight);

      rect(legX2, legY2, legWidth, legHeight);

      //Draw Head
      fill(206, 161, 82);
      ellipse(nogginX, nogginY, headRad, headRad);

      //Draw body
      fill(175, 28, 21);
      rectMode(CENTER);
      rect(bodyX, bodyY, bodyWidth, bodyHeight);
    }

    if (timer>90) {
      startTimer=false;
      walk2=true;
    }

    if (walk2) {
      bodyX=200;
      nogginX=bodyX;
      legX=190;
      legX2=200;
      walkY=walkY-walkSpeed;

      if (walkY>-125) {
        pushMatrix();
        translate(0, walkY);
        //Draw legs

        rectMode(CORNER);
        fill(0, 0, 255);
        rect(legX, legY, legWidth, legHeight);

        rect(legX2, legY2, legWidth, legHeight);

        //Draw Head
        fill(206, 161, 82);
        ellipse(nogginX, nogginY, headRad, headRad);

        //Draw body
        fill(175, 28, 21);
        rectMode(CENTER);
        rect(bodyX, bodyY, bodyWidth, bodyHeight);
        popMatrix();
      }
    }

    if (walkY<=-125) {
      walk2=!walk2;
      medalTime=true;
    }

    if (medalTime) {
      fill(0);
      rect(0, 0, 400, 55);
      rect(0, 350, 400, 50);

      //Add image box
      rectMode(CORNER);
      noStroke();
      fill(0, 100);
      rect(0, 55, 400, 295);

      //Congratulations text
      fill(0, 255, 255);
      textSize(36);
      text("CONGRATULATIONS!", 20, 40);

      textSize(18);
      text("SECRET ENDING", 15, 370);

      //Creator's Note
      textSize(15);
      text("-Jeje1197", 315, 390);

      //Moving medal
      translate(200, medalY);
      rotate(angle);

      fill(200, 0, 0);
      rect(-22, 0, 14, 80);
      rect(8, 0, 14, 80);
      fill(0, 0, 255);
      rect(-7, 0, 14, 80);
      fill(136, 121, 27);
      ellipse(0, 80, 60, 60);

      if (angle>(PI/6)) {
        angleDec=true;
        angleInc=false;
      }
      if (angle<-(PI/6)) {
        angleInc = true;
        angleDec=false;
      }

      if (angleDec) {
        angle=angle-angleSpeed;
      }

      if (angleInc) {
        angle=angle+angleSpeed;
      }

      if (medalY<200) { //Moves medal down
        medalY=medalY+1;
      }

      if (medalY==200) {//Stops medal in center
        medalY=200;
      }
    }//**END OF MEDALTIME BRACKET**

    if (keyPressed) {//Close chat box
      if (key == 'c' | key == 'C') {
        specialEnd=false;
        mainMenu=true;
      }
    }

    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        specialEnd = false;
        mainMenu = true;
      }
    }
  }//**END OF SPECIAL END BRACKET**
}

//Stop or play background music button
public void mousePressed() {
  if (mainMenu && 105 < mouseY && mouseY < 135 && 275 < mouseX && mouseX < 370 && musicOn) {
    musicOn = false;
    stopMusic();
  } else if (mainMenu && 105 < mouseY && mouseY < 135 && 275 < mouseX && mouseX < 370 && musicOn == false) {
    musicOn = true;
    playMusic();
  }
}



public void keyControls() {
  //Set Controls
  //Add in key controls

  if (keyPressed && !loadingScreen) {//Activate night mode
    if (key == 'n' || key == 'N') {
      nightMode = true;
    }
  }

  if (keyPressed && !loadingScreen) {
    if (key == 'm' || key == 'M') {//Activate day mode
      nightMode = false;
    }
  }
}
public void createSky() {
  
    rectMode(CORNER);
  if (nightMode) {
    //Draw night sky
    noStroke();
    fill(11, 15, 48);
    rect(0, 0, 400, 60);

    //Draw Stars (Line 1 & Line 2)
    fill(153, 158, 11);
    textSize(37);
    text("*  *  *  *  *  *  *  *  *", 22, 38);
    text("*  *  *  *  *  *  *  *", 43, 60);
  } else {  

    //Paint the sky
    noStroke();
    fill(244, 164, 66);
    rect(0, 0, 400, 60);

    //Draw the sun
    noStroke();
    fill(230, 244, 68);
    ellipse(400, 0, 160, 160);

    //Draw sun rays
    fill(240, 252, 113, 100);
    ellipse(400, 0, 200, 200);
  }
} 
public void createlvl5Sky() {

  //Switch to night
  rectMode(CORNER);
  noStroke();
  if (nightMode) {
    //**Night** Activate by pressing the "n" key

    //**Draw night sky**
    fill(11, 15, 48);
    rect(0, 0, 400, 60);

    //This covers up the orange background & sun
    rect(0, 60, 50, 180);
    rect(350, 60, 50, 180);
    rect(50, 60, 300, 60);

    //Draw Stars (Line 1 & Line 2)
    fill(153, 158, 11);
    textSize(37);
    text("*  *  *  *  *  *  *  *  *", 22, 38);
    text("*  *  *  *  *  *  *  *", 43, 60);
  } else {
    //**Day** Activate by pressing the "m" key

    //Repaint over the night sky for when clicked
    fill(244, 164, 66);
    rect(0, 0, 400, 60);
    rect(0, 60, 50, 180);
    rect(350, 60, 50, 180);
    rect(50, 60, 300, 60);

    //Draw the sun
    noStroke();
    fill(230, 244, 68);
    ellipse(400, 0, 160, 160);

    //Draw sun rays
    fill(240, 252, 113);
    ellipse(400, 0, 200, 200);
  }
}

public void stage1() {
  if (lvl1) {
    createSky();
    drawlvl1();

    //Add moving truck to street
    truck.create();
    truck.move();

    drawlvl1trees();

    //Set truck boundaries
    lvl1Boundaries();

    //Package 
    pScreen();
  }//**END OF LEVEL 1 BRACKET**
}
public void stage2() {
  if (lvl2) { //**START OF LVL2 BRACKET**
    createSky();
    drawlvl2();

    //Add moving truck to street
    truck.create();
    truck.move(); 

    drawlvl2trees();

    //Set truck boundaries
    lvl2Boundaries();
    
    hScreen();

    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        lvl2 = false;
        mainMenu = true;
      }
    }
  } //**END OF LVL2 Bracket**
}

public void stage3() {
  if (lvl3) {
    createSky();
    drawlvl3();
    nightPanel();

    //Add moving truck to street
    truck.create();
    truck.move();

    drawlvl3trees();

    //Set boundaries
    lvl3Boundaries();

    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        lvl3 = false;
        mainMenu = true;
      }
    }
  }//**END OF LEVEL 3 BRACKET**
}
public void stage4() {
  if (lvl4) {
    createSky();
    drawlvl4();
    
    //Add moving truck to street
    truck.create();
    truck.move();

    drawlvl4trees();
    lvl4Boundaries();

    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        lvl4 = false;
        mainMenu = true;
      }
    }
  }//**END OF LEVEL 4 BRACKET**
}

public void stage5() {
  if (lvl5) { //Draw the game screen **Left bracket for lvl5**
    drawlvl5();
    truck.create();
    truck.move();

    //**Set boundaries**
    lvl5Boundaries();

    //**Insert conditional options for day/night**
    createlvl5Sky();

    if (keyPressed) { //Return to previous screen
      if (key == BACKSPACE) {
        lvl5 = false;
        mainMenu = true;
      }
    }
  }//**Right bracket for level 5**
}
public void drawlvl1() {
  //Draw Grass
  noStroke();
  fill(37, 209, 63);
  rect(0, 60, 400, 340);

  //Delivery Building
  //Building Frame
  rectMode(CENTER);
  stroke(0, 150);
  fill(160, 137, 86);
  rect(280, 80, 140, 95);

  strokeWeight(2);
  fill(200);
  rect(280, 101, 60, 53);

  //Road
  rectMode(CORNER);
  noStroke();
  fill(0);
  rect(0, 200, 170, 60);
  rect(110, 260, 60, 60);
  rect(170, 260, 230, 60);

  //Road lines
  rectMode(CENTER);
  fill(224, 218, 40);
  rect(25, 230, 35, 10);
  rect(85, 230, 35, 10);
  rect(140, 260, 10, 35);
  rect(195, 290, 35, 10);
  rect(255, 290, 35, 10);
  rect(315, 290, 35, 10);
  rect(375, 290, 35, 10);
}
public void drawlvl1trees() {
  //Trees
  //Tree 1
  rectMode(CENTER);
  stroke(0, 150);
  fill(153, 140, 59);
  rect(0, 280, 30, 60);
  fill(2, 102, 17);
  rect(0, 240, 55, 50);

  //Tree 2
  fill(153, 140, 59);
  rect(50, 150, 30, 60);
  fill(2, 102, 17);
  rect(50, 110, 55, 50);

  //Tree 3
  fill(153, 140, 59);
  rect(140, 110, 30, 60);
  fill(2, 102, 17);
  rect(140, 70, 55, 50);

  //Tree 4
  fill(153, 140, 59);
  rect(210, 210, 30, 60);
  fill(2, 102, 17);
  rect(210, 170, 55, 50);

  //Tree 5
  fill(153, 140, 59);
  rect(350, 210, 30, 60);
  fill(2, 102, 17);
  rect(350, 170, 55, 50);

  //Tree 6
  fill(153, 140, 59);
  rect(350, 340, 30, 60);
  fill(2, 102, 17);
  rect(350, 300, 55, 50);

  //Tree 7
  fill(153, 140, 59);
  rect(210, 340, 30, 60);
  fill(2, 102, 17);
  rect(210, 300, 55, 50);
}
public void drawlvl2() {
  //Draw Grass
  noStroke();
  fill(37, 209, 63);
  rect(0, 60, 400, 340);

  //Draw Medical Center
  //Building Frame
  rectMode(CENTER);
  stroke(0, 150);
  fill(150);
  rect(340, 100, 120, 80);

  strokeWeight(2);
  fill(200);
  rect(340, 120, 60, 40);

  //Medical Plus Symbol
  strokeWeight(5);
  stroke(255);
  fill(163, 25, 21);
  rect(340, 75, 10, 20);
  rect(340, 75, 20, 10);
  noStroke();
  rect(340, 75, 10, 20);
  rect(340, 75, 20, 10);

  strokeWeight(2);
  rectMode(CORNER);

  //Road
  //Pavement
  noStroke();
  fill(0);
  rect(0, 340, 100, 60);
  rect(40, 120, 60, 240);
  rect(100, 120, 120, 60);
  rect(160, 180, 60, 220);
  quad(160, 375, 160, 315, 310, 240, 310, 300);
  rect(310, 240, 90, 60);
  rect(310, 140, 60, 160);

  //Road Lines
  //Road Line 1
  noStroke();
  fill(224, 218, 40);
  rect(65, 335, 10, 35);
  rect(65, 275, 10, 35);
  rect(65, 215, 10, 35);
  rect(65, 155, 10, 35);
  rect(115, 145, 35, 10);
  rect(390, 263, 35, 10);
  rect(0, 365, 35, 10);
  rect(185, 335, 10, 35);
  rect(185, 275, 10, 35);
  rect(185, 215, 10, 35);
  rect(185, 155, 10, 35);
  quad(245, 305, 245, 295, 280, 277, 280, 287);
  rect(335, 221, 10, 35);
  rect(335, 156, 10, 35);
}
public void drawlvl2trees() {
  //Add trees
  //Tree 1
  rectMode(CENTER);
  stroke(0, 150);
  fill(153, 140, 59);
  rect(0, 280, 30, 60);
  fill(2, 102, 17);
  rect(0, 240, 55, 50);

  //Tree 2
  fill(153, 140, 59);
  rect(130, 220, 30, 60);
  fill(2, 102, 17);
  rect(130, 180, 55, 50);

  //Tree 3
  fill(153, 140, 59);
  rect(130, 340, 30, 60);
  fill(2, 102, 17);
  rect(130, 300, 55, 50);

  //Tree 4
  fill(153, 140, 59);
  rect(280, 360, 30, 60);
  fill(2, 102, 17);
  rect(280, 320, 55, 50);

  //Tree 5
  fill(153, 140, 59);
  rect(270, 160, 30, 60);
  fill(2, 102, 17);
  rect(269, 120, 55, 50);
}
public void drawlvl3() {
  //Draw Grass
  noStroke();
  fill(37, 209, 63);
  rect(0, 60, 400, 340);

  //Draw road
  rectMode(CORNER);
  noStroke();
  fill(0);
  rect(170, 60, 60, 60);
  rect(60, 120, 280, 60);
  rect(60, 180, 60, 150);
  rect(60, 330, 280, 60);
  rect(280, 180, 60, 150);
}
public void drawlvl3trees() {
  //Tree 1
  rectMode(CENTER);
  strokeWeight(2);
  stroke(0, 150);
  fill(153, 140, 59);
  rect(30, 320, 30, 60);
  fill(2, 102, 17);
  rect(30, 280, 55, 50);

  //Tree 2
  fill(153, 140, 59);
  rect(370, 320, 30, 60);
  fill(2, 102, 17);
  rect(370, 280, 55, 50);

  //Tree 3
  fill(153, 140, 59);
  rect(30, 200, 30, 60);
  fill(2, 102, 17);
  rect(30, 160, 55, 50);

  //Tree 4
  fill(153, 140, 59);
  rect(370, 200, 30, 60);
  fill(2, 102, 17);
  rect(370, 160, 55, 50);

  //Tree 5
  fill(153, 140, 59);
  rect(290, 80, 30, 60);
  fill(2, 102, 17);
  rect(290, 40, 55, 50);

  //Tree 6
  fill(153, 140, 59);
  rect(110, 80, 30, 60);
  fill(2, 102, 17);
  rect(110, 40, 55, 50);
}
public void drawlvl4() {
  //Draw Grass
  noStroke();
  fill(37, 209, 63);
  rect(0, 60, 400, 340);

  //Road
  rectMode(CORNER);

  //Water Under Bridge
  fill(55, 88, 155);
  rect(155, 215, 90, 90);

  //Left road
  noStroke();
  fill(0);
  rect(0, 230, 150, 60);

  //Right road
  rect(250, 230, 150, 60);

  //Upper road
  rect(170, 60, 60, 130);

  //Road Lines
  rectMode(CENTER);
  fill(224, 218, 40);
  rect(90, 260, 35, 10);
  rect(310, 260, 35, 10);
  rect(200, 130, 10, 35);

  //Draw bridge
    rectMode(CENTER);
    strokeWeight(3);
    stroke(132, 120, 31);
    fill(226, 174, 226);
  if (drawBridge) {
    rect(bridge1X, 260, 50, 60);//Left bridge
    rect(bridge2X, 260, 50, 60);//Right bridge
    rect(200, bridge3Y, 60, 40);//Upper Bridge
  } else {
    rect(bridge1X-50, 260, 50, 60);//Left bridge
    rect(bridge2X+50, 260, 50, 60);//Right bridge
    rect(200, bridge3Y-40, 60, 40);//Upper Bridge
  }//**END OF DRAW BRDIGE BRACKET**
}
public void drawlvl4trees() {
  //Tree 1
  rectMode(CENTER);
  strokeWeight(2);
  stroke(0, 150);
  fill(153, 140, 59);
  rect(30, 320, 30, 60);
  fill(2, 102, 17);
  rect(30, 280, 55, 50);

  //Tree 2
  fill(153, 140, 59);
  rect(80, 190, 30, 60);
  fill(2, 102, 17);
  rect(80, 150, 55, 50);

  //Tree 3
  fill(153, 140, 59);
  rect(370, 320, 30, 60);
  fill(2, 102, 17);
  rect(370, 280, 55, 50);

  //Tree 4
  fill(153, 140, 59);
  rect(320, 190, 30, 60);
  fill(2, 102, 17);
  rect(320, 150, 55, 50);

  //Tree 5
  fill(153, 140, 59);
  rect(130, 100, 30, 60);
  fill(2, 102, 17);
  rect(130, 60, 55, 50);

  //Tree 6
  fill(153, 140, 59);
  rect(270, 100, 30, 60);
  fill(2, 102, 17);
  rect(270, 60, 55, 50);
}
public void drawlvl5() {
  rectMode(CORNER);
  background(244, 164, 66);

  //Draw rectangle base
  fill(0);
  stroke(238, 244, 66);
  rect(0, 339, 399, 60);

  //Draw Grass
  noStroke();
  fill(37, 209, 63);
  rect(0, 240, 400, 99);

  //Add text to base
  fill(66, 179, 244);
  textSize(40);
  text("WELCOME TO UCSC!", 7, 385);

  //***Building***
  //Draw Building Frame
  rectMode(CENTER);

  stroke(0, 150);
  fill(150);
  rect(200, 180, 300, 120);

  //Draw Building Door
  strokeWeight(2);
  fill(200);
  rect(200, 215, 70, 50);

  //Draw Building Windows

  //Left Window
  strokeWeight(4);
  fill(215);
  rect(110, 165, 45, 45);

  //Right Window
  rect(290, 165, 45, 45);

  //Add moving truck to street
  truck.create();

  //Add Trees

  //Left Tree 2
  strokeWeight(1);
  stroke(0, 150);
  fill(153, 140, 59);
  rect(130, 235, 25, 70);
  fill(2, 102, 17);
  rect(130, 200, 50, 50);

  //Right Tree 2
  fill(153, 140, 59);
  rect(270, 235, 25, 70);
  fill(2, 102, 17);
  rect(270, 200, 50, 50);

  //Left Tree 1
  fill(153, 140, 59);
  rect(100, 280, 30, 80);
  fill(2, 102, 17);
  rect(100, 240, 55, 55);

  //Right Tree 1
  fill(153, 140, 59);
  rect(300, 280, 30, 80);
  fill(2, 102, 17);
  rect(300, 240, 55, 55);

  //***Draw Road To Building***
  //Pavement
  stroke(0);
  fill(0);
  quad(165, 240, 235, 240, 280, 339, 120, 339);

  //Road Line 2
  noStroke();
  fill(224, 218, 40);
  rect(200, 265, 10, 35);

  //Road Line 1
  rect(200, 312, 10, 30);

  //Just to reset everything before the next process
  noStroke();
  noFill();
  rectMode(CORNER);
}
public void lvl1Boundaries() {
  if (truck.centerX < 0) { //If the truck goes off the left side, reset
    truck.centerX = 0; 
    truck.centerY = 230;
  }

  if (truck.centerX < 110 && (truck.centerY < 200 || truck.centerY > 260)) { 
    truck.centerX = 0; 
    truck.centerY = 230;
  }

  if (truck.centerX > 110 && truck.centerX < 170 && (truck.centerY < 200 || truck.centerY > 320)) { 
    truck.centerX = 0; 
    truck.centerY = 230;
  }

  if (truck.centerX > 170 && truck.centerX < 400 && (truck.centerY < 260 || truck.centerY > 320)) { 
    truck.centerX = 0; 
    truck.centerY = 230;
  }
}
public void lvl2Boundaries() {
  if (truck.centerX < 0) { //If the truck goes off the left side return to level 1
    lvl2= false;
    loadingScreen=true;
    movetolvl=-1;
  }

  if (truck.centerY > 400) { //If the truck goes off the bottom go to level3
    lvl2=false;
    lvl3unlocked=true;
    loadingScreen=true;
    movetolvl=3;
  }

  if (truck.centerX < 40 & truck.centerY < 340) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 100 && truck.centerX < 160 && truck.centerY > 340) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX < 40 && truck.centerY < 340 && 180< truck.centerY) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 100 && truck.centerY < 340 && 180< truck.centerY  && truck.centerX <160) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 40 && truck.centerX < 220 && 120 > truck.centerY) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 220 && truck.centerX < 300 && 240 > truck.centerY) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 220 && truck.centerX < 300 && 300 < truck.centerY) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 300 && 300 < truck.centerY) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 370 && 240 > truck.centerY) {
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 310 && truck.centerX < 370 && truck.centerY < 140) {//Reach medical center
    hospitalScreen = true;
  }
  
  if (truck.centerX > 400) { //If the truck goes off the bottom go to level3
    lvl2=false;
    lvl4unlocked=true;
    loadingScreen=true;
    movetolvl=4;
  }
}
public void lvl3Boundaries() {
  if (truck.centerY<60) {
    lvl3=false;
    loadingScreen = true;
    movetolvl=32;
  }

  if (truck.centerX<60) {
    truck.centerX=200;
    truck.centerY=60;
  }

  if (truck.centerX>340) {
    truck.centerX=200;
    truck.centerY=60;
  }

  if (truck.centerY<120 && (truck.centerX < 170 || truck.centerX > 230)) {
    truck.centerX=200;
    truck.centerY=60;
  }

  if (truck.centerY > 390) {
    truck.centerX=200;
    truck.centerY=60;
  }

  if (truck.centerX < 170 && truck.centerX > 230 && truck.centerY <120 ) {
    truck.centerX=200;
    truck.centerY=60;
  }

  if (((truck.centerX > 120 && truck.centerX <150) || (truck.centerX <280 && truck.centerX>250)) && 
    truck.centerY >180 && truck.centerY <330) {
    truck.centerX=200;
    truck.centerY=60;
  }

  if (truck.centerX > 120 && truck.centerX <280 && truck.centerY>180 && truck.centerY<330 && !nightMode) {
    truck.centerX=200;
    truck.centerY=60;
  }

  if (truck.centerX > 170 && truck.centerX<230 && truck.centerY ==200) {//Activate draw bridge lever
    if (!drawBridge) {
      drawBridge=true;
    } else { 
      drawBridge=false;
    }
  }
}
public void lvl4Boundaries() {
  //Set boundaries
  if (truck.centerX<0) {
    lvl4 = false;
    loadingScreen = true;
    movetolvl = -2;
  }

  if (truck.centerY > 290) {
    truck.centerX = 0;
    truck.centerY = 260;
  }

  if ((truck.centerX < 170 || truck.centerX > 230) && truck.centerY< 230) {
    truck.centerX =0;
    truck.centerY=260;
  }

  if (truck.centerX > 150 && truck.centerX <250 && truck.centerY>190 && truck.centerY <290 && !drawBridge ) {
    truck.centerX =0;
    truck.centerY=260;
  }

  if (truck.centerX >400) {
    lvl4 = false;
    lvl5unlocked = true;
    loadingScreen = true;
    movetolvl=5;
  }

  if (truck.centerY <60 && specialunlocked) {
    lvl4 = false;
    specialEnd=true;
  }

  if (truck.centerY < 60 && !specialunlocked) {
    specialEvent = true;
  }
}
public void lvl5Boundaries() {
  if (truck.centerX < 0) { //If the truck goes off the left side, return to lvl4
    lvl5=false;
    loadingScreen=true;
    movetolvl=-4;
  }

  if (truck.centerX > 400) { //If the truck goes off the right side, reset
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerY > 400) { //If the truck goes off the bottom, reset
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX > 280 & truck.centerY < 340) {//If the truck goes too far into the right side grass
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  if (truck.centerX>=0 && truck.centerX < 120 && truck.centerY < 340) {//If the truck goes too far into the left side grass
    truck.centerX = 0; 
    truck.centerY = 350;
  }

  //**You win the game**
  if (truck.centerX > 120 && truck.centerX <340 && truck.centerY < 215) {//If the truck reaches the building door
    lvl5=false;
    credits = true;
  }
}
public void lScreen() {
  if (loadingScreen) {
    startTimer = true; //Start the timer

    background(185, 41, 229);

    //Add image box
    rectMode(CORNER);
    noStroke();
    fill(0);
    rect(0, 55, 400, 295);

    //Add loading... text
    fill(0, 0, 240);
    textSize(30);
    text("LOADING", 80, 300);
    rect(dot1X, dot1Y, 10, 10);
    rect(dot1X+20, dot2Y, 10, 10);
    rect(dot1X+40, dot3Y, 10, 10);

    //Conditions for moving loading dots
    if (dot1Y < 285) {
      dot1Yup = false;
    }

    if (dot1Y >315) {
      dot1Yup = true;
    }

    if (dot2Y <285) {
      dot2Yup = false;
    }

    if (dot2Y >315) {
      dot2Yup = true;
    }

    if (dot3Y <285) {
      dot3Yup = false;
    }

    if (dot3Y >315) {
      dot3Yup = true;
    }

    if (dot1Yup) {
      dot1Y=dot1Y - dotSpeed;
    } else {
      dot1Y=dot1Y + dotSpeed;
    }

    if (dot2Yup) {
      dot2Y=dot2Y - dotSpeed;
    } else {
      dot2Y=dot2Y + dotSpeed;
    }

    if (dot3Yup) {
      dot3Y=dot3Y - dotSpeed;
    } else {
      dot3Y=dot3Y + dotSpeed;
    }

    if (timer == timelimit1) { //Loading screen closes after 4 secs
      startTimer=false; 
      loadingScreen=false;
    }
  }//**END OF LOADING SCREEN
}
public void levelActivation() {
  if (!loadingScreen && movetolvl==1) {//Level 1 opens after loading screen
    lvl1 = true; 
    truck.centerX=0;
    truck.centerY=230;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==-1) {//Level 1 opens after loading screen
    lvl1 = true; 
    truck.centerX=390;
    truck.centerY=290;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==2) {//Level 2 opens after loading screen
    lvl2 = true; 
    truck.centerX=0;
    truck.centerY=350;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==-2) {//Level 2 opens after loading screen
    lvl2 = true;
    truck.centerX=390;
    truck.centerY=270;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==32) {
    truck.centerX= 200;
    truck.centerY=390;
    lvl2=true;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==3) {
    lvl3=true;
    truck.centerX=200;
    truck.centerY=60;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==4 ) {
    truck.centerX=0;
    truck.centerY=260;
    lvl4=true;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==-4) {
    truck.centerX=390;
    truck.centerY=260;
    lvl4=true;
    movetolvl=0;
  }

  if (!loadingScreen && movetolvl==5) {//Level 5 opens after loading screen
    lvl5 = true; 
    truck.centerX=0;
    truck.centerY=350;
    movetolvl=0;
  }
}
public void nightPanel() {
  if (nightMode) {
    fill(0);
    rectMode(CENTER);
    rect(200, 255, 60, 175);
    fill(0);
    ellipse(200, 255, 100, 100);

    fill(levercolorR, levercolorG, 0);
    rect(200, 255, 30, 30);
  } else {
    //Draw moon symbol on level 3
    stroke(232, 230, 220);
    fill(224, 218, 175);
    ellipse(200, 255, 100, 100);
    stroke(232, 230, 220, 140);
    noFill();
    ellipse(170, 235, 20, 20);
    ellipse(195, 260, 20, 20);
    ellipse(225, 225, 20, 20);
  }

  if (!drawBridge) {
    levercolorR=255;
    levercolorG=0;
  } else {
    levercolorR=0;
    levercolorG=255;
  }
}
public void pScreen() {
  if (truck.centerX == 250 && packageEvent) {
    packageScreen = true;
    packageEvent = false;
  }

  if (truck.centerX > 400) {//Level1 complete
    lvl1 = false;
    lvl2unlocked = true;
    movetolvl=2;
    loadingScreen=true;
  }

  if (packageScreen) {
    background(185, 41, 229);

    //Add image box
    rectMode(CORNER);
    noStroke();
    fill(0);
    rect(0, 55, 400, 295);

    //Add image
    image(img3, 0, 55, 400, 295);

    textSize(18);
    text("Postal Worker: Please drop off this package", 5, 370);
    text("at the UCSC building!", 5, 390);
    truck.centerX=250; //Truck stopped at pickup
    truck.centerY=290;

    if (keyPressed) {//Close chat box
      if (key == 'c' | key == 'C') {
        packageScreen = false;
      }
    }
  }

  if (keyPressed) { //Return to main menu
    if (key == BACKSPACE) {
      lvl1 = false;
      mainMenu = true;
    }
  }
}
public void hScreen() {
  if (hospitalScreen) {
    background(185, 41, 229);

    //Add image box
    rectMode(CORNER);
    noStroke();
    fill(0);
    rect(0, 55, 400, 295);
    //Add image
    image(img2, 0, 55, 400, 295);

    textSize(18);
    text("Nurse: The draw bridge ahead is not down.", 5, 370);
    text("Maybe you could find someway to do it?", 5, 390);
    truck.centerX=340; //Truck stopped at building exit during screen
    truck.centerY=150;

    if (keyPressed) {//Close chat box
      if (key == 'c' | key == 'C') {
        hospitalScreen = false;
      }
    }

    if (keyPressed) { //Return to main menu
      if (key == BACKSPACE) {
        lvl1 = false;
        mainMenu = true;
      }
    }
  }//**END OF HOSPITAL SCREEN BRACKET
}
public void minutero() {
  if (startTimer) { //Timer for the game
    timer = timer + 1;
  } else {
    timer=0; //This resets the timer when turned off
  }
}
public void flashMinutero() {
  if (startFlashTimer && levelSelect) { //Timer for the game
    flashTimer = flashTimer + 1;
  } else {
    flashTimer=0; //This resets the timer when turned off
  }
}

public void playMusic() { //Play music upon click
  //player.loop();
}

public void stopMusic() { //Play music upon click
  //player.pause();
 
}
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

  public void create() {
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
  
  public void move() {
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
  public void settings() {  size(400, 400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "JEv8_musicFix" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
