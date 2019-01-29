head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式当日注文伝票一覧サービスImpl(WEB3AdminFeqOrderVoucherListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
Revesion History : 2007/01/16 周捷 (中訊) モデルNo.330、332、337
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2008/10/02 大澤(SRA) 【外国株式】仕様変更管理台帳（モデル）No.470
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqOrderAcceptTypeDef;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListCondUnit;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderVoucherListService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式当日注文伝票一覧サービスImpl)<BR>
 * 管理者外国株式当日注文伝票一覧サービス実装クラス<BR>
 *    
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListServiceImpl implements WEB3AdminFeqOrderVoucherListService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderVoucherListServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F201E4
     */
    public WEB3AdminFeqOrderVoucherListServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式当日注文伝票一覧サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    get入力画面()<BR>
     *    get一覧画面()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC6DB02B3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqOrderVoucherListInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqOrderVoucherListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderVoucherListRequest)
        {
            //get一覧画面()
            l_response = 
                this.getListScreen((WEB3AdminFeqOrderVoucherListRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）当日注文伝票一覧）get入力画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC6D7035F
     */
    protected WEB3AdminFeqOrderVoucherListInputResponse getInputScreen(WEB3AdminFeqOrderVoucherListInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderVoucherListInputRequest) ";
        log.entering(STR_METHOD_NAME);        
        
        //1.1:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "管理者のログイン情報が存在しない。");
        }
        
        //1.2:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
        
        //1.3:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.4:get取扱可能外株市場(String)
        String[] l_strMarketCodes =
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY);

        if (l_strMarketCodes == null || l_strMarketCodes.length == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        //1.8:createResponse( )
        WEB3AdminFeqOrderVoucherListInputResponse l_response = 
           (WEB3AdminFeqOrderVoucherListInputResponse)l_request.createResponse();
        
        //1.9:(*2)プロパティセット   
        l_response.marketCodeList = l_strMarketCodes;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get一覧画面)<BR>
     * 一覧画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）当日注文伝票一覧）get一覧画面」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図（管）当日注文伝票一覧 / （（管）当日注文伝票一覧）get一覧画面<BR>
     * 　@　@:  1.8.doFindAllQuery:検索結果が取得できなかった場合、<BR>
     * 　@　@　@「該当するデータが存在しません。」の<BR>
     * 　@　@　@業務エラーを返却する。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_00398<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderVoucherListResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FC6D7037E
     */
    protected WEB3AdminFeqOrderVoucherListResponse getListScreen(WEB3AdminFeqOrderVoucherListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqOrderVoucherListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.1: validate( )
            l_request.validate();
            
            //1.2:getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
            if (l_admin == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "管理者のログイン情報が存在しない。");
            }
            
            //1.3:validate権限(機@能カテゴリコード : String, is更新 : boolean)
            l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
            
            //1.4:get証券会社コード( )
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            
            //1.5:getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException

            //(*)検索条件格納先の作成
            String l_strWhere = null;
            Object[] l_objs = null;
            List l_lisOrderUnitRows = null;
            List l_lisObjs = new ArrayList();
            //1.6:(*1)リクエスト.一覧条件の各要素についてLoop処理
            if (l_request.condList != null && l_request.condList.length > 0)
            {
                int l_intCnt = l_request.condList.length;
                
                log.debug("1.6:(*1)リクエスト.一覧条件の各要素についてLoop処理:" + l_intCnt);
                
                WEB3AdminFeqOrderVoucherListCondUnit[] l_orderVoucherListConds = l_request.condList;
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    log.debug("処理:" + i);
                    
                    //1.6.1:create取得条件文字列(外国株式当日注文伝票一覧条件)
                    String l_strCreateQuery = this.createQueryString(l_orderVoucherListConds[i]);

                    //1.6.2:create取得条件データコンテナ(String, 外国株式当日注文伝票一覧条件)
                    l_objs = this.createQueryContainer(
                        l_strInstitutionCode,
                        l_orderVoucherListConds[i]);

                    //(*)検索条件格納先への追加
                    if (l_strWhere == null)
                    {
                        l_strWhere = "(" + l_strCreateQuery + ")";
                    }
                    else
                    {
                        l_strWhere += " or (" + l_strCreateQuery + ")";
                    }
                    for (int j = 0; j < l_objs.length; j++)
                    {
                        l_lisObjs.add(l_objs[j]);
                    }
                }

                //doFindAllQuery(arg0 : String, arg1 : String,
                //     arg2 : String, arg3 : String, arg4 : Object[])
                //[引数] 
                // Rowタイプ：　@注文単位Row.TYPE
                // Where：　@作成した検索条件文字列
                // ソート：　@"biz_date, market_id"（発注日 昇順, 市場ID 昇順）
                // condition：　@null
                // リスト：　@作成した検索条件データコンテナ
                l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                    FeqOrderUnitRow.TYPE,
                    l_strWhere,
                    "biz_date asc, market_id asc",
                    null,
                    l_lisObjs.toArray());

                if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
                {
                    log.debug("該当するデータが存在しません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                         WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                         this.getClass().getName() + STR_METHOD_NAME,
                         "該当するデータが存在しません。");
                }
            }

            //(*)取得済オブジェクトリスト作成
            HashMap l_hmBranch = new HashMap();
            HashMap l_hmAccount = new HashMap();
            HashMap l_hmTrader = new HashMap();
            HashMap l_hmProduct = new HashMap();
            HashMap l_hmMarket = new HashMap();
            HashMap l_hmCurrency = new HashMap();

            //StringBuffer( )
            StringBuffer l_sbCSVData = new StringBuffer();

            //ArrayList( )
            ArrayList l_arrayListCSV = new ArrayList();

            //(*2)取得した注文単位毎にLoop処理
            int l_intRowsCnt = l_lisOrderUnitRows.size();
            for (int i = 0; i < l_intRowsCnt; i++)
            {
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisOrderUnitRows.get(i);

                //delete(arg0 : int, arg1 : int)
                //[引数]
                // arg0：　@0（固定）
                // arg1：　@StringBufferインスタンス.length
                l_sbCSVData.delete(0, l_sbCSVData.length());

                //getObject
                //[引数]
                // キー情報：　@処理対象の要素.部店ID
                // 取得済オブジェクト一覧：　@作成した取得済オブジェクトリスト（部店）
                // クラス情報：　@部店.class
                // 注文単位Row：　@処理対象の要素
                WEB3GentradeBranch l_branch =
                    (WEB3GentradeBranch)this.getObject(
                        l_orderUnitRow.getBranchId() + "",
                        l_hmBranch,
                        WEB3GentradeBranch.class,
                        l_orderUnitRow);

                //getObject
                //[引数]
                // キー情報：　@処理対象の要素.口座ID
                // 取得済オブジェクト一覧：　@作成した取得済オブジェクトリスト（顧客）
                // クラス情報：　@顧客.class
                // 注文単位Row：　@処理対象の要素
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)this.getObject(
                        l_orderUnitRow.getAccountId() + "",
                        l_hmAccount,
                        WEB3GentradeMainAccount.class,
                        l_orderUnitRow);

                //(*)処理対象の要素.取引者ID != nullの場合
                WEB3GentradeTrader l_trader = null;
                if (!l_orderUnitRow.getTraderIdIsNull())
                {
                    //getObject
                    //[引数]
                    // キー情報：　@処理対象の要素.取引者ID
                    // 取得済オブジェクト一覧：　@作成した取得済オブジェクトリスト（扱者）
                    // クラス情報：　@扱者.class
                    // 注文単位Row：　@処理対象の要素
                    l_trader =
                        (WEB3GentradeTrader)this.getObject(
                            l_orderUnitRow.getTraderId() + "",
                            l_hmTrader,
                            WEB3GentradeTrader.class,
                            l_orderUnitRow);
                }

                //getObject
                //[引数]
                // キー情報：　@処理対象の要素.銘柄ID
                // 取得済オブジェクト一覧：　@作成した取得済オブジェクトリスト（銘柄）
                // クラス情報：　@外株銘柄.class
                // 注文単位Row：　@処理対象の要素
                WEB3FeqProduct l_product =
                    (WEB3FeqProduct)this.getObject(
                        l_orderUnitRow.getProductId() + "",
                        l_hmProduct,
                        WEB3FeqProduct.class,
                        l_orderUnitRow);

                //getObject
                //[引数]
                // キー情報：　@処理対象の要素.市場ID
                // 取得済オブジェクト一覧：　@作成した取得済オブジェクトリスト（市場）
                // クラス情報：　@市場.class
                // 注文単位Row：　@処理対象の要素
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)this.getObject(
                        l_orderUnitRow.getMarketId() + "",
                        l_hmMarket,
                        WEB3GentradeMarket.class,
                        l_orderUnitRow);

                //getObject
                //[引数]
                // キー情報：　@処理対象の要素.証券会社コード + 通貨コード
                // 取得済オブジェクト一覧：　@作成した取得済オブジェクトリスト（通貨）
                // クラス情報：　@通貨.class
                // 注文単位Row：　@処理対象の要素
                WEB3GentradeCurrency l_currency =
                    (WEB3GentradeCurrency)this.getObject(
                        l_orderUnitRow.getInstitutionCode() + l_orderUnitRow.getCurrencyCode(),
                        l_hmCurrency,
                        WEB3GentradeCurrency.class,
                        l_orderUnitRow);

                //(*)CSVデータ作成
                //証券会社コード
                l_sbCSVData.append(l_orderUnitRow.getInstitutionCode());

                //部店コード
                l_sbCSVData.append("," + l_branch.getBranchCode());
                //口座番号
                l_sbCSVData.append("," + l_mainAccount.getDisplayAccountCode());
                //扱者コード
                if (l_trader != null)
                {
                    l_sbCSVData.append("," + l_trader.getTraderCode());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //識別コード
                l_sbCSVData.append("," + l_orderUnitRow.getOrderRequestNumber());
                //伝票No
                l_sbCSVData.append("," + l_orderUnitRow.getVoucherNo());
                //注文シーケンスNo
                l_sbCSVData.append("," + l_orderUnitRow.getOrderId());
                //銘柄コード
                l_sbCSVData.append("," + l_product.getProductCode());
                //現地銘柄コード
                l_sbCSVData.append("," + l_product.getOffshoreProductCode());
                //銘柄名
                l_sbCSVData.append("," + l_product.getDisplayProductName());
                //市場コード
                l_sbCSVData.append("," + l_market.getMarketCode());
                //市場名
                l_sbCSVData.append("," + l_market.getMarketName());
                //売買
                if (OrderTypeEnum.FEQ_BUY.equals(l_orderUnitRow.getOrderType()))
                {
                    l_sbCSVData.append("," + WEB3TransactionTypeDef.BUY);
                }
                else
                {
                    l_sbCSVData.append("," + WEB3TransactionTypeDef.SELL);
                }
                //注文株数
                l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getQuantity())).doubleValue()));
                //指値・成行
                if (l_orderUnitRow.getLimitPrice() == 0)
                {
                    l_sbCSVData.append("," + WEB3OrderPriceDivDef.MARKET_PRICE);
                }
                else
                {
                    l_sbCSVData.append("," + WEB3OrderPriceDivDef.LIMIT_PRICE);
                }
                //単価
                l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getLimitPrice())).doubleValue()));
                //決済区分
                if (l_orderUnitRow.getSettleDiv() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getSettleDiv());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //通貨コード
                l_sbCSVData.append("," + l_orderUnitRow.getCurrencyCode());
                //通貨名称
                l_sbCSVData.append("," + l_currency.getCurrencyName());
                //注文日時
                if (l_orderUnitRow.getReceivedDateTime() != null)
                {
                    l_sbCSVData.append("," +
                        WEB3DateUtility.formatDate(
                            l_orderUnitRow.getReceivedDateTime(), "yyyy/MM/dd HH:mm:ss"));
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //発注日
                l_sbCSVData.append("," + l_orderUnitRow.getBizDate());
                //執行条件
                if (l_orderUnitRow.getExecutionConditionType() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getExecutionConditionType().intValue());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //発注条件
                if (l_orderUnitRow.getOrderConditionType() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderConditionType());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //発注条件演算子
                if (l_orderUnitRow.getOrderCondOperator() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderCondOperator());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //発注条件単価
                if (!l_orderUnitRow.getStopOrderPriceIsNull())
                {
                    l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getStopOrderPrice())).doubleValue()));
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //（W指値）訂正指値
                if (!l_orderUnitRow.getWLimitPriceIsNull())
                {
                    l_sbCSVData.append("," + WEB3StringTypeUtility.formatNumber(new BigDecimal(String.valueOf(l_orderUnitRow.getWLimitPrice())).doubleValue()));
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //注文状態
                if (OrderStatusEnum.ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NAN);
                }
                else if (OrderStatusEnum.ORDERED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NOT_NAN);
                }
                else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.NOT_CANCELLED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR);
                }
                else if (OrderStatusEnum.MODIFIED.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCELLED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3FeqOrderAcceptTypeDef.CNANGE_CANCELED_DATA);
                }
                //約定区分
                if (l_orderUnitRow.getExecutedQuantity() == 0)
                {
                    l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE);
                }
                else if(l_orderUnitRow.getExecutedQuantity() > 0)
                {
                    if (l_orderUnitRow.getExecutedQuantity() < l_orderUnitRow.getQuantity())
                    {
                        if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(l_orderUnitRow.getTemporaryExecutionFlag()))
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE);
                        }
                        else
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE);
                        }
                    }
                    else if (l_orderUnitRow.getExecutedQuantity() == l_orderUnitRow.getQuantity())
                    {
                        if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(l_orderUnitRow.getTemporaryExecutionFlag()))
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE);
                        }
                        else
                        {
                            l_sbCSVData.append("," + WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE);
                        }
                    }
                }
                //訂正取消区分
                l_sbCSVData.append("," + l_orderUnitRow.getModifyCancelType());
                //注文チャネル
                if (l_orderUnitRow.getOrderChanel() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderChanel());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //ファ@クター
                if (l_orderUnitRow.getFactor() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getFactor());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //手数料No
                if (l_orderUnitRow.getCommTblNo() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getCommTblNo());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //手数料No枝番
                if (l_orderUnitRow.getCommTblSubNo() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getCommTblSubNo());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //商品コード
                if (l_orderUnitRow.getCommProductCode() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getCommProductCode());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //注文経路区分
                if (l_orderUnitRow.getOrderRootDiv() != null)
                {
                    l_sbCSVData.append("," + l_orderUnitRow.getOrderRootDiv());
                }
                else
                {
                    l_sbCSVData.append(",");
                }
                //メール区分
                if (OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus()))
                {
                    l_sbCSVData.append("," + WEB3MqStatusDef.NOT_SEND_MAIL);
                }
                else
                {
                    l_sbCSVData.append("," + WEB3MqStatusDef.MAIL_SENDED);
                }
                //特定口座区分
                if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
                {
                    l_sbCSVData.append("," + WEB3TaxTypeSpecialDef.NORMAL);
                }
                else if (TaxTypeEnum.SPECIAL.equals(l_orderUnitRow.getTaxType()))
                {
                    l_sbCSVData.append("," + WEB3TaxTypeSpecialDef.SPECIAL);
                }
                //運用コード
                l_sbCSVData.append("," + l_orderUnitRow.getOrderEmpCode());

                //add(arg0 : Object)
                //[引数]
                //　@arg0：　@生成したStringBufferインスタンス.toString()
                l_arrayListCSV.add(l_sbCSVData.toString());
            }

            //createResponse( )
            WEB3AdminFeqOrderVoucherListResponse l_response = 
               (WEB3AdminFeqOrderVoucherListResponse)l_request.createResponse();

            //(*3)プロパティセット
            //(*3) 以下のとおりに、プロパティをセットする。
            //当日注文伝票： sort()の戻
            String[] l_strOrderVoucherList = new String[l_arrayListCSV.size()];
            l_arrayListCSV.toArray(l_strOrderVoucherList);
            l_response.orderVoucherList = l_strOrderVoucherList;

            log.exiting(STR_METHOD_NAME);
            return l_response;

        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
    }
    
    /**
     * (create取得条件文字列)<BR>
     * 取得条件文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）市場ID<BR>
     * <BR>
     *    "market_id = ? " を１）の文字列に追加する。<BR>
     * <BR>
     * ３）発注日<BR>
     * <BR>
     * ３－１）引数.一覧条件.発注日（自） != null の場合<BR>
     * <BR>
     *    " and biz_date >= ? and biz_date <= ?" を１）の文字列に追加する。<BR>
     * <BR>
     * ３－２）引数.一覧条件.発注日（自） == null の場合<BR>
     * <BR>
     *    " and biz_date = ?" を１）の文字列に追加する。<BR>
     * <BR>
     * ４）生成した文字列を返却する。<BR>
     * @@param l_listCond - (一覧条件)<BR>
     * 外国株式当日注文伝票一覧条件オブジェクト<BR>
     * 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42A01AC0001E
     */
    protected String createQueryString(WEB3AdminFeqOrderVoucherListCondUnit l_listCond) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminFeqOrderVoucherListCondUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_listCond == null)
        {
            log.debug("一覧条件が未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "一覧条件が未指定(null)です。");
        }
        
        //１）空の文字列を生成する。
        String l_strQueryStr = null;
        
        //２）市場ID
        // "market_id = ? " を１）の文字列に追加する。
        l_strQueryStr = " market_id = ?";
        
        //３）発注日
        //３－１）引数.一覧条件.発注日（自） != null の場合
        //" and biz_date >= ? and biz_date <= ?" を１）の文字列に追加する。
        if (l_listCond.orderBizDateFrom != null)
        {
            l_strQueryStr += " and biz_date >= ? and biz_date <= ? ";
        }        
        //３－２）引数.一覧条件.発注日（自） == null の場合
        // " and biz_date = ?" を１）の文字列に追加する。
        else
        {
            l_strQueryStr += " and biz_date = ? ";
        }
        
        //４）生成した文字列を返却する。        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStr;
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * 取得条件にセットするデータコンテナを生成する。<BR>
     * <BR>
     * １）空のリストを生成する。<BR>
     * <BR>
     * ２）市場ID<BR>
     * <BR>
     * ２－１）市場オブジェクトの取得<BR>
     * <BR>
     *    拡張金融オブジェクトマネージャ.get市場()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    証券会社コード： 引数.証券会社コード<BR>
     *    市場コード： 引数.一覧条件.市場コード<BR>
     * <BR>
     * ２－２）市場IDの取得<BR>
     * <BR>
     *    市場.市場IDを１）のリストに追加する。<BR>
     * <BR>
     * ３）発注日<BR>
     * <BR>
     * ３－１）引数.一覧条件.発注日（自） != null の場合<BR>
     * <BR>
     *    引数.一覧条件.発注日（自）の日付部分（YYYYMMDD）の文字列<BR>
     *    引数.一覧条件.発注日（至）の日付部分（YYYYMMDD）の文字列<BR>
     * <BR>
     *    を１）の文字列に追加する。<BR>
     * <BR>
     * ３－２）引数.一覧条件.発注日（自） == null の場合<BR>
     * <BR>
     *     システムタイムスタンプの日付部分（YYYYMMDD）の文字列<BR>
     * <BR>
     *    を１）の文字列に追加する。<BR>
     * <BR>
     * ４）リストから配列を取得し、返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_listCond - (一覧条件)<BR>
     * 外国株式当日注文伝票一覧条件オブジェクト<BR>
     * 
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 42A01ADF01A5
     */
    protected Object[] createQueryContainer(String l_strInstitutionCode, WEB3AdminFeqOrderVoucherListCondUnit l_listCond) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer(String, WEB3AdminFeqOrderVoucherListCondUnit) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            if (l_listCond == null)
        
            {
                log.debug("一覧条件が未指定(null)です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "一覧条件が未指定(null)です。");
            }
            
            //１）空のリストを生成する。
            List l_lisObjs = new ArrayList();
            
            //２－１）市場オブジェクトの取得
            //拡張金融オブジェクトマネージャ.get市場()をコールする。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            if (l_finApp == null)
            {
                log.debug("FinAppが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinAppが存在しない。");
            }
    
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            if (l_finObjManager == null)
            {
                log.debug("拡張金融オブジェクトマネージャが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "拡張金融オブジェクトマネージャが存在しない。");
            }
            
            Market l_market = l_finObjManager.getMarket(l_strInstitutionCode, l_listCond.marketCode);//NotFoundException
            
            if (l_market == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "市場が存在しない。");
            }
            //２－２）市場IDの取得
            l_lisObjs.add(new Long(l_market.getMarketId()));
            
            //３）発注日
            //３－１）引数.一覧条件.発注日（自） != null の場合
            //引数.一覧条件.発注日（自）の日付部分（YYYYMMDD）の文字列
            //引数.一覧条件.発注日（至）の日付部分（YYYYMMDD）の文字列
            if (l_listCond.orderBizDateFrom != null)
            {
                l_lisObjs.add(WEB3DateUtility.formatDate(
                    l_listCond.orderBizDateFrom, 
                    "yyyyMMdd"));
                l_lisObjs.add(WEB3DateUtility.formatDate(
                    l_listCond.orderBizDateTo, 
                    "yyyyMMdd"));
            }
            //３－２）引数.一覧条件.発注日（自） == null の場合
            //システムタイムスタンプの日付部分（YYYYMMDD）の文字列
            else
            {
                l_lisObjs.add(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));
            }
            
            //４）リストから配列を取得し、返却する。            
            Object[]  l_objs = new Object[l_lisObjs.size()];
            l_lisObjs.toArray(l_objs);           
            
            log.exiting(STR_METHOD_NAME);
            
            return l_objs;
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }    

    /**
     * 引数のキー情報に該当するオブジェクトを引数のHashMapより返却する。<BR>
     * オブジェクトが引数のHashMapに存在しない場合は<BR>
     * DBから新規にレコードを取得し、引数のHashMapに追加した後、<BR>
     * 返却する。<BR>
     *<BR>
     * １）　@オブジェクトの検索<BR>
     * 　@パラメータ.取得済オブジェクト一覧.get()メソッドをコールする。<BR>
     *<BR>
     * 　@[get()に指定する引数]<BR>
     * 　@　@key：　@パラメータ.キー情報<BR>
     *<BR>
     * 　@結果が取得できた場合は、get()の戻り値を返却する。<BR>
     *<BR>
     * ２）　@１）にてnullが返却された場合、<BR>
     * 　@以下の手順にてDBからレコードを取得し、オブジェクトを生成する。<BR>
     * 　@２－１）　@パラメータ.クラス情報により処理を分岐する。<BR>
     * 　@　@※各処理のHashMap.put()メソッドの引数：keyは、<BR>
     * 　@　@　@Stringで設定すること。<BR>
     * 　@　@※呼出側でカスタマイズしたメソッドを使用する為、<BR>
     * 　@　@　@オブジェクトはWEB3～の型で作成／追加すること。<BR>
     * 　@　@　@（xTrade標準オブジェクトではない）<BR>
     * 　@　@　@また、分岐条件の判別もWEB3～の型で行うこと。<BR>
     * 　@　@　@例）　@×　@branch.class<BR>
     * 　@　@　@　@　@　@○　@WEB3GentradeBranch.class<BR>
     *<BR>
     * 　@　@// 部店<BR>
     * 　@　@[パラメータ.クラス情報 == 部店.classの場合]<BR>
     * 　@　@　@パラメータ.注文単位Row.部店IDに該当する部店オブジェクトを取得し、<BR>
     * 　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。<BR>
     *<BR>
     * 　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@key：　@パラメータ.注文単位Row.部店ID<BR>
     * 　@　@　@　@value：　@取得した部店オブジェクト<BR>
     *<BR>
     * 　@　@// 顧客<BR>
     * 　@　@[パラメータ.クラス情報 == 顧客.classの場合]<BR>
     * 　@　@　@パラメータ.注文単位Row.口座IDに該当する顧客オブジェクトを取得し、<BR>
     * 　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。<BR>
     *<BR>
     * 　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@key：　@パラメータ.注文単位Row.口座ID<BR>
     * 　@　@　@　@value：　@取得した顧客オブジェクト<BR>
     *<BR>
     * 　@　@// 扱者<BR>
     * 　@　@[パラメータ.クラス情報 == 扱者.classの場合]<BR>
     * 　@　@　@パラメータ.注文単位Row.取引者IDに該当する顧客オブジェクトを取得し、<BR>
     * 　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。<BR>
     *<BR>
     * 　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@key：　@パラメータ.注文単位Row.取引者ID<BR>
     * 　@　@　@　@value：　@取得した扱者オブジェクト<BR>
     *<BR>
     * 　@　@// 外株銘柄<BR>
     * 　@　@[パラメータ.クラス情報 == 外株銘柄.classの場合]<BR>
     * 　@　@　@パラメータ.注文単位Row.銘柄IDに該当する外株銘柄オブジェクトを取得し、<BR>
     * 　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。<BR>
     *<BR>
     * 　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@key：　@パラメータ.注文単位Row.銘柄ID<BR>
     * 　@　@　@　@value：　@取得した外株銘柄オブジェクト<BR>
     *<BR>
     * 　@　@// 市場<BR>
     * 　@　@[パラメータ.クラス情報 == 市場.classの場合]<BR>
     * 　@　@　@パラメータ.注文単位Row.市場IDに該当する市場オブジェクトを取得し、<BR>
     * 　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。<BR>
     *<BR>
     * 　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@key：　@パラメータ.注文単位Row.市場ID<BR>
     * 　@　@　@　@value：　@取得した市場オブジェクト<BR>
     *<BR>
     * 　@　@// 通貨<BR>
     * 　@　@[パラメータ.クラス情報 == （共通）通貨.classの場合]<BR>
     * 　@　@　@パラメータ.注文単位Row.証券会社コード、通貨コードを引数として、<BR>
     * 　@　@　@（共通）通貨オブジェクトを生成し、<BR>
     * 　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。<BR>
     *<BR>
     * 　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@key：　@パラメータ.注文単位Row.証券会社コード + 通貨コード<BR>
     * 　@　@　@　@value：　@取得した（共通）通貨オブジェクト<BR>
     *<BR>
     * 　@　@上記処理にて検索結果が取得できなかった場合、<BR>
     * 　@　@「該当データなし」のシステムエラーをスローする。<BR>
     *<BR>
     * ３）　@２）にて取得したオブジェクトを返却する。<BR>
     * 
     * @@param l_strKeyInfo - (キー情報)<BR>
     * HashMapからオブジェクトを取得する為のキー情報 <BR>
     * <BR>
     * Obj　@　@　@　@Key <BR>
     * ----　@　@　@---- <BR>
     * 部店　@⇒　@部店ID <BR>
     * 顧客　@⇒　@口座ID <BR>
     * 扱者　@⇒　@取引者ID <BR>
     * 銘柄　@⇒　@銘柄ID <BR>
     * 市場　@⇒　@市場ID <BR>
     * 通貨　@⇒　@証券会社コード + 通貨コード<BR>
     * 
     * @@param l_hmObjectList - (取得済オブジェクト一覧)<BR>
     * DBから取得済のオブジェクト一覧<BR>
     * 
     * @@param l_classInfo - (クラス情報)<BR>
     * 取得済オブジェクト一覧に格納されている（格納する予定）の <BR>
     * オブジェクトのクラス情報（Object.class）<BR>
     * 
     * @@param l_feqOrderUnitRow - (注文単位Row)<BR>
     * 外株注文単位Rowオブジェクト
     * @@return Object
     * @@throws WEB3BaseException 
     */
    protected Object getObject(
        String l_strKeyInfo, HashMap l_hmObjectList,
        Class l_classInfo, FeqOrderUnitRow l_feqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getObject(String, HashMap, Class, FeqOrderUnitRow) ";
        log.entering(STR_METHOD_NAME);

        if (l_hmObjectList == null || l_feqOrderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("パラメータがnullです。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータがnullです。");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3FeqProductManager l_web3FeqProductManager =
            (WEB3FeqProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();

        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //１）　@オブジェクトの検索
        // パラメータ.取得済オブジェクト一覧.get()メソッドをコールする。
        // [get()に指定する引数]
        // 　@key：　@パラメータ.キー情報
        // 結果が取得できた場合は、get()の戻り値を返却する。
        Object l_objRutern = null;
        l_objRutern = l_hmObjectList.get(l_strKeyInfo);
        if (l_objRutern != null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_objRutern;
        }
        else
        {
            //２）　@１）にてnullが返却された場合、
            //　@以下の手順にてDBからレコードを取得し、オブジェクトを生成する。
            //　@２－１）　@パラメータ.クラス情報により処理を分岐する。
            //　@　@※各処理のHashMap.put()メソッドの引数：keyは、
            //　@　@　@Stringで設定すること。
            //　@　@※呼出側でカスタマイズしたメソッドを使用する為、
            //　@　@　@オブジェクトはWEB3～の型で作成／追加すること。
            //　@　@　@（xTrade標準オブジェクトではない）
            //　@　@　@また、分岐条件の判別もWEB3～の型で行うこと。
            //　@　@　@例）　@×　@branch.class
            //　@　@　@　@　@　@○　@WEB3GentradeBranch.class
            //　@　@部店
            //　@　@[パラメータ.クラス情報 == 部店.classの場合]
            //　@　@　@パラメータ.注文単位Row.部店IDに該当する部店オブジェクトを取得し、
            //　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。
            //　@　@　@[put()に指定する引数]
            //　@　@　@　@key：　@パラメータ.注文単位Row.部店ID
            //　@　@　@　@value：　@取得した部店オブジェクト
            try
            {
                if (l_classInfo == WEB3GentradeBranch.class)
                {
                    WEB3GentradeBranch l_branch =
                        (WEB3GentradeBranch)l_gentradeAccountManager.getBranch(
                            l_feqOrderUnitRow.getBranchId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getBranchId() + "", l_branch);
                }

                //　@　@顧客
                //　@　@[パラメータ.クラス情報 == 顧客.classの場合]
                //　@　@　@パラメータ.注文単位Row.口座IDに該当する顧客オブジェクトを取得し、
                //　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。
                //　@　@　@[put()に指定する引数]
                //　@　@　@　@key：　@パラメータ.注文単位Row.口座ID
                //　@　@　@　@value：　@取得した顧客オブジェクト
                else if (l_classInfo == WEB3GentradeMainAccount.class)
                {
                    WEB3GentradeMainAccount l_mainAccount =
                        (WEB3GentradeMainAccount)l_gentradeAccountManager.getMainAccount(
                            l_feqOrderUnitRow.getAccountId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getAccountId() + "", l_mainAccount);
                }

                //　@　@扱者
                //　@　@[パラメータ.クラス情報 == 扱者.classの場合]
                //　@　@　@パラメータ.注文単位Row.取引者IDに該当する顧客オブジェクトを取得し、
                //　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。
                //　@　@　@[put()に指定する引数]
                //　@　@　@　@key：　@パラメータ.注文単位Row.取引者ID
                //　@　@　@　@value：　@取得した扱者オブジェクト
                else if (l_classInfo == WEB3GentradeTrader.class)
                {
                    WEB3GentradeTrader l_trader =
                        (WEB3GentradeTrader)l_finObjectManager.getTrader(
                            l_feqOrderUnitRow.getTraderId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getTraderId() + "", l_trader);
                }

                //　@　@外株銘柄
                //　@　@[パラメータ.クラス情報 == 外株銘柄.classの場合]
                //　@　@　@パラメータ.注文単位Row.銘柄IDに該当する外株銘柄オブジェクトを取得し、
                //　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。
                //　@　@　@[put()に指定する引数]
                //　@　@　@　@key：　@パラメータ.注文単位Row.銘柄ID
                //　@　@　@　@value：　@取得した外株銘柄オブジェクト
                else if (l_classInfo == WEB3FeqProduct.class)
                {
                    WEB3FeqProduct l_product =
                        (WEB3FeqProduct)l_web3FeqProductManager.getFeqProduct(
                            l_feqOrderUnitRow.getProductId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getProductId() + "", l_product);
                }

                //   市場
                //　@　@[パラメータ.クラス情報 == 市場.classの場合]
                //　@　@　@パラメータ.注文単位Row.市場IDに該当する市場オブジェクトを取得し、
                //　@　@　@パラメータ.取得済オブジェクト一覧に追加（put）する。
                //　@　@　@[put()に指定する引数]
                //　@　@　@　@key：　@パラメータ.注文単位Row.市場ID
                //　@　@　@　@value：　@取得した市場オブジェクト
                else if (l_classInfo == WEB3GentradeMarket.class)
                {
                    WEB3GentradeMarket l_market =
                        (WEB3GentradeMarket)l_finObjectManager.getMarket(
                            l_feqOrderUnitRow.getMarketId());
                    l_hmObjectList.put(l_feqOrderUnitRow.getMarketId() + "", l_market);
                }

                //通貨
                //[パラメータ.クラス情報 == （共通）通貨.classの場合]
                //　@パラメータ.注文単位Row.証券会社コード、通貨コードを引数として、
                //　@（共通）通貨オブジェクトを生成し、
                //　@パラメータ.取得済オブジェクト一覧に追加（put）する。
                //　@[put()に指定する引数]
                //　@　@key：　@パラメータ.注文単位Row.証券会社コード + 通貨コード
                //　@　@value：　@取得した（共通）通貨オブジェクト
                else if (l_classInfo == WEB3GentradeCurrency.class)
                {
                    WEB3GentradeCurrency l_currency =
                        WEB3GentradeCurrency.genCurrency(
                            l_feqOrderUnitRow.getInstitutionCode(),
                            l_feqOrderUnitRow.getCurrencyCode());
                    l_hmObjectList.put(
                        l_feqOrderUnitRow.getInstitutionCode() + l_feqOrderUnitRow.getCurrencyCode(),
                        l_currency);
                }

                //上記処理にて検索結果が取得できなかった場合、
                //「該当データなし」のシステムエラーをスローする。
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("該当データなし。");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "該当データなし。");
                }
            }
            catch(NotFoundException l_ex)
            {
                log.error("該当データなし。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //３）　@２）にて取得したオブジェクトを返却する。
            l_objRutern = l_hmObjectList.get(l_strKeyInfo);
            log.exiting(STR_METHOD_NAME);
            return l_objRutern;
        }
    }
}
@
