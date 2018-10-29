package cn.edu.pku.qiyuanyuan.weatherforecast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.pku.qiyuanyuan.app.MyApplication;
import cn.edu.pku.qiyuanyuan.bean.City;

public class SelectCity extends Activity implements View.OnClickListener {

    private ImageView mBackBtn;
    private ListView cityListLv;

    private List<City> mCityList;
    private MyApplication mApplication;
    private ArrayList<String> mArrayList;

    private String updateCityCode = "-1";
    int updateCode;


   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);

       setContentView(R.layout.select_city);

       mBackBtn = (ImageView) findViewById(R.id.title_back);
       mBackBtn.setOnClickListener(this);

       mApplication = (MyApplication)getApplication();
       mCityList = mApplication.getmCityList();
       mArrayList = new ArrayList<String>();
       for (int i = 0; i < mCityList.size(); i++){
           String cityName = mCityList.get(i).getCity();
           mArrayList.add(cityName);
       }

       cityListLv = (ListView)findViewById(R.id.selectCity_lv);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this, android.R.layout.simple_list_item_1, mArrayList);
       cityListLv.setAdapter(adapter);

       //添加ListView项的点击事件的动作
       AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id){
               updateCode = Integer.parseInt(mCityList.get(position).getNumber());
               Log.d("update city code", Integer.toString(updateCode));
           }
       };
       //为组件绑定监听
       cityListLv.setOnItemClickListener(itemClickListener);
   }

   public void onClick(View v){
       updateCityCode = Integer.toString(updateCode);
       switch (v.getId()){
           case R.id.title_back:
               Intent i = new Intent(this, MainActivity.class);
               i.putExtra("cityCode", updateCityCode);
               Log.d("**", updateCityCode);
               startActivity(i);

//               setResult(RESULT_OK, i);
//               finish();
               break;
           default:
               break;
       }

   }

}
