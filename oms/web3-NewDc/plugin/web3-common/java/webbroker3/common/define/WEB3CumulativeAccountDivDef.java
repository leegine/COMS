head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CumulativeAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投口座開設区分 定数定義インタフェイス(WEB3CumulativeAccountDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 顧客マスター.累投口座開設区分　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3CumulativeAccountDivDef
{
    /**
     * 0 : 累投口座開設
     */
    public static final String ESTABLISH = "0";

    /**
     * 1 : 累投口座未開設
     */
    public static final String NOT_ESTABLISH = "1";

}
@
