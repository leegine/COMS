head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTDocumentCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面チェック区分定数定義インタフェイス(WEB3AdminFPTDocumentCheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 何文敏 (中訊) 新規作成
*/
package webbroker3.docadmin.define;

/**
 * 管理者金商法@ソートキー定数定義インタフェイス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AdminFPTDocumentCheckDivDef
{
    /**
     * 1:IPO
     */
    public static String IPO = "1";

    /**
     *  2：投信
     */
    public static String MUTUAL_FUND = "2";

    /**
     *  3：FX
     */
    public static String FX = "3";

    /**
     * 4:金商法@
     */
    public static String FPT = "4";
}
@
