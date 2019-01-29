head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCallCancelNotifyTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知call取消通知TransactionCallback(WEB3EquityCallCancelNotifyTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 中尾寿彦(SRA) 新規作成
                 : 2005/01/05 岡村和明(SRA) 口座ロック対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptRow;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCancelEventService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyCancelUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式出来通知call取消通知TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3EquityCallCancelNotifyTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityCallCancelNotifyTransactionCallback.class);

    /**
      * 注文単位オブジェクト。<BR>
      */
    private EqTypeOrderUnit orderUnit;

    /**
      * 株式出来通知キューParamsオブジェクト。
      */
    private HostEquityOrderExecNotifyParams orderExecNotifyParams;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_orderUnit - (注文単位)
     * @@params l_orderExecNotifyParams - (株式出来通知キューParams)
     */
    public WEB3EquityCallCancelNotifyTransactionCallback(
        EqTypeOrderUnit l_orderUnit,
        HostEquityOrderExecNotifyParams l_orderExecNotifyParams)
    {
        orderUnit = l_orderUnit;
        orderExecNotifyParams = l_orderExecNotifyParams;
    }

    /**
     * トランザクション処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出来通知一件サービス）call取消通知処理」参照。<BR>
     * @@return Object
     * @@throws DataNetworkException, DataQueryException, DataCallbackException
     */
    public Object process()
        throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        HostEqtypeOrderClmdReceiptParams l_params = null;
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        String l_strStatus = null;
        try
        {
            // 1.5.1.1. get処理中取消通知キュー(株式出来通知キューParams)
            l_params = this.getCancelNotifyQueueParams(orderExecNotifyParams);
            
            if (l_params == null)
            {
                return null;
            }

            // 1.5.1.2. 注文単位＝現物株式の場合
            if (OrderCategEnum.ASSET.equals(orderUnit.getOrderCateg()))
            {
                WEB3EquityReceiveCancelEventService l_unitService =
                    (WEB3EquityReceiveCancelEventService)Services.getService(
                        WEB3EquityReceiveCancelEventService.class);
                l_strStatus = l_unitService.notifyCancel(l_params, orderUnit);
            }
            // 1.5.1.3. 注文単位＝信用取引の場合
            else if (OrderCategEnum.OPEN_MARGIN.equals(orderUnit.getOrderCateg()) ||
                      OrderCategEnum.CLOSE_MARGIN.equals(orderUnit.getOrderCateg()) ||
                      OrderCategEnum.SWAP_MARGIN.equals(orderUnit.getOrderCateg()))
            {
                WEB3MarginChangeCancelNotifyCancelUnitService l_unitService =
                    (WEB3MarginChangeCancelNotifyCancelUnitService)Services.getService(
                        WEB3MarginChangeCancelNotifyCancelUnitService.class);
                l_strStatus = l_unitService.notifyCancel(l_params, orderUnit);
            }
            // 上記以外
            else
            {
                String l_strMessage =
                    "株式注文単位.注文カテゴリ≠（\"現物注文\"or\"新規建注文\"or\"返済注文\"or\"現引・現渡注文\"）";
                log.error(l_strMessage);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80025,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMessage);
            }

            // 1.5.1.4. 株式訂正取消通知キュー.処理区分をupdateする
            // ○処理区分の更新値設定
            //   notify取消( )がエラーとなった場合："9：エラー"
            //   上記以外の場合：notify取消の戻り値
            l_params.setStatus(l_strStatus);
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_processor.doUpdateQuery(l_params);
        }
        catch (WEB3BaseException l_bex)
        {
            ErrorInfo l_errorInfo = l_bex.getErrorInfo();
            if (l_errorInfo.getErrorTag().startsWith("BUSINESS_ERROR"))
            {
                throw new DataCallbackException(
                    l_bex.getMessage(),
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01962,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_bex.getMessage(),
                        l_bex));
            }
            else
            {
                throw new DataCallbackException(
                    l_bex.getMessage(),
                    new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80078,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_bex.getMessage(),
                        l_bex));
            }
        }
        catch (Exception l_ex)
        {
            throw new DataCallbackException(
                l_ex.getMessage(),
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80078,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex));
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * （get処理中取消通知キュー）<BR>
     * <BR>
     * 【株式注文訂正取消通知キューテーブル】より、<BR>
     * 信用取引訂正取消通知取消一件サービスにより出来待ちとされた<BR>
     * 取消に対する「処理中」レコードを取得する。<BR>
     * <BR>
     * １）　@【株式注文訂正取消通知キューテーブル】より、以下の条件でデータを取得する。<BR>
     * 　@　@　@※select for update指定にて取得する。<BR>
     * <BR>
     * 　@　@＜抽出条件＞<BR>
     * 　@　@データコード＝”AI817”<BR>
     * 　@　@かつ　@証券会社コード＝引数の出来通知キュー.証券会社コード<BR>
     * 　@　@かつ　@部店コード＝引数の出来通知キュー.部店コード<BR>
     * 　@　@かつ　@顧客コード＝引数の出来通知キュー.顧客コード(*)<BR>
     * 　@　@かつ　@識別コード＝引数の出来通知キュー.識別コード<BR>
     * 　@　@かつ　@訂正取消通知区分＝”取消完了”<BR>
     * 　@　@かつ　@処理区分＝”処理中”<BR>
     * <BR>
     * 　@　@(*)ダイレクトリンクによる出来データの場合、顧客コードには（0 or null）が設定されてくる。<BR>
     * 　@　@　@　@この場合は、this.注文単位.口座IDに該当する顧客.口座コードを代わりに使用する。<BR>
     * <BR>
     * ２）　@該当するデータが存在する場合は、取得した株式注文訂正取消通知キューParamsを返す。<BR>
     * 　@　@　@該当するデータが存在しない場合は、nullを返す。<BR>
     * <BR>
     * @@param l_execNotifyQueParams - (出来通知キュー)<BR>
     * 【株式出来通知キューテーブル】の１レコード。
     * @@return HostEqtypeOrderClmdReceiptParams
     * @@throws WEB3BaseException
     */
    protected HostEqtypeOrderClmdReceiptParams getCancelNotifyQueueParams(
        HostEquityOrderExecNotifyParams l_execNotifyQueParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCancelNotifyQueueParams(HostEquityOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        // １）　@【株式注文訂正取消通知キューテーブル】より、以下の条件でデータを取得する。
        // 　@　@＜抽出条件＞
        // 　@　@データコード＝”AI817”
        // 　@　@かつ　@証券会社コード＝引数の出来通知キュー.証券会社コード
        // 　@　@かつ　@部店コード＝引数の出来通知キュー.部店コード
        // 　@　@かつ　@顧客コード＝引数の出来通知キュー.顧客コード
        // 　@　@かつ　@識別コード＝引数の出来通知キュー.識別コード
        // 　@　@かつ　@訂正取消通知区分＝”取消完了”
        // 　@　@かつ　@処理区分＝”処理中”
        String l_strWhere = " request_code = ? "
                          + " and institution_code = ? "
                          + " and branch_code = ? "
                          + " and account_code = ? "
                          + " and order_request_number = ? "
                          + " and canmod_receipt_type = ? "
                          + " and status = ? ";
        String l_strAccountCode = l_execNotifyQueParams.getAccountCode();
        if (l_strAccountCode == null ||
            Integer.parseInt(l_strAccountCode.trim()) == 0)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                MainAccount l_account =
                    l_accountManager.getMainAccount(this.orderUnit.getAccountId());
                l_strAccountCode = l_account.getAccountCode();
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        Object[] l_objWhere =
          { WEB3HostRequestCodeDef.EQUITY_CHANGE_CANCEL_NOTICE,
            l_execNotifyQueParams.getInstitutionCode(),
            l_execNotifyQueParams.getBranchCode(),
            l_strAccountCode,
            l_execNotifyQueParams.getOrderRequestNumber(),
            WEB3CanmodReceiptTypeDef.CANCEL,
            WEB3StatusDef.DEALING };

        log.debug("===================検索条件開始=====================");
        log.debug("証券会社コード: [" + l_execNotifyQueParams.getInstitutionCode() + "]");
        log.debug("    部店コード: [" + l_execNotifyQueParams.getBranchCode() + "]");
        log.debug("    顧客コード: [" + l_strAccountCode + "]");
        log.debug("    識別コード: [" + l_execNotifyQueParams.getOrderRequestNumber() + "]");
        log.debug("===================検索条件終了=====================");

        // ２）　@該当するデータが存在する場合は、取得した株式注文訂正取消通知キューParamsを返す。
        // 　@　@　@該当するデータが存在しない場合は、nullを返す。
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisSearchResult =
                l_QueryProcessor.doFindAllQuery(
                    HostEqtypeOrderClmdReceiptRow.TYPE,
                    l_strWhere,
                    null,
                    "for update",
                    l_objWhere);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        int l_intNum = 0;
        if (l_lisSearchResult != null)
        {
            l_intNum = l_lisSearchResult.size();
        }
        log.debug("株式注文訂正取消通知キューの件数 : [" + l_intNum + "]");
        HostEqtypeOrderClmdReceiptParams l_params = null;
        if (l_intNum > 0)
        { 
            l_params = (HostEqtypeOrderClmdReceiptParams)l_lisSearchResult.get(0);
        }

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
