head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BuyingMinimumCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 買付最低金額チェック定数定義インタフェイス(WEB3BuyingMinimumCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/27 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 投資信託乗換先買付最低金額チェック方法@定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3BuyingMinimumCheckDef
{
    /**
     * 0：買付最低金額チェック不要
     */
    public static final String DEFAULT = "0";

    /**
     * 1：買付最低金額チェック要
     */
    public static final String AMOUNT_CHECK = "1";
}
@
