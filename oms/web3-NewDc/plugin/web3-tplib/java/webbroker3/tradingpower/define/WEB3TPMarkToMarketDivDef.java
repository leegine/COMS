head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarkToMarketDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「値洗い区分」の定数定義クラス(WEB3TPMarkToMarketDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/01/27 齋藤　@栄三(FLJ) 新規作成
                    2006/11/14 徐大方　@仕様変更モデル072
 */
package webbroker3.tradingpower.define;

/**
 * 「値洗い区分」の定数定義クラス<br>
 *
 * @@author Eizo Saito
 * @@version 1.0
 */
public interface WEB3TPMarkToMarketDivDef
{

    /**
     * 通常
     */
    public static final String NORMAL = "0";

    /**
     * 前場引け値洗い
     */
    public static final String MORNING_CLOSED = "1";

    /**
     * DB時価値洗い
     */
    public static final String DB_QUOTE = "2";
}
@
