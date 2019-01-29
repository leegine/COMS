head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知サービスImpl(WEB3AioOutputNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import webbroker3.aio.data.HostSecDeliveryTransferParams;
import webbroker3.aio.data.HostSecDeliveryTransferRow;
import webbroker3.aio.data.HostTransferPaymentParams;
import webbroker3.aio.data.HostTransferPaymentRow;
import webbroker3.aio.define.WEB3HostTransferPaymentStatusDef;
import webbroker3.aio.define.WEB3AioHostTransferPaymentTransferFlagDef;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

/**
 * (出庫通知サービスImpl)<BR>
 * 出庫通知サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyServiceImpl implements WEB3AioOutputNotifyService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyServiceImpl.class); 
    
    /**
     * 出庫通知処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出庫通知）execute」 参照 <BR>
     * <BR> 
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41579661009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //If (l_request == null)
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //１.1）出庫通知TransactionCallback( )
            WEB3AioOutputNotifyTransactionCallBack
                l_aioOutputNotifyTransactionCallBack =
                    new WEB3AioOutputNotifyTransactionCallBack();
            
            //１.2）クエリプロセッサのインスタンスを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // １.3）DBトランザクション処理を実施する。
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_aioOutputNotifyTransactionCallBack);
            
            //1.4）出庫通知リクエスト.createレスポンス()をコールし、
             // 出庫通知レスポンスオブジェクトを生成する。
            WEB3BackResponse l_backResponse = l_request.createResponse();
            //－生成した出庫通知レスポンスを返す。
            log.exiting(STR_METHOD_NAME);
            return l_backResponse;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("クエリプロセッサのインスタンスを取得失敗");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("クエリプロセッサのインスタンスを取得失敗");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (出庫通知TransactionCallback)<BR>
     *  出庫通知TransactionCallbackクラス
     */
    public class WEB3AioOutputNotifyTransactionCallBack implements TransactionCallback 
    {
        /**
         *  ログユーティリティ<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(WEB3AioOutputNotifyTransactionCallBack.class);
        
        /**
         * (出庫通知TransactionCallback)<BR>
         * デフォルトコンストラクタ
         * @@return WEB3AioOutputNotifyTransactionCallBack
         * @@roseuid 415796810178
         */
        public WEB3AioOutputNotifyTransactionCallBack() 
        {
         
        }
        
        /**
         * 出庫通知処理を実施する。  <BR>
         * <BR>
         * シーケンス図  <BR>
         * 「（出庫通知）process」 参照<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415796B5005F
         */
        public Object process() 
        	throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            //1.1）（※１）振替請求伝票キューテーブル読込み
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_strWhereA = new StringBuffer();
            l_strWhereA.append(" request_code = ? ");
            l_strWhereA.append(" and transfer_flag = ? ");
            l_strWhereA.append(" and status = ? ");   
            String l_strCondition = null;
            
            Object[] l_objWhereValuesA = { 
             	WEB3HostRequestCodeDef.AIO_REQUEST_SLIP, 
             	WEB3AioHostTransferPaymentTransferFlagDef.TRANSFER_STORAGE_OUT,
             	WEB3HostTransferPaymentStatusDef.NOT_DEAL};     
            /*検索*/
            List l_lisOrderNotifyresultsA =
                l_queryProcessor.doFindAllQuery(
                    HostTransferPaymentRow.TYPE,
                    l_strWhereA.toString(),
                    l_strCondition,
                    l_objWhereValuesA);
             
            //1.2）取得したキューテーブルのレコード毎のLOOP処理            
            int l_intSizeA = 0;
            if(l_lisOrderNotifyresultsA != null && !l_lisOrderNotifyresultsA.isEmpty())
            {
                l_intSizeA = l_lisOrderNotifyresultsA.size();
            }                      
            //処理区分を設定
            HashMap l_map = new HashMap();
            //振替請求伝票キューParams
            HostTransferPaymentRow l_transferPaymentParams = null;
            for (int i = 0; i < l_intSizeA; i++)
            {
                l_transferPaymentParams =
                    (HostTransferPaymentParams) l_lisOrderNotifyresultsA.get(i);
                                
                try
                {
                    // TransactionCallback生成
                    WEB3AioOutputNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AioOutputNotifyNormalTransactionCallback(
                            l_transferPaymentParams, true);
    
                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }

                catch (Exception l_ex)
                {
                    if(l_ex instanceof WEB3BaseRuntimeException)
                    {
                        //口座ロックに失敗してエラーが発生した場合、
                        //処理対象キューを更新しない。
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_wre.getErrorInfo()))
                        {
                            continue;
                        } 
                    }
                    log.error("__an Exception__ ", l_ex);
                    l_map.put("status", WEB3HostTransferPaymentStatusDef.DATA_ERROR);
                                    
					// 処理対象の振替請求伝票キューテーブル.処理区分を設定
					String l_strUpdateWhereA = "rowid = ? ";

					String[] l_objUpdateWhereValuesA = {l_transferPaymentParams.getRowid()};
             
                    //  1.2.2 キューテーブルのレコードの処理区分の更新
                    l_queryProcessor.doUpdateAllQuery(
                        HostTransferPaymentRow.TYPE,
                        l_strUpdateWhereA.toString(),
                        l_objUpdateWhereValuesA,
                        l_map);
                }
            }
            
            //1.3 （※３）証券出庫請求伝票キューテーブル読込み
            StringBuffer l_strWhereB = new StringBuffer();
            l_strWhereB.append(" request_code = ? ");
            l_strWhereB.append(" and status = ? ");   

            Object[] l_objWhereValuesB = { 
	            WEB3HostRequestCodeDef.SECURITIES_OUT_REQUEST_SLIP, 
               	WEB3HostTransferPaymentStatusDef.NOT_DEAL}; 
                
            /*検索*/
            List l_lisOrderNotifyresultsB =
                l_queryProcessor.doFindAllQuery(
                    HostSecDeliveryTransferRow.TYPE,
                    l_strWhereB.toString(),
                    l_strCondition,
                    l_objWhereValuesB);
            
            //1.4）取得したキューテーブルのレコード毎のLOOP処理      
            int l_intSizeB = 0;
            if(l_lisOrderNotifyresultsB != null && !l_lisOrderNotifyresultsB.isEmpty())
            {
                l_intSizeB = l_lisOrderNotifyresultsB.size();
            }
            //証券出庫請求伝票キューParams
            HostSecDeliveryTransferParams l_secDeliveryTransferParams = null;
            for (int i = 0; i < l_intSizeB; i++)
            {
                l_secDeliveryTransferParams =
                    (HostSecDeliveryTransferParams) l_lisOrderNotifyresultsB.get(i);
                
                try
                {
                    // TransactionCallback生成
                    WEB3AioOutputNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AioOutputNotifyNormalTransactionCallback(
                            l_secDeliveryTransferParams, false);
    
                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_ex)
                {
                    if(l_ex instanceof WEB3BaseRuntimeException)
                    {
                        //口座ロックに失敗してエラーが発生した場合、
                        //処理対象キューを更新しない。
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_wre.getErrorInfo()))
                        {
                            continue;
                        } 
                    }
                    log.error("__an Exception__ ", l_ex);
                    l_map.put("status", WEB3HostTransferPaymentStatusDef.DATA_ERROR);
                     
					// 処理対象の証券出庫請求伝票キューテーブル.処理区分を設定用
					String l_strUpdateWhereB = "rowid = ? ";

					String[] l_objUpdateWhereValuesB = {l_secDeliveryTransferParams.getRowid()};
             
                    //  1.4.2 キューテーブルのレコードの処理区分の更新
                    l_queryProcessor.doUpdateAllQuery(
                        HostSecDeliveryTransferRow.TYPE,
                        l_strUpdateWhereB.toString(),
                        l_objUpdateWhereValuesB,
                        l_map);
                } 

            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
