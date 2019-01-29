head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCancelEnableFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取消可能フラグ 定数定義インタフェイス(WEB3AioCancelEnableFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 何文敏 (中訊) 新規作成 仕様変更・モデルNo.756
*/
package webbroker3.aio.define;

/**
 * 取消可能フラグ 定数定義インタフェイス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public interface WEB3AioCancelEnableFlagDef
{
    /**
     * 0：取消不可
     */
    public static String CANCEL_NOT = "0";

    /**
     * 1：取消可能
     */
    public static String CANCEL_POSSIBLE = "1";

    /**
     * 2：送信済
     */
    public static String SENDED = "2";

    /**
     * 3：取消済
     */
    public static String CANCElED = "3";

    /**
     * 4：送信エラー
     */
    public static String ERROR = "4";
}
@
