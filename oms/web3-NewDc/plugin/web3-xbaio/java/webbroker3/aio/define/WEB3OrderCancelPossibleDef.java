head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3OrderCancelPossibleDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3OrderCancelPossibleDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 出金注文が取消可能　@定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */
public interface WEB3OrderCancelPossibleDef
{
    /**
     * 0 : 取消可能
     */
    public static final String CANCEL_OK = "0";

    /**
     * 1 : 取消可能NG
     */
    public static final String CANCEL_NG = "1";
}
@
