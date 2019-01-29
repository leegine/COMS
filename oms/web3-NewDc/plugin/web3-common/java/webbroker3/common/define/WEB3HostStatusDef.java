head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3HostStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブル処理区分 定数定義インタフェイス(WEB3HostStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * キューテーブル処理区分　@定数定義インタフェイス<BR>
 *
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3HostStatusDef
{
    /**
     * 0：未処理<BR>
     */
    public static final String NOT_STARTED_PROCESS = "0";

    /**
     * 1：処理済み<BR>
     */
    public static final String COMPLETE_PROCESS = "1";

    /**
     * 2：処理中<BR>
     */
    public static final String STARTED_PROCESS = "2";

    /**
     * 8：プログラムエラー<BR>
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * 9：データエラー<BR>
     */
    public static final String DATA_ERROR = "9";
}
@
