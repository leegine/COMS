head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminMailDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者確認用メール区分定数定義インタフェイス(WEB3AdminMailDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 管理者確認用メール区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3AdminMailDivDef
{
    /**
     * Null：DEFAULT
     */
    public static final String DEFAULT = null;

    /**
     *  1：管理者確認用メール　@
     */
    public static final String ADMIN_MAIL = "1";
}
@
