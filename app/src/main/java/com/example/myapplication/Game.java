package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class Game extends SurfaceView  implements Runnable{


    private Thread game_thread;
    private boolean isPlaying;
    private int screenX;
    private int screenY;
    private Paint paint;

    //Per te bere te pershtatshem per size te ndryshme
    private float ratio_X;
    private float ratio_Y;

    private boolean moveRight;
    private boolean moveLeft;
    //screen of all sizes
    private  float screenRatioX;
    private  float ScreenRatioY;
    //Per te bere background to move.
    private Background background1,background2;
    private Player[] players;
    private Player test_player;

    //Create object
    private GameObject my_wall,my_wall1;


    //Create an array to hold all object.
    GameObject[] object_array;
    public  Game(Context context,int screenX,int screenY)
    {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        ratio_X = 720f / screenX;
        ratio_Y = 1280f / screenY;

        background1 = new Background(screenX,2*screenY,getResources(),R.drawable.idle);
        background2 = new Background(screenX,2*screenY,getResources(),R.drawable.idle_reverse);

        //Background 2 nuk do te shfaqet ne ekran por ai do te jete pas background 1

        background2.x = screenX;


        my_wall = new GameObject(900,500,900,100,getResources());
        my_wall1 = new GameObject(1900,500,900,100,getResources());
        players = new Player[5];
        for(int i =0;i<players.length;i++)
        {
            Player player = new Player(100,400,100,100,getResources());
            players[i] = player;
        }
        test_player = players[0];
        //player = new Player(100,400,100,100,getResources());

        object_array = new GameObject[] {my_wall,my_wall1};
        paint = new Paint();
    }


    //Return background x , y;
    public int background_X()
    {
        return background1.x;
    }

    public int background_Y()
    {
        return background1.y;
    }
    public  void resume()
    {
        isPlaying = true;
        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {

        while(isPlaying)
        {
            update();
            draw();
            sleep();
        }
    }

    public  void pause()
    {
        try {
            isPlaying = false;
            game_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private  void update()
    {




        /*
        //Per te leviz lart
        if(moveLeft == true && (background1.y < 0 || player.pos_Y>0))
        {
            player.move_up();

            //background1.x -= 20;
            //background2.x -= 20;
            if(background1.y == 0)
            {
                background1.y += 0;
                background2.y += 0;


            }
            else if(background1.y < 0)
            {
                background1.y += 10;
                background2.y += 10;

                for(int i = 0;i<object_array.length;i++)
                {
                    //object_array[i].move_right();
                    object_array[i].move_down();
                }
            }

        }
        */
        //Per te leviz poshte
        if( test_player.pos_Y + 100< background1.my_background.getHeight()/2)
        {
            //player.move_right();
           for(int i =0;i<players.length;i++)
               players[i].move_down();



            if(background1.y == background1.my_background.getHeight())
            {

                background1.y -= 0;
                background2.y -= 0;
            }
            //background1.x -= 20;
            //background2.x -= 20;

        if(background1.y > -background1.my_background.getHeight()/2) {
            background1.y -= 10*ratio_X;
            background2.y -= 10*ratio_X;
            for(int i = 0;i<object_array.length;i++)
            {
                //object_array[i].move_left();
                object_array[i].move_up();
            }

        }


        }





        if(moveLeft == true && test_player.to_left())
        {

            for(int i = 0;i<object_array.length;i++)
            {
               // object_array[i].move_right();
            }
            //player.move_right();
            for(int i = 0;i<players.length;i++) {

                players[i].move_left();

/*
                background1.x += 10;
                background2.x += 10;
                if (background1.x - background1.my_background.getWidth() < 0) {
                    //call background 1
                    background1.x = -screenX;
                }
                if (background2.x - background2.my_background.getWidth() < 0) {
                    //call background 1
                    background2.x = screenX;
                }*/

            }


        }
        //Per te leviz poshte
        if(moveRight == true)
        {


            for(int i = 0;i<object_array.length;i++)
            {
                object_array[i].move_left();
            }
            //player.move_right();
            for(int i = 0;i<players.length;i++) {

                    players[i].move_right();


                if(players[i].pos_X < background1.my_background.getHeight()/2)
                {
                    background1.x -= 10;
                    background2.x -= 10;
                }

                if(players[i].pos_X > background1.my_background.getHeight()/2)
                {
                    background1.x -= 20;
                    background2.x -= 20;
                }
                if (background1.x + background1.my_background.getWidth() < 0) {
                    //call background 1
                    background1.x = screenX  + 5;
                }
                if (background2.x + background2.my_background.getWidth() < 0) {
                    //call background 1
                    background2.x = screenX + 5;
                }

            }
        }
    }

    private  void draw()
    {
        if(getHolder().getSurface().isValid()) //if surface view is initiated
        {
            Canvas canvas = getHolder().lockCanvas();//return the current canvas displayed on the screen
            canvas.drawBitmap(background1.my_background,background1.x,background1.y,paint);
            canvas.drawBitmap(background2.my_background,background2.x,background2.y,paint);

           for(int i = 0;i<players.length;i++)
           {
               float my_time = System.currentTimeMillis();
               if(moveRight != true)
                   canvas.drawBitmap(players[0].player_bitmap,players[i].pos_X,players[i].pos_Y,paint);
               if(moveRight == true)
               {

                       canvas.drawBitmap(players[i].getImage(),players[i].pos_X,players[i].pos_Y,paint);

               }
               if(moveLeft == true)
               {

                   canvas.drawBitmap(players[i].getImage(),players[i].pos_X,players[i].pos_Y,paint);

               }
           }

            //draw wall
            canvas.drawBitmap(my_wall.object,my_wall.Object_X(),my_wall.Object_Y(),paint);
            canvas.drawBitmap(my_wall1.object,my_wall1.Object_X(),my_wall1.Object_Y(),paint);
            //canvas ready to show on scrreen
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep()
    {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public  boolean isPlaying()
    {
        return isPlaying;
    }

    //Calls eventListener on Touch for Testing

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //If the user Touches the screen
        if(event.getAction()!=MotionEvent.ACTION_UP)
        {

            if((int)event.getX()>this.getWidth()/2)
            {moveRight=true;
                moveLeft = false;
                }

            if((int)event.getX()<=this.getWidth()/2)
            {moveLeft=true;
                moveRight = false;
            }
            return true;
        }
        else {moveRight=false;moveLeft=false;

            return false;}
    }


}
