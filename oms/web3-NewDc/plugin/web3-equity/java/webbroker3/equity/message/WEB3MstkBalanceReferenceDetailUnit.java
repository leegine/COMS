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
filename	WEB3MstkBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ®~jcÆï¾×(WEB3MstkBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 òº@@mm(SRA) VKì¬
*/

package webbroker3.equity.message;


/**
 * i®~jcÆï¾×jB<BR>
 * <BR>
 * ®~jcÆï¾×NX<BR>
 */
public class WEB3MstkBalanceReferenceDetailUnit extends WEB3MstkSellUnit
{
    
    /**
     * (ID)<BR>
     * <BR>
     * ÛLYID<BR>
     */
    public String id;
    
    /**
     * (ûÀæª)<BR>
     * <BR>
     * 0FêÊ@@1FÁè<BR>
     */
    public String taxType;
    
    /**
     * (TZë¿P¿)<BR>
     * <BR>
     * TZë¿P¿<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * (ë¿P¿üÍÏtO)<BR>
     * <BR>
     * falseF@@¢üÍ<BR>
     * trueF@@üÍÏ<BR>
     */
    public boolean estimatedBookPriceInputFlag = false;
    
    /**
     * (¿)<BR>
     * <BR>
     * ¿<BR>
     */
    public String currentPrice = null;
    
    /**
     * (Oúä)<BR>
     * <BR>
     * Oúä<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (¿æ¾Ô)<BR>
     * <BR>
     * ¿æ¾Ô<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (TZ]¿z)<BR>
     * <BR>
     * TZ]¿z<BR>
     */
    public String estimatedAsset;
    
    /**
     * (TZ]¿¹v)<BR>
     * <BR>
     * TZ]¿¹v<BR>
     */
    public String estimatedlAppraisalProfitLoss = null;
    
    /**
     * (tÂ\tO)<BR>
     * <BR>
     * trueFtÂ\@@@@falseFtsÂ<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * ftHgRXgN^<BR>
     * <BR>
     * @@roseuid 41C65B3400C0<BR>
     */
    public WEB3MstkBalanceReferenceDetailUnit() 
    {
     
    }
}
@
