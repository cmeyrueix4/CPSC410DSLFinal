# CPSC410DSL
DSL for image manipulation, gif and collage creation. 


# Examples
## Environment
imgs/ = cat1.png, cat2.png, cat3.png, dog1.png, dog2.png, dog3.png, sticker.png

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

filter cat with blur
filter dog with sharpen

sequence cat
sequence dog
overlay sticker bottom-right

save all gif 2s
```

## Example 3
```
load all in imgs

collage cat1 and cat2 as c2

save c2
```

# Problem
## Environment
holiday/ = park1.png, park2.png, park4.png, park7.png, beach1.png, beach2.png, beach3.png, cat1.png

## Task
Create a gif showing images from the beach, then images from the park. Apply a vignette to only images from the park. Blur images from the beach.


