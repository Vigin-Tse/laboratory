package com.vg.basic.websocket;

/**
 * @Description websocket 服务器
 * @Author xieweij
 * @create 2020/7/24 9:17
 */

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * @ServerEndpoint 可以把当前类变成websocket服务类（与spring 默认的单例模式冲突，每当一个新连接进来都会创建一个WebSocketServer对象，所以无法使用spring的注入）
 */
@ServerEndpoint("/websocket/{userno}")
@Component
public class WebSocketServer {

    /**
     * 当前发消息的人员编号
     */
    private String userno;

    private Session session;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<String, WebSocketServer>();



    public WebSocketServer(){
        System.out.println("WebSocketServer对象创建");
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userno") String userno, Session session) throws IOException{
        this.userno = userno;
        this.session = session;
        webSocketSet.put(userno, this);
        log("建立连接成功！");
    }

    /**
     * 连接关闭调用的方法
     * 不要使用session发送消息给用户
     * 不要手动调用close方法
     * 不能有任何异常抛出
     */
    @OnClose
    public void onClose() {
        System.out.println("调用onClose");
        if (!StringUtils.isEmpty(this.userno)){
            if (webSocketSet.get(userno) != null){
                webSocketSet.remove(userno);
                System.out.println("连接关闭：" + userno);
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message){
//        if (!StringUtils.isEmpty(userno) && webSocketSet.get(userno) != null && webSocketSet.get(userno).session != null){
//            webSocketSet.get(userno).session.getAsyncRemote().sendText("服务器响应：" + userno + "，你好！");
//        }
        System.out.println("进入OnMessage");

            new Thread(() ->{
                System.out.println("进入线程");
                while (!StringUtils.isEmpty(userno) && webSocketSet.get(userno) != null){
//                webSocketSet.get(userno).session.getAsyncRemote().sendText("服务器响应：" + userno + "，你好！");
                    try {
                        webSocketSet.get(userno).session.getBasicRemote().sendText("服务器响应：" + userno + "，你好！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("退出线程");
            }).run();

        System.out.println("退出OnMessage");
    }

    private void log(String logMessage){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = sf.format(new Date());

        String mess = time + "|" + Thread.currentThread().getName() + "|" + userno + "：" + logMessage;
        System.out.println(mess);
        if (!StringUtils.isEmpty(userno) && webSocketSet.get(userno) != null && webSocketSet.get(userno).session != null){
            webSocketSet.get(userno).session.getAsyncRemote().sendText(mess);
        }
    }
}
