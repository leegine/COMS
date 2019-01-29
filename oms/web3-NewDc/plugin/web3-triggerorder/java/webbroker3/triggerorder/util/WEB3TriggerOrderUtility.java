head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.06.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3TriggerOrderUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文のユーティリティ(WEB3TriggerOrderUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 孟東 (中訊) 新規作成
*/
package webbroker3.triggerorder.util;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.util.WEB3LogUtility;

/**
 * 連続注文のユーティリティクラス。<BR>
 * <BR>
 * 
 * @@author 孟東(中訊)
 * @@version 1.0
 */
public class WEB3TriggerOrderUtility
{
    /**
     * <p>（ログ出力ユーティリティ)。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TriggerOrderUtility.class);

    /**
     * (createキー)<BR>
     * 注文ID + 銘柄タイプでhashmapのキーを作成 <BR>
     * <BR>
     * @@param l_lngParentOrderId 親注文の注文ID  
     * @@param l_productType 銘柄タイプ
     * @@return String
     */
    public static String createKey(
        long l_lngParentOrderId, 
        ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = "getProduct(long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        String l_strKey = "";
        
        l_strKey = 
            new Long(l_lngParentOrderId).toString() + l_productType.stringValue();
        
        log.exiting(STR_METHOD_NAME);
        return l_strKey;
    }
}
@
