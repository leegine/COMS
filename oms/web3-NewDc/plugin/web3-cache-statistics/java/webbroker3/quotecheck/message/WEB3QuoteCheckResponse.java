head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : レスポンスクラス(WEB3QuoteCheckResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 許 (FLJ)新規作成
 */

package webbroker3.quotecheck.message;

import webbroker3.common.message.*;

/**
 * （レスポンスクラススクラス）。<br>
 * <br>
 * レスポンスクラス
 * @@author 許 (FLJ)
 * @@version 1.0
 */
public class WEB3QuoteCheckResponse
    extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "quote_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902030000L;
    
    /**
     * 件数<BR>
     */
    public int count;

    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3QuoteCheckResponse()
    {
    }

}
@
