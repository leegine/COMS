head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ItemCheckModeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 項目チェック方式(WEB3ItemCheckModeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
Revision History : 2007/06/05 栄イ(中訊) ＤＢレイアウト(連絡管理項目マスタ)による
*/

package webbroker3.common.define;

/**
 * 項目チェック方式 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3ItemCheckModeDef
{

    /**
     *  00：DEFAULT（チェックなし）
     */
    public final static String DEFAULT = "00";

    /**
     * 01：有効コード値チェック(*1)
     */
    public final static String VALID_CODE_CHECK = "01";

    /**
     * 02：半角数字のみ
     */
    public final static String HALF_NUMBER = "02";

    /**
     * 03：半角英数字
     */
    public final static String HALF_ALPHABET_NUMBER = "03";

    /**
     * 04：半角英字
     */
    public final static String HALF_ALPHABET = "04";

    /**
     * 05：半角カナ
     */
    public final static String HALF_KANA = "05";

    /**
     * 06：全角文字
     */
    public final static String FULL_CHARACTER = "06";

    /**
     * 07：住所／氏名カナ(*2)
     */
    public final static String ADDRESS_GIVEN_NAME_KANA = "07";

    /**
     * 08：メールアドレス
     */
    public final static String MAIL_ADDRESS = "08";

    /**
     * 10：郵便番号
     */
    public final static String ZIP_CODE = "10";

    /**
     * 11：電話／携帯番号
     */
    public final static String TELEPHONE_MOBILE_NUMBER = "11";

    /**
     * 12：年齢（20歳以上）
     */
    public final static String AGE = "12";

    /**
     * 13：フラグ（BooleanEnum.TRUE/FALSE）
     */
    public final static String BOOLEAN_FLAG = "13";

    /**
     * 14：銘柄チェック
     */
    public final static String PRODUCT_CHECK = "14";

    /**
     * 15：投信銘柄チェック
     */
    public final static String MUTUALFUND_PRODUCT_CHECK = "15";

    /**
     * 16：債券銘柄チェック
     */
    public final static String BOND_PRODUCT_CHECK = "16";
}@
