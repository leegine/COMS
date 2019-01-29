head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentDateDetDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金日設定区分 定数定義インタフェイス(WEB3PaymentDateDetDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 栄イ (中訊) 新規作成
Revesion History : 2009/07/28 趙林鵬 (中訊)【債券】仕様変更管理台帳.ＤＢレイアウトNo.037
*/

package webbroker3.common.define;

/**
 * 債券部店別条件テーブルの入金日設定区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3PaymentDateDetDivDef
{
    /**
     * 0：デフォルト
     */
    public static final String DEFAULT = "0";

    /**
     * 1：約定日基準
     */
    public static final String EXECUTE_DATE_BASE = "1";

    /**
     * 2:登録日基準
     */
    public static final String REGIST_DATE_BASE = "2";
}
@
