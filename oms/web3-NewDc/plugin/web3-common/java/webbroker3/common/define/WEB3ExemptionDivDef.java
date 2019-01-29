head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExemptionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 控除区分 定数定義インタフェイス(WEB3ExemptionDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 孟東(Sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 控除区分定数定義インタフェイス
 *
 * @@author Meng-Dong
 * @@version 1.0
 */
public interface WEB3ExemptionDivDef
{
    /**
     * 0：手数料無料
     */
    public static final String COMMISSION_FREE = "0";

    /**
     * 1：非課税
     */
    public static final String TAX_FREE = "1";
}
@
