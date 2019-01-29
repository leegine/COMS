head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFFirstDisplayDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3MFFirstDisplayDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/12 安陽(中訊) 新規作成 モデル605
Revision History : 2008/08/01 武波(中訊) 仕様変更 モデル623
*/
package webbroker3.mf.define;

/**
 * 初回表示フラグ 定数定義インタフェイス
 * @@author 安陽
 * @@version 1.0
 */
public interface WEB3MFFirstDisplayDivDef
{
    /**
     * 表示する
     */
    public static final String DISPLAY = "0";

    /**
     * 表示しない
     */
    public static final String NO_DISPLAY = "1";

    /**
     * 2：閲覧チェック時
     */
    public static final String READING_CHECK = "2";
}
@
