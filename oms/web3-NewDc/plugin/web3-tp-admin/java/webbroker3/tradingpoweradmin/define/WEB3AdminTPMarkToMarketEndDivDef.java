head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPMarkToMarketEndDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 値洗い終了区分定義インターフェース(WEB3AdminTPMarkToMarketEndDiv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/25 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3TPAdminProductCodeDefインターフェース
 * 値洗い終了区分を定義する。
 * @@author asano(FLJ)
 * @@version 1.0
 */
public interface WEB3AdminTPMarkToMarketEndDivDef
{

    /**
     * 大引け値洗い
     */
    public static final String NORMAL = "0";

    /**
     * 前場引け値洗い
     */
    public static final String MORNING_CLOSED = "1";

}
@
