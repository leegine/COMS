head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資売買注文通知サービス実装クラス(WEB3RuitoTradeOrderNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 杜 森 (中訊) 新規作成
                   2004/12/08 韋念瓊 (中訊) 残対応
Revesion History : 2007/12/21 馮海濤 (中訊) 仕様変更 No095
Revesion History : 2007/12/26 武波 (中訊) 仕様変更 No096
Revesion History : 2009/01/23 王志葵 (中訊) 実装の問題 No.002
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CourseDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoNewOrderSpec;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.define.WEB3RuitoOrderDivTypeDef;


/**
 * 累投売買通知１件サービス実装クラス <BR>
 * <BR>
 * 注文１件ごとの累投売買通知処理を実施する。<BR>
 */
public class WEB3RuitoTradeOrderNotifyUnitServiceImpl 
    implements WEB3RuitoTradeOrderNotifyUnitService 
{

    final String STR_METHOD_NAME = "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";
    /**
    * ログ出力ユーティリティ。<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40C41C390157
     */
    public WEB3RuitoTradeOrderNotifyUnitServiceImpl()
    {

    }

    /**
     * 累投売買注文通知処理を行う。<BR>
     * <BR>
     * シーケンス図「（累投売買注文通知）notify売買注文通知」参照。<BR>
     * <BR>
     * 　@－拡張累投注文マネージャ.submitNewOrder()の戻り値判定 <BR>
     * 　@　@　@拡張累投注文マネージャ.submitNewOrder()の戻り値. <BR>
     *             getProcessingResult().isSuccessfulResult()==false <BR>
     * 　@        　@の場合、例外をスローする。 <BR>
     *             class    : WEB3BusinessLayerException <BR>
     *             tag      : BUSINESS_ERROR_00191 <BR>
     * @@param l_orderNotifyCueParams - 累投注文通知キューParams <BR>
     * @@param l_tradeOrderNotifyDecisionInterceptor - 累投売買注文通知確定インタセプタ 
     * <BR>
     * @@roseuid 408F4C930023
     */
    public void notifyTradeOrderNotify(
        HostRuitoOrderNotifyParams l_hostOrderNotifyParams,
        WEB3RuitoTradedOrderNotifyDecisionInterceptor 
        l_tradeOrderNotifyDecisionInterceptor)
        throws WEB3BaseException
    {        
        String STR_METHOD_NAME = 
            "HostRuitoOrderNotifyParams l_hostOrderNotifyParams," + 
            "WEB3RuitoTradedOrderNotifyDecisionInterceptor " + 
            "l_tradeOrderNotifyDecisionInterceptor)";
        
        if (l_hostOrderNotifyParams == null)
        { 
            log.debug("パラメータ値がNULL");
            throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "該当パラメータにNull値は設定できません。");
        }
        if (l_tradeOrderNotifyDecisionInterceptor == null)
        { 
            log.debug("パラメータ値がNULL");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "該当パラメータにNull値は設定できません。");
        }
        log.entering(STR_METHOD_NAME);
        Institution l_institution = null;
        Branch l_branch = null;
         
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accManager = l_finApp.getAccountManager();


        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        //顧客コードの取得
        String l_lngAccountId = l_hostOrderNotifyParams.getAccountCode();
        log.debug("顧客コードの取得=" + l_lngAccountId);
        
        //NotFoundException
        try
        {       
            //1.1 証券会社オブジェクトを取得する。
            l_institution = l_accMgr.getInstitution
                (l_hostOrderNotifyParams.getInstitutionCode());
            log.debug("証券会社オブジェクトを取得=" + 
                l_institution.getInstitutionCode());
            
            //1.2 部店オブジェクトを取得する。
            l_branch =  l_accMgr.getBranch(
                l_institution,l_hostOrderNotifyParams.getBranchCode());
            log.debug("部店オブジェクトを取得=" + 
                l_branch.getBranchCode());    
             
            //1.3 顧客オブジェクトを取得する。
            MainAccount l_mainAccount;
            l_mainAccount = l_accManager.getMainAccount
                (l_institution,l_branch,l_lngAccountId);
            log.debug("顧客オブジェクト=" + 
                l_mainAccount.getAccountId());     
            
            //1.4 補助口座オブジェクトを取得する。 
            SubAccount l_subAccount;
            l_subAccount = l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            log.debug("補助口座オブジェクトを取得=" + 
                l_subAccount.getSubAccountId());        
           
            //1.5 累投タイプを取得する。 
            RuitoTypeEnum l_ruitoTypeEnum = null;
            l_ruitoTypeEnum = this.getRuitoType(l_hostOrderNotifyParams);
            log.debug("累投タイプを取得する=" + 
                l_ruitoTypeEnum.toString());             
            
            //1.6 累投解約区分を取得する。
            String l_strRuitoSellDiv = this.getRuitoSellDivision(
                    l_hostOrderNotifyParams);
            log.debug("累投解約区分を取得する=" + 
                l_strRuitoSellDiv);            

            WEB3RuitoProductManager l_ruitoProductManager =
               (WEB3RuitoProductManager) l_finApp.getTradingModule(
                   ProductTypeEnum.RUITO).getProductManager();
            
            WEB3RuitoPositionManager l_ruitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();
            
            //1.7 拡張累投銘柄オブジェクトを取得する。 
            WEB3RuitoProduct l_ruitoProduct = (WEB3RuitoProduct)
                l_ruitoProductManager.getRuitoProductWithCoursePlan(
                    l_institution,
                    l_hostOrderNotifyParams.getCourse(),
                    l_hostOrderNotifyParams.getPlan());
            log.debug("拡張累投銘柄オブジェクトを取得する=" + 
                l_ruitoProduct.getProductCode());
            
            //1.8 全部解約時、注文数量を算出する。
            //累投注文通知キューParams.get注文種別()の戻り値が「1：全部解約」の場合に、
            //get解約可能残高()をコールして解約可能残高を算出する。  
            double l_dblOrderQuantity = 0;
            if (WEB3RuitoOrderDivTypeDef.ALL_SELL.equals(
                    l_hostOrderNotifyParams.getOrderDiv()))
            {

                l_dblOrderQuantity = 
                    l_ruitoPositionManager.getSellPossibleBalance(
                        l_subAccount, l_ruitoProduct);
            }
            else
            {
                l_dblOrderQuantity = l_hostOrderNotifyParams.getAmount();
            }   
            
            //1.9 注文処理 
            //引数.累投売買注文通知確定インタセプタを累投注文マネージャに設定する。       
            WEB3RuitoOrderManager l_ruitoOrderManager =   
                (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();   
                       
            l_ruitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_tradeOrderNotifyDecisionInterceptor);
                
            log.debug("引数.累投売買注文通知確定イン" +
                 "タセプタを累投注文マネージャに設定する。");    
            
            //1.10 累投売買注文通知確定インタセプタに受渡日を設定する。
            Timestamp l_deliveryDate = l_hostOrderNotifyParams.getDeliveryDate();
            l_tradeOrderNotifyDecisionInterceptor.setDeliveryDate(l_deliveryDate);
            log.debug("受渡日="+
                l_tradeOrderNotifyDecisionInterceptor.getDeliveryDate());
            
            //1.11 累投売買注文通知確定インタセプタに受注日時を設定する。
            Timestamp l_acceptedDate = l_hostOrderNotifyParams.getOrderDate();
            l_tradeOrderNotifyDecisionInterceptor.setAcceptedDate(l_acceptedDate);
            log.debug("受注日時="+
                l_tradeOrderNotifyDecisionInterceptor.getAcceptedDate());
            
            //1.12 累投売買注文通知確定インタセプタに識別コードを設定する。
            String l_strRequestNumber = l_hostOrderNotifyParams.getOrderRequestNumber();
            l_tradeOrderNotifyDecisionInterceptor.setRequestNumber(
                l_strRequestNumber);
            log.debug("識別コード="+
                l_tradeOrderNotifyDecisionInterceptor.getRequestNumber());
            
            //1.13 累投売買注文通知確定インタセプタに受渡方法@を設定する
            String l_strPaymentMethod = 
                l_hostOrderNotifyParams.getPaymentMethod();
            l_tradeOrderNotifyDecisionInterceptor.setPaymentMethod(
                l_strPaymentMethod);
            log.debug("受渡方法@"+
                l_tradeOrderNotifyDecisionInterceptor.getPaymentMethod());
            
            //1.14 累投売買注文通知確定インタセプタに累投タイプを設定する。
            l_tradeOrderNotifyDecisionInterceptor.setRuitoTypeEnum(
                l_ruitoTypeEnum);
            log.debug("累投タイプ"+
                l_tradeOrderNotifyDecisionInterceptor.getRuitoTypeEnum());
            
            //1.15 累投売買注文通知確定インタセプタに累投解約区分を設定する。
            l_tradeOrderNotifyDecisionInterceptor.setRuitoSellDiv(
                l_strRuitoSellDiv);
            log.debug("解約区分"+
                l_tradeOrderNotifyDecisionInterceptor.getRuitoSellDiv());
            
            //1.16 累投売買注文通知確定インタセプタに注文チャネルを設定する。
            String l_strOrderChannel = l_hostOrderNotifyParams.getChannel();
            l_tradeOrderNotifyDecisionInterceptor.setOrderChannel(
                 l_strOrderChannel);
            log.debug("注文チャネル"+
                l_tradeOrderNotifyDecisionInterceptor.getOrderChannel());
            
            //1.17 累投市場リクエスト送信サービスに、市場送信処理を実施するという設定を行う
            TradingModule l_tradingModule = l_finApp.getTradingModule(
                ProductTypeEnum.RUITO);
              MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
              WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarReq =
                  (WEB3RuitoMarketRequestSubmitServiceImpl)
                  l_marketAdapter.getMarketRequestSenderServce();        
            boolean isMarketSubmit = true;
            
            l_ruitoMarReq.setMarketSubmit(isMarketSubmit);                       
    
            // 注文数量： 引数.累投注文通知キューParams.get注文種別()の戻り値 <BR>
            
            //注文数量タイプ
            QuantityTypeEnum l_quantityType = this.getOrderQuantityType(
                    l_hostOrderNotifyParams,l_ruitoProduct);
            // 税区分
            TaxTypeEnum l_taxType = TaxTypeEnum.UNDEFINED;     
           
            //1.18 拡張累投新規注文内容を生成する。 
            WEB3RuitoNewOrderSpec l_ruitoNewOrderSpec;
            if(WEB3RuitoOrderDivTypeDef.BUY.equals(
                    l_hostOrderNotifyParams.getOrderDiv()))
            {
                l_ruitoNewOrderSpec =  
                    new WEB3RuitoNewOrderSpec(
                        null, //扱者
                        true, //is買付                                          
                        l_ruitoProduct.getProductCode(), //銘柄コード                                          
                        l_dblOrderQuantity, //注文数量
                        l_quantityType,//注文数量タイプ 
                        l_taxType); //税区分

                 log.debug("拡張累投新規注文内容を生成する。 ");
                 log.debug("注文種別=" + l_hostOrderNotifyParams.getOrderDiv());
                 log.debug("扱者=null");
                 log.debug("is買付=true");
                 log.debug("銘柄コード=" + l_ruitoProduct.getProductCode());
                 log.debug("注文数量=" + l_dblOrderQuantity);
                 log.debug("注文数量タイプ=" + l_quantityType);
                 log.debug("税区分=" + l_taxType);                                                          
            }
            else
            {   
                l_ruitoNewOrderSpec = new WEB3RuitoNewOrderSpec(
                    null, //扱者
                    false, //is買付                                          
                    l_ruitoProduct.getProductCode(), //銘柄コード                                          
                    l_dblOrderQuantity, //注文数量
                    l_quantityType,//注文数量タイプ 
                    l_taxType); //税区分
                
                log.debug("新規注文内容の生成");
                log.debug("注文種別=" + l_hostOrderNotifyParams.getOrderDiv());
                log.debug("扱者=null");
                log.debug("is買付=false");
                log.debug("銘柄コード=" + l_ruitoProduct.getProductCode());
                log.debug("注文数量=" + l_dblOrderQuantity);
                log.debug("注文数量タイプ=" + l_quantityType);
                log.debug("税区分=" + l_taxType);                          
            }

            
            //1.19 拡張累投注文マネージャ.createNewOrderId()をコールして注文IDを取得する。  
            
            long l_lngOrderId = 0;
            l_lngOrderId = l_ruitoOrderManager.createNewOrderId();
            log.debug("注文ID = " + l_lngOrderId);
            
            //1.20 取引パスワード
            String l_strPassword = l_mainAccount.getTradingPassword();
            log.debug("取引パスワード = " + l_strPassword);            
            
            log.debug("subAccount.getInstitution()" + 
                l_subAccount.getInstitution().getInstitutionCode());
            log.debug("spec.getProductCode()" +
                l_ruitoNewOrderSpec.getProductCode());
            log.debug("spec.getIssueCode() = " +
                l_ruitoNewOrderSpec.getIssueCode());
            log.debug("spec.getMarketCode() = " +
                l_ruitoNewOrderSpec.getMarketCode());
            
            //1.21 －取引時間管理.setTimestamp()をコールする。 
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //1.22 注文データ作成   
            OrderSubmissionResult l_orderSubmissionResult = null;
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_orderSubmissionResult = l_ruitoOrderManager.submitNewOrder(
                        l_subAccount,
                        ProductTypeEnum.RUITO,
                        l_ruitoNewOrderSpec,
                        l_lngOrderId,
                        l_crypt.decrypt(l_strPassword), 
                        true);
                                                               
            //拡張累投注文マネージャ.submitNewOrder()の戻り値判定 
            boolean l_blnResult;             
            l_blnResult = 
                l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
            if(!l_blnResult)
            {
                log.debug("__新規注文失敗__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "新規注文失敗");
            }
            
			// －余力再計算サービスを取得し、余力再計算()をコールする。 
			// [余力再計算に渡すパラメタ] 
			// 　@補助口座：取得した補助口座オブジェクト
			WEB3GentradeSubAccount l_genSubAccount =(WEB3GentradeSubAccount) l_subAccount;
			WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService = 
				(WEB3TPTradingPowerReCalcService) Services.getService(
					WEB3TPTradingPowerReCalcService.class);
			l_tpTradingPowerReCalcService.reCalcTradingPower(l_genSubAccount);     
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("拡張累投取引銘柄がない");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00250,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get累投タイプ) <BR>
     * 累投注文通知キューParams.getコース()の戻り値より、<BR>
     * 累投タイプ（RuitoTypeEnum）を赴pする。<BR>
     * <BR>
     * １） 中国Fのセット<BR>
     * 　@コース＝’G’の場合、RuitoTypeEnum.”中国F”を返却する。<BR>
     * <BR>
     * ２） MMFのセット<BR>
     * 　@コース＝’T’の場合、RuitoTypeEnum.”MMF”を返却する。<BR>
     * @@param l_ruitoOrderNotifyCueParams - 累投注文通知キューParams <BR>
     * @@return RuitoTypeEnum
     * @@roseuid 408F53D601A2
     */
    public RuitoTypeEnum getRuitoType(HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = 
            "getRuitoSellDivision(HostRuitoOrderNotifyParams)";
        if (l_ruitoOrderNotifyCueParams == null)
        { 
            log.debug("引数=null");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strRuitoCourse = l_ruitoOrderNotifyCueParams.getCourse();
        RuitoTypeEnum l_ruitoTypeEnum = null;
        
        if(WEB3CourseDef.MEDIUM_TERM_NATIONAL_BONDS_FUNDS.equals(
            l_strRuitoCourse))
        {
            l_ruitoTypeEnum = RuitoTypeEnum.CHUUKOKU_FUND;//中国Fのセット
        }
        
        if(WEB3CourseDef.MMF.equals(l_strRuitoCourse))
        {
            l_ruitoTypeEnum = RuitoTypeEnum.MMF;//MMFのセット
        }
        log.exiting(STR_METHOD_NAME); 
        return l_ruitoTypeEnum;
    }

    /**
     * (get累投解約区分) <BR>
     * 累投注文通知キューParams.get注文種別()の戻り値が”買付”以外の場合、<BR>
     * 累投注文通知キューParams．get注文種別()の戻り値、<BR>
     * 累投注文通知キューParams.get指定区分()の戻り値より、<BR>
     * 累投解約区分を返却する。<BR>
     * <BR>
     * <BR>
     * １） 全部解約のセット<BR>
     * <BR>
     * 　@　@注文種別＝’１’の場合、 ’２’（全部指定）を返却する。<BR>
     * <BR>
     * ２） 口数指定のセット <BR>
     * <BR>
     * 　@　@注文種別＝’３’の場合、<BR>
     *           指定区分＝’１’（口数）なら、’４’（口数指定）を返却する。<BR>
     * <BR>
     * ３） 金額指定のセット<BR>
     * <BR>
     * 　@　@注文種別＝’３’の場合、<BR>
     *           指定区分≠’１’なら、’３’（金額指定）を返却する。<BR>
     * @@param l_ruitoOrderNotifyCueParams - 累投注文通知キューParams <BR>
     * @@return java.lang.String
     * @@roseuid 408F53D601A4
     */
    public String getRuitoSellDivision(HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams) throws WEB3BaseException
    {        
       
        String STR_METHOD_NAME = 
            "getRuitoSellDivision(HostRuitoOrderNotifyParams)";
        if (l_ruitoOrderNotifyCueParams == null)
        { 
            log.debug("引数=null");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                    this.getClass().getName() + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);
        String l_orderType = l_ruitoOrderNotifyCueParams.getOrderDiv();
        String l_orderDivision = l_ruitoOrderNotifyCueParams.getSpecifyDiv();
        String l_sellDiv = null;
        
        
        log.debug("l_orderType="+l_orderType);
        if(WEB3RuitoOrderDivTypeDef.ALL_SELL.equals
            (l_orderType))
        {
            
            l_sellDiv = WEB3SellDivDef.ALL_DESIGNATE;

        }

        if(WEB3RuitoOrderDivTypeDef.PARTIALLY_SELL.equals(
           l_orderType) &&  "1".equals(l_orderDivision))
        {
            l_sellDiv = WEB3SellDivDef.COUNT_DESIGNATE;
        }
        if(WEB3RuitoOrderDivTypeDef.PARTIALLY_SELL.equals(
           l_orderType) &&  !"1".equals(l_orderDivision))
        {
            l_sellDiv = WEB3SellDivDef.MONEY_DESIGNATE;
        } 
        log.exiting(STR_METHOD_NAME); 
        return l_sellDiv;        
    }

    /**
     * (get注文数量タイプ) <BR>
     * 注文数量タイプを返す。<BR>
     * <BR>
     * １）　@引数.累投注文通知キューParams.get注文種別()の戻り値が”1：<BR>
     *             全部解約”の場合は、以下の処理を行う。<BR>
     * <BR>
     * 　@－引数.拡張累投銘柄.get指定方法@（解約）()の戻り値が”0：<BR>
     *             選択指定”の場合はQuantityTypeEnum.金額を返す。<BR>
     * 　@－引数.拡張累投銘柄.get指定方法@（解約）()の戻り値が”3：<BR>
     *             金額指定”の場合はQuantityTypeEnum.金額を返す。<BR>
     * 　@－引数.拡張累投銘柄.get指定方法@（解約）()の戻り値が”4：<BR>
     *             口数指定”の場合はQuantityTypeEnum.数量を返す。<BR>
     * <BR>
     * ２）　@引数.累投注文通知キューParams.get注文種別()の戻り値が”1：<BR>
     *             全部解約”ではない場合は、以下の処理を行う。<BR>
     * <BR>
     * 　@－引数.累投注文通知キューParams.get指定方法@()の戻り値が”1：<BR>
     *             口数”の場合はQuantityTypeEnum.数量を、<BR>
     *             それ以外はQuantityTypeEnum.金額を返す。<BR>
     * @@param l_ruitoOrderNotifyCueParams - 累投注文通知キューParams <BR>
     * @@param l_expansionRuitoProduct - 拡張累投銘柄 <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum
     * @@roseuid 40A3366E00AF
     */
    public QuantityTypeEnum getOrderQuantityType(
        HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams,
        WEB3RuitoProduct l_ruitoProduct) throws WEB3BaseException
    {        
        String STR_METHOD_NAME = 
            "getOrderQuantityType(HostRuitoOrderNotifyParams," +
            "WEB3RuitoProduct)";
        if (l_ruitoOrderNotifyCueParams == null)
        { 
            log.debug("引数=null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_ruitoProduct == null)
        { 
            log.debug("引数=null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.entering(STR_METHOD_NAME);       
        String l_orType = l_ruitoOrderNotifyCueParams.getOrderDiv();
        String l_strPaymentMethod = l_ruitoProduct.getPaymentMethodSell();
        QuantityTypeEnum l_quantityType = null;
        
        //１）　@引数.累投注文通知キューParams.get注文種別()の戻り値が”
        //1：全部解約”の場合
        if (WEB3RuitoOrderDivTypeDef.ALL_SELL.equals(l_orType))
        {                     
            log.debug("l_orTypeA=" + l_orType);
            log.debug("l_strPaymentMethod=" + l_strPaymentMethod);
             if (WEB3DesignateMethodDef.SELECT.equals(l_strPaymentMethod))
             {
                 l_quantityType = QuantityTypeEnum.AMOUNT;
                 //return QuantityTypeEnum.AMOUNT;
             }
             else if (WEB3DesignateMethodDef.AMOUNT.equals(
                        l_strPaymentMethod))
             {
                 l_quantityType = QuantityTypeEnum.AMOUNT;
                 //return QuantityTypeEnum.AMOUNT;
             }
             else if (WEB3DesignateMethodDef.NUMBER.equals(
                        l_strPaymentMethod))
             {
                 l_quantityType = QuantityTypeEnum.QUANTITY;
                 //return QuantityTypeEnum.QUANTITY;
             }
         }
         //２）　@引数.累投注文通知キューParams.get注文種別()の戻り値が”1：
         //全部解約”ではない場合
         else 
         {             
             String l_Method = l_ruitoOrderNotifyCueParams.getSpecifyDiv();
             if ("1".equals(l_Method))
             {
                 l_quantityType = QuantityTypeEnum.QUANTITY;
                 //return QuantityTypeEnum.QUANTITY;
             }
             else
             {
                 l_quantityType = QuantityTypeEnum.AMOUNT;      
             }
         }
         log.exiting(STR_METHOD_NAME);     
         return l_quantityType;   
    }

}

@
