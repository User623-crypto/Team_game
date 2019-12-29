package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {
    public int pos_X;
    public int pos_Y;
    private int width;
    private int height;


    //Jump
    private int _jumppower=5;
    private int holding;
    private boolean finishjumping=false;


    private int image_turn = 1;
    private boolean move_left;
    private boolean move_up;
    Bitmap player_bitmap,player_walk1,player_walk2;

    public Player(int _X, int _Y, int _width, int _height, Resources res)
    {
        this.pos_X = _X;
        this.pos_Y = _Y;
        this.width = _width;
        this.height = _height;

        player_bitmap = BitmapFactory.decodeResource(res,R.drawable.alienblue);
        player_walk1 = BitmapFactory.decodeResource(res,R.drawable.alien_walk1);
        player_walk2 = BitmapFactory.decodeResource(res,R.drawable.alien_walk2);

        player_bitmap = Bitmap.createScaledBitmap(player_bitmap,width,height,false);
        player_walk1 = Bitmap.createScaledBitmap(player_walk1,width,height,false);
        player_walk2 = Bitmap.createScaledBitmap(player_walk2,width,height,false);

        this.holding=pos_Y;

    }


    public boolean to_left()
    {
        if(pos_X < 11)
            return false;

        return  true;

    }

    public  boolean to_up()
    {
        if(pos_Y < 11)
            return false;
        return true;
    }

    public  boolean to_bottom()
    {
        if(pos_Y > player_bitmap.getHeight() - 11)
            return false;
        return true;
    }

    public int player_X()
    {
        return pos_X;

    }

    public void jump()
    {

        if(_jumppower<13 && !finishjumping)
        {
            pos_Y-=15; _jumppower++;
        }
        else if ((_jumppower>=13 )&&pos_Y<=holding)
        {
            pos_Y+=7;
            finishjumping=true;
        }
        else {
            finishjumping=false;_jumppower=5;
        }
    }

    public  int player_Y()
    {
        return pos_Y;
    }

    public  void move_right()
    {

            pos_X +=2;
            jump();
    }
    public  void  move_left()
    {

            pos_X -=10;
            jump();

    }
    public  void move_up()
    {
        pos_Y -= 2;
    }

    public  void move_down()
    {
        pos_Y += 2;
    }

    public int player_width()
    {
        return player_bitmap.getWidth();
    }
    public int player_height()
    {
        return player_bitmap.getHeight();
    }
    Bitmap getImage()
    {
        if(image_turn == 1)
        {
            image_turn++;
            return player_bitmap;

        }
        if(image_turn == 2)
        {
            image_turn ++;
            return player_walk1;

        }
        if(image_turn == 3)
        {
            image_turn ++;
            return player_walk1;

        }

        if(image_turn == 4)
        {
            image_turn ++;
            return player_walk2;

        }
        image_turn = 1;
        return player_walk2;
    }





}
