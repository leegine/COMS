head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransferStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TransferStatusDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 振替状況区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3TransferStatusDivDef
{
    /**
     * 0：決済中　@　@
     */
    public static final String PROCESSING = "0";
    
    /**
     * 1：決済完了
     */
    public static final String PROCESS_COMPLETE = "1";
    
    /**
     * 2：決済エラー
     */
    public static final String PROCESS_ERROR = "2";
    
    /**
     * 3：取消
     */
    public static final String CANCEL = "3";
    
    /**
     * 5：その他
     */
    public static final String OTHER = "5";
}
@
