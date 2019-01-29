head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DeliveryMethodDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受渡方法@定数定義インタフェイス(WEB3DeliveryMethodDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 受渡方法@ 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3DeliveryMethodDef
{

    /**
     * 0：選択指定　@　@　@　@  　@　@
     */
    public final static String  SELECT_DESIGNATE = "0";

    /**
     * 1：銀行振込
     */
    public final static String BANK_TRANSFER = "1";

    /**
     * 2：証券口座入金(売付)　@　@　@　@　@　@  　@　@
     */
    public final static String SECURITIES_ACCOUNT_INPUT_SELL = "2";
    
    /**
     * 3：無関係(買付)　@　@　@　@　@  　@　@
     */
    public final static String IRRELEVENT_BUY = "3";    

} @
