head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DirAdminFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ＤＩＲ管理者フラグ定数定義インタフェイス(WEB3DirAdminFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * ＤＩＲ管理者フラグ 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3DirAdminFlagDef
{
    /**
     * 0：通常管理者：DEFAULT
     */
    public static final String NORMAL_ADMINISTRATOR = "0";

    /**
     * 1 : DIR管理者
     */
    public static final String DIR_ADMINISTRATOR = "1";

    /**
     * 2：通常管理者(申請者)
     */
    public static final String NORMAL_ADMINISTRATOR_APPLICATIONER = "2";

    /**
     * 3：通常管理者（承認者）
     */
    public static final String NORMAL_ADMINISTRATOR_APPROVALER = "3";

}
@
