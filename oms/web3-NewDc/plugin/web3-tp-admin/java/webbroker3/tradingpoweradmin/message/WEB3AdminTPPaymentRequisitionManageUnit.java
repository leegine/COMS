head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理ユニットクラス(WEB3AdminTPPaymentRequisitionManageUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 入金請求管理ユニット
 */
public class WEB3AdminTPPaymentRequisitionManageUnit extends Message 
{
    
    /**
     * 部店コード
     */
    public String branchCode;

    /**
     * 顧客コード
     */
    public String accountCode;

    /**
     * 顧客名
     */
    public String accountName;
    
    /**
     * 扱者コード
     */
    public String traderCode;
    
    /**
     * 属性
     * 
     * 1:前金 2:証評 3:信用
     */
    public String attribute;
    
    /**
     * 取引停止
     */
    public String[] tradingPowerStop;

    /**
     * 20%割れ発生日
     */
    public Date break20Day;
    
    /**
     * 20%割れ経過日数
     */
    public String break20ElapsedDays;
    
    /**
     * 30%割れ発生日
     */
    public Date break30Day;
    
    /**
     * 30%割れ経過日数
     */
    public String break30ElapsedDays;
    
    public WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails;
    
    /**
     * @@roseuid 4412A9CB0238
     */
    public WEB3AdminTPPaymentRequisitionManageUnit() 
    {
     
    }

    /**
     * toString
     *
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPPaymentRequisitionManageUnit[");
        l_sb.append("branchCode=" + this.branchCode);
        l_sb.append("accountCode=" + this.accountCode);
        l_sb.append("accountName=" + this.accountName);
        l_sb.append("traderCode=" + this.traderCode);
        l_sb.append("attribute=" + this.attribute);
        l_sb.append("break20Day=" + this.break20Day);
        l_sb.append("break20ElapsedDays=" + this.break20ElapsedDays);
        l_sb.append("break30Day=" + this.break30Day);
        l_sb.append("break30ElapsedDays=" + this.break30ElapsedDays);
        l_sb.append("]");
        
        return l_sb.toString();
    }

}
@
