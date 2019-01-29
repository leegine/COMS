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
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : Œ»•¨Š”®c‚Æ‰ï–¾×(WEB3EquityBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 ‘ò‘º@@mm(SRA) V‹Kì¬
*/

package webbroker3.equity.message;


/**
 * iŒ»•¨Š”®c‚Æ‰ï–¾×jB<BR>
 * <BR>
 * Œ»•¨Š”®c‚Æ‰ï–¾×ƒNƒ‰ƒX<BR>
 */
public class WEB3EquityBalanceReferenceDetailUnit extends WEB3EquityAssetUnit
{
    
    /**
     * (c‚Š””)<BR>
     *<BR>
     * c‚Š””<BR>
     */
    public String balanceQuantity;
    
    /**
     * (”„•t•s”\Š””)<BR>
     *<BR>
     * ”„•t•s”\Š””<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (ŠTZ•ë‰¿’P‰¿)<BR>
     *<BR>
     * ŠTZ•ë‰¿’P‰¿<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * (•ë‰¿’P‰¿“ü—ÍÏƒtƒ‰ƒO)<BR>
     *<BR>
     * •ë‰¿’P‰¿“ü—ÍÏƒtƒ‰ƒO<BR>
     * <BR>
     * falseF@@–¢“ü—Í<BR>
     * trueF@@“ü—ÍÏ<BR>
     */
    public boolean estimatedBookPriceInputFlag = false;
    
    /**
     * (‰¿)<BR>
     *<BR>
     * ‰¿<BR>
     */
    public String currentPrice = null;
    
    /**
     * (‘O“ú”ä)<BR>
     *<BR>
     * ‘O“ú”ä<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (‰¿æ“¾ŠÔ)<BR>
     *<BR>
     * ‰¿æ“¾ŠÔ<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (—DæsêƒR[ƒh)<BR>
     *<BR>
     * —DæsêƒR[ƒh<BR>
     */
    public String primaryMarketCode = null;
    
    /**
     * (ŠTZ•]‰¿Šz(c‚Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿Šz(c‚Š””)<BR>
     */
    public String estimatedAssetBalanceQuantity;
    
    /**
     * (ŠTZ•]‰¿Šz(”„•t‰Â”\Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿Šz(”„•t‰Â”\Š””)<BR>
     */
    public String estimatedAssetSellPossQuantity;
    
    /**
     * (ŠTZ•]‰¿Šz(’•¶’†Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿Šz(’•¶’†Š””)<BR>
     */
    public String estimatedAssetOrderedQuantity;
    
    /**
     * (ŠTZ•]‰¿Šz(”„•t•s”\Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿Šz(”„•t•s”\Š””)<BR>
     */
    public String estimatedAssetSellImpossQuantity;
    
    /**
     * (ŠTZ•]‰¿‘¹‰v(c‚Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿‘¹‰v(c‚Š””)<BR>
     */
    public String estimatedAppraisalProfitLossBalanceQuantity = null;
    
    /**
     * (ŠTZ•]‰¿‘¹‰v(”„•t‰Â”\Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿‘¹‰v(”„•t‰Â”\Š””)<BR>
     */
    public String estimatedAppraisalProfitLossSellPossQuantity = null;
    
    /**
     * (ŠTZ•]‰¿‘¹‰v(’•¶’†Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿‘¹‰v(’•¶’†Š””)<BR>
     */
    public String estimatedAppraisalProfitLossOrderedQuantity = null;
    
    /**
     * (ŠTZ•]‰¿‘¹‰v(”„•t•s”\Š””))<BR>
     *<BR>
     * ŠTZ•]‰¿‘¹‰v(”„•t•s”\Š””)<BR>
     */
    public String estimatedAppraisalProfitLossSellImpossQuantity = null;
    
    /**
     * (”ƒ•t‰Â”\ƒtƒ‰ƒO)<BR>
     *<BR>
     * trueF”ƒ•t‰Â”\@@@@falseF”ƒ•t•s‰Â<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * ƒRƒ“ƒXƒgƒ‰ƒNƒ^<BR>
     * <BR>
     * |©g‚Ì‘S€–Ú‚ğnull‚Å‰Šú‰»‚·‚éB<BR>
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
