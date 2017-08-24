package com.mrhi2017.volleytest;

import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2;
    ImageView iv;

    //네트워크 요청작업을 저장해놓는 큐저장소..
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        iv= (ImageView) findViewById(R.id.iv);
        //iv.setImageResource(R.drawable.iu);//<<--OOM에러

        requestQueue= Volley.newRequestQueue(this);
    }

    public void clickBtn(View v){
        String url="http://mrhi2017.dothome.co.kr/test.txt";
        url="http://mrhi2017.dothome.co.kr/index.html";
        url="http://www.daum.net/index.html";


        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

    public void clickBtn2(View v){
        String url="http://mrhi2017.dothome.co.kr/iu.jpg";

        ImageRequest imageRequest= new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }
        }, 1000, 1000, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(imageRequest);
    }

    public void clickBtn3(View v){
        String url="http://mrhi2017.dothome.co.kr/data.json";

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String name= response.getString("name");
                    String msg= response.getString("msg");

                    tv2.append(name+"   "+ msg+"\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    public void clickBtn4(View v){
        String url1="http://mrhi2017.dothome.co.kr/index.html";
        String url2="http://mrhi2017.dothome.co.kr/iu.jpg";
        String url3="http://mrhi2017.dothome.co.kr/data.json";

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        ImageRequest imageRequest= new ImageRequest(url2, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }
        }, 1000, 1000, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String name= response.getString("name");
                    String msg= response.getString("msg");

                    tv2.append(name+"   "+ msg+"\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
        requestQueue.add(imageRequest);
        requestQueue.add(jsonObjectRequest);
    }


    public void clickBtn5(View v){

        String url="http://mrhi2017.dothome.co.kr/postTest.php";

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                new AlertDialog.Builder(MainActivity.this).setMessage(response).create().show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        }){

            //이 메소드의 리턴값이 서버에 전송된 데이터..
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String name="SAM";
                String msg="Hello world!";

                Map<String, String> params= new HashMap<>();
                params.put("name", name);
                params.put("msg", msg);

                return params;
            }

        };

        requestQueue.add(stringRequest);

    }

}
