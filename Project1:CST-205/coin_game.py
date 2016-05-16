import pygame
import random

#colors for backgrounds 
black = [0, 0, 0]
white = [255,255,255]
red = [255,0,0]

#sets the screens size 
screen_width = 900 #x coordinate
screen_height = 500 #y coordinate
#imported pictures
coin = pygame.image.load("bitcoin.png")
playerImage = pygame.image.load("mario1.png")

#creates my player and settings for it
class Coins(pygame.sprite.Sprite):
    
    #methods for class Coins 
    def __init__(self):
        # calls the Sprite parent class
        pygame.sprite.Sprite.__init__(self) 
        self.image = pygame.Surface([39,39])#blank surface
        self.image.blit(coin,[0,0]) # image of coins
        self.rect = self.image.get_rect() #sets the rectangle around the image
        
    def reset_pos(self):
        self.rect.y = random.randrange(-300, -20) # more smooth affect
        self.rect.x = random.randrange(screen_width - 20) #coins are set random in the x coordinate 
         
    def update(self):
        #adds one to y every time update is called
        self.rect.y += 1
        # if coin is not on screen anymore reset position to the top of screen
        if self.rect.y > (screen_height + self.rect.height):
            self.reset_pos()#calls the methods reset pos
            
class Player(pygame.sprite.Sprite):
    def __init__ (self):
        pygame.sprite.Sprite.__init__(self) 
        self.image = pygame.Surface([25,40])#blank surface
        self.image.blit(playerImage,[0,0]) # sets image of mario 
        self.image.set_colorkey(black) # takes black away from background of image
        self.rect = self.image.get_rect() #sets the rectangle around the image
        
    def update(self):
        pos = pygame.mouse.get_pos() # gets x,y coordinate of mouse 
        # sets the players character on the x and y coordinates of the mouse
        self.rect.x = pos[0] 
        self.rect.y = pos[1]

#sets the game up
class Game():
    #three empty variables are used and player creation
    block_list = None
    all_sprites_list = None
    player = None
    #game over is to check if the game is over or keeps running
    game_over = False

    # how many points the player has received 
    score = 0
    #class methods
    def __init__(self):
        self.score = 0
        self.game_over = False
        # list are set to groups(list)
        self.block_list = pygame.sprite.Group() #list of coins
        self.all_sprites_list = pygame.sprite.Group()# list of all coins including player
        
        #for loop makes 75 coins
        for i in range(75):
            coin = Coins()
            #sets them to random place on the screen 
            coin.rect.x = random.randrange(screen_width)
            coin.rect.y = random.randrange(-200,screen_height)
            # add the coins to list
            self.block_list.add(coin) #adds coins to block_list 
            self.all_sprites_list.add(coin) #adds coins to list all_sprites 

        self.player = Player()# creates player (mario)
        self.all_sprites_list.add(self.player) #adds player to list all_sprites list

    def process(self):
        #main program loop
        for event in pygame.event.get(): # user does something
            if event.type == pygame.QUIT: # clicks closed
                return True # if you want the window closed 
            if event.type == pygame.MOUSEBUTTONDOWN:
                if self.game_over: # restarts the game
                    self.__init__()

        return False#keep program running
        
    def run_logic(self):

         if not self.game_over:
             #Moves all sprites
             self.all_sprites_list.update()
        
             #see if the player has collided with a coin
             blocks_hit_list = pygame.sprite.spritecollide(self.player, self.block_list, True)

             #for loop adds 1 to score every time there is a collision
             for coin in blocks_hit_list:
                 self.score += 1
                 print(self.score)
             #if there are no more coins left stop the program 
             if len(self.block_list) == 0:
                 self.game_over = True  # changes to true, shuts program off
                 
    # shows the display of background and text.
    def display_frame(self,screen):
        
        #background image
        background = pygame.image.load("game.png").convert()
        screen.blit(background,[0,0])
        # if game is over prints game over,click to restart 
        if self.game_over:
            font = pygame.font.SysFont("serif", 25)
            text = font.render("Game Over, click to restart", True, black)
            # sets the screen size
            x = (screen_width // 2) - (text.get_width() // 2)
            y = (screen_height // 2) - (text.get_height() // 2)
            screen.blit(text,[x,y])
        #if game is not over display score
        if not self.game_over:
            font = pygame.font.SysFont("serif", 25)
            text = font.render("Score: " +str(self.score), True, white)
            # sets the screen size
            x = 5
            y = 5
            screen.blit(text,[x,y]) 
        #if game is not over keep all sprites on screen 
        if not self.game_over:
            self.all_sprites_list.draw(screen) #draws all the sprites 

        pygame.display.flip()
         

def main():
    #starts the game
    pygame.init()
    #sets the screen 
    size = [screen_width,screen_height]
    screen = pygame.display.set_mode(size)
    
    #Caption and sets the mouse to not get shown on screen
    pygame.display.set_caption("Coin Game")
    pygame.mouse.set_visible(False)

    #condition statements, sets variable and time condition.
    done = False
    clock= pygame.time.Clock()
    game = Game()

    while not done:
        # process events
        done = game.process() #if True ends the game.

        #update object positions 

        game.run_logic()

        #draw the current frame
        game.display_frame(screen)

        #pause for next frame 
        clock.tick(60)
        
    pygame.quit()

#calls main
if __name__ == "__main__":
    main()
    
    
