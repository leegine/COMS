head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdditionalDepositStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追証未入金区分定数定義インタフェイス(WEB3AdditionalDepositStopDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/01 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客余力条件テーブルの追証未入金区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3AdditionalDepositStopDef
{
    /**
     * 0:追証未入金なし
     */
    public final static String ADDITIONAL_DEPOSIT_NOT_STOP = "0";

    /**
     * 1:追証未入金あり
     */
    public final static String ADDITIONAL_DEPOSIT_STOP = "1";
}
@
