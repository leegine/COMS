head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 取引明細サービスImpl(WEB3HistoryTradeDetailServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 王敏 (中訊) 新規作成
                   2006/10/19  張騰宇 (中訊) モデル 057
                   2006/10/26  張騰宇 (中訊) モデル 064
*/

package webbroker3.tradehistory.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.tradehistory.data.TradeDetailHistoryParams;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeDetailService;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;


/**
 * (取引明細サービスImpl)<BR>
 * 取引明細サービス実装クラス<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3HistoryTradeDetailServiceImpl extends WEB3ClientRequestService implements WEB3HistoryTradeDetailService 
{
    /**
      * ログ出力ユーティリティ。<BR>
      */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistoryTradeDetailServiceImpl.class);      
    /**
     * @@roseuid 41789C47004E
     */
    public WEB3HistoryTradeDetailServiceImpl() 
    {
     
    }
    
    /**
     * 取引明細画面表示処理を行う。<BR>
     * <BR>
     * パラメータのリクエストデータを取引明細リクエストにキャストして<BR>
     * get取引明細画面()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413D975A018A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3HistoryTradeDetailRequest)
        {
            l_response = this.getTradeDetailScreen((WEB3HistoryTradeDetailRequest)l_request);
        
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                                      WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                       STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get取引明細画面)<BR>
     * 取引明細画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(取引明細サービス)get取引明細画面」参照<BR>
     *  <BR>
     *  =============================================== <BR>
     *         シーケンス図 :  「(取引明細サービス)get取引明細画面」<BR>
     *         具体位置    :  1.5 is取報実施(顧客 : 顧客)  <BR>
     *         falseが返却された場合は、<BR>
     *         「取引報告書未実施顧客エラー」の例外をスローする。<BR>
     *         class         :  WEB3BusinessLayerException<BR>
     *         tag            :  BUSINESS_ERROR_01059         <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 取引明細リクエストオブジェクト<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse
     * @@roseuid 413FB0410158
     */
    protected WEB3HistoryTradeDetailResponse getTradeDetailScreen(WEB3HistoryTradeDetailRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1. validate()
        l_request.validate();

        //1.2. get補助口座(補助口座タイプ : SubAccountTypeEnum)
        SubAccount l_subAccount =this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3. getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4. validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5. getTradeDetailHistory(顧客, String)
        TradeDetailHistoryParams  l_tradeDetailHistoryParams = 
            WEB3HistoryTradeHistoryDataManager.getTradeDetailHistory(
                l_request.detailsManageNo);
        if (l_tradeDetailHistoryParams == null)
        {
            log.error("データ不整合エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.createResponse()
        WEB3HistoryTradeDetailResponse l_response = 
            (WEB3HistoryTradeDetailResponse)l_request.createResponse();

        //1.7.プロパティセット
        //レスポンス.翻訳摘要名	＝　@リクエスト.翻訳摘要名
        l_response.remarkName = l_request.remarkName;
        //レスポンス.銘柄コード	＝　@取引明細履歴Params.銘柄コード
        l_response.productCode = l_tradeDetailHistoryParams.getProductCode();
        //レスポンス.銘柄名		＝　@リクエスト.銘柄名
        l_response.productName = l_request.productName;
        //レスポンス.市場区分		＝　@取引明細履歴Params.市場区分
        l_response.marketDiv = l_tradeDetailHistoryParams.getMarketDiv();
        //レスポンス.数量		＝　@取引明細履歴Params.売買数量
        l_response.quantity =WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getQuantity());
        //レスポンス.単価		＝　@取引明細履歴Params.約定単価
        l_response.price =WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getPrice());
        //レスポンス.口座区分		＝　@取引明細履歴Params.口座区分
        l_response.taxType = l_tradeDetailHistoryParams.getAccountDiv();
        //レスポンス.約定日		＝　@取引明細履歴Params.約定日
        l_response.execDate = WEB3DateUtility.toDay(l_tradeDetailHistoryParams.getExecDate());
        //レスポンス.受渡日		＝　@取引明細履歴Params.受渡日
        l_response.deliveryDate = WEB3DateUtility.toDay(l_tradeDetailHistoryParams.getDeliveryDate());
        //レスポンス.約定金額		＝　@取引明細履歴Params.約定金額
        l_response.execAmount = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getExecutedAmount());
        //レスポンス.手数料		＝　@取引明細履歴Params.手数料
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getCommissionFee());
        //レスポンス.手数料消費税	＝　@取引明細履歴Params.手数料消費税
        l_response.commissionTax = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getCommissionFeeTax());
        //レスポンス.受渡金額		＝　@取引明細履歴Params.受渡金額
        l_response.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getNetAmount());

        //以下追加
        //レスポンス.商品コード     ＝　@取引明細履歴Params.商品コード
        String l_strCommodityCode = l_tradeDetailHistoryParams.getCommodityCode();
        l_response.commodityCode = l_strCommodityCode;

        //レスポンス.通貨単位      ＝　@取引明細履歴Params.通貨単位
        l_response.monetaryUnit = l_tradeDetailHistoryParams.getMonetaryUnit();

        //レスポンス.市場区分（外株）  ＝　@取引明細履歴Params.市場区分（外株）
        l_response.fstkExchDiv = l_tradeDetailHistoryParams.getFstkExchDiv();

        //レスポンス.約定金額（外貨）  ＝　@取引明細履歴Params.約定金額（外貨））
        l_response.monetaryUnitExecutedAmount =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitExecutedAmount());

        //レスポンス.現地手数料（外貨） ＝　@取引明細履歴Params.現地手数料（外貨）
        l_response.monetaryUnitComission =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitComission());

        //レスポンス.現地取引税（外貨） ＝　@取引明細履歴Params.現地取引税（外貨）
        l_response.monetaryUnitTradeTax =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitTradeTax());

        //レスポンス.その他手数料（外貨）    ＝　@取引明細履歴Params.その他手数料（外貨）
        l_response.monetaryUnitInterest =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getMonetaryUnitInterest());

        //レスポンス.現地受渡代金（外貨）＝　@取引明細履歴Params.現地受渡代金（外貨）
        l_response.localSettleAmount =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getLocalSettleAmount());

        //レスポンス.現地受渡代金（円貨）＝　@取引明細履歴Params.現地受渡代金（円貨）
        l_response.localSettleAmountYen =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getLocalSettleAmountYen());

        //レスポンス.約定為替      ＝　@取引明細履歴Params.約定為替
        l_response.execExchange =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getExecExchange());

        //レスポンス.国内手数料（円貨） ＝　@取引明細履歴Params.国内手数料（円貨）
        l_response.domesticCommission =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getDomesticCommission());

        //レスポンス.額面
        l_response.faceAmount =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getDenomination());

        //レスポンス.決済区分
        l_response.settleDiv = l_tradeDetailHistoryParams.getPaymentDiv();

        //レスポンス.経過利子(円貨)
        l_response.yenAccruedInterest =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getAccruedInterestYen());

        //レスポンス.経過利子(外貨)
        l_response.foreignAccruedInterest =
            WEB3StringTypeUtility.formatNumber(l_tradeDetailHistoryParams.getAccruedInterest());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
