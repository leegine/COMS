head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PlanDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プラン  定数定義クラス(WEB3PlanDef )
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 周勇 (sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * プラン  定数を定義する。
 *
 * @@author 周勇 (sinocom)
 * @@version 1.0
 */
public interface WEB3PlanDef
{

    /**
     * 大和投信　@
     */
    public final static String DAIWA_TRUST = "A";

    /**
     * 日興投信
     */
    public final static String NIKKO_TRUST = "B";

    /**
     * 山一投信　@
     */
    public final static String YAMAICHI_TRUST = "C";

    /**
     * 野村投信
     */
    public final static String NOMURA_TRUST = "D";

    /**
     * 三洋投信　@
     */
    public final static String SANYO_TRUST = "E";

    /**
     * 朝日投信
     */
    public final static String ASAHI_TRUST = "F";

    /**
     * 新和光投信　@
     */
    public final static String SHINWAKO_TRUST = "G";

    /**
     * 太陽投信
     */
    public final static String TAIYO_TRUST = "H";

    /**
     * 国際投信　@
     */
    public final static String KOKUSAI_TRUST = "I";

    /**
     * 第一投信
     */
    public final static String DAIICHI_TRUST = "J";

    /**
     * 日本投信　@
     */
    public final static String NIHON_TRUST = "K";

    /**
     * ユニバーサル投信
     */
    public final static String UNIVERSAL_TRUST = "L";

    /**
     * コスモ投信　@
     */
    public final static String KOSUMO_TRUST = "M";

    /**
     * 太平洋投信
     */
    public final static String TAIHEIYO_TRUST = "P";

    /**
     * 東京投信
     */
    public final static String TOKYO_TRUST = "Q";


}
@
