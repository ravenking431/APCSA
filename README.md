A Wordsearch made in java using javaFX and more. A group project for APCSA.

Repo was forked from JavaFX, but has left the fork network.

How to start the virtual display and open your window:

paste 

~/start-gui.sh #wait 10 seconds, until you get a pop up about opened ports, if you don't get a pop up about it, just try restarting the codespace. 

export DISPLAY=:1
cd HelloFX/Maven/hellofx
mvn javafx:run #it should open a test window, congrats

pkill Xvfb || true
pkill fluxbox || true
pkill x11vnc || true
pkill websockify || true #kill me