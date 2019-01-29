head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式残高照会サービスImpl(WEB3FeqBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー     
Revesion History : 2007/11/20 柴双紅(中訊) モデルNo.363
Revesion History : 2008/01/14 柴双紅(中訊) モデルNo.370、No.375、No.382、No.383
                                           No.384、No.385、No.386、No.387
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2008/02/02 柴双紅(中訊) モデルNo.396
Revesion History : 2008/03/03 馮海濤(中訊) モデルNo.410、モデルNo.411
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBalanceReferenceComparator;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqBalanceReferenceDetailUnit;
import webbroker3.feq.message.WEB3FeqBalanceReferenceRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceResponse;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalResponse;
import webbroker3.feq.message.WEB3FeqProductCodeNameUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3FeqBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (外国株式残高照会サービスImpl)<BR>
 * 外国株式残高照会サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqBalanceReferenceServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqBalanceReferenceService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F502BF
     */
    public WEB3FeqBalanceReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式残高照会サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * リクエストデータが、<BR>
     * [外国株式残高照会リクエストの場合]<BR>
     * 　@this.get残高照会()をコールする。<BR>
     * <BR>
     * [外国株式残高合計リクエストの場合]<BR>
     * 　@this.get残高合計()をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A8056C01D1
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
        
        //リクエストデータの型により、以下のメソッドをコールする。 
        // リクエストデータが、[外国株式残高照会リクエストの場合] 
        //　@this.get残高照会()をコールする。
        if (l_request instanceof WEB3FeqBalanceReferenceRequest)
        {
            l_response = 
                this.getBalanceReference(
                    (WEB3FeqBalanceReferenceRequest)l_request);   
        }        
        //[外国株式残高合計リクエストの場合] 
        //this.get残高合計()をコールする。
        else if (l_request instanceof WEB3FeqBalanceReferenceTotalRequest)
        {
            l_response =
                this.getBalanceTotal(
                    (WEB3FeqBalanceReferenceTotalRequest)l_request);
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
     * (get残高照会)<BR>
     * 外国株式残高照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株残高照会）get残高照会」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式残高照会リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqBalanceReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A805AC01B2
     */
    protected WEB3FeqBalanceReferenceResponse getBalanceReference(
            WEB3FeqBalanceReferenceRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getBalanceReference(WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 validate注文受付可能( )(取引時間管理::validate注文受付可能)
        //"照会"がバッチ中・システム緊急停止中であるかどうかチェックする。

        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.4 create銘柄情報一覧(補助口座)
        //顧客の保持する資産から銘柄情報の一覧を作成する。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値
        WEB3FeqProductCodeNameUnit[] l_productCodeNameUnit = 
            this.createProductInformationList(l_subAccount);
        
        //1.5 get取引中取扱可能市場(部店, ProductTypeEnum)
        //取引中の取扱可能市場を取得する。 

        //[引数] 
        //部店：　@get補助口座()の戻り値.get取引店() 
        //銘柄タイプ：　@ProductTypeEnum.外国株式
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)
            l_subAccount.getMainAccount().getBranch();
        
        String[] l_strMarketList = 
            WEB3GentradeFeqBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY);

        //1.6 create明細一覧(補助口座, 外国株式残高照会リクエスト)
        //残高照会明細の一覧を作成する。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //リクエストデータ：　@リクエストデータ 
        WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetails = 
            this.createDetailList(l_subAccount, l_request);
        
        //1.7 sort残高照会明細(外国株式残高照会明細[], 外国株式ソートキー[])
        //残高照会明細をソートする。 
        //[引数] 
        //残高照会明細：　@create明細一覧()の戻り値 
        //ソートキー：　@リクエストデータ.ソートキー
        this.sortBalanceReferenceDetailUnit(
            l_balanceReferenceDetails, 
            l_request.sortKeys);

        //1.8 WEB3PageIndexInfo()
        //WEB3PageIndexInfoインスタンスを生成する。 
        //[引数] 
        //arg0：　@sort残高照会明細()の戻り値
        //arg1：　@要求ページ番号 
        //arg2：　@ページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_balanceReferenceDetails, 
            Integer.parseInt(l_request.pageIndex), 
            Integer.parseInt(l_request.pageSize));
        
        //1.9 getArrayReturned(arg0 : Class)
        //表示対象行の外国株式残高照会明細の配列を生成する。 
        //[引数] 
        //arg0：　@外国株式残高照会明細.class
        l_balanceReferenceDetails = (WEB3FeqBalanceReferenceDetailUnit[])
            l_pageIndexInfo.getArrayReturned(
                WEB3FeqBalanceReferenceDetailUnit.class);
        
        //1.10 表示ページ番号を取得する。
        String l_strPageIndex = Integer.toString(l_pageIndexInfo.getPageIndex());
        
        //1.11 総ページ数を取得する。
        String l_strTotalPages = Integer.toString(l_pageIndexInfo.getTotalPages());
        
        //1.12 総レコード数を取得する。
        String l_strTotalRecords = Integer.toString(l_pageIndexInfo.getTotalRecords());
        
        //1.13 レスポンスデータを生成する。 
        WEB3FeqBalanceReferenceResponse l_response = 
            (WEB3FeqBalanceReferenceResponse)l_request.createResponse();
        
        //1.14 (*)プロパティセット
        //銘柄一覧 = create銘柄情報一覧()の戻り値
        l_response.productCodeNames = l_productCodeNameUnit;
        
        //市場コード一覧 = get取引中取扱可能市場()の戻り値
        l_response.marketList = l_strMarketList;
        
        //明細一覧 = getArrayReturned()の戻り値
        l_response.foreignBalanceReferenceDetail = l_balanceReferenceDetails;
        
        //レスポンス.表示ページ番号 = getPageIndex()の戻り値
        l_response.pageIndex = l_strPageIndex;
        
        //レスポンス.総ページ数 = getTotalPages()の戻り値
        l_response.totalPages = l_strTotalPages;
         
        //レスポンス.総レコード数 = getTotalRecords()の戻り値
        l_response.totalRecords = l_strTotalRecords;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get残高合計)<BR>
     * 外国株式残高合計処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株残高照会）get残高合計」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式残高合計リクエストオブジェクト
     * @@return WEB3FeqBalanceReferenceTotalResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A805AC01D1
     */
    protected WEB3FeqBalanceReferenceTotalResponse getBalanceTotal(
            WEB3FeqBalanceReferenceTotalRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(" +
            "WEB3FeqBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate注文受付可能( )(取引時間管理::validate注文受付可能)
        //"照会"がバッチ中・システム緊急停止中であるかどうかチェックする。

        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2 補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.3 get保有資産一覧()
        //保有資産一覧を取得する。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //銘柄タイプ：　@ProductTypeEnum.外国株式 
        //検索条件文字列：　@null 
        //検索条件データコンテナ：　@null 
        //ソート条件：　@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
    
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        
        List l_lisAssetRows = 
            l_feqPositionManager.getAssetList(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                null,
                null,
                null);     
        
        //1.4 createResponse( )
        WEB3FeqBalanceReferenceTotalResponse l_response = 
            (WEB3FeqBalanceReferenceTotalResponse)l_request.createResponse();

        if (l_lisAssetRows == null)
        {
            return l_response;
        }
        log.debug("get保有資産一覧.size = " + l_lisAssetRows.size());
        
        //1.5 時価情報を格納するHashMapを生成する。
        HashMap l_hashMap = new HashMap();
        
        double l_dblNormalTotal = 0.0D;
        double l_dblNormalTotalProfitLoss = 0.0D;
        double l_dblCapitalTotal = 0.0D;
        double l_dblCapitalTotalProfitLoss = 0.0D;        
        
        //1.6 (*)get保有資産一覧()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lisAssetRows.size(); i++)
        {
            //1.6.1 外国株式銘柄を取得する。 
            //arg0：　@保有資産.銘柄ID            
            AssetRow l_assetRow = (AssetRow)l_lisAssetRows.get(i);
            log.debug("保有資産Row = " + l_assetRow);
            WEB3FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = (WEB3FeqProduct)l_feqProductManager.getProduct(
                    l_assetRow.getProductId());
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
            
            if (l_feqProduct == null)
            {
                log.debug("Error in 外国株式銘柄を取得する");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.6.2 get時価情報(補助口座, 外国株式銘柄, HashMap)
            //時価情報を取得する。 
            //[引数] 
            //補助口座：　@get補助口座()の戻り値 
            //外株銘柄：　@getProduct()の戻り値 
            //時価情報格納リスト：　@生成したHashMap
            WEB3FeqProductQuote l_productQuote = 
                this.getEquityProductQuote(
                    l_subAccount, l_feqProduct, l_hashMap);
            
            //1.6.3 通貨を取得する。
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
            if (l_productQuote != null)
            {
                //1.6.4 calc評価額(double, double, 通貨)
                //評価額（円貨）を算出する。 
                //[引数] 
                //評価単価：　@get時価情報()の戻り値.時価 
                //数量：　@保有資産.数量 + 保有資産.売付不能数量 
                //通貨：　@通貨
                double l_dblEstimatedValue = 
                    this.calcEstimatedValue(
                        l_productQuote.getCurrentPrice(), 
                        l_assetRow.getQuantity() + l_assetRow.getQuantityCannotSell(), 
                        l_currency);
                
                log.debug("calc評価額の戻り値 = " + l_dblEstimatedValue);
                
                //1.6.5 calc概算簿価単価(double, double)
                //概算簿価単価（円貨）を算出する。 
                //[引数] 
                //簿価：　@保有資産.簿価（簿価単価計算用） 
                //数量：　@保有資産.数量（簿価単価計算用）
                WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                    (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
                BigDecimal l_bdBookValuePrice = 
                    l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                        l_assetRow.getBookValue(), 
                        l_assetRow.getQuantityForBookValue());
    
                log.debug("calc概算簿価単価の戻り値 = " + l_bdBookValuePrice);

                //1.6.7 calc評価損益(double, double, double, 通貨)
                //評価損益（円貨）を算出する。 
                //[引数] 
                //評価単価：　@get時価情報()の戻り値.時価 
                //簿価：　@calc概算簿価単価()の戻り値
                //数量：　@保有資産.数量 + 保有資産.売付不能数量 
                //通貨：　@通貨 
                double l_dblProFitLoss = 
                    this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(),
                        l_bdBookValuePrice,
                        l_assetRow.getQuantity() + l_assetRow.getQuantityCannotSell(), 
                        l_currency);
    
                log.debug("calc評価損益の戻り値 = " + l_dblProFitLoss);
                
                //1.6.8 (*)保有資産.税区分 == "一般"の場合
                if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
                {
                    log.debug("保有資産.税区分 == '一般'の場合");
                    //1.6.8.1 (*)一般口座評価額合計、一般口座評価損益合計に加算する
                    l_dblNormalTotal += l_dblEstimatedValue;
                    
                    l_response.normalAccountTotalAsset = 
                        WEB3StringTypeUtility.formatNumber(l_dblNormalTotal);
                    
					// ※各概算評価損益額は、概算簿価単価（円貨・小数点6桁を四捨五入）
					//    != 0の場合のみセット。
                    if ((l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP)).doubleValue() != 0)
                    {
                        l_dblNormalTotalProfitLoss += l_dblProFitLoss;
						l_response.normalAccountTotalAppraisalProfitLoss = 
							WEB3StringTypeUtility.formatNumber(l_dblNormalTotalProfitLoss);	
                    }
                }
                //1.6.9 (*)保有資産.税区分 == "特定"の場合
                else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
                {
                    log.debug("保有資産.税区分 == '特定'の場合");
                    //1.6.9.1 (*)特定口座評価額合計、特定口座評価損益合計に加算する
                    l_dblCapitalTotal += l_dblEstimatedValue;
                    
                    l_response.capitalGainTotalAsset = 
                        WEB3StringTypeUtility.formatNumber(l_dblCapitalTotal);

                    l_dblCapitalTotalProfitLoss += l_dblProFitLoss;
                    l_response.capitalGainTotalAppraisalProfitLoss = 
                        WEB3StringTypeUtility.formatNumber(l_dblCapitalTotalProfitLoss);
                }
            }
        }
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create銘柄情報一覧)<BR>
     * 引数の補助口座に該当する保有資産から<BR>
     * 銘柄情報の一覧を作成し、返却する。<BR>
     * <BR>
     * １）保有資産取得<BR>
     * 　@外国株式ポジションマネージャ.get保有資産一覧()メソッドをコールする。<BR>
     * <BR>
     * 　@[get保有資産一覧()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@銘柄タイプ：　@ProductTypeEnum.外国株式<BR>
     * 　@　@検索条件文字列：　@null<BR>
     * 　@　@検索条件データコンテナ：　@null<BR>
     * 　@　@ソート条件：　@"product_id asc"<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）ArrayListを生成する。<BR>
     * <BR>
     * ３）１）の戻り値の要素(=保有資産オブジェクト)数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@３－１）処理対象の保有資産.getProduct()にて<BR>
     * 　@　@外国株式銘柄を取得する。<BR>
     * <BR>
     * 　@３－２）銘柄コード重複チェック<BR>
     * 　@　@ArrayListに、取得した外国株式銘柄.銘柄コードと同じ<BR>
     * 　@　@銘柄コードを保持する要素が存在する場合、<BR>
     * 　@　@次の要素へ処理を移行する。(continue;)<BR>
     * <BR>
     * 　@３－３）外国株式銘柄情報インスタンスを生成する。<BR>
     * <BR>
     * 　@３－４）生成したインスタンスに、以下のプロパティをセットする。<BR>
     * 　@　@銘柄コード = 取得した外国株式銘柄.銘柄コード<BR>
     * 　@　@銘柄名 = 取得した外国株式銘柄.get表示銘柄名()の戻り値<BR>
     * <BR>
     * 　@３－５）生成したArrayListにプロパティセットした<BR>
     * 　@　@インスタンスを追加する。<BR>
     * <BR>
     * ４）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@return WEB3FeqProductCodeNameUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A80881027D
     */
    protected WEB3FeqProductCodeNameUnit[] createProductInformationList(
        WEB3GentradeSubAccount l_subAccount) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductInformationList(" +
            "WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）保有資産取得 
        //外国株式ポジションマネージャ.get保有資産一覧()メソッドをコールする。 

        //[get保有資産一覧()にセットするパラメータ] 
        //　@補助口座：　@パラメータ.補助口座 
        //　@銘柄タイプ：　@ProductTypeEnum.外国株式 
        //　@検索条件文字列：　@null 
        //　@検索条件データコンテナ：　@null 
        //　@ソート条件：　@"product_id asc" 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
    
        List l_lisAssetRows = 
            l_feqPositionManager.getAssetList(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                null,
                null,
                "product_id asc");        
        
        //検索結果が取得できなかった場合は、nullを返却する。 
        if (l_lisAssetRows == null || l_lisAssetRows.size() == 0)
        {
            return null;
        }
        
        //２）ArrayListを生成する。 
        List l_lisProductCodeNames = new ArrayList();
        
        //３）１）の戻り値の要素(=保有資産オブジェクト)数分、 
        //  以下の処理を繰り返す。                
        List l_lisProductCode = new ArrayList();
        
        for (int i = 0; i < l_lisAssetRows.size(); i++)
        {
            //３－１）処理対象の保有資産.getProduct()にて 
            //　@ 外国株式銘柄を取得する。 
            AssetRow l_assetRow = (AssetRow)l_lisAssetRows.get(i);
            log.debug("保有資産Row = " + l_assetRow);
            Asset l_asset = null;
            try
            {
                l_asset = 
                    l_feqPositionManager.getAsset(l_assetRow.getAssetId());
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
            FeqProduct l_feqProduct = (FeqProduct)l_asset.getProduct();
            
            //３－２）銘柄コード重複チェック 
            //ArrayListに、取得した外国株式銘柄.銘柄コードと同じ 
            //銘柄コードを保持する要素が存在する場合、 
            //次の要素へ処理を移行する。(continue;)

            log.debug("銘柄コード = " + l_feqProduct.getProductCode());
            
            if (WEB3Toolkit.contain(l_lisProductCode, l_feqProduct.getProductCode()))
            {
                continue;
            }
            
            l_lisProductCode.add(l_feqProduct.getProductCode());
            
            //３－３）外国株式銘柄情報インスタンスを生成する。
            WEB3FeqProductCodeNameUnit l_productCodeNameUnit = new 
                WEB3FeqProductCodeNameUnit();
            
            //３－４）生成したインスタンスに、以下のプロパティをセットする。 
            //銘柄コード = 取得した外国株式銘柄.銘柄コード 
            //銘柄名 = 取得した外国株式銘柄.get表示銘柄名()の戻り値
            l_productCodeNameUnit.productCode = l_feqProduct.getProductCode();
            l_productCodeNameUnit.productName = l_feqProduct.getStandardName();
            
            //３－５）生成したArrayListにプロパティセットしたインスタンスを追加する。 
            l_lisProductCodeNames.add(l_productCodeNameUnit);
        }       
        
        //４）生成したArrayList.toArray()の戻り値を返却する。
        WEB3FeqProductCodeNameUnit[] l_feqProductCodeNameUnits = new 
            WEB3FeqProductCodeNameUnit[l_lisProductCodeNames.size()];
                
        l_lisProductCodeNames.toArray(l_feqProductCodeNameUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqProductCodeNameUnits;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * (createQueryString) <BR>
     * 検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * １）パラメータ.銘柄コード == nullの場合、<BR>
     * 　@nullを返却して終了する。<BR>
     * <BR>
     * ２）銘柄コードに該当する銘柄IDの検索条件文字列を作成する。<BR>
     * <BR>
     * 　@検索条件文字列 = " and product_id = ? "<BR>
     * <BR>
     * ３）作成した検索条件文字列を返却する。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@return String
     * @@roseuid 42A80C63000C
     */
    protected String createQueryString(String l_strProductCode) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(" +
            "WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //検索条件（where以下指定の文字列）を作成する。 

        //１）パラメータ.銘柄コード == nullの場合、 
        //　@nullを返却して終了する。 
        if (l_strProductCode == null)
        {
            return null;
        }
        
        //２）銘柄コードに該当する銘柄IDの検索条件文字列を作成する。 
        //　@ 検索条件文字列 = " and product_id = ? " 

        String l_strQueryString = " and product_id = ? ";
        
        //３）作成した検索条件文字列を返却する。        
        log.exiting(STR_METHOD_NAME);
        
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * (createQueryDataContainer)<BR>
     * 検索条件（where以下指定の文字列）のパラメータの文字列配列を作成する。<BR>
     * <BR>
     * １）パラメータ.銘柄コード == nullの場合、<BR>
     * 　@nullを返却して終了する。<BR>
     * <BR>
     * ２）パラメータ.銘柄コードに該当する銘柄IDを<BR>
     * 　@要素とする文字列配列を生成する。<BR>
     * <BR>
     * 　@文字列配列 = 銘柄ID(*1)<BR>
     * <BR>
     * ３）作成した文字列配列を返却する。<BR>
     * <BR>
     * (*1)外国株式プロダクトマネージャ.getFeqProduct(パラメータ.証券会社,<BR>
     *  パラメータ.銘柄コード).銘柄ID<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※指定なしの場合はnull<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 42A80C63003B
     */
    protected String[] createQueryDataContainer(
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(" +
            "WEB3GentradeInstitution l_institution, String l_strProductCode)";
        log.entering(STR_METHOD_NAME);
    
        if (l_institution == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）パラメータ.銘柄コード == nullの場合、 
        //nullを返却して終了する。 
        if (l_strProductCode == null)
        {
            log.debug("パラメータ.銘柄コード == nullの場合");
            return null;
        }

        //２）パラメータ.銘柄コードに該当する銘柄IDを 
        // 要素とする文字列配列を生成する。 
        List l_lisQueryContainer = new ArrayList();
        
        // 文字列配列 = 銘柄ID(*1) 
        //(*1)外国株式プロダクトマネージャ.getFeqProduct(
        //                           パラメータ.証券会社, パラメータ.銘柄コード).銘柄ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
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
        
       //３）作成した文字列配列を返却する。
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }
    
    /**
     * (create明細一覧)<BR>
     * 顧客の保持する資産から残高照会明細の一覧を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株残高照会）create明細一覧」<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式残高照会リクエストオブジェクト<BR>
     * @@return WEB3FeqBalanceReferenceDetailUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A80E820348
     */
    protected WEB3FeqBalanceReferenceDetailUnit[] createDetailList(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqBalanceReferenceRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createDetailList(" +
            "WEB3GentradeSubAccount l_subAccount, " +
            "WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 create検索条件文字列(String)
        //検索条件文字列を作成する。 
        //[引数] 
        //銘柄コード：　@リクエストデータ.銘柄コード
        String l_strQueryString = 
            this.createQueryString(l_request.productCode);
        
        //1.2 create検索条件データコンテナ(証券会社, String)
        //検索条件データコンテナを作成する。 
        //[引数] 
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@リクエストデータ.銘柄コード
        String[] l_strQueryDatas = 
            this.createQueryDataContainer(
                (WEB3GentradeInstitution)l_subAccount.getInstitution(), 
                l_request.productCode);
        
        //1.3 get保有資産一覧(補助口座, ProductTypeEnum, String, String[], String)
        //条件に該当する保有資産の一覧を取得する。 
        //[引数] 
        //補助口座：　@パラメータ.補助口座 
        //銘柄タイプ：　@ProductTypeEnum.外国株式 
        //検索条件文字列：　@create検索条件文字列()の戻り値 
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        //ソート条件：　@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();    
        
        List l_lisAssetRows = 
            l_feqPositionManager.getAssetList(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_strQueryString,
                l_strQueryDatas,
                null);        
        
        //1.4 (*)該当データなし(get保有資産一覧()の戻り値 == null)の場合
        if (l_lisAssetRows == null)
        {
            //nullを返却して終了する。
            return null;
        }
        log.debug("l_lisAssetRows.size() = " + l_lisAssetRows.size());
        
        //1.5 getOrderValidator( ) 注文チェックオブジェクトを取得する。        
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 

        //1.6 validate取引可能顧客(SubAccount)(注文チェック::validate取引可能顧客)
        //取引可能顧客かどうかチェックする。 
        //[引数] 
        //補助口座：　@パラメータ.補助口座
        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        boolean l_blnIsFailedResult = false;
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            l_blnIsFailedResult = true;
        }        
        //1.7 ArrayListを生成する。
        List l_lisBalanceReferenceDetail = new ArrayList();

        //1.8 時価情報を格納するHashMapを生成する。
        HashMap l_hashMap = new HashMap();
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3FeqProduct l_feqProduct = null;
        
        //1.9  (*)get保有資産一覧()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_lisAssetRows.size(); i++)
        {
            //1.9.1 getProduct()外国株式銘柄オブジェクトを取得する。 
            //arg0：　@処理対象の保有資産.銘柄ID
            AssetRow l_assetRow = (AssetRow)l_lisAssetRows.get(i); 
            log.debug("保有資産Row = " + l_assetRow);
            
            try
            {
                l_feqProduct = (WEB3FeqProduct)l_feqProductManager.getProduct(
                    l_assetRow.getProductId());
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
            
            if (l_feqProduct == null)
            {
                log.debug("Error in 外国株式銘柄を取得する");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.9.2 市場オブジェクトを取得する。
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();
            
            //1.9.3 (*)リクエストデータ.市場コード != nullの場合
            if (l_request.marketCode != null)
            {
                //1.9.3.1 (*)指定市場チェック
                //(*)指定市場チェック
                //リクエストデータ.市場コード != get市場()の戻り値.get市場コード()の場合、
                //次の要素へ処理を移行する。(continue)                
                if (!l_request.marketCode.equals(l_market.getMarketCode()))
                {
                    log.debug("リクエストデータ.市場コード != get市場()の戻り値.get市場コード()の場合");
                    continue;
                }
            }
            //1.9.4 reset市場コード(String)(取引時間管理::reset市場コード)
            //市場コードを再セットする。 
            //[引数] 
            //市場コード：　@get市場()の戻り値
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                l_market.getMarketCode());            

            //1.9.5 getQuantity( ) 数量を取得する。            
            double l_dblQuantity = l_assetRow.getQuantity();
            log.debug("getQuantity( ) 数量 = " + l_dblQuantity);
            
            //1.9.6 getLockedQuantity( ) ロック中数量を取得する。
            Asset l_asset = null;
            try
            {
                l_asset = l_feqPositionManager.getAsset(l_assetRow.getAssetId());
            }
            catch (NotFoundException l_ex)
            {
                log.debug("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            double l_dblLockedQuantity = l_asset.getLockedQuantity();
            log.debug("getLockedQuantity( ) ロック中数量 = " + l_dblLockedQuantity);
            
            //1.9.7 外国株式残高照会明細( )(外国株式残高照会明細::外国株式残高照会明細)
            //外国株式残高照会明細インスタンスを生成する。
            //(*)インスタンス生成後に以下のプロパティセットを行う。
            //売付可能フラグ　@＝　@getQuantity() > getLockedQuantity()の場合、
            //　@　@　@　@　@trueをセット。以外、falseをセット。
            //買付可能フラグ　@＝　@trueをセット。
            //※validate取引可能顧客()がチェックNGの場合、売付／買付可能フラグ
            //両方ともfalseをセットする。
            WEB3FeqBalanceReferenceDetailUnit l_balanceReferenceDetail = 
                new WEB3FeqBalanceReferenceDetailUnit();
            
            boolean l_blnSellPossFlag = false;
            if (l_dblQuantity > l_dblLockedQuantity)
            {
                log.debug("getQuantity() > getLockedQuantity()の場合");
                l_blnSellPossFlag = true;
            }
            else
            {
                l_blnSellPossFlag = false;
            }
            boolean l_blnBuyPossFlag = true;
            
            if (l_blnIsFailedResult)
            {
                log.debug("validate取引可能顧客()がチェックNGの場合");
                l_blnSellPossFlag = false;
                l_blnBuyPossFlag = false;
            }
            
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            
            //1.9.8 (*)残高照会明細.買付可能フラグ == trueの場合            
            if (l_blnBuyPossFlag)
            {
                log.debug("残高照会明細.買付可能フラグ == trueの場合");
                
                //1.9.8.1 validate取引銘柄(外国株式銘柄, 市場, boolean)
                //銘柄の売買規制をチェックする。 
                //[引数] 
                //外国株式銘柄：　@getProduct()の戻り値 
                //市場：　@get市場()の戻り値 
                //is買注文：　@true（買注文）
                try
                {
                    l_feqOrderManager.validateTradedProduct(
                        l_feqProduct, l_market, true);
                }
                catch(WEB3BaseException l_ex)
                {
                    //例外がスローされた場合、
                    //残高照会明細.買付可能フラグにfalseをセットする。
                    log.debug("error in validate取引銘柄例外がスローされた場合", l_ex);
                    l_blnBuyPossFlag = false;
                }
            }
            //1.9.9 (*)残高照会明細.売付可能フラグ == trueの場合
            if (l_blnSellPossFlag)
            {
                log.debug("残高照会明細.売付可能フラグ == trueの場合");
                
                //1.9.9.1 validate取引銘柄(外国株式銘柄, 市場, boolean)
                //銘柄の売買規制をチェックする。 
                //[引数] 
                //外国株式銘柄：　@getProduct()の戻り値 
                //市場：　@get市場()の戻り値 
                //is買注文：　@false（売注文）
                try
                {
                    l_feqOrderManager.validateTradedProduct(
                        l_feqProduct, l_market, false);
                }
                catch(WEB3BaseException l_ex)
                {
                    //例外がスローされた場合、
                    //残高照会明細.売付可能フラグにfalseをセットする。
                    l_blnSellPossFlag = false;
                }
            }
            l_balanceReferenceDetail.sellPossFlag = l_blnSellPossFlag;
            l_balanceReferenceDetail.buyPossFlag = l_blnBuyPossFlag;
            
            //1.9.10 get時価情報(補助口座, 外国株式銘柄, HashMap)
            //時価情報を取得する。 
            //[引数] 
            //補助口座：　@パラメータ.補助口座 
            //外株銘柄：　@getProduct()の戻り値 
            //時価情報格納リスト：　@生成したHashMap
            WEB3FeqProductQuote l_productQuote = 
                this.getEquityProductQuote(
                    l_subAccount, l_feqProduct, l_hashMap);

            //1.9.11 calc概算簿価単価(double, double)
            //概算簿価単価（円貨）を算出する。 
            //[引数] 
            //簿価：　@保有資産.簿価（簿価単価計算用） 
            //数量：　@保有資産.数量（簿価単価計算用）
            WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            BigDecimal l_bdBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    l_assetRow.getBookValue(), 
                    l_assetRow.getQuantityForBookValue());

            log.debug("calc概算簿価単価 = " + l_bdBookValuePrice);
            
            //1.9.12 通貨を取得する。
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
            //1.9.13 calc外貨換算(BigDecimal, double, int, String)
            //概算簿価単価（外貨）を算出する。 
            //[引数] 
            //金額（円貨）：calc概算簿価単価()の戻り値.小数点6位を四捨五入した値
            //レート：　@通貨.get売付基準為替レート() 
            //小数部桁数： 通貨.get小数部桁数()の戻り値 
            //外貨換算丸め方式： 通貨.get外貨換算丸め方式()の戻り値 
            
            BigDecimal l_bdForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP),
                    l_currency.getSellBaseRate(), 
                    l_currency.getScale(), 
                    l_currency.getChangeFCcyRoundDiv());

            log.debug("calc外貨換算 = " + l_bdForeignCCYAmount);
            
            //1.9.14 (*)外国株式残高照会明細に以下のプロパティをセットする。

            //保有資産ID      ＝　@保有資産.保有資産ID
            l_balanceReferenceDetail.assetId = l_assetRow.getAssetId() + "";
            //市場コード           ＝　@getProduct()の戻り値.get市場コード
            l_balanceReferenceDetail.marketCode = l_feqProduct.getMarketCode();
            
            //銘柄コード           ＝　@getProduct()の戻り値.銘柄コード
            l_balanceReferenceDetail.productCode = l_feqProduct.getProductCode();
            
            //現地銘柄コード     ＝　@getProduct()の戻り値.現地銘柄コード
            l_balanceReferenceDetail.localProductCode = 
                l_feqProduct.getOffshoreProductCode();
            
            //銘柄名         ＝　@getProduct()の戻り値.get表示銘柄名()の戻り値
            l_balanceReferenceDetail.productName = 
                l_feqProduct.getDisplayProductName();
            
            //特定口座区分      ＝　@保有資産.税区分 == "一般"の場合、"一般"をセット。
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"特定"の場合、"特定"をセット。
            if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
            {
                l_balanceReferenceDetail.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
            {
                l_balanceReferenceDetail.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            
            //通貨コード           ＝　@getProduct()の戻り値.通貨コード
            l_balanceReferenceDetail.currencyCode= 
                l_feqProduct.getCurrencyCode();
            
            //売付可能数量      ＝　@getQuantity()の戻り値 - getLockedQuantity()の戻り値
            double l_dblSellPossQuantity = l_dblQuantity - l_dblLockedQuantity;
            
            l_balanceReferenceDetail.sellPossQuantity = 
                WEB3StringTypeUtility.formatNumber(l_dblSellPossQuantity);
            
            //注文中数量       ＝　@getLockedQuantity()の戻り値
            l_balanceReferenceDetail.orderedQuantity = 
                WEB3StringTypeUtility.formatNumber(l_dblLockedQuantity);
                      
            //残高数量            ＝　@getQuantity()の戻り値 + 保有資産.売付不能数量
            double l_dblBalanceQuantity = 
                l_dblQuantity + l_assetRow.getQuantityCannotSell();
            
            l_balanceReferenceDetail.balanceQuantity = 
                WEB3StringTypeUtility.formatNumber(l_dblBalanceQuantity);
            
            //売付不能数量      ＝　@保有資産.売付不能数量
            l_balanceReferenceDetail.sellImpossQuantity = 
                WEB3StringTypeUtility.formatNumber(
                    l_assetRow.getQuantityCannotSell());
            
            //概算簿価単価（円貨）  ＝　@calc概算簿価単価()の戻り値
            //　@　@　@　@　@　@　@　@　@　@　@　@　@※小数点6位を四捨五入する。
            //　@　@　@　@　@　@　@　@　@　@　@　@　@※戻り値が0の場合は、nullをセット。
            log.debug("l_dblBookValuePrice = " + l_bdBookValuePrice);
            if (l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue() != 0)
            {
                l_balanceReferenceDetail.estimatedBookPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        (l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP)).doubleValue());
            }
            else
            {
                log.debug("calc概算簿価単価()の戻り値が0の場合");
                l_balanceReferenceDetail.estimatedBookPrice = null;
            }
            
            //概算簿価単価（外貨）  ＝　@calc外貨換算()の戻り値
            //　@　@　@　@　@　@　@　@　@　@　@　@　@※戻り値が0の場合は、nullをセット。
            if (l_bdForeignCCYAmount.doubleValue() != 0)
            {
                l_balanceReferenceDetail.foreignEstimatedBookPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdForeignCCYAmount.doubleValue());
            }
            else
            {
                l_balanceReferenceDetail.foreignEstimatedBookPrice = null;
            }
            
            //簿価単価入力済フラグ  ＝　@保有資産.入力簿価単価 != nullの場合、true。
            //                     以外、falseをセット。
            if (!l_assetRow.getInputBookValueIsNull())
            {
                log.debug("保有資産.入力簿価単価 != nullの場合");
                l_balanceReferenceDetail.estimatedBookPriceInputFlag = true;
            }
            else
            {
                log.debug("保有資産.入力簿価単価 == nullの場合");
                l_balanceReferenceDetail.estimatedBookPriceInputFlag = false;
            }
            
            //(*1)get時価情報()の戻り値 != nullの場合のみセット。
            //※各概算評価額、概算評価損益は、get時価情報()の戻り値 != nullの場合のみセット。
            //※各概算評価額は、概算簿価単価（円貨） != nullの場合のみセット。
            
            if (l_productQuote != null)
            {
                log.debug("get時価情報()の戻り値 != nullの場合");
                
                //時価(*1)          ＝　@get時価情報()の戻り値.時価
                l_balanceReferenceDetail.currentPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_productQuote.getCurrentPrice());
                
                log.debug("時価 = " + l_balanceReferenceDetail.currentPrice);                
                
                //前日比(*1)     ＝　@get時価情報()の戻り値.前日比
                l_balanceReferenceDetail.comparedPreviousDay = 
                    WEB3StringTypeUtility.formatNumber(
                        l_productQuote.getComparedPreviousDay());
                
                log.debug("前日比 = " + l_balanceReferenceDetail.comparedPreviousDay);
                
                //時価取得時間(*1)      ＝　@get時価情報()の戻り値.時価発表時間
                //※yyyy/MM/dd形式にフォーマットする。
                l_balanceReferenceDetail.currentPriceTime = 
                    WEB3DateUtility.formatDate(
                        l_productQuote.getCurrentPricePublicTime(), WEB3GentradeTimeDef.DATE_SPLIT_YMD);
                        
                log.debug("時価取得時間 = " + l_balanceReferenceDetail.currentPriceTime);

                log.debug("概算簿価単価（円貨） != nullの場合");
                
                //概算評価額(残高数量) ＝　@this.calc評価額(時価, 残高数量, 通貨)            
                double l_dblEstBalanceQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    l_dblBalanceQuantity, 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetBalanceQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_dblEstBalanceQuantity);
                
                log.debug("概算評価額(残高数量) = " + 
                    l_balanceReferenceDetail.estimatedAssetBalanceQuantity);
                
                //概算評価額(売付可能数量)   ＝　@this.calc評価額(時価, 売付可能数量, 通貨)
                double l_dblEstSellPossQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    l_dblSellPossQuantity, 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetSellPossQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_dblEstSellPossQuantity);                       
                
                log.debug("概算評価額(売付可能数量) = " + 
                    l_balanceReferenceDetail.estimatedAssetSellPossQuantity);
                
                //概算評価額(注文中数量)    ＝　@this.calc評価額(時価, 注文中数量, 通貨)
                double l_dblEstOrderedQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    Double.parseDouble(l_balanceReferenceDetail.orderedQuantity), 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetOrderedQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_dblEstOrderedQuantity);
                
                log.debug("概算評価額(注文中数量) = " + 
                    l_balanceReferenceDetail.estimatedAssetOrderedQuantity);
                
                //概算評価額(売付不能数量)   ＝　@this.calc評価額(時価, 売付不能数量, 通貨)
                double l_dblEstSellImpossQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    Double.parseDouble(l_balanceReferenceDetail.sellImpossQuantity), 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetSellImpossQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_dblEstSellImpossQuantity);
                
                log.debug("概算評価額(売付不能数量) = " + 
                    l_balanceReferenceDetail.estimatedAssetSellImpossQuantity);

                //保有資産.税区分=="一般"の場合 ）get時価情報()の戻り値 != null
                //      かつ　@概算簿価単価（円貨） != nullの場合
                if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
                {
                    if (l_balanceReferenceDetail.estimatedBookPrice != null)
                    {
                        //概算評価損益(残高数量)    ＝
                        //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 残高数量, 通貨)
                        double l_dblProLossBalanceQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            l_dblBalanceQuantity,
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossBalanceQuantity);

                        log.debug("概算評価損益(残高数量) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity);

                        //概算評価損益(売付可能数量)  ＝
                        //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 売付可能数量, 通貨)
                        double l_dblProLossSellPossQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            l_dblSellPossQuantity,
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossSellPossQuantity);

                        log.debug("概算評価損益(売付可能数量) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity);

                        //概算評価損益(注文中数量)   ＝
                        //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 注文中数量, 通貨)
                        double l_dblProLossOrderedQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            Double.parseDouble(l_balanceReferenceDetail.orderedQuantity),
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossOrderedQuantity);

                        log.debug("概算評価損益(注文中数量) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity);

                        //概算評価損益(売付不能数量)  ＝
                        //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 売付不能数量, 通貨)
                        double l_dblProLossSellImpossQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            Double.parseDouble(l_balanceReferenceDetail.sellImpossQuantity),
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossSellImpossQuantity);

                        log.debug("概算評価損益(売付不能数量) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity);
                    }
                }
                //保有資産.税区分=="特定"の場合 ）get時価情報()の戻り値 != null　@の場合
                else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
                {
                    //概算評価損益(残高数量)    ＝　@
                    //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 残高数量, 通貨)
                    double l_dblProLossBalanceQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        l_dblBalanceQuantity, 
                        l_currency);                
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossBalanceQuantity);
                    
                    log.debug("概算評価損益(残高数量) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity);
                                
                    //概算評価損益(売付可能数量)  ＝　@
                    //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 売付可能数量, 通貨)
                    double l_dblProLossSellPossQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        l_dblSellPossQuantity, 
                        l_currency);
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossSellPossQuantity);
                    
                    log.debug("概算評価損益(売付可能数量) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity);
                    
                    //概算評価損益(注文中数量)   ＝　@
                    //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 注文中数量, 通貨)
                    double l_dblProLossOrderedQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        Double.parseDouble(l_balanceReferenceDetail.orderedQuantity), 
                        l_currency);
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossOrderedQuantity);
                    
                    log.debug("概算評価損益(注文中数量) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity);
                    
                    //概算評価損益(売付不能数量)  ＝　@
                    //  this.calc評価損益(時価, calc概算簿価単価()の戻り値, 売付不能数量, 通貨)
                    double l_dblProLossSellImpossQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        Double.parseDouble(l_balanceReferenceDetail.sellImpossQuantity), 
                        l_currency);
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossSellImpossQuantity);
                    
                    log.debug("概算評価損益(売付不能数量) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity);
                }
            }
            //1.9.15 ArrayListにプロパティセットしたインスタンスを追加する。 
            //[引数] 
            //arg0：　@プロパティセットした外国株式残高照会明細
            l_lisBalanceReferenceDetail.add(l_balanceReferenceDetail);
        }
        
        //1.10 toArray()
        //外国株式残高照会明細の配列を生成する。
        WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits = 
            new WEB3FeqBalanceReferenceDetailUnit[l_lisBalanceReferenceDetail.size()];
        
        l_lisBalanceReferenceDetail.toArray(l_balanceReferenceDetailUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_balanceReferenceDetailUnits;
    }
    
    /**
     * (get時価情報)<BR>
     * 時価情報を取得する。<BR>
     * <BR>
     * １）時価情報検索<BR>
     * 　@パラメータ.時価情報格納リスト.get()メソッドをコールする。<BR>
     * <BR>
     * 　@[get()にセットするパラメータ]<BR>
     * 　@　@key：　@パラメータ.外株銘柄.銘柄コード<BR>
     * 　@　@　@　@　@+ パラメータ.外株銘柄.get市場コード()<BR>
     * <BR>
     * ２）時価情報返却<BR>
     * 　@１）の戻り値 != nullの場合、取得した時価情報を返却する。<BR>
     * <BR>
     * ３）時価情報取得<BR>
     * 　@１）の戻り値 == nullの場合、以下の手順で時価情報を取得する。<BR>
     * 　@３－１）パラメータ.外株銘柄.get取引銘柄()をコールする。<BR>
     * <BR>
     * 　@３－２）外国株式プロダクトマネージャ．get時価情報をコールする。<BR>
     * 　@　@　@　@　@　@[ 引数 ]<BR>
     * 　@　@　@　@　@　@　@取引銘柄<BR>
     * 　@　@　@　@　@　@　@リアル区分：引値<BR>
     * <BR>
     * 　@３－３）パラメータ.時価情報格納リスト.put()メソッドをコールし、<BR>
     * 　@　@取得した時価情報を格納する。<BR>
     * <BR>
     * 　@　@[put()にセットするパラメータ]<BR>
     * 　@　@　@key：　@パラメータ.外株銘柄.銘柄コード<BR>
     * 　@　@　@　@　@　@+ パラメータ.外株銘柄.get市場コード()<BR>
     * 　@　@　@value：　@取得した時価情報<BR>
     * <BR>
     * 　@３－４）取得した時価情報を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_feqProduct - (外株銘柄)<BR>
     * 外国株式銘柄オブジェクト<BR>
     * @@param l_hmProductQuote - (時価情報格納リスト)<BR>
     * 以前の処理で取得済みの時価情報を格納するリスト
     * @@return WEB3FeqProductQuote
     * @@throws WEB3BaseException
     * @@roseuid 42A8313B026E
     */
    protected WEB3FeqProductQuote getEquityProductQuote(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqProduct l_feqProduct, 
        HashMap l_hmProductQuote) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEquityProductQuote(" +
            "WEB3GentradeSubAccount l_subAccount, " +
            "WEB3FeqProduct l_feqProduct, HashMap l_hmProductQuote)";

        log.entering(STR_METHOD_NAME);
    
        if (l_subAccount == null || l_feqProduct == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //時価情報を取得する。 
        //１）時価情報検索 
        //パラメータ.時価情報格納リスト.get()メソッドをコールする。 
        //[get()にセットするパラメータ] 
        //　@key：　@パラメータ.外株銘柄.銘柄コード 
        //　@　@　@　@+ パラメータ.外株銘柄.get市場コード() 
        WEB3FeqProductQuote l_feqProductQuote = (WEB3FeqProductQuote)
            l_hmProductQuote.get(l_feqProduct.getProductCode() + 
                l_feqProduct.getMarketCode());
        
        //２）時価情報返却 
        //１）の戻り値 != nullの場合、取得した時価情報を返却する。 
        if (l_feqProductQuote != null)
        {
            log.debug("時価情報の戻り値 != nullの場合");
            log.exiting(STR_METHOD_NAME);
            return l_feqProductQuote;
        }        
        //３）時価情報取得 
        //１）の戻り値 == nullの場合、以下の手順で時価情報を取得する。 
        else
        {
            log.debug("時価情報の戻り値 == nullの場合");
            //３－１）パラメータ.外株銘柄.get取引銘柄()をコールする。 
            WEB3FeqTradedProduct l_feqTradedProduct = 
                l_feqProduct.getFeqTradedProduct();

            //３－２）外国株式プロダクトマネージャ．get時価情報をコールする。
            //　@　@　@　@　@　@[ 引数 ]
            //　@　@　@　@　@　@　@取引銘柄
            //　@　@　@　@　@　@　@リアル区分：引値
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqProductManager l_productMgr =
                (WEB3FeqProductManager)l_tradingModule.getProductManager();
            l_feqProductQuote = l_productMgr.getCurrentPriceUnit(
                l_feqTradedProduct,
                RealType.CLOSING_PRICE);

            //３－３）パラメータ.時価情報格納リスト.put()メソッドをコールし、 
            //　@取得した時価情報を格納する。     
            //　@[put()にセットするパラメータ] 
            //　@　@key：　@パラメータ.外株銘柄.銘柄コード 
            //　@　@　@　@　@+ パラメータ.外株銘柄.get市場コード() 
            //　@　@value：　@取得した時価情報 
            l_hmProductQuote.put(
                l_feqProduct.getProductCode() + l_feqProduct.getMarketCode(), 
                l_feqProductQuote);     
            
            //　@３－４）取得した時価情報を返却する。 
            log.exiting(STR_METHOD_NAME);
            return l_feqProductQuote;
        }       
    }
    
    /**
     * (calc評価額)<BR>
     * 評価額（円貨）を算出し、返却する。<BR>
     * ※手数料は含まない。<BR>
     * <BR>
     * １）　@評価額（外貨）の算出<BR>
     * 　@以下の計算式にて算出する。<BR>
     * <BR>
     * 　@評価額（外貨） = パラメータ.評価単価 * パラメータ.数量<BR>
     * <BR>
     * ２）　@円貨換算<BR>
     * 　@外国株式計算サービス.calc円貨換算()にて円貨換算した<BR>
     * 　@評価額を返却する。<BR>
     * <BR>
     * 　@[calc円貨換算()に指定する引数]<BR>
     * 　@　@金額（外貨）：　@１）の計算結果<BR>
     * 　@　@レート：　@パラメータ.通貨.get売付約定為替レート<BR>
     * 　@　@円貨換算丸め方式：　@パラメータ.通貨.get円貨換算丸め方式()<BR>
     * @@param l_dblPrice - (評価単価)<BR>
     * 評価単価
     * @@param l_dblQuantity - (数量)<BR>
     * 数量
     * @@param l_currency - (通貨)<BR>
     * （共通）通貨オブジェクト
     * @@return double
     * @@roseuid 42A8430A03E5
     */
    protected double calcEstimatedValue(
        double l_dblPrice, 
        double l_dblQuantity, 
        WEB3GentradeCurrency l_currency) 
    {
        final String STR_METHOD_NAME = "calcEstimatedValue(" +
            "double l_dblPrice, double l_dblQuantity, " +
            "WEB3GentradeCurrency l_currency)" ;
        log.entering(STR_METHOD_NAME);
        
        //１）　@評価額（外貨）の算出 
        //以下の計算式にて算出する。 
        //評価額（外貨） = パラメータ.評価単価 * パラメータ.数量
        double l_dblEstimatedValueFc = l_dblPrice * l_dblQuantity;
        
        //２）　@円貨換算 
        //外国株式計算サービス.calc円貨換算()にて円貨換算した評価額を返却する。 
        //[calc円貨換算()に指定する引数] 
        //金額（外貨）：　@１）の計算結果 
        //レート：　@パラメータ.通貨.get売付約定為替レート
        //円貨換算丸め方式：　@パラメータ.通貨.get円貨換算丸め方式() 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        double l_dblJpyAmout = 
            l_feqBizLogicProvider.calcJPYAmount(
                l_dblEstimatedValueFc, 
                l_currency.getSellExecRate(),
                l_currency.getChangeJpyRoundDiv());
        
        log.debug("評価額 = " + l_dblJpyAmout);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblJpyAmout;
    }
    
    /**
     * (calc評価損益)<BR>
     * 評価損益（円貨）を算出し、返却する。<BR>
     * ※手数料は含まない。<BR>
     * <BR>
     * １）　@評価額（評価単価・円貨）の算出<BR>
     * <BR>
     * 　@this.calc評価額をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@パラメータ．評価単価<BR>
     * 　@　@　@パラメータ．数量<BR>
     * 　@　@　@パラメータ．通貨<BR>
     * <BR>
     * ２）　@評価額（概算簿価単価・円貨）の算出<BR>
     * 　@　@以下の計算をおこなう。<BR>
     * <BR>
     * 　@　@　@パラメータ．概算簿価単価　@*　@パラメータ．数量　@（円未満切捨て）<BR>
     * <BR>
     * ３）　@評価損益の計算<BR>
     * 　@以下の計算式にて計算した結果を返却する。<BR>
     * <BR>
     * 　@１）の戻り値　@－　@２）の計算結果<BR>
     * <BR>
     * @@param l_dblPrice - (評価単価)<BR>
     * 評価単価
     * @@param l_bdBookValue - (概算簿価単価)<BR>
     * calc概算簿価単価()の戻り値
     * 
     * @@param l_dblQuantity - <数量><BR>
     * 数量
     * @@param l_currency - (通貨)<BR>
     * 通貨オブジェクト
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 42A843430339
     */
    protected double calcProfitLoss(
        double l_dblPrice, BigDecimal l_bdBookValue, 
        double l_dblQuantity, WEB3GentradeCurrency l_currency)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcProfitLoss(double, " +
            "BigDecimal, double, WEB3GentradeCurrency";
        log.entering(STR_METHOD_NAME);

        //評価額（評価単価・円貨）の算出
        //　@this.calc評価額をコールする。
        //　@　@[引数]
        //　@　@　@パラメータ．評価単価
        //　@　@　@パラメータ．数量
        //　@　@　@パラメータ．通貨
        double l_dblEstimatedValue = this.calcEstimatedValue(
            l_dblPrice,
            l_dblQuantity,
            l_currency);

        //評価額（概算簿価単価・円貨）の算出
        //パラメータ．概算簿価単価　@*　@パラメータ．数量　@（円未満切捨て）
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
        BigDecimal l_bdEstimatedValue =
            (l_bdBookValue.multiply(l_bdQuantity)).setScale(0, BigDecimal.ROUND_DOWN);

        //評価損益の計算
        //１）の戻り値　@－　@２）の計算結果
        BigDecimal l_bdResultValue =
            (new BigDecimal(String.valueOf(l_dblEstimatedValue))).subtract(
                l_bdEstimatedValue);

        log.exiting(STR_METHOD_NAME);
        return l_bdResultValue.doubleValue();
    }
    
    /**
     * (sort残高照会明細)<BR>
     * 指定されたソートキー、昇降順にもどついて残高照会明細のソートを行う。<BR>
     * <BR>
     * １）パラメータ.残高照会明細 == nullの場合、<BR>
     * 　@処理を終了する。<BR>
     * <BR>
     * ２）ArrayListを生成する。<BR>
     * <BR>
     * ３）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@３－１）ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、<BR>
     * 　@　@　@ArrayListに追加する。<BR>
     * <BR>
     * 　@　@　@①@外国株式残高照会Comparatorを生成する。<BR>
     * <BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@　@　@　@orderBy： ソートキー.昇順／降順<BR>
     * 　@　@　@　@　@比較項目：　@ソートキー.キー項目<BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。<BR>
     * <BR>
     * ４）WEB3ArraysUtility.sort()メソッドをコールする。<BR>
     * <BR>
     * 　@[sort()にセットするパラメータ]<BR>
     * 　@　@obj：　@パラメータ.残高照会明細<BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値<BR>
     * @@param l_balanceReferenceDetailUnits - (残高照会明細)<BR>
     * 外国株式残高照会明細の配列
     * @@param l_sortKeys - (ソートキー)<BR>
     * 外国株式ソートキーの配列
     * @@roseuid 42A844210368
     */
    protected void sortBalanceReferenceDetailUnit(
        WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits, 
        WEB3ForeignSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit(" +
            "WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits, " +
            "WEB3ForeignSortKey[] l_sortKeys)" ;
        log.entering(STR_METHOD_NAME);
        
        //１）パラメータ.残高照会明細 == nullの場合、 
        //処理を終了する。 
        if (l_balanceReferenceDetailUnits == null)
        {
            log.debug("パラメータ.残高照会明細 == nullの場合");
             return;
        }

        //２）ArrayListを生成する。 
        List l_lisComparator = new ArrayList();
        
        //３）パラメータ.ソートキーの要素数分以下の処理を繰り返す。 
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //　@３－１）ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、 
            //　@　@　@ArrayListに追加する。             
            //　@　@　@(1)外国株式残高照会Comparatorを生成する。             
            //　@　@　@　@[コンストラクタにセットするパラメータ] 
            //　@　@　@　@　@orderBy： ソートキー.昇順／降順 
            //　@　@　@　@　@比較項目：　@ソートキー.キー項目 
            WEB3FeqBalanceReferenceComparator l_banlanceReferenceComparator = 
                new WEB3FeqBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc, 
                    l_sortKeys[i].keyItem);
            //　@　@　@(2)ArrayListに生成したComparatorを追加する。 
            l_lisComparator.add(l_banlanceReferenceComparator);
        }
        
        //４）WEB3ArraysUtility.sort()メソッドをコールする。 
        //[sort()にセットするパラメータ] 
        //　@obj：　@パラメータ.残高照会明細 
        //　@com：　@生成したArrayList.toArray()の戻り値 
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        
        WEB3ArraysUtility.sort(
            l_balanceReferenceDetailUnits, 
            l_comparators);        
        
        log.entering(STR_METHOD_NAME);
    }
}
@
