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

public class AdvanceActivity extends AppCompatActivity {

    private TextView moveCounter;
    private TextView feedbackText;
    private Button[] buttons;
    private Boolean bad_move=false;
    private static final Integer[] goal = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0};
    private ArrayList<Integer> cells = new ArrayList<Integer>();
    public String options;
    private ArrayList<Bitmap> bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        options=Level_Option.Option;
        Toast.makeText(getApplicationContext(), options, Toast.LENGTH_SHORT).show();
        bitmaps=new ArrayList<Bitmap>();
        buttons=findButtons();

        for(int i=1;i<25;i++)
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
        for (int i = 1; i < 25; i++) {
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

        moveCounter.setText("0");
        feedbackText.setText(R.string.game_feedback_text);

        if(options.equals("1"))
            getBitmaps(BitmapFactory.decodeResource(getResources(), R.drawable.bbt));
        else if(options.equals("2")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.himym), dpToPx(300), dpToPx(300)));
        }else if(options.equals("3")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.tahm), dpToPx(300), dpToPx(300)));
        }else if(options.equals("4")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.friends), dpToPx(300), dpToPx(300)));
        }else if(options.equals("5")) {
            getBitmaps(bitmapScaler(BitmapFactory.decodeResource(getResources(), R.drawable.tmf), dpToPx(300), dpToPx(300)));
        }

    }


    public Button[] findButtons() {
        Button[] b = new Button[25];
        b[0] = (Button) findViewById(R.id.Button00a);
        b[1] = (Button) findViewById(R.id.Button01a);
        b[2] = (Button) findViewById(R.id.Button02a);
        b[3] = (Button) findViewById(R.id.Button03a);
        b[4] = (Button) findViewById(R.id.Button04a);
        b[5] = (Button) findViewById(R.id.Button05a);
        b[6] = (Button) findViewById(R.id.Button06a);
        b[7] = (Button) findViewById(R.id.Button07a);
        b[8] = (Button) findViewById(R.id.Button08a);
        b[9] = (Button) findViewById(R.id.Button09a);
        b[10] = (Button) findViewById(R.id.Button10a);
        b[11] = (Button) findViewById(R.id.Button11a);
        b[12] = (Button) findViewById(R.id.Button12a);
        b[13] = (Button) findViewById(R.id.Button13a);
        b[14] = (Button) findViewById(R.id.Button14a);
        b[15] = (Button) findViewById(R.id.Button15a);
        b[16] = (Button) findViewById(R.id.Button16a);
        b[17] = (Button) findViewById(R.id.Button17a);
        b[18] = (Button) findViewById(R.id.Button18a);
        b[19] = (Button) findViewById(R.id.Button19a);
        b[20] = (Button) findViewById(R.id.Button20a);
        b[21] = (Button) findViewById(R.id.Button21a);
        b[22] = (Button) findViewById(R.id.Button22a);
        b[23] = (Button) findViewById(R.id.Button23a);
        b[24] = (Button) findViewById(R.id.Button24a);
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
                if(b_pos==1||b_pos==5)
                    bad_move=false;
                break;
            case(1):
                if(b_pos==0||b_pos==6||b_pos==2)
                    bad_move=false;
                break;
            case(2):
                if(b_pos==1||b_pos==7||b_pos==3)
                    bad_move=false;
                break;
            case(3):
                if(b_pos==2||b_pos==8||b_pos==4)
                    bad_move=false;
                break;
            case(4):
                if(b_pos==3||b_pos==9)
                    bad_move=false;
                break;
            case(5):
                if(b_pos==0||b_pos==6||b_pos==10)
                    bad_move=false;
                break;
            case(6):
                if(b_pos==1||b_pos==5||b_pos==7||b_pos==11)
                    bad_move=false;
                break;
            case(7):
                if(b_pos==2||b_pos==6||b_pos==8||b_pos==12)
                    bad_move=false;
                break;
            case(8):
                if(b_pos==3||b_pos==7||b_pos==9||b_pos==13)
                    bad_move=false;
                break;
            case(9):
                if(b_pos==4||b_pos==8||b_pos==14)
                    bad_move=false;
                break;
            case(10):
                if(b_pos==5||b_pos==11||b_pos==15)
                    bad_move=false;
                break;
            case(11):
                if(b_pos==6||b_pos==10||b_pos==16||b_pos==12)
                    bad_move=false;
                break;
            case(12):
                if(b_pos==7||b_pos==11||b_pos==13||b_pos==17)
                    bad_move=false;
                break;
            case(13):
                if(b_pos==8||b_pos==12||b_pos==14||b_pos==18)
                    bad_move=false;
                break;
            case(14):
                if(b_pos==9||b_pos==13||b_pos==19)
                    bad_move=false;
                break;
            case(15):
                if(b_pos==10||b_pos==16||b_pos==20)
                    bad_move=false;
                break;
            case(16):
                if(b_pos==11||b_pos==15||b_pos==17||b_pos==21)
                    bad_move=false;
                break;
            case(17):
                if(b_pos==12||b_pos==16||b_pos==18||b_pos==22)
                    bad_move=false;
                break;
            case(18):
                if(b_pos==13||b_pos==17||b_pos==19||b_pos==23)
                    bad_move=false;
                break;
            case(19):
                if(b_pos==14||b_pos==18||b_pos==24)
                    bad_move=false;
                break;
            case(20):
                if(b_pos==15||b_pos==21)
                    bad_move=false;
                break;
            case(21):
                if(b_pos==16||b_pos==20||b_pos==22)
                    bad_move=false;
                break;
            case(22):
                if(b_pos==17||b_pos==21||b_pos==23)
                    bad_move=false;
                break;
            case(23):
                if(b_pos==18||b_pos==22||b_pos==24)
                    bad_move=false;
                break;
            case(24):
                if(b_pos==19||b_pos==23)
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

        for(int i=0;i<25;i++)
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
        for(int i=0;i<25;i++)
        {
            int text=cells.get(i);
            AbsoluteLayout.LayoutParams absParams =
                    (AbsoluteLayout.LayoutParams)buttons[text].getLayoutParams();
            switch(i)
            {   case(0):

                absParams.x = dpToPx(5);
                absParams.y = dpToPx(5);
                buttons[text].setLayoutParams(absParams);
                break;
                case(1):
                    absParams.x = dpToPx(67);
                    absParams.y = dpToPx(5);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(2):

                    absParams.x = dpToPx(129);
                    absParams.y = dpToPx(5);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(3):
                    absParams.x = dpToPx(191);
                    absParams.y = dpToPx(5);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(4):

                    absParams.x =dpToPx(253);
                    absParams.y =dpToPx(5);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(5):

                    absParams.x = dpToPx(5);
                    absParams.y =dpToPx(67);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(6):

                    absParams.x = dpToPx(67);
                    absParams.y = dpToPx(67);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(7):

                    absParams.x = dpToPx(129);
                    absParams.y = dpToPx(67);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(8):

                    absParams.x = dpToPx(191);
                    absParams.y = dpToPx(67);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(9):

                    absParams.x =dpToPx(253);
                    absParams.y =dpToPx(67);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(10):

                    absParams.x = dpToPx(5);
                    absParams.y =dpToPx(129);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(11):

                    absParams.x = dpToPx(67);
                    absParams.y = dpToPx(129);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(12):

                    absParams.x = dpToPx(129);
                    absParams.y = dpToPx(129);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(13):

                    absParams.x = dpToPx(191);
                    absParams.y = dpToPx(129);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(14):

                    absParams.x = dpToPx(253);
                    absParams.y = dpToPx(129);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(15):

                    absParams.x = dpToPx(5);
                    absParams.y = dpToPx(191);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(16):

                    absParams.x = dpToPx(67);
                    absParams.y = dpToPx(191);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(17):

                    absParams.x = dpToPx(129);
                    absParams.y = dpToPx(191);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(18):

                    absParams.x = dpToPx(191);
                    absParams.y = dpToPx(191);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(19):

                    absParams.x = dpToPx(253);
                    absParams.y = dpToPx(191);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(20):

                    absParams.x = dpToPx(5);
                    absParams.y = dpToPx(253);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(21):

                    absParams.x = dpToPx(67);
                    absParams.y = dpToPx(253);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(22):

                    absParams.x = dpToPx(129);
                    absParams.y = dpToPx(253);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(23):

                    absParams.x = dpToPx(191);
                    absParams.y = dpToPx(253);
                    buttons[text].setLayoutParams(absParams);
                    break;
                case(24):

                    absParams.x = dpToPx(253);
                    absParams.y = dpToPx(253);
                    buttons[text].setLayoutParams(absParams);
                    break;
            }
        }

    }

    public int find_pos(int element)
    {
        int i=0;
        for(i=0;i<25;i++)
        {
            if(cells.get(i)==element)
            {
                break;
            }
        }
        return i;
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void reset(View v)
    {
        Intent intent=new Intent(this,AdvanceActivity.class);
        startActivity(intent);
        this.finish();
    }
    public void back(View v)
    {
        super.onBackPressed();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
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

    public void getBitmaps(Bitmap bitmap){
        int w,h;
        w=bitmap.getWidth();
        h=bitmap.getHeight();
        bitmaps.add(Bitmap.createBitmap(bitmap,0,0,w/5,h/5));
        buttons[1].setBackground(new BitmapDrawable(bitmaps.get(0)));
        bitmaps.add(Bitmap.createBitmap(bitmap,w/5,0,w/5,h/5));
        buttons[2].setBackground(new BitmapDrawable(bitmaps.get(1)));
        bitmaps.add(Bitmap.createBitmap(bitmap,2*w/5,0,w/5,h/5));
        buttons[3].setBackground(new BitmapDrawable(bitmaps.get(2)));
        bitmaps.add(Bitmap.createBitmap(bitmap,3*w/5,0,w/5,h/5));
        buttons[4].setBackground(new BitmapDrawable(bitmaps.get(3)));
        bitmaps.add(Bitmap.createBitmap(bitmap,4*w/5,0,w/5,h/5));
        buttons[5].setBackground(new BitmapDrawable(bitmaps.get(4)));
        bitmaps.add(Bitmap.createBitmap(bitmap,0,h/5,w/5,h/5));
        buttons[6].setBackground(new BitmapDrawable(bitmaps.get(5)));
        bitmaps.add(Bitmap.createBitmap(bitmap,w/5,h/5,w/5,h/5));
        buttons[7].setBackground(new BitmapDrawable(bitmaps.get(6)));
        bitmaps.add(Bitmap.createBitmap(bitmap,2*w/5,h/5,w/5,h/5));
        buttons[8].setBackground(new BitmapDrawable(bitmaps.get(7)));
        bitmaps.add(Bitmap.createBitmap(bitmap,3*w/5,h/5,w/5,h/5));
        buttons[9].setBackground(new BitmapDrawable(bitmaps.get(8)));
        bitmaps.add(Bitmap.createBitmap(bitmap,4*w/5,h/5,w/5,h/5));
        buttons[10].setBackground(new BitmapDrawable(bitmaps.get(9)));
        bitmaps.add(Bitmap.createBitmap(bitmap,0,2*h/5,w/5,h/5));
        buttons[11].setBackground(new BitmapDrawable(bitmaps.get(10)));
        bitmaps.add(Bitmap.createBitmap(bitmap,w/5,2*h/5,w/5,h/5));
        buttons[12].setBackground(new BitmapDrawable(bitmaps.get(11)));
        bitmaps.add(Bitmap.createBitmap(bitmap,2*w/5,2*h/5,w/5,h/5));
        buttons[13].setBackground(new BitmapDrawable(bitmaps.get(12)));
        bitmaps.add(Bitmap.createBitmap(bitmap,3*w/5,2*h/5,w/5,h/5));
        buttons[14].setBackground(new BitmapDrawable(bitmaps.get(13)));
        bitmaps.add(Bitmap.createBitmap(bitmap,4*w/5,2*h/5,w/5,h/5));
        buttons[15].setBackground(new BitmapDrawable(bitmaps.get(14)));
        bitmaps.add(Bitmap.createBitmap(bitmap,0,3*h/5,w/5,h/5));
        buttons[16].setBackground(new BitmapDrawable(bitmaps.get(15)));
        bitmaps.add(Bitmap.createBitmap(bitmap,w/5,3*h/5,w/5,h/5));
        buttons[17].setBackground(new BitmapDrawable(bitmaps.get(16)));
        bitmaps.add(Bitmap.createBitmap(bitmap,2*w/5,3*h/5,w/5,h/5));
        buttons[18].setBackground(new BitmapDrawable(bitmaps.get(17)));
        bitmaps.add(Bitmap.createBitmap(bitmap,3*w/5,3*h/5,w/5,h/5));
        buttons[19].setBackground(new BitmapDrawable(bitmaps.get(18)));
        bitmaps.add(Bitmap.createBitmap(bitmap,4*w/5,3*h/5,w/5,h/5));
        buttons[20].setBackground(new BitmapDrawable(bitmaps.get(19)));
        bitmaps.add(Bitmap.createBitmap(bitmap,0,4*h/5,w/5,h/5));
        buttons[21].setBackground(new BitmapDrawable(bitmaps.get(20)));
        bitmaps.add(Bitmap.createBitmap(bitmap,w/5,4*h/5,w/5,h/5));
        buttons[22].setBackground(new BitmapDrawable(bitmaps.get(21)));
        bitmaps.add(Bitmap.createBitmap(bitmap,2*w/5,4*h/5,w/5,h/5));
        buttons[23].setBackground(new BitmapDrawable(bitmaps.get(22)));
        bitmaps.add(Bitmap.createBitmap(bitmap,3*w/5,4*h/5,w/5,h/5));
        buttons[24].setBackground(new BitmapDrawable(bitmaps.get(23)));

    }

    public void writeHighScore(int c)
    {
        try
        {   String s=String.valueOf(c);
            FileOutputStream fileOutputStream =openFileOutput("Advancehighscore", Context.MODE_PRIVATE);
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
            FileInputStream fi=openFileInput("Advancehighscore");
            int i=0;
            while((i=fi.read())!=-1)
            {
                str=str+(char)i;
            }
            c=Integer.parseInt(str);
            return c;
        }catch (FileNotFoundException e){
             c=9999999;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return c;
    }
}
