head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMarketCodes.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価用の市場コートの定義クラス(WEB3QuoteMarketCodes.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/21 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor.stdimpls;

/**
 * 時価用の市場コードの定義クラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
final class WEB3QuoteMarketCodes
{

    /**
     * 東証
     */
    static final String TOKYO = "1";

    /**
     * 大証
     */
    static final String OSAKA = "2";

    /**
     * 名証
     */
    static final String NAGOYA = "3";

    /**
     * NNM
     */
    static final String NNM = "5";

    /**
     * 福証
     */
    static final String FUKUOKA = "6";

    /**
     * 札証
     */
    static final String SAPPORO = "8";

    /**
     * JASDAQ
     */
    static final String JASDAQ = "9";

    /**
     * 時価用のマーケットコードを、
     * 業務ロジック用のマーケットコードに変換する。
     * 
     * @@param marketCode 時価用のマーケットコード
     */
    static String toWEB3MarketCode(String marketCode)
    {
        // TODO 現状定数で定義してあるが、本来は定数クラスより値を取得
        if (TOKYO.equals(marketCode))
        {
            return "1";
        } else if (OSAKA.equals(marketCode))
        {
            return "2";
        } else if (NAGOYA.equals(marketCode))
        {
            return "3";
        } else if (NNM.equals(marketCode))
        {
            return "9";
        } else if (FUKUOKA.equals(marketCode))
        {
            return "6";
        } else if (SAPPORO.equals(marketCode))
        {
            return "8";
        } else if (JASDAQ.equals(marketCode))
        {
            return "10";
        } else
        {
            throw new IllegalArgumentException();
        }
    }

}
@
