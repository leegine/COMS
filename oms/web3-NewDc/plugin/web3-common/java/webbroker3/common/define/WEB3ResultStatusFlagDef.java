head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ResultStatusFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理FLAG（決済結果）  定数定義インタフェイス(WEB3ResultStatusFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 処理FLAG（決済結果）　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3ResultStatusFlagDef
{

    /**
     *  ０：未処理　@
     */
    public static final String NOT_DEAL = "0";

    /**
     *  １：通知受信　@
     */
    public static final String NOTIFY_RECEIPT = "1";

    /**
     *  ２：応答送信　@
     */
    public static final String RESPONSE_SEND = "2";
    
    /**
     *  ３：通知エラー（FAIL）
     */
    public static final String NOTIFY_ERROR_FAIL = "3";
    
    /**
     *  ４：通知エラー（ERROR）　@
     */
    public static final String NOTIFY_ERROR_ERROR = "4";
    
    /**
     *  ５：応答エラー（電文不備）
     */
    public static final String RESPONSE_ERROR_TELEGRAM = "5";
    
    /**
     *  ６：セッションエラー（COMPLETE）
     */
    public static final String SESSION_ERROR_COMPLETE = "6";
    
    /**
     *  ７：セッションエラー（COMPLETE以外）
     */
    public static final String SESSION_ERROR_COMPLETE_EXCEPT = "7";
    
    /**
     *  ８：余力計算失敗　@
     */
    public static final String REMAINING_CALCULATION_FAIL = "8";
    
    /**
     *  ９：余力再計算完了　@
     */
    public static final String REMAINING_CALCULATION_COMPLETE = "9";
    
    /**
     *  Ａ：決済再処理完了
     */
    public static final String SETTLEMENT_RESEND_PROCESS_COMPLETE= "A";

     
}
@
