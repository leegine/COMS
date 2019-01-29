head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginRejectIpStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン拒否IPのステータス定数定義インタフェイス(WEB3LoginRejectIpStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 栄イ(中訊) 新規作成
Revision History : 2008/9/24 陸文静 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.646
*/
package webbroker3.common.define;

/**
 * ログイン拒否IPのステータス 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3LoginRejectIpStatusDef
{
    /**
     * 0:対象
     */
    public final static String OBJECT = "0";

    /**
     * 1：無効
     */
    public final static String INEFFECTIVE = "1";

    /**
     * 2：対象外
     */
    public final static String EXCEPT_OBJECT = "2";
}
@
