#include <SDL.h>
#include <SDL_ttf.h>
#include <stdio.h>
#include <stdlib.h>
static int Board[3][3]={0};
static int check = 10;
static SDL_Window* baseWindow;
static SDL_Renderer* mainRenderer;
static SDL_Event event;
static int mousex,mousey;
int i,j;

int f1=0,f2=0;

int main(int arg,char* argv[]){

    srand(time(0));

    if(SDL_Init(SDL_INIT_EVERYTHING) <  0)
    {
        printf("SDL Initialization faild \n");
        return 0;
    }

    //Initialize SDL_ttf
    if( TTF_Init() == -1 ) {
        printf("SDL Initialization faild \n");
        return 0;
    }

    baseWindow = SDL_CreateWindow(
            "TicTacToe",
            SDL_WINDOWPOS_UNDEFINED,
            SDL_WINDOWPOS_UNDEFINED,
            300,
            300,
            0
    );
    mainRenderer = SDL_CreateRenderer(
            baseWindow,
            0,
            SDL_RENDERER_ACCELERATED
    );

    while(SDL_WaitEvent(&event)) {
        switch (event.type) {
            case SDL_QUIT:
                printf("Quiting the application ...");
                exit(0);
                break;
            case SDL_MOUSEBUTTONDOWN:
                SDL_GetMouseState(&mousex,&mousey);
                printf("%d %d",mousex,mousey);
                int x;
                int y;
                x=mousex/100;
                y=mousey/100;
                if(event.button.button == SDL_BUTTON_LEFT && check == 10)
                {
                            Show();
                            f2=0;
                            while(f2==0)
                            {

                                    Board[x][y]=1;
                                    f2=1;

                            }
                            Show();
                            if(Check()==1)
                            {
                                printf("You Win!!!");
                                //getch();
                                check = 0;
                                f1=1;
                                Show();
                            }
                             else if (Check()==-1)
                                {
                                printf("Nobody Wins");
                                //getch();
                                check = 1;
                                f1=1;
                                Show();
                                }
                            else
                            {
                                Computer();
                                Show();
                                if(Check()==1)
                                {
                                    printf("Computer Wins");
                                    //getch();
                                    check = 2;
                                    f1=1;
                                    Show();
                                }
                            }

                }

                break;
        }

    }
    return 0;


}


///////////////////////////////////////////////////////////////////////////////


void Show()
{

        SDL_Surface *imgX = SDL_LoadBMP("x.bmp");
        SDL_Surface *imgO = SDL_LoadBMP("o.bmp");
        SDL_Texture* imgTextureX = SDL_CreateTextureFromSurface(
            mainRenderer,
            imgX
        );
        SDL_Texture* imgTextureO = SDL_CreateTextureFromSurface(
            mainRenderer,
            imgO
        );

        for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            SDL_Rect printRect={
                    .x=i*100,
                    .y=j*100,
                    .w=100,
                    .h=100
            };

            if(Board[i][j]==1){
            //SDL_SetRenderDrawColor(mainRenderer, 68, 108, 255, 255);
            //SDL_BlitSurface(imgX, NULL, mainRenderer, &printRect);
            SDL_RenderCopy(mainRenderer,imgTextureX,NULL,&printRect);
            }else if(Board[i][j]==-1){
            //SDL_SetRenderDrawColor(mainRenderer, 68,108,179, 255);
            SDL_RenderCopy(mainRenderer,imgTextureO,NULL,&printRect);
            }else{
            //SDL_SetRenderDrawColor(mainRenderer, 255, 255, 255, 255);
            SDL_RenderCopy(mainRenderer,NULL,NULL,&printRect);
            }
            //SDL_RenderCopy(mainRenderer, text, NULL, &printRect);
            //SDL_RenderCopy(mainRenderer,msgTexture,NULL,&printRect);
        }
        }
        //printf(" %d ",check);
        if(check != 10){

        TTF_Font* Sans = TTF_OpenFont("OpenSans-Regular.ttf", 24);

        SDL_Color red = {255, 0, 0};
        //SDL_Surface* surfaceMessage;
        char message[50];

        if(check == 0){
             strcpy(message, "YOU WINS");
        }else if(check == 1){
             strcpy(message, "NOBODY WINS");
        }else if(check == 2){
             strcpy(message, "COMPUTER WINS");
        }

        SDL_Surface* surfaceMessage = TTF_RenderUTF8_Solid(Sans, message, red);
        SDL_Texture* Message = SDL_CreateTextureFromSurface(mainRenderer, surfaceMessage); //now you can convert it into a texture

        SDL_Rect Message_rect; //create a rect
        Message_rect.x = 50;  //controls the rect's x coordinate
        Message_rect.y = 100; // controls the rect's y coordinte
        Message_rect.w = 200; // controls the width of the rect
        Message_rect.h = 100; // controls the height of the rect

        //Mind you that (0,0) is on the top left of the window/screen, think a rect as the text's box, that way it would be very simple to understance

        //Now since it's a texture, you have to put RenderCopy in your game loop area, the area where the whole code executes

        SDL_RenderCopy(mainRenderer, Message, NULL, &Message_rect);
        }


    SDL_RenderPresent(mainRenderer);

}

