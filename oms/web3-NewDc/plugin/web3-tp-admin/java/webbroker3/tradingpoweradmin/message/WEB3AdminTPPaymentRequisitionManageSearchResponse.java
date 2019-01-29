head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理一覧レスポンスクラス(WEB3AdminTPPaymentRequisitionManageSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 入金請求管理一覧レスポンス
 */
public class WEB3AdminTPPaymentRequisitionManageSearchResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_payment_requisition_manage_List";
    
    /**
     * 総ページ数
     */
    public String totalPages;
    
    /**
     * 総レコード数
     */
    public String totalRecords;
    
    /**
     * 表示ページ番号
     */
    public String pageIndex;
    
    /**
     * 入金請求顧客一覧
     */
    public WEB3AdminTPPaymentRequisitionManageUnit[] manageUnits;
    
    /**
     * @@roseuid 4412A9CA017D
     */
    public WEB3AdminTPPaymentRequisitionManageSearchResponse() 
    {
     
    }
}
@
