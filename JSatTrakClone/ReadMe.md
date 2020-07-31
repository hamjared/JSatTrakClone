### JSatTrakClone

Inspiration take from [here](https://www.gano.name/shawn/JSatTrak/)

## Overview

This a simple application capable of plotting satellite ground tracks and displaying 2 dimensional representations of Orbits around earth.

## How to use

Run Main.java

3 satellites come built in as well as one ground station

Change between satellites using the dropdown menu. 

Click refresh button to regenerate the ground track plot. 

## Add Satellite

Click add satellite
Input Orbital Parameters
Warning (Input validation of parameters is not fully implemented)

Example:

- Name: Hubble
- Eccentricity: 0
- Inclination (degrees): 51.7
- Semi Major Axis (meters): 6.918e6
- Longitude of Ascending node (degrees): 174.7
- Argument of Periapsis (degrees): 289.4  

Select the new satellite from dropdown list and click refresh.

## Orbit Transfer

Warning: Only hohmann transfers are implemented. This may lead to undesired behavior if complex transfers are attempted. 
	Also only transfer between orbits with Argument of Periapsis = 0
	
Example: Transfer included GEO satellite to a LEO orbit. 

- Satellite: GEO
- Eccentricity: 0
- Inclination: 0
- Semi Major Axis: 10e6
- Longitude of Ascending Node: 0
- Argument of Periapsis: 0


## Add Ground Station
Input name, lattitude and longitude of a ground station. 

Click add

Select new ground station from dropdown list and it will display on the map. 




