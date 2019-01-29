head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RealnameUpdateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報顧客正式名称更新定数定義インタフェイス(WEB3RealnameUpdateDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/19 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * お客様情報顧客正式名称更新定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3RealnameUpdateDef
{
    /**
     * 0:顧客正式名称の更新を実行しない。
     */
    public static final String DEFAULT = "0";

    /**
     * 1:顧客正式名称の更新を実行する。
     */
    public static final String EXECUTE = "1";
}
@
