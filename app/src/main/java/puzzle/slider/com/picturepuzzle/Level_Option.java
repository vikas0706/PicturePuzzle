package puzzle.slider.com.picturepuzzle;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Level_Option extends AppCompatActivity {
   public String level;
    public static String Option="0";
    public Button[] levelsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level__option);
        Intent i=getIntent();
        levelsButton=new Button[5];
        levelsButton[0]=(Button)findViewById(R.id.img1);
        levelsButton[1]=(Button)findViewById(R.id.img2);
        levelsButton[2]=(Button)findViewById(R.id.img3);
        levelsButton[3]=(Button)findViewById(R.id.img4);
        levelsButton[4]=(Button)findViewById(R.id.img5);
        Bitmap bg1=bitmapScaler(new BitmapFactory().decodeResource(getResources(), R.drawable.bbt), dpToPx(150),dpToPx(200));
        levelsButton[0].setBackground(new BitmapDrawable(bg1));
        bg1=bitmapScaler(new BitmapFactory().decodeResource(getResources(), R.drawable.himym),dpToPx(150),dpToPx(200));
        levelsButton[1].setBackground(new BitmapDrawable(bg1));
        bg1=bitmapScaler(new BitmapFactory().decodeResource(getResources(),R.drawable.tahm),dpToPx(150),dpToPx(200));
        levelsButton[2].setBackground(new BitmapDrawable(bg1));
        bg1=bitmapScaler(new BitmapFactory().decodeResource(getResources(),R.drawable.friends),dpToPx(150),dpToPx(200));
        levelsButton[3].setBackground(new BitmapDrawable(bg1));
        bg1=bitmapScaler(new BitmapFactory().decodeResource(getResources(),R.drawable.tmf),dpToPx(150),dpToPx(200));
        levelsButton[4].setBackground(new BitmapDrawable(bg1));
        level=i.getStringExtra(LevelSelector.beg);
    }

    public void b1(View v)
    {
        if(level.equals("1"))
        {
            Intent in=new Intent(this,Beginner_Activity.class);
            Option="1";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
        if(level.equals("2"))
        {
            Intent in=new Intent(this,Intermediate_Activity.class);
            Option="1";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
        if(level.equals("3"))
        {
        Intent in=new Intent(this,AdvanceActivity.class);
        Option="1";
        startActivity(in);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
         }

    }
    public void b2(View v)
    {
        if(level.equals("1"))
        {
            Intent in=new Intent(this,Beginner_Activity.class);
            Option="2";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }else
        if(level.equals("2"))
        {
            Intent in=new Intent(this,Intermediate_Activity.class);
            Option="2";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
        if(level.equals("3"))
        {
            Intent in=new Intent(this,AdvanceActivity.class);
            Option="2";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }
    public void b3(View v)
    {
        if(level.equals("1"))
        {
            Intent in=new Intent(this,Beginner_Activity.class);
            Option="3";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }else
        if(level.equals("2"))
        {
            Intent in=new Intent(this,Intermediate_Activity.class);
            Option="3";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
        if(level.equals("3"))
        {
            Intent in=new Intent(this,AdvanceActivity.class);
            Option="3";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }
    public void b4(View v)
    {
        if(level.equals("1"))
        {
            Intent in=new Intent(this,Beginner_Activity.class);
            Option="4";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }else
        if(level.equals("2"))
        {
            Intent in=new Intent(this,Intermediate_Activity.class);
            Option="4";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
        if(level.equals("3"))
        {
            Intent in=new Intent(this,AdvanceActivity.class);
            Option="4";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }

    }
    public void b5(View v)
    {
        if(level.equals("1"))
        {
            Intent in=new Intent(this,Beginner_Activity.class);
            Option="5";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }else
        if(level.equals("2"))
        {
            Intent in=new Intent(this,Intermediate_Activity.class);
            Option="5";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
        if(level.equals("3"))
        {
            Intent in=new Intent(this,AdvanceActivity.class);
            Option="5";
            startActivity(in);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level__option, menu);
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
        canvas.drawBitmap(bitmap,middleX-bitmap.getWidth()/2,middleY-bitmap.getHeight()/2,new Paint(Paint.FILTER_BITMAP_FLAG));
        return scaledBitmap;
    }
    public int dpToPx(int dp)
    {
        DisplayMetrics displayMetrics=getApplicationContext().getResources().getDisplayMetrics();
        int px=Math.round(dp*(displayMetrics.xdpi/DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
