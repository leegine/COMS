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
filename	WEB3IfoExecStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定状態区分 定数定義インタフェイス(WEB3IfoExecStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 凌建平(中訊) 作成
*/

package webbroker3.ifo.define;

/**
 * 約定状態区分　@定数定義インタフェイス
 *                                                                     
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3IfoExecStatusTypeDef
{
    /**
     * 0:未約定
     */
    public final static String EXEC_TYPE_NOT_PROMISE = "0";
     
    /**
     * 1:一部成立
     */
    public final static String EXEC_TYPE_ONE_COMPLETE = "1";
    
    /**
     * 2:全部成立
     */
    public final static String EXEC_TYPE_ALL_COMPLETE = "2";
    
}
@
