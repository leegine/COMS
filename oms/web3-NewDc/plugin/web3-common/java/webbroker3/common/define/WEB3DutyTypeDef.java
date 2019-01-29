head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DutyTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税種類　@定数定義インタフェイス(WEB3TaxTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2007/07/26 栄イ (中訊) ＤＢレイアウト(税率テーブル)による
Revesion History : 2008/04/11 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo621
*/
package webbroker3.common.define;

/**
 * 税種類　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3DutyTypeDef
{
    /**
     * 10 : 消費税
     */
    public static final String CONSUMPTION_TAX = "10";

    /**
     * 20 : 譲渡益税
     */
    public static final String CPITAL_GAIN_TAX = "20";

    /**
     * 21 : 譲渡益税（国税）
     */
    public static final String CPITAL_GAIN_TAX_NATIONAL = "21";

    /**
     * 22 : 譲渡益税（地方税）
     */
    public static final String CPITAL_GAIN_TAX_LOCAL = "22";

    /**
     * 30 : 投信源泉徴収（株式型・国税）
     */
    public final static String MF_WITHHOLDING_STOCK_NATIONAL_TAX = "30";

    /**
     * 40 : 投信源泉徴収（債券型・国税）
     */
    public final static String MF_WITHHOLDING_BOND__NATIONAL_TAX = "40";

    /**
     * 50 : 投信源泉徴収（株式型・地方税）
     */
    public final static String MF_WITHHOLDING_STOCK_LOCAL_TAX = "50";

    /**
     * 60 : 投信源泉徴収（債券型・地方税）
     */
    public final static String MF_WITHHOLDING_BOND_LOCAL_TAX = "60";

    /**
     * 70 : 国内債券源泉徴収率
     */
    public final static String DOMESTIC_BOND_WITHHOLDING_TAX = "70";
}
@
