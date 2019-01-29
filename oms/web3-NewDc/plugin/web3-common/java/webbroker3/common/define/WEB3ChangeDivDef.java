head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ChangeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 変更区分定数定義インタフェイス(WEB3ChangeDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/09 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 変更区分定数定義インタフェイス<BR>
 * （定時定額買付条件変更テーブルの変更区分の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3ChangeDivDef
{
    /**
     * 1:追加
     */
    public static final String ADD = "1";

    /**
     * 2:変更
     */
    public static final String CHANGE = "2";

    /**
     * 3:解除
     */
    public static final String RELEASE = "3";

    /**
     * 4:一時停止
     */
    public static final String TEMP_STOP = "4";
}@
