head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountinfoMultiMailaddressFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報：複数メールアドレスフラグ定数定義インタフェイス(WEB3AccountinfoMultiMailaddressFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 栄イ(中訊) 新規作成
Revision History : 2010/02/23 趙林鵬(中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.706
*/
package webbroker3.common.define;

/**
 * お客様情報：複数メールアドレスフラグ 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3AccountinfoMultiMailaddressFlagDef
{
    /**
     * 0：複数メールアドレス未対応会社
     */
    public final static String DEFAULT = "0";

    /**
     * 1：複数メールアドレス対応会社
     */
    public final static String EXECUTE_T0 = "1";

    /**
     * 2：複数メールアドレス対応会社
     */
    public final static String EXECUTE_T1 = "2";
}
@
