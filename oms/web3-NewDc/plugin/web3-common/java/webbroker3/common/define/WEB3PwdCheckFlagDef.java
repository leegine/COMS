head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PwdCheckFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : パスワードチェック有無フラグ定数定義クラス(WEB3PwdCheckFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/05 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * パスワードチェック有無フラグ定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3PwdCheckFlagDef
{
    /**
     * チェックなし
     */
    public static final String NO_CHECK = "0";
    
    /**
     * チェックあり
     */
    public static final String CHECK = "1";
    
    /**
     * 初期パスワードチェックあり
     */
    public static final String INIT_PWD_CHECK = "2";
}
@
