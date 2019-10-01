# CPSC410DSL
DSL for image manipulation, gif and collage creation. 


# Examples
## Environment
imgs/ = cat1.png, cat2.png, cat3.png, dog1.png, dog2.png, dog3.png

filter_options:
- blur
- sharpen 
- vignette
- blackandwhite


## Example 1
```
load from imgs: all

filter vignette: all

save all
```

## Example 2
```
load from imgs: all

filter blur: cat1
filter sharpen: dog1

create gif catdog: cat1, dog1

save catdog
```

## Example 3
```
load from imgs: all

create collage cutecats: cat1, cat2, cat3

save cutecats
```

# Problem
## Environment
holiday/ = sun1.png, park1.png, park2.png, beach1.png, beach3.png, beach5.png, cat1.png

## Task
Create a gif showing images from the beach, then images from the park. Apply a vignette to only images from the park. Blur images from the beach.


