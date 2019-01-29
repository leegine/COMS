head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文約定照会サービスImpl(WEB3FeqExecuteReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成       
                 : 2005/08/03 鄭海良(中訊) レビュー       
Revesion History : 2007/07/04 柴双紅(中訊) 実装No.003
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.473、474、476
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderAction;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTypeOrderManagerReusableValidations;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.define.WEB3FeqReferenceTypeDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3FeqChangeCancelHistoryGroup;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqExecuteDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqExecuteDetailsRequest;
import webbroker3.feq.message.WEB3FeqExecuteDetailsResponse;
import webbroker3.feq.message.WEB3FeqExecuteGroup;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceResponse;
import webbroker3.feq.message.WEB3FeqExecuteUnit;
import webbroker3.feq.message.WEB3FeqOrderDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderHistoryRequest;
import webbroker3.feq.message.WEB3FeqOrderHistoryResponse;
import webbroker3.feq.message.WEB3FeqProductCodeNameUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqExecuteReferenceService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (外国株式注文約定照会サービスImpl)<BR>
 * 外国株式注文約定照会サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqExecuteReferenceServiceImpl extends 
    WEB3FeqClientRequestService implements WEB3FeqExecuteReferenceService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F6036B
     */
    public WEB3FeqExecuteReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式注文約定照会サービス処理を行う <BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * リクエストデータの型が、<BR>
     * 　@[外国株式注文約定照会リクエストの場合]<BR>
     * 　@　@this.get注文約定照会()メソッドをコールする。<BR>
     * <BR>
     * 　@[外国株式注文約定詳細リクエストの場合]<BR>
     * 　@　@this.get注文約定詳細()メソッドをコールする。<BR>
     * <BR>
     * 　@[外国株式注文約定履歴リクエストの場合]<BR>
     * 　@　@this.get注文約定履歴()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA2C01E0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //リクエストデータの型が、 
        //[外国株式注文約定照会リクエストの場合] 
        if (l_request instanceof WEB3FeqExecuteReferenceRequest)
        {
            //　@this.get注文約定照会()メソッドをコールする。 
            l_response = this.getOrderExecuteReference(
                (WEB3FeqExecuteReferenceRequest)l_request);   
        }        
        //[外国株式注文約定詳細リクエストの場合] 
        else if (l_request instanceof WEB3FeqExecuteDetailsRequest)
        {
            //　@this.get注文約定詳細()メソッドをコールする。 
            l_response = 
                this.getOrderExecuteDetails(
                    (WEB3FeqExecuteDetailsRequest)l_request);   
        }        
        //[外国株式注文約定履歴リクエストの場合] 
        else if (l_request instanceof WEB3FeqOrderHistoryRequest)
        {
            //　@this.get注文約定履歴()メソッドをコールする。 
            l_response =
                this.getOrderExecuteAction((WEB3FeqOrderHistoryRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get注文約定照会)<BR>
     * 外国株式注文約定照会処理を行う <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）get注文約定照会」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定照会リクエストオブジェクト
     * @@return WEB3FeqExecuteReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA0E0367
     */
    protected WEB3FeqExecuteReferenceResponse getOrderExecuteReference(
        WEB3FeqExecuteReferenceRequest l_request) 
            throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = "getOrderExecuteReference(" +
            "WEB3FeqExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.3 validate注文約定照会(補助口座, 外国株式注文約定照会リクエスト)
        //注文約定照会処理が可能かどうかチェックする。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //リクエストデータ：　@リクエストデータ
        this.validateOrderExecuteReference(l_subAccount, l_request);
        
        //1.4 レスポンスデータを生成する。
        WEB3FeqExecuteReferenceResponse l_response = 
            (WEB3FeqExecuteReferenceResponse)l_request.createResponse();

        //1.5 create銘柄情報一覧()
        //顧客に該当する銘柄情報を作成し、レスポンスデータにセットする。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //リクエストデータ：　@リクエストデータ 
        //レスポンスデータ：　@createResponse()の戻り値
        this.createProductInformationList(l_subAccount, l_request, l_response);
        
        //1.6 create注文明細一覧()
        //顧客に該当する注文明細を作成し、レスポンスデータにセットする。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //リクエストデータ：　@リクエストデータ 
        //レスポンスデータ：　@createResponse()の戻り値
        this.createOrderDetailList(l_subAccount, l_request, l_response);
        
        //1.7 get市場閉局警告外株市場(部店 : 部店)
        //市場閉局間近の市場コードの一覧を取得する。 
        //[引数] 
        //部店：　@get補助口座()の戻り値.get取引店()
        WEB3GentradeBranch l_branch = 
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
        String[] l_strCloseFeqMarkets = 
        WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);

        //1.8 get取扱可能市場(部店, ProductTypeEnum)
        //部店が取扱可能な市場コードの一覧を取得する。 
        //[引数] 
        //部店：　@get補助口座()の戻り値.get取引店() 
        //銘柄タイプ：　@ProductTypeEnum.外国株式
        String[] l_strHandlingPossMarkets = 
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.9 get発注日一覧(String, String[])
        //発注日の一覧を取得する。 
        //[引数] 
        //照会区分：　@リクエストデータ.照会区分 
        //市場コード一覧：　@get取扱可能市場()の戻り値
        Date[] l_datOrderBizDates = 
            this.getOrderBizDateList(
                l_request.referenceType, l_strHandlingPossMarkets);
        
        //1.10 レスポンスデータにプロパティをセットする。
        //(*)レスポンスデータにプロパティをセットする。        
        
        //レスポンス.市場コード一覧       ＝　@get取扱可能市場()の戻り値
        l_response.marketList = l_strHandlingPossMarkets;        
        log.debug("レスポンス.市場コード一覧 = " + l_response.marketList);
        
        //レスポンス.発注日一覧     ＝　@get発注日一覧()の戻り値
        l_response.orderBizDateList = l_datOrderBizDates;
        log.debug("レスポンス.発注日一覧 = " + l_response.orderBizDateList);
        
        //レスポンス.取引終了警告市場コード一覧 ＝　@get市場閉局警告外株市場()の戻り値
        l_response.messageSuspension = l_strCloseFeqMarkets;
        log.debug("レスポンス.取引終了警告市場コード一覧 = " + l_response.messageSuspension);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get注文約定詳細)<BR>
     * 外国株式注文約定詳細照会処理を行う <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）get注文約定詳細」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定詳細リクエストオブジェクト
     * @@return WEB3FeqExecuteDetailsResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA0E0386
     */
    protected WEB3FeqExecuteDetailsResponse getOrderExecuteDetails(
        WEB3FeqExecuteDetailsRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExecuteDetails(" +
            "WEB3FeqExecuteDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getOrderUnits(arg0 : long)
        //注文単位を取得する。 
        //[引数] 
        //arg0：　@リクエストデータ.注文ID        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        OrderUnit[] l_orderUnits = l_feqOrderManager.getOrderUnits(
            Long.parseLong(l_request.orderId));       
        
        if (l_orderUnits == null)
        {
          log.debug("Error in 注文単位オブジェクトを取得する");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02142,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
            
        //以降の処理では、getOrderUnits()の戻り値の0番目の要素を注文単位として扱う。        
        WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_orderUnits[0];
        
        //1.3 create注文詳細情報(外国株式注文単位)
        //注文詳細情報を作成する。 
        //[引数] 
        //注文単位：　@注文単位
        WEB3FeqOrderDetailInfoUnit l_feqOrderDetailInfoUnit = 
            this.createOrderDetailsInformation(l_feqOrderUnit);
        
        //1.4 create約定詳細情報(外国株式注文単位)
        //約定詳細情報を作成する。 
        //[引数] 
        //注文単位：　@注文単位
        WEB3FeqExecuteDetailInfoUnit l_feqExecuteDetailInfoUnit = 
            this.createExecuteDetailsInformation(l_feqOrderUnit);

        //1.5 レスポンスデータを生成する。
        WEB3FeqExecuteDetailsResponse l_response = 
            (WEB3FeqExecuteDetailsResponse)l_request.createResponse();
        
        //1.6 (*)レスポンスデータに以下のプロパティをセットする。
        //レスポンス.注文詳細  ＝　@create注文詳細情報()の戻り値
        l_response.orderDetailInfoUnit = l_feqOrderDetailInfoUnit;
        
        //レスポンス.約定詳細  ＝　@create約定詳細情報()の戻り値
        l_response.executeDetailInfoUnit = l_feqExecuteDetailInfoUnit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get注文約定履歴)<BR>
     * 外国株式注文約定履歴照会処理を行う <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）get注文約定履歴」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定履歴リクエストオブジェクト
     * @@return WEB3FeqOrderHistoryResponse
     * @@throws WEB3BaseException
     * @@roseuid 429EAA0E03A5
     */
    protected WEB3FeqOrderHistoryResponse getOrderExecuteAction(
        WEB3FeqOrderHistoryRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExecuteAction(" +
            "WEB3FeqOrderHistoryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getOrderUnits(arg0 : long)
        //注文単位を取得する。 
        //[引数] 
        //arg0：　@リクエストデータ.注文ID        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        OrderUnit[] l_orderUnits = l_feqOrderManager.getOrderUnits(
            Long.parseLong(l_request.orderId));   
        
        if (l_orderUnits == null)
        {
          log.debug("Error in 注文単位オブジェクトを取得する");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_02142,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
            
        //以降の処理では、getOrderUnits()の戻り値の0番目の要素を注文単位として扱う。        
        WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)l_orderUnits[0];
        
        //1.3 create注文履歴一覧(外国株式注文単位)
        //注文履歴一覧を作成する。 
        //[引数] 
        //注文単位：　@注文単位
        WEB3FeqChangeCancelHistoryGroup[] l_changeCancelHistoryGroups = 
            this.createOrderActionList(l_feqOrderUnit);
        
        //1.4 レスポンスデータを生成する。
        WEB3FeqOrderHistoryResponse l_response = 
            (WEB3FeqOrderHistoryResponse)l_request.createResponse();
        
        //1.5 (*)レスポンスデータに以下のプロパティをセットする。
        //レスポンス.注文履歴一覧 ＝　@create注文履歴一覧()の戻り値
        l_response.changeCancelHistoryGroups = l_changeCancelHistoryGroups;        
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get補助口座)<BR>
     * （getSubAccountのオーバーライド）<BR>
     * <BR>
     * ログインセキュリティサービスより補助口座を取得する。<BR>
     * ※管理者外国株式注文約定照会機@能からコールされることを考慮。<BR>
     * <BR>
     * １）ログインセキュリティサービスより口座ＩＤを取得し、<BR>
     * 該当する顧客オブジェクトを取得する。<BR>
     *    ※取得した口座ID == 0の場合、<BR>
     * ThreadLocalSystemAttributeRegistry.getAttribute()より<BR>
     *       口座IDを取得する。<BR>
     * <BR>
     *          設定キー：　@ACCOUNT_ID<BR>
     * 　@　@　@　@※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、<BR>
     * その定数を参照すること。<BR>
     * <BR>
     * ２）顧客オブジェクト.getSubAccount( )にて、<BR>
     * 該当顧客の補助口座オブジェクトを取得する。<BR>
     * <BR>
     *    [getSubAccount引数]<BR>
     * 　@　@[顧客.is信用口座開設("指定なし") == trueの場合]<BR>
     * 　@　@　@SubAccountTypeEnum.株式信用取引口座<BR>
     * EQUITY_MARGIN_SUB_ACCOUNT）<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@SubAccountTypeEnum.株式取引口座（EQUITY_SUB_ACCOUNT）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 429EACB000E6
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        
        //１）ログインセキュリティサービスより口座ＩＤを取得し、該当する顧客オブジェクトを取得する。 
        //※取得した口座ID == 0の場合、ThreadLocalSystemAttributeRegistry.getAttribute()より 
        //口座IDを取得する。 
        //   設定キー：　@ACCOUNT_ID 
        //  ※設定キー：ACCOUNT_IDは必ず定数クラスを作成し、その定数を参照すること。
        
        long l_lngAccountId = l_opLoginSec.getAccountId();
        if (l_lngAccountId == 0L)
        {
            log.debug("取得した口座ID == 0の場合");
            l_lngAccountId = Long.parseLong(
                (String)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID));           
        }
        //２）顧客オブジェクト.getSubAccount( )にて、該当顧客の補助口座オブジェクトを取得する。 
        //[getSubAccount引数] 
        //[顧客.is信用口座開設("指定なし") == trueの場合] 
        //　@SubAccountTypeEnum.株式信用取引口座（EQUITY_MARGIN_SUB_ACCOUNT） 
        //[上記以外の場合] 
        //SubAccountTypeEnum.株式取引口座（EQUITY_SUB_ACCOUNT） 

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(l_lngAccountId);
        
            SubAccountTypeEnum l_subAccountType = null;
            
            boolean l_blnMarginAccount = 
                l_mainAccount.isMarginAccountEstablished(
                    WEB3GentradeRepaymentDivDef.DEFAULT);
            
            if (l_blnMarginAccount)
            {
                log.debug("顧客.is信用口座開設()==trueの場合");
                l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                log.debug("顧客.is信用口座開設()==falseの場合");
                l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }    
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(l_subAccountType);
            
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate注文約定照会)<BR>
     * 注文約定照会処理が実施可能であるかチェックする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）validate注文約定照会」参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株注約照会」(外株注約照会）validate注文約定照会)<BR>
     * 　@　@: 1.1.2.2.1 throw<BR> 
     * 　@　@「指定銘柄なしエラー」の例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@外株注約照会」(外株注約照会）validate注文約定照会)<BR>
     * 　@　@: 1.2.5.1 throw<BR> 
     * 　@　@「訂正取消不可」の例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02177<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * 　@外株注約照会」(外株注約照会）validate注文約定照会)<BR>
     * 　@　@: 1.2.8.1 validate外株銘柄(証券会社, String)<BR> 
     * 　@　@該当データなしの場合は、<BR>
     *     「指定銘柄なしエラー」の例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_request - (リクエストデータ)<BR>
     * @@throws WEB3BaseException
     * 外国株式注文約定照会リクエストオブジェクト
     * @@roseuid 429ED278024E
     */
    protected void validateOrderExecuteReference(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqExecuteReferenceRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderExecuteReference(" +
            "WEB3GentradeSubAccount, WEB3FeqExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);               
        
        if (l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        //1.1 (*)リクエストデータ.照会区分 == "注文約定照会"の場合
        if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
            l_request.referenceType))
        {
            log.debug("リクエストデータ.照会区分 == '注文約定照会'の場合");
            
            //1.1.1 validate注文受付可能( )(取引時間管理::validate注文受付可能)
            //"照会"のシステム緊急停止中・バッチ中チェックを行う。
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //1.1.2 (*)リクエストデータ.銘柄コード != nullの場合
            if (l_request.productCode != null)
            {
                log.debug("リクエストデータ.銘柄コード != nullの場合");
                
                //1.1.2.1  get外国株式銘柄(Institution, String)
                //外国株式銘柄を取得する。 
                //[引数] 
                //arg0：　@パラメータ.補助口座.getInstitution() 
                //arg1：　@リクエストデータ.銘柄コード
                
                WEB3FeqProductManager l_feqProductManager = 
                    (WEB3FeqProductManager)l_tradingModule.getProductManager();
                                
                try
                {                    
                    l_feqProductManager.getFeqProduct(
                        l_subAccount.getInstitution(), 
                        l_request.productCode);
                }
                //1.1.2.2 (*)get外国株式銘柄()が例外をスローした場合
                catch (NotFoundException l_ex)                
                {                    
                    log.error("__NotFound 外国株式銘柄__", l_ex);
                    
                    //1.1.2.2.1 throw 「指定銘柄なしエラー」の例外をスローする。
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "外国株式銘柄が取得できません。");
                }                
            }            
        }
        //1.2  (*)リクエストデータ.照会区分 == "訂正取消一覧"の場合
        else if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
            l_request.referenceType))
        {
            log.debug("リクエストデータ.照会区分 == '訂正取消一覧'の場合");
            
            //1.2.1 reset注文受付トランザクション(String)
            //注文受付トランザクションを"訂正"に変更する。 
            //[引数] 
            //注文受付トランザクション：　@"訂正"
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);
            
            //1.2.2 validate注文受付可能( )(取引時間管理::validate注文受付可能)
            //"訂正"のシステム緊急停止中・バッチ中チェックを行う。
            boolean l_blnOrderAcceptErr1 = false;
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("error in validate注文受付可能( )");
                l_blnOrderAcceptErr1 = true;
            }

            //1.2.3 reset注文受付トランザクション(String)
            //注文受付トランザクションを"取消"に変更する。 
            //[引数] 
            //注文受付トランザクション：　@"取消"
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CANCEL);
            
            //1.2.4 validate注文受付可能( )(取引時間管理::validate注文受付可能)
            //"取消"のシステム緊急停止中・バッチ中チェックを行う。
            boolean l_blnOrderAcceptErr2 = false;
            try
            {
                WEB3GentradeTradingTimeManagement.validateOrderAccept();   
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("error in validate注文受付可能( )");
                l_blnOrderAcceptErr2 = true;
            }
            //1.2.5 (*)上記validate注文受付可能()が両方とも例外をスローした場合            
            if (l_blnOrderAcceptErr1 && l_blnOrderAcceptErr2)
            {
                //1.2.5.1 throw 「訂正取消不可」の例外をスローする。
                log.debug("訂正取消不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02177,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "訂正取消不可。");
            }
            
            //1.2.6 getOrderValidator( )
            //注文チェックオブジェクトを取得する。            
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();      
            
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
            
            //1.2.7 validate取引可能顧客(SubAccount)
            //取引可能顧客チェックを行う。 
            //[引数] 
            //補助口座：　@パラメータ.補助口座
            OrderValidationResult l_validationResult =
                l_orderValidator.validateSubAccountForTrading(
                    l_subAccount);
                
            log.debug("注文チェック.validate取引可能顧客(): isFailedResult = " + 
                    l_validationResult.getProcessingResult().isFailedResult());                       
            
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引可能顧客チェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引可能顧客チェックエラー");
            }
            
			//getInstance( )
			WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
			   	(WEB3FeqTypeOrderManagerReusableValidations)
				   WEB3FeqTypeOrderManagerReusableValidations.getInstance();
            
            //validate外国株式口座開設(補助口座)
			l_orderMgrResVal.validateFeqAccountEstablish(l_subAccount);
            
            //1.2.8  (*)リクエストデータ.銘柄コード != nullの場合
            if (l_request.productCode != null)
            {
                log.debug("リクエストデータ.銘柄コード != nullの場合");
                
                //1.2.8.1 validate外株銘柄(証券会社, String)
                //外国株式銘柄の取得チェックを行う。 
                //[引数] 
                //証券会社：　@パラメータ.補助口座.getInstitution() 
                //銘柄コード：　@リクエストデータ.銘柄コード
                
                WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)
                    l_subAccount.getInstitution();
                
                WEB3FeqProduct l_feqProduct = null;
                try
                {
                    l_feqProduct = (WEB3FeqProduct)
                        l_feqOrderManager.validateFeqProduct(
                            l_institution, 
                            l_request.productCode);
                }
                catch(WEB3BaseException l_ex)
                {
                    //該当データなしの場合は、
                    //「指定銘柄なしエラー」の例外をスローする。               
                    log.debug("外国株式銘柄が取得できません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "外国株式銘柄が取得できません。");                
                }

                //1.2.8.2 validate取引銘柄(外国株式銘柄, 市場)
                //外国株式取引銘柄の取得・非上場・上場期間チェックを行う。 
                //[引数] 
                //外国株式銘柄：　@validate外株銘柄()の戻り値 
                //市場：　@validate外株銘柄()の戻り値.get市場()
                l_feqOrderManager.validateTradedProduct(
                    l_feqProduct, 
                    l_feqProduct.getMarket());
            }
            
            //1.2.9 (*)リクエストデータ.市場コード != nullの場合
            if (l_request.marketCode != null)
            {
                log.debug("リクエストデータ.市場コード != nullの場合");
                
                //1.2.9.1 get市場(, )(拡張金融オブジェクトマネージャ::get市場)
                //市場オブジェクトを取得する。 
                //[引数] 
                //証券会社コード：　@パラメータ.補助口座.getInstitution().get証券会社コード() 
                //市場コード：　@リクエストデータ.市場コード                
                WEB3GentradeFinObjectManager l_finObjManager = 
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();                               
                
                String l_strInstitutionCode = 
                    l_subAccount.getInstitution().getInstitutionCode();
                
                try
                {      
                    l_finObjManager.getMarket(
                        l_strInstitutionCode, 
                        l_request.marketCode);
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("__NotFoundException__", l_ex);
                    //例外をスローする
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }   
                //1.2.9.2 validate取扱可能市場(String, String, String)
                //取扱可能市場チェックを行う。 
                //[引数] 
                //証券会社コード：　@パラメータ.補助口座.getInstitution().get証券会社コード() 
                //部店コード：　@パラメータ.補助口座.get取引店().get部店コード() 
                //市場コード：　@リクエストデータ.市場コード
                String l_strBranchCode = 
                    l_subAccount.getMainAccount().getBranch().getBranchCode();
                
                l_feqOrderManager.validateHandlingPossibleMarket(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_request.marketCode);
            }            
        }        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (create銘柄情報一覧)<BR>
     * 顧客に該当する外国株式銘柄情報一覧を作成し、<BR>
     * レスポンスデータにセットする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）create銘柄情報一覧」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定照会リクエストオブジェクト
     * @@param l_response - (レスポンスデータ)<BR>
     * 外国株式注文約定照会レスポンスオブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 42A41AF801E0
     */
    protected void createProductInformationList(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqExecuteReferenceRequest l_request, 
        WEB3FeqExecuteReferenceResponse l_response) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductInformationList(" +
            "WEB3GentradeSubAccount l_subAccount, WEB3FeqExecuteReferenceRequest l_request, " +
                "WEB3FeqExecuteReferenceResponse l_response)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_request == null || l_response == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 create検索条件文字列(String, String, String, Date)
        //検索条件文字列を作成する。 
        //[引数] 
        //銘柄コード：　@null 
        //市場コード：　@null 
        //約定状態区分：　@null 
        //発注日：　@null
        String l_strQueryString = 
            this.createQueryString(null, null, null, null);
        
        //1.2 create検索条件データコンテナ(証券会社, String, String, String, Date)
        //検索条件データコンテナを作成する。 
        //[引数] 
        //証券会社：　@null 
        //銘柄コード：　@null 
        //市場コード：　@null 
        //約定状態区分：　@null 
        //発注日：　@業務日付(*1)の前営業日 
        //(*1)GtlUtils.getTradingSystem().getBizDate()
        
        Date l_datSysDate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsSysDate = new Timestamp(l_datSysDate.getTime());
        
        WEB3GentradeBizDate l_gentradeBizDate = 
            new WEB3GentradeBizDate(new Timestamp(l_datSysDate.getTime()));
            
        Date l_datPreBizDate = l_gentradeBizDate.calcFeqBizDate(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_request.marketCode, 
            l_tsSysDate, 
            -1);
        
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainer(
                null, null, null, null, l_datPreBizDate);
            
        //1.3 createソート条件(外国株式ソートキー[])
        //ソート条件を作成する。 
        //[引数] 
        //ソートキー一覧：　@null
        String l_strSortCond = this.createSortCond(null);

        //1.4 get注文単位一覧(補助口座, ProductTypeEnum, String, String[], String)
        //条件に該当する注文単位の一覧を取得する。 
        //[引数] 
        //補助口座：　@パラメータ.補助口座 
        //銘柄タイプ：　@ProductTypeEnum.外国株式 
        //検索条件文字列：　@create検索条件文字列()の戻り値 
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        //ソート条件：　@createソート条件()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        List l_lisOrderUnits = new ArrayList();
        l_lisOrderUnits = 
            l_feqOrderManager.getOrderUnits(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_strQueryString, 
                l_strQueryDataContainers, 
                l_strSortCond);

        //1.5 (*)該当データなし(get注文単位一覧()の戻り値 == null)の場合
        if (l_lisOrderUnits == null)
        {
            //1.5.1  (*)レスポンス.銘柄情報一覧にnullをセット。
            l_response.productCodeNames = null;
            
            return;
        }
        
        //1.6  TreeMap( )
        //TreeMapを生成する。
        TreeMap l_treeMap = new TreeMap();

        log.debug("注文単位一覧.size() = " + l_lisOrderUnits.size());
        
        //1.7 (*)get注文単位一覧()の要素数分Loop処理
        for (int i = 0; i < l_lisOrderUnits.size(); i++)
        {
            WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_lisOrderUnits.get(i);
            
            //1.7.1 getProduct( ) 外国株式銘柄を取得する。
            WEB3FeqProduct l_feqProduct = (WEB3FeqProduct)
                l_feqOrderUnit.getProduct();
            
            if (l_feqProduct == null)
            {
                log.debug("Error in 外国株式銘柄を取得する");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.7.2 get(arg0 : Object)
            //TreeMapから要素を取得する。 
            //[引数] 
            //arg0：　@getProduct()の戻り値.銘柄コード
            WEB3FeqProductCodeNameUnit l_feqProductCodeNameUnit = null;
            
            if (l_treeMap.get(l_feqProduct.getProductCode()) != null)
            {
                l_feqProductCodeNameUnit = 
                    (WEB3FeqProductCodeNameUnit)l_treeMap.get(l_feqProduct.getProductCode());
            }
            //1.7.3 (*)リストに未追加(get()の戻り値 == null)の場合、以下の処理を実施する。
            if (l_feqProductCodeNameUnit == null)
            {
                //1.7.3.1 外国株式銘柄情報インスタンスを生成する。
                l_feqProductCodeNameUnit = new WEB3FeqProductCodeNameUnit();
                
                //1.7.3.2 (*)プロパティセット
                //(*)外国株式銘柄情報インスタンスに以下のプロパティをセットする。
                //銘柄コード   ＝　@getProduct()の戻り値.銘柄コード
                l_feqProductCodeNameUnit.productCode = 
                    l_feqProduct.getProductCode();
                
                log.debug("外国株式銘柄情報.銘柄コード = " + l_feqProductCodeNameUnit.productCode);
                
                //銘柄名     ＝　@getProduct()の戻り値.get表示銘柄名()の戻り値
                l_feqProductCodeNameUnit.productName = 
                    l_feqProduct.getDisplayProductName();
                
                log.debug("外国株式銘柄情報.銘柄名 = " + l_feqProductCodeNameUnit.productName);
                
                //1.7.3.3  put(arg0 : Object, arg1 : Object)
                //TreeMapにプロパティセットしたインスタンスを追加する。 
                //[引数] 
                //arg0：　@getProduct()の戻り値.銘柄コード 
                //arg1：　@プロパティセットした外国株式銘柄情報インスタンス
                l_treeMap.put(
                    l_feqProduct.getProductCode(), l_feqProductCodeNameUnit);                
            }
        }
        
        //1.8 values( ).toArray( )
        //外国株式銘柄情報インスタンスの配列を生成する。
        WEB3FeqProductCodeNameUnit[] l_feqProductCodeNameUnits = 
            new WEB3FeqProductCodeNameUnit[l_treeMap.size()];
        
        l_treeMap.values().toArray(l_feqProductCodeNameUnits);
        
        //1.9 (*)レスポンス.銘柄情報一覧 = values().toArray()の戻り値をセットする。　@
        //※戻り値の要素数 == 0の場合は、nullをセットする。
        if (l_feqProductCodeNameUnits != null)
        {
            l_response.productCodeNames = l_feqProductCodeNameUnits;
        }
        else
        {
            l_response.productCodeNames = null;
        }        
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (create注文明細一覧)<BR>
     * 顧客に該当する外国株式注文明細一覧を作成し、<BR>
     * レスポンスデータにセットする。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）create注文明細一覧」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定照会リクエストオブジェクト
     * @@param l_response - (レスポンスデータ)<BR>
     * 外国株式注文約定照会レスポンスオブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 429EF7910069
     */
    protected void createOrderDetailList(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqExecuteReferenceRequest l_request, 
        WEB3FeqExecuteReferenceResponse l_response) 
            throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = "createOrderDetailList(" +
            "WEB3GentradeSubAccount l_subAccount, WEB3FeqExecuteReferenceRequest l_request, " +
                "WEB3FeqExecuteReferenceResponse l_response)";        
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_request == null || l_response == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 create検索条件文字列(String, String, String, Date)
        //検索条件文字列を作成する。 
        //[引数] 
        //銘柄コード：　@リクエストデータ.銘柄コード 
        //市場コード：　@リクエストデータ.市場コード 
        //約定状態区分：　@リクエストデータ.約定状態区分 
        //発注日：　@リクエストデータ.発注日
        String l_strQueryString = 
            this.createQueryString(
                l_request.productCode, l_request.marketCode, 
                l_request.execType, l_request.orderBizDate);
        
        //1.2 検索条件データコンテナを作成する。 
        //[引数] 
        //証券会社：　@パラメータ.補助口座.getInstitution() 
        //銘柄コード：　@リクエストデータ.銘柄コード 
        //市場コード：　@リクエストデータ.市場コード 
        //約定状態区分：　@リクエストデータ.約定状態区分 
        //発注日：　@リクエストデータ.発注日
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        
        String[] l_strQueryDataContainer = 
            this.createQueryDataContainer(
                l_institution, 
                l_request.productCode, 
                l_request.marketCode, 
                l_request.execType, 
                l_request.orderBizDate);
        
        //1.3 createソート条件(外国株式ソートキー[])
        //ソート条件を作成する。 
        //[引数] 
        //ソートキー一覧：　@リクエストデータ.ソートキー
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.4 get注文単位一覧(補助口座, ProductTypeEnum, String, String[], String)
        //条件に該当する注文単位の一覧を取得する。 
        //[引数] 
        //補助口座：　@パラメータ.補助口座 
        //銘柄タイプ：　@ProductTypeEnum.外国株式 
        //検索条件文字列：　@create検索条件文字列()の戻り値 
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        //ソート条件：　@createソート条件()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        List l_lisOrderUnits = new ArrayList();
        
        l_lisOrderUnits = 
            l_feqOrderManager.getOrderUnits(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_strQueryString, 
                l_strQueryDataContainer, 
                l_strSortCond);
        
        if (l_lisOrderUnits == null)
        {
            log.debug("Error in 注文単位オブジェクトを取得する");
			//レスポンスデータに初期値をセットする。
			//レスポンス.注文一覧  ＝　@null
			l_response.executeGroups = null;
            
			//レスポンス.表示ページ番号   ＝　@0
			l_response.pageIndex = 0 + "";
            
            //レスポンス.総ページ数 ＝　@0
			l_response.totalPages = 0 + "";
            
			//レスポンス.総レコード数    ＝　@0
			l_response.totalRecords = 0 + "";
            
			return;
        }
        
        //1.5 remove繰越元注文単位(外国株式注文単位[])
        //繰越元注文の除去を行う。 
        //[引数] 
        //注文単位一覧：　@get注文単位一覧()の戻り値.toArray()
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            new WEB3FeqOrderUnit[l_lisOrderUnits.size()];
        
        l_lisOrderUnits.toArray(l_feqOrderUnits);
        WEB3FeqOrderUnit[] l_carryOrderUnits = 
            l_feqOrderManager.removeCarryOverOriginOrder(l_feqOrderUnits);
        
        WEB3FeqOrderUnit[] l_feqOrderUnitResults = l_carryOrderUnits;
                         
        //1.6 (*)リクエストデータ.照会区分 == "訂正取消一覧"の場合
        if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(
            l_request.referenceType))
        {
            log.debug("リクエストデータ.照会区分 == '訂正取消一覧'の場合");
            
            //1.6.1 remove訂正取消不可注文単位(外国株式注文単位[])
            //訂正取消不可な注文単位を除去する。 
            //[引数] 
            //注文単位一覧：　@remove繰越元注文単位()の戻り値
            WEB3FeqOrderUnit[] l_changeCancelOrderUnits = 
                this.removeChangeCancelNotOrderUnit(l_carryOrderUnits);
            
            l_feqOrderUnitResults = l_changeCancelOrderUnits;
        }
        //remove訂正取消不可注文単位()メソッドをコールしている場合は、
        //その戻り値について判別する。
        //1.7 (*)該当データなし(remove繰越元注文単位()の戻り値 == null)の場合
        if (l_feqOrderUnitResults == null)
        {
            log.debug("remove繰越元注文単位()の戻り値 == nullの場合");
            //1.7.1 (*)プロパティセット
            //(*)レスポンスデータに初期値をセットする。
            //レスポンス.注文一覧  ＝　@null
            l_response.executeGroups = null;
            
            //レスポンス.表示ページ番号   ＝　@0
            l_response.pageIndex = 0 + "";
            
            //レスポンス.総ページ数 ＝　@0
            l_response.totalPages = 0 + "";
            
            //レスポンス.総レコード数    ＝　@0
            l_response.totalRecords = 0 + "";
            
            //1.7.2 
            return;
        }
        //1.8 WEB3PageIndexInfo()
        //WEB3PageIndexInfoインスタンスを生成する。 
        //[引数] 
        //arg0：　@remove繰越元注文単位()の戻り値 
        //※remove訂正取消不可注文単位()をコールしている場合は、その戻り値 
        //arg1：　@リクエストデータ.要求ページ番号 
        //arg2：　@リクエストデータ.ページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_feqOrderUnitResults, 
            Integer.parseInt(l_request.pageIndex), 
            Integer.parseInt(l_request.pageSize));
        
        //1.9 getArrayReturned(arg0 : Class)
        //表示対象の注文単位一覧を取得する。 
        //[引数] 
        //arg0：　@外国株式注文単位.class
        l_feqOrderUnitResults = (WEB3FeqOrderUnit[])
            l_pageIndexInfo.getArrayReturned(
                WEB3FeqOrderUnit.class);
        
        List l_lisFeqExecuteGroup = new ArrayList();
        
        //1.10 (*)表示対象の注文単位(getArrayReturned()の戻り値)数分Loop処理
        for (int i = 0; i < l_feqOrderUnitResults.length; i++)
        {
            WEB3FeqOrderUnit l_feqOrderUnitLoop = l_feqOrderUnitResults[i];
            
            //1.10.1 get市場( )(外国株式注文単位::get市場)
            //市場オブジェクトを取得する。
            Market l_market = l_feqOrderUnitLoop.getMarket();
            
            //1.10.2 reset市場コード(String)(取引時間管理::reset市場コード)
            //取引カレンダコンテキスト.市場コードを再セットする。 
            //[引数] 
            //市場コード：　@get市場()の戻り値.市場コード
            String l_strMarketCode = null;
            if (l_market != null)
            {
                l_strMarketCode = l_market.getMarketCode();
            }
                
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            //リクエストデータ.照会区分 == "訂正取消一覧"の場合は、
            //既にチェック済みである為、is訂正取消注文単位()の戻り値は、一律trueとする。
            boolean l_blnIsChangeCancelOrder = true;
            
            //1.10.3  (*)リクエストデータ.照会区分 == "注文約定照会"の場合
            if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
                l_request.referenceType))
            {
                log.debug("リクエストデータ.照会区分 == '注文約定照会'の場合");
                
                //1.10.3.1 is訂正取消可能注文単位(外国株式注文単位)
                //訂正取消可能な注文単位かどうか判別する。 
                //[引数] 
                //注文単位：　@処理対象の注文単位
                l_blnIsChangeCancelOrder = 
                    this.isChangeCancelOrderUnit(l_feqOrderUnitLoop);
            }
            //1.10.4 is訂正可能(外国株式注文単位)
            //訂正可能な注文単位であるかどうか判別する。 
            //[引数] 
            //注文単位：　@処理対象の注文単位
            boolean l_blnIsChangePossible = 
                this.isChangePossible(l_feqOrderUnitLoop);

            //1.10.5 is取消可能(外国株式注文単位)
            //取消可能な注文単位であるかどうか判別する。 
            //[引数] 
            //注文単位：　@処理対象の注文単位
            boolean l_blnIsCancelPossible = 
                this.isCancelPossible(l_feqOrderUnitLoop);

            //1.10.6 getProduct( )
            //外国株式銘柄オブジェクトを取得する。
            WEB3FeqProduct l_feqProduct = 
                (WEB3FeqProduct)l_feqOrderUnitLoop.getProduct();
            
            if (l_feqProduct == null)
            {
                log.debug("Error in 外国株式銘柄を取得する");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.10.7 is買付( )(外国株式注文単位::is買付)
            //買付注文かどうか判別する。 
            boolean l_blnIsBuy = l_feqOrderUnitLoop.isBuy();

            //1.10.8 is指値( )(外国株式注文単位::is指値)
            //指値注文かどうか判別する。
            boolean l_blnIsLimit = l_feqOrderUnitLoop.isLimitOrder();
            
            //1.10.9 get処理状況区分( )(外国株式注文単位::get処理状況区分)
            //処理状況区分を取得する。
            String l_strTransactionStatetype = 
                l_feqOrderUnitLoop.getTransactionStateType();
            
            //1.10.10 get執行条件（SONAR）(String)
            //執行条件（SONAR）を取得する。 
            //[引数] 
            //執行条件：　@処理対象の注文単位.執行条件
            String l_strExecCondTypeSonar = 
                l_feqOrderManager.getExecutionConditionTypeSonar(                
                    l_feqOrderUnitLoop.getExecutionConditionType().intValue() + "");
                
            //1.10.11 is出来るまで注文単位(FeqOrderUnit)
            //出来るまで注文かどうか判別する。 
            //[引数] 
            //注文単位：　@処理対象の注文単位
            boolean l_blnIsCarriedOrderUnit = 
                l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnitLoop);
            
            //1.10.12 外国株式注文明細インスタンスを生成する。
            WEB3FeqExecuteGroup l_feqExecuteGroup = new WEB3FeqExecuteGroup();
            
            //1.10.13 (*)プロパティセット
            //(*)外国株式注文明細インスタンスに以下のプロパティをセットする。
            FeqOrderUnitParams l_feqOrderUnitParams = 
                new FeqOrderUnitParams(
                    (FeqOrderUnitRow) l_feqOrderUnitLoop.getDataSourceObject());
            
            log.debug("注文単位Params = " + l_feqOrderUnitParams);
            
            //注文ID            ＝　@注文単位.注文ID
            l_feqExecuteGroup.id = l_feqOrderUnitParams.getOrderId() + "";
            
            //銘柄コード       ＝　@getProduct()の戻り値.銘柄コード
            l_feqExecuteGroup.productCode = l_feqProduct.getProductCode();
            
            //現地銘柄コード     ＝　@getProduct()の戻り値.現地銘柄コード
            l_feqExecuteGroup.localProductCode = 
                l_feqProduct.getOffshoreProductCode();
            
            //銘柄名         ＝　@getProduct()の戻り値.get表示銘柄名()の戻り値
            l_feqExecuteGroup.productName = 
                l_feqProduct.getDisplayProductName();
            
            //特定口座区分      ＝　@注文単位.税区分 == "一般"の場合、"一般"をセット。
            //                                  "特定"の場合、"特定"をセット。
            if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitParams.getTaxType()))
            {
                l_feqExecuteGroup.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitParams.getTaxType()))
            {
                l_feqExecuteGroup.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            
            //市場コード       ＝　@get市場()の戻り値.市場コード
            l_feqExecuteGroup.marketCode = l_strMarketCode;
            
            //売買区分        ＝　@is買付()の戻り値 == trueの場合、"買付"をセット。　@
            //                 以外、"売付"をセット。
            if (l_blnIsBuy)
            {
                l_feqExecuteGroup.dealingType = WEB3BuySellTypeDef.BUY;
            }
            else
            {
                l_feqExecuteGroup.dealingType = WEB3BuySellTypeDef.SELL;
            }            
            //決済区分        ＝　@注文単位.決済区分
            l_feqExecuteGroup.settleDiv = l_feqOrderUnitParams.getSettleDiv();
            
            //執行条件        ＝　@get執行条件（SONAR）の戻り値
            l_feqExecuteGroup.execCondType = l_strExecCondTypeSonar;
            
            //発注条件        ＝　@注文単位.発注条件
            l_feqExecuteGroup.orderCondType = 
                l_feqOrderUnitParams.getOrderConditionType();
            
            //発注日         ＝　@注文単位.発注日
            l_feqExecuteGroup.orderBizDate = WEB3DateUtility.getDate(
                l_feqOrderUnitParams.getBizDate(), "yyyyMMdd");
            
            //注文有効期限      ＝　@is出来るまで注文単位()の戻り値 == trueの場合、
            //                   注文単位.注文失効日付をセット。     
            if (l_blnIsCarriedOrderUnit)
            {
                l_feqExecuteGroup.expirationDate = 
                    l_feqOrderUnitParams.getExpirationDate();
            }            
            //注文時間        ＝　@注文単位.作成日付
            l_feqExecuteGroup.orderDate = 
                l_feqOrderUnitParams.getCreatedTimestamp();
            
            //注文数量        ＝　@注文単位.数量
            l_feqExecuteGroup.orderQuantity = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getQuantity());
            
            //注文単価区分      ＝　@is指値()の戻り値 == trueの場合、"指値"をセット。　@
            //                   以外、"成行"をセット。
            if (l_blnIsLimit)  
            {
                l_feqExecuteGroup.orderPriceDiv = 
                    WEB3OrderPriceDivDef.LIMIT_PRICE;
                
                //注文単価        ＝　@is指値()の戻り値 == trueの場合、注文単位.指値をセット。
                l_feqExecuteGroup.limitPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqOrderUnitParams.getLimitPrice());
            }
            else
            {
                l_feqExecuteGroup.orderPriceDiv = 
                    WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            
            //通貨コード       ＝　@注文単位.通貨コード
            l_feqExecuteGroup.currencyCode = 
                l_feqOrderUnitParams.getCurrencyCode();
            
            //(*1)注文単位.isUnExecuted() == falseの場合のみセット。
            if(!l_feqOrderUnitLoop.isUnexecuted())
            {
                log.debug("注文単位.isUnExecuted() == falseの場合");
                //約定数量(*1)        ＝　@注文単位.約定数量
                l_feqExecuteGroup.execQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_feqOrderUnitParams.getExecutedQuantity());
            						
				//約定単価(*1)        ＝
				//注文単位.合計約定金額(外貨) ／ 注文単位.約定数量
                BigDecimal l_bdExecutedAmount =
                    new BigDecimal(
                        String.valueOf(l_feqOrderUnitParams.getExecutedAmount()));
                BigDecimal l_bdExecutedQuantity =
                    new BigDecimal(
                        String.valueOf(l_feqOrderUnitParams.getExecutedQuantity()));
                BigDecimal l_bdExecPrice =
                    l_bdExecutedAmount.divide(l_bdExecutedQuantity, 6, BigDecimal.ROUND_HALF_EVEN);
                l_feqExecuteGroup.execPrice =
                    WEB3StringTypeUtility.formatNumber(l_bdExecPrice.doubleValue());
            }
            
            //受渡代金        ＝　@注文単位.概算受渡代金
            l_feqExecuteGroup.estimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getEstimatedPrice());
            
            //受渡代金（外貨）        ＝　@注文単位.概算受渡代金（外貨）
            l_feqExecuteGroup.foreignEstimatedPrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_feqOrderUnitParams.getFEstimatedPrice());
            
            //処理状況        ＝　@get処理状況区分()の戻り値
            l_feqExecuteGroup.transactionStateType = l_strTransactionStatetype;
            
            //訂正可能フラグ     ＝　@is訂正取消可能注文単位()の戻り値 == true かつ 
            //                   is訂正可能()の戻り値 == trueの場合、trueをセット。
            //　@　@　@　@　@　@　@　@　@　@以外、falseをセット。
            if (l_blnIsChangeCancelOrder && l_blnIsChangePossible)
            {
                l_feqExecuteGroup.changeFlag = true;                
            }
            else
            {
                l_feqExecuteGroup.changeFlag = false;
            }
            //取消可能フラグ     ＝　@is訂正取消可能注文単位()の戻り値 == true かつ 
            //                   is取消可能()の戻り値 == trueの場合、trueをセット。
            //　@　@　@　@　@　@　@　@　@　@以外、falseをセット。
            if (l_blnIsChangeCancelOrder && l_blnIsCancelPossible)
            {
                l_feqExecuteGroup.cancelFlag = true;
            }
            else
            {
                l_feqExecuteGroup.cancelFlag = false;
            }
            
            //(*2)this.get代理入力者() != null(コールセンターからの参照)の場合のみセット。
            if (this.getTrader() != null)
            {
                //注文チャネル(*2)      ＝　@注文単位.初回注文の注文チャネル
                l_feqExecuteGroup.orderChannel = 
                    l_feqOrderUnitParams.getOrderChanel();
                
                //注文経路区分(*2)      ＝　@注文単位.注文経路区分
                l_feqExecuteGroup.orderRootDiv = 
                    l_feqOrderUnitParams.getOrderRootDiv();
                
                //扱者コード(*2)       ＝　@
                //拡張金融オブジェクトマネージャ.getTrader(注文単位.取引者ID).扱者コードをセット。
                
                WEB3GentradeFinObjectManager l_finObjManager = 
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                try
                {
                	//(注文単位.取引者ID != null)の場合のみ処理実行
                	if(!l_feqOrderUnitParams.getTraderIdIsNull()){
						String l_strTraderCode = l_finObjManager.getTrader(
							l_feqOrderUnitParams.getTraderId()).getTraderCode();
                    
						l_feqExecuteGroup.traderCode = l_strTraderCode;
                	}
                     
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("__NotFoundException__", l_ex);
                    //例外をスローする
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }   
            }
            //分割約定一覧(*1)      ＝　@this.create分割約定一覧(注文単位)
            //(*1)注文単位.isUnExecuted() == falseの場合のみセット。
            if(!l_feqOrderUnitLoop.isUnexecuted())
            {
                l_feqExecuteGroup.executeUnits = 
                    this.createExecuteUnits(l_feqOrderUnitLoop); 
            }
            //1.10.14 add(arg0 : Object)
            //ArrayListにプロパティセットしたインスタンスを追加する。 
            //[引数] 
            //arg0：　@プロパティセットした外国株式注文明細インスタンス
            l_lisFeqExecuteGroup.add(l_feqExecuteGroup);
        }

        //1.11 toArray( )
        //外国株式注文明細の配列を生成する。
        WEB3FeqExecuteGroup[] l_feqExecuteGroups = 
            new WEB3FeqExecuteGroup[l_lisFeqExecuteGroup.size()];
        
        l_lisFeqExecuteGroup.toArray(l_feqExecuteGroups);

        //1.12 (*)プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        
        //レスポンス.注文一覧  ＝　@toArray()の戻り値
        l_response.executeGroups = l_feqExecuteGroups;
        
        //レスポンス.表示ページ番号   ＝　@WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        
        //レスポンス.総ページ数 ＝　@WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        
        //レスポンス.総レコード数    ＝　@WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get発注日一覧)<BR>
     * 発注日の一覧を作成し、返却する。<BR>
     * <BR>
     * 返却値、返却順は以下のとおり。<BR>
     * 　@①@業務日付(*1)の前営業日　@※照会区分 == 注文約定照会の場合のみ。<BR>
     * 　@②業務日付<BR>
     * 　@③パラメータ.市場コード一覧の市場について発注日を取得し、<BR>
     * 　@　@①@、②と異なる場合は追加。(①@、②と同一の場合はセットしない)<BR>
     * 　@　@※③は引け後の発注日の考慮<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.照会区分 == "注文約定照会"の場合、<BR>
     * 　@　@　@業務日付の前営業日をArrayListに追加する。<BR>
     * <BR>
     * ３）　@業務日付をArrayListに追加する。<BR>
     * <BR>
     * ４）　@パラメータ.市場コード一覧の要素数分、以下の処理を繰り返す。<BR>
     * 　@４－１）　@取引時間管理.reset市場コード()をコールする。<BR>
     * <BR>
     * 　@　@[reset市場コード()に指定する引数]<BR>
     * 　@　@　@市場コード：　@処理対象の市場コード<BR>
     * 　@４－２）　@取引時間管理.get発注日()をコールする。<BR>
     * 　@４－３）　@４－２）の戻り値がArrayListに含まれていない場合は、<BR>
     * 　@　@ArrayListに追加する。<BR>
     * <BR>
     * ５）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_strReferenceType - (照会区分)<BR>
     * 照会区分<BR>
     * <BR>
     * 0：注文約定照会<BR>
     * 1：訂正取消一覧<BR>
     * @@param l_strMarketCodeList - (市場コード一覧)<BR>
     * 部店が取扱可能な市場コードの一覧
     * @@return java.util.Date[]
     * @@throws WEB3BaseException
     * @@roseuid 429EFBBC000B
     */
    protected Date[] getOrderBizDateList(
        String l_strReferenceType, 
        String[] l_strMarketCodes) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDateList(" +
            "String l_strReferenceType, String[] l_strMarketCodes)";
        
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListを生成する。 
        List l_lisBizDates = new ArrayList();
        
        //業務日付 (*1)GtlUtils.getTradingSystem().getBizDate()
        Date l_datSysDate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsSysDate = new Timestamp(l_datSysDate.getTime());
        
        //２）　@パラメータ.照会区分 == "注文約定照会"の場合、 
        //　@　@業務日付の前営業日をArrayListに追加する。   //QA88
        if (WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(
            l_strReferenceType))
        {
            log.debug("パラメータ.照会区分 == '注文約定照会'の場合");
            
            //市場コード一覧に対応する全営業日を全て取得し
            for (int i = 0; i < l_strMarketCodes.length; i++)
            {
                WEB3GentradeBizDate l_gentradeBizDate = 
                    new WEB3GentradeBizDate(
                        new Timestamp(l_datSysDate.getTime()));
                    
                Date l_datPreBizDate = l_gentradeBizDate.calcFeqBizDate(
                    this.getSubAccount().getInstitution().getInstitutionCode(), 
                    l_strMarketCodes[i], 
                    l_tsSysDate, 
                    -1);
                
                log.debug("業務日付の前営業日 = " + l_datPreBizDate);
                
                boolean l_blnIsContain = 
                    WEB3Toolkit.contain(l_lisBizDates, l_datPreBizDate);
                
                //重複しない値のみArrayListに追加して下さい。
                if (!l_blnIsContain)
                {
                    l_lisBizDates.add(l_datPreBizDate);
                }                
            }            
        }
        
        //３）　@業務日付をArrayListに追加する。 
        l_lisBizDates.add(l_datSysDate);
        
        //４）　@パラメータ.市場コード一覧の要素数分、以下の処理を繰り返す。 
        //４－１）　@取引時間管理.reset市場コード()をコールする。 
        //[reset市場コード()に指定する引数] 
        //　@市場コード：　@処理対象の市場コード 
        //４－２）　@取引時間管理.get発注日()をコールする。 
        //４－３）　@４－２）の戻り値がArrayListに含まれていない場合は、 
        //ArrayListに追加する。 
        for (int i = 0; i < l_strMarketCodes.length; i++)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                l_strMarketCodes[i]);
            
            Date l_datBizDte = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            log.debug("取引時間管理.get発注日() = " + l_datBizDte);
            boolean l_blnIsContain = 
                WEB3Toolkit.contain(l_lisBizDates, l_datBizDte);
            
            if (!l_blnIsContain)
            {
                l_lisBizDates.add(l_datBizDte);
            }
        }
        
        //５）　@ArrayList.toArray()の戻り値を返却する。
        log.debug("発注日一覧.size = " + l_lisBizDates.size());
        Date[] l_datOrderBizDates = new Date[l_lisBizDates.size()];
        l_lisBizDates.toArray(l_datOrderBizDates);
        
        log.exiting(STR_METHOD_NAME);
        return l_datOrderBizDates;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * (createQueryString)<BR>
     * 検索条件文字列を作成し、返却する。<BR>
     * <BR>
     * １）　@銘柄条件作成<BR>
     * 　@パラメータ.銘柄コード != nullの場合、銘柄条件を<BR>
     * 　@検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += " and product_id = ?"<BR>
     * <BR>
     * ２）　@市場条件作成<BR>
     * 　@パラメータ.市場コード != nullの場合、市場条件を<BR>
     * 　@検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += " and market_id = ?"<BR>
     * <BR>
     * ３）　@約定状態条件作成<BR>
     * 　@パラメータ.約定状態区分 != nullの場合、約定状態条件を<BR>
     * 　@検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[パラメータ.約定状態区分 == "未約定"の場合]<BR>
     * 　@　@検索条件文字列 += " and (executed_quantity is null"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " or executed_quantity = ?)"<BR>
     * <BR>
     * 　@[パラメータ.約定状態区分 == "一部成立" または "約定処理中(一部成立)" の場合]<BR>
     * 　@　@検索条件文字列 += " and executed_quantity is not null"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " and executed_quantity != ?"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " and executed_quantity < confirmed_quantity"<BR>
     * <BR>
     * 　@[パラメータ.約定状態区分 == "全部成立" または "約定処理中(全部成立)" の場合]<BR>
     * 　@　@検索条件文字列 += " and executed_quantity is not null"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " and executed_quantity != ?"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " and executed_quantity = confirmed_quantity"<BR>
     * <BR>
     * ４）　@仮約定状態条件作成<BR> 
     * 　@パラメータ.約定状態区分 != nullの場合、仮約定状態条件を<BR>
     * 　@検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += " and temporary_execution_flag = ?"<BR>
     * <BR>
     * ５）　@発注日条件作成<BR>
     * 　@発注日条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[パラメータ.発注日 == nullの場合]<BR>
     * 　@　@検索条件文字列 += " and biz_date >= ?"<BR>
     * <BR>
     * 　@[パラメータ.発注日 != nullの場合]<BR>
     * 　@　@検索条件文字列 += " and biz_date = ?"<BR>
     * <BR>
     * ６）　@作成した検索条件文字列を返却する。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@param l_strExecType - (約定状態区分)<BR>
     * 約定状態区分<BR>
     * <BR>
     * 0：未約定<BR>
     * 1：一部成立<BR>
     * 2：全部成立<BR>
     * 3：約定処理中(一部成立)<BR>
     * 4：約定処理中(全部成立)<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 発注日<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@return String
     * @@roseuid 42A3B2A901F5
     */
    protected String createQueryString(
        String l_strProductCode, 
        String l_strMarketCode, 
        String l_strExecType, 
        Date l_datOrderBizDate) 
    {
        final String STR_METHOD_NAME = "createQueryString(" +
            "String l_strProductCode, String l_strMarketCode, " +
            "String l_strExecType, Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@銘柄条件作成 
        //パラメータ.銘柄コード != nullの場合、銘柄条件を 
        //検索条件文字列に追加する。 
        //検索条件文字列 += " and product_id = ?" 
        String l_strQueryString = "";
        if (l_strProductCode != null)
        {
            l_strQueryString += " and product_id = ?";
        }        
        //２）　@市場条件作成 
        //パラメータ.市場コード != nullの場合、市場条件を 
        //検索条件文字列に追加する。 
        //検索条件文字列 += " and market_id = ?" 
        if (l_strMarketCode != null)
        {
            l_strQueryString += " and market_id = ?";
        }
        //３）　@約定状態条件作成 
        //パラメータ.約定状態区分 != nullの場合、約定状態条件を 
        //検索条件文字列に追加する。 

        if (l_strExecType != null)
        {
            //[パラメータ.約定状態区分 == "未約定"の場合] 
            //検索条件文字列 += " and (executed_quantity is null" 
            //　@　@　@　@　@　@　@ + " or executed_quantity = ?)"            
            if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(
                l_strExecType))
            {
                l_strQueryString += " and (executed_quantity is null"  + 
                    " or executed_quantity = ?)" ;
            }
            
            //[パラメータ.約定状態区分 == "一部成立" または "約定処理中(一部成立)" の場合] 
            //検索条件文字列 += " and executed_quantity is not null" 
            //　@　@　@　@　@　@　@ + " and executed_quantity != ?" 
            //　@　@　@　@　@　@　@ + " and executed_quantity < confirmed_quantity"            
            else if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_strExecType)
                        || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(l_strExecType))
            {
                l_strQueryString += " and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity < confirmed_quantity";
            }   
            
            //[パラメータ.約定状態区分 == "全部成立" または "約定処理中(全部成立)" の場合] 
            //検索条件文字列 += " and executed_quantity is not null" 
            //　@　@　@　@　@　@　@ + " and executed_quantity != ?" 
            //　@　@　@　@　@　@　@ + " and executed_quantity = confirmed_quantity" 
            else if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_strExecType)
                        || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(l_strExecType))
            {
                l_strQueryString += " and executed_quantity is not null"  + 
                    " and executed_quantity != ?" + 
                    " and executed_quantity = confirmed_quantity";
            }
        }
        
        // 仮約定状態条件作成
        // 仮約定状態条件を検索条件文字列に追加する。
        if (l_strExecType != null)
        {
            l_strQueryString += " and temporary_execution_flag = ?";
        }
        
        //４）　@発注日条件作成 
        //発注日条件を検索条件文字列に追加する。        
        //[パラメータ.発注日 == nullの場合] 
        //　@検索条件文字列 += " and biz_date >= ?" 
        if (l_datOrderBizDate == null)
        {
            l_strQueryString += " and biz_date >= ?";
        }
        //[パラメータ.発注日 != nullの場合] 
        //　@検索条件文字列 += " and biz_date = ?" 
        else
        {
            l_strQueryString += " and biz_date = ?";
        }
        
        //５）作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * (createQueryDataContainer)<BR>
     * 検索条件データコンテナ(：String[]）を作成し、返却する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@銘柄条件作成<BR>
     * 　@パラメータ.銘柄コード != nullの場合、銘柄IDを<BR>
     * 　@ArrayListに追加する。<BR>
     * <BR>
     * 　@※銘柄IDは、外国株式プロダクトマネージャ.getFeqProduct()にて取得した<BR>
     * 　@　@外国株式銘柄.銘柄IDを使用する。<BR>
     * <BR>
     * 　@　@[getFeqProduct()に指定する引数]<BR>
     * 　@　@　@arg0：　@パラメータ.証券会社<BR>
     * 　@　@　@arg1：　@パラメータ.銘柄コード<BR>
     * <BR>
     * ３）　@市場条件作成<BR>
     * 　@パラメータ.市場コード != nullの場合、市場IDを<BR>
     * 　@ArrayListに追加する。<BR>
     * <BR>
     * 　@※市場IDは、拡張金融オブジェクトマネージャ.get市場()にて取得した<BR>
     * 　@　@市場.市場IDを使用する。<BR>
     * <BR>
     * 　@　@[get市場()に指定する引数]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード<BR>
     * 　@　@　@市場コード：　@パラメータ.市場コード<BR>
     * <BR>
     * <BR>
     * ４）　@約定状態条件作成<BR>
     * 　@パラメータ.約定状態区分 != nullの場合、"0"を<BR>
     * 　@ArrayListに追加する。<BR>
     * <BR>
     * ５）　@仮約定状態条件作成<BR> 
     * 　@パラメータ.約定状態区分 != nullの場合、以下の仮約定状態条件をArrayListに追加する。<BR> 
     * <BR>
     * 　@[パラメータ.約定状態区分 == "約定処理中(一部成立)" または "約定処理中(全部成立)" の場合]<BR> 
     * 　@　@・"仮約定"<BR>
     * <BR>
     * 　@[上記以外の場合]<BR> 
     * 　@　@・DEFAULT<BR>
     * <BR>
     * ６）　@発注日条件作成<BR>
     * 　@以下の発注日条件をArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.発注日 == nullの場合]<BR>
     * 　@　@・業務日付(*1)のYYYYMMDD<BR>
     * <BR>
     * 　@[パラメータ.発注日 != nullの場合]<BR>
     * 　@　@・パラメータ.発注日のYYYYMMDD<BR>
     * <BR>
     * ７）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@param l_strExecType - (約定状態区分)<BR>
     * 約定状態区分<BR>
     * <BR>
     * 0：未約定<BR>
     * 1：一部成立<BR>
     * 2：全部成立<BR>
     * 3：約定処理中(一部成立)<BR>
     * 4：約定処理中(全部成立)<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 発注日<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 42A3B61D03D9
     */
    protected String[] createQueryDataContainer(
            WEB3GentradeInstitution l_institution, 
            String l_strProductCode, 
            String l_strMarketCode, 
            String l_strExecType, 
            Date l_datOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(" +
            "WEB3GentradeInstitution l_institution, String l_strProductCode, " +
            "String l_strMarketCode, String l_strExecType, " +
            "Date l_datOrderBizDate) ";        
        
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListを生成する。
        List l_lisQueryContainer = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //２）　@銘柄条件作成 
        //パラメータ.銘柄コード != nullの場合、銘柄IDをArrayListに追加する。 
        if (l_strProductCode != null)
        {
            log.debug("パラメータ.銘柄コード != nullの場合");            

            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            
            WEB3FeqProductManager l_feqProductManager = 
                (WEB3FeqProductManager)l_tradingModule.getProductManager();
                    
            FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = 
                    l_feqProductManager.getFeqProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)                
            {
                log.debug("__NotFound 外国株式銘柄__", l_ex);
                //例外をスローする
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            String l_strProductId = l_feqProduct.getProductId() + "";
            l_lisQueryContainer.add(l_strProductId);         
        }
    
        //３）　@市場条件作成 
        //パラメータ.市場コード != nullの場合、市場IDを 
        //ArrayListに追加する。 

        //※市場IDは、拡張金融オブジェクトマネージャ.get市場()にて取得した 
        //　@市場.市場IDを使用する。 

        //　@[get市場()に指定する引数] 
        //　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード 
        //　@　@市場コード：　@パラメータ.市場コード 
        WEB3GentradeFinObjectManager l_finObjManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;

        if (l_strMarketCode != null)
        {
            log.debug("パラメータ.市場コード != nullの場合");
            try
            {
                l_market = l_finObjManager.getMarket(
                    l_institution.getInstitutionCode(), 
                    l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.debug("__NotFoundException__", l_ex);
                //例外をスローする
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            String l_strMarketId = l_market.getMarketId() + "";
            l_lisQueryContainer.add(l_strMarketId);
        }
        
        //４）　@約定状態条件作成 
        //パラメータ.約定状態区分 != nullの場合、"0"をArrayListに追加する。 
        if (l_strExecType != null)
        {
            log.debug("パラメータ.約定状態区分 != nullの場合");
            l_lisQueryContainer.add(WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE);
        }
        
        // 仮約定状態条件作成
        if (l_strExecType != null)
        {
            // [パラメータ.約定状態区分 == "約定処理中(一部成立)" または "約定処理中(全部成立)" の場合]
            if (WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(l_strExecType)
                || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(l_strExecType))
            {
                l_lisQueryContainer.add(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
            }
            // [上記以外の場合]
            else
            {
                l_lisQueryContainer.add(WEB3TemporaryExecutionFlagDef.DEFAULT);
            }            
        }
        
        //５）　@発注日条件作成 
        //以下の発注日条件をArrayListに追加する。 
        //[パラメータ.発注日 == nullの場合] 
        //　@・業務日付(*1)のYYYYMMDD 
        //(*1)GtlUtils.getTradingSystem().getBizDate() 

        if (l_datOrderBizDate == null)
        {
            log.debug("パラメータ.発注日 == nullの場合");
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(
                GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
        }
        //[パラメータ.発注日 != nullの場合] 
        //　@・パラメータ.発注日のYYYYMMDD 
        else
        {
            log.debug("パラメータ.発注日 != nullの場合");
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(
                l_datOrderBizDate, "yyyyMMdd"));
        }
        
       //６）　@ArrayList.toArray()の戻り値を返却する。 
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }
    
    /**
     * (createソート条件)<BR>
     * (createSortCond)<BR>
     * ソート条件を作成し、返却する。<BR>
     * <BR>
     * １）　@パラメータ.ソートキー一覧 == nullの場合、<BR>
     * 　@"銘柄ID 昇順"のソート条件を返却する。<BR>
     * <BR>
     * ２）　@パラメータ.ソートキー一覧の要素数分、<BR>
     * 対応するテーブルの列物理名、<BR>
     * 　@　@昇順／降順指定を付加しセット<BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り<BR>
     * 　@　@　@※テーブル名：外国株式注文単位テーブル(feq_order_unit)<BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照<BR>
     * <BR>
     * 　@　@　@　@変換前　@　@　@　@　@　@　@　@　@変換後<BR>
     *        -------------　@　@　@　@-----------------------------<BR>
     * 　@　@　@　@・銘柄コード  　@　@　@　@　@：外国株式注文単位テーブル．銘柄ID<BR>
     * 　@　@　@　@・特定口座区分　@　@　@ ：外国株式注文単位テーブル．税区分<BR>
     *         ・市場コード  　@　@　@　@　@：外国株式注文単位テーブル．市場ID<BR>
     * 　@　@　@　@・売買区分 　@　@　@　@　@　@：外国株式注文単位テーブル．注文種別<BR>
     * 　@　@　@　@・決済区分　@　@　@　@　@　@ ：外国株式注文単位テーブル．決済区分<BR>
     * 　@　@　@　@・執行条件 　@　@　@　@　@　@：外国株式注文単位テーブル．執行条件<BR>
     * 　@　@　@　@・発注条件 　@　@　@　@　@　@：外国株式注文単位テーブル．発注条件<BR>
     * 　@　@　@　@・注文時間 　@　@　@　@　@　@：外国株式注文単位テーブル．作成日付<BR>
     * 　@　@　@　@・発注日    　@　@　@　@　@　@：外国株式注文単位テーブル．発注日<BR>
     * 　@　@　@　@・注文有効期限 　@　@　@：外国株式注文単位テーブル．注文失効日付<BR>
     * <BR>
     * 　@　@・昇順／降順指定は、外国株式ソートキー.昇順／降順 指定に従い設定<BR>
     * <BR>
     * ３）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加する。<BR>
     * <BR>
     * ４）　@作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー一覧)
     * @@return String
     * @@roseuid 42A3B777030E
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "createSortCond(" +
            "WEB3ForeignSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_strSortCond = new StringBuffer();
        
        //１）　@パラメータ.ソートキー一覧 == nullの場合、 
        //"銘柄ID 昇順"のソート条件を返却する。
        if (l_sortKeys == null)
        {
            l_strSortCond.append(" product_id");
            
            log.exiting(STR_METHOD_NAME);
            return l_strSortCond.toString();
        }

        //２）　@パラメータ.ソートキー一覧の要素数分、対応するテーブルの列物理名、 
        //昇順／降順指定を付加しセット 

        //・キー項目とテーブルの列名称との対応は以下の通り 
        //　@※テーブル名：外国株式注文単位テーブル(feq_order_unit) 
        //　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照 
        //　@※テーブルの列物理名は、テーブルレイアウトを参照
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //・銘柄コード  　@　@　@　@　@：外国株式注文単位テーブル．銘柄ID 
            if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" product_id");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" product_id desc");
                }
            }
            //・特定口座区分　@　@　@ ：外国株式注文単位テーブル．税区分 
            else if (WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" tax_type");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" tax_type desc");
                }
            }
            //・市場コード  　@　@　@　@　@：外国株式注文単位テーブル．市場ID 
            else if (WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" market_id");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" market_id desc");
                }
            }
            //・売買区分 　@　@　@　@　@　@：外国株式注文単位テーブル．注文種別
            else if (WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_type");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_type desc");
                }
            }
            //・決済区分　@　@　@　@　@　@ ：外国株式注文単位テーブル．決済区分
            else if (WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" settle_div");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" settle_div desc");
                }
            }
            //・執行条件 　@　@　@　@　@　@：外国株式注文単位テーブル．執行条件
            else if (WEB3FeqSortKeyItemNameDef.EXEC_COND_TYPE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" execution_condition_type");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" execution_condition_type desc");
                }
            }
            //・発注条件 　@　@　@　@　@　@：外国株式注文単位テーブル．発注条件
            else if (WEB3FeqSortKeyItemNameDef.ORDER_COND_TYPE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_condition_type");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" order_condition_type desc");
                }
            }
            //・注文時間 　@　@　@　@　@　@：外国株式注文単位テーブル．作成日付
            else if (WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" created_timestamp");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" created_timestamp desc");
                }
            }
            //・発注日    　@　@　@　@　@　@：外国株式注文単位テーブル．発注日
            else if (WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" biz_date");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" biz_date desc");
                }
            }
            //・注文有効期限 　@　@　@：外国株式注文単位テーブル．注文失効日付
            else if (WEB3FeqSortKeyItemNameDef.EXPIRATION_DATE.equals(
                l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" expiration_date");
                }
                //    ・昇順/降順 == "D"（降順） の場合
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortCond.append(" expiration_date desc");
                }
            }
            
            l_strSortCond.append(",");
            
        }
        //３）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加する。 
        l_strSortCond.append(" last_updated_timestamp");
        
        //４）　@作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond.toString();
    }
    
    /**
     * (remove訂正取消不可注文単位)<BR>
     * 引数の注文単位一覧から訂正取消不可な注文単位を<BR>
     * 除去する。<BR>
     * <BR>
     * １）　@パラメータ.注文単位一覧 == nullの場合、<BR>
     * 　@nullを返却して終了する。<BR>
     * <BR>
     * ２）　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@パラメータ.注文単位一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@３－１）　@取引時間管理.reset市場コード()をコールする。<BR>
     * <BR>
     * 　@　@[reset市場コード()に指定する引数]<BR>
     * 　@　@　@市場コード：　@処理対象の注文単位.get市場().市場コード<BR>
     * <BR>
     * 　@３－２）　@this.is訂正取消可能注文単位()をコールする。<BR>
     * 　@　@falseが返却された場合、次の要素へ処理を移行する。(continue)<BR>
     * <BR>
     * 　@　@[is訂正取消可能注文単位()に指定する引数]<BR>
     * 　@　@　@注文単位：　@処理対象の注文単位<BR>
     * <BR>
     * 　@３－３）　@this.is訂正可能()、is取消可能()をコールする。<BR>
     * 　@　@両方ともfalseを返却した場合、次の要素へ処理を移行する。(continue)<BR>
     * <BR>
     * 　@　@[is訂正可能()、is取消可能()に指定する引数]<BR>
     * 　@　@　@注文注文単位：　@処理対象の注文単位<BR>
     * <BR>
     * 　@３－４）　@ArrayListに処理対象の注文単位を追加する。<BR>
     * <BR>
     * ４）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * 　@　@　@※ArrayListのサイズ == 0の場合、nullを返却する。<BR>
     * @@param l_orderUnits - (注文単位一覧)<BR>
     * 外国株式注文単位オブジェクトの配列
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A3DED80089
     */
    protected WEB3FeqOrderUnit[] removeChangeCancelNotOrderUnit(
        WEB3FeqOrderUnit[] l_orderUnits) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "removeChangeCancelNotOrderUnit(" +
            "WEB3FeqOrderUnit[] l_orderUnits)";
       
        log.entering(STR_METHOD_NAME);
        
        //１）　@パラメータ.注文単位一覧 == nullの場合、 
        //nullを返却して終了する。 
        if (l_orderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２）　@ArrayListを生成する。 
        List l_lisFeqOrderUnits = new ArrayList();
        
        //３）　@パラメータ.注文単位一覧の要素数分、 
        //　@以下の処理を繰り返す。 
        //　@３－１）　@取引時間管理.reset市場コード()をコールする。 
        //　@[reset市場コード()に指定する引数] 
        //　@　@市場コード：　@処理対象の注文単位.get市場().市場コード 
        for (int i = 0; i < l_orderUnits.length; i++)
        {
            String l_strMarketCode = l_orderUnits[i].getMarket().getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
            
            //３－２）　@this.is訂正取消可能注文単位()をコールする。 
            //falseが返却された場合、次の要素へ処理を移行する。(continue) 
            //[is訂正取消可能注文単位()に指定する引数] 
            //　@注文単位：　@処理対象の注文単位 
            boolean l_blnIsChangeCancel = 
                this.isChangeCancelOrderUnit(l_orderUnits[i]);
            if (!l_blnIsChangeCancel)
            {
                log.debug("this.is訂正取消可能注文単位(),falseが返却された場合");
                continue;
            }
            
            //３－３）　@this.is訂正可能()、is取消可能()をコールする。 
            //両方ともfalseを返却した場合、次の要素へ処理を移行する。(continue) 
            //[is訂正可能()、is取消可能()に指定する引数] 
            //　@注文注文単位：　@処理対象の注文単位 
            boolean l_blnIsChange = this.isChangePossible(l_orderUnits[i]);
            boolean l_blnIsCancel = this.isCancelPossible(l_orderUnits[i]);
            log.debug("is訂正可能() = " + l_blnIsChange);
            log.debug("is取消可能() = " + l_blnIsCancel);
            
            if (!l_blnIsChange && !l_blnIsCancel)
            {
                log.debug("this.is訂正可能()、is取消可能(),両方ともfalseを返却した場合");
                continue;
            }
            
            //３－４）　@ArrayListに処理対象の注文単位を追加する。
            l_lisFeqOrderUnits.add(l_orderUnits[i]);
        }
        //４）　@ArrayList.toArray()の戻り値を返却する。 
        //　@※ArrayListのサイズ == 0の場合、nullを返却する。
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            new WEB3FeqOrderUnit[l_lisFeqOrderUnits.size()];

        l_lisFeqOrderUnits.toArray(l_feqOrderUnits);
                
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnits;
    }
    
    /**
     * (is訂正取消可能注文単位)<BR>
     * 引数の注文単位が訂正取消可能な注文単位であるか判別する。<BR>
     * (訂正取消共通チェック)<BR>
     * <BR>
     * 訂正取消可能な場合はtrueを、以外falseを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）is訂正取消可能注文単位」参照<BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 42A3E99102BB
     */
    protected boolean isChangeCancelOrderUnit(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeCancelOrderUnit(" +
            "WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        //1.1 validate取引銘柄(外国株式銘柄, 市場)
        //取引銘柄の売買規制チェックを行う。 
        //[引数] 
        //外国株式銘柄：　@パラメータ.注文単位.getProduct() 
        //市場：　@パラメータ.注文単位.get市場()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();        
        
        try
        {            
            l_feqOrderManager.validateTradedProduct(
                (WEB3FeqProduct)l_feqOrderUnit.getProduct(), 
                (WEB3GentradeMarket)l_feqOrderUnit.getMarket());
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("取引銘柄チェックエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //1.2 validate取扱可能市場(String, String, String)
        //取扱可能な市場かどうか判別する。 
        //[引数] 
        //証券会社コード：　@パラメータ.注文単位.get証券会社コード 
        //部店コード：　@パラメータ.注文単位.get部店コード 
        //市場コード：　@パラメータ.注文単位.get市場().市場コード
        try
        {
            l_feqOrderManager.validateHandlingPossibleMarket(
                l_feqOrderUnit.getInstitutionCode(),
                l_feqOrderUnit.getBranchCode(), 
                l_feqOrderUnit.getMarket().getMarketCode());
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("市場チェックエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //1.3 getOrderValidator( )
        //注文チェックオブジェクトを取得する。        
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        //1.4 validate取引可能顧客(顧客)(注文チェック::validate取引可能顧客)
        //取引可能顧客かどうかチェックする。 
        //[引数] 
        //補助口座：　@パラメータ.注文単位.get補助口座()
        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(
                l_feqOrderUnit.getSubAccount());
            
        log.debug("注文チェック.validate取引可能顧客(): isFailedResult = " + 
                l_validationResult.getProcessingResult().isFailedResult());
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        boolean l_blnIsTradeOpenTimeZone = 
            WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        
        //1.5 (*)場引け後(is市場開局時間帯() == false)　@かつ　@
        //      ホスト受付済当日注文(市場から確認済みの数量 != NaN)の場合
        if (!l_blnIsTradeOpenTimeZone && !Double.isNaN(
            l_feqOrderUnit.getConfirmedQuantity()))
        {
            log.debug("is市場開局時間帯() == false かつ 市場から確認済みの数量 != NaN の場合");
            
            //1.5.1 validate閉局後訂正取消受付可能(ProductTypeEnum)
            //閉局後に訂正取消注文が受付可能であるかチェックする。 
            //[引数] 
            //銘柄タイプ：　@ProductTypeEnum.外国株式
            try
            {
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.FOREIGN_EQUITY);
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("取引可能顧客チェエラー", l_ex);
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        //上記validateメソッドのいずれかが例外をスロー、またはチェックNG
        //だった場合は、falseを返却する。
        //全てチェックOKの場合は、trueを返却する。
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (is訂正可能)<BR>
     * 引数の注文単位が訂正可能な注文か判別する。<BR>
     * <BR>
     * １）　@注文受付トランザクションの再セット<BR>
     * 　@取引時間管理.reset注文受付トランザクション()をコールする。<BR>
     * <BR>
     * 　@[reset注文受付トランザクション()に指定する引数]<BR>
     * 　@　@注文受付トランザクション：　@"訂正"<BR>
     * <BR>
     * ２）　@訂正可能チェック<BR>
     * 　@以下のメソッドをコールし、一つでも例外がスローされた場合はfalseを<BR>
     * 　@以外、trueを返却する。<BR>
     * 　@※戻り値を返却する前に、reset注文受付トランザクション()にて<BR>
     * 　@　@注文受付トランザクションを"照会"に再セットする。<BR>
     * 　@　@・取引時間管理.validate注文受付可能()<BR>
     * 　@　@・外国株式注文マネージャ.validate注文訂正可能状態()<BR>
     * 　@　@・外国株式注文マネージャ.validate取引銘柄()<BR>
     * 　@　@・外国株式注文マネージャ.validate顧客銘柄別取引停止()<BR>
     * <BR>
     * 　@　@　@[validate注文訂正可能状態()に指定する引数]<BR>
     * 　@　@　@　@注文ID：　@パラメータ.注文単位.注文ID<BR>
     * <BR>
     * 　@　@　@[validate外株銘柄()に指定する引数]<BR>
     * 　@　@　@　@外国株式銘柄：　@パラメータ.注文単位.getProduct()<BR>
     * 　@　@　@　@市場：　@パラメータ.注文単位.get市場()<BR>
     * 　@　@　@　@is買注文：　@パラメータ.注文単位.is買付()<BR>
     * <BR>
     * 　@　@　@[validate顧客銘柄別取引停止()に指定する引数]<BR>
     * 　@　@　@　@補助口座：　@パラメータ.注文単位.補助口座IDに該当する補助口座<BR>
     * 　@　@　@　@銘柄ID：　@パラメータ.注文単位.銘柄ID<BR>
     * 　@　@　@　@注文種別：　@パラメータ.注文単位.注文種別<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 42A3E9A5027D
     */
    protected boolean isChangePossible(WEB3FeqOrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangePossible(" +
                "WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@注文受付トランザクションの再セット 
        //取引時間管理.reset注文受付トランザクション()をコールする。 
        //[reset注文受付トランザクション()に指定する引数] 
        //　@注文受付トランザクション：　@"訂正" 
        WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CHANGE);
        
        //２）　@訂正可能チェック 
        //以下のメソッドをコールし、一つでも例外がスローされた場合はfalseを 
        //以外、trueを返却する。 
        //※戻り値を返却する前に、reset注文受付トランザクション()にて 
        //　@注文受付トランザクションを"照会"に再セットする。 
        //　@・取引時間管理.validate注文受付可能() 
        //　@・外国株式注文マネージャ.validate注文訂正可能状態() 
        //　@・外国株式注文マネージャ.validate取引銘柄() 
        //　@・外国株式注文マネージャ.validate顧客銘柄別取引停止() 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        try
        {
            //取引時間管理.validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //[validate注文訂正可能状態()に指定する引数] 
            //注文ID：　@パラメータ.注文単位.注文ID 
            l_feqOrderManager.validateOrderChangePossibleStatus(
                l_orderUnit.getOrderId());
            
            //[validate取引銘柄()に指定する引数] 
            //外国株式銘柄：　@パラメータ.注文単位.getProduct() 
            //市場：　@パラメータ.注文単位.get市場() 
            //is買注文：　@パラメータ.注文単位.is買付() 
            l_feqOrderManager.validateTradedProduct(
                (WEB3FeqProduct)l_orderUnit.getProduct(), 
                l_orderUnit.getMarket(), 
                l_orderUnit.isBuy());
            
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            long l_lngSubAccountId = 
                l_orderUnit.getSubAccount().getSubAccountId();
            
            long l_lngAccountId = l_orderUnit.getAccountId();
            
            SubAccount l_subAccount = l_accountManager.getSubAccount(
                l_lngAccountId, l_lngSubAccountId);
            
            long l_lngProductId = l_orderUnit.getProduct().getProductId();
            log.debug("test1@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            //[validate顧客銘柄別取引停止()に指定する引数] 
            //　@補助口座：　@パラメータ.注文単位.補助口座IDに該当する補助口座 
            //　@銘柄ID：　@パラメータ.注文単位.銘柄ID 
            //　@注文種別：　@パラメータ.注文単位.注文種別 
            l_feqOrderManager.validateAccountProductTradedStop(
                l_subAccount, 
                l_lngProductId, 
                l_orderUnit.getOrderType());
            
            log.debug("test2@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        catch (NotFoundException l_ex)
        {
            log.error("___NotFoundException___" , l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(WEB3BaseException l_ex)
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
            
            log.exiting(STR_METHOD_NAME);
            return false;            
        }
    }
    
    /**
     * (is取消可能)<BR>
     * 引数の注文単位が取消可能な注文か判別する。<BR>
     * <BR>
     * １）　@注文受付トランザクションの再セット<BR>
     * 　@取引時間管理.reset注文受付トランザクション()をコールする。<BR>
     * <BR>
     * 　@[reset注文受付トランザクション()に指定する引数]<BR>
     * 　@　@注文受付トランザクション：　@"取消"<BR>
     * <BR>
     * ２）　@取消可能チェック<BR>
     * 　@以下のメソッドをコールし、一つでも例外がスローされた場合はfalseを<BR>
     * 　@以外、trueを返却する。<BR>
     * 　@※戻り値を返却する前に、reset注文受付トランザクション()にて<BR>
     * 　@　@注文受付トランザクションを"照会"に再セットする。<BR>
     * 　@　@・取引時間管理.validate注文受付可能()<BR>
     * 　@　@・外国株式注文マネージャ.validate注文取消可能状態()<BR>
     * <BR>
     * 　@　@　@[validate注文取消可能状態()に指定する引数]<BR>
     * 　@　@　@　@注文ID：　@パラメータ.注文単位.注文ID<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return Boolean
     * @@roseuid 42A7A4BD00DF
     */
    protected boolean isCancelPossible(WEB3FeqOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "isCancelPossible(" +
            "WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@注文受付トランザクションの再セット 
        //取引時間管理.reset注文受付トランザクション()をコールする。 
        //[reset注文受付トランザクション()に指定する引数] 
        //　@注文受付トランザクション：　@"取消" 
        WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.CANCEL);

        //２）　@取消可能チェック 
        //以下のメソッドをコールし、一つでも例外がスローされた場合はfalseを 
        //以外、trueを返却する。 
        //※戻り値を返却する前に、reset注文受付トランザクション()にて 
        //　@　@注文受付トランザクションを"照会"に再セットする。 
        //　@・取引時間管理.validate注文受付可能() 
        //　@・外国株式注文マネージャ.validate注文取消可能状態()       

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        try
        {
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //[validate注文取消可能状態()に指定する引数] 
            //注文ID：　@パラメータ.注文単位.注文ID 
            l_feqOrderManager.validateOrderCancelPossibleStatus(
                l_orderUnit.getOrderId());            

            log.exiting(STR_METHOD_NAME);
            return true;
        }        
        catch(WEB3BaseException l_ex)
        {
            WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
            
            log.exiting(STR_METHOD_NAME);
            return false;            
        }        
    }
    
    /**
     * (create分割約定一覧)<BR>
     * 引数の注文単位より外国株式約定情報の一覧を <BR>
     * 作成し、返却する。 <BR>
     * <BR>
     * １）　@ArrayListを生成する。 <BR>
     * <BR>
     * ２）　@パラメータ.注文単位.getExecutions()をコールする。 <BR>
     * <BR>
     * ３）　@２）の戻り値の要素数分以下の処理を繰り返す。 <BR>
     * 　@３－１）　@外国株式約定情報インスタンスを生成する。 <BR>
     * 　@３－２）　@生成したインスタンスに以下のプロパティをセットする。 <BR>
     * <BR>
     * 　@　@約定日時　@＝　@処理対象の約定.約定日時 <BR>
     * 　@　@約定数量　@＝　@処理対象の約定.約定数量 <BR>
     * 　@　@約定単価　@＝　@処理対象の約定.約定単価 <BR>
     * 　@３－３）　@プロパティセットしたインスタンスをArrayListに追加する。<BR> 
     * <BR>
     * ４）　@ArrayList.toArray()の戻り値を返却する。 <BR>
     * 　@　@　@※ArrayList.size() == 0の場合、nullを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return WEB3FeqExecuteUnit[]
     * @@roseuid 42A53E7202FC
     */
    protected WEB3FeqExecuteUnit[] createExecuteUnits(
        WEB3FeqOrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "createExecuteUnits(" +
            "WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@ArrayListを生成する。 
        List l_lisFeqExecuteUnit = new ArrayList();
        
        //２）　@パラメータ.注文単位.getExecutions()をコールする。
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
        
        //３）　@２）の戻り値の要素数分以下の処理を繰り返す。 
        for (int i = 0; i < l_orderExecutions.length; i++)
        {
            //３－１）　@外国株式約定情報インスタンスを生成する。 
            WEB3FeqExecuteUnit l_feqExecuteUnit = new WEB3FeqExecuteUnit();
            
            //３－２）　@生成したインスタンスに以下のプロパティをセットする。 
            //　@約定日時　@＝　@処理対象の約定.約定日時 
            l_feqExecuteUnit.executionTimestamp = 
                l_orderExecutions[i].getExecutionTimestamp();
            
            log.debug("約定日時 = " + l_feqExecuteUnit.executionTimestamp);
            
            //　@約定数量　@＝　@処理対象の約定.約定数量 
            l_feqExecuteUnit.execQuantity = WEB3StringTypeUtility.formatNumber(
                l_orderExecutions[i].getExecutionQuantity());
            
            log.debug("約定数量 = " + l_feqExecuteUnit.execQuantity);
            
            //　@約定単価　@＝　@処理対象の約定.約定単価 
            l_feqExecuteUnit.execPrice = WEB3StringTypeUtility.formatNumber(
                l_orderExecutions[i].getExecutionPrice());            

            log.debug("約定単価 = " + l_feqExecuteUnit.execPrice);
            
            //３－３）　@プロパティセットしたインスタンスをArrayListに追加する。
            l_lisFeqExecuteUnit.add(l_feqExecuteUnit);
        }
        
        //４）　@ArrayList.toArray()の戻り値を返却する。 
        //　@※ArrayList.size() == 0の場合、nullを返却する。
        WEB3FeqExecuteUnit[] l_feqExecuteUnits = 
            new WEB3FeqExecuteUnit[l_lisFeqExecuteUnit.size()];
        
        l_lisFeqExecuteUnit.toArray(l_feqExecuteUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqExecuteUnits;
    }
    
    /**
     * (create注文詳細情報)<BR>
     * 引数の注文単位より外国株式注文詳細情報を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）create注文詳細情報」参照<BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return WEB3FeqOrderDetailInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 42A4402D0071
     */
    protected WEB3FeqOrderDetailInfoUnit createOrderDetailsInformation(
        WEB3FeqOrderUnit l_feqOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderDetailsInformation(" +
            "WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }    
        
        //1.1 外国株式注文詳細情報インスタンスを生成する。
        WEB3FeqOrderDetailInfoUnit l_feqOrderDetailInfoUnit = 
            new WEB3FeqOrderDetailInfoUnit();
        
        //1.2 create外国株式注文共通明細(外国株式注文共通明細, 外国株式注文単位)
        //スーパークラスのプロパティセットを行う。 
        //[引数] 
        //外国株式注文共通明細：　@生成したインスタンス 
        //注文単位：　@パラメータ.注文単位
        WEB3FeqCommonMessageCreatedService l_commonMessageCreatedService = 
            new WEB3FeqCommonMessageCreatedServiceImpl();

        l_commonMessageCreatedService.createFeqOrderCommonUnit(
            l_feqOrderDetailInfoUnit, 
            l_feqOrderUnit);
        
        //(*)生成したインスタンスにプロパティをセットする。

        //注文有効期限 ＝ 外国株式注文マネージャ.is出来るまで注文(パラメータ.注文単位) == false
        //　@　@　@　@　@　@　@　@の場合のみnullをセット。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_blnIsCarriedOrderUnit = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);
        
        if (!l_blnIsCarriedOrderUnit)
        {
            l_feqOrderDetailInfoUnit.expirationDate = null;
        }
        
        //注文経路区分      ＝　@パラメータ.注文単位.注文経路区分
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        l_feqOrderDetailInfoUnit.orderRootDiv = 
            l_feqOrderUnitParams.getOrderRootDiv();
        
        //注文状態区分      ＝　@パラメータ.注文単位.get注文状態区分()
        String l_strOrderStatusDiv = 
            l_feqOrderUnit.getOrderStatusDiv();
        
        l_feqOrderDetailInfoUnit.orderState = 
            l_feqOrderUnit.getOrderStatusDiv();
        
        //訂正取消区分      ＝　@パラメータ.注文単位.注文訂正・取消区分
        l_feqOrderDetailInfoUnit.changeCancelDiv = 
            l_feqOrderUnitParams.getModifyCancelType();
        
        //繰越エラーコード        ＝　@get注文状態区分()の戻り値 == "繰越失敗"の場合、
        //　@　@　@　@　@　@　@　@　@　@　@　@　@  パラメータ.注文単位.注文エラー理由コードをセット。
        if (WEB3OrderStatusDef.NOT_TRANSFERED.equals(l_strOrderStatusDiv))
        {
            l_feqOrderDetailInfoUnit.transferErrCode = 
                l_feqOrderUnitParams.getErrorReasonCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderDetailInfoUnit;
    }
    
    /**
     * (create約定詳細情報)<BR>
     * 引数の注文単位より外国株式約定詳細情報を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）create約定詳細情報」参照<BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return WEB3FeqExecuteDetailInfoUnit
     * @@WEB3BaseException
     * @@roseuid 42A44089036F
     */
    protected WEB3FeqExecuteDetailInfoUnit createExecuteDetailsInformation(
        WEB3FeqOrderUnit l_feqOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createExecuteDetailsInformation(" +
        "WEB3FeqOrderUnit l_feqOrderUnit)";
    log.entering(STR_METHOD_NAME);
    
    if (l_feqOrderUnit == null)
    {
        log.debug("パラメータNull出来ない。");
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
            this.getClass().getName() + "." + STR_METHOD_NAME);
    }    
    
    //1.1 外国株式注文詳細情報インスタンスを生成する。
    WEB3FeqExecuteDetailInfoUnit l_feqExecuteDetailInfoUnit = 
        new WEB3FeqExecuteDetailInfoUnit();
    
    //1.2 get約定状態区分( )(外国株式注文単位::get約定状態区分)
    //約定状態区分を取得する。
    String l_strExecStatusDiv = l_feqOrderUnit.getExecStatusDiv();

    //1.3 (*)未約定(get約定状態区分()の戻り値 == "未約定")の場合  
    if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(
        l_strExecStatusDiv))
    {
        log.debug("未約定(get約定状態区分()の戻り値 == '未約定')の場合");
        //1.3.1 (*)約定状態区分にget約定状態区分()の戻り値をセット。
        l_feqExecuteDetailInfoUnit.execType = l_strExecStatusDiv;
        
        //1.3.2 
        log.exiting(STR_METHOD_NAME);
        return l_feqExecuteDetailInfoUnit;
    }
    //1.4 getトランザクション(外国株式注文単位)
    //(外国株式トランザクションマネージャ::getトランザクション)
    //注文単位に紐づくトランザクションを取得する。 
    //[引数] 
    //注文単位：　@パラメータ.注文単位
    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    TradingModule l_tradingModule = l_finApp.getTradingModule(
        ProductTypeEnum.FOREIGN_EQUITY);
    
    WEB3FeqFinTransactionManager l_finTransactionManager = 
        (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
    
    List l_lisTransaction = 
        l_finTransactionManager.getTransactions(l_feqOrderUnit);
    
    double l_dblDeliveryPerice = 0.0D; 
    BigDecimal l_bdNetAmountFc = new BigDecimal("0"); 
    BigDecimal l_bdForeignCommissionFee = new BigDecimal("0"); 
    BigDecimal l_bdForeignTax = new BigDecimal("0"); 
    BigDecimal l_bdForeignFeeExt1 = new BigDecimal("0"); 
    BigDecimal l_bdForeignFeeExt2 = new BigDecimal("0"); 
    double l_dblBalanceAmount = 0.0D; 
    BigDecimal l_bdBalanceAmountFc = new BigDecimal("0"); 
    double l_dblCommissionFee = 0.0D; 
    double l_dblCommissionFeeTax = 0.0D; 
    BigDecimal l_bdCommissionFeeFc = new BigDecimal("0"); 
    BigDecimal l_bdCommissionFeeTaxFc = new BigDecimal("0"); 
    
    log.debug("getトランザクション()の戻り値の要素数 = " + l_lisTransaction.size());
    
    //1.5.1 (*)getトランザクション()の戻り値の要素数分Loop処理
    for (int i = 0; i < l_lisTransaction.size(); i++)
    {
        //(*)処理対象トランザクションの以下の値を集計する。
        FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow) 
            l_lisTransaction.get(i);     
        log.debug("トランザクションRow = " + l_transactionRow);
        
        //集計した受渡代金            += トランザクション.getNetAmount()
        l_dblDeliveryPerice += l_transactionRow.getNetAmount();
        
        //集計した受渡代金（外貨）        += トランザクション.getNetAmountFc()
        l_bdNetAmountFc =
            l_bdNetAmountFc.add(new BigDecimal(String.valueOf(l_transactionRow.getNetAmountFc())));
        
        //集計した現地手数料         += トランザクション.getForeignCommissionFee()
        l_bdForeignCommissionFee =
            l_bdForeignCommissionFee.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignCommissionFee())));
        
        l_feqExecuteDetailInfoUnit.localCommission = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignCommissionFee.doubleValue());
        
        //集計した現地取引税           += トランザクション.getForeignTax()
        l_bdForeignTax =
            l_bdForeignTax.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignTax())));
        
        l_feqExecuteDetailInfoUnit.localTradingTax = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignTax.doubleValue());
        
        //集計したその他コスト１     += トランザクション.getForeignFeeExt1()
        l_bdForeignFeeExt1 =
            l_bdForeignFeeExt1.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignFeeExt1())));
        
        l_feqExecuteDetailInfoUnit.otherCost1 = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignFeeExt1.doubleValue());
        
        //集計したその他コスト２     += トランザクション.getForeignFeeExt2()
        l_bdForeignFeeExt2 =
            l_bdForeignFeeExt2.add(new BigDecimal(String.valueOf(l_transactionRow.getForeignFeeExt2())));
        
        l_feqExecuteDetailInfoUnit.otherCost2 = 
            WEB3StringTypeUtility.formatNumber(l_bdForeignFeeExt2.doubleValue());
        
        //集計した清算代金            += トランザクション.getBalanceAmount()
        l_dblBalanceAmount += l_transactionRow.getBalanceAmount();
        
        l_feqExecuteDetailInfoUnit.clearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_dblBalanceAmount);
        
        //集計した清算代金（外貨）        += トランザクション.getBalanceAmountFc()
        l_bdBalanceAmountFc =
            l_bdBalanceAmountFc.add(new BigDecimal(String.valueOf(l_transactionRow.getBalanceAmountFc())));
        
        l_feqExecuteDetailInfoUnit.foreignClearUpPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdBalanceAmountFc.doubleValue());
        
        //集計した国内手数料           += トランザクション.getCommissionFee()
        l_dblCommissionFee += l_transactionRow.getCommissionFee();
        
        l_feqExecuteDetailInfoUnit.commission = 
            WEB3StringTypeUtility.formatNumber(l_dblCommissionFee);
        
        //集計した国内手数料消費税        += トランザクション.getCommissionFeeTax()
        l_dblCommissionFeeTax += l_transactionRow.getCommissionFeeTax();
        
        l_feqExecuteDetailInfoUnit.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_dblCommissionFeeTax);
        
        //集計した国内手数料（外貨）       += トランザクション.getCommissionFeeFc()
        l_bdCommissionFeeFc =
            l_bdCommissionFeeFc.add(new BigDecimal(String.valueOf(l_transactionRow.getCommissionFeeFc())));
        
        l_feqExecuteDetailInfoUnit.foreignCommission = 
            WEB3StringTypeUtility.formatNumber(l_bdCommissionFeeFc.doubleValue());
        
        //集計した国内手数料消費税（外貨）    += トランザクション.getCommissionFeeTaxFc()
        l_bdCommissionFeeTaxFc =
            l_bdCommissionFeeTaxFc.add(new BigDecimal(String.valueOf(l_transactionRow.getCommissionFeeTaxFc())));
        
        l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_bdCommissionFeeTaxFc.doubleValue());
    }
    
    //1.6 ArrayListを生成する。
    List l_lisExecDetailInfoUnit = new ArrayList();
    
    //1.7 getExecutions( )
    //注文単位に紐づく約定の一覧を取得する。
    OrderExecution[] l_orderExecutions = (OrderExecution[])
        l_feqOrderUnit.getExecutions();
        
    //1.8 (*)getExecutions()の戻り値の要素数分Loop処理
    for (int i = 0; i < l_orderExecutions.length; i++)
    {
        WEB3FeqOrderExecution l_feqOrderExecution = 
            (WEB3FeqOrderExecution) l_orderExecutions[i];
        
        //1.8.1 getトランザクション(FeqOrderExecution)
        //約定に対応するトランザクションを取得する。 
        //[引数] 
        //約定：　@約定
        FeqFinTransactionParams l_transactionParams = 
            l_finTransactionManager.getTransaction(l_feqOrderExecution);
        
        log.debug("トランザクションParams = " + l_transactionParams);

        //1.8.2 外国株式約定詳細（管理者）インスタンスを生成する。
        WEB3FeqExecDetailInfoUnit l_feqExecDetailInfoUnit = 
            new WEB3FeqExecDetailInfoUnit();
        
        //1.8.3 create外国株式約定詳細（管理者）
        //生成した外国株式約定詳細（管理者）インスタンスにプロパティをセットする。 
        //[引数] 
        //外国株式約定詳細（管理者）：　@生成したインスタンス 
        //約定：　@約定 
        //トランザクション（取引勘定明細）行：　@getトランザクション()の戻り値
        WEB3FeqCommonMessageCreatedService l_commonMessageCreatedService = 
            new WEB3FeqCommonMessageCreatedServiceImpl();
        
        l_commonMessageCreatedService.createAdminFeqExecDetailInfoUnit(
            l_feqExecDetailInfoUnit, 
            l_feqOrderExecution, 
            l_transactionParams);
        
        //1.8.4 (*)約定日時 = 処理対象の約定.約定日時をセットする。
        l_feqExecDetailInfoUnit.executionTimestamp = 
            l_feqOrderExecution.getExecutionTimestamp();
        
        //1.8.5 add(arg0 : Object)
        //ArrayListにプロパティセットしたインスタンスを追加する。 
        //[引数] 
        //arg0：　@プロパティセットした外国株式約定詳細（管理者）インスタンス
        l_lisExecDetailInfoUnit.add(l_feqExecDetailInfoUnit);            
    }
    
    //1.9 toArray( )      
    WEB3FeqExecDetailInfoUnit[] l_feqExecDetailInfoUnit = 
        new WEB3FeqExecDetailInfoUnit[l_lisExecDetailInfoUnit.size()];
    
    l_lisExecDetailInfoUnit.toArray(l_feqExecDetailInfoUnit);

    WEB3FeqOrderExecution l_feqOrderExecution = 
        (WEB3FeqOrderExecution) l_orderExecutions[0];
    
    //1.10 (*)生成したインスタンスに以下のプロパティをセットする。
    
    //約定数量        ＝　@パラメータ.注文単位.約定数量
    FeqOrderUnitParams l_feqOrderUnitparams = new FeqOrderUnitParams(
        (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject());        
   
    l_feqExecuteDetailInfoUnit.execQuantity = 
        WEB3StringTypeUtility.formatNumber(
            l_feqOrderUnitparams.getExecutedQuantity());

    log.debug("約定数量 = " + l_feqExecuteDetailInfoUnit.execQuantity);

    //約定単価  ＝　@パラメータ.注文単位.合計約定金額（外貨）　@／　@パラメータ.注文単位.約定数量
    BigDecimal l_bdExecutedAmount =
        new BigDecimal(
            String.valueOf(l_feqOrderUnitparams.getExecutedAmount()));
    BigDecimal l_bdExecutedQuantity =
        new BigDecimal(
            String.valueOf(l_feqOrderUnitparams.getExecutedQuantity()));
    BigDecimal l_bdExecPrice =
        l_bdExecutedAmount.divide(l_bdExecutedQuantity, 6, BigDecimal.ROUND_HALF_EVEN);
    l_feqExecuteDetailInfoUnit.execPrice =
        WEB3StringTypeUtility.formatNumber(l_bdExecPrice.doubleValue());

    log.debug("約定単価 = " + l_feqExecuteDetailInfoUnit.execPrice);
    
    //約定状態区分      ＝　@get約定状態区分()の戻り値
    l_feqExecuteDetailInfoUnit.execType = l_strExecStatusDiv;
        
    log.debug("約定状態区分 = " + l_feqExecuteDetailInfoUnit.execType);
    
    //約定明細一覧      ＝　@toArray()の戻り値
    l_feqExecuteDetailInfoUnit.execDetailList = l_feqExecDetailInfoUnit;
            
    log.debug("約定明細一覧 = " + l_feqExecuteDetailInfoUnit.execDetailList);
    
    // 注文単位.get約定状態区分()の戻り値が"約定処理中(一部成立)"または"約定処理中(全部成立)"の場合、
    if (!(WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv())
        || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(l_feqOrderUnit.getExecStatusDiv())))
    {       
        //受渡日         ＝　@getExecutions()の戻り値[0].受渡日
        l_feqExecuteDetailInfoUnit.deliveryDate = 
            l_feqOrderExecution.getDeliveryDate();
        
        log.debug("受渡日 = " + l_feqExecuteDetailInfoUnit.deliveryDate);
        
        //現地受渡日       ＝　@getExecutions()の戻り値[0].現地受渡日
        FeqOrderExecutionRow l_feqOrderExeRow =
                    (FeqOrderExecutionRow)l_feqOrderExecution.getDataSourceObject();
        l_feqExecuteDetailInfoUnit.localDeliveryDate = 
            l_feqOrderExeRow.getFDeliveryDate();
        
        log.debug("現地受渡日 = " + l_feqExecuteDetailInfoUnit.localDeliveryDate);
        
        //約定代金        ＝　@パラメータ.注文単位.合計約定金額
        l_feqExecuteDetailInfoUnit.execAmount = 
            WEB3StringTypeUtility.formatNumber(
                l_feqOrderUnitparams.getExecutedAmount());
        
        log.debug("約定代金 = " + l_feqExecuteDetailInfoUnit.execAmount);
        
        //約定為替レート   ＝　@getExecutions()の戻り値[0].為替レート
        l_feqExecuteDetailInfoUnit.execExchangeRate = 
            WEB3StringTypeUtility.formatNumber(l_feqOrderExecution.getFxRate());
        
        log.debug("約定為替レート = " + l_feqExecuteDetailInfoUnit.execExchangeRate);
        
        //扱者コード       ＝　@拡張金融オブジェクトマネージャ.getTrader(
        //                  パラメータ.注文単位.取引者ID).扱者コードをセット。
        //   ※this.get代理入力者() != null(コールセンターからの参照)の場合のみセット。
        
        if (this.getTrader() != null)
        {
            log.debug("this.get代理入力者() != null");
            
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            try
            {
                if (!l_feqOrderUnitparams.getTraderIdIsNull())
                {
                    String l_strTraderCode = 
                        l_finObjManager.getTrader(
                            l_feqOrderUnitparams.getTraderId()).getTraderCode();
                    
                    l_feqExecuteDetailInfoUnit.traderCode = l_strTraderCode;
                    
                    log.debug("扱者コード = " + l_feqExecuteDetailInfoUnit.traderCode);
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("___NotFoundException___" , l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
        }
        
        //受渡代金        ＝　@集計した受渡代金
        if (OrderTypeEnum.FEQ_BUY.equals(
                        l_feqOrderUnit.getOrderType()) == true)
        {
            l_feqExecuteDetailInfoUnit.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblDeliveryPerice * (-1));
        }else{
            l_feqExecuteDetailInfoUnit.deliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblDeliveryPerice);
        }
        
        log.debug("受渡代金 = " + l_feqExecuteDetailInfoUnit.deliveryPrice);
        
        //受渡代金（外貨）  ＝　@集計した受渡代金（外貨）
        if (OrderTypeEnum.FEQ_BUY.equals(
                        l_feqOrderUnit.getOrderType()) == true)
        {
            l_feqExecuteDetailInfoUnit.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdNetAmountFc.multiply(new BigDecimal("-1")).doubleValue());
        }else{
            l_feqExecuteDetailInfoUnit.foreignDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdNetAmountFc.doubleValue());
        }
        
        log.debug("受渡代金（外貨） = " + l_feqExecuteDetailInfoUnit.foreignDeliveryPrice);
        
        //現地手数料       ＝　@集計した現地手数料
        l_feqExecuteDetailInfoUnit.localCommission = 
            l_feqExecuteDetailInfoUnit.localCommission;
        
        log.debug("現地手数料 = " + l_feqExecuteDetailInfoUnit.localCommission);
        
        //現地取引税       ＝　@集計した現地取引税
        l_feqExecuteDetailInfoUnit.localTradingTax = 
            l_feqExecuteDetailInfoUnit.localTradingTax;
        
        log.debug("現地取引税 = " + l_feqExecuteDetailInfoUnit.localTradingTax);
        
        //その他コスト１     ＝　@集計したその他コスト１
        l_feqExecuteDetailInfoUnit.otherCost1 = 
            l_feqExecuteDetailInfoUnit.otherCost1;
        
        log.debug("その他コスト１ = " + l_feqExecuteDetailInfoUnit.otherCost1);
        
        //その他コスト２     ＝　@集計したその他コスト２
        l_feqExecuteDetailInfoUnit.otherCost2 = 
            l_feqExecuteDetailInfoUnit.otherCost2;
        
        log.debug("その他コスト２ = " + l_feqExecuteDetailInfoUnit.otherCost2);
        
        //清算代金        ＝　@集計した清算代金
        l_feqExecuteDetailInfoUnit.clearUpPrice = 
            l_feqExecuteDetailInfoUnit.clearUpPrice;
        
        log.debug("清算代金 = " + l_feqExecuteDetailInfoUnit.clearUpPrice);
        
        //清算代金（外貨）   ＝　@集計した清算代金（外貨）
        l_feqExecuteDetailInfoUnit.foreignClearUpPrice = 
            l_feqExecuteDetailInfoUnit.foreignClearUpPrice;
        
        log.debug("清算代金（外貨） = " + l_feqExecuteDetailInfoUnit.foreignClearUpPrice);
        
        //国内手数料       ＝　@集計した国内手数料
        l_feqExecuteDetailInfoUnit.commission = 
            l_feqExecuteDetailInfoUnit.commission;
        
        log.debug("国内手数料 = " + l_feqExecuteDetailInfoUnit.commission);
        
        //国内手数料消費税    ＝　@集計した国内手数料消費税
        l_feqExecuteDetailInfoUnit.commissionConsumptionTax = 
            l_feqExecuteDetailInfoUnit.commissionConsumptionTax;
        
        log.debug("国内手数料消費税 = " + l_feqExecuteDetailInfoUnit.commissionConsumptionTax);
        
        //国内手数料（外貨）   ＝　@集計した国内手数料（外貨）
        l_feqExecuteDetailInfoUnit.foreignCommission = 
            l_feqExecuteDetailInfoUnit.foreignCommission;
        
        log.debug("国内手数料（外貨） = " + l_feqExecuteDetailInfoUnit.foreignCommission);
        
        //国内手数料消費税（外貨）    ＝　@集計した国内手数料消費税（外貨）
        l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax = 
            l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax;
        
        log.debug("国内手数料消費税（外貨） = " + 
            l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax);
    }
    // 約定単価と約定数量と約定明細一覧の項目以外にはnullをセットする。
    else
    {
        // 受渡日
        l_feqExecuteDetailInfoUnit.deliveryDate = null;
        
        // 現地受渡日
        l_feqExecuteDetailInfoUnit.localDeliveryDate = null;
        
        // 約定代金
        l_feqExecuteDetailInfoUnit.execAmount = null;
        
        // 約定為替レート
        l_feqExecuteDetailInfoUnit.execExchangeRate = null;
        
        // 扱者コード
        l_feqExecuteDetailInfoUnit.traderCode = null;
        
        // 受渡代金
        l_feqExecuteDetailInfoUnit.deliveryPrice = null;
        
        // 受渡代金（外貨）
        l_feqExecuteDetailInfoUnit.foreignDeliveryPrice = null;
        
        // 現地手数料
        l_feqExecuteDetailInfoUnit.localCommission = null;
        
        // 現地取引税
        l_feqExecuteDetailInfoUnit.localTradingTax = null;
        
        // その他コスト１
        l_feqExecuteDetailInfoUnit.otherCost1 = null;
        
        // その他コスト２
        l_feqExecuteDetailInfoUnit.otherCost2 = null;
        
        // 清算代金
        l_feqExecuteDetailInfoUnit.clearUpPrice = null;
        
        // 清算代金（外貨）
        l_feqExecuteDetailInfoUnit.foreignClearUpPrice = null;
        
        // 国内手数料
        l_feqExecuteDetailInfoUnit.commission = null;
        
        // 国内手数料消費税
        l_feqExecuteDetailInfoUnit.commissionConsumptionTax = null;
        
        // 国内手数料（外貨）
        l_feqExecuteDetailInfoUnit.foreignCommission = null;
        
        // 国内手数料消費税（外貨）
        l_feqExecuteDetailInfoUnit.foreignCommissionConsumptionTax = null;
    }
    
    log.exiting(STR_METHOD_NAME);
    return l_feqExecuteDetailInfoUnit;
}
    
    /**
     * (create注文履歴一覧)<BR>
     * 引数の注文単位から注文履歴一覧を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株注約照会）create注文履歴一覧」参照<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return WEB3FeqChangeCancelHistoryGroup[]
     * @@throws WEB3BaseException
     * @@roseuid 42A547C503C7
     */
    protected WEB3FeqChangeCancelHistoryGroup[] createOrderActionList(
        WEB3FeqOrderUnit l_feqOrderUnit) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createOrderActionList(WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 get出来るまで注文単位FromFirstToLast(外国株式注文単位)
        //原注文～最新までの出来るまで注文を全て取得する。 
        //[引数] 
        //注文単位：　@パラメータ.注文単位
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            this.getCarriedOrderUnitFromFirstToLast(l_feqOrderUnit);
        
        //1.2 is出来るまで注文単位(FeqOrderUnit)
        //出来るまで注文かどうか判別する。 
        //[引数] 
        //注文単位：　@引数の注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_blnIsCarriedOrder = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);
        
        //1.3 ArrayListを生成する。
        List l_lisChangeCancelHistoryGroup = new ArrayList();
        
        //1.4  (*)get出来るまで注文単位FromFirstToLast()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_feqOrderUnits.length; i++)
        {
            //1.4.1 getOrderActions( )
            //注文単位に紐づく注文履歴を取得する。 
            OrderAction[] l_orderActions = 
			l_feqOrderUnits[i].getOrderActions();
            
            //1.4.2 (*)getOrderActions()の戻り値の要素数分Loop処理
            for (int j = 0; j < l_orderActions.length; j++)
            {
                WEB3FeqOrderAction l_feqOrderAction = 
                    (WEB3FeqOrderAction)l_orderActions[j];
                
                FeqOrderActionParams l_feqOrderActionParams = 
                    new FeqOrderActionParams((FeqOrderActionRow)
                        l_orderActions[j].getDataSourceObject());
                
                log.debug("注文履歴Params = " + l_feqOrderActionParams);
                
                //1.4.2.1 get履歴状態区分(外国株式注文単位)
                //履歴状態区分を取得する。 
                //[引数] 
                //注文単位：　@処理対象の注文単位
                String l_strActionStateDiv = 
                    l_feqOrderAction.getActionStateDiv(l_feqOrderUnits[i]);

                //1.4.2.2 get受付結果区分( )(外国株式注文履歴::get受付結果区分)
                //受付結果区分を取得する。
                String l_strAcceptStatesDiv = 
                    l_feqOrderAction.getAcceptStatusDiv();
                
                //1.4.2.3 isUnexecuted( )
                //約定時に作成された履歴かどうか判別する。
                boolean l_blnIsUnexecuted = l_feqOrderAction.isUnexecuted();
                
                //1.4.2.4 get執行条件（SONAR）(String)
                //執行条件（SONAR）を取得する。 
                //[引数] 
                //執行条件：　@処理対象の注文履歴.執行条件
                String l_strExectutinConditionType = 
                    l_feqOrderAction.getExecutionConditionType().intValue() + "";
                
                String l_strExecutionConditionTypeSonar = 
                    l_feqOrderManager.getExecutionConditionTypeSonar(
                        l_strExectutinConditionType);

                //1.4.2.5 外国株式注文約定履歴明細インスタンスを生成する。
                WEB3FeqChangeCancelHistoryGroup l_feqChangeCancelHistoryGroup = 
                    new WEB3FeqChangeCancelHistoryGroup();
                
                //1.4.2.6  (*)約定時に作成された履歴でない(isUnexecuted() == true)場合
                if (l_blnIsUnexecuted)
                {
                    //1.4.2.6.1 (*)プロパティセット
                    //  (*)【約定時に作成された履歴でない場合】
                    //生成したインスタンスに以下のプロパティをセットする。

                    //注文履歴ID      ＝　@注文履歴.注文履歴ID
                    l_feqChangeCancelHistoryGroup.orderActionId = 
                        l_feqOrderActionParams.getOrderActionId() + "";
                    
                    //受付日時        ＝　@注文履歴.作成日付
                    l_feqChangeCancelHistoryGroup.orderDate = 
                        l_feqOrderActionParams.getCreatedTimestamp();
                        
                    //履歴状態区分      ＝　@get履歴状態区分()の戻り値
                    l_feqChangeCancelHistoryGroup.orderType = l_strActionStateDiv;
                    
                    //執行条件        ＝　@get執行条件（SONAR）の戻り値
                    l_feqChangeCancelHistoryGroup.execCondType = 
                        l_strExecutionConditionTypeSonar;
                    
                    //発注条件        ＝　@注文履歴.発注条件
                    l_feqChangeCancelHistoryGroup.orderCondType = 
                        l_feqOrderActionParams.getOrderConditionType();
                    
                    // (*1)注文履歴.発注条件 == ("逆指値" or "W指値")の場合のみセット。
                    if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                            l_feqOrderActionParams.getOrderConditionType()) || 
                        WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                            l_feqOrderActionParams.getOrderConditionType()))
                    {
                        //発注条件単価(*1)　@＝　@注文履歴.逆指値基準値
                        l_feqChangeCancelHistoryGroup.orderCondPrice = 
                            WEB3StringTypeUtility.formatNumber(
                                l_feqOrderActionParams.getStopOrderPrice());
                        
                        //発注条件演算子(*1) ＝　@注文履歴.発注条件演算子をセット。
                        l_feqChangeCancelHistoryGroup.condOperator = 
                            l_feqOrderActionParams.getOrderCondOperator();
                    }
                    
                    //(*2)注文履歴.発注条件 == "W指値"の場合のみセット。
                    if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                        l_feqOrderActionParams.getOrderConditionType()))
                    {
                        //W指値用注文単価区分(*2)  ＝　@
                        //          注文履歴.（W指値）訂正指値 == 0の場合、"成行"をセット。
                        //　@　@　@　@　@　@以外、"指値"をセット。
                        if (l_feqOrderActionParams.getWLimitPrice() == 0)
                        {
                            l_feqChangeCancelHistoryGroup.wLimitOrderPriceDiv = 
                                WEB3OrderPriceDivDef.MARKET_PRICE;
                        }
                        else
                        {
                            l_feqChangeCancelHistoryGroup.wLimitOrderPriceDiv = 
                                WEB3OrderPriceDivDef.LIMIT_PRICE;
                        }                    
                        //W指値用注文単価(*2)  ＝　@注文履歴.（W指値）訂正指値 != 0の場合、
                        //　@　@　@　@　@　@　@　@　@　@　@　@　@注文履歴.（W指値）訂正指値をセット。
                        if (l_feqOrderActionParams.getWLimitPrice() != 0)
                        {
                            l_feqChangeCancelHistoryGroup.wLimitPrice = 
                                WEB3StringTypeUtility.formatNumber(
                                    l_feqOrderActionParams.getWLimitPrice());
                        }
                    }
                    //注文数量        ＝　@注文履歴.注文数量
                    l_feqChangeCancelHistoryGroup.orderQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getQuantity());
                    
                    //注文単価区分　@＝　@注文履歴.注文単価 == 0の場合、"成行"。
                    //               以外、"指値"をセット。
                    if (l_feqOrderActionParams.getPrice() == 0)
                    {
                        l_feqChangeCancelHistoryGroup.orderPriceDiv = 
                            WEB3OrderPriceDivDef.MARKET_PRICE;
                    }
                    else
                    {
                        l_feqChangeCancelHistoryGroup.orderPriceDiv = 
                            WEB3OrderPriceDivDef.LIMIT_PRICE;
                    }
                    
                    //注文単価        ＝　@注文履歴.注文単価
                    l_feqChangeCancelHistoryGroup.limitPrice = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getPrice());
                    
                    //注文有効期限      ＝　@is出来るまで注文単位()の戻り値 == trueの場合、
                    //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ 注文履歴.注文失効日付をセット。
                    if (l_blnIsCarriedOrder)
                    {
                        l_feqChangeCancelHistoryGroup.expirationDate = 
                            WEB3DateUtility.toDay(
                                l_feqOrderActionParams.getExpirationDate());
                    }
                    
                    //受付結果区分      ＝　@get受付結果区分()の戻り
                    l_feqChangeCancelHistoryGroup.acceptedResultDiv = 
                        l_strAcceptStatesDiv;
                    
                    //通貨コード       ＝　@注文単位.通貨コード
                    l_feqChangeCancelHistoryGroup.currencyCode = 
                        l_feqOrderUnits[i].getCurrencyCode();
                }
                //1.4.2.7 (*)約定時に作成された履歴(isUnexecuted() == false)の場合
                else
                {
                    //1.4.2.7.1 (*)プロパティセット
                    //(*)【約定時に作成された履歴の場合】
                    //生成したインスタンスに以下のプロパティをセットする。
                    //注文履歴ID      ＝　@注文履歴.注文履歴ID
                    l_feqChangeCancelHistoryGroup.orderActionId = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getOrderActionId());
                    
                    //受付日時        ＝　@注文履歴.作成日付
                    l_feqChangeCancelHistoryGroup.orderDate = 
                        l_feqOrderActionParams.getCreatedTimestamp();
                    
                    //履歴状態区分      ＝　@get履歴状態区分()の戻り値
                    l_feqChangeCancelHistoryGroup.orderType = l_strActionStateDiv;
                    
                    //約定数量        ＝　@注文履歴.約定数量
                    l_feqChangeCancelHistoryGroup.execQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getExecutedQuantity());
                    
                    //約定単価        ＝　@注文履歴.約定単価
                    l_feqChangeCancelHistoryGroup.execPrice = 
                        WEB3StringTypeUtility.formatNumber(
                            l_feqOrderActionParams.getExecutedPrice());
                    
                    //約定日時        ＝　@注文履歴.約定日時
                    l_feqChangeCancelHistoryGroup.executionTimestamp = 
                        l_feqOrderActionParams.getExecTimestamp();
                    
                    //受付結果区分      ＝　@get受付結果区分()の戻り値
                    l_feqChangeCancelHistoryGroup.acceptedResultDiv = 
                        l_strAcceptStatesDiv;
                    
                    //通貨コード       ＝　@注文単位.通貨コード
                    l_feqChangeCancelHistoryGroup.currencyCode = 
                        l_feqOrderUnits[i].getCurrencyCode();
                }
                //1.4.2.8 add(arg0 : Object)
                //ArrayListにプロパティセットしたインスタンスを追加する。 
                //[引数] 
                //arg0：　@プロパティセットした外国株式注文約定履歴明細インスタンス
                l_lisChangeCancelHistoryGroup.add(l_feqChangeCancelHistoryGroup);                
            }
        }
        //1.5 toArray( )
        //外国株式注文約定履歴明細の配列を生成する。
        WEB3FeqChangeCancelHistoryGroup[] l_feqChangeCancelHistorys = 
            new WEB3FeqChangeCancelHistoryGroup[l_lisChangeCancelHistoryGroup.size()];
        
        l_lisChangeCancelHistoryGroup.toArray(l_feqChangeCancelHistorys);

        log.exiting(STR_METHOD_NAME);
        return l_feqChangeCancelHistorys;
    }
    
    /**
     * (get出来るまで注文単位FromFirstToLast)<BR>
     * 指定された「出来るまで注文」の注文単位オブジェクトに対する、<BR>
     * 原注文～最新の注文までの注文単位オブジェクトの一覧を取得する。<BR>
     * <BR>
     * １）　@出来るまで注文チェック<BR>
     * 　@外国株式注文マネージャ.is出来るまで<BR>
     * 注文単位(パラメータ.注文単位) == falseの場合、<BR>
     * 　@パラメータ.注文単位のみを要素とする外国株式注文単位の配列を生成し、<BR>
     * 　@返却する。<BR>
     * <BR>
     * ２）　@原注文～最新の注文までの注文単位オブジェクトを下記抽出条件にて取得する。<BR>
     * <BR>
     * 　@　@＜抽出条件＞<BR>
     * <BR>
     * 　@　@　@[パラメータ.注文単位.初回注文の注文単位ID == 0（＝出来るまで注文の<BR>
     * 原注文）の場合]<BR>
     * 　@　@　@　@パラメータ.注文単位（原注文自身）、<BR>
     * 　@　@　@　@及び　@初回注文の注文単位ID == パラメータ.注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@初回注文の注文単位ID == パラメータ.注文単位.初回注文の<BR>
     * 注文単位ID　@または<BR>
     * 　@　@　@　@注文単位ID == パラメータ.注文単位.初回注文の注文単位ID<BR>
     * <BR>
     * 　@　@　@　@※原注文.初回注文の注文単位IDには、0がセットされている為。<BR>
     * <BR>
     * 　@　@　@　@取得した注文単位オブジェクトを作成日時順に昇順でソートし、<BR>
     * 配列にして返却する。<BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@roseuid 42A54A49009B
     */
    protected WEB3FeqOrderUnit[] getCarriedOrderUnitFromFirstToLast(
        WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getCarriedOrderUnitFromFirstToLast(WEB3FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@出来るまで注文チェック 
        //外国株式注文マネージャ.is出来るまで注文単位(パラメータ.注文単位) == falseの場合、 
        //パラメータ.注文単位のみを要素とする外国株式注文単位の配列を生成し、 
        //返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_blnIsCarriedOrderUnit = 
            l_feqOrderManager.isCarriedOrderUnit(l_feqOrderUnit);       

        if (!l_blnIsCarriedOrderUnit)
        {
            log.debug("外国株式注文マネージャ.is出来るまで注文単位(パラメータ.注文単位) == falseの場合");
            
            WEB3FeqOrderUnit[] l_feqOrderUnits = new WEB3FeqOrderUnit[1];
            l_feqOrderUnits[0] = l_feqOrderUnit;
            
            log.exiting(STR_METHOD_NAME);
            return l_feqOrderUnits;
        }
        
        //２）　@原注文～最新の注文までの注文単位オブジェクトを下記抽出条件にて取得する。 
        //＜抽出条件＞ 
        //　@[パラメータ.注文単位.初回注文の注文単位ID == 0（＝出来るまで注文の原注文）の場合] 
        //　@　@    パラメータ.注文単位（原注文自身）、 
        //　@　@    及び　@初回注文の注文単位ID == パラメータ.注文単位.注文単位ID 
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        FeqOrderManager l_orderManager =
            (FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        
        if (l_feqOrderUnitParams.getFirstOrderUnitId() == 0)
        {
            log.debug("パラメータ.注文単位.初回注文の注文単位ID == 0の場合");
            
            List l_lisFeqOrderUnits = new ArrayList();
            
            List l_lisSearchRows = null;
            String l_strWhereClause = 
                "order_unit_id = ? or first_order_unit_id = ?"; 
            
            String l_strSortCond = "created_timestamp";
            
            long l_lngOrderUnitId = l_feqOrderUnit.getOrderUnitId();
            log.debug("注文単位ID = " + l_lngOrderUnitId);
            
            Object l_bindVars[] = {
                new Long(l_lngOrderUnitId), 
                new Long(l_lngOrderUnitId)};
            
            try
            {
                l_lisSearchRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        FeqOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_strSortCond, 
                        null,
                        l_bindVars);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (l_lisSearchRows == null || l_lisSearchRows.isEmpty())
            {
                log.debug("外株注文単位がない");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            for (int i = 0; i < l_lisSearchRows.size(); i++)
            {
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_lisSearchRows.get(i);
                FeqOrderUnit l_newFeqOrderUnit = 
                    (FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
                
                l_lisFeqOrderUnits.add(l_newFeqOrderUnit);
            }
            WEB3FeqOrderUnit[] l_feqOrderUnits = 
                new WEB3FeqOrderUnit[l_lisFeqOrderUnits.size()];
            
            l_lisFeqOrderUnits.toArray(l_feqOrderUnits);
            
            log.exiting(STR_METHOD_NAME);
            return l_feqOrderUnits;
            
        }
        //　@　@[上記以外の場合] 
        else
        {
            List l_lisFeqOrderUnits = new ArrayList();
            
            List l_lisSearchRows = null;
            
            //初回注文の注文単位ID == パラメータ.注文単位.初回注文の注文単位ID　@または 
            //注文単位ID == パラメータ.注文単位.初回注文の注文単位ID 
            
            String l_strWhereClause = 
                "first_order_unit_id = ? or order_unit_id = ?";            
            
            String l_strSortCond = "created_timestamp";
            
            long l_lngFirstOrderUnitId = l_feqOrderUnitParams.getFirstOrderUnitId();
            log.debug("初回注文の注文単位ID = " + l_lngFirstOrderUnitId);
            
            Object l_bindVars[] = {
                new Long(l_lngFirstOrderUnitId), 
                new Long(l_lngFirstOrderUnitId)};
            
            try
            {
                l_lisSearchRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        FeqOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_strSortCond, 
                        null,
                        l_bindVars);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (l_lisSearchRows == null || l_lisSearchRows.isEmpty())
            {
                log.debug("外株注文単位がない");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            for (int i = 0; i < l_lisSearchRows.size(); i++)
            {
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_lisSearchRows.get(i);
                FeqOrderUnit l_newFeqOrderUnit = 
                    (FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
                
                l_lisFeqOrderUnits.add(l_newFeqOrderUnit);
            }
            WEB3FeqOrderUnit[] l_feqOrderUnits = new WEB3FeqOrderUnit[l_lisFeqOrderUnits.size()];
            l_lisFeqOrderUnits.toArray(l_feqOrderUnits);
            
            log.exiting(STR_METHOD_NAME);
            return l_feqOrderUnits;
        }
    }
}
@
