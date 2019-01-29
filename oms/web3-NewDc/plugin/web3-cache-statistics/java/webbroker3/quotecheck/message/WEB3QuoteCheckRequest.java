head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : リクエスト(WEB3QuoteCheckRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 許 (FLJ)新規作成
 */

package webbroker3.quotecheck.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （リクエスト）。<br>
 * <br>
 * リクエスト
 * @@author 許 (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteCheckRequest
    extends WEB3GenRequest
{

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "request"; 
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "quote_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902030000L;
    
    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3QuoteCheckRequest()
    {
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3QuoteCheckResponse();
    }
}
@
