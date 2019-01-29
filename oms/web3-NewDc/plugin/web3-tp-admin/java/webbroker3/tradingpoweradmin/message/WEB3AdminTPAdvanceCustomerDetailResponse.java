head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPAdvanceCustomerDetailResponse.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金維持率割れ/立替金発生顧客詳細画面表示レスポンス
 */
public class WEB3AdminTPAdvanceCustomerDetailResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_detail";

    /**
     * (立替金明細一覧)
     */
    public WEB3AdminTPAdvanceDetailUnit[] advanceCustomerDetailUnits;
        
    /**
     * コンストラクタ
     */
    public WEB3AdminTPAdvanceCustomerDetailResponse()
    {
    }

}
@
