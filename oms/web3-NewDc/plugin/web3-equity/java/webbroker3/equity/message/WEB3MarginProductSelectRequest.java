head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建注文銘柄選択リクエスト(WEB3MarginProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （信用取引新規建注文銘柄選択リクエスト）。<br>
 * <br>
 * 信用取引新規建注文銘柄選択リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginProductSelectRequest extends WEB3GenRequest 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_productSelect";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * @@roseuid 4140477E0291
     */
    public WEB3MarginProductSelectRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140477E029B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginProductSelectResponse(this);
    }
}
@
