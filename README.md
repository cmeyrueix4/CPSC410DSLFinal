# CPSC410DSL
DSL for image manipulation, gif and collage creation. 

Images {
Blur: one image or a list of images
Sharpen: one image or a list of images
Vignette: one image or a list of images
…
…
…
}
(We can basically add any filter we want)
 
Gif {
Stack: list of images
Overlay: overlay a sticker on an image
…
…
…
}
 
Collage {
Template1: choose 3 images
Template2: choose 4 images
Template3: choose 2 images
…
…
…
}
# Examples
## Environment
imgs/ = cat1.png, cat2.png, cat3.png, dog1.png, dog2.png, dog3.png, sticker.png

## Example 1
```
load all in imgs

filter all with vignette

save all png
```

## Example 2
```
load all in imgs

filter cat with blur
filter dog with sharpen

sequence cat
sequence dog
overlay sticker bottom-right

save all gif 1s
```

## Example 3
```
load all in imgs

collage all as c

save gif 1s
```

# Problem
## Environment
holiday/ = park1.png, park2.png, park4.png, park7.png, beach1.png, beach2.png, beach3.png, cat1.png

## Task
Create a gif showing image from the beach, then images from the park. Apply a vignette to only images from the park. Blur images from the beach.


