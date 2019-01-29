head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPExecTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「約定区分」の定数定義クラス(WEB3ExecTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * 「約定区分」の定数定義クラス<br>
 *
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3TPExecTypeDef
{

    /**
     * 未約定
     */
    public static final String UNEXECUTED = "1";

    /**
     * 約定済
     */
    public static final String EXECUTED = "2";

}
@
