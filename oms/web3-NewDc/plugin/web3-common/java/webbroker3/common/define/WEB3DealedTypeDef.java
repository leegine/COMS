head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DealedTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3DealedTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2004/09/13 孟 東(sinocom) CANCELの日本語名を変更
*/
package webbroker3.common.define;

/**
 * 出来通知区分　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3DealedTypeDef
{
    /**
     * 0 : 約定
     */
    public static final String EXECUTED = "0";

    /**
     * 1 : 全部約定
     */
    public static final String FULLY_EXECUTED = "1";

    /**
     * 2 : 一部約定
     */
    public static final String PARTIALLY_EXECUTED = "2";

    /**
     * 4 : 約定取消
     */
    public static final String CANCEL = "4";

}
@
