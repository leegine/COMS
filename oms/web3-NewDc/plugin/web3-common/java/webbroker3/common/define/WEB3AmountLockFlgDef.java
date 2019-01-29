head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AmountLockFlgDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金額ロックフラグ定数定義インタフェイス(WEB3AmountLockFlgDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 担保ローン出金拘束金テーブルの金額ロックフラグ 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3AmountLockFlgDef
{
    /**
     * 0：通常
     */
    public final static String NORMAL  = "0";

    /**
     * 1：金額ロック中
     */
    public final static String AMOUNT_LOCKING  = "1";
}
@
