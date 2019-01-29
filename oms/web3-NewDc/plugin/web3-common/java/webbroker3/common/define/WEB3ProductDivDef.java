head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ProductDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 商品区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3ProductDivDef
{
    /**
     * 1x:ユニット
     */
    public static final String UNIT = "1x";

    /**
     * 2x:オープン 
     */
    public static final String OPEN = "2x";
    
    /**
     * 3x:積立
     */
    public static final String RESERVE = "3x";
    
    /**
     * 4x:公社債
     */
    public static final String PUBLIC_AND_CORPORATE_BOODS = "4x";
    
    /**
     * 5x:外国投信
     */
    public static final String FOREIGN_NUTUALl = "5x";

}
@
