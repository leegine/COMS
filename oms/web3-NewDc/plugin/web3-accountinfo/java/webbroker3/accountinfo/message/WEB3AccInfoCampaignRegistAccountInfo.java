head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCampaignRegistAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : è¿øLy[o^Úqîñ(WEB3AccInfoCampaignRegistAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  êÏ(u) VKì¬
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (è¿øLy[o^Úqîñ)<BR>
 * è¿øLy[o^Úqîñ<BR>
 * <BR>
 * @@author êÏ
 * @@version 1.0
 */
public class WEB3AccInfoCampaignRegistAccountInfo extends Message 
{
    
    /**
     * (è¿Ly[ðID)<BR>
     * è¿Ly[ðID<BR>
     */
    public String campaignId;
    
    /**
     * (¤iR[h)<BR>
     * ¤iR[h<BR>
     * <BR>
     * 10 F ãê®<BR>
     * 11 F JASDAQ<BR>
     * 12 F ~j® <BR>
     * 30 F Â <BR>
     * 31 F ÂiXªj <BR>
     */
    public String[] itemCode;
    
    /**
     * (Ly[¼Ì)<BR>
     * Ly[¼Ì<BR>
     */
    public String campaignName;
    
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
     * (Úq¼)<BR>
     * Úq¼<BR>
     */
    public String accountName;
    
    /**
     * (µÒR[h)<BR>
     * µÒR[h<BR>
     */
    public String traderCode;
    
    /**
     * (ûÀJÝæª)<BR>
     * ûÀJÝæª<BR>
     * <BR>
     * 1 F ûÀ<BR>
     * 2 F MpûÀ<BR>
     * 3 F æ¨OPûÀ<BR>
     * 4 F FXûÀ<BR>
     * 5 F ûÀ<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (¥û¦)<BR>
     * ¥û¦<BR>
     */
    public String collectRate;
    
    /**
     * (ÎÛúÔFrom)<BR>
     * ÎÛúÔFrom<BR>
     */
    public Date targetPeriodFrom;
    
    /**
     * (ÎÛúÔTo)<BR>
     * ÎÛúÔTo<BR>
     */
    public Date targetPeriodTo;
    
    /**
     * (o^^Cv)<BR>
     * o^^Cv<BR>
     * <BR>
     * 0 F ûÀJÝðwè<BR>
     * 1 F ÂÊÚqwè<BR>
     * 2 F ­§ÂÊÚqwè<BR>
     */
    public String registType;
    
    /**
     * (Løæª)<BR>
     * Løæª<BR>
     * <BR>
     * 0 F ³ø<BR>
     * 1 F Lø<BR>
     */
    public String activeDiv;
    
    /**
     * @@roseuid 45C0875E01F9
     */
    public WEB3AccInfoCampaignRegistAccountInfo() 
    {
     
    }
}
@
