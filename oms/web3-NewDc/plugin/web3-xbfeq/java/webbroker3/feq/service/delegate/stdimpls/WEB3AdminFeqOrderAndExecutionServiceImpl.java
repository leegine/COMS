head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 管理者外国株式約定入力サービスImpl(WEB3AdminFeqOrderAndExecutionServiceImpl)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/24 王暁傑 (中訊) 新規作成
                 : 2005/08/02 韋念瓊(中訊) レビュー
                   2006/10/17 徐大方(中訊) モデル　@No.291対応
Revesion History : 2007/11/07 何文敏(中訊) モデル　@No.357、360、361対応
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2008/02/02 柴双紅(中訊) モデルNo.396
Revesion History : 2008/11/12 劉仁和(中訊) モデルNo.496
Revesion History : 2009/08/03 車進(中訊)    モデルNo.512
Revesion History : 2010/10/15 趙天月(中訊) モデルNo.559
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.DefaultFeqOrderFillMarketResponseMessage;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqExecuteUpdateInterceptor;
import webbroker3.feq.WEB3FeqForeignCost;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqPositionManagerHelper;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductTypeOrderSubmitterPersistenceManager;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAndExecutionService;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.feq.util.WEB3FeqDateUtility;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式約定入力サービスImpl)<BR>
 * 管理者外国株式約定入力サービス実装クラス<BR>
 * 
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionServiceImpl implements WEB3AdminFeqOrderAndExecutionService 
{
    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminFeqOrderAndExecutionServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F4005D
     */
    public WEB3AdminFeqOrderAndExecutionServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式約定入力処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －get検索画面()<BR>
     * －get入力画面()<BR>
     * －validate約定()<BR>
     * －submit約定()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403AA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqOrderAndExecutionInputRequest)
        {
            //get入力画面
            l_response = 
                this.getInputScreen((WEB3AdminFeqOrderAndExecutionInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAndExecutionSearchRequest)
        {
            //get検索画面
            l_response = 
                this.getQueryScreen((WEB3AdminFeqOrderAndExecutionSearchRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAndExecutionConfirmRequest)
        {
            //validate約定
            l_response = 
                this.validateExec((WEB3AdminFeqOrderAndExecutionConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAndExecutionCompleteRequest)
        {
            //submit約定
            l_response = 
                this.submitExec((WEB3AdminFeqOrderAndExecutionCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get検索画面)<BR>
     * 検索画面表示処理。<BR>
     * <BR>
     * シーケンス図「（(管)約定入力）get検索画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B9149E0098
     */
    protected WEB3AdminFeqOrderAndExecutionSearchResponse getQueryScreen(
        WEB3AdminFeqOrderAndExecutionSearchRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getQueryScreen(WEB3AdminFeqOrderAndExecutionSearchRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3Administrator l_web3Administrator;
        
        //1.1管理者オブジェクトを取得する getInstanceFromログイン情報()
        l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2管理者の権限チェックを行う 機@能カテゴリコード,
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.”外株（注文約定管理）” 
        //is更新：　@true 
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
    
        //1.3レスポンスデータを生成する
        log.exiting(STR_METHOD_NAME);
        return (WEB3AdminFeqOrderAndExecutionSearchResponse)l_request.createResponse();
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図「（(管)約定入力）get入力画面」 参照。<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@(管)約定入力」(管)約定入力）get入力画面)<BR>
     * 　@　@:  1.6 (*) 未発注の場合（getOrderStatus() == ”1:受付済（新規注文）”）、<BR>
     * 　@　@　@　@例外をスローする。<BR> 
     * <BR> 
     * 　@　@未発注の場合（getOrderStatus() == ”1:受付済（新規注文）”）、<BR>
     * 　@　@例外をスローする<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02143<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * 　@(管)約定入力」(管)約定入力）get入力画面)<BR>
     * 　@　@:  1.8　@(*) 出来終了処理済みの場合（is出来終了() == true）、<BR> 
     * 　@　@　@　@例外をスローする。<BR> 
     * <BR> 
     * 　@　@出来終了処理済みの場合（is出来終了() == true）、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02144<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@(管)約定入力」(管)約定入力）get入力画面)<BR>
     * 　@　@:  1.10　@(*) 一部出来の場合（isPartiallyExecuted() == true）、<BR> 
     * 　@　@　@　@例外をスローする。<BR> 
     * <BR> 
     * 　@　@一部出来の場合（isPartiallyExecuted() == true）、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02145<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403AC
     */
    protected WEB3AdminFeqOrderAndExecutionInputResponse getInputScreen(
        WEB3AdminFeqOrderAndExecutionInputRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderAndExecutionInputRequest)";
        log.entering(STR_METHOD_NAME);        
        
        //1.1 validate() リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_web3Administrator
            = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限()
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.”外株（注文約定管理）” 
        //is更新：　@true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        WEB3AdminFeqOrderAndExecutionInputResponse l_response = null;
        
        //1.4 運用コード未入力の場合
        if (WEB3StringTypeUtility.isEmpty(l_request.managementCode))
        {
            //1.4.1 createResponse( )
            l_response = 
                (WEB3AdminFeqOrderAndExecutionInputResponse)l_request.createResponse();
            //1.4.2  return()
            return l_response;
        }

        //証券会社コードを取得する。
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //７桁の「運用コード」文字列を取得する。
        //[get運用コード()に指定する引数]
        //証券会社コード：get証券会社コード( )の戻り値
        //運用コード：リクエストデータ.運用コード
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService) Services.getService(
            WEB3FeqOrderEmpCodeGettingService.class);

        String l_strManagementCode = l_feqOrderEmpCodeGettingService.getEmpCode(
            l_strInstitutionCode, l_request.managementCode);

        //1.5 get有効注文単位By運用コード()
        //[get有効注文単位By運用コード()に指定する引数] 
        //発注日：　@リクエストデータ.発注日 
        //※未入力の場合、処理日時（TradingSystem.getSystemTimestamp()）の日付部分。 
        //運用コード：get運用コード（）の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        //発注日を取得               
        Date l_datOrderBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        if (l_request.orderBizDate != null) 
        {
            l_datOrderBizDate = l_request.orderBizDate;
        }
        
        //get有効注文単位By運用コード
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_feqOrderManager.getValidOrderUnitByOrderEmpCode(
                l_datOrderBizDate,
                l_strManagementCode);

        //1.6 未発注の場合（getOrderStatus() == ”1:受付済（新規注文）”）又は
        //    発注中の場合（getOrderStatus() == ”2:発注中（新規注文）”）、
        //    対象データが注文受付取消認証を行っていない場合
        //例外をスローする。
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (OrderStatusEnum.ACCEPTED.equals(l_feqOrderUnit.getOrderStatus()) ||
            OrderStatusEnum.ORDERING.equals(l_feqOrderUnit.getOrderStatus()) ||
            l_confirmedPrice)
        {
            //1.6.1 例外をスローする
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文状態が" + l_feqOrderUnit.getOrderStatus() + "です");
        }
        
        //1.7 is出来終了()
        boolean l_blnIsExecEnd = l_feqOrderUnit.isExecEnd();
        //1.8 出来終了処理済みの場合（is出来終了() == true）、例外をスローする。
        if (l_blnIsExecEnd)
        {
            //1.8.1 例外をスローする
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02144,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "is出来終了() = " + l_blnIsExecEnd);
        }
        
        //1.9 isPartiallyExecuted() 約定を取得する
        boolean l_blnIsPartExecuted  = l_feqOrderUnit.isPartiallyExecuted();
        //1.10 一部出来の場合（isPartiallyExecuted() == true）
        if (l_blnIsPartExecuted)
        {
            //1.10.1 例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02145,
                this.getClass().getName() + STR_METHOD_NAME,
                "sPartiallyExecuted() = " + l_blnIsPartExecuted);
        }
        
        //1.11 外国株式約定入力情報を生成する
        WEB3FeqOrderAndExecutionUnit l_feqExecuteInputInfo = 
            new WEB3FeqOrderAndExecutionUnit();
        
        //1.12 注文の情報を外国株式約定入力情報にセットする
        WEB3FeqCommonMessageCreatedService l_messageCreateService =
            new WEB3FeqCommonMessageCreatedServiceImpl();
        //create外国株式約定入力情報
        //外国株式注文共通明細：（生成したオブジェクト）
        //注文単位：　@get有効注文単位By運用コード()
        //約定：　@null
        //トランザクション（取引勘定明細）行：　@null
        l_messageCreateService.createFeqOrderAndExecutionUnit(
            l_feqExecuteInputInfo,
            l_feqOrderUnit,
            null,
            null);
        
        //1.13 レスポンスデータを生成する
        l_response = (WEB3AdminFeqOrderAndExecutionInputResponse)l_request.createResponse();
        
        //1.14 レスポンスデータプロパティに値をセットする
        l_response.orderAndExecutionUnit = l_feqExecuteInputInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate約定)<BR>
     * 約定入力確認処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)約定入力）validate約定」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqOrderAndExecutionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403B9
     */
    protected WEB3AdminFeqOrderAndExecutionConfirmResponse validateExec(
        WEB3AdminFeqOrderAndExecutionConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateExec(WEB3AdminFeqOrderAndExecutionConfirmRequest)";
        log.entering(STR_METHOD_NAME);                

        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限()
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.”外株（注文約定管理）” 
        //is更新：　@true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            true);

        //1.3 clone()
        //メッセージデータのコピーを取得する。 
        WEB3FeqOrderAndExecutionUnit l_feqExeInputInfo = 
            (WEB3FeqOrderAndExecutionUnit) l_request.orderAndExecutionUnit.clone();
            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.4 HOST注文（リクエストデータ.約定入力情報.注文ＩＤが未入力）の場合
        if (WEB3StringTypeUtility.isEmpty(l_feqExeInputInfo.orderId))
        {
            //1.4.1 get証券会社()
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution) l_web3Administrator.getInstitution();

            //1.4.2 validateHOST注文約定()
            //証券会社：get証券会社()
            //約定入力情報：リクエストデータ.約定入力情報.clone()
            validateHostOrderExec(
                l_institution,
                l_feqExeInputInfo);
            
            // プロパティセット
            // 注文ID
            l_feqExeInputInfo.orderId = l_feqOrderManager.createNewOrderId() + "";
            // 注文時間 
            l_feqExeInputInfo.orderDate 
                = GtlUtils.getSystemTimestamp();
            
            // 銘柄名
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            WEB3FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = 
                    (WEB3FeqProduct) l_feqProductManager.getFeqProduct(
                        l_institution, 
                        l_feqExeInputInfo.productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.debug("外国株式銘柄が取得できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_feqExeInputInfo.productName = l_feqProduct.getDisplayProductName();
                        
        } 
        else
        {
            //1.5 ｲﾝﾀｰﾈｯﾄ注文（リクエストデータ.約定入力情報.注文ＩＤに入力あり）の場合
                        
            //1.5.1 get注文単位ByOrderId(long)
            //注文ＩＤ：　@リクエストデータ.約定入力情報.注文ＩＤ
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            l_feqOrderUnit = 
                (WEB3FeqOrderUnit) l_feqOrderManager.getOrderUnitByOrderId(
                    new Long(l_feqExeInputInfo.orderId).longValue());

            //1.5.2 validateｲﾝﾀｰﾈｯﾄ注文約定
            //注文単位：　@get注文単位ByOrderId()
            //約定入力情報：　@リクエストデータ.約定入力情報.clone()
            validateInternetOrderExec(
                l_feqOrderUnit,
                l_feqExeInputInfo);
        }
        //1.6 createResponse()
        WEB3AdminFeqOrderAndExecutionConfirmResponse l_response = 
            (WEB3AdminFeqOrderAndExecutionConfirmResponse)l_request.createResponse();
        
        //1.7 プロパティセット
        l_response.orderAndExecutionUnit = l_feqExeInputInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit約定)<BR>
     * 約定入力完了処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)約定入力）submit約定」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqOrderAndExecutionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FFA9403BB
     */
    protected WEB3AdminFeqOrderAndExecutionCompleteResponse submitExec(
        WEB3AdminFeqOrderAndExecutionCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitExec(WEB3AdminFeqOrderAndExecutionCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3Administrator l_web3Administrator = null;

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターが未指定(null)です。");
        }

        //1.1 validate()
        l_request.validate();

        //1.2 getInstanceFromログイン情報()
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限()
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.”外株（注文約定管理）”
        //is更新：　@true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
            true);

        //1.4 validate取引パスワード()
        //[validate取引パスワード()に指定する引数] 
        //パスワード：　@リクエストデータ.暗証番号
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.5 取引カレンダコンテキストの市場コードを再セットする
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.orderAndExecutionUnit.marketCode);
        
        //1.6 注文テーブルを取得する。
        boolean l_isHostOrder = false;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        Order l_feqOrder = null;
        try
        {
            l_feqOrder = 
                (Order) l_feqOrderManager.getOrder(
                    new Long(l_request.orderAndExecutionUnit.orderId).longValue());
        }
        //NotFoundExceptionがスローされた場合(該当注文が存在しない)、HOST注文とする。
        catch(NotFoundException ex)
        {
            l_isHostOrder = true;
        }
        
        //1.7 HOST注文（注文テーブルなし）の場合
        if (l_isHostOrder)
        {
            //1.7.1 get証券会社()
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution)l_web3Administrator.getInstitution();

            //1.7.2 get管理者コード()
            String l_strAdminCode = l_web3Administrator.getAdministratorCode();

            //1.7.3 persistHOST注文()
            //証券会社：　@get証券会社()
            //更新者コード：　@get管理者コード()
            //約定入力情報：　@リクエストデータ.約定入力情報
            persistHostOrder(l_institution,
                l_strAdminCode,
                l_request.orderAndExecutionUnit);
        }
        //1.8 ｲﾝﾀｰﾈｯﾄ注文（注文テーブルあり）の場合
        else
        {
            //1.8.1 persistｲﾝﾀｰﾈｯﾄ注文
            //約定入力情報：　@リクエストデータ.約定入力情報
            persistInternetOrder(l_request.orderAndExecutionUnit);
        }

        log.exiting(STR_METHOD_NAME);

        //1.9 createResponse()
        return (WEB3AdminFeqOrderAndExecutionCompleteResponse) l_request.createResponse();
    }
    
    /**
     * (validateｲﾝﾀｰﾈｯﾄ注文約定)<BR>
     * 約定入力確認処理を行う。（インターネット注文の場合）<BR>
     * <BR>
     * １）　@注文データのチェック<BR>
     * 　@注文データの内容が変更されていないかチェックを行う。<BR>
     * <BR>
     * 　@１－１）　@注文が未発注の場合（注文単位.getOrderStatus() == <BR>
     * ”1:受付済（新規注文）”）、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02143<BR>
     * <BR>
     * 　@１－２）　@出来終了済みの場合（注文単位.is出来終了() == true）、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02144<BR>
     * <BR>
     * 　@１－３）　@一部出来の場合（注文単位.isPartiallyExecuted() == true）、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02145<BR>
     * <BR>
     * 　@１－４）　@外国株式約定入力情報生成<BR>
     * 　@　@外国株式約定入力情報を生成し、<BR>
     *　@　@ 外国株式共通メッセージ作成サービスImpl.create外国株式約定入力情報()<BR>
     * 　@　@にて、メッセージデータを生成する。<BR>
     * <BR>
     * 　@　@[create外国株式約定入力情報()に指定する引数]<BR>
     * 　@　@外国株式約定入力情報：　@（生成したオブジェクト）<BR>
     * 　@　@注文単位：　@注文単位<BR>
     * 　@　@約定：　@null<BR>
     * 　@　@トランザクション（取引勘定明細）行：　@null<BR>
     * <BR>
     * 　@１－５）　@オブジェクト比較<BR>
     * 　@　@１－４）で生成したオブジェクトと引数の約定入力情報のプロパティ値が<BR>
     * 同じかを比較する。<BR>
     * 　@　@同一でない場合注文データが変更されたと判定し、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02146<BR>
     * <BR>
     * 　@　@※但し、約定／トランザクション（取引勘定明細）行から編集する<BR>
     * 項目は比較の対象外とする。<BR>
     * <BR>
     * ２）　@約定入力情報.約定為替レートのチェック<BR>
     * 　@項目値が変更された場合（１－４）で生成したオブジェクト.約定為替レート != <BR>
     * 引数の約定入力情報.約定為替レート）のみ、処理実施。<BR>
     * <BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02036<BR>
     * <BR>
     *   ２－２）　@数値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02220<BR>
     * <BR>
     * 　@２－３）　@数値に変換した時の有効桁数が、整数部３桁，<BR>
     * 小数部４桁の範囲外であれば、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02037<BR>
     * <BR>
     * 　@２－４）　@数値に変換した値 <= 0であれば、例外をスローする。 <BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02196<BR>
     * <BR>
     * ３）　@約定入力情報.約定詳細.約定単価のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02021<BR>
     * <BR>
     * 　@３－２）　@数値でない場合、例外をスローする<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02022<BR>
     * <BR>
     * 　@３－３）　@数値に変換した値 <= 0の場合、例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02023<BR>
     * <BR>
     * 　@３－４）　@数値に変換した時の有効桁数が、整数部６桁，<BR>
     * 小数部５桁の範囲外であれば、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02024<BR>
     * <BR>
     * 　@３－５）　@外国株式注文マネージャ.validate約定単価()にて、<BR>
     * 約定単価をチェックする。<BR>
     * <BR>
     * 　@　@[validate約定単価()に指定する引数]<BR>
     * 　@　@注文単位：　@注文単位<BR>
     * 　@　@約定数量：　@約定入力情報.約定詳細.約定単価<BR>
     * <BR>
     * ４）　@約定入力情報.約定詳細.約定数量のチェック<BR>
     * 　@４－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02025<BR>
     * <BR>
     * 　@４－２）　@9桁以内の整数値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02026<BR>
     * <BR>
     *   ４－３）　@数値に変換した値 <= 0の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02186<BR>
     * <BR>
     * 　@４－４）　@外国株式注文マネージャ.validate約定数量()にて、<BR>
     *             約定数量をチェックする。<BR>
     * <BR>
     * 　@　@[validate約定数量()に指定する引数]<BR>
     * 　@　@注文単位：　@注文単位<BR>
     * 　@　@約定数量：　@約定入力情報.約定詳細.約定数量<BR>
     * <BR>
     * ５）　@約定入力情報.約定日時のチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場場合）<BR>
     * 　@　@外国株式注文マネージャ.validate約定日()にて、約定日時をチェックする。<BR>
     * <BR>
     * 　@　@[validate約定日()に指定する引数]<BR>
     * 　@　@注文単位：　@注文単位<BR>
     * 　@　@約定日：　@約定入力情報.約定詳細.約定日時<BR>
     * <BR>
     * 　@－（未入力の場合）<BR>
     * 　@　@　@当日（TradingSystem.getSystemTimestamp()）をセットする。<BR>
     * <BR>
     * ６）　@約定入力情報.現地受渡日のチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場合）<BR>
     * 　@　@外国株式注文マネージャ.validate現地受渡日()にて、現地受渡日をチェックする。<BR>
     * <BR>
     * 　@　@[validate現地受渡日()に指定する引数]<BR>
     * 　@　@注文単位：　@注文単位<BR>
     * 　@　@現地受渡日：　@約定入力情報.現地受渡日<BR>
     * <BR>
     * 　@－（未入力の場合）<BR>
     * 　@　@約定入力情報.約定日の３営業日後をセットする。<BR>
     * <BR>
     * ７）　@国内受渡日のチェック<BR>
     * 　@（未入力の場合）<BR>
     * 　@　@約定入力情報.約定日の３営業日後をセットする。<BR>
     * <BR>
     * ８）　@約定No.の採番<BR>
     * 　@（同一注文で過去に約定が発生している場合）<BR> 
     * 　@　@注文単位.getExecute()にて、既約定を取得する。<BR> 
     * 　@　@『最後の注文No.＋1』を今回の約定No.とする。<BR>
     * <BR>
     * ９）　@金額項目のチェック<BR>
     * 　@this.validate外国株式金額()をコールする。<BR>
     * <BR>
     * 　@[validate外国株式金額()に指定する引数]<BR>
     * 　@証券会社コード：　@注文単位.証券会社コード<BR>
     * 　@補助口座：　@注文単位.get補助口座()<BR>
     * 　@市場：　@注文単位.getProduct().get市場()<BR>
     * 　@通貨：　@注文単位.getProduct().get通貨()<BR>
     * 　@銘柄：　@外国株式銘柄<BR>
     * 　@税区分：　@注文単位.getTaxType()<BR>
     * 　@約定入力情報：　@約定入力情報<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_execInputInfo - (約定入力情報)<BR>
     * 外国株式約定入力情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B65B4C035D
     */
    private void validateInternetOrderExec(
        WEB3FeqOrderUnit l_orderUnit, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateInternetOrderExec(WEB3FeqOrderUnit," + 
            " WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターが未指定(null)です。");
        }
        
        //１）　@注文データのチェック 注文データの内容が変更されていないかチェックを行う。
        
        //１－１）　@注文が未発注の場合
        //（注文単位.getOrderStatus() == ”1:受付済（新規注文）”）、
        //例外をスローする。
        if (OrderStatusEnum.ACCEPTED.equals(l_orderUnit.getOrderStatus())) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文状態が" + l_orderUnit.getOrderStatus() + "です");
        }
        
        //１－２）　@出来終了済みの場合（注文単位.is出来終了() == true）、
        //例外をスローする。
        if (l_orderUnit.isExecEnd())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02144,
                this.getClass().getName() + STR_METHOD_NAME,
                "出来終了処理済みなので、約定不可です。");
        }
        
        //１－３）　@一部出来の場合（注文単位.isPartiallyExecuted() == true）、
        //例外をスローする。
        if (l_orderUnit.isPartiallyExecuted())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02145,
                this.getClass().getName() + STR_METHOD_NAME,
                "一部出来の場合なので、約定不可です。");
        }
        
        //１－４）　@外国株式約定入力情報生成
        //外国株式約定入力情報を生成し、
        //外国株式共通メッセージ作成サービスImpl.create外国株式約定入力情報()にて、
        //メッセージデータを生成する。        
        WEB3FeqCommonMessageCreatedService l_messageCreateService =
            new WEB3FeqCommonMessageCreatedServiceImpl();

        WEB3FeqOrderAndExecutionUnit l_web3FeqOrderAndExecutionUnit = 
            (WEB3FeqOrderAndExecutionUnit) l_execInputInfo.clone();
        //[create外国株式約定入力情報()に指定する引数]
        //外国株式約定入力情報：　@（生成したオブジェクト）
        //注文単位：　@注文単位
        //約定：　@null
        //トランザクション（取引勘定明細）行：　@null
        l_messageCreateService.createFeqOrderAndExecutionUnit(
            l_web3FeqOrderAndExecutionUnit,
            l_orderUnit,
            null,
            null);
        
        //１－５）　@オブジェクト比較
        //  １－４）で生成したオブジェクトと引数の約定入力情報のプロパティ値が同じかを比較する。
        //　@同一でない場合注文データが変更されたと判定し、例外をスローする。
        //　@※但し、約定／トランザクション（取引勘定明細）行から編集する項目は比較の対象外とする。      
        if (!this.isOrderAndExecutionUnitEquals(l_web3FeqOrderAndExecutionUnit, l_execInputInfo))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02146,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定入力情報が同一ではない注文データなので、変更不可です。");            
        }

        if (l_execInputInfo.execDetailInfoUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定入力情報.約定詳細が存在しない。");   
        }

        //２）　@約定入力情報.約定為替レートのチェック
        //項目値が変更された場合（１－４）で
        //生成したオブジェクト.約定為替レート != 引数の約定入力情報.約定為替レート）のみ、
        //処理実施。
        String l_strExecRate1 = l_web3FeqOrderAndExecutionUnit.execExchangeRate;
        String l_strExecRate2 = l_execInputInfo.execExchangeRate;
        if (!this.isEqual(l_strExecRate1, l_strExecRate2))
        {
            //２－１）　@未入力の場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(l_strExecRate2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02036,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定為替レートが未指定です。");                
            }
            //２－２）　@数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(l_strExecRate2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 約定為替レートが数値でないの場合チェック"); 
            }            
            //２－３）　@数値に変換した時の有効桁数が、整数部３桁，
            //小数部４桁の範囲外であれば、例外をスローする。
            if (WEB3StringTypeUtility.getIntegerDigits(l_strExecRate2) > 3 
                || WEB3StringTypeUtility.getFractionDigits(l_strExecRate2) > 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定為替レートの有効桁数が、整数部３桁，小数部４桁の範囲外です。");
            }

            //２－４）　@数値に変換した値 <= 0であれば、例外をスローする。
            if (Double.parseDouble(l_strExecRate2) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定為替レートが数値に変換した値 <= 0です。");
            }
        }

        //３）　@約定入力情報.約定詳細.約定単価のチェック
        //　@３－１）　@未入力の場合、例外をスローする。
        String l_strExecPrice = l_execInputInfo.execDetailInfoUnit.execPrice;
        if (WEB3StringTypeUtility.isEmpty(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価が未入力です。");
        }
        
        //　@３－２）　@数値でない場合、例外をスローする
        if (!WEB3StringTypeUtility.isNumber(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価が数値以外の値です。");
        }
        
        //　@３－３）　@数値に変換した値 <= 0の場合、例外をスローする。
        if (Double.parseDouble(l_strExecPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価が数値に変換した値 <= 0です。");
        }
        //　@３－４）　@数値に変換した時の有効桁数が、整数部６桁，
        //小数部５桁の範囲外であれば、例外をスローする。
        if (WEB3StringTypeUtility.getIntegerDigits(l_strExecPrice) > 6 
            || WEB3StringTypeUtility.getFractionDigits(l_strExecPrice) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価は整数部６桁，小数部５桁の範囲外です。");
        }
        
        //　@３－５）　@外国株式注文マネージャ.validate約定単価()にて、
        //約定単価をチェックする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        double l_dblExecPrice = Double.parseDouble(l_strExecPrice);
        //[validate約定単価()に指定する引数] 
        //注文単位：　@注文単位
        //約定単価：　@約定入力情報.約定詳細.約定単価
        l_feqOrderManager.validateExecutedPrice(
            l_orderUnit,
            l_dblExecPrice);

        //４）　@約定入力情報.約定詳細.約定数量のチェック
        String l_strExecQuantity = l_execInputInfo.execDetailInfoUnit.execQuantity;
        //　@４－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_strExecQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02025,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定数量が未入力です。");
        }
        
        //　@４－２）　@9桁以内の整数値でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(l_strExecQuantity) || 
            WEB3StringTypeUtility.getIntegerDigits(l_strExecQuantity) > 9)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定数量が9桁以内の整数値ではありません。");
        }
        
        //  ４－３）　@数値に変換した値 <= 0の場合、例外をスローする。
        if (Double.parseDouble(l_strExecQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定数量が数値に変換した値 <= 0です。");
        }
        
        //　@４－４）　@外国株式注文マネージャ.validate約定数量()にて、
        //約定数量をチェックする。
        double l_dblExecQuantity = Double.parseDouble(l_strExecQuantity);
        //[validate約定数量()に指定する引数] 
        //注文単位：　@注文単位
        //約定数量：　@約定入力情報.約定詳細.約定数量
        l_feqOrderManager.validateExecutedQuantity(
            l_orderUnit,
            l_dblExecQuantity);
        
		//売買区分が売付の場合、約定数量について以下のチェックを行う。
		if (WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType)){
			//銘柄IDを取得する。
			long l_lngProductId = l_orderUnit.getProduct().getProductId();
			//税区分を取得する。
			TaxTypeEnum l_taxType = 
						  WEB3TaxTypeSpecialDef.SPECIAL.equals(l_execInputInfo.taxType)?TaxTypeEnum.SPECIAL:TaxTypeEnum.NORMAL;

			WEB3FeqPositionManager l_web3FeqPositionManager =
						(WEB3FeqPositionManager)l_finApp.getTradingModule(
						ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();

			//保有資産を取得する。
			Asset l_asset = 
					  l_web3FeqPositionManager.getAsset(
					  l_orderUnit.getAccountId(), 
					  l_orderUnit.getSubAccountId(),
					  l_lngProductId, 
					  l_taxType);
							
			if(l_asset == null){
				//保有資産に該当するデータがない場合、例外をスローする。
				log.error("保有資産該当データなし。");
				throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00204,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}else{
				if(!(l_asset.getQuantity() >= Long.parseLong(l_execInputInfo.execDetailInfoUnit.execQuantity))){
					//（保有資産の売付可能数量 >= 約定数量）でない場合、例外をスローする。
					log.error("保有資産残数量チェックエラー。");
					throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01931,
					this.getClass().getName() + "." + STR_METHOD_NAME);
				}
			}
		}
        
        //５）　@約定入力情報.約定日時のチェック
        //　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@－（未入力の場合）
        //　@　@　@当日（TradingSystem.getSystemTimestamp()）をセットする。
        if (l_execInputInfo.execDetailInfoUnit.executionTimestamp == null)
        {
            l_execInputInfo.execDetailInfoUnit.executionTimestamp = 
                GtlUtils.getSystemTimestamp();
        }
        else
        {
            //　@－（入力がある場場合）
            //　@　@外国株式注文マネージャ.validate約定日()にて、約定日時をチェックする。
            //注文単位：　@注文単位
            //約定日：　@約定入力情報.約定詳細.約定日時
            l_feqOrderManager.validateExecutionDate(
                l_orderUnit,
                l_execInputInfo.execDetailInfoUnit.executionTimestamp);
        }
        
        //６）　@約定入力情報.現地受渡日のチェック
        //　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@－（未入力の場合）
        //　@　@約定入力情報.約定日の３営業日後をセットする。        
        if (l_execInputInfo.localDeliveryDate == null)
        {
            
            Timestamp l_tsExecutionTimestamp = 
                new Timestamp(l_execInputInfo.execDetailInfoUnit.executionTimestamp.getTime());
            
            WEB3GentradeBizDate l_bizDate = 
                new WEB3GentradeBizDate(l_tsExecutionTimestamp);            
            
            l_execInputInfo.localDeliveryDate = l_bizDate.roll(3);
        } 
        else
        {
            //　@－（入力がある場合）
            //　@　@外国株式注文マネージャ.validate現地受渡日()にて、現地受渡日をチェックする。
            //注文単位：　@注文単位
            //現地受渡日：　@約定入力情報.現地受渡日
            l_feqOrderManager.validateFDeliveryDate(
                l_orderUnit,
                l_execInputInfo.localDeliveryDate);
        }
        
        //７）　@国内受渡日のチェック
        //　@未入力の場合は値セットを行う。
        //　@　@約定入力情報.約定日の３営業日後をセットする。        
        if (l_execInputInfo.execDetailInfoUnit.deliveryDate == null)
        {            
            Timestamp l_tsExecutionTimestamp = 
                new Timestamp(l_execInputInfo.execDetailInfoUnit.executionTimestamp.getTime());
            
            WEB3GentradeBizDate l_bizDate = 
                new WEB3GentradeBizDate(l_tsExecutionTimestamp);            
            
            l_execInputInfo.execDetailInfoUnit.deliveryDate = l_bizDate.roll(3);
        }        
        
        //８）　@約定No.の採番
        // 同一注文で過去に約定が発生している場合は、
        // 最後の『約定No.＋１』を今回の約定No.とする。         
        // 注文単位.getExecutions()にて、既約定を取得する。
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
        
        int l_intExecSerialNo = 1; 
        for (int i = 0; i < l_orderExecutions.length; i++)
        {
            if (l_intExecSerialNo < l_orderExecutions[i].getExecutionSerialNo())
            {
                l_intExecSerialNo = l_orderExecutions[i].getExecutionSerialNo() + 1;
            }
        }
        // フォーマット変換
        l_execInputInfo.execDetailInfoUnit.execNo = 
            WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);
        
        //９）　@金額項目のチェック
        //　@this.validate外国株式金額()をコールする。
        //証券会社コード：　@注文単位.証券会社コード
        //補助口座：　@注文単位.get補助口座()
        //市場：　@注文単位.getProduct().get市場()
        //通貨：　@注文単位.getProduct().get通貨()
        //銘柄：　@外国株式銘柄
        //税区分：　@注文単位.getTaxType()
        //約定入力情報：　@約定入力情報
        WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)l_orderUnit.getProduct();
        validateFeqAmount(
            l_orderUnit.getInstitutionCode(),
            l_orderUnit.getSubAccount(),
            l_feqProduct.getMarket(),
            l_feqProduct.getCurrency(),
            l_feqProduct,
            l_orderUnit.getTaxType(),
            l_execInputInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateHOST注文約定)<BR>
     * 約定入力確認処理を行う。（HOST注文の場合）<BR>
     * <BR>
     * １）　@約定入力情報.部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）　@存在チェックを実施する。<BR>
     * 　@　@拡張アカウントマネージャ.getBranch()にて部店オブジェクトを取得する。<BR>
     * 　@　@取得できなかった場合、部店コード入力が不正と判断し、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01386<BR>
     * <BR>
     * 　@　@[getBranch()に指定する引数]<BR>
     * 　@　@証券会社：　@証券会社<BR>
     * 　@　@部店コード：　@約定入力情報.部店コード<BR>
     * <BR>
     * ２）　@約定入力情報.顧客コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_00835<BR>
     * <BR>
     * 　@２－２）　@存在チェックを実施する。<BR>
     * 　@　@拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。<BR>
     * 　@　@取得できなかった場合、顧客コード入力が不正と判断し、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_01035<BR>
     * <BR>
     * 　@　@[get顧客()に指定する引数]<BR>
     * 　@　@証券会社コード：　@証券会社.getInstitutionCode()<BR>
     * 　@　@部店コード：　@約定入力情報.部店コード<BR>
     * 　@　@口座コード：　@約定入力情報.顧客コード<BR>
     * <BR>
     * ３）　@約定入力情報.扱者コードのチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場合）<BR>
     * 　@　@顧客.扱者コード（SONAR）と約定入力情報.扱者コードが不一致の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02147<BR>
     * <BR>
     * 　@－（未入力の場合）<BR>
     * 　@　@顧客.扱者コード（SONAR）をセットする。<BR>
     * <BR>
     * ４）　@約定入力情報.約定日時のチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場合）<BR>
     * 　@　@約定日時の日付が、営業日であるかチェックする。<BR>
     * 　@　@営業日でなければ、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02149<BR>
     * <BR>
     * 　@－（未入力の場合）<BR>
     * 　@　@　@当日（TradingSystem.getSystemTimestamp()）をセットする。<BR>
     * <BR>
     * ５）　@約定入力情報.特定口座区分のチェック<BR>
     * 　@５－１）　@未入力の場合、例外をスローする。
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02113<BR>
     * <BR>
     * 　@５－２）　@特定口座指定（約定入力情報.特定口座区分 == ”1：特定”）の場合、<BR>
     * 特定口座で取引可能かをチェックする。<BR>
     * <BR>
     * 　@　@注文マネージャ.validate特定口座開設()にて特定口座区分をチェックする。<BR>
     * <BR>
     * 　@　@[validate特定口座開設()に指定する引数]<BR>
     * 　@　@補助口座：　@外国株式クライアントリクエストサービス.get補助口座（顧客）<BR>
     * 　@　@発注日：　@約定入力情報.約定詳細.約定日時<BR>
     * <BR>
     *   ６）　@約定入力情報.銘柄コード／現地銘柄コードのチェック<BR>
     * 　@６－１）　@約定入力情報.銘柄コード／現地銘柄コードの両方が未入力の場合、<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02148<BR>
     * <BR>
     * 　@６－２）　@存在チェックを実施する。<BR>
     * 　@　@外国株式プロダクトマネージャ.get外国株式銘柄()にて外国株式<BR>
     * 銘柄オブジェクトを取得する。<BR>
     * 　@　@取得できなかった場合、銘柄コード入力が不正と判断し、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02142<BR>
     * <BR>
     * 　@　@[get外国株式銘柄()に指定する引数]<BR>
     * 　@　@証券会社：　@証券会社<BR>
     * 　@　@銘柄コード：　@約定入力情報.銘柄コード。未入力の場合は現地銘柄コード。<BR>
     * <BR>
     * 　@６－３）　@銘柄コード／現地銘柄コード／市場コード／通貨コードをセットする。<BR>
     * 　@　@約定入力情報.銘柄コード：　@外国株式銘柄.getProductCode()<BR>
     * 　@　@約定入力情報.現地銘柄コード：　@外国株式銘柄.get現地銘柄コード()<BR>
     * 　@　@約定入力情報.市場コード：　@外国株式銘柄.get市場コード()<BR>
     * 　@　@約定入力情報.通貨コード：　@外国株式銘柄.get通貨コード()<BR>
     * <BR>
     * ７）　@約定入力情報.決済区分のチェック<BR>
     * 　@７－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02111<BR>
     * <BR>
     * 　@７－２）　@外貨決済指定（約定入力情報.決済区分 == ”1：外貨決済”）の場合、<BR>
     * 外貨決済可能かをチェックする。<BR>
     * 　@　@注文マネージャ.validate外貨決済()にて決済区分をチェックする。<BR>
     * <BR>
     * 　@　@[validate外貨決済()に指定する引数]<BR>
     * 　@　@補助口座：　@外国株式クライアントリクエストサービス.get補助口座（顧客）<BR>
     * 　@　@通貨コード：　@約定入力情報.通貨コード<BR>
     * <BR>
     * ８）　@約定入力情報.売買区分のチェック<BR>
     * 　@８－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_01402<BR>
     * <BR>
     * 　@８－２）　@不正なコード値の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_01403<BR>
     * <BR>

     * ９）　@注文単価区分のチェック<BR>
     * 　@９－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_00184<BR>
     * <BR>
     * 　@９－２）　@不正なコード値の場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_00185<BR>
     * <BR>
     * １０）　@約定入力情報.約定為替レートのチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * 　@１０－１）　@未入力の場合<BR>
     * 　@　@　@１０－１－１）　@通貨コードを取得する。<BR>
     * 　@　@　@１０－１－２）　@（共通）通貨テーブルより該当する約定為替レートを取得し、<BR>
     * 　@　@　@セットする。<BR>
     * 　@　@　@　@　@-a)通貨コードと証券会社コードに紐づく、（共通）通貨オブジェクトを取得する。<BR>
     * 　@　@　@　@　@-b)（共通）通貨オブジェクトより、　@リクエストデータ．売買区分に該当する<BR>
     * 　@　@　@　@　@　@為替レートを取得する。<BR>
     * 　@　@　@　@　@　@　@（売買区分　@＝　@買　@の場合）　@買付約定為替レートを取得する。<BR>
     * 　@　@　@　@　@　@　@（売買区分　@＝　@売　@の場合）　@売付約定為替レートを取得する。<BR>
     * 　@　@　@　@　@-c)　@-bで取得した約定為替レートをセットする。<BR>
     * <BR>
     *   １０－２）　@数値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02220<BR>
     * <BR>
     * 　@１０－３）　@数値に変換した時の有効桁数が、整数部３桁，小数部４桁の<BR>
     * 範囲外であれば、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02037<BR>
     * <BR>
     * 　@１０－４）　@数値に変換した値 <= 0であれば、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02196<BR>
     * <BR>
     * １１）　@約定入力情報.約定詳細.約定単価のチェック<BR>
     * 　@１１－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02021<BR>
     * <BR>
     * 　@１１－２）　@数値でない場合、例外をスローする<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02022<BR>
     * <BR>
     * 　@１１－３）　@数値に変換した値 <= 0の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02023<BR>
     * <BR>
     * 　@１１－４）　@数値に変換した時の有効桁数が、整数部６桁，小数部５桁の<BR>
     * 範囲外であれば、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02024<BR>
     * <BR>
     * １２）　@約定入力情報.約定詳細.約定数量のチェック<BR>
     * 　@１２－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02025<BR>
     * <BR>
     * 　@１２－２）　@9桁以内の整数値でない場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02026<BR>
     * １２－３）　@数値に変換した値 <= 0の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02186<BR>
     * <BR>
     * １３）　@約定入力情報.約定詳細.国内受渡日セット<BR>
     * 　@受渡日が未入力の場合（約定入力情報.約定詳細.国内受渡日 == null）、<BR>
     * 　@約定日の３営業日後をセットする。<BR>
     * <BR>
     * １４）　@約定入力情報.現地受渡日のチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（未入力の場合）<BR>
     * 　@　@約定入力情報.約定詳細.国内受渡日をセットする。<BR>
     * <BR>
     * １５）　@発注日のチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場合）<BR>
     * 　@　@当日（TradingSystem.getSystemTimestamp()の日付部分）の<BR>
     * ３営業日前を取得する。<BR>
     * 　@　@３営業日前より前の日付であれば（当日の３営業日前 ＞ 発注日）、<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02150<BR>
     * <BR>
     * 　@－（未入力の場合）<BR>
     * 　@　@当日（TradingSystem.getSystemTimestamp()の日付部分）をセットする。<BR>
     * <BR>
     * 　@－発注日が営業日かチェックする。<BR> 
     * 　@　@営業日でなければ、例外をスローする。<BR>
     * １６）　@システム自動セット項目のチェック<BR>
     * 　@１６－１）　@入力値チェック<BR>
     * 　@　@以下の項目に入力がある場合（nullでない場合）、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:　@　@BUSINESS_ERROR_02151<BR>
     * <BR>
     * 　@　@約定入力情報.運用コード<BR>
     * 　@　@約定入力情報.伝票番号<BR>
     * 　@　@約定入力情報.注文数量<BR>
     * 　@　@約定入力情報.注文単価<BR>
     * <BR>
     * 　@１６－２）　@値セット<BR>
     * 　@　@以下の通り、約定入力情報のプロパティに値をセットする。<BR>
     * <BR>
     * 　@　@約定入力情報.運用コード：　@<BR>
     * 　@　@　@外国株式運用コード採番サービス.get新規運用コード()<BR>
     * <BR>
     * 　@　@　@[get新規運用コード()に指定する引数]<BR>
     * 　@　@　@市場：　@外国株式銘柄.get市場()<BR>
     * 　@　@　@発注日：　@約定入力情報.発注日<BR>
     * <BR>
     * 　@　@約定入力情報.識別コード：<BR>
     * 　@　@　@注文識別コード採番サービス.get新規識別コード()<BR>
     * <BR>
     * 　@　@　@[get新規識別コード()に指定する引数]<BR>
     * 　@　@　@証券会社コード：　@証券会社.getInstitutionCode()<BR>
     * 　@　@　@部店コード：　@約定入力情報.部店コード<BR>
     * 　@　@　@銘柄タイプ：　@銘柄タイプ.外国株式<BR>
     * <BR>
     * 　@　@約定入力情報.伝票番号：　@"9"(WebBroker)＋識別コードの下3桁<BR>
     * <BR>　@　@
     * 　@　@約定入力情報.注文数量：　@約定入力情報.約定詳細.約定数量<BR>
     * 　@　@約定入力情報.注文単価：　@約定入力情報.約定詳細.約定単価<BR>
     * <BR>
     * １７）　@約定No.の採番<BR>
     *   this.validate外国株式金額()をコールする。<BR>
     * <BR>
     * １８）　@金額項目のチェック<BR>
     * 　@this.validate外国株式金額()をコールする。<BR>
     * <BR>
     * 　@[validate外国株式金額()に指定する引数]<BR>
     * 　@証券会社コード：　@注文単位.証券会社コード<BR>
     * 　@補助口座：　@外国株式クライアントリクエストサービス.get補助口座（顧客）<BR>
     * 　@市場：　@外国株式銘柄.get市場()<BR>
     * 　@通貨：　@外国株式銘柄.get通貨()<BR>
     * 　@銘柄：　@外国株式銘柄<BR>
     * 　@税区分：　@約定入力情報.特定口座区分に対応する税区分（TaxTypeEnum）<BR>
     * 　@約定入力情報：　@約定入力情報<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_execInputInfo - (約定入力情報)<BR>
     * 外国株式約定入力情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B65B4E0001
     */
    private void validateHostOrderExec(
        WEB3GentradeInstitution l_institution, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateHostOrderExec(WEB3GentradeInstitution," + 
            " WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターが未指定(null)です。");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        WEB3FeqProductManager l_web3FeqProductManager = 
            (WEB3FeqProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        
		WEB3FeqPositionManager l_web3FeqPositionManager =
					(WEB3FeqPositionManager)l_finApp.getTradingModule(
						ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();
        
        WEB3FeqProduct l_web3FeqProduct = null;
        
        //１）　@約定入力情報.部店コードのチェック
        //　@１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードが未指定です。");
        } 
        else
        {
            //１－２）　@存在チェックを実施する。
            //　@拡張アカウントマネージャ.getBranch()にて部店オブジェクトを取得する。
            //  取得できなかった場合、部店コード入力が不正と判断し、例外をスローする。
            //[getBranch()に指定する引数] 
            //証券会社：　@証券会社
            //部店コード：　@約定入力情報.部店コード
            try 
            {
                WEB3GentradeBranch l_branch = 
                    (WEB3GentradeBranch)l_gentradeAccountManager.getBranch(
                        l_institution,
                        l_execInputInfo.branchCode);
                
                if (l_branch == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "部店コード入力が不正です、入力された部店コード = " + l_execInputInfo.branchCode);                     
                }
            }
            catch (NotFoundException l_nfex) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "部店コード入力が不正です、入力された部店コード = " + l_execInputInfo.branchCode);
            }
        }
         
        //２）　@約定入力情報.顧客コードのチェック
        //　@２－１）　@未入力の場合、例外をスローする。
        MainAccount l_mainAccount = null;
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.accountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }
        else
        {
            //２－２）　@存在チェックを実施する。
            //　@拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。
            //　@取得できなかった場合、顧客コード入力が不正と判断し、例外をスローする。
            //
            //[get顧客()に指定する引数] 
            //   証券会社コード：　@証券会社.getInstitutionCode()
            //   部店コード：　@約定入力情報.部店コード
            //   口座コード：　@約定入力情報.顧客コード
            try
            {
                l_mainAccount = l_gentradeAccountManager.getMainAccount(
                    l_institution.getInstitutionCode(),
                    l_execInputInfo.branchCode,
                    l_execInputInfo.accountCode);
            }
            catch (WEB3BaseException e)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
        }
         
        //３）　@約定入力情報.扱者コードのチェック
        //　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@－（未入力の場合）
        //　@　@顧客.扱者コード（SONAR）をセットする。
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        if (l_mainAccountRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客Rowオブジェクトが存在しない。"); 
        }
        String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.traderCode))
        {
            l_execInputInfo.traderCode = l_strSonarTraderCode;
        }
        else
        {
            //　@－（入力がある場合）
            //　@　@顧客.扱者コード（SONAR）と約定入力情報.扱者コードが不一致の場合、例外をスローする。
            if (!l_strSonarTraderCode.equals(l_execInputInfo.traderCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02147,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定入力情報の扱者コードと顧客.扱者コード（SONAR）が不一致です。");
            }
        }
        
        WEB3GentradeSubAccount l_subAccount = 
            WEB3FeqClientRequestService.getSubAccount(
                (WEB3GentradeMainAccount)l_mainAccount);
             
        //４）　@約定入力情報.約定日時のチェック
        //　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@－（未入力の場合）
        //　@　@　@当日（TradingSystem.getSystemTimestamp()）をセットする。
        if (l_execInputInfo.execDetailInfoUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定入力情報.約定詳細が存在しない。");   
        }
        Timestamp l_tsExecutionTimestamp = GtlUtils.getSystemTimestamp(); 

        if (l_execInputInfo.execDetailInfoUnit.executionTimestamp == null)
        {
            l_execInputInfo.execDetailInfoUnit.executionTimestamp = 
                l_tsExecutionTimestamp;
        }
        else
        {
            l_tsExecutionTimestamp = 
                new Timestamp(l_execInputInfo.execDetailInfoUnit.executionTimestamp.getTime());
            //－（入力がある場合）
            //　@約定日時の日付が、営業日であるかチェックする。
            //　@営業日でなければ、例外をスローする。
            if (!WEB3FeqDateUtility.isFeqBizDate(l_tsExecutionTimestamp))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定日時の日付が営業日ではありません。"); 
            }
        }

        //５）　@約定入力情報.特定口座区分のチェック
        //　@５－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.taxType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02113,
                this.getClass().getName() + STR_METHOD_NAME,
                "特定口座区分がnullです。");             
        }
        else
        {
            //５－２）特定口座指定（約定入力情報.特定口座区分 == ”1：特定”）の場合、
            //    特定口座で取引可能かをチェックする。
            //　@注文マネージャ.validate特定口座開設()にて特定口座区分をチェックする。
            //　@[validate特定口座開設()に指定する引数]
            //　@補助口座：　@外国株式クライアントリクエストサービス.get補助口座（顧客）
            //　@発注日：　@約定入力情報.約定詳細.約定日時
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_execInputInfo.taxType))
            {
                //補助口座：　@外国株式クライアントリクエストサービス.get補助口座（顧客）
                //発注日：　@約定入力情報.約定詳細.約定日時
                l_orderManager.validateSpecialAccountEstablish(
                    l_subAccount,
                    l_execInputInfo.execDetailInfoUnit.executionTimestamp);
            }
        }
        
        //６）　@約定入力情報.銘柄コード／現地銘柄コードのチェック
        //　@６－１）　@約定入力情報.銘柄コード／現地銘柄コードの両方が未入力の場合、
        //例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.productCode) 
            && WEB3StringTypeUtility.isEmpty(l_execInputInfo.localProductCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02148,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定入力情報.銘柄コードと現地銘柄コードの両方が未入力です。"); 
        }
        else
        {
            //６－２）　@存在チェックを実施する。
            //　@外国株式プロダクトマネージャ.get外国株式銘柄()にて外国株式銘柄オブジェクトを取得する。
            //　@取得できなかった場合、銘柄コード入力が不正と判断し、例外をスローする。
            //
            //　@[get外国株式銘柄()に指定する引数]
            //　@証券会社：　@証券会社
            //　@銘柄コード：　@約定入力情報.銘柄コード。未入力の場合は現地銘柄コード。
            String l_strProductCode = l_execInputInfo.productCode;
            if (WEB3StringTypeUtility.isEmpty(l_strProductCode))
            {
                l_strProductCode = l_execInputInfo.localProductCode;
            }
            try
            {
                l_web3FeqProduct = 
                    (WEB3FeqProduct)l_web3FeqProductManager.getFeqProduct(
                        l_institution,
                        l_strProductCode); 
                //取得できなかった場合、銘柄コード入力が不正と判断し、例外をスローする。
                if (l_web3FeqProduct == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "銘柄コード入力が不正。"); 
                }
                
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "銘柄コード入力が不正。");                 
            }
            
            //　@６－３）　@銘柄コード／現地銘柄コード／市場コード／通貨コードをセットする。
            //約定入力情報.銘柄コード：　@外国株式銘柄.getProductCode()
            l_execInputInfo.productCode = l_web3FeqProduct.getProductCode();
            //約定入力情報.現地銘柄コード：　@外国株式銘柄.get現地銘柄コード()
            l_execInputInfo.localProductCode = l_web3FeqProduct.getOffshoreProductCode();
            //約定入力情報.市場コード：　@外国株式銘柄.get市場コード()
            l_execInputInfo.marketCode = l_web3FeqProduct.getMarketCode();
            //約定入力情報.通貨コード：　@外国株式銘柄.get通貨コード()
            l_execInputInfo.currencyCode = l_web3FeqProduct.getCurrencyCode();
        }
         
        //７）　@約定入力情報.決済区分のチェック
        //　@７－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.settleDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02111,
                this.getClass().getName() + STR_METHOD_NAME,
                "決済区分がnullです。");  
        }
        else
        {
            //７－２）外貨決済指定（約定入力情報.決済区分 == ”1：外貨決済”）の場合、
            //      外貨決済可能かをチェックする。
            //　@注文マネージャ.validate外貨決済()にて決済区分をチェックする。
            //　@[validate外貨決済()に指定する引数]
            //　@補助口座：　@外国株式クライアントリクエストサービス.get補助口座（顧客）
            //　@通貨コード：　@約定入力情報.通貨コード
            if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(
                l_execInputInfo.settleDiv))
            {
                //補助口座：　@外国株式クライアントリクエストサービス.get補助口座(顧客)
                //通貨コード：　@約定入力情報.通貨コード
                l_orderManager.validateFcSettle(
                    l_subAccount,
                    l_execInputInfo.currencyCode);
            }
        }
         
        //８）　@約定入力情報.売買区分のチェック
        //　@８－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.dealingType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01402,
                this.getClass().getName() + STR_METHOD_NAME,
                "売買区分が未指定です。");  
        }
        else
        {
            //　@８－２）　@不正なコード値の場合、例外をスローする。
            if (!WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType)
                && !WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "売買区分の値が存在しないコード値です。"); 
            }
        }                 
         
        //９）　@注文単価区分のチェック
        //９－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderPriceDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00184,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文単価区分が未指定です。"); 
        }
        else if (!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_execInputInfo.orderPriceDiv)
            && !WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_execInputInfo.orderPriceDiv))
        {
            //９－２）　@不正なコード値の場合、例外をスローする。
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00185,
                this.getClass().getName() + STR_METHOD_NAME,
                "注文単価区分の値が存在しないコード値です。"); 
        }

        //１０）　@約定入力情報.約定為替レートのチェック
        //　@１０－１）　@未入力の場合
        String l_execExchangeRate = l_execInputInfo.execExchangeRate;

        if (WEB3StringTypeUtility.isEmpty(l_execExchangeRate))
        {
            // １０－１－１）通貨コードを取得する。
            String l_strCurrencyCode = l_execInputInfo.currencyCode;
            // 証券会社コード
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            // 売買区分
            String l_strDealingType = l_execInputInfo.dealingType;

            // １０－１－２）（共通）通貨テーブルより該当する約定為替レートを取得し、セットする。
            // -a)通貨コードと証券会社コードに紐づく、（共通）通貨オブジェクトを取得する。
            WEB3GentradeCurrency l_currency =
                WEB3GentradeCurrency.genCurrency(l_strInstitutionCode, l_strCurrencyCode);

            // （売買区分　@＝　@買　@の場合）　@買付約定為替レートを取得する。
            if (WEB3BuySellTypeDef.BUY.equals(l_strDealingType))
            {
                // -bで取得した約定為替レートをセットする。
                l_execInputInfo.execExchangeRate =
                    WEB3StringTypeUtility.formatNumber(l_currency.getBuyExecRate());
            }
            // （売買区分　@＝　@売　@の場合）　@売付約定為替レートを取得する。
            else if (WEB3BuySellTypeDef.SELL.equals(l_strDealingType))
            {
                // -bで取得した約定為替レートをセットする。
                l_execInputInfo.execExchangeRate =
                    WEB3StringTypeUtility.formatNumber(l_currency.getSellExecRate());
            }
        }
        else
        {
            //１０－２）　@数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(l_execExchangeRate))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 約定為替レートが数値でないの場合チェック"); 
            }
            
            //　@１０－３）　@数値に変換した時の有効桁数が、整数部３桁，小数部４桁の範囲外であれば、
            //例外をスローする。
            if (WEB3StringTypeUtility.getIntegerDigits(l_execExchangeRate) > 3 || 
                WEB3StringTypeUtility.getFractionDigits(l_execExchangeRate) > 4)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定為替レートの有効桁数が、整数部３桁，小数部４桁の範囲外です。"); 
            }

            // １０－４）　@数値に変換した値 <= 0であれば、例外をスローする。
            if (Double.parseDouble(l_execExchangeRate) <= 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定為替レートが数値に変換した値 <= 0です。"); 
            }
        }

        //１１）　@約定入力情報.約定詳細.約定単価のチェック
        String l_strExecPrice = l_execInputInfo.execDetailInfoUnit.execPrice;
        //　@１１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価が未入力です。"); 
        }

        //１１－２）　@数値でない場合、例外をスローする
        //有効数値チェック
        if (!WEB3StringTypeUtility.isNumber(l_strExecPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価が数値以外の値です。"); 
        }
         
        //１１－３）　@数値に変換した値 <= 0であれば、例外をスローする。
        if (Double.parseDouble(l_strExecPrice) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価が数値に変換した値 <= 0です。"); 
        }
         
        //１１－４）　@数値に変換した時の有効桁数が、整数部６桁，小数部５桁の範囲外であれば、例外をスローする。
        if (WEB3StringTypeUtility.getIntegerDigits(l_strExecPrice) > 6 
            || WEB3StringTypeUtility.getFractionDigits(l_strExecPrice) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定単価は整数部６桁，小数部５桁の範囲外です。"); 
        }
         
        //１２）　@約定入力情報.約定詳細.約定数量のチェック
        String l_strExecQuantity = l_execInputInfo.execDetailInfoUnit.execQuantity;
        //　@１２－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_strExecQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02025,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定数量が未入力です。"); 
        }
        //　@１２－２）　@9桁以内の整数値でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(l_strExecQuantity) 
            || WEB3StringTypeUtility.getIntegerDigits(l_strExecQuantity) > 9)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定数量が9桁以内の整数値ではありません。"); 
        }
        
        //  １２－３）　@数値に変換した値 <= 0であれば、例外をスローする。
        if (Double.parseDouble(l_strExecQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定数量が数値に変換した値 <= 0です。"); 
        }
         
		//売買区分が売付の場合、約定数量について以下のチェックを行う。
		if (WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType)){
			//銘柄IDを取得する。
			FeqProductParams l_feqProductParams = 
							new FeqProductParams((FeqProductRow)l_web3FeqProduct.getDataSourceObject());
			long l_lngProductId = l_feqProductParams.getProductId();

			//税区分を取得する。
			TaxTypeEnum l_taxType = 
							WEB3TaxTypeSpecialDef.SPECIAL.equals(l_execInputInfo.taxType)?TaxTypeEnum.SPECIAL:TaxTypeEnum.NORMAL;

			//保有資産を取得する。
			Asset l_asset = 
					l_web3FeqPositionManager.getAsset(
							l_mainAccountRow.getAccountId(), 
							l_subAccount.getSubAccountId(),
							l_lngProductId, 
							l_taxType);
							
			if(l_asset == null){
				//保有資産に該当するデータがない場合、例外をスローする。
				log.error("保有資産該当データなし。");
				throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00204,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}else{
				if(!(l_asset.getQuantity() >= Long.parseLong(l_execInputInfo.execDetailInfoUnit.execQuantity))){
					//（保有資産の売付可能数量 >= 約定数量）でない場合、例外をスローする。
					log.error("保有資産残数量チェックエラー。");
					throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01931,
					this.getClass().getName() + "." + STR_METHOD_NAME);
				}
			}
		}
         
        //１３）　@約定入力情報.約定詳細.国内受渡日セット
        //　@受渡日が未入力の場合（約定入力情報.約定詳細.国内受渡日 == null）、
        //　@約定日の３営業日後をセットする。
        if (l_execInputInfo.execDetailInfoUnit.deliveryDate == null)
        {
            WEB3GentradeBizDate l_web3GenBizDate = 
                new WEB3GentradeBizDate(l_tsExecutionTimestamp);
            
            l_execInputInfo.execDetailInfoUnit.deliveryDate = l_web3GenBizDate.roll(3);
        }
        
        //１４）　@約定入力情報.現地受渡日のチェック
        //　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@－（未入力の場合）
        if (l_execInputInfo.localDeliveryDate == null)
        {
            l_execInputInfo.localDeliveryDate = 
                l_execInputInfo.execDetailInfoUnit.deliveryDate;
        }
         
        //１５）　@発注日のチェック
        //　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@－（未入力の場合）
        //　@　@当日（TradingSystem.getSystemTimestamp()の日付部分）をセットする。
        if (l_execInputInfo.orderBizDate == null)
        {
            l_execInputInfo.orderBizDate = 
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        }
        else
        {
            //　@－（入力がある場合）
            //　@　@当日（TradingSystem.getSystemTimestamp()の日付部分）の３営業日前を取得する。
            //　@　@３営業日前より前の日付であれば（当日の３営業日前 ＞ 発注日）、例外をスローする。
            WEB3GentradeBizDate l_web3GenBizDate
                = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            
            Date l_datBizDate3DayAfter = l_web3GenBizDate.calcFeqBizDate(
                l_institution.getInstitutionCode(),
                l_execInputInfo.marketCode,
                GtlUtils.getSystemTimestamp(),
                -3);
             
            if (WEB3DateUtility.compareToDay(
                l_datBizDate3DayAfter, 
                l_execInputInfo.orderBizDate) > 0) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02150,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "発注日が３営業日前より前の日付です。"); 
            }
        }
        
        //　@－発注日が営業日かチェックする。 
        //　@　@営業日でなければ、例外をスローする。
        String l_strFlag = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(l_execInputInfo.orderBizDate.getTime()));
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                " 発注日は営業日ではありません。" + l_execInputInfo.orderBizDate); 
        }
        
        //１６）　@システム自動セット項目のチェック
        //　@１６－１）　@入力値チェック
        //　@　@以下の項目に入力がある場合（nullでない場合）、例外をスローする。
        //　@　@約定入力情報.運用コード
        //　@　@約定入力情報.伝票番号
        //　@　@約定入力情報.注文数量
        //　@　@約定入力情報.注文単価
        if (!WEB3StringTypeUtility.isEmpty(l_execInputInfo.managementCode)
            || !WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderNumber)
            || !WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderQuantity)
            || !WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02151,
                this.getClass().getName() + STR_METHOD_NAME,
                "運用コード,伝票番号,注文数量,注文単価がシステム自動セット項目なので、入力不可です。"); 
        }
        else
        {
            //１６－２）　@値セット
            //以下の通り、約定入力情報のプロパティに値をセットする。
            //約定入力情報.運用コード：　@
            //外国株式運用コード採番サービス.get新規運用コード()
            WEB3FeqOrderEmpCodeManageService l_codeManageService = 
                (WEB3FeqOrderEmpCodeManageService) Services.getService(
                    WEB3FeqOrderEmpCodeManageService.class);
             
            //[get新規運用コード()に指定する引数] 
            //市場：　@外国株式銘柄.get市場()
            //発注日：　@約定入力情報.発注日
            l_execInputInfo.managementCode = l_codeManageService.getNewEmpCode(
                l_web3FeqProduct.getMarket(),
                l_execInputInfo.orderBizDate);
             
            //約定入力情報.識別コード：
            //注文識別コード採番サービス.get新規識別コード()
            //[get新規識別コード()に指定する引数]
            //証券会社コード：　@証券会社.getInstitutionCode()
            //部店コード：　@約定入力情報.部店コード
            //銘柄タイプ：　@銘柄タイプ.外国株式
            //注文識別コード採番サービス
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
             
            l_execInputInfo.requestNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_institution.getInstitutionCode(),
                l_execInputInfo.branchCode,
                ProductTypeEnum.FOREIGN_EQUITY);
             
            //約定入力情報.伝票番号：　@"9"(WebBroker)＋識別コードの下3桁
            l_execInputInfo.orderNumber = "9"
                + l_execInputInfo.requestNumber.substring(l_execInputInfo.requestNumber.length() - 3);
             
            //約定入力情報.注文数量：　@約定入力情報.約定詳細.約定数量
            l_execInputInfo.orderQuantity = 
                l_execInputInfo.execDetailInfoUnit.execQuantity;
            //約定入力情報.注文単価：　@約定入力情報.約定詳細.約定単価
            l_execInputInfo.orderPrice = 
                l_execInputInfo.execDetailInfoUnit.execPrice;
        }
        
        //１７） 約定No.の採番
        int l_intExecSerialNo = 1;
        l_execInputInfo.execDetailInfoUnit.execNo = 
            WEB3StringTypeUtility.formatNumber(l_intExecSerialNo, 3);
         
        //１８）　@金額項目のチェック
        //　@this.validate外国株式金額()をコールする。
        //　@[validate外国株式金額()に指定する引数]
        //　@証券会社コード：　@注文単位.証券会社コード
        //　@補助口座：　@外国株式クライアントリクエストサービス.get補助口座（顧客）
        //　@市場：　@外国株式銘柄.get市場()
        //　@通貨：　@外国株式銘柄.get通貨()
        //　@銘柄：　@外国株式銘柄
        //　@税区分：　@約定入力情報.特定口座区分に対応する税区分（TaxTypeEnum）
        //　@約定入力情報：　@約定入力情報
        validateFeqAmount(
            l_institution.getInstitutionCode(),
            l_subAccount,
            l_web3FeqProduct.getMarket(),
            l_web3FeqProduct.getCurrency(),
            l_web3FeqProduct,
            WEB3FeqOrderUtility.getTaxType(l_execInputInfo.taxType),
            l_execInputInfo);
                 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate外国株式金額)<BR>
     * 外国株式金額項目入力チェックを行う。<BR>
     * 以下に記述する有効範囲外の場合、例外をスローする。<BR>
     * <BR>
     * １）　@売買代金の計算<BR>
     * 　@以下の通り売買代金を計算し、値をセットする。<BR>
     * <BR>
     * 　@　@約定入力情報.約定詳細.売買代金：　@約定入力情報.約定詳細.約定単価 × <BR>
     * 約定入力情報.約定詳細.約定株数<BR>
     * <BR>
     * ２）　@現地諸経費のチェック<BR>
     * 　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場合）以下に示す有効桁数範囲内の数値であることをチェックする。<BR>
     * 　@　@約定入力情報.約定詳細.現地手数料<BR>
     * 　@　@　@整数部：　@9桁以内<BR>
     * 　@　@　@小数部：　@有効桁数(*1)以内<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02152<BR>
     * <BR>
     * 　@　@約定入力情報.約定詳細.現地取引税<BR>
     * 　@　@　@整数部：　@9桁以内<BR>
     * 　@　@　@小数部：　@有効桁数(*1)以内<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02153<BR>
     * <BR>
     * 　@　@約定入力情報.約定詳細.その他コスト１<BR>
     * 　@　@　@整数部：　@7桁以内<BR>
     * 　@　@　@小数部：　@有効桁数(*1)以内<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02154<BR>
     * <BR>
     * 　@　@約定入力情報.約定詳細.その他コスト２<BR>
     * 　@　@　@整数部：　@7桁以内<BR>
     * 　@　@　@小数部：　@有効桁数(*1)以内<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02155<BR>
     * <BR>　@　@
     * 　@－（未入力の場合）外国株式計算サービス.calc現地諸経費()にて各コストを計算し、<BR>
     * 約定入力情報.約定詳細の各項目にセットする。<BR>
     * <BR>
     * 　@　@[calc現地諸経費()に指定する引数]<BR>
     * 　@　@銘柄ID：　@銘柄.getProductId()<BR>
     * 　@　@現地銘柄コード：　@銘柄.get現地銘柄コード()<BR>
     * 　@　@証券会社コード：　@証券会社コード<BR>
     * 　@　@市場コード：　@約定入力情報.市場コード<BR>
     * 　@　@売買代金（外貨）：　@約定入力情報.約定詳細.売買代金<BR>
     * 　@　@基準日：　@約定入力情報.約定日<BR>
     * 　@　@is買付：　@（約定入力情報.売買区分 == ”買付”）の場合true、以外false。<BR>
     * <BR>　@　@
     * 　@(*1) 各有効桁数について<BR>
     * 　@海外諸経費マスタ行のうち、諸経費区分に該当する行より取得する。<BR>
     * 　@対応する諸経費区分の行がない場合は、チェックなし。<BR>
     * <BR>
     * 　@[海外諸経費マスタの取得]<BR>
     * 　@外国株式計算サービス.get海外諸経費マスタ()にて、海外諸経費マスタ行（Params）を<BR>
     * 取得する。<BR>
     * <BR>
     * 　@[get海外諸経費マスタ()に指定する引数]<BR>
     * 　@銘柄ID：　@銘柄.getProductId()<BR>
     * 　@現地銘柄コード：　@銘柄.get現地銘柄コード() <BR>
     * 　@証券会社コード：　@証券会社コード<BR>
     * 　@市場コード：　@約定入力情報.市場コード<BR>
     * 　@売買代金：　@約定入力情報.約定詳細.売買代金<BR>
     * 　@基準日：　@約定入力情報.約定詳細.約定日時<BR>
     * 　@　@※未入力の場合、当日（TradingSystem.getSystemTimestamp()の日付部分）<BR>
     * 　@諸経費区分：　@null<BR>
     * 　@売買区分：　@約定入力情報.売買区分<BR>
     * <BR>
     * ３）　@約定入力情報.約定詳細.清算代金の計算<BR>
     * 　@以下の通り、清算代金（外貨）／（円貨）を計算し、値をセットする。<BR>
     * <BR>
     * 　@約定入力情報.約定詳細.清算代金（外貨）：<BR>
     * 　@　@○　@買の場合（約定入力情報.売買区分 == ”買付”）<BR>
     * 　@　@　@現地清算代金（外貨） = <BR>
     * 売買代金（外貨）＋現地手数料＋現地取引税＋その他コスト１＋その他コスト２<BR>
     * <BR>
     * 　@　@○　@売の場合（約定入力情報.売買区分 == ”売付”）<BR>
     * 　@　@　@現地清算代金（外貨） = <BR>
     * 売買代金（外貨）－現地手数料－現地取引税－その他コスト１－その他コスト２<BR>
     * <BR>
     * 　@約定入力情報.約定詳細.清算代金（円貨）：<BR>
     * 　@　@外国株式計算サービス.calc円貨換算()にて清算代金（外貨）を邦貨換算した値。<BR>
     * <BR>
     * 　@　@[calc円貨換算()に指定する引数]<BR>
     * 　@　@金額（外貨）：　@約定入力情報.約定詳細.清算代金（外貨）<BR>
     * 　@　@レート：　@約定入力情報.約定為替レート<BR>
     * 　@　@円貨換算丸め方式：　@通貨.get円貨換算丸め方式()<BR>
     * <BR>
     * ４）　@約定入力情報.約定詳細.国内手数料（円貨）のチェック<BR>
     * 　@４－１）　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場合）以下に示す有効桁数範囲内の数値であることをチェックする。<BR>
     * 　@　@整数部：　@9桁以内<BR>
     * 　@　@小数部：　@なし<BR>
     * <BR>
     * 　@－（未入力の場合）外国株式計算サービス.calc委託手数料()にて計算し、<BR>
     * 約定入力情報.約定詳細の項目にセットする。<BR>
     * <BR>
     * 　@　@[calc委託手数料()に指定する引数]<BR>
     * 　@　@補助口座：　@補助口座<BR>
     * 　@　@市場：　@市場<BR>
     * 　@　@基準日：　@約定入力情報.約定詳細.約定日時<BR>
     * 　@　@is指値注文：　@（約定入力情報.注文単価区分 == ”指値”）の場合true、
     * <BR>以外false。<BR>
     * 　@　@現地清算代金（円貨）：　@約定入力情報.約定詳細.清算代金（円貨）<BR>
     * 　@　@注文チャネル： <BR>
     *   －HOST注文の場合（約定入力情報.注文IDが未入力） <BR>
     *      "営業店"をセット <BR>
     *   －上記以外の場合 <BR>
     *      約定入力情報.注文IDに該当する注文単位.初回注文の注文チャネルをセット。<BR>
     * <BR>
     * 　@４－２）　@約定入力情報.約定詳細.国内手数料（外貨）値セット<BR>
     * 　@　@以下の通り、国内手数料（外貨）を計算し、値をセットする。<BR>
     * <BR>
     * 　@　@約定入力情報.約定詳細.国内手数料（外貨）：<BR>
     * 　@　@　@外国株式計算サービス.calc外貨換算()にて国内手数料（円貨）を外貨換算した値。<BR>
     * <BR>
     * 　@　@　@[calc外貨換算()に指定する引数]<BR>
     * 　@　@　@金額（円貨）：　@約定入力情報.約定詳細.国内手数料（円貨）<BR>
     * 　@　@　@レート：　@約定入力情報.約定為替レート<BR>
     * 　@　@　@小数部桁数：　@通貨.get小数部桁数()<BR>
     * 　@　@　@外貨換算丸め方式：　@通貨.get外貨換算丸め方式()<BR>
     * <BR>
     * ５）　@約定入力情報.約定詳細.消費税（円貨）のチェック<BR>
     * 　@５－１）　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。<BR>
     * <BR>
     * 　@－（入力がある場合）以下に示す有効桁数範囲内の数値であることをチェックする。<BR>
     * 　@　@整数部：　@9桁以内<BR>
     * 　@　@小数部：　@なし<BR>
     * <BR>
     * 　@－（未入力の場合）外国株式計算サービス.calc消費税()にて計算し、<BR>
     * 約定入力情報.約定詳細の項目にセットする。<BR>
     * 　@　@[calc消費税()に指定する引数]<BR>
     * 　@　@金額：　@約定入力情報.約定詳細.国内手数料（円貨）<BR>
     * 　@　@基準日：　@約定入力情報.約定詳細.約定日時<BR>
     * 　@　@補助口座：　@補助口座<BR>
     * <BR>
     * 　@５－２）　@約定入力情報.約定詳細.消費税（外貨）値セット<BR>
     * 　@　@以下の通り、国内手数料（外貨）を計算し、値をセットする。<BR>
     * <BR>
     * 　@　@約定入力情報.約定詳細.国内手数料（外貨）：<BR>
     * 　@　@　@外国株式計算サービス.calc外貨換算()にて消費税（円貨）を外貨換算した値。<BR>
     * <BR>
     * 　@　@　@[calc外貨換算()に指定する引数]<BR>
     * 　@　@　@金額（円貨）：　@約定入力情報.約定詳細.消費税（円貨）<BR>
     * 　@　@　@レート：　@約定入力情報.約定為替レート<BR>
     * 　@　@　@小数部桁数：　@通貨.get小数部桁数()<BR>
     * 　@　@　@外貨換算丸め方式：　@通貨.get外貨換算丸め方式()<BR>
     * <BR>
     * ６）　@受渡代金の計算<BR>
     * 　@以下の通り、受渡代金（外貨）／（円貨）を計算し、値をセットする。<BR>
     * <BR>
     * 　@約定入力情報.約定詳細.受渡代金（円貨）：<BR>
     * 　@　@○　@買の場合（約定入力情報.売買区分 == ”買付”）<BR>
     * 　@　@　@受渡代金（円貨） = 清算代金（円貨）＋国内手数料（円貨）＋消費税（円貨）<BR>
     * <BR>
     * 　@　@○　@売の場合（約定入力情報.売買区分 == ”売付”）<BR>
     * 　@　@　@受渡代金（円貨） = 清算代金（円貨）－国内手数料（円貨）－消費税（円貨）<BR>
     * <BR>
     * 　@約定入力情報.約定詳細.受渡代金（外貨）：<BR>
     * 　@　@○　@買の場合（約定入力情報.売買区分 == ”買付”）<BR>
     * 　@　@　@受渡代金（外貨） = 清算代金（外貨）＋国内手数料（外貨）＋消費税（外貨）<BR>
     * <BR>
     * 　@　@○　@売の場合（約定入力情報.売買区分 == ”売付”）<BR>
     * 　@　@　@受渡代金（外貨） = 清算代金（外貨）－国内手数料（外貨）－消費税（外貨）<BR>
     * <BR>
     * ７）　@譲渡益／譲渡益税の計算<BR>
     * 　@以下の通り、譲渡益／譲渡益税を計算し、値をセットする。<BR>
     * <BR>
     * 　@○　@買の場合（約定入力情報.売買区分 == ”買付”）<BR>
     * 　@　@約定入力情報.譲渡益：　@0<BR>
     * 　@　@約定入力情報.譲渡益税：　@0<BR>
     * <BR>
     * 　@○　@売の場合（約定入力情報.売買区分 == ”売付”）、<BR>
     * 　@　@以下の計算結果。<BR>
     * <BR>
     * 　@　@約定入力情報.譲渡益：<BR>
     * 　@　@　@外国株式計算サービス.calc譲渡損益()にて計算した値。<BR>
     * <BR>
     * 　@　@　@[calc譲渡損益()に指定する引数]<BR>
     * 　@　@　@売買代金（円貨）：　@約定入力情報.約定詳細.受渡代金（円貨）<BR>
     * 　@　@　@売株数：　@約定入力情報.約定詳細.約定数量<BR>
     * 　@　@　@銘柄ＩＤ：　@銘柄.getProductId()<BR>
     * 　@　@　@補助口座：　@補助口座<BR>
     * 　@　@　@税区分：　@税区分<BR>
     * <BR>
     * 　@　@約定入力情報.譲渡益税：<BR>
     * 　@　@　@外国株式計算サービス.calc譲渡益税()にて計算した値。<BR>
     * <BR>
     * 　@　@　@[calc譲渡益税()に指定する引数]<BR>
     * 　@　@　@補助口座：　@補助口座<BR>
     * 　@　@　@税区分：　@税区分<BR>
     * 　@　@　@売買代金（円貨）：　@約定入力情報.譲渡益<BR>
     * 　@　@　@受渡日：　@約定入力情報.約定詳細.国内受渡日<BR>
     * 　@
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_market - (市場)<BR>
     * 市場オブジェクト<BR>
     * @@param l_currency - (通貨)<BR>
     * 通貨オブジェクト<BR>
     * @@param l_product - (銘柄)<BR>
     * 銘柄オブジェクト<BR>
     * @@param l_taxType - (税区分)<BR>
     * 税区分<BR>
     * @@param l_execInputInfo - (約定入力情報)<BR>
     * 外国株式約定入力情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B66A3F009C
     */
    private void validateFeqAmount(
        String l_strInstitutionCode, 
        WEB3GentradeSubAccount l_subAccount, 
        WEB3GentradeMarket l_market, 
        WEB3GentradeCurrency l_currency, 
        WEB3FeqProduct l_product, 
        TaxTypeEnum l_taxType, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateFeqAmount(String, " +
            "WEB3GentradeSubAccount, WEB3GentradeCurrency, WEB3FeqProduct, " + 
            "TaxTypeEnum, WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || 
            l_market == null ||
            l_product == null || 
            l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメーターが未指定(null)です。");  
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //１）　@売買代金の計算
        //以下の通り売買代金を計算し、値をセットする。
        //約定入力情報.約定詳細.売買代金：　@約定入力情報.約定詳細.約定単価 × 約定入力情報.約定詳細.約定株数
        double l_dblTradePrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice) * Double.parseDouble(
                l_execInputInfo.execDetailInfoUnit.execQuantity);
        
        int l_intDecimalPlace = l_currency.getScale();
        BigDecimal l_bdTradePrice = new BigDecimal(l_dblTradePrice);
        l_bdTradePrice =
            l_bdTradePrice.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblTradePrice = l_bdTradePrice.doubleValue();

        l_execInputInfo.execDetailInfoUnit.foreignTradePrice = 
            WEB3StringTypeUtility.formatNumber(l_dblTradePrice);
        
        //現地手数料
        String l_strLocalCommission = 
            l_execInputInfo.execDetailInfoUnit.localCommission;
        //現地取引税
        String l_strLocalTradingTax = 
            l_execInputInfo.execDetailInfoUnit.localTradingTax;        
        //その他コスト１
        String l_strOtherCost1 = 
            l_execInputInfo.execDetailInfoUnit.otherCost1;
        //その他コスト２
        String l_strOtherCost2 = 
            l_execInputInfo.execDetailInfoUnit.otherCost2;
        //約定為替レート
        double l_dblExecExchangeRate = 
            Double.parseDouble(l_execInputInfo.execExchangeRate);
        
        double l_dblFDoubleoreignTradePrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice);
        
        //２）　@現地諸経費のチェック
        //　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@入力がない場合は外国株式計算サービス.calc現地諸経費()にて各コストを計算し、
        //　@約定入力情報.約定詳細の各項目にセットする。
        //
        //約定入力情報.約定詳細の各項目にセットする。
        //　@　@[calc現地諸経費()に指定する引数]
        //    銘柄ID：　@銘柄.getProductId()
        //    証券会社コード：　@証券会社コード
        //    市場コード：　@約定入力情報.市場コード
        //    売買代金（外貨）：　@約定入力情報.約定詳細.売買代金
        //    基準日：　@約定入力情報.約定日
        //    is買付：　@（約定入力情報.売買区分 == ”買付”）の場合true、以外false。
        //現地諸経費
        WEB3FeqForeignCost l_web3FeqBalanceCost =  
            l_bizLogicProvider.calcForeignCost(
                new Long(l_product.getProductId()),
                l_product.getOffshoreProductCode(),
                l_strInstitutionCode,
                l_market.getMarketCode(),
                l_dblFDoubleoreignTradePrice,
                l_execInputInfo.execDetailInfoUnit.executionTimestamp,
                (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType) ? true : false));            


        //有効桁数を取得
        //　@(*1) 各有効桁数について 
        //海外諸経費マスタ行のうち、諸経費区分に該当する行より取得する。 
        //対応する諸経費区分の行がない場合は、チェックなし。
        //　@[海外諸経費マスタの取得] 
        //外国株式計算サービス.get海外諸経費マスタ()にて、
        // 海外諸経費マスタ行（Params）を取得する。                        
        Date l_datBaseDate = 
            l_execInputInfo.execDetailInfoUnit.executionTimestamp;
        if (l_datBaseDate == null)
        {
            l_datBaseDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        }
            
        //[get海外諸経費マスタ()に指定する引数] 
        //銘柄ID：　@銘柄.getProductId()<BR>
        //証券会社コード：　@証券会社コード 
        //市場コード：　@約定入力情報.市場コード 
        //売買代金：　@約定入力情報.約定詳細.売買代金 
        //基準日：　@約定入力情報.約定詳細.約定日時 
        //　@※未入力の場合、当日（TradingSystem.getSystemTimestamp()の日付部分） 
        //諸経費区分：　@null 
        //売買区分：　@約定入力情報.売買区分
        ForeignCostParams[] l_foreignCostParams  = 
            l_bizLogicProvider.getForeignCost(
                new Long(l_product.getProductId()),
                l_product.getOffshoreProductCode(),
                l_strInstitutionCode,
                l_execInputInfo.marketCode,
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice),
                l_datBaseDate,
                null,
                l_execInputInfo.dealingType);
            
        //現地手数料の有効桁数
        int l_intScaleLocalCommission = 0;
        boolean l_blnIsCheckLocalCommission = false;
            
        //現地取引税の有効桁数
        int l_intScaleLocalTradingTax = 0;
        boolean l_blnIsCheckLocalTradingTax = false;
            
        //その他コスト１の有効桁数
        int l_intScaleOtherCost1 = 0;     
        boolean l_blnIsCheckOtherCost1 = false;
            
        //その他コスト２の有効桁数
        int l_intScaleOtherCost2 = 0;
        boolean l_blnIsCheckOtherCost2 = false;            
            
        //外国株式計算サービス.get海外諸経費マスタ()の戻る値をloop
        if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
        {
            for (int i = 0; i < l_foreignCostParams.length; i++)
            {
                if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //諸経費区分が"現地手数料"のデータがあるの場合。
                    l_intScaleLocalCommission = l_foreignCostParams[i].getScale();
                    l_blnIsCheckLocalCommission = true;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //諸経費区分が"現地取引税"のデータがあるの場合。
                    l_intScaleLocalTradingTax = l_foreignCostParams[i].getScale();
                    l_blnIsCheckLocalTradingTax = true;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //諸経費区分が"その他コスト１"のデータがあるの場合。
                    l_intScaleOtherCost1 = l_foreignCostParams[i].getScale();
                    l_blnIsCheckOtherCost1 = true;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    //諸経費区分が"その他コスト１"のデータがあるの場合。
                    l_intScaleOtherCost2 = l_foreignCostParams[i].getScale();
                    l_blnIsCheckOtherCost2 = true;
                }
            }
        }            

        //　@　@約定入力情報.約定詳細.現地手数料
        //　@　@　@整数部：　@9桁以内
        //　@　@　@小数部：　@有効桁数(*1)以内        
        if (l_execInputInfo.execDetailInfoUnit.localCommission != null)
        {
            if (WEB3StringTypeUtility.getIntegerDigits(l_strLocalCommission) > 9
                || (l_blnIsCheckLocalCommission && 
                    WEB3StringTypeUtility.getFractionDigits(l_strLocalCommission) >  
                        l_intScaleLocalCommission))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02152,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "現地手数料が有効桁数範囲外です。"); 
            }
        }
        else
        {
            //約定入力情報.約定詳細.現地手数料にセットする。
            l_execInputInfo.execDetailInfoUnit.localCommission = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignCommissionFee());

            //現地手数料
            l_strLocalCommission = 
                l_execInputInfo.execDetailInfoUnit.localCommission;
        }

        if (l_execInputInfo.execDetailInfoUnit.localTradingTax != null)
        {
            //　@　@約定入力情報.約定詳細.現地取引税
            //　@　@　@整数部：　@9桁以内
            //　@　@　@小数部：　@有効桁数(*1)以内
            if (l_execInputInfo.execDetailInfoUnit.localTradingTax != null)
            {
                if (WEB3StringTypeUtility.getIntegerDigits(l_strLocalTradingTax) > 9
                    || (l_blnIsCheckLocalTradingTax && 
                        WEB3StringTypeUtility.getFractionDigits(l_strLocalTradingTax) > 
                            l_intScaleLocalTradingTax))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02153,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "現地取引税が有効桁数範囲外です。"); 
                }
            }            
        }
        else
        {
            //約定入力情報.約定詳細.現地取引税にセットする。
            l_execInputInfo.execDetailInfoUnit.localTradingTax = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignTax());

            //現地取引税
            l_strLocalTradingTax = 
                l_execInputInfo.execDetailInfoUnit.localTradingTax;
        }
        
        //　@　@約定入力情報.約定詳細.その他コスト１
        //　@　@　@整数部：　@7桁以内
        //　@　@　@小数部：　@有効桁数(*1)以内
        if (l_execInputInfo.execDetailInfoUnit.otherCost1 != null)
        {                
            if (WEB3StringTypeUtility.getIntegerDigits(l_strOtherCost1) > 7
                || (l_blnIsCheckOtherCost1 && 
                    WEB3StringTypeUtility.getFractionDigits(l_strOtherCost1) > 
                        l_intScaleOtherCost1))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02154,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "その他コスト１が有効桁数範囲外です。"); 
            }
        }
        else
        {
            //約定入力情報.約定詳細.現地取引税にセットする。
            l_execInputInfo.execDetailInfoUnit.otherCost1 = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignFeeExt1());

            //その他コスト１
            l_strOtherCost1 = 
                l_execInputInfo.execDetailInfoUnit.otherCost1;
        }
            
        //　@　@約定入力情報.約定詳細.その他コスト２
        //　@　@　@整数部：　@7桁以内
        //　@　@　@小数部：　@有効桁数(*1)以内
        if (l_execInputInfo.execDetailInfoUnit.otherCost2 != null)
        {                
            if (WEB3StringTypeUtility.getIntegerDigits(l_strOtherCost2) > 7
                || (l_blnIsCheckOtherCost2 && 
                    WEB3StringTypeUtility.getFractionDigits(l_strOtherCost2) > 
                        l_intScaleOtherCost2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02155,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "その他コスト２が有効桁数範囲外です。"); 
            }
        }
        else
        {
            //約定入力情報.約定詳細.現地取引税にセットする。
            l_execInputInfo.execDetailInfoUnit.otherCost2 = 
                WEB3StringTypeUtility.formatNumber(l_web3FeqBalanceCost.getForeignFeeExt2());

            //その他コスト２
            l_strOtherCost2 = 
                l_execInputInfo.execDetailInfoUnit.otherCost2;
        }


        //現地手数料
        double l_dblLocalCommission = 0.0D;
        if (l_strLocalCommission != null)
        {
            l_dblLocalCommission = Double.parseDouble(l_strLocalCommission);
        }
        
        //現地取引税
        double l_dblLocalTradingTax = 0.0D;
        if (l_strLocalTradingTax != null)
        {
            l_dblLocalTradingTax = Double.parseDouble(l_strLocalTradingTax);
        }
        
        //その他コスト１
        double l_dblOtherCost1 = 0.0D;
        if (l_strOtherCost1 != null)
        {
            l_dblOtherCost1 = Double.parseDouble(l_strOtherCost1);
        }
        
        //その他コスト２
        double l_dblOtherCost2 = 0.0D;
        if (l_strOtherCost2 != null)
        {
            l_dblOtherCost2 = Double.parseDouble(l_strOtherCost2);
        }
        
        //３）　@約定入力情報.約定詳細.清算代金の計算
        //　@以下の通り、清算代金（外貨）/（円貨）を計算し、値をセットする。
        //　@約定入力情報.約定詳細.清算代金（外貨）：
        double l_foreignClearUpPrice = 0.0D;
        //○　@買の場合（約定入力情報.売買区分 == ”買付”）
        //　@　@現地清算代金（外貨） = 売買代金（外貨）＋現地手数料＋現地取引税＋その他コスト１＋その他コスト２
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblFDoubleoreignTradePrice,l_dblLocalCommission);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_foreignClearUpPrice,l_dblLocalTradingTax);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_foreignClearUpPrice,l_dblOtherCost1);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalPlus(l_foreignClearUpPrice,l_dblOtherCost2);
        }
        else
        {
            //○　@売の場合（約定入力情報.売買区分 == ”売付”）
            //　@　@現地清算代金（外貨） = 売買代金（外貨）－現地手数料－現地取引税 －その他コスト１－その他コスト２
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblFDoubleoreignTradePrice,l_dblLocalCommission);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_foreignClearUpPrice,l_dblLocalTradingTax);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_foreignClearUpPrice,l_dblOtherCost1);
                
            l_foreignClearUpPrice =
                WEB3FeqOrderUtility.decimalMinus(l_foreignClearUpPrice,l_dblOtherCost2);
        }
        //　@約定入力情報.約定詳細.清算代金（外貨） 
        l_execInputInfo.execDetailInfoUnit.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_foreignClearUpPrice);
        
        //　@約定入力情報.約定詳細.清算代金（円貨）：
        //　@　@外国株式計算サービス.calc円貨換算()にて清算代金（外貨）を邦貨換算した値。
        //　@　@[calc円貨換算()に指定する引数]
        //    金額(外貨):約定入力情報.約定詳細.清算代金（外貨）
        //    レート：　@約定入力情報.約定為替レート
        //    円貨換算丸め方式：　@通貨.get円貨換算丸め方式()
        double l_dblClearUpPrice = 
            l_bizLogicProvider.calcJPYAmount(
                l_foreignClearUpPrice,
                l_dblExecExchangeRate,
                l_currency.getChangeJpyRoundDiv());        
        l_execInputInfo.execDetailInfoUnit.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblClearUpPrice);
        
        //４）　@約定入力情報.約定詳細.国内手数料（円貨）のチェック
        //　@４－１）　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        //　@－（入力がある場合）以下に示す有効桁数範囲内の数値であることをチェックする。
        //　@　@整数部：　@9桁以内
        //　@　@小数部：　@なし
        if  (l_execInputInfo.execDetailInfoUnit.commission != null)
        {
            String l_strCommission = l_execInputInfo.execDetailInfoUnit.commission;
            //有効数値チェック
            if (!WEB3StringTypeUtility.isInteger(l_strCommission) 
                || WEB3StringTypeUtility.getIntegerDigits(l_strCommission) > 9)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02189,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "国内手数料（円貨）が有効桁数範囲外です。"); 
            }
        }
        else        
        {
            //－（未入力の場合）外国株式計算サービス.calc委託手数料()にて計算し、
            //約定入力情報.約定詳細の項目にセットする。
            
            //[calc委託手数料()に指定する引数] 
            //補助口座：　@補助口座
            //市場：　@市場
            //基準日：　@約定入力情報.約定詳細.約定日時
            //is買付：　@（約定入力情報.売買区分 == ”買付”）の場合、true、以外false。 
            //is指値注文：（約定入力情報.注文単価区分 == ”指値”）の場合true、以外false。
            //現地清算代金（円貨）：　@約定入力情報.約定詳細.清算代金（円貨） 
            //注文チャネル： 
            // －HOST注文の場合（約定入力情報.注文IDが未入力） 
            // "営業店"をセット 
            // －上記以外の場合 
            // 約定入力情報.注文IDに該当する注文単位.初回注文の注文チャネルをセット。  
            String l_strOrderChannel = null;
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderId))
            {
                l_strOrderChannel = WEB3ChannelDef.BRANCH;
            }
            else
            {
                WEB3FeqOrderManager l_orderMgr = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();                
                
                WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_orderMgr.getOrderUnitByOrderId(
                    Long.parseLong(l_execInputInfo.orderId));
                    
                if (l_orderUnit == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         this.getClass().getName() + STR_METHOD_NAME,
                        "注文単位が存在しない。");
                }
                
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                
                if (l_orderUnitRow == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         this.getClass().getName() + STR_METHOD_NAME,
                        "注文単位行が存在しない。");
                }
                
                l_strOrderChannel = l_orderUnitRow.getOrderChanel();
            }
            WEB3GentradeCommission l_web3GentradeCommission = 
                l_bizLogicProvider.calcCommission(
                    l_subAccount,
                    l_market,
                    l_execInputInfo.execDetailInfoUnit.executionTimestamp,
                    (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType)
                        ? true : false),
                    (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(
                        l_execInputInfo.orderPriceDiv) ? true : false),
                    l_dblClearUpPrice,
                    l_strOrderChannel);
            
            l_execInputInfo.execDetailInfoUnit.commission = 
                WEB3StringTypeUtility.formatNumber(l_web3GentradeCommission.getCommission());
        }
        
        //４－２）　@約定入力情報.約定詳細.国内手数料（外貨）値セット
        //以下の通り、国内手数料（外貨）を計算し、値をセットする。
        //約定入力情報.約定詳細.国内手数料（外貨）：
        //外国株式計算サービス.calc外貨換算()にて国内手数料（円貨）を外貨換算した値。
        //
        //　@[calc外貨換算()に指定する引数] 
        //金額（円貨）：約定入力情報.約定詳細.国内手数料（円貨）
        //レート：　@約定入力情報.約定為替レート
        //小数部桁数：　@通貨.get小数部桁数()
        //外貨換算丸め方式：　@通貨.get外貨換算丸め方式()
        
        double l_dblForeignCommission = 
            l_bizLogicProvider.calcForeignCCYAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission),
                l_dblExecExchangeRate,
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
        
        l_execInputInfo.execDetailInfoUnit.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_dblForeignCommission);
        
        //５）　@約定入力情報.約定詳細.消費税（円貨）のチェック
        //　@５－１）　@入力がある場合は入力値のチェック、未入力の場合は値セットを行う。
        if (l_execInputInfo.execDetailInfoUnit.consumptionTax != null)
        {
            //　@－（入力がある場合）以下に示す有効桁数範囲内の数値であることをチェックする。
            //　@　@整数部：　@9桁以内
            //　@　@小数部：　@なし
            String l_strConsumptionTax = l_execInputInfo.execDetailInfoUnit.consumptionTax;
            
            //有効数値チェック
            if (!WEB3StringTypeUtility.isInteger(l_strConsumptionTax) 
                || WEB3StringTypeUtility.getIntegerDigits(l_strConsumptionTax) > 9)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02190,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "消費税（円貨）が有効桁数範囲外です。"); 
            }
        }
        else
        {
            //　@－（未入力の場合）外国株式計算サービス.calc消費税()にて計算し、
            //約定入力情報.約定詳細の項目にセットする。
            
            //[calc消費税()に指定する引数] 
            //金額：約定入力情報.約定詳細.国内手数料（円貨）
            //基準日：約定入力情報.約定詳細.約定日時
            //補助口座：補助口座
            double l_dblConsumptionTax = 
                l_bizLogicProvider.calcSalesTax(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission),
                    l_execInputInfo.execDetailInfoUnit.executionTimestamp,
                    l_subAccount);
            
            l_execInputInfo.execDetailInfoUnit.consumptionTax = 
                WEB3StringTypeUtility.formatNumber(l_dblConsumptionTax);
        }
        
        //　@５－２）　@約定入力情報.約定詳細.消費税（外貨）値セット
        //　@　@以下の通り、国内手数料（外貨）を計算し、値をセットする。
        //　@　@　@外国株式計算サービス.calc外貨換算()にて消費税（円貨）を外貨換算した値。
        //      金額（円貨）：約定入力情報.約定詳細.消費税（円貨）
        //      レート：約定入力情報.約定為替レート
        //      小数部桁数：通貨.get小数部桁数()
        //      外貨換算丸め方式：通貨.get外貨換算丸め方式()
        double l_dblForeignConsumptionTax
        = l_bizLogicProvider.calcForeignCCYAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.consumptionTax),
                l_dblExecExchangeRate,
                l_currency.getScale(),
                l_currency.getChangeFCcyRoundDiv());
        
        //約定入力情報.約定詳細.国内手数料（外貨）：
        l_execInputInfo.execDetailInfoUnit.foreignConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_dblForeignConsumptionTax);
        
        //６）　@受渡代金の計算
        //　@以下の通り、受渡代金（外貨）／（円貨）を計算し、値をセットする。
        //　@約定入力情報.約定詳細.受渡代金（円貨）：
        //　@　@○　@買の場合（約定入力情報.売買区分 == ”買付”）
        //　@　@　@受渡代金（円貨） = 清算代金（円貨）＋国内手数料（円貨）＋消費税（円貨）
        //　@　@○　@売の場合（約定入力情報.売買区分 == ”売付”）
        //　@　@　@受渡代金（円貨） = 清算代金（円貨）－国内手数料（円貨）－消費税（円貨）
        double l_dblDeliveryPrice = 0.0D;
        
        //清算代金（円貨）
        l_dblClearUpPrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.clearUpPrice);
        //国内手数料（円貨）
        double l_dblCommission = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission);
        //消費税（円貨）
        double l_dblConsumptionTax = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.consumptionTax);
        
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            //買の場合
            //受渡代金（円貨） = 清算代金（円貨）＋国内手数料（円貨）＋消費税（円貨）
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblClearUpPrice,l_dblCommission);
                
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblDeliveryPrice,l_dblConsumptionTax);
        }
        else
        {
            //売の場合
            //受渡代金（円貨） = 清算代金（円貨）－国内手数料（円貨）－消費税（円貨）
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblClearUpPrice,l_dblCommission);
            
            l_dblDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblDeliveryPrice,l_dblConsumptionTax);
        }
        l_execInputInfo.execDetailInfoUnit.deliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblDeliveryPrice);
        
        //　@約定入力情報.約定詳細.受渡代金（外貨）：
        //　@　@○　@買の場合（約定入力情報.売買区分 == ”買付”）
        //　@　@　@受渡代金（外貨） = 清算代金（外貨）＋国内手数料（外貨）＋消費税（外貨）
        //
        //　@　@○　@売の場合（約定入力情報.売買区分 == ”売付”）
        //　@　@　@受渡代金（外貨） = 清算代金（外貨）－国内手数料（外貨）－消費税（外貨）        
        double l_dblForeignDeliveryPrice = 0.0D;
        
        //清算代金（外貨）
        double l_dblForeignClearUpPrice = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignClearUpPrice);
        //国内手数料（外貨）
        l_dblForeignCommission = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignCommission);
        //消費税（外貨）
        l_dblForeignConsumptionTax = 
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignConsumptionTax);
        
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            //買の場合
            //受渡代金（外貨） = 清算代金（外貨）＋国内手数料（外貨）＋消費税（外貨）
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblForeignClearUpPrice,l_dblForeignCommission);
                
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalPlus(l_dblForeignDeliveryPrice,l_dblForeignConsumptionTax);
        }
        else
        {
            //売の場合
            //受渡代金（外貨） = 清算代金（外貨）－国内手数料（外貨）－消費税（外貨）
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblForeignClearUpPrice,l_dblForeignCommission);
                
            l_dblForeignDeliveryPrice =
                WEB3FeqOrderUtility.decimalMinus(l_dblForeignDeliveryPrice,l_dblForeignConsumptionTax);
        }
        //約定入力情報.約定詳細.受渡代金（外貨）
        l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblForeignDeliveryPrice);
        
        //７）　@譲渡益／譲渡益税の計算
        //　@以下の通り、譲渡益／譲渡益税を計算し、値をセットする。
        //　@○　@買の場合（約定入力情報.売買区分 == ”買付”）
        if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
        {
            //約定入力情報.譲渡益：　@0
            l_execInputInfo.passProfit = "0";
            //約定入力情報.譲渡益税：　@0
            l_execInputInfo.passProfitTax = "0";
        }
        else
        {
            //　@○　@売の場合（約定入力情報.売買区分 == ”売付”）、
            //　@　@以下の計算結果。
            
            //　@　@約定入力情報.約定詳細.譲渡益：
            //　@　@　@外国株式計算サービス.calc譲渡損益()にて計算した値。
            //      売買代金（円貨）：約定入力情報.約定詳細.受渡代金（円貨）
            //      売株数：約定入力情報.約定詳細.約定数量
            //      銘柄ＩＤ：銘柄.getProductId()
            //      補助口座：補助口座
            //      税区分：税区分
            double l_dblCapitalProfitLoss = 
                l_bizLogicProvider.calcCapitalProfitLoss(
                    l_dblDeliveryPrice,
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity),
                    l_product.getProductId(),
                    l_subAccount,
                    l_taxType);
            
            l_execInputInfo.passProfit = 
                WEB3StringTypeUtility.formatNumber(l_dblCapitalProfitLoss);
            
            //　@　@約定入力情報.約定詳細.譲渡益税：
            //　@　@　@外国株式計算サービス.calc譲渡益税()にて計算した値。
            //      補助口座：補助口座
            //      税区分：税区分
            //      売買代金（円貨）：約定入力情報.約定詳細.譲渡益
            //      受渡日：約定入力情報.約定詳細.国内受渡日
            double l_dblCapitalGainTax = 
                l_bizLogicProvider.calcCapitalGainTax(
                    l_subAccount,
                    l_taxType,
                    Double.parseDouble(l_execInputInfo.passProfit),
                    l_execInputInfo.execDetailInfoUnit.deliveryDate);
            
            l_execInputInfo.passProfitTax = 
                WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTax);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (persistHOST注文)<BR>
     * 注文情報をDBに更新する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（(管)約定入力）persistHOST注文」参照。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_execInputInfo - (約定入力情報)<BR>
     * 外国株式約定入力情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7AB0C0075
     */
    private void persistHostOrder(
        WEB3GentradeInstitution l_institution,
        String l_strUpdaterCode,
        WEB3FeqOrderAndExecutionUnit l_execInputInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " persistHostOrder(WEB3GentradeInstitution," + 
            " String, WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメーターが未指定(null)です。");  
        }        
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        WEB3GentradeAccountManager l_accManage = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.1 顧客オブジェクトを取得  拡張アカウントマネージャ.get顧客
        //[get顧客()に指定する引数] 
        //証券会社コード：　@証券会社.getInstitutionCode()
        //部店コード：　@約定入力情報.部店コード
        //口座コード：　@約定入力情報.顧客コード
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_accManage.getMainAccount(
            l_institution.getInstitutionCode(),
            l_execInputInfo.branchCode,
            l_execInputInfo.accountCode);

        //1.2 get補助口座
        //[get補助口座()に指定する引数] 
        //顧客：　@get顧客() 
        SubAccount l_subAccount = 
            WEB3FeqClientRequestService.getSubAccount(l_mainAccount);

        WEB3FeqProductManager l_web3FeqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        //1.3 get外国株式銘柄        
        //[get外国株式銘柄()に指定する引数] 
        //証券会社：　@証券会社
        //銘柄コード：　@約定入力情報.銘柄コード。未入力の場合は現地銘柄コード。
        WEB3FeqProduct l_web3FeqProduct = null;
        try
        {
            l_web3FeqProduct = 
                (WEB3FeqProduct) l_web3FeqProductManager.getFeqProduct(
                   l_institution,
                   WEB3StringTypeUtility.isEmpty(l_execInputInfo.productCode) ? 
                       l_execInputInfo.localProductCode : l_execInputInfo.productCode);
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コード入力が不正。");
        }
               
        WEB3FeqBizLogicProvider l_bizLogicProvider =
           (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //現在日時
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        
        //1.4 注文（ﾍｯﾀﾞ）行オブジェクト
        FeqOrderParams l_feqOrderParams = new FeqOrderParams();

        QueryProcessor l_queryProcessor;
        //1.6 doInsertQuery
        try 
        {
            //1.5 注文（ﾍｯﾀﾞ）行オブジェクトに値をセット
            
            //注文ＩＤ:（自動採番）
            l_feqOrderParams.setOrderId(l_orderManager.createNewOrderId());
            //口座ＩＤ:補助口座.口座ＩＤ
            l_feqOrderParams.setAccountId(l_subAccount.getAccountId());
            //補助口座ＩＤ:補助口座.補助口座ＩＤ
            l_feqOrderParams.setSubAccountId(l_subAccount.getSubAccountId());
            //銘柄タイプ:4：外国株（ProductTypeEnumにて定義）
            l_feqOrderParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            //更新者コード:管理者コード
            l_feqOrderParams.setLastUpdater(l_strUpdaterCode);
            //作成日付:現在日時
            l_feqOrderParams.setCreatedTimestamp(l_tsSystemTimestamp);
            //更新日付:現在日時
            l_feqOrderParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_feqOrderParams);        
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7 注文単位行オブジェクトを生成
        FeqOrderUnitParams l_feqOrderUnitParams = new FeqOrderUnitParams();
                
        //1.9 doInsertQuery
        try 
        {            
            //1.8 注文単位行オブジェクトに値をセット
            //注文単位ＩＤ: （自動採番）
            l_feqOrderUnitParams.setOrderUnitId(l_orderManager.createNewOrderUnitId());
            
            //証券会社コード   :証券会社コード
            l_feqOrderUnitParams.setInstitutionCode(l_institution.getInstitutionCode());
            
            //口座ＩＤ :補助口座.getAccountId()
            l_feqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
            
            //補助口座ＩＤ :補助口座.getSubAccountId()
            l_feqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
            
            //部店ＩＤ :補助口座.getBranch().getBranchId()
            l_feqOrderUnitParams.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            
            //取引者ＩＤ :null
            l_feqOrderUnitParams.setTraderId(null);
            
            //注文ＩＤ :注文.注文ID
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            
            //注文種別 :約定入力情報.売買区分 == ”買付”の場合、”701：外株買い” 
            //         ”売付”の場合、”702：外株売り”
            if (WEB3BuySellTypeDef.BUY.equals(l_execInputInfo.dealingType))
            {
                l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_BUY);
            } 
            else if (WEB3BuySellTypeDef.SELL.equals(l_execInputInfo.dealingType))
            {
                l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_SELL);
            }
            
            //注文カテゴリ :1：現物注文（OrderCategEnumにて定義）
            l_feqOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            
            //注文履歴最終通番 :1
            l_feqOrderUnitParams.setLastOrderActionSerialNo(1);
            
            //約定最終通番 :1
            l_feqOrderUnitParams.setLastExecutionSerialNo(1);
            
            //銘柄タイプ :4：外国株（ProductTypeEnumにて定義）
            l_feqOrderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            
            //市場ＩＤ :外国株式銘柄.get市場().市場ID
            l_feqOrderUnitParams.setMarketId(l_web3FeqProduct.getMarket().getMarketId());
            
            //注文数量 :約定入力情報.約定詳細.約定数量
            l_feqOrderUnitParams.setQuantity(Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
            
            //指値 :約定入力情報.注文単価区分 == ”成行”の場合0。
            //以外、約定入力情報.約定詳細.約定単価。
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_execInputInfo.orderPriceDiv))
            {
                l_feqOrderUnitParams.setLimitPrice(0);
            } 
            else
            {
                l_feqOrderUnitParams.setLimitPrice(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
            }
            
            //執行条件 :FeqExecutionConditionType.NONE(1:条件なし)
            l_feqOrderUnitParams.setExecutionConditionType(
                FeqExecutionConditionType.NONE);
            
            //発注条件 :約定入力情報.発注条件
            l_feqOrderUnitParams.setOrderConditionType(l_execInputInfo.orderCondType);
            
            //発注条件演算子 :約定入力情報.発注条件演算子
            l_feqOrderUnitParams.setOrderCondOperator(l_execInputInfo.condOperator);
            
            //逆指値基準値 :約定入力情報.発注条件単価
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.orderCondPrice))
            {
                l_feqOrderUnitParams.setStopOrderPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setStopOrderPrice(
                    Double.parseDouble(l_execInputInfo.orderCondPrice));
            }
            
            //（W指値）訂正指値 :（約定入力情報.W指値用注文単価区分 == ”成行”）の場合、0。
            //以外、約定入力情報.W指値用注文単価。
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_execInputInfo.wLimitOrderPriceDiv))
            {
                l_feqOrderUnitParams.setWLimitPrice(0);
            } 
            else
            {
                if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.wLimitPrice))
                {
                    l_feqOrderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_feqOrderUnitParams.setWLimitPrice(
                        Double.parseDouble(l_execInputInfo.wLimitPrice));
                }
            }
            
            //決済区分 :"約定入力情報.決済区分（確認処理にて入力／セット）"
            l_feqOrderUnitParams.setSettleDiv(l_execInputInfo.settleDiv);
            
            //受渡日 :約定入力情報.約定詳細.国内受渡日
            l_feqOrderUnitParams.setDeliveryDate(
                l_execInputInfo.execDetailInfoUnit.deliveryDate);
            
            //注文失効日付 :約定入力情報.約定日時の日付部分
            l_feqOrderUnitParams.setExpirationDate(
                WEB3DateUtility.toDay(l_execInputInfo.execDetailInfoUnit.executionTimestamp));
            
            //市場から確認済みの数量 :注文数量
            l_feqOrderUnitParams.setConfirmedQuantity(
                l_feqOrderUnitParams.getQuantity());
            
            //市場から確認済みの指値 :指値
            l_feqOrderUnitParams.setConfirmedPrice(l_feqOrderUnitParams.getLimitPrice());
            
            //約定数量 :約定入力情報.約定詳細.約定数量
            l_feqOrderUnitParams.setExecutedQuantity(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
            
            //合計約定金額 :約定入力情報.売買代金（外貨）
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.foreignTradePrice))
            {
                l_feqOrderUnitParams.setExecutedAmount(null);
            }
            else
            {
                l_feqOrderUnitParams.setExecutedAmount(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice));
            }
            
            //合計約定金額（円） :約定入力情報.売買代金（円貨）
            //（売買代金（外貨）を計算サービス.calc円貨換算()にて円貨換算した値）
            //
            //証券会社コード
            //通貨コード
             WEB3GentradeCurrency l_genCurrency = WEB3GentradeCurrency.genCurrency(
                l_institution.getInstitutionCode(),
                l_execInputInfo.currencyCode);
             
            //売買代金（外貨）
            //レート： 約定入力情報.約定為替レート
            //円貨換算丸め方式： 通貨.get円貨換算丸め方式()
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.foreignTradePrice))
            {
                l_feqOrderUnitParams.setExecutedAmountYen(null);
            }
            else
            {
                double l_dblTradePrice = 
                    l_bizLogicProvider.calcJPYAmount(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignTradePrice),
                    Double.parseDouble(l_execInputInfo.execExchangeRate),
                    l_genCurrency.getChangeJpyRoundDiv());
                l_feqOrderUnitParams.setExecutedAmountYen(l_dblTradePrice);
            }
                    
            //注文状態 :1：受付済み（新規注文）。（OrderStatusEnumにて定義）
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            //注文有効状態 :2：クローズ（OrderOpenStatusEnumにて定義）
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            //失効区分 :1:オープン（OrderExpirationStatusEnumにて定義）
            l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            
            //税区分 :約定入力情報.特定口座区分に対応する税区分（TaxTypeEnumにて定義）
            l_feqOrderUnitParams.setTaxType(
                WEB3FeqOrderUtility.getTaxType(l_execInputInfo.taxType));
            
            //発注日 :約定入力情報.発注日
            l_feqOrderUnitParams.setBizDate(
                WEB3FeqDateUtility.formatDate(l_execInputInfo.orderBizDate, "yyyyMMdd"));
            
            //銘柄ＩＤ :外国株式銘柄.getProductId()
            l_feqOrderUnitParams.setProductId(l_web3FeqProduct.getProductId());
            
            //通貨コード :約定入力情報.通貨コード
            l_feqOrderUnitParams.setCurrencyCode(l_execInputInfo.currencyCode);
            
            //初回注文の注文チャネル :注文チャネル.”0：営業店”
            l_feqOrderUnitParams.setOrderChanel(WEB3ChannelDef.BRANCH);
            
            //受注日時 :現在日時
            l_feqOrderUnitParams.setReceivedDateTime(l_tsSystemTimestamp);
            
            //伝票No :"9"(WebBroker)＋識別コードの下3桁
            l_feqOrderUnitParams.setVoucherNo("9" + 
                l_execInputInfo.requestNumber.substring(l_execInputInfo.requestNumber.length() - 3));
            
            //初回注文の手数料No        :null
            l_feqOrderUnitParams.setCommTblNo(null);
            
            //初回注文の手数料No枝番    :null
            l_feqOrderUnitParams.setCommTblSubNo(null);
            
            //扱者コード（SONAR） :顧客.扱者コード（SONAR）
            String l_strTraderCode = 
                ((MainAccountRow) (l_mainAccount.getDataSourceObject())).getSonarTraderCode();
            l_feqOrderUnitParams.setSonarTraderCode(l_strTraderCode);
                    
            //注文単価 :約定入力情報.約定詳細.約定単価
            l_feqOrderUnitParams.setPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
            
            //識別コード :約定入力情報.識別コード
            l_feqOrderUnitParams.setOrderRequestNumber(l_execInputInfo.requestNumber);
            
            //概算受渡代金 :約定入力情報.約定詳細.受渡代金（円貨）
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.deliveryPrice))
            {
                l_feqOrderUnitParams.setEstimatedPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setEstimatedPrice(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice));
            }
            
            //概算受渡代金（外貨） :約定入力情報.約定詳細.受渡代金（外貨）
            if (WEB3StringTypeUtility.isEmpty(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice))
            {
                l_feqOrderUnitParams.setFEstimatedPrice(null);
            }
            else
            {
                l_feqOrderUnitParams.setFEstimatedPrice(
                    Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice));
            }
            
            //取引コード（SONAR） :11：普通株式(WEB3TransactionTypeSONARDefにて定義)
            l_feqOrderUnitParams.setSonarTradedCode(
                WEB3TransactionTypeSONARDef.MARKET_TRADING);
                    
            //市場コード（SONAR） :外国株式銘柄.get市場().市場コード(SONAR)
            Market l_market = l_web3FeqProduct.getMarket();
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();        
            l_feqOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            
            //手数料商品コード :40：外国株式(WEB3CommisionProductCodeDefにて定義)
            l_feqOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.FOREIGN_EQITY);

            //譲渡益金額 :約定入力情報.約定詳細.譲渡益
            l_feqOrderUnitParams.setCapitalGain(
                Double.parseDouble(l_execInputInfo.passProfit));

            //譲渡益税額 :約定入力情報.約定詳細.譲渡益税
            l_feqOrderUnitParams.setCapitalGainTax(
                Double.parseDouble(l_execInputInfo.passProfitTax));

            //注文訂正・取消区分 :"0：初期値（WEB3ModifyCancelTypeDefにて定義）"        
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            //注文経路区分 :注文経路区分.”9：HOST”
            l_feqOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            //市場から確認済みの注文単価 :指値
            l_feqOrderUnitParams.setConfirmedOrderPrice(l_feqOrderUnitParams.getLimitPrice());
            
            //市場から確認済みの概算受渡代金 :約定入力情報.約定詳細.受渡代金（円貨）
            l_feqOrderUnitParams.setConfirmedEstimatedPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice));

            //市場から確認済みの概算受渡代金（外貨）:約定入力情報.約定詳細.受渡代金（外貨）
            l_feqOrderUnitParams.setConfirmedFEstimatedPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice));
                    
            //市場から確認済みの執行条件 :FeqExecutionConditionType.NONE(1:条件なし)
            l_feqOrderUnitParams.setConfirmedExecConditionType(
			    FeqExecutionConditionType.NONE);
            
            //注文エラー理由コード :0000：正常
            l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            //ファ@クター :ブランク
            l_feqOrderUnitParams.setFactor(" ");
            
            //運用コード :約定入力情報.運用コード
            l_feqOrderUnitParams.setOrderEmpCode(l_execInputInfo.managementCode);
            
            //顧客区分 :約定入力情報.顧客区分
            l_feqOrderUnitParams.setAccountDiv(l_execInputInfo.accountDiv);
            
            //出来終了処理日時 :null
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            
            //初回注文の注文単位ＩＤ :null
            l_feqOrderUnitParams.setFirstOrderUnitId(null);
            
            //更新者コード :注文.更新者コード
            l_feqOrderUnitParams.setLastUpdater(l_strUpdaterCode);
            
            //作成日付 :現在日時
            l_feqOrderUnitParams.setCreatedTimestamp(
                l_tsSystemTimestamp);

            //更新日付 :現在日時
            l_feqOrderUnitParams.setLastUpdatedTimestamp(
                l_tsSystemTimestamp);
            l_queryProcessor.doInsertQuery(l_feqOrderUnitParams);
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.10 注文履歴行オブジェクトを生成
        FeqOrderActionParams l_feqOrderActionParams = new FeqOrderActionParams();
        
        //1.12 doInsertQuery
        try
        {
            //1.11 注文履歴行オブジェクトに値をセット
            //注文履歴ＩＤ : （自動採番）
            l_feqOrderActionParams.setOrderActionId(FeqOrderActionDao.newPkValue());
            
            //口座ＩＤ :注文単位.口座ＩＤ
            l_feqOrderActionParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            
            //補助口座ＩＤ :注文単位.補助口座ＩＤ
            l_feqOrderActionParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            
            //注文ＩＤ :注文単位.注文ＩＤ
            l_feqOrderActionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            
            //注文単位ＩＤ :注文単位.注文単位ＩＤ
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            
            //市場ＩＤ :注文単位.市場ＩＤ
            l_feqOrderActionParams.setMarketId(l_feqOrderUnitParams.getMarketId());
            
            //注文種別 :注文単位.注文種別
            l_feqOrderActionParams.setOrderType(l_feqOrderUnitParams.getOrderType());
            
            //注文イベントタイプ :91：約定済（OrderEventTypeEnumにて定義）
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXECUTE);
            
            //注文単価 :注文単位.指値
            if (l_feqOrderUnitParams.getLimitPriceIsNull())
            {
                l_feqOrderActionParams.setPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setPrice(l_feqOrderUnitParams.getLimitPrice());
            }
            
            //執行条件 :注文単位.執行条件
            l_feqOrderActionParams.setExecutionConditionType(
                l_feqOrderUnitParams.getExecutionConditionType());
            
            //発注条件 :注文単位.発注条件
            l_feqOrderActionParams.setOrderConditionType(
                l_feqOrderUnitParams.getOrderConditionType());
            
            //発注条件演算子 :注文単位.発注条件演算子
            l_feqOrderActionParams.setOrderCondOperator(
                l_feqOrderUnitParams.getOrderCondOperator());
            
            //逆指値基準値 :注文単位.逆指値基準値
            if (l_feqOrderUnitParams.getStopOrderPriceIsNull())
            {
                l_feqOrderActionParams.setStopOrderPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitParams.getStopOrderPrice());
            }
            
            //（W指値）訂正指値 :注文単位.（W指値）訂正指値
            if (l_feqOrderUnitParams.getWLimitPriceIsNull())
            {
                l_feqOrderActionParams.setWLimitPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitParams.getWLimitPrice());
            }
            
            //注文失効日付 :注文単位.注文失効日付
            l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitParams.getExpirationDate());
            
            //注文数量 :注文単位.数量
            l_feqOrderActionParams.setQuantity(l_feqOrderUnitParams.getQuantity());
            
            //市場と確認済みの指値 :注文単位.市場から確認済の指値
            if (l_feqOrderUnitParams.getConfirmedPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedPrice(l_feqOrderUnitParams.getConfirmedPrice());
            }
            
            //市場と確認済みの数量 :注文単位.市場から確認済の数量
            if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
            {
                l_feqOrderActionParams.setConfirmedQuantity(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedQuantity(l_feqOrderUnitParams.getConfirmedQuantity());
            }
            
            //約定数量 :注文単位.注文数量
            if (l_feqOrderUnitParams.getQuantityIsSet())
            {
                l_feqOrderActionParams.setExecutedQuantity(l_feqOrderUnitParams.getQuantity());
            }
            else
            {
                l_feqOrderActionParams.setExecutedQuantity(null);
            }
            
            //注文状態 :注文単位.注文状態
            l_feqOrderActionParams.setOrderStatus(l_feqOrderUnitParams.getOrderStatus());
            
            //注文失効ステータス :注文単位.失効区分
            l_feqOrderActionParams.setExpirationStatus(l_feqOrderUnitParams.getExpirationStatus());
            
            //注文履歴番号 :注文単位.注文履歴最終通番
            l_feqOrderActionParams.setOrderActionSerialNo(
                l_feqOrderUnitParams.getLastOrderActionSerialNo());
            
            //約定単価 :注文単位.指値
            if (l_feqOrderUnitParams.getLimitPriceIsNull())
            {
                l_feqOrderActionParams.setExecutedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setExecutedPrice(l_feqOrderUnitParams.getLimitPrice());
            }
            
            //約定日時 :約定入力情報.約定日時
            l_feqOrderActionParams.setExecTimestamp(
                l_execInputInfo.execDetailInfoUnit.executionTimestamp);
            
            //銘柄ＩＤ :注文単位.銘柄ＩＤ
            l_feqOrderActionParams.setProductId(
                l_feqOrderUnitParams.getProductId());
            
            //概算受渡代金 :注文単位.概算受渡代金
            if (l_feqOrderUnitParams.getEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setEstimatedPrice(
                    l_feqOrderUnitParams.getEstimatedPrice());
            }
            
            //概算受渡代金（外貨） :注文単位.概算受渡代金（外貨）
            if (l_feqOrderUnitParams.getFEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setFEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setFEstimatedPrice(
                    l_feqOrderUnitParams.getFEstimatedPrice());
            }
            
            //注文訂正・取消区分 :注文単位.注文訂正・取消区分
            l_feqOrderActionParams.setModifyCancelType(
                l_feqOrderUnitParams.getModifyCancelType());
            
            //注文経路区分 :注文単位.注文経路区分
            l_feqOrderActionParams.setOrderRootDiv(l_feqOrderUnitParams.getOrderRootDiv());
            
            //市場から確認済みの注文単価 :注文単位.市場から確認済みの注文単価
            if (l_feqOrderUnitParams.getConfirmedOrderPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedOrderPrice(
                    l_feqOrderUnitParams.getConfirmedOrderPrice());
            }
            
            //市場から確認済みの概算受渡代金 :注文単位.市場から確認済みの概算受渡代金
            if (l_feqOrderUnitParams.getConfirmedEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedEstimatedPrice(
                    l_feqOrderUnitParams.getConfirmedEstimatedPrice());
            }
            
            //市場から確認済みの概算受渡代金 :注文単位.市場から確認済みの概算受渡代金（外貨）
            if (l_feqOrderUnitParams.getConfirmedFEstimatedPriceIsNull())
            {
                l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
            }
            else
            {
                l_feqOrderActionParams.setConfirmedFEstimatedPrice(
                    l_feqOrderUnitParams.getConfirmedFEstimatedPrice());
            }

            //市場から確認済みの執行条件 :注文単位.市場から確認済みの執行条件
            l_feqOrderActionParams.setConfirmedExecConditionType(
                l_feqOrderUnitParams.getConfirmedExecConditionType());
            
            //注文エラー理由コード :注文単位.注文エラー理由コード
            l_feqOrderActionParams.setErrorReasonCode(
                l_feqOrderUnitParams.getErrorReasonCode());
            
            //更新者コード :注文単位.更新者コード
            l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitParams.getLastUpdater());
            
            //作成日付 :現在日時
            l_feqOrderActionParams.setCreatedTimestamp(l_tsSystemTimestamp);
            
            //更新日付 :現在日時
            l_feqOrderActionParams.setLastUpdatedTimestamp(
                l_tsSystemTimestamp);
            
            l_queryProcessor.doInsertQuery(l_feqOrderActionParams);        
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.13 FeqOrderExecutionParams()
        FeqOrderExecutionParams l_feqOrderExecutionParams = new FeqOrderExecutionParams();
                
        //1.15 doInsertQuery
        try 
        {            
            //1.14 約定行オブジェクトに値をセット
            //約定ＩＤ : （自動採番）
            l_feqOrderExecutionParams.setOrderExecutionId(FeqOrderExecutionDao.newPkValue());
    
            //口座ＩＤ :注文単位.口座ＩＤ
            l_feqOrderExecutionParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            
            //補助口座ＩＤ :注文単位.補助口座ＩＤ
            l_feqOrderExecutionParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            
            //部店ＩＤ :注文単位.部店ＩＤ
            l_feqOrderExecutionParams.setBranchId(l_feqOrderUnitParams.getBranchId());
            
            //取引者ＩＤ :注文単位.取引者ＩＤ
            if (l_feqOrderUnitParams.getTraderIdIsNull())
            {
                l_feqOrderExecutionParams.setTraderId(null);
            }
            else
            {
                l_feqOrderExecutionParams.setTraderId(l_feqOrderUnitParams.getTraderId());
            }

            //注文ＩＤ :注文単位.注文ＩＤ
            l_feqOrderExecutionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            
            //注文単位ＩＤ :注文単位.注文単位ＩＤ
            l_feqOrderExecutionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            
            //注文種別 :注文単位.注文種別
            l_feqOrderExecutionParams.setOrderType(l_feqOrderUnitParams.getOrderType());
            
            //銘柄タイプ :注文単位.銘柄タイプ
            l_feqOrderExecutionParams.setProductType(l_feqOrderUnitParams.getProductType());
            
            //市場ＩＤ :注文単位.市場ＩＤ
            l_feqOrderExecutionParams.setMarketId(l_feqOrderUnitParams.getMarketId());
            
            //受渡日 :注文単位.受渡日
            l_feqOrderExecutionParams.setDeliveryDate(l_feqOrderUnitParams.getDeliveryDate());
            
            //現地受渡日 :約定入力情報.現地受渡日
            l_feqOrderExecutionParams.setFDeliveryDate(l_execInputInfo.localDeliveryDate);
            
            //約定通番 :注文単位.約定最終通番
            l_feqOrderExecutionParams.setExecSerialNo(l_feqOrderUnitParams.getLastExecutionSerialNo());
            
            //約定単価 :約定入力情報.約定詳細.約定単価
            l_feqOrderExecutionParams.setExecPrice(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
            
            //為替レート :約定入力情報.約定為替レート
            l_feqOrderExecutionParams.setFxRate(
                Double.parseDouble(l_execInputInfo.execExchangeRate));
            
            //約定数量 :約定入力情報.約定詳細.約定数量
            l_feqOrderExecutionParams.setExecQuantity(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
            
            //約定日時 :約定入力情報.約定日時
            l_feqOrderExecutionParams.setExecTimestamp(
                l_execInputInfo.execDetailInfoUnit.executionTimestamp);
            
            //削除フラグ :0：FALSE
            l_feqOrderExecutionParams.setDeleteFlag(BooleanEnum.FALSE);
            
            //発注日 :注文単位.発注日
            l_feqOrderExecutionParams.setBizDate(l_feqOrderUnitParams.getBizDate());
            
            //銘柄ＩＤ :注文単位.銘柄ＩＤ
            l_feqOrderExecutionParams.setProductId(
                l_feqOrderUnitParams.getProductId());
            
            //決済区分 :注文単位.決済区分
            l_feqOrderExecutionParams.setSettleDiv(l_feqOrderUnitParams.getSettleDiv());
            
            //識別コード :注文単位.識別コード
            l_feqOrderExecutionParams.setOrderRequestNumber(l_feqOrderUnitParams.getOrderRequestNumber());
            
            //運用コード :注文単位.運用コード
            l_feqOrderExecutionParams.setOrderEmpCode(l_feqOrderUnitParams.getOrderEmpCode());
            
            //      約定経路区分
            // :ThreadLocalSystemAttributesRegistry.getAttribute(”約定経路区分”)
            //（※各サービスインタセプタにてセットした値）
            l_feqOrderExecutionParams.setOrderExecRouteDiv(
                (String) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV));
            
            //更新者コード :注文単位.更新者コード
            l_feqOrderExecutionParams.setLastUpdater(l_feqOrderUnitParams.getLastUpdater());
            
            //作成日付 :現在日時
            l_feqOrderExecutionParams.setCreatedTimestamp(l_tsSystemTimestamp);
            
            //更新日付 :現在日時
            l_feqOrderExecutionParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);
            
            //売買代金　@小数点第三位を四捨五入（sonarの仕様）
            double l_dblForeignTradePricewk =
                new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecPrice()))
                .multiply(new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecQuantity())))
                .doubleValue();
            double l_dblForeignTradePrice = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblForeignTradePricewk,
                    2,
                    WEB3FeqOrderUtility.ROUND); 
            l_feqOrderExecutionParams.setForeignTradePrice(l_dblForeignTradePrice);
            
            l_queryProcessor.doInsertQuery(l_feqOrderExecutionParams);        
        } 
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.16 persist約定残高(FeqOrderUnitParams, long, 外国株式約定入力情報)
        //      注文単位行：　@（生成した注文単位行オブジェクト）
        //      約定ＩＤ：　@（生成した約定行オブジェクト）.getOrderExecutionId()
        //      約定入力情報：　@約定入力情報
        persistExecBalance(
            l_feqOrderUnitParams, 
            l_feqOrderExecutionParams.getOrderExecutionId(), 
            l_execInputInfo);
        
        //1.17 余力再計算
        WEB3TPTradingPowerReCalcService l_powerReCalcServie = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        //[余力再計算()に指定する引数] 
        //補助口座：　@get補助口座()
        l_powerReCalcServie.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (persistｲﾝﾀｰﾈｯﾄ注文)<BR>
     * 注文情報をDBに更新する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（(管)約定入力）persistｲﾝﾀｰﾈｯﾄ注文」参照。<BR>
     * @@param l_execInputOrder - (約定入力情報)<BR>
     * 外国株式約定入力情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7CF4C010A
     */
    private void persistInternetOrder(WEB3FeqOrderAndExecutionUnit l_execInputOrder)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" persistInternetOrder(WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_execInputOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメーターが未指定(null)です。");  
        }         
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        //1.1 注文単位を取得
        //[get注文単位ByOrderId()に指定する引数]
        // 注文ＩＤ：　@約定入力情報.注文ＩＤ
        WEB3FeqOrderUnit l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                Long.parseLong(l_execInputOrder.orderId));

        FeqOrderUnitRow l_feqOrderUnitRow = 
            (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        
        //1.2 更新イベントインタセプタを生成
        //   [コンストラクタに指定する引数]
        //    外株出来通知キュー：　@（生成したオブジェクト※）
        //    　@※外株出来通知キューParamsを生成し、以下の通りプロパティに値をセットする。
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams
            = new HostFeqOrderExecNotifyParams();
        
        //証券会社コード：　@注文単位.get証券会社コード()
        l_hostFeqOrderExecNotifyParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
        
        //部店コード：　@注文単位.get部店コード()
        l_hostFeqOrderExecNotifyParams.setBranchCode(l_feqOrderUnit.getBranchCode());
        
        //顧客コード：　@注文単位.get口座コード()
        l_hostFeqOrderExecNotifyParams.setAccountCode(l_feqOrderUnit.getAccountCode());
        
        //識別コード：　@注文単位.識別コード
        l_hostFeqOrderExecNotifyParams.setRequestCode(l_feqOrderUnitRow.getOrderRequestNumber());
        
        //運用コード：　@注文単位.運用コード
        l_hostFeqOrderExecNotifyParams.setOrderEmpCode(l_feqOrderUnit.getOrderEmpCode());
        
        //約定株数：　@約定入力情報.約定詳細.約定株数
        if (WEB3StringTypeUtility.isEmpty(l_execInputOrder.execDetailInfoUnit.execQuantity))
        {
            l_hostFeqOrderExecNotifyParams.setExecQuantity(null);
        }
        else
        {
            l_hostFeqOrderExecNotifyParams.setExecQuantity(
                Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execQuantity));
        }
        
        //約定単価：　@約定入力情報.約定詳細.約定単価
        if (WEB3StringTypeUtility.isEmpty(l_execInputOrder.execDetailInfoUnit.execPrice))
        {
            l_hostFeqOrderExecNotifyParams.setExecPrice(null);
        }
        else
        {
            l_hostFeqOrderExecNotifyParams.setExecPrice(
                Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execPrice));
        }
        
        //約定日時：　@約定入力情報.約定詳細.約定日時
        l_hostFeqOrderExecNotifyParams.setExecTimestamp(
            l_execInputOrder.execDetailInfoUnit.executionTimestamp);
        
        //現地受渡日：　@約定入力情報.現地受渡日
        l_hostFeqOrderExecNotifyParams.setFDeliveryDate(l_execInputOrder.localDeliveryDate);
        
        //為替レート：　@約定入力情報.約定為替レート
        if (WEB3StringTypeUtility.isEmpty(l_execInputOrder.execExchangeRate))
        {
            l_hostFeqOrderExecNotifyParams.setFxRate(null);
        }
        else
        {
            l_hostFeqOrderExecNotifyParams.setFxRate(
                Double.parseDouble(l_execInputOrder.execExchangeRate));
        }

        WEB3FeqExecuteUpdateInterceptor l_web3FeqExecuteUpdateInterceptor 
            = new WEB3FeqExecuteUpdateInterceptor(l_hostFeqOrderExecNotifyParams);
        
        // 注文単位をセット
        l_web3FeqExecuteUpdateInterceptor.setFeqOrderUnit(l_feqOrderUnit);        
        // 約定数量をセット
        l_web3FeqExecuteUpdateInterceptor.setExecQuantity(
            Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execQuantity));
        // 約定単価をセット
        l_web3FeqExecuteUpdateInterceptor.setExecPrice(
            Double.parseDouble(l_execInputOrder.execDetailInfoUnit.execPrice));
        // 約定為替レートをセット
        l_web3FeqExecuteUpdateInterceptor.setFxRate(
            Double.parseDouble(l_execInputOrder.execExchangeRate));
        
        //1.3 更新イベントインタセプタを注文マネージャにセット
        //[setThreadLocalPersistenceEventInterceptor()に指定する引数]
        // arg0：　@外国株式約定更新イベントインタセプタ
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_web3FeqExecuteUpdateInterceptor);
        
        //1.4 約定コールバックメッセージを生成
        //[コンストラクタの引数]
        // 注文ＩＤ：　@注文単位.getOrderId()
        // 注文単位ＩＤ：　@注文単位.getOrderUnitId()
        // 約定数量：　@外株出来通知キュー.約定株数
        // 約定単価：　@外株出来通知キュー.約定単価
        // 約定為替レート：　@外株出来通知キュー.為替レート
        DefaultFeqOrderFillMarketResponseMessage l_defaultFeqOrderFillMarketResponseMessage = 
            new DefaultFeqOrderFillMarketResponseMessage(
                l_feqOrderUnit.getOrderId(),
                l_feqOrderUnit.getOrderUnitId(),
                l_hostFeqOrderExecNotifyParams.getExecQuantity(),
                l_hostFeqOrderExecNotifyParams.getExecPrice(),
                l_hostFeqOrderExecNotifyParams.getFxRate());

        //1.5 handle約定更新
        //[handle約定更新()に指定する引数]
        // 注文ＩＤ：　@約定入力情報.注文ＩＤ
        // 約定コールバックメッセージ：　@生成した約定コールバックメッセージ
        WEB3FeqProductTypeOrderSubmitterPersistenceManager l_orderSubmitterPersistenceManager = 
            (WEB3FeqProductTypeOrderSubmitterPersistenceManager)WEB3FeqProductTypeOrderSubmitterPersistenceManager.getInstance();

        OrderExecution l_orderExecution
            = l_orderSubmitterPersistenceManager.handleExecutionUpdate(
                Long.parseLong(l_execInputOrder.orderId),
                l_defaultFeqOrderFillMarketResponseMessage);

        //注文単位再取得
        l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderId(
                Long.parseLong(l_execInputOrder.orderId));

        //1.6 残高情報をDBに更新する。
        //[persist約定残高()に指定する引数]
        //注文単位行：　@注文単位.getDataSourceObject()
        //約定ＩＤ：　@handle約定更新()の戻り値
        //約定入力情報：　@約定入力情報
        persistExecBalance(
                (FeqOrderUnitParams) l_feqOrderUnit.getDataSourceObject(),
                l_orderExecution.getOrderExecutionId(),
                l_execInputOrder);

        //1.7 概算受渡代金再計算処理を行う。
        //[update概算受渡代金()に指定する引数]
        //注文単位：　@注文単位
        //基準日：　@約定入力情報.約定詳細.約定日時
        Date l_datBaseDate  =  l_execInputOrder.execDetailInfoUnit.executionTimestamp;
        
        l_orderManager.updateEstimatedPrice(
            l_feqOrderUnit,
            l_datBaseDate);

        l_orderManager.executeChangeCancelOrderRejected(l_feqOrderUnit.getOrderUnitId());

        //1.8 余力再計算処理を実施する。
        //[余力再計算()に指定する引数]
        //補助口座：　@注文単位.get補助口座()

        WEB3TPTradingPowerReCalcService l_powerReCalcServie = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        
        l_powerReCalcServie.reCalcTradingPower(l_feqOrderUnit.getSubAccount());
        
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (persist約定残高)<BR>
     * 残高情報をDBに更新する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（(管)約定入力）persist約定残高」参照。<BR>
     * @@param l_orderUnitParams - (注文単位行)<BR>
     * 注文単位行オブジェクト<BR>
     * @@param l_lngExecId - (約定ID)<BR>
     * 約定ID<BR>
     * @@param l_execInputInfo - (約定入力情報)<BR>
     * 外国株式約定入力情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B7B641006D
     */
    private void persistExecBalance(
        FeqOrderUnitParams l_orderUnitParams, 
        long l_lngExecId, 
        WEB3FeqOrderAndExecutionUnit l_execInputInfo)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " persistExecBalance(FeqOrderUnitParams," +
            " long, WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null || l_execInputInfo == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメーターが未指定(null)です。");  
        }
                
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        //1.1 トランザクション（取引勘定明細）行オブジェクトを生成
        FeqFinTransactionParams l_feqFinTransactionParams = 
            new FeqFinTransactionParams();
        
        //1.2 取引勘定明細行オブジェクトに値をセット
        //口座ＩＤ : 注文単位.口座ＩＤ
        l_feqFinTransactionParams.setAccountId(l_orderUnitParams.getAccountId());
        
        //補助口座ＩＤ : 注文単位.補助口座口座ＩＤ
        l_feqFinTransactionParams.setSubAccountId(l_orderUnitParams.getSubAccountId());
        
        //注文単位.銘柄ＩＤ
        l_feqFinTransactionParams.setProductId(l_orderUnitParams.getProductId());
        
        //トランザクションタイプ : （注文単位.注文種別 == 701：外株買い）の場合、701：外株買い
        //（注文単位.注文種別 == 702：外株売り）の場合、702：外株売り
        if (l_orderUnitParams.getOrderType().equals(OrderTypeEnum.FEQ_BUY))
        {
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            //約定入力情報.約定詳細.受渡代金（円貨）×（-1）
            l_feqFinTransactionParams.setNetAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice) * (-1));
            //約定入力情報.約定詳細.受渡代金（外貨）×（-1）
            l_feqFinTransactionParams.setNetAmountFc(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice) * (-1));
        } 
        else if (l_orderUnitParams.getOrderType().equals(OrderTypeEnum.FEQ_SELL))
        {
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            //約定入力情報.約定詳細.受渡代金（円貨）
            l_feqFinTransactionParams.setNetAmount(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.deliveryPrice));
            //約定入力情報.約定詳細.受渡代金（外貨）
            l_feqFinTransactionParams.setNetAmountFc(
                Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignDeliveryPrice));
        }
        
        //トランザクションカテゴリ:  20：現物取引（株式、ミニ株、債券、投信、外株）
        l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
        
        //トランザクション発生日時 : 現在日時
        l_feqFinTransactionParams.setFinTransactionTimestamp(
            GtlUtils.getSystemTimestamp());
        
        //税区分 : 注文単位.税区分
        l_feqFinTransactionParams.setTaxType(l_orderUnitParams.getTaxType());
        
        //決済区分 : 注文単位.決済区分
        l_feqFinTransactionParams.setSettleDiv(l_orderUnitParams.getSettleDiv());
        
        //発注日: 注文単位.発注日
        l_feqFinTransactionParams.setBizDate(l_orderUnitParams.getBizDate());
        
        //受渡日: 約定入力情報.約定詳細.受渡日
        l_feqFinTransactionParams.setDeliveryDate(l_execInputInfo.execDetailInfoUnit.deliveryDate);
        
        //通貨コード:注文単位.通貨コード
        l_feqFinTransactionParams.setCurrencyCode(l_orderUnitParams.getCurrencyCode());
        
        //適用為替レート: 約定入力情報.約定為替レート
        l_feqFinTransactionParams.setFxRate(
            Double.parseDouble(l_execInputInfo.execExchangeRate));
        
        //銘柄タイプ: 注文単位.銘柄タイプ
        l_feqFinTransactionParams.setProductType(l_orderUnitParams.getProductType());
        
        //市場ＩＤ: 注文単位.市場ＩＤ
        l_feqFinTransactionParams.setMarketId(l_orderUnitParams.getMarketId());
        
        //約定単価:約定入力情報.約定詳細.約定単価
        l_feqFinTransactionParams.setPrice(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execPrice));
        
        //約定数量:約定入力情報.約定詳細.約定数量
        l_feqFinTransactionParams.setQuantity(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.execQuantity));
        
        //注文ＩＤ: 注文単位.注文ＩＤ
        l_feqFinTransactionParams.setOrderId(l_orderUnitParams.getOrderId());
        
        //注文単位ＩＤ:注文単位.注文単位ＩＤ
        l_feqFinTransactionParams.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
        
        //約定ＩＤ
        l_feqFinTransactionParams.setOrderExecutionId(l_lngExecId);
        
        //委託手数料:約定入力情報.約定詳細.国内手数料（円貨）
        l_feqFinTransactionParams.setCommissionFee(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.commission));
        
        //委託手数料消費税:約定入力情報.約定詳細.消費税（円貨）
        l_feqFinTransactionParams.setCommissionFeeTax(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.consumptionTax));
        
        //現地清算代金（円貨）:約定入力情報.約定詳細.清算代金（円貨）
        l_feqFinTransactionParams.setBalanceAmount(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.clearUpPrice));
        
        //委託手数料（外貨）:約定入力情報.約定詳細.国内手数料（外貨）
        l_feqFinTransactionParams.setCommissionFeeFc(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignCommission));
        
        //委託手数料消費税（外貨）:約定入力情報.約定詳細.消費税（外貨）
        l_feqFinTransactionParams.setCommissionFeeTaxFc(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignConsumptionTax));
        
        //現地清算代金（外貨）: 約定入力情報.約定詳細.清算代金（外貨）
        l_feqFinTransactionParams.setBalanceAmountFc(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.foreignClearUpPrice));        
        
        //現地手数料:約定入力情報.約定詳細.現地手数料
        l_feqFinTransactionParams.setForeignCommissionFee(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.localCommission));
        
        //現地取引税:約定入力情報.約定詳細.現地取引税
        l_feqFinTransactionParams.setForeignTax(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.localTradingTax));
        
        //その他コスト１: 約定入力情報.約定詳細.その他コスト１
        l_feqFinTransactionParams.setForeignFeeExt1(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.otherCost1));
        
        //その他コスト２: 約定入力情報.約定詳細.その他コスト２
        l_feqFinTransactionParams.setForeignFeeExt2(
            Double.parseDouble(l_execInputInfo.execDetailInfoUnit.otherCost2));
                
        //譲渡益金額:約定入力情報.譲渡益
        l_feqFinTransactionParams.setCapitalGain(
            Double.parseDouble(l_execInputInfo.passProfit));
        
        //譲渡益税額:約定入力情報.譲渡益税
        l_feqFinTransactionParams.setCapitalGainTax(
            Double.parseDouble(l_execInputInfo.passProfitTax));
        
        //譲渡益金額（外貨）
        boolean l_blnIsBuy = l_orderUnitParams.getOrderType().equals(OrderTypeEnum.FEQ_BUY);
        WEB3FeqBizLogicProvider l_bizLogicProvider =
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblCapitalGainFc =
            l_bizLogicProvider.calcForeignCCYAmount(
                l_feqFinTransactionParams.getCapitalGain(),
                l_feqFinTransactionParams.getFxRate(),
                l_orderUnitParams.getProductId(),
                l_blnIsBuy,
                true);
        l_feqFinTransactionParams.setCapitalGainFc(l_dblCapitalGainFc);
        //譲渡益税額（外貨）
        double l_dblCapitalGainTaxFc =
            l_bizLogicProvider.calcForeignCCYAmount(
                l_feqFinTransactionParams.getCapitalGainTax(),
                l_feqFinTransactionParams.getFxRate(),
                l_orderUnitParams.getProductId(),
                l_blnIsBuy,
                true);
        l_feqFinTransactionParams.setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
        
        //売却保有資産の管理費:0
        l_feqFinTransactionParams.setTransferedAssetMngFee(0);
        //売却保有資産の管理費消費税:0
        l_feqFinTransactionParams.setTransferedAssetMngFeeTax(0);
        //売却保有資産の手数料:0
        l_feqFinTransactionParams.setTransferedAssetSetupFee(0);
        //売却保有資産の手数料消費税:0
        l_feqFinTransactionParams.setTransferedAssetSetupFeeTax(0);
        //資産の簿価:0
        l_feqFinTransactionParams.setTransferedAssetBookValue(0);
        //削除フラグ:0：FALSE
        l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        //約定経路区分: "ThreadLocalSystemAttributesRegistry.getAttribute(”約定経路区分”)
        //（※各サービスインタセプタにてセットした値）"
        l_feqFinTransactionParams.setOrderExecRouteDiv(            
                (String) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV));

        //更新者コード:注文単位.更新者コード
        l_feqFinTransactionParams.setLastUpdater(l_orderUnitParams.getLastUpdater());

        //作成日付:"現在日時（※標準実装setExecutionInfoToMarketOrderedTrans( )にてセット）"
        l_feqFinTransactionParams.setCreatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //更新日付:"現在日時（※標準実装setExecutionInfoToMarketOrderedTrans( )にてセット）"
        l_feqFinTransactionParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        try
        {
            //1.3 保有資産を更新する。
            //[applyTo保有資産残高()に指定する引数]
            //トランザクション（取引勘定明細）行：　@（生成したオブジェクト）
            WEB3FeqPositionManager l_web3FeqPositionManager
                = (WEB3FeqPositionManager) l_tradingModule.getPositionManager();

            l_web3FeqPositionManager.applyToAssetBalance(l_feqFinTransactionParams);

            //1.4 外国株式更新データマネージャを取得
            WEB3FeqPositionManagerHelper l_web3FeqPositionManagerHelper
                = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);

            //1.5 トランザクション（取引勘定明細）行オブジェクトをDBに更新する。
            //[saveNewFinTransaction()に指定する引数]
            //トランザクション（取引勘定明細）行：　@（生成したオブジェクト）
            l_web3FeqPositionManagerHelper.getPersistenceManager().saveNewFinTransaction(
                l_feqFinTransactionParams);

            //1.6 顧客勘定明細，補助口座を更新する。
            //[notify顧客勘定()に指定する引数]
            //トランザクション（取引勘定明細）行：　@（生成したオブジェクト）
            l_web3FeqPositionManager.notifyAccountCash(l_feqFinTransactionParams);            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
			ErrorInfo l_errorInfo = l_ex.getErrorInfo();
			
			//error_tagが"BUSINESS_ERROR_00204"の場合は、WEB3BusinessLayerExceptionをスローする。
			if (l_errorInfo.getErrorTag().equals("BUSINESS_ERROR_00204"))
			{
				throw new WEB3BusinessLayerException(
		  		WEB3ErrorCatalog.BUSINESS_ERROR_00204,
		  		this.getClass().getName() + "." + STR_METHOD_NAME);
		  		
			}
			//error_tagが"BUSINESS_ERROR_01931"の場合は、WEB3BusinessLayerExceptionをスローする。
			else if(l_errorInfo.getErrorTag().equals("BUSINESS_ERROR_01931"))
			{
				throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01931,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			else
			{
				throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}
        }
        catch (DataException l_ex)
        {
        	
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * to compare WEB3FeqOrderAndExecutionUnit's instance
     * @@param l_unit1
     * @@param l_unit2
     * @@return boolean
     */
    private boolean isOrderAndExecutionUnitEquals(
        WEB3FeqOrderAndExecutionUnit l_unit1, 
        WEB3FeqOrderAndExecutionUnit l_unit2)
    {
        final String STR_METHOD_NAME = " isOrderAndExecutionUnitEquals(" +
            "WEB3FeqOrderAndExecutionUnit, " + 
            "WEB3FeqOrderAndExecutionUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_unit1 == null && l_unit2 == null)
        {
            return true;
        }
        
        if (l_unit1 != null && l_unit2 == null)
        {
            return false;
        }
        
        if (l_unit1 == null && l_unit2 != null)
        {
            return false;
        }        
        
        //扱者コード：　@        
        if (!this.isEqual(l_unit1.traderCode, l_unit2.traderCode))
        {
            return false;
        }        
        
        //顧客区分：　@
        if (!this.isEqual(l_unit1.accountDiv, l_unit2.accountDiv))
        {
            return false;
        }   
        log.exiting(STR_METHOD_NAME);
        return true;        
    }
    
    /**
     * to compare Object
     * @@param l_obj1
     * @@param l_obj2
     * @@return boolean
     */
    private boolean isEqual(Object l_obj1, Object l_obj2)
    {
        final String STR_METHOD_NAME = " isEqual(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        if (l_obj1 != null)
        {
            if (l_obj1.equals(l_obj2))
            {
                return true;
            }
        }
        else
        {
            if (l_obj2 != null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        
        return false;        
    }
}
@
