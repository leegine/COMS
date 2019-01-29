head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginAttributeNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ThreadLocalSystemAttributesRegistry(設定キー)　@定数定義インタフェイス (WEB3MarginAttributeNameDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/02/07 沢村　@仁士(SRA) 作成
*/
package webbroker3.equity.define;

/**
 * ThreadLocalSystemAttributesRegistry(設定キー)　@定数定義インタフェイス
 *                                                                     
 * @@author 沢村　@仁士
 * @@version 1.0
 */
public interface WEB3MarginAttributeNameDef {
    /**
     * CURRENT_PRICE_INFO(信用取引残高照会にて使用)
     */
    public final static String CURRENT_PRICE_INFO = "CURRENT_PRICE_INFO";
    
    /**
     * CURRENT_PRICE(信用取引建株照会、決済一覧にて使用)
     */
    public final static String CURRENT_PRICE = "CURRENT_PRICE";
    
    /**
     * ACCOUNT_ID(現物／信用取引注文約定照会にて使用)
     */
    public final static String ACCOUNT_ID = "ACCOUNT_ID"; 
    
    /**
     * BACK_SERVICE_ONLINE(現物／信用下り処理で使用)
     */
    public final static String BACK_SERVICE_IN_ONLINE = "BACK_SERVICE_IN_ONLINE";
}
@
