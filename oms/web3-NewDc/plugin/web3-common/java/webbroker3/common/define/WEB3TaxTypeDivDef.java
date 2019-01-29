head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TaxTypeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TaxTypeDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 特定口座区分　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3TaxTypeDivDef
{
    /**
     * 0：一般口座
     */
    public static final String NORMAL = "0";
    
    /**
     * 1:特定口座（源泉なし）
     */
    public static final String SPECIAL_NO_SOURCE = "1";

    /**
     * 2:特定口座（源泉あり）
     */
    public static final String SPECIAL_SOURCE = "2";

}
@
