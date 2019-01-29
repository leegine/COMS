head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityRegulationTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 株式銘柄情報取引規制　@コード定義インタフェイス (WEB3EquityRegulationTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/22 SRA坂上 作成
*/
package webbroker3.equity.define;

/**
 * 株式銘柄情報取引規制　@コード定義インタフェイス
 *                                                                     
 * @@author SRA坂上
 * @@version 1.0
 */
public interface WEB3EquityRegulationTypeDef {
    /**
     * 1:売買停止中
     */
    public final static String REG_TYPE_TRDSTOP = "1";
    
    /**
     * 2:現物取引停止中
     */
    public final static String REG_TYPE_STOCKTRDSTOP = "2";
    
    /**
     * 3:信用取引停止中
     */
    public final static String REG_TYPE_MARTRDSTOP = "3";
    
    /**
     * 4：制度信用取引停止中
     */
    public final static String REG_TYPE_MARSYSTRDSTOP = "4";
    
    /**
     * 5：一般信用取引停止中 
     */
    public final static String REG_TYPE_MARGENTRDSTOP = "5";
    
    /**
     * 6：現物買付停止中
     */
    public final static String REG_TYPE_BUYCASHSTOP = "6";
    
    /**
     * 7：現物売付停止中
     */
    public final static String REG_TYPE_SELLCASHSTOP = "7";
    
    /**
     * 8：ミニ株取引停止中
     */
    public final static String REG_TYPE_MINISTOCKSTOP = "8";
    
    /**
     * 9：ミニ株買付停止中 
     */
    public final static String REG_TYPE_BUYMINISTOCKSTOP = "9";
    
    /**
     * 10：ミニ株売付停止中
     */
    public final static String REG_TYPE_SELLMINISTOCKSTOP = "10";
    
    /**
     * 11：信用新規建停止中
     */
    public final static String REG_TYPE_MARGINSTOP = "11";
    
    /**
     * 12：信用新規買停止中
     */
    public final static String REG_TYPE_LONGMARGINSTOP = "12";
    
    /**
     * 13：信用新規売停止中
     */
    public final static String REG_TYPE_SHORTMARGINSTOP = "13";
    
    /**
     * 14：制度信用新規建停止中
     */
    public final static String REG_TYPE_MARGINSYSSTOP = "14";
    
    /**
     * 15：一般信用新規建停止中
     */
    public final static String REG_TYPE_MARGINGENSTOP = "15";
    
    /**
     * 16：制度信用新規買停止中
     */
    public final static String REG_TYPE_LONGMARGINSYSSTOP = "16";
    
    /**
     * 17：制度信用新規売停止中
     */
    public final static String REG_TYPE_SHORTMARGINSYSSTOP = "17";
    
    /**
     * 18：一般信用新規買停止中
     */
    public final static String REG_TYPE_LONGMARGINGENSTOP = "18";
    
    /**
     * 19：一般信用新規売停止中
     */
    public final static String REG_TYPE_SHORTMARGINGENSTOP = "19";
    
    /**
     * 20：信用返済停止中
     */
    public final static String REG_TYPE_CLOSEMARGINSTOP = "20";
    /**
     * 21：信用返済売停止中
     */
    public final static String REG_TYPE_LONGCLOSEMARGINSTOP = "21";
    
    /**
     * 22：信用返済買停止中
     */
    public final static String REG_TYPE_SHORTCLOSEMARGINSTOP = "22";
    
    /**
     * 23：制度信用返済停止中
     */
    public final static String REG_TYPE_CLOSEMARGINSYSSTOP = "23";
    
    /**
     * 24：一般信用返済停止中
     */
    public final static String REG_TYPE_CLOSEMARGINGENSTOP = "24";
    
    /**
     * 25：制度信用返済売停止中
     */
    public final static String REG_TYPE_LONGCLOSEMARGINSYSSTOP = "25";
    
    /**
     * 26：制度信用返済買停止中
     */
    public final static String REG_TYPE_SHORTCLOSEMARGINSYSSTOP = "26";
    
    /**
     * 27：一般信用返済売停止中
     */
    public final static String REG_TYPE_LONGCLOSEMARGINGENSTOP = "27";
    
    /**
     * 28：一般信用返済買停止中
     */
    public final static String REG_TYPE_SHORTCLOSEMARGINGENSTOP = "28";
    
    /**
     * 29：信用現引現渡停止中
     */
    public final static String REG_TYPE_SWAPMARGINSTOP = "29";
    
    /**
     * 30：信用現引停止中
     */
    public final static String REG_TYPE_LONGSWAPMARGINSTOP = "30";
        
    /**
     * 31：信用現渡停止中
     */
    public final static String REG_TYPE_SHORTSWAPMARGINSTOP = "31";
    
    /**
     * 32：制度信用現引現渡停止中
     */
    public final static String REG_TYPE_SWAPMARGINSYSSTOP = "32";
    
    /**
     * 33：一般信用現引現渡停止中
     */
    public final static String REG_TYPE_SWAPMARGINGENSTOP = "33";
    
    /**
     * 34：制度信用現引停止中
     */
    public final static String REG_TYPE_LONGSWAPMARGINSYSSTOP = "34";
    
    /**
     * 35：制度信用現渡停止中
     */
    public final static String REG_TYPE_SHORTSWAPMARGINSYSSTOP = "35";
    
    /**
     * 36：一般信用現引停止中
     */
    public final static String REG_TYPE_LONGSWAPMARGINGENSTOP = "36";
    
    /**
     * 37：一般信用現渡停止中 
     */
    public final static String REG_TYPE_SHORTSWAPMARGINGENSTOP = "37";
    
    /**
     * 38：成行現物取引停止中
     */
    public final static String REG_TYPE_SPOTMARKETORDDESSTOP = "38";
    
    /**
     * 39：成行現物買付停止中 
     */
    public final static String REG_TYPE_BUYSPOTMARKETORDDESSTOP = "39";
    
    /**
     * 40：成行現物売付停止中
     */
    public final static String REG_TYPE_SELLSPOTMARKETORDDESSTOP = "40";
    
    /**
     * 41：成行信用新規建停止中
     */
    public final static String REG_TYPE_MMARKETORDDESSTOP = "41";
    
    /**
     * 42：成行信用新規買停止中
     */
    public final static String REG_TYPE_LONGMMARKETORDDESSTOP = "42";
    
    /**
     * 43：成行信用新規売停止中
     */
    public final static String REG_TYPE_SHORTMMARKETORDDESSTOP = "43";
    
    /**
     * 44：成行制度信用新規建停止中
     */
    public final static String REG_TYPE_MSMARKETORDDESSTOP = "44";
    
    /**
     * 45：成行一般信用新規建停止中
     */
    public final static String REG_TYPE_MGMARKETORDDESSTOP = "45";
    
    /**
     * 46：成行制度信用新規買停止中
     */
    public final static String REG_TYPE_LONGMSMARKETORDDESSTOP = "46";
    
    /**
     * 47：成行制度信用新規売停止中
     */
    public final static String REG_TYPE_SHORTMSMARKETORDDESSTOP = "47";
    
    /**
     * 48：成行一般信用新規買停止中
     */
    public final static String REG_TYPE_LONGMGMARKETORDDESSTOP = "48";
    
    /**
     * 49：成行一般信用新規売停止中 
     */
    public final static String REG_TYPE_SHORTMGMARKETORDDESSTOP = "49";
    
    /**
     * 50：成行信用返済停止中
     */
    public final static String REG_TYPE_CMMARKETORDDESSTOP = "50";
        
    /**
     * 51:成行信用返済売停止中
     */
    public final static String REG_TYPE_LONGCMMARKETORDDESSTOP = "51";
    
    /**
     * 52：成行信用返済買停止中
     */
    public final static String REG_TYPE_SHORTCMMARKETORDDESSTOP = "52";
    
    /**
     * 53：成行制度信用返済停止中
     */
    public final static String REG_TYPE_CMSMARKETORDDESSTOP = "53";
    
    /**
     * 54：成行一般信用返済停止中
     */
    public final static String REG_TYPE_CMGMARKETORDDESSTOP = "54";
    
    /**
     * 55：成行制度信用返済売停止中
     */
    public final static String REG_TYPE_LONGCMSMARKETORDDESSTOP = "55";
    
    /**
     * 56：成行制度信用返済買停止中
     */
    public final static String REG_TYPE_SHORTCMSMARKETORDDESSTOP = "56";
    
    /**
     * 57：成行一般信用返済売停止中
     */
    public final static String REG_TYPE_LONGCMGMARKETORDDESSTOP = "57";
    
    /**
     * 58：成行一般信用返済買停止中
     */
    public final static String REG_TYPE_SHORTCMGMARKETORDDESSTOP = "58";

        
}
@
