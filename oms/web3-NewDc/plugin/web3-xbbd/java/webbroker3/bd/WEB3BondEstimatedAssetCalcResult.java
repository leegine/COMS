head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondEstimatedAssetCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
 File Name        : ÂŒ”ŠTZ•]‰¿ŠzŒvZŒ‹‰Ê(WEB3BondEstimatedAssetCalcResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ê‰Ï (’†u) V‹Kì¬
 */

package webbroker3.bd;

import java.math.BigDecimal;

/**
 * (ÂŒ”ŠTZ•]‰¿ŠzŒvZŒ‹‰Ê)<BR>
 * ÂŒ”ŠTZ•]‰¿ŠzŒvZŒ‹‰Ê<BR>
 * 
 * @@author ê‰Ï
 * @@version 1.0
 */
public class WEB3BondEstimatedAssetCalcResult 
{
    /**
     * (•]‰¿’P‰¿)<BR>
     * •]‰¿’P‰¿<BR>
     */
    private BigDecimal estimatedPrice;
    
    /**
     * (ŠTZ•]‰¿Šzi‰~‰İj)<BR>
     * ŠTZ•]‰¿Šzi‰~‰İj<BR>
     */
    private BigDecimal estimatedAsset;
    
    /**
     * (ŠTZ•]‰¿ŠziŠO‰İj)<BR>
     * ŠTZ•]‰¿ŠziŠO‰İj<BR>
     */
    private BigDecimal foreignEstimatedAsset;
    
    /**
     * (ÂŒ”ŠTZ•]‰¿ŠzŒvZŒ‹‰Ê)<BR>
     * ƒRƒ“ƒXƒgƒ‰ƒNƒ^<BR>
     * @@roseuid 44C085DA025A
     */
    public WEB3BondEstimatedAssetCalcResult() 
    {
     
    }
    
    /**
     * (getŠTZ•]‰¿Šzi‰~‰İj)<BR>
     * ŠTZ•]‰¿Šzi‰~‰İj‚ğæ“¾‚·‚éB<BR>
     * @@return BigDecima‚Œ
     * @@roseuid 44C088020131
     */
    public BigDecimal getEstimatedAsset() 
    {
         return estimatedAsset;
    }
    
    /**
     * (getŠTZ•]‰¿ŠziŠO‰İj)<BR>
     * ŠTZ•]‰¿ŠziŠO‰İj‚ğæ“¾‚·‚éB<BR>
     * @@return BigDecima‚Œ
     * @@roseuid 44C088230316
     */
    public BigDecimal getForeignEstimatedAsset() 
    {
         return foreignEstimatedAsset;
    }
    
    /**
     * (get•]‰¿’P‰¿)<BR>
     * •]‰¿’P‰¿‚ğæ“¾‚·‚éB<BR>
     * @@return BigDecimal
     * @@roseuid 44C95EDC03BF
     */
    public BigDecimal getEstimatedPrice() 
    {
         return estimatedPrice;
    }
    
    /**
     * (setŠTZ•]‰¿Šzi‰~‰İj)<BR>
     * ŠTZ•]‰¿Šzi‰~‰İj‚ğƒZƒbƒg‚·‚éB<BR>
     * @@param l_bdEstimatedAsset - (ŠTZ•]‰¿Šzi‰~‰İj)<BR>
     * ŠTZ•]‰¿Šzi‰~‰İj<BR>
     * @@roseuid 44C95F0E00C2
     */
    public void setEstimatedAsset(BigDecimal l_bdEstimatedAsset) 
    {
         this.estimatedAsset = l_bdEstimatedAsset;
    }
    
    /**
     * (setŠTZ•]‰¿ŠziŠO‰İj)<BR>
     * ŠTZ•]‰¿ŠziŠO‰İj‚ğƒZƒbƒg‚·‚éB<BR>
     * @@param l_bdForeignEstimatedAsset - (ŠTZ•]‰¿ŠziŠO‰İj)<BR>
     * ŠTZ•]‰¿ŠziŠO‰İj<BR>
     * @@roseuid 44C95F490083
     */
    public void setForeignEstimatedAsset(BigDecimal l_bdForeignEstimatedAsset) 
    {
        this.foreignEstimatedAsset = l_bdForeignEstimatedAsset;
    }
    
    /**
     * (set•]‰¿’P‰¿)<BR>
     * •]‰¿’P‰¿‚ğƒZƒbƒg‚·‚éB<BR>
     * @@param l_bdEstimatedPrice - (•]‰¿’P‰¿)<BR>
     * •]‰¿’P‰¿<BR>
     * @@roseuid 44C086F601BE
     */
    public void setEstimatedPrice(BigDecimal l_bdEstimatedPrice) 
    {
        this.estimatedPrice = l_bdEstimatedPrice;
    }
}
@
