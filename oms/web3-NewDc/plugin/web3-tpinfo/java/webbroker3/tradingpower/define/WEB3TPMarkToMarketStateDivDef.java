head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarkToMarketStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 値洗い状態区分(WEB3TPMarkToMarketStateDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/06/06 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * 「値洗い状態区分」の定数定義クラス <br>
 */
public interface WEB3TPMarkToMarketStateDivDef
{

    /**
     * 値洗い中
     */
    public static final String PRELIMINARY = "1";

    /**
     * 値洗い終了
     */
    public static final String FIXED = "2";

}
@
