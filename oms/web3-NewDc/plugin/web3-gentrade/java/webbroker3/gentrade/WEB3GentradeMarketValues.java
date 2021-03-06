head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMarketValues.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;


/**
 * (市場種類)<BR>
 * 市場種類を定数定義するインタフェイス。<BR>
 */
public interface WEB3GentradeMarketValues
{
   
    /**
     * 市場種類.東京の定数定義<BR>
     */
    public static  String MARKET_TOKYO = "1";
   
    /**
     * 市場種類.大阪の定数定義<BR>
     */
    public static  String MARKET_OSAKA = "2";
   
    /**
     * 市場種類.名古屋の定数定義<BR>
     */
    public static  String MARKET_NAGOYA = "3";
   
    /**
     * 市場種類.京都の定数定義<BR>
     */
    public static  String MARKET_KYOTO = "4";
   
    /**
     * 市場種類.広島の定数定義<BR>
     */
    public static  String MARKET_HIROSHIMA = "5";
   
    /**
     * 市場種類.福岡の定数定義<BR>
     */
    public static  String MARKET_FUKUOKA = "6";
   
    /**
     * 市場種類.札幌の定数定義<BR>
     */
    public static  String MARKET_SAPPORO = "8";
   
    /**
     * 市場種類.NNMの定数定義<BR>
     */
    public static  String MARKET_NNM = "9";
   
    /**
     * 市場種類.JASDAQの定数定義<BR>
     */
    public static  String MARKET_JASDAQ = "10";
}
@
