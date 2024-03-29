package com.shubham.fintech.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shubham.fintech.App.AppController;
import com.shubham.fintech.R;
import com.squareup.picasso.Picasso;
import com.ypyproductions.utils.ApplicationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class full_scoreboard extends AppCompatActivity {
    public static String datapath, cur_status,matchDesc, batsmanId_ing1, outdescription_ing1, run_ing1, four_ing1, ball_ing1, six_ing1, sr_ing1, bowlerId, bowler_over, maiden, run, bowler_wicket, noball, wideball, sr;
    TabLayout tabLayout;
    TextView match_status;
    String[] split_status;
    String value_squad_team2,flag_url1,flag_url2;
    ImageView display_img1,display_img2;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private ActionBar actionBar;

    private LinearLayout flag_layout,descion_layout,tab_layout,contain_body;
    public static final String ADMOB_ID_INTERTESTIAL = "ca-app-pub-9972921565167432/8959364108";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_scoreboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flag_layout = (LinearLayout)findViewById(R.id.flag_layout);
        descion_layout = (LinearLayout)findViewById(R.id.decsion_layout);
        tab_layout = (LinearLayout)findViewById(R.id.tab_layout);
        contain_body = (LinearLayout)findViewById(R.id.fragment_container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        match_status = (TextView) findViewById(R.id.match_status);
        datapath = getIntent().getExtras().getString("datapath");
        cur_status = getIntent().getExtras().getString("status");
        matchDesc = getIntent().getExtras().getString("matchDesc");
        match_status.setText(cur_status);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        display_img1 = (ImageView) findViewById(R.id.display_img);
        display_img2 = (ImageView) findViewById(R.id.display_img2);

        mToolbar.setTitle(matchDesc);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setTitleTextColor(Color.WHITE);

        score_board();
     }



    public void score_board() {

        String SCOR_BOARD = "http://synd.cricbuzz.com/iphone/3.0/match/" + datapath + "scorecard.json";
        // Toast.makeText(this, datapath, Toast.LENGTH_LONG).show();

        final String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
      /*  final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();*/


        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                SCOR_BOARD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject header = null;
                    contain_body.setVisibility(View.VISIBLE);
                    JSONObject match = new JSONObject(response);
                    if (!match.has("matchId")) {
                        descion_layout.setVisibility(View.VISIBLE);
                        Toast.makeText(full_scoreboard.this, cur_status, Toast.LENGTH_SHORT).show();
                    }

                    if (match.has("matchId")) {
                        flag_layout.setVisibility(View.VISIBLE);
                        descion_layout.setVisibility(View.VISIBLE);
                        tab_layout.setVisibility(View.VISIBLE);



                        String matchId = match.getString("matchId");
                        String srsid = match.getString("srsid");
                        String srs = match.getString("srs");
                        String datapath = match.getString("datapath");
                    }
                    if (match.has("header")) {
                        header = match.getJSONObject("header");
                        String startdte = header.getString("startdt");
                        String stTme = header.getString("stTme");
                        String stTmeGMT = header.getString("stTmeGMT");
                        String enddt = header.getString("enddt");
                        String mnum = header.getString("mnum");
                        String type = header.getString("type");
                        String mchDesc = header.getString("mchDesc");
                        String mchState = header.getString("mchState");
                        String TW = header.getString("TW");
                        String decisn = header.getString("decisn");
                        String grnd = header.getString("grnd");
                        String vcity = header.getString("vcity");
                        String vcountry = header.getString("vcountry");
                        String status = header.getString("status");
                        // split_status = status.split("in");
                        String addnStatus = header.getString("addnStatus");
                        String MOM = header.getString("MOM");
                        String NoOfIngs = header.getString("NoOfIngs");
                    }

                    if (match.has("Innings")) {

                        JSONObject Innings = match.getJSONObject("Innings");
                        if (Innings.has("1")) {
                            JSONObject Inning_num_1 = Innings.getJSONObject("1");
                            String battingteamid = Inning_num_1.getString("battingteamid");
                            String battingteam = Inning_num_1.getString("battingteam");


                            tabLayout.addTab(tabLayout.newTab().setText(battingteam).setIcon(Drawable.createFromPath(flag_url1)));

                            String bowlingteam = Inning_num_1.getString("bowlingteam");
                            String bowlingteamid = Inning_num_1.getString("bowlingteamid");
                            String runs = Inning_num_1.getString("runs");
                            String wickets = Inning_num_1.getString("wickets");
                            String overs = Inning_num_1.getString("overs");
                            String innDesc = Inning_num_1.getString("innDesc");
                            String RR = Inning_num_1.getString("RR");

                        }

                        if (Innings.has("2")) {
                            JSONObject Inning_num_2 = Innings.getJSONObject("2");
                            String battingteamid = Inning_num_2.getString("battingteamid");
                            String battingteam = Inning_num_2.getString("battingteam");
                            tabLayout.addTab(tabLayout.newTab().setText(battingteam).setIcon(Drawable.createFromPath(flag_url1)));
                            String bowlingteam = Inning_num_2.getString("bowlingteam");
                            String bowlingteamid = Inning_num_2.getString("bowlingteamid");
                            String runs = Inning_num_2.getString("runs");
                            String wickets = Inning_num_2.getString("wickets");
                            String overs = Inning_num_2.getString("overs");
                            String innDesc = Inning_num_2.getString("innDesc");
                            String RR = Inning_num_2.getString("RR");
                        }
                        if (Innings.has("3")) {
                            JSONObject Inning_num_3 = Innings.getJSONObject("3");
                            String battingteamid = Inning_num_3.getString("battingteamid");
                            String battingteam = Inning_num_3.getString("battingteam");
                            tabLayout.addTab(tabLayout.newTab().setText(battingteam));
                            String bowlingteam = Inning_num_3.getString("bowlingteam");
                            String bowlingteamid = Inning_num_3.getString("bowlingteamid");
                            String runs = Inning_num_3.getString("runs");
                            String wickets = Inning_num_3.getString("wickets");
                            String overs = Inning_num_3.getString("overs");
                            String innDesc = Inning_num_3.getString("innDesc");
                            String RR = Inning_num_3.getString("RR");
                        }
                        if (Innings.has("4")) {

                            JSONObject Inning_num_4 = Innings.getJSONObject("4");
                            String battingteamid = Inning_num_4.getString("battingteamid");
                            String battingteam = Inning_num_4.getString("battingteam");
                            tabLayout.addTab(tabLayout.newTab().setText(battingteam));
                            String bowlingteam = Inning_num_4.getString("bowlingteam");
                            String bowlingteamid = Inning_num_4.getString("bowlingteamid");
                            String runs = Inning_num_4.getString("runs");
                            String wickets = Inning_num_4.getString("wickets");
                            String overs = Inning_num_4.getString("overs");
                            String innDesc = Inning_num_4.getString("innDesc");
                            String RR = Inning_num_4.getString("RR");

                        }
                    }
                    if (match.has("official")) {
                        JSONObject official = match.getJSONObject("official");

                        if (official.has("umpire1")) {
                            JSONObject umpire1 = official.getJSONObject("umpire1");
                            String umpire1_name = umpire1.getString("umpirename");
                            String umpire1_country = umpire1.getString("country");
                            // Toast.makeText(full_scoreboard.this, umpire1_country+umpire1_name, Toast.LENGTH_SHORT).show();
                        }
                        if (official.has("umpire2")) {
                            JSONObject umpire2 = official.getJSONObject("umpire2");
                            String umpire2_name = umpire2.getString("umpirename");
                            String umpire2_country = umpire2.getString("country");
                            // Toast.makeText(full_scoreboard.this, umpire2_country+umpire2_name, Toast.LENGTH_SHORT).show();

                        }
                        if (official.has("umpire3")) {
                            JSONObject umpire3 = official.getJSONObject("umpire3");
                            String umpire3_name = umpire3.getString("umpirename");
                            String umpire3_country = umpire3.getString("country");
                        }
                        if (official.has("referee")) {
                            JSONObject referee = official.getJSONObject("referee");
                            String referee_name = referee.getString("refereename");
                            String referee_country = referee.getString("country");
                        }
                    }
                    if (match.has("team1")) {
                        JSONObject team1 = match.getJSONObject("team1");
                        String team1_id = team1.getString("id");
                        flag_url1 = "http://i.cricketcb.com/cbzandroid/2.0/flags/team_" + team1_id + ".png";

                        Picasso.get().load(flag_url1).into(display_img1);


                        String team1_name = team1.getString("name");
                        String team1_fName = team1.getString("fName");
                        String team1_sName = team1.getString("sName");
                        String flag = team1.getString("flag");
                        JSONArray squad = team1.getJSONArray("squad");
                        for (int k = 0; k < squad.length(); k++) {
                            String value_squad_team1 = squad.getString(k);
                            //Toast.makeText(full_scoreboard.this, value_squad_team1, Toast.LENGTH_SHORT).show();
                        }

                    }
                    if (match.has("team2")) {
                        JSONObject team2 = match.getJSONObject("team2");
                        String team2_id = team2.getString("id");
                        flag_url2 = "http://i.cricketcb.com/cbzandroid/2.0/flags/team_" +team2_id + ".png";
                        Picasso.get().load(flag_url2).into(display_img2);
                        String team2_name = team2.getString("name");
                        String team2_fName = team2.getString("fName");
                        String team2_sName = team2.getString("sName");
                        String team2_flag = team2.getString("flag");
                        JSONArray squad_team2 = team2.getJSONArray("squad");
                        for (int k = 0; k < squad_team2.length(); k++) {
                            value_squad_team2 = squad_team2.getString(k);
                        }

                    }
                    if (match.has("players")) {
                        JSONArray players = match.getJSONArray("players");
                        for (int q = 0; q < players.length(); q++) {
                            JSONObject players_object = players.getJSONObject(q);
                            String player_id = players_object.getString("id");
                            String player_fName = players_object.getString("fName");
                            String player_name = players_object.getString("name");
                            String player_role = players_object.getString("role");

                            //Toast.makeText(full_scoreboard.this, player_id, Toast.LENGTH_SHORT).show();

                        }
                    }
                    if (match.has("valueAdd")) {
                        JSONObject valueAdd = match.getJSONObject("valueAdd");
                        JSONObject alerts = valueAdd.getJSONObject("alerts");
                        String enabled = alerts.getString("enabled");
                        String alerts_type = alerts.getString("type");
                        JSONObject pointstable = valueAdd.getJSONObject("pointsTable");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

              // pDialog.hide();

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }


                Toast.makeText(getApplicationContext(), message + "no internet", Toast.LENGTH_LONG).show();

            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq1, tag_string_req1);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    replaceFragment(new bat_bowl_ining_1());
                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    replaceFragment(new bat_bowl_ining_2());

                } else if (tabLayout.getSelectedTabPosition() == 2) {
                    replaceFragment(new bat_bowl_ining_3());

                } else if (tabLayout.getSelectedTabPosition() == 3) {
                    replaceFragment(new bat_bowl_ining_4());

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(full_scoreboard.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}