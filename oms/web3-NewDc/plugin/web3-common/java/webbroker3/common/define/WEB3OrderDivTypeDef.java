head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderDivTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文種別定数定義クラス(WEB3OrderDivTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/06 DU-SEN 新規作成
*/
package webbroker3.common.define;

/**
 * 注文種別　@定数定義インタフェイス
 *
 * @@author DU-SEN
 * @@version 1.0
 */
public interface WEB3OrderDivTypeDef
{
    /**
     * 1 : 全部解約
     */
    public static final String ALL_CANCEL = "1";

    /**
     * 2 : 買付
     */
    public static final String BUY = "2";
    
    /**
     * 3 : 一部解約
     */
    public static final String PARTIALLY_CANCEL = "3";
}
@
