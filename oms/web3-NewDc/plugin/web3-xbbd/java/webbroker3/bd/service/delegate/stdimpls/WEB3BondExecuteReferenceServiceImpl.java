head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会サービスImpl (WEB3BondExecuteReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 周捷 (中訊) 新規作成
Revesion History : 2007/02/08 崔遠鵬 (中訊) 仕様変更・モデル158
Revesion History : 2007/07/11 周墨洋 (中訊) 仕様変更・モデル197
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.define.WEB3BondExecuteReferenceDetailUnitDef;
import webbroker3.bd.define.WEB3BondProductDivDef;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;
import webbroker3.bd.message.WEB3BondExecuteReferenceDetailUnit;
import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3BondExecuteReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券注文約定照会サービスImpl)<BR>
 * 債券注文約定照会サービス実装クラス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceServiceImpl extends WEB3BondClientRequestService
    implements WEB3BondExecuteReferenceService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceServiceImpl.class);
    
    /**
     * 債券注文約定照会処理を行う。 <BR>
     * <BR>
     * this.get注文約定照会()メソッドをコールする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        WEB3GenResponse l_response = null;
        
        //validate売却注文
        if (l_request instanceof WEB3BondExecuteReferenceRequest)
        {
            l_response = getExecuteReference(
                (WEB3BondExecuteReferenceRequest) l_request);
        }
        else 
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正");             
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get注文約定照会)<BR>
     * 債券注文約定照会処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（債券注文約定照会サービス）get注文約定照会」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondExecuteReferenceResponse
     * @@throws WEB3BaseException 
     * @@roseuid 44E9470401FC
     */
    protected WEB3BondExecuteReferenceResponse getExecuteReference(
        WEB3BondExecuteReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getExecuteReference(WEB3BondExecuteReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate( )
        l_request.validate();
        
        //1.2.validate注文受付可能( )
        //システム緊急停止中・バッチ中チェックを行う。
        WEB3BondTradingTimeManagement.validateOrderAccept();
        
        //1.3.createResponse( )
        WEB3BondExecuteReferenceResponse l_response =
            (WEB3BondExecuteReferenceResponse) l_request.createResponse();
        
        //1.4.create検索条件文字列(String)
        String l_strQueryString = createQueryString(l_request.productDiv);
        
        //1.5.create検索条件データ(String)
        Object[] l_objQueryDatas = createQueryData(l_request.productDiv);
        
        //1.6.createソート条件(債券ソートキー[])
        //ソート条件を作成する。 
        //[引数] 
        //ソートキー一覧： リクエストデータ.ソートキー
        String l_strSortCond = createSortCond(l_request.sortKeys);
        
        //1.7.get注文単位一覧(String, Object[], String)
        //指定条件に該当する拡張債券注文単位オブジェクトの一覧を取得する。 
        //[引数] 
        //検索条件文字列： 生成した検索条件文字列 
        //検索条件データコンテナ： 生成した検索条件データコンテナ 
        //ソート条件： 生成したソート条件
        List l_lisOrderUnitLists = 
            getOrderUnitList(l_strQueryString, l_objQueryDatas, l_strSortCond);
        
        //1.8.(*)該当データなし（get注文単位一覧()の戻り値 == null）の場合
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        if (l_lisOrderUnitLists == null)
        {
            //1.8.1.プロパティセット
            l_response.pageIndex = "0";
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.details = null;
            
            //1.8.2.レスポンスデータ
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            //1.9.ArrayList( )
            ArrayList l_lisUnitLists = new ArrayList();
            //1.10.(*)get注文単位一覧()の戻り値の要素数分Loop処理
            for (int i = 0; i < l_lisOrderUnitLists.size(); i++)
            {
                WEB3BondOrderUnit l_orderUnit = 
                    (WEB3BondOrderUnit) l_lisOrderUnitLists.get(i);
                
                //1.10.1.validate注文取消可能状態(拡張債券注文単位)
                //注文取消が可能かチェックを行う。 
                //[引数] 
                //債券注文単位： 処理対象の拡張債券注文単位
                try
                {
                    l_orderManager.validateOrderCancelPossibleStatus(l_orderUnit);
                }
                catch(WEB3BaseException l_ex)
                {
                    //1.10.2.(*)validate注文取消可能状態()が例外をthrowする場合
                    //1.10.2.1. (*)リクエストデータ.照会区分 == "取消一覧"の場合、Loop処理の先頭に戻る
                    if (WEB3BondReferenceTypeDef.CANCEL_LIST.equals(l_request.referenceType))
                    {
                        continue;
                    }
                    //1.10.2.2.(*)リクエストデータ.照会区分 == "注文約定照会"の場合、処理を続行する
                    else if (WEB3BondReferenceTypeDef.EXECUTE_REFERENCE.equals(l_request.referenceType))
                    {

                    }
                }
                
                //1.10.3.getProduct( )
                //拡張債券注文.銘柄IDに該当する債券銘柄オブジェクトを取得する。
                WEB3BondProduct l_product = (WEB3BondProduct) l_orderUnit.getProduct();
                BondOrderUnitRow l_unitRow = (BondOrderUnitRow) l_orderUnit.getDataSourceObject();
                
                //1.10.4.債券注文約定照会明細( )
                //債券注文約定照会明細クラスのインスタンスを生成する。
                WEB3BondExecuteReferenceDetailUnit l_detailUnit = new WEB3BondExecuteReferenceDetailUnit();
                
                ////1.10.5.プロパティセット
                //(*)生成した債券注文約定照会明細オブジェクトのプロパティに以下の値をセットする。
                
                //(*1)拡張債券注文単位.get債券タイプ() != "外国債券" の場合はnullをセット。
                //(*2)拡張債券注文単位.get通貨コード() == null or "T0" の場合はnullをセット。
                //(*3)this.get代理入力者() != null （コールセンターからの参照）の場合
                //のみセット。それ以外の場合、null。
                //注文ID　@　@　@　@　@　@　@　@＝　@拡張債券注文単位.getOrderId()の戻り値
                l_detailUnit.orderId = l_orderUnit.getOrderId() + "";
                
                //銘柄名　@　@　@　@　@　@　@　@＝　@債券銘柄.get銘柄名()の戻り値
                l_detailUnit.productName = l_product.getProductName();
                
                //種別コード　@　@　@　@　@　@＝　@債券銘柄.get種別コード()の戻り値
                l_detailUnit.bondCategCode = l_product.getBondCategCode();
                
                //取引区分　@　@　@　@　@　@　@＝　@拡張債券注文単位.get取引区分()の戻り値
                l_detailUnit.stateDiv = l_orderUnit.getDealDiv();
                
                //決済区分　@　@　@　@　@　@　@＝　@拡張債券注文単位.get決済区分()の戻り値
                l_detailUnit.settleDiv = l_orderUnit.getSettlementDiv();
                
                //額面金額　@　@　@　@　@　@　@＝　@拡張債券注文単位.get注文約定区分() == "約定済"の場合、
                //拡張債券注文単位.getExecutedQuantity()の戻り値                
                //　@　@　@　@　@　@　@　@　@　@　@　@　@拡張債券注文単位.get注文約定区分() != "約定済"の場合、
                //拡張債券注文単位.getQuantity()の戻り値
                if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
                {
                    if (!l_unitRow.getExecutedQuantityIsNull())
                    {
                        l_detailUnit.faceAmount = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());
                    }
                }
                else 
                {
                    if (l_unitRow.getQuantityIsSet())
                    {
                        l_detailUnit.faceAmount = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
                    }
                }
                
                //注文単価　@　@　@　@　@　@　@＝　@拡張債券注文単位.get注文約定区分() == "約定済"の場合、
                //拡張債券注文単位.get約定単価()の戻り値                
                //　@　@　@　@　@　@　@　@　@　@　@　@　@拡張債券注文単位.get注文約定区分() != "約定済"の場合、
                //拡張債券注文単位.getLimitPrice()の戻り値
                if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
                {
                    if (!l_unitRow.getExecutedPriceIsNull())
                    {
                        l_detailUnit.orderPrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedPrice());
                    }
                }
                else 
                {
                    if (!l_unitRow.getLimitPriceIsNull())
                    {
                        l_detailUnit.orderPrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
                    }
                }
                
                //売買代金（円貨）　@　@　@＝　@拡張債券注文単位.get売買代金（円貨）()の戻り値
                if (!l_unitRow.getTradingPriceIsNull())
                {
                    l_detailUnit.yenTradePrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getTradingPrice());
                }
                
                //経過利子（円貨）　@　@　@＝　@拡張債券注文単位.get経過利子（円貨）()の戻り値
                if (!l_unitRow.getAccruedInterestIsNull())
                {
                    l_detailUnit.yenAccruedInterest = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getAccruedInterest());
                }
                
                //受渡代金（円貨）　@　@　@＝　@拡張債券注文単位.get受渡代金（円貨）()の戻り値
                if (!l_unitRow.getEstimatedPriceIsNull())
                {
                    l_detailUnit.yenDeliveryPrice = 
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getEstimatedPrice());
                }
            
                if (BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
                {
                    //通貨コード(*1)　@　@　@　@＝　@拡張債券注文単位.get通貨コード()の戻り値
                    l_detailUnit.currencyCode = l_orderUnit.getCurrencyCode();
                    
                    //現地約定日(*1)　@　@　@　@＝　@拡張債券注文単位.get現地約定日()の戻り値
                    l_detailUnit.foreignExecutionDate = l_orderUnit.getForeignExecDate();
                }
                
                if (l_orderUnit.getCurrencyCode() != null && 
                    !WEB3GentradeCurrencyCodeDef.JPY.equals(l_orderUnit.getCurrencyCode()))
                {
                    //為替レート(*2)　@　@　@　@＝　@拡張債券注文単位.get注文約定区分() == "約定済"の場合、
                    //拡張債券注文単位.get約定為替レート()の戻り値
                    //　@　@　@　@　@　@　@　@　@　@　@　@　@拡張債券注文単位.get注文約定区分() != "約定済"の場合、
                    //拡張債券注文単位.get基準為替レート()の戻り値
                    if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
                    {
                        if (!l_unitRow.getExecFxRateIsNull())
                        {
                            l_detailUnit.fxRate = 
                                WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecFxRate());
                        }
                    }
                    else 
                    {
                        if (!l_unitRow.getBaseFxRateIsNull())
                        {
                            l_detailUnit.fxRate = 
                                WEB3StringTypeUtility.formatNumber(l_orderUnit.getBaseFxRate());
                        }
                    }
                    
                    //売買代金（外貨）(*2)　@＝　@拡張債券注文単位.get売買代金（外貨）()の戻り値
                    if (!l_unitRow.getForeignTradingPriceIsNull())
                    {
                        l_detailUnit.foreignTradePrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignTradingPrice());
                    }
                    
                    //経過利子（外貨）(*2)　@＝　@拡張債券注文単位.get経過利子（外貨）()の戻り値
                    if (!l_unitRow.getForeignAccruedInterestIsNull())
                    {
                        l_detailUnit.foreignAccruedInterest = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignAccruedInterest());
                    }
                    
                    //受渡代金（外貨）(*2)　@＝　@拡張債券注文単位.get受渡代金（外貨）()の戻り値
                    if (!l_unitRow.getForeignEstimatedPriceIsNull())
                    {
                        l_detailUnit.foreignDeliveryPrice = 
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignEstimatedPrice());
                    }
                }
                                
                //注文日時　@　@　@　@　@　@　@＝　@拡張債券注文単位.get受注日時()の戻り値
                l_detailUnit.orderDate = l_orderUnit.getReceivedDateTime();
                
                //約定日　@　@　@　@　@　@　@　@＝　@拡張債券注文単位.get約定日()の戻り値
                l_detailUnit.domesticExecutionDate = l_orderUnit.getExecDate();
                
                if (WEB3BondDealDivDef.RECRUIT.equals(l_orderUnit.getDealDiv()))
                {
                    //拡張債券注文単位.get取引区分 == "応募”の場合、
                    //受渡日　@＝　@受渡日拡張債券注文単位.get入金日()の戻り値
                    l_detailUnit.domesticDeliveryDate = l_orderUnit.getPaymentDate();

                    //現地受渡日(*1)＝拡張債券注文単位.get入金日()の戻り値
                    l_detailUnit.foreignDeliveryDate = l_orderUnit.getPaymentDate();
                }
                else
                {
                    //拡張債券注文単位.get取引区分 != "応募”の場合、
                    //受渡日　@＝　@拡張債券注文単位.get受渡日()の戻り値
                    l_detailUnit.domesticDeliveryDate = l_orderUnit.getDeliveryDate();

                    //現地受渡日(*1)＝拡張債券注文単位.get現地受渡日()の戻り値
                    l_detailUnit.foreignDeliveryDate = l_orderUnit.getForeignDeliveryDate();
                }
                //(*1)拡張債券注文単位.get債券タイプ() != "外国債券"の場合はnullをセット。
                if (!BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
                {
                    l_detailUnit.foreignDeliveryDate = null;
                }

                //注文状態　@　@　@　@　@　@　@＝　@拡張債券注文単位.get注文約定区分()の戻り値
                l_detailUnit.executionState = l_orderUnit.getOrderExecStatus();
                
                if (getTrader() != null)
                {
                    //注文経路区分(*3)　@　@　@　@　@＝　@拡張債券注文単位.get注文経路区分()の戻り値
                    l_detailUnit.orderRootDiv = l_orderUnit.getOrderRootDiv();
                    
                    //注文チャネル(*3)　@　@　@＝　@拡張債券注文単位.get初回注文の注文チャネル()の戻り値
                    l_detailUnit.orderChannel = l_orderUnit.getOrderChannel();
                    
                    //オペレータコード(*3)　@＝　@
                    //拡張金融オブジェクトマネージャ.getTrader(拡張債券注文単位.getTraderId()).getTraderCode()の戻り値
                    if(l_orderUnit.getTraderId() != 0)
                    {
                        try
                        {
                            l_detailUnit.operatorCode = 
                                l_finObjectManager.getTrader(l_orderUnit.getTraderId()).getTraderCode();
                        }
                        catch (NotFoundException l_ex)
                        {
                            log.error("テーブルに該当するデータがありません。",l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }
                }
                
                //取消可能フラグ　@＝　@validate注文取消可能状態()が例外をthrowしない場合はtrue
                //　@　@　@　@　@　@　@　@　@validate注文取消可能状態()が例外をthrowした場合はfalse
                try
                {
                    l_orderManager.validateOrderCancelPossibleStatus(l_orderUnit);
                    l_detailUnit.cancelDiv  = true;
                }
                catch(WEB3BaseException l_ex)
                {
                    l_detailUnit.cancelDiv  = false;
                }
                
                //1.10.6.add(オブジェクト : Object)
                //ArrayListにプロパティセットした債券注文約定照会明細インスタンスを追加する。 
                //[引数] 
                //オブジェクト： プロパティセットした債券注文約定照会明細インスタンス
                l_lisUnitLists.add(l_detailUnit);
            }
            
            //1.11.(*)ArrayListの要素数 == 0 の場合
            if (l_lisUnitLists.size() == 0)
            {
                //1.11.1.プロパティセット
                l_response.pageIndex = "0";
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
                l_response.details = null;
                
                //1.11.2.レスポンスデータ
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            
            //1.12.WEB3PageIndexInfo(リスト : List, 引数1 : int, 引数2 : int)
            //WEB3PageIndexInfoインスタンスを生成する。 
            //[引数] 
            //リスト： ArrayList 
            //引数1： リクエストデータ.要求ページ番号 
            //引数2： リクエストデータ.ページ内表示行数
            
            int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
            
            WEB3PageIndexInfo l_pageIndexInfo = 
                new WEB3PageIndexInfo(
                    l_lisUnitLists, 
                    l_intRequestPageIndex, 
                    l_intRequestPageSize);
            
            //1.13.getArrayReturned(クラス : Class)
            //表示対象の債券注文約定照会明細の配列を取得する。 
            //[引数] 
            //クラス： 債券注文約定照会明細.class
            WEB3BondExecuteReferenceDetailUnit[] l_executeReferenceDetailUnitList = 
                (WEB3BondExecuteReferenceDetailUnit[])l_pageIndexInfo.getArrayReturned(
                    WEB3BondExecuteReferenceDetailUnit.class);
            
            //1.14.プロパティセット
            //表示ページ番号 = WEB3PageIndexInfo.getPageIndex()
            //総ページ数 = WEB3PageIndexInfo.getTotalPages()
            //総レコード数 = WEB3PageIndexInfo.getTotalRecords()
            //債券注文約定照会明細一覧 = 取得した債券注文約定照会明細の配列
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
            l_response.details = l_executeReferenceDetailUnitList;
        }
        
        //1.15.メッセージ レスポンスデータ
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成し、返却する。<BR>
     * <BR>
     * １）　@以下の文字列を返却する。<BR>
     * <BR>
     * 　@パラメータ．商品区分＝＝"すべての銘柄" or パラメータ．商品区分＝＝null の場合<BR>
     * 　@”account_id = ? and sub_account_id = ?<BR>
     * 　@　@and (biz_date >= ? or order_exec_status = ?)”<BR>
     * <BR>
     * 　@パラメータ．商品区分＝＝"外国債券銘柄のみ"<BR>
     *   or パラメータ．商品区分＝＝"個人向け国債のみ" の場合<BR>
     * 　@　@”account_id = ? and sub_account_id = ?<BR>
     * 　@　@　@and (biz_date >= ? or order_exec_status = ?) and bond_type = ? ”<BR>
     * <BR>
     * 　@パラメータ．商品区分＝＝"国内債券（個人向け国債を含む）" の場合<BR>
     * 　@　@”account_id = ? and sub_account_id = ? and (biz_date >= ?<BR>
     * 　@　@　@or order_exec_status = ?) and bond_type not in(?) ”<BR>
     * <BR>
     * 　@パラメータ．商品区分＝＝"国内債券（個人向け国債を含まない）" の場合<BR>
     * 　@　@”account_id = ? and sub_account_id = ? and (biz_date >= ?<BR>
     * 　@　@　@or order_exec_status = ?) and bond_type not in(?, ?) ”<BR>
     * @@param l_strReferenceType - (商品区分)<BR>
     * 商品区分
     * @@return String
     */
    protected String createQueryString(String l_strProductDiv)
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);

        String l_strQueryString = null;

        // １）　@以下の文字列を返却する。
        // パラメータ．商品区分＝＝"すべての銘柄" or パラメータ．商品区分＝＝null の場合
        if (WEB3BondProductDivDef.ALL_PRODUCT.equals(l_strProductDiv)
            || l_strProductDiv == null)
        {
            // パラメータ．商品区分＝＝"すべての銘柄" or パラメータ．商品区分＝＝null の場合
            // account_id = ? and sub_account_id = ?
            // and (biz_date >= ? or order_exec_status = ?)
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) ";
        }
        else if (WEB3BondProductDivDef.FOREIGN_BOND_ONLY.equals(l_strProductDiv)
            || WEB3BondProductDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strProductDiv))
        {
            // パラメータ．商品区分＝＝"外国債券銘柄のみ"
            // or パラメータ．商品区分＝＝"個人向け国債のみ" の場合
            // ”account_id = ? and sub_account_id = ?
            // and (biz_date >= ? or order_exec_status = ?) and bond_type = ? ”
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) and bond_type = ? ";
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND.equals(l_strProductDiv))
        {
            // パラメータ．商品区分＝＝"国内債券（個人向け国債を含む）" の場合
            // ”account_id = ? and sub_account_id = ? and (biz_date >= ?
            // and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?) ”
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?) ";
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(l_strProductDiv))
        {
            // パラメータ．商品区分＝＝"国内債券（個人向け国債を含まない）" の場合
            // account_id = ? and sub_account_id = ? and (biz_date >= ?
            // or order_exec_status = ?) and bond_type not in(?, ?) ”
            l_strQueryString = "account_id = ? and sub_account_id = ? "
                + "and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?, ?) ";
        }

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データ)<BR>
     * 検索条件データコンテナ(：Object[]）を作成し、返却する。 <BR>
     * <BR>
     * １）　@ArrayListを生成する。 <BR>
     * <BR>
     * ２）　@this.get補助口座()をコールし、補助口座オブジェクトを取得する。 <BR>
     * <BR>
     * ３）　@取得した補助口座.口座IDをLongクラスに変換したオブジェクトを、<BR> 
     * 　@ArrayListに追加する。 <BR>
     * <BR>
     * ４）　@取得した補助口座.補助口座IDをLongクラスに変換したオブジェクトを、<BR> 
     * 　@ArrayListに追加する。 <BR>
     * <BR>
     * ５）　@業務日付(*1)を”YYYYMMDD”形式に変換した文字列をArrayListに追加する。<BR> 
     * <BR>
     * ６）　@未約定（約定状態区分）をArrayListに追加する。<BR>
     * <BR>
     * ７）　@商品区分をArrayListに追加する。<BR>
     * 　@パラメータ．商品区分＝＝"外国債券銘柄のみ"
     *   or パラメータ．商品区分＝＝"国内債券（個人向け国債を含む）" の場合<BR>
     * 　@ArrayListに"債券タイプ．外国債券"を追加する。<BR>
     * <BR>
     * 　@パラメータ．商品区分＝＝"国内債券（個人向け国債を含まない）" の場合<BR>
     * 　@ArrayListに"債券タイプ．外国債券"を追加する。<BR>
     * 　@ArrayListに"債券タイプ．個人向け国債"を追加する。<BR>
     * <BR>
     * 　@パラメータ．商品区分＝＝"個人向け国債のみ" の場合<BR>
     * 　@ArrayListに"債券タイプ．個人向け国債"を追加する。<BR>
     * ８）　@ArrayList.toArray()の戻り値を返却する。 <BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_strReferenceType - (商品区分)<BR>
     * 商品区分
     * @@return Object[]
     * @@throws WEB3BaseException 
     */
    protected Object[] createQueryData(String l_strProductDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryData(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListを生成する。
        List l_lisQuery = new ArrayList();
        

        //２）　@this.get補助口座()をコールし、補助口座オブジェクトを取得する。 
        SubAccount l_subAccount = getSubAccount();
        
        //３）　@取得した補助口座.口座IDをLongクラスに変換したオブジェクトを、 
        //　@ArrayListに追加する。 
        l_lisQuery.add(new Long(l_subAccount.getAccountId()));
        
        //４）　@取得した補助口座.補助口座IDをLongクラスに変換したオブジェクトを、 
        //　@ArrayListに追加する。 
        l_lisQuery.add(new Long(l_subAccount.getSubAccountId()));
        
        //５）　@業務日付(*1)を”YYYYMMDD”形式に変換した文字列をArrayListに追加する。 
        String l_strBizDate = 
            WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
        l_lisQuery.add(l_strBizDate);
        
        //６）　@未約定（約定状態区分）をArrayListに追加する。
        l_lisQuery.add(WEB3BondOrderExecStatusDef.UNEXECUTED);

        //７）　@商品区分をArrayListに追加する。
        if(WEB3BondProductDivDef.FOREIGN_BOND_ONLY.equals(l_strProductDiv)
            || WEB3BondProductDivDef.DOMESTIC_BOND.equals(l_strProductDiv))
        {
            //パラメータ．商品区分＝＝"外国債券銘柄のみ"
            //or パラメータ．商品区分＝＝"国内債券（個人向け国債を含む）" の場合
            //ArrayListに"債券タイプ．外国債券"を追加する。
            l_lisQuery.add(BondTypeEnum.FOREIGN_BOND);
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(l_strProductDiv))
        {
            //パラメータ．商品区分＝＝"国内債券（個人向け国債を含まない）" の場合
            //ArrayListに"債券タイプ．外国債券"を追加する。
            //ArrayListに"債券タイプ．個人向け国債"を追加する。
            l_lisQuery.add(BondTypeEnum.FOREIGN_BOND);
            l_lisQuery.add(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
        }
        else if (WEB3BondProductDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strProductDiv))
        {
            //パラメータ．商品区分＝＝"個人向け国債のみ" の場合
            //ArrayListに"債券タイプ．個人向け国債"を追加する。
            l_lisQuery.add(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
        }
        
        //８）　@ArrayList.toArray()の戻り値を返却する。 
        //(*1)GtlUtils.getTradingSystem().getBizDate()
        log.exiting(STR_METHOD_NAME);
        return l_lisQuery.toArray();
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を作成し、返却する。<BR> 
     * <BR>
     * １）　@パラメータ.ソートキー一覧の要素数分、対応するテーブルの列物理名、 <BR>
     * 　@　@昇順／降順指定を付加しセット <BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り <BR>
     * 　@　@　@※テーブル名：債券注文単位テーブル(bond_order_unit) <BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照 <BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照 <BR>
     * <BR>
     * 　@　@　@　@変換前　@　@　@　@　@　@　@　@　@変換後 <BR>
     * -------------　@　@　@　@----------------------------- <BR>
     * 　@　@　@　@・銘柄名　@　@　@　@　@　@　@　@：債券注文単位テーブル.銘柄ID <BR>
     * 　@　@　@　@・取引区分　@　@　@　@　@　@　@：債券注文単位テーブル.取引 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@債券注文単位テーブル.注文種別 <BR>
     * 　@　@　@　@・決済区分　@　@　@　@　@　@　@：債券注文単位テーブル.決済区分 <BR>
     * 　@　@　@　@・注文日時　@　@　@　@　@　@　@：債券注文単位テーブル.受注日時 <BR>
     * 　@　@　@　@・注文状態　@　@　@　@　@　@　@：債券注文単位テーブル.注文約定区分 <BR>
     * <BR>
     * 　@　@・昇順／降順指定は、債券ソートキー.昇順／降順 指定に従い設定 <BR>
     * <BR>
     * ２）　@作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー一覧)<BR>
     * ソートキー一覧
     * @@return String
     * @@throws WEB3BaseException 
     */
    protected String createSortCond(WEB3BondSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        StringBuffer l_sbReturn = new StringBuffer(); 
        
        //１）　@パラメータ.ソートキー一覧の要素数分、対応するテーブルの列物理名、 
        //　@昇順／降順指定を付加しセット 
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //・銘柄名　@　@　@　@　@　@　@　@：債券注文単位テーブル.銘柄ID 
            if (WEB3BondExecuteReferenceDetailUnitDef.PRODUCT_NAME.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.product_id");
            }
            //　@　@　@　@・取引区分　@　@　@　@　@　@　@：債券注文単位テーブル.取引 
            //            債券注文単位テーブル.注文種別 
            else if (WEB3BondExecuteReferenceDetailUnitDef.STATE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.deal_type, bond_order_unit.order_type");
            }
            //　@　@　@　@・決済区分　@　@　@　@　@　@　@：債券注文単位テーブル.決済区分 
            else if(WEB3BondExecuteReferenceDetailUnitDef.SETTLE_DIV.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.settlement_div");
            }
            //　@　@　@　@・注文日時　@　@　@　@　@　@　@：債券注文単位テーブル.受注日時 
            else if(WEB3BondExecuteReferenceDetailUnitDef.ORDER_DATE.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.received_date_time");
            }
            //　@　@　@　@・注文状態　@　@　@　@　@　@　@：債券注文単位テーブル.注文約定区分 
            else if(WEB3BondExecuteReferenceDetailUnitDef.EXECUTION_STATE.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbReturn.append("bond_order_unit.order_exec_status");
            }
            else
            {
                continue;
            }
            //・昇順／降順指定は、債券ソートキー.昇順／降順 指定に従い設定
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbReturn.append("　@ASC , ");
                }
                else
                {
                    l_sbReturn.append("　@ASC  ");
                }
            }
            else
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbReturn.append("　@DESC , ");
                }
                else
                {
                    l_sbReturn.append("　@DESC  ");
                }
            }
        }
        
        //２）　@作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbReturn.toString();
    }
    
    /**
     * (get注文単位一覧)<BR>
     * 指定条件に該当する拡張債券注文単位オブジェクトの一覧を返却する。 <BR>
     * <BR>
     * １）　@QueryProcessor.doFindAllQuery()により、BondOrderUnitRowのListを取得する。 <BR>
     * 　@　@　@[引数] <BR>
     * 　@　@　@Rowタイプ： 債券注文単位Row.TYPE <BR>
     * 　@　@　@検索条件文字列： パラメタ.検索条件文字列 <BR>
     * 　@　@　@ソート条件： パラメタ.ソート条件 <BR>
     * 　@　@　@クエリ条件： null <BR>
     * 　@　@　@検索条件データコンテナ： パラメタ.検索条件データコンテナ <BR>
     * <BR>
     * 　@　@　@※該当データなしの場合、nullを返却する。 <BR>
     * <BR>
     * ２）　@ArrayListを生成する。 <BR>
     * <BR>
     * ３）　@１）の戻り値の要素数分以下の処理をLoopする。 <BR>
     * 　@　@　@－　@拡張債券注文マネージャ.to債券注文単位((*1)BondOrderUnitRow)メソッドをコールする。<BR> 
     * 　@　@　@－　@to債券注文単位()の戻り値をArrayListに追加する。 <BR>
     * <BR>
     * 　@　@　@(*1)BondOrderUnitRow・・・処理対象の要素をBondOrderUnitRowにキャストする。 <BR>
     * <BR>
     * ４）　@ArrayListを返却する。<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列
     * @@param l_objQueryDatas - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件
     * @@return ArrayList
     * @@throws WEB3BaseException 
     */
    protected ArrayList getOrderUnitList(
        String l_strQueryString, Object[] l_objQueryDatas, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@QueryProcessor.doFindAllQuery()により、BondOrderUnitRowのListを取得する。 
        // 　@[引数] 
        // 　@　@Rowタイプ： 債券注文単位Row.TYPE 
        // 　@　@検索条件文字列： パラメタ.検索条件文字列 
        // 　@　@ソート条件： パラメタ.ソート条件 
        // 　@　@クエリ条件： null 
        // 　@　@検索条件データコンテナ： パラメタ.検索条件データコンテナ
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    BondOrderUnitRow.TYPE,
                    l_strQueryString,
                    l_strSortCond,
                    null,
                    l_objQueryDatas);
        }
        catch (DataException l_de)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME, l_de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);            
        }
        
        //※該当データなしの場合、nullを返却する。 
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２）　@ArrayListを生成する。 
        ArrayList l_lisOrderUnits = new ArrayList();
        
        //３）　@１）の戻り値の要素数分以下の処理をLoopする。  
        // 　@－　@to債券注文単位()の戻り値をArrayListに追加する。 
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            BondOrderUnitRow l_orderUnitRow = (BondOrderUnitRow) l_lisRecords.get(i);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3BondOrderManager l_orderManager = 
                (WEB3BondOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getOrderManager();
            
            // 　@－　@拡張債券注文マネージャ.to債券注文単位((*1)BondOrderUnitRow)メソッドをコールする。
            //(*1)BondOrderUnitRow・・・処理対象の要素をBondOrderUnitRowにキャストする。 
            OrderUnit l_orderUnit = l_orderManager.toOrderUnit(l_orderUnitRow);
            
            //－　@to債券注文単位()の戻り値をArrayListに追加する。 
            l_lisOrderUnits.add(l_orderUnit);
        }

        //４）　@ArrayListを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisOrderUnits;
    }
}
@