int Check()
{
    if(Board[0][0]==Board[0][1] && Board[0][1]==Board[0][2] && Board[0][0]!=0)
        return 1;
    else if(Board[1][0]==Board[1][1] && Board[1][1]==Board[1][2] && Board[1][0]!=0)
        return 1;
    else if(Board[2][0]==Board[2][1] && Board[2][1]==Board[2][2] && Board[2][0]!=0)
        return 1;
    else if(Board[0][0]==Board[1][0] && Board[1][0]==Board[2][0] && Board[0][0]!=0)
        return 1;
    else if(Board[0][1]==Board[1][1] && Board[1][1]==Board[2][1] && Board[1][1]!=0)
        return 1;
    else if(Board[0][2]==Board[1][2] && Board[1][2]==Board[2][2] && Board[0][2]!=0)
        return 1;
    else if(Board[0][0]==Board[1][1] && Board[1][1]==Board[2][2] && Board[0][0]!=0)
        return 1;
    else if(Board[0][2]==Board[1][1] && Board[1][1]==Board[2][0] && Board[1][1]!=0)
        return 1;
    else if(Board[0][0]!=0 && Board[0][1]!=0 && Board[0][2]!=0 && Board[1][0]!=0 && Board[1][1]!=0 &&
            Board[1][2]!=0 && Board[2][0]!=0 && Board[2][1]!=0 && Board[2][2]!=0)
        return -1;
    else
        return 0;
}

