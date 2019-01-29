head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookPriceRegistResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式簿価単価登録レスポンス(WEB3EquityBookPriceRegistResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * （現物株式簿価単価登録レスポンス）。<BR>
 * <BR>
 * 現物株式簿価単価登録レスポンスクラス<BR>
 */
public class WEB3EquityBookPriceRegistResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_book_price_regist";
    
    /**
     * @@roseuid 4206CF2F0144
     */
    public WEB3EquityBookPriceRegistResponse() 
    {
     
    }
    
    public WEB3EquityBookPriceRegistResponse(WEB3EquityBookPriceRegistRequest l_request)
    {
        super(l_request);
    }
}
@
