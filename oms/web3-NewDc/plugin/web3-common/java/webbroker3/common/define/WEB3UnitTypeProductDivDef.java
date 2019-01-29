head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3UnitTypeProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定日取引銘柄区分定数定義インタフェイス(WEB3UnitTypeProductDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/12 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 特定日取引銘柄区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3UnitTypeProductDivDef
{
    /**
     * 0：通常銘柄
     */
    public static final String NORMAL_PRODUCT = "0";

    /**
     * 1：買付のみ
     */
    public static final String BUY = "1";

    /**
     * 2:解約のみ
     */
    public static final String OF_A_CONTRACT = "2";

    /**
     * 3:両方
     */
    public static final String BOTH = "3";
}
@
