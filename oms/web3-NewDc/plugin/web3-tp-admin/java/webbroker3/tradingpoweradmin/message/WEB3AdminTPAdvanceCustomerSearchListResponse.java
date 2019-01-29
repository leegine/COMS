head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPAdvanceCustomerSearchListResponse.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/02/08 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金維持率割れ/立替金発生顧客検索一覧画面表示レスポンス
 */
public class WEB3AdminTPAdvanceCustomerSearchListResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_search_list";
       
    /**
     * (値洗い終了区分)
     */
    public String[] markToMarketEndDiv;
    
    /**
     * (立替金顧客一覧)
     */
    public WEB3AdminTPAdvanceCustomerUnit[] adminAdvanceCustomerUnits;
    
    /**
     * (総ページ数)
     */
    public String totalPages;
    
    /**
     * (総レコード数)
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)
     */
    public String pageIndex;
        
    /**
     * (コンストラクタ)
     */
    public WEB3AdminTPAdvanceCustomerSearchListResponse()
    {
    }

}
@
