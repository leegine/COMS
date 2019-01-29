head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定状態区分 定数定義インタフェイス(WEB3EquityExecStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/27 戴義波(中訊) 作成
*/

package webbroker3.equity.define;

/**
 *約定状態区分　@定数定義インタフェイス
 *                                                                     
 * @@author 戴義波
 * @@version 1.0
 */
public interface WEB3EquityExecStatusTypeDef
{
    /**
     * 約定状態区分:
     * 0:未約定
     */
    public final static String EXEC_TYPE_NOT_PROMISE = "0";
     
    /**
     * 約定状態区分:
     * 1:一部成立
     */
    public final static String EXEC_TYPE_ONE_COMPLETE = "1";
    
    /**
     * 約定状態区分:
     * 2:全部成立
     */
    public final static String EXEC_TYPE_ALL_COMPLETE = "2";
    
    /**
     * 約定状態区分:
     * 3:失効
     */
    public final static String EXEC_TYPE_LAPSE = "3"; 
    
    /**
     * 約定状態区分:
     * 4:一部失効
     */
    public final static String EXEC_TYPE_ONE_LAPSE = "4"; 
    
    /**
     * 約定状態区分:
     * 5:約定取消
     */
    public final static String EXEC_TYPE_PROMISE_CANCEL = "5";    

}
@
