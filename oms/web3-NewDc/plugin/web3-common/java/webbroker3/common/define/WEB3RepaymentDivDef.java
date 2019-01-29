head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RepaymentDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 弁済区分 定数定義インタフェイス(WEB3RepaymentDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/27 孟 東(中訊) 新規作成
Revesion History : 2004/09/13 孟 東(中訊) DEFAULTを削除
Revesion History : 2004/09/13 孟 東(中訊) 定数名を変更
*/
package webbroker3.common.define;

/**
 * 弁済区分 定数定義インタフェイス
 *                                                                      
 * @@author 孟 東
 * @@version 1.0
 */
public interface WEB3RepaymentDivDef
{    
    
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
