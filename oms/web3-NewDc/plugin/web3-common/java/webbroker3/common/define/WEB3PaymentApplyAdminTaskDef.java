head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentApplyAdminTaskDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3PaymentApplyAdminTaskDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 王蘭芬(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 証券会社テーブルの出金申込管理者タスク　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3PaymentApplyAdminTaskDef
{
    /**
     * 0：一覧表示のみ　@
     */
    public static final String ONLY_LIST = "0";

    /**
     * 1：出金実施　@
     */
    public static final String OUT_CASH_ENFORCEMENT = "1";    

    /**
     * 2：取消実施　@
     */
    public static final String CANCEL_ENFORCEMENT = "2";    

    /**
     * 3：両方実施
     */
    public static final String BOTH_ENFORCEMENT = "3";    
}

@
