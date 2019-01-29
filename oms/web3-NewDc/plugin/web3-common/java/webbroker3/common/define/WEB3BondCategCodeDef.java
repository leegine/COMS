head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondCategCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 種別コード定数定義インタフェイス(WEB3BondCategCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 栄イ(中訊) 新規作成
Revesion History : 2006/10/06 栄イ(中訊) 仕様変更・ＤＢレイアウトNo020
*/
package webbroker3.common.define;

/**
 * 種別コード定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3BondCategCodeDef
{
    /**
     * 1011：その他国債（含.中期国債）
     */
    public final static String OTHER_GOVERNMENT_BOND = "1011";

    /**
     * 1012：長期国債
     */
    public final static String LONG_TERM_GOVERNMENT_BOND = "1012";

    /**
     * 1013：短期国債
     */
    public final static String SHORT_TERM_GOVERNMENT_BOND = "1013";

    /**
     * 1014：割引国債
     */
    public final static String DISCOUNT_GOVERNMENT_BOND = "1014";

    /**
     * 1021：公募地方債, 非公募地方債
     */
    public final static String REGION_BOND = "1021";

    /**
     * 1031：政保債
     */
    public final static String GOVERNMENT_WARRANT_BOND = "1031";

    /**
     * 1032：利付電々債
     */
    public final static String INTEREST_BEARING_ELETRICITY_BOND = "1032";

    /**
     * 1033：割引電々債
     */
    public final static String DISCOUNT_ELETRICITY_BOND = "1033";

    /**
     * 1034：特別電々債等
     */
    public final static String SPECIAL_ELETRICITY_BOND = "1034";

    /**
     * 1035：利付金融債
     */
    public final static String INTEREST_BEARING_FINANCE_BOND = "1035";

    /**
     * 1036：割引金融債
     */
    public final static String DISCOUNT_FINANCE_BOND = "1036";

    /**
     * 1041：電力債
     */
    public final static String ELECTRICITY_BOND = "1041";

    /**
     * 1042：一般事業債（含.ＳＢ）, 非公募事業債
     */
    public final static String NORMAL_BUSINESS_BOND = "1042";

    /**
     * 1043：転換社債・ワラント債
     */
    public final static String CONVERSION_COMPANY_BOND_WARRANT_BOND = "1043";

    /**
     * 1091：その他債券類
     */
    public final static String OTHER_BOND = "1091";

    /**
     * 2010：利付国債
     */
    public final static String INTEREST_BEARING_GOVERNMENT_BOND = "2010";

    /**
     * 2011：トレジャリービル
     */
    public final static String TO_RE_JIA_RI_BII_RU = "2011";

    /**
     * 2012：その他割引国債
     */
    public final static String OTHER_DISCOUNT_GOVERNMENT_BOND = "2012";

    /**
     * 2013：インデックス国債
     */
    public final static String INDEX_GOVERNMENT_BOND = "2013";

    /**
     * 2020：利付政府機@関債
     */
    public final static String INTEREST_BEARING_GOVERNMENT_ORGANIZATION_BOND = "2020";

    /**
     * 2021：割引政府機@関債
     */
    public final static String DISCOUNT_GOVERNMENT_ORGANIZATION_BOND = "2021";

    /**
     * 2022：インデックス政府機@関債
     */
    public final static String INDEX_GOVERNMENT_ORGANIZATION_BOND = "2022";

    /**
     * 2030：利付地方債
     */
    public final static String INTEREST_BEARING_REGION_BOND = "2030";

    /**
     * 2031：割引地方債
     */
    public final static String DISCOUNT_REGION_BOND = "2031";

    /**
     * 2032：インデックス地方債
     */
    public final static String INDEX_REGION_BOND = "2032";

    /**
     * 2040：利付公益社債
     */
    public final static String INTEREST_BEARING_INTEREST_COMPANY_BOND = "2040";

    /**
     * 2041：利付一般社債
     */
    public final static String INTEREST_BEARING_NOTMAL_COMPANY_BOND = "2041";

    /**
     * 2042：利付金融債
     */
    public final static String FOREIGN_INTEREST_BEARING_FINANCE_BOND = "2042";

    /**
     * 2043：割引金融債
     */
    public final static String FOREIGN_DISCOUNT_FINANCE_BOND = "2043";

    /**
     * 2044：割引社債
     */
    public final static String DISCOUNT_COMPANY_BOND = "2044";

    /**
     * 2050：フローティングレート債
     */
    public final static String FLOATING_RATE_BOND = "2050";

    /**
     * 2053：段階利付債
     */
    public final static String STEP_INTEREST_BEARING_BOND = "2053";

    /**
     * 2056：インデックス社債
     */
    public final static String INDEX_COMPANY_BOND = "2056";

    /**
     * 2060：転換社債
     */
    public final static String CONVERSION_COMPANY_BOND = "2060";

    /**
     * 2063：ワラント付社債
     */
    public final static String WARRANT_FOLLOW_COMPANY_BOND = "2063";

    /**
     * 2064：ワラント落社債
     */
    public final static String WARRANT_FALL_COMPANY_BOND = "2064";

    /**
     * 2070：利付国際機@関債
     */
    public final static String INTEREST_BEARING_INTERNATIONAL_ORGANIZATION_BOND = "2070";

    /**
     * 2071：割引国際機@関債
     */
    public final static String DISCOUNT_INTERNATIONAL_ORGANIZATION_BOND = "2071";

    /**
     * 2072：インデックス国際機@関債
     */
    public final static String INDEX_INTERNATIONAL_ORGANIZATION_BOND = "2072";

    /**
     * 2090：ＣＰ
     */
    public final static String CP = "2090";

    /**
     * 2091：ＣＤ
     */
    public final static String CD = "2091";

    /**
     * 2097：株式ワラント（スイスフラン建）
     */
    public final static String STOCK_WARRANT_SWITZERLAND_FRANC_BOND = "2097";

    /**
     * 2098：株式ワラント
     */
    public final static String STOCK_WARRANT = "2098";

    /**
     * 2099：その他有価証券
     */
    public final static String OTHER_VALUE_SECURITIES = "2099";
}
@
