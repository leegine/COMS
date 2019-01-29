head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SoapSendReceiveDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3SoapSendReceiveDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 凌建平 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 送受信区分 定数定義インタフェイス
 *                                                                     
 * @@author 凌建平
 * @@version 1.0
 */

public interface WEB3SoapSendReceiveDivDef
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
