head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CalcBaseFormDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算基準方式定数定義インタフェイス(WEB3CalcBaseFormDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 計算基準方式 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3CalcBaseFormDef
{
    /**
     * 1:金額優先方式
     */
    public final static String AMOUNT_PRIORITY_FORM = "1";

    /**
     * 2:単価優先方式
     */
    public final static String UNIT_PRICE_PRIORITY_FORM = "2";
}
@
