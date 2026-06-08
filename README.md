A Wordsearch made in java using javaFX and more. A group project for APCSA.



Repo was forked from JavaFX, but has left the fork network.




How to start the virtual display and open your window:

#You need to paste the following commands into your terminal, use (CTRL+Shift+V), not (CTRL+V) since it is a terminal. Just copy and paste everything within the brackets into the terminal and hit enter.
 [
pkill Xvfb || true
pkill fluxbox || true
pkill x11vnc || true
pkill websockify || true #kill me

~/start-gui.sh #wait 10 seconds, until you get a pop up about opened ports, if you don't get a pop up about it, just try restarting the codespace. 

export DISPLAY=:1
cd HelloFX/Maven/hellofx
mvn javafx:run #it should open a test window, congrats

]
#Once you do this and wait 15 seconds or so, head to the ports section to the right of the terminal and open the link for port 6080. Once you are on the tab click vnc.html and after the redirect click the large connect button. You should see the game.

#If you don't run the last 3 commands again, lines 21-23 in a new bash terminal, you open that by heading to terminal and clicking the plus button.