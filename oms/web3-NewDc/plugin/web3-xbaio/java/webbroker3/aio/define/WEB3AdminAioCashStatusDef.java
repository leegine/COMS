head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ステータス定数定義(WEB3AdminAioCashStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 何文敏 (中訊) 新規作成　@仕様変更モデル NO.693
*/
package webbroker3.aio.define;

/**
 * ステータス定数定義
 * 
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminAioCashStatusDef
{

    /**
     * ０：全て
     */
    public static final String ALL = "0";

    /**
     * １：完了
     */
    public static final String COMPLETE = "1";

    /**
     * ２：未処理
     */
    public static final String NOT_TRANSACTION = "2";

    /**
     * ９：エラー
     */
    public static final String ERROR = "9";
}
@
