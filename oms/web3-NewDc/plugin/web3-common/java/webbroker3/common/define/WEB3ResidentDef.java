head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ResidentDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ResidentDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 居住／非居住区分　@定数定義インタフェイス
 *                                                                       
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ResidentDef
{
    /**
     * 0 : 住居者
     */
    public static final String RESIDENT = "0";

    /**
     * 1 : 特別非住居者
     */
    public static final String SPE_NON_RESIDENT = "1";

    /**
     * 2 : 非住居者
     */
    public static final String NON_RESIDENT = "2";

}
@
