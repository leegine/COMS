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
filename	WEB3IfoSettlementStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Ifo決済状態区分(WEB3IfoSettlementStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 王暁傑 (中訊) 新規作成
*/
package webbroker3.ifo.define;

/**
 * 決済状態区分
 *                                                                     
 * @@author 王暁傑
 * @@version 1.0
 */
public interface WEB3IfoSettlementStatusDef

{
    /**
     * -2：新規建約定取消
     */
    public static final int OPENCONTRACT_EXECUTE_CANCLE = -2;  

    /**
     * -1：前日以前決済済
     */
    public static final int BEFORE_YESTODAY_SETTLED = -1;
    
    /**
     * 0：決済済
     */
    public static final int SETTLED = 0;    
    
    /**
     * 1：未決済
     */
    public static final int UNSETTLED = 1;    
    
    /**
     * 2：決済中
     */
    public static final int SETTLING = 2;

    /**
     * 3：未決済と決済中
     */
    public static final int UNSETTLED_SETTLING = 3;

    /**
     * 4：決済済と未決済
     */
    public static final int SETTLED_UNSETTLED = 4;
    
    /**
     * 5：決済済と未決済と決済中
     */
    public static final int SETTLED_UNSETTLED_SETTLING = 5;
    
    /**
     * 6：決済済と決済中
     */
    public static final int SETTLED_SETTLING = 6;
}@
