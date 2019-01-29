head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderModifyCancelTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文訂正・取消区分(WEB3IfoOrderModifyCancelTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  王暁傑 (中訊) 新規作成
*/
package webbroker3.ifo.define;

/**
 * 注文訂正・取消区分
 *                                                                     
 * @@author wang-xiaojie
 * @@version 1.0
 */
public interface WEB3IfoOrderModifyCancelTypeDef
{
    /**
     * 4:取消失敗
     */
    public static final String CANCEL_FAILED = "4";

    /**
     * 5:訂正中
     */
    public static final String ORDER_REVISING = "5";
    
    /**
     * 6:一部訂正完了
     */
    public static final String ORDER_REVISED_PARTLY = "6";
    
    /**
     * 7:訂正完了
     */
    public static final String ORDER_REVISED_ALL = "7"; 
    
    /**
     * 8:訂正失敗
     */
    public static final String CHANGE_FAILED = "8"; 
       
    /**
     * 0000:正常
     */
    public static final String ERROR_STATUS_NORMAL = "0000";       
}
@
