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
filename	WEB3AdminToTradeStopKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AdminToTradeStopKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@呉衛安(中訊) 新規作成
*/
package webbroker3.admintriggerorder.define;

/**
 * 取扱停止ソートキーのキー項目  定数定義インタフェイス
 *
 * @@author 呉衛安
 * @@version 1.0
 */
public interface WEB3AdminToTradeStopKeyItemDef                
{

    /**
     *  銘柄コード
     */
    public static final String  PRODUCT_CODE = "productCode";

    /**
     *  有効期限From
     */
    public static final String  EXPIRATION_START_DATE = "expirationStartDate";
    
}
@
