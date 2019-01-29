head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「処理区分」の定数定義クラス(WEB3TPStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/02 kikuchi(SCS) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * 「処理区分」の定数定義クラス<br>
 *
 * @@author kikuchi
 * @@version 1.0
 */
public interface WEB3TPStatusDef
{

    /**
     * 未処理
     */
    public static final String NOT_DEAL = "0";

    /**
     * 処理済
     */
    public static final String DEAL = "1";

    /**
     * プログラムエラー
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * データエラー
     */
    public static final String DATA_ERROR = "9";

}
@
