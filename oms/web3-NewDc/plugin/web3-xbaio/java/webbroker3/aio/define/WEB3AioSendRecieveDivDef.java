head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSendRecieveDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioSendRecieveDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * FX口座開設申込明細 の送受信区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AioSendRecieveDivDef
{
    /**
     * 0：送信未済 
     */
    public static final String NOT_RECEIVE = "0";
    
    /**
     * 1：送信済
     */
    public static final String RECEIVE = "1";
}
@
