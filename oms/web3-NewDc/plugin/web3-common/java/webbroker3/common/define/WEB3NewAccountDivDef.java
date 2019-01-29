head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3NewAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3NewAccountDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 新規口座区分　@定数定義インタフェイス
 *
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3NewAccountDivDef
{
    /**
     * 0 : 新規口座以外 （１以外全て）
     */
    public static final String NOT_NEW_ACCOUNT = "0";

    /**
     * 1 : 新規口座
     */
    public static final String NEW_ACCOUNT = "1";

}
@
