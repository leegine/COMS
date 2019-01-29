head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InputOutputActionDeleveryMethodDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの受渡方法@インタフェイス(WEB3InputOutputActionDeleveryMethodDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの受渡方法@インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3InputOutputActionDeleveryMethodDivDef 
{

    /**
     * 1（店頭精算）
     */
    public static final String BRANCH_CALCULATE = "1";
    
    /**
     * 2（郵送精算）
     */
    public static final String SEND_CALCULATE = "2";

    /**
     * 3（先発精算）
     */
    public static final String FIRST_CALCULATE = "3";

    /**
     * 9（他店精算）
     */
    public static final String OTHER_CALCULATE = "9";

    /**
     * 0（約定（売））
     */
    public static final String EXEC_SELL = "0";

    /**
     * ブランク（約定（買））
     */
    public static final String EXEC_BUY = " ";
}
@
