head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenUpdateStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 更新後状態 定数定義インタフェイス（WEB3AccOpenUpdateStatusDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.164
*/
package webbroker3.accountopen.define;

/**
 * 更新後状態 定数定義インタフェイス
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccOpenUpdateStatusDef
{
    /**
     * 1：無効（削除）/印刷済/受領済
     */
    public static final String DELETE = "1";

    /**
     * 0：有効/印刷可/未受領
     */
    public static final String DEFAULT = "0";
}
@
