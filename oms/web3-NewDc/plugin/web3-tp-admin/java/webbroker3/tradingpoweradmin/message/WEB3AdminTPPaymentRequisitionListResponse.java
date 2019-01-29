head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索一覧レスポンス(WEB3AdminTPPaymentRequisitionListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (入金請求顧客検索一覧レスポンス)<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081728L;

    /**
     * (計算日)<BR>
     */
    public Date calcDate;

    /**
     * (請求事由)<BR>
     */
    public String claimReason;

    /**
     * (日数)<BR>
     */
    public String days = null;

    /**
     * (総ページ数)<BR>
     */
    public String totalPages;

    /**
     * (表示ページ番号)<BR>
     */
    public String pageIndex;

    /**
     * (総レコード数)<BR>
     */
    public String totalRecords;

    /**
     * (入金請求顧客検索情報一覧)<BR>
     */
    public WEB3AdminTPPaymentRequisitionListUnit[] paymentRequisitionListUnit = null;

    /**
     * @@roseuid 48EC7033025A
     */
    public WEB3AdminTPPaymentRequisitionListResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminTPPaymentRequisitionListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
