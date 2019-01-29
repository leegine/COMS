head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IfodepositNonCalcSqProductDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金SQ日銘柄ポジション非計上定数定義インタフェイス(WEB3IfodepositNonCalcSqProductDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/16 栄イ(中訊) 新規作成 共通仕様変更管理台帳・ＤＢレイアウトNo577
*/
package webbroker3.common.define;

/**
 * 証拠金SQ日銘柄ポジション非計上 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3IfodepositNonCalcSqProductDef
{
    /**
     * 0：計上する
     */
    public final static String DEFAULT = "0";

    /**
     * 1：計上しない
     */
    public final static String NON_CALC = "1";
}
@
