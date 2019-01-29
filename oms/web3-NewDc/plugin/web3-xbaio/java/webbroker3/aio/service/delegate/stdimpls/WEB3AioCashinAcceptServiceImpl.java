head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金受付サービス実装クラス(WEB3AioCashinAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 周勇 (中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostCashTransOrderAcceptParams;
import webbroker3.aio.data.HostCashTransOrderAcceptRow;
import webbroker3.aio.message.WEB3AioCashinAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金受付サービスImpl)<BR>
 * 入金受付サービス実装クラス
 *
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinAcceptServiceImpl implements WEB3AioCashinAcceptService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinAcceptServiceImpl.class);

    /**
     * 入金受付処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出金受付）入金受付」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FC785D0174
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("WEB3BackRequest is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1)入金受付トランザクションコールバックインスタンスを生成する
        WEB3AioCashinAcceptTransactionCallback l_aioCashinAcceptTransactionCallback =
            new WEB3AioCashinAcceptTransactionCallback();

        try
        {
            // 1.2)クエリプロセッサを取得する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // 1.3)DBトランザクション処理を実施する。
            //[doTransaction()に指定する引数]
            //トランザクション属性：　@TX_CREATE_NEW
            //トランザクションコールバック：　@入金受付TransactionCallbackインスタンス
            l_queryProcessor.doTransaction(l_aioCashinAcceptTransactionCallback);

            // 1.4)createResponse( )(入金受付リクエスト::createResponse)
            WEB3AioCashinAcceptResponse l_response =
                (WEB3AioCashinAcceptResponse) l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

    }

    /**
     * (入金受付TransactionCallback)<BR>
     * 入金受付トランザクションコールバッククラス
     */
    public class WEB3AioCashinAcceptTransactionCallback implements TransactionCallback
    {
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioCashinAcceptTransactionCallback.class);

        /**
         * (入金受付TransactionCallback)<BR>
         * デフォルトコンストラクタ
         * @@return WEB3AioCashinAcceptTransactionCallback
         * @@roseuid 40F7C57E004D
         */
        public WEB3AioCashinAcceptTransactionCallback()
        {

        }

        /**
         * 注文受付処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（入金受付）process」参照<BR>
         * @@return Object<BR>
         * @@roseuid 40F7C59C0109
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "WEB3AioCashinAcceptTransactionCallback::process()";
            m_log.entering(STR_METHOD_NAME);

            // 1.1)入出金伝票受付キューテーブル読込み
            //(*1) 以下の条件で 入出金伝票受付キューテーブルから行ロックオプションにてレコードを取得する
            //[検索条件]
            //データコード = "GI80C"（入出金伝票受付）
            //処理区分 = "0"（未処理）
            String l_strWhere = " request_code = ? and status = ? ";
            // 李志強 U01832の暫定対応 start
//            String l_strCondition = " for update";
            String l_strCondition = null;
            // 李志強 U01832の暫定対応 end
            Object[] l_objWhereValues = {WEB3HostRequestCodeDef.AIO_SLIP_ACCEPT,
                     WEB3StatusDef.NOT_DEAL};


            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisResult =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransOrderAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhereValues);

            int l_intResultLength = 0;

            if(!l_lisResult.isEmpty())
            {
                l_intResultLength = l_lisResult.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            // 李志強 U01832の暫定対応 start
//            WEB3AioCashTransferAcceptUnitService l_aioCashTransferAcceptUnitService =
//                (WEB3AioCashTransferAcceptUnitService) Services.getService(
//                    WEB3AioCashTransferAcceptUnitService.class);
//
//            WEB3AioCashTransferCompleteUnitService l_aioCashTransferCompleteUnitService =
//                (WEB3AioCashTransferCompleteUnitService) Services.getService(
//                    WEB3AioCashTransferCompleteUnitService.class);
            // 李志強 U01832の暫定対応 end

            // 1.2)取得したレコード毎のLoop処理
            for(int i = 0; i < l_intResultLength; i++)
            {
                HostCashTransOrderAcceptParams l_hostCashTransOrderAcceptParams =
                    (HostCashTransOrderAcceptParams) l_lisResult.get(i);

                String l_strInstitutionCode = l_hostCashTransOrderAcceptParams.getInstitutionCode();
                String l_strBranchCode = l_hostCashTransOrderAcceptParams.getBranchCode();
                String l_strAccountCode = l_hostCashTransOrderAcceptParams.getAccountCode();
                String l_strOrderRequestNumber = l_hostCashTransOrderAcceptParams.getOrderRequestNumber();

                boolean l_blnExc = false;
                try
                {
                    // 1.2.1)注文単位オブジェクトを取得する。
                    //[引数]
                    //証券会社コード： 入出金伝票受付キューテーブル.証券会社コード
                    //部店コード： 入出金伝票受付キューテーブル.部店コード
                    //顧客コード： 入出金伝票受付キューテーブル.顧客コード
                    //識別コード： 入出金伝票受付キューテーブル.識別コード
                    //補助口座タイプ： 1（預り金口座）
                    AioOrderUnit l_aioOrderUnit = l_aioOrderManager.getOrderUnit(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strOrderRequestNumber,
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    // 李志強 U01832の暫定対応 start
                    WEB3AioCashinAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3AioCashinAcceptNormalTransactionCallback(
                            l_aioOrderUnit,
                            l_hostCashTransOrderAcceptParams);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    // 1.2.2)入出金受付DB更新処理を行う。
//                    //[引数]
//                    //注文単位： 取得した注文単位オブジェクト
//                    //エラーコード： 出金請求受付キューテーブル.エラーメッセージ
//                    //受付通知区分： 出金請求受付キューテーブル.受付通知区分
//                    l_aioCashTransferAcceptUnitService.execute(
//                        l_aioOrderUnit,
//                        l_hostCashTransOrderAcceptParams.getErrorMessage(),
//                        l_hostCashTransOrderAcceptParams.getAcceptDiv());
//
//                    // 入出金伝票受付キューテーブル.受付通知区分 = "1"（受付完了）の場合
//                    if(WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE.equals(
//                        l_hostCashTransOrderAcceptParams.getAcceptDiv()))
//                    {
//                        //1.2.3)受付完了の場合
//                        // 1.2.3.1)入出金完了処理に伴う注文データの更新とトランザクションデータの生成を行う。
//                        //[引数]
//                        //注文単位： get注文単位()で取得した注文単位オブジェクト
//                        l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
//
//                    }
                    // 李志強 U01832の暫定対応 end
                }
                catch(WEB3BaseException l_ex)
                {
                    m_log.debug("__an WEB3BaseException ", l_ex);
                    l_blnExc = true;
                }
                catch(NotFoundException l_ex)
                {
                    m_log.debug("__an NotFoundException__ ", l_ex);
                    l_blnExc = true;
                }
                // 李志強 U01832の暫定対応 start
                catch(Exception l_ex)
                {
                    m_log.debug("__an Exception__ ", l_ex);
                    l_blnExc = true;
                }
                // 李志強 U01832の暫定対応 end

                //1.2.4)キューテーブルの処理区分の更新
                //入出金伝票受付キューテーブル.処理区分に以下の値をセットして更新する。
                //"9"（エラー）： 上記処理（Loop内の処理）で例外が発生した場合
                //"1"（処理済）： それ以外の場合
                HashMap l_hashMap = new HashMap();

                // 李志強 U01832の暫定対応 start
//                if(l_blnExc)
//                {
//                    l_hashMap.put("status", WEB3StatusDef.DATA_ERROR);
//                }
//                else
//                {
//                    l_hashMap.put("status", WEB3StatusDef.DEALT);
//                }
//
//                String l_strRequestCode = l_hostCashTransOrderAcceptParams.getRequestCode();
//
//                String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
//                    "and branch_code = ? and account_code = ? and order_request_number = ? ";
//
//                Object[] l_objUpdaeWereValues = {l_strRequestCode, l_strInstitutionCode,
//                        l_strBranchCode, l_strAccountCode, l_strOrderRequestNumber};
//
//                l_queryProcessor.doUpdateAllQuery(
//                    HostCashTransOrderAcceptRow.TYPE,
//                    l_strUpdateWhere,
//                    l_objUpdaeWereValues,
//                    l_hashMap);

                if(l_blnExc)
                {
                    l_hashMap.put("status", WEB3StatusDef.DATA_ERROR);

                    String l_strRequestCode = l_hostCashTransOrderAcceptParams.getRequestCode();

                    String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
                        "and branch_code = ? and account_code = ? and order_request_number = ? ";

                    Object[] l_objUpdaeWereValues = {l_strRequestCode, l_strInstitutionCode,
                            l_strBranchCode, l_strAccountCode, l_strOrderRequestNumber};

                    l_queryProcessor.doUpdateAllQuery(
                        HostCashTransOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_objUpdaeWereValues,
                        l_hashMap);
                }
                // 李志強 U01832の暫定対応 end
            }

            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
