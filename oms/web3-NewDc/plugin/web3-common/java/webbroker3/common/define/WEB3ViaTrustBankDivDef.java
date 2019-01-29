head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ViaTrustBankDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ViaTrustBankDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 信託経由区分　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ViaTrustBankDivDef
{
    /**
     * 0 : 信託経由以外 （１以外全て）
     */
    public static final String NOT_VIA_TRUST_BANK = "0";

    /**
     * 1 : 信託経由
     */
    public static final String VIA_TRUST_BANK = "1";

}
@
