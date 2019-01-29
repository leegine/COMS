head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金区分定数定義(WEB3AdminAioDevDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/07 周捷 (中訊) 新規作成　@仕様変更モデル NO.693
*/
package webbroker3.aio.define;

/**
 * 入出金区分定数定義
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAioDivDef
{
    /**
     * 0：　@入金
     */
    public static final String CASH_IN = "0";

    /**
     * 1：　@出金
     */
    public static final String CASH_OUT = "1";
}
@
