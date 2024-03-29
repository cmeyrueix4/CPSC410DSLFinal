# CPSC410DSL
DSL for image manipulation, gif and collage creation. 

# Instructions for Running the Project
The project uses OpenCV to implement many of the filters which can complicate running the project. You need to download and install OpenCV and then link the OpenCV Java bindings JAR file. You then need to link the native `.dll` executable to this JAR file to compile and run the project.

We followed [this guide](https://medium.com/@aadimator/how-to-set-up-opencv-in-intellij-idea-6eb103c1d45c) to install opencv with intellij.

**This is much easier to do on a windows system, opencv does not provide pre-compiled code for osx or linux**

# Examples
## Environment
imgs/ = cat1.png, cat2.png, cat3.png, dog1.png, dog2.png, dog3.png

filter_options:
- blur
- sharpen 
- brighten
- darken
- invert
- saturate
- contrast


## Example 1 Applying filters to images
```
load from imgs: dog1, dog2, cat1

filter blur: dog1, cat1
filter invert: dog2

save: dog1, cat1, dog2
```

## Example 2 Applying filters to the same image
```
load from imgs: dog2

copy dog2 as: d1, d2, d3

filter blur: d1
filter sharpen: d2
filter invert: d3

save: d1, d2, d3
```

## Example 3 Creating a gif
```
load from imgs: all

copy cat1 as: cat1.1

filter blur: cat1, cat2
filter sharpen: dog1, cat1.1

gif create catdog: cat1, cat1.1, dog1, cat2

save: catdog
```

## Example 4 Creating a collage
```
load from imgs: all

collage create cutecats: cat1, cat2, cat3

save: cutecats
```

# Problem
## Environment
imgs/ = sun1.png, park1.png, park2.png, beach1.png, beach3.png, beach5.png, cat1.png

## Task
Create a gif showing images from the beach, then images from the park. Apply a brighten to only images from the park. Darken images from the beach.


