head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.15.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3RichPushDataStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「処理区分」の定数定義クラス(WEB3RichPushDataStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */
package webbroker3.rcp.define;

/**
 * 「処理区分」の定数定義クラス<br>
 *
 * @@author　@劉(FLJ)
 * @@version 1.0
 */
public interface WEB3RichPushDataStatusDef
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
