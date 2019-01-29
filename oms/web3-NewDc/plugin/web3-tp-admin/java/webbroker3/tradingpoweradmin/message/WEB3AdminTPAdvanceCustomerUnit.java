head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 立替金顧客ユニットクラス(WEB3AdminTPAdvanceCustomerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/08 asano(SCS) 新規作成
 */
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (立替金顧客ユニット)<BR>
 * 立替金顧客ユニット<BR>
 */
public class WEB3AdminTPAdvanceCustomerUnit extends Message
{

    /**
     * (余力計算結果ID)
     */
    public String calcResultId;
    
    /**
     * (部店コード)
     */
    public String branchCode;
    
    /**
     * (顧客コード)
     */
    public String accountCode;
    
    /**
     * (顧客名)
     */
    public String accountName;
    
    /**
     * (扱者コード)
     */
    public String traderCode;
    
    /**
     * (預り証券顧客区分)
     */
    public String depositDiv;
    
    /**
     * (立替金顧客明細一覧)
     */
    public WEB3AdminTPAdvanceCustomerDetailUnit[] advanceCustomerDetailUnits;
    
    /**
     * (コンストラクタ)
     */
    public WEB3AdminTPAdvanceCustomerUnit()
    {
    }
    
    /**
     * toString
     *
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceCustomerUnit[");
        l_sb.append("branchCode=" + this.branchCode);
        l_sb.append(",accountCode=" + this.accountCode);
        l_sb.append(",accountName=" + this.accountName);
        l_sb.append(",calcResultId=" + this.calcResultId);
        l_sb.append(",depositDiv=" + this.depositDiv);
        l_sb.append(",traderCode=" + this.traderCode);
        for(int i = 0; i < this.advanceCustomerDetailUnits.length; i++)
        {
            l_sb.append(",advanceCustomerDetailUnits[" + i + "]=" + this.advanceCustomerDetailUnits[i]);            
        }
        l_sb.append("]");
        
        return l_sb.toString();
    }
    
}
@
