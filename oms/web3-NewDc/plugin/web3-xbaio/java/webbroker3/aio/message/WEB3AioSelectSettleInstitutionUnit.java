head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSelectSettleInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : IðÏ@@Ö¾×(WEB3AioSelectSettleInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 © (u) VKì¬
                   2004/10/25 üE(u) r[
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (IðÏ@@Ö¾×)<BR>
 * IðÏ@@Ö¾×NX<BR>
 * 
 * @@author ©(u)
 * @@version 1.0 
 */
public class WEB3AioSelectSettleInstitutionUnit extends Message
{
    /**
     * (Ï@@ÖID)
     */
    public String paySchemeId;
    
    /**
     * (Ï@@ÖÌ¼Ì)
     */
    public String paySchemeName;
    
    /**
     * (ótóµ)<BR>
     * Ï@@ÖÌ»_ÅÌótóµ<BR>
     * <BR>
     * 0F â~<BR>
     * 1F ót<BR>
     */
    public String receptionStatus;
    
    /**
     * (RXgN^)
     * @@return webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit
     * @@roseuid 40E257400363
     */
    public WEB3AioSelectSettleInstitutionUnit() 
    {
     
    }
}
@
