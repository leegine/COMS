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
filename	WEB3BondBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会サービスImpl(WEB3BondBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 趙林鵬 (中訊) 新規作成
Revesion History : 2006/10/12 柴雙紅 (中訊)  WEBⅢ開発標準の見直しの対応（new BigDecimal部分）
Revesion History : 2007/03/09 齊珂   (中訊) 仕様変更・モデル160
Revesion History : 2007/07/17 武波   (中訊) 仕様変更・モデル207
Revesion History : 2007/07/17 謝旋 (中訊) 仕様変更・モデル208
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedAssetCalcResult;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondPositionManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondBalanceReferenceDetailUnitDef;
import webbroker3.bd.define.WEB3BondBalanceReferenceTypeDef;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.define.WEB3BondSellPossDivDef;
import webbroker3.bd.message.WEB3BondBalanceReferenceComparator;
import webbroker3.bd.message.WEB3BondBalanceReferenceDetailUnit;
import webbroker3.bd.message.WEB3BondBalanceReferenceRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.bd.message.WEB3BondCurrencyInfo;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3BondBalanceReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券残高照会サービスImpl)<BR>
 * 債券残高照会サービス実装クラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceServiceImpl 
    extends WEB3BondClientRequestService    
    implements WEB3BondBalanceReferenceService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceServiceImpl.class);  
    /**
     * @@roseuid 424CC4BE031C
     */
    public WEB3BondBalanceReferenceServiceImpl()
    {

    }
    
    /**
     * 債券残高照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○債券残高照会残高合計リクエストの場合<BR>
     * 　@this.get残高合計()をコールする。<BR>
     * <BR>
     * ○債券残高照会リクエストの場合<BR>
     * 　@this.get残高照会()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421964C401AD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
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

        if (l_request instanceof WEB3BondBalanceReferenceTotalRequest)
        {
            //債券残高照会残高合計リクエスト
            l_response = this.getBalanceTotal((WEB3BondBalanceReferenceTotalRequest)l_request);
        }
        else if (l_request instanceof WEB3BondBalanceReferenceRequest)
        {
            //債券残高照会リクエスト
            l_response = this.getBalanceReference((WEB3BondBalanceReferenceRequest)l_request);
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
     * (get残高照会)<BR>
     * 債券残高照会処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(債券残高照会サービス)get残高照会」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 債券残高照会リクエストオブジェクト<BR>
     * @@return WEB3BondBalanceReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 421964E3019C
     */  
    protected WEB3BondBalanceReferenceResponse getBalanceReference(
        WEB3BondBalanceReferenceRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3BondBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1validate( )
        l_request.validate();

        if (l_request.referenceType == null)
        {
            l_request.referenceType = WEB3BondBalanceReferenceTypeDef.ALL_PRODUCT;
        }

        //1.2validate注文受付可能()
        WEB3BondTradingTimeManagement.validateOrderAccept();
        
        //1.3get補助口座( )
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.4create残高照会明細一覧(補助口座, String)
        String l_strReferenceDiv = l_request.referenceType;
        
        WEB3BondBalanceReferenceDetailUnit[] l_referenceDetailUnit =
            this.createBalanceReferenceDetailList(l_subAccount, l_strReferenceDiv);
        
        //1.5(*)create残高照会明細一覧()の戻り値 == null
        if (l_referenceDetailUnit == null)
        {
            //1.5.1レスポンスデータを生成する。
            WEB3BondBalanceReferenceResponse l_response = 
                (WEB3BondBalanceReferenceResponse)l_request.createResponse();
            
            //1.5.2(*)プロパティセット
            //(*)レスポンスデータに以下のプロパティをセットする。
            //レスポンスデータ.通貨情報 = null
            //レスポンスデータ.残高照会明細 = null
            //レスポンスデータ.表示ページ番号 = 0
            //レスポンスデータ.総ページ数 = 0
            //レスポンスデータ.総レコード数 = 0
            l_response.currencyList = null;    
            l_response.balanceReference = null;
            l_response.pageIndex = "0";
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        // 1.6 get（共通）通貨(String)
        WEB3GentradeCurrency[] l_currency = 
            WEB3GentradeCurrency.getGentradeCurrency(
                l_subAccount.getInstitution().getInstitutionCode());
        
        List l_lisCurrencyInfos = new ArrayList();
        //1.7(*)get（共通）通貨()の要素分、LOOP処理
        for (int i = 0; i < l_currency.length; i++)
        {
 
            //1.7.1通貨情報( )
            WEB3BondCurrencyInfo l_currencyInfo = new WEB3BondCurrencyInfo();
            
            //1.72（*2）プロパティセット
            //通貨情報.通貨コード　@=　@通貨オブジェクト.通貨コード
            //通貨情報.為替レート　@=　@通貨オブジェクト.今回売付為替レート
            l_currencyInfo.currencyCode = l_currency[i].getCurrencyCode();
            l_currencyInfo.fxRate = 
                WEB3StringTypeUtility.formatNumber(l_currency[i].getSellBaseRate());
          
            l_lisCurrencyInfos.add(l_currencyInfo); 

        }
        
        //1.8sort残高照会明細(債券残高照会明細[], 債券ソートキー[])
        //残高照会明細：　@create残高照会明細一覧()の戻り値 
        //ソートキー：　@リクエストデータ.ソートキー
        this.sortBalanceReferenceDetail(
            l_referenceDetailUnit,
            l_request.sortKeys);
        
        //1.9.WEB3PageIndexInfo(arg0 : 論理ビュー::java::lang::Object[], arg1 : int, arg2 : int)
        //arg0：　@ソートされた残高照会明細 
        //arg1：　@リクエストデータ.要求ページ番号 
        //arg2：　@リクエストデータ.ページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_referenceDetailUnit,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
        //1.10createResponse( )
        WEB3BondBalanceReferenceResponse l_response = (
            WEB3BondBalanceReferenceResponse)l_request.createResponse();
        
        //1.11
        //レスポンスデータ.通貨情報       ＝　@作成した通貨情報の配列
     
        WEB3BondCurrencyInfo[] l_currencyInfos = null;
        if (!l_lisCurrencyInfos.isEmpty())
        {
            l_currencyInfos = new WEB3BondCurrencyInfo[l_lisCurrencyInfos.size()];
            l_lisCurrencyInfos.toArray(l_currencyInfos);
        }
        l_response.currencyList = l_currencyInfos;

        //レスポンスデータ.表示ページ番号    ＝　@WEB3PageIndexInfoオブジェクト.getPageIndex()
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        //レスポンスデータ.総ページ数      ＝　@WEB3PageIndexInfoオブジェクト.getTotalPages()
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        //レスポンスデータ.総レコード数     ＝　@WEB3PageIndexInfoオブジェクト.getTatalRecords()
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        //レスポンスデータ.残高照会明細     ＝　@
        //WEB3PageIndexInfoオブジェクト.getArrayReturned(債券残高照会明細.class)
        Object[] l_objs = l_pageIndexInfo.getArrayReturned(
            WEB3BondBalanceReferenceDetailUnit.class);
        int l_arrayLen = 0;
        if (l_objs != null)
        {
            l_arrayLen = l_objs.length;
        }
        WEB3BondBalanceReferenceDetailUnit[] l_detailUnits = 
            new WEB3BondBalanceReferenceDetailUnit[l_arrayLen];
        for (int i = 0; i < l_arrayLen; i++)
        {
            l_detailUnits[i] = (WEB3BondBalanceReferenceDetailUnit)l_objs[i];
        }
        
        l_response.balanceReference = l_detailUnits;
        log.exiting(STR_METHOD_NAME);
      
        return l_response;
 
    }
    
    
    /**
     * (create残高照会明細一覧)<BR>
     * 債券残高照会明細の一覧を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(債券残高照会サービス)create残高照会明細一覧」参照<BR>
     * @@param l_subAccount - 補助口座オブジェクト
     * @@param l_strReferenceDiv - 照会区分
     * @@return WEB3BondBalanceReferenceDetailUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4219769201E4
     */
    protected WEB3BondBalanceReferenceDetailUnit[] createBalanceReferenceDetailList(
        WEB3GentradeSubAccount l_subAccount, String l_strReferenceDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailList(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //1.1get債券保有資産一覧(補助口座, String, String[], String)
        //補助口座：　@パラメータ.補助口座 
        //検索条件文字列：　@null 
        //検索条件データコンテナ：　@null 
        //ソート条件：　@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondPositionManager l_bondPositionManager = (
            WEB3BondPositionManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getPositionManager();
        
        List l_lisAsserts = l_bondPositionManager.getAssets(
            l_subAccount,
            null,
            null,
            null);
        
        //1.2(*)get債券保有資産一覧()の戻り値 == nullの場合、nullを返却して終了する。
        if (l_lisAsserts.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //1.3ArrayList( )
        ArrayList l_array = new ArrayList();
        
        //1.4(*)get債券保有資産一覧()の戻り値の要素(=保有資産)数分Loop処理
        WEB3BondProductManager l_BondProductManger = (
            WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        
        for (int i = 0; i < l_lisAsserts.size(); i++)
        {
            AssetRow l_assetRow = (AssetRow) l_lisAsserts.get(i);

            try
            {
                Asset l_asset = 
                    l_bondPositionManager.getAsset(l_assetRow.getAssetId());
                //1.4.1get債券銘柄(long)(long)
                //銘柄ID：　@保有資産.銘柄ID
                WEB3BondProduct l_BondProduct = (
                    WEB3BondProduct)l_BondProductManger.getProduct(
                        l_assetRow.getProductId());

                //is表示対象明細(String, 債券銘柄)
                //照会区分　@：　@リクエストデータ.照会区分
                //債券銘柄　@：　@get債券銘柄（）の戻り値
                boolean l_blnIsIndicationObjectDetails =
                    this.isIndicationObjectDetails(l_strReferenceDiv, l_BondProduct);

                //is表示対象明細(String, 債券銘柄)==false、以降の処理をskipし、
                //次の保有資産オブジェクトの処理を行なう。
                if (!l_blnIsIndicationObjectDetails)
                {
                    continue;
                }

                //1.4.2calc概算評価額(補助口座, double, 債券銘柄, boolean, boolean)
                //補助口座：　@引数の補助口座 
                //数量：　@保有資産.数量 － 保有資産.getロック中数量  
                //債券銘柄：　@取得した債券銘柄オブジェクト 
                //is買付：　@false 
                //is約定計算：　@false
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
                WEB3BondBizLogicProvider l_bondBizLogicProvider =
                    (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
                BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_assetRow.getQuantity()));
                BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_asset.getLockedQuantity()));
                BigDecimal l_bdSellAbleFaceAmount = l_bdQuantity.subtract(l_bdLockedQuantity);
                
                WEB3BondEstimatedAssetCalcResult l_calcResult = 
                    l_bondBizLogicProvider.calcEstimatedAsset(
                        l_subAccount,
                        l_bdSellAbleFaceAmount.doubleValue(),
                        l_BondProduct,
                        false,
                        false);
                
                //1.4.3validate顧客取扱可能銘柄(債券銘柄, String)
                //顧客取扱可能チェックを行い、債券残高照会明細.売却可能区分にセットする値を判定する。 
                //債券銘柄：　@get債券銘柄（）の戻り値 
                //取引区分：　@売却
                WEB3BondOrderManager l_bondOrderManager = 
                    (WEB3BondOrderManager) l_finApp.getTradingModule(
                        ProductTypeEnum.BOND).getOrderManager();
                
                boolean l_validateAccountHandlingPossibleProduct = true;
                
                try
                {
                    l_bondOrderManager.validateAccountHandlingPossibleProduct(
                        l_BondProduct,
                        WEB3BondDealDivDef.SELL);
                }
                catch(WEB3BaseException l_ex)
                {
                    l_validateAccountHandlingPossibleProduct = false;
                }
                
                //1.4.4債券残高照会明細( )
                WEB3BondBalanceReferenceDetailUnit l_referenceDetailUnit = 
                    new WEB3BondBalanceReferenceDetailUnit();
                
                BondProductRow l_bondProductRow = 
                    (BondProductRow)l_BondProduct.getDataSourceObject();
                //1.4.5(*)プロパティセット
                //(*)債券残高照会明細インスタンスにプロパティをセットする。
                //債券残高照会明細.ID     ＝　@保有資産.保有資産ID
                l_referenceDetailUnit.id = l_assetRow.getAssetId() + "";
                
                //債券残高照会明細.債券タイプ      ＝　@get債券銘柄()の戻り値.債券タイプ
                l_referenceDetailUnit.bondKind = l_BondProduct.getBondType().intValue() + "";
                
                //債券残高照会明細.種別     ＝　@get債券銘柄()の戻り値.種別コード
                l_referenceDetailUnit.bondCategCode = l_BondProduct.getBondCategCode();
                
                //債券残高照会明細.銘柄コード      ＝　@get債券銘柄()の戻り値.銘柄コード(SONAR)
                l_referenceDetailUnit.productCode = l_BondProduct.getHostProductCode();
                
                //債券残高照会明細.回号コード      ＝　@get債券銘柄()の戻り値.回号コード(SONAR)
                l_referenceDetailUnit.productIssueCode = l_BondProduct.getHostProductIssueCode();
                
                //債券残高照会明細.銘柄名        ＝　@get債券銘柄()の戻り値.get銘柄名()
                l_referenceDetailUnit.productName = l_BondProduct.getProductName();
                
                //(*1)債券銘柄.is転換社債 == trueの場合、セット。falseの場合はNULLをセット。
                //債券残高照会明細.口座区分　@  ＝　@保有資産.税区分 == "一般"の場合、"一般"をセット。    
                //"特定" or "特定口座かつ源泉徴収"の場合、"特定"をセット。(*1)
                if (l_BondProduct.isExperienceDivWt())
                {
                    if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
                    {
                        l_referenceDetailUnit.taxType = WEB3TaxTypeDef.NORMAL;
                    }
                    else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()) || 
                        TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetRow.getTaxType()))
                    {
                        l_referenceDetailUnit.taxType = WEB3TaxTypeDef.SPECIAL;
                    }
                }
                else
                {
                    l_referenceDetailUnit.taxType =  null;
                }
                
                //債券残高照会明細.売却可能数量 ＝　@保有資産.get数量－保有資産.getロック中数量                
                l_referenceDetailUnit.sellAbleQty = WEB3StringTypeUtility.formatNumber(
                    l_bdSellAbleFaceAmount.doubleValue());

                //債券残高照会明細.売却不能数量 ＝　@保有資産.売付不能数量
                l_referenceDetailUnit.sellDisableQty = 
                    WEB3StringTypeUtility.formatNumber(l_assetRow.getQuantityCannotSell());
                
                //債券残高照会明細.通貨     ＝　@get債券銘柄()の戻り値.通貨コード
                l_referenceDetailUnit.currencyCode = l_BondProduct.getCurrencyCode();
                
                //債券残高照会明細.売却（評価）単価   ＝　@get債券銘柄()の戻り値.売却単価
                if(!l_bondProductRow.getSellPriceIsNull())
                {
                    l_referenceDetailUnit.sellEvaluationPrice = 
                        WEB3StringTypeUtility.formatNumber(l_BondProduct.getSellPrice());
                }
                
                //債券残高照会明細.概算評価額（円貨）  ＝　@債券概算評価額計算結果.概算評価額（円貨）をセット。
                l_referenceDetailUnit.yenEstimatedAsset = 
                    WEB3StringTypeUtility.formatNumber(l_calcResult.getEstimatedAsset().doubleValue());
                //l_calcResult.getEstimatedAsset().toString()
                //債券残高照会明細.概算評価額（外貨）  ＝　@債券概算評価額計算結果.概算評価額（外貨）をセット。
                if (l_calcResult.getForeignEstimatedAsset() != null)
                {
                    l_referenceDetailUnit.foreignEstimatedAsset = 
                        WEB3StringTypeUtility.formatNumber(
                            l_calcResult.getForeignEstimatedAsset().doubleValue());
                }
                //債券残高照会明細.発行日        ＝　@get債券銘柄()の戻り値.発行日
                l_referenceDetailUnit.issueDate = l_BondProduct.getIssueDate();
                
                //債券残高照会明細.発行価格       ＝　@get債券銘柄()の戻り値.発行価格
                l_referenceDetailUnit.issuePrice = 
                    WEB3StringTypeUtility.formatNumber(l_BondProduct.getIssuePrice());
                
                //債券残高照会明細.償還日        ＝　@get債券銘柄()の戻り値.償還日
                l_referenceDetailUnit.maturityDate = l_BondProduct.getMaturityDate();
                
                //債券残高照会明細.年間利払い回数       ＝　@get債券銘柄()の戻り値.年間利払回数
                l_referenceDetailUnit.yearlyInterestPayments = 
                    l_BondProduct.getYearlyInterestPayments() + "";
                
                //債券残高照会明細.利払日１       ＝　@get債券銘柄()の戻り値.利払日１
                l_referenceDetailUnit.interestPaymentDay1 = l_BondProduct.getInterestPaymentDay1();
                
                //債券残高照会明細.利払日２       ＝　@get債券銘柄()の戻り値.利払日２
                l_referenceDetailUnit.interestPaymentDay2 = l_BondProduct.getInterestPaymentDay2();
                
                //債券残高照会明細.年利率        ＝　@get債券銘柄()の戻り値.利率
                l_referenceDetailUnit.coupon = 
                    WEB3StringTypeUtility.formatNumber(l_BondProduct.getCoupon());
                
                //債券残高照会明細.売却可能区分 ＝　@validate顧客取扱可能銘柄()が例外をthrowしない &&
                //                                                債券残高照会明細.売却可能数量 > 0 の場合は”取扱可”をセット。
                //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@      それ以外は”取扱不可”をセット。               
                if((l_validateAccountHandlingPossibleProduct) && (Double.parseDouble(l_referenceDetailUnit.sellAbleQty)) > 0)
                {
                    l_referenceDetailUnit.sellPossDiv = WEB3BondSellPossDivDef.SELL_POSS;
                }
                else
                {
                    l_referenceDetailUnit.sellPossDiv = WEB3BondSellPossDivDef.SELL_POSS_NO;
                }
                
                //1.4.6add(arg0 : Object)
                //ArrayListに債券残高照会明細を追加する。 
                //arg0：　@プロパティセットした債券残高照会明細
                l_array.add(l_referenceDetailUnit);

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
        //toArray( )
        //債券残高照会明細の配列を作成する。
        WEB3BondBalanceReferenceDetailUnit[] l_bondBalanceReferenceUnits = 
            new WEB3BondBalanceReferenceDetailUnit[l_array.size()];
        
        l_array.toArray(l_bondBalanceReferenceUnits);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_bondBalanceReferenceUnits;
    }
   
    /**
     * (sort残高照会明細)<BR>
     * 指定されたソートキー、昇降順にもどついて残高照会明細のソートを行う。 <BR>
     * <BR>
     * １）ArrayListを生成する。  <BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * 　@２－１）ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、 <BR>
     * 　@　@　@ArrayListに追加する。  <BR>
     * <BR>
     * 　@　@　@①@ソートキー.キー項目が、<BR>
     * 　@　@　@　@["銘柄名"の場合]<BR>
     * 　@　@　@　@　@以下の項目の数分だけ債券残高照会Comparatorを生成する。<BR>
     * 　@　@　@　@　@※orderByは一律、ソートキー.昇順／降順をセット。<BR>
     * 　@　@　@　@　@　@・比較項目：債券残高照会明細.種別<BR>
     * 　@　@　@　@　@　@・比較項目：債券残高照会明細.銘柄コード<BR>
     * 　@　@　@　@　@　@・比較項目：債券残高照会明細.回号コード<BR>
     * <BR>
     * 　@　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@　@以下の引数にて債券残高照会Comparatorを生成する。<BR>
     * <BR>
     * 　@　@　@　@　@[コンストラクタにセットするパラメータ]  <BR>
     * 　@　@　@　@　@　@orderBy： ソートキー.昇順／降順 <BR>
     * 　@　@　@　@　@　@比較項目：　@ソートキー.キー項目 <BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。 <BR>
     * <BR>
     * ３）WEB3ArraysUtility.sort()メソッドをコールする。  <BR>
     * <BR>
     * 　@[sort()にセットするパラメータ]  <BR>
     * 　@　@obj：　@パラメータ.残高照会明細 <BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値 <BR>
     * @@param l_bondBalanceReferenceDetailUnits - (残高照会明細)<BR>
     * 債券残高照会明細オブジェクトの配列<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * 債券ソートキーの配列<BR>
     * @@roseuid 421979C60148
     */
    protected void sortBalanceReferenceDetail(
        WEB3BondBalanceReferenceDetailUnit[] l_bondBalanceReferenceDetailUnits,
        WEB3BondSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "sortBalanceReferenceDetail(WEB3BondBalanceReferenceDetailUnit[] WEB3BondSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        // １）ArrayListを生成する。   
        ArrayList l_lisComparators = new ArrayList();
        
        // ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。 
        // 　@２－１）ソートキー.キー項目の値に対応する比較項目のComparatorを生成し、 
        // 　@　@　@ArrayListに追加する。  
        int l_intSortKeysLen = 0; 
        if (l_sortKeys != null)
        {
            l_intSortKeysLen = l_sortKeys.length;
        }
        for (int i = 0; i < l_intSortKeysLen; i++)
        {
            //①@ソートキー.キー項目が、
            // ["銘柄名"の場合]
            // 　@以下の項目の数分だけ債券残高照会Comparatorを生成する。
            // 　@※orderByは一律、ソートキー.昇順／降順をセット。
            // 　@　@・比較項目：債券残高照会明細.種別
            // 　@　@・比較項目：債券残高照会明細.銘柄コード
            // 　@　@・比較項目：債券残高照会明細.回号コード
            if (WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_NAME.equals(l_sortKeys[i].keyItem))
            {              
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc, 
                    WEB3BondBalanceReferenceDetailUnitDef.BOND_CATEG_CODE));
                             
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc,
                    WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_CODE));
                
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc,
                    WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_ISSUE_CODE));
            }
            // [上記以外の場合]
            // 　@以下の引数にて債券残高照会Comparatorを生成する。 
            // 　@[コンストラクタにセットするパラメータ]  
            // 　@　@orderBy： ソートキー.昇順／降順 
            // 　@　@比較項目：　@ソートキー.キー項目 
            else
            {
                l_lisComparators.add(new WEB3BondBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc,
                    l_sortKeys[i].keyItem));
            }

        }
        //②ArrayListに生成したComparatorを追加する。 
        Comparator[] l_comparators = new Comparator[l_lisComparators.size()];
        l_lisComparators.toArray(l_comparators);
        
        // ３）WEB3ArraysUtility.sort()メソッドをコールする。  
        // [sort()にセットするパラメータ]  
        // obj：　@パラメータ.残高照会明細 
        // com：　@生成したArrayList.toArray()の戻り値 
        WEB3ArraysUtility.sort(l_bondBalanceReferenceDetailUnits,l_comparators);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get残高合計)<BR>
     * 債券残高合計処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（債券残高照会サービス）get残高合計」参照。<BR>
     * <BR>
     * @@param l_request - 債券残高照会残高合計リクエスト
     * @@return WEB3BondBalanceReferenceTotalResponse - 債券残高照会残高合計レスポンス
     * @@throws WEB3BaseException
     */
    protected WEB3BondBalanceReferenceTotalResponse getBalanceTotal(
        WEB3BondBalanceReferenceTotalRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBalanceTotal(WEB3BondBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        //概算評価額（円貨）合計
        BigDecimal l_bdYenEstimatedTotalAsset = new BigDecimal("0");

        //validate()
        l_request.validate();

        //validate注文受付可能( )
        //システムが"バッチ中"、"緊急停止中"であるかどうかのチェックを行う
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get補助口座( )
        //補助口座を取得する
        SubAccount l_subAccount = this.getSubAccount();

        //get債券保有資産一覧
        //補助口座：　@パラメータ.補助口座
        //検索条件文字列：　@null
        //検索条件データコンテナ：　@null
        //ソート条件：　@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getPositionManager();
        List l_lisAssets = l_bondPositionManager.getAssets(l_subAccount, null, null, null);

        //createResponse( )
        WEB3BondBalanceReferenceTotalResponse l_response =
            (WEB3BondBalanceReferenceTotalResponse)l_request.createResponse();

        //get債券保有資産一覧()の戻り値の要素(=保有資産)数分Loop処理
        Iterator l_iterator = l_lisAssets.iterator();
        while (l_iterator.hasNext())
        {
            WEB3BondProductManager l_bondProductManger =
                (WEB3BondProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getProductManager();

            //保有資産
            AssetRow l_assetRow = (AssetRow)l_iterator.next();
            //銘柄ID
            long l_lngProductID = l_assetRow.getProductId();

            //get債券銘柄(long)
            WEB3BondProduct l_bondProduct =
                (WEB3BondProduct)l_bondProductManger.getBondProduct(l_lngProductID);

            //is表示対象明細(String, 債券銘柄)
            //　@　@照会区分　@：　@リクエストデータ.照会区分
            //    債券銘柄　@：　@get債券銘柄（）の戻り値
            boolean l_blnIsIndicationObjectDetails =
                this.isIndicationObjectDetails(l_request.referenceType, l_bondProduct);

            //is表示対象明細（）の戻り値 == false 場合、以降の処理をskipし、
            //次の保有資産オブジェクトの処理を行なう。
            if (!l_blnIsIndicationObjectDetails)
            {
                continue;
            }

            //calc概算評価額
            //補助口座：　@取得した補助口座
            //数量：　@保有資産.数量 － 保有資産.getロック中数量
            //債券銘柄：　@取得した債券銘柄オブジェクト
            //is買付：　@false
            //is約定計算：　@false
            WEB3BondBizLogicProvider l_bizLogicProvider =
                (WEB3BondBizLogicProvider)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getBizLogicProvider();

            //数量：　@保有資産.数量 － 保有資産.getロック中数量
            Asset l_asset = null;
            try
            {
                l_asset = l_bondPositionManager.getAsset(l_assetRow.getAssetId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_asset.getQuantity()));
            BigDecimal l_bdLockedQuantity = new BigDecimal(String.valueOf(l_asset.getLockedQuantity()));
            double l_dblQuantityT = (l_bdQuantity.subtract(l_bdLockedQuantity)).doubleValue();

            WEB3BondEstimatedAssetCalcResult l_bondEstimatedAssetCalcResult =
                l_bizLogicProvider.calcEstimatedAsset(
                    l_subAccount,
                    l_dblQuantityT,
                    l_bondProduct,
                    false,
                    false);

            //債券概算評価額計算結果.概算評価額（円貨）を
            //レスポンスデータ.概算評価額（円貨）合計に加算する。
            l_bdYenEstimatedTotalAsset =
                l_bdYenEstimatedTotalAsset.add(l_bondEstimatedAssetCalcResult.getEstimatedAsset());
        }

        //レスポンスデータ.概算評価額（円貨）合計
        l_response.yenEstimatedTotalAsset = l_bdYenEstimatedTotalAsset.toString();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (is表示対象明細)<BR>
     * 表示対象明細かどうかチェックを行なう。<BR>
     * <BR>
     * ①@照会区分 == ”外国債券銘柄のみ” かつ<BR>
     *　@　@債券銘柄.債券タイプ ≠ ”外国債券”の場合、<BR>
     *　@　@falseを返却する。<BR>
     * <BR>
     * ②照会区分 == ”国内債券（個人向け国債を含む）” かつ<BR>
     *　@　@債券銘柄.債券タイプ == ”外国債券”の場合、<BR>
     *　@　@falseを返却する。<BR>
     * <BR>
     * ③照会区分 == ”国内債券（個人向け国債を含まない）” かつ<BR>
     *　@　@（　@債券銘柄.債券タイプ == ”外国債券”<BR>
     *　@ または、債券銘柄.債券タイプ == ”個人向け国債”　@）の場合、<BR>
     *　@　@falseを返却する。<BR>
     * <BR>
     * ④照会区分 == ”個人向け国債のみ” かつ<BR>
     *　@　@債券銘柄.債券タイプ ≠  ”個人向け国債”の場合、<BR>
     *　@　@falseを返却する。<BR>
     * <BR>
     * ⑤上記以外の場合、trueを返却する。<BR>
     * @@param l_strReferenceType - (照会区分)<BR>
     * 照会区分<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@return boolean
     */
    protected boolean isIndicationObjectDetails(String l_strReferenceType, WEB3BondProduct l_bondProduct)
    {
        final String STR_METHOD_NAME = "isIndicationObjectDetails(String, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        //①@照会区分 == ”外国債券銘柄のみ” かつ 債券銘柄.債券タイプ ≠ ”外国債券”の場合、
        //falseを返却する。
        if (WEB3BondBalanceReferenceTypeDef.FOREIGN_BOND_ONLY.equals(l_strReferenceType)
            && !BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //②照会区分 == ”国内債券（個人向け国債を含む）” かつ 債券銘柄.債券タイプ == ”外国債券”の場合、
        //falseを返却する。
        else if (WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND.equals(l_strReferenceType)
            && BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //③照会区分 == ”国内債券（個人向け国債を含まない）” かつ （　@債券銘柄.債券タイプ == ”外国債券”
        //または、債券銘柄.債券タイプ == ”個人向け国債”　@）の場合、
        //falseを返却する。
        else if (WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(l_strReferenceType)
            && (BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType())
            || BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND.equals(l_bondProduct.getBondType())))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //④照会区分 == ”個人向け国債のみ” かつ 債券銘柄.債券タイプ ≠  ”個人向け国債”の場合、
        //falseを返却する。
        else if (WEB3BondBalanceReferenceTypeDef.DOMESTIC_BOND_INDIVIDUAL.equals(l_strReferenceType)
            && !BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND.equals(l_bondProduct.getBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        //⑤上記以外の場合、trueを返却する。
        return true;
    }
}
@
