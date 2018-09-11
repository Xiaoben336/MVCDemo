package com.example.zjf.mvcdemo;
/**
*@description SearchNumInterface的作用是对getNum方法的一个封装，具体的实现是通过SearchNumModelImpl。
 *
 *              比如我后期要对查询联系人进行修改，不是从系统的数据库里进行查找，需要到第三方联系人Abc.apk
 *              数据库里去匹配联系人，那么我只需要新写一个AbcSearchNumModelImpl的实现类继承SearchNumInterface
 *              这个接口就可以了。
 *
*@author zjf
*@date 2018/9/11 21:10
*/
public interface SearchNumInterface {
    void getNum(String name,SearchNumberListener listener);
}
