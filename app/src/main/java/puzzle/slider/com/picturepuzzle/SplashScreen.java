package puzzle.slider.com.picturepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread timer= new Thread()
        {
            public void run()
            {   int time=0;
                String s="Loading";
                for(int i=0;i<3;i++){
                try
                {
                    sleep(500);
                    s=s+". ";
                    setText(s);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                }
                {
                    Intent openMain= new Intent("puzzle.slider.com.picturepuzzle.MAINACTIVITY");
                    startActivity(openMain);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            }
        };
        timer.start();
    }

    private void setText(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.textView)).setText(s);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
}
