head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketCodeSONARDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場コード（ＳＯＮＡＲ）　@定数定義クラス(WEB3MarketCodeSONARDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 仲川 里織(SRA) 新規作成
Revesion History : 2004/11/26 鄒政 (中訊) 修正
Revesion History : 2007/12/18 孟暁シン (中訊) 【共通】仕様変更管理台帳No568(ＤＢレイアウト)を対応
Revesion History : 2009/09/02 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.694
*/
package webbroker3.common.define;

/**
 * 市場コード（ＳＯＮＡＲ）　@定数定義クラス
 *
 * @@author 仲川 里織(SRA)
 * @@version 1.0
 */
public class WEB3MarketCodeSONARDef
{

    /**
     * 1：東京
     */
    public static final String TOKYO = "1";
    
    /**
     *  2：大阪
     */
    public static final String OSAKA = "2";

    /**
     * 3：名古屋
     */    
    public static final String NAGOYA = "3";
    
    /**
     * 4：札幌
     */
    public static final String SAPPORO = "4";
    
    /**
     * 5：NNM
     */
    public static final String NNM = "5";
    
    /**
     * 6：JASDAQ
     */
    public static final String JASDAQ = "6";
    
    /**
     * 9：福岡
     */
    public static final String FUKUOKA = "9";
    
    /**
     * P:JNX-PTS
     */
    public static final String JNX_PTS = "P";

    /**
     * x:香港
     */
    public static final String HONG_KONG = "x";

    /**
     * y:上海
     */
    public static final String SHANG_HAI = "y";

    /**
     * z:深セン
     */
    public static final String SHEN_ZHEN = "z";

    /**
     * 指定した市場コードに該当する市場コード（SONAR）を返却する。<BR>
     * 該当市場コードが存在しない場合はnullを返却する。<BR>
     * <BR>
     * @@param l_marketCode　@市場コード
     * @@return
     */
    public static String getMarketCodeSONAR(String l_marketCode) {
        
        if (l_marketCode.equals(WEB3MarketCodeDef.TOKYO)) {
            return WEB3MarketCodeSONARDef.TOKYO;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.OSAKA)) {
            return WEB3MarketCodeSONARDef.OSAKA;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.NAGOYA)) {
            return WEB3MarketCodeSONARDef.NAGOYA;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.NNM)) {
            return WEB3MarketCodeSONARDef.NNM;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.FUKUOKA)) {
            return WEB3MarketCodeSONARDef.FUKUOKA;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.SAPPORO)) {
            return WEB3MarketCodeSONARDef.SAPPORO;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.JASDAQ)) {
            return WEB3MarketCodeSONARDef.JASDAQ;
        }        
        return null;
        
    }
    
    /**
     * 指定した市場コード（SOANR）に該当する市場コードを返却する。<BR>
     * 該当市場コード（SONAR）が存在しない場合はnullを返却する。<BR>
     * <BR>
     * @@param l_marketCodeSONAR　@市場コード（SONAR）
     * @@param l_listType 上場区分：WEB3ListTypeDefに定義した値
     * @@return
     */
    public static String getMarketCode(String l_marketCodeSONAR,
        String l_listType) {

        if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.TOKYO)) {
            if (l_listType.equals(WEB3ListTypeDef.OTC)) 
            {
                return WEB3MarketCodeDef.JASDAQ;
            }
            else
            {
                return WEB3MarketCodeDef.TOKYO;
            }
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.OSAKA)) {
            return WEB3MarketCodeDef.OSAKA;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.NAGOYA)) {
            return WEB3MarketCodeDef.NAGOYA;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.NNM)) {
            return WEB3MarketCodeDef.NNM;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.FUKUOKA)) {
            return WEB3MarketCodeDef.FUKUOKA;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.SAPPORO)) {
            return WEB3MarketCodeDef.SAPPORO;
        }
        return null;
        
    }
    
}
@
