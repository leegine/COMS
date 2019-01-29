head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ImportantConnectionMailFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 重要連絡メール送信フラグ定数定義インタフェイス(WEB3ImportantConnectionMailFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客マスターの重要連絡メール送信フラグ 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ImportantConnectionMailFlagDef
{
    /**
     * 1：基本メールアドレス
     */
    public final static String BASE_MAIL_ADDRESS = "1";

    /**
     * 2：メールアドレス２
     */
    public final static String MAIL_ADDRESS_2 = "2";

    /**
     * 3：メールアドレス３
     */
    public final static String MAIL_ADDRESS_3 = "3";

    /**
     * 4：全てのメールアドレス
     */
    public final static String ALL_MAIL_ADDRESS = "4";
}
@
