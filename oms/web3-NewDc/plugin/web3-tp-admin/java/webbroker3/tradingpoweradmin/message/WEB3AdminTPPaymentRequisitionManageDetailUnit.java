head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : ()εa€ Ψ\[VVXeζρ
 File Name        : όΰΏΗΎΧjbgNX(WEB3AdminTPAdvanceCustomerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/03/13 {{ Δ(SCS) VKμ¬
 */
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * όΰΏΗΎΧjbg
 */
public class WEB3AdminTPPaymentRequisitionManageDetailUnit  extends Message 
{
    
    /**
     * cΖϊ
     */
    public Date bizDate;
    
    /**
     * όΰΏz
     */
    public String paymentRequisitionAmount;
    
    /**
     * Ώζͺ
     * 
     * 1:§Φΰ 2:ΑΚ§Φΰ 3:ΗΨ 4:§ΦΰEΗΨ 5:ΑΚ§ΦΰEΗΨ
     */
    public String paymentRequisitionDivision;
    
    /**
     * aθΰ
     */
    public String accountBalance;
    
    /**
     * SONARq¨
     */
    public String sonarAccountBalance;
    
    /**
     * ϊvθS©ΰ
     */
    public String dayTradeRestraint;
    
    /**
     * »ΜΌS©ΰ
     */
    public String otherRestraint;
    
    /**
     * ΟυΫΨΰ
     */
    public String paidMarginDeposit;
    
    /**
     * σόΫΨΰc
     */
    public String receiptMarginDepositRest;
    
    /**
     * KvΫΨΰ
     */
    public String marginDeposit;
    
    /**
     * ΰ»ΰKvΫΨΰ
     */
    public String cashMarginDeposit;
    
    /**
     * Κγΰ
     */
    public String contractAmount;
    
    /**
     * ΫΨΰaυ¦
     */
    public String marginDepositRate;
    
    /**
     * @@roseuid 4412A9CC0035
     */
    public WEB3AdminTPPaymentRequisitionManageDetailUnit() 
    {
     
    }

    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceDetailUnit=");
        l_sb.append("accountBalance=" + this.accountBalance);
        l_sb.append(",sonarAccountBalance=" + this.sonarAccountBalance);
        l_sb.append(",dayTradeRestraint=" + this.dayTradeRestraint);
        l_sb.append(",otherRestraint=" + this.otherRestraint);
        l_sb.append(",paidMarginDeposit=" + this.paidMarginDeposit);
        l_sb.append(",receiptMarginDepositRest=" + this.receiptMarginDepositRest);
        l_sb.append(",marginDeposit=" + this.marginDeposit);
        l_sb.append(",cashMarginDeposit=" + this.cashMarginDeposit);
        l_sb.append(",contractAmount=" + this.contractAmount);
        l_sb.append(",marginDepositRate=" + this.marginDepositRate);
        return l_sb.toString();
    }
}
@
