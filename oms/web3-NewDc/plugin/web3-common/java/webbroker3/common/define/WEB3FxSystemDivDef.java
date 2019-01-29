head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FxSystemDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 会社別FXシステム条件テーブル・FXシステム区分定数定義インタフェイス(WEB3FxSystemDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/27 凌建平 (中訊) 新規作成
Revesion History : 2006/07/13 栄イ 【入出金】仕様変更管理台帳・ＤＢレイアウト097を対応
Revesion History : 2008/9/24 陸文静 (中訊) 【入出金】仕様変更管理台帳 ＤＢレイアウトNo.151
*/

package webbroker3.common.define;

/**
 * FXシステム区分 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3FxSystemDivDef
{
    /**
     * 1：先OPシステム
     */
    public final static String FUOP_SYSTEM  = "1";

    /**
     * 2：CFDシステム
     */
    public final static String CFD_SYSTEM  = "2";
}@
