head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignSearchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : è¿øLy[õð(WEB3AccInfoCampaignSearchCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  êÏ(u) VKì¬
Revision History : 2007/2/1  fNo.165
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (è¿øLy[õð)<BR>
 * Ly[õð<BR>
 * <BR>
 * @@author êÏ
 * @@version 1.0
 */
public class WEB3AccInfoCampaignSearchCondition extends Message 
{
    
    /**
     * (XR[h)<BR>
     * XR[h<BR>
     */
    public String branchCode;
    
    /**
     * (ÚqR[h)<BR>
     * ÚqR[h<BR>
     */
    public String accountCode;
    
    /**
     * (¤iR[h)<BR>
     * ¤iR[h<BR>
     */
    public String itemCode;
    
    /**
     * (Ly[¼Ì)<BR>
     * Ly[¼Ì
     */
    public String campaignName;
    
    /**
     * (ÎÛú)<BR>
     * ÎÛú
     */
    public Date targetDate;
    
    /**
     * (¥û¦)<BR>
     * ¥û¦<BR>
     */
    public String collectRate;
    
    /**
     * (µÒR[h)<BR>
     * µÒR[h<BR>
     */
    public String traderCode;
    
    /**
     * (ûÀJÝæª)<BR>
     * ûÀJÝæª<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (ítO)<BR>
     * ítO
     */
    public String deleteFlag;
    
    /**
     * @@roseuid 45C0875E0312
     */
    public WEB3AccInfoCampaignSearchCondition() 
    {
     
    }
}
@
