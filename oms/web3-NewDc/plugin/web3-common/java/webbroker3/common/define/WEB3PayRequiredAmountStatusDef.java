head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PayRequiredAmountStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分定数定義インタフェイス(WEB3PayRequiredAmountStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/13 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 返済必要額データテーブルの処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3PayRequiredAmountStatusDef
{
    /**
     * 1:正常終了
     */
    public static final String NORMAL_END = "1";

    /**
     * 2:現物顧客以外
     */
    public static final String EQTYPE_COSTOMER_EXCEPT = "2";

    /**
     * 3:証券担保ローン未開設
     */
    public static final String SECURITIES_WARRANTY_LOAN_NOT_OPEN = "3";

    /**
     * 9:エラー
     */
    public static final String ERROR = "9";
}
@
