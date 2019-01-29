head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文通知サービスImpl(WEB3EquityOrderNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 欒学峰(中訊) 作成
Revesion History : 2004/12/13 岡村和明(SAR) 残案件対応 Ｎｏ.０４５＆Ｎｏ.０４７＆Ｎｏ.０４８＆Ｎｏ.０５１＆Ｎｏ.０６２
                                                     ＆Ｎｏ.１４７＆Ｎｏ.１５３＆Ｎｏ.２３２＆Ｎｏ.２３９＆Ｎｏ.２７９
                                                     ＆Ｎｏ.３０４＆Ｎｏ.３５４
Revesion History : 2005/01/05 岡村和明(SRA) 口座ロックの対応
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2007/04/17 趙林鵬 (中訊) モデル 1139
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Iterator;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.message.WEB3EquityOrderNotifyRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式注文通知サービスImpl） 。<BR>
 * <BR>
 * 株式注文通知ユースケースのエントリポイント。<BR>
 * キューテーブルからのデータ取得、及び <BR>
 * 現物株式用と信用取引用の <BR>
 * 登録処理を行うサブサービスの呼び分けを行う。
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyServiceImpl implements WEB3EquityOrderNotifyService
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderNotifyServiceImpl.class);

    /**
     * @@roseuid 40B3EE9800AB
     */
    public WEB3EquityOrderNotifyServiceImpl()
    {

    }

    /**
     * （execute）。<BR>
     * <BR>
     * 引数で与えられたリクエストを基に業務処理を行い、<BR>
     * 処理結果をレスポンスに設定してを返す。
     * @@param l_request リクエスト
     * @@return 処理結果が設定されたレスポンス
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4021F50103E3
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderNotifyRequest l_eqOrderAcceptRequest =
            (WEB3EquityOrderNotifyRequest)l_request;

        // 1.1 validate( )
        l_eqOrderAcceptRequest.validate();

        try
        {
            // 1.2 getDefaultProcessor()
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            // 1.3 株式注文通知TransactionCallback()
            WEB3EquityOrderNotifyServiceTransactionCallback l_orderNotifyServiceTransactionCallback =
                new WEB3EquityOrderNotifyServiceTransactionCallback();

            // 1.4 set識別コードプレフィクス一覧()
            l_orderNotifyServiceTransactionCallback.setOrderRequestNumberPrefixGroup(
                l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);

            // 1.5 doTransaction()
            l_qp.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_orderNotifyServiceTransactionCallback);
        }
        catch (DataCallbackException l_dce)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
            throw l_wbe;
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_eqOrderAcceptRequest.createResponse();
    }

    /**
     * （株式注文通知TransactionCallback）。<BR>
     * <BR>
     * 株式注文通知TransactionCallback<BR>
     * トランザクション処理を実施する内部クラス
     * @@author 岡村和明(SRA)
     * @@version 1.0
     */
    public class WEB3EquityOrderNotifyServiceTransactionCallback
        implements TransactionCallback
    {

        /**
         * 識別コードプレフィクス一覧
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * @@roseuid 40B3F2C600AB
         */
        public WEB3EquityOrderNotifyServiceTransactionCallback()
        {

        }

        /**
         * （process）。<BR>
         * <br>
         * 注文通知処理を実施する。<BR>
         * <br>
         * シーケンス図<br>
         * 「（株式注文通知サービス）process」参照<BR>
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40B3F1530148
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";

            log.entering(STR_METHOD_NAME);

            // 1.1 get入力通知キュー( )
            List l_lisHostEqtypeOrderNotify = getHostEqtypeOrderReceipt();


            if (l_lisHostEqtypeOrderNotify != null)
            {
                Iterator l_iterator = l_lisHostEqtypeOrderNotify.iterator();

                // 1.2 取得したキューテーブルのレコード数分Loop処理
                while (l_iterator.hasNext())
                {
                    HostEqtypeOrderReceiptParams l_params =
                        (HostEqtypeOrderReceiptParams)l_iterator.next();
                    QueryProcessor l_qp = Processors.getDefaultProcessor();

                    //1.2.1get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
                    //証券会社コード：　@株式注文入力通知キューParams.証券会社コード
                    //部店コード：　@　@　@　@株式注文入力通知キューParams.部店コード
                    //口座コード：　@　@　@　@株式注文入力通知キューParams口座コー
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                    WEB3GentradeAccountManager l_accountMgr =
                        (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                    try
                    {
                        l_accountMgr.getMainAccount(
                            l_params.getInstitutionCode(),
                            l_params.getBranchCode(),
                            l_params.getAccountCode());
                    }
                    catch(WEB3BaseException l_ex)
                    {
                        log.debug(l_ex.getMessage(), l_ex);
                        l_params.setStatus(WEB3StatusDef.DEALT);
                        l_params.setLastUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
                        l_qp.doUpdateQuery(l_params);
                        continue;
                    }

                    // 1.2.1 create(株式注文入力通知キューParams)
                    // 株式注文入力通知データアダプタをcreateする。
                    // 引数は以下の通りに設定する。
                    // 株式注文入力通知キューParams：　@処理対象【株式注文入力通知キューテーブル】１レコード
                    WEB3EquityOrderInputNotifyAdapter l_adapter =
                        WEB3EquityOrderInputNotifyAdapter.create(l_params);

                    try
                    {
                        // 1.2.2 メッセージ 通知一件処理(株式注文入力通知データアダプタ : 株式注文入力通知データアダプタ)
                        WEB3EquityOrderNotifyUnitService l_service =
                            (WEB3EquityOrderNotifyUnitService)Services.getService(
                                WEB3EquityOrderNotifyUnitService.class);
                        l_service.notifyPartProcess(l_adapter);

                        // 処理対象キューレコードの処理区分をupdate
                        //一件処理内部へ移す.start
                        //log.debug("1.3.2 株式注文入力通知キューテーブル.処理区分をupdateする");
                        //l_params.setStatus(WEB3StatusDef.DEALT);
                        //l_qp.doUpdateQuery(l_params);
                        //.end
                    }
                    catch (Exception l_exp)
                    {
                        log.error("一件処理にてエラー発生：", l_exp);
                        l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_qp.doUpdateQuery(l_params);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (get入力通知キュー)<BR>
         * <BR>
         * １）　@【株式注文入力通知キューテーブル】から、<BR>
         * 　@　@以下の条件に合致する全データを SELECT FOR UPDATE（行ロック）で取得し、<BR>
         * 　@　@「株式注文入力通知キューテーブルParams」のリストを作成する。<BR>
         * <BR>
         * 　@　@　@　@　@データコード＝"AI821"(株式注文入力通知)"<BR>
         * 　@　@かつ　@取引コード（SONAR）＝（"通常取引（上場銘柄）"、"立会外分売"） <BR>
         * 　@　@かつ　@識別コードの先頭２桁が、get識別コードプレフィクス一覧( )の戻り値配列のいずれかと一致<BR>
         * 　@　@かつ　@処理区分＝"未処理"<BR>
         * <BR>
         * ２）　@「株式注文入力通知キューテーブルParams」のListを返す。<BR>
         * @@return List
         * @@throws DataQueryException
         * @@throws DataNetworkException
         * @@throws DataCallbackException
         * @@roseuid 402711F301E2
         */
        public List getHostEqtypeOrderReceipt() throws DataQueryException, DataNetworkException, DataCallbackException
        {
            final String STR_METHOD_NAME = "getHostEqtypeOrderReceipt()";
            log.entering(STR_METHOD_NAME);

            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
            
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                    WEB3EquityOrderNotifyRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("(sonar_traded_code = ? or sonar_traded_code = ?)");
            l_sbWhere.append(" and status = ?");
            int l_intRequestNumberPrefixGroupCnt = 0;
            if (l_orderRequestNumberPrefixGroup != null)
            {
                l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;
            }
            if (l_intRequestNumberPrefixGroupCnt > 0)
            {
                l_sbWhere.append(" and (order_request_number like ?");
                for (int i = 1; i < l_intRequestNumberPrefixGroupCnt ; i++)
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_sbWhere.append(")");
            }
            int l_intRequestCodesForReadListCnt = 0;
            if (l_requestCodesForReadList != null)
            {
                l_intRequestCodesForReadListCnt = l_requestCodesForReadList.length;
            }
            if (l_intRequestCodesForReadListCnt > 0)
            {
                l_sbWhere.append(" and (request_code = ?");
                for (int i = 1; i < l_intRequestCodesForReadListCnt; i++)
                {
                    l_sbWhere.append(" or request_code = ?");
                }
                l_sbWhere.append(")");
            }
            log.debug("検索条件文字列:[" + l_sbWhere.toString() + "]");
            Object[] l_objWhere = new Object[l_intRequestNumberPrefixGroupCnt + l_intRequestCodesForReadListCnt + 3];
            l_objWhere[0] = WEB3TransactionTypeSONARDef.MARKET_TRADING;
            l_objWhere[1] = WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
            l_objWhere[2] = WEB3StatusDef.NOT_DEAL;
            int l_intSize = 3;
            for (int i = 0; i < l_intRequestNumberPrefixGroupCnt; i++)
            {
                l_objWhere[l_intSize++] = l_orderRequestNumberPrefixGroup[i] + "%";
                log.debug("識別コードプレフィクス一覧[" + i + "]：[" + l_orderRequestNumberPrefixGroup[i] + "%]");
            }
            for (int i = 0; i < l_intRequestCodesForReadListCnt; i++)
            {
                l_objWhere[l_intSize++] = l_requestCodesForReadList[i];
                log.debug("処理対象データコード一覧[" + i + "]：[" + l_requestCodesForReadList[i] + "]");
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisHostEqtypeOrderAccept =
                l_qp.doFindAllQuery(
                    HostEqtypeOrderReceiptParams.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);

            log.exiting(STR_METHOD_NAME);
            return l_lisHostEqtypeOrderAccept;
        }

        /**
         * (get識別コードプレフィクス一覧)<BR>
         * <BR>
         * 識別コードプレフィクス一覧を取得する。
         * @@return 識別コードプレフィクス一覧
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * (set識別コードプレフィクス一覧)<BR>
         * <BR>
         * 引数の識別コードプレフィクス一覧をプロパティにセットする。
         * @@param orderRequestNumberPrefixGroup 識別コードプレフィクス一覧
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
        {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }
    }
}
@
