head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrderdMarketCodeListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 優先順市場コードリスト定数定義(WEB3TPOrderdMarketCodeListDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/21 asano(SCS) 新規作成
*/
package webbroker3.tradingpower.define;

import webbroker3.common.define.WEB3MarketCodeDef;

/**
 * 優先順市場コードリスト定数定義インターフェイス
 */
public interface WEB3TPOrderdMarketCodeListDef 
{
    
    /**
     * 優先順市場コードリスト
     */
    public static String[] ORDERD_MARKET_CODE_LIST
        = { WEB3MarketCodeDef.TOKYO,//東京
            WEB3MarketCodeDef.OSAKA,//大阪
            WEB3MarketCodeDef.NAGOYA,//名古屋
            WEB3MarketCodeDef.JASDAQ,//JASDAQ
            WEB3MarketCodeDef.NNM,//NNM
            WEB3MarketCodeDef.FUKUOKA,//福岡
            WEB3MarketCodeDef.SAPPORO//札幌
          };

}
@
