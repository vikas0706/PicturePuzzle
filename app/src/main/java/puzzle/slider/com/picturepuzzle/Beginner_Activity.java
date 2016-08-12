package puzzle.slider.com.picturepuzzle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Beginner_Activity extends AppCompatActivity {

    private TextView moveCounter;
    private TextView feedbackText;
    private Button[] buttons;
    private Boolean bad_move=false;
    private static final Integer[] goal = new Integer[] {1,2,3,4,5,6,7,8,0};
    private ArrayList<Bitmap> bitmaps;
    private ArrayList<Integer> cells = new ArrayList<Integer>();
    public String options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_);
        options=Level_Option.Option;
        Toast.makeText(getApplicationContext(),options,Toast.LENGTH_SHORT).show();
        buttons=findButtons();
        bitmaps=new ArrayList<Bitmap>();
        
        for(int i=1;i<9;i++)
        {
            this.cells.add(i);
        }
        this.cells.add(0);
        Random random=new Random();
         //random cells array
        Collections.shuffle(this.cells, random);
        fill_grid();

        moveCounter = (TextView) findViewById(R.id.MoveCounter);
        feedbackText = (TextView) findViewById(R.id.FeedbackText);
        buttons[0].setVisibility(View.INVISIBLE);
        for (int i = 1; i < 9; i++) {
            buttons[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction()==MotionEvent.ACTION_DOWN)
                    {
                        makeMove((Button) v);
                    }
                    return false;
                }
            });
        }
        if(options.equals("1"))
            getBitmaps(BitmapFactory.decodeResource(getResources(),R.drawable.bbt));
        else if(options.equals("2")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.himym), dpToPx(300), dpToPx(300)));
        }else if(options.equals("3")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.tahm), dpToPx(300), dpToPx(300)));
        }else if(options.equals("4")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.friends), dpToPx(300), dpToPx(300)));
        }else if(options.equals("5")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.tmf), dpToPx(300), dpToPx(300)));
        }

        moveCounter.setText("0");
        feedbackText.setText(R.string.game_feedback_text);

    }

    public Button[] findButtons() {
        Button[] b = new Button[9];
        b[0] = (Button) findViewById(R.id.Button00);
        b[1] = (Button) findViewById(R.id.Button01);
        b[2] = (Button) findViewById(R.id.Button02);
        b[3] = (Button) findViewById(R.id.Button03);
        b[4] = (Button) findViewById(R.id.Button04);
        b[5] = (Button) findViewById(R.id.Button05);
        b[6] = (Button) findViewById(R.id.Button06);
        b[7] = (Button) findViewById(R.id.Button07);
        b[8] = (Button) findViewById(R.id.Button08);

        return b;
    }

    public void makeMove(final Button b) {
        bad_move=true;
        int b_text,b_pos,zuk_pos;
        b_text=Integer.parseInt((String) b.getText());
        b_pos=find_pos(b_text);
        zuk_pos=find_pos(0);
        switch(zuk_pos)
        {
            case(0):
                if(b_pos==1||b_pos==3)
                    bad_move=false;
                break;
            case(1):
                if(b_pos==0||b_pos==2||b_pos==4)
                    bad_move=false;
                break;
            case(2):
                if(b_pos==1||b_pos==5)
                    bad_move=false;
                break;
            case(3):
                if(b_pos==0||b_pos==4||b_pos==6)
                    bad_move=false;
                break;
            case(4):
                if(b_pos==1||b_pos==3||b_pos==5||b_pos==7)
                    bad_move=false;
                break;
            case(5):
                if(b_pos==2||b_pos==4||b_pos==8)
                    bad_move=false;
                break;
            case(6):
                if(b_pos==3||b_pos==7)
                    bad_move=false;
                break;
            case(7):
                if(b_pos==4||b_pos==6||b_pos==8)
                    bad_move=false;
                break;
            case(8):
                if(b_pos==5||b_pos==7)
                    bad_move=false;
                break;
        }

        if(bad_move==true)
        {
            feedbackText.setText("Wrong Move");
            return;
        }
        feedbackText.setText("Move OK");
        cells.remove(b_pos);
        cells.add(b_pos, 0);
        cells.remove(zuk_pos);
        cells.add(zuk_pos, b_text);

        fill_grid();
        moveCounter.setText(Integer.toString(Integer.parseInt((String) moveCounter.getText())+1));

        for(int i=0;i<9;i++)
        {
            if(cells.get(i)!=goal[i])
            {
                return;
            }
        }
        feedbackText.setText("WINNER");
        if(Integer.parseInt((String)moveCounter.getText())<readHighScore()){
            AlertDialog.Builder builder=new AlertDialog.Builder(this)
                    .setTitle("WINNER")
                    .setMessage("You Got a NEW HIGHSCORE - "+ moveCounter.getText().toString());
            Dialog dialog=builder.create();
            dialog.show();
            writeHighScore(Integer.parseInt((String) moveCounter.getText()));
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this)
                    .setTitle("WINNER")
                    .setMessage("HIGHSCORE - "+readHighScore()+"\nYOUR SCORE - "+ moveCounter.getText().toString());
            Dialog dialog=builder.create();
            dialog.show();
        }

    }

    public int dpToPx(int dp)
    {
        DisplayMetrics displayMetrics=getApplicationContext().getResources().getDisplayMetrics();
        int px=Math.round(dp*(displayMetrics.xdpi/DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public void fill_grid()
    {
        for(int i=0;i<9;i++)
        {
            int text=cells.get(i);
            AbsoluteLayout.LayoutParams absParams =
                    (AbsoluteLayout.LayoutParams)buttons[text].getLayoutParams();
            switch(i)
            {case(0):

                absParams.x = dpToPx(5);
                absParams.y = dpToPx(5);
                buttons[text].setLayoutParams(absParams);
                break;
                case(1):
                    absParams.x = dpToPx(110);
                    absParams.y = dpToPx(5);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(2):

                    absParams.x = dpToPx(215);
                    absParams.y = dpToPx(5);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(3):
                    absParams.x = dpToPx(5);
                    absParams.y = dpToPx(110);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(4):

                    absParams.x =dpToPx(110);
                    absParams.y =dpToPx(110);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(5):

                    absParams.x = dpToPx(215);
                    absParams.y =dpToPx(110);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(6):

                    absParams.x = dpToPx(5);
                    absParams.y = dpToPx(215);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(7):

                    absParams.x = dpToPx(110);
                    absParams.y = dpToPx(215);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(8):

                    absParams.x = dpToPx(215);
                    absParams.y = dpToPx(215);
                    buttons[text].setLayoutParams(absParams);
                    break;

            }
        }

    }

    public int find_pos(int element)
    {
        int i=0;
        for(i=0;i<9;i++)
        {
            if(cells.get(i)==element)
            {
                break;
            }
        }
        return i;
    }

   public void reset(View v)
   {
       Intent intent=new Intent(this,Beginner_Activity.class);
       startActivity(intent);
       this.finish();
   }
   public void back(View v)
   {
       super.onBackPressed();
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void getBitmaps(Bitmap bitmap){
        bitmaps.add(Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[1].setBackground(new BitmapDrawable(bitmaps.get(0)));
        bitmaps.add(Bitmap.createBitmap(bitmap,bitmap.getWidth()/3,0,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[2].setBackground(new BitmapDrawable(bitmaps.get(1)));
        bitmaps.add(Bitmap.createBitmap(bitmap,2*bitmap.getWidth()/3,0,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[3].setBackground(new BitmapDrawable(bitmaps.get(2)));
        bitmaps.add(Bitmap.createBitmap(bitmap,0,bitmap.getHeight()/3,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[4].setBackground(new BitmapDrawable(bitmaps.get(3)));
        bitmaps.add(Bitmap.createBitmap(bitmap,bitmap.getWidth()/3,bitmap.getHeight()/3,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[5].setBackground(new BitmapDrawable(bitmaps.get(4)));
        bitmaps.add(Bitmap.createBitmap(bitmap,2*bitmap.getWidth()/3,bitmap.getHeight()/3,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[6].setBackground(new BitmapDrawable(bitmaps.get(5)));
        bitmaps.add(Bitmap.createBitmap(bitmap,0,2*bitmap.getHeight()/3,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[7].setBackground(new BitmapDrawable(bitmaps.get(6)));
        bitmaps.add(Bitmap.createBitmap(bitmap,bitmap.getWidth()/3,2*bitmap.getHeight()/3,bitmap.getWidth()/3,bitmap.getHeight()/3));
        buttons[8].setBackground(new BitmapDrawable(bitmaps.get(7)));
    }

    public Bitmap bitmapScaler(Bitmap bitmap,int newWidth,int newHieght){
        Bitmap scaledBitmap=Bitmap.createBitmap(newWidth,newHieght, Bitmap.Config.ARGB_8888);
        float ratioX=newWidth/(float)bitmap.getWidth();
        float ratioY=newHieght/(float)bitmap.getHeight();
        float middleX=newWidth/2.0f;
        float middleY=newHieght/2.0f;
        Matrix scaleMatrix=new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);
        Canvas canvas=new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        return scaledBitmap;
    }

    public void writeHighScore(int c)
    {
        try
        {   String s=String.valueOf(c);
            FileOutputStream fileOutputStream =openFileOutput("Beginerhighscore", Context.MODE_PRIVATE);
            fileOutputStream.write(s.getBytes());
            fileOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int readHighScore()
    {  int c=0;
        String str=new String();
        try
        {
            FileInputStream fi=openFileInput("Beginerhighscore");
            int i=0;
            while((i=fi.read())!=-1)
            {
                str=str+(char)i;
            }
            c=Integer.parseInt(str);
            return c;
        }catch (FileNotFoundException e){
            c=99999999;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return c;
    }
}
