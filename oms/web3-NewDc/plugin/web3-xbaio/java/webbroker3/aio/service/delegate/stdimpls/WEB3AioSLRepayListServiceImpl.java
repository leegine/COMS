head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済一覧サービスImpl(WEB3AioSLRepayListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 仕様変更・モデルNo.757,775,791,794
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.define.WEB3AioCancelEnableFlagDef;
import webbroker3.aio.message.WEB3SLRepayCancelListRequest;
import webbroker3.aio.message.WEB3SLRepayCancelListResponse;
import webbroker3.aio.message.WEB3SLRepayUnit;
import webbroker3.aio.service.delegate.WEB3AioSLRepayListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証券担保ローン返済一覧サービスImpl)<BR>
 * 証券担保ローン返済一覧サービス実装クラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSLRepayListServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLRepayListService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayListServiceImpl.class);

    /**
     * @@roseuid 46E8908400D7
     */
    public WEB3AioSLRepayListServiceImpl()
    {

    }

    /**
     * 証券担保ローン返済一覧サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券担保ローン返済一覧）一覧画面表示データ取得」参照。<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済一覧サービス）execute<BR>
     * 具体位置：is証券担保ローン口座開設( )<BR>
     * 　@　@戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@　@BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@roseuid 46DE36D90377
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3SLRepayCancelListRequest l_slRepayCancelListRequest =
            (WEB3SLRepayCancelListRequest)l_request;
        // 証券担保ローン返済一覧サービス処理を実施する。
        // 「（証券担保ローン返済一覧）一覧画面表示データ取得」参照。
        // get補助口座(補助口座タイプ : SubAccountTypeEnum
        // 補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //  validate注文(SubAccount)
        // 補助口座： get補助口座()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.validateOrder(l_subAccount);

        // getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // is証券担保ローン口座を開設しているかチェックする。
        boolean l_blnIsSecuredLoanAccountOpen = l_mainAccount.isSecuredLoanAccountOpen();

        // 戻り値 == false の場合、例外をスローする。
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            log.debug("証券担保ローン口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券担保ローン口座が未開設です。");
        }

        // get発注日( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        // 補助口座.getSubAccountId()の戻り値
        // 1020（振替注文（預かり金からオリックスクレジット））
        Long l_subAccountId = new Long(l_subAccount.getSubAccountId());
        Object[] l_objValues = new Object[]{l_subAccountId, OrderTypeEnum.TO_ORIX_CREDIT};

        // doFindAllQuery(Rowタイプ : String, Where : String, orderBy : String, condition : String, リスト : Object[])
        // Rowタイプ： AioOrderUnitRow.TYPE
        // Where： "sub_account_id=? and order_type=?"
        // orderBy： "received_date_time"
        // condition： null
        // リスト： 以下の項目のリスト
        List l_lisAioOrderUnitRows = null;
        try
        {
            // getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisAioOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    "sub_account_id=? and order_type=?",
                    "received_date_time",
                    null,
                    l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ArrayList
        List l_lisResults = new ArrayList();

        // 注文単位Paramsの要素毎にLoop
        int l_intLength = l_lisAioOrderUnitRows.size();
        for (int i = 0; i < l_intLength; i++)
        {
            // 証券担保ローン返済明細( )
            // 証券担保ローン返済明細インスタンスを生成する。
            WEB3SLRepayUnit l_slRepayUnit = new WEB3SLRepayUnit();

            // get取消可能フラグ(注文単位Params, Date)
            // 注文単位Params： 注文単位Params
            // 発注日： get発注日( )の戻り値
            AioOrderUnitParams l_aioOrderUnitParams =
                (AioOrderUnitParams)l_lisAioOrderUnitRows.get(i);
            String l_strCancelPossibleFlag =
                this.getCancelEnableFlag(l_aioOrderUnitParams, l_datOrderBizDate);

            // プロパティセット
            // 証券担保ローン返済明細.注文ID = 注文単位Params.注文ID
            l_slRepayUnit.orderId =
                WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getOrderId());
            // 証券担保ローン返済明細.受付日時 = 注文単位Params.受注日時
            l_slRepayUnit.receptionDate = l_aioOrderUnitParams.getReceivedDateTime();
            // 証券担保ローン返済明細.返済予定日 = .注文単位Params.振替予定日
            l_slRepayUnit.repayScheduledDate = l_aioOrderUnitParams.getEstTransferDate();
            // 証券担保ローン返済明細.返済額 = 注文単位Params.数量
            l_slRepayUnit.repayAmt =
                WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getQuantity());
            // 証券担保ローン取消可能フラグ = get取消可能フラグ()の戻り値
            l_slRepayUnit.cancelFlag = l_strCancelPossibleFlag;

            // add(arg0 : Object)
            l_lisResults.add(l_slRepayUnit);
        }

        // toArray( )
        WEB3SLRepayUnit[] l_slRepayUnits = new WEB3SLRepayUnit[l_lisResults.size()];
        l_lisResults.toArray(l_slRepayUnits);

        // createResponse( )
        WEB3SLRepayCancelListResponse l_slRepayCancelListResponse =
            (WEB3SLRepayCancelListResponse)l_slRepayCancelListRequest.createResponse();

        // プロパティセット
        // レスポンス.証券担保ローン返済明細 = 証券担保ローン返済明細リスト
        l_slRepayCancelListResponse.stockLoanRepayUnits = l_slRepayUnits;

        log.exiting(STR_METHOD_NAME);
        return l_slRepayCancelListResponse;
    }

    /**
     * (get取消可能フラグ)<BR>
     * 取消可能かどうかの状態を取得する。<BR>
     * <BR>
     * １）引数.注文単位Params.MQステータス ＝ 0:未送信の場合、<BR>
     * 　@　@１－１）引数.注文単位Params.注文状態 = 14:発注済（取消注文） and<BR>
     * 　@　@　@　@　@　@引数.注文単位Params.注文有効状態 = 2:クローズ の場合、<BR>
     *    　@　@　@ 　@　@3：取消済みを返却する。<BR>
     * 　@　@１－２）１－１）以外で、引数.発注日 ＞ 引数.注文単位Params.発注日の場合、<BR>
     *     　@　@　@　@　@0：取消不可を返却する。<BR>
     * 　@　@１－３）１－１）、１－２）以外の場合、<BR>
     *     　@　@　@　@　@1：取消可能を返却する。<BR>
     * <BR>
     * ２）引数.注文単位Params.MQステータス ＝ 1:送信済みの場合、<BR>
     *     　@　@　@　@　@2：送信済みを返却する。<BR>
     * <BR>
     * ３）１）、２）以外の場合、<BR>
     *     　@　@　@　@　@4：送信エラーを返却する。<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (注文単位Params)<BR>
     * 注文単位Params<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 直近振込日<BR>
     * @@return String
     * @@roseuid 46E48EEF022F
     */
    private String getCancelEnableFlag(AioOrderUnitParams l_aioOrderUnitParams, Date l_datOrderBizDate)
    {
        final String STR_METHOD_NAME = "getCancelPossibleFlag(AioOrderUnitParams, Date)";
        log.entering(STR_METHOD_NAME);

        String l_strCancelPossibleFlag = null;
        // 引数.注文単位Params.発注日
        Date l_datBizDate = WEB3DateUtility.getDate(
            l_aioOrderUnitParams.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        // 取消可能かどうかの状態を取得する。
        // １）引数.注文単位Params.MQステータス ＝ 0:未送信の場合、
        if (WEB3MqStatusDef.NOT_SEND_MAIL.equals(l_aioOrderUnitParams.getMqStatus()))
        {
            // 引数.注文単位Params.注文状態 = 14:発注済（取消注文） and
            // 引数.注文単位Params.注文有効状態 = 2:クローズ の場合、
            if (OrderStatusEnum.CANCELLED.equals(l_aioOrderUnitParams.getOrderStatus()) &&
                OrderOpenStatusEnum.CLOSED.equals(l_aioOrderUnitParams.getOrderOpenStatus()))
            {
                // 3：取消済みを返却する。
                l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.CANCElED;
                log.exiting(STR_METHOD_NAME);
                return l_strCancelPossibleFlag;
            }
            // １－２）１－１）以外で、引数.発注日 ＞ 引数.注文単位Params.発注日 の場合、
            // 0：取消不可を返却する。
            else if (WEB3DateUtility.compare(l_datOrderBizDate, l_datBizDate) > 0)
            {
                // 0：取消不可を返却する。
                l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.CANCEL_NOT;
                log.exiting(STR_METHOD_NAME);
                return l_strCancelPossibleFlag;
            }
            // １－３）１－１）、１－２）以外の場合、
            else
            {
                // 1：取消可能を返却する。
                l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.CANCEL_POSSIBLE;
                log.exiting(STR_METHOD_NAME);
                return l_strCancelPossibleFlag;
            }
        }
        // ２）引数.注文単位Params.MQステータス ＝ 1:送信済みの場合、
        else if (WEB3MqStatusDef.MAIL_SENDED.equals(l_aioOrderUnitParams.getMqStatus()))
        {
            // 2：送信済みを返却する。
            l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.SENDED;
            log.exiting(STR_METHOD_NAME);
            return l_strCancelPossibleFlag;
        }
        // ３）１）、２）以外の場合、
        else
        {
            // 4：送信エラーを返却する。
            l_strCancelPossibleFlag = WEB3AioCancelEnableFlagDef.ERROR;
            log.exiting(STR_METHOD_NAME);
            return l_strCancelPossibleFlag;
        }
    }
}
@
