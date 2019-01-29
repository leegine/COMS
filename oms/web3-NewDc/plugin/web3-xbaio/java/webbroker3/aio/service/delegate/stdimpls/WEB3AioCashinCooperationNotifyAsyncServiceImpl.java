head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyAsyncServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携通知非同期サービスImplクラス(WEB3AioCashinCooperationNotifyAsyncServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/11 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.aio.message.WEB3AioCashinCooperationNotifyRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連携通知非同期サービスImpl)<BR>
 * 入金連携通知非同期サービスImplクラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationNotifyAsyncServiceImpl implements Runnable 
{
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationNotifyAsyncServiceImpl.class);     
    
    /**
     * 入金連携通知リクエスト。<BR>
     */
    private WEB3AioCashinCooperationNotifyRequest aioCashinCooperationNotifyRequest;
    
    /**
     * (コンストラクタ) <BR>
     * <BR>
     * 引数.入金連携通知リクエストを、this.入金連携通知リクエストにセットする。 <BR>
     * <BR>
     * @@param l_request - (入金連携通知リクエスト)<BR>
     * @@roseuid 40F240CB0198
     */
    public WEB3AioCashinCooperationNotifyAsyncServiceImpl(
        WEB3AioCashinCooperationNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "WEB3AioCashinCooperationNotifyAsyncServiceImpl(" +
            "WEB3AioCashinCooperationNotifyRequest l_request)";
        
        log.entering(STR_METHOD_NAME);
        
        this.aioCashinCooperationNotifyRequest = l_request;
       
        log.exiting(STR_METHOD_NAME);
    }    
   
    /**
     * ()
     * シーケンス図 <BR>
     * 「(入金連携通知非同期サービスImpl)run()」 参照 <BR>
     * <BR>
     * @@roseuid 40F240CB0198
     */
    public void run()
    {
        final String STR_METHOD_NAME = "run()";
        log.entering(STR_METHOD_NAME); 
        
        try
        {
            //1.1 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        
            WEB3AioCashinCooperationUnitTransactionCallback l_transactionCallback =
                new WEB3AioCashinCooperationUnitTransactionCallback();
            
            //1.1.1 doTransaction(arg0 : int, arg1 : TransactionCallback)
            //引数：
            //QueryProcessor.TX_CREATE_NEW
            //入金連携トランザクションコールバック
            
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_transactionCallback);            
        }        
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2  (*)スレッドを開放する    
        //スレッド開放
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(
                this.aioCashinCooperationNotifyRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug("Error In スレッドを開放する", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);   
    }
}
@
