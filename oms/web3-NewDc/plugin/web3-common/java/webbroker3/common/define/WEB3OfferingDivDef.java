head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OfferingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 購入申込区分定数定義インタフェイス(WEB3OfferingDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 購入申込区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3OfferingDivDef
{

    /**
     * 0：DEFAULT（初期値）　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：購入申込　@　@　@ 　@　@　@ 　@　@
     */
    public final static String PURCHASE_APPLICATION = "1";
    
    /**
     * 2：辞退　@　@　@　@　@　@
     */
    public final static String REFUSAL = "2";
        
}@
