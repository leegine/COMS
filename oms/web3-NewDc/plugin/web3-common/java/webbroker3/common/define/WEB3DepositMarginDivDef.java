head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DepositMarginDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3DepositMarginDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 預り区分　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3DepositMarginDivDef
{
    /**
     * 1:預り金
     */
    public static final String FROM_DEPOSIT = "1";

    /**
     * 4:保証金
     */
    public static final String GUARANTEE = "4";

    /**
     * 5:先物証拠金
     */
    public static final String FUTURES_DEPOSIT = "5";

    /**
     * M:MRF
     */
    public static final String MRF = "M";

}
@
