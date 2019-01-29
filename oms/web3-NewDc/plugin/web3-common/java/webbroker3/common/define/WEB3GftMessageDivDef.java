head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GftMessageDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3GftMessageDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * GFT電文種別区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3GftMessageDivDef
{
    /**
     * 1：送信（DIR→GFT）　@
     */
    public static final String SEND = "1";
    
    /**
     * 2：受信（GFT→DIR）
     */
    public static final String RECEIVE = "2";
}
@
