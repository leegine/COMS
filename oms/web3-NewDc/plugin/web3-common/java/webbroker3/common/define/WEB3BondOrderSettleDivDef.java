head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondOrderSettleDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済区分 定数定義インタフェイス(WEB3BondOrderSettleDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/26 栄イ (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 債券注文単位テーブルの決済区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3BondOrderSettleDivDef
{
    /**
     * 1：円貨
     */
    public static final String YEN_CURRENCY = "1";

    /**
     * 2：外貨
     */
    public static final String FOREIGN_CURRENCY = "2";
}
@
