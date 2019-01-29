head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金サービスImpl(WEB3AioSonarCashTransServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 黄建 (中訊) 新規作成
                   2004/10/26 屈陽 (中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.aio.data.HostCashTransferRow;
import webbroker3.aio.message.WEB3AioSonarCashTransResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR入出金サービスImpl)<BR>
 * SONAR入出金サービス実装クラス<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AioSonarCashTransServiceImpl
    implements WEB3AioSonarCashTransService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransServiceImpl.class);

    /**
     * SONAR入出金サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（SONAR入出金）execute」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FF74E2001F
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //１.１） SONAR入出金トランザクションコールバックインスタンスを生成する。
            WEB3AioSonarCashTransTransactionCallback
                l_aioSonarCashTransTransactionCallback =
                    new WEB3AioSonarCashTransTransactionCallback();

            //１.２）クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //１.３）　@DBトランザクション処理を実施する。
            //[doTransaction()に指定する引数]
            //トランザクション属性：　@TX_CREATE_NEW
            //トランザクションコールバック：　@
            //SONAR入出金TransactionCallbackインスタンス
            l_queryProcessor.doTransaction(l_aioSonarCashTransTransactionCallback);

            //１.４）　@SONAR入出金リクエストレスポンスを生成し、リターンする。
            //－SONAR入出金リクエスト.createレスポンス()をコールし、
            // SONAR入出金レスオブジェクトを生成する。
            WEB3AioSonarCashTransResponse
                l_aioSonarCashTransResponse =
                    (WEB3AioSonarCashTransResponse) l_request.createResponse();

            //－生成したSONAR入出金レスポンスを返す。
            log.exiting(STR_METHOD_NAME);
            return l_aioSonarCashTransResponse;
        }
        catch (DataException l_ex)
        {
            log.error("クエリプロセッサのインスタンスを取得失敗", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (SONAR入出金TransactionCallback)<BR>
     */
    public class WEB3AioSonarCashTransTransactionCallback
        implements TransactionCallback
    {
        /**
         *  ログユーティリティ<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioSonarCashTransTransactionCallback.class);

        /**
         * (SONAR入出金TransactionCallback)<BR>
         * デフォルトコンストラクタ<BR>
         * @@return WEB3AioSonarCashTransTransactionCallback
         * @@roseuid 40FF759D0177
         */
        public WEB3AioSonarCashTransTransactionCallback()
        {

        }

        /**
         * SONAR入出金処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（SONAR入出金）process」 参照<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40FF76C3009C
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process() ";
            m_log.entering(STR_METHOD_NAME);

            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //１.１）メッセージ (*1) 入出金テーブル読込み
            //(*1) 以下の条件で 入出金テーブルから
            // 行ロックオプションにてレコードを取得する。
            //[検索条件]
            //データコード = "GI811"（個別） or "FI811"（本部一括）
            //処理区分 = "0"（未処理）
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" (request_code = ?");
            l_strWhere.append(" or request_code = ?)");
            l_strWhere.append(" and status = ? ");
            // 李志強 U01832の暫定対応 start
//            String l_strCondition = " for update ";
            String l_strCondition = null;
            // 李志強 U01832の暫定対応 end
            Object[] l_objMutualFrgncalWhere =
            {
               WEB3HostRequestCodeDef.AIO_INDIVIDUAL,
               WEB3HostRequestCodeDef.AIO_ALL_HEADQUARTERS ,
               WEB3StatusDef.NOT_DEAL
            };

            /*検索*/
            List l_lisHostCashTransferRows =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransferRow.TYPE,
                    l_strWhere.toString(),
                    l_strCondition,
                    l_objMutualFrgncalWhere);

            int l_intSize = 0;
            if (l_lisHostCashTransferRows != null && !l_lisHostCashTransferRows.isEmpty())
            {
                l_intSize = l_lisHostCashTransferRows.size();
            }
            //============================FinApp=========================
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //入出金注文マネージャクラスを取得する。
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            //拡張アカウントマネージャ取得する
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            //１.２）メッセージ 取得したレコード毎のLoop処理
            for (int i = 0; i < l_intSize; i++)
            {
                //検索結果の入出金テーブルの各行に対して実施する

                //入出金Paramsを取得する
                HostCashTransferRow l_hostCashTransferRow =
                       (HostCashTransferRow) l_lisHostCashTransferRows.get(i);
                HostCashTransferParams l_hostCashTransferParams =
                    new HostCashTransferParams(l_hostCashTransferRow);

                // 李志強 U01832の暫定対応 start
//                //入出金金額を取得
//                long l_lngAmount = l_hostCashTransferParams.getAmount();
//                m_log.debug("入出金金額を取得:"+ l_lngAmount);
                // 李志強 U01832の暫定対応 end

                //==========入出金テーブルの処理区分の更新設定============
                boolean l_blnIsFail = false;
                //処理区分を設定
                HashMap l_map = new HashMap();
                //入出金テーブルの処理区分の更新条件設定
                String l_strUpdateWhere =  " rowid = ? ";
                //入出金テーブルの処理区分の更新値設定
                String[] l_updateParams = {l_hostCashTransferRow.getRowid()};

                //==========================End==========================

                try
                {
                    // 李志強 U01832の暫定対応 start
                    WEB3AioSonarCashTransNormalTransactionCallback l_transactionCallback =
                        new WEB3AioSonarCashTransNormalTransactionCallback(
                            l_hostCashTransferRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);

//                    // SONAR入出金UnitServiceを取得する。
//                    WEB3AioSonarCashTransUnitService
//                        l_aioSonarCashTransUnitService =
//                            (WEB3AioSonarCashTransUnitService) Services.getService(
//                                WEB3AioSonarCashTransUnitService.class);
//
//                    // 入出金受付UnitServiceを取得する。
//                    WEB3AioCashTransferAcceptUnitService
//                        l_aioCashTransferAcceptUnitService =
//                            (WEB3AioCashTransferAcceptUnitService) Services.getService(
//                                WEB3AioCashTransferAcceptUnitService.class);
//
//                    //入出金完了UnitServiceを取得する。
//                    WEB3AioCashTransferCompleteUnitService
//                        l_aioCashTransferCompleteUnitService =
//                            (WEB3AioCashTransferCompleteUnitService) Services.getService(
//                                WEB3AioCashTransferCompleteUnitService.class);
//
//                    //入出金注文単位
//                    AioOrderUnit l_aioOrderUnit = null;
//
//                    //１.２.１）入出金Params.入出金金額 > 0 の場合
//                    if (l_lngAmount > 0)
//                    {
//
//                        //１.２.１.1）submit注文(入出金Params)
//                        //新規注文の登録を行う。
//                        //[引数] 入出金Params： 入出金Paramsオブジェクト
//
//                        //注文IDを取得する
//                        long l_lngOrderId =
//                            l_aioSonarCashTransUnitService.submitOrder(
//                                l_hostCashTransferParams);
//
//                        //１.２.１.２）execute(AioOrderUnit, String, String)
//                        //入出金受付DB更新処理を行う。
//                        //[引数]
//                        //注文単位： 注文(submit注文()の戻り値).getOrderUnits()[0] の戻り値
//                        //エラーコード： "0000"（正常）
//                        //受付通知区分： "1"（受付完了）
//
//                        //入出金注文単位オブジェクトを取得する
//                        //===================NotFoundException=================
//                        l_aioOrderUnit =
//                            (AioOrderUnit)l_aioOrderManager.getOrderUnits(l_lngOrderId)[0];
//
//                        //入出金受付DB更新処理を行う
//                        l_aioCashTransferAcceptUnitService.execute(
//                            l_aioOrderUnit,
//                            WEB3ErrorReasonCodeDef.NORMAL,
//                            WEB3AcceptDivDef.CASH_TRANS_SERVICE_COMPLETE);
//
//                        //１.２.１.３）complete入出金(AioOrderUnit)
//                        //入出金完了処理に伴う注文データの更新とトランザクションデータの生成を行う。
//                        //[引数]
//                        //注文単位： 注文(submit注文()の戻り値).getOrderUnits()[0] の戻り値
//                        l_aioCashTransferCompleteUnitService.completeCashTransfer(l_aioOrderUnit);
//                    }
//
//                    //１.２.２）入出金Params.入出金金額 < 0 の場合
//                    if (l_lngAmount < 0)
//                    {
//                        //１.２.２.１）get注文単位(入出金Params)
//                        //取消対象の注文単位を取得する。
//                        //[引数] 入出金Params： 入出金Paramsオブジェクト
//                        l_aioOrderUnit =
//                            this.getOrderUnit(l_hostCashTransferParams);
//
//                        //１.２.２.２）complete入出金取消(AioOrderUnit)
//                        //注文の取消処理を行う。
//                        //[引数] 注文単位： get注文単位()の戻り値
//                        l_aioCashTransferCompleteUnitService.completeCashTransferCancel(l_aioOrderUnit);
//                    }
//
//                    //１.２.３） InstitutionImpl(InstitutionRow)
//                    //証券会社インスタンスを生成する。
//                    //[引数]
//                    //証券会社コード： 入出金テーブル.証券会社コード
//                    //===============NotfoundException===================
//                    Institution l_institution =
//                        l_accMgr.getInstitution(
//                            l_hostCashTransferParams.getInstitutionCode());
//
//                    //１.２.４)MainAccountImpl(MainAccountRow)
//                    //顧客インスタンスを生成する。
//                    //[引数]
//                    //証券会社ID： 証券会社.getInstitutionId()の戻り値
//                    //部店コード： 入出金テーブル.部店コード
//                    //顧客コード： 入出金テーブル.顧客コード
//                    //===============NotfoundException===================
//
//                    MainAccount l_mainAccount =
//                        l_accMgr.getMainAccount(
//                            l_institution.getInstitutionId(),
//                            l_hostCashTransferParams.getBranchCode(),
//                            l_hostCashTransferParams.getAccountCode());
//
//                    //=========remain zhou-yong 2004/12/7 NO.1 begin ===========
//                    //1.2.5) 余力再計算(補助口座 : 補助口座)
//                    //アイテムの定義
//                    //余力の更新をする。
//                    //[引数]
//                    //補助口座：　@補助口座オブジェクト
//                    WEB3TPTradingPowerService l_service =
//                        (WEB3TPTradingPowerService) Services.getService(
//                            WEB3TPTradingPowerService.class);
//                    SubAccount l_subAccount =
//                        l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//
//                    WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
//
//                    l_service.reCalcTradingPower(l_gentradeSubAccount);
                    // 李志強 U01832の暫定対応 end
                    //=========remain zhou-yong 2004/12/7 NO.1 end ===========
                }
                // 李志強 U01832の暫定対応 start
//                catch(WEB3BaseException l_ex)
//                {
//                    l_blnIsFail = true;
//                    m_log.debug("__an WEB3BaseException__ ", l_ex);
//                }
//                catch (NotFoundException l_ex)
//                {
//                    l_blnIsFail = true;
//                    m_log.debug("__an NotFoundException__ ", l_ex);
//                }
//                if (l_blnIsFail)
//                {
//                    //"9"（エラー）： 上記処理（Loop内の処理）で例外が発生した場合
//                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
//                }
//                else
//                {
//                    //処理区分の更新( "1"（処理済）： それ以外の場合)
//                    l_map.put("status", WEB3StatusDef.DEALT);
//                }
//
//                //１.２.７)メッセージ (*2) 入出金テーブルの処理区分の更新
//                // (*2)入出金テーブル.処理区分に以下の値をセットして更新する。
//                l_queryProcessor.doUpdateAllQuery(
//                    HostCashTransferRow.TYPE,
//                    l_strUpdateWhere,
//                    l_updateParams,
//                    l_map);

                catch(Exception l_ex)
                {
                    l_blnIsFail = true;
                    m_log.debug("__an Exception__ ", l_ex);
                }
                if (l_blnIsFail)
                {
                    //"9"（エラー）： 上記処理（Loop内の処理）で例外が発生した場合
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);

                    //１.２.７)メッセージ (*2) 入出金テーブルの処理区分の更新
                    // (*2)入出金テーブル.処理区分に以下の値をセットして更新する。
                    l_queryProcessor.doUpdateAllQuery(
                        HostCashTransferRow.TYPE,
                        l_strUpdateWhere,
                        l_updateParams,
                        l_map);
                }
                // 李志強 U01832の暫定対応 end
            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
          }

        // 李志強 U01832の暫定対応 start
