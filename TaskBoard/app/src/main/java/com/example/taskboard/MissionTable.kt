package com.example.taskboard

import cn.bmob.v3.BmobObject

class MissionTable:BmobObject(){
    var title:String?=null
    var publisherID:String?=null
    var reward:String?=null
    var detail:String?=null
    var ddlT:String?=null
    var receiverID:String?=null
    var receiverName:String?=null
    var receiveT:String?=null
    var receiveB:String?=null
    var publisherName:String?=null
    var completeR:String?=null
    var completeC:String?=null
    //接受这完成
    //发布者确认完成
    fun getTitle1():String?
    {
        return this.title
    }
}