head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OnlineServiceDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  オンラインサービス区分　@定数定義インタフェイス(WEB3OnlineServiceDiv)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/05/09 鄒政 (中訊) 新規作成
Revesion History : 2006/03/16 凌建平 (中訊) 共通仕様変更管理台帳(ＤＢレイアウト)No.352を対応
Revesion History : 2006/09/20 栄イ (中訊) 共通仕様変更管理台帳(ＤＢレイアウト)No.415を対応
Revesion History : 2007/04/23 栄イ (中訊) 共通仕様変更管理台帳(ＤＢレイアウト)No.479、480を対応
Revesion History : 2007/06/22 栄イ (中訊) 共通仕様変更管理台帳(ＤＢレイアウト)No.511を対応
Revesion History : 2007/11/21 栄イ (中訊) 共通仕様変更管理台帳(ＤＢレイアウト)No.567を対応
Revesion History : 2008/01/23 栄イ (中訊) 共通仕様変更管理台帳(ＤＢレイアウト)No.589を対応
Revesion History : 2008/01/28 栄イ (中訊) 共通仕様変更管理台帳(ＤＢレイアウト)No.594を対応
*/
package webbroker3.common.define;

/**
 * オンライン実行結果テーブルのオンラインサービス区分　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3OnlineServiceDiv
{
    /**
     * 1：出来終了通知 
     */
    public static final String ORDER_EXEC_END = "1";
    
    /**
     * 2：注文繰越
     */
    public static final String ORDER_CARRY_OVER = "2";
    
    /**
     * 3：手動失効
     */
    public static final String MANUAL_EXPIRE = "3";

    /**
     * 4：自動約定
     */
    public static final String AUTO_EXECUTE = "4";

    /**
     * 5：強制決済（オンライン開始前）
     */
    public static final String FORCED_SETTLE_BEFORE_ONLINE = "5";

    /**
     * 6：強制決済（承認）
     */
    public static final String FORCED_SETTLE_APPROVAL = "6";

    /**
     * 7：強制決済（非承認）
     */
    public static final String FORCED_SETTLE_UNAPPROVAL = "7";

    /**
     * 8：夕場前注文繰越
     */
    public static final String BEFORE_EVENING_SESSION_ORDER_CARRY_OVER = "8";

    /**
     * 9：夕場前出来終了
     */
    public static final String BEFORE_EVENING_SESSION_ORDER_EXEC_END = "9";

    /**
     * A：(PTS)出来終了通知
     */
    public static final String PTS_ORDER_EXEC_END = "A";

    /**
     * B：強制決済（場間）
     */
    public static final String FORCED_SETTLE_MARKET = "B";
}
@
