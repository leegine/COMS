head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderExecRouteDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定経路区分定義インタフェイス(WEB3FeqOrderExecRouteDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/06 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 約定経路区分定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqOrderExecRouteDivDef
{
    /**
     * 0：出来通知（Default）
     */
    public static final String EXEC_NOTIFY = "0";
    
    /**
     * 1：出来入力
     */
    public static final String EXEC_INPUT = "1";

    /**
     * 2：約定結果一括入力
     */
    public static final String EXECUTED_RESULT_UPLOAD = "2";
    
    /**
     * 3: 注文受付  
     */
    public static final String ORDER_ACCEPT = "3";
    
    /**
     * 4: 注文受付取消認証  
     */
    public static final String ORDER_ACCEPT_CANCEL_AUTHENTICATE = "4";
    
    /**
     * 5: 注文受付結果一括入力  
     */
    public static final String ORDER_ACCEPT_RESULT_UPLOAD = "5";

    /**
     * 9：約定入力
     */
    public static final String ORDER_AND_EXEC_INPUT = "9";
}
@
