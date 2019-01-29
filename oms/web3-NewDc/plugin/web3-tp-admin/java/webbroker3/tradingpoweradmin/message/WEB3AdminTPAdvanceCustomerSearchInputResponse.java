head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金維持率割れ/立替金発生顧客検索入力画面表示クラス(WEB3AdminTPAdvanceCustomerSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金維持率割れ/立替金発生顧客検索入力画面表示レスポンスクラス
 */
public class WEB3AdminTPAdvanceCustomerSearchInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "admintradingpower_advance_customer_search_input";
                                          
    /**
     * (値洗い終了区分)
     */
    public String[] markToMarketEndDiv;
   
}
@
