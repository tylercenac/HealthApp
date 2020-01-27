package tyler.HealthAppV3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class NutritionCalculatorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView mName, mEmail;
    View mView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_calculator);
///Nav Draw
        Toolbar mToolbar = findViewById(R.id.iToolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mView = mNavigationView.inflateHeaderView(R.layout.nav_header);
        mName = mView.findViewById(R.id.hName);
        mEmail = mView.findViewById(R.id.hEmail);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.nav_draw_open, R.string.nav_draw_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Button calcBtn = (Button) findViewById(R.id.calcBtn);
        calcBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
                TextView tdcText = (TextView) findViewById(R.id.tdcText);
                TextView proteinText = (TextView) findViewById(R.id.proteinText);
                TextView fatsText = (TextView) findViewById(R.id.fatsText);
                TextView carbsText = (TextView) findViewById(R.id.carbsText);

                int weight = Integer.parseInt(weightEditText.getText().toString());


                double dailyCalories = Math.round(weight * 17.5);
                double fats = (dailyCalories * 0.25)/9.375;
                double carbs = (dailyCalories - weight*4 - dailyCalories*0.25)/4;

                tdcText.setText((int) dailyCalories+"");
                proteinText.setText(weight+"");
                fatsText.setText((int)fats+"");
                carbsText.setText((int)carbs+"");

            }});




    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_a:
                Intent m1 = new Intent(this, MainActivity.class);
                startActivity(m1);
                finish();
                break;
            case R.id.nav_b:
                Intent m2 = new Intent(this, RoutinesActivity.class);
                startActivity(m2);
                finish();
                break;
            case R.id.nav_c:
                Intent m3 = new Intent(this, SettingsActivity.class);
                startActivity(m3);
                finish();
                break;
            case R.id.nav_d:
               Intent m4 = new Intent(this, BMIActivity.class);
                startActivity(m4);
                break;

            case R.id.nav_e:
//                Intent m5    = new Intent(this, NutritionCalculatorActivity.class);
//                startActivity(m5);
//                finish();
                break;

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
