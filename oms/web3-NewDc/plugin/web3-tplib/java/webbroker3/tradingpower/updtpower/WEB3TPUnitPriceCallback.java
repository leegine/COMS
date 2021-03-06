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
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ]ΏPΏCallbackC^[tF[X(WEB3TPQuoteInfluenceCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 βV‘@@hO (FLJ) VKμ¬
*/
package webbroker3.tradingpower.updtpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;

/**
 * (]ΏPΏCallbackC^[tF[X)
 */
public interface WEB3TPUnitPriceCallback
{
    /**
     * (get]ΏPΏ<ΞΫΑΏ>)<BR>
     * ]ΏPΏπΤ·B(ΐNXQΖ)<BR>
     * @@param l_productRow - (ΑΏRow)
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow);
    
    /**
     * (get]ΏPΏ<δr>)<BR>
     * ]ΏPΏπΤ·B(ΐNXQΖ)<BR>
     * @@param l_double - (l)
     * @@param l_product - (ΞΫΑΏ)
     * @@return double
     */
    public double getUnitPrice(double l_dblComp, WEB3TPSecurityValuationProduct l_product);
    
    /**
     * (get]ΏPΏ<δrsv>)<BR>
     * ]ΏPΏπΤ·B(ΐNXQΖ)<BR>
     * @@param l_double - (l)
     * @@param l_product - (ΞΫΑΏ)
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblComp, WEB3TPSecurityValuationProduct l_product);
    
    /**
     * (get]ΏPΏ<>)<BR>
     * ]ΏPΏπΤ·B(ΐNXQΖ)<BR>
     * @@param l_targetContractDetail - (ΞΫΚΪΧ)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail);
}
@
