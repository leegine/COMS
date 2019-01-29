head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 評価単価Callbackインターフェース(WEB3TPQuoteInfluenceCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 齋藤　@栄三 (FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;

/**
 * (評価単価Callbackインターフェース)
 */
public interface WEB3TPUnitPriceCallback
{
    /**
     * (get評価単価<対象銘柄>)<BR>
     * 評価単価を返す。(実装クラス参照)<BR>
     * @@param l_productRow - (銘柄Row)
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow);
    
    /**
     * (get評価単価<比較>)<BR>
     * 評価単価を返す。(実装クラス参照)<BR>
     * @@param l_double - (数値)
     * @@param l_product - (対象銘柄)
     * @@return double
     */
    public double getUnitPrice(double l_dblComp, WEB3TPSecurityValuationProduct l_product);
    
    /**
     * (get評価単価<比較不要>)<BR>
     * 評価単価を返す。(実装クラス参照)<BR>
     * @@param l_double - (数値)
     * @@param l_product - (対象銘柄)
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblComp, WEB3TPSecurityValuationProduct l_product);
    
    /**
     * (get評価単価<建株>)<BR>
     * 評価単価を返す。(実装クラス参照)<BR>
     * @@param l_targetContractDetail - (対象建玉詳細)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail);
}
@
