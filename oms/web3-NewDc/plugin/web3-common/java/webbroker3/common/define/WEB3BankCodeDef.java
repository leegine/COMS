head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BankCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付通知区分  定数定義インタフェイス(WEB3BankCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09　@王蘭芬（中訊）新規作成
*/
package webbroker3.common.define;

/**
 * 振込先金融機@関テーブルの的銀行コード 定数定義インタフェイス
 *
 * @@author 王蘭芬（中訊）
 * @@version 1.0
 */
public interface WEB3BankCodeDef {

    /**
     *  "9900"（郵貯） 
     */
    public static final String  POSTAL_SAVINGS = "9900";

}
@
