head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : OP新規建入力サービスImpl(WEB3OptionOpenContractInputServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/15 呉艶飛 (中訊) 新規作成
                            :2004/07/23  呉艶飛 (中訊) 新規修正
                 001: 2004/07/31 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000095、96
                 002: 2004/08/13 王暁傑 対応バグ BUG94
                 003: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応                 
                 004: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000083)を対応
                 005: 2006/07/14 唐性峰  (中訊) モデルNo.466を対応
Revesion History    : 2007/06/11 張騰宇　@(中訊) モデル 661
Revesion History    : 2007/06/21 孟亜南　@(中訊) モデル 712
Revesion History    : 2007/06/27 張騰宇　@(中訊) モデル 767
Revesion History    : 2007/06/28 張騰宇　@(中訊) モデル 769
Revesion History    : 2007/11/20 トウ鋒鋼　@(中訊) モデル 792
Revesion History    : 2008/07/25 陸文静　@(中訊) モデル No.889，890，894
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;

import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoIndexMaster;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.IfoIndexMasterRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.WEB3OptionClientRequestService;

/**
 * (OP新規建入力サービスImpl)<BR>
 * <BR>
 * 株価指数オプション新規建入力サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionOpenContractInputServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionOpenContractInputService
{
    /**
      * Logger
      */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3OptionOpenContractInputServiceImpl.class);

    /**
     * @@roseuid 40C0C0A9029F
     */
    public WEB3OptionOpenContractInputServiceImpl()
    {

    }

    /**
     * 株価指数オプション新規建入力サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、create入力画面または、create銘柄選択画面をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A521802AF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3OptionsOpenMarginInputRequest)
        {        
            l_response = this.createInputScreen((WEB3OptionsOpenMarginInputRequest) l_request);
        }
        else if (l_request instanceof WEB3OptionsProductSelectRequest)
        {         
            l_response = this.createProductSelectScreen((WEB3OptionsProductSelectRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }

    /**
     * (create入力画面)<BR>
     * <BR>
     * 株価指数オプション新規建入力画面を表示する。<BR>
     * <BR>
     * 「(OP新規建入力)入力画面表示データ取得」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse
     * @@roseuid 409B5298028A
     */
    public WEB3OptionsOpenMarginInputResponse createInputScreen(WEB3OptionsOpenMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createInputScreen(WEB3OptionsOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.validate();
            
            //補助口座を取得する。
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            WEB3GentradeMainAccount l_genTradeMainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
                (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
           
            //validate注文を呼び出す。
            WEB3OptionOrderManagerImpl l_ifoOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            l_ifoOrderManagerImpl.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = 
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();  
                                        
            //validate取扱可能新規建注文
            //補助口座： get補助口座()の返り値 
            //is買建：　@リクエスト．建区分が”買建”の場合はtrue、”売建”の場合はfalse            
            boolean l_blnIsBuy = WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType) ? true : false;
            l_ifoOrderManagerReusableValidations.validateHandlingOpenContractOrder(l_subAccount, l_blnIsBuy);
            
            Institution l_institution = l_subAccount.getInstitution();

            //部店
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
                        
            WEB3IfoProductImpl l_ifoProductImpl = null;
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =null; 
            Market l_market = null;
            
            //銘柄コードがnullでない場合は、以下の処理を実施する
            if (l_request.opProductCode != null)
            {
                //get銘柄(証券会社 : Institution, 銘柄コード : String)
                //[引数] 
                //証券会社：　@補助口座．get証券会社()の返り値 
                //銘柄コード：　@リクエスト．銘柄コード
                try
                {
                    l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                        l_institution, 
                        l_request.opProductCode);
                }
                catch (NotFoundException l_nfex)    
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);
                }   

                if (l_ifoProductImpl == null)   
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);
                }   

                l_market = l_ifoProductImpl.getPrimaryMarket(); 

                //get取引銘柄
                //[引数] 
                //証券会社：　@補助口座．get証券会社()の返り値 
                //銘柄コード：　@リクエスト．銘柄コード 
                //市場コード： 市場．getMarketCode()の返り値
                try
                {
                    l_ifoTradedProductImpl = l_ifoProductManagerImpl.getIfoTradedProduct(
                        l_institution, 
                        l_request.opProductCode, 
                        l_market.getMarketCode()); 
                }
                catch (NotFoundException l_nfex)    
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);
                }   
                if (l_ifoTradedProductImpl == null) 
                {   
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);
                }   

                //validate取扱可能指数
                //[引数] 
                //部店コード： 補助口座．get取引店()．getBranchCode()の返り値 
                //先物OP取引銘柄： 先物OP取引銘柄オブジェクト 
                l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                    l_branch.getBranchCode(), 
                    l_ifoTradedProductImpl);                
            }
            
            //発注日           
            Date l_datBizDate = null;           
            Date l_datTmpBizDate = null;            

            // 以下のリクエストデータがnullでない場合は以下の処理を実施する            
            if (l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.strikePrice != null 
                && l_request.delivaryMonth != null 
                && l_request.opProductType != null)           
            {         
                IfoDerivativeTypeEnum l_ifoDerivativeTypeEnum = null;     
                if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))       
                {     
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.CALL_OPTIONS; 
                }     
                else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))       
                {     
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.PUT_OPTIONS;  
                }     
                
                double l_dblStrikePrice = 0;      
                if (l_request.strikePrice != null && !"".equals(l_request.strikePrice))       
                {     
                    l_dblStrikePrice = Double.parseDouble(l_request.strikePrice); 
                }     
                
                try       
                {     
                //先物OP銘柄オブジェクトを取得する。      
                l_ifoProductImpl=     
                l_ifoProductManagerImpl.getIfoProduct(        
                    l_institution,    
                    l_request.targetProductCode,  
                    l_request.delivaryMonth,  
                    l_ifoDerivativeTypeEnum,  
                    l_dblStrikePrice, 
                    WEB3DivisionTypeDef.DIVISION_DEFAULT, 
                    l_request.marketCode);    
                }     
                catch (NotFoundException l_nfex)      
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);   
                }     
                
                if (l_ifoProductImpl == null)     
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                        STR_METHOD_NAME);   
                }     
                
                //取引時間管理.reset銘柄コード(先物OP指数マスタオブジェクト.原資産銘柄コード)をコールする。      
                WEB3GentradeTradingTimeManagement.resetProductCode(l_request.targetProductCode);      
                
                l_datTmpBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();        
                //発注日を取得      
                if (l_datBizDate == null || l_datTmpBizDate.before(l_datBizDate))     
                {     
                    l_datBizDate = l_datTmpBizDate;   
                }     
                try       
                {     
                //先物OP取引銘柄オブジェクトを取得する。        
                l_ifoTradedProductImpl =      
                l_ifoProductManagerImpl.getIfoTradedProduct(      
                    l_institution,    
                    l_request.marketCode, 
                    l_request.targetProductCode,  
                    l_request.delivaryMonth,  
                    l_ifoDerivativeTypeEnum,  
                    l_dblStrikePrice, 
                    WEB3DivisionTypeDef.DIVISION_DEFAULT, 
                    l_request.marketCode);    
                }     
                catch (NotFoundException l_nfex)      
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);   
                }     
                
                if (l_ifoTradedProductImpl == null)       
                {     
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                        STR_METHOD_NAME);   
                }     
                
                // validate取扱可能指数  
                //[引数]        
                //部店コード： 補助口座．get取引店()．getBranchCode()の返り値        
                //先物OP取引銘柄： 先物OP取引銘柄オブジェクト        
                l_ifoOrderManagerReusableValidations.validateHandlingIndex(       
                    l_branch.getBranchCode(),   
                    l_ifoTradedProductImpl);                    
            }         

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            
            Double  l_dblTradingPower = null;
            //※リクエスト.建区分 == ”買建”の場合のみ実施する
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
            {
				if ((l_request.opProductCode !=null)||  
				(l_request.marketCode != null 
				&& l_request.targetProductCode != null 
				&& l_request.strikePrice != null 
				&& l_request.delivaryMonth != null 
				&& l_request.opProductType != null))

				{
				    l_dblTradingPower = l_tradingPowerService.getOptionBuyTradingPower(l_subAccount, l_ifoProductImpl);  
				}else
				{
					l_dblTradingPower = l_tradingPowerService.getOptionBuyTradingPower(l_subAccount, null);  
				}
                              
            }
            //※リクエスト.建区分 == ”売建”の場合のみ実施する。
            else
            {
				if ((l_request.opProductCode !=null)||  
				(l_request.marketCode != null 
				&& l_request.targetProductCode != null 
				&& l_request.strikePrice != null 
				&& l_request.delivaryMonth != null 
				&& l_request.opProductType != null))

				{
                    l_dblTradingPower = l_tradingPowerService.getFuturesOptionTradingPower(
                        l_subAccount, 
                        false, 
                        l_ifoProductImpl);
				}
				else
				{
					l_dblTradingPower = l_tradingPowerService.getFuturesOptionTradingPower(
						l_subAccount, 
						false, 
						null);
				}
            }

            //閉局間近の市場コードを取得する。
            //[引数] 
            //部店：　@補助口座．get取扱店() 
            //銘柄タイプ：　@先物/オプション                   
            String[] l_tradeCloseMarkets = 
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_branch, 
                    WEB3FuturesOptionDivDef.OPTION);
            
            //[取扱可能注文条件()に指定する引数] 
            //証券会社コード： 
            //証券会社 = 補助口座.get証券会社() 
            //証券会社コード = 証券会社.get証券会社コード() 
            //銘柄タイプ(="先物オプション")：　@ProductTypeEnum 
            //先物／オプション区分(="オプション")：　@String
            WEB3GentradeHandlingOrderCond l_handingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_institution.getInstitutionCode(), 
                ProductTypeEnum.IFO, 
                WEB3FuturesOptionDivDef.OPTION, 
                WEB3MarginTradingDivDef.DEFAULT);

            if (l_request.opProductCode != null
                || (l_request.marketCode != null
                    && l_request.targetProductCode != null
                    && l_request.strikePrice != null
                    && l_request.delivaryMonth != null
                    && l_request.opProductType != null))
            {
                //set取引最終日(取引最終日 : Date)
                l_handingOrderCond.setTradingEndDate(l_ifoTradedProductImpl.getLastTradingDate());
            }

            //メッセージ 取扱可能注文単価区分取得
            //[引数] 
            //is新規建：　@true(:新規建) 
            //is買建：　@リクエスト．建区分が”買建”の場合はtrue、”売建”の場合はfalse。
            String[] l_orderUnitDivision = null;
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
            {
                l_orderUnitDivision = l_handingOrderCond.getHandlingPossibleOrderPriceDiv(true, true);
            }
            else
            {
                l_orderUnitDivision = l_handingOrderCond.getHandlingPossibleOrderPriceDiv(true, false);
            }
            
            //取扱可能執行条件取得( )
            String[] l_strPossibleExecCond = l_handingOrderCond.getHandlingPossibleExecCond();
			l_strPossibleExecCond=l_ifoOrderManagerImpl.getHandlingPossibleExecConds(l_orderUnitDivision,l_strPossibleExecCond);

            //取扱可能注文期限区分を取得する
            String[] l_strHandlingPossible = l_handingOrderCond.getHandlingPossibleExpirationDateType();

            //取扱可能発注条件を取得する            
            String[] l_strHandingPossibleOrderCond = l_handingOrderCond.getHandlingPossibleOrderCond();

            //(部店指数別)取扱条件オブジェクトを取得する
            WEB3GentradeBranchIndexDealtCond[] l_handLingCondBranchIndex;
            l_handLingCondBranchIndex =
                WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                    l_institution.getInstitutionCode(), l_branch.getBranchCode(), WEB3FuturesOptionDivDef.OPTION);

            int l_length = 0;
            if (l_handLingCondBranchIndex != null)
            {
                l_length = l_handLingCondBranchIndex.length;
            }

            //市場一覧
            ArrayList l_lisMarkets = new ArrayList();
            HashMap l_hsmMarkets = new HashMap();

            //指数種別一覧
            ArrayList l_lisUnderlyingProducts = new ArrayList();
            HashMap l_hsmUnderlyingProducts = new HashMap();

            String l_strMatketCode = null;
            String l_strUnderlyingProductCode = null;

            for (int i = 0; i < l_length; i++)
            {
                //先物OP指数マスタオブジェクトを取得する 
                WEB3IfoIndexMaster l_indexMaster = new WEB3IfoIndexMaster(l_handLingCondBranchIndex[i].getUnderlyingProductCode(), WEB3FuturesOptionDivDef.OPTION); //DateFindException

                //取引時間管理.reset銘柄コード(先物OP指数マスタオブジェクト.原資産銘柄コード)をコールする。
                WEB3GentradeTradingTimeManagement.resetProductCode(((IfoIndexMasterRow) l_indexMaster.getDataSourceObject()).getUnderlyingProductCode());

                l_datTmpBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                //発注日を取得
                if (l_datBizDate == null || l_datTmpBizDate.before(l_datBizDate))
                {
                    l_datBizDate = l_datTmpBizDate;
                }

                l_strMatketCode = ((BranchIndexDealtCondRow) l_handLingCondBranchIndex[i].getDataSourceObject()).getMarketCode();
                l_strUnderlyingProductCode = l_handLingCondBranchIndex[i].getUnderlyingProductCode();

                if (!l_hsmMarkets.containsKey(l_strMatketCode))
                {
                    //重複しない市場を取得
                    l_hsmMarkets.put(l_strMatketCode, l_strMatketCode);
                    l_lisMarkets.add(l_strMatketCode);
                }

                if (!l_hsmUnderlyingProducts.containsKey(l_strUnderlyingProductCode))
                {
                    //重複しない指数種別を取得
                    l_hsmUnderlyingProducts.put(l_strUnderlyingProductCode, l_strUnderlyingProductCode);
                    l_lisUnderlyingProducts.add(l_strUnderlyingProductCode);
                }

            }

            //限月一覧
            List l_lisMonthOfDeliverys = new ArrayList();

            //引数の取扱可能な原資産銘柄コード、先物/オプション区分に該当する、限月項目を取得する。
            l_lisMonthOfDeliverys = l_ifoProductManagerImpl.getDeliveryMonthList(
                l_handLingCondBranchIndex,
                WEB3FuturesOptionDivDef.OPTION);

            //注文開始日
            Date l_datStartDay = null;

            //注文最終日
            Date l_datEndDay = null;

            //注文期限内祝日一覧
            Date[] l_datHoliday = null;

            //is出来るまで注文取扱可能<取引最終日考慮>( )がtrueの場合は以下の処理を実施する
            if (l_handingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                if (l_request.opProductCode == null)
                {
                    //get出来るまで注文開始日<取引最終日考慮>(Date)
                    l_datStartDay =
                        l_handingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(l_datBizDate);

                    //get出来るまで注文最終日<取引最終日考慮>(Date)
                    l_datEndDay =
                        l_handingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(l_datBizDate);

                    // get注文期限内祝日一覧(Date)
                    l_datHoliday = l_handingOrderCond.getExpirationDateHoliday(l_datBizDate);
                }
                else
                {
                    //get出来るまで注文開始日<取引最終日考慮>(Date)
                    l_datStartDay = l_handingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);

                    //get出来るまで注文最終日<取引最終日考慮>(Date)
                    l_datEndDay = l_handingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);

                    // get注文期限内祝日一覧(Date)
                    l_datHoliday = l_handingOrderCond.getExpirationDateHoliday();
                }
            }

            //市場一覧
            String[] l_strMarkertsList = new String[l_lisMarkets.size()];
            l_lisMarkets.toArray(l_strMarkertsList);

            //限月一覧
            String[] l_strMonthOfDeliverys = new String[l_lisMonthOfDeliverys.size()];
            l_lisMonthOfDeliverys.toArray(l_strMonthOfDeliverys);

            //指数種別一覧
            String[] l_strUnderlyingProductsList = new String[l_lisUnderlyingProducts.size()];
            l_lisUnderlyingProducts.toArray(l_strUnderlyingProductsList);

            //時価
            double l_dblCurrentPrice = 0;

            //時価(現在値)　@
            Timestamp l_currentPriceTime = null;

            //前日比
            double l_dblChange = 0;
            
            // 以下のいずれかにあてはまる場合実施する。    
            // １）リクエスト.銘柄コード!=null 
            // ２）以下のリクエストデータ!=null 
            // リクエスト.取引市場    
            // リクエスト.指数種別    
            // リクエスト.限月  
            // リクエスト.オプション区分 
            // リクエスト.行使価格    
            if ((l_request.opProductCode !=null)||  
                (l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.strikePrice != null 
                && l_request.delivaryMonth != null 
                && l_request.opProductType != null))
            {       
                FinApp finApp = (FinApp) Services.getService(FinApp.class);
                WEB3QuoteDataSupplierService l_quoteDataSupplierService = (WEB3QuoteDataSupplierService) finApp.getTradingModule(ProductTypeEnum.IFO).getQuoteDataSupplierService();

                l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.DELAY);
                WEB3IfoQuoteData l_quoteDataImpl = null;

                if (l_genTradeMainAccount.isRealCustomer())
                {
                    l_quoteDataImpl = l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.REAL);
                }
                else
                {
                    l_quoteDataImpl = l_quoteDataSupplierService.getIfoQuote(l_ifoTradedProductImpl, RealType.DELAY);
                }

                //時価
                l_dblCurrentPrice = l_quoteDataImpl.getCurrentPrice();
                log.debug("l_dblCurrentPrice = " + l_quoteDataImpl.getCurrentPrice());
                
                //時価(現在値)時刻を取得する
                l_currentPriceTime = l_quoteDataImpl.getCurrentPriceTime();

                //前日比を取得する
                l_dblChange = l_quoteDataImpl.getChange();
            }

            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = (WEB3OptionsOpenMarginInputResponse) l_request.createResponse();
            //取扱可能注文単価区分取得
            l_marginInputResponse.orderPriceDivList = l_orderUnitDivision;

            //取扱可能執行条件取得
            l_marginInputResponse.execCondList = l_strPossibleExecCond;

            //取扱可能注文期限区分取得
            l_marginInputResponse.expirationDateTypeList = l_strHandlingPossible;

            //レスポンス.有効期限開始日＝get出来るまで注文開始日<取引最終日考慮>の返り値
            l_marginInputResponse.expirationStartDate = WEB3DateUtility.toDay(l_datStartDay);

            //レスポンス.有効期限最終日＝get出来るまで注文最終日<取引最終日考慮>の返り値
            l_marginInputResponse.expirationEndDate = WEB3DateUtility.toDay(l_datEndDay);

            //get注文期限内祝日一覧
            l_marginInputResponse.holidayList = l_datHoliday;

            //取扱可能発注条件取得の返り値
            l_marginInputResponse.orderCondTypeList = l_strHandingPossibleOrderCond;
            
            //レスポンス.Ｗ指値用執行条件一覧 = 先物OPデータアダプタ.getＷ指値用執行条件一覧()の返り値
            l_marginInputResponse.wlimitExecCondList = 
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(l_strPossibleExecCond, l_strHandingPossibleOrderCond);

            //レスポンス.立会区分 = 取引時間管理.get立会区分()
            l_marginInputResponse.sessionType = WEB3GentradeTradingTimeManagement.getSessionType();

            //レスポンス．新規建可能額　@＝　@
            //・リクエスト.建区分が買建の場合、getオプション新規買建可能額の返り値　@※戻り値がマイナス値の場合は0に設定
            //・リクエスト.建区分が売建の場合、get先物オプション新規建可能額の返り値　@※戻り値がマイナス値の場合は0に設定
            double l_dblPower = 0;
            if (l_dblTradingPower != null)
            {
                l_dblPower = l_dblTradingPower.doubleValue();

                if (l_dblPower < 0)
                {
                    l_dblPower = 0;
                }

                //レスポンス．新規建可能額　@＝　@getオプション新規建可能額の返り値 ※戻り値がマイナス値の場合は0に設定
                l_marginInputResponse.opTradingPower = WEB3StringTypeUtility.formatNumber(l_dblPower);
            }
            else
            {
                l_marginInputResponse.opTradingPower = null;
            }
            
            //get市場閉局警告指数の返り値
            l_marginInputResponse.messageSuspension = l_tradeCloseMarkets;

            //レスポンス．取引市場一覧　@＝　@(部店指数別)取扱条件オブジェクト．市場コード　@
            l_marginInputResponse.marketList = l_strMarkertsList;

            //レスポンス．指数種別一覧　@＝　@(部店指数別)取扱条件オブジェクト．原資産銘柄コード
            l_marginInputResponse.targetProductList = l_strUnderlyingProductsList;

            //レスポンス．限月一覧　@＝　@先物OPプロダクトマネージャ．get限月一覧（）の返り値
            l_marginInputResponse.delivaryMonthList = l_strMonthOfDeliverys;
            
            //)分岐フロー以下のリクエストデータがnullでない場合は以下の処理を実施する
            if (l_request.marketCode != null 
                && l_request.targetProductCode != null 
                && l_request.strikePrice != null 
                && l_request.delivaryMonth != null 
                && l_request.opProductType != null)
            {
                //取引銘柄                
                l_marginInputResponse.opProductCode = ((IfoProductRow) l_ifoTradedProductImpl.getProduct().getDataSourceObject()).getProductCode();

                //取引市場
                l_marginInputResponse.marketCode = l_request.marketCode;

                //指数種別    
                l_marginInputResponse.targetProductCode = l_request.targetProductCode;

                //限月
                l_marginInputResponse.delivaryMonth = l_request.delivaryMonth;

                //オプション商品区分
                l_marginInputResponse.opProductType =  l_request.opProductType;

                //行使価格
                l_marginInputResponse.strikePrice = l_request.strikePrice;

                //getCurrentPriceの返り値                                   
                //時価が0の場合、nullを設定する                                 
                if (l_dblCurrentPrice == 0D)                                    
                {                                   
                    l_marginInputResponse.currentPrice = null;                                       
                }                                   
                else                                    
                {                                   
                    //時価が0でない場合、取得した現在値を設定する                                     
                    l_marginInputResponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                      
                }                                   

                //レスポンス．取引時間　@＝　@getCurrentPriceTimeの返り値
                l_marginInputResponse.currentPriceTime = l_currentPriceTime;

                //レスポンス．前日比　@＝　@getChangeの返り値
                l_marginInputResponse.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
            }
            else
            {
                if (l_request.opProductCode == null)
                {
                    //※銘柄コードがnullの場合はnullをセット
                    //レスポンス．getProductCode()
                    l_marginInputResponse.opProductCode = null;
                    
                    //レスポンス．原資産銘柄コード    
                    l_marginInputResponse.targetProductCode = null;
                    
                    //レスポンス．市場コード
                    l_marginInputResponse.marketCode = null;
                    
                    //先物OP銘柄オブジェクト．限月　@
                    l_marginInputResponse.delivaryMonth = null;
                    
                    //先物OP銘柄オブジェクト．行使価格
                    l_marginInputResponse.strikePrice = null;
                    
                    //レスポンス．オプション商品区分   ＝　@null
                    l_marginInputResponse.opProductType = null;
                    
                    //レスポンス．現在値　@＝　@null
                    l_marginInputResponse.currentPrice = null;

                    //レスポンス．取引時間 = ＝　@null
                    l_marginInputResponse.currentPriceTime = null;

                    //レスポンス．前日比　@＝　@null
                    l_marginInputResponse.comparedPreviousDay = null;
                }
                else
                {
                    //レスポンス．getProductCode()
                    l_marginInputResponse.opProductCode = l_ifoProductImpl.getProductCode();

                    //レスポンス．指数種別   
                    l_marginInputResponse.targetProductCode = l_ifoProductImpl.getUnderlyingProductCode();

                    //レスポンス．市場コード
                    l_marginInputResponse.marketCode = l_market.getMarketCode();

                    //レスポンス．限月　@
                    l_marginInputResponse.delivaryMonth = l_ifoProductImpl.getMonthOfDelivery();

                    //レスポンス．行使価格
                    l_marginInputResponse.strikePrice = WEB3StringTypeUtility.formatNumber(l_ifoProductImpl.getStrikePrice());
                    
                    //レスポンス．オプション商品区分 = 
                    //先物OP銘柄オブジェクト.先物オプション商品＝”コールオプション”の場合は、”C”をセット。”プットオプション”の場合は、”P”をセット。
                    if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(
                        l_ifoProductImpl.getDerivativeType()))
                    {
                        l_marginInputResponse.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                    }
                    else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
                    {
                        l_marginInputResponse.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                    } 
                    
                    //getCurrentPriceの返り値                                   
                    //時価が0の場合、nullを設定する                                 
                    if (l_dblCurrentPrice == 0D)                                    
                    {                                   
                        l_marginInputResponse.currentPrice = null;                                       
                    }                                   
                    else                                    
                    {                                   
                        //時価が0でない場合、取得した現在値を設定する                                     
                        l_marginInputResponse.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);                                      
                    }                                   

                    //レスポンス．取引時間　@＝　@getCurrentPriceTimeの返り値
                    l_marginInputResponse.currentPriceTime = l_currentPriceTime;
                    
                    //レスポンス．前日比　@＝　@getChangeの返り値
                    l_marginInputResponse.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return l_marginInputResponse;
        }
        catch (DataQueryException l_edqx)
        {
            log.error("DBへのアクセスに失敗しました", l_edqx);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, STR_METHOD_NAME, 
                l_edqx.toString(), 
                l_edqx);
        }
        catch (DataNetworkException l_edw)
        {
            log.error("DBへのアクセスに失敗しました。", l_edw);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                STR_METHOD_NAME, 
                l_edw.toString(), 
                l_edw);
        }
        catch (NotFoundException l_enf)
        {
            log.error("データ不整合エラー", l_enf);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                STR_METHOD_NAME, 
                l_enf.toString(), 
                l_enf);
        }
    }

    /**
     * (create銘柄選択画面)<BR>
     * <BR>
     * 株価指数オプション新規建銘柄選択画面を表示する。<BR>
     * <BR>
     * 「(OP新規建入力)銘柄選択画面表示データ取得」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsOrderCarryOverResponse.WEB3OptionsProductSelectResponse
     * @@roseuid 409B52CB01B0
     */
    protected WEB3OptionsProductSelectResponse createProductSelectScreen(WEB3OptionsProductSelectRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductSelectScreen(WEB3OptionsProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //メッセージ validate( )
        l_request.validate();

        //補助口座を取得する。
        SubAccount l_subAccount = getSubAccount();    

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //validate注文を呼び出す。
        WEB3OptionOrderManagerImpl l_ifoOrderManagerImpl = (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        l_ifoOrderManagerImpl.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();

        //閉局間近の市場コードを取得する。(部店, ProductTypeEnum, String)
        String[] l_tradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.OPTION);

        //取得証券会社
        Institution l_institution = l_subAccount.getInstitution();

        //(部店指数別)取扱条件オブジェクトを取得する     
        WEB3GentradeBranchIndexDealtCond[] l_handLingCondBranchIndex;

        l_handLingCondBranchIndex =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                l_institution.getInstitutionCode(), l_branch.getBranchCode(), WEB3FuturesOptionDivDef.OPTION);

        log.debug("l_institution.getInstitutionCode() = " + l_institution.getInstitutionCode());
        log.debug("l_branch.getBranchCode() = " + l_branch.getBranchCode());

        int l_intLength = 0;

        if (l_handLingCondBranchIndex != null)
        {
            l_intLength = l_handLingCondBranchIndex.length;
        }

        //市場一覧
        ArrayList l_lisMarkets = new ArrayList();
        HashMap l_hsmMarkets = new HashMap();

        //指数種別一覧
        ArrayList l_lisUnderlyingProducts = new ArrayList();
        HashMap l_hsmUnderlyingProducts = new HashMap();

        String l_strMatketCode = null;
        String l_strUnderlyingProductCode = null;

        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        for (int i = 0; i < l_intLength; i++)
        {
            l_strMatketCode = ((BranchIndexDealtCondRow) l_handLingCondBranchIndex[i].getDataSourceObject()).getMarketCode();
            l_strUnderlyingProductCode = l_handLingCondBranchIndex[i].getUnderlyingProductCode();
            if (!l_hsmMarkets.containsKey(l_strMatketCode))
            {
                //重複しない市場を取得
                l_hsmMarkets.put(l_strMatketCode, l_strMatketCode);
                l_lisMarkets.add(l_strMatketCode);
            }

            if (!l_hsmUnderlyingProducts.containsKey(l_strUnderlyingProductCode))
            {
                //重複しない指数種別を取得
                l_hsmUnderlyingProducts.put(l_strUnderlyingProductCode, l_strUnderlyingProductCode);
                l_lisUnderlyingProducts.add(l_strUnderlyingProductCode);
            }

        }

        //引数の取扱可能な原資産銘柄コード、先物/オプション区分に該当する、限月項目を取得する。
        List l_lisDeliveryMonths = l_ifoProductManagerImpl.getDeliveryMonthList(
            l_handLingCondBranchIndex,
            WEB3FuturesOptionDivDef.OPTION);
        //レスポンスデータを生成する。
        WEB3OptionsProductSelectResponse l_marginSelectResponse = (WEB3OptionsProductSelectResponse) l_request.createResponse();

        //市場一覧
        String[] l_strMarkertsList = new String[l_lisMarkets.size()];
        l_lisMarkets.toArray(l_strMarkertsList);

        //限月一覧
        String[] l_strMonthOfDeliverys = new String[l_lisDeliveryMonths.size()];
        l_lisDeliveryMonths.toArray(l_strMonthOfDeliverys);

        //指数種別一覧
        String[] l_strUnderlyingProductsList = new String[l_lisUnderlyingProducts.size()];
        l_lisUnderlyingProducts.toArray(l_strUnderlyingProductsList);

        //レスポンス．新規建可能額　@＝　@null
        l_marginSelectResponse.opTradingPower = null;
        //レスポンス．取引市場一覧　@＝　@(部店指数別)取扱条件オブジェクト．市場コード
        l_marginSelectResponse.marketList = l_strMarkertsList;
        //レスポンス．指数種別一覧　@＝　@(部店指数別)取扱条件オブジェクト．原資産銘柄コード
        l_marginSelectResponse.targetProductList = l_strUnderlyingProductsList;
        //レスポンス．限月一覧　@＝　@先物OPプロダクトマネージャ．get限月一覧（）の返り値
        l_marginSelectResponse.delivaryMonthList = l_strMonthOfDeliverys;
        //レスポンス．取引終了警告文言　@＝　@get市場閉局警告指数の返り値
        l_marginSelectResponse.messageSuspension = l_tradeCloseMarkets;

        return l_marginSelectResponse;

    }
}
@
