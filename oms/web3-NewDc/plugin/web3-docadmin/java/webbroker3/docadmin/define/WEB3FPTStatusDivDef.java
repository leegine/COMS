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
filename	WEB3FPTStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分 定数定義インタフェイス(WEB3FPTStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.013
*/
package webbroker3.docadmin.define;

/**
 * (処理区分 定数定義インタフェイス)<BR>
 * 処理区分 定数定義インタフェイス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3FPTStatusDivDef
{
    /**
     * 0：登録アップロード処理
     */
    public static final String LOGIN = "0";

    /**
     * 1：削除アップロード処理
     */
    public static final String DELETE = "1";
}
@
