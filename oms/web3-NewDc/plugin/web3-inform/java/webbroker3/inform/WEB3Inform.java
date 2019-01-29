head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3Inform.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種連絡(WEB3Inform.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
Revesion History : 2007/06/06 謝旋(中訊) 修正 モデルNo.056、No.068
Revesion History : 2007/06/08 徐宏偉(中訊) 修正 モデルNo.077
Revesion History : 2007/06/19 周墨洋(中訊) 修正
Revesion History : 2008/02/19 柴双紅(中訊) モデルNo.122
*/
package webbroker3.inform;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.bd.WEB3BondProductManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3ExtDiv1Def;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.inform.data.VariousInformPK;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3InformAddInfoUnit;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.inform.util.WEB3InformColumnSpec;
import webbroker3.inform.util.WEB3InformTableSpec;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (各種連絡)<BR>
 * 各種連絡クラス<BR>
 */
public class WEB3Inform implements BusinessObject
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3Inform.class);
    /**
     * (各種連絡行)<BR>
     * 各種連絡行オブジェクト<BR>
     */
    private VariousInformParams variousInformParams;

    /**
     * @@roseuid 41EE642C0177
     */
    public WEB3Inform()
    {

    }

    /**
     * (各種連絡)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）空の各種連絡行オブジェクトを生成する。<BR>
     * <BR>
     * ２）引数.各種連絡情報の各項目の値を各種連絡行オブジェクトの同項目にセットする。<BR>
     * <BR>
     * ３）行オブジェクトをthis.各種連絡行にセットする。<BR>
     * @@param l_informDetailInfoUnit - (連絡情報)
     * 各種連絡情報オブジェクト
     * @@roseuid 41BD39FE0053
     */
    public WEB3Inform(WEB3InformDetailInfoUnit l_informDetailInfoUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3Inform(WEB3InformDetailInfoUnit l_informDetailInfoUnit)";
        log.entering(STR_METHOD_NAME);
        VariousInformParams l_variousInformParams = new VariousInformParams();
        // 連絡種別
        l_variousInformParams.setInformDiv(l_informDetailInfoUnit.informType);

        // 証券会社コード
        l_variousInformParams.setInstitutionCode(l_informDetailInfoUnit.institutionCode);

        // 部店コード
        l_variousInformParams.setBranchCode(l_informDetailInfoUnit.branchCode);

        // 口座番号
        l_variousInformParams.setAccountCode(l_informDetailInfoUnit.accountNumber);

        // 顧客名
        l_variousInformParams.setAccountName(l_informDetailInfoUnit.accountName);

        // メールアドレス
        l_variousInformParams.setEmailAddress(l_informDetailInfoUnit.mailAddress);

        // 区分１
        l_variousInformParams.setExtDiv1(l_informDetailInfoUnit.div1);

        // 区分２
        l_variousInformParams.setExtDiv2(l_informDetailInfoUnit.div2);

        // 区分３
        l_variousInformParams.setExtDiv3(l_informDetailInfoUnit.div3);

        // 区分４
        l_variousInformParams.setExtDiv4(l_informDetailInfoUnit.div4);

        // 区分５
        l_variousInformParams.setExtDiv5(l_informDetailInfoUnit.div5);

        // 区分６
        l_variousInformParams.setExtDiv6(l_informDetailInfoUnit.div6);

        // 区分７
        l_variousInformParams.setExtDiv7(l_informDetailInfoUnit.div7);

        // 区分８
        l_variousInformParams.setExtDiv8(l_informDetailInfoUnit.div8);

        // 区分９
        l_variousInformParams.setExtDiv9(l_informDetailInfoUnit.div9);

        // 区分１０
        l_variousInformParams.setExtDiv10(l_informDetailInfoUnit.div10);

        // 区分１１
        l_variousInformParams.setExtDiv11(l_informDetailInfoUnit.div11);

        // 区分１２
        l_variousInformParams.setExtDiv12(l_informDetailInfoUnit.div12);

        // 区分１３
        l_variousInformParams.setExtDiv13(l_informDetailInfoUnit.div13);

        // 区分１４
        l_variousInformParams.setExtDiv14(l_informDetailInfoUnit.div14);

        // 区分１５
        l_variousInformParams.setExtDiv15(l_informDetailInfoUnit.div15);

        // 区分１６
        l_variousInformParams.setExtDiv16(l_informDetailInfoUnit.div16);

        // 区分１７
        l_variousInformParams.setExtDiv17(l_informDetailInfoUnit.div17);

        // 区分１８
        l_variousInformParams.setExtDiv18(l_informDetailInfoUnit.div18);

        // 区分１９
        l_variousInformParams.setExtDiv19(l_informDetailInfoUnit.div19);

        // 区分２０
        l_variousInformParams.setExtDiv20(l_informDetailInfoUnit.div20);

        // 区分２１
        l_variousInformParams.setExtDiv21(l_informDetailInfoUnit.div21);

        // 区分２２
        l_variousInformParams.setExtDiv22(l_informDetailInfoUnit.div22);

        // 区分２３
        l_variousInformParams.setExtDiv23(l_informDetailInfoUnit.div23);

        // 区分２４
        l_variousInformParams.setExtDiv24(l_informDetailInfoUnit.div24);

        // 区分２５
        l_variousInformParams.setExtDiv25(l_informDetailInfoUnit.div25);

        // 区分２６
        l_variousInformParams.setExtDiv26(l_informDetailInfoUnit.div26);

        // 区分２７
        l_variousInformParams.setExtDiv27(l_informDetailInfoUnit.div27);

        // 区分２８
        l_variousInformParams.setExtDiv28(l_informDetailInfoUnit.div28);

        // 区分２９
        l_variousInformParams.setExtDiv29(l_informDetailInfoUnit.div29);

        // 区分３０
        l_variousInformParams.setExtDiv30(l_informDetailInfoUnit.div30);

        // 区分３１
        l_variousInformParams.setExtDiv31(l_informDetailInfoUnit.div31);

        // 区分３２
        l_variousInformParams.setExtDiv32(l_informDetailInfoUnit.div32);

        // 区分３３
        l_variousInformParams.setExtDiv33(l_informDetailInfoUnit.div33);

        // 区分３４
        l_variousInformParams.setExtDiv34(l_informDetailInfoUnit.div34);

        // 区分３５
        l_variousInformParams.setExtDiv35(l_informDetailInfoUnit.div35);

        // 区分３６
        l_variousInformParams.setExtDiv36(l_informDetailInfoUnit.div36);

        // 区分３７
        l_variousInformParams.setExtDiv37(l_informDetailInfoUnit.div37);

        // 区分３８
        l_variousInformParams.setExtDiv38(l_informDetailInfoUnit.div38);

        // 区分３９
        l_variousInformParams.setExtDiv39(l_informDetailInfoUnit.div39);

        // 区分４０
        l_variousInformParams.setExtDiv40(l_informDetailInfoUnit.div40);

        // コード１
        l_variousInformParams.setExtCode1(l_informDetailInfoUnit.code1);

        // コード２
        l_variousInformParams.setExtCode2(l_informDetailInfoUnit.code2);

        // コード３
        l_variousInformParams.setExtCode3(l_informDetailInfoUnit.code3);

        // コード４
        l_variousInformParams.setExtCode4(l_informDetailInfoUnit.code4);

        // コード５
        l_variousInformParams.setExtCode5(l_informDetailInfoUnit.code5);

        // コード６
        l_variousInformParams.setExtCode6(l_informDetailInfoUnit.code6);

        // コード７
        l_variousInformParams.setExtCode7(l_informDetailInfoUnit.code7);

        // コード８
        l_variousInformParams.setExtCode8(l_informDetailInfoUnit.code8);

        // コード９
        l_variousInformParams.setExtCode9(l_informDetailInfoUnit.code9);

        // コード１０
        l_variousInformParams.setExtCode10(l_informDetailInfoUnit.code10);

        // テキスト１
        l_variousInformParams.setExtText1(l_informDetailInfoUnit.txt1);

        // テキスト２
        l_variousInformParams.setExtText2(l_informDetailInfoUnit.txt2);

        // テキスト３
        l_variousInformParams.setExtText3(l_informDetailInfoUnit.txt3);

        // テキスト４
        l_variousInformParams.setExtText4(l_informDetailInfoUnit.txt4);

        // テキスト５
        l_variousInformParams.setExtText5(l_informDetailInfoUnit.txt5);

        // テキスト６
        l_variousInformParams.setExtText6(l_informDetailInfoUnit.txt6);

        // テキスト７
        l_variousInformParams.setExtText7(l_informDetailInfoUnit.txt7);

        // テキスト８
        l_variousInformParams.setExtText8(l_informDetailInfoUnit.txt8);

        // テキスト９
        l_variousInformParams.setExtText9(l_informDetailInfoUnit.txt9);

        // テキスト１０
        l_variousInformParams.setExtText10(l_informDetailInfoUnit.txt10);

        // テキスト１１
        l_variousInformParams.setExtText11(l_informDetailInfoUnit.txt11);

        // テキスト１２
        l_variousInformParams.setExtText12(l_informDetailInfoUnit.txt12);

        // テキスト１３
        l_variousInformParams.setExtText13(l_informDetailInfoUnit.txt13);

        // テキスト１４
        l_variousInformParams.setExtText14(l_informDetailInfoUnit.txt14);

        // テキスト１５
        l_variousInformParams.setExtText15(l_informDetailInfoUnit.txt15);

        // テキスト１６
        l_variousInformParams.setExtText16(l_informDetailInfoUnit.txt16);

        // テキスト１７
        l_variousInformParams.setExtText17(l_informDetailInfoUnit.txt17);

        // テキスト１８
        l_variousInformParams.setExtText18(l_informDetailInfoUnit.txt18);

        // テキスト１９
        l_variousInformParams.setExtText19(l_informDetailInfoUnit.txt19);

        // テキスト２０
        l_variousInformParams.setExtText20(l_informDetailInfoUnit.txt20);

        // テキスト２１
        l_variousInformParams.setExtText21(l_informDetailInfoUnit.txt21);

        // テキスト２２
        l_variousInformParams.setExtText22(l_informDetailInfoUnit.txt22);

        // テキスト２３
        l_variousInformParams.setExtText23(l_informDetailInfoUnit.txt23);

        // テキスト２４
        l_variousInformParams.setExtText24(l_informDetailInfoUnit.txt24);

        // テキスト２５
        l_variousInformParams.setExtText25(l_informDetailInfoUnit.txt25);

        // テキスト２６
        l_variousInformParams.setExtText26(l_informDetailInfoUnit.txt26);

        // テキスト２７
        l_variousInformParams.setExtText27(l_informDetailInfoUnit.txt27);

        // テキスト２８
        l_variousInformParams.setExtText28(l_informDetailInfoUnit.txt28);

        // テキスト２９
        l_variousInformParams.setExtText29(l_informDetailInfoUnit.txt29);

        // テキスト３０
        l_variousInformParams.setExtText30(l_informDetailInfoUnit.txt30);

        // テキスト３１
        l_variousInformParams.setExtText31(l_informDetailInfoUnit.txt31);

        // テキスト３２
        l_variousInformParams.setExtText32(l_informDetailInfoUnit.txt32);

        // テキスト３３
        l_variousInformParams.setExtText33(l_informDetailInfoUnit.txt33);

        // テキスト３４
        l_variousInformParams.setExtText34(l_informDetailInfoUnit.txt34);

        // テキスト３５
        l_variousInformParams.setExtText35(l_informDetailInfoUnit.txt35);

        // テキスト３６
        l_variousInformParams.setExtText36(l_informDetailInfoUnit.txt36);

        // テキスト３７
        l_variousInformParams.setExtText37(l_informDetailInfoUnit.txt37);

        // テキスト３８
        l_variousInformParams.setExtText38(l_informDetailInfoUnit.txt38);

        // テキスト３９
        l_variousInformParams.setExtText39(l_informDetailInfoUnit.txt39);

        // テキスト４０
        l_variousInformParams.setExtText40(l_informDetailInfoUnit.txt40);

        // 数値１
        if (l_informDetailInfoUnit.num1 != null)
        {
            l_variousInformParams.setExtValue1(Long.parseLong(l_informDetailInfoUnit.num1));
        }
                


        // 数値２
        if (l_informDetailInfoUnit.num2 != null)
        {
            l_variousInformParams.setExtValue2(Long.parseLong(l_informDetailInfoUnit.num2));
        }


        // 数値３
        if (l_informDetailInfoUnit.num3 != null)
        {
            l_variousInformParams.setExtValue3(Long.parseLong(l_informDetailInfoUnit.num3));
        }


        // 数値４
        if (l_informDetailInfoUnit.num4 != null)
        {
            l_variousInformParams.setExtValue4(Long.parseLong(l_informDetailInfoUnit.num4));
        }

        // 数値５
        if (l_informDetailInfoUnit.num5 != null)
        {
            l_variousInformParams.setExtValue5(Long.parseLong(l_informDetailInfoUnit.num5));
        }
        // 数値６
        if (l_informDetailInfoUnit.num6 != null)
        {
            l_variousInformParams.setExtValue6(Long.parseLong(l_informDetailInfoUnit.num6));
        }
        // 数値７
        if (l_informDetailInfoUnit.num7 != null)
        {
            l_variousInformParams.setExtValue7(Long.parseLong(l_informDetailInfoUnit.num7));
        }
        // 数値８
        if (l_informDetailInfoUnit.num8 != null)
        {
            l_variousInformParams.setExtValue8(Long.parseLong(l_informDetailInfoUnit.num8));
        }
        // 数値９
        if (l_informDetailInfoUnit.num9 != null)
        {
            l_variousInformParams.setExtValue9(Long.parseLong(l_informDetailInfoUnit.num9));
        }
        // 数値１０
        if (l_informDetailInfoUnit.num10 != null)
        {
            l_variousInformParams.setExtValue10(Long.parseLong(l_informDetailInfoUnit.num10));
        }
        // 数値１１
        if (l_informDetailInfoUnit.num11 != null)
        {
            l_variousInformParams.setExtValue11(Long.parseLong(l_informDetailInfoUnit.num11));
        }
        // 数値１２
        if (l_informDetailInfoUnit.num12 != null)
        {
            l_variousInformParams.setExtValue12(Long.parseLong(l_informDetailInfoUnit.num12));
        }
        // 数値１３
        if (l_informDetailInfoUnit.num13 != null)
        {
            l_variousInformParams.setExtValue13(Long.parseLong(l_informDetailInfoUnit.num13));
        }
        // 数値１４
        if (l_informDetailInfoUnit.num14 != null)
        {
            l_variousInformParams.setExtValue14(Long.parseLong(l_informDetailInfoUnit.num14));
        }
        // 数値１５
        if (l_informDetailInfoUnit.num15 != null)
        {
            l_variousInformParams.setExtValue15(Long.parseLong(l_informDetailInfoUnit.num15));
        }
        // 数値１６
        if (l_informDetailInfoUnit.num16 != null)
        {
            l_variousInformParams.setExtValue16(Long.parseLong(l_informDetailInfoUnit.num16));
        }
        // 数値１７
        if (l_informDetailInfoUnit.num17 != null)
        {
            l_variousInformParams.setExtValue17(Long.parseLong(l_informDetailInfoUnit.num17));
        }
        // 数値１８
        if (l_informDetailInfoUnit.num18 != null)
        {
            l_variousInformParams.setExtValue18(Long.parseLong(l_informDetailInfoUnit.num18));
        }
        // 数値１９
        if (l_informDetailInfoUnit.num19 != null)
        {
            l_variousInformParams.setExtValue19(Long.parseLong(l_informDetailInfoUnit.num19));
        }
        // 数値２０
        if (l_informDetailInfoUnit.num20 != null)
        {
            l_variousInformParams.setExtValue20(Long.parseLong(l_informDetailInfoUnit.num20));
        }
        // 数値２１
        if (l_informDetailInfoUnit.num21 != null)
        {
            l_variousInformParams.setExtValue21(Long.parseLong(l_informDetailInfoUnit.num21));
        }
        // 数値２２
        if (l_informDetailInfoUnit.num22 != null)
        {
            l_variousInformParams.setExtValue22(Long.parseLong(l_informDetailInfoUnit.num22));
        }
        // 数値２３
        if (l_informDetailInfoUnit.num23 != null)
        {
            l_variousInformParams.setExtValue23(Long.parseLong(l_informDetailInfoUnit.num23));
        }
        // 数値２４
        if (l_informDetailInfoUnit.num24 != null)
        {
            l_variousInformParams.setExtValue24(Long.parseLong(l_informDetailInfoUnit.num24));
        }
        // 数値２５
        if (l_informDetailInfoUnit.num25 != null)
        {
            l_variousInformParams.setExtValue25(Long.parseLong(l_informDetailInfoUnit.num25));
        }
        // 数値２６
        if (l_informDetailInfoUnit.num26 != null)
        {
            l_variousInformParams.setExtValue26(Long.parseLong(l_informDetailInfoUnit.num26));
        }
        // 数値２７
        if (l_informDetailInfoUnit.num27 != null)
        {
            l_variousInformParams.setExtValue27(Long.parseLong(l_informDetailInfoUnit.num27));
        }
        // 数値２８
        if (l_informDetailInfoUnit.num28 != null)
        {
            l_variousInformParams.setExtValue28(Long.parseLong(l_informDetailInfoUnit.num28));
        }
        // 数値２９
        if (l_informDetailInfoUnit.num29 != null)
        {
            l_variousInformParams.setExtValue29(Long.parseLong(l_informDetailInfoUnit.num29));
        }
        // 数値３０
        if (l_informDetailInfoUnit.num30 != null)
        {
            l_variousInformParams.setExtValue30(Long.parseLong(l_informDetailInfoUnit.num30));
        }
        // 備考１
        l_variousInformParams.setExtNote1(l_informDetailInfoUnit.remark1);

        // 備考２
        l_variousInformParams.setExtNote2(l_informDetailInfoUnit.remark2);

        // 銘柄コード
        l_variousInformParams.setFundCode(l_informDetailInfoUnit.productCode);

        // 扱者コード（SONAR）
        l_variousInformParams.setSonarTraderCode(l_informDetailInfoUnit.traderCode);

        // 伝票作成状況
        l_variousInformParams.setStatus(l_informDetailInfoUnit.voucherInfo);

        // エラー理由コード
        l_variousInformParams.setErrorReasonCode(l_informDetailInfoUnit.errorReasonCode);

        // 伝票識別コード
        l_variousInformParams.setOrderRequestNumber(l_informDetailInfoUnit.voucherRequestNumber);

        // データコード
        l_variousInformParams.setRequestCode(l_informDetailInfoUnit.dataCode);

        // 伝票送信日時
        l_variousInformParams.setSendTimestamp(l_informDetailInfoUnit.voucherSendDate);

        // 伝票受信日時
        l_variousInformParams.setReceiptTimestamp(l_informDetailInfoUnit.voucherRecvDate);

        this.variousInformParams = l_variousInformParams;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate各種連絡情報)<BR>
     * 各種連絡データの入力チェックを行う。 <BR>
     * <BR>
     * 各社カスタマイズがあれば、各種連絡項目マスタテーブルに指定されたチェックを行う。 <BR>
     * 各社カスタマイズがなければ、DBレイアウト 「各種連絡テーブル.xls」に記述されたデフォルトチェックを行う。 <BR>
     * <BR>
     * this.各種連絡行のチェック対象項目(*1)すべてについて、以下１）～３）のチェックを行う。 <BR>
     * <BR>
     * １）　@各種連絡項目マスタ生成 <BR>
     * 　@各種連絡項目マスタを生成する。 <BR>
     * 　@引数①@でnullが返却された場合は、引数②で生成する。 <BR>
     * <BR>
     * 　@[コンストラクタの引数①@] <BR>
     * 　@証券会社コード：　@this.get証券会社コード()の戻り値<BR>
     * 　@部店コード：　@this.get部店コード()の戻り値<BR>
     * 　@連絡種別：　@this.get連絡種別()の戻り値<BR>
     * 　@項目物理名：　@チェック対象項目(*1) <BR>
     * <BR>
     * 　@[コンストラクタの引数②] <BR>
     * 　@証券会社コード：　@this.get証券会社コード()の戻り値<BR>
     * 　@部店コード：　@”000” <BR>
     * 　@連絡種別：　@this.get連絡種別()の戻り値<BR>
     * 　@項目物理名：　@チェック対象項目(*1) <BR>
     * <BR>
     * 　@引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断し、 <BR>
     * 　@各種連絡項目マスタ.getDefault項目マスタ()にてインスタンスを生成する。 <BR>
     * <BR>
     * 　@[getDefault項目マスタ()に指定する引数] <BR>
     * 　@必須項目フラグ：　@チェック対象項目(*1)が【Null】項目であればBooleanEnum.FALSE、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@【NotNull】項目であれば、BooleanEnum.TRUEを指定する。 <BR>
     * 　@項目最大長：　@チェック対象項目(*1)の【SIZE】 <BR>
     * 　@項目チェック方式：　@チェック対象項目(*1)の【項目チェック方式（WEB3デフォルト）】 <BR>
     * <BR>
     * ２）　@必須項目チェック <BR>
     * 　@１）で生成した各種連絡項目マスタ.validate必須項目()をコールする。 <BR>
     * 　@falseが返却された場合は、対応する例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01309<BR>
     * <BR>
     * 　@[validate必須項目()に指定する引数] <BR>
     * 　@項目値：　@チェック対象項目(*1)の値 <BR>
     * <BR>
     *   validate必須項目()の戻り値 == true && 項目値 == null の場合は、<BR>
     *   それ以降のチェック処理をスキップする。<BR>
     * <BR>    
     * ３）　@データレングスチェック <BR>
     * 　@１）で生成した各種連絡項目マスタ.validateレングス()をコールする。 <BR>
     * 　@falseが返却された場合は、対応する例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01310<BR>
     * <BR>
     * 　@[validateレングス()に指定する引数] <BR>
     * 　@項目値：　@チェック対象項目(*1)の値 <BR>
     * <BR>
     * ４）　@有効値チェック <BR>
     * 　@○　@有効コード値チェックの場合（if(各種連絡項目マスタ.is有効コードチェック() == true)） <BR>
     * <BR>
     * 　@　@各種連絡項目属性オブジェクトを生成する。 <BR>
     * 　@　@引数①@でnullが返却された場合は、引数②で生成する。 <BR>
     * <BR>
     * 　@　@[コンストラクタの引数①@] <BR>
     * 　@　@証券会社コード：　@this.get証券会社コード()の戻り値<BR>
     * 　@　@部店コード：　@this.get部店コード()の戻り値<BR>
     * 　@　@連絡種別：　@this.get連絡種別()の戻り値<BR>
     * 　@　@項目物理名：　@チェック対象項目(*1)<BR>
     * <BR>
     * 　@　@[コンストラクタの引数②] <BR>
     * 　@　@証券会社コード：　@this.get証券会社コード()の戻り値<BR>
     * 　@　@部店コード：　@”000”<BR>
     * 　@　@連絡種別：　@this.get連絡種別()の戻り値<BR>
     * 　@　@項目物理名：　@チェック対象項目(*1)<BR>
     * <BR>
     * 　@　@引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断しWEB3デフォルトチェックを行う。 <BR>
     * 　@　@チェック対象項目(*1)の値が存在するコード値でなければ、例外をスローする。 <BR>
     * 　@　@※チェック対象項目(*1)の【説明（有効コード、意味）】参照。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01311<BR>
     * <BR>
     * 　@　@オブジェクトが生成できた場合は、各社カスタマイズデータがあると判定し、 <BR>
     * 　@　@各種連絡項目属性.validate有効コード値()をコールする。 <BR>
     * <BR>
     * 　@　@[validate有効コード値()に指定する引数] <BR>
     * 　@　@項目値：　@チェック対象項目(*1)の値 <BR>
     * <BR>
     * 　@○　@銘柄チェックの場合（else if(各種連絡項目マスタ.is銘柄チェック() == true)）<BR>
     *     this.validate銘柄()をコールする。<BR>
     * 　@　@falseが返却された場合は、対応する例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01869<BR>
     * <BR>
     * 　@　@[validate銘柄()に指定する引数] <BR>
     *     証券会社： this.get証券会社()の戻り値<BR>
     *     銘柄コード： チェック対象項目(*1)の値<BR>
     * <BR>
     * 　@○　@投信銘柄チェックの場合（else if(各種連絡項目マスタ.is投信銘柄チェック() == true)）<BR>
     * 　@　@　@this.validate投信銘柄()をコールする。<BR>
     * 　@　@　@falseが返却された場合は、対応する例外をスローする。<BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_02796<BR>
     * <BR>
     * 　@　@　@[validate投信銘柄()に指定する引数]<BR>
     * 　@　@　@証券会社： this.get証券会社()の戻り値<BR>
     * 　@　@　@銘柄コード： チェック対象項目(*1)の値<BR>
     * <BR>
     * 　@○　@債券銘柄チェックの場合（else if(各種連絡項目マスタ.is債券銘柄チェック() == true)）<BR>
     * 　@　@　@this.validate債券銘柄()をコールする。<BR>
     * 　@　@　@falseが返却された場合は、対応する例外をスローする。<BR>
     * 　@　@class:WEB3BusinessLayerException<BR>
     * 　@　@tag:BUSINESS_ERROR_02797<BR>
     * <BR>
     * 　@　@　@[validate債券銘柄()に指定する引数]
     * 　@　@　@証券会社： this.get証券会社()の戻り値
     * 　@　@　@銘柄コード： チェック対象項目(*1)の値
     * <BR>
     * 　@○　@上記チェック以外（else）の場合<BR>
     * 　@　@１）で生成した各種連絡項目マスタ.validate有効値()をコールする。 <BR>
     * 　@　@falseが返却された場合は、対応する例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01312<BR>
     * <BR>
     * 　@　@[validate有効値()に指定する引数] <BR>
     * 　@　@項目値：　@チェック対象項目(*1)の値 <BR>
     * <BR>
     * <BR>
     * (*1) チェック対象項目 <BR>
     * 　@DBレイアウト 「各種連絡テーブル.xls」内の<BR>
     * 【項目チェック方式】列に記載がある項目とカスタマイズ可能か項目が対象。。 <BR> 
     * @@roseuid 41BD3B87038F
     */
    public void validateInformDetailInfoUnit() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInformDetailInfoUnit()";
        log.entering(STR_METHOD_NAME);
        //各社カスタマイズがあれば、各種連絡項目マスタテーブルに指定されたチェックを行う。 
        //各社カスタマイズがなければ、DBレイアウト 「各種連絡テーブル.xls」に記述されたデフォルトチェックを行う。 
        //
        //this.各種連絡行のチェック対象項目(*1)すべてについて、以下１）～４）のチェックを行う。 
        //
        //１）　@各種連絡項目マスタ生成 
        //　@各種連絡項目マスタを生成する。 
        //　@引数①@でnullが返却された場合は、引数②で生成する。 
        //
        //　@[コンストラクタの引数①@] 
        //　@証券会社コード：　@this.get証券会社コード()の戻り値
        //　@部店コード：　@this.get部店コード()の戻り値
        //　@連絡種別：　@this.get連絡種別()の戻り値
        //　@項目物理名：　@チェック対象項目(*1) 
        WEB3InformItemMaster l_informItemMaster = null;
        WEB3InformTableSpec l_spec = new WEB3InformTableSpec();

        int l_intColumnLen = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName()).length;
        String[] l_strItemSymbolNames = new String[l_intColumnLen];
        for (int i = 0; i < l_intColumnLen;i++)
        {
            WEB3InformColumnSpec l_loopSpec = l_spec.getColumnSpecsByTableName(VariousInformRow.TYPE.getTableName())[i];
            if (!l_loopSpec.isDefaultCheckModeSet() && !l_loopSpec.isCustomizeAble())
            {
                continue;
            }
            l_strItemSymbolNames[i] = l_loopSpec.asHeader();
            try
            {
                l_informItemMaster = new WEB3InformItemMaster(
                    this.getInstitutionCode(),
                    this.getBranchCode(),
                    this.getInformDiv(),
                    l_strItemSymbolNames[i]); 
            }
            catch (NotFoundException l_ex)
            {
                try
                {
                    //　@[コンストラクタの引数②] 
                    //　@証券会社コード：　@this.get証券会社コード()の戻り値
                    //　@部店コード：　@”000” 
                    //　@連絡種別：　@this.get連絡種別()の戻り値
                    //　@項目物理名：　@チェック対象項目(*1) 
                    l_informItemMaster = new WEB3InformItemMaster(
                        this.getInstitutionCode(),
                        WEB3BranchCodeDef.DEFAULT,
                        this.getInformDiv(),
                        l_strItemSymbolNames[i]); 
                }
                catch (NotFoundException l_ex1)
                {
                    //　@引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断し、 
                    //　@各種連絡項目マスタ.getDefault項目マスタ()にてインスタンスを生成する。
                    //　@[getDefault項目マスタ()に指定する引数] 
                    //　@必須項目フラグ：　@チェック対象項目(*1)が【Null】項目であればBooleanEnum.FALSE、 
                    //　@　@　@　@　@　@　@　@　@　@　@　@【NotNull】項目であれば、BooleanEnum.TRUEを指定する。 
                    //　@項目最大長：　@チェック対象項目(*1)の【SIZE】 
                    //　@項目チェック方式：　@チェック対象項目(*1)の【項目チェック方式（WEB3デフォルト）】 
                    // 

                    BooleanEnum l_boolEnum = null;
                    if (l_loopSpec.getIsNullable())
                    {
                        l_boolEnum = BooleanEnum.FALSE;
                    }
                    else
                    {
                        l_boolEnum = BooleanEnum.TRUE;
                    }
                    l_informItemMaster = WEB3InformItemMaster.getDefaultItemMaster(l_boolEnum,
                        l_loopSpec.columnSize(),l_loopSpec.getDefaultCheckMode());

                }
            }


            //２）　@必須項目チェック 
            //　@１）で生成した各種連絡項目マスタ.validate必須項目()をコールする。 
            //　@falseが返却された場合は、対応する例外をスローする。 
            //　@class: WEB3BusinessLayerException
            //　@tag: BUSINESS_ERROR_01309
            //
            //　@[validate必須項目()に指定する引数] 
            //　@項目値：　@チェック対象項目(*1)の値 
            //
            //InformCtrlItemMasterParams l_masterParams = (InformCtrlItemMasterParams)l_informItemMaster.getDataSourceObject();
            Object l_obj = variousInformParams.getColumn(l_loopSpec.asHeader());

            boolean l_necessaryItem = l_informItemMaster.validateNecessaryItem(l_obj);
            if (!l_necessaryItem)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_loopSpec.getColumnNameKana());
            }
            //・validate必須項目()の戻り値 == true && 項目値 == null の場合は、
            //  それ以降のチェック処理をスキップする。
            if (l_necessaryItem && l_obj == null)
            {
                continue;
            }
 
            //３）　@データレングスチェック 
            //　@１）で生成した各種連絡項目マスタ.validateレングス()をコールする。 
            //　@falseが返却された場合は、対応する例外をスローする。 
            //　@class: WEB3BusinessLayerException
            //　@tag: BUSINESS_ERROR_01310
            //
            //　@[validateレングス()に指定する引数] 
            //　@項目値：　@チェック対象項目(*1)の値 
            //
            boolean l_necessaryItemLen = l_informItemMaster.validateLength(l_obj);
            
            if (!l_necessaryItemLen)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01310,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_loopSpec.getColumnNameKana());
            }
            //４）　@有効値チェック 
            //　@○　@有効コード値チェックの場合（if(各種連絡項目マスタ.is有効コードチェック() == true)） 
            //
            //　@　@各種連絡項目属性オブジェクトを生成する。 
            //　@　@引数①@でnullが返却された場合は、引数②で生成する。 
            //
            //　@　@[コンストラクタの引数①@] 
            //　@　@証券会社コード：　@this.get証券会社コード()の戻り値
            //　@　@部店コード：　@this.get部店コード()の戻り値
            //　@　@連絡種別：　@this.get連絡種別()の戻り値
            //　@　@項目物理名：　@チェック対象項目(*1)
            //
            //　@　@[コンストラクタの引数②] 
            //　@　@証券会社コード：　@this.get証券会社コード()の戻り値
            //　@　@部店コード：　@”000”
            //　@　@連絡種別：　@this.get連絡種別()の戻り値
            //　@　@項目物理名：　@チェック対象項目(*1)
            //
            WEB3InformItemProperty l_property = null;
            if (l_informItemMaster.isEffectiveCodeCheck())
            {
                try
                {
                    l_property = new WEB3InformItemProperty(
                        this.getInstitutionCode(),
                        this.getBranchCode(),
                        this.getInformDiv(),
                        l_strItemSymbolNames[i]); 
                }
                catch (NotFoundException l_ex)
                {
                    try
                    {
                        l_property = new WEB3InformItemProperty(
                            this.getInstitutionCode(),
                            WEB3BranchCodeDef.DEFAULT,
                            this.getInformDiv(),
                            l_strItemSymbolNames[i]); 
                    }
                    catch(NotFoundException l_ex1)
                    {


                    }
                }

                //　@　@引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断しWEB3デフォルトチェックを行う。 
                //　@　@チェック対象項目(*1)の値が存在するコード値でなければ、例外をスローする。 
                //　@　@※チェック対象項目(*1)の【説明（有効コード、意味）】参照。 
                //　@class: WEB3BusinessLayerException
                //　@tag: BUSINESS_ERROR_01311
                //
                //　@　@オブジェクトが生成できた場合は、各社カスタマイズデータがあると判定し、 
                //　@　@各種連絡項目属性.validate有効コード値()をコールする。 
                //
                //　@　@[validate有効コード値()に指定する引数] 
                //　@　@項目値：　@チェック対象項目(*1)の値
                if (l_property != null)
                {
                    if (l_obj instanceof String)
                    {
                        boolean l_blnEffectiveValue;
                        l_blnEffectiveValue = l_property.validateEffectiveCodeValue(String.valueOf(l_obj));

                        if (!l_blnEffectiveValue)
                        {
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01311,
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_loopSpec.getColumnNameKana());
                        }

                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01311,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_loopSpec.getColumnNameKana());

                    }
                }                        
            }

            //　@○　@銘柄チェックの場合（else if(各種連絡項目マスタ.is銘柄チェック() == true)）
            //    this.validate銘柄()をコールする。
            //　@　@falseが返却された場合は、対応する例外をスローする。 
            //　@class: WEB3BusinessLayerException
            //　@tag: BUSINESS_ERROR_01869
            //
            //　@　@[validate銘柄()に指定する引数] 
            //    証券会社： this.get証券会社()の戻り値
            //    銘柄コード： チェック対象項目(*1)の値
            else if (l_informItemMaster.isProductCheck())
            {
                Boolean l_blnValidateProduct = 
                    this.validateProduct(this.getInstitution(),String.valueOf(l_obj));
                if (Boolean.FALSE.equals(l_blnValidateProduct))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01869,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
     
            }

            //○　@投信銘柄チェックの場合（else if(各種連絡項目マスタ.is投信銘柄チェック() == true)）
            //this.validate投信銘柄()をコールする。
            //falseが返却された場合は、対応する例外をスローする。
            //[validate投信銘柄()に指定する引数]
            //証券会社： this.get証券会社()の戻り値
            //銘柄コード： チェック対象項目(*1)の値
            else if (l_informItemMaster.isMutualProductCheck())
            {
                boolean l_blnValidateMutualProduct =
                    this.validateMutualProduct(this.getInstitution(), String.valueOf(l_obj));

                if (!l_blnValidateMutualProduct)
                {
                    log.debug("投信銘柄を取得できません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02796,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
            }

            //　@○　@債券銘柄チェックの場合（else if(各種連絡項目マスタ.is債券銘柄チェック() == true)）
            //this.validate債券銘柄()をコールする。
            //falseが返却された場合は、対応する例外をスローする。
            //[validate債券銘柄()に指定する引数]
            //証券会社： this.get証券会社()の戻り値
            //銘柄コード： チェック対象項目(*1)の値
            else if (l_informItemMaster.isBondProductCheck())
            {
                boolean l_blnValidateBondProduct =
                    this.validateBondProduct(this.getInstitution(), String.valueOf(l_obj));

                if (!l_blnValidateBondProduct)
                {
                    log.debug("債券銘柄を取得できません。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02797,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
            }

            //　@○　@上記チェック以外（else）の場合 
            //　@　@１）で生成した各種連絡項目マスタ.validate有効値()をコールする。 
            //　@　@falseが返却された場合は、対応する例外をスローする。 
            //　@class: WEB3BusinessLayerException
            //　@tag: BUSINESS_ERROR_01312
            //
            //　@　@[validate有効値()に指定する引数] 
            //　@　@項目値：　@チェック対象項目(*1)の値 
            else
            {
                if (!l_informItemMaster.validateEffectiveValue(variousInformParams.getColumn(l_loopSpec.asHeader())))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01312,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_loopSpec.getColumnNameKana());
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get連絡種別)<BR>
     * 連絡種別を取得する。<BR>
     * <BR>
     * this.各種連絡行.連絡種別を返却する。<BR>
     * @@return String
     * @@roseuid 41BD3DB50351
     */
    public String getInformDiv()
    {
        final String STR_METHOD_NAME = "getInformDiv()";
        log.entering(STR_METHOD_NAME);
        String l_strInfromDiv = this.variousInformParams.getInformDiv();
        log.exiting(STR_METHOD_NAME);
        return l_strInfromDiv;
    }

    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * <BR>
     * this.各種連絡行.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 41BD3E340082
     */
    public String getInstitutionCode()
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        String l_strInstitutionCode = this.variousInformParams.getInstitutionCode();
        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }

    /**
     * (get証券会社)<BR>
     * 各種連絡行の情報から証券会社オブジェクトを取得する。<BR>
     * <BR>
     * １）拡張アカウントマネージャ.get顧客()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 証券会社コード： this.各種連絡行.証券会社コード<BR>
     * 部店コード： this.各種連絡行.部店コード<BR>
     * 口座コード： this.各種連絡行.顧客コード<BR>
     * <BR>
     * ２）顧客.getInstitution()の戻り値を返却する。<BR>
     * @@return Institution
     * @@roseuid 41BD493201DA
     */
    public Institution getInstitution() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInstitution()";
        log.entering(STR_METHOD_NAME);
        Institution l_institution = null;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = 
            l_gentradeAccountManager.getMainAccount(this.variousInformParams.getInstitutionCode(),
            this.variousInformParams.getBranchCode(),
            this.variousInformParams.getAccountCode());
        l_institution = l_mainAccount.getInstitution();

        log.exiting(STR_METHOD_NAME);
        return l_institution;
    }

    /**
     * (get部店コード)<BR>
     * 部店コードを取得する。<BR>
     * <BR>
     * this.各種連絡行.部店コードを返却する。<BR>
     * @@return String
     * @@roseuid 41BD3E5D016C
     */
    public String getBranchCode()
    {
        final String STR_METHOD_NAME = "getBranchCode()";
        log.entering(STR_METHOD_NAME);
        String l_strBranchCode = this.variousInformParams.getBranchCode();
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }



    /**
     * (validate銘柄)<BR>
     * 銘柄の存在チェックを行う。<BR>
     * <BR>
     * １）EqTypeのトレーディングモジュールを取得する。<BR>
     * <BR>
     * ２）プロダクトマネージャを取得する。<BR>
     * <BR>
     * ３）プロダクトマネージャ.get銘柄()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 証券会社： 引数.証券会社<BR>
     * 銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * ４）銘柄オブジェクトが取得できた場合はtrue、取得できなかった場合はfalseを返却する。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * 
     * @@return Boolean
     * @@roseuid 41BD44340322
     */
    protected Boolean validateProduct(
        Institution l_institution,
        String l_strProductCode)
    {
        final String STR_METHOD_NAME = "validateProduct";
        log.entering(STR_METHOD_NAME);
        boolean l_blnValidateProduct;
        //１）EqTypeのトレーディングモジュールを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //２）プロダクトマネージャを取得する。
        //
        EqTypeProductManager l_productManager = (EqTypeProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        //３）プロダクトマネージャ.get銘柄()をコールする。
        //
        //[引数]
        //証券会社： 引数.証券会社
        //銘柄コード： 引数.銘柄コード
        //
        //４）銘柄オブジェクトが取得できた場合はtrue、取得できなかった場合はfalseを返却する。
        try
        {
            l_productManager.getProduct(l_institution,l_strProductCode);
            l_blnValidateProduct = true;
        }
        catch(NotFoundException l_ex)
        {
            log.debug("NotFound Product.",l_ex);
            l_blnValidateProduct = false;
        }
        log.exiting(STR_METHOD_NAME);
        return new Boolean(l_blnValidateProduct);
    }


    /**
     * (saveNew各種連絡)<BR>
     * 各種連絡テーブルにデータを登録する。<BR> 
     * <BR>
     * １） 各種連絡行オブジェクト取得 <BR>
     * 　@this.各種連絡行を取得する。 <BR>
     * <BR>
     * ２） 更新情報をセットする。 <BR>
     * 　@各種連絡行の入力データ以外の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「各種連絡DB更新仕様.xls」参照 <BR>
     * <BR>
     * ３） DB登録 <BR>
     * 　@各種連絡行オブジェクトの内容で、各種連絡テーブルに登録（insert）する。 <BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * 
     * @@param l_strRequstNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@roseuid 41BD5905037F
     */
    public void saveNewInform(
        String l_strUpdaterCode,
        String l_strRequstNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewInform(String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 各種連絡行オブジェクト取得 
        //　@this.各種連絡行を取得する。 
        VariousInformParams l_params = this.variousInformParams;

        //２） 更新情報をセットする。
        //　@各種連絡行の入力データ以外の項目に値をセットする。
        //
        //　@DB更新仕様「各種連絡DB更新仕様.xls」参照
        if (l_params.getAccountCode() != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getAccountCode());
            //顧客コード
            l_params.setAccountCode(l_mainAccount.getAccountCode());

        }
        //識別コード   request_number     識別コード（※get新規識別コード()にて取得した識別コード）
        l_params.setRequestNumber(l_strRequstNumber);
        //扱者コード   trader_code     更新者コード
        l_params.setTraderCode(l_strUpdaterCode);                                                            
        //更新者コード  last_updater     更新者コード
        l_params.setLastUpdater(l_strUpdaterCode);                               
        //作成日時    created_timestamp   処理日時
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_tsProcessDate);     
        //更新日時    last_updated_timestamp   処理日時
        l_params.setLastUpdatedTimestamp(l_tsProcessDate);     

        try
        { 
            //３） DB登録 
            //　@各種連絡行オブジェクトの内容で、各種連絡テーブルに登録（insert）する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                
            l_queryProcessor.doInsertQuery(l_params);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.各種連絡行を返却する。<BR>
     * @@return Object
     * @@roseuid 41BD32D201AB
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = "getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.variousInformParams;
    }
    /**
     * (get付加情報)<BR>
     * レスポンスにセットする付加情報を取得する。<BR>
     * <BR>
     * １）各種連絡付加情報のインスタンスを生成する。<BR>
     * <BR>
     * ２）this.各種連絡行のコード１～１０の項目について、以下の処理を行う。 <BR>
     * <BR>
     * ２－１）　@各種連絡項目マスタ生成 <BR>
     * 　@各種連絡項目マスタを生成する。 <BR>
     * 　@引数①@でnullが返却された場合は、引数②で生成する。 <BR>
     * <BR>
     * 　@[コンストラクタの引数①@] <BR>
     * 　@証券会社コード：　@this.get証券会社コード()の戻り値<BR>
     * 　@部店コード：　@this.get部店コード()の戻り値<BR>
     * 　@連絡種別：　@this.get連絡種別()の戻り値<BR>
     * 　@項目物理名：　@対象項目(*1) <BR>
     * <BR>
     * 　@[コンストラクタの引数②] <BR>
     * 　@証券会社コード：　@this.get証券会社コード()の戻り値<BR>
     * 　@部店コード：　@”000” <BR>
     * 　@連絡種別：　@this.get連絡種別()の戻り値<BR>
     * 　@項目物理名：　@対象項目(*1) <BR>
     * <BR>
     * 　@引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断し、 <BR>
     * 　@各種連絡項目マスタ.getDefault項目マスタ()にてインスタンスを生成する。<BR> 
     * <BR>
     * 　@[getDefault項目マスタ()に指定する引数] <BR>
     * 　@必須項目フラグ：　@対象項目(*1)が【Null】項目であればBooleanEnum.FALSE、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@【NotNull】項目であれば、BooleanEnum.TRUEを指定する。 <BR>
     * 　@項目最大長：　@対象項目(*1)の【SIZE】 <BR>
     * 　@項目チェック方式：　@対象項目(*1)の【項目チェック方式（WEB3デフォルト）】 <BR>
     * <BR>
     * ２－２）対象項目のチェック方式が銘柄チェック or 投信銘柄チェック or 債券銘柄チェック<BR>
     * の場合、（各種連絡項目マスタ.is銘柄チェック() == true or<BR>
     * is投信銘柄チェック() == true or is債券銘柄チェック() == true）<BR>
     * <BR>
     *   ２－２－１）EqTypeのトレーディングモジュールを取得する。<BR>
     * <BR>
     *   ２－２－２）プロダクトマネージャを取得する。<BR>
     * <BR>
     *   ２－２－３）プロダクトマネージャ.get銘柄()をコールする。<BR>
     * <BR>
     * 　@　@[get銘柄()に指定する引数] <BR>
     *     証券会社： this.get証券会社()の戻り値<BR>
     *     銘柄コード： 対象項目(*1)の値<BR>
     * <BR>
     * ２－３）銘柄オブジェクトを取得した場合<BR>
     *   コード項目の連番に対応する各種連絡付加情報の付加情報項目に<BR>
     *   銘柄名をセットする。<BR>
     * <BR>
     * 　@２－２－２ ）　@投信銘柄チェックの場合、投信銘柄オブジェクトを取得する。<BR>
     *<BR>
     * 　@　@２－２－２－１）投信のトレーディングモジュールを取得する。<BR>
     *<BR>
     * 　@  ２－２－２－２）プロダクトマネージャを取得する。<BR>
     *<BR>
     * 　@  ２－２－２－３）プロダクトマネージャ.get銘柄()をコールする。<BR>
     *<BR>
     * 　@　@２－２－２－４）投信銘柄オブジェクトを取得した場合<BR>
     *   　@コード項目の連番に対応する各種連絡付加情報の付加情報項目に<BR>
     * 　@  銘柄名をセットする。<BR>
     *<BR>
     * 　@　@[get銘柄()に指定する引数] <BR>
     *     証券会社： this.get証券会社()の戻り値<BR>
     *     銘柄コード： 対象項目(*1)の値<BR>
     *<BR>
     * 　@２－２－３ ）　@債券銘柄チェックの場合、債券銘柄オブジェクトを取得する。<BR>
     *<BR>
     * 　@　@２－２－３－１）債券のトレーディングモジュールを取得する。<BR>
     *<BR>
     * 　@  ２－２－３－２）プロダクトマネージャを取得する。<BR>
     *<BR>
     * 　@  ２－２－３－３）プロダクトマネージャ.get銘柄()をコールする。<BR>
     *<BR>
     * 　@　@２－２－３－４）債券銘柄オブジェクトを取得した場合<BR>
     *   　@コード項目の連番に対応する各種連絡付加情報の付加情報項目に<BR>
     * 　@  銘柄名をセットする。<BR>
     *<BR>
     * 　@　@[get銘柄()に指定する引数] <BR>
     *     証券会社： this.get証券会社()の戻り値<BR>
     *     銘柄コード： 対象項目(*1)の値<BR>
     *<BR>
     * ３）生成された各種連絡付加情報を返却する。<BR>
     * @@return WEB3InformAddInfoUnit
     */
    public WEB3InformAddInfoUnit getInformAddInfoUnit() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformAddInfoUnit()";
        log.entering(STR_METHOD_NAME);
        //１）各種連絡付加情報のインスタンスを生成する。
        WEB3InformAddInfoUnit l_informAddInforUnit = new WEB3InformAddInfoUnit();
        //２）this.各種連絡行のコード１～１０の項目について、以下の処理を行う。 
        //
        //２－１）　@各種連絡項目マスタ生成 
        //　@各種連絡項目マスタを生成する。 
        //　@引数①@でnullが返却された場合は、引数②で生成する。 
        //
        //　@[コンストラクタの引数①@] 
        //　@証券会社コード：　@this.get証券会社コード()の戻り値
        //　@部店コード：　@this.get部店コード()の戻り値
        //　@連絡種別：　@this.get連絡種別()の戻り値
        //　@項目物理名：　@対象項目(*1) 
        //
        //　@[コンストラクタの引数②] 
        //　@証券会社コード：　@this.get証券会社コード()の戻り値
        //　@部店コード：　@”000” 
        //　@連絡種別：　@this.get連絡種別()の戻り値
        //　@項目物理名：　@対象項目(*1) 
        //
        WEB3InformItemMaster l_informItemMaster = null;
        WEB3InformTableSpec l_spec = new WEB3InformTableSpec();


        String[] l_strItemSymbolNames = {"ext_code1","ext_code2","ext_code3","ext_code4","ext_code5","ext_code6",
                "ext_code7","ext_code8","ext_code9","ext_code10"};
        for (int i = 0; i < 10;i++)
        {
            WEB3InformColumnSpec l_loopSpec = l_spec.getColumnSpec(VariousInformRow.TYPE.getTableName(),l_strItemSymbolNames[i]);
            try
            {
                l_informItemMaster = new WEB3InformItemMaster(
                    this.getInstitutionCode(),
                    this.getBranchCode(),
                    this.getInformDiv(),
                    l_strItemSymbolNames[i]); 
            }
            catch (NotFoundException l_ex)
            {
                try
                {
                    l_informItemMaster = new WEB3InformItemMaster(
                        this.getInstitutionCode(),
                        WEB3BranchCodeDef.DEFAULT,
                        this.getInformDiv(),
                        l_strItemSymbolNames[i]); 
                }
                catch (NotFoundException l_ex1)
                {
                    //　@引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断し、 
                    //　@各種連絡項目マスタ.getDefault項目マスタ()にてインスタンスを生成する。 
                    //
                    //　@[getDefault項目マスタ()に指定する引数] 
                    //　@必須項目フラグ：　@対象項目(*1)が【Null】項目であればBooleanEnum.FALSE、 
                    //　@　@　@　@　@　@　@　@　@　@　@　@【NotNull】項目であれば、BooleanEnum.TRUEを指定する。 
                    //　@項目最大長：　@対象項目(*1)の【SIZE】 
                    //　@項目チェック方式：　@対象項目(*1)の【項目チェック方式（WEB3デフォルト）】 
                    //
                    BooleanEnum l_boolEnum = null;
                    if (l_loopSpec.getIsNullable())
                    {
                        l_boolEnum = BooleanEnum.FALSE;
                    }
                    else
                    {
                        l_boolEnum = BooleanEnum.TRUE;
                    }
                    l_informItemMaster = WEB3InformItemMaster.getDefaultItemMaster(l_boolEnum,
                        l_loopSpec.columnSize(),l_loopSpec.getDefaultCheckMode());

                }
            }
                       
            //２－２）対象項目のチェック方式が銘柄チェックの場合（各種連絡項目マスタ.is銘柄チェック() == true）
            //  銘柄オブジェクトを取得する。
            //
            Product l_product = null;
            String l_strProductName = null;            
            
            if (l_informItemMaster.isProductCheck())
            {
                //  ２－２－１）EqTypeのトレーディングモジュールを取得する。
                //
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                //  ２－２－２）プロダクトマネージャを取得する。
                //
                EqTypeProductManager l_productManager = (EqTypeProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                //  ２－２－３）プロダクトマネージャ.get銘柄()をコールする。
                //
                //　@　@[get銘柄()に指定する引数] 
                //    証券会社： this.get証券会社()の戻り値
                //    銘柄コード： 対象項目(*1)の値
                //
                
                String l_strValue = null;
                if (this.variousInformParams.getColumn(l_strItemSymbolNames[i]) == null)
                {
                    continue;
                }
                l_strValue = this.variousInformParams.getColumn(l_strItemSymbolNames[i]).toString();
                try
                {
                    l_product = l_productManager.getProduct(this.getInstitution(),l_strValue);
                    l_strProductName = l_product.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error(STR_METHOD_NAME,l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }


                //２－３）銘柄オブジェクトを取得した場合
                //  コード項目の連番に対応する各種連絡付加情報の付加情報項目に
                //  銘柄名をセットする。
                //
                switch (i)
                {
                    case 0:l_informAddInforUnit.addInfo1 = l_strProductName;break;
                    case 1:l_informAddInforUnit.addInfo2 = l_strProductName;break;
                    case 2:l_informAddInforUnit.addInfo3 = l_strProductName;break;
                    case 3:l_informAddInforUnit.addInfo4 = l_strProductName;break;
                    case 4:l_informAddInforUnit.addInfo5 = l_strProductName;break;
                    case 5:l_informAddInforUnit.addInfo6 = l_strProductName;break;
                    case 6:l_informAddInforUnit.addInfo7 = l_strProductName;break;
                    case 7:l_informAddInforUnit.addInfo8 = l_strProductName;break;
                    case 8:l_informAddInforUnit.addInfo9 = l_strProductName;break;
                    default: l_informAddInforUnit.addInfo10 = l_strProductName;
                }
            }

            if (l_informItemMaster.isMutualProductCheck())
            {
                //２－２－２－１）投信のトレーディングモジュールを取得する。
                FinApp l_finapp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

                //２－２－２－２）プロダクトマネージャを取得する。
                WEB3MutualFundProductManager l_mutualFundProductManager =
                    (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

                // ２－２－２－３）プロダクトマネージャ.get銘柄()をコールする
                String l_strValue = null;
                if (this.variousInformParams.getColumn(l_strItemSymbolNames[i]) == null)
                {
                    continue;
                }
                l_strValue = this.variousInformParams.getColumn(l_strItemSymbolNames[i]).toString();

                try
                {
                    l_product = l_mutualFundProductManager.getMutualFundProduct(this.getInstitution(), l_strValue);
                    l_strProductName = l_product.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //　@　@２－２－２－４）投信銘柄オブジェクトを取得した場合
                //コード項目の連番に対応する各種連絡付加情報の付加情報項目に
                //銘柄名をセットする。
                switch (i)
                {
                    case 0:l_informAddInforUnit.addInfo1 = l_strProductName; break;
                    case 1:l_informAddInforUnit.addInfo2 = l_strProductName; break;
                    case 2:l_informAddInforUnit.addInfo3 = l_strProductName; break;
                    case 3:l_informAddInforUnit.addInfo4 = l_strProductName; break;
                    case 4:l_informAddInforUnit.addInfo5 = l_strProductName; break;
                    case 5:l_informAddInforUnit.addInfo6 = l_strProductName; break;
                    case 6:l_informAddInforUnit.addInfo7 = l_strProductName; break;
                    case 7:l_informAddInforUnit.addInfo8 = l_strProductName; break;
                    case 8:l_informAddInforUnit.addInfo9 = l_strProductName; break;
                    default: l_informAddInforUnit.addInfo10 = l_strProductName;
                }
            }

            if (l_informItemMaster.isBondProductCheck())
            {
                //２－２－３－１）債券のトレーディングモジュールを取得する。
                FinApp l_finapp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);

                //２－２－３－２）プロダクトマネージャを取得する。
                WEB3BondProductManager l_bondProductManager =
                    (WEB3BondProductManager)l_tradingModule.getProductManager();

                // ２－２－３－３）プロダクトマネージャ.get銘柄()をコールする。
                String l_strValue = null;
                if (this.variousInformParams.getColumn(l_strItemSymbolNames[i]) == null)
                {
                    continue;
                }
                l_strValue = this.variousInformParams.getColumn(l_strItemSymbolNames[i]).toString();

                try
                {
                    l_product = l_bondProductManager.getBondProduct(this.getInstitution(), l_strValue);
                    l_strProductName = l_product.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //　@　@２－２－３－４）債券銘柄オブジェクトを取得した場合
                //コード項目の連番に対応する各種連絡付加情報の付加情報項目に
                //銘柄名をセットする。
                switch (i)
                {
                    case 0:l_informAddInforUnit.addInfo1 = l_strProductName; break;
                    case 1:l_informAddInforUnit.addInfo2 = l_strProductName; break;
                    case 2:l_informAddInforUnit.addInfo3 = l_strProductName; break;
                    case 3:l_informAddInforUnit.addInfo4 = l_strProductName; break;
                    case 4:l_informAddInforUnit.addInfo5 = l_strProductName; break;
                    case 5:l_informAddInforUnit.addInfo6 = l_strProductName; break;
                    case 6:l_informAddInforUnit.addInfo7 = l_strProductName; break;
                    case 7:l_informAddInforUnit.addInfo8 = l_strProductName; break;
                    case 8:l_informAddInforUnit.addInfo9 = l_strProductName; break;
                    default: l_informAddInforUnit.addInfo10 = l_strProductName;
                }
            }
        }

        //３）生成された各種連絡付加情報を返却する。
        
        log.exiting(STR_METHOD_NAME);
        return l_informAddInforUnit;
    }

    /**
     * (validate投信銘柄)<BR>
     * 投信銘柄の存在チェックを行う。<BR>
     *<BR>
     * １）投信のトレーディングモジュールを取得する。<BR>
     *<BR>
     * ２）プロダクトマネージャを取得する。<BR>
     *<BR>
     * ３）プロダクトマネージャ.get銘柄()をコールする。<BR>
     *<BR>
     * [引数]<BR>
     * 証券会社： 引数.証券会社<BR>
     * 銘柄コード： 引数.銘柄コード<BR>
     *<BR>
     * ４）銘柄オブジェクトが取得できた場合はtrue、取得できなかった場合はfalseを返却する。<BR>
     * @@param l_institution - 証券会社
     * @@param l_strProductCode - 銘柄コード
     * @@return boolean
     */
    protected boolean validateMutualProduct(Institution l_institution, String l_strProductCode)
    {
        final String STR_METHOD_NAME = "validateMutualProduct(Institution, String)";
        log.entering(STR_METHOD_NAME);

        //１）投信のトレーディングモジュールを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        //２）プロダクトマネージャを取得する。
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        //３）プロダクトマネージャ.get銘柄()をコールする。
        try
        {
            l_mutualFundProductManager.getMutualFundProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate債券銘柄)<BR>
     * 債券銘柄の存在チェックを行う。<BR>
     *<BR>
     * １）債券のトレーディングモジュールを取得する。<BR>
     *<BR>
     * ２）プロダクトマネージャを取得する。<BR>
     *<BR>
     * ３）プロダクトマネージャ.get銘柄()をコールする。<BR>
     *<BR>
     * [引数]<BR>
     * 証券会社： 引数.証券会社<BR>
     * 銘柄コード： 引数.銘柄コード<BR>
     *<BR>
     * ４）銘柄オブジェクトが取得できた場合はtrue、取得できなかった場合はfalseを返却する。<BR>
     * @@param l_institution - 証券会社
     * @@param l_strProductCode - 銘柄コード
     * @@return boolean
     */
    protected boolean validateBondProduct(Institution l_institution, String l_strProductCode)
    {
        final String STR_METHOD_NAME = "validateBondProduct(Institution, String)";
        log.entering(STR_METHOD_NAME);

        //１）債券のトレーディングモジュールを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);

        //２）プロダクトマネージャを取得する。
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();

        //プロダクトマネージャ.get銘柄()をコールする。
        try
        {
            l_bondProductManager.getBondProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get各種連絡行)<BR>
     * 主キーを基に各種連絡テーブルを検索する。<BR>
     *<BR>
     * １）　@各種連絡テーブルの検索<BR>
     * [検索条件]<BR>
     * 証券会社コード： 引数.証券会社コード<BR>
     * 連絡種別： 引数.連絡種別<BR>
     * 識別コード： 引数.識別コード<BR>
     * 部店コード： 引数.部店コード<BR>
     *<BR>
     * * レコードが見つからない場合、nullを返却<BR>
     *<BR>
     * ２）検索結果を返却<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_strInformType - 連絡種別
     * @@throws WEB3BaseException
     * @@return VariousInformParams - 各種連絡Params
     */
    public static VariousInformParams getVariousInform(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber,
        String l_strInformType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getVariousInform(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        VariousInformPK l_variousInformPK = new VariousInformPK();
        l_variousInformPK.institution_code = l_strInstitutionCode;
        l_variousInformPK.branch_code = l_strBranchCode;
        l_variousInformPK.request_number = l_strRequestNumber;
        l_variousInformPK.inform_div = l_strInformType;

        VariousInformParams l_variousInformParams = null;
        try
        {
            //１）　@各種連絡テーブルの検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_variousInformParams =
                (VariousInformParams)l_queryProcessor.doFindByPrimaryKeyQuery(l_variousInformPK);
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_variousInformParams;
    }

    /**
     * (update各種連絡)
     * 各種連絡テーブルのレコードを更新する。<BR>
     *<BR>
     * ［更新条件］<BR>
     * 証券会社コード = 各種連絡行.get証券会社コード（） and<BR>
     * 連絡種別 = 各種連絡行.get連絡種別（） and<BR>
     * 識別コード = 各種連絡行.get識別コード（） and<BR>
     * 部店コード = 各種連絡行.get部店コード（）<BR>
     * @@param l_map - Map
     * @@throws WEB3BaseException
     */
    public void updateInform(Map l_map) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateInform(Map)";
        log.entering(STR_METHOD_NAME);

        String l_strUpdateWhere = " institution_code = ? "
            + " and inform_div = ? "
            + " and request_number = ? "
            + " and branch_code = ? ";

        Object[] l_arrayUpdateParams = {
            this.variousInformParams.getInstitutionCode(),
            this.variousInformParams.getInformDiv(),
            this.variousInformParams.getRequestNumber(),
            this.variousInformParams.getBranchCode()
        };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                VariousInformRow.TYPE,
                l_strUpdateWhere,
                l_arrayUpdateParams,
                l_map);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveNew各種連絡)<BR>
     * 各種連絡テーブルにデータを登録する。<BR>
     *<BR>
     * １） 各種連絡行オブジェクト取得<BR>
     * 　@this.各種連絡行を取得する。<BR>
     *<BR>
     * ２） 更新情報をセットする。<BR>
     * 　@各種連絡行の入力データ以外の項目に値をセットする。<BR>
     *<BR>
     * 　@DB更新仕様「分配金振替口座登録_各種連絡テーブル.xls」参照<BR>
     *<BR>
     * ３） DB登録<BR>
     * 　@各種連絡行オブジェクトの内容で、各種連絡テーブルに登録（insert）する。<BR>
     *@@param l_strUpdaterCode - 更新者コード
     *@@param l_strRequestNumber - 識別コード
     *@@param l_strMakeStatus - 作成状況
     *@@throws WEB3BaseException
     */
    public void saveNewInform(
        String l_strUpdaterCode,
        String l_strRequestNumber,
        String l_strMakeStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewVariousInform(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 各種連絡行オブジェクト取得
        //　@this.各種連絡行を取得する。
        VariousInformParams l_params = this.variousInformParams;

        //２） 更新情報をセットする。
        //各種連絡行の入力データ以外の項目に値をセットする。
        //DB更新仕様「分配金振替口座登録_各種連絡テーブル.xls」参照
        //識別コード  request_number  識別コード（※get新規識別コード()にて取得した識別コード）
        l_params.setRequestNumber(l_strRequestNumber);

        // 口座番号
        if (l_params.getAccountCode() != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)((WEB3GentradeAccountManager)l_finApp.getAccountManager()).getMainAccount(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getAccountCode());
            //顧客コード
            l_params.setAccountCode(l_mainAccount.getAccountCode());
        }

        //扱者コード  trader_code  null
        l_params.setTraderCode(null);

        //更新者コード  last_updater  引数:更新者コード
        l_params.setLastUpdater(l_strUpdaterCode);

        //作成日時  created_timestamp  処理日時
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_tsProcessDate);

        //更新日時  last_updated_timestamp  処理日時
        l_params.setLastUpdatedTimestamp(l_tsProcessDate);

        //伝票作成状況  status  引数:作成状況
        l_params.setStatus(l_strMakeStatus);

        //エラー理由コード  error_reason_code  null
        l_params.setErrorReasonCode(null);

        //伝票識別コード  order_request_number  null
        l_params.setOrderRequestNumber(null);

        //データコード  request_code  null
        l_params.setRequestCode(null);

        //伝票送信日時  send_timestamp null
        l_params.setSendTimestamp(null);

        //伝票受信日時  receipt_timestamp  null
        l_params.setReceiptTimestamp(null);

        try
        {
            //３） DB登録
            //　@各種連絡行オブジェクトの内容で、各種連絡テーブルに登録（insert）する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_params);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * コンストラクタ<BR>
     * <BR>
     * １）各種連絡行に引数:各種連絡行をセットする。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行
     */
    public WEB3Inform(VariousInformParams l_variousInformParams)
    {
        this.variousInformParams = l_variousInformParams;
    }

    /**
     * (get各種連絡)<BR>
     * （static メソッド）<BR>
     * 顧客に該当する各種連絡を取得する。<BR>
     * <BR>
     * １）　@顧客.getDataSourceObject()にて顧客行を取得する。<BR>
     * <BR>
     * ２）　@各種連絡テーブル検索<BR>
     * 　@以下の条件で、各種連絡テーブルを検索する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード： 顧客行.get証券会社コード()<BR>
     * 　@　@連絡種別： 引数.連絡種別<BR>
     * 　@　@部店コード： 顧客行.get部店コード()<BR>
     * 　@　@顧客コード： 顧客行.get口座コード()<BR>
     * 　@　@区分１：  "1"（有効）<BR>
     * 　@[ソート条件]<BR>
     * 　@　@更新日時（降順）<BR>
     * 　@　@　@※複数件取得した場合は、先頭レコード（更新日時が新しいもの）とする。<BR>
     * <BR>
     * ３）　@各種連絡オブジェクト返却<BR>
     * 　@取得した行オブジェクトについて、各種連絡オブジェクトを生成し返却する。<BR>
     * 　@行が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strInformDiv - (連絡種別)<BR>
     * 連絡種別<BR>
     * @@return WEB3Inform
     * @@throws WEB3BaseException
     */
    public static WEB3Inform getVariousInform(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strInformDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getVariousInform(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        //顧客.getDataSourceObject()にて顧客行を取得する
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //各種連絡テーブル検索
        // 　@[検索条件]
        // 　@　@証券会社コード： 顧客行.get証券会社コード()
        // 　@　@連絡種別： 引数.連絡種別
        // 　@　@部店コード： 顧客行.get部店コード()
        // 　@　@顧客コード： 顧客行.get口座コード()
        // 　@　@区分１：  "1"（有効）
        // 　@[ソート条件]
        // 　@　@更新日時（降順）
        // 　@　@　@※複数件取得した場合は、先頭レコード（更新日時が新しいもの）とする。
        StringBuffer l_sbSql = new StringBuffer();
        l_sbSql.append(" institution_code = ? ");
        l_sbSql.append(" and inform_div = ? ");
        l_sbSql.append(" and branch_code = ? ");
        l_sbSql.append(" and account_code = ? ");
        l_sbSql.append(" and ext_div1 = ? ");

        Object[] l_sqlValues = new Object[]{
            l_mainAccountRow.getInstitutionCode(),
            l_strInformDiv,
            l_mainAccountRow.getBranchCode(),
            l_mainAccountRow.getAccountCode(),
            WEB3ExtDiv1Def.VALIDITY};

        String l_strSort = " last_updated_timestamp desc ";
        List l_lisResults = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_sbSql.toString(),
                l_strSort,
                null,
                l_sqlValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //各種連絡オブジェクト返却
        //　@取得した行オブジェクトについて、各種連絡オブジェクトを生成し返却する。
        //　@行が取得できなかった場合は、nullを返却する。
        if (l_lisResults.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        VariousInformParams l_variousInformParams =
            new VariousInformParams((VariousInformRow)l_lisResults.get(0));
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        log.exiting(STR_METHOD_NAME);
        return l_inform;
    }

    /**
     * (createNew各種連絡)<BR>
     * （static メソッド）<BR>
     * 各種連絡新規行を生成する。<BR>
     * <BR>
     * １）　@行オブジェクト生成<BR>
     * 　@各種連絡Paramsオブジェクトを生成する。<BR>
     * <BR>
     * 　@※各種連絡ParamsはDDLより自動生成する<BR>
     * <BR>
     * ２）　@顧客.getDataSourceObject()にて顧客行を取得する。<BR>
     * <BR>
     * ３）　@オブジェクトのプロパティに、以下の通りセットを行う。<BR>
     * 　@１）で生成した各種連絡Paramsオブジェクトのプロパティに、以下の通りセットを行う。<BR>
     * <BR>
     * 　@各種連絡行.証券会社コード ＝ 顧客行.証券会社コード<BR>
     * 　@各種連絡行.連絡種別 ＝ 引数.連絡種別<BR>
     * 　@各種連絡行.識別コード ＝ 引数.識別コード<BR>
     * 　@各種連絡行.部店コード ＝ 顧客行.部店コード<BR>
     * 　@各種連絡行.顧客コード ＝ 顧客行.口座コード<BR>
     * 　@各種連絡行.顧客名 ＝ 顧客行.名前（苗字）<BR>
     * 　@各種連絡行.区分１ ＝ "1"(有効)<BR>
     * 　@各種連絡行.区分２ ＝ 引数.PTS口座開設区分<BR>
     * 　@各種連絡行.更新者コード ＝ 引数.更新者コード<BR>
     * 　@各種連絡行.作成日時 ＝ 処理日時<BR>
     * 　@各種連絡行.更新日時 ＝ 処理日時<BR>
     * <BR>
     * ４）　@各種連絡オブジェクト返却<BR>
     * 　@行オブジェクトを指定し、各種連絡オブジェクトを生成し返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strInformDiv - (連絡種別)<BR>
     * 連絡種別<BR>
     * @@param l_strPTSAccOpenDiv - (PTS口座開設区分)<BR>
     * PTS口座開設区分<BR>
     * @@param l_strLastUpdater - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@return WEB3Inform
     */
    public static WEB3Inform createNewVariousInform(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strInformDiv,
        String l_strPTSAccOpenDiv,
        String l_strLastUpdater,
        String l_strRequestNumber)
    {
        final String STR_METHOD_NAME = "createNewVariousInform(" +
            "WEB3GentradeMainAccount, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //各種連絡Paramsオブジェクトを生成する
        VariousInformParams l_variousInformParams =
            new VariousInformParams();

        //顧客.getDataSourceObject()にて顧客行を取得する
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();

        //オブジェクトのプロパティに、以下の通りセットを行う
        //生成した各種連絡Paramsオブジェクトのプロパティに、以下の通りセットを行う
        //　@各種連絡行.証券会社コード ＝ 顧客行.証券会社コード
        l_variousInformParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());

        //　@各種連絡行.連絡種別 ＝ 引数.連絡種別
        l_variousInformParams.setInformDiv(l_strInformDiv);

        //　@各種連絡行.識別コード ＝ 引数.識別コード
        l_variousInformParams.setRequestNumber(l_strRequestNumber);

        //　@各種連絡行.部店コード ＝ 顧客行.部店コード
        l_variousInformParams.setBranchCode(l_mainAccountRow.getBranchCode());

        //　@各種連絡行.顧客コード ＝ 顧客行.口座コード
        l_variousInformParams.setAccountCode(l_mainAccountRow.getAccountCode());

        //　@各種連絡行.顧客名 ＝ 顧客行.名前（苗字）
        l_variousInformParams.setAccountName(l_mainAccountRow.getFamilyName());

        //　@各種連絡行.区分１ ＝ "1"(有効)
        l_variousInformParams.setExtDiv1(WEB3ExtDiv1Def.VALIDITY);

        //　@各種連絡行.区分２ ＝ 引数.PTS口座開設区分
        l_variousInformParams.setExtDiv2(l_strPTSAccOpenDiv);

        //　@各種連絡行.更新者コード ＝ 引数.更新者コード
        l_variousInformParams.setLastUpdater(l_strLastUpdater);

        //　@各種連絡行.作成日時 ＝ 処理日時
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_variousInformParams.setCreatedTimestamp(l_tsSystemTime);

        //　@各種連絡行.更新日時 ＝ 処理日時
        l_variousInformParams.setLastUpdatedTimestamp(l_tsSystemTime);

        //各種連絡オブジェクト返却
        //行オブジェクトを指定し、各種連絡オブジェクトを生成し返却する
        log.exiting(STR_METHOD_NAME);
        return new WEB3Inform(l_variousInformParams);
    }

    /**
     * (saveNew各種連絡)<BR>
     * 各種連絡テーブルにデータを登録する。<BR>
     * <BR>
     * １） 各種連絡行オブジェクト取得<BR>
     * 　@this.各種連絡行を取得する。<BR>
     * <BR>
     * ２） DB登録<BR>
     * 　@各種連絡行オブジェクトの内容で、各種連絡テーブルに登録（insert）する。<BR>
     * <BR>
     * 　@DB更新仕様「PTS口座開設申込_各種連絡テーブル.xls」<BR>
     * 　@PTS口座開設申込_insert_DB更新仕様 参照<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void saveNewVariousInform() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewVariousInform()";
        log.entering(STR_METHOD_NAME);

        //各種連絡行オブジェクト取得
        //this.各種連絡行を取得する
        VariousInformParams l_variousInformParams = this.variousInformParams;

        //DB登録
        //各種連絡行オブジェクトの内容で、各種連絡テーブルに登録（insert）する
        //DB更新仕様「PTS口座開設申込_各種連絡テーブル.xls」
        //PTS口座開設申込_insert_DB更新仕様 参照
        //扱者コード:null
        l_variousInformParams.setTraderCode(null);

        //作成日時:処理日時
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_variousInformParams.setCreatedTimestamp(l_tsSystemTime);

        //更新日時:処理日時
        l_variousInformParams.setLastUpdatedTimestamp(l_tsSystemTime);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_variousInformParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (updatePTS各種連絡)<BR>
     * 各種連絡テーブルのレコードを更新する。<BR>
     * <BR>
     * DB更新仕様「PTS口座開設申込_各種連絡テーブル.xls」<BR>
     * PTS口座開設申込_update_DB更新仕様 参照<BR>
     * <BR>
     * ［更新条件］<BR>
     * 　@証券会社コード = 各種連絡行.get証券会社コード() and<BR>
     * 　@連絡種別 = 各種連絡行.get連絡種別() and<BR>
     * 　@識別コード = 各種連絡行.get識別コード() and<BR>
     * 　@部店コード = 各種連絡行.get部店コード()<BR>
     * <BR>
     * @@param l_updateContents - (更新内容)<BR>
     * 更新内容<BR>
     * @@throws WEB3BaseException
     */
    public void updatePTSVariousInform(Map l_updateContents) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updatePTSVariousInform(Map)";
        log.entering(STR_METHOD_NAME);

        //［更新条件］
        //　@証券会社コード = 各種連絡行.get証券会社コード() and
        //　@連絡種別 = 各種連絡行.get連絡種別() and
        //　@識別コード = 各種連絡行.get識別コード() and
        //　@部店コード = 各種連絡行.get部店コード()
        StringBuffer l_sbUpdateSql = new StringBuffer();
        l_sbUpdateSql.append(" institution_code = ? ");
        l_sbUpdateSql.append(" and inform_div = ? ");
        l_sbUpdateSql.append(" and request_number = ? ");
        l_sbUpdateSql.append(" and branch_code = ? ");

        Object[] l_updateValues = new Object[]{
            this.variousInformParams.getInstitutionCode(),
            this.variousInformParams.getInformDiv(),
            this.variousInformParams.getRequestNumber(),
            this.variousInformParams.getBranchCode()};

        //DB更新仕様「PTS口座開設申込_各種連絡テーブル.xls」
        //PTS口座開設申込_update_DB更新仕様 参照
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                VariousInformRow.TYPE,
                l_sbUpdateSql.toString(),
                l_updateValues,
                l_updateContents);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get区分２)<BR>
     * 区分２を取得する。<BR>
     * <BR>
     * this.各種連絡行.区分２を返却する<BR>
     * <BR>
     * @@return String
     */
    public String getExtDiv2()
    {
        return this.variousInformParams.getExtDiv2();
    }
}
@
