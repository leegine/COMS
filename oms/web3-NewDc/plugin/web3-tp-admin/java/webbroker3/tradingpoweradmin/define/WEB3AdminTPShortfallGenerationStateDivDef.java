head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPShortfallGenerationStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 不足金発生状況定義インターフェース(WEB3AdminTPShortfallGenerationStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/13 安陽(中訊) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * 不足金発生状況　@定数定義インタフェイス
 *
 * @@author 安陽
 * @@version 1.0
 */
public interface WEB3AdminTPShortfallGenerationStateDivDef
{
    /**
     * 0：不足金未発生
     */
    public static final String SHORTFALL_NOT_OCCUR = "0";

    /**
     * 1：不足金発生(現物顧客）
     */
    public static final String SHORTFALL_GENERATION_EQUITY_CUST = "1";

    /**
     * 2：不足金発生(信用顧客）
     */
    public static final String SHORTFALL_GENERATION_MARGIN_CUST = "2";
}
@
