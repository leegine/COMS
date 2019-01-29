head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSysProductTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 制度信用銘柄区分 定数定義インタフェイス(WEB3MarginSysProductTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/06　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 制度信用銘柄区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3MarginSysProductTypeDef
{

    /**
     *  0 ： 制度信用銘柄でない
     */
    public static final String NOT_MARGIN_SYS_PRODUCT = "0";

    /**
     *  1 ： 制度信用銘柄（貸借銘柄）
     */
    public static final String MARGIN_SYS_PRODUCT_LOAN = "1";
    /**
     *  2 ： 制度信用銘柄（非貸借銘柄）
     */
    public static final String MARGIN_SYS_PRODUCT_NO_LOAN = "2";
    

}
@
