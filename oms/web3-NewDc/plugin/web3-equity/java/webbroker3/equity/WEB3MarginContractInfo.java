head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        :  (Mpζψξρ)<BR>
                 :   ξρκi[NX(WEB3MarginContractInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 Ότ (u) VKμ¬
                   2005/01/06 ͺΊaΎ (SRA) JavaDocC³
*/
package webbroker3.equity;

import java.util.Date;


/**
 * iMpζψξρjB<BR>
 * <BR>
 * ξρκi[NX
 * @@version 1.0
 */
public class WEB3MarginContractInfo 
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
    public String standardName;
    
    /**
     * (sκR[h)
     */
    public String marketCode;
    
    /**
     * (ϋΐζͺ)<BR>
     * 0FκΚ@@1FΑθ
     */
    public String accountType;
    
    /**
     * (ζͺ)<BR>
     * 1F@@2F
     * iContractTypeEnumΙΔθ`j
     */
    public String contractType;
    
    /**
     * (ΩΟζͺ)<BR>
     * 1F§xMp@@2FκΚMp
     */
    public String repaymentType;
    
    /**
     * (ΩΟϊΐl)<BR>
     * wθB<BR>
     * ³ϊΐΜκh9999999h
     */
    public String repaymentNum;
    
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
    public String quantity;
    
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
     * (]ΏΉv)
     */
    public String evaluationIncome;
    
    /**
     * (]ΏΉv(oοlΆ))<BR>
     * oοπ·΅ψ’½]ΏΉv
     */
    public String takeExpensesOffEvaluationIncome;
    
    /**
     * (θΏ)
     */
    public String setupFee;
    
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
    public String transferFee;
    
    /**
     * (Ηο)
     */
    public String managementFee;
    
    /**
     * (»ΜΌ)
     */
    public String other;
    
    /**
     * (ΟσΤζͺ)<BR>
     * 0FΟΟ@@1F’Ο@@2FΟ
     */
    public String closingStatusType;
    
    /**
     * (ΤΟΒ\tO)<BR>
     * trueFΤΟΒ\@@falseFΤΟsΒ
     */
    public boolean closingPossibleFlag;
    
    /**
     * (»ψ»nΒ\tO)<BR>
     * trueF»ψ»nΒ\@@falseF»ψ»nsΒ
     */
    public boolean swapPossibleFlag;
    
    /**
     * (Mpζψξρ)<BR>
     * RXgN^
     * @@roseuid 40EB7FD70197
     */
    public WEB3MarginContractInfo() 
    {
     
    }
}
@
