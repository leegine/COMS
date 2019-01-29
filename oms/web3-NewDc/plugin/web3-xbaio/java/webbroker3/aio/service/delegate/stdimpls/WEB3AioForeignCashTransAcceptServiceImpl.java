head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨入出金受付サービスImpl(WEB3AioForeignCashTransAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 徐宏偉 (中訊) 新規作成
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
import webbroker3.aio.data.HostFCashTransOrderAcceptParams;
import webbroker3.aio.data.HostFCashTransOrderAcceptRow;
import webbroker3.aio.message.WEB3AioForeignCashTransAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioForeignCashTransAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (外貨入出金受付サービスImpl)<BR>
 * 外貨入出金受付サービス実装クラス<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptServiceImpl 
    implements WEB3AioForeignCashTransAcceptService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransAcceptServiceImpl.class);

    /**
     * 外貨入出金受付処理を行う。<BR> 
     * <BR>
     * シーケンス図<BR> 
     * 「（入出金受付）外貨入出金受付」参照 <BR>
     * @@param l_request - (リクエストデータ)<BR>
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
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1)外貨入出金受付トランザクションコールバックインスタンスを生成する。 
        WEB3AioForeignCashTransAcceptTransactionCallback l_aioForeignCashTransAcceptTransactionCallback =
            new WEB3AioForeignCashTransAcceptTransactionCallback();

        try
        {
            // 1.2)クエリプロセッサを取得する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.3)doTransaction(トランザクション属性 : int, トランザクションコールバック : TransactionCallback)
            //DBトランザクション処理を実施する。  
            //
            //[doTransaction()に指定する引数]  
            //トランザクション属性：　@TX_CREATE_NEW  
            //トランザクションコールバック：　@外貨入出金受付TransactionCallbackインスタンス 
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, 
                l_aioForeignCashTransAcceptTransactionCallback);

            // 1.4)createResponse( )(外貨入金受付リクエスト::createResponse)
            WEB3AioForeignCashTransAcceptResponse l_response =
                (WEB3AioForeignCashTransAcceptResponse)l_request.createResponse();

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
     * (外貨入出金受付TransactionCallback )<BR>
     * 外貨入出金受付トランザクションコールバッククラス
     */
    public class WEB3AioForeignCashTransAcceptTransactionCallback implements TransactionCallback
    {
        /**
         * ログ出力ユーティリティ。
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioForeignCashTransAcceptTransactionCallback.class);

        /**
         * (外貨入金受付TransactionCallback)<BR>
         * デフォルトコンストラクタ
         */
        public WEB3AioForeignCashTransAcceptTransactionCallback()
        {

        }

        /**
         * 注文受付処理を実施する。 <BR>
         * <BR>
         * シーケンス図 <BR>
         * 「（外貨入出金受付）process」参照 <BR>
         * @@return Object<BR>
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            String STR_METHOD_NAME = "WEB3AioForeignCashTransAcceptTransactionCallback::process()";
            m_log.entering(STR_METHOD_NAME);

            // 1.1)外貨入出金伝票受付キューテーブル読込み
            //(*1) 以下の条件で 外貨入出金伝票受付キューテーブルから行ロックオプションにてレコードを取得する
            //[検索条件]
            //データコード = "GI80D"
            //処理区分 = "0"（未処理）
            String l_strWhere = " request_code = ? and status = ? ";

            String l_strCondition = null;

            Object[] l_objWhereValues = {
                WEB3HostRequestCodeDef.F_CASH_TRANS_ORDER_ACCEPT,
                WEB3StatusDef.NOT_DEAL};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisResults =
                l_queryProcessor.doFindAllQuery(
                    HostFCashTransOrderAcceptRow.TYPE,
                    l_strWhere,
                    null,
                    l_strCondition,
                    l_objWhereValues);

            int l_intResultLength = 0;

            if(!l_lisResults.isEmpty())
            {
                l_intResultLength = l_lisResults.size();
            }

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            // 1.2)取得したレコード毎のLoop処理
            for(int i = 0; i < l_intResultLength; i++)
            {
                HostFCashTransOrderAcceptRow l_row = 
                    (HostFCashTransOrderAcceptRow)l_lisResults.get(i);
                HostFCashTransOrderAcceptParams l_hostFCashTransOrderAcceptParams =
                    new HostFCashTransOrderAcceptParams(l_row);

                String l_strInstitutionCode = 
                    l_hostFCashTransOrderAcceptParams.getInstitutionCode();
                String l_strBranchCode = 
                    l_hostFCashTransOrderAcceptParams.getBranchCode();
                String l_strAccountCode = 
                    l_hostFCashTransOrderAcceptParams.getAccountCode();
                String l_strOrderRequestNumber = 
                    l_hostFCashTransOrderAcceptParams.getOrderRequestNumber();

                boolean l_blnExc = false;
                try
                {
                    // 1.2.1)注文単位オブジェクトを取得する。  
                    //[引数]  
                    //証券会社コード： 外貨入出金伝票受付キューテーブル.証券会社コード  
                    //部店コード： 外貨入出金伝票受付キューテーブル.部店コード  
                    //顧客コード： 外貨入出金伝票受付キューテーブル.顧客コード  
                    //識別コード： 外貨入出金伝票受付キューテーブル.識別コード  
                    //補助口座タイプ： 1（預り金口座） 

                    AioOrderUnit l_aioOrderUnit = l_aioOrderManager.getOrderUnit(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode,
                        l_strOrderRequestNumber,
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                    WEB3AioForeignCashTransAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3AioForeignCashTransAcceptNormalTransactionCallback(
                            l_aioOrderUnit,
                            l_hostFCashTransOrderAcceptParams);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

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
                catch(Exception l_ex)
                {
                    m_log.debug("__an Exception__ ", l_ex);
                    l_blnExc = true;
                }

                //1.2.4)外貨入出金伝票受付キューテーブルの処理区分の更新
                //外貨入出金伝票受付キューテーブル.処理区分に以下の値をセットして更新する。
                //"9"（エラー）： 上記処理（Loop内の処理）で例外が発生した場合
                HashMap l_hashMap = new HashMap();

                if(l_blnExc)
                {
                    l_hashMap.put("status", WEB3StatusDef.DATA_ERROR);

                    String l_strRequestCode = 
                        l_hostFCashTransOrderAcceptParams.getRequestCode();

                    String l_strUpdateWhere = "request_code = ? and institution_code = ? " +
                        "and branch_code = ? and account_code = ? and order_request_number = ? ";

                    Object[] l_objUpdaeWereValues = {
                        l_strRequestCode, 
                        l_strInstitutionCode,
                        l_strBranchCode, 
                        l_strAccountCode, 
                        l_strOrderRequestNumber};

                    l_queryProcessor.doUpdateAllQuery(
                        HostFCashTransOrderAcceptRow.TYPE,
                        l_strUpdateWhere,
                        l_objUpdaeWereValues,
                        l_hashMap);
                }
            }

            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
