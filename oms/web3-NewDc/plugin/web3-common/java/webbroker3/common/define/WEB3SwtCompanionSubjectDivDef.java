head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SwtCompanionSubjectDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3SwtCompanionSubjectDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 乗換相手先区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3SwtCompanionSubjectDivDef
{
    /**
     * 0 : 海外運用PFに乗換不可
     */
    public static final String FOREIGN_OPERATING_PF_SWITCHING = "0";

    /**
     * 1 : 海外運用PFに乗換可能
     */
    public static final String FOREIGN_OPERATING_PF_NOT_SWITCHING = "1";

}@
