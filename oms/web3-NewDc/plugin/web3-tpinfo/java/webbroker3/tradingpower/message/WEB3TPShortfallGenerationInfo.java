head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : •s‘«‹à”­¶î•ñ(WEB3TPShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 ’£“«‰Fi’†ujV‹Kì¬ ƒ‚ƒfƒ‹No.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (•s‘«‹à”­¶î•ñ) <BR>
 * (•s‘«‹à”­¶î•ñƒ†ƒjƒbƒg)<BR>
 * <BR>
 * @@author ’£“«‰F
 * @@version 1.0
 */
public class WEB3TPShortfallGenerationInfo extends Message
{

    /**
     * (Šú“ú(T+0))<BR>
     * (Šú“ú(T+0))<BR>
     */
    public Date closeDate0;
    
    /**
     * (Šú“ú(T+1))<BR>
     * (Šú“ú(T+1))<BR>
     */
    public Date closeDate1;
    
    /**
     * (Šú“ú(T+2))<BR>
     * (Šú“ú(T+2))<BR>
     */
    public Date closeDate2;
    
    /**
     * (Šú“ú(T+3))<BR>
     * (Šú“ú(T+3))<BR>
     */
    public Date closeDate3;
    
    /**
     * (Šú“ú(T+4))<BR>
     * (Šú“ú(T+4))<BR>
     */
    public Date closeDate4;
    
    /**
     * (Šú“ú(T+5))<BR>
     * (Šú“ú(T+5))<BR>
     */
    public Date closeDate5;
    
    /**
     * (•K—v“ü‹àŠz(T+0))<BR>
     * (•K—v“ü‹àŠz(T+0))<BR>
     */
    public String requiredPayAmt0 = "0";
    
    /**
     * (•K—v“ü‹àŠz(T+1))<BR>
     * (•K—v“ü‹àŠz(T+1))<BR>
     */
    public String requiredPayAmt1 = "0";
    
    /**
     * (•K—v“ü‹àŠz(T+2))<BR>
     * (•K—v“ü‹àŠz(T+2))<BR>
     */
    public String requiredPayAmt2 = "0";
    
    /**
     * (•K—v“ü‹àŠz(T+3))<BR>
     * (•K—v“ü‹àŠz(T+3))<BR>
     */
    public String requiredPayAmt3 = "0";
    
    /**
     * (•K—v“ü‹àŠz(T+4))<BR>
     * (•K—v“ü‹àŠz(T+4))<BR>
     */
    public String requiredPayAmt4 = "0";
    
    /**
     * (•K—v“ü‹àŠz(T+5))<BR>
     * (•K—v“ü‹àŠz(T+5))<BR>
     */
    public String requiredPayAmt5 = "0";
    
    /**
     * (¸ZŠz(T+0))<BR>
     * (¸ZŠz(T+0))<BR>
     */
    public String adjustedAmt0 = "0";
    
    /**
     * (¸ZŠz(T+1))<BR>
     * (¸ZŠz(T+1))<BR>
     */
    public String adjustedAmt1 = "0";
    
    /**
     * (“úŒv‚èS‘©‹à(T+0))<BR>
     * (“úŒv‚èS‘©‹à(T+0))<BR>
     */
    public String dayTradeRestraint0 = "0";
    
    /**
     * (“úŒv‚èS‘©‹à(T+1))<BR>
     * (“úŒv‚èS‘©‹à(T+1))<BR>
     */
    public String dayTradeRestraint1 = "0";
    
    /**
     * (•ÛØ‹à‚©‚ç‚ÌU‘ÖŠz(T+0))<BR>
     * (•ÛØ‹à‚©‚ç‚ÌU‘ÖŠz(T+0))<BR>
     */
    public String transferFromMarginDeposit0 = "0";
    
    /**
     * (•ÛØ‹à‚©‚ç‚ÌU‘ÖŠz(T+1))<BR>
     * (•ÛØ‹à‚©‚ç‚ÌU‘ÖŠz(T+1))<BR>
     */
    public String transferFromMarginDeposit1 = "0";
}
@
