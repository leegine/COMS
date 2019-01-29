head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoTransactionStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO処理状態(WEB3IpoTransactionStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/26 呉　@鈞(中訊) 新規作成
*/

package webbroker3.ipo.define;

/**
 * IPO処理状態<BR>
 * @@author 呉　@鈞
 * @@version 1.0
 */
public class WEB3IpoTransactionStatusDef
{
    /**
     * 9：異常終了
     */
    public static final String ERROR = "9";
    
    /**
     * 2：確定完了
     */
    public static final String CONFIRM_COMPLETED = "2";
    

    /**
     * 1：確定処理中
     */
    public static final String DEALING = "1";
}
@
