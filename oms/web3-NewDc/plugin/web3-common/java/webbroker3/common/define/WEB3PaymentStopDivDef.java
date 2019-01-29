head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金停止区分定数定義インタフェイス(WEB3PaymentStopDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/13 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 担保不足顧客データテーブルの出金停止区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3PaymentStopDivDef
{
    /**
     * 1：全額
     */
    public static final String FULL = "1";

    /**
     * 2：一部
     */
    public static final String PART = "2";
}
@
