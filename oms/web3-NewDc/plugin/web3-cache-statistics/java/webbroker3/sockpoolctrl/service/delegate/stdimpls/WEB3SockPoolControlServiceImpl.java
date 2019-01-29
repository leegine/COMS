head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3SockPoolControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@(WEB3SockPoolControlServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/06 劉(FLJ) 新規作成
 */

package webbroker3.sockpoolctrl.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.comm.sockpool.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.sockpoolctrl.message.*;
import webbroker3.sockpoolctrl.service.delegate.*;
import webbroker3.util.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.Field;

public class WEB3SockPoolControlServiceImpl
    implements WEB3SockPoolControlService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SockPoolControlServiceImpl.class);

    /**
     * ワーカースレッド終了待機@時間
     */
    private final static long DEFAULT_SLEEP_MILLISECS= 10000;

    /**
     * ワーカースレッド終了待機@時間プリファ@レンスキー
     */
    private final static String  SOCKPOOL_SAFE_SHUTDOWN_SLEEP_KEY= "sockpool.safe.shutdown.sleep.millisecs";

    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String mem = Runtime.getRuntime().freeMemory() + " free, " +
                Runtime.getRuntime().totalMemory() + " total";
            log.error("Out Of Memory was catched!!! " + mem);
            log.error(
                "Out Of Memory was catched!!! try to shut down xtrade socket server...");


            Class l_class = SocketPoolPlugin.class;
            Field l_field;
            Set l_workerThreads;
            ServerSocket l_serverSocket;

            l_field = l_class.getDeclaredField("server");
            l_field.setAccessible(true);
            SocketPoolServer l_socketPoolServer = (SocketPoolServer)l_field.get(null);
            
            Class l_socketPoolServerClass = l_socketPoolServer.getClass();
            Field l_workerThreadsField =l_socketPoolServerClass.getDeclaredField("workerThreads");
            l_workerThreadsField.setAccessible(true);
            l_workerThreads =(Set)l_workerThreadsField.get(l_socketPoolServer);
            
            Field l_serverSocketField =l_socketPoolServerClass.getDeclaredField("serverSocket");
            l_serverSocketField.setAccessible(true);
            l_serverSocket =(ServerSocket)l_serverSocketField.get(l_socketPoolServer);

            Field l_noOfMessagesBeingProcessedField =l_socketPoolServerClass.getDeclaredField("noOfMessagesBeingProcessed");
            l_noOfMessagesBeingProcessedField.setAccessible(true);
            Object l_objNoOfMessagesBeingProcessedField =l_noOfMessagesBeingProcessedField.get(l_socketPoolServer);

            
            log.info("will stop");

            Field l_runningField =l_socketPoolServerClass.getDeclaredField("running");
            l_runningField.setAccessible(true);
            l_runningField.setBoolean(l_socketPoolServer,false);

            if(l_workerThreads!=null){
               synchronized(l_workerThreads){
                    
                    try
                    {
                        l_serverSocket.close();
                    }
                    catch(IOException ioe)
                    {
                        log.error("server socket closure threw " + ioe);
                    }
                    
                    Object t;
                    for(Iterator iter = l_workerThreads.iterator(); iter.hasNext();){
                        t = iter.next();
                        Class l_workerThreadClass = t.getClass();
                        Field l_workerThreadField =l_workerThreadClass.getDeclaredField("running");
                        l_workerThreadField.setAccessible(true);
                        l_workerThreadField.setBoolean(t,false);
                    }

                    long l_lngSleepTime = DEFAULT_SLEEP_MILLISECS;
                    String l_strSleepTime = GtlUtils.getTradingSystem().getPreference(SOCKPOOL_SAFE_SHUTDOWN_SLEEP_KEY);
                    if(l_strSleepTime != null)
                    {
                        try
                        {
                            l_lngSleepTime = Long.parseLong(l_strSleepTime);
                        }
                        catch (NumberFormatException e)
                        {
                            log.error(e.getMessage(), e);
                        }
                    }
                    
                    log.info("sleep millisecs=" + l_lngSleepTime);
                    try
                    {
                        Thread.sleep(l_lngSleepTime);
                    }
                    catch (InterruptedException e)
                    {
                        log.error(e.getMessage(), e);
                    }

                    int l_intCloseSocket = 0;
                    Object l_workerThread;
                    for(Iterator iter = l_workerThreads.iterator(); iter.hasNext();)
                    {
                        l_workerThread = iter.next();
                        Class l_workerThreadClass = l_workerThread.getClass();
                        Field l_workerThreadField =l_workerThreadClass.getDeclaredField("socket");
                        l_workerThreadField.setAccessible(true);
                        Socket l_socket = (Socket)l_workerThreadField.get(l_workerThread);

                        if(l_socket != null)
                        {
                            if(! l_socket.isClosed())
                            {
                                try
                                {
                                    l_socket.close();
                                    l_intCloseSocket++;
                                }
                                catch (IOException l_e)
                                {
                                    log.error("closing socket : " + l_e.getMessage(), l_e);
                                }
                            }
                        }
                    }
                    log.info("closed socket count=" + l_intCloseSocket);

                    synchronized(l_objNoOfMessagesBeingProcessedField)
                    {
                        l_objNoOfMessagesBeingProcessedField.notifyAll();
                        log.info("stopping");                   
                    } 
                }
            }
            else {
                 log.error("the l_workerThreads is NULL!");
            }
            log.error("Out Of Memory was catched!!!  xtrade socket server shutdown.");
        }
        catch (Throwable sex)
        {
            sex.printStackTrace();
            log.error(sex.getMessage(), sex);
            log.error("Out Of Memory was catched!!! xtrade socket server shut down!");
        }
        log.entering(STR_METHOD_NAME);
        return new WEB3SockPoolCtrlResponse();
    }

}
@
