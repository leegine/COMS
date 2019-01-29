head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginGenProductTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 一般信用銘柄区分 定数定義インタフェイス(WEB3MarginGenProductTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/06　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 一般信用銘柄区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3MarginGenProductTypeDef
{

    /**
     *  0 ： 一般信用銘柄でない
     */
    public static final String NOT_MARGIN_GEN_PRODUCT = "0";

    /**
     *  1 ： 一般信用銘柄（売建可能銘柄）
     */
    public static final String  MARGIN_GEN_PRODUCT_OPEN_SELL = "1";
    /**                      
     *  2 ：一般信用銘柄（売建不可銘柄）
     */
    public static final String  MARGIN_GEN_PRODUCT_NO_OPEN_SELL = "2";
                              

}
@
