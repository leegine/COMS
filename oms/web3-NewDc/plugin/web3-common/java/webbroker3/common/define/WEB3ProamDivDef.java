head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProamDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プロ・アマ区分定数定義インタフェイス(WEB3ProamDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/25 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * プロ・アマ区分定数定義インタフェイス<BR>
 * （顧客登録伝票(G11)キューテーブルのプロ・アマ区分の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3ProamDivDef
{
    /**
     * 0：アマ
     */
    public static final String AM = "0";
}
@
