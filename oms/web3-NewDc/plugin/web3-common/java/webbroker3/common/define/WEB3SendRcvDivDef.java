head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SendRcvDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3SendRcvDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 送受信区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3SendRcvDivDef
{
    /**
     * 1：送信済
     */
    public static final String SEND_COMPLETE = "1";
    
    /**
     * 2：受信済
     */
    public static final String RECEIVE_COMPLETE = "2";
    
    /**
     * 3：受信エラー
     */
    public static final String RECEIVE_ERROR = "3";

    /**
     * 0：未送信
     */
    public static final String NOT_SEND = "0";
}
@
