head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 入金請求管理履歴ユニットクラス(WEB3AdminTPPaymentRequisitionManageHistoryUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
 */

package webbroker3.tradingpoweradmin.message;

import java.util.Date;

/**
 * 入金請求管理履歴ユニット
 */
public class WEB3AdminTPPaymentRequisitionManageHistoryUnit extends WEB3AdminTPPaymentRequisitionManageHistoryResponse 
{
    
    /**
     * 日付
     */
    public Date bizDate;
    
    /**
     * 属性
     * 
     * 1:前金 2:証評 3:信用
     */
    public String attribute;
    
    /**
     * 停止
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
    
    /**
     * 入金請求管理明細一覧
     */
    public WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails;
//    public WEB3AdminTPPaymentRequisitionManageHistoryResponse WEB3AdminTPPaymentRequisitionManageHistoryResponse;
    
    /**
     * @@roseuid 4412A9CB0083
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit() 
    {
     
    }
}
@
