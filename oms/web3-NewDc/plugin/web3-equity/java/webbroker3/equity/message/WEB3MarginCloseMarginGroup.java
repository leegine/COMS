head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : MpζψΟκs(WEB3MarginCloseMarginGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ½½ (u) VKμ¬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * iMpζψΟκsjB<br>
 * <br>
 * MpζψΟκsNX
 * @@author ½½
 * @@version 1.0
 */
public class WEB3MarginCloseMarginGroup extends Message 
{
    
    /**
     * (ΑΏR[h)
     */
    public String productCode;
    
    /**
     * (ΑΏΌ)
     */
    public String productName;
    
    /**
     * (sκR[h)
     */
    public String marketCode;
    
    /**
     * (ϋΐζͺ)<BR>
     * 0FκΚ@@1FΑθ
     */
    public String taxType;
    
    /**
     * (ζͺ)<BR>
     * 1F@@2F<BR>
     * iContractTypeEnumΙΔθ`j<BR>
     */
    public String contractType;
    
    /**
     * (ΩΟ)<BR>
     * MpζψΩΟ
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * ()
     */
    public String contractQuantity;
    
    /**
     * (½ΟPΏ)
     */
    public String averageContractPrice;
    
    /**
     * (»έl)
     */
    public String currentPrice;
    
    /**
     * (]ΏΉv)
     */
    public String appraisalProfitLoss;
    
    /**
     * (ΟσΤζͺ)<BR>
     * 0FΟΟ@@1F’Ο@@2FΟ
     */
    public String settlementState;
    
    /**
     * (ΤΟΒ\tO)<BR>
     * trueFΤΟΒ\@@falseFΤΟsΒ
     */
    public boolean closeMarginFlag;
    
    /**
     * (»ψ»nΒ\tO)<BR>
     * trueF»ψ»nΒ\@@falseF»ψ»nsΒ
     */
    public boolean swapFlag;
    
    /**
     * (ΎΧκ)<BR>
     * MpζψΎΧΜκ
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * @@roseuid 414032D10087
     */
    public WEB3MarginCloseMarginGroup() 
    {
     
    }
}
@
