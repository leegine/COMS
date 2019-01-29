head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.15.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3QtpRichPushOrderTypeNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QtpRichPushOrderTypeNameDefクラス(WEB3QtpRichPushOrderTypeNameDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */
package webbroker3.rcp.define;

/**
 * 「売買」の定数定義クラス<br>
 *
 * @@author　@孫(FLJ)
 * @@version 1.0
 */
public interface WEB3QtpRichPushOrderTypeNameDef
{
    public static final String EQUITY_BUY = "現物/買付";
    public static final String EQUITY_SELL = "現物/売却";
    public static final String MARGIN_LONG = "信用/買建";
    public static final String MARGIN_SHORT = "信用/売建";
    public static final String CLOSE_MARGIN_LONG = "信用/買建返済";
    public static final String CLOSE_MARGIN_SHORT = "信用/売建返済";
    public static final String SWAP_MARGIN_LONG = "信用/現引";
    public static final String SWAP_MARGIN_SHORT = "信用/現渡";
    public static final String IDX_FUTURES_BUY_TO_OPEN = "先物/買建";
    public static final String IDX_FUTURES_SELL_TO_OPEN = "先物/売建";
    public static final String IDX_FUTURES_BUY_TO_CLOSE = "先物/買建返済";
    public static final String IDX_FUTURES_SELL_TO_CLOSE = "先物/売建返済";
    public static final String IDX_OPTIONS_BUY_TO_OPEN = "OP/買建";
    public static final String IDX_OPTIONS_SELL_TO_OPEN = "OP/売建";
    public static final String IDX_OPTIONS_BUY_TO_CLOSE = "OP/買建返済";
    public static final String IDX_OPTIONS_SELL_TO_CLOSE = "OP/売建返済";

}
@
