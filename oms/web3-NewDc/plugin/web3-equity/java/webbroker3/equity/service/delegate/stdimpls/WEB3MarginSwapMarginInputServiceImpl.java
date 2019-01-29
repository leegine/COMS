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
filename	WEB3MarginSwapMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡入力サービス実装(WEB3MarginSwapMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
                 : 2006/12/14 唐性峰　@(中訊)　@モデルNo.1083
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginContractUnitContractPriceComparator;
import webbroker3.equity.WEB3MarginContractUnitOpenDateComparator;
import webbroker3.equity.WEB3MarginContractUnitFirstOpenDateComparator;
import webbroker3.equity.WEB3MarginTempContractUnit;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.message.WEB3MarginSwapMarginInputRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginInputResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （信用取引現引現渡入力サービスImpl）。<BR>
 * <BR>
 * 信用取引現引現渡入力サービス実装クラス。
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginSwapMarginInputService 
{
    /**
     * (ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginInputServiceImpl.class); 
    
    /**
     * (コンストラクタ)。
     * @@roseuid 4140066E0010
     */
    public WEB3MarginSwapMarginInputServiceImpl() 
    {
    }
    
    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引現引現渡入力サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータを「信用取引現引現渡注文入力リクエスト」型に変換し、<BR>
     * get現引現渡入力画面()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407CA5B103D4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MarginSwapMarginInputRequest)
        {
            //this.validateOrder((WEB3MarginSwapMarginInputRequest) l_request);
            l_response = this.getSwapMarginInputScreen((WEB3MarginSwapMarginInputRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //return l_request.createResponse();
        return l_response;
    }
    
    /**
     * (get現引現渡入力画面)。<BR>
     * <BR>
     * 信用取引現引現渡入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引現引現渡入力サービス）get現引現渡入力画面１」<BR>
     * 「（信用取引現引現渡入力サービス）get現引現渡入力画面２」<BR>
     * 「（信用取引現引現渡入力サービス）get現引現渡入力画面３」<BR>
     * 参照。<BR> 
     * <BR>
     *  ========================================================<BR>
     *   シーケンス図(「（信用取引現引現渡入力サービス）get現引現渡入力画面２」) <BR>
     * 　@　@: (25*)(*) 分岐フロー　@決済残高チェック<BR>   
     *   (*) 分岐フロー<BR>
     *   決済可能残高チェック<BR>
     *   注文株数が画面より入力された<BR>
　@　@ *    （リクエストデータ.注文株数 != null）場合、決済可能残高チェックを行う。<BR>
     *    －注文株数が決済可能残高より大きい<BR>
　@　@ *   （注文株数（残数）（：BigDecimal） > 0）場合、例外をスローする。<BR>   
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00657<BR>
     *   ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3MarginSwapMarginInputResponse<BR>
     * @@roseuid 41070977009B
     */
    protected WEB3MarginSwapMarginInputResponse getSwapMarginInputScreen(
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSwapMarginInputScreen(WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {   
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        
        log.debug("（信用取引現引現渡入力サービス）get現引現渡入力画面１（参照）");
        // 信用取引現引現渡入力 / （信用取引現引現渡入力サービス）get現引現渡入力画面１（参照）
        //2 信用取引返済注文入力リクエストで、validate()をコール
        l_request.validate();
        
        //3 信用取引現引現渡入力サービスImplで、get補助口座() 
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            WEB3EquityContract l_contract = this.getContract(l_request);

            //5 取引時間管理で、reset市場コード(String)を調用
            //引数は以下の通りに設定する。 
            //  市場コード：　@get建株(建株ID)で取得した建株.市場IDの市場オブジェクト.市場コード
            //WEB3GentradeMarket l_gentradeMarket = new WEB3GentradeMarket(l_contract.getMarketId());
            WEB3GentradeFinObjectManager l_objectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_getMarket = l_objectManager.getMarket(l_contract.getMarketId());
            String l_strMarketCode = l_getMarket.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
            //6 validate信用注文(補助口座, String)(拡張株式注文マネージャ::validate信用注文)
            //引数は以下の通り指定する。 
            //  補助口座：　@（get補助口座()の戻り値） 
            //  弁済区分：　@（get建株()で取得した建株.弁済区分） 
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_contract.getDataSourceObject();
            // 弁済区分
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();
            l_orderManager.validateMarginOrder(l_subAccount,l_strRepaymentType);
        
            //7 取引銘柄を取得,建株で、getTradedProduct()
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct = (WEB3EquityTradedProduct) l_contract.getTradedProduct();
            }
            catch (RuntimeSystemException l_rse)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "建株ID=[" + Long.toString(l_contract.getContractId()) + "]の建株に紐付く取引銘柄無し",
                    l_rse);
            }
            
            //8 getDailyDeliveryDate( )(取引銘柄::getDailyDeliveryDate)
            Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
            
            //9 validate特定口座開設（信用）(補助口座, TaxTypeEnum, Date)(拡張株式注文マネージャ::validate特定口座開設（信用）)
            //引数は以下の通り指定する。 
            //  補助口座：　@（get補助口座()の戻り値） 
            //  税区分：　@建株.税区分 
            //  受渡日：　@（getDailyDeliveryDate()の戻り値）
            l_orderManager.validateMarginSpecialAccountOpen(
                l_subAccount,
                l_eqtypeContractRow.getTaxType(),
                l_datDeliveryDate);
            
            //10 取引銘柄で、getMarket()(取引銘柄::getMarket)
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_tradedProduct.getMarket();
            
            //11 建株で、getProduct()
            Product l_product = l_contract.getProduct();
            
            //12 建株で、isShort()
            boolean l_blnShort = l_contract.isShort();
            
            //13 validate銘柄コード（信用）(java.lang.String, String, String)(拡張株式注文マネージャ::validate銘柄コード（信用）)
            //引数は以下の通り指定する。 
            //  銘柄コード：　@getProduct()の戻り値.getProductCode() 
            //  証券会社コード：　@補助口座.証券会社コード 
            //  弁済区分：　@建株.弁済区分 
            EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_orderManager.validateProductCode(
                l_productRow.getProductCode(),
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strRepaymentType);
            
            //14 validate取引銘柄（信用）(株式銘柄, 市場, 部店, String, OrderCategEnum, boolean)(拡張株式注文マネージャ::validate取引銘柄（信用）)
            //引数は以下の通り指定する。 
            //  株式銘柄：　@validate銘柄コード（信用）()の戻り値 
            //  市場：　@getMarket()の戻り値 
            //  部店：　@補助口座.get取引店() 
            //  弁済区分：　@建株.弁済区分 
            //  注文カテゴリ：　@OrderCategEnum.返済注文（CLOSE_MARGIN）固定 
            //  is売建：　@isShort()の戻り値
            WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
            WEB3EquityTradedProduct l_marginTrading =  (WEB3EquityTradedProduct)l_orderManager.validateTradedProductForMarginTrading(
                l_subAccount,
                l_equityProduct,
                l_market,
                l_branch,
                l_strRepaymentType,
                OrderCategEnum.SWAP_MARGIN,
                l_blnShort);

            //15 validate取扱可能市場（信用）(部店, 取引銘柄, String, String, double)(拡張株式注文マネージャ::validate取扱可能市場（信用）)
            //引数は以下の通り指定する。 
            //  部店：　@補助口座.get取引店() 
            //  取引銘柄：　@validate取引銘柄（信用）()の戻り値 
            //  市場コード：　@getMarket().getMarketCode() 
            //  弁済区分：　@建株.弁済区分 
            //  弁済期限値：　@建株.弁済期限値 
            l_orderManager.validateHandlingMarket(
                l_branch,
                l_marginTrading,
                l_market.getMarketCode().toString(),
                l_strRepaymentType,
                l_eqtypeContractRow.getRepaymentNum());
            
            //顧客銘柄別取引停止
            l_orderManager.validateAccountProductOrderStop(
                l_subAccount, l_equityProduct.getProductId(),
                l_blnShort ? OrderTypeEnum.SWAP_MARGIN_SHORT : OrderTypeEnum.SWAP_MARGIN_LONG);
        
            //16 インサイダーチェック(未設計のため、後対応)
            //isインサイダー警告表示
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_product.getProductId());
        
            log.debug("（信用取引現引現渡入力サービス）get現引現渡入力画面２（参照）");
            // （信用取引現引現渡入力サービス）get現引現渡入力画面２（参照）
        
            //3 get時価(EqTypeTradedProduct)(拡張プロダクトマネージャ::get時価)
            //引数は以下の通り指定する。 
            //  取引銘柄：　@（getTradedProduct()の戻り値） 
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            double l_dblCurrentPrice = l_productManager.getCurrentPrice((EqTypeTradedProduct)l_tradedProduct);
            
            WEB3MarginContractUnit[] l_unitTemp =
                this.createMarginContractUnitList(l_request, l_dblCurrentPrice);
            
            this.sortMarginContractUnitList(l_unitTemp, l_request);

            List l_lstContractUnits = new ArrayList(); 
            //信用取引建株明細Temp[]から、信用取引建株明細[]を作成する。            
            for (int i = 0; i < l_unitTemp.length; i++)
            {
                WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
                
                l_contractUnit.id = l_unitTemp[i].id;
                l_contractUnit.openDate = l_unitTemp[i].openDate;
                l_contractUnit.contractPrice = l_unitTemp[i].contractPrice;
                l_contractUnit.contractQuantity = l_unitTemp[i].contractQuantity;
                l_contractUnit.contractExecPrice = l_unitTemp[i].contractExecPrice;
                l_contractUnit.appraisalProfitLoss = l_unitTemp[i].appraisalProfitLoss;
                l_contractUnit.orderQuantity = l_unitTemp[i].orderQuantity;
                l_contractUnit.partContQuantity = l_unitTemp[i].partContQuantity;
                l_contractUnit.settlePriority = l_unitTemp[i].settlePriority;
                
                l_lstContractUnits.add(l_contractUnit);
            }
            WEB3MarginContractUnit[] l_contractUnits =
                new WEB3MarginContractUnit[l_unitTemp.length];
            l_lstContractUnits.toArray(l_contractUnits);

            //19 分岐フロー
            BigDecimal l_bdOrderQuantity = null;
            if (l_request.orderQuantity != null)
            {
                //20 注文株数(残数):igDecimal(arg0（=リクエストデータ.注文株数） : String)
                l_bdOrderQuantity = new BigDecimal(l_request.orderQuantity);
            }
            
            //21 建株明細[]（建株明細List.toArray()の戻り値）各要素毎のLOOP処理
            //（注文株数（残数）
            int l_intRequestDataLth = l_contractUnits.length;
            for (int i=0; i<l_intRequestDataLth; i++)
            {
                //24 (*2) プロパティセット
                //（リクエストデータ.注文株数 == null）の場合、建株明細[index].建株数
                if (l_request.orderQuantity == null)
                {
                    //注文株数
                    l_contractUnits[i].orderQuantity = l_contractUnits[i].contractQuantity;
                }
                else
                {
                    //（リクエストデータ.注文株数 != null） && （注文株数（残数） == 0）の場合、0
                    if (l_bdOrderQuantity.doubleValue() <= 0)
                    {
                        //注文株数
                        l_contractUnits[i].orderQuantity = String.valueOf(0);
                    }
                    // （リクエストデータ.注文株数 != null） && （注文株数（残数） >= 建株明細[index].建株数）の場合、建株明細[index].建株数
                    else if (l_bdOrderQuantity.doubleValue() >= Double.parseDouble(l_contractUnits[i].contractQuantity))
                    {
                        //注文株数
                        l_contractUnits[i].orderQuantity = l_contractUnits[i].contractQuantity;
                    }
                    // （リクエストデータ.注文株数 != null） && （注文株数（残数） < 建株明細[index].建株数）の場合、注文株数（残数）
                    else
                    {
                        //注文株数
                        l_contractUnits[i].orderQuantity = "" + l_bdOrderQuantity;
                    }
                }

                // 決済順位
                l_contractUnits[i].settlePriority = String.valueOf(i + 1);

                //22 分岐フロー
                if (l_request.orderQuantity != null)
                {
                    //23 注文株数(残数):subtract(arg0（=建株明細[index].建株数） : BigDecimal)
                    l_bdOrderQuantity = l_bdOrderQuantity.subtract(new BigDecimal(l_contractUnits[i].contractQuantity));
                }
            }

            //25 分岐フロー　@決済残高チェック
            //   注文株数が画面より入力された<BR>
            //      （リクエストデータ.注文株数 != null）場合、決済可能残高チェックを行う。<BR>
            //   －注文株数が決済可能残高より大きい<BR>
            //      （注文株数（残数）（：BigDecimal） > 0）場合、例外をスローする。<BR>   
            //          class: WEB3BusinessLayerException<BR>
            //          tag:   BUSINESS_ERROR_00657<BR>
            if (l_request.orderQuantity != null)
            {
                if (l_bdOrderQuantity.doubleValue() > 0)
                {
                    log.error("注文数量が決済可能残高数量を超えのエラー。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00657,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

            }

            log.debug("（信用取引現引現渡入力サービス）get現引現渡入力画面３（参照）");
            // （信用取引現引現渡入力サービス）get現引現渡入力画面３（参照）
            // get現引可能額
            Double l_dblSwapLongTradingPower =
                this.getActualReceiptTradingPower(l_subAccount, l_contract);
            
            //4 get市場閉局警告市場(部店, ProductTypeEnum, String)(取引時間管理::get市場閉局警告市場)
            //引数は以下の通り設定する。 
            //  部店：　@補助口座.get取引店() 
            //  銘柄タイプ：　@ProductTypeEnum."株式" 
            //  信用取引区分：　@建株.弁済区分 
            String[] l_strTradeCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                l_strRepaymentType);
            
            // 時価情報を取得する。
            WEB3EquityProductQuote l_productQuote =
                l_productManager.getDisplayEquityProductQuote((EqTypeTradedProduct)l_tradedProduct, l_subAccount);
            
            // 時価区分を取得する
            String l_strQuoteTypeDiv = l_productQuote.getQuoteTypeDiv();
            
            //  時価（現在値）を取得する
            double l_dblQuote = l_productQuote.getQuote();
            
            //  前日比を取得する
            double l_dblChange = l_productQuote.getComparedPreviousDay();
            
            //  取引時間(時価発表時間)を取得する
            Timestamp l_currentPriceTime = l_productQuote.getQuoteTime();
            
            //9 is特定口座開設(補助口座, String)(顧客::is特定口座開設)
            //引数は以下の通り指定する。 
            //  補助口座：　@this.get補助口座( ) 
            //  税区分タイプ：　@"1：現物株式"
            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            boolean l_blnSpecialAccount = l_account.isSpecialAccountEstablished(l_subAccount,WEB3GentradeEquityMarginDivDef.EQUITY);
            
            //10 createResponse( )(信用取引現引現渡注文入力リクエスト::createResponse)
            WEB3MarginSwapMarginInputResponse l_response = (WEB3MarginSwapMarginInputResponse)l_request.createResponse();
            
            log.debug("（信用取引現引現渡入力サービス）プロパティセット");
            //11  プロパティセット 
            //現引可能額：　@calc信用現引可能額()の戻り値
            if (l_dblSwapLongTradingPower != null)
            {
                l_response.swapLongTradingPower =
                    WEB3StringTypeUtility.formatNumber(l_dblSwapLongTradingPower.doubleValue());
            }
            
            //銘柄コード：　@株式銘柄.銘柄コード
            l_response.productCode = l_equityProduct.getProductCode();
            
            //銘柄名：　@株式銘柄.銘柄名　@
            //              ※株式銘柄はvalidate銘柄コード（信用）( )の戻り値にて取得
            EqtypeProductRow l_equityProductRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
            l_response.productName = l_equityProductRow.getStandardName();
            
            //市場コード：　@市場.市場コード
            //　@　@            ※市場はgetMarket( )にて取得
            l_response.marketCode = l_market.getMarketCode();
            
            //口座区分：　@
            //  （建株.税区分 == TaxTypeEnum.一般）の場合、"一般"
            //  （建株.税区分 == TaxTypeEnum.特定，または　@TaxTypeEnum.特定且つ源泉徴収）の場合、"特定"
            if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType()))
            {
                String l_strNormal = WEB3TaxTypeSpecialDef.NORMAL;
                l_response.taxType = l_strNormal;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_eqtypeContractRow.getTaxType())
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_eqtypeContractRow.getTaxType()))
            {
                String l_strSpecial = WEB3TaxTypeSpecialDef.SPECIAL;
                l_response.taxType = l_strSpecial;
            }

            //取引区分：　@
            //  （建株.isLong() == true）の場合、現引
            //  （建株.isLong() == false）の場合、現渡
            if (l_contract.isLong() == true)
            {
                l_response.tradingType = WEB3MarginTradeTypeDef.GENBIKI_ORDER;
            }
            else
            {
                l_response.tradingType = WEB3MarginTradeTypeDef.GENWADASI_ORDER;
            }

            //弁済：
            //  信用取引弁済.弁済区分：　@建株.弁済区分
            //  信用取引弁済.弁済期限値：　@建株.弁済期限値
            WEB3MarginRepaymentUnit l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_strRepaymentType;
            l_repayment.repaymentTimeLimit = String.valueOf(l_eqtypeContractRow.getRepaymentNum());
            l_response.repayment = l_repayment;

            //現引先現渡元口座区分一覧：
            //  ・（建株.税区分 == TaxTypeEnum.一般 && 建株.isLong() == true 現引）の場合、"一般"
            //    ※現引注文の場合、一般口座の建株を特定口座の保有資産に差し入れることはできない。
            //  ・上記以外の場合、
            //     （is特定口座開設() == false）の場合、"一般"
            //     （is特定口座開設() == true）の場合、｛"一般"，"特定"｝
            if (TaxTypeEnum.NORMAL.equals(l_eqtypeContractRow.getTaxType())
                    && l_contract.isLong() == true)
            {
                String[] l_strNormal = {WEB3TaxTypeSpecialDef.NORMAL};
                l_response.swapTaxTypeList = l_strNormal;
            }
            else if (l_blnSpecialAccount == false)
            {
                String[] l_strNormal = {WEB3TaxTypeSpecialDef.NORMAL};
                l_response.swapTaxTypeList = l_strNormal;
            }
            else if(l_blnSpecialAccount == true)
            {
                String[] l_strNormalSpecial = {
                    WEB3TaxTypeSpecialDef.NORMAL,
                    WEB3TaxTypeSpecialDef.SPECIAL};
                l_response.swapTaxTypeList = l_strNormalSpecial;
            }
            
            //時価区分
            l_response.currentPriceDiv = l_strQuoteTypeDiv;
            //時価(現在値)：
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblQuote);
            
            //前日比：
            l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
            
            //取引時間(時価発表時間)：
            l_response.currentPriceTime = l_currentPriceTime;
            
            //板情報項目の設定仕様は、以下の通り。
            //　@現在値：　@取得した株式銘柄時価情報.get現在値()の戻り値をセット
            l_response.boardCurrentPrice = l_productQuote.getBoardCurrentPrice();

            //　@現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻()の戻り値をセット
            l_response.boardCurrentPriceTime = l_productQuote.getBoardCurrentPriceTime();

            //　@現在値区分：　@取得した株式銘柄時価情報.get現在値区分()の戻り値をセット
            l_response.boardCurrentPriceDiv = l_productQuote.getBoardCurrentPriceDiv();

            //　@現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比()の戻り値をセット
            l_response.boardChange = l_productQuote.getBoardChange();

            //　@出来高：　@取得した株式銘柄時価情報.get出来高()の戻り値をセット
            l_response.volume = l_productQuote.getVolume();

            //　@出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻()の戻り値をセット
            l_response.volumeTime = l_productQuote.getVolumeTime();

            //　@買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分()の戻り値をセット
            l_response.askPriceTitle = l_productQuote.getAskPriceTitle();

            //　@買気配値：　@取得した株式銘柄時価情報.get買気配値()の戻り値をセット
            l_response.askPrice = l_productQuote.getAskPrice();

            //　@買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻()の戻り値をセット
            l_response.askPriceTime = l_productQuote.getAskPriceTime();

            //　@売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分()の戻り値をセット
            l_response.bidPriceTitle = l_productQuote.getBidPriceTitle();

            //　@売気配値：　@取得した株式銘柄時価情報.get売気配値()の戻り値をセット
            l_response.bidPrice = l_productQuote.getBidPrice();

            //　@売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻()の戻り値をセット
            l_response.bidPriceTime = l_productQuote.getBidPriceTime();

            //　@基準値段：　@取得した株式銘柄時価情報.get基準値段()の戻り値をセット
            l_response.basePrice = l_productQuote.getBasePrice();

            //建株明細一覧：　@建株明細[]（建株明細List.toArray()の戻り値）
            l_response.contractUnits = l_contractUnits;
            
            //取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値配列
            l_response.messageSuspension = l_strTradeCloseMarket;
            
            //インサイダー警告表示フラグ
            l_response.insiderWarningFlag = l_boolIsInsider;
            
            return l_response;
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }
    }
    
    /**
     * (get建株)<BR>
     * 建株を取得し返却する。<BR>
     * <BR>
     * 株式ポジションマネージャ.get建株()をコールし、<BR>
     * 戻り値を返却する。<BR>
     * <BR>
     * [get建株()に指定する引数]<BR>
     * 建株ID：　@リクエストデータ.ID[0]<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引現引現渡注文入力リクエストオブジェクト。
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    protected WEB3EquityContract getContract(
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract(WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract =
                (WEB3EquityContract)l_positionManager.getContract(
                    Long.parseLong(l_request.id[0]));
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
        return l_contract;
    }
    
    /**
     * (create建株明細一覧)<BR>
     * リクエストデータより信用取引建株明細の一覧を<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.リクエストデータ.IDの要素数分、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@２－１）　@株式ポジションマネージャ.get建株()にて<BR>
     * 　@　@建株を取得する。<BR>
     * <BR>
     * 　@　@[get建株()に指定する引数]<BR>
     * 　@　@　@建株ID：　@処理対象の要素<BR>
     * <BR>
     * 　@２－２）　@信用取引建株明細Tempインスタンスを生成する。<BR>
     * <BR>
     * 　@２－３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@ID：　@建株.建株ID<BR>
     * 　@　@建日 = 建株.建日<BR>
     * 　@　@建単価 = 建株.建単価<BR>
     * 　@　@建株数 = 未決済株数(*1)<BR>
     * 　@　@建代金 = 建株.get建代金(未決済株数)<BR>
     * 　@　@評価損益 =<BR>
     * 　@　@　@建株.get評価損益（建株諸経費考慮）(パラメータ.時価, 未決済株数)<BR>
     * 　@　@注文株数 = 0　@※当メソッドの外でセット。<BR>
     * 　@　@内出来数量 = null<BR>
     * 　@　@当初建日 = 建株.当初建日<BR>
     * 　@　@<BR>
     * 　@　@(*1)建株.建株数 - ロック中数量(*2)<BR>
     * 　@　@(*2)建株.getLockedQuantity()にて取得。<BR>
     * <BR>
     * 　@２－４）　@ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_request - (クエストデータ)<BR>
     * 信用取引現引現渡注文入力リクエストオブジェクト。
     * @@param l_dblCurrentPrice - (時価)<BR>
     * 時価。
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        WEB3MarginSwapMarginInputRequest l_request,
        double l_dblCurrentPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(WEB3MarginSwapMarginInputRequest, double)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstContractUnit = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        for (int i = 0;i < l_request.id.length;i++)
        {
            WEB3EquityContract l_contract = null;
            try
            {
                l_contract =
                    (WEB3EquityContract)l_positionManager.getContract(
                        Long.parseLong(l_request.id[i]));
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            WEB3MarginTempContractUnit l_contractUnit = new WEB3MarginTempContractUnit();
            EqtypeContractRow l_contractRow = (EqtypeContractRow)l_contract.getDataSourceObject();

            l_contractUnit.id = Long.toString(l_contract.getContractId());
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
            l_contractUnit.contractPrice =
                WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
            double l_dblContractQuantity =
                l_contract.getQuantity() - l_contract.getLockedQuantity();
            l_contractUnit.contractQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
            l_contractUnit.contractExecPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_contract.getContractAmount(l_dblContractQuantity));
            double l_dblAppraisalProfitLoss =
                l_contract.getAppraisalProfitOrLossExpenses(
                    l_dblCurrentPrice,
                    l_dblContractQuantity);
            l_contractUnit.appraisalProfitLoss =
                WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitLoss);
            l_contractUnit.orderQuantity = "0";
            l_contractUnit.partContQuantity = null;
            l_contractUnit.firstOpenDate = l_contractRow.getFirstOpenDate();
            
            l_lstContractUnit.add(l_contractUnit);
        }
        
        WEB3MarginTempContractUnit[] l_contractUnits =
            new WEB3MarginTempContractUnit[l_lstContractUnit.size()];
        l_lstContractUnit.toArray(l_contractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (sort建株明細一覧)<BR>
     * 引数の建株明細一覧をソートする。<BR>
     * <BR>
     * 処理内容は、<BR>
     * シーケンス図<BR>
     * 「（信用取引現引現渡入力サービス）get現引現渡入力画面２」の<BR>
     * WEB3ArraysUtility.sort()のノートアンカーを参照。<BR>
     * @@param l_contractUnits - (建株明細一覧)<BR>
     * 信用取引建株明細の配列。（実際の型：信用取引建株明細Temp型）
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引現引現渡注文入力リクエストオブジェクト。
     * @@throws WEB3BaseException
     */
    protected void sortMarginContractUnitList(
        WEB3MarginContractUnit[] l_contractUnits,
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sortMarginContractUnitList(WEB3MarginContractUnit[], WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSortKeyCnt = l_request.sortKeys.length;
        int index = 0;
        List l_lstComparators = new ArrayList();
            
        for (int i = 0;i < l_intSortKeyCnt;i++)
        {
            WEB3MarginSortKey l_sortKey = l_request.sortKeys[index];
                
            if (WEB3EquityKeyItemDef.OPEN_DATE.equals(l_sortKey.keyItem))
            {
                l_lstComparators.add(
                    new WEB3MarginContractUnitOpenDateComparator(l_sortKey.ascDesc));
                l_lstComparators.add(
                    new WEB3MarginContractUnitFirstOpenDateComparator(WEB3AscDescDef.ASC));
                l_intSortKeyCnt++;
                i++;
            }
            else if (WEB3EquityKeyItemDef.CONTRACT_PRICE.equals(l_sortKey.keyItem))
            {
                l_lstComparators.add(
                    new WEB3MarginContractUnitContractPriceComparator(l_sortKey.ascDesc));
            }
            index++;
        }
        Comparator[] l_comparators = new Comparator[l_intSortKeyCnt];
        l_lstComparators.toArray(l_comparators);
        
        WEB3ArraysUtility.sort(l_contractUnits, l_comparators);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get現引可能額)<BR>
     * 現引可能額を取得する。<BR>
     * <BR>
     * １）　@引数の建株.isLong()==false（not買建）の場合は、nullを返却する。<BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@取引余力サービス.get信用現引可能額(引数の補助口座)の戻り値を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_contract - (建株)<BR>
     * 建株オブジェクト。
     * @@return Double
     * @@throws WEB3BaseException
     */
    protected Double getActualReceiptTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityContract l_contract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getActualReceiptTradingPower(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        if (!l_contract.isLong())
        {
            return null;
        }
        
        WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblSwapLongTradingPower =
            l_tradingPowerService.getActualReceiptTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblSwapLongTradingPower);
    }
}
@
