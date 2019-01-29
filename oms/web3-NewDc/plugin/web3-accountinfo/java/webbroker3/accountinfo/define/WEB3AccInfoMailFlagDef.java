head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.27.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 案内メール送信フラグ定数定義インタフェイス(WEB3AccInfoMailFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/16 黄建 (中訊) 新規作成
*/

package webbroker3.accountinfo.define;

/**
 * 案内メール送信フラグ 定数定義インタフェイス
 *
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AccInfoMailFlagDef
{
    /**
     * 0：FALSE/送信不要（DEFAULT）
     */
    public final static String FALSE = "0";

    /**
     * 1：TRUE/送信要
     */
    public final static String TRUE= "1";
}
@
