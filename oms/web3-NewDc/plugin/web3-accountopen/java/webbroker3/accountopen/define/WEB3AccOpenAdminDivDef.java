head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAdminDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者区分 定数定義インタフェイス(WEB3AccOpenAdminDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2000/09/02 張騰宇 (中訊) 新規作成 モデル204
*/

package webbroker3.accountopen.define;

/**
 * 管理者区分 定数定義インタフェイス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AccOpenAdminDivDef
{
    /**
     * 0：管理者、扱者以外<BR>
     */
    public static final String NOT_ADMIN = "0";

    /**
     * 1：管理者、扱者<BR>
     */
    public static final String ADMIN = "1";
}
@
