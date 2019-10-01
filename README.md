# CPSC410DSL
DSL for image manipulation, gif and collage creation. 

# Environment Setup
Make sure you have `OpenCV` installed from [here](https://opencv.org/releases/).
The Java project uses gradle as it's build system.


# Examples
## Environment
imgs/ = cat1.png, cat2.png, cat3.png, dog1.png, dog2.png, dog3.png

filter_options:
- blur
- sharpen 
- vignette
- blackandwhite

collage_templates:
- c2 (creates a collage with 2 photos)
- c3 (creates a collage with 3 photos)
- c4 (creates a collage with 4 photos)


## Example 1
```
load all in imgs

filter all with vignette

save all png
```

## Example 2
```
load all in imgs

filter cat1 with blur
filter dog1 with sharpen

sequence cat1
sequence dog1

save cat1,dog1 gif as catdog
```

## Example 3
```
load all in imgs

collage cat1 and cat2 with c2

save cat1,cat2 png as cutecats
```

# Problem
## Environment
holiday/ = sun1.png, park1.png, park2.png, beach1.png, beach3.png, beach5.png, cat1.png

## Task
Create a gif showing images from the beach, then images from the park. Apply a vignette to only images from the park. Blur images from the beach.


