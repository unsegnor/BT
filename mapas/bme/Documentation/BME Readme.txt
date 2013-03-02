BME V0.11.5
===========

Contents:

1)	WHAT IS BME?

2)	USING BME

3)	ADVANCED FEATURES

4)	CUSTOM TILES / TILESETS

5)	NEW FEATURES SINCE LAST VERSION

6)	CONTACTS

7)	AND FINALLY...



1) WHAT IS BME?
---------------

BME is the Battletech Map Editor, by Jake Staines.

The object of BME is to provide a tool that makes creating maps
for Battletech, or any hex-based game, easy. You can then export these
maps to Bitmap files or print them out and use them in your games. 

BME is free, so long as you follow the stipulations on the BME 
homepage:

	http://www.geocities.com/Jake_Staines/

One last thing - BME is Beta software - I can't guarantee it's bug-
free. What's more, because I want to keep BME free, I cannot accept
any responsibility for any damage whatsoever BME causes or may cause.
(and so on - you know the drill - BME is provided 'as is' and all that)

Again, see the web site for more details.

2) USING BME
------------

I cannot provide an in-depth tutorial here - When BME is *finished*, I will
put together a Windows Help file to go with the final release.

Terrain forming:
Use the raise and lower tool (click on the ground hex you want to
raise or lower) to change the elevation. LMB for raise, RMB for lower. 
Use the toggle tag tool - the one with a number on it - to turn level 
tags on or off if you want to.
When you lower terrain past lvl 0, it becomes water.

Features:
Trees, buildings, rubble etc - simply place these by selecting the
appropriate feature from the menu and clicking in the hex you want them 
to appear in. You can only have one feature per hex, however.
Clear features by right-clicking the tool or using the clear tool.

Roads and Rivers:
Click in the hex you want the road or river to start with the 
appropriate tool selected. Then click in each connecting hex in turn, 
until you have the whole road or river laid out. To clear a connection
between two hexes, simply click over it again.

Zones:
Left-click a hex with a zone tool (eg Concrete) to turn that zone on in
that hex - the ground and/or zone edges will be drawn in as appropriate. 
Right-click a hex to turn the selected zone off again in that hex.

3) ADVANCED FEATURES
--------------------

Hex Numbering:
To number your hexes using the standard FASA system, simply go into
the 'map' menu and check Hex Numbering. You can change the font, size
and colour in the 'font' option.

Files:
Saving, Opening etc is done exactly the same way you would expect from
a standard windows app.

BMP Export:
To print your map you will need to export it to a BMP and print it in 
another app. Simply choose 'export' from the 'map' menu and name the
file - you can now open it with your favourite paint/publication 
program and print it out.

Tilesets:
You can use multiple tilesets with BME - but no more than one at a time. 
The .BME file (saved maps) do not store which tileset they use, they are 
displayed as using whichever tileset you have loaded.
	(Currently, if you load a map that used mroe features/zones/whatever 
	than you currently have available, you get an 'Access Violation'
	error. Don't worry about it - it's being addressed...)

Printing:
To print the map you have open, simply select the print option in the
file menu, then choose one of the options from the sub-menu. If you want to
use the map to play BTech on, then select 'Normal'. This will print it out 
with the hexes the same size as those used on the FASA maps. The other 
options, Half-Size and Quarter-size, are for games that use smaller hexes, so
BMe can print directly with hexes half the size of FASAs, or one-quarter the 
size.
I recommend using the lowest DPI you have available for BTech-sized maps, as
the image will be low-resolution anyway and higher DPI will just eat up your
printer memory.

Command Line Parameters:
Firstly, if you specify a file as a command line parameter, BME will load 
that file - this means you can associate BME with the .BME filetype, for 
example. 
If you use the parameter /t (for tileset) followed by the name of a 
tileset (direct path if not under the BME directory) then BME will start 
up using that tileset. (eg BME.exe /tdustball would load the dustball 
tileset. If your path includes spaces you must enclose it in double quotes 
(").

4) CUSTOM TILES
---------------

By using BMETiles.inf, you can now stick in extra custom tiles, in the 
form of grounds, roads, features, zones or hills. More detail as to how 
to do this is in the  document, 'a Tutorial to making Custom 
Tilesets for BME' - available from the official BME homepage. (It is also
avaiable from the Mailing List shared Files Archive)
For now, you will have to be able to figure it out pretty much from the 
BMETiles.inf provided as an example in the graphics directory. 

Tilesets are sets of custom tiles, stored in a directory structure of 
their own and with an accompanying file to tell BME how to use them. 
Creating a tileset is no mean feat, as it is currently barely documented 
apart from the example I give as the default graphics set, but it is by 
no means impossible. Several custom tilesets are available on the shared 
files directory on the BMEList, and these will soon be transferred to the 
BME Homepage.

To change tilesets while using BME, you must save your map (as BME will 
not do this automatically and it will be lost in the process - support for 
this is coming soon but not done yet) and choose 'Tileset...' from the map 
menu. BME will prompt you as to whether you want to lose your map and load 
the tileset. If you choose to, you must select the 'BMETiles.inf' file 
from your tileset directory - BME will then restart using the new tileset.

5) NEW IN THIS VERSION
----------------------

* JPEG Support. Basic, and it's likely to stay that way until the rewrite.

* More options (incl. colour!) on the text overlays.

This version also fixes a couple of rather major bugs.

6) CONTACTS
-----------

With Queries, Suggestions, Problems and Bug Reports, Contact me at:

	csued@dcs.warwick.ac.uk

The official BME homepage is currently at:
	http://www.geocities.com/Jake_Staines/

You can subscribe to the BME Mailing list, kindly hosted by Onelist, at:
	http://www.onelist.com/BMEList/subscribe/
and post at:
	BMEList@Onelist.com (Membership of Onelist and BMEList required)

7) AND FINALLY...
-----------------

BME is copyright Jake Staines, 1999.
Battletech is a registered trademark of FASA corporation, used 
without permission. no infringement of copyright is intended - I
intended BME for use with Battletech, and so called it the 'Battletech'
map editor. 
