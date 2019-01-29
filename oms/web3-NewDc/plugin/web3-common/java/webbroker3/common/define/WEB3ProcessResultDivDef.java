head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProcessResultDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理結果区分定数定義インタフェイス(WEB3ProcessResultDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/04 趙林鵬(中訊) 新規作成
Revision History : 2009/02/12 趙林鵬(中訊)【株管理者】仕様変更管理台帳ＤＢレイアウトNo.027
*/

package webbroker3.common.define;

/**
 * 処理結果区分定数定義インタフェイス<BR>
 * (注意情報履歴テーブルの処理結果区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3ProcessResultDivDef
{
    /**
     * 1：正常<BR>
     */
    public final static String NORMAL = "1";

    /**
     * 2：正常（更新無）<BR>
     */
    public final static String NORMAL_NOT_UPDATE = "2";

    /**
     * 9：エラー<BR>
     */
    public final static String ERROR = "9";
}
    @
