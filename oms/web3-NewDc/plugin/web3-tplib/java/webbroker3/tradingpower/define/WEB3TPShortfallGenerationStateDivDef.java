head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 不足金発生状況の定数定義インターフェース(WEB3TPShortfallGenerationStateDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/10/20 陸文静 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * (不足金発生状況の定数定義インターフェース)
 */
public interface WEB3TPShortfallGenerationStateDivDef
{
    /**
     *  0 : 不足金未発生<BR>
     */
    public final static String SHORTFALL_NOT_GENERATION = "0";

    /**
     *  1 : 不足金発生<現物顧客><BR>
     */
    public final static String SHORTFALL_GENERATION_EQUITY_ACCOUNT = "1";

    /**
     *  2 : 不足金発生<信用顧客><BR>
     */
    public final static String SHORTFALL_GENERATION_MARGIN_ACCOUNT = "2";
}
@
