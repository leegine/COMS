head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者外国株式出来入力サービスImpl(WEB3AdminFeqExecutionInputServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/18 戴義波(中訊) 新規作成
                    2005/08/01 韋念瓊(中訊) レビュー
 Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
 Revesion History : 2008/02/02 柴双紅(中訊) モデルNo.396
 Revesion History : 2009/08/03 武　@波(中訊) モデルNo.509
 */

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqAmountCalcResultFactor;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductTypeOrderSubmitterPersistenceManager;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionInputResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionSearchRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionSearchResponse;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqExecuteUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionInputService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式出来入力サービスImpl) <BR>
 * 管理者外国株式出来入力サービス実装クラス <BR>
 * 
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputServiceImpl implements WEB3AdminFeqExecutionInputService
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionInputServiceImpl.class);

    /**
     * @@roseuid 42CE39F100CB
     */
    public WEB3AdminFeqExecutionInputServiceImpl()
    {

    }

    /**
     * 外国株式出来入力処理を実施する。 <BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。 <BR>
     * <BR>
     * －get検索画面() <BR>
     * －get入力画面() <BR>
     * －validate出来() <BR>
     * －submit出来() <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            リクエストデータ <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F840136
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        //リクエストデータの型により、以下のメソッドをコールする。
        //－get検索画面()
        if (l_request instanceof WEB3AdminFeqExecutionSearchRequest)
        {
            l_response = getSearchScreen((WEB3AdminFeqExecutionSearchRequest) l_request);
        }

        //－get入力画面()
        else if (l_request instanceof WEB3AdminFeqExecutionInputRequest)
        {
            l_response = getInputScreen((WEB3AdminFeqExecutionInputRequest) l_request);
        }

        //－validate出来()
        else if (l_request instanceof WEB3AdminFeqExecutionConfirmRequest)
        {
            l_response = validateExec((WEB3AdminFeqExecutionConfirmRequest) l_request);
        }

        //－submit出来()
        else if (l_request instanceof WEB3AdminFeqExecutionCompleteRequest)
        {
            l_response = submitExec((WEB3AdminFeqExecutionCompleteRequest) l_request);
        } 
        else
        {
            log.debug("error in get necessory request");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索画面) <BR>
     * 検索画面表示処理。 <BR>
     * <BR>
     * シーケンス図「（(管)出来入力）get検索画面」 参照。 <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            リクエストデータ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B91317022E
     */
    protected WEB3AdminFeqExecutionSearchResponse getSearchScreen(
        WEB3AdminFeqExecutionSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminFeqExecutionSearchRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 管理者オブジェクトを取得 getInstanceFromログイン情報( )
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.2 管理者の権限チェックを行う
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 レスポンスデータを生成する
        WEB3AdminFeqExecutionSearchResponse l_response = 
            (WEB3AdminFeqExecutionSearchResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面) <BR>
     * 入力画面の表示に必要なデータを取得する。 <BR>
     * <BR>
     * シーケンス図「（(管)出来入力）get入力画面」 参照。 <BR>
     * ======================================================== <BR>
     * シーケンス図(「(外国株式サービスモデル) /<BR>
     * (管)出来入力」((管)出来入力）get入力画面) <BR>
     * : 1.6 未発注の場合（getOrderStatus() ==”1:受付済（新規注文）”）、 <BR>
     * 例外をスローする <BR>
     * 未発注の場合（getOrderStatus() == ”1:受付済（新規注文）”）、 <BR>
     * 例外をスローする <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02161 <BR>
     * ========================================================== <BR>
     * ========================================================== <BR>
     * シーケンス図(「(外国株式サービスモデル) /<BR>
     * (管)出来入力」((管)出来入力）get入力画面) <BR>
     * : 1.8 出来終了後（is出来終了() == true）、例外をスローする。 <BR>
     * 出来終了後（is出来終了() == true）、例外をスローする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02162 <BR>
     * ========================================================== <BR>
     * ========================================================== <BR>
     * シーケンス図(「(外国株式サービスモデル) /<BR>
     * (管)出来入力」((管)出来入力）get入力画面) <BR>
     * : 1.10 (*) HOST発注の場合（isHOST発注() == true）<BR>
     * 、例外をスローする。 <BR>
     * HOST発注の場合（isHOST発注() == true）、例外をスローする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02163 <BR>
     * ========================================================== <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            リクエストデータ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F840221
     */
    protected WEB3AdminFeqExecutionInputResponse getInputScreen(
        WEB3AdminFeqExecutionInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminFeqExecutionInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 リクエストデータの整合性をチェックする validate()
        l_request.validate();

        //1.2 管理者オブジェクトを取得 getInstanceFromログイン情報( )
         WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
         //1.3 管理者の権限チェックを行う
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);

        //get証券会社コード
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //get運用コード(証券会社コード : String, 運用コード : String)
        //証券会社コード： get証券会社コード（）の戻り値
        //運用コード：リクエストデータ.運用コード
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.4 運用コードに該当する注文単位を取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingModule.getOrderManager();
        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
       
        //[get有効注文単位By運用コード()に指定する引数]
        // 発注日： リクエストデータ.発注日
        // ※未入力の場合、処理日時（TradingSystem.getSystemTimestamp()）の日付部分。
        // 運用コード： get運用コード（）の戻り値
        Date l_datBizDate = null;
        if (l_request.orderBizDate == null)
        {
            l_datBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            log.debug("l_request.orderBizDate == null, so l_datBizdat = " + l_datBizDate);
        } 
        else
        {
            l_datBizDate = l_request.orderBizDate;
        }
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit) l_orderManager.getValidOrderUnitByOrderEmpCode(
                l_datBizDate,
                l_strEmpCode);
        
        if (l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "外国株式注文単位が存在しない。");
        }
        // 対象データが注文受付取消認証を行っていない場合　@処理対象外
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (l_confirmedPrice)
        {
            log.debug("該当する注文IDデータは対象外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.5 注文状態を取得する getOrderStatus()
        //1.6 未発注の場合（getOrderStatus() == ”1:受付済（新規注文）”）又は
        //    発注中の場合（getOrderStatus() == ”2:発注中（新規注文）”）、例外をスローする
        if (OrderStatusEnum.ACCEPTED.equals(l_feqOrderUnit.getOrderStatus()) ||
            OrderStatusEnum.ORDERING.equals(l_feqOrderUnit.getOrderStatus()))
        {
            String l_strMessage = "注文状態を取得例外";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02161, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.7 出来終了後かを判定する
        //1.8 出来終了後（is出来終了() == true）、例外をスローする
        if (l_feqOrderUnit.isExecEnd())
        {
            String l_strMessage = "出来終了";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02162, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.9 HOST注文かを判定する
        //1.10 HOST発注の場合（isHOST発注() == true）、例外をスローする
        if (l_feqOrderUnit.isHOSTOrder())
        {
            String l_strMessage = "HOST注文";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02163, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.11 外国株式銘柄オブジェクトを取得する
        WEB3FeqProduct l_web3FeqProduct = 
            (WEB3FeqProduct) l_feqOrderUnit.getProduct();
            
        if (l_web3FeqProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "外国株式銘柄が存在しない。");
        }        

        //1.12 通貨オブジェクトを取得する
        WEB3GentradeCurrency l_currency = l_web3FeqProduct.getCurrency();

        //1.13 約定為替レートを取得する
        //[get為替レート()に指定する引数]
        //is買付：
        //注文単位.getSide() == ”買”の場合true
        //以外、false
        //is約定計算： true
        //入力為替レート： 0
        boolean l_blnIsBuy = false;
        if (SideEnum.BUY.equals(l_feqOrderUnit.getSide()))
        {
            l_blnIsBuy = true;
            log.debug("注文単位.getSide() == ”買”の場合true");
        }
        
        double l_dblFxRate = l_currency.getExchangeRate(
            l_blnIsBuy, 
            true, 
            0);

        //1.4 レスポンスデータを生成する
        WEB3AdminFeqExecutionInputResponse l_response = 
            (WEB3AdminFeqExecutionInputResponse) l_request.createResponse();
        
        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        if (l_feqOrderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "注文単位Rowが存在しない。");
        }
            
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = 
                (WEB3GentradeMainAccount) l_finApp.getAccountManager().getMainAccount(
                    l_feqOrderUnitRow.getAccountId());
        } 
        catch (NotFoundException l_ex)
        {
            log.error("getMainAccount not found");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "getMainAccount not found", 
                l_ex);
        }
        
        //(*)プロパティセット
        //識別コード： 注文単位.識別コード
        l_response.requestNumber = l_feqOrderUnitRow.getOrderRequestNumber();

        //注文ＩＤ： 注文単位.注文ＩＤ
        l_response.orderId = Long.toString(l_feqOrderUnitRow.getOrderId());

        //顧客コード： 注文単位.getAccountId()に該当する顧客.get表示顧客コード()。
        l_response.accountCode = l_mainAccount.getDisplayAccountCode();

        //特定口座区分： 注文単位.税区分 == ”一般”の場合”一般”、以外”特定”。
        l_response.taxType = 
            TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()) ? 
                WEB3TaxTypeDivDef.NORMAL : WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE;

        //伝票番号： 注文単位.伝票Ｎｏ．
        l_response.orderNumber = l_feqOrderUnitRow.getVoucherNo();

        //注文時間： 注文単位.受注日時
        l_response.orderDate = l_feqOrderUnitRow.getReceivedDateTime();
        
        //発注日： 注文単位.発注日
        l_response.orderBizDate = 
            WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        //市場コード： 注文単位.市場ＩＤに該当する市場.getMarketCode()。        
        if (!l_feqOrderUnitRow.getMarketIdIsNull())
        {
            long l_lngMarketId = l_feqOrderUnitRow.getMarketId();
            
            try
            {
                l_response.marketCode = 
                    l_finObjectManager.getMarket(l_lngMarketId).getMarketCode();
            } 
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "Market not found";
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_strMessage, 
                    l_ex);
            }
        }
        else
        {
            l_response.marketCode = null;                                
        }        

        //銘柄コード： 外国株式銘柄.getProductCode()
        l_response.productCode = l_web3FeqProduct.getProductCode();

        //銘柄名： 外国株式銘柄.get表示銘柄名()
        l_response.productName = l_web3FeqProduct.getDisplayProductName();

        //売買区分： （注文単位.getSide() == SideEnum.買）の場合”買付”，以外”売付”。
        l_response.dealingType = 
            SideEnum.BUY.equals(l_feqOrderUnit.getSide()) ? 
                Integer.toString(SideEnum.BUY.intValue()) : Integer.toString(SideEnum.SELL.intValue());

        //注文数量： 注文単位. 注文数量
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getQuantity());

        //注文単価： 注文単位.指値
        l_response.orderPrice = WEB3StringTypeUtility.formatNumber(l_feqOrderUnit.getLimitPrice());

        //決済区分： 注文単位.決済区分
        l_response.settleDiv = l_feqOrderUnitRow.getSettleDiv();

        //約定為替レート： get通貨().get為替レート()        
        l_response.execExchangeRate = 
            WEB3StringTypeUtility.formatNumber(l_dblFxRate);
            
        //WEB3-XBFEQ-A-UT-0141 
        //現地銘柄コード           
        ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
        if (l_productMgr == null)
        {
            log.debug("ProductManagerが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "ProductManagerが存在しない。");
        }
        
        try
        {
            WEB3FeqProduct l_product = 
                (WEB3FeqProduct)l_productMgr.getProduct(l_feqOrderUnitRow.getProductId());//NotFoundException
                
            if (l_product == null)
            {
                log.debug("銘柄が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "銘柄が存在しない。");
            }
            
            l_response.localProductCode = l_product.getOffshoreProductCode();
        }
        catch (NotFoundException l_ex) 
        {
            log.debug("銘柄が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "銘柄が存在しない。");
        }                

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate出来) <BR>
     * 出来入力確認処理を行う。 <BR>
     * <BR>
     * シーケンス図「（(管)出来入力）validate出来」 参照。 <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            リクエストデータ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F8402EC
     */
    protected WEB3AdminFeqExecutionConfirmResponse validateExec(
        WEB3AdminFeqExecutionConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateExec(WEB3AdminFeqExecutionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accManage = l_finApp.getAccountManager();

        //1.1 リクエストデータの整合性をチェックする validate()
        l_request.validate();

        //1.2 管理者の権限チェックを行う
        //[validate権限()に指定する引数]
        // 機@能カテゴリコード： 機@能カテゴリコード.”外株（注文約定管理）”
        // is更新： true
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 注文単位オブジェクトを取得する
        //[get注文単位ByOrderId()に指定する引数]
        // 注文ＩＤ： リクエストデータ.注文ＩＤ
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit) l_orderManager.getOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));

        //1.4 市場コードをセットする
        //[reset市場コード()に指定する引数]
        // 市場コード： 注文単位.get市場().getMarketCode()
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_feqOrderUnit.getMarket().getMarketCode());

        //1.5（※入力のある場合のみ）
        //約定日のチェックを行う。
        //[validate約定日()に指定する引数]
        //注文単位： get注文単位ByOrderId()
        //約定日： リクエストデータ.約定日
        if (l_request.executionDate != null)
        {
            log.debug("1.5（※入力のある場合のみ）約定日のチェック");
            
            l_orderManager.validateExecutionDate(
                l_feqOrderUnit, 
                l_request.executionDate);
        }

        //1.6 約定数量のチェックを行う。
        //[validate約定数量()に指定する引数]
        //注文単位： get注文単位ByOrderId()
        //約定数量： リクエストデータ.出来情報一覧.約定数量の合計値（sum）
        double l_dblExecQuantityTotal = 0;
        
        int l_intCnt = 0;
        
        if (l_request.executeList != null && l_request.executeList.length > 0)
        {
            l_intCnt = l_request.executeList.length;
        }
        
        WEB3FeqExecuteUnit[] l_feqExecuteUnits = l_request.executeList;
        for (int i = 0; i < l_intCnt; i++)
        {
            l_dblExecQuantityTotal = 
                l_dblExecQuantityTotal + 
                Double.parseDouble(l_feqExecuteUnits[i].execQuantity);
        }
        
        log.debug("リクエストデータ.出来情報一覧.約定数量の合計値（sum）:" + l_dblExecQuantityTotal);
        l_orderManager.validateExecutedQuantity(
            l_feqOrderUnit, 
            l_dblExecQuantityTotal);

        //1.7 （※入力のある場合のみ）
        //現地受渡日のチェックを行う。
        //[validate現地受渡日()に指定する引数]
        //注文単位： get注文単位ByOrderId()
        //約定日： リクエストデータ.現地受渡日
        if (l_request.localDeliveryDate != null)
        {
            log.debug("1.7 （※入力のある場合のみ）現地受渡日のチェック");
            
            l_orderManager.validateFDeliveryDate(
                l_feqOrderUnit, 
                l_request.localDeliveryDate);
        }
        
        // (*1)当日日時を取得
        Date l_datExecTimestamp = GtlUtils.getSystemTimestamp();

        //1.8 リクエストデータ.出来情報一覧[]の要素数分LOOP処理
        log.debug("リクエストデータ.出来情報一覧[]の要素数:" + l_intCnt);
        
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.8 約定単価のチェックを行う。
            //[validate約定単価()に指定する引数]
            //注文単位： get注文単位ByOrderId()
            //約定数量： リクエストデータ.出来情報一覧[index].約定単価
            l_orderManager.validateExecutedPrice(
                l_feqOrderUnit, 
                Double.parseDouble(l_feqExecuteUnits[i].execPrice));
                
            // リクエスト.出来情報一覧[index].約定日時 != nullの場合
            // （約定時間が入力されている場合）
            // 約定日時のチェックを行う。
            // [validate約定日()に指定する引数] 
            // 注文単位：　@注文単位 
            // 約定日：　@リクエストデータ.出来情報一覧[index].約定日時 
            if (l_feqExecuteUnits[i].executionTimestamp != null)
            {
                l_orderManager.validateExecutionDate(
                    l_feqOrderUnit,
                    l_feqExecuteUnits[i].executionTimestamp);
            }
            // リクエスト.出来情報一覧[index].約定日時 == nullの場合
            else
            {
                // 取得した当日日付(*1)をセットする。
                l_feqExecuteUnits[i].executionTimestamp = l_datExecTimestamp;
            }
        }

        //1.9 create出来詳細情報一覧
        //注文単位，出来情報一覧より約定計算を行う。
        //計算結果にて外国株式約定詳細（管理者）オブジェクトの配列を生成する。
        //
        //[create出来詳細情報一覧()に指定する引数]
        //注文単位行： 注文単位.getDataSourceObject()
        //出来情報一覧： リクエストデータ.出来情報一覧
        //約定日： リクエストデータ.約定日
        //※（約定日 == null）の場合、当日
        //約定為替レート： リクエストデータ.約定為替レート
        Date l_datExecDate = l_request.executionDate;
        if (l_datExecDate == null)
        {
            l_datExecDate = GtlUtils.getSystemTimestamp();
            
            log.debug("（約定日 == null）," + l_datExecDate);
        }
        
        WEB3FeqExecDetailInfoUnit[] l_web3FeqExecDetailInfoUnits = 
            createExecDetailInfoList(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject(), 
                l_request.executeList,
                l_datExecDate, 
                Double.parseDouble(l_request.execExchangeRate));

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizLogicProvider = 
            (WEB3FeqBizLogicProvider) l_tradingModule.getBizLogicProvider();
        WEB3GentradeSubAccount l_web3GentradeSubAccount = null;
        try
        {
            l_web3GentradeSubAccount = 
                (WEB3GentradeSubAccount) l_accManage.getSubAccount(
                    l_feqOrderUnit.getAccountId(), 
                    l_feqOrderUnit.getSubAccountId());
        } 
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "not found sub account";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }

        //1.10 売付の場合（注文単位.getSide() == SideEnum."売"）、譲渡損益／譲渡益税を計算する
        double l_deliveryPriceTotal = 0;
        double l_execQuantityTotal = 0;
        double l_dblCapitalProfitLoss = 0;
        double l_dblCapitalGainTax = 0;
        if (SideEnum.SELL.equals(l_feqOrderUnit.getSide()))
        {
            log.debug("1.10 売付の場合（注文単位.getSide() == SideEnum.売）");
            
            //1.10.1 譲渡損益を計算する。
            //[calc譲渡損益()に指定する引数]
            //売買代金（円貨）： 受渡代金（円貨）合計※1
            //売株数： 約定数量数量合計※2
            //銘柄ＩＤ： 注文単位.getProduct().getProductId()
            //補助口座： 注文単位.getSubAccountId()に該当する補助口座
            //税区分： 注文単位.getTaxType()
            //※1 出来詳細情報（：外国株式約定詳細（管理者））.受渡代金の合計値（sum）
            //※2 出来詳細情報（：外国株式約定詳細（管理者））.約定数量の合計値（sum）
            int l_intCnt2 = 0;
            if (l_web3FeqExecDetailInfoUnits != null && l_web3FeqExecDetailInfoUnits.length > 0)
            {
                l_intCnt2 = l_web3FeqExecDetailInfoUnits.length;
            }
            
            for (int i = 0; i < l_intCnt2; i++)
            {
                l_deliveryPriceTotal = 
                    l_deliveryPriceTotal + 
                    Double.parseDouble(l_web3FeqExecDetailInfoUnits[i].deliveryPrice);
                l_execQuantityTotal += Double.parseDouble(l_web3FeqExecDetailInfoUnits[i].execQuantity);
            }
            l_dblCapitalProfitLoss = 
                l_bizLogicProvider.calcCapitalProfitLoss(
                    l_deliveryPriceTotal,
                    l_execQuantityTotal, 
                    l_feqOrderUnit.getProduct().getProductId(), 
                    l_web3GentradeSubAccount,
                    l_feqOrderUnit.getTaxType());
            
            log.debug("1.10.1 譲渡損益を計算する。" + l_dblCapitalProfitLoss);

            //1.10.2 譲渡益税を計算する。
            //[calc譲渡益税()に指定する引数]
            //補助口座： 注文単位.getSubAccountId()に該当する補助口座
            //税区分： 注文単位.getTaxType()
            //売買代金（円貨）： calc譲渡損益()
            //基準日： リクエストデータ.約定日時（日付部分）の３営業日後
            //※（約定日時 == null）の場合、当日の３営業日
            WEB3GentradeBizDate l_web3GentradeBizDate = 
                new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            if (l_datExecDate != null)
            {
                l_web3GentradeBizDate = 
                    new WEB3GentradeBizDate(new Timestamp(WEB3DateUtility.toDay(l_datExecDate).getTime()));
            }
            Date l_datBaseDate = l_web3GentradeBizDate.roll(3);
            log.debug("基準日:" + l_datBaseDate);
            
            l_dblCapitalGainTax = 
                l_bizLogicProvider.calcCapitalGainTax(
                    l_web3GentradeSubAccount, 
                    l_feqOrderUnit.getTaxType(), 
                    l_dblCapitalProfitLoss, 
                    WEB3DateUtility.toDay(l_datBaseDate));
            
            log.debug("1.10.2 譲渡益税を計算する。" + l_dblCapitalGainTax);
        }

        //1.11 レスポンスデータを生成する
        WEB3AdminFeqExecutionConfirmResponse l_response = 
            (WEB3AdminFeqExecutionConfirmResponse) l_request.createResponse();

        //1.12 レスポンスデータプロパティに値をセットする
        //出来詳細情報一覧： create出来詳細情報一覧()
        l_response.execDetailInfoList = l_web3FeqExecDetailInfoUnits;
 
        //約定為替レート： リクエストデータ.約定為替レート
        l_response.execExchangeRate = l_request.execExchangeRate;


        //約定日： リクエストデータ.約定日（※nullの場合、TradingSystem.getSystemTimestamp()の日付部分）
        if (l_request.executionDate != null)
        {
            l_response.executionDate = 
                WEB3DateUtility.toDay(l_request.executionDate);
        } 
        else
        {
            l_response.executionDate = WEB3DateUtility.toDay(
                GtlUtils.getSystemTimestamp());
        }

        //現地受渡日： リクエストデータ.現地受渡日（※nullの場合、約定日の３営業日後）
        if (l_request.localDeliveryDate == null)
        {
            WEB3GentradeBizDate l_web3GentradeBizDate = 
                new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            if (l_datExecDate != null)
            {
                l_web3GentradeBizDate = 
                    new WEB3GentradeBizDate(new Timestamp(WEB3DateUtility.toDay(l_datExecDate).getTime()));
            }
            Date l_datBaseDate = l_web3GentradeBizDate.roll(3);
            l_response.localDeliveryDate = l_datBaseDate;
        } 
        else
        {
            l_response.localDeliveryDate = l_request.localDeliveryDate;
        }

        //譲渡益： calc譲渡損益()
        l_response.passProfit = 
            WEB3StringTypeUtility.formatNumber(l_dblCapitalProfitLoss);

        //譲渡益税： calc譲渡益税()
        l_response.passProfitTax = 
            WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTax);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit出来) <BR>
     * 出来入力完了処理を行う。 <BR>
     * <BR>
     * シーケンス図「（(管)出来入力）submit出来」 参照。 <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ) <BR>
     *            リクエストデータ <BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 428C3F8403A7
     */
    protected WEB3AdminFeqExecutionCompleteResponse submitExec(
        WEB3AdminFeqExecutionCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitExec(WEB3AdminFeqExecutionCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();

        //1.2 管理者の権限チェックを行う。
        //[validate権限()に指定する引数]
        //機@能カテゴリコード： 機@能カテゴリコード.”外株（注文約定管理）”
        //is更新： true
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 取引パスワードのチェックを行う。
        //[validate取引パスワード()に指定する引数]
        //パスワード： リクエストデータ.暗証番号
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.4 注文単位オブジェクトを取得する。
        //[get注文単位ByOrderId()に指定する引数]
        //注文ＩＤ： リクエストデータ.注文ＩＤ
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager) l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                    Long.parseLong(l_request.orderId));

        //1.5 市場コードをセットする。
        //[reset市場コード()に指定する引数]
        //市場コード： 注文単位.get市場().getMarketCode()
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_feqOrderUnit.getMarket().getMarketCode());

        //1.6 約定日のチェックを行う。
        //[validate約定日()に指定する引数]
        //注文単位： get注文単位ByOrderId()
        //約定日： リクエストデータ.約定日
        //※（約定日時 == null）の場合、当日
        Date l_datExecDate = l_request.executionDate;
        if (l_datExecDate == null)
        {
            l_datExecDate = GtlUtils.getSystemTimestamp();
        }
        
        log.debug("1.6 約定日のチェックを行う。約定日:" + l_datExecDate);
        l_orderManager.validateExecutionDate(
            l_feqOrderUnit, 
            l_datExecDate);

        //1.7 約定数量のチェックを行う。
        int l_intCnt = 0;
        if (l_request.executeList != null && l_request.executeList.length > 0)
        {
            l_intCnt = l_request.executeList.length;
        }
        double l_dblExecuteQuantitySum = 0;
        
        for (int i = 0; i < l_intCnt; i++)
        {
            l_dblExecuteQuantitySum = 
                l_dblExecuteQuantitySum
                + Double.parseDouble(l_request.executeList[i].execQuantity);
        }
        log.debug("約定数量Sum:" + l_dblExecuteQuantitySum);

        //[validate約定数量()に指定する引数]
        //注文単位： get注文単位ByOrderId()
        //約定数量： リクエストデータ.出来情報一覧.約定数量の合計値（sum）
        l_orderManager.validateExecutedQuantity(
            l_feqOrderUnit, 
            l_dblExecuteQuantitySum);

        if (l_request.localDeliveryDate != null)
        {
            l_orderManager.validateFDeliveryDate(
                l_feqOrderUnit,
                l_request.localDeliveryDate);
        }

        WEB3FeqPositionManager l_positionManager = 
            (WEB3FeqPositionManager) l_tradingModule.getPositionManager();
        long l_lngOrderUnitId = l_feqOrderUnit.getOrderUnitId();
        
        log.debug("リクエストデータ.出来情報一覧[]の要素数分LOOP処理:" + l_intCnt);    

        //1.8 リクエストデータ.出来情報一覧[]の要素数分LOOP処理
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.8.1 約定単価のチェックを行う。
            //[validate約定単価()に指定する引数]
            //注文単位： get注文単位ByOrderId()
            //約定数量： リクエストデータ.出来情報一覧[index].約定単価
            l_orderManager.validateExecutedPrice(
                l_feqOrderUnit, 
                Double.parseDouble(l_request.executeList[i].execPrice));

            //1.8.2 約定情報を更新する。
            //シーケンス図「（約定情報更新）update約定情報」
            WEB3FeqOrderAndExecutionUpdateService l_executeUpdateService = 
                (WEB3FeqOrderAndExecutionUpdateService) Services.getService(
                    WEB3FeqOrderAndExecutionUpdateService.class);

            //[update約定情報()に指定する引数]
            //外株出来通知キュー： （生成したオブジェクト※）
            HostFeqOrderExecNotifyParams l_orderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
            
            //　@※外株出来通知キューParamsを生成し、以下の通りプロパティに値をセットする。
            //　@証券会社コード： 注文単位.get証券会社コード()
            l_orderExecNotifyParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());

            //　@部店コード： 注文単位.get部店コード()
            l_orderExecNotifyParams.setBranchCode(l_feqOrderUnit.getBranchCode());

            //　@顧客コード： 注文単位.get口座コード()
            l_orderExecNotifyParams.setAccountCode(l_feqOrderUnit.getAccountCode());

            //　@識別コード： 注文単位.識別コード
            l_orderExecNotifyParams.setRequestCode(l_feqOrderUnitRow.getOrderRequestNumber());

            //　@運用コード： 注文単位.運用コード
            l_orderExecNotifyParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());

            //　@約定株数： リクエストデータ.出来情報一覧[index].約定株数
            l_orderExecNotifyParams.setExecQuantity(
                Double.parseDouble(l_request.executeList[i].execQuantity));

            //　@約定単価： リクエストデータ.出来情報一覧[index].約定単価
            l_orderExecNotifyParams.setExecPrice(
                Double.parseDouble(l_request.executeList[i].execPrice));

            //　@約定日時： リクエストデータ.出来情報一覧[index].約定日時
            l_orderExecNotifyParams.setExecTimestamp(
                l_request.executeList[i].executionTimestamp);

            //　@現地受渡日： リクエストデータ.現地受渡日
            l_orderExecNotifyParams.setFDeliveryDate(l_request.localDeliveryDate);
            
            //  発注日： 注文単位．発注日
            l_orderExecNotifyParams.setOrderBizDate(WEB3DateUtility.getDate(l_feqOrderUnit.getBizDate(), "yyyyMMdd"));

            //　@為替レート： リクエストデータ.約定為替レート
            l_orderExecNotifyParams.setFxRate(
                Double.parseDouble(l_request.execExchangeRate));

            l_executeUpdateService.updateExecuteUnit(l_orderExecNotifyParams);
            
            l_positionManager.updateTransaction(
                l_lngOrderUnitId, 
                false);
            
            WEB3FeqOrderUnit l_feqOrderUnitUpdated = null;
            try
            {
                l_feqOrderUnitUpdated = 
                    (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            Date l_datBizDate =  l_request.executeList[i].executionTimestamp;
            l_orderManager.updateEstimatedPrice(
                l_feqOrderUnitUpdated, 
                l_datBizDate);
            
            l_orderManager.executeChangeCancelOrderRejected(
                l_feqOrderUnitUpdated.getOrderUnitId());
        }

        //1.11 余力再計算を実施する。
        //[余力再計算()に指定する引数]
        //補助口座： get注文単位ByOrderId().get補助口座()
        WEB3TPTradingPowerReCalcService l_powerReCalcServie = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        l_powerReCalcServie.reCalcTradingPower(l_feqOrderUnit.getSubAccount());

        //1.12 レスポンスデータを生成する
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminFeqExecutionCompleteResponse) l_request.createResponse();
    }

    /**
     * (create出来詳細情報一覧) <BR>
     * 注文単位，出来情報一覧より約定計算を行う。 <BR>
     * 計算結果にて外国株式約定詳細（管理者）オブジェクトの配列を生成する。 <BR>
     * <BR>
     * シーケンス図「（(管)出来入力）create出来詳細情報一覧」 参照。 <BR>
     * 
     * @@param l_orderUnitRow -
     *            (注文単位行) <BR>
     *            注文単位行オブジェクト <BR>
     * @@param l_execInfoList -
     *            (出来情報一覧) <BR>
     *            画面で入力された外国株式約定情報の配列 <BR>
     * @@param l_datExecDate -
     *            (約定日) <BR>
     *            約定日 <BR>
     * @@param l_dblExecFxRate -
     *            (約定為替レート) <BR>
     *            約定為替レート <BR>
     * @@return WEB3FeqExecDetailInfoUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42B2545B000A
     */
    private WEB3FeqExecDetailInfoUnit[] createExecDetailInfoList(
        FeqOrderUnitRow l_orderUnitRow, 
        WEB3FeqExecuteUnit[] l_execInfoList, 
        Date l_datExecDate, 
        double l_dblExecFxRate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createExecDetailInfoList()";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitRow == null)
        {
            log.debug("パラメータNull出来ない");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            
        WEB3FeqPositionManager l_positionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
        WEB3FeqBizLogicProvider l_bizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        WEB3FeqOrderManager l_web3FeqOrderManager = 
            (WEB3FeqOrderManager) l_tradingModule.getOrderManager();            
        
        //1.1 既約定のトランザクションListを取得する。
        //[get取引勘定明細ForOrderUnit()に指定する引数]
        //注文単位ＩＤ： 注文単位行.getOrderUnitId()
        List l_lisFinTransactionParams = null;
        try 
        {
            l_lisFinTransactionParams = l_positionManager.getFinTransactionForOrderUnit(
                l_orderUnitRow.getOrderUnitId());
        } 
        catch (DataException l_ex) 
        {
            String l_strMessage = "既約定のトランザクションListを取得する";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        
        int l_intFinTransactionForOrderUnitCnt = 0;        
        
        if (l_lisFinTransactionParams != null && !l_lisFinTransactionParams.isEmpty())
        {
            l_intFinTransactionForOrderUnitCnt = l_lisFinTransactionParams.size();
        }
        else
        {
            l_lisFinTransactionParams = new ArrayList();
        }
        
        log.debug("既約定のトランザクションList-Count: " + l_intFinTransactionForOrderUnitCnt);
        
        //1.2 getInstance()
        WEB3FeqProductTypeOrderSubmitterPersistenceManager l_updateManager =            
            (WEB3FeqProductTypeOrderSubmitterPersistenceManager)WEB3FeqProductTypeOrderSubmitterPersistenceManager.getInstance();
        
        //1.3 出来情報一覧[]の要素数分LOOP処理
        int l_intExecInfoListCnt = 0;
        
        if (l_execInfoList != null && l_execInfoList.length > 0)
        {
            l_intExecInfoListCnt = l_execInfoList.length;
        }
        
        log.debug("1.3 出来情報一覧[]の要素数分LOOP処理-Count: " + l_intExecInfoListCnt);
        
        for (int i = 0; i < l_intExecInfoListCnt; i++) 
        {
            //1.3.1 約定オブジェクトを生成する。

            //[create約定()に指定する引数]
            //注文単位行： 注文単位行
            //約定数量： 出来情報一覧[index].約定株数
            //約定単価： 出来情報一覧[index].約定単価
            //約定日時：　@出来情報一覧[index].約定日時 
            //約定為替レート： 約定為替レート
            WEB3FeqOrderExecution l_web3FeqOrderExecution
                = l_updateManager.createExecution(
                    l_orderUnitRow,
                    Double.parseDouble(l_execInfoList[i].execQuantity),
                    Double.parseDouble(l_execInfoList[i].execPrice),
                    l_execInfoList[i].executionTimestamp,
                    l_dblExecFxRate);
            
            //1.3.2 トランザクション（取引勘定明細）行を生成する。
            //[createトランザクション行()に指定する引数]
            //約定： create約定()
            //注文単位行： 注文単位行
            FeqFinTransactionParams l_feqFinTransactionParams
                = l_positionManager.createTransactionParams(
                    l_web3FeqOrderExecution,
                    l_orderUnitRow);
            
            //1.3.3 トランザクションListに生成したトランザクションを追加する
            l_lisFinTransactionParams.add(l_feqFinTransactionParams);
        }
        
        //1.4 トランザクションListのサイズを取得する
        int l_intFeqFinTransactionParamsCnt = l_lisFinTransactionParams.size();
        
        log.debug("トランザクションList-Count: " + l_intFeqFinTransactionParamsCnt);
        
        //1.5 分岐フロー 約定が１件の場合（size() == 1）
        WEB3GentradeBizDate l_web3GentradeBizDate = 
            new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
        if (l_datExecDate != null)
        {
            l_web3GentradeBizDate = 
                new WEB3GentradeBizDate(new Timestamp(WEB3DateUtility.toDay(l_datExecDate).getTime()));
        }
        if (l_intFeqFinTransactionParamsCnt == 1) 
        {
            log.debug("1.5 分岐フロー 約定が１件の場合（size() == 1）");
            
            //1.5.1 出来詳細情報（：外国株式約定詳細（管理者））を生成する
            WEB3FeqExecDetailInfoUnit l_execDetailInfoUnit = 
                new WEB3FeqExecDetailInfoUnit();
            
            //1.5.2 出来詳細情報（：外国株式約定詳細（管理者））にプロパティをセットする。
            //[create出来詳細情報()に指定する引数]
            //出来詳細情報： （生成したオブジェクト）
            //トランザクション（取引勘定明細）行： トランザクション明細List[0]
            //受渡日： （約定日時（日付部分）の３営業日後）
            //約定通番： （注文単位行.約定最終通番 + 1）
            FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
            String l_strMarketCode = null;
            if (!l_orderUnitRow.getMarketIdIsNull())
            {
                long l_lngMarketId = l_orderUnitRow.getMarketId();
            
                try
                {
                    l_strMarketCode = 
                        l_finObjectManager.getMarket(l_lngMarketId).getMarketCode();
                } 
                catch (NotFoundException l_ex)
                {
                    String l_strMessage = "Market not found";
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        l_strMessage, 
                        l_ex);
                }
            }
 
            Date l_datDeliveryDate = l_web3GentradeBizDate.roll(3);
            
            log.debug("約定日時: " + l_datExecDate);
            log.debug("受渡日:" + l_datDeliveryDate);                        
            
            createExecDetailInfo(
                l_execDetailInfoUnit,
                (FeqFinTransactionParams) l_lisFinTransactionParams.get(0),
                l_datDeliveryDate,
                l_orderUnitRow.getLastExecutionSerialNo() + 1);
            
            //1.5.3 return
            return new WEB3FeqExecDetailInfoUnit[] {l_execDetailInfoUnit};
        }
        
        //1.6 外国株式金額按分計算を行う。
        FeqFinTransactionParams[] l_feqFinTransactions = 
            new FeqFinTransactionParams[l_intFeqFinTransactionParamsCnt];
            l_lisFinTransactionParams.toArray(l_feqFinTransactions);

        //[calc外国株式金額（按分）に指定する引数]
        //トランザクションList.toArray() 
        WEB3FeqAmountCalcResultFactor l_resultFactor =
            l_bizLogicProvider.calcFeqAmountFactor(l_feqFinTransactions);

        //return result
        List l_lisExecDetailInfoList = new ArrayList();

        int l_intLastExecSerNo = l_orderUnitRow.getLastExecutionSerialNo();
        
        //1.7 取引勘定明細List各要素毎のLOOP処理
        for (int i = 0; i < l_intFeqFinTransactionParamsCnt; i++) 
        {
            //1.7.1 外国株式金額計算結果を取得する。

            //[get外国株式金額計算結果（明細）()に指定する引数]
            //index： index
            WEB3FeqAmountCalcResult l_web3FeqAmountCalcResult
                = l_resultFactor.getFeqAmountCalcResultDetails(i);

            //1.7.2 出来詳細情報（：外国株式約定詳細（管理者））を生成する
            WEB3FeqExecDetailInfoUnit l_execDetailInfoUnit 
                = new WEB3FeqExecDetailInfoUnit();
            
            //1.7.3 出来詳細情報（：外国株式約定詳細（管理者））にプロパティをセットする。
            //[create出来詳細情報()に指定する引数]
            //出来詳細情報： （生成したオブジェクト）
            //トランザクション（取引勘定明細）行：　@トランザクション明細List[index] 
            //外国株式金額計算結果：　@get外国株式金額計算結果（明細）()の戻り値 
            
            //受渡日：
            //既約定の場合(*1)： 約定(*2).受渡日
            //約定通番：
            //既約定の場合(*1)： 約定(*2).約定順番号
            //(*2) トランザクション明細List[index].約定ＩＤに該当する約定
            //(*1) 既約定の場合の判定
            //　@（index <= get取引勘定明細ForOrderUnit()で取得したListの要素数）であれば既約定、以外今回約定。
            Date l_datDeliveryDate = null;
            int l_intExecSerialNo = 0;
            if ( i < l_intFinTransactionForOrderUnitCnt)
            {
                log.debug("既約定");
                
                FeqFinTransactionRow l_feqFinTransactionRow = 
                    (FeqFinTransactionRow) l_lisFinTransactionParams.get(i);
                WEB3FeqOrderExecution l_feqOrdreExecution = null;
                try 
                {
                    l_feqOrdreExecution = 
                        (WEB3FeqOrderExecution) l_web3FeqOrderManager.getOrderExecution(
                            l_feqFinTransactionRow.getOrderExecutionId());
                } 
                catch (NotFoundException l_ex) 
                {
                    String l_strMessage = "約定ＩＤに該当する約定 not found";
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        this.getClass().getName()
                        + STR_METHOD_NAME, l_strMessage, l_ex);
                }
                
                l_datDeliveryDate = l_feqOrdreExecution.getDeliveryDate();
                l_intExecSerialNo = l_feqOrdreExecution.getExecutionSerialNo();
                
                log.debug("受渡日:" + l_datDeliveryDate);
                log.debug("約定通番:" + l_intExecSerialNo);
            } 

            //今回約定の場合： （約定日時（日付部分）の３営業日後）
            //今回約定の場合： （注文単位行.約定最終通番 + index + 1
            else 
            {
                log.debug("今回約定");
                
                FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
                String l_strMarketCode = null;
                if (!l_orderUnitRow.getMarketIdIsNull())
                {
                    long l_lngMarketId = l_orderUnitRow.getMarketId();
            
                    try
                    {
                        l_strMarketCode = 
                            l_finObjectManager.getMarket(l_lngMarketId).getMarketCode();
                    } 
                    catch (NotFoundException l_ex)
                    {
                        String l_strMessage = "Market not found";
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                            this.getClass().getName() + "." + STR_METHOD_NAME, 
                            l_strMessage, 
                            l_ex);
                    }
                }
                l_datDeliveryDate = l_web3GentradeBizDate.roll(3);
                l_intExecSerialNo = l_intLastExecSerNo + 1;
                l_intLastExecSerNo ++;
                
                log.debug("受渡日:" + l_datDeliveryDate);
                log.debug("約定通番:" + l_intExecSerialNo);
            }            

            createExecDetailInfo(
                l_execDetailInfoUnit,
                l_feqFinTransactions[i],
                l_web3FeqAmountCalcResult,
                l_datDeliveryDate,
                l_intExecSerialNo);
            l_lisExecDetailInfoList.add(l_execDetailInfoUnit);
        }

        WEB3FeqExecDetailInfoUnit[] l_execDetailInfoUnits = 
            new WEB3FeqExecDetailInfoUnit[l_lisExecDetailInfoList.size()];
        l_lisExecDetailInfoList.toArray(l_execDetailInfoUnits);

        //temp serialNo[]
        int l_intExecDetailInfoLen = l_execDetailInfoUnits.length;
        int[] serialNo = new int[l_intExecDetailInfoLen];
        for (int i = 0; i < l_intExecDetailInfoLen; i++) 
        {
            serialNo[i] = Integer.parseInt(l_execDetailInfoUnits[i].execNo);
        }

        //（一口計算分の出来詳細情報）と（約定入力分の出来詳細情報）の各要素を
        //約定番号の昇順にsortし、配列で返却する。
        WEB3FeqExecDetailInfoUnit l_web3FeqExecDetailInfoUnit = null;
        int l_intTemp = 0;
        for (int j = 0; j < l_intExecDetailInfoLen - 1; j++) 
        {
            for (int i = 0; i < l_intExecDetailInfoLen - 1 - j; i++) 
            {
                if (serialNo[i] > serialNo[i + 1]) 
                {
                    l_intTemp = serialNo[i + 1];
                    serialNo[i + 1] = serialNo[i];
                    serialNo[i] = l_intTemp;
                    l_web3FeqExecDetailInfoUnit = l_execDetailInfoUnits[i + 1];
                    l_execDetailInfoUnits[i + 1] = l_execDetailInfoUnits[i];
                    l_execDetailInfoUnits[i] = l_web3FeqExecDetailInfoUnit;
                }
            }
        }
        
        //1.8 return
        log.exiting(STR_METHOD_NAME);
        return l_execDetailInfoUnits;
    }
    
    /**
     * (create出来詳細情報) <BR>
     * 出来詳細情報に以下の通り値をセットする。 <BR>
     * <BR>
     * 約定ＩＤ： null <BR>
     * 約定番号： 約定通番を、pattern="000"でフォーマットした文字列。 <BR>
     * 売買代金： トランザクション（取引勘定明細）行.約定数量() <BR>
     * ×トランザクション（取引勘定明細）行.約定単価() <BR>
     * 現地手数料： トランザクション（取引勘定明細）行.現地手数料 <BR>
     * 現地取引税： トランザクション（取引勘定明細）行.現地取引税 <BR>
     * その他コスト１： トランザクション（取引勘定明細）行.その他コスト１ <BR>
     * その他コスト２： トランザクション（取引勘定明細）行.その他コスト２ <BR>
     * 清算代金（円貨）： トランザクション（取引勘定明細）行.現地清算代金（円貨） <BR>
     * 清算代金（外貨）： トランザクション（取引勘定明細）行.現地清算代金（外貨） <BR>
     * 国内手数料（円貨）： トランザクション（取引勘定明細）行.委託手数料 <BR>
     * 国内手数料（外貨）： トランザクション（取引勘定明細）行.委託手数料（外貨） <BR>
     * 消費税（円貨）： トランザクション（取引勘定明細）行.委託手数料消費税 <BR>
     * 消費税（外貨）： トランザクション（取引勘定明細）行.委託手数料消費税（外貨） <BR>
     * 受渡代金： トランザクション（取引勘定明細）行.受渡代金 <BR>
     * 受渡代金（外貨）： トランザクション（取引勘定明細）行.受渡代金（外貨） <BR>
     * 国内受渡日： 受渡日 <BR>
     * 約定単価：　@トランザクション（取引勘定明細）行.約定単価<BR>
     * 約定数量：　@トランザクション（取引勘定明細）行.約定数量<BR>
     * 約定日時：　@トランザクション（取引勘定明細）行.トランザクション発生日時<BR>
     * <BR>
     * @@param l_execDetailInfo -
     *            (出来詳細情報) <BR>
     *            出来詳細情報メッセージデータオブジェクト <BR>
     * @@param l_transactionParams -
     *            (トランザクション（取引勘定明細）行) <BR>
     *            トランザクション（取引勘定明細）行オブジェクト <BR>
     * @@param l_datDeliveryDate -
     *            (受渡日) <BR>
     *            受渡日 <BR>
     * @@param l_intExecSerialNo -
     *            (約定通番) <BR>
     *            約定通番 <BR>
     * @@roseuid 42B2786B03BC
     */
    private void createExecDetailInfo(
        WEB3FeqExecDetailInfoUnit l_execDetailInfo,
        FeqFinTransactionParams l_transactionParams, 
        Date l_datDeliveryDate, 
        int l_intExecSerialNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createExecDetailInfo(WEB3FeqExecDetailInfoUnit, " + 
            "FeqFinTransactionParams, Date, int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_execDetailInfo == null || l_transactionParams == null)
        {
            log.debug("パラメータNull出来ない");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //約定ＩＤ： null
        l_execDetailInfo.execId = null;
        //約定番号： 約定通番を、pattern="000"でフォーマットした文字列。
        l_execDetailInfo.execNo = WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);

        //売買代金： トランザクション（取引勘定明細）行.約定数量()×トランザクション（取引勘定明細）行.約定単価()
        long l_lngOrderUnitId = l_transactionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager =
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnit.getProduct();
        WEB3GentradeCurrency l_currency = l_product.getCurrency();
        int l_intDecimalPlace = l_currency.getScale();
        double l_dblForeignTradePrice =
            l_transactionParams.getQuantity() * l_transactionParams.getPrice();
        BigDecimal l_bdForeignTradePrice = new BigDecimal(l_dblForeignTradePrice);
        l_bdForeignTradePrice =
            l_bdForeignTradePrice.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblForeignTradePrice = l_bdForeignTradePrice.doubleValue();
        l_execDetailInfo.foreignTradePrice =
            WEB3StringTypeUtility.formatNumber(l_dblForeignTradePrice);

        //現地手数料： トランザクション（取引勘定明細）行.現地手数料
        l_execDetailInfo.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignCommissionFee());

        //現地取引税： トランザクション（取引勘定明細）行.現地取引税
        l_execDetailInfo.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignTax());

        //その他コスト１： トランザクション（取引勘定明細）行.その他コスト１
        l_execDetailInfo.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignFeeExt1());

        //その他コスト２： トランザクション（取引勘定明細）行.その他コスト２
        l_execDetailInfo.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getForeignFeeExt2());

        //清算代金（円貨）： トランザクション（取引勘定明細）行.現地清算代金（円貨）
        l_execDetailInfo.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getBalanceAmount());

        //清算代金（外貨）： トランザクション（取引勘定明細）行.現地清算代金（外貨）
        l_execDetailInfo.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getBalanceAmountFc());

        //国内手数料（円貨）： トランザクション（取引勘定明細）行.委託手数料
        l_execDetailInfo.commission = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFee());

        //国内手数料（外貨）： トランザクション（取引勘定明細）行.委託手数料（外貨）
        l_execDetailInfo.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFeeFc());

        //消費税（円貨）： トランザクション（取引勘定明細）行.委託手数料消費税
        l_execDetailInfo.consumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFeeTax());

        //消費税（外貨）： トランザクション（取引勘定明細）行.委託手数料消費税（外貨）
        l_execDetailInfo.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getCommissionFeeTaxFc());

        //受渡代金 (円貨)
        if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {
            //受渡金額（円貨）：　@トランザクション（取引勘定明細）行.受渡代金 ×（-1）
            l_execDetailInfo.deliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmount() * (-1));
        }
        else if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //受渡金額（円貨）：　@トランザクション（取引勘定明細）行.受渡代金
            l_execDetailInfo.deliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmount());
        }
        
        //受渡代金（外貨）
        if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_BUY))
        {
            //受渡金額（外貨）：　@トランザクション（取引勘定明細）行.受渡代金 ×（-1）
            l_execDetailInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmountFc() * (-1));
        }
        else if(l_transactionParams.getFinTransactionType().equals(FinTransactionType.EQTYPE_FEQ_SELL))
        {
            //受渡金額（外貨）：　@トランザクション（取引勘定明細）行.受渡代金
            l_execDetailInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_transactionParams.getNetAmountFc());
        }
        
        //国内受渡日： 受渡日
        l_execDetailInfo.deliveryDate = 
            WEB3DateUtility.toDay(l_datDeliveryDate);
            
        //約定単価：トランザクション（取引勘定明細）行.約定単価 
        l_execDetailInfo.execPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getPrice());
        
        //約定数量：トランザクション（取引勘定明細）行.約定数量
        l_execDetailInfo.execQuantity = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getQuantity());
         
        //約定日時：トランザクション（取引勘定明細）行.トランザクション発生日時 
        l_execDetailInfo.executionTimestamp = l_transactionParams.getFinTransactionTimestamp();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create出来詳細情報) <BR>
     * 出来詳細情報に以下の通り値をセットする。 <BR>
     * <BR>
     * 約定ＩＤ： null <BR>
     * 約定番号： 約定通番を、pattern="000"でフォーマットした文字列。 <BR>
     * 売買代金： 外国株式金額計算結果.get売買代金（外貨）() <BR>
     * 現地手数料： 外国株式金額計算結果.get現地手数料() <BR>
     * 現地取引税： 外国株式金額計算結果.get現地取引税() <BR>
     * その他コスト１： 外国株式金額計算結果.getその他コスト１() <BR>
     * その他コスト２： 外国株式金額計算結果.getその他コスト２() <BR>
     * 清算代金（円貨）： 外国株式金額計算結果.get現地清算代金（円貨）() <BR>
     * 清算代金（外貨）： 外国株式金額計算結果.get現地清算代金() <BR>
     * 国内手数料（円貨）： 外国株式金額計算結果.get委託手数料() <BR>
     * 国内手数料（外貨）： 外国株式金額計算結果.get委託手数料（外貨）() <BR>
     * 消費税（円貨）： 外国株式金額計算結果.get委託手数料消費税() <BR>
     * 消費税（外貨）： 外国株式金額計算結果.get委託手数料消費税（外貨）() <BR>
     * 受渡代金： 外国株式金額計算結果.get受渡代金() <BR>
     * 受渡代金（外貨）： 外国株式金額計算結果.get受渡代金（外貨）() <BR>
     * 国内受渡日： 受渡日 <BR>
     * 約定単価：　@トランザクション（取引勘定明細）行.約定単価<BR>
     * 約定数量：　@トランザクション（取引勘定明細）行.約定数量<BR>
     * 約定日時：　@トランザクション（取引勘定明細）行.トランザクション発生日時<BR>
     * 
     * @@param l_execDetailInfo -
     *            (出来詳細情報) <BR>
     *            出来詳細情報メッセージデータオブジェクト <BR>
     * @@param l_transactionParams -
     *            (トランザクション（取引勘定明細）行)<BR>
     *            トランザクション（取引勘定明細）行オブジェクト<BR>
     * @@param l_feqAmountCalcResult -
     *            (外国株式金額計算結果) <BR>
     *            外国株式金額計算結果オブジェクト <BR>
     * @@param l_datDeliveryDate -
     *            (受渡日) <BR>
     *            受渡日 <BR>
     * @@param l_intExecSerialNo -
     *            (約定通番) <BR>
     *            約定通番 <BR>
     * @@roseuid 42B27BD50310
     */
    private void createExecDetailInfo(
        WEB3FeqExecDetailInfoUnit l_execDetailInfo,
        FeqFinTransactionParams l_transactionParams,
        WEB3FeqAmountCalcResult l_feqAmountCalcResult, 
        Date l_datDeliveryDate, 
        int l_intExecSerialNo)
    {
        final String STR_METHOD_NAME = "createExecDetailInfo(WEB3FeqExecDetailInfoUnit, " + 
            "FeqFinTransactionParams, WEB3FeqAmountCalcResult, Date, int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_execDetailInfo == null || l_feqAmountCalcResult == null)
        {
            log.debug("パラメータNull出来ない");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //約定ＩＤ： null
        l_execDetailInfo.execId = null;

        //約定番号： 約定通番を、pattern="000"でフォーマットした文字列。
        l_execDetailInfo.execNo = WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);

        //売買代金： 外国株式金額計算結果.get売買代金（外貨）()
        l_execDetailInfo.foreignTradePrice = WEB3StringTypeUtility.formatNumber(
            l_feqAmountCalcResult.getTradePriceFc());

        //現地手数料： 外国株式金額計算結果.get現地手数料()
        l_execDetailInfo.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForignCommissionFee());

        //現地取引税： 外国株式金額計算結果.get現地取引税()
        l_execDetailInfo.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForeignTax());

        //その他コスト１： 外国株式金額計算結果.getその他コスト1()
        l_execDetailInfo.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForeignFeeExt1());

        //その他コスト２： 外国株式金額計算結果.getその他コスト２()
        l_execDetailInfo.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getForeignFeeExt2());

        //清算代金（円貨）： 外国株式金額計算結果.get現地清算代金（円貨）()
        l_execDetailInfo.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getBalanceAmount());

        //清算代金（外貨）： 外国株式金額計算結果.get現地清算代金()
        l_execDetailInfo.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getBalanceAmountFc());

        //国内手数料（円貨）： 外国株式金額計算結果.get委託手数料()
        l_execDetailInfo.commission = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommissionFee());

        //国内手数料（外貨）： 外国株式金額計算結果.get委託手数料（外貨）()
        l_execDetailInfo.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommissionFeeFc());

        //消費税（円貨）： 外国株式金額計算結果.get委託手数料消費税()
        l_execDetailInfo.consumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommisionFeeTax());

        //消費税（外貨）： 外国株式金額計算結果.get委託手数料消費税（外貨）()
        l_execDetailInfo.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getCommisionFeeTaxFc());

        //受渡代金： 外国株式金額計算結果.get受渡代金()
        l_execDetailInfo.deliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getNetAmount());

        //受渡代金（外貨）： 外国株式金額計算結果.get受渡代金（外貨）()
        l_execDetailInfo.foreignDeliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_feqAmountCalcResult.getNetAmountFc());

        //国内受渡日： 受渡日
        l_execDetailInfo.deliveryDate = l_datDeliveryDate;
        
        //約定単価：トランザクション（取引勘定明細）行.約定単価 
        l_execDetailInfo.execPrice = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getPrice());
        
        //約定数量：トランザクション（取引勘定明細）行.約定数量
        l_execDetailInfo.execQuantity = 
            WEB3StringTypeUtility.formatNumber(l_transactionParams.getQuantity());
         
        //約定日時：トランザクション（取引勘定明細）行.トランザクション発生日時 
        l_execDetailInfo.executionTimestamp = l_transactionParams.getFinTransactionTimestamp();

        log.exiting(STR_METHOD_NAME);
    }
}@
