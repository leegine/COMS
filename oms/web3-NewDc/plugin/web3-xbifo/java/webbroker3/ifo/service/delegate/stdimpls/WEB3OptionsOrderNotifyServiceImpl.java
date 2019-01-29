head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文通知サービスImpl(WEB3OptionsOrderNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.data.HostFotypeOrderReceiptRow;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (OP注文通知サービスImpl)<BR>
 * 株価指数オプション注文通知サービス実装クラス<BR>
 * @@author  : 凌建平
 * @@version : 1.0
 */
public class WEB3OptionsOrderNotifyServiceImpl implements WEB3OptionsOrderNotifyService 
{
    /**
     * ログを出力
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsOrderNotifyServiceImpl.class);
   
    /**
     * @@roseuid 41AAE845004E
     */
    public WEB3OptionsOrderNotifyServiceImpl() 
    {
    
    }
   
    /**
     * 株価指数オプション注文通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OPサービス）オプション注文通知」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163A7E7001C
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1.1 getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.2  OP注文通知TransactionCallback()
            WEB3OptionsOrderNotifyTransactionCallback l_orderNotifyTransactionCallback = 
                new WEB3OptionsOrderNotifyTransactionCallback();

            //1.3 処理対象パラメータセット（詳細未定）

            //1.4 doTransaction(TransactionCallback)
            log.debug("l_QueryProcessor.doTransaction");
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_orderNotifyTransactionCallback);
        }
        catch (DataRollbackException l_dataRollbackException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataRollbackException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataRollbackException.getMessage(),
                l_dataRollbackException);
        }
        catch (DataFindException l_dataFindException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataFindException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataFindException.getMessage(),
                l_dataFindException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataCallbackException l_dataCallbackException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataCallbackException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataCallbackException.getMessage(),
                l_dataCallbackException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }
           
        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
   
    /**
     * (OP注文通知TransactionCallback)<BR>
     * OP注文通知TransactionCallbackクラス<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     */
    public class WEB3OptionsOrderNotifyTransactionCallback implements TransactionCallback 
    {
          
        /**
         * (OP注文通知TransactionCallback)<BR>
         * コンストラクタ<BR>
         * @@roseuid 4163A9E202EB
         */
        public WEB3OptionsOrderNotifyTransactionCallback() 
        {
        
        }
          
        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（OP注文通知）process」参照。<BR>
         * <BR>
         * ※戻り値は、nullとする。<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4163C2240368
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1  (*1)先物OP注文通知キューテーブル読込
            //(*1) 先物OP注文通知キューテーブルを、行ロック（select for update）オプションにて読み込む。
            //　@[検索条件]
            //　@先物OP注文通知キューテーブル.データコード == "OP注文通知"(EI821)
            //　@先物OP注文通知キューテーブル.処理区分 == "未処理"
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" request_code = ? ");
            l_strWhere.append(" and status = ? ");

            Object[] l_objWhere = {
                WEB3HostRequestCodeDef.OPTION_ORDER_NOTICE, // OP注文通知(EI821)
                WEB3StatusDef.NOT_DEAL};                    // 未処理

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_QueryProcessor.doFindAllQuery(
                HostFotypeOrderReceiptRow.TYPE,
                l_strWhere.toString(),
                null,
                null,
                l_objWhere);
            
            int l_intListRecordCnt = 0;
            if (l_lisRecords != null)
            {
                l_intListRecordCnt = l_lisRecords.size();
            }
            log.debug("l_lisRecords.size() = " + l_lisRecords.size());

            //1.2 先物OP注文通知キューテーブル検索結果レコード各行ごとのLoop処理
            for (int i=0; i<l_intListRecordCnt; i++)
            {
                log.debug("LOOP i = " + i);
                HostFotypeOrderReceiptRow l_receiptRow = (HostFotypeOrderReceiptRow)l_lisRecords.get(i);
                HostFotypeOrderReceiptParams l_notifyParams = new HostFotypeOrderReceiptParams(l_receiptRow);
                try
                {

                    // TransactionCallback生成
                    WEB3OptionsOrderNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3OptionsOrderNotifyNormalTransactionCallback(
                            l_receiptRow,
                            l_notifyParams);

                    // doTransaction()
                    l_QueryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_exp)
                {
                    if (l_exp instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                        //口座ロックエラーの場合(処理対象先物OP注文通知キューレコード.処理区分は”未処理”のまま)
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            log.debug("口座ロック失敗：" + l_notifyParams.toString());
                            continue;
                        }
                    }
                    if (l_exp instanceof DataCallbackException)
                    {
                        DataCallbackException l_dce = (DataCallbackException)l_exp;
                        WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
                        log.error("一件処理にてエラー発生", l_wbe);
                    }
                    else
                    {
                        log.error("一件処理にてエラー発生", l_exp);
                    }
                    //処理対象先物OP注文通知キューレコード処理区分を”エラー”で更新
                    l_notifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_QueryProcessor.doUpdateQuery(l_notifyParams);
                }
            }
            
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
