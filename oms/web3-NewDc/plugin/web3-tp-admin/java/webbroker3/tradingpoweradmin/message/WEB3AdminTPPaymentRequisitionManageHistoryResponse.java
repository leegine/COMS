head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理履歴レスポンス(WEB3AdminTPPaymentRequisitionManageHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 入金請求管理履歴レスポンス
 */
public class WEB3AdminTPPaymentRequisitionManageHistoryResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_payment_requisition_manage_history";

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
     * 入金請求管理履歴一覧
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit[] historyUnits;
//    public WEB3AdminTPPaymentRequisitionManageHistoryUnit WEB3AdminTPPaymentRequisitionManageHistoryUnit[];
    
    /**
     * @@roseuid 4412A9CA02F4
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryResponse() 
    {
     
    }
}
@
