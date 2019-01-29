head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信売買注文通知UnitServiceImpl(WEB3MutualTradeOrderNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
Revesion History : 2004/08/24 王蘭芬 (中訊) レビュー
Revesion History : 2004/12/10 黄建 (中訊) 残対応
Revesion History : 2005/11/18 黄建 (中訊) フィデリティ対応
Revesion History : 2007/01/05 松本 (SRA) 仕様変更 No519
Revesion History : 2007/01/15 車進 (中訊) 仕様変更 No520（本番障害票：H00139）
Revesion History : 2007/12/21 馮海濤 (中訊) 仕様変更 No587
Revesion History : 2007/12/26 武波 (中訊) 仕様変更 No588
Revesion History : 2008/04/28 武波 (中訊) 仕様変更 No599
Revesion History : 2008/05/12 武波 (中訊) 仕様変更 No603
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AvailableDatacodeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3InputUnitDef;
import webbroker3.common.define.WEB3OrderCancelDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SpecifyDivDef;
import webbroker3.common.define.WEB3SwtDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundBizLogicProvider;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradeOrderNotifyConfirmInterceptor;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MFTradeTypeDef;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

/**
 * (投信売買注文通知UnitServiceImpl)
 * 投信売買注文通知１件サービス実装クラス投信売買注文通知１件ごとの処理を実施する。<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualTradeOrderNotifyUnitServiceImpl 
	implements WEB3MutualTradeOrderNotifyUnitService
{
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualTradeOrderNotifyUnitServiceImpl.class);
    
    /**
     * (notify売買注文通知)<BR>
     * 投信売買注文通知処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信売買注文通知）notify売買注文通知」参照
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信売買注文通知）notify売買注文通知」: <BR>
     *    18((拡張投信注文マネージャ.submitNewOrder()の戻り値.<BR>
     *       getProcessingResult().isSuccessfulResult()==falseの場合、<BR>
     *       例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00191<BR>
     * ==========================================================<BR>
     * @@param l_orderNotifyQueueParams - 投信注文通知キューParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    public void notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams) ";
        log.entering(STR_METHOD_NAME);
        
        // If (投信注文通知キューParams == null 
        if (l_orderNotifyQueueParams == null)
        {
            log.debug("パラメータNull出来ない。 " +
                "with (投信注文通知キューParams)l_orderNotifyQueueParams ="+ 
                    l_orderNotifyQueueParams);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);     
        }

        //拡張アカウントマネージャの取得
        FinApp l_finApp = 
            (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //1.1）　@証券会社オブジェクトの取得
            Institution l_institution = 
                l_accMgr.getInstitution(
                    l_orderNotifyQueueParams.getInstitutionCode());
            
            //1.2） 部店オブジェクトを取得する。
            Branch l_branch = 
                l_accMgr.getBranch(
                    l_institution, 
                    l_orderNotifyQueueParams.getBranchCode());
            
			// 1.3）以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。
			//[取得条件]
			//部店ID=取得した部店.部店ID
			//プリファ@レンス名="mf.available.datacode"
			//プリファ@レンスの連番=1
            BranchPreferencesRow l_branchPreFerencespRow = null;
            int l_intNameSerialNo = 1;
            int l_intFalg = 0;
            try
            {
                l_branchPreFerencespRow = BranchPreferencesDao.findRowByPk(
                    l_branch.getBranchId(),
                    WEB3BranchPreferencesNameDef.AVAILABLE_DATACODE,
                    l_intNameSerialNo);
            }
            catch (DataFindException l_ex)
            {
                log.debug("レコードが取得できなかった場合");
                log.exiting(STR_METHOD_NAME);                
                
                l_intFalg = 1;
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
			// 1.4）以下の条件のを満たす場合は、以降の処理をスキップする。
            String l_strValue = null;
            if (l_intFalg != 1)
            {
                l_strValue = l_branchPreFerencespRow.getValue();
            }

            log.debug("l_strValue = " + l_strValue);
            //未使用
            boolean l_blnIsDefault = 
                WEB3AvailableDatacodeDef.DEFAULT.equals(l_strValue);
            //外国投信取扱可能
            boolean l_blnIsForeignMf = 
                WEB3AvailableDatacodeDef.FOREIGN_MF.equals(l_strValue);
            //募集取扱可能
            boolean l_blnIsRecruitOrder = 
                WEB3AvailableDatacodeDef.RECRUIT_ORDER.equals(l_strValue);
            //外国投信＋募集取扱可能
            boolean l_blnIsAll = 
                WEB3AvailableDatacodeDef.ALL.equals(l_strValue);
            //取扱不可
            boolean l_blnIsNone =
                WEB3AvailableDatacodeDef.NONE.equals(l_strValue);
            
            //国内投信
            boolean l_blnIsDomesttc = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_DOMESTIC_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());
           //外国投信
            boolean l_blnIsForeign = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());
            //募集
            boolean l_blnIsRecruit = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());

            //[条件]
			//○取得したプリファ@レンスの値 == ”未使用” or レコードが取得できなかった場合
			//  引数.投信注文通知キューParams.データコード != ”国内投信”
            //○取得したプリファ@レンスの値 == ”外国投信取扱可能” の場合
			//   引数.投信注文通知キューParams.データコード != （”国内投信” or ”外国投信”）
			//○取得したプリファ@レンスの値 == ”募集取扱可能” の場合
			//   引数.投信注文通知キューParams.データコード != （”国内投信” or ”募集”）
			//○取得したプリファ@レンスの値 == ”外国投信＋募集取扱可能” の場合
			//   引数.投信注文通知キューParams.データコード != （”国内投信” or ”外国投信” or ”募集”）
            //○取得したプリファ@レンスの値 == ”取扱不可”の場合
            if (((l_blnIsDefault || l_intFalg == 1) && !l_blnIsDomesttc) ||      
               (l_blnIsForeignMf && !(l_blnIsDomesttc || l_blnIsForeign)) ||
               (l_blnIsRecruitOrder && !(l_blnIsDomesttc || l_blnIsRecruit)) ||
               (l_blnIsAll && !(l_blnIsDomesttc || l_blnIsForeign || l_blnIsRecruit)) ||
               l_blnIsNone)
            {
                return;
            }
            
            if (WEB3OrderCancelDivDef.CANCEL_NOT.equals(l_orderNotifyQueueParams.getOrderCancelDiv()))
            {
                //1.5) create注文(投信注文通知キューParams)
                this.createOrder(l_orderNotifyQueueParams);
            }
            else if (WEB3OrderCancelDivDef.CANCEL.equals(l_orderNotifyQueueParams.getOrderCancelDiv()))
            {
                //1.6 cancel注文(投信注文通知キューParams)
                this.cancelOrder(l_orderNotifyQueueParams);
            }
         }
         catch (NotFoundException l_ex)
         {
             log.error("該当データなし。", l_ex);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }                
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create注文)<BR>
     * 注文生成処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信売買注文通知）注文生成」 参照。
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信売買注文通知）注文生成」: <BR>
     *    18((submitNewOrder()より成功の戻り値が返されなかった場合、<BR>
     *       例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00191<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_orderNotifyQueueParams - 投信注文通知キューParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void createOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)" ;
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.1 getInstitution
            //拡張アカウントマネージャの取得
            FinApp l_finApp = 
                (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //証券会社オブジェクトの取得
            Institution l_institution = 
                l_accMgr.getInstitution(
                    l_orderNotifyQueueParams.getInstitutionCode());
           
            //1.2 getBranch
            //部店オブジェクトを取得する。
            Branch l_branch = 
                l_accMgr.getBranch(
                    l_institution, 
                    l_orderNotifyQueueParams.getBranchCode());
            
            //1.3 getMainAccount
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount) l_accMgr.getMainAccount(
	                l_institution, 
	                l_branch, 
	                l_orderNotifyQueueParams.getAccountCode());

            //1.4 is信用口座開設
			boolean l_blnisMarginAccountEstablished =
				l_mainAccount.isMarginAccountEstablished(
				    WEB3GentradeRepaymentDivDef.DEFAULT);
	        
			//1.5 getSubAccount
			//[getSubAccountに渡すパラメタ] 
			// 補助口座タイプ：  
			//is信用口座開設=true の場合、
			//SubAccountTypeEnum.株式信用取引口座（保証金）  
			SubAccount l_subAccount = null;	
			if (l_blnisMarginAccountEstablished)
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
			}
			else
			{
				//is信用口座開設=false の場合、
			    //SubAccountTypeEnum.株式取引口座（預り金）
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
			}
			
            //1.6 get投信銘柄
			
			//拡張投信銘柄マネージャを取得する。
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mutualFundProduct = 
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution, 
                    l_orderNotifyQueueParams.getProductCode());
            //拡張投信注文マネージャ取得
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            
			//1.7 if　@乗換区分＝乗換の場合
            WEB3MutualFundProduct l_swtDivProduct = null;
            //乗換区分＝乗換の場合
            boolean l_blnIsSwitching = 
                WEB3SwtDivDef.SWITCHING.equals(l_orderNotifyQueueParams.getSwtDiv());
            if (l_blnIsSwitching)
            {
                //1.7.1 get投信銘柄(Institution, String, String)
                l_swtDivProduct =  
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                        l_institution, 
                        l_orderNotifyQueueParams.getSwtProductCode());
            }

            //売買区分 == 募集 の場合
            boolean l_blnIsRecruit = 
                WEB3MFTradeTypeDef.RECRUIT.equals(
                    l_orderNotifyQueueParams.getTradeType());
            //売買区分 == 買付 の場合
            boolean l_blnIsBuy = 
                WEB3MFTradeTypeDef.BUY.equals(
                    l_orderNotifyQueueParams.getTradeType());
            //売買区分 == 解約 の場合
            boolean l_blnIsSell =  
                WEB3MFTradeTypeDef.SELL.equals(
                    l_orderNotifyQueueParams.getTradeType());

            //注文数量
            double l_dblOrderQuantity = 0.0 ;
            
            //1.8 if  募集以外の場合
            //1.8.1 注文数量の取得
            //取得した拡張投信銘柄.get入力単位()の戻り値が”1：1”の場合 
            //注文数量 = 引数.投信注文通知キューParams.get売買数量()
            //(*) 取得した拡張投信銘柄.get入力単位()の戻り値が”1：1”の場合　@
             //注文数量 = 引数.投信注文通知キューParams.get売買数量()
            if(!l_blnIsRecruit){
                if ((WEB3InputUnitDef.ONE).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity();
                }
                //取得した拡張投信銘柄.get入力単位()の戻り値が”2：1万”の場合 
                //注文数量 = 引数.投信注文通知キューParams.get売買数量() ＊ 10000 
                else if ((WEB3InputUnitDef.TEN_THOUSAND).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity() * 10000;
                }
            }                    
            
            //1.9 if　@売買区分 == 募集 の場合
    		//売買区分が（3：募集）の場合、 
    		//約定日＝発注日 
    		//受渡日＝募集終了日となる
            //募集終了日
            Timestamp l_tsRecruitEndDate = null;
            //発注日
            Timestamp l_tsBizDate = null;
            //指定方法@（募集）
            String l_strRecruitSpecityDiv = null;
            //概算売買口数
            double l_dblEstimateDealingQty = 0.0D;
            if (l_blnIsRecruit)
            {
                //1.9.1 get発注日( )
                //発注日（=約定日）を取得する。 
                l_tsBizDate = new Timestamp(
                        WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());

                //1.9.2 get募集終了日( )
                //募集終了日（=受渡日）を取得する。
                l_tsRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

                //1.9.3 get指定方法@（募集）( )
                l_strRecruitSpecityDiv =
                    l_mutualFundProduct.getRecruitSpecityDiv();                    
                if(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    l_strRecruitSpecityDiv))
                {
                    log.debug("指定方法@（募集）エラー(”選択指定”を指定不可)。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02297,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "指定方法@（募集）エラー(”選択指定”を指定不可)。");
                }

                //1.9.4 概算売買口数の取得
                if ((WEB3InputUnitDef.ONE)
                    .equals(l_mutualFundProduct.getInputUnit()))
                {
                    l_dblEstimateDealingQty =
                        l_orderNotifyQueueParams.getQuantity();
                }
                else if (
                    (WEB3InputUnitDef.TEN_THOUSAND).equals(
                        l_mutualFundProduct.getInputUnit()))
                {
                    l_dblEstimateDealingQty =
                        l_orderNotifyQueueParams.getQuantity() * 10000;
                }

                //1.9.5 注文数量の取得
                if (WEB3BuySellSwtSpecityDivDef
                    .QUANTITY_DESIGNATE
                    .equals(l_strRecruitSpecityDiv))
                {
                    l_dblOrderQuantity = l_dblEstimateDealingQty;
                }
                else if (
                    WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                        l_strRecruitSpecityDiv))
                {
                    l_dblOrderQuantity =
                        l_orderNotifyQueueParams.getEstimatedPrice();
                }
            }
            
            //1.10 if　@乗換区分＝乗換 の場合
            
            //乗換約定日
            Timestamp l_tsSwtExecutedDate = null;
            //乗換受渡日
            Timestamp l_datSwtDeliveryDate = null;
            
            if (l_blnIsSwitching)
            {
                //1.10.1  get乗換約定日(Institution, String, String)
				//乗換時の約定日を取得する。 
				//［get乗換約定日に渡すパラメタ］ 
				//証券会社： 取得した証券会社オブジェクト 
				//乗換元銘柄コード： 
                //引数.投信注文通知キューParams.get銘柄コード()の戻り値 
				//乗換先銘柄コード： 
                //引数.投信注文通知キューParams.get買付銘柄コード()の戻り値
                l_tsSwtExecutedDate = 
                    l_mutualFundProductManager.getSwtExecutedDate(
                        l_institution, 
                        l_orderNotifyQueueParams.getProductCode(),
                        l_orderNotifyQueueParams.getSwtProductCode());
                
                //1.10.2 get乗換受渡日(Institution, String, String)
				//乗換時の受渡日を取得する。 
				//［get乗換受渡日に渡すパラメタ］ 
				//証券会社： 取得した証券会社オブジェクト 
				//乗換元銘柄コード： 
                //引数.投信注文通知キューParams.get銘柄コード()の戻り値 
				//乗換先銘柄コード： 
                //引数.投信注文通知キューParams.get買付銘柄コード()の戻り値
                l_datSwtDeliveryDate =
                    l_mutualFundProductManager.getSwtDeliveryDate(
                        l_institution,
                        l_orderNotifyQueueParams.getProductCode(),
                        l_orderNotifyQueueParams.getSwtProductCode());
            }
            
            //1.11 if 募集、乗換以外の場合
            
            //約定日
            Timestamp l_tsExecTimestamp = null;
            //受渡日
            Timestamp l_tsDeliveryTimestamp = null;

            Date l_datBizDate = null;
            if (!(l_blnIsRecruit || l_blnIsSwitching))
            {
                //発注日取得
                //拡張投信銘柄.is特定日取引銘柄()をコール
                //[拡張投信銘柄.is特定日取引銘柄()の引数]
                //注文種別：取得した注文種別(*1)
                //(*1)注文種別の取得。
                OrderTypeEnum l_orderTypeEnum = null;
                if (l_blnIsSell && !l_blnIsSwitching)
                {
                    //投信注文通知キューParams.get売買区分()の戻り値が”1：売付”
                    //かつ投信注文通知キューParams.get乗換区分()が”1：乗換”でない場合、202：投資信託売注文
                    l_orderTypeEnum = OrderTypeEnum.MF_SELL;
                }
                else if (l_blnIsBuy)
                {
                    //投信注文通知キューParams.get売買区分()の戻り値が”2：買付”、201：投資信託買注文
                    l_orderTypeEnum = OrderTypeEnum.MF_BUY;
                }

                boolean l_blnIsUnitTypeProduct =
                    l_mutualFundProduct.isUnitTypeProduct(l_orderTypeEnum);

                //拡張投信銘柄.is特定日取引銘柄() = trueの場合
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                if (l_blnIsUnitTypeProduct)
                {
                    if (OrderTypeEnum.MF_BUY.equals(l_orderTypeEnum))
                    {
                        //取得した注文種別(*1)が「201：投資信託買注文」の場合
                        //拡張投信銘柄.getDataSourceObject().get買付終了日を発注日とする。
                        l_datBizDate = l_mutualFundProductRow.getBuyEndDate();
                    }
                    else if (OrderTypeEnum.MF_SELL.equals(l_orderTypeEnum))
                    {
                        //取得した注文種別(*1)が「202：投資信託売注文」の場合
                        //拡張投信銘柄.getDataSourceObject().get解約乗換終了日を発注日とする。
                        l_datBizDate = l_mutualFundProductRow.getSellSwtEndDate();
                    }
                }
                else
                {
                    //拡張投信銘柄.is特定日取引銘柄() = falseの場合
                    //投信注文通知キューParams.get発注日時()の戻り値を発注日とする。
                    l_datBizDate = l_orderNotifyQueueParams.getBizDatetime();
                }

                //1.11.1 get約定日(Institution, String, Date)
                l_tsExecTimestamp = 
                    l_mutualFundProductManager.getExecutedDate(
                        l_institution, 
                        l_orderNotifyQueueParams.getProductCode(),
                        WEB3DateUtility.toDay(l_datBizDate));

                //(*)特定日取引銘柄の場合の約定日取得
                //取得した拡張投信銘柄.getProductIdが3303910181800または3303911181800の場合
                //かつ引数.投信注文通知キューParams.get売買区分()が”1:売付”の場合
                long l_lngProductId = l_mutualFundProduct.getProductId();
                if ((l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
                    && l_blnIsSell)
                {
                    //１）　@取引カレンダーを取得する
                    //GtlUtils.getFinObjectManager().getTradingCalendar()をコールして、取引カレンダーを取得する。
                    //［getTradingCalendarに渡すパラメタ］
                    //取引銘柄ID：
                    //取得した拡張投信銘柄.getProductIdが3303910181800の場合、330003910181800
                    //取得した拡張投信銘柄.getProductIdが3303911181800の場合、330003911181800
                    TradingCalendar l_tradingCalendar = null;
                    if (l_lngProductId == 3303910181800L)
                    {
                        l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                            330003910181800L);
                    }
                    else
                    {
                        l_tradingCalendar = GtlUtils.getFinObjectManager().getTradingCalendar(
                            330003911181800L);
                    }
                    //２）　@約定日を取得する
                    //取引カレンダー.roll()をコールして、約定日を取得する。
                    //［rollに渡すパラメタ］
                    //発注日： 取得した投信発注日
                    //移動日数： 2
                    Date l_datExecTimestamp =
                        l_tradingCalendar.roll(WEB3DateUtility.toDay(l_datBizDate), 2);
                    //３）　@取得した約定日を返す。
                    //※前処理で取得した約定日を上書きする。
                    l_tsExecTimestamp =
                        new Timestamp(l_datExecTimestamp.getTime());
                }

                //1.11.2 get受渡日(Institution, String, boolean,Date)
        		//［get受渡日に渡すパラメタ］ 
        		//証券会社： 取得した証券会社オブジェクト 
        		//銘柄コード： 引数.投信注文通知キューParams.get銘柄コード()の戻り値 
        		//is買付： 
        		//(*) 引数.投信注文通知キューParams.get売買区分()
                //の戻り値が”1：売付”の場合 false を指定。 
        		//(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”
                //2：買付”の場合 true を指定。
                //約定日：取得した約定日
                l_tsDeliveryTimestamp  = 
                    l_mutualFundProductManager.getDeliveryDate(
                        l_institution,
                        l_orderNotifyQueueParams.getProductCode(),
                        l_blnIsBuy,
                        l_tsExecTimestamp);
            }
            
			double l_dblEstimatedPrice = 0.0D;

            //1.12 if　@データコード≠募集 & データコード≠外国投信 の場合
            
            //概算受渡代金オブジェクト
            WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;   
           
            if (!WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT_ORDER_NOTIFY.equals(
                	l_orderNotifyQueueParams.getRequestCode()) && 
                !WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode()))
            {
                //1.12.1 calc概算受渡代金(SubAccount, 拡張投信銘柄, String, double, String, String)
        		//［引数］ 
        		//補助口座： 取得した補助口座オブジェクト 
        		//銘柄： 取得した拡張投信銘柄オブジェクト 
                //銘柄（乗換先）： 
        		//(*)  引数.投信注文通知キューParams.get売買区分()の戻り値が”
                //1：売付”で、かつ 
        		//引数.投信注文通知キューParams.get乗換区分()が”1：乗換”の場合 
        		//取得した乗換先の拡張投信銘柄オブジェクトを指定 
        		//(*) 乗換以外の場合、nullセット 
                
                //処理区分
                String l_strStatus = null; 
                //指定方法@                                                               
                String l_strSpecifyDiv = null; 
                if (l_blnIsSell)
                {
                    //処理区分： 
            		//(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”
                    //1：売付”で、かつ 
            		//引数.投信注文通知キューParams.get乗換区分()が”
                    //1：乗換”でない場合、”2：解約”を指定 
                    if (!l_blnIsSwitching)
                    {
                        l_strStatus = WEB3MFProcessDivDef.SELL;  
                    }
                    else
                    {
                        //(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”
                        //1：売付”で、かつ 
                		//引数.投信注文通知キューParams.get乗換区分()が”
                        //1：乗換”の場合、 ”3：乗換”を指定 
                        l_strStatus = WEB3MFProcessDivDef.SWITCHING;  
                    }
                }
                
                //(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”
                //2：買付” の場合、”1：買付”を指定 
                else if(l_blnIsBuy)
                {
                    l_strStatus = WEB3MFProcessDivDef.BUY;    
                }
        		//(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”
                //3：募集”の場合、 の場合、”4：募集”を指定 
        		else if(l_blnIsRecruit)
        		{
        		    l_strStatus = WEB3MFProcessDivDef.RECRUIT;
        		}
        		
                //注文数量： 取得した注文数量 
                //指定方法@： 
        		//(*) 引数.投信注文通知キューParams.get指定()の戻り値が”
        		//1：口数”の場合 ”4：口数指定”を指定 
        		//(*) 引数.投信注文通知キューParams.get指定()の戻り値が”
        		//2：金額”の場合 ”3：金額指定”を指定 
                /*指定方法@取得*/
                
                //(*) 引数.投信注文通知キューParams.get指定()の戻り値が”
                 //1：口数”の場合” 4：口数指定”を指定
                if ((WEB3SpecifyDivDef.COUNT).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv()))
                {
                    l_strSpecifyDiv = 
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE;      
                }
                
                //(*) 引数.投信注文通知キューParams.get指定()の戻り値が”
                 //2：金額”の場合 ”3：金額指定”を指定
                else if ((WEB3SpecifyDivDef.MONEY).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv())) 
                {
                    l_strSpecifyDiv = 
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE;    
                }
        		
                //決済方法@： 引数.投信注文通知キューParams.get決済()の戻り値 
        		//請求方法@の取得
                String l_claimDiv = l_orderNotifyQueueParams.getClaimDiv();
                if (" ".equals(l_claimDiv))
                {
                    l_claimDiv = WEB3ClaimDivDef.SELL;
                }
                
                //口座区分： 引数.投信注文通知キューParams.get特定口座区分() 
        		//注文チャネル： 引数.投信注文通知キューParams.入力区分()の戻り値
                //発注日：
                //(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”1：売付”
                //かつ引数.投信注文通知キューParams.get乗換区分()が”1：乗換”でない場合
                //(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”2：買付”の場合取得した発注日
                //(*) それ以外、引数.投信注文通知キューParams.発注日時()の戻り値
                Date l_datBizDatetime = null;
                if (l_blnIsSell && !l_blnIsSwitching)
                {
                    l_datBizDatetime = l_datBizDate;
                }
                else if (l_blnIsBuy)
                {
                    l_datBizDatetime = l_datBizDate;
                }
                else
                {
                    l_datBizDatetime = l_orderNotifyQueueParams.getBizDatetime();
                }

                //概算受渡代金オブジェクトを取得する
                l_mfEstimatedPrice =
                    l_orderManager.calcEstimateDeliveryAmount(
                        l_subAccount,
                        l_mutualFundProduct,
                        l_swtDivProduct,
                        l_strStatus,
                        l_dblOrderQuantity,
                        l_strSpecifyDiv,
                        l_orderNotifyQueueParams.getSettlementDiv(),
                        l_claimDiv,
                        l_orderNotifyQueueParams.getTaxType(),
                        l_orderNotifyQueueParams.getInputDiv(),
                        l_datBizDatetime);

                //取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値セット
                l_dblEstimatedPrice = 
                	l_mfEstimatedPrice.getEstimatedPrice();
            } 
            else
            {
				//(*) 「募集」あるいは「外国投信」の場合
				//投信注文通知キューParams.get概算金額()の戻り値セット。  
				l_dblEstimatedPrice = 
					l_orderNotifyQueueParams.getEstimatedPrice();            	
            }
            
            //1.13 if　@乗換区分＝乗換の場合
            // 乗換先の概算買付口数の取得
            double l_dblSwitchingEstimatedQty = 0.0D;
            if (l_blnIsSwitching)
            {
                WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
                    (WEB3MutualFundBizLogicProvider) l_finApp
                        .getTradingModule(ProductTypeEnum.MUTUAL_FUND)
                        .getBizLogicProvider();
                l_dblSwitchingEstimatedQty =
                    l_mfBizLogicProvider.calcEstimatedBuyQty(
                        l_swtDivProduct,
                        l_dblEstimatedPrice);
            }
            
            //1.14 投信売買注文通知確定インタセプタ( )
            WEB3MutualFundTradeOrderNotifyConfirmInterceptor 
            	l_tradeOrderNotifyConfirmInterceptor = 
            	    new WEB3MutualFundTradeOrderNotifyConfirmInterceptor();
            
            //1.15 setThreadLocalPersistenceEventInterceptor(
            //MutualFundOrderManagerPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_tradeOrderNotifyConfirmInterceptor);

            //1.16 ＜プロパティ・セット＞
    		//※以下の処理における、取引の種別の定義 

            //外国投信：投信注文通知キューParams.getデータコード()の戻り値が”CI813：外国投信” 
            boolean l_blnIsForeignPossible = 
                WEB3HostRequestCodeDef.MUTUAL_FUND_FOREIGN_ORDER_NOTIFY.equals(
                    l_orderNotifyQueueParams.getRequestCode());
            
    		//乗換：投信注文通知キューParams.get売買区分()の戻り値が”1：売付”、 
    		//かつ、 投信注文通知キューParams.get乗換区分()が”1：乗換” 
            boolean l_blnIsSwtPossible = (l_blnIsSell && l_blnIsSwitching); 
            
    		//買付：投信注文通知キューParams.get売買区分()の戻り値が”2：買付” 
    		//  　@（特別な断りがない限り、国内投信と外国投信の両方の買付を指す）
            boolean l_blnIsBuyPossible = l_blnIsBuy;
            
    		//売付：投信注文通知キューParams.get売買区分()の戻り値が”1：売付”、
    		// かつ、投信注文通知キューParams.get乗換区分()が”1：乗換”でない場合、 
    		//  　@（特別な断りがない限り、国内投信と外国投信の両方の売付を指す） 
            boolean l_blnIsSellPossible =  (l_blnIsSell && !l_blnIsSwitching); 
            
    		//募集：投信注文通知キューParams.gett売買区分()の戻り値が”3：募集” 
            boolean l_blnIsRecruitPossible = l_blnIsRecruit;
            
    		//引数.投信売買注文通知確定インタセプタ.set注文種別( )をコールする。 
            OrderTypeEnum l_orderType = null;
    		//引数：注文種別： 
    		//(*) 「買付」の場合、 ”201：投資信託買注文”
            //（OrderTypeEnumに定義）を指定する。 
    		if (l_blnIsBuyPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_BUY;
    		}
            //(*) 「売付」の場合 ”202：投資信託売注文”
    		//（OrderTypeEnumに定義）を指定する。
    		else if (l_blnIsSellPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_SELL;
    		}
    		//(*) 「乗換」の場合、”204：投資信託乗換注文”
    		//（OrderTypeEnumに定義）を指定する。
    		else if (l_blnIsSwtPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_SWITCHING;
    		}
    		//(*) 「募集」の場合、”203：投資信託募集注文”
    		//（OrderTypeEnumに定義）を指定する。　@　@　@　@　@ 
    		else if (l_blnIsRecruitPossible)
    		{
    		    l_orderType = OrderTypeEnum.MF_RECRUIT;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setOrderType(l_orderType);
            
    		//・引数.投信売買注文通知確定インタセプタ.set発注日( )をコールする。
    		//引数：発注日：
            //(*) 「買付」、「売付」の場合
            //取得した発注日
            //(*) それ以外の場合
            //引数.投信注文通知キューParams.get発注日時()の戻り値をセット
            if (l_blnIsBuyPossible || l_blnIsSellPossible)
            {
                l_tradeOrderNotifyConfirmInterceptor.setBizDate(
                    new Timestamp(l_datBizDate.getTime()));
            }
            else
            {
        		l_tradeOrderNotifyConfirmInterceptor.setBizDate(
        		    l_orderNotifyQueueParams.getBizDatetime());
            }
    		//引数.投信売買注文通知確定インタセプタ.set受渡日( )をコールする。
    		Timestamp l_tsDelivery = null;
    		//引数：受渡日： 
    		//(*) 「募集」の場合 
            //get募集終了日()の戻り値を指定する。
    		if (l_blnIsRecruitPossible)
    		{
    		    l_tsDelivery = l_tsRecruitEndDate;
    		}
    		//(*) 「乗換」の場合
    		//get乗換受渡日()の戻り値を指定する。 
    		else if (l_blnIsSwtPossible)
    		{
    		    l_tsDelivery = l_datSwtDeliveryDate;
    		}
    		//(*) それ以外の場合、取得した受渡日を指定する。 
    		else
    		{
    		    l_tsDelivery = l_tsDeliveryTimestamp;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setDeliveryDate(l_tsDelivery);
    		
    		//引数.投信売買注文通知確定インタセプタ.set扱者コード（SONAR）()をコールする。  
    		//引数：引数.投信注文通知キューParams.get扱者コード()の戻り値  
    		l_tradeOrderNotifyConfirmInterceptor.setSonarTraderCode(
    		    l_orderNotifyQueueParams.getTraderCode());
    		
    		//引数.投信売買注文通知確定インタセプタ.set識別コード()をコールする。  
    		//引数：引数.投信注文通知キューParams.get識別コード()の戻り値  
    		l_tradeOrderNotifyConfirmInterceptor.setDiscriminationCode(
    		    l_orderNotifyQueueParams.getOrderRequestNumber());
    		
    		//引数.投信売買注文通知確定インタセプタ.set計算基準価額()をコールする。  
    		double l_dblConstantValue = 0.0D;
    		//引数：計算基準価額：  
    		//(*) 「募集」の場合
    		//引数：引数.投信注文通知キューParams.get基準価額()の戻り値セット。  
    		if (l_blnIsRecruitPossible)
    		{
    		    l_dblConstantValue = l_orderNotifyQueueParams.getConstantValue();
    		}
    		//(*) 「買付」の場合、
    		//取得した拡張投信銘柄オブジェクト.get買付基準価額()の戻り値を指定する。
    		else if (l_blnIsBuyPossible)
    		{
    		    l_dblConstantValue = l_mutualFundProduct.getConstantValue();
    		}
    		//(*) 「売付」「乗換」の場合、
    		//取得した拡張投信銘柄オブジェクト.get解約価額()の戻り値を指定する  
    		else if ((l_blnIsSellPossible) || (l_blnIsSwtPossible))
    		{
    		    l_dblConstantValue = l_mutualFundProduct.getSellValue();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setConstantValue(l_dblConstantValue);
    		
    		//引数.投信売買注文通知確定インタセプタ.set計算基準価額（乗換先）()をコールする。 
    		double l_dblSwtConstantValue = 0.0D;
    		//引数：計算基準価額（乗換先）：  
    		//(*) 「乗換」の場合
    		//取得した乗換先銘柄オブジェクト.get買付基準価額()の戻り値を指定する。  
    		if (l_blnIsSwtPossible)
    		{
    		    l_dblSwtConstantValue = l_swtDivProduct.getConstantValue();

    		}
    		//(*) 「乗換」でない場合Double.NaNを指定する
    		else
    		{
    		    l_dblSwtConstantValue = Double.NaN;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setSwitchingConstantValue(
    		    l_dblSwtConstantValue);
    		
    		//引数.投信売買注文通知確定インタセプタ.set基準価額適用日()をコールする。
    		Timestamp l_tsConstantValueAppDate = null;
    		//引数：基準価額適用日：  
    		//(*) 「募集」の場合  nullを指定する。 
    		//(*) 「募集」でない場合、
            //取得した拡張投信銘柄オブジェクト.get基準価額適用日()の戻り値を指定する。 
    		if (!l_blnIsRecruitPossible)
    		{
    		    l_tsConstantValueAppDate = 
    		        l_mutualFundProduct.getConstantValueAppDate();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setConstantValueAppDate(
    		    l_tsConstantValueAppDate);
    		
    		//引数.投信売買注文通知確定インタセプタ.set概算受渡代金()をコールする。  
    		//引数：概算受渡代金：  
    		//(*) 「募集」あるいは「外国投信」の場合
    		//投信注文通知キューParams.get概算金額()の戻り値セット。  
    		//(*) それ以外の場合
    		//取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値セット
    		l_tradeOrderNotifyConfirmInterceptor.setEstimatedPrice(
    		    l_dblEstimatedPrice);
    		
    		//引数.投信売買注文通知確定インタセプタ.set概算売買口数()をコールする。  
    		double l_dblEstimatedQty = 0.0D;
    		//(*) 「募集」の場合
            //取得した概算売買口数をセットする。  
    		if (l_blnIsRecruitPossible)
    		{
    		    l_dblEstimatedQty = l_dblEstimateDealingQty;
    		}
    		//(*) 「外国投信」の場合、
    		//投信注文通知キューParams.get換算口数()の戻り値セット。
    		else if (l_blnIsForeignPossible)
    		{
    		    l_dblEstimatedQty = l_orderNotifyQueueParams.getEstimatedUnit();
    		}
    		//(*) それ以外の場合
    		//取得した概算受渡代金オブジェクト.get概算売買口数()の戻り値セット 
    		else
    		{
    		    l_dblEstimatedQty = l_mfEstimatedPrice.getEstimatedQty();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setEstimatedQty(l_dblEstimatedQty);
    		
    		//引数.投信売買注文通知確定インタセプタ.set概算買付口数（乗換先）()をコールする。
    		//引数：概算売買口数（乗換先）：
            double l_dblSwtEstimatedQty = 0.0D;
            //(*) 「乗換」の場合、calc概算買付口数()の戻り値を指定する。
            if (l_blnIsSwtPossible)
            {
                l_dblSwtEstimatedQty = l_dblSwitchingEstimatedQty;
            }
            //(*) 「乗換」でない場合、Double.NaNを指定する。
            else
            {
                l_dblSwtEstimatedQty = Double.NaN;
            }
            l_tradeOrderNotifyConfirmInterceptor.setSwitchingEstimatedQty(
                l_dblSwtEstimatedQty);

    		//引数.投信売買注文通知確定インタセプタ.set税区分（乗換先）()をコールする。  
    		TaxTypeEnum l_switchingSubjectTaxDivision = null;
    		//引数：税区分（乗換先）：  
    		//(*) 「乗換」の場合
    		//投信注文通知キューParams.get特定口座区分（乗換先）() の戻り値が 
    		if (l_blnIsSwtPossible)
    		{
    		    //”0：一般”ならばTaxTypeEnum.NORMAL
                if ((WEB3TaxTypeSpecialDef.NORMAL).equals(
                    l_orderNotifyQueueParams.getSwtTaxType()))
                {
                    l_switchingSubjectTaxDivision = TaxTypeEnum.NORMAL;
                }
                //1：特定”ならばTaxTypeEnum.SPECIALを指定する。 
                else if ((WEB3TaxTypeSpecialDef.SPECIAL).equals(
                    l_orderNotifyQueueParams.getSwtTaxType()))
                {
                    l_switchingSubjectTaxDivision = TaxTypeEnum.SPECIAL;
                }
    		}
    		//(*)「乗換」でない場合 nullを指定する。  
    		else
    		{
    		    l_switchingSubjectTaxDivision = null;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setSwitchingSubjectTaxDivision(
    		    l_switchingSubjectTaxDivision);
    		
    		//引数.投信売買注文通知確定インタセプタ.set受渡方法@()をコールする。  
    		String l_strDeliveryDiv = null;
    		//引数：受渡方法@：  
    		//(*) 「売付」の場合 
    		//投信注文通知キューParams.get受渡方法@()の戻り値を指定する。 
    		//(*) 「売付」でない場合 nullを指定する。
    		if (l_blnIsSellPossible)
    		{
    		    l_strDeliveryDiv = l_orderNotifyQueueParams.getDeliveryMethod();
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setDeliveryDiv(l_strDeliveryDiv);

    		//引数.投信売買注文通知確定インタセプタ.set投信タイプ()をコールする。  
    		//引数：投信タイプ： 取得した拡張投信銘柄.getMutualFundType()の戻り値  
    		l_tradeOrderNotifyConfirmInterceptor.setMutualFundType(
    		    l_mutualFundProduct.getMutualFundType().intValue() + "");

    		//引数.投信売買注文通知確定インタセプタ.set投信解約区分()をコールする。  
    		String l_strMutualFundSellDiv = null;
    		//引数：投信解約区分：  
    		if ((l_blnIsSellPossible) || (l_blnIsSwtPossible))
    		{
        		//(*) 「売付」or 「乗換」、かつ、
    		    //投信注文通知キューParams.get指定()の戻り値が
                //”1：口数”の場合 ”4：口数指定”を指定  
                if ((WEB3SpecifyDivDef.COUNT).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv()))
                {
                    l_strMutualFundSellDiv = WEB3SellDivDef.COUNT_DESIGNATE;
                }
        		//(*) 「売付」、かつ、
                //投信注文通知キューParams.get指定()の戻り値が”
                //2：金額”の場合 ”3：金額指定”を指定  
                else if ((WEB3SpecifyDivDef.MONEY).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv())) 
                {
                    l_strMutualFundSellDiv = WEB3SellDivDef.MONEY_DESIGNATE;
                }
    		}
    		//(*) それ以外の場合 nullを指定 
    		l_tradeOrderNotifyConfirmInterceptor.setMutualFundSellDiv(
    		    l_strMutualFundSellDiv);
    		

    		//引数.投信売買注文通知確定インタセプタ.set約定日()をコールする。
    		Timestamp l_tsExecutionTimestamp = null;
    		//引数：約定日：  
    		//(*) 「募集」の場合 get発注日()の戻り値を指定する。 
    		if (l_blnIsRecruitPossible)
    		{
    		    l_tsExecutionTimestamp = l_tsBizDate;
    		}
    		//(*) 「乗換」の場合 
    		//拡張投信銘柄マネージャー.get乗換約定日()の戻り値を指定する。 
    		else if (l_blnIsSwtPossible)
    		{
    		    l_tsExecutionTimestamp = l_tsSwtExecutedDate;
    		}
    		//(*) それ以外の場合 取得した約定日を指定する。 
    		else
    		{
    		    l_tsExecutionTimestamp = l_tsExecTimestamp;
    		}
    		l_tradeOrderNotifyConfirmInterceptor.setExecutionTimestamp(
    		    l_tsExecutionTimestamp);

    		//引数.投信売買注文通知確定インタセプタ.set決済区分()をコールする。  
    		//引数：引数.投信注文通知キューParams.get決済()の戻り値  
            l_tradeOrderNotifyConfirmInterceptor.setSettlementType(
                l_orderNotifyQueueParams.getSettlementDiv());

    		//引数.投信売買注文通知確定インタセプタ.set無手数料区分()をコールする。  
    		//引数：引数.投信注文通知キューParams.get無手数料区分()の戻り値  
            l_tradeOrderNotifyConfirmInterceptor.setNoCommissionDivision(
                l_orderNotifyQueueParams.getCommissionDiv());

    		//引数.投信売買注文通知確定インタセプタ.set銘柄コード（乗換先）()をコールする。  
    		//引数：引数.投信注文通知キューParams.get買付銘柄コード()の戻り値  
            l_tradeOrderNotifyConfirmInterceptor.setSwitchingSubjectMutualProductCode(
                l_orderNotifyQueueParams.getSwtProductCode());

    		//引数.投信売買注文通知確定インタセプタ.set請求区分()をコールする。
            String l_strRequestDivision = null;
    		//引数：請求区分：  
            if ((l_blnIsSellPossible) || (l_blnIsSwtPossible))
            {
        		//(*) 「売付」、かつ、
                //投信注文通知キューParams.get請求区分()の戻り値が ”
                //空白：解約”の場合 ”0：解約”を指定
                if (l_orderNotifyQueueParams.getClaimDiv() != null && 
                        "".equals(l_orderNotifyQueueParams.getClaimDiv().trim()))
                {
                    l_strRequestDivision = WEB3ClaimDivDef.SELL;        
                }
        		//(*) 「売付」、かつ、
                //投信注文通知キューParams.get請求区分()の戻り値が ”
                //1：買取”の場合 ”1：買取”を指定
                else if(WEB3ClaimDivDef.BUY.equals(
                    l_orderNotifyQueueParams.getClaimDiv()))
                {
                    l_strRequestDivision = WEB3ClaimDivDef.BUY;        
                }
            }
            //(*) それ以外の場合 nullを指定
            l_tradeOrderNotifyConfirmInterceptor.setRequestDivision(
                l_strRequestDivision); 

    		//引数.投信売買注文通知確定インタセプタ.set受注日時()をコールする。  
    		//引数：引数.投信注文通知キューParams.get注文日時()の戻り値 
            l_tradeOrderNotifyConfirmInterceptor.setAcceptDate(
                l_orderNotifyQueueParams.getCreateDatetime());

    		//引数.投信売買注文通知確定インタセプタ.set入金日( )をコールする。 
            Timestamp l_tsPaymentDate = null;
    		//引数：入金日：  
    		//(*) 「募集」の場合、
            //投信注文通知キューParams.get入金日()の戻り値を指定する。 
            if (l_blnIsRecruitPossible)
            {
                l_tsPaymentDate = l_orderNotifyQueueParams.getPaymentDate();
            }
    		//(*) 「募集」でない場合 nullを指定する。
            l_tradeOrderNotifyConfirmInterceptor.setPaymentDate(l_tsPaymentDate);
            
    		//引数.投信売買注文通知確定インタセプタ.setCPUNo( )をコールする。 
            String l_strCpuNo = null;
    		//引数：CPUNo：  
    		//(*) 「募集」の場合、
            //投信注文通知キューParams.getCPUNo()の戻り値を指定する。 
    		//(*) 「募集」でない場合 nullを指定する。
            if (l_blnIsRecruitPossible)
            {
                l_strCpuNo = l_orderNotifyQueueParams.getCpuNo();
            }
    		//(*) 「募集」でない場合 nullを指定する。
            l_tradeOrderNotifyConfirmInterceptor.setCPUNo(l_strCpuNo);
            
            //1.17  拡張投信新規注文内容
    		//拡張投信新規注文内容を生成する。 
    		//[拡張投信新規注文内容のコンストラクタに渡すパラメタ] 
    		//代理入力者： null 
    		//is買付： 
    		//(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”1：売付” 
    		//	  の場合 false を指定。 
    		//(*) 引数.投信注文通知キューParams.get売買区分()の戻り値が”2：買付” 
    		//	  又は、”3：募集”の場合 true を指定。 
    		//銘柄コード： 引数.投信注文通知キューParams.get銘柄コード()の戻り値 
    		//注文数量： 取得した注文数量 
    		//注文数量タイプ：
            //[募集の場合]
            //(*) 拡張投信銘柄.get指定方法@（募集）()の戻り値が”4：口数指定 ”の場合
            //QuantityTypeEnum.数量を指定。
            //(*) 拡張投信銘柄.get指定方法@（募集）()の戻り値が”3：金額指定 ”の場合
            //QuantityTypeEnum.金額を指定。
            //[それ以外]  
    		//(*) 引数.投信注文通知キューParams.get指定()の戻り値が”1：口数” 
    		//	  QuantityTypeEnum.数量を指定。 
    		//(*) 引数.投信注文通知キューParams.get指定()の戻り値が”2：金額” 
    		//の場合はQuantityTypeEnum.金額を指定 
    		//税区分： 
    		//(*) 引数.投信注文通知キューParams.get特定口座区分()の戻り値が 
    		//”0：一般”ならばTaxTypeEnum.NORMALを、”1：特定”ならば 
    		//TaxTypeEnum.SPECIALを設定する。
            
            /*注文数量タイプ取得*/
            QuantityTypeEnum l_orderQuantityType = null;
            if(l_blnIsRecruit){
                if (WEB3BuySellSwtSpecityDivDef
                    .QUANTITY_DESIGNATE
                    .equals(l_strRecruitSpecityDiv))
                {
                    l_orderQuantityType = QuantityTypeEnum.QUANTITY;
                }
                else if (
                    WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                        l_strRecruitSpecityDiv))
                {
                    l_orderQuantityType = QuantityTypeEnum.AMOUNT;
                }
            }else{
                //(*) 引数.投信注文通知キューParams.get指定()の戻り値が”
                 //1：口数”QuantityTypeEnum.数量を指定。
                if ((WEB3SpecifyDivDef.COUNT).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv()))
                {
                    l_orderQuantityType = QuantityTypeEnum.QUANTITY ;      
                }
                //(*) 引数.投信注文通知キューParams.get指定()の戻り値が”
                 //2：金額”の場合はQuantityTypeEnum.金額を指定
                else if ((WEB3SpecifyDivDef.MONEY).equals(
                    l_orderNotifyQueueParams.getSpecifyDiv())) 
                {
                    l_orderQuantityType = QuantityTypeEnum.AMOUNT ;    
                }
            }
            
            //(*) 引数.投信注文通知キューParams.get特定口座区分()の戻り値が
            TaxTypeEnum l_taxTypeEnum = null;
		    //”0：一般”ならばTaxTypeEnum.NORMAL
            if ((WEB3TaxTypeSpecialDef.NORMAL).equals(
                l_orderNotifyQueueParams.getTaxType()))
            {
                l_taxTypeEnum = TaxTypeEnum.NORMAL;
            }
            //1：特定”ならばTaxTypeEnum.SPECIALを指定する。 
            else if ((WEB3TaxTypeSpecialDef.SPECIAL).equals(
                l_orderNotifyQueueParams.getTaxType()))
            {
                l_taxTypeEnum = TaxTypeEnum.SPECIAL;
            } 

            boolean l_blnIsBuySpec = 
                WEB3MFTradeTypeDef.BUY.equals(l_orderNotifyQueueParams.getTradeType()) ||                 
                WEB3MFTradeTypeDef.RECRUIT.equals(l_orderNotifyQueueParams.getTradeType());
            
            //新規注文内容の生成
            WEB3MutualFundNewOrderSpec  l_mutualFundNewOrderSpec = 
                new WEB3MutualFundNewOrderSpec(
                    null,
                    l_blnIsBuySpec,
                    l_orderNotifyQueueParams.getProductCode(),
                    l_dblOrderQuantity,
                    l_orderQuantityType,
                    l_taxTypeEnum);

            //1.18 createNewOrderId( )
            long l_lngOrderId = l_orderManager.createNewOrderId();
            
            //1.19 setTimestamp( )
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //1.20 submitNewOrder
    		//[submitNewOrderに渡すパラメタ] 
    		//補助口座：　@取得した補助口座オブジェクト 
    		//銘柄タイプ： ProductTypeEnum.投資信託 
    		//新規注文内容： 拡張投信新規注文内容 
    		//注文ID： 取得した注文ID 
    		//取引パスワード： 
    		//WEB3Crypt.decrypt()の戻り値を設定する。 
    		//［decryptに渡すパラメタ] 
    		//暗号化文字列： 
            //取得した顧客オブジェクト.getTradingPassword()の戻り値 
    		//is発注審査省略： true
            //取引パスワード
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            
            //拡張投信注文マネージャ.submitNewOrder() の戻り値   
            OrderSubmissionResult l_orderSubmissionResult = 
                l_orderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.MUTUAL_FUND,
                    l_mutualFundNewOrderSpec,
                    l_lngOrderId,
                    l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);
            
            //1.21 ＜拡張投信注文マネージャ.submitNewOrder()の戻り値
            //.getProcessingResult().isSuccessfulResult()==falseの場合＞
            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult() == false)
             {
                 log.debug("該当拡張投信注文マネージャ.submitNewOrder()の戻り値" +
                    ".getProcessingResult().isSuccessfulResult()" +
                    "==falseの場合。");
                 throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "新規注文失敗");
             }
            
            //1.22 余力再計算(補助口座 : 補助口座)
			WEB3GentradeSubAccount l_genSubAccount = 
			    (WEB3GentradeSubAccount) l_subAccount;
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
				(WEB3TPTradingPowerReCalcService) Services.getService(
					WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_genSubAccount); 
	        log.exiting(STR_METHOD_NAME);
			
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (cancel注文)<BR>
     * 注文取消処理を行う。<BR>
     * <BR>
     * シーケンス図「（投信売買注文通知）注文取消」 参照。
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「（投信売買注文通知）注文取消」: <BR>
     *    1.2.5(戻り値 == ”選択指定” の場合、例外をスローする、<BR>
     *       例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_02297<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_orderNotifyQueueParams - 投信注文通知キューParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void cancelOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "cancelOrder(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)" ;
        log.entering(STR_METHOD_NAME);
        
        //1.1 
		//以下の条件で、注文単位を取得する。
		//[条件]
		//CPUNo = 引数.投信注文通知キューParams.CPUNo
        List l_lisRowsMfOrderUnit = new ArrayList();
        String l_strWhereA = "cpu_no = ? ";
        Object[] l_bindVarsA = {l_orderNotifyQueueParams.getCpuNo()}; 
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRowsMfOrderUnit = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhereA,
                null, 
                l_bindVarsA);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        //1.2.1 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
		//顧客オブジェクトを取得する。 
		//[引数] 
		//証券会社コード： 引数.投信注文通知キューParams.会社コード 
		//部店コード： 引数.投信注文通知キューParams.部店コード 
		//口座コード： 引数.投信注文通知キューParams.顧客コード 
        
        //拡張アカウントマネージャの取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount) l_accMgr.getMainAccount(
                l_orderNotifyQueueParams.getInstitutionCode(), 
                l_orderNotifyQueueParams.getBranchCode(), 
                l_orderNotifyQueueParams.getAccountCode());
        
        //1.2.2 is信用口座開設(弁済区分 : String)
		//信用口座を開設しているかどうかをチェックする。 
		//[引数] 
		//弁済区分： ”指定なし” 
		boolean l_blnisMarginAccountEstablished =
			l_mainAccount.isMarginAccountEstablished(
			    WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //1.2.3 getSubAccount(arg0 : SubAccountTypeEnum)
		//補助口座オブジェクトを取得する。 
		//[引数] 
		//arg0： （以下のとおり） 

	    WEB3MutualFundProduct l_mutualFundProduct =  null;
	    SubAccount l_subAccount = null;
		try
        {
    		//is信用口座開設()の戻り値==trueの場合、
		    //SubAccountTypeEnum.保証金口座 
			if (l_blnisMarginAccountEstablished)
            {
                l_subAccount = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
			//is信用口座開設=false の場合、
			//SubAccountTypeEnum.株式取引口座（預り金）
			else
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
			}
			
			//1.2.4 get投信銘柄(Institution, String)
			//拡張投信銘柄マネージャを取得する。
			//銘柄オブジェクトを取得する。 
			//[引数] 
			//証券会社： 取得した顧客.getInstitution()の戻り値 
			//銘柄コード： 引数.投信注文通知キューParams.銘柄コード 
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            l_mutualFundProduct = 
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_mainAccount.getInstitution(), 
                    l_orderNotifyQueueParams.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2.5  get指定方法@（募集）
        //戻り値 == ”選択指定” の場合、例外をスローする。
        if(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
            l_mutualFundProduct.getRecruitSpecityDiv()))
        {
			log.debug("指定方法@（募集）エラー(”選択指定”を指定不可)。");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02297,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"指定方法@（募集）エラー(”選択指定”を指定不可)。");
        }
        
        //1.2 (*)注文単位が取得できなかった場合（WEBからの注文の場合）
        if(l_lisRowsMfOrderUnit == null || l_lisRowsMfOrderUnit.size() == 0)
        {
            //1.2.6
    		//以下の条件で、注文単位を取得する。
    		//[条件]
    		//口座ID = 取得した顧客.getAccontId()の戻り値
    		//補助口座ID = 取得した補助口座.getSubAccountId()の戻り値
    		//銘柄ID = 取得した銘柄.getProductId()の戻り値
    		//注文種別 = ”募集”
    		//注文状態 != ”発注失敗（新規注文）” or ”発注済（取消注文）”
    		//入金日 = 引数.投信注文通知キューParams.入金日
    		//注文数量 = （以下のとおり）
    		//get指定方法@（募集）の戻り値 == ”金額指定” の場合、
            //引数.投信注文通知キューParams.概算金額
    		//get指定方法@（募集）の戻り値 == ”口数指定” の場合、
            //(*)取得した拡張投信銘柄.get入力単位()の戻り値が”1：1”の場合
            //引数.投信注文通知キューParams.売買数量
            //(*)取得した拡張投信銘柄.get入力単位()の戻り値が”2：1万”の場合
            //引数.投信注文通知キューParams.売買
    		//[ソート順]
    		//受注日時の昇順
    		//※複数件取得できた場合は、先頭のもの（受注日時が古いもの）とする。
            String l_strWhereB = 
                "account_id = ? and sub_account_id = ? and product_id = ? and order_type = ? " +
                "and (order_status != ? and order_status != ?)  " +
                "and payment_date = ? and quantity = ?";
            
            //注文数量
            double l_dblOrderQuantity = 0.0D;
            //get指定方法@（募集）の戻り値 == ”口数指定” の場合
            if(WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                l_mutualFundProduct.getRecruitSpecityDiv()))
            {
                if ((WEB3InputUnitDef.ONE).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity();
                } 
                else if ((WEB3InputUnitDef.TEN_THOUSAND).equals(
                    l_mutualFundProduct.getInputUnit()))
                {
                    l_dblOrderQuantity = 
                        l_orderNotifyQueueParams.getQuantity() * 10000;
                }
            }
    		//get指定方法@（募集）の戻り値 == ”金額指定” の場合、 
            //引数.投信注文通知キューParams.概算金額
            else if ((WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE).equals(
                l_mutualFundProduct.getRecruitSpecityDiv())) 
            {
                l_dblOrderQuantity = 
                    l_orderNotifyQueueParams.getEstimatedPrice();
            }
            String l_strSortKey = "received_date_time";
            
            Object[] l_bindVarsB = 
                {new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId()),
                OrderTypeEnum.MF_RECRUIT, 
                OrderStatusEnum.NOT_ORDERED, 
                OrderStatusEnum.CANCELLED,
                l_orderNotifyQueueParams.getPaymentDate(),
                new Double(l_dblOrderQuantity)};   
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRowsMfOrderUnit = l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereB,
                    l_strSortKey,
                    null, 
                    l_bindVarsB);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            
            if (l_lisRowsMfOrderUnit == null || l_lisRowsMfOrderUnit.size() == 0)
            {
    			log.debug("テーブルに該当するデータがありません。");
    			throw new WEB3SystemLayerException(
    				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
    				this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        MutualFundOrderUnitParams l_mfOrderUnitParams = 
            (MutualFundOrderUnitParams) l_lisRowsMfOrderUnit.get(0);
        
        //1.3 投信受付確定インタセプタ( )
        WEB3MutualFundAcceptConfirmInterceptor 
        	l_mfAcceptConfirmInterceptor = 
        	    new WEB3MutualFundAcceptConfirmInterceptor();
        
        //1.4 set注文エラー理由コード(String)
		//エラー理由コードを設定する。 
		//[引数] 
		//エラーコード： "0000"（正常） 
        l_mfAcceptConfirmInterceptor.setOrderErrorReasonCode(
            WEB3GftErrorReasonCodeDef.NORMAL);

	    //1.5 setThreadLocalPersistenceEventInterceptor(
        //arg0 : MutualFundOrderManagerPersistenceEventInterceptor)
        //拡張投信注文マネージャ取得
        WEB3MutualFundOrderManager l_orderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_mfAcceptConfirmInterceptor);

        //1.6 DefaultCancelOrderAcceptedMarketResponseMessage(arg0 : long)
        DefaultCancelOrderAcceptedMarketResponseMessage 
        	l_defaultMarketResponseMessage =
        	    new DefaultCancelOrderAcceptedMarketResponseMessage(
        	        l_mfOrderUnitParams.getOrderId());
        
        //1.7 process(arg0 : CancelOrderAcceptedMarketResponseMessage)
        MarketAdapter l_marketAdapter =
            l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getMarketAdapter();
        MutualFundMarketResponseReceiverCallbackService l_marketCallbackService =
            (MutualFundMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_processingResylt = 
            l_marketCallbackService.process(l_defaultMarketResponseMessage);
        if (l_processingResylt.isFailedResult())
        {
            log.debug("注文受付処理失敗である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00395,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "注文受付処理失敗である");   
        }   

        //1.8 余力再計算(補助口座 : 補助口座)
        // [余力再計算に渡すパラメタ] 
        // 　@補助口座：取得した補助口座オブジェクト
		WEB3GentradeSubAccount l_genSubAccount = 
		    (WEB3GentradeSubAccount) l_subAccount;
        WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        l_tpTradingPowerReCalcService.reCalcTradingPower(l_genSubAccount);
        log.exiting(STR_METHOD_NAME);
    }
}
@
