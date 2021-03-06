head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : MpζψΖοΎΧ(WEB3MarginContractReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ½½ (u) VKμ¬
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * iMpζψΖοΎΧjB<br>
 * <br>
 * MpζψΖοΎΧNX
 * @@author ½½
 * @@version 1.0
 */
public class WEB3MarginContractReferenceUnit extends Message 
{
    
    /**
     * (ID)<BR>
     * hc
     */
    public String id;
    
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
     * (ϊ)
     */
    public Date openDate;
    
    /**
     * (PΏ)
     */
    public String contractPrice;
    
    /**
     * ()
     */
    public String contractOrderQuantity;
    
    /**
     * (ϊϊ)<BR>
     * κΚMpA³ϊΐΜκh99991231h
     */
    public Date closeDate;
    
    /**
     * (γΰ)
     */
    public String contractExecPrice;
    
    /**
     * (θΏ)
     */
    public String contractCommission;
    
    /**
     * (ϊΰ)
     */
    public String interestFee;
    
    /**
     * (tϊΰ)
     */
    public String payInterestFee;
    
    /**
     * (έΏ)
     */
    public String loanEquityFee;
    
    /**
     * (·Ώ)
     */
    public String setupFee;
    
    /**
     * (Ηο)
     */
    public String managementFee;
    
    /**
     * (»ΜΌ)
     */
    public String otherwise;
    
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
     * (MpζψΖοΎΧ)<BR>
     * RXgN^B
     * @@return webbroker3.margin.message.WEB3MarginContractReferenceUnit
     * @@roseuid 40E3F0B7013F
     */
    public WEB3MarginContractReferenceUnit() 
    {
     
    }
}
@
