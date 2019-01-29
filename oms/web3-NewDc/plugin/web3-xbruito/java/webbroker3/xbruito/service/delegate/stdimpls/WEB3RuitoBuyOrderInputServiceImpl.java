head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文入力サービス実装クラス(WEB3RuitoBuyOrderInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 杜 森 (中訊) 新規作成
                   2004/12/08 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3StopDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.define.WEB3RuitoBuyPossibleDivDef;
import webbroker3.xbruito.message.WEB3RuitoBuyInputResponse;
import webbroker3.xbruito.message.WEB3RuitoProductCodeNameUnit;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderInputService;


/**
 * 累積投資買付注文入力サービス実装クラス<BR>
 */
public class WEB3RuitoBuyOrderInputServiceImpl extends WEB3RuitoClientRequestService implements WEB3RuitoBuyOrderInputService 
{
   
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3RuitoBuyOrderInputServiceImpl.class);   
  
   /**
    * 累積投資買付注文入力サービス処理を実施する。<BR>
    * <BR>
    * シーケンス図「累投買付注文入力／（累投）買付注文入力」参照<BR>
    * <BR>
    * @@param l_request - リクエストデータ
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 406932820270
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
       throws WEB3BaseException 
       {     
           final String STR_METHOD_NAME =
               "execute(WEB3GenRequest l_request)";
    
          log.entering(STR_METHOD_NAME);                    
          if (l_request == null)
          { 
              log.debug("パラメータ値がNULL");
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  "パラメータ値がNULL");
          }
    
        //1.1 　@補助口座取得
          SubAccount l_subAccount = this.getSubAccount();
         log.debug("補助口座取得" + l_subAccount.getSubAccountId() + "");
          
        //1.2 注文チェックオブジェクトを取得する
         FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         CommonOrderValidator l_gentradeOrderValidator = 
             l_finApp.getCommonOrderValidator();
         log.debug("注文チェックオブジェクトを取得する");
                   
        //1.3　@顧客別取引停止属性チェック
        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("__Error[validate取引可能顧客をチェック]__");
            throw new WEB3SystemLayerException(
                  l_orderValidationResult.getProcessingResult().getErrorInfo(),
                  this.getClass().getName() + "." + STR_METHOD_NAME, 
                  "顧客別取引停止属性チェックエラーの場合"); 
        }    
        log.debug("顧客別取引停止属性チェック");
        
        
        //1.4 累投発注審査個別チェックのインスタンスを取得する   
        WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderCheckt = 
            (WEB3RuitoOrderManagerReusableValidationsCheck)
                WEB3RuitoOrderManagerReusableValidationsCheck.getInstance(); 
        log.debug("累投発注審査個別チェックのインスタンスを取得する");                     
     
        //1.5　@累積投資取引口座チェック 
        try
        {
            l_ruitoOrderCheckt.validateRuitoTradedAccountEstablish(l_subAccount);
            log.debug("累積投資取引口座チェック");
        }
        catch(OrderValidationException e)
        {
            log.error("累積投資取引口座チェックエラー");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "累積投資取引口座チェックエラー");               
        }
    
        //　@受付時間チェック、システム取引停止チャック 
        boolean l_blnMidiumFundValidFlag = true;
        boolean l_blnMMFValidFlag = true;
        int l_intFundError = 0;
        int l_intMmfError = 0;
        
        //1.6 －中期国債ファ@ンドの注文受付可能チェックを行う。 
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug(
                "中期国債ファ@ンドの注文受付可能チェックを行う。validate注文受付可能"); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("中期国債ファ@ンド 受付時間エラー");
            l_blnMidiumFundValidFlag = false;  
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 3;
            }
        }        
        
        //－取引時間管理.reset受付時間区分()をコールし、受付時間区分を中期国債ファ@ンドから 
        //  MMFに変更する。
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.MMF_SET);
        
        //受付日時、日付ロールをセットする        
        WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
                      
        //－MMFの注文受付可能チェックを行う。 
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("MMFの注文受付可能チェックを行う");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("MMF 受付時間エラー");
            l_blnMMFValidFlag = false;  
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 3;
            }
        }    
        
        //変数.中国ファ@ンド買付可能区分
        String l_strChuukokuFundBuyPossdiv = null;
        
        //変数.MMF買付可能区分
        String l_strMmfBuyPossdiv = null;
        
        //－中期国債ファ@ンドとMMFの両方、またはいずれかで例外が返された場合、以下を行う。 
        if (!l_blnMidiumFundValidFlag || !l_blnMMFValidFlag)
        {
            //(1)中期国債ファ@ンドの注文のチェックの場合 
            if (!l_blnMidiumFundValidFlag)                    
            {
                //・「バッチ処理中」の例外が返された場合
                //  変数.中国ファ@ンド買付可能区分に”システム取引停止中”をセットする。
                //・「緊急停止中」の例外が返された場合
                //  変数.中国ファ@ンド買付可能区分に”システム取引停止中”をセットする。
                if (l_intFundError == 1 || l_intFundError == 2)
                {
                    l_strChuukokuFundBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //・「受付不可時間エラー」の例外が返された場合
                //  変数.中国ファ@ンド買付可能区分に”受付時間エラー”をセットする。 
                else if (l_intFundError == 3)
                {
                    l_strChuukokuFundBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //・例外が返されなかった場合、変数.中国ファ@ンド買付可能区分にNULLをセットする。 
                else
                {
                    l_strChuukokuFundBuyPossdiv = null;
                }
            }
            //(2)MMFの注文のチェックの場合 
            if (!l_blnMMFValidFlag)
            {
                //　@・受付時間エラーの例外が返された場合、 
                //  変数.MMF買付可能区分に”受付時間エラー”をセットする。 
                if (l_intMmfError == 1 || l_intMmfError == 2)
                {
                    l_strMmfBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //・システム取引停止中の例外が返された場合、 
                //  変数.MMF買付可能区分に”システム取引停止中”をセットする。 
                else if (l_intMmfError == 3)
                {
                    l_strMmfBuyPossdiv = 
                        WEB3RuitoBuyPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //・例外が返されなかった場合、変数.MMF買付可能区分にNULLをセットする。
                else
                {
                    l_strMmfBuyPossdiv = null;
                }
            }            
        } 
        //1.12　@銘柄一覧の取得
        //－拡張累投銘柄マネージャ.get累投銘柄一覧( )をコールする。
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        List l_lisRuitoProduct = 
            l_ruitoProductManager.getRuitoProductList(
                l_subAccount.getInstitution().getInstitutionCode());
        
        //－銘柄コード名称一覧の作成
        WEB3RuitoProductCodeNameUnit[] l_ruitoProductCodeNames = 
            new WEB3RuitoProductCodeNameUnit[l_lisRuitoProduct.size()];
        
        //1.13 get累投銘柄一覧( )の戻り値の件数分、繰り返し累投銘柄コード名称の配列を作成する。
        for (int i = 0; i < l_lisRuitoProduct.size(); i++)
        {
            RuitoProductRow l_ruitoProductRow = 
                (RuitoProductRow)l_lisRuitoProduct.get(i);        
            
            l_ruitoProductCodeNames[i] = new WEB3RuitoProductCodeNameUnit();
    
            l_ruitoProductCodeNames[i].ruitoProductCode = 
                l_ruitoProductRow.getProductCode();
            l_ruitoProductCodeNames[i].ruitoProductName = 
                l_ruitoProductRow.getStandardName();
        
            //－拡張累投銘柄マネージャ.get累投取引銘柄( )をコールし、累投取引銘柄オブジェクトを取得する。        
            RuitoTradedProduct l_ruitoTradedProduct = null;
            try
            {
                l_ruitoTradedProduct = 
                    l_ruitoProductManager.getRuitoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_ruitoProductRow.getProductCode());
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
            
            RuitoTradedProductRow l_ruitoTradedProductRow = 
                (RuitoTradedProductRow)l_ruitoTradedProduct.getDataSourceObject();
            String l_strBuyPossDiv = null;
            
            WEB3RuitoProduct l_ruitoProduct = null;
            try
            {
                l_ruitoProduct = (WEB3RuitoProduct) 
                    l_ruitoProductManager.getRuitoProduct(l_ruitoProductRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                    
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
           
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                
            //－累投銘柄Params.累投タイプ=”MMF”の場合は”MMF（設定）”を、累投銘柄Params.累投タイプ=”中国F”の 
            //場合は”中国F”を引数に、reset注文受付商品( )をコール。 
            if (RuitoTypeEnum.MMF.equals(l_ruitoProductRow.getRuitoType()))
            {
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3TradingTimeTypeDef.MMF_SET);
                //－取引時間管理.setTimestamp( )をコール。
                WEB3GentradeTradingTimeManagement.setTimestamp();
                
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getBuyStop()))
                {
                    //(1)取得した累投取引銘柄オブジェクト.get買付停止( )＝”停止中”の場合、 
                    //”緊急停止中”をセットする。 
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.SCRAM_STOPING;
                }
                else if (!l_ruitoProduct.isBuyPossible(l_datBizDate))
                {
                    //(2)拡張累投銘柄.is買付可能( )=falseの場合、”取引停止中”をセットする。 
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.TRADING_STOPING;
                }
                else if (l_strMmfBuyPossdiv != null)
                {
                    //(3)受付時間エラー、システム停止中エラーのチェック                 
                    //－累投銘柄Params.get銘柄タイプ＝”MMF”の場合、変数.MMF買付可能区分!=NULLの場合、
                    //変数.MMF買付可能区分をセットする。        
                    l_strBuyPossDiv = l_strMmfBuyPossdiv;
                }
                else
                {
                    //(4)上記(1)(2)(3)のいずれにも該当せず、かつ 
                    //変数.MMF買付可能区分がNULLの場合NULLをセットする。 
                    l_strBuyPossDiv = null;
                }
            }
            else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoProductRow.getRuitoType()))
            {
                WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(
                        WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                //(1)取引時間管理.setTimestamp( )をコール。
                WEB3GentradeTradingTimeManagement.setTimestamp();
                
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getBuyStop()))
                {
                    //－取得した累投取引銘柄オブジェクト.get買付停止( )＝”停止中”の場合、 
                    //”緊急停止中”をセットする。  
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.SCRAM_STOPING;
                }                         
                else if (!l_ruitoProduct.isBuyPossible(l_datBizDate))
                {
                    //(2)拡張累投銘柄.is買付可能( )=falseの場合、”取引停止中”をセットする。 
                    l_strBuyPossDiv = WEB3RuitoBuyPossibleDivDef.TRADING_STOPING;
                }                
                //(3)受付時間エラー、システム停止中エラーのチェック 
                //－累投銘柄Params.get銘柄タイプ＝”中期国債ファ@ンド”の場合であり、かつ 
                //  変数.中国ファ@ンド買付可能区分!=NULLの場合、変数.中国ファ@ンド買付可能区分をセットする。
                else if (l_strChuukokuFundBuyPossdiv != null)
                {
                    l_strBuyPossDiv = l_strChuukokuFundBuyPossdiv;
                }
                else
                {
                    //(4)上記(1)(2)(3)のいずれにも該当せず、かつ 
                    //変数.中国ファ@ンド買付可能区分がNULLの場合NULLをセットする。 
                    l_strBuyPossDiv = null;
                }
            }            
            //買付可能区分
            l_ruitoProductCodeNames[i].buyPosDiv = l_strBuyPossDiv;
            
        }
        //　@買付可能額取得 
        double l_dblTradingPower = 0;
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        //(1) 発注日を取得する 
        Date l_datBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //(2)　@受渡日を取得する 
        Date l_datDeliveryDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
    
        //1.14 取引余力サービス.getその他商品買付可能額()をコールし、買付可能額を取得する。 
        l_dblTradingPower = l_tpTradingPowerService.getOtherTradingPower(
            (WEB3GentradeSubAccount)l_subAccount,
            l_datDeliveryDate);
        
        //1.15　@指定方法@取得     
        int l_lngOrderInputCount = l_lisRuitoProduct.size();
        List l_listRuitoProduct = new ArrayList(0);
        for(int k=0; k < l_lngOrderInputCount; k++)
        {
            String l_productParams;
             
            RuitoProductParams l_RuitoProductParams = 
                (RuitoProductParams)l_lisRuitoProduct.get(k);
            l_productParams = l_RuitoProductParams.getBuyDesignateMethod();
            
            if(l_productParams != null)
            {
                //金額指定が可能
                if (l_productParams.equals(WEB3DesignateMethodDef.AMOUNT))
                {
                    if(l_listRuitoProduct.size() == 0)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);                                                   
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.AMOUNT)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);
                    }
                }
            
                //口数指定が可能
                if (l_productParams.equals(WEB3DesignateMethodDef.NUMBER))
                {
                    if(l_listRuitoProduct.size() == 0)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);                                                   
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.NUMBER)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);
                    }                                              
                }
            
                //金額指定と口数指定の両方が可能
                if (l_productParams.equals(WEB3DesignateMethodDef.SELECT))
                {
                    if(l_listRuitoProduct.size() == 0)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.AMOUNT)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.AMOUNT);
                    }
                    if(l_listRuitoProduct.size() == 1 && l_listRuitoProduct.get(0) != 
                        WEB3DesignateMethodDef.NUMBER)
                    {
                        l_listRuitoProduct.add(WEB3DesignateMethodDef.NUMBER);
                    } 
                                                                
                }
            }
        }
                              
        //累投買付注文入力レスポンスオブジェクト生成
        WEB3GenResponse l_response = l_request.createResponse();
        WEB3RuitoBuyInputResponse l_ruitoBuyInputResponse 
                   = (WEB3RuitoBuyInputResponse)l_response;
                   
        log.debug("累投買付注文入力レスポンスオブジェクト生成");
        //銘柄コード名称一覧
        List l_listtoarray = new ArrayList();
        for(int k = 0; k < l_lngOrderInputCount; k++)
        {
            RuitoProductParams l_RuitoProductParams = 
                 (RuitoProductParams)l_lisRuitoProduct.get(k);
            WEB3RuitoProductCodeNameUnit l_WEB3RuitoProductCodeName = 
                new WEB3RuitoProductCodeNameUnit();
            
            l_WEB3RuitoProductCodeName.ruitoProductCode = 
                l_RuitoProductParams.getProductCode();
            
            log.debug("l_RuitoProductParams.getProductCode() =" + 
                    l_RuitoProductParams.getProductCode());
            
            l_WEB3RuitoProductCodeName.ruitoProductName = 
                l_RuitoProductParams.getStandardName();
            
            log.debug("l_RuitoProductParams.getStandardName() =" + 
                    l_RuitoProductParams.getStandardName());
            
            l_WEB3RuitoProductCodeName.buyPosDiv = l_ruitoProductCodeNames[k].buyPosDiv;

            log.debug("l_ruitoProductCodeNames[" + k + "].buyPosDiv =" + 
                l_ruitoProductCodeNames[k].buyPosDiv);
            
            l_listtoarray.add(l_WEB3RuitoProductCodeName);
                
        }
        if(l_listtoarray != null)
        {
            int l_lsize = l_listtoarray.size();
            WEB3RuitoProductCodeNameUnit[] l_temp = 
                new WEB3RuitoProductCodeNameUnit[l_lsize];
            for(int i = 0; i < l_lsize; i++)
            {
                l_temp[i] = (WEB3RuitoProductCodeNameUnit)l_listtoarray.get(i);
            }       
            l_ruitoBuyInputResponse.ruitoProductCodeNames = l_temp;
        }
        else
        {
            l_ruitoBuyInputResponse.ruitoProductCodeNames = null;
        }
        log.debug("銘柄コード名称一覧");
        for(int i = 0; i < l_ruitoBuyInputResponse.ruitoProductCodeNames.length; i ++)
        {
            log.debug("銘柄コード名称一覧" + i + "productCode=" + 
                    l_ruitoBuyInputResponse.ruitoProductCodeNames[i].ruitoProductCode);
            log.debug("銘柄コード名称一覧" + i + "productName=" + 
                    l_ruitoBuyInputResponse.ruitoProductCodeNames[i].ruitoProductName);
        }
    
        
        //買付可能金額
    
        // ----------------- Start
        // Modify by Alan wang 2004/08/13 according to formating type double to type String
    //    l_ruitoBuyInputResponse.tradingPower = 
    //         new Double(l_tradingPower).toString();
         l_ruitoBuyInputResponse.tradingPower = 
            WEB3StringTypeUtility.formatNumber(l_dblTradingPower);
        // ----------------- End
    
        log.debug("買付可能金額 = " + l_ruitoBuyInputResponse.tradingPower);         
             
        //指定方法@一覧
        if(l_listRuitoProduct != null)
        {
            int l_lsize = l_listRuitoProduct.size();
            String[] l_temp = new String[l_lsize];
            for(int i = 0; i < l_lsize; i++)
            {
                l_temp[i] = (String)l_listRuitoProduct.get(i);
            }       
            l_ruitoBuyInputResponse.specifyDivList = l_temp;
    
        }
        else
        {
            l_ruitoBuyInputResponse.specifyDivList = null;     
        }
    
        log.exiting(STR_METHOD_NAME); 
        return l_ruitoBuyInputResponse;
    }
}
@
