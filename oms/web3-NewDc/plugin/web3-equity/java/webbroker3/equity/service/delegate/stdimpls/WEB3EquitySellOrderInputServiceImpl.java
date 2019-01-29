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
filename	WEB3EquitySellOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文入力サービス処理を実施する(WEB3EquitySellOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2004/12/20 森川  (SRA)  残案件対応
                   2005/01/06 岡村和明(SRA) JavaDoc修正
                   2006/07/04 周捷 (中訊) 仕様変更管理No.937
                   2006/08/29 柴雙紅(中訊) 仕様変更モデル970
                   2006/11/06 張騰宇(中訊) モデル 986
Revesion History : 2007/12/17 金傑(中訊) モデル 1220、1221、1226
Revesion History : 2007/12/20 金傑(中訊) モデル 1264
Revesion History : 2008/01/15 趙林鵬(中訊) モデルNO.1283
Revesion History : 2008/01/16 趙林鵬(中訊) モデルNO.1288, 1292
Revesion History : 2008/04/10 崔遠鵬(中訊) モデルNo.1311
Revesion History : 2008/04/16 崔遠鵬(中訊) モデルNo.1313
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.equity.message.WEB3EquitySellInputResponse;
import webbroker3.equity.service.delegate.WEB3EquitySellOrderInputService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (現物株式売付注文入力サービスImpl)。<BR>
 * <BR>
 * 現物株式売付注文入力サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquitySellOrderInputServiceImpl 
    extends WEB3EquityClientRequestService implements WEB3EquitySellOrderInputService 
{
   /**
    * (ログ出力ユーティリティ。)<BR>
    */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3EquitySellOrderInputServiceImpl.class);
   
   /**
    * (コンストラクタ)。<BR>
    * @@roseuid 4091EC7F0210
    */
   public WEB3EquitySellOrderInputServiceImpl() 
   {
    
   }
   
   /**
    * (execute)。<BR>
    * <BR>
    * 現物株式売付注文入力サービス処理を実施する。 <BR>
    * <BR>
    * シーケンス図「（売付注文入力）入力画面表示データ取得」参照。<BR>
    * <BR>
    * @@param l_request - リクエストデータ
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 40628C2F0153
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
       throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
       log.entering(STR_METHOD_NAME);
       
       WEB3EquitySellInputResponse l_sellInputResponse =
           this.getSellInputScreen((WEB3EquitySellInputRequest)l_request);
       
       log.exiting(STR_METHOD_NAME);
       return l_sellInputResponse;
   }

   /**
    * (get入力画面)<BR>
    * 現物株式売付注文入力画面表示処理を実施する。<BR>
    * <BR>
    * シーケンス図「（売付注文入力）入力画面表示データ取得」参照。<BR>
    * @@param l_sellInputRequest - リクエストデータ
    * @@return WEB3EquitySellInputResponse
    * @@throws WEB3BaseException
    */
   protected WEB3EquitySellInputResponse getSellInputScreen(
       WEB3EquitySellInputRequest l_sellInputRequest)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getSellInputScreen(WEB3EquitySellInputRequest)";
       log.entering(STR_METHOD_NAME);
        
       //--------------------
       //各オブジェクトの取得
       //--------------------
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3EquityTradingModule l_tradingModule
           = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
       WEB3EquityOrderManager l_orderManager
           = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
       WEB3EquityProductManager l_productManager
           = (WEB3EquityProductManager) l_tradingModule.getProductManager();
       WEB3GentradeFinObjectManager l_finObjectManager
           = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();


       //--------------------
       //リクエスト.validate
       //--------------------
       l_sellInputRequest.validate();

       //--------------------
       //get保有資産
       //--------------------
       Asset l_asset = this.getAsset(l_sellInputRequest);
       
       // get補助口座()
       WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
       WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
       WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

       //reset市場コード(市場コード：String)
       WEB3GentradeTradingTimeManagement.resetMarketCode(l_sellInputRequest.marketCode);

       // isPTS口座開設
       // 引数：顧客：　@補助口座.getMainAccount()
       boolean l_blnIsPTSAccountEstablished = this.isPTSAccountOpen(l_mainAccount);

       //取引時間管理.validate注文受付ステイタス()
       WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();

       // validate取引可能顧客
       // 引数：
       //  顧客：　@補助口座.getMainAccount()
       //  isPTS口座開設： isPTS口座開設()の戻り値
       //市場コード：　@リクエスト.市場コード
       OrderValidationResult l_validationResult =
           this.validateAccountForTrading(
               l_mainAccount,
               l_blnIsPTSAccountEstablished,
               l_sellInputRequest.marketCode);

       if (l_validationResult.getProcessingResult().isFailedResult())
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               l_validationResult.getProcessingResult().getErrorInfo(),
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       // get市場閉局警告市場
       // 引数：
       //  部店：　@補助口座.get取引店( )
       //  isPTS口座開設：　@isPTS口座開設()の戻り値
       String[] l_strTradeCloseMarkets = this.getTradeCloseMarket(l_branch, l_blnIsPTSAccountEstablished);

       //--------------------
       //validate銘柄コード
       //--------------------
       String l_strInstitutionCode;
       long l_lngProductId;
       EqTypeProduct l_eqtypeProduct = null;
       try
       {  
           l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
           l_lngProductId = l_asset.getProduct().getProductId();
           l_eqtypeProduct = (EqTypeProduct)l_productManager.getProduct(l_lngProductId);
       }
       catch (NotFoundException l_nfe)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       String l_strProductCode = l_eqtypeProduct.getProductCode();
       WEB3EquityProduct l_product
            = l_orderManager.validateProductCode(l_strProductCode, l_strInstitutionCode);

       //--------------------
       //get取扱可能市場
       //--------------------
       // 部店：　@補助口座.get取引店() 
       // isPTS口座開設：　@ isPTS口座開設()の戻り値
       String[] l_strMarketCodeList = this.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountEstablished);

       //0件時はエラー
       if (l_strMarketCodeList == null || l_strMarketCodeList.length == 0)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00643,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       int l_iMarketListCnt = l_strMarketCodeList.length;
        

       //--------------------
       //取得した部店で取り扱い可能な市場コード数分、繰り返し
       //--------------------
       WEB3GentradeMarket l_market;
       WEB3EquityTradedProduct l_wkTradedProduct;
       final boolean l_blnIsSell = true;                    //is売付(売なのでtrue)
       
       TreeMap l_treeMapMarketCodeList = new TreeMap();
       TreeMap l_treeMapOrderBizDateList = new TreeMap();
       Integer l_iWkMarketCode = null;
       Date l_datWkOrderBizDate = null;
       
       for (int i = 0; i < l_iMarketListCnt; i++)
       {
           //reset市場コード
           WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCodeList[i]);
           try
           {
               //validate取引銘柄
               l_market = (WEB3GentradeMarket) l_finObjectManager
                   .getMarket(l_strInstitutionCode, l_strMarketCodeList[i]);
               l_wkTradedProduct = (WEB3EquityTradedProduct) l_orderManager
                   .validateTradedProduct(l_product, l_market);
               
           }
           catch (NotFoundException l_nfe)
           {
               //市場の取得でエラーが出た場合
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }
           catch (WEB3BaseException l_wbe)
           {
               //例外が発生しないもののみレスポンスへの設定対象とする。
               continue;
           }
           try
           {
               l_iWkMarketCode = new Integer(l_strMarketCodeList[i]);
           }
           catch (NumberFormatException l_nfe)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }

           // 市場.isPTS市場()==falseの場合
           if (!l_market.isPTSMarket())
           {
               //get発注日
               l_datWkOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
           }
           //TreeMapに追加する
           l_treeMapMarketCodeList.put(l_iWkMarketCode, l_strMarketCodeList[i]);
           l_treeMapOrderBizDateList.put(l_iWkMarketCode, l_datWkOrderBizDate);

       }
       //0件時はエラー
       if(l_treeMapMarketCodeList.size()==0){
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00643,
				this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       

       //--------------------
       //分岐フロー リクエスト.市場コード≠nullの場合
       //リクエスト．市場コードのチェック
       //--------------------
       // TreeMapのsetとiteratorの設定
       Set stKeyMapMarketCodeList = l_treeMapMarketCodeList.entrySet();
       Set stKeyMapOrderBizDateList = l_treeMapOrderBizDateList.entrySet();
       Iterator l_iteMapMarketCodeList = stKeyMapMarketCodeList.iterator();
       Iterator l_iteMapOrderBizDateList = stKeyMapOrderBizDateList.iterator();
       
       Object l_objMapMarketCodeList = null;
       Map.Entry l_entMapMarketCodeList = null;
       boolean l_blnIsHandingMarket = false;
       String l_valMarketCode = null;
       
       if (l_sellInputRequest.marketCode != null)
       {           
           while (l_iteMapMarketCodeList.hasNext())
           {
               l_objMapMarketCodeList = l_iteMapMarketCodeList.next();
               l_entMapMarketCodeList = (Map.Entry)l_objMapMarketCodeList;
               
               l_valMarketCode = (String)l_entMapMarketCodeList.getValue();
               
               if (l_sellInputRequest.marketCode.equals(l_valMarketCode))
               {
                   l_blnIsHandingMarket = true;
                   break;
               }
           }
           //リクエスト．市場コードが取扱可能市場に含まれていない場合エラー
           if (l_blnIsHandingMarket == false)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }
             
       }
        

       //--------------------
       //分岐フロー リクエスト.市場コード≠nullの場合
       //指定市場で売付対象の銘柄が取扱可能かどうかのチェックを行う
       //--------------------
       WEB3EquityTradedProduct l_tradedProduct = null;
        
       String[] l_orderPriceDivList = null;                        //注文単価区分一覧
        
       double l_dblCurrentPrice = 0;                               //時価
       Timestamp l_tsCurrentPriceTime = null;                     //現在値時間
       double l_dblChange = 0;                                    //前日比
       String l_strCurrentPriceDiv = null;
       String l_strBoardCurrentPrice = null;                       //現在値
       Timestamp l_tsBoardCurrentPriceTime = null;                 //現在値時刻
       String l_strBoardCurrentPriceDiv = null;                    //現在値区分
       String l_strBoardChange = null;                             //現在値前日比
       String l_strVolume = null;                                  //出来高
       Timestamp l_tsVolumeTime = null;                            //出来高時刻
       String l_strAskPriceTitle = null;                           //買気配値タイトル区分
       String l_strAskPrice = null;                                //買気配値
       Timestamp l_tsAskPriceTime = null;                          //買気配値時刻
       String l_strBidPriceTitle = null;                           //売気配値タイトル区分
       String l_strbBidPrice = null;                               //売気配値
       Timestamp l_tsBidPriceTime = null;                          //売気配値時刻
       String l_strBasePrice = null;                               //基準値段
       if (l_sellInputRequest.marketCode != null)
       {
           //reset市場コード
           WEB3GentradeTradingTimeManagement.resetMarketCode(l_sellInputRequest.marketCode);

           try
           {
               //get取引銘柄
               l_tradedProduct = (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                   l_subAccount.getInstitution(), l_product.getProductCode(), l_sellInputRequest.marketCode);
           }
           catch (NotFoundException l_nfe)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME);
           }
           
           //get注文単価区分一覧
           l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);

           //時価、現在値時刻、前日比を取得
           WEB3EquityProductQuote l_productQuote =
               l_productManager.getDisplayEquityProductQuote(
                   l_tradedProduct,
                   l_subAccount);
           l_dblCurrentPrice = l_productQuote.getQuote();
           l_tsCurrentPriceTime = l_productQuote.getQuoteTime();
           l_dblChange = l_productQuote.getComparedPreviousDay();
           l_strCurrentPriceDiv = l_productQuote.getQuoteTypeDiv();
           
           l_strBoardCurrentPrice = l_productQuote.getBoardCurrentPrice();  
           l_tsBoardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime(); 
           l_strBoardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();  
           l_strBoardChange = l_productQuote.getBoardChange(); 
           l_strVolume = l_productQuote.getVolume();  
           l_tsVolumeTime = l_productQuote.getVolumeTime();  
           l_strAskPriceTitle = l_productQuote.getAskPriceTitle(); 
           l_strAskPrice = l_productQuote.getAskPrice(); 
           l_tsAskPriceTime = l_productQuote.getAskPriceTime(); 
           l_strBidPriceTitle = l_productQuote.getBidPriceTitle();
           l_strbBidPrice = l_productQuote.getBidPrice(); 
           l_tsBidPriceTime = l_productQuote.getBidPriceTime();   
           l_strBasePrice = l_productQuote.getBasePrice();  
       }
        

       //--------------------
       //以下条件・区分を取得
       //--------------------
       WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond    = null;
       String[] l_handingPossibleExecCond                          = null;
       String[] l_handingPossibleOrderCond                         = null;
       String[] l_handingPossiblePriceCond                         = null;
       String[] l_handingPossibleExpirationDateType                = null;
       
           
       //--------------------
       //取扱可能注文条件取得
       //--------------------
       l_gentradeHandingOrderCond = new WEB3GentradeHandlingOrderCond(
           l_strInstitutionCode,
           ProductTypeEnum.EQUITY,
           WEB3FuturesOptionDivDef.DEFAULT,
           WEB3MarginTradingDivDef.DEFAULT,
           WEB3MarketCodeDef.DEFAULT);


       //--------------------
       //取扱可能執行条件より
       //執行条件、発注条件、値段条件、注文期限区分取得
       //--------------------
       l_handingPossibleExecCond
           = l_gentradeHandingOrderCond.getHandlingPossibleExecCond();
       l_handingPossibleOrderCond
           = l_gentradeHandingOrderCond.getHandlingPossibleOrderCond();
       l_handingPossiblePriceCond
           = l_gentradeHandingOrderCond.getHandlingPriceCond();
       l_handingPossibleExpirationDateType
           = l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();
        

       //--------------------
       //出来るまで注文From日付の作成
       //--------------------
       //出来るまで注文from日付
       Date l_datOrderUntilDeadLineFrom            = null;
        
       //受付日付（Timestamp型／Date型／営業日計算ユーティリティー型）
       Timestamp l_tsSysTimestamp = GtlUtils.getSystemTimestamp();
       Date l_datSysTimestamp = new Date(l_tsSysTimestamp.getTime());
       WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsSysTimestamp);

       //受付日付と等しい発注日を持つ市場がある場合 true : 以外false
       boolean l_blnContainSysDatInOdrBizDatLst = false;

       //TreeMapの設定
       Object l_objMapOrderBizDateList = null;
       Map.Entry l_entMapOrderBizDateList = null;
       Date l_valOrderBizDate;

       //--------------------
       //出来るまで注文開始日／終了日／注文期限内祝日一覧取得
       //--------------------
       Date l_datOrderUntilDeadLineStartDate       = null;     //出来るまで注文開始日
       Date l_datOrderUntilDeadLineEndDate         = null;     //出来るまで注文終了日
       Date[] l_datOrderUntilDeadLineHolidayList   = null;     //注文期限内祝日一覧

       //分岐フロー 取扱可能注文条件.isできるまで注文取扱可能() == trueの場合
       //※ 取扱可能注文条件は「現物買注文」の時のみ取得
       if (l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling())
       {
           boolean l_blnIsPTSMarket = false;
           // リクエスト.市場コード！＝nullの場合
           if (l_sellInputRequest.marketCode != null)
           {
               // get市場
               // 引数:
               //  証券会社コード:補助口座.get証券会社.get証券会社コード()
               //  市場コード： リクエスト.市場コード
               try
               {
                   l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                       l_strInstitutionCode, l_sellInputRequest.marketCode);

                   // リクエスト.市場コード != null かつ 市場 .isPTS市場()==falseの場合
                   l_blnIsPTSMarket = l_market.isPTSMarket();
                   if (!l_blnIsPTSMarket)
                   {
                       l_datOrderUntilDeadLineFrom = null;
                   }
               }
               catch (NotFoundException l_notFoundException)
               {
                   log.error("テーブルに該当するデータがありません。", l_notFoundException);
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       l_notFoundException.getMessage(),
                       l_notFoundException);
               }
           }
           // reset市場コード
           WEB3GentradeTradingTimeManagement.resetMarketCode(l_sellInputRequest.marketCode);

           // （リクエスト.市場コード==null） または
           // （リクエスト.市場コード != null かつ 市場 .isPTS市場()==true）の場合
           if (l_sellInputRequest.marketCode == null
               || (l_sellInputRequest.marketCode != null && l_blnIsPTSMarket))
           {
               while (l_iteMapOrderBizDateList.hasNext())
               {
                   l_objMapOrderBizDateList = l_iteMapOrderBizDateList.next();
                   l_entMapOrderBizDateList = (Map.Entry)l_objMapOrderBizDateList;
                   l_valOrderBizDate = (Date)l_entMapOrderBizDateList.getValue();

                   if (WEB3DateUtility.compareToDay(l_datSysTimestamp, l_valOrderBizDate) == 0)
                   {
                       l_blnContainSysDatInOdrBizDatLst = true;
                       break;
                   }
               }

               //受付日付と等しい発注日を持つ市場がある場合、受付日付を設定する。
               if (l_blnContainSysDatInOdrBizDatLst)
               {
                   l_datOrderUntilDeadLineFrom = WEB3DateUtility.toDay(l_datSysTimestamp);
               }
               //全市場の発注日が受付日付と異なる場合、翌営業日を設定する。
               else
               {
                   l_datOrderUntilDeadLineFrom = WEB3DateUtility.toDay(l_genBizDate.roll(1));
               }
           }

           // get出来るまで注文開始日
           l_datOrderUntilDeadLineStartDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineStartDay(
               l_datOrderUntilDeadLineFrom);

           // get出来るまで注文最終日
           l_datOrderUntilDeadLineEndDate = l_gentradeHandingOrderCond.getOrderUntilDeadLineEndDay(
               l_datOrderUntilDeadLineFrom);

           // get注文期限内祝日一覧
           l_datOrderUntilDeadLineHolidayList = l_gentradeHandingOrderCond.getExpirationDateHoliday(
               l_datOrderUntilDeadLineFrom);
       }

       //getＷ指値用執行条件一覧(String[], String[])
       String[] l_strWLimitExecutionConditionTypeList = null;
       l_strWLimitExecutionConditionTypeList =
           WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
               l_handingPossibleExecCond,
               l_handingPossibleOrderCond);

       //--------------------
       //get概算簿価単価
       //--------------------
       String l_strEstimatedBookPrice
           = this.getEstimatedBookPrice(l_sellInputRequest);
        

       //--------------------
       //is特定口座開設
       //--------------------

       if (l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL)
           || l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
       {
           boolean l_isSpecialAccountEstablished = true;
           if (l_sellInputRequest.marketCode != null)
           {
               l_isSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(l_tradedProduct.getDailyDeliveryDate(), l_subAccount);
           }
           else
           {
               l_isSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(l_subAccount);
           }
           if (!l_isSpecialAccountEstablished)
           {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00026,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
           }    
       }

       //--------------------
       //isインサイダー警告表示
       //--------------------
       boolean l_blnIsInsiderMessageSuspension
           = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_lngProductId);
        
        
       //--------------------
       //validate顧客銘柄別取引停止
       //--------------------
       // 引数:
       //  補助口座：　@this.get補助口座（）の戻り値 
       //  銘柄ID：　@保有資産.銘柄ID 
       //  isPTS口座開設：　@isPTS口座開設()の戻り値 
       //市場コード：　@リクエスト.市場コード
       this.validateAccountProductOrderStop(
           l_subAccount, l_lngProductId, l_blnIsPTSAccountEstablished, l_sellInputRequest.marketCode);
       

       //--------------------
       //createレスポンス
       //--------------------
       WEB3EquitySellInputResponse l_response
           = (WEB3EquitySellInputResponse) l_sellInputRequest.createResponse();
           
       
       //--------------------
       //プロパティセット
       //--------------------
       String[] l_strWkPriceDivList        = {
           WEB3OrderPriceDivDef.MARKET_PRICE, WEB3OrderPriceDivDef.LIMIT_PRICE};
                
       if (l_sellInputRequest.marketCode != null)
       {
           l_response.orderPriceDivList        = l_orderPriceDivList;
       }
       else
       {
           l_response.orderPriceDivList        = l_strWkPriceDivList;
       }
       
       l_response.priceCondList            = l_handingPossiblePriceCond;
       l_response.execCondList             = l_handingPossibleExecCond;
       l_response.wlimitExecCondList       = l_strWLimitExecutionConditionTypeList;
       l_response.expirationStartDate      = l_datOrderUntilDeadLineStartDate;
       l_response.expirationEndDate        = l_datOrderUntilDeadLineEndDate;
       l_response.holidayList              = l_datOrderUntilDeadLineHolidayList;
       
       // this.get市場閉局警告市場()の戻り値配列をセット
       l_response.messageSuspension        = l_strTradeCloseMarkets;
       l_response.insiderWarningFlag       = l_blnIsInsiderMessageSuspension;
       
       l_response.currentPriceDiv = l_strCurrentPriceDiv;
       
       if (l_sellInputRequest.marketCode != null)
       {
           l_response.currentPrice             = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
           l_response.comparedPreviousDay      = WEB3StringTypeUtility.formatNumber(l_dblChange);
           
           //現在値：　@取得した株式銘柄時価情報.get現在値( )の戻り値をセット
           l_response.boardCurrentPrice = l_strBoardCurrentPrice;
           
           //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻( )の戻り値をセット
           l_response.boardCurrentPriceTime = l_tsBoardCurrentPriceTime; 
           
           //現在値区分：　@取得した株式銘柄時価情報.get現在値区分( )の戻り値をセット           
           l_response.boardCurrentPriceDiv = l_strBoardCurrentPriceDiv; 
           
           //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比( )の戻り値をセット
           l_response.boardChange = l_strBoardChange;
           
           //出来高：　@取得した株式銘柄時価情報.get出来高( )の戻り値をセット
           l_response.volume = l_strVolume;
           
           //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻( )の戻り値をセット
           l_response.volumeTime = l_tsVolumeTime;
           
           //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分( )の戻り値をセット
           l_response.askPriceTitle = l_strAskPriceTitle;
           
           //買気配値：　@取得した株式銘柄時価情報.get買気配値( )の戻り値をセット
           l_response.askPrice = l_strAskPrice; 
           
           //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻( )の戻り値をセット
           l_response.askPriceTime = l_tsAskPriceTime;
           
           //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分( )の戻り値をセット
           l_response.bidPriceTitle = l_strBidPriceTitle;
           
           //売気配値：　@取得した株式銘柄時価情報.get売気配値( )の戻り値をセット
           l_response.bidPrice = l_strbBidPrice;
           
           //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻( )の戻り値をセット
           l_response.bidPriceTime = l_tsBidPriceTime;
           
           //基準値段：　@取得した株式銘柄時価情報.get基準値段( )の戻り値をセット
           l_response.basePrice = l_strBasePrice;
       }
       else
       {
           l_response.currentPrice             = null;
           l_response.comparedPreviousDay      = null;
       }
       
       l_response.currentPriceTime         = l_tsCurrentPriceTime;
       
       EqtypeProductRow l_validateProductRow = (EqtypeProductRow)l_product.getDataSourceObject();
       l_response.productCode              = l_validateProductRow.getProductCode();
       l_response.productName              = l_validateProductRow.getStandardName();
       
       // this.get取扱可能市場( )の戻り値配列
       l_response.marketList               = (String[]) l_treeMapMarketCodeList.values().toArray(
                                               new String[l_treeMapMarketCodeList.size()]);
       l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_asset.getTaxType());
       
       double l_dbQuantity                   = l_asset.getQuantity() - l_asset.getLockedQuantity();
       l_response.orderQuantity            = WEB3StringTypeUtility.formatNumber(l_dbQuantity);
       l_response.estimatedBookPrice       = l_strEstimatedBookPrice;
       l_response.expirationDateTypeList   = l_handingPossibleExpirationDateType;
       l_response.orderCondTypeList        = l_handingPossibleOrderCond;
       
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
    }
   
   /**
    * (get保有資産)<BR>
    * 保有資産オブジェクトを取得する。<BR>
    * <BR>
    * 株式ポジションマネージャ.getAsset(リクエスト.ID（＝資産ID）)にdelegateする。<BR>
    * @@param l_request - リクエストデータ
    * @@return Asset
    * @@throws WEB3BaseException
    */
   protected Asset getAsset(
       WEB3EquitySellInputRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getAsset(WEB3EquitySellInputRequest)";
       log.entering(STR_METHOD_NAME);
        
       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       TradingModule l_tradingModule =
           l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
       WEB3EquityPositionManager l_positionManager =
           (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
       Asset l_asset = null;
       try
       {
           l_asset = l_positionManager.getAsset(Long.parseLong(l_request.id));
       }
       catch (NotFoundException l_nfe)
       {
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
       }
       
       log.exiting(STR_METHOD_NAME);
       return l_asset;
   }
   
   /**
    * (get概算簿価単価)<BR>
     * 概算簿価単価を取得し返す。<BR>
     * <BR>
     * １）　@保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@株式ポジションマネージャ.getAsset(引数のリクエスト.ID)をコールする。<BR>
     * <BR>
     * ２）　@株式計算サービス.calc概算簿価単価(保有資産.銘柄ID, get補助口座(), 保有資産.税区分)をコールし、<BR>
     * 　@　@　@戻り値を返却する。<BR>
    * @@param l_request - リクエストデータ
    * @@return String
    * @@throws WEB3BaseException
    */
   protected String getEstimatedBookPrice(
       WEB3EquitySellInputRequest l_request)
       throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getEstimatedBookPrice(WEB3EquitySellInputRequest)";
       log.entering(STR_METHOD_NAME);
        
       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       TradingModule l_tradingModule =
           l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
       WEB3EquityPositionManager l_positionManager =
           (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
       Asset l_asset = null;
       try
       {
           l_asset = l_positionManager.getAsset(Long.parseLong(l_request.id));
       }
       catch (NotFoundException l_nfe)
       {
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
       }
       WEB3EquityBizLogicProvider l_bizLogicProvider =
           (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
       double l_dblEstimatedBookPrice =
           l_bizLogicProvider.calcEstimatedBookPrice(
               l_asset.getProduct().getProductId(),
               this.getSubAccount(),
               l_asset.getTaxType());
       String l_strEstimatedBookPrice =
           WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        
       log.exiting(STR_METHOD_NAME);
       return l_strEstimatedBookPrice;
   }

   /**
    * (get市場閉局警告市場)<BR>
    * 市場閉局警告市場を取得する。<BR>
    * PTS口座開設顧客の場合、PTSの閉局警告市場も取得する。<BR>
    * <BR>
    * １）取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する。<BR>
    * <BR>
    * 　@　@[取引時間管理.get市場閉局警告市場()にセットする引数]<BR>
    * 　@　@部店：　@引数.部店<BR>
    * 　@　@銘柄タイプ：　@"株式"<BR>
    * 　@　@信用取引区分：　@"DEFAULT"<BR>
    * <BR>
    * <BR>
    * ２）PTS口座開設顧客の場合(引数.isPTS口座開設==true)<BR>
    * <BR>
    * 　@　@２－１）PTS取引時間管理.get市場閉局警告市場()をコールし、<BR>
    * 　@　@　@　@　@　@市場コードの配列を取得する<BR>
    * <BR>
    * 　@　@[PTS取引時間管理.get市場閉局警告市場()にセットする引数]<BR>
    * 　@　@部店：　@引数.部店<BR>
    * 　@　@銘柄タイプ：　@"株式"<BR>
    * 　@　@信用取引区分：　@"DEFAULT" <BR>
    * <BR>
    * 　@　@２－２）１）の結果と　@２－１）の結果をマージして市場コード昇順でソートする。<BR>
    * <BR>
    * ３）取得した市場コードの配列を返却する。<BR>
    * @@param l_branch - (部店)<BR>
    * 部店オブジェクト
    * @@param l_blnIsPTSAccountEstablished - (isPTS口座開設)<BR>
    * isPTS口座開設
    * @@return String[]
    * @@throws WEB3BaseException
    */
   protected String[] getTradeCloseMarket(
       WEB3GentradeBranch l_branch,
       boolean l_blnIsPTSAccountEstablished)throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getTradeCloseMarket(WEB3GentradeBranch, boolean)";
       log.entering(STR_METHOD_NAME);

       // １）取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する。
         //[取引時間管理.get市場閉局警告市場()にセットする引数]
         //　@　@部店：　@引数.部店
         //　@　@銘柄タイプ：　@"株式"
         //　@　@信用取引区分：　@"DEFAULT"

       String[] l_strTradeCloseMarkets = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
           l_branch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

       // ２）PTS口座開設顧客の場合(引数.isPTS口座開設==true)
       if (l_blnIsPTSAccountEstablished)
       {
           // ２－１）PTS取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する
           String[] l_strPtsTradeCloses = WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
               l_branch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);
           // ２－２）１）の結果と２－１）の結果をマージして市場コード昇順でソートする。
           l_strTradeCloseMarkets = this.mergeAndSort(l_strTradeCloseMarkets, l_strPtsTradeCloses);
       }

       log.exiting(STR_METHOD_NAME);
       // ３）取得した市場コードの配列を返却する。
       return l_strTradeCloseMarkets;
   }

   /**
    * (get取扱可能市場)<BR>
    * 取扱可能市場を取得する。<BR>
    * PTS口座開設顧客の場合、PTSの取扱可能市場も取得する。<BR>
    * <BR>
    * １）(部店市場別)取扱条件.get取扱可能市場()をコールし、市場コードの配列を取得する。<BR>
    * <BR>
    * 　@　@[(部店市場別)取扱条件.get取扱可能市場()にセットする引数] <BR>
    * 　@　@部店：　@引数.部店<BR>
    * 　@　@銘柄タイプ：　@"株式"<BR>
    * <BR>
    * ２）PTS口座開設顧客の場合(引数.isPTS口座開設==true)<BR>
    * <BR>
    * 　@　@２－１）(部店市場別・PTS)取扱条件.get取扱可能市場()をコールし、<BR>
    * 　@　@　@　@　@　@市場コードの配列を取得する。<BR>
    * <BR>
    * 　@　@[(部店市場別・PTS)取扱条件.get取扱可能市場()にセットする引数]<BR>
    * 　@　@部店：　@引数.部店<BR>
    * 　@　@銘柄タイプ：　@"株式"<BR>
    * <BR>
    * 　@　@２－２）１）の結果と　@２－１）の結果をマージして市場コード昇順でソートする。 <BR>
    * <BR>
    * ３）取得した市場コードの配列を返却する。<BR>
    * @@param l_branch - (部店)<BR>
    * 部店オブジェクト
    * @@param l_blnIsPTSAccountEstablished - (isPTS口座開設)<BR>
    * isPTS口座開設
    * @@return String[]
    * @@throws WEB3BaseException
    */
   protected String[] getHandlingPossibleMarket(
       WEB3GentradeBranch l_branch,
       boolean l_blnIsPTSAccountEstablished)throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "getHandlingPossibleMarket(WEB3GentradeBranch, boolean)";
       log.entering(STR_METHOD_NAME);

       // １）(部店市場別)取扱条件.get取扱可能市場()をコールし、市場コードの配列を取得する。
       //[(部店市場別)取扱条件.get取扱可能市場()にセットする引数]
       //  部店：　@引数.部店
       //  銘柄タイプ：　@"株式"
       String[] l_strHandlingPossibleMarkets = WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
           l_branch, ProductTypeEnum.EQUITY);

       // ２）PTS口座開設顧客の場合(引数.isPTS口座開設==true)
       if (l_blnIsPTSAccountEstablished)
       {
           // ２－１）(部店市場別・PTS)取扱条件.get取扱可能市場()をコールし、市場コードの配列を取得する。
           // [(部店市場別・PTS)取扱条件.get取扱可能市場()にセットする引数]
           // 部店：　@引数.部店
           // 銘柄タイプ：　@"株式"

           String[] l_strPtsPossibleMarkets = WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
               l_branch, ProductTypeEnum.EQUITY);

           // ２－２）１）の結果と２－１）の結果をマージして市場コード昇順でソートする。
           l_strHandlingPossibleMarkets = this.mergeAndSort(l_strHandlingPossibleMarkets, l_strPtsPossibleMarkets);
       }

       log.exiting(STR_METHOD_NAME);
       // ３）取得した市場コードの配列を返却する。
       return l_strHandlingPossibleMarkets;
   }

   /**
    * (isPTS口座開設)<BR>
    * 顧客.isPTS口座開設をコールし、結果を返却する <BR>
    * <BR>
    * １）引数.顧客オブジェクトのisPTS口座開設()をコールし、結果を返却する。<BR>
    * @@param l_mainAccount - (顧客)<BR>
    * 顧客オブジェクト
    * @@return boolean
    * @@throws WEB3BaseException
    */
   protected boolean isPTSAccountOpen(
       WEB3GentradeMainAccount l_mainAccount)throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "isPTSAccountOpen(WEB3GentradeMainAccount)";
       log.entering(STR_METHOD_NAME);

       if (l_mainAccount == null)
       {
         log.debug("パラメータ値不正。");
         log.exiting(STR_METHOD_NAME);
         throw new WEB3SystemLayerException(
             WEB3ErrorCatalog.SYSTEM_ERROR_80017,
             this.getClass().getName() + "." + STR_METHOD_NAME,
             "パラメータ値不正。");
       }

       // １）引数.顧客オブジェクトのisPTS口座開設()をコールし、結果を返却する。
       log.exiting(STR_METHOD_NAME);
       return l_mainAccount.isPTSAccountOpen();
   }

   /**
    * (validate取引可能顧客)<BR>
    * 取引可能顧客かどうかチェックする。<BR>
    * PTS口座開設顧客でPTS市場が指定されている場合は、<BR>
    * PTS取引時間管理から発注日を取得し、<BR>
    * 発注日指定でチェックを行う。<BR>
    * <BR>
    * １）引数.市場コード≠nullの場合、<BR>
    * <BR>
    * 　@　@拡張金融オブジェクトマネージャ.getMarket()をコールし、<BR>
    * 　@　@市場オブジェクトを生成する。<BR>
    * <BR>
    * 　@　@　@[引数の設定]<BR>
    * 　@　@　@証券会社コード:　@引数.顧客.getInstitution().getInstitutionCode()<BR>
    * 　@　@　@市場コード:　@引数.市場コード<BR>
    * <BR>
    * ２）注文チェックオブジェクトを生成する。<BR>
    * <BR>
    * ３）以下の処理を行う。<BR>
    * <BR>
    * 　@３－１）引数.isPTS口座開設＝true　@かつ<BR>
    * 　@　@　@　@　@引数.市場コード≠null　@かつ<BR>
    * 　@　@　@　@　@市場.isPTS市場()＝true　@の場合<BR>
    * <BR>
    * 　@　@　@注文チェック.validate取引可能顧客(顧客、発注日)をコールする。<BR>
    * <BR>
    * 　@　@　@[引数の設定]<BR>
    * 　@　@　@顧客：　@引数.顧客<BR>
    * 　@　@　@発注日：　@PTS取引時間管理.get発注日()<BR>
    * <BR>
    * 　@３－２）　@３－１）以外の場合<BR>
    * <BR>
    * 　@　@　@注文チェック.validate取引可能顧客(顧客)をコールする。<BR>
    * <BR>
    * 　@　@　@[引数の設定]<BR>
    * 　@　@　@顧客：　@引数.顧客<BR>
    * <BR>
    * @@param l_mainAccount - (顧客)<BR>
    * 顧客<BR>
    * @@param l_blnIsPTSAccountOpen - (isPTS口座開設)<BR>
    * isPTS口座開設<BR>
    * @@param l_strMarketCode - (市場コード)<BR>
    * 市場コード<BR>
    * @@return OrderValidationResult
    */
   protected OrderValidationResult validateAccountForTrading(
       WEB3GentradeMainAccount l_mainAccount, boolean l_blnIsPTSAccountOpen, String l_strMarketCode)
   {
       final String STR_METHOD_NAME = "validateAccountForTrading(WEB3GentradeMainAccount, boolean, String)";
       log.entering(STR_METHOD_NAME);

       if (l_mainAccount == null)
       {
           log.debug("パラメータ値不正。");
           log.exiting(STR_METHOD_NAME);
           return new OrderValidationResult(
               ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017));
       }

       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       //１）引数.市場コード≠nullの場合、
       WEB3GentradeMarket l_market = null;
       boolean l_blnIsPTSMarket = false;
       if (l_strMarketCode != null)
       {
           WEB3GentradeFinObjectManager l_finObjectManager =
               (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
           //拡張金融オブジェクトマネージャ.getMarket()をコールし、市場オブジェクトを生成する。
           //[引数の設定]
           //証券会社コード:　@引数.顧客.getInstitution().getInstitutionCode()
           //市場コード:　@引数.市場コード
           try
           {
               l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                   l_mainAccount.getInstitution().getInstitutionCode(),
                   l_strMarketCode);
           }
           catch (NotFoundException l_ex)
           {
               log.error("テーブルに該当するデータがありません。", l_ex);
               log.exiting(STR_METHOD_NAME);
               return new OrderValidationResult(
                   ProcessingResult.newFailedResultInstance(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005));
           }

           try
           {
               l_blnIsPTSMarket = l_market.isPTSMarket();
           }
           catch (WEB3BaseException l_ex)
           {
               log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
               log.exiting(STR_METHOD_NAME);
               return new OrderValidationResult(
                   ProcessingResult.newFailedResultInstance(
                       l_ex.getErrorInfo()));
           }
       }

       //２）注文チェックオブジェクトを生成する。
       WEB3GentradeOrderValidator l_gentradeOrderValidator =
           (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

       //３－１）引数.isPTS口座開設＝true　@かつ 引数.市場コード≠null　@かつ 市場.isPTS市場()＝true　@の場合
       if (l_blnIsPTSAccountOpen && l_strMarketCode != null && l_blnIsPTSMarket)
       {
           //PTS取引時間管理.get発注日()
           Date l_datOrderBizDate = null;
           try
           {
               l_datOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
           }
           catch (WEB3SystemLayerException l_ex)
           {
               log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
               log.exiting(STR_METHOD_NAME);
               return new OrderValidationResult(
                   ProcessingResult.newFailedResultInstance(
                       l_ex.getErrorInfo()));
           }

           //注文チェック.validate取引可能顧客(顧客、発注日)をコールする。
           //[引数の設定]
           //顧客：　@引数.顧客
           //発注日：　@PTS取引時間管理.get発注日()
           OrderValidationResult l_validationPtsResult =
               l_gentradeOrderValidator.validateAccountForTrading(
                   l_mainAccount,
                   new Timestamp(l_datOrderBizDate.getTime()));

           log.exiting(STR_METHOD_NAME);
           return l_validationPtsResult;
       }
       else
       {
           //以外の場合注文チェック.validate取引可能顧客(顧客)をコールする。
           //[引数の設定]
           //顧客：　@引数.顧客
           OrderValidationResult l_validationResult =
               l_gentradeOrderValidator.validateAccountForTrading(l_mainAccount);

           log.exiting(STR_METHOD_NAME);
           return l_validationResult;
       }
   }

   /**
    * (validate顧客銘柄別取引停止)<BR>
    * 顧客が指定された銘柄の取引について取引停止中であるかチェックする。<BR>
    * PTS口座開設顧客でPTS市場が指定されている場合は、<BR>
    * PTS注文マネージャの同名メソッドでチェックを行う。<BR>
    * <BR>
    * １）引数.市場コード≠nullの場合、<BR>
    * <BR>
    * 　@　@拡張金融オブジェクトマネージャ.getMarket()をコールし、<BR>
    * 　@　@市場オブジェクトを生成する。<BR>
    * <BR>
    * 　@　@　@[引数の設定]<BR>
    * 　@　@　@証券会社コード:　@引数.補助口座.getInstitution().getInstitutionCode()<BR>
    * 　@　@　@市場コード:　@引数.市場コード<BR>
    * <BR>
    * ２）以下の処理を行う。<BR>
    * <BR>
    * 　@２－１）引数.isPTS口座開設＝true　@かつ<BR>
    * 　@　@　@　@　@引数.市場コード≠null　@かつ<BR>
    * 　@　@　@　@　@市場.isPTS市場()＝true　@の場合<BR>
    * <BR>
    * 　@　@　@PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)()をコールする。<BR>
    * <BR>
    * 　@　@　@[引数の設定]<BR>
    * 　@　@　@補助口座：　@引数.補助口座<BR>
    * 　@　@　@銘柄ID：　@引数.銘柄ID<BR>
    * 　@　@　@注文種別：　@EQUITY_SELL（現物売注文）<BR>
    * <BR>
    * 　@２－２）　@２－１）以外の場合<BR>
    * <BR>
    * 　@　@　@拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする。<BR>
    * <BR>
    * 　@　@　@[引数の設定]<BR>
    * 　@　@　@補助口座：　@引数.補助口座<BR>
    * 　@　@　@銘柄ID：　@引数.銘柄ID<BR>
    * 　@　@　@注文種別：　@EQUITY_SELL（現物売注文）<BR>
    * <BR>
    * @@param l_subAccount - (補助口座)<BR>
    * 補助口座オブジェクト<BR>
    * @@param l_lngProductId - (銘柄ID)<BR>
    * 銘柄ID<BR>
    * @@param l_blnIsPTSAccountOpen - (isPTS口座開設)<BR>
    * isPTS口座開設<BR>
    * @@param l_strMarketCode - (市場コード)<BR>
    * 市場コード<BR>
    * @@throws WEB3BaseException
    */
   protected void validateAccountProductOrderStop(
       WEB3GentradeSubAccount l_subAccount,
       long l_lngProductId,
       boolean l_blnIsPTSAccountOpen,
       String l_strMarketCode) throws WEB3BaseException
   {
       final String STR_METHOD_NAME =
           "validateAccountProductOrderStop(WEB3GentradeSubAccount, long, boolean, String)";
       log.entering(STR_METHOD_NAME);

       if (l_subAccount == null)
       {
           log.debug("パラメータ値不正。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "," + STR_METHOD_NAME,
               "パラメータ値不正。");
       }

       FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
       WEB3EquityTradingModule l_tradingModule =
           (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

       WEB3EquityPTSOrderManager l_orderPTSManager =
           (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();

       //１）引数.市場コード≠nullの場合、
       WEB3GentradeMarket l_market = null;
       if (l_strMarketCode != null)
       {
           WEB3GentradeFinObjectManager l_finObjectManager =
               (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
           //拡張金融オブジェクトマネージャ.getMarket()をコールし、市場オブジェクトを生成する。
           //[引数の設定]
           //証券会社コード:　@引数.顧客.getInstitution().getInstitutionCode()
           //市場コード:　@引数.市場コード
           try
           {
               l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                   l_subAccount.getInstitution().getInstitutionCode(),
                   l_strMarketCode);
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

       //２－１）引数.isPTS口座開設＝true　@かつ 引数.市場コード≠null　@かつ 市場.isPTS市場()＝true　@の場合
       if (l_blnIsPTSAccountOpen && l_strMarketCode != null && l_market.isPTSMarket())
       {
           //PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)()をコールする。
           //[引数の設定]
           //補助口座：　@引数.補助口座
           //銘柄ID：　@引数.銘柄ID
           //注文種別：　@EQUITY_SELL（現物売注文）
           l_orderPTSManager.validatePTSAccountProductOrderStop(
               l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_SELL);
       }
       else
       {
           //　@２－２）　@２－１）以外の場合
           //拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする。
           //[引数の設定]
           //補助口座：　@引数.補助口座
           //銘柄ID：　@引数.銘柄ID
           //注文種別：　@EQUITY_SELL（現物売注文）
           l_orderPTSManager.validateAccountProductOrderStop(
               l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_SELL);
       }
       log.exiting(STR_METHOD_NAME);
   }

   /**
    * 二つの配列を一つの配列に合併して、昇順ソートで返却する
    * @@param l_strArraySrcs
    * @@param l_strDests
    * @@return String[]
    */
   private String[] mergeAndSort(String[] l_strArraySrcs, String[] l_strDests)
    {
        final String STR_METHOD_NAME = "mergeAndSort(String[], String[])";
        log.entering(STR_METHOD_NAME);

        Object[] l_objMergeArrays = WEB3Toolkit.merge(l_strArraySrcs, l_strDests);
        l_objMergeArrays = WEB3Toolkit.toUnique(l_objMergeArrays);

        if (l_objMergeArrays == null || l_objMergeArrays.length == 0)
        {
            return null;
        }

        int l_intMergeArrayCnt = l_objMergeArrays.length;
        String[] l_strResults = new String[l_intMergeArrayCnt];
        for (int i = 0; i < l_intMergeArrayCnt; i++)
        {
            l_strResults[i] = (String)l_objMergeArrays[i];
        }

        int l_intResultsCnt = l_strResults.length;
        String l_strTemp = null;
        for (int i = 0; i < l_intResultsCnt; i++)
        {
            for (int j = i + 1; j < l_intResultsCnt; j++)
            {
                if (Integer.parseInt(l_strResults[i]) > Integer.parseInt(l_strResults[j]))
                {
                    l_strTemp = l_strResults[j];
                    l_strResults[j] = l_strResults[i];
                    l_strResults[i] = l_strTemp;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strResults;
    }
}
@
