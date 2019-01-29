head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentApplyTriggerDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3PaymentApplyTriggerDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 王蘭芬(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 証券会社テーブルの出金申込トリガ発行　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3PaymentApplyTriggerDef
{
    /**
     * 0：タイマー実施（余力チェックなし）　@
     */
    public static final String ENFORCEMENT_NOTCHECK = "0";

    /**
     * 1：タイマー実施（余力チェックあり）
     */
    public static final String ENFORCEMENT_CHECK = "1";    

    /**
     * 2：リアルタイム実施　@　@
     */
    public static final String REAL_ENFORCEMENT = "2";    
}
@
