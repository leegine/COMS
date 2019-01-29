head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferForceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替強制サービスImpl(WEB3AioSecurityTransferForceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 屈陽 (中訊) 新規作成
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

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.aio.data.HostMrgsecTransNotifyRow;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceUnitService;
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
 * (証券振替強制サービスImpl)<BR>
 * 証券振替強制サービス実装クラス
 *
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferForceServiceImpl implements WEB3AioSecurityTransferForceService
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferForceServiceImpl.class);

    /**
     * 証券振替強制処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券振替強制）execute」 参照
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41579661009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3BackRequest l_request)";
        log.entering(l_strMethodName);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }

        //1.1 証券振替強制トランザクションコールバックインスタンスを生成する。
        WEB3AioSecurityTransferForceTransactionCallBack l_aioSecurityTransferForceTransactionCallBack =
            new WEB3AioSecurityTransferForceTransactionCallBack();

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
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("クエリプロセッサの例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 doTransaction(TransactionCallback)
        //[doTransaction()に指定する引数]
        //トランザクション属性：　@TX_CREATE_NEW
        //トランザクションコールバック：証券振替強制TransactionCallbackインスタンス
        try
        {
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_aioSecurityTransferForceTransactionCallBack);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBトランザクション処理の例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBトランザクション処理の例外", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //1.4 レスポンスデータを生成する。
        WEB3BackResponse l_response = l_request.createResponse();

        log.exiting(l_strMethodName);

        return l_response;
    }

    /**
     * (証券振替強制TransactionCallback)<BR>
     * 証券振替強制TransactionCallbackクラス
     */
    public class WEB3AioSecurityTransferForceTransactionCallBack implements TransactionCallback
    {
        /**
         * (証券振替強制TransactionCallback)<BR>
         * デフォルトコンストラクタ
         * @@return
         * webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferForceServiceImpl.WEB3AioSecurityTransferForceTransaction
         * CallBack
         * @@roseuid 415796810178
         */
        public WEB3AioSecurityTransferForceTransactionCallBack()
        {

        }

        /**
         * 証券振替強制処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（証券振替強制）process」 参照
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415796B5005F
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String l_strMethodName = "process()";
            log.entering(l_strMethodName);

            //1.1 (*1) 以下の条件で 代用振替強制キューテーブルから
            //行ロックオプションにてレコードを取得する。
            //[検索条件]
            //データコード = "GI813"（代用振替強制）
            //処理区分 = "0"（未処理）

            //a> queryprocessr
            //throw DataNetWorkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //b> where
            String l_strWhere = " request_code = ? and status = ? ";
            //c> data
            Object[] l_bindVars =
                {WEB3HostRequestCodeDef.AIO_MRGSEC_TRANS_NOTIFY, WEB3StatusDef.NOT_DEAL};
            //d> condition
            // 李志強 U01362の暫定対応 start
//            String l_strCondition = "for update";
            String l_strCondition = null;
            // 李志強 U01362の暫定対応 end

            //throw DataQueryException, DataNetworkException
            List l_lisHostMrgsecTransNotifyRows =
                l_queryProcessor.doFindAllQuery(
                    HostMrgsecTransNotifyRow.TYPE,
                    l_strWhere,
                    l_strCondition,
                    l_bindVars);

            //1.2 取得したキューテーブルのレコード毎のLoop処理
            int l_intSize = 0;
            if (l_lisHostMrgsecTransNotifyRows != null && l_lisHostMrgsecTransNotifyRows.size() != 0)
            {
                l_intSize = l_lisHostMrgsecTransNotifyRows.size();
            }

            for (int i = 0; i < l_intSize; i++)
            {
                //1.2.1 新規注文（振替元＋振替先）の登録を行う。
                //[引数]
                //代用振替強制キューParams： 代用振替強制キューParamsオブジェクト

                //証券振替強制UnitService
                WEB3AioSecurityTransferForceUnitService l_aioSecurityTransferForceUnitService =
                    (WEB3AioSecurityTransferForceUnitService)Services.getService(
                        WEB3AioSecurityTransferForceUnitService.class);

                //get the row
                HostMrgsecTransNotifyRow l_hostMrgsecTransNotifyRow =
                    (HostMrgsecTransNotifyRow)l_lisHostMrgsecTransNotifyRows.get(i);

                //get the params
                HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
                    new HostMrgsecTransNotifyParams(l_hostMrgsecTransNotifyRow);

                //****************pre-deal before 1.2.3*****************
                //a> the map to be updated
                HashMap l_map = new HashMap();

                //b> where
                String l_strWhereUpdate =
                    " institution_code = ? and branch_code = ? and account_code = ? and order_request_number = ? ";

                //c> data
                Object[] l_bindVarsUpdate =
                    {l_hostMrgsecTransNotifyRow.getInstitutionCode(),
                        l_hostMrgsecTransNotifyRow.getBranchCode(),
                        l_hostMrgsecTransNotifyRow.getAccountCode(),
                        l_hostMrgsecTransNotifyRow.getOrderRequestNumber()};
                //****************end of the pre-deal*******************

                try
                {
                    // 李志強 U01362の暫定対応 start
                    // TransactionCallback生成
                    WEB3AioSecurityTransferForceNormalTransactionCallBack l_transactionCallback =
                        new WEB3AioSecurityTransferForceNormalTransactionCallBack(
                        l_hostMrgsecTransNotifyRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    //throw WEB3BaseException
//                    AioOrderUnit[] l_aioOrderUnits =
//                        l_aioSecurityTransferForceUnitService.submitOrder(l_hostMrgsecTransNotifyParams);
//
//                    if (l_aioOrderUnits == null || l_aioOrderUnits.length < 1)
//                    {
//                        log.debug("エラー：該当の振替注文単位がなし");
//                        throw new WEB3SystemLayerException(
//                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                            this.getClass().getName() + "." + l_strMethodName);
//                    }
//
//                    //1.2.2 証券振替通知処理を行う。
//                    //[引数]
//                    //注文単位： submit注文()の戻り値 --l_aioOrderUnit
//                    //エラーコード： "0000"（正常）--WEB3ErrorReasonCodeDef.NORMAL
//                    //受付通知区分： "1"（受付完了）--WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE
//
//                    //証券振替通知UnitService
//                    WEB3AioSecurityTransferNotifyUnitService l_aioSecurityTransferNotifyUnitService =
//                        (WEB3AioSecurityTransferNotifyUnitService)Services.getService(
//                            WEB3AioSecurityTransferNotifyUnitService.class);
//
//                    //throw WEB3BaseException
//                    l_aioSecurityTransferNotifyUnitService.execute(
//                        l_aioOrderUnits,
//                        WEB3ErrorReasonCodeDef.NORMAL,
//                        WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
//
//                    //1.2.3 代用振替強制キュー.処理区分に以下の値をセットして更新する。
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
//                    HostMrgsecTransNotifyRow.TYPE,
//                    l_strWhereUpdate,
//                    l_bindVarsUpdate,
//                    l_map);

                catch (WEB3BaseRuntimeException l_ex)
                {
                    log.debug("証券振替通知処理失敗である", l_ex);
                    l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

                    //update
                    l_queryProcessor.doUpdateAllQuery(
                        HostMrgsecTransNotifyRow.TYPE,
                        l_strWhereUpdate,
                        l_bindVarsUpdate,
                        l_map);
                }
				catch (Exception l_ex)
				{
					log.debug("__an Exception__ ", l_ex);
					l_map.put("status", WEB3HostStatusDef.DATA_ERROR);

					//update
					l_queryProcessor.doUpdateAllQuery(
						HostMrgsecTransNotifyRow.TYPE,
						l_strWhereUpdate,
						l_bindVarsUpdate,
						l_map);
				}
                // 李志強 U01362の暫定対応 end
            }

            log.exiting(l_strMethodName);

            return null;
        }
    }
}
@
