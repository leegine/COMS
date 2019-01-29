head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場コード　@定数定義インタフェイス(WEB3MarketCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 仲川 里織(SRA) 新規作成
                   2004/06/10 馬振田 (中訊) 修正
                   2006/12/25 栄イ(中訊) 仕様変更・モデルNo219
                   2007/12/18 孟暁シン(中訊) ＤＢレイアウト(市場テーブル)による
*/
package webbroker3.common.define;

/**
 * 市場コード　@定数定義インタフェイス。
 *
 * @@author 仲川 里織(SRA)
 * @@version 1.0
 */
public interface WEB3MarketCodeDef
{

    /**
     * デフォルト
     */
    public static final String DEFAULT = "0";

    /**
     * 東京
     */
    public static final String TOKYO = "1";
    
    /**
     * 大阪
     */
    public static final String OSAKA = "2";

    /**
     * 名古屋
     */    
    public static final String NAGOYA = "3";
    
    /**
     * 京都
     */
    //public static final String KYOTO = "4";
    
    /**
     * 広島
     */
    //public static final String HIROSHIMA = "5";
    
    /**
     * 福岡
     */
    public static final String FUKUOKA = "6";
    
    /**
     * 札幌
     */
    public static final String SAPPORO = "8";
    
    /**
     * NNM
     */
    public static final String NNM = "9";
    
    /**
     * JASDAQ
     */
    public static final String JASDAQ = "10";
    
    /**
     * 香港
     */
    public static final String HONGKONG = "N1";

    /**
     * 深セン
     */
    public static final String SHENZHEN = "N2";

    /**
     * 上海
     */
    public static final String SHANGHAI = "X1";

    /**
     * 優先市場
     */
    public static final String PRIORITY_MARKET = "99";
    
    /**
     * JNX-PTS
     */
    public static final String JNX_PTS = "11";
}

@