//        /**
//         * (get注文単位)<BR>
//         * 入出金Paramsの内容に合致する注文単位を取得する。<BR>
//         * <BR>
//         * １）証券会社オブジェクトを取得する。<BR>
//         * <BR>
//         *   [コンストラクタに渡す引数]<BR>
//         *   入出金Params.証券会社コード<BR>
//         * <BR>
//         * ２）部店オブジェクトを取得する。<BR>
//         * <BR>
//         *   [コンストラクタに渡す引数]<BR>
//         *   証券会社オブジェクト<BR>
//         *   入出金Params.部店コード<BR>
//         * <BR>
//         * ３）顧客オブジェクトを取得する。<BR>
//         * <BR>
//         *   [コンストラクタに渡す引数]<BR>
//         *   証券会社.証券会社ID<BR>
//         *   部店.部店ID<BR>
//         *   入出金Params.顧客コード<BR>
//         * <BR>
//         * ４）補助口座オブジェクトを取得する。<BR>
//         * <BR>
//         *   [コンストラクタに渡す引数]<BR>
//         *   1（預り金口座）<BR>
//         * <BR>
//         * ５）以下の条件で、取消対象となる注文単位を検索する。<BR>
//         * <BR>
//         *    [検索条件]<BR>
//         *    口座ID： 口座.口座ID<BR>
//         *    補助口座ID： 補助口座.補助口座ID<BR>
//         *    部店ID： 部店.部店ID<BR>
//         *    注文種別： 入出金Params.区分='1'（出金）の場合、1001<BR>
//         *                  入出金Params.区分='2'（入金）の場合、1002<BR>
//         *    注文状態： 6”発注失敗（新規注文）”、14”発注済（取消注文）”以外 <BR>
//         *    数量： 入出金Params.金額 * -1<BR>
//         *    受渡日： 入出金Params.受渡日<BR>
//         * <BR>
//         * ６）取得した注文単位を返す。<BR>
//         * ※複数取得された場合は、注文単位.受注日時の一番古いものを返却する。
//         * <BR>
//         * @@param l_hostCashTransferParams - (入出金Params)<BR>
//         * 入出金Paramsオブジェクト<BR>
//         * @@return AioOrderUnit
//         * @@roseuid 41417583006F
//         */
//        protected AioOrderUnit getOrderUnit(HostCashTransferParams l_hostCashTransferParams)
//            throws WEB3BaseException
//        {
//            final String STR_METHOD_NAME =
//                 "getOrderUnit(HostCashTransferParams l_hostCashTransferParams)";
//            m_log.entering(STR_METHOD_NAME);
//
//            if (l_hostCashTransferParams == null)
//            {
//                log.debug("パラメータNull出来ない。");
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                    this.getClass().getName() + "." + STR_METHOD_NAME);
//            }
//
//            try
//            {
//                //アカウントマネージャ取得する
//                AccountManager l_accountManager = GtlUtils.getAccountManager();
//
//                //１）証券会社オブジェクトを取得する。
//                Institution l_institution =
//                    l_accountManager.getInstitution(
//                        l_hostCashTransferParams.getInstitutionCode());
//
//                //２）部店オブジェクトを取得する。
//                Branch l_branch =
//                    l_accountManager.getBranch(
//                        l_institution, l_hostCashTransferParams.getBranchCode());
//
//                //３）顧客オブジェクトを取得する。
//                MainAccount l_mainAccount =
//                    l_accountManager.getMainAccount(
//                        l_institution.getInstitutionId(),
//                        l_branch.getBranchId(),
//                        l_hostCashTransferParams.getAccountCode());
//
//
//                //４）補助口座オブジェクトを取得する。
//                SubAccount l_subAccount =
//                    l_mainAccount.getSubAccount(
//                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//
//                //５）以下の条件で、取消対象となる注文単位を検索する。
//                StringBuffer l_strWhere = new StringBuffer();
//                l_strWhere.append(" account_id = ?");
//                l_strWhere.append(" and sub_account_id = ?");
//                l_strWhere.append(" and branch_id = ?");
//                l_strWhere.append(" and order_type = ?");
//                //注文状態： 6”発注失敗（新規注文）”、14”発注済（取消注文）”以外
//                l_strWhere.append(" and order_status != " + OrderStatusEnum.NOT_ORDERED.intValue());
//                l_strWhere.append(" and order_status != " + OrderStatusEnum.CANCELLED.intValue());
//                l_strWhere.append(" and quantity = ?");
//                l_strWhere.append(" and delivery_date = ? ");
//
//                // 口座ID： 口座.口座IDを取得する
//                long l_lngAccountId =  l_subAccount.getAccountId();
//                //補助口座ID： 補助口座.補助口座ID
//                long l_lngSubAccountId = l_subAccount.getSubAccountId();
//                //部店ID： 部店.部店ID
//                long l_lngBranchId = l_branch.getBranchId();
//                //注文種別を取得する
//                OrderTypeEnum l_orderType = null;
//                //注文種別： 入出金Params.区分='1'（出金）の場合、1001（出金注文）をセットする。
//                if ((WEB3OrderDivDef.CASHOUT).equals((l_hostCashTransferParams.getOrderDiv())))
//                {
//                    l_orderType =  OrderTypeEnum.CASH_OUT;
//
//                }
//                else
//                {
//                    //入出金Params.入出金区分="2"（入金）の場合、
//                    //1002（入金注文）をセットする。
//                    if ((WEB3OrderDivDef.CASHIN).equals((l_hostCashTransferParams.getOrderDiv())))
//                    {
//                        l_orderType = OrderTypeEnum.CASH_IN;
//                    }
//                }
//
//                //数量を取得する
//                long l_lngQuantity = l_hostCashTransferParams.getAmount() * -1;
//                //受渡日を取得する： 入出金Params.受渡日
//                Timestamp l_tsDeliveryDate =
//                    l_hostCashTransferParams.getCashTransDate();
//                Object[] l_objMutualFrgncalWhere =
//                {
//                    new Long(l_lngAccountId),
//                    new Long(l_lngSubAccountId),
//                    new Long(l_lngBranchId),
//                    l_orderType,
//                    new Long(l_lngQuantity),
//                    l_tsDeliveryDate
//                };
//
//                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//
//                //検索
//                List l_lisAioOrderUnitRows =
//                    l_queryProcessor.doFindAllQuery(
//                        AioOrderUnitRow.TYPE,
//                        l_strWhere.toString(),
//                        " delivery_date ",
//                        null,
//                        l_objMutualFrgncalWhere);
//
//                if (l_lisAioOrderUnitRows == null || l_lisAioOrderUnitRows.isEmpty())
//                {
//                    log.debug("Error In 注文単位を検索する ");
//                    throw new WEB3SystemLayerException(
//                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                        this.getClass().getName() + "." + STR_METHOD_NAME);
//                }
//
//                //注文マネージャクラスを取得する。
//                AioOrderManager l_orderManager =
//                    (AioOrderManager) GtlUtils.getTradingModule(
//                        ProductTypeEnum.AIO).getOrderManager();
//
//                //注文単位オブジェクトを取得する
//                AioOrderUnitRow l_orderUnitRow =
//                   (AioOrderUnitRow) l_lisAioOrderUnitRows.get(0);
//                AioOrderUnit l_orderUnit =
//                    (AioOrderUnit) l_orderManager.toOrderUnit(l_orderUnitRow);
//
//                //６）取得した注文単位を返す。
//                m_log.exiting(STR_METHOD_NAME);
//                return l_orderUnit ;
//            }
//            catch (NotFoundException l_ex)
//            {
//                m_log.error("__NotFoundException__", l_ex);
//                m_log.exiting(STR_METHOD_NAME);
//                throw new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                    this.getClass().getName() + "." + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex);
//            }
//            catch (DataException l_ex)
//            {
//                m_log.error("__DataException__", l_ex);
//                m_log.exiting(STR_METHOD_NAME);
//                throw new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    this.getClass().getName() + "." + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex);
//            }
//        }
        // 李志強 U01832の暫定対応 end
    }
}@
