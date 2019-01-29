head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTProcessFlagDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 更新処理フラグ 定数定義インタフェイス(WEB3AdminFPTProcessFlagDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/05 馮海濤 (中訊) 新規作成 モデル No.037
*/
package webbroker3.docadmin.define;

/**
 * (更新処理フラグ 定数定義インタフェイス)<BR>
 * 更新処理フラグ 定数定義インタフェイス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public interface WEB3AdminFPTProcessFlagDivDef
{

    /**
     * 0：登録
     */
    public static final String INSERT = "0";

    /**
     * 1：更新
     */
    public static final String UPDATE = "1";

    /**
     * 2：削除
     */
    public static final String DELETE = "2";
}
@
