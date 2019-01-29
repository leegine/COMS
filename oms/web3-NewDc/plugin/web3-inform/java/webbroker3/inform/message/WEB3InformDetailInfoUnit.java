head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 各種連絡情報クラス(WEB3InformDetailInfoUnit.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/20 凌建平(中訊) 作成
Revesion History    : 2007/05/11 劉立峰 (中訊) モデルNo.35
Revesion History    : 2007/05/14 劉立峰 (中訊) モデルNo.42
Revesion History    : 2007/06/18 周墨洋(中訊) 修正 モデル088、091
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (各種連絡情報)<BR>
 * 各種連絡情報クラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3InformDetailInfoUnit extends Message
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformDetailInfoUnit.class);
            
    /**
     * (連絡種別)<BR>
     * 連絡種別<BR>
     */
    public String informType;
    
    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (口座番号)<BR>
     * 口座番号<BR>
     */
    public String accountNumber;
    
    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;
    
    /**
     * (区分１)<BR>
     * 区分１<BR>
     */
    public String div1;
    
    /**
     * (区分２)<BR>
     * 区分２
     */
    public String div2;
    
    /**
     * (区分３)<BR>
     * 区分３
     */
    public String div3;
    
    /**
     * (区分４)<BR>
     * 区分４
     */
    public String div4;
    
    /**
     * (区分５)<BR>
     * 区分５
     */
    public String div5;
    
    /**
     * (区分６)<BR>
     * 区分６
     */
    public String div6;
    
    /**
     * (区分７)<BR>
     * 区分７
     */
    public String div7;
    
    /**
     * (区分８)<BR>
     * 区分８
     */
    public String div8;
    
    /**
     * (区分９)<BR>
     * 区分９
     */
    public String div9;
    
    /**
     * (区分１０)<BR>
     * 区分１０
     */
    public String div10;
    
    /**
     * (区分１１)<BR>
     * 区分１１
     */
    public String div11;
    
    /**
     * (区分１２)<BR>
     * 区分１２
     */
    public String div12;
    
    /**
     * (区分１３)<BR>
     * 区分１３
     */
    public String div13;
    
    /**
     * (区分１４)<BR>
     * 区分１４
     */
    public String div14;
    
    /**
     * (区分１５)<BR>
     * 区分１５
     */
    public String div15;
    
    /**
     * (区分１６)<BR>
     * 区分１６
     */
    public String div16;
    
    /**
     * (区分１７)<BR>
     * 区分１７
     */
    public String div17;
    
    /**
     * (区分１８)<BR>
     * 区分１８
     */
    public String div18;
    
    /**
     * (区分１９)<BR>
     * 区分１９
     */
    public String div19;
    
    /**
     * (区分２０)<BR>
     * 区分２０
     */
    public String div20;
    
    /**
     * (区分２１)<BR>
     * 区分２１
     */
    public String div21;
    
    /**
     * (区分２２)<BR>
     * 区分２２
     */
    public String div22;
    
    /**
     * (区分２３)<BR>
     * 区分２３
     */
    public String div23;
    
    /**
     * (区分２４)<BR>
     * 区分２４
     */
    public String div24;
    
    /**
     * (区分２５)<BR>
     * 区分２５
     */
    public String div25;
    
    /**
     * (区分２６)<BR>
     * 区分２６
     */
    public String div26;
    
    /**
     * (区分２７)<BR>
     * 区分２７
     */
    public String div27;
    
    /**
     * (区分２８)<BR>
     * 区分２８
     */
    public String div28;
    
    /**
     * (区分２９)<BR>
     * 区分２９
     */
    public String div29;
    
    /**
     * (区分３０)<BR>
     * 区分３０
     */
    public String div30;
    
    /**
     * (区分３１)<BR>
     * 区分３１
     */
    public String div31;
    
    /**
     * (区分３２)<BR>
     * 区分３２
     */
    public String div32;
    
    /**
     * (区分３３)<BR>
     * 区分３３
     */
    public String div33;
    
    /**
     * (区分３４)<BR>
     * 区分３４
     */
    public String div34;
    
    /**
     * (区分３５)<BR>
     * 区分３５
     */
    public String div35;
    
    /**
     * (区分３６)<BR>
     * 区分３６
     */
    public String div36;
    
    /**
     * (区分３７)<BR>
     * 区分３７
     */
    public String div37;
    
    /**
     * (区分３８)<BR>
     * 区分３８
     */
    public String div38;
    
    /**
     * (区分３９)<BR>
     * 区分３９
     */
    public String div39;
    
    /**
     * (区分４０)<BR>
     * 区分４０
     */
    public String div40;
    
    /**
     * (コード１)<BR>
     * コード１
     */
    public String code1;
    
    /**
     * (コード２)<BR>
     * コード２
     */
    public String code2;
    
    /**
     * (コード３)<BR>
     * コード３
     */
    public String code3;
    
    /**
     * (コード４)<BR>
     * コード４
     */
    public String code4;
    
    /**
     * (コード５)<BR>
     * コード５
     */
    public String code5;
    
    /**
     * (コード６)<BR>
     * コード６
     */
    public String code6;
    
    /**
     * (コード７)<BR>
     * コード７
     */
    public String code7;
    
    /**
     * (コード８)<BR>
     * コード８
     */
    public String code8;
    
    /**
     * (コード９)<BR>
     * コード９
     */
    public String code9;
    
    /**
     * (コード１０)<BR>
     * コード１０
     */
    public String code10;
    
    /**
     * (テキスト１)<BR>
     * テキスト１
     */
    public String txt1;
    
    /**
     * (テキスト２)<BR>
     * テキスト２
     */
    public String txt2;
    
    /**
     * (テキスト３)<BR>
     * テキスト３
     */
    public String txt3;
    
    /**
     * (テキスト４)<BR>
     * テキスト４
     */
    public String txt4;
    
    /**
     * (テキスト５)<BR>
     * テキスト５
     */
    public String txt5;
    
    /**
     * (テキスト６)<BR>
     * テキスト６
     */
    public String txt6;
    
    /**
     * (テキスト７)<BR>
     * テキスト７
     */
    public String txt7;
    
    /**
     * (テキスト８)<BR>
     * テキスト８
     */
    public String txt8;
    
    /**
     * (テキスト９)<BR>
     * テキスト９
     */
    public String txt9;
    
    /**
     * (テキスト１０)<BR>
     * テキスト１０
     */
    public String txt10;
    
    /**
     * (テキスト１１)<BR>
     * テキスト１１
     */
    public String txt11;
    
    /**
     * (テキスト１２)<BR>
     * テキスト１２
     */
    public String txt12;
    
    /**
     * (テキスト１３)<BR>
     * テキスト１３
     */
    public String txt13;
    
    /**
     * (テキスト１４)<BR>
     * テキスト１４
     */
    public String txt14;
    
    /**
     * (テキスト１５)<BR>
     * テキスト１５
     */
    public String txt15;
    
    /**
     * (テキスト１６)<BR>
     * テキスト１６
     */
    public String txt16;
    
    /**
     * (テキスト１７)<BR>
     * テキスト１７
     */
    public String txt17;
    
    /**
     * (テキスト１８)<BR>
     * テキスト１８
     */
    public String txt18;
    
    /**
     * (テキスト１９)<BR>
     * テキスト１９
     */
    public String txt19;
    
    /**
     * (テキスト２０)<BR>
     * テキスト２０
     */
    public String txt20;
    
    /**
     * (テキスト２１)<BR>
     * テキスト２１
     */
    public String txt21;
    
    /**
     * (テキスト２２)<BR>
     * テキスト２２
     */
    public String txt22;
    
    /**
     * (テキスト２３)<BR>
     * テキスト２３
     */
    public String txt23;
    
    /**
     * (テキスト２４)<BR>
     * テキスト２４
     */
    public String txt24;
    
    /**
     * (テキスト２５)<BR>
     * テキスト２５
     */
    public String txt25;
    
    /**
     * (テキスト２６)<BR>
     * テキスト２６
     */
    public String txt26;
    
    /**
     * (テキスト２７)<BR>
     * テキスト２７
     */
    public String txt27;
    
    /**
     * (テキスト２８)<BR>
     * テキスト２８
     */
    public String txt28;
    
    /**
     * (テキスト２９)<BR>
     * テキスト２９
     */
    public String txt29;
    
    /**
     * (テキスト３０)<BR>
     * テキスト３０
     */
    public String txt30;
    
    /**
     * (テキスト３１)<BR>
     * テキスト３１
     */
    public String txt31;
    
    /**
     * (テキスト３２)<BR>
     * テキスト３２
     */
    public String txt32;
    
    /**
     * (テキスト３３)<BR>
     * テキスト３３
     */
    public String txt33;
    
    /**
     * (テキスト３４)<BR>
     * テキスト３４
     */
    public String txt34;
    
    /**
     * (テキスト３５)<BR>
     * テキスト３５
     */
    public String txt35;
    
    /**
     * (テキスト３６)<BR>
     * テキスト３６
     */
    public String txt36;
    
    /**
     * (テキスト３７)<BR>
     * テキスト３７
     */
    public String txt37;
    
    /**
     * (テキスト３８)<BR>
     * テキスト３８
     */
    public String txt38;
    
    /**
     * (テキスト３９)<BR>
     * テキスト３９
     */
    public String txt39;
    
    /**
     * (テキスト４０)<BR>
     * テキスト４０
     */
    public String txt40;
    
    /**
     * (数値１)<BR>
     * 数値１
     */
    public String num1;
    
    /**
     * (数値２)<BR>
     * 数値２
     */
    public String num2;
    
    /**
     * (数値３)<BR>
     * 数値３
     */
    public String num3;
    
    /**
     * (数値４)<BR>
     * 数値４
     */
    public String num4;
    
    /**
     * (数値５)<BR>
     * 数値５
     */
    public String num5;
    
    /**
     * (数値６)<BR>
     * 数値６
     */
    public String num6;
    
    /**
     * (数値７)<BR>
     * 数値７
     */
    public String num7;
    
    /**
     * (数値８)<BR>
     * 数値８
     */
    public String num8;
    
    /**
     * (数値９)<BR>
     * 数値９
     */
    public String num9;
    
    /**
     * (数値１０)<BR>
     * 数値１０
     */
    public String num10;
    
    /**
     * (数値１１)<BR>
     * 数値１１
     */
    public String num11;
    
    /**
     * (数値１２)<BR>
     * 数値１２
     */
    public String num12;
    
    /**
     * (数値１３)<BR>
     * 数値１３
     */
    public String num13;
    
    /**
     * (数値１４)<BR>
     * 数値１４
     */
    public String num14;
    
    /**
     * (数値１５)<BR>
     * 数値１５
     */
    public String num15;
    
    /**
     * (数値１６)<BR>
     * 数値１６
     */
    public String num16;
    
    /**
     * (数値１７)<BR>
     * 数値１７
     */
    public String num17;
    
    /**
     * (数値１８)<BR>
     * 数値１８
     */
    public String num18;
    
    /**
     * (数値１９)<BR>
     * 数値１９
     */
    public String num19;
    
    /**
     * (数値２０)<BR>
     * 数値２０
     */
    public String num20;
    
    /**
     * (数値２１)<BR>
     * 数値２１
     */
    public String num21;
    
    /**
     * (数値２２)<BR>
     * 数値２２
     */
    public String num22;
    
    /**
     * (数値２３)<BR>
     * 数値２３
     */
    public String num23;
    
    /**
     * (数値２４)<BR>
     * 数値２４
     */
    public String num24;
    
    /**
     * (数値２５)<BR>
     * 数値２５
     */
    public String num25;
    
    /**
     * (数値２６)<BR>
     * 数値２６
     */
    public String num26;
    
    /**
     * (数値２７)<BR>
     * 数値２７
     */
    public String num27;
    
    /**
     * (数値２８)<BR>
     * 数値２８
     */
    public String num28;
    
    /**
     * (数値２９)<BR>
     * 数値２９
     */
    public String num29;
    
    /**
     * (数値３０)<BR>
     * 数値３０
     */
    public String num30;
    
    /**
     * (備考１)<BR>
     * 備考１
     */
    public String remark1;
    
    /**
     * (備考２)<BR>
     * 備考２
     */
    public String remark2;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;

    /**
     * (扱者コード)<BR>
     * 扱者コード
     */
    public String traderCode;

    /**
     * (伝票作成情報)<BR>
     * 伝票作成情報
     */
    public String voucherInfo;

    /**
     * (エラー理由コード)<BR>
     * エラー理由コード
     */
    public String errorReasonCode;

    /**
     * (伝票識別コード)<BR>
     * 伝票識別コード
     */
    public String voucherRequestNumber;

    /**
     * (データコード)<BR>
     * データコード
     */
    public String dataCode;

    /**
     * (伝票送信日時)<BR>
     * 伝票送信日時
     */
    public Date voucherSendDate;

    /**
     * (伝票受信日時)<BR>
     * 伝票受信日時
     */
    public Date voucherRecvDate;

    /**
     * (各種連絡情報)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 各項目に、引数.各種連絡行オブジェクトの同項目の値をセットする。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行オブジェクト<BR>
     * @@roseuid 41C167D000BC
     */
    public WEB3InformDetailInfoUnit(VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3InformDetailInfoUnit()";
        log.entering(STR_METHOD_NAME);

        // 連絡種別
        this.informType = l_variousInformParams.getInformDiv();
    
        // 証券会社コード
        this.institutionCode = l_variousInformParams.getInstitutionCode();
    
        // 部店コード
        this.branchCode = l_variousInformParams.getBranchCode();
    
        log.debug("証券会社コード:" + this.institutionCode);
        log.debug("部店コード:" + this.branchCode);
        log.debug("口座コード(取出前):" + l_variousInformParams.getAccountCode());

        // 口座番号
        // 引数.各種連絡Params.顧客コードがnullでない場合、頭6桁をセットする。
        if (WEB3StringTypeUtility.isNotEmpty(l_variousInformParams.getAccountCode()))
        {
            this.accountNumber = l_variousInformParams.getAccountCode().substring(0, 6);
        }
        log.debug("口座コード(取出後):" + this.accountNumber);
    
        // 顧客名
        this.accountName = l_variousInformParams.getAccountName();
    
        // メールアドレス
        this.mailAddress = l_variousInformParams.getEmailAddress();
    
        // 区分１
        this.div1 = l_variousInformParams.getExtDiv1();
    
        // 区分２
        this.div2 = l_variousInformParams.getExtDiv2();
    
        // 区分３
        this.div3 = l_variousInformParams.getExtDiv3();
    
        // 区分４
        this.div4 = l_variousInformParams.getExtDiv4();
    
        // 区分５
        this.div5 = l_variousInformParams.getExtDiv5();
    
        // 区分６
        this.div6 = l_variousInformParams.getExtDiv6();

        // 区分７
        this.div7 = l_variousInformParams.getExtDiv7();
    
        // 区分８
        this.div8 = l_variousInformParams.getExtDiv8();
    
        // 区分９
        this.div9 = l_variousInformParams.getExtDiv9();

        // 区分１０
        this.div10 = l_variousInformParams.getExtDiv10();
    
        // 区分１１
        this.div11 = l_variousInformParams.getExtDiv11();
    
        // 区分１２
        this.div12 = l_variousInformParams.getExtDiv12();

        // 区分１３
        this.div13 = l_variousInformParams.getExtDiv13();
    
        // 区分１４
        this.div14 = l_variousInformParams.getExtDiv14();
    
        // 区分１５
        this.div15 = l_variousInformParams.getExtDiv15();
    
        // 区分１６
        this.div16 = l_variousInformParams.getExtDiv16();

        // 区分１７
        this.div17 = l_variousInformParams.getExtDiv17();
    
        // 区分１８
        this.div18 = l_variousInformParams.getExtDiv18();
    
        // 区分１９
        this.div19 = l_variousInformParams.getExtDiv19();
    
        // 区分２０
        this.div20 = l_variousInformParams.getExtDiv20();
    
        // 区分２１
        this.div21 = l_variousInformParams.getExtDiv21();
    
        // 区分２２
        this.div22 = l_variousInformParams.getExtDiv22();
    
        // 区分２３
        this.div23 = l_variousInformParams.getExtDiv23();
    
        // 区分２４
        this.div24 = l_variousInformParams.getExtDiv24();
    
        // 区分２５
        this.div25 = l_variousInformParams.getExtDiv25();
    
        // 区分２６
        this.div26 = l_variousInformParams.getExtDiv26();
    
        // 区分２７
        this.div27 = l_variousInformParams.getExtDiv27();
    
        // 区分２８
        this.div28 = l_variousInformParams.getExtDiv28();
    
        // 区分２９
        this.div29 = l_variousInformParams.getExtDiv29();
    
        // 区分３０
        this.div30 = l_variousInformParams.getExtDiv30();

        // 区分３１
        this.div31 = l_variousInformParams.getExtDiv31();

        // 区分３２
        this.div32 = l_variousInformParams.getExtDiv32();
    
        // 区分３３
        this.div33 = l_variousInformParams.getExtDiv33();
    
        // 区分３４
        this.div34 = l_variousInformParams.getExtDiv34();
    
        // 区分３５
        this.div35 = l_variousInformParams.getExtDiv35();
    
        // 区分３６
        this.div36 = l_variousInformParams.getExtDiv36();
    
        // 区分３７
        this.div37 = l_variousInformParams.getExtDiv37();
    
        // 区分３８
        this.div38 = l_variousInformParams.getExtDiv38();
    
        // 区分３９
        this.div39 = l_variousInformParams.getExtDiv39();
    
        // 区分４０
        this.div40 = l_variousInformParams.getExtDiv40();
    
        // コード１
        this.code1 = l_variousInformParams.getExtCode1();
    
        // コード２
        this.code2 = l_variousInformParams.getExtCode2();
    
        // コード３
        this.code3 = l_variousInformParams.getExtCode3();
    
        // コード４
        this.code4 = l_variousInformParams.getExtCode4();
    
        // コード５
        this.code5 = l_variousInformParams.getExtCode5();
    
        // コード６
        this.code6 = l_variousInformParams.getExtCode6();
    
        // コード７
        this.code7 = l_variousInformParams.getExtCode7();
    
        // コード８
        this.code8 = l_variousInformParams.getExtCode8();
    
        // コード９
        this.code9 = l_variousInformParams.getExtCode9();
    
        // コード１０
        this.code10 = l_variousInformParams.getExtCode10();
    
        // テキスト１
        this.txt1 = l_variousInformParams.getExtText1();
    
        // テキスト２
        this.txt2 = l_variousInformParams.getExtText2();
    
        // テキスト３
        this.txt3 = l_variousInformParams.getExtText3();
    
        // テキスト４
        this.txt4 = l_variousInformParams.getExtText4();
    
        // テキスト５
        this.txt5 = l_variousInformParams.getExtText5();
    
        // テキスト６
        this.txt6 = l_variousInformParams.getExtText6();
    
        // テキスト７
        this.txt7 = l_variousInformParams.getExtText7();
    
        // テキスト８
        this.txt8 = l_variousInformParams.getExtText8();
    
        // テキスト９
        this.txt9 = l_variousInformParams.getExtText9();
    
        // テキスト１０
        this.txt10 = l_variousInformParams.getExtText10();
    
        // テキスト１１
        this.txt11 = l_variousInformParams.getExtText11();
    
        // テキスト１２
        this.txt12 = l_variousInformParams.getExtText12();
    
        // テキスト１３
        this.txt13 = l_variousInformParams.getExtText13();
    
        // テキスト１４
        this.txt14 = l_variousInformParams.getExtText14();
    
        // テキスト１５
        this.txt15 = l_variousInformParams.getExtText15();
    
        // テキスト１６
        this.txt16 = l_variousInformParams.getExtText16();
    
        // テキスト１７
        this.txt17 = l_variousInformParams.getExtText17();
    
        // テキスト１８
        this.txt18 = l_variousInformParams.getExtText18();
    
        // テキスト１９
        this.txt19 = l_variousInformParams.getExtText19();
    
        // テキスト２０
        this.txt20 = l_variousInformParams.getExtText20();
    
        // テキスト２１
        this.txt21 = l_variousInformParams.getExtText21();
    
        // テキスト２２
        this.txt22 = l_variousInformParams.getExtText22();
    
        // テキスト２３
        this.txt23 = l_variousInformParams.getExtText23();
    
        // テキスト２４
        this.txt24 = l_variousInformParams.getExtText24();
    
        // テキスト２５
        this.txt25 = l_variousInformParams.getExtText25();
    
        // テキスト２６
        this.txt26 = l_variousInformParams.getExtText26();
    
        // テキスト２７
        this.txt27 = l_variousInformParams.getExtText27();
    
        // テキスト２８
        this.txt28 = l_variousInformParams.getExtText28();
    
        // テキスト２９
        this.txt29 = l_variousInformParams.getExtText29();
    
        // テキスト３０
        this.txt30 = l_variousInformParams.getExtText30();
    
        // テキスト３１
        this.txt31 = l_variousInformParams.getExtText31();
    
        // テキスト３２
        this.txt32 = l_variousInformParams.getExtText32();
    
        // テキスト３３
        this.txt33 = l_variousInformParams.getExtText33();
    
        // テキスト３４
        this.txt34 = l_variousInformParams.getExtText34();
    
        // テキスト３５
        this.txt35 = l_variousInformParams.getExtText35();
    
        // テキスト３６
        this.txt36 = l_variousInformParams.getExtText36();
    
        // テキスト３７
        this.txt37 = l_variousInformParams.getExtText37();
    
        // テキスト３８
        this.txt38 = l_variousInformParams.getExtText38();
    
        // テキスト３９
        this.txt39 = l_variousInformParams.getExtText39();
    
        // テキスト４０
        this.txt40 = l_variousInformParams.getExtText40();
    
        // 数値１
        if (!l_variousInformParams.getExtValue1IsNull())
        {
            this.num1 = "" + l_variousInformParams.getExtValue1();
        }
    
        // 数値２
        if (!l_variousInformParams.getExtValue2IsNull())
        {
            this.num2 = "" + l_variousInformParams.getExtValue2();
        }    
    
        // 数値３
        if (!l_variousInformParams.getExtValue3IsNull())
        {
            this.num3 = "" + l_variousInformParams.getExtValue3();
        }    
    
        // 数値４
        if (!l_variousInformParams.getExtValue4IsNull())
        {
            this.num4 = "" + l_variousInformParams.getExtValue4();
        }    
    
        // 数値５
        if (!l_variousInformParams.getExtValue5IsNull())
        {
            this.num5 = "" + l_variousInformParams.getExtValue5();
        }    
    
        // 数値６
        if (!l_variousInformParams.getExtValue6IsNull())
        {
            this.num6 = "" + l_variousInformParams.getExtValue6();
        }    
    
        // 数値７
        if (!l_variousInformParams.getExtValue7IsNull())
        {
            this.num7 = "" + l_variousInformParams.getExtValue7();
        }    
    
        // 数値８
        if (!l_variousInformParams.getExtValue8IsNull())
        {
            this.num8 = "" + l_variousInformParams.getExtValue8();
        }    
    
        // 数値９
        if (!l_variousInformParams.getExtValue9IsNull())
        {
            this.num9 = "" + l_variousInformParams.getExtValue9();
        }    
    
        // 数値１０
        if (!l_variousInformParams.getExtValue10IsNull())
        {
            this.num10 = "" + l_variousInformParams.getExtValue10();
        }    
    
        // 数値１１
        if (!l_variousInformParams.getExtValue11IsNull())
        {
            this.num11 = "" + l_variousInformParams.getExtValue11();
        }    
    
        // 数値１２
        if (!l_variousInformParams.getExtValue12IsNull())
        {
            this.num12 = "" + l_variousInformParams.getExtValue12();
        }    
    
        // 数値１３
        if (!l_variousInformParams.getExtValue13IsNull())
        {
            this.num13 = "" + l_variousInformParams.getExtValue13();
        }    
    
        // 数値１４
        if (!l_variousInformParams.getExtValue14IsNull())
        {
            this.num14 = "" + l_variousInformParams.getExtValue14();
        }    
    
        // 数値１５
        if (!l_variousInformParams.getExtValue15IsNull())
        {
            this.num15 = "" + l_variousInformParams.getExtValue15();
        }    
    
        // 数値１６
        if (!l_variousInformParams.getExtValue16IsNull())
        {
            this.num16 = "" + l_variousInformParams.getExtValue16();
        }    
    
        // 数値１７
        if (!l_variousInformParams.getExtValue17IsNull())
        {
            this.num17 = "" + l_variousInformParams.getExtValue17();
        }    
    
        // 数値１８
        if (!l_variousInformParams.getExtValue18IsNull())
        {
            this.num18 = "" + l_variousInformParams.getExtValue18();
        }    
    
        // 数値１９
        if (!l_variousInformParams.getExtValue19IsNull())
        {
            this.num19 = "" + l_variousInformParams.getExtValue19();
        }    
    
        // 数値２０
        if (!l_variousInformParams.getExtValue20IsNull())
        {
            this.num20 = "" + l_variousInformParams.getExtValue20();
        }    
    
        // 数値２１
        if (!l_variousInformParams.getExtValue21IsNull())
        {
            this.num21 = "" + l_variousInformParams.getExtValue21();
        }    
    
        // 数値２２
        if (!l_variousInformParams.getExtValue22IsNull())
        {
            this.num22 = "" + l_variousInformParams.getExtValue22();
        }    
    
        // 数値２３
        if (!l_variousInformParams.getExtValue23IsNull())
        {
            this.num23 = "" + l_variousInformParams.getExtValue23();
        }    
    
        // 数値２４
        if (!l_variousInformParams.getExtValue24IsNull())
        {
            this.num24 = "" + l_variousInformParams.getExtValue24();
        }    
    
        // 数値２５
        if (!l_variousInformParams.getExtValue25IsNull())
        {
            this.num25 = "" + l_variousInformParams.getExtValue25();
        }    
    
        // 数値２６
        if (!l_variousInformParams.getExtValue26IsNull())
        {
            this.num26 = "" + l_variousInformParams.getExtValue26();
        }    
    
        // 数値２７
        if (!l_variousInformParams.getExtValue27IsNull())
        {
            this.num27 = "" + l_variousInformParams.getExtValue27();
        }    
    
        // 数値２８
        if (!l_variousInformParams.getExtValue28IsNull())
        {
            this.num28 = "" + l_variousInformParams.getExtValue28();
        }    
    
        // 数値２９
        if (!l_variousInformParams.getExtValue29IsNull())
        {
            this.num29 = "" + l_variousInformParams.getExtValue29();
        }    
    
        // 数値３０
        if (!l_variousInformParams.getExtValue30IsNull())
        {
            this.num30 = "" + l_variousInformParams.getExtValue30();
        }    

        // 備考１
        this.remark1 = l_variousInformParams.getExtNote1();
    
        // 備考２
        this.remark2 = l_variousInformParams.getExtNote2();

        // 銘柄コード
        this.productCode = l_variousInformParams.getFundCode();

        // 扱者コード
        this.traderCode = l_variousInformParams.getSonarTraderCode();

        // 伝票作成情報
        this.voucherInfo = l_variousInformParams.getStatus();

        // エラー理由コード
        this.errorReasonCode = l_variousInformParams.getErrorReasonCode();

        // 伝票識別コード
        this.voucherRequestNumber = l_variousInformParams.getOrderRequestNumber();

        // データコード
        this.dataCode = l_variousInformParams.getRequestCode();

        // 伝票送信日時
        this.voucherSendDate = l_variousInformParams.getSendTimestamp();

        // 伝票受信日時
        this.voucherRecvDate = l_variousInformParams.getReceiptTimestamp();
     
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * データの整合性をチェックする。<BR>
     * <BR>
     * １）this.連絡種別 == null の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01817<BR>
     * <BR>
     * ２）this.証券会社コード == null の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00827<BR>
     * <BR>
     * ３）this.数値１～this.数値３０の項目が以下の条件の場合、<BR>
     *      例外をスローする。<BR>
     *      項目がnullでない and 数字以外がセットされている<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01947<BR>
     * <BR>
     * @@roseuid 41C167D000BC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // this.連絡種別 == null の場合、例外をスローする
        if (this.informType == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + STR_METHOD_NAME,
                "連絡情報.連絡種別がnullの値である。");
        }

        // ２）this.証券会社コード == null の場合、例外をスローする
        if (this.institutionCode == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }

        // ３）this.数値１～this.数値３０の項目が以下の条件の場合、
        //     例外をスローする
        if (this.num1 != null 
            && !WEB3StringTypeUtility.isDigit(this.num1))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１項目が半角数字以外の値です。");
        }

        if (this.num2 != null 
            && !WEB3StringTypeUtility.isDigit(this.num2))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２項目が半角数字以外の値です。");
        }

        if (this.num3 != null 
            && !WEB3StringTypeUtility.isDigit(this.num3))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値３項目が半角数字以外の値です。");
        }

        if (this.num4 != null 
            && !WEB3StringTypeUtility.isDigit(this.num4))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値４項目が半角数字以外の値です。");
        }

        if (this.num5 != null 
            && !WEB3StringTypeUtility.isDigit(this.num5))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値５項目が半角数字以外の値です。");
        }

        if (this.num6 != null 
            && !WEB3StringTypeUtility.isDigit(this.num6))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値６項目が半角数字以外の値です。");
        }

        if (this.num7 != null 
            && !WEB3StringTypeUtility.isDigit(this.num7))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値７項目が半角数字以外の値です。");
        }

        if (this.num8 != null 
            && !WEB3StringTypeUtility.isDigit(this.num8))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値８項目が半角数字以外の値です。");
        }

        if (this.num9 != null 
            && !WEB3StringTypeUtility.isDigit(this.num9))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値９項目が半角数字以外の値です。");
        }

        if (this.num10 != null 
            && !WEB3StringTypeUtility.isDigit(this.num10))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１０項目が半角数字以外の値です。");
        }

        if (this.num11 != null 
            && !WEB3StringTypeUtility.isDigit(this.num11))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１１項目が半角数字以外の値です。");
        }

        if (this.num12 != null 
            && !WEB3StringTypeUtility.isDigit(this.num12))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１２項目が半角数字以外の値です。");
        }

        if (this.num13 != null 
            && !WEB3StringTypeUtility.isDigit(this.num13))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１３項目が半角数字以外の値です。");
        }

        if (this.num14 != null 
            && !WEB3StringTypeUtility.isDigit(this.num14))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１４項目が半角数字以外の値です。");
        }

        if (this.num15 != null 
            && !WEB3StringTypeUtility.isDigit(this.num15))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１５項目が半角数字以外の値です。");
        }

        if (this.num16 != null 
            && !WEB3StringTypeUtility.isDigit(this.num16))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１６項目が半角数字以外の値です。");
        }

        if (this.num17 != null 
            && !WEB3StringTypeUtility.isDigit(this.num17))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１７項目が半角数字以外の値です。");
        }

        if (this.num18 != null 
            && !WEB3StringTypeUtility.isDigit(this.num18))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１８項目が半角数字以外の値です。");
        }

        if (this.num19 != null 
            && !WEB3StringTypeUtility.isDigit(this.num19))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値１９項目が半角数字以外の値です。");
        }

        if (this.num20 != null 
            && !WEB3StringTypeUtility.isDigit(this.num20))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２０項目が半角数字以外の値です。");
        }

        if (this.num21 != null 
            && !WEB3StringTypeUtility.isDigit(this.num21))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２１項目が半角数字以外の値です。");
        }

        if (this.num22 != null 
            && !WEB3StringTypeUtility.isDigit(this.num22))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２２項目が半角数字以外の値です。");
        }

        if (this.num23 != null 
            && !WEB3StringTypeUtility.isDigit(this.num23))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２３項目が半角数字以外の値です。");
        }

        if (this.num24 != null 
            && !WEB3StringTypeUtility.isDigit(this.num24))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２４項目が半角数字以外の値です。");
        }

        if (this.num25 != null 
            && !WEB3StringTypeUtility.isDigit(this.num25))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２５項目が半角数字以外の値です。");
        }

        if (this.num26 != null 
            && !WEB3StringTypeUtility.isDigit(this.num26))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２６項目が半角数字以外の値です。");
        }

        if (this.num27 != null 
            && !WEB3StringTypeUtility.isDigit(this.num27))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２７項目が半角数字以外の値です。");
        }

        if (this.num28 != null 
            && !WEB3StringTypeUtility.isDigit(this.num28))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２８項目が半角数字以外の値です。");
        }

        if (this.num29 != null 
            && !WEB3StringTypeUtility.isDigit(this.num29))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値２９項目が半角数字以外の値です。");
        }

        if (this.num30 != null 
            && !WEB3StringTypeUtility.isDigit(this.num30))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01947,
                this.getClass().getName() + STR_METHOD_NAME,
                "入力の数値３０項目が半角数字以外の値です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (各種連絡情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 41BE80DA0161
     */
    public WEB3InformDetailInfoUnit() 
    {
     
    }
}
@
