head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSellOrderPriceDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「未約定売付注文単価設定区分」の定数定義インターフェース(WEB3TPSellOrderPriceDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/13 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * (「未約定売付注文単価設定区分」の定数定義インターフェース)
 */
public interface WEB3TPSellOrderPriceDivDef
{
    /**
     * (（未約定売付注文の内）注文銘柄を、「単価=0円」評価する　@※指値の場合も、「単価=0円」を採用)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (（未約定売付注文の内）注文銘柄を、「単価=時価」評価する　@※指値の場合、「単価=指値」を採用)<BR>
     */
    public final static String QUOTE_PRICE = "1";

}@
