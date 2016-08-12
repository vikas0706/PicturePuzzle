package puzzle.slider.com.picturepuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LevelSelector extends AppCompatActivity {
    public static final String beg="b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);
    }

    public void beginner(View v)
    {
        Intent in=new Intent(this,Level_Option.class);
        in.putExtra(beg, "1");
        startActivity(in);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
    public void intermediate(View v)
    {
        Intent in=new Intent(this,Level_Option.class);
        in.putExtra(beg,"2");
        startActivity(in);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void advance(View v)
    {
        Intent in=new Intent(this,Level_Option.class);
        in.putExtra(beg,"3");
        startActivity(in);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_selector, menu);
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
}
