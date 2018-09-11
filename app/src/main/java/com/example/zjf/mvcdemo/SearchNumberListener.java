package com.example.zjf.mvcdemo;
/**
*@description  SearchNumListener有两个方法，作用就是model进行一系列操作后方便通知View结果，
 *              可以是查询联系人，也可以是网络请求，也可以是算法等等；是和View的一个桥梁
*
*@author zjf
*@date 2018/9/11 17:31
*/
public interface SearchNumberListener {
    void onSuccess(String num);
    void onError();
}
