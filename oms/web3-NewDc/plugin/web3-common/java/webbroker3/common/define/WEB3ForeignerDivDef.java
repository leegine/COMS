head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForeignerDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国人区分定数定義インタフェイス(WEB3ForeignerDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/25 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 外国人区分定数定義インタフェイス<BR>
 * （顧客登録伝票(G11)キューテーブルの外国人区分の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3ForeignerDivDef
{
    /**
     * 0：外国人以外
     */
    public static final String EXCEPT_FOREIGNER = "0";
}
@
