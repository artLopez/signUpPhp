import spritesheet

ss = spritesheet.spritesheet('ss.jpg')
# Sprite is 16x16 pixels at location 0,0 in the file...
image = ss.image_at((0, 0, 62, 64))
images = []
# Load two images into an array, their transparent bit is (255, 255, 255)
images = ss.images_at((0, 0, 62, 64),(17, 0, 62,64), colorkey=(255, 255, 255))
