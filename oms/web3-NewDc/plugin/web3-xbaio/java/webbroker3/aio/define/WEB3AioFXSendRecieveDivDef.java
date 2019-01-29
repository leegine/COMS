head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFXSendRecieveDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 送受信区分(WEB3AioFXSendRecieveDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 張騰宇 (中訊) 新規作成・モデル1200
*/
package webbroker3.aio.define;

/**
 * 送受信区分　@定数定義インタフェイス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AioFXSendRecieveDivDef
{
    /**
     * 0：送信 
     */
    public static final String SEND = "0";
    
    /**
     * 1：受信
     */
    public static final String RECEIVE = "1";
}
@
