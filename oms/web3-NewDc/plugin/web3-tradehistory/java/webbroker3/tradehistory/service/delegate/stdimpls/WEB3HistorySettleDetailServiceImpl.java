head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済明細サービス実装クラス(WEB3HistorySettleDetailService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.data.SettleDetailHistoryParams;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailRequest;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistorySettleDetailService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (決済明細サービスImpl)<BR>
 * 決済明細サービス実装クラス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0
 */
public class WEB3HistorySettleDetailServiceImpl extends WEB3ClientRequestService implements WEB3HistorySettleDetailService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistorySettleDetailServiceImpl.class);      

    /**
     * @@roseuid 41789C4801B5
     */
    public WEB3HistorySettleDetailServiceImpl() 
    {
     
    }
    
    /**
     * 決済明細表示処理を行う。<BR>
     * <BR>
     * パラメータのリクエストデータを決済明細リクエストにキャストして<BR>
     * get決済明細画面()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413EBAB1033F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if(l_request instanceof WEB3HistorySettleDetailRequest)
        {
            
            l_response = this.getSettleDetailScreen((WEB3HistorySettleDetailRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get決済明細画面)<BR>
     * 決済明細画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(決済明細サービス)get決済明細画面」参照<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  「(決済明細サービス)get決済明細画面」<BR>
     *         具体位置    :  1.5 is取報実施(顧客 : 顧客) <BR>
     *         falseが返却された場合は、<BR>
     *         「取引報告書未実施顧客エラー」の<BR>
     *         例外をスローする。  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_01059           <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 決済明細リクエストオブジェクト<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse
     * @@roseuid 413FB0F9033C
     */
    protected WEB3HistorySettleDetailResponse getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleDetailScreen(WEB3HistorySettleDetailRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();

        //1.2 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 get決済明細履歴(顧客, String)        
        SettleDetailHistoryParams l_settleDetailHistoryParams = 
            WEB3HistoryTradeHistoryDataManager.getSettleDetailHistory(
                l_request.detailsManageNo);
        if (l_settleDetailHistoryParams == null)
        {
            log.error("データ不整合エラー");
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);  
        }
     
        //1.6 createResponse()
        WEB3HistorySettleDetailResponse l_response = 
            (WEB3HistorySettleDetailResponse)l_request.createResponse();

        //1.7 レスポンスデータにプロパティをセットする
        l_response.remarkName = l_request.remarkName;
        l_response.productCode = l_settleDetailHistoryParams.getProductCode();
        l_response.productName = l_request.productName;
        l_response.marketDiv = l_settleDetailHistoryParams.getMarketDiv();
        l_response.quantity = l_request.quantity;
        l_response.taxType = l_settleDetailHistoryParams.getAccountDiv();
        l_response.repaymentDiv = l_settleDetailHistoryParams.getRepaymentType();
        l_response.openExecDate = WEB3DateUtility.toDay(l_settleDetailHistoryParams.getOpenExecDate());
        l_response.closeExecDate = WEB3DateUtility.toDay(l_settleDetailHistoryParams.getCloseExecDate());
        l_response.contractPrice = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getContractPrice());
        l_response.closeContractPrice = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseContractPrice());
        l_response.deliveryDate = WEB3DateUtility.toDay(l_settleDetailHistoryParams.getDeliveryDate());
        l_response.contractAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getContractAmount());
        l_response.closeContractAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseContractAmount());
        l_response.openCommission = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getOpenCommission());
        l_response.closeCommission = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseCommission());
        l_response.openCommissionTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getOpenCommissionTax());
        l_response.closeCommissionTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCloseCommissionTax());
        l_response.managementFee = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getManagementFee());
        l_response.managementFeeTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getManagementFeeTax());
        l_response.nameTransferFee = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getNameTransferFee());
        l_response.nameTransferFeeTax = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getNameTransferFeeTax());
        
        if (l_settleDetailHistoryParams.getLoanEquityFeeIsNull())
        {
            l_response.loanEquityFee = null;
        }
        else
        {
            l_response.loanEquityFee = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getLoanEquityFee());   
        }        
        l_response.debitDailyInterest = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getDebitDailyInterest());
        l_response.creditDailyInterest = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getCreditDailyInterest());
        l_response.dividendAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getDividendAmount());
        l_response.adjustAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getAdjustAmount());
        l_response.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_settleDetailHistoryParams.getNetAmount());
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
