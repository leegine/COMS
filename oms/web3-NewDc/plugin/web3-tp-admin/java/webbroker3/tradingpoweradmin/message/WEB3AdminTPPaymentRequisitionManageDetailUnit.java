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
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 入金請求管理明細ユニットクラス(WEB3AdminTPAdvanceCustomerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
 */
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 入金請求管理明細ユニット
 */
public class WEB3AdminTPPaymentRequisitionManageDetailUnit  extends Message 
{
    
    /**
     * 営業日
     */
    public Date bizDate;
    
    /**
     * 入金請求額
     */
    public String paymentRequisitionAmount;
    
    /**
     * 請求区分
     * 
     * 1:立替金 2:特別立替金 3:追証 4:立替金・追証 5:特別立替金・追証
     */
    public String paymentRequisitionDivision;
    
    /**
     * 預り金
     */
    public String accountBalance;
    
    /**
     * SONAR客勘
     */
    public String sonarAccountBalance;
    
    /**
     * 日計り拘束金
     */
    public String dayTradeRestraint;
    
    /**
     * その他拘束金
     */
    public String otherRestraint;
    
    /**
     * 委託保証金
     */
    public String paidMarginDeposit;
    
    /**
     * 受入保証金残
     */
    public String receiptMarginDepositRest;
    
    /**
     * 必要保証金
     */
    public String marginDeposit;
    
    /**
     * 内現金必要保証金
     */
    public String cashMarginDeposit;
    
    /**
     * 建玉代金
     */
    public String contractAmount;
    
    /**
     * 保証金預託率
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
