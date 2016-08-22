# java-2drenderer-prototyp
An Prototyp for an 2D renderer in Java 8 with OpenGL & LWJGL

Use OpenGL to render - you dont have to concern about window & context creation and so on.

## Requirements
  - Maven (you can also convert pom.xml files to gradle files)
  - LWJGL 3.0
  - log4j

## Features
  - Window Creation with GLFW (you can add other context systems)
  - game state system
  - possibility to execute tasks in ui thread
  - multi threading: one thread for rendering and one extra thread for update game states
  - allow it to use OpenGL very easy