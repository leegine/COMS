head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccruedInterestCalcTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 経過利子計算タイプ定数定義インタフェイス(WEB3AccruedInterestCalcTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 経過利子計算タイプ定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3AccruedInterestCalcTypeDef
{
    /**
     * A：ユーロ方式
     */
    public final static String EURO_FORM = "A";

    /**
     * B：英国方式
     */
    public final static String ENGLISH_FORM = "B";

    /**
     * C：米国国債方式
     */
    public final static String USA_GOVERNMENT_BOND_FORM = "C";

    /**
     * D：西ドイツ方式
     */
    public final static String WEST_GERMANY_FORM = "D";

    /**
     * E：日本方式
     */
    public final static String JAPAN_FORM = "E";

    /**
     * F：フランス方式
     */
    public final static String FRANCE_FORM = "F";

    /**
     * G：フランス国債方式
     */
    public final static String FRANCE_GOVERNMENT_BOND_FORM = "G";

    /**
     * H：ベルギー方式
     */
    public final static String BELGIUM_FORM = "H";

    /**
     * I：オランダ方式
     */
    public final static String ORAN_FORM = "I";

    /**
     * J：オーストラリア方式
     */
    public final static String AUSTRALIA_FORM = "J";

    /**
     * K：スイス方式
     */
    public final static String SWITZERLAND_FORM = "K";

    /**
     * L：ヤンキー方式
     */
    public final static String YANKEE_FORM = "L";

    /**
     * N：ECU方式
     */
    public final static String ECU_FORM = "N";

    /**
     * X：FRN（ドル）方式
     */
    public final static String FRN_DOLLOR_FORM = "X";

    /**
     * Y：FRN（ポンド）方式
     */
    public final static String FRN_POUND_FORM = "Y";
}
@
