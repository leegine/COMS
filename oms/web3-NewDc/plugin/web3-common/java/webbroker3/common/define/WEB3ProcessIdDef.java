head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProcessIdDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : プロセスＩＤ定数定義インタフェイス(WEB3ProcessIdDef.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2009/03/04 趙林鵬(中訊) 新規作成
Revision History : 2009/09/17 趙林鵬(中訊)【共通】仕様変更管理台帳ＤＢレイアウトNo.697
Revision History : 2010/07/16 劉レイ(中訊)【共通】仕様変更管理台帳ＤＢレイアウトNo.711
Revision History : 2010/09/10 張騰宇(中訊)【共通】仕様変更管理台帳ＤＢレイアウトNo.716
*/

package webbroker3.common.define;

/**
 * プロセスＩＤ定数定義インタフェイス<BR>
 * (プロセス管理テーブルのプロセスＩＤの參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3ProcessIdDef
{
    /**
     * 0001:証拠金不足確定
     */
    public final static String DEPOSIT_SHORTAGE_CONFIRM = "0001";

    /**
     * 0008:清算値速報受信
     */
    public final static String QUICK_REPORT_RECEIVED = "0008";

    /**
     * 0011：翌日基準値速報受信
     */
    public final static String NEXT_DAY_BASE_PRICE_QUICK_REPORT_REC = "0011";

    /**
     * 0012：株式終値速報受信
     */
    public final static String EQ_LAST_PRICE_QUICK_REPORT_REC = "0012";

    /**
     * 0013：先物ＯＰ基準値受信
     */
    public final static String IFO_BASE_PRICE_REC = "0013";

    /**
     * 0014:外国株式為替ネッティング処理完了
     */
    public final static String FEQ_NETTING_EXCHANGE_COMPLETE = "0014";
}
@
