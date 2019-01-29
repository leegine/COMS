head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceQuoteCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 評価単価<時価>Callback(WEB3TPQuoteUnitPriceCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;

/**
 * (評価単価<時価>Callback)
 */
public class WEB3TPUnitPriceQuoteCallback extends WEB3TPUnitPriceStandardCallback
{

    /**
     * (評価単価<時価>Callback)<BR>
     * コンストラクタ<BR>
     * @@param l_calcCondition
     */
    public WEB3TPUnitPriceQuoteCallback(WEB3TPCalcCondition l_calcCondition)
    {
        super(l_calcCondition);
    }

    /**
     * (get評価単価<対象銘柄>)<BR>
     * 銘柄タイプが株式の場合、時価を返す。<BR>
     * 銘柄タイプが株式でない、又は 株式で時価を取得できない場合、<BR>
     * スーパークラスのメソッドの戻り値を返す。<BR>
     * @@param l_productRow - (銘柄Row)
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow) 
    {
        
        //プロダクトタイプを取得
        ProductTypeEnum l_productTypeEnum = l_productRow.getProductType();

        //評価単価
        double l_dblUnitPrice = 0.0;
        
        //株式の場合
        if(ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
        {
            //銘柄IDを取得
            long l_lngProdcutId = l_productRow.getProductId();
            
            //優先市場IDに値がある時だけ時価を取得する
            if(! l_productRow.getPrimaryMarketIdIsNull())
            {
                //市場IDを取得
                long l_lngMarketId = l_productRow.getPrimaryMarketId();
                
                //時価を取得
                l_dblUnitPrice = calcCondition.getEqtypeQuote(l_lngProdcutId, l_lngMarketId);
            }
        }
        
        //評価単価の値に変更がない場合(株式以外 又は 株式(ミニ株含む)で時価を取得できない場合)
        if(l_dblUnitPrice == 0.0)
        {
            //標準処理
            l_dblUnitPrice = super.getUnitPrice(l_productRow);
        }
        
        return l_dblUnitPrice;
    }

    /**
     * (get評価単価<比較>)<BR>
     * 対象銘柄.評価単価を返す。<BR>
     * @@param l_double - (数値)
     * @@param l_product - (対象銘柄)
     * @@return double
     */
    public double getUnitPrice(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        //対象銘柄の評価単価が時価か終値かは関係なく、常に評価単価を返す
        return l_product.getUnitPrice();
    }

    /**
     * (get評価単価<比較不要>)<BR>
     * 対象銘柄.評価単価を返す。<BR>
     * @@param l_double - (数値)
     * @@param l_product - (対象銘柄)
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        //対象銘柄の評価単価が時価か終値かは関係なく、常に評価単価を返す
        return l_product.getUnitPrice();
    }

    /**
     * (get評価単価<建株>)<BR>
     * 時価を取得し、時価を返す。<BR>
     * 時価を取得できない場合、<BR>
     * スーパークラスのメソッドの戻り値を返す。<BR>
     * @@param l_targetContractDetail - (対象建玉詳細)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        //銘柄IDを取得
        long l_productId = l_targetContractDetail.getProductId();
        
        //市場IDを取得
        long l_marketId = l_targetContractDetail.getMarketId();
        
        //時価を取得
        double l_unitPrice = calcCondition.getEqtypeQuote(l_productId, l_marketId);
        
        //時価を取得できない場合
        if(l_unitPrice == 0.0)
        {
            //標準処理
            l_unitPrice = super.getUnitPrice(l_targetContractDetail);
        }
        
        return l_unitPrice;
    }
}
@
