head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PrintFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 印刷フラグ定数定義インタフェイス(WEB3PrintFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/14 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 印刷フラグ定数定義インタフェイス<BR>
 * (口座開設見込客テーブルの印刷フラグの參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3PrintFlagDef
{
    /**
     *  0：印刷可<BR>
     */
    public static final String ENABLE_PRINT = "0";

    /**
     * 1：印刷済<BR>
     */
    public static final String PRINT_COMPLETE = "1";

    /**
     * 3：未処理（DEFAULT）<BR>
     */
    public static final String DEFAULT = "3";
}@
