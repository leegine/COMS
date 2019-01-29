head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : フロント発注処理区分定数定義インタフェイス(WEB3FrontOrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/21 孟東(中訊)　@新規作成
Revesion History : 2006/5/25 凌建平 (中訊) インターフェイス申請・No079
*/
package webbroker3.common.define;

/**
 * フロント発注処理区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3FrontOrderStatusDef
{
    /**
     * 0：未処理
     */
    public static final String NOT_DEAL = "0";

    /**
     * 1：送信済
     */
    public static final String SENDED = "1";

    /**
     * 2：AMG入力完了
     */
    public static final String AMG_INPUT_COMPLETE = "2";

    /**
     * 3：市場入力完了
     */
    public static final String MARKET_INPUT_COMPLETE = "3";

    /**
     * 6：AMG入力エラー
     */
    public static final String AMG_INPUT_ERROR = "6";

    /**
     * 7：市場入力エラー
     */
    public static final String MARKET_INPUT__ERROR = "7";

    /**
     * 8：市場受付確認済
     */
    public static final String MARKET_ACCEPT_CONFIRMED = "8";

    /**
     * 9：市場受付確認中
     */
    public static final String MARKET_ACCEPT_CONFIRMING = "9";

    /**
     * 4：管理者手動失効済
     */
    public static final String ADMIN_MANUAL_EXPIRED = "4";
}
@
