head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 立替金顧客明細ユニットクラス(WEB3AdminTPAdvanceCustomerDetailUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/08 asano(SCS) 新規作成
 */
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (立替金顧客明細ユニット)<BR>
 * 立替金顧客明細ユニット<BR>
 */
public class WEB3AdminTPAdvanceCustomerDetailUnit extends Message
{

    /**
     * (実質客勘残)
     */
    public String realAccountBalance;

    /**
     * (預り証券評価額)
     */
    public String trustSecurityAsset;

    /**
     * (受入保証金)
     */
    public String receiptMarginDeposit;
    
    /**
     * (保証金維持必要額)
     */
    public String marginMaintenanceAmount;
    
    /**
     * (保証金預託率)
     */
    public String marginDepositRate;
    
    /**
     * (立替金)
     */
    public String debitAmount;
    
    /**
     * (特別立替金)
     */
    public String specialDebitAmount;
    
    /**
     * (保証金請求額)
     */
    public String marginClaimedAmount;

    /**
     * (コンストラクタ)
     */
    public WEB3AdminTPAdvanceCustomerDetailUnit()
    {
    }

    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceCustomerDetailUnit[");
        l_sb.append("debitAmount=" + this.debitAmount);
        l_sb.append(",specialDebitAmount=" + this.specialDebitAmount);
        l_sb.append(",receiptMarginDeposit=" + this.receiptMarginDeposit);
        l_sb.append(",marginMaintenanceAmount=" + this.marginMaintenanceAmount);
        l_sb.append(",marginDepositRate=" + this.marginDepositRate);
        l_sb.append(",trustSecurityAsset=" + this.trustSecurityAsset);
        l_sb.append(",marginClaimedAmount=" + this.marginClaimedAmount);
        l_sb.append(",realAccountBalance=" + this.realAccountBalance);
        l_sb.append("]");
        
        return l_sb.toString();
    }
    
    
}
@
