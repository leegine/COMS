head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : リクエスト(WEB3QuotePriceRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/06 劉 (FLJ)新規作成
 */

package webbroker3.quoteprice.message;

import webbroker3.common.message.*;
import webbroker3.util.*;

/**
 * （リクエスト）。<br>
 * <br>
 * リクエスト
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3QuotePriceRequest
    extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePriceRequest.class);

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "request"; 
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "quote_price_save";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602060000L;
    
    /**
     * Quote Table name<BR>
     */
    public String quote_table;

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3QuotePriceRequest()
    {
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3QuotePriceResponse();
    }
}
@
