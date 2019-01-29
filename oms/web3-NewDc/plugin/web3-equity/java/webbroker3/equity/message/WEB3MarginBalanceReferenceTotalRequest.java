head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高合計リクエスト(WEB3MarginBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * （信用取引残高照会残高合計リクエスト）。<BR>
 * <BR>
 * 信用取引残高照会残高合計リクエストクラス<BR>
 */
public class WEB3MarginBalanceReferenceTotalRequest extends WEB3GenRequest 
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_balance_reference_total";

    /**
     * @@roseuid 4206CDBA0367<BR>
     */
    public WEB3MarginBalanceReferenceTotalRequest() 
    {
     
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3MarginBalanceReferenceTotalResponse(this);
    }
}
@
