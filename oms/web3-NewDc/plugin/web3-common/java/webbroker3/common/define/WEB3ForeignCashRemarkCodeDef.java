head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForeignCashRemarkCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 摘要コード定数定義インタフェイス(WEB3ForeignCashRemarkCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/01 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 外貨入出金伝票キューテーブルの摘要コード 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ForeignCashRemarkCodeDef
{
    /**
     * 07:摘要入力
     */
    public final static String REMARK_INPUT = "07";

    /**
     * 01：銀行送金
     */
    public final static String BANK_REMITTANCE = "01";
}
@
