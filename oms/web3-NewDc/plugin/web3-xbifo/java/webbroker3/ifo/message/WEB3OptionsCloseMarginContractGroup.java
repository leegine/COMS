head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΏwIvVΤΟΚκΎΧsNX(WEB3OptionsCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ΰς VKμ¬
              001: 2004/07/28 €Ε (u) Ξ ΪΧέv`FbNwE (ϊ{€) 
                   com.fitechlabs.xtrade.kernel.message.Messageπp³B
Revesion History : 2007/06/08   ·^] (u) dlΟXfNo.640
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (ΏwIvVΤΟΚκΎΧs)<BR>
 * ΏwIvVΤΟΚκΎΧsNX<BR>
 * @@author ΰς
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginContractGroup extends Message
{
      
    /**
     * (PΏ)<BR>
     */
    public String contractPrice;
    
    /**
     * (ϊ)<BR>
     */
    public Date openDate;
    
    /**
     * (ΤΟΚiΆͺΚj)<BR>
     */
    public String contractOrderQuantity;

    /**
     * (ΆPΏζͺ)<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (ΆPΏ)<BR>
     */
    public String limitPrice;

    /**
     * (ρθΚ)<BR>
     */
    public String execQuantity;
    
    /**
     * (ρθPΏ)<BR>
     */
    public String execPrice;
    
    /**
     * (θΏ)<BR>
     */
    public String contractCommission;
    
    /**
     * (θΏΑοΕ)<BR>
     */
    public String contractCommissionConsumptionTax;
    
    /**
     * (ρθΰz)<BR>
     */
    public String contractExecTotalPrice;
    
    /**
     * (Ήv)<BR>
     */
    public String income;
    
    /**
     * (Κ)<BR>
     */
    public String closeMarginOrderNumber;

    /**
     * (§οζͺ)<BR>
     * 1F[κi[κΐ{·ιοΠΜ[κΤΡΜέέθj@@NULLF»ΜΌ<BR>
     */
    public String sessionType;
}
@
