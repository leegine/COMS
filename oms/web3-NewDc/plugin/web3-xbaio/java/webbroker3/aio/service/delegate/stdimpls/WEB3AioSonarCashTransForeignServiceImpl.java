head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）サービスImpl(WEB3AioSonarCashTransForeignServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.aio.data.HostForeignCashTransferRow;
import webbroker3.aio.message.WEB3AioSonarCashTransForeignResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR入出金（外貨）サービスImpl)<BR>
 * SONAR入出金（外貨）サービス実装クラス<BR> 
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignServiceImpl 
    implements WEB3AioSonarCashTransForeignService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignServiceImpl.class);
    
    /**
     * SONAR入出金（外貨）サービス処理を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「（SONAR入出金（外貨））execute」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //1.1 SONAR入出金（外貨）TransactionCallback( )
            //SONAR入出金（外貨）トランザクションコールバックインスタンスを生成する。
            WEB3AioSonarCashTransForeignTransactionCallback 
                l_aioSonarCashTransForeignTransactionCallback = 
                    new WEB3AioSonarCashTransForeignTransactionCallback();
            
            //1.2 getDefaultProcessor( )
            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.3 doTransaction(トランザクション属性 : int, 
            //トランザクションコールバック : TransactionCallback)
            //DBトランザクション処理を実施する。 
            //[doTransaction()に指定する引数] 
            //トランザクション属性：　@TX_CREATE_NEW 
            //トランザクションコールバック：　@SONAR入出金（外貨）TransactionCallbackインスタンス
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, 
                l_aioSonarCashTransForeignTransactionCallback);
            
            //1.4 createResponse( )
            WEB3AioSonarCashTransForeignResponse
                l_aioSonarCashTransForeignResponse =
                    (WEB3AioSonarCashTransForeignResponse)l_request.createResponse();
            
            //－生成したSONAR入出金（外貨）レスポンスを返す。
            log.exiting(STR_METHOD_NAME);
            return l_aioSonarCashTransForeignResponse;
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (SONAR入出金（外貨）TransactionCallback )<BR>
     * SONAR入出金（外貨）トランザクションコールバッククラス<BR>
     */
    public class WEB3AioSonarCashTransForeignTransactionCallback
        implements TransactionCallback
    {
        /**
         * ログユーティリティ<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioSonarCashTransForeignTransactionCallback.class);

        /**
         * (SONAR入出金（外貨）TransactionCallback)<BR>
         * デフォルトコンストラクタ<BR>
         */
        public WEB3AioSonarCashTransForeignTransactionCallback()
        {

        }

        /**
         * SONAR入出金（外貨）処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（SONAR入出金（外貨））process」 参照<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process() ";
            m_log.entering(STR_METHOD_NAME);
            
            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.1 (*1) 外貨入出金テーブル読込
            //    (*1) 以下の条件で、外貨入出金テーブルから
            // 　@　@　@行ロックオプションにてレコードを取得する。
            // [検索条件]
            // データコード = "GI853"
            // 処理区分 = "0"（未処理）
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            l_sbWhere.append(" and status = ? ");

            Object[] l_objValues = {
                WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER,
                WEB3StatusDef.NOT_DEAL};

            /*検索*/
            List l_lisForeignHostCashTransferRows =
                l_queryProcessor.doFindAllQuery(
                    HostForeignCashTransferRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objValues);
            
            int l_intSize = 0;
            if (l_lisForeignHostCashTransferRows != null && 
                !l_lisForeignHostCashTransferRows.isEmpty())
            {
                l_intSize = l_lisForeignHostCashTransferRows.size();
            }

            //1.2 取得したレコード毎のループ処理
            for (int i = 0; i < l_intSize; i ++)
            {
                //外貨入出金Paramsを取得する
                HostForeignCashTransferRow l_hostForeignCashTransferRow =
                   (HostForeignCashTransferRow)l_lisForeignHostCashTransferRows.get(i);
                HostForeignCashTransferParams l_hostForeignCashTransferParams = 
                    new HostForeignCashTransferParams(l_hostForeignCashTransferRow);
                
                //==========外貨入出金テーブルの処理区分の更新設定============
                
                boolean l_blnIsFail = false;
                //処理区分を設定
                HashMap l_map = new HashMap();
                
                //外貨入出金テーブルの処理区分の更新条件設定
                
                String l_strUpdateWhere = " rowid = ? ";
                
                //外貨入出金テーブルの処理区分の更新値設定
                String[] l_strUpdateParams = {l_hostForeignCashTransferParams.getRowid()};

                //==========================End==========================
                
                try
                {
                    WEB3AioSonarCashTransForeignNormalTransactionCallback l_transactionCallback =
                        new WEB3AioSonarCashTransForeignNormalTransactionCallback(
                            l_hostForeignCashTransferRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch(Exception l_ex)
                {
                    l_blnIsFail = true;
                    m_log.debug("__an Exception__ ", l_ex);
                }
                if (l_blnIsFail)
                {
                    //1.2.2.3 (*2) 外貨入出金テーブルの処理区分の更新
                    //(*2) 外貨入出金テーブル.処理区分に以下の値をセットして更新する。
                    //"9"（エラー）： 上記処理（Loop内の処理）で例外が発生した場合
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    l_queryProcessor.doUpdateAllQuery(
                        HostForeignCashTransferRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateParams,
                        l_map);
                }
            }
            
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
