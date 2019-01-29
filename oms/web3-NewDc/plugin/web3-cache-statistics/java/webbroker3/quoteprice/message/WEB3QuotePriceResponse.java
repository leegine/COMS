head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : レスポンスクラス(WEB3QuotePriceResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/06 劉 (FLJ)新規作成
 */

package webbroker3.quoteprice.message;

import webbroker3.common.message.*;

/**
 * （レスポンスクラススクラス）。<br>
 * <br>
 * レスポンスクラス
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3QuotePriceResponse
    extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "quote_price_save";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602060000L;
    
    public String quote_table;

    /**
     * (処理結果)
     */
    public WEB3QuotePriceSaveResult result[];

    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3QuotePriceResponse()
    {
    }

}
@
