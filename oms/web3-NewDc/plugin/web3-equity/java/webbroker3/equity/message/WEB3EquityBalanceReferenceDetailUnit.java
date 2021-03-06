head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : »¨®cÆï¾×(WEB3EquityBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 òº@@mm(SRA) VKì¬
*/

package webbroker3.equity.message;


/**
 * i»¨®cÆï¾×jB<BR>
 * <BR>
 * »¨®cÆï¾×NX<BR>
 */
public class WEB3EquityBalanceReferenceDetailUnit extends WEB3EquityAssetUnit
{
    
    /**
     * (c)<BR>
     *<BR>
     * c<BR>
     */
    public String balanceQuantity;
    
    /**
     * (ts\)<BR>
     *<BR>
     * ts\<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (TZë¿P¿)<BR>
     *<BR>
     * TZë¿P¿<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * (ë¿P¿üÍÏtO)<BR>
     *<BR>
     * ë¿P¿üÍÏtO<BR>
     * <BR>
     * falseF@@¢üÍ<BR>
     * trueF@@üÍÏ<BR>
     */
    public boolean estimatedBookPriceInputFlag = false;
    
    /**
     * (¿)<BR>
     *<BR>
     * ¿<BR>
     */
    public String currentPrice = null;
    
    /**
     * (Oúä)<BR>
     *<BR>
     * Oúä<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (¿æ¾Ô)<BR>
     *<BR>
     * ¿æ¾Ô<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (DæsêR[h)<BR>
     *<BR>
     * DæsêR[h<BR>
     */
    public String primaryMarketCode = null;
    
    /**
     * (TZ]¿z(c))<BR>
     *<BR>
     * TZ]¿z(c)<BR>
     */
    public String estimatedAssetBalanceQuantity;
    
    /**
     * (TZ]¿z(tÂ\))<BR>
     *<BR>
     * TZ]¿z(tÂ\)<BR>
     */
    public String estimatedAssetSellPossQuantity;
    
    /**
     * (TZ]¿z(¶))<BR>
     *<BR>
     * TZ]¿z(¶)<BR>
     */
    public String estimatedAssetOrderedQuantity;
    
    /**
     * (TZ]¿z(ts\))<BR>
     *<BR>
     * TZ]¿z(ts\)<BR>
     */
    public String estimatedAssetSellImpossQuantity;
    
    /**
     * (TZ]¿¹v(c))<BR>
     *<BR>
     * TZ]¿¹v(c)<BR>
     */
    public String estimatedAppraisalProfitLossBalanceQuantity = null;
    
    /**
     * (TZ]¿¹v(tÂ\))<BR>
     *<BR>
     * TZ]¿¹v(tÂ\)<BR>
     */
    public String estimatedAppraisalProfitLossSellPossQuantity = null;
    
    /**
     * (TZ]¿¹v(¶))<BR>
     *<BR>
     * TZ]¿¹v(¶)<BR>
     */
    public String estimatedAppraisalProfitLossOrderedQuantity = null;
    
    /**
     * (TZ]¿¹v(ts\))<BR>
     *<BR>
     * TZ]¿¹v(ts\)<BR>
     */
    public String estimatedAppraisalProfitLossSellImpossQuantity = null;
    
    /**
     * (tÂ\tO)<BR>
     *<BR>
     * trueFtÂ\@@@@falseFtsÂ<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * RXgN^<BR>
     * <BR>
     * |©gÌSÚðnullÅú»·éB<BR>
     * @@roseuid 41B683410086<BR>
     */
    public WEB3EquityBalanceReferenceDetailUnit() 
    {
        this.balanceQuantity = null;
        this.sellImpossQuantity = null;
        this.estimatedAssetBalanceQuantity = null;
        this.estimatedAssetOrderedQuantity = null;
        this.estimatedAssetSellImpossQuantity = null;
        this.estimatedAssetSellPossQuantity = null;
        this.buyPossFlag = true;
        this.sellPossFlag = true;
    }
}
@
