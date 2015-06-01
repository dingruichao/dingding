package com.ruhua.service.impl;



import com.ruhua.domain.wx.Article;
import com.ruhua.domain.wx.NewsMessageResponse;
import com.ruhua.domain.wx.ResponseUtil;
import com.ruhua.service.BaseService;
import com.ruhua.service.MsgResponse;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lijing3
 * Date: 14-4-2
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MsgResponseImpl extends BaseService implements MsgResponse {
    private static final Logger logger = Logger.getLogger(MsgResponseImpl.class);

    @Override
    public String settleText(Document doc,String toUser,String fromUser) {
        Element rootElt = doc.getRootElement();
        String content =  rootElt.elementTextTrim("Content");
        logger.info(fromUser + "：" + content);
        if(content.indexOf("/::") >= 0) {
            return null;
        }
        //关键字 年会
        if("年会".equals(content) || "\"年会\"".equals(content) || "O2O年会".equals(content) || "nianhui".equals(content)) {
            return ResponseUtil.responseTextReturn("HI，欢迎来到属于我们的一周年庆典。/:rose\n" +
                    "今天属于我们每一个人，想知道怎么玩？回复以下字母，获取更多：\n" +
                    "1.回复“A”，查看“当天行程和安排”；\n" +
                    "2.回复“B”，查看“晚会节目单”；\n" +
                    "3.回复“C”，查看“现场地图”；\n" +
                    "4.回复“D”，查看“晚会奖品有哪些”；\n" +
                    "5.回复“E”，了解“吐槽弹幕”；", fromUser, toUser);
        }
        //关键字 年会
        if("A".equals(content) || "a".equals(content)) {
            return ResponseUtil.responseTextReturn("★★★★26号安排★★★★\n" +
                    "12：00 北辰楼下西门集合\n" +
                    "注：上海童鞋车辆在南站等候\n" +
                    "       亦庄童鞋12点前到北辰\n" +
                    "14：00 到达阳光丽城温泉酒店\n" +
                    "14：10 前往3层302室开会\n" +
                    "15：30 发放房卡，入住酒店\n" +
                    "~(≧▽≦)~自由活动~(≧▽≦)~\n" +
                    "18：30 晚宴入场\n" +
                    "19：00 晚宴开始\n" +
                    "22：00 晚宴结束\n" +
                    "~(≧▽≦)~自由活动~(≧▽≦)~\n" +
                    "★★★★27号安排★★★★\n" +
                    "9：00 之前自助早餐\n" +
                    "10：30 酒店广场集合返程\n" +
                    "★★★★各回各家★★★★", fromUser, toUser);
        }
        //关键字 年会
        if("B".equals(content) || "b".equals(content)) {
            return ResponseUtil.responseTextReturn("★★★★年会节目单★★★★\n" +
                    "1、《迷宫》\n" +
                    "2、《炒鸡访谈》\n" +
                    "3、《终于等到你》\n" +
                    "4、《甄嬛传》\n" +
                    "5、《光辉岁月》\n" +
                    "6、《胡撸胡撸瓢儿》\n" +
                    "7、《最美的时光》、《星空》\n" +
                    "8、《快递的故事》\n" +
                    "9、《老男孩》\n" +
                    "10、《咋了，爸爸》\n" +
                    "★★★★其他服务★★★★\n" +
                    "请回复“E”，教你使用“弹幕吐槽”/:8-)",fromUser,toUser);
        }
        //关键字 年会
        if("C".equals(content) || "c".equals(content)) {
            NewsMessageResponse response = new NewsMessageResponse();
            List<Article> article = new ArrayList<Article>();
            Article a = new Article();
            a.setTitle("热乎乎的地图来啰！");
            a.setPicUrl("http://i.niupic.com/images/2014/12/25/549c006a3da08.jpg");
            a.setUrl("http://i.niupic.com/images/2014/12/25/549c006a3da08.jpg");
            article.add(a);
            response.setArticleCount(1);
            response.setArticles(article);
            return ResponseUtil.responseNewsReturn(response, fromUser, toUser);
        }
        //关键字 年会
        if("D".equals(content) || "d".equals(content) || "奖品".equals(content) || "大奖".equals(content)) {
            return ResponseUtil.responseTextReturn("哈哈，这么心急啊~  \n" +
                    "想知道奖品有哪些？我就偏不告诉你~/:B-)\n" +
                    "想知道，晚上等着瞧~ /::,@\n" +
                    "\n" +
                    "不过，Plus神马的都不是事儿···",fromUser,toUser);
        }
        //关键字 年会
        if("e".equals(content) || "E".equals(content) || "弹幕".equals(content) || "吐糟".equals(content) || "弹幕吐槽".equals(content)) {
            return ResponseUtil.responseTextReturn("问我，弹幕是什么？/:?\n" +
                    "   自行学习脑补去····\n" +
                    "\n" +
                    "【怎么用弹幕吐槽？】\n" +
                    "   哈哈，让我告诉你\n" +
                    "   晚会开始后，直接在此微信中，输入任意文字内容，你给我的回复信息就将出现在大屏幕上哦。/::*/::*\n" +
                    "        最重要的是，这个是匿名的哦~/:B-)/:B-)\n" +
                    "\n" +
                    "      所以，想说什么，直接冲我来吧！/::$/::$",fromUser,toUser);
        }
        return ResponseUtil.responseTextReturn("http://dingding.tunnel.mobi",fromUser,toUser);
//        DanmuData.setMsg(content);
        //关键字回复
//        return null;
    }


    @Override
    public String settleVoice(Document doc,String toUser,String fromUser) {
        Element rootElt = doc.getRootElement();
        String content =  rootElt.elementTextTrim("Recognition");
        logger.info("语音识别结果：" + content);
        logger.info(fromUser + "：" + content);
        //关键字 年会
        if("年会".equals(content) || "\"年会\"".equals(content) || "O2O年会".equals(content) || "nianhui".equals(content)) {
            return ResponseUtil.responseTextReturn("HI，欢迎来到属于我们的一周年庆典。/:rose\n" +
                    "今天属于我们每一个人，想知道怎么玩？回复以下字母，获取更多：\n" +
                    "1.回复“A”，查看“当天行程和安排”；\n" +
                    "2.回复“B”，查看“晚会节目单”；\n" +
                    "3.回复“C”，查看“现场地图”；\n" +
                    "4.回复“D”，查看“晚会奖品有哪些”；\n" +
                    "5.回复“E”，了解“吐槽弹幕”；",fromUser,toUser);
        }
        //关键字 年会
        if("A".equals(content) || "a".equals(content)) {
            return ResponseUtil.responseTextReturn("★★★★26号安排★★★★\n" +
                    "12：00 北辰楼下西门集合\n" +
                    "注：上海童鞋车辆在南站等候\n" +
                    "       亦庄童鞋12点前到北辰\n" +
                    "14：00 到达阳光丽城温泉酒店\n" +
                    "14：10 前往3层302室开会\n" +
                    "15：30 发放房卡，入住酒店\n" +
                    "~(≧▽≦)~自由活动~(≧▽≦)~\n" +
                    "18：30 晚宴入场\n" +
                    "19：00 晚宴开始\n" +
                    "22：00 晚宴结束\n" +
                    "~(≧▽≦)~自由活动~(≧▽≦)~\n" +
                    "★★★★27号安排★★★★\n" +
                    "9：00 之前自助早餐\n" +
                    "10：30 酒店广场集合返程\n" +
                    "★★★★各回各家★★★★",fromUser,toUser);
        }
        //关键字 年会
        if("B".equals(content) || "b".equals(content)) {
            return ResponseUtil.responseTextReturn("★★★★年会节目单★★★★\n" +
                    "1、《迷宫》\n" +
                    "2、《炒鸡访谈》\n" +
                    "3、《终于等到你》\n" +
                    "4、《甄嬛传》\n" +
                    "5、《光辉岁月》\n" +
                    "6、《胡撸胡撸瓢儿》\n" +
                    "7、《最美的时光》、《星空》\n" +
                    "8、《快递的故事》\n" +
                    "9、《老男孩》\n" +
                    "10、《咋了，爸爸》\n" +
                    "★★★★其他服务★★★★\n" +
                    "请回复“E”，教你使用“弹幕吐槽”/:8-)",fromUser,toUser);
        }
        //关键字 年会
        if("C".equals(content) || "c".equals(content)) {
            NewsMessageResponse response = new NewsMessageResponse();
            List<Article> article = new ArrayList<Article>();
            Article a = new Article();
            a.setTitle("热乎乎的地图来啰！");
            a.setPicUrl("http://i.niupic.com/images/2014/12/25/549c006a3da08.jpg");
            a.setUrl("http://i.niupic.com/images/2014/12/25/549c006a3da08.jpg");
            article.add(a);
            response.setArticleCount(1);
            response.setArticles(article);
            return ResponseUtil.responseNewsReturn(response, fromUser, toUser);
        }
        //关键字 年会
        if("D".equals(content) || "d".equals(content) || "奖品".equals(content) || "大奖".equals(content)) {
            return ResponseUtil.responseTextReturn("哈哈，这么心急啊~  \n" +
                    "想知道奖品有哪些？我就偏不告诉你~/:B-)\n" +
                    "想知道，晚上等着瞧~ /::,@\n" +
                    "\n" +
                    "不过，Plus神马的都不是事儿···",fromUser,toUser);
        }
        //关键字 年会
        if("e".equals(content) || "E".equals(content) || "弹幕".equals(content) || "吐糟".equals(content) || "弹幕吐槽".equals(content)) {
            return ResponseUtil.responseTextReturn("问我，弹幕是什么？/:?\n" +
                    "   自行学习脑补去····\n" +
                    "\n" +
                    "【怎么用弹幕吐槽？】\n" +
                    "   哈哈，让我告诉你\n" +
                    "   晚会开始后，直接在此微信中，输入任意文字内容，你给我的回复信息就将出现在大屏幕上哦。/::*/::*\n" +
                    "        最重要的是，这个是匿名的哦~/:B-)/:B-)\n" +
                    "\n" +
                    "      所以，想说什么，直接冲我来吧！/::$/::$",fromUser,toUser);
        }

//        DanmuData.setMsg(content);
        return null;
    }

    @Override
    public String settleEvent(Document doc,String toUser,String fromUser) {
        String reply = "";
        NewsMessageResponse resp = null;
        Element rootElt = doc.getRootElement();
        String eventType =  rootElt.elementTextTrim("Event");
        if("subscribe".equals(eventType)) {
            reply = ResponseUtil.responseTextReturn("HI，欢迎来到O2O业务部年会。 /::,@\n" +
                    "精彩大奖、弹幕吐槽··· 都在这里。想知道更多？回复“年会”二字，获得更多信息。/:v\n" +
                    "另外，此公众号还会每天推送行业相关干货，值得你长期拥有。/:B-)",fromUser,toUser);
        }
        return reply;
    }

}