void Computer()
{
    //COMPUTER IS WINNING:

    if(Board[0][0]==Board[0][1] && Board[0][2]==0 && Board[0][0]!=0 && Board[0][0]==-1)
        Board[0][2]=-1;

    else if(Board[0][0]==Board[0][2] && Board[0][1]==0  && Board[0][0]!=0 && Board[0][0]==-1)
        Board[0][1]=-1;

    else if(Board[0][1]==Board[0][2] && Board[0][0]==0  && Board[0][1]!=0 && Board[0][1]==-1)
        Board[0][0]=-1;

    else if(Board[0][0]==Board[1][0] && Board[2][0]==0 &&  Board[0][0]!=0 && Board[0][0]==-1)
        Board[2][0]=-1;

    else if(Board[0][0]==Board[2][0] && Board[1][0]==0  && Board[0][0]!=0 && Board[0][0]==-1)
        Board[1][0]=-1;

    else if(Board[1][0]==Board[2][0] && Board[0][0]==0  && Board[1][0]!=0 && Board[1][0]==-1)
        Board[0][0]=-1;

    else if(Board[2][0]==Board[2][1] && Board[2][2]==0  && Board[2][0]!=0 && Board[2][0]==-1)
        Board[2][2]=-1;

    else if(Board[2][0]==Board[2][2] && Board[2][1]==0  && Board[2][0]!=0 && Board[2][0]==-1)
        Board[2][1]=-1;

    else if(Board[2][1]==Board[2][2] && Board[2][0]==0  && Board[2][1]!=0 && Board[2][1]==-1)
        Board[2][0]=-1;

    else if(Board[0][2]==Board[1][2] && Board[2][2]==0 && Board[0][2]!=0 && Board[0][2]==-1)
        Board[2][2]=-1;

    else if(Board[0][2]==Board[2][2] && Board[1][2]==0 && Board[0][2]!=0 && Board[0][2]==-1)
        Board[1][2]=-1;

    else if(Board[1][2]==Board[2][2] && Board[0][2]==0 && Board[2][2]!=0 && Board[1][2]==-1)
        Board[0][2]=-1;

    else if(Board[0][0]==Board[1][1] && Board[2][2]==0 && Board[0][0]!=0 && Board[0][0]==-1)
        Board[2][2]=-1;

    else if(Board[0][0]==Board[2][2] && Board[1][1]==0  && Board[0][0]!=0 && Board[0][0]==-1)
        Board[1][1]=-1;

    else if(Board[1][1]==Board[2][2] && Board[0][0]==0  && Board[1][1]!=0 && Board[1][1]==-1)
        Board[0][0]=-1;

    else if(Board[0][2]==Board[1][1] && Board[2][0]==0  && Board[0][2]!=0 && Board[0][2]==-1)
        Board[2][0]=-1;

    else if(Board[0][2]==Board[2][0] && Board[1][1]==0 && Board[0][2]!=0 && Board[0][2]==-1)
        Board[1][1]=-1;

    else if(Board[2][0]==Board[1][1] && Board[0][2]==0 && Board[2][0]!=0 && Board[2][0]==-1)
        Board[0][2]=-1;

    else if(Board[1][0]==Board[1][1] && Board[1][2]==0 && Board[1][0]!=0 && Board[1][0]==-1)
        Board[1][2]=-1;

    else if(Board[1][0]==Board[1][2] && Board[1][1]==0 && Board[1][0]!=0 && Board[1][0]==-1)
        Board[1][1]=-1;

    else if(Board[1][1]==Board[1][2] && Board[1][0]==0 && Board[1][1]!=0 && Board[1][1]==-1)
        Board[1][0]=-1;

    else if(Board[0][1]==Board[1][1] && Board[2][1]==0 && Board[0][1]!=0 && Board[0][1]==-1)
        Board[2][1]=-1;

    else if(Board[0][1]==Board[2][1] && Board[1][1]==0 && Board[0][1]!=0 && Board[0][1]==-1)
        Board[1][1]=-1;

    else if(Board[1][1]==Board[2][1] && Board[0][1]==0 && Board[1][1]!=0 && Board[1][1]==-1)
        Board[0][1]=-1;

    //PREVENTING FROM COMPUTER LUSING:

    else if(Board[0][0]==Board[0][1] && Board[0][2]==0 && Board[0][0]!=0 && Board[0][0]==1)
        Board[0][2]=-1;

    else if(Board[0][0]==Board[0][2] && Board[0][1]==0  && Board[0][0]!=0 && Board[0][0]==1)
        Board[0][1]=-1;

    else if(Board[0][1]==Board[0][2] && Board[0][0]==0  && Board[0][1]!=0 && Board[0][1]==1)
        Board[0][0]=-1;

    else if(Board[0][0]==Board[1][0] && Board[2][0]==0 &&  Board[0][0]!=0 && Board[0][0]==1)
        Board[2][0]=-1;

    else if(Board[0][0]==Board[2][0] && Board[1][0]==0  && Board[0][0]!=0 && Board[0][0]==1)
        Board[1][0]=-1;

    else if(Board[1][0]==Board[2][0] && Board[0][0]==0  && Board[1][0]!=0 && Board[1][0]==1)
        Board[0][0]=-1;

    else if(Board[2][0]==Board[2][1] && Board[2][2]==0  && Board[2][0]!=0 && Board[2][0]==1)
        Board[2][2]=-1;

    else if(Board[2][0]==Board[2][2] && Board[2][1]==0  && Board[2][0]!=0 && Board[2][0]==1)
        Board[2][1]=-1;

    else if(Board[2][1]==Board[2][2] && Board[2][0]==0  && Board[2][1]!=0 && Board[2][1]==1)
        Board[2][0]=-1;

    else if(Board[0][2]==Board[1][2] && Board[2][2]==0 && Board[0][2]!=0 && Board[0][2]==1)
        Board[2][2]=-1;

    else if(Board[0][2]==Board[2][2] && Board[1][2]==0 && Board[0][2]!=0 && Board[0][2]==1)
        Board[1][2]=-1;

    else if(Board[1][2]==Board[2][2] && Board[0][2]==0 && Board[2][2]!=0 && Board[1][2]==1)
        Board[0][2]=-1;

    else if(Board[0][0]==Board[1][1] && Board[2][2]==0 && Board[0][0]!=0 && Board[0][0]==1)
        Board[2][2]=-1;

    else if(Board[0][0]==Board[2][2] && Board[1][1]==0  && Board[0][0]!=0 && Board[0][0]==1)
        Board[1][1]=-1;

    else if(Board[1][1]==Board[2][2] && Board[0][0]==0  && Board[1][1]!=0 && Board[1][1]==1)
        Board[0][0]=-1;

    else if(Board[0][2]==Board[1][1] && Board[2][0]==0  && Board[0][2]!=0 && Board[0][2]==1)
        Board[2][0]=-1;

    else if(Board[0][2]==Board[2][0] && Board[1][1]==0 && Board[0][2]!=0 && Board[0][2]==1)
        Board[1][1]=-1;

    else if(Board[2][0]==Board[1][1] && Board[0][2]==0 && Board[2][0]!=0 && Board[2][0]==1)
        Board[0][2]=-1;

    else if(Board[1][0]==Board[1][1] && Board[1][2]==0 && Board[1][0]!=0 && Board[1][0]==1)
        Board[1][2]=-1;

    else if(Board[1][0]==Board[1][2] && Board[1][1]==0 && Board[1][0]!=0 && Board[1][0]==1)
        Board[1][1]=-1;

    else if(Board[1][1]==Board[1][2] && Board[1][0]==0 && Board[1][1]!=0 && Board[1][1]==1)
        Board[1][0]=-1;

    else if(Board[0][1]==Board[1][1] && Board[2][1]==0 && Board[0][1]!=0 && Board[0][1]==1)
        Board[2][1]=-1;

    else if(Board[0][1]==Board[2][1] && Board[1][1]==0 && Board[0][1]!=0 && Board[0][1]==1)
        Board[1][1]=-1;

    else if(Board[1][1]==Board[2][1] && Board[0][1]==0 && Board[1][1]!=0 && Board[1][1]==1)
        Board[0][1]=-1;

    else
    {
        int i,j,f=0;
        while(f==0)
        {
            i=rand()%3;
            j=rand()%3;
            if(Board[i][j]==0)
            {
                Board[i][j]=-1;
                f=1;
            }
        }
    }
}















