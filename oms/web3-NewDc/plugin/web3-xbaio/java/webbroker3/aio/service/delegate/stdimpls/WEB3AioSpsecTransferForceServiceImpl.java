head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制サービスImpl(WEB3AioSpsecTransferForceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.aio.data.HostSpsecTransNotifyRow;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceService;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * ( 特定口座振替強制サービスImpl )<BR>
 * 特定口座振替強制サービス実装クラス
 *
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceServiceImpl implements WEB3AioSpsecTransferForceService
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceServiceImpl.class);

    /**
     * 特定口座振替強制処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（特定口座振替強制）execute」 参照
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41579661009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 特定口座振替強制トランザクションコールバックインスタンスを生成する。
        WEB3AioSpsecTransferForceTransactionCallBack l_spsecTransferForceTransactionCallBack =
            new WEB3AioSpsecTransferForceTransactionCallBack();

        //1.2 getDefaultProcessor()
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataFindException l_ex)
        {
            log.error("クエリプロセッサの例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("クエリプロセッサの例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 doTransaction(TransactionCallback)
        //[doTransaction()に指定する引数]
        //トランザクション属性：　@TX_CREATE_NEW
        //トランザクションコールバック：　@特定口座振替強制TransactionCallbackインスタンス
        try
        {
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_spsecTransferForceTransactionCallBack);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBトランザクション処理の例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBトランザクション処理の例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.4 レスポンスデータを生成する。
        WEB3BackResponse l_response = l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * ( 特定口座振替強制TransactionCallback)<BR>
     *  特定口座振替強制TransactionCallbackクラス
     */
    public class WEB3AioSpsecTransferForceTransactionCallBack implements TransactionCallback
    {
        /**
         * (特定口座振替強制TransactionCallback)<BR>
         * デフォルトコンストラクタ
         * @@return WEB3AioSecurityTransferForceTransactionCallBack
         * @@roseuid 415796810178
         */
        public WEB3AioSpsecTransferForceTransactionCallBack()
        {

        }

        /**
         * 特定口座振替強制処理を行う。 <BR>
         * <BR>
         * シーケンス図<BR>
         * 「（特定口座振替強制）process」 参照
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415796B5005F
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {

            String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1 (*1) 以下の条件で 特定口座強制振替キューテーブルから
            //行ロックオプションにてレコードを取得する。
            //[検索条件]
            //データコード = "GI835"（特定口座強制）
            //処理区分 = "0"（未処理）

            //a> queryprocessr
            //throw DataNetWorkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //b> where
            String l_strWhere = " request_code = ? and status = ? ";
            //c> data
            Object[] l_bindVars =
                {WEB3HostRequestCodeDef.AIO_SPSEC_TRANS_NOTIFY,
                 WEB3StatusDef.NOT_DEAL};
            //d> condition
            // 李志強 U01362の暫定対応 start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // 李志強 U01362の暫定対応 end

            //throw DataQueryException, DataNetworkException
            List l_lisHostSpsecTransNotifyRows =
                l_queryProcessor.doFindAllQuery(
                    HostSpsecTransNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_bindVars);

            //1.2 取得したキューテーブルのレコード毎のLoop処理
            int l_intSize = 0;
            if (l_lisHostSpsecTransNotifyRows != null)
            {
                l_intSize = l_lisHostSpsecTransNotifyRows.size();
            }

            for (int i = 0; i < l_intSize; i++)
            {
                //get the row
                HostSpsecTransNotifyRow l_hostSpsecTransNotifyRow =
                    (HostSpsecTransNotifyRow)l_lisHostSpsecTransNotifyRows.get(i);
                //get the params
                HostSpsecTransNotifyParams l_hostSpsecTransNotifyParams =
                    new HostSpsecTransNotifyParams(l_hostSpsecTransNotifyRow);

                log.debug("特定口座強制振替キューRow" + l_hostSpsecTransNotifyRow);

                WEB3AioSpsecTransferForceUnitService l_aioSpsecTransferForceUnitService =
                    (WEB3AioSpsecTransferForceUnitService)Services.getService(
                        WEB3AioSpsecTransferForceUnitService.class);

                //a> the map to be updated
                HashMap l_map = new HashMap();

                //b> where
                String l_strWhereUpdate =
                    "institution_code = ? and branch_code = ? and " +
                    "account_code = ? and order_request_number = ? ";

                //c> data
                Object[] l_bindVarsUpdate =
                    {l_hostSpsecTransNotifyRow.getInstitutionCode(),
                        l_hostSpsecTransNotifyRow.getBranchCode(),
                        l_hostSpsecTransNotifyRow.getAccountCode(),
                        l_hostSpsecTransNotifyRow.getOrderRequestNumber()};

                try
                {
                    // 李志強 U01362の暫定対応 start
                    // TransactionCallback生成
                    WEB3AioSpsecTransferForceNormalTransactionCallBack l_transactionCallback =
                        new WEB3AioSpsecTransferForceNormalTransactionCallBack(
                        l_hostSpsecTransNotifyRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    //1.2.1 特定口座強制振替キュー.数量 > 0 の場合
//                    if (l_hostSpsecTransNotifyRow.getQuantity() > 0)
//                    {
//                        log.debug("特定口座強制振替キュー.数量 > 0 の場合");
//                        //1.2.1.1 新規注文（振替元＋振替先）の登録を行う。
//                        //[引数]
//                        //特定口座強制振替キューParams： 特定口座強制振替キューParamsオブジェクト
//
//                        //throw WEB3BaseException
//                        AioOrderUnit[] l_aioOrderUnits =
//                            l_aioSpsecTransferForceUnitService.submitOrder(
//                                    l_hostSpsecTransNotifyParams);
//
//                        if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
//                        {
//                            log.debug("エラー：該当の振替注文単位がなし");
//                            throw new WEB3SystemLayerException(
//                                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                                this.getClass().getName() + "." + STR_METHOD_NAME);
//                        }
//                        //1.2.1.2 証券振替通知処理を行う。
//                        //[引数]
//                        //注文単位： submit注文()の戻り値
//                        //エラーコード： "0000"（正常）
//                        //受付通知区分： "1"（受付完了）
//
//                        //証券振替通知UnitService
//                        WEB3AioSecurityTransferNotifyUnitService l_aioSecurityTransferNotifyUnitService =
//                            (WEB3AioSecurityTransferNotifyUnitService)Services.getService(
//                                WEB3AioSecurityTransferNotifyUnitService.class);
//
//                        //throw WEB3BaseException
//                        l_aioSecurityTransferNotifyUnitService.execute(
//                            l_aioOrderUnits,
//                            WEB3ErrorReasonCodeDef.NORMAL,
//                            WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
//                    }
//                    //1.2.2 特定口座強制振替キュー.数量 < 0 の場合
//                    else if (l_hostSpsecTransNotifyRow.getQuantity() < 0)
//                    {
//                        log.debug("特定口座強制振替キュー.数量 < 0 の場合");
//                        //1.2.2.1 該当注文の取消を行う。
//                        //[引数]
//                        //特定口座強制振替キューParams： 特定口座強制振替キューParamsオブジェクト
//                        l_aioSpsecTransferForceUnitService.submitCancel(
//                                l_hostSpsecTransNotifyParams);
//                    }
//
//                    //1.2.3 特定口座強制振替キュー.処理区分に以下の値をセットして更新する。
//                    //"9"（エラー）： 上記処理（Loop内の処理）で例外が発生した場合
//                    //"1"（処理済）： それ以外の場合
//                    l_map.put("status", WEB3HostStatusDef.COMPLETE_PROCESS);
                    // 李志強 U01362の暫定対応 end
                }
                // 李志強 U01362の暫定対応 start
//                catch (WEB3BaseException l_ex)
//                {
//                    log.debug("証券振替通知処理失敗である", l_ex);
//                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);
//                }
//
//                //update
//                l_queryProcessor.doUpdateAllQuery(
//                    HostSpsecTransNotifyRow.TYPE,
//                    l_strWhereUpdate,
//                    l_bindVarsUpdate,
//                    l_map);

                catch (WEB3BaseRuntimeException l_ex)
                {
                    log.debug("証券振替通知処理失敗である", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //update
                    l_queryProcessor.doUpdateAllQuery(
                        HostSpsecTransNotifyRow.TYPE,
                        l_strWhereUpdate,
                        l_bindVarsUpdate,
                        l_map);
                }
                catch (Exception l_ex)
                {
                    log.debug("証券振替通知処理失敗である", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //update
                    l_queryProcessor.doUpdateAllQuery(
                        HostSpsecTransNotifyRow.TYPE,
                        l_strWhereUpdate,
                        l_bindVarsUpdate,
                        l_map);
                }
                // 李志強 U01362の暫定対応 end
            }

            log.exiting(STR_METHOD_NAME);

            return null;
        }
    }
}
@
