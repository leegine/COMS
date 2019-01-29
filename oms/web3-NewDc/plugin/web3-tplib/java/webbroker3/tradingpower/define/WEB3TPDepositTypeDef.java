head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDepositTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 定数定義インタフェイス(WEB3TPDepositTypeDef)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/08/03 劉(fitechlabs)　@新規作成
 */

package webbroker3.tradingpower.define;

/**
 *預り区分　@定数定義インタフェイス
 *
 * @@author 劉
 * @@version 1.0
 */
public interface WEB3TPDepositTypeDef {
    /**
     * 預り区分:
     * 0:保護
     */
    public final static String TRUST = "0";

    /**
     * 預り区分:
     * 1:代用
     */
    public final static String SUBSTITUTE = "1";

}
@
