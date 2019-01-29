head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EditWayDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 項目編集方法@(WEB3EditWayDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
Revision History : 2009/08/28 趙林鵬 (中訊)【口座開設】仕様変更管理台帳ＤＢレイアウトNo.061
*/

package webbroker3.common.define;

/**
 * 項目編集方法@ 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3EditWayDivDef
{

    /**
     * 0：固定値
     */
    public final static String FIXED_VALUE  = "0";

    /**
     * １：口座開設見込客テーブルの項目より編集
     */
    public final static String EXP_ACCOUNT_OPEN_ITEM  = "1";

    /**
     * 2：口座開設見込客テーブルの項目を半角ｶﾅに変換して編集
     */
    public final static String EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA  = "2";

    /**
     * 3：和暦日付(入力項目物理名1(年号)、入力項目物理名2(年月日))を西暦日付に変換（yyyymmdd）
     */
    public final static String WEST_DATE_CHANGE_TO_JAP_DATE = "3";

}@
