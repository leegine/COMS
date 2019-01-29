head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.45.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMessageHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価サービスの管理用メッセージを処理するメッセージハンドラー(WEB3QuoteMessageHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
                 : 2005/04/28 山田　@卓司(FLJ) 時価監視の開始・終了処理を追加
*/
package webbroker3.quoteadaptor.stdimpls.handler;

import java.util.*;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.message.Response;

import webbroker3.quoteadaptor.stdimpls.*;
import webbroker3.quoteadaptor.stdimpls.message.*;
import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * 時価サービスの管理用メッセージを処理するメッセージハンドラー。
 * 通常、ジョブ･スケジューラから時価サービスを起動・停止するときに使用される。
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class WEB3QuoteMessageHandler implements MessageHandler
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuoteMessageHandler.class);

    private static final boolean DBG = log.ison();

    // OK_RESPONSE
    private static final Response OK_RESPONSE = new Response();

    /**
     * エラー情報
     */
    private static ErrorInfo ERROR_INFO =
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
            "QUOTE_REQUEST_PROCESS_ERROR",
            "Failed to process the request.");

    /**
     * エラー情報
     */
    private static ErrorInfo BROADCAST_ERROR =
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
            "QUOTE_BROADCAST_ERROR",
            "Failed to process the request.");

    /**
     * エラー情報
     */
    private static ErrorInfo DUMP_ERROR =
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
            "QUOTE_DUMP_ERROR",
            "Failed to process the request.");
    
    /**
     * エラー情報
     */
    private static final ErrorInfo START_MONITORING_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "START_QUOTE_MONITORING_ERROR",
                "Failed to process the request.");

    /**
     * エラー情報
     */
    private static final ErrorInfo STOP_MONITORING_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "STOP_QUOTE_MONITORING_ERROR",
                "Failed to process the request.");

    /**
     * エラー情報
     */
    private static final ErrorInfo SWITCH_PROTOCOL_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "SWITCH_QUOTE_PROTOCOL_ERROR",
                "Failed to process the request.");
    /**
     * エラー情報
     */
    private static final ErrorInfo CHANGE_RMM_DATASOURCE_ERROR = 
        ErrorManager.getInstance(
            WEB3QuoteMessageHandler.class).defineErrorInfo(
                "CHANGE_RMM_DATASOURCE_ERROR",
                "Failed to process the request.");

    /**
     * デフォルトコンストラクタ
     */
    public WEB3QuoteMessageHandler()
    {
    }

    /**
     * <p>
     * WEB3StartQuoteServiceRequestメッセージを処理する。
     * </p>
     * <p>
     * WEB3StartQuoteServiceRequestで指定された時価サービスを起動する。
     * 起動時にエラーが発生した場合は、エラー情報が追加された応答メッセージを返す。
     * </p>
     * 
     * @@param req WEB3StartQuoteServiceRequestメッセージ
     * @@return 応答メッセージ
     */
    public Response handle(final WEB3StartQuoteServiceRequest req)
    {
        if (DBG)
        {
            log.debug("processing StartQuoteServiceRequest message");
        }

        try
        {

            WEB3QuoteDataSupplierServiceManager manager =
                getWEB3QuoteDataSupplierServiceManager();

            if (WEB3QuoteConstants.ALL_SERVICES.equals(req.service_id))
            {
                manager.startAllServices();
            } else
            {
                manager.startService(req.service_id);
            }
            return OK_RESPONSE;
        } catch (RuntimeException re)
        {
            log.warn(re.getMessage(), re);
            Response resp = new Response();
            resp.server_exception = ERROR_INFO.addText(re.getMessage());
            return resp;
        }
    }

    /**
     * <p>
     * WEB3StopQuoteServiceRequestメッセージを処理する。
     * </p>
     * <p>
     * WEB3StopQuoteServiceRequestで指定された時価サービスを停止する。
     * 停止時にエラーが発生した場合は、エラー情報が追加された応答メッセージを返す。
     * </p>
     * 
     * @@param req WEB3StartQuoteServiceRequestメッセージ
     * @@return 応答メッセージ
     */
    public Response handle(final WEB3StopQuoteServiceRequest req)
    {
        if (DBG)
        {
            log.debug("processing StopQuoteServiceRequest message");
        }

        try
        {

            WEB3QuoteDataSupplierServiceManager manager =
                getWEB3QuoteDataSupplierServiceManager();

            if (WEB3QuoteConstants.ALL_SERVICES.equals(req.service_id))
            {
                manager.stopAllServices();
            } else
            {
                manager.stopService(req.service_id);
            }
            return OK_RESPONSE;
        } catch (RuntimeException re)
        {
            log.warn(re.getMessage(), re);
            Response resp = new Response();
            resp.server_exception = ERROR_INFO.addText(re.getMessage());
            return resp;
        }
    }

    /**
     * <p>
     * WEB3StartQuoteServiceBroadcastRequestメッセージを処理する。
     * </p>
     * <p>
     * クラスター内の全てのサーバ上で、
     * WEB3StartQuoteServiceBroadcastRequestで指定された時価サービスを起動する。
     * </p>
     * 
     * @@param req WEB3StartQuoteServiceBroadcastRequestメッセージ
     * @@return 応答メッセージ
     */
    public Response handle(final WEB3StartQuoteServiceBroadcastRequest req)
    {
        if (DBG)
        {
            log.debug("processing StartQuoteServiceBroadcastRequest message");
        }

        if (isHoliday())
        {
            Response resp = new Response();
            return resp;
        }

        try
        {
            String[] urls = getClusterUrls();
            if (urls == null)
            {
                Response resp = new Response();
                resp.server_exception =
                    BROADCAST_ERROR.addText("No URL is registered.");
                return resp;
            }

            Response[] responses = new Response[urls.length];
            WEB3StartQuoteServiceRequest forwardReq =
                new WEB3StartQuoteServiceRequest();
            StringBuffer errorMsgBuf = new StringBuffer(100);

            for (int i = 0; i < urls.length; i++)
            {
                // get Accessor
                ServerAccessor accessor = null;
                if (urls[i].startsWith("sockpool:"))
                {
                    accessor = new SocketPoolAccessor(urls[i]);
                } else
                {
                    accessor = new HttpServerAccessor(urls[i]);
                }

                // make a request
                forwardReq.service_id = req.service_id;

                try
                {
                    // send it
                    responses[i] = accessor.doRequest(forwardReq);
                } catch (Exception ex)
                {
                    log.warn("Failed in forwarding a quote request.", ex);
                }

                // check response		
                if (responses[i] == null
                    || responses[i].server_exception != null)
                {
                    errorMsgBuf.append(urls[i] + ", ");
                }
            }

            if (errorMsgBuf.length() == 0)
            {
                return OK_RESPONSE;
            } else
            {
                errorMsgBuf.insert(0, " Failed: ");
                String errorMsg = errorMsgBuf.toString();
                if (errorMsg.endsWith(", "))
                {
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 2);
                }

                Response resp = new Response();
                resp.server_exception = BROADCAST_ERROR.addText(errorMsg);
                return resp;
            }
        } catch (Exception e)
        {
            log.error("Unexpected error", e);
            Response resp = new Response();
            resp.server_exception = BROADCAST_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    /**
     * <p>
     * WEB3StopQuoteServiceBroadcastRequestメッセージを処理する。
     * </p>
     * <p>
     * クラスター内の全てのサーバ上で、
     * WEB3StopQuoteServiceBroadcastRequestで指定された時価サービスを停止する。
     * </p>
     * 
     * @@param req WEB3StopQuoteServiceBroadcastRequestメッセージ
     * @@return 応答メッセージ
     */
    public Response handle(final WEB3StopQuoteServiceBroadcastRequest req)
    {
        if (DBG)
        {
            log.debug("processing StopQuoteServiceBroadcastRequest message");
        }

        if (isHoliday())
        {
            log.info("Today is holiday. Stop operation.");
            Response resp = new Response();
            return resp;
        }

        try
        {
            String[] urls = getClusterUrls();
            if (urls == null)
            {
                Response resp = new Response();
                resp.server_exception =
                    BROADCAST_ERROR.addText("No URL is registered.");
                return resp;
            }

            Response[] responses = new Response[urls.length];
            WEB3StopQuoteServiceRequest forwardReq =
                new WEB3StopQuoteServiceRequest();
            StringBuffer errorMsgBuf = new StringBuffer(100);

            for (int i = 0; i < urls.length; i++)
            {
                // get Accessor
                ServerAccessor accessor = null;
                if (urls[i].startsWith("sockpool:"))
                {
                    accessor = new SocketPoolAccessor(urls[i]);
                } else
                {
                    accessor = new HttpServerAccessor(urls[i]);
                }

                // make a request
                forwardReq.service_id = req.service_id;

                try
                {
                    // send it
                    responses[i] = accessor.doRequest(forwardReq);
                } catch (Exception ex)
                {
                    log.warn("Failed in forwarding a quote request.", ex);
                }

                // check response		
                if (responses[i] == null
                    || responses[i].server_exception != null)
                {
                    errorMsgBuf.append(urls[i] + ", ");
                }
            }

            if (errorMsgBuf.length() == 0)
            {
                return OK_RESPONSE;
            } else
            {
                errorMsgBuf.insert(0, " Failed: ");
                String errorMsg = errorMsgBuf.toString();
                if (errorMsg.endsWith(", "))
                {
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 2);
                }

                Response resp = new Response();
                resp.server_exception = BROADCAST_ERROR.addText(errorMsg);
                return resp;
            }
        } catch (Exception e)
        {
            log.error("Unexpected error", e);
            Response resp = new Response();
            resp.server_exception = BROADCAST_ERROR.addText(e.getMessage());
            return resp;
        }
    }
    
    public Response handle(final WEB3QuoteDumpRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            manager.dump();
            return OK_RESPONSE;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            Response resp = new Response();
            resp.server_exception = DUMP_ERROR.addText(e.getMessage());
            return resp;
        }
    }
    
    public Response handle(final WEB3StartQuoteMonitoringRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            manager.startMonitoring();
            return OK_RESPONSE;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            Response resp = new Response();
            resp.server_exception = START_MONITORING_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    public Response handle(final WEB3StopQuoteMonitoringRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            manager.stopMonitoring();
            return OK_RESPONSE;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            Response resp = new Response();
            resp.server_exception = STOP_MONITORING_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    public Response handle(final WEB3SwitchQuoteProtocolRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            String l_strMessage = manager.switchDataSourceProtocol();
            
            WEB3SwitchQuoteProtocolResponse resp = new WEB3SwitchQuoteProtocolResponse();
            resp.info = l_strMessage;
            return resp;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            WEB3SwitchQuoteProtocolResponse resp = new WEB3SwitchQuoteProtocolResponse();
            resp.server_exception = SWITCH_PROTOCOL_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    public Response handle(final WEB3ChangeRMMQuoteDataSourceRequest request)
    {
        try
        {
            WEB3QuoteDataSupplierServiceManager manager = getWEB3QuoteDataSupplierServiceManager();
            WEB3QuoteFutureData l_data = manager.changeRMMDataSource();
            String l_strMessage = l_data.getData();
            
            WEB3ChangeRMMQuoteDataSourceResponse resp = new WEB3ChangeRMMQuoteDataSourceResponse();
            resp.info = l_strMessage;
            return resp;
        } catch (Exception e)
        {
            log.error("Unexpected error.", e);
            WEB3ChangeRMMQuoteDataSourceResponse resp = new WEB3ChangeRMMQuoteDataSourceResponse();
            resp.server_exception = CHANGE_RMM_DATASOURCE_ERROR.addText(e.getMessage());
            return resp;
        }
    }

    /**
     * 管理用サービスを取得する。
     * 
     * @@return 管理用サービス
     */
    private WEB3QuoteDataSupplierServiceManager getWEB3QuoteDataSupplierServiceManager()
    {
        return (WEB3QuoteDataSupplierServiceManager) Services.getService(
            WEB3QuoteDataSupplierServiceManager.class);
    }

    private static String[] getClusterUrls()
    {
        try
        {
            Properties entries = ServerConfig.getConfigCategory("cluster.urls");
            if (entries == null || entries.isEmpty())
            {
                return null;
            }

            List list = new ArrayList();
            for (Enumeration e = entries.propertyNames(); e.hasMoreElements();)
            {
                String url = entries.getProperty((String) e.nextElement());
                
                if (url != null)
                {
                    list.add(url);
                }
            }
            String[] urls = (String[]) list.toArray(new String[list.size()]);
            return urls;
        } catch (DataNetworkException dne)
        {
            return null;
        }
    }

    /**
     * 休日チェックメソッド
     * 
     * <p>
     * JobUtilで時価サービスを起動・停止するとき、
     * 平日のみの設定を行っても、
     * カレンダーに登録された休日（祭日）は判定できない。
     * そのため、いったん起動・停止メッセージを受信した後で、
     * カレンダーテーブルから休日情報を取得し、
     * 休日の場合はサービスの起動・停止処理を実行しないようにする。
     * </p>
     * 
     * @@return 休日判定結果　@true　@休日／　@false 非休日
     * 
     * TODO 要実装（現在は常にfalse（非休日）を返す）
     */
    protected boolean isHoliday()
    {
        return false;
    }

}
@
