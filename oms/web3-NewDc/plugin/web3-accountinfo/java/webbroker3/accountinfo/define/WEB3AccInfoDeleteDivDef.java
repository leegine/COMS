head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoDeleteDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 削除区分定数定義インタフェイス(WEB3AccInfoDeleteDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/16 黄建 (中訊) 新規作成
*/

package webbroker3.accountinfo.define;

/**
 * 削除区分 定数定義インタフェイス
 *
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AccInfoDeleteDivDef
{
    /**
     * 0：メールアドレス、案内メール送信フラグ更新
     */
    public final static String INFORMATION_MAIL_FLAG_UPDATE = "0";

    /**
     * 1：メールアドレス、案内メール送信フラグ削除
     */
    public final static String INFORMATION_MAIL_FLAG_DELETE = "1";
}
@
