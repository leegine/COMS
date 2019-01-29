head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeRepaymentDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 弁済区分定義インタフェイス(WEB3GentradeRepaymentDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/13 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade.define;

/**
 * 弁済区分定義インタフェイス
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3GentradeRepaymentDivDef
{
    /**
     * 0 : 指定なし 
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1 : 制度信用
     */
    public static final String REPAYMENT_DIV_MARGIN_SYS = "1";

    /**
     * 2 : 一般信用
     */
    public static final String REPAYMENT_DIV_MARGIN_GEN = "2";

}
@
