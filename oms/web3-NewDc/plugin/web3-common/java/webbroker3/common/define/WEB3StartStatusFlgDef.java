head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StartStatusFlgDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理FLAG（決済開始）  定数定義インタフェイス(WEB3StartStatusFlgDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 処理FLAG（決済開始）　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3StartStatusFlgDef
{

    /**
     *  ０：未処理　@
     */
    public static final String NOT_DEAL = "0";

    /**
     *  １：要求受信　@
     */
    public static final String REPUEST_RECEIPT = "1";

    /**
     *  ２：応答送信　@
     */
    public static final String RESPONSE_SEND = "2";
    
    /**
     *  ３：要求エラー（NG） 
     */
    public static final String REPUEST_ERROR_NG = "3";
    
    /**
     *  ４：要求エラー（ERROR）　@
     */
    public static final String REPUEST_ERROR_ERROR = "4";
    
    /**
     *  ５：キャンセル　@
     */
    public static final String CANCEL = "5";
    
    /**
     *  ６：セッションエラー 
     */
    public static final String SESSION_ERROR = "6";

}
@
