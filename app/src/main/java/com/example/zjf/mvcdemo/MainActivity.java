package com.example.zjf.mvcdemo;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
*@description 布局文件activity_main.xml可以视为视图
 *
 *              MainActivity.java为Controller
 *
 *              User现在要搜索联系人名字为Jack的号码是多少
 *              View：在EditTextView中填写了Jack，点击搜索按钮Button；
 *              Control：接收到交互View发出的搜索请求，和字符串Jack，通知Model进行数据查询；
 *              Model：Model接受到关键字Jack，进行名字匹配，完毕后，通过接口给View发送号码，通知View显示；
 *
 *              整个过程如下：
 *              View交互：User点击button
 *              Control：响应点击，发送name给Model；
 *              Model：通过名字查询号码，返回给View显示；
 *              这就是MVC模型的简单例子。
 *
*@author zjf
*@date 2018/9/11 17:26
*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener ,SearchNumberListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mEditTextName;
    private Button mButtonSearch;
    private TextView mTextViewNum;
    private SearchNumModelImpl mSearchNumModelImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //new了一个SearchNumModelImpl搜索联系人的Model
        mSearchNumModelImpl = new SearchNumModelImpl(this);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
    }

    private void initView() {
        mEditTextName = (EditText) findViewById(R.id.et_name);
        mTextViewNum = (TextView)findViewById(R.id.tv_number);
        mButtonSearch = (Button)findViewById(R.id.bt_search);

        mButtonSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_search:
                //通过SearchNumModelImpl的getNum获得号码。
                mSearchNumModelImpl.getNum(mEditTextName.getText().toString(),this);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onSuccess(String num) {
        mTextViewNum.setText(num);
    }

    @Override
    public void onError() {
        Toast.makeText(this,"no number", Toast.LENGTH_SHORT).show();
    }
}
