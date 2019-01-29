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
filename	WEB3EquityOrderBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文買付入力サービスImpl(WEB3EquityOrderBuyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/10 森川   (SRA) 新規作成
                   2006/07/04 周捷 (中訊) 仕様変更管理No.937
                   2006/11/06 張騰宇(中訊) モデル 986
                   2006/12/25 柴双紅(中訊) モデルNo.1088、1089
Revesion History : 2007/12/17 趙林鵬(中訊) モデルNO.1211,1212,1225,1264,1272
Revesion History : 2008/01/15 趙林鵬(中訊) モデルNO.1283
Revesion History : 2008/01/16 趙林鵬(中訊) モデルNO.1288,1292
Revesion History : 2008/02/28 趙林鵬(中訊) モデルNO.1305
Revesion History : 2008/04/16 崔遠鵬(中訊) モデルNo.1313,1316
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.define.WEB3EquityProductIdDef;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityBuyInputResponse;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.equity.message.WEB3EquityProductSelectResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderBuyInputService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
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
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * （現物株式買付注文入力サービスImpl）。<BR>
 * <BR>
 * 現物株式買付注文入力サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityOrderBuyInputServiceImpl
    extends WEB3EquityClientRequestService 
    implements WEB3EquityOrderBuyInputService
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputServiceImpl.class);
    
    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 409A008A0399
     */
    public WEB3EquityOrderBuyInputServiceImpl()
    {
    }


    /**
     * (get銘柄選択画面)。<BR>
     * <BR>
     * 現物株式の銘柄選択画面表示サービスを実施する。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（買付注文入力）get銘柄選択画面」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3EquityProductSelectResponse<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3EquityProductSelectResponse getProductSelectScreen(WEB3EquityProductSelectRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductSelectScreen(WEB3EquityProductSelectRequest)";
        log.entering(STR_METHOD_NAME);

        //get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        // isPTS口座開設(顧客)
        //顧客： 補助口座.get顧客()
        boolean l_blnIsPTSAccountOpen =
            this.isPTSAccountOpen(l_mainAccount);

        //取引時間管理.validate注文受付ステイタス()
        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();

        //validate取引可能顧客(顧客, boolean, String)
        //顧客：　@補助口座.getMainAccount()
        //isPTS口座開設： isPTS口座開設()の戻り値
        //市場コード： null
        OrderValidationResult l_validationResult =
            this.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountOpen, null);
        if (l_validationResult.getProcessingResult().isFailedResult() )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get市場閉局警告市場(部店, boolean)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_strTradeCloseMarkets = this.getTradeCloseMarket(
            l_branch,
            l_blnIsPTSAccountOpen);

        //get取扱可能市場(部店, boolean)
        String[] l_strMarketList = this.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountOpen);
        //0件時はエラー
        if (l_strMarketList == null || l_strMarketList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate顧客銘柄別取引停止(補助口座, long, OrderTypeEnum)
        //補助口座：　@this.get補助口座()の戻り値
        //銘柄ID：　@”0”（全銘柄）
        //isPTS口座開設：　@顧客.isPTS口座開設()の戻り値
        //市場コード： null
        this.validateAccountProductOrderStop(
            l_subAccount, WEB3EquityProductIdDef.ALL_PRODUCTS, l_blnIsPTSAccountOpen, null);

        //createResponse
        WEB3EquityProductSelectResponse l_response
            = (WEB3EquityProductSelectResponse) l_request.createResponse();
        l_response.marketList = l_strMarketList;
        l_response.messageSuspension = l_strTradeCloseMarkets;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


    /**
     * (execute)。 <BR>
     * <BR>
     * 現物株式買付注文入力サービス処理を実施する。  <BR>
     * <BR>
     * リクエストデータの型により、get銘柄選択画面()または、<BR>
     * get新規建入力画面()メソッドのいずれかをコールする。<BR>
     * <BR>

     * @@param l_request - リクエストデータ <BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406285B601EF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3EquityProductSelectRequest)
        {
            l_response = this.getProductSelectScreen((WEB3EquityProductSelectRequest) l_request);
        }
        else if (l_request instanceof WEB3EquityBuyInputRequest)
        {
            l_response = this.getBuyInputScreen((WEB3EquityBuyInputRequest) l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


    /**
     * (get入力画面)。 <BR>
     * <BR>
     * 現物株式買付注文入力画面表示処理を実施する。 <BR>
     * <BR>
     * シーケンス図「（買付注文入力）入力画面表示データ取得」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ <BR>
     * @@return webbroker3.equity.message.WEB3EquityBuyInputResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406285B601EF
     */
    protected WEB3EquityBuyInputResponse getBuyInputScreen(
        WEB3EquityBuyInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getBuyInputScreen(WEB3EquityBuyInputRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
            = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager
            = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        l_request.validate();

        //get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        log.debug("get補助口座（補助口座ID=[" + l_subAccount.getSubAccountId() + "]）");

        //get市場コード(String, String, String)
        //引数は以下の通りに設定する。
        //銘柄コード：　@リクエストデータ.銘柄コード
        //市場コード：　@リクエストデータ.市場コード
        //証券会社コード：　@補助口座.証券会社
        l_request.marketCode =
            this.getMarketCode(
                l_request.productCode,
                l_request.marketCode,
                l_subAccount.getInstitution().getInstitutionCode());

        //reset市場コード(市場コード : String)
        //引数は以下の通りに設定する。
        //市場コード：　@リクエスト.市場コード
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

        // isPTS口座開設(顧客)
        //顧客： 補助口座.get顧客()
        boolean l_blnIsPTSAccountOpen =
            this.isPTSAccountOpen(l_mainAccount);

        //取引時間管理.validate注文受付ステイタス()
        WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();

        WEB3GentradeMarket l_market = null;
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        //リクエスト.市場コード！＝nullの場合
        if (l_request.marketCode != null)
        {
            //リクエスト.取引区分=="現物買注文"　@の場合
            if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
            {
                //validate市場コード
                //市場コード：　@リクエスト.市場コード
                //証券会社コード：　@補助口座.証券会社コード
                l_market = (WEB3GentradeMarket)l_orderManager.validateMarket(
                    l_request.marketCode,
                    l_strInstitutionCode);
            }

            //リクエスト.取引区分=="立会外分売"　@の場合
            if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
            {
                try
                {
                    //get市場
                    //証券会社コード：　@this.get補助口座( ).証券会社コード
                    //市場コード：　@リクエスト.市場コード
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_strInstitutionCode,
                        l_request.marketCode);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("非取扱市場エラー。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        //validate取引可能顧客(顧客, boolean, String)
        OrderValidationResult l_validationResult =
            this.validateAccountForTrading(l_mainAccount, l_blnIsPTSAccountOpen, l_request.marketCode);
        if (l_validationResult.getProcessingResult().isFailedResult() )
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get市場閉局警告市場(部店, boolean)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_strTradeCloseMarkets = this.getTradeCloseMarket(
            l_branch,
            l_blnIsPTSAccountOpen);

        //--------------------
        //get株式買付可能額
        //--------------------
        double l_dblEquityTradingPower = this.getEquityTradingPower(l_subAccount);
        
        //--------------------
        //以下条件・区分を取得
        //--------------------
        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond    = null;
        String[] l_handingPossibleExecCond                          = null;
        String[] l_handingPossibleOrderCond                         = null;
        String[] l_handingPossiblePriceCond                         = null;
        String[] l_handingPossibleExpirationDateType                = null;
        String[] l_strWLimitExecutionConditionTypeList              = null;

        //--------------------
        //分岐フロー：現物の場合のみ
        //--------------------
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
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
            // getＷ指値用執行条件一覧(String[], String[])
            l_strWLimitExecutionConditionTypeList =
                WEB3EquityDataAdapter.getWLimitExecutionConditionTypeList(
                    l_handingPossibleExecCond,
                    l_handingPossibleOrderCond);
        }

        //--------------------
        //get取扱可能市場
        //--------------------
        String[] l_strMarketCodeList = null;
        int l_iMarketListCnt = 0;
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
            //引数は以下の通りに設定する。
            //部店：　@補助口座.get取引店( )
            //isPTS口座開設: isPTS口座開設()の戻り値
            l_strMarketCodeList = this.getHandlingPossibleMarket(l_branch, l_blnIsPTSAccountOpen);
            //0件時はエラー
            if (l_strMarketCodeList == null || l_strMarketCodeList.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_iMarketListCnt = l_strMarketCodeList.length;
        }

        //--------------------
        //分岐フロー リクエスト.銘柄コード≠nullの場合
        //--------------------
        boolean l_blnIsInsiderMessageSuspension = false;
        EqTypeProduct l_eqTypeProduct = null;
        long l_lngProductId = WEB3EquityProductIdDef.ALL_PRODUCTS;
        if (l_request.productCode != null)
        {
            //validate銘柄コード
            l_eqTypeProduct = l_orderManager.validateProductCode(
                l_request.productCode, l_strInstitutionCode);
            l_lngProductId = l_eqTypeProduct.getProductId();

            //isインサイダー警告表示
            l_blnIsInsiderMessageSuspension
                = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_lngProductId);
        }

        final boolean l_blnIsSell = false;                          //is売付(買なのでfalse)

        String[] l_strResMarketCodeList = null;                     //レスポンス設定用市場コード一覧
        ArrayList l_lstWkMarketCodeList;                             //レスポンス設定用市場コード一覧作成用ワーク
        
        long l_lngMarketId = 0;                                     //市場ID
        WEB3EquityTradedProduct l_tradedProduct = null;             //取引銘柄
        
        String[] l_orderPriceDivList = null;                        //注文単価区分一覧
        
        double l_dblCurrentPrice = 0;                               //時価
        Timestamp l_tsCurrentPriceTime = null;                      //現在値時間
        double l_dblChange = 0;                                     //前日比
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

        //--------------------
        //分岐フロー リクエスト.銘柄コード≠null かつ リクエスト.市場コード≠nullの場合
        //--------------------
        if (l_request.productCode != null && l_request.marketCode != null)
        {
            l_lngMarketId = l_market.getMarketId();

            //validate取引銘柄
            l_tradedProduct
                = (WEB3EquityTradedProduct) l_orderManager.validateTradedProduct(
                    l_subAccount, l_eqTypeProduct, l_market, l_blnIsSell);
            
            //validateJASDAQ銘柄取扱可能
            l_tradedProduct.validateJASDAQProductHandling(l_branch);

            //--------------------
            //リクエスト．取引区分＝立会外分売の場合
            //--------------------
            if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
            {
                //validate立会外分売受付可能
                l_orderManager.validateOffFloorOrderPossible(l_lngProductId, l_lngMarketId, l_subAccount);
                
                //validate立会外分売複数注文
                l_orderManager.validateOffFloorDuplicateOrder(l_lngProductId, l_lngMarketId, l_subAccount);
            }
            //
            //--------------------
            //リクエスト．取引区分＝現物買注文の場合
            //--------------------
            else if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
            {
                //get注文単価区分一覧
                l_orderPriceDivList = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);
            }

            //--------------------
            //時価情報取得
            //--------------------
            //時価、現在値時刻、前日比を取得
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();
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
        //
        //--------------------
        //分岐フロー リクエスト.銘柄コード≠null
        //--------------------
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType) && 
            l_request.productCode != null)
        {
            l_lstWkMarketCodeList = new ArrayList();
            for (int i = 0; i < l_iMarketListCnt; i++)
            {
                //reset市場コード
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCodeList[i]);
                
                try {
                    //get市場
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_strInstitutionCode, l_strMarketCodeList[i]);
                    
                    //validate取引銘柄
                    l_tradedProduct = (WEB3EquityTradedProduct) l_orderManager
                        .validateTradedProduct(l_eqTypeProduct, l_market);
                    
                    //例外が発生しない場合のみ市場コード一覧への設定対象とする
                    l_lstWkMarketCodeList.add(l_strMarketCodeList[i]);
                }
                catch (WEB3BaseException l_wbe){}
                catch (NotFoundException l_nfe){
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

            //0件時はエラー
            if (l_lstWkMarketCodeList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00643,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                //例外が発生しない場合、
                //当該市場コードをレスポンス．市場コード一覧への設定対象とする。
                l_strResMarketCodeList = new String[l_lstWkMarketCodeList.size()];
                l_lstWkMarketCodeList.toArray(l_strResMarketCodeList);
            }
        }
        else
        {
            l_strResMarketCodeList = l_strMarketCodeList;
        }

        //--------------------
        //リクエスト市場コードのチェック
        //--------------------
        TreeMap l_treeMapMarketCodeList = new TreeMap();
        TreeMap l_treeMapOrderBizDateList = new TreeMap();
        Integer l_iWkMarketCode;
        Date l_datWkOrderBizDate = null;
        boolean l_blnIsHandingMarket = false;

        //リクエスト．取引区分＝現物買注文の場合のみ
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
            
            //--------------------
            //市場コードの配列(=get取扱可能市場()の戻り値)の要素分、LOOP処理
            //（TreeMapに追加することでソートされる）
            //--------------------
            for (int i = 0; i < l_strResMarketCodeList.length; i++)
            {
                //reset市場コード
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strResMarketCodeList[i]);

                // get市場(証券会社コード : , 市場コード : )
                //証券会社コード:補助口座.get証券会社.get証券会社コード()
                //市場コード： LOOP処理内で取得した市場コード
                try
                {
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                        l_strInstitutionCode,
                        l_strResMarketCodeList[i]);
                }
                catch(NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //get発注日
                try {
                    l_iWkMarketCode = new Integer(l_strResMarketCodeList[i]);
                }
                catch (NumberFormatException l_nfe)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //市場.isPTS市場==falseの場合
                if (!l_market.isPTSMarket())
                {
                    l_datWkOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                }

                //TreeMapに追加する
                l_treeMapMarketCodeList.put(l_iWkMarketCode, l_strResMarketCodeList[i]);
                l_treeMapOrderBizDateList.put(l_iWkMarketCode, l_datWkOrderBizDate);
            }

            //--------------------
            //分岐フロー リクエスト.市場コード≠nullの場合
            //リクエスト．市場コードのチェック
            //--------------------
            if (l_request.marketCode != null)
            {
                Set stKey = l_treeMapMarketCodeList.entrySet();
                Iterator l_iteMapMarketCodeList = stKey.iterator();
                while (l_iteMapMarketCodeList.hasNext())
                {
                    Object l_objMapMarketCodeList = l_iteMapMarketCodeList.next();
                    Map.Entry l_entMapMarketCodeList = (Map.Entry)l_objMapMarketCodeList;
                    String l_valMarketCode = (String)l_entMapMarketCodeList.getValue();
                    if (l_request.marketCode.equals(l_valMarketCode))
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
        }
        //リクエスト．取引区分＝立会外分売の場合のみ
        else if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
        {
            //--------------------
            //is取扱可能市場
            //--------------------
            if (l_request.marketCode != null)
            {
                //is取扱可能市場
                l_blnIsHandingMarket = l_branch.isHandlingPossibleMarket(
                    l_request.marketCode, ProductTypeEnum.EQUITY);
                
                //リクエスト．市場コードが取扱可能市場に含まれていない場合エラー
                if (l_blnIsHandingMarket == false)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }

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

        //リクエスト.市場コード！＝nullの場合
        //get市場(証券会社コード : , 市場コード : )
        //証券会社コード:補助口座.get証券会社.get証券会社コード()
        //市場コード： リクエスト.市場コード
        if (l_request.marketCode != null)
        {
            try
            {
                l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                    l_strInstitutionCode,
                    l_request.marketCode);
            }
            catch(NotFoundException l_ex)
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

        //（リクエスト.市場コード==nul） または
        //（リクエスト.市場コード != null かつ 市場.isPTS市場()==true) の場合
        //※出来るまで注文From日付が必要なのは現物買注文の場合のみ
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType)
            && (l_request.marketCode == null
                || (l_request.marketCode != null && l_market.isPTSMarket())))
        {
            Set stKey = l_treeMapOrderBizDateList.entrySet();
            Iterator l_iteMapOrderBizDateList = stKey.iterator();
            
            while (l_iteMapOrderBizDateList.hasNext())
            {
                Object l_objMapOrderBizDateList = l_iteMapOrderBizDateList.next();
                Map.Entry l_entMapOrderBizDateList = (Map.Entry)l_objMapOrderBizDateList;
                Date l_valOrderBizDate = (Date)l_entMapOrderBizDateList.getValue();
                
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
                l_datOrderUntilDeadLineFrom = WEB3DateUtility.toDay(l_genBizDate.roll(1));;
            }
        }
        //リクエスト.市場コード != null かつ 市場.isPTS市場()==falseの場合
        //出来るまで注文From日付にnullを設定する。
        else
        {
            l_datOrderUntilDeadLineFrom = null;
        }

        //--------------------
        //出来るまで注文開始日／終了日／注文期限内祝日一覧取得
        //--------------------
        Date l_datOrderUntilDeadLineStartDate       = null;     //出来るまで注文開始日
        Date l_datOrderUntilDeadLineEndDate         = null;     //出来るまで注文終了日
        Date[] l_datOrderUntilDeadLineHolidayList   = null;     //注文期限内祝日一覧
        
        //分岐フロー 取扱可能注文条件.isできるまで注文取扱可能() == trueの場合
        //※ 取扱可能注文条件は「現物買注文」の時のみ取得
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType)
            && l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling())
        {
			//reset市場コード
			WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);

            //出来るまで注文開始日・終了日・注文期限内祝日一覧取得
            l_datOrderUntilDeadLineStartDate = l_gentradeHandingOrderCond
                .getOrderUntilDeadLineStartDay(l_datOrderUntilDeadLineFrom);
            l_datOrderUntilDeadLineEndDate = l_gentradeHandingOrderCond
                .getOrderUntilDeadLineEndDay(l_datOrderUntilDeadLineFrom);
            l_datOrderUntilDeadLineHolidayList = l_gentradeHandingOrderCond
                .getExpirationDateHoliday(l_datOrderUntilDeadLineFrom);
        }

        //--------------------
        //is特定口座開設
        //--------------------
        boolean l_blnIsSpecialAccountEstablished = false;
        if (l_request.productCode != null && l_request.marketCode != null)
        {
            l_blnIsSpecialAccountEstablished = l_mainAccount
                .isSpecialAccountEstablished(l_tradedProduct.getDailyDeliveryDate(), l_subAccount);
        }
        else
        {
            l_blnIsSpecialAccountEstablished = l_mainAccount
                .isSpecialAccountEstablished(l_subAccount);
        }

        //--------------------
        //validate顧客銘柄別取引停止
        //--------------------
        this.validateAccountProductOrderStop(
            l_subAccount, l_lngProductId, l_blnIsPTSAccountOpen, l_request.marketCode);

        //--------------------
        //プロパティセット
        //--------------------
        WEB3EquityBuyInputResponse l_response = (WEB3EquityBuyInputResponse) l_request.createResponse();
        
        String[] l_strTaxTypeList;
        if (l_blnIsSpecialAccountEstablished)
        {
            l_strTaxTypeList = new String[2];
            l_strTaxTypeList[0] = WEB3AccountDivDef.NORMAL;
            l_strTaxTypeList[1] = WEB3AccountDivDef.SPECIAL;
        }
        else
        {
            l_strTaxTypeList = new String[1];
            l_strTaxTypeList[0] = WEB3AccountDivDef.NORMAL;
        }

        //リクエスト．取引区分＝「現物買注文」の場合
        if (WEB3TradingTypeDef.BUY_ORDER.equals(l_request.tradingType))
        {
            String[] l_strWkPriceDivList        = {
                WEB3OrderPriceDivDef.MARKET_PRICE, WEB3OrderPriceDivDef.LIMIT_PRICE};
                
            if (l_request.productCode != null && l_request.marketCode != null)
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
            l_response.marketList               = (String[]) l_treeMapMarketCodeList.values().toArray(
                                                    new String[l_treeMapMarketCodeList.size()]);
            l_response.taxTypeList              = l_strTaxTypeList;
            l_response.expirationDateTypeList   = l_handingPossibleExpirationDateType;
            l_response.orderCondTypeList        = l_handingPossibleOrderCond;
            l_response.marketCode = l_request.marketCode;
        }
        //
        //リクエスト．取引区分＝「立会外分売」の場合
        else if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(l_request.tradingType))
        {
            String[] l_strWkPriceDivList        = {WEB3OrderPriceDivDef.LIMIT_PRICE};
            String[] l_strWkPriceCondList       = {WEB3PriceConditionDef.DEFAULT};
            String[] l_strWkExecCondList        = {WEB3ExecutionConditionDef.NO_CONDITION};
            String[] l_strWkMktCdList           = {l_request.marketCode};
            String[] l_strWkExpDatTypList       = {WEB3OrderExpirationDateTypeDef.DAY_LIMIT};
            String[] l_strWkOrderCondList       = {WEB3OrderingConditionDef.DEFAULT};
            
            l_response.orderPriceDivList        = l_strWkPriceDivList;
            l_response.priceCondList            = l_strWkPriceCondList;
            l_response.execCondList             = l_strWkExecCondList;
            l_response.wlimitExecCondList       = null;
            l_response.expirationStartDate      = null;
            l_response.expirationEndDate        = null;
            l_response.holidayList              = null;
            l_response.marketList               = l_strWkMktCdList;
            l_response.taxTypeList              = l_strTaxTypeList;
            l_response.expirationDateTypeList   = l_strWkExpDatTypList;
            l_response.orderCondTypeList        = l_strWkOrderCondList;
        }
        //両方の取引分共通のプロパティ
        l_response.messageSuspension        = l_strTradeCloseMarkets;
        l_response.insiderWarningFlag       = l_blnIsInsiderMessageSuspension;
        
        l_response.currentPriceDiv = l_strCurrentPriceDiv;
        
        //(*2)　@銘柄コード・市場コード指定(リクエストデータ.銘柄コード != null、かつ、リクエストデータ.市場コード != null)
        //      の場合のみ設定。以外、nullを設定。
        if (l_request.productCode != null && l_request.marketCode != null)
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
            l_response.boardCurrentPrice        = null;  
            l_response.boardCurrentPriceTime    = null; 
            l_response.boardCurrentPriceDiv     = null;  
            l_response.boardChange              = null; 
            l_response.volume                   = null; 
            l_response.volumeTime               = null; 
            l_response.askPriceTitle            = null;  
            l_response.askPrice                 = null; 
            l_response.askPriceTime             = null; 
            l_response.bidPriceTitle            = null;  
            l_response.bidPrice                 = null;  
            l_response.bidPriceTime             = null; 
            l_response.basePrice                = null;  
        }
        
        l_response.currentPriceTime         = l_tsCurrentPriceTime;
        l_response.tradingPower             = WEB3StringTypeUtility.formatNumber(l_dblEquityTradingPower);
        
        if (l_request.productCode != null)
        {
            EqtypeProductRow l_eqTypeProductRow = (EqtypeProductRow)l_eqTypeProduct.getDataSourceObject();
            l_response.productName              = l_eqTypeProductRow.getStandardName();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get買付可能額)<BR>
     * get買付可能額を取得する。<BR>
     * <BR>
     * 取引余力サービス.get株式買付可能額(引数の補助口座)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@return double
     * @@throws WEB3BaseException
     */
    protected double getEquityTradingPower(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEquityTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblEquityTradingPower =
            l_tradingpowerService.getEquityTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblEquityTradingPower;
    }

    /**
     * (get市場コード)<BR>
     * 市場コードを取得する。<BR>
     * <BR>
     * １）　@パラメータ.市場コード == nullまたは<BR>
     * 　@　@パラメータ.銘柄コード == nullの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@パラメータ.市場コード != null<BR>
     * 　@　@かつ　@パラメータ.銘柄コード != null<BR>
     * 　@　@かつ　@パラメータ.市場コード != "99：優先市場"の場合、パラメータ.市場コードを返却する。<BR>
     * <BR>
     * ３）　@上記以外の場合<BR>
     * <BR>
     * 　@３－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。<BR>
     * 　@　@　@　@　@[getProduct()に設定する引数]<BR>
     * 　@　@　@　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@　@　@　@銘柄コード　@　@　@：　@パラメータ.銘柄コード<BR>
     * <BR>
     * ３－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。<BR>
     * <BR>
     * ３－３）　@３－２）で取得した市場がnullの場合、例外をthrowする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_02702<BR>
     * 　@　@　@　@　@以外、市場.getMarketCode()の戻り値を返却する。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getMarketCode(
        String l_strProductCode,
        String l_strMarketCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarketCode(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@パラメータ.市場コード == nullまたは
        //　@パラメータ.銘柄コード == nullの場合、nullを返却する。
        if (l_strMarketCode == null || l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@パラメータ.市場コード != null
        //　@かつ　@パラメータ.銘柄コード != null
        //　@かつ　@パラメータ.市場コード != "99：優先市場"の場合、パラメータ.市場コードを返却する。
        if (l_strMarketCode != null &&
            l_strProductCode != null &&
            !WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            log.exiting(STR_METHOD_NAME);
            return l_strMarketCode;
        }

        //３）　@上記以外の場合
        //　@３－１）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。
        //　@　@　@　@　@　@[getProduct()に設定する引数]
        //　@　@　@　@　@　@　@証券会社コード：　@パラメータ.証券会社コード
        //　@　@　@　@　@　@　@銘柄コード　@　@　@：　@パラメータ.銘柄コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3EquityProductManager l_equityProductManager
            = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        Institution l_institution = null;
        WEB3EquityProduct l_eqTypeProduct = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            l_eqTypeProduct =
                (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //３－２）　@株式銘柄.get優先市場()をコールし、市場を取得する。
        Market l_market = l_eqTypeProduct.getPrimaryMarket();

        //　@３－３）　@３－２）で取得した市場がnullの場合、例外をthrowする。
        //　@　@　@　@　@　@以外、市場.getMarketCode()の戻り値を返却する。
        if (l_market == null)
        {
            log.debug("優先市場が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02702,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "優先市場が未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_market.getMarketCode();
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
     * 　@　@市場コードの配列を取得する<BR>
     * <BR>
     * 　@　@[PTS取引時間管理.get市場閉局警告市場()にセットする引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * 　@　@銘柄タイプ：　@"株式"<BR>
     * 　@　@信用取引区分：　@"DEFAULT"<BR>
     * <BR>
     * 　@　@２－２）　@１）の結果と　@２－１）の結果をマージして<BR>
     * 　@　@市場コード昇順でソートする。<BR>
     * <BR>
     * ３）取得した市場コードの配列を返却する。<BR>
     * <BR>
     * @@param l_gentradeBranch - (部店)<BR>
     * 補助口座.get取引店( )<BR>
     * @@param l_blnIsPTSAccountOpen - (isPTS口座開設)<BR>
     * 顧客.isPTS口座開設()で取得した値<BR>
     * @@throws WEB3BaseException
     * @@return String[]
     */
    protected String[] getTradeCloseMarket(
        WEB3GentradeBranch l_gentradeBranch,
        boolean l_blnIsPTSAccountOpen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseMarket(WEB3GentradeBranch, boolean)";
        log.entering(STR_METHOD_NAME);

        //１）取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する。
        //[取引時間管理.get市場閉局警告市場()にセットする引数]
        //部店：　@引数.部店
        //銘柄タイプ：　@"株式"
        //信用取引区分：　@"DEFAULT"
        String[] l_strMarketCodes = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
            l_gentradeBranch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

        //２）PTS口座開設顧客の場合(引数.isPTS口座開設==true)
        if (l_blnIsPTSAccountOpen)
        {
            //２－１）PTS取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得する
            //[PTS取引時間管理.get市場閉局警告市場()にセットする引数]
            //部店：　@引数.部店
            //銘柄タイプ：　@"株式"
            //信用取引区分：　@"DEFAULT"
            String[] l_strPTSMarketCodes = WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_gentradeBranch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

            //２－２）　@１）の結果と　@２－１）の結果をマージして市場コード昇順でソートする。
            l_strMarketCodes = this.mergeAndSort(l_strMarketCodes, l_strPTSMarketCodes);
        }

        //３）取得した市場コードの配列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }

    /**
     * (get取扱可能市場)<BR>
     * 取扱可能市場を取得する。<BR>
     * PTS口座開設顧客の場合、PTSの取扱可能市場も取得する。<BR>
     * <BR>
     * １）(部店市場別)取扱条件.get取扱可能市場()をコールし、<BR>
     * 　@市場コードの配列を取得する。<BR>
     * <BR>
     * 　@　@[(部店市場別)取扱条件.get取扱可能市場()にセットする引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * 　@　@銘柄タイプ：　@"株式"<BR>
     * <BR>
     * ２）PTS口座開設顧客の場合(引数.isPTS口座開設==true)<BR>
     * <BR>
     * 　@　@２－１）(部店市場別・PTS)取扱条件.get取扱可能市場()をコールし、<BR>
     * 　@　@市場コードの配列を取得する。<BR>
     * <BR>
     * 　@　@[(部店市場別・PTS)取扱条件.get取扱可能市場()にセットする引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * 　@　@銘柄タイプ：　@"株式"<BR>
     * <BR>
     * 　@　@２－２）　@１）の結果と　@２－１）の結果をマージして<BR>
     * 　@　@市場コード昇順でソートする。<BR>
     * <BR>
     * ３）取得した市場コードの配列を返却する。<BR>
     * <BR>
     * @@param l_gentradeBranch - (部店)<BR>
     * 補助口座.get取引店( )<BR>
     * @@param l_blnIsPTSAccountOpen - (isPTS口座開設)<BR>
     * 顧客.isPTS口座開設()で取得した値<BR>
     * @@throws WEB3BaseException
     * @@return String[]
     */
    protected String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_gentradeBranch,
        boolean l_blnIsPTSAccountOpen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleMarket(WEB3GentradeBranch, boolean)";
        log.entering(STR_METHOD_NAME);

        //１）(部店市場別)取扱条件.get取扱可能市場()をコールし、市場コードの配列を取得する。
        //[(部店市場別)取扱条件.get取扱可能市場()にセットする引数]
        //部店：　@引数.部店
        //銘柄タイプ：　@"株式"
        String[] l_strMarketCodes = WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
            l_gentradeBranch,  ProductTypeEnum.EQUITY);

        //２）PTS口座開設顧客の場合(引数.isPTS口座開設==true)
        if (l_blnIsPTSAccountOpen)
        {
            //２－１）(部店市場別・PTS)取扱条件.get取扱可能市場()をコールし、市場コードの配列を取得する。
            //[(部店市場別・PTS)取扱条件.get取扱可能市場()にセットする引数]
            //部店：　@引数.部店
            //銘柄タイプ：　@"株式"
            String[] l_strPTSMarketCodes = WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                l_gentradeBranch,  ProductTypeEnum.EQUITY);

            //２－２）　@１）の結果と　@２－１）の結果をマージして市場コード昇順でソートする。
            l_strMarketCodes = this.mergeAndSort(l_strMarketCodes, l_strPTSMarketCodes);
        }

        //３）取得した市場コードの配列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }

    /**
     * (isPTS口座開設)<BR>
     * 顧客.isPTS口座開設をコールし、結果を返却する<BR>
     * <BR>
     * １）引数.顧客オブジェクトのisPTS口座開設()をコールし、結果を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return boolean
     */
    protected boolean isPTSAccountOpen(WEB3GentradeMainAccount l_mainAccount)
    {
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
        WEB3GentradeMainAccount l_mainAccount,
        boolean l_blnIsPTSAccountOpen,
        String l_strMarketCode)
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
     * 　@　@　@注文種別： EQUITY_BUY（現物買注文）<BR>
     * <BR>
     * 　@２－２）　@２－１）以外の場合<BR>
     * <BR>
     * 　@　@　@拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする。<BR>
     * <BR>
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@補助口座：　@引数.補助口座<BR>
     * 　@　@　@銘柄ID：　@引数.銘柄ID<BR>
     * 　@　@　@注文種別： EQUITY_BUY（現物買注文）<BR>
     * <BR>
     * @@param l_subAccount -（補助口座）<BR>
     * 補助口座<BR>
     * @@param l_lngProductId -（銘柄ID）<BR>
     * 銘柄ID<BR>
     * @@param l_blnIsPTSAccountOpen - (isPTS口座開設)<BR>
     * isPTS口座開設<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@throws WEB3BaseException
     */
    protected void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        boolean l_blnIsPTSAccountOpen,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountProductOrderStop(SubAccount, long, boolean, String)";
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
            //注文種別： EQUITY_BUY（現物買注文）
            l_orderPTSManager.validatePTSAccountProductOrderStop(
                l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_BUY);
        }
        else
        {
            //　@２－２）　@２－１）以外の場合
            //拡張株式注文マネージャ.validate顧客銘柄別取引停止()をコールする。
            //[引数の設定]
            //補助口座：　@引数.補助口座
            //銘柄ID：　@引数.銘柄ID
            //注文種別： EQUITY_BUY（現物買注文）
            l_orderPTSManager.validateAccountProductOrderStop(
                l_subAccount, l_lngProductId, OrderTypeEnum.EQUITY_BUY);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 二つの配列を一つの配列に合併して、昇順ソートで返却する
     * @@param l_strArraySrc
     * @@param l_strDest
     * @@return String[]
     */
    private String[] mergeAndSort(String[] l_strArraySrcs, String[] l_strDests)
    {
        final String STR_METHOD_NAME = "mergeAndSort(String[], String[])";
        log.entering(STR_METHOD_NAME);

         Object[] l_objMergeArrays =
             WEB3Toolkit.toUnique(WEB3Toolkit.merge(l_strArraySrcs, l_strDests));

         if (l_objMergeArrays == null || l_objMergeArrays.length == 0)
         {
             return null;
         }

         String[] l_strResults = new String[l_objMergeArrays.length];

         for (int i = 0; i < l_objMergeArrays.length; i++)
         {
             l_strResults[i] = (String)l_objMergeArrays[i];
         }

         String l_strTemp = null;
         for (int i = 0; i < l_strResults.length; i++)
         {
             for (int j = i + 1; j < l_strResults.length; j++)
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
