head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradeauditCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 自己委託区分定数定義インタフェイス(WEB3TradeauditCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 自己委託区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3TradeauditCodeDef
{
    /**
     * 0：委託
     */
    public static final String COMMISSION = "0";

    /**
     * 9：自己
     */
    public static final String SELF = "9";
}
@
