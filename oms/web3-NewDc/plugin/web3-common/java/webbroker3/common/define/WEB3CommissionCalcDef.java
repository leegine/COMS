head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommissionCalcDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託手数料計算方法@定数定義インタフェイス(WEB3CommissionCalcDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/27 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 投資信託手数料計算方法@定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3CommissionCalcDef
{
    /**
     * 0：手数料計算不要
     */
    public static final String DEFAULT = "0";

    /**
     * 1：手数料計算要
     */
    public static final String COMMISSION_CALC = "1";
}
@
