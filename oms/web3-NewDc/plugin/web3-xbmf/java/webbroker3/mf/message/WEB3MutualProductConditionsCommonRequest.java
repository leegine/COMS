head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductConditionsCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信銘柄条件登録共通リクエスト(WEB3MutualProductConditionsCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 黄建 (中訊) 新規作成                                   
Revesion History : 2006/05/18 周捷 (中訊) 仕様変更・モデル414
Revesion History : 2006/10/19 周捷 (中訊) 仕様変更・モデル499、505
Revesion History : 2007/04/24 張騰宇 (中訊) 仕様変更・モデル567
Revesion History : 2007/10/15 孫洪江 (中訊) 仕様変更・モデル579
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3RecruitCommissionDivDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.define.WEB3UnitTypeProductDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信銘柄条件登録共通リクエスト)<BR>
 * 投信銘柄条件登録共通リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualProductConditionsCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412011741L;
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualProductConditionsCommonRequest.class);
    
    /**
     * (銘柄情報)<BR>
     * 投信銘柄条件登録共通情報
     */
    public WEB3MutualProductConditionsCommonInfo mutualProductInfo;
    
    /**
     * (投信銘柄条件登録共通リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 417745C90255
     */
    public WEB3MutualProductConditionsCommonRequest()
    {
    }
    
    /**
     * (validate)<BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * 1) 銘柄情報のチェック<BR>
     * 　@ this.銘柄情報＝nullの場合、例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01251<BR>
     * <BR>
     * 2) WEBBROKER3取扱状況のチェック <BR>
     * 　@this.WEBBROKER3取扱状況!=nullであり、かつ以下の値以外の場合、<BR> 
     * 　@例外をスローする。 <BR>
     * 　@”0:WEBBROKER3で取り扱わない” <BR>
     * 　@”1:WEBBROKER3で取り扱う” <BR>
     * 　@”2:郵送請求のみ” <BR>
     *  <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01292<BR>
     *<BR>
     * 3) 銘柄コードのチェック<BR>
     * 　@ this.銘柄情報.銘柄コード＝nullの場合、例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01252<BR>
     * <BR>
     * 4) 銘柄名（英名）のチェック<BR>
     * 　@ this.銘柄情報.銘柄名（英名）!=nullであり、<BR>
     *    かつセットされている値に全角文字<BR>
     * 　@ が含まれている場合、例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01253<BR>
     * <BR>
     * 5) 指定方法@（買付）のチェック<BR>
     * 　@  this.銘柄情報.指定方法@（買付）!=nullであり、かつセットされている値が<BR>
     * 　@　@以下の値のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"選択指定"<BR>
     * 　@　@　@"金額"<BR>
     * 　@　@　@"口数"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01254<BR>
     * <BR>
     * 6) 指定方法@（解約）のチェック<BR>
     * 　@  this.銘柄情報.指定方法@（解約）!=nullであり、かつセットされている値が<BR>
     * 　@　@以下の値のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"選択指定"<BR>
     * 　@　@　@"金額"<BR>
     * 　@　@　@"口数"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01255<BR>
     * <BR>
     * 7) 指定方法@（乗換）のチェック<BR>
     * 　@  this.銘柄情報.指定方法@（乗換）!=nullであり、かつセットされている値が<BR>
     * 　@　@以下の値のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"選択指定"<BR>
     * 　@　@　@"金額"<BR>
     * 　@　@　@"口数"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01256<BR>
     * <BR>
     * 8) 指定方法@（募集）のチェック <BR>
     * 　@this.銘柄情報.指定方法@（募集）!=nullであり、かつセットされている値が <BR>
     * 　@　@以下の値のいずれかではない場合、例外をスローする。 <BR>
     * 　@　@　@"選択指定" <BR>
     * 　@　@　@"金額" <BR>
     * 　@　@　@"口数" <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_02264<BR>
     * <BR>
     * 9) this.銘柄情報.銘柄コード以外、全ての属性＝nullだった場合、<BR>
     *    例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01257<BR>
     * <BR>
     * 10) this.銘柄情報.買付可能区分（当日発注分）!=nullであり、<BR>
     *    かつセットされている<BR>
     * 　@ 値が以下のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"不可"<BR>
     * 　@　@　@"可"<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:BUSINESS_ERROR_01258<BR>
     * <BR>
     * 11) this.銘柄情報.解約可能区分（当日発注分）!=nullであり、<BR>
     *     かつセットされている<BR>
     * 　@  値が以下のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"不可"<BR>
     * 　@　@　@"可"<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_01259<BR>
     * <BR>
     * 12) this.銘柄情報.乗換可能区分（当日発注分）!=nullであり、<BR>
     *      かつセットされている<BR>
     * 　@   値が以下のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"不可"<BR>
     * 　@　@　@"可"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01260<BR>
     * <BR>
     * 13) this.銘柄情報.募集可能区分（当日発注分）!=nullであり、かつセットされている <BR>
     * 　@値が以下のいずれかではない場合、例外をスローする。 <BR>
     * 　@　@　@"不可" <BR>
     * 　@　@　@"可" <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_02265<BR>
     * <BR>
     * 14) this.銘柄情報.買付可能区分（翌日発注分）!=nullであり、<BR>
     *      かつセットされている<BR>
     * 　@   値が以下のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"不可"<BR>
     * 　@　@　@"可"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01261<BR>
     * <BR>
     * 15) this.銘柄情報.解約可能区分（翌日発注分）!=nullであり、<BR>
     *      かつセットされている<BR>
     * 　@   値が以下のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"不可"<BR>
     * 　@　@　@"可"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01262<BR>
     * <BR>
     * 16) this.銘柄情報.乗換可能区分（翌日発注分）!=nullであり、<BR>
     *      かつセットされている<BR>
     * 　@   値が以下のいずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"不可"<BR>
     * 　@　@　@"可"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01263<BR>
     * <BR>
     * 17) this.銘柄情報.募集可能区分（翌日発注分）!=nullであり、かつセットされている <BR>
     * 　@値が以下のいずれかではない場合、例外をスローする。 <BR>
     * 　@　@　@"不可" <BR>
     * 　@　@　@"可" <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02266<BR>
     * <BR>
     * 18) 注文受付停止時間のチェック<BR>
     *  18-1) this.銘柄情報.注文受付停止開始時間（平日）!=nullであり、<BR>
     *          かつ値が数字以外の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01264<BR>
     *  18-2) this.銘柄情報.注文受付停止終了時間（平日）!=nullであり、<BR>
     *          かつ値が数字以外の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01265<BR>
     *  18-3) this.銘柄情報.注文受付停止開始時間（平日）!=nullであり、<BR>
     *          かつ注文受付停止終了時間（平日）!=nullであり、<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01266<BR>
     * 　@　@     かつ”開始時間＞終了時間”の場合、例外をスローする。<BR>
     *  18-4) this.銘柄情報.注文受付停止終了時間（平日）!=nullであり、かつ<BR>
     * 　@　@    ”終了時間（平日）＞235959”の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01267<BR>
     *  18-5) this.銘柄情報.注文受付停止開始時間（半日）!=nullであり、<BR>
     *          かつ値が数字以外の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01268<BR>
     *  18-6) this.銘柄情報.注文受付停止終了時間（半日）!=nullであり、<BR>
     *          かつ値が数字以外の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01269<BR>
     *  18-7) this.銘柄情報.注文受付停止開始時間（半日）!=nullであり、
     *          かつ注文受付停止終了時間（半日）!=nullであり、<BR>
     * 　@　@     かつ”開始時間＞終了時間”の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01270<BR>
     *  18-8) this.銘柄情報.注文受付停止終了時間（半日）!=nullであり、かつ<BR>
     * 　@　@     ”終了時間＞235959”の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01271<BR>
     * <BR>
     * 19) オペレーション日付のチェック<BR>
     *      this.銘柄情報.オペレーション日付＝nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01272<BR>
     * <BR>
     * 20) 銘柄名（和名）のチェック <BR>
     * 　@this.銘柄情報.銘柄名（和名）!=nullであり、かつセットされている値に半角カナ文字 <BR>
     * 　@　@が含まれている場合、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_01996<BR>
     * <BR>
     * 21)買付制限区分のチェック <BR>
     *   this.銘柄情報.買付制限区分!=nullであり、かつセットされている値が以下のいずれか <BR>
     *   ではない場合、例外をスローする。 <BR>
     * 　@　@　@"買付可" <BR>
     * 　@　@　@"乗換買付のみ可" <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02267<BR>
     * <BR>
     * 22)受渡方法@のチェック <BR>
     * 　@this.銘柄情報,受渡方法@!=nullであり、かつセットされている値が以下のいずれかではない場合、<BR> 
     * 　@例外をスローする。 <BR>
     * 　@　@　@"0：選択指定" <BR>
     * 　@　@　@"1：銀行振込" <BR>
     * 　@　@　@"2：証券口座入金"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00099<BR>
     * <BR>
     * 23)特定日取引銘柄区分のチェック <BR>
     *   this.特定日取引銘柄区分!=nullであり、かつセットされている値が以下のいずれかではない場合、<BR> 
     *   　@例外をスローする。 <BR>
     *   　@　@　@"通常銘柄" <BR>
     *   　@　@　@"買付のみ" <BR>
     *   　@　@　@"解約のみ" <BR>
     *   　@　@　@"両方"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02679<BR>
     * <BR>
     * 24)募集手数料区分のチェック<BR>
     * 　@this.銘柄情報,募集手数料区分!=nullであり、かつセットされている値が以下の<BR>
     * 　@いずれかではない場合、例外をスローする。<BR>
     * 　@　@　@"0：なし"<BR>
     * 　@　@　@"1：内枠"<BR>
     * 　@　@　@"2：外枠"<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_02949<BR>
     * @@throws WEB3BaseException
     * @@roseuid 417745CA00CF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1)銘柄情報のチェック 
        //  this.銘柄情報＝nullの場合、例外をスローする。
        if (this.mutualProductInfo == null)
        {
            log.debug("銘柄情報が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01251,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄情報が未指定です。");
        }
        
        //2)WEBBROKER3取扱状況のチェック 
        //  this.WEBBROKER3取扱状況!=nullであり、かつ以下の値以外の場合、 
        //  例外をスローする。 
        //     ”0:WEBBROKER3で取り扱わない” 
        //     ”1:WEBBROKER3で取り扱う”
        //     ”2:郵送請求のみ”  
        if (this.mutualProductInfo.web3TreatmentFlag != null)
        {
            if (!(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN.equals(
                    this.mutualProductInfo.web3TreatmentFlag)
                || WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN.equals(
                    this.mutualProductInfo.web3TreatmentFlag)
                || WEB3SystemHandlingDivDef.MAIL_REQUEST_ONLY.equals(
                    this.mutualProductInfo.web3TreatmentFlag)))
            {
                log.debug("WEBBROKER3取扱状況の値が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01292,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "WEBBROKER3取扱状況の値が存在しないコード値です。");
            }
        }
        
        //3)銘柄コードのチェック  
        //  this.銘柄情報.銘柄コード＝nullの場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.mutualProductCode))
        {
            log.debug("銘柄情報の銘柄コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄情報の銘柄コードが未指定です。");
        }
        
        //4)銘柄名（英名）のチェック 
        //  this.銘柄情報.銘柄名（英名）!=nullであり、かつセットされている値に全角文字 
        //  が含まれている場合、例外をスローする。 
        if ((this.mutualProductInfo.engProductName != null)
            && (!WEB3StringTypeUtility.isSingle(this.mutualProductInfo.engProductName)))
        {
            log.debug("銘柄情報の銘柄名（英名）の値に全角文字が含まれている。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01253,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄情報の銘柄名（英名）の値に全角文字が含まれている。");
        }
        
        //5)指定方法@（買付）のチェック 
        //  this.銘柄情報.指定方法@（買付）!=nullであり、かつセットされている値が 
        //  以下の値のいずれかではない場合、例外をスローする。 
        //    "選択指定" 
        //    "金額" 
        //    "口数" 
        if (this.mutualProductInfo.buySelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.buySelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.buySelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.buySelectable)))
            {
                log.debug("銘柄情報の指定方法@（買付）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01254,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の指定方法@（買付）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //6)指定方法@（解約）のチェック 
        //  this.銘柄情報.指定方法@（解約）!=nullであり、かつセットされている値が 
        //  以下の値のいずれかではない場合、例外をスローする。 
        //　@ "選択指定" 
        //　@ "金額" 
        //　@ "口数" 
        if (this.mutualProductInfo.sellSelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.sellSelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.sellSelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.sellSelectable)))
            {
                log.debug("銘柄情報の指定方法@（解約）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01255,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の指定方法@（解約）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //7)指定方法@（乗換）のチェック 
        //  this.銘柄情報.指定方法@（乗換）!=nullであり、かつセットされている値が 
        //  以下の値のいずれかではない場合、例外をスローする。 
        //　@ "選択指定" 
        //　@ "金額" 
        //　@ "口数" 
        if (this.mutualProductInfo.switchingSelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.switchingSelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.switchingSelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.switchingSelectable)))
            {
                log.debug("銘柄情報の指定方法@（乗換）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01256,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の指定方法@（乗換）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //8) 指定方法@（募集）のチェック 
        //　@this.銘柄情報.指定方法@（募集）!=nullであり、かつセットされている値が 
        //　@以下の値のいずれかではない場合、例外をスローする。 
        //　@　@"選択指定" 
        //　@　@"金額" 
        //　@　@"口数" 
        if (this.mutualProductInfo.applySelectable != null)
        {
            if (!(WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                    this.mutualProductInfo.applySelectable)
                || WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    this.mutualProductInfo.applySelectable)
                || WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    this.mutualProductInfo.applySelectable)))
            {
                log.debug("銘柄情報の指定方法@（募集）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02264,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の指定方法@（募集）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //9) this.銘柄情報.銘柄コード以外、全ての属性＝nullだった場合、例外をスローする。 
        if (
            WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.web3TreatmentFlag)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.jpnProductName)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.engProductName)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.categoryCode)
            && this.mutualProductInfo.buyStartDate == null
            && this.mutualProductInfo.buyEndDate == null
            && this.mutualProductInfo.sellSwitchingStartDate == null
            && this.mutualProductInfo.sellSwitchingEndDate == null
            && this.mutualProductInfo.buyClaimStartDate == null
            && this.mutualProductInfo.buyClaimEndDate == null
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.buySelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.newBuyUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.addBuyUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellSelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.sellUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingSelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.switchingUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todayBuyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todaySellPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todaySwitchingPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDayBuyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDaySellPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDaySwitchingPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseStartTimeFull)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseEndTimeFull)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseStartTimeHalf)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.orderCloseEndTimeHalf)
            && this.mutualProductInfo.operationDate == null
            && this.mutualProductInfo.applyAbleStartDate == null
            && this.mutualProductInfo.applyAbleEndDate == null
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applySelectable)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyMinQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyUnitQty)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyMinAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyUnitAmt)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.todayApplyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.nextDayApplyPossDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.buyRestrictionDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.deliveryVariation)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.unitTypeProductDiv)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnMinAmtBuy)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnUnitAmtBuy)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnMinAmtAdd)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnUnitAmtAdd)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnMinAmtSell)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.frgnUnitAmtSell)
            && WEB3StringTypeUtility.isEmpty(this.mutualProductInfo.applyCommissionDiv))
        {
            log.debug("銘柄情報の全ての属性が未指定です。（銘柄コード以外）。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01257,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄情報の全ての属性が未指定です。（銘柄コード以外）。");
        }
        
        //10)this.銘柄情報.買付可能区分（当日発注分）!=nullであり、かつセットされている 
        //  値が以下のいずれかではない場合、例外をスローする。 
        //　@ "不可" 
        //　@ "可" 
        if (this.mutualProductInfo.todayBuyPossDiv != null)
        {
            if (!(WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(
                    this.mutualProductInfo.todayBuyPossDiv)
                || WEB3BuyPossibleDivDef.ACQUIRED.equals(
                    this.mutualProductInfo.todayBuyPossDiv)))
            {
                log.debug("銘柄情報の買付可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01258,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の買付可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //11) this.銘柄情報.解約可能区分（当日発注分）!=nullであり、かつセットされている 
        //  値が以下のいずれかではない場合、例外をスローする。 
        //　@ "不可" 
        //　@ "可" 
        if (this.mutualProductInfo.todaySellPossDiv != null)
        {
            if (!(WEB3SellPossibleDivDef.NOT_SELL.equals(
                    this.mutualProductInfo.todaySellPossDiv)
                || WEB3SellPossibleDivDef.SELL.equals(
                    this.mutualProductInfo.todaySellPossDiv)))
            {
                log.debug("銘柄情報の解約可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01259,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の解約可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //12) this.銘柄情報.乗換可能区分（当日発注分）!=nullであり、かつセットされている 
        //  値が以下のいずれかではない場合、例外をスローする。 
        //　@ "不可" 
        //　@ "可" 
        if (this.mutualProductInfo.todaySwitchingPossDiv != null)
        {
            if (!(WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(
                    this.mutualProductInfo.todaySwitchingPossDiv)
                || WEB3SwtPossibleDivDef.SWITCHING.equals(
                    this.mutualProductInfo.todaySwitchingPossDiv)))
            {
                log.debug("銘柄情報の乗換可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01260,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の乗換可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //13) this.銘柄情報.募集可能区分（当日発注分）!=nullであり、かつセットされている 
        //　@値が以下のいずれかではない場合、例外をスローする。 
        //　@"不可" 
        //　@"可" 
        if (this.mutualProductInfo.todayApplyPossDiv != null)
        {
            if (!(WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(
                    this.mutualProductInfo.todayApplyPossDiv)
                || WEB3MFRecruitPossibleDivDef.RECRUIT.equals(
                    this.mutualProductInfo.todayApplyPossDiv)))
            {
                log.debug("銘柄情報の募集可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02265,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の募集可能区分（当日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //14) this.銘柄情報.買付可能区分（翌日発注分）!=nullであり、かつセットされている 
        //  値が以下のいずれかではない場合、例外をスローする。 
        //　@ "不可" 
        //　@ "可" 
        if (this.mutualProductInfo.nextDayBuyPossDiv != null)
        {
            if (!(WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(
                    this.mutualProductInfo.nextDayBuyPossDiv)
                || WEB3BuyPossibleDivDef.ACQUIRED.equals(
                    this.mutualProductInfo.nextDayBuyPossDiv)))
            {
                log.debug("銘柄情報の買付可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01261,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の買付可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //15)this.銘柄情報.解約可能区分（翌日発注分）!=nullであり、かつセットされている 
        //  値が以下のいずれかではない場合、例外をスローする。 
        //　@ "不可" 
        //　@ "可" 
        if (this.mutualProductInfo.nextDaySellPossDiv != null)
        {
            if (!(WEB3SellPossibleDivDef.NOT_SELL.equals(
                    this.mutualProductInfo.nextDaySellPossDiv)
                || WEB3SellPossibleDivDef.SELL.equals(
                    this.mutualProductInfo.nextDaySellPossDiv)))
            {
                log.debug("銘柄情報の解約可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01262,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の解約可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //16) this.銘柄情報.乗換可能区分（翌日発注分）!=nullであり、かつセットされている 
        //  値が以下のいずれかではない場合、例外をスローする。 
        //　@ "不可" 
        //　@ "可" 
        if (this.mutualProductInfo.nextDaySwitchingPossDiv != null)
        {
            if (!(WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(
                    this.mutualProductInfo.nextDaySwitchingPossDiv)
                || WEB3SwtPossibleDivDef.SWITCHING.equals(
                    this.mutualProductInfo.nextDaySwitchingPossDiv)))
            {
                log.debug("銘柄情報の乗換可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01263,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の乗換可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //17) this.銘柄情報.募集可能区分（翌日発注分）!=nullであり、かつセットされている 
        //　@値が以下のいずれかではない場合、例外をスローする。 
        //　@　@"不可" 
        //　@　@"可" 
        if (this.mutualProductInfo.nextDayApplyPossDiv != null)
        {
            if (!(WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(
                    this.mutualProductInfo.nextDayApplyPossDiv)
                || WEB3MFRecruitPossibleDivDef.RECRUIT.equals(
                    this.mutualProductInfo.nextDayApplyPossDiv)))
            {
                log.debug("銘柄情報の募集可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02266,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報の募集可能区分（翌日発注分）の値は規定枚挙値の範囲ではないです。");
            }
        }
        
        //18) 注文受付停止時間のチェック 
        //  18-1) this.銘柄情報.注文受付停止開始時間（平日）!=nullであり、
        //      かつ値が数字以外の場合、例外をスローする。
        if (this.mutualProductInfo.orderCloseStartTimeFull != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseStartTimeFull))
        {
            log.debug("注文受付停止開始時間（平日）が数字以外の値である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01264,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文受付停止開始時間（平日）が数字以外の値である。");
        }
        
        //18-2) this.銘柄情報.注文受付停止終了時間（平日）!=nullであり、
        //  かつ値が数字以外の場合、例外をスローする。
        if (this.mutualProductInfo.orderCloseEndTimeFull != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseEndTimeFull))
        {
            log.debug("注文受付停止終了時間（平日）が数字以外の値である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01265,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文受付停止終了時間（平日）が数字以外の値である。");
        }
        
        //18-3) this.銘柄情報.注文受付停止開始時間（平日）!=nullであり、
        //  かつ注文受付停止終了時間（平日）!=nullであり、
        //　@かつ”開始時間＞終了時間”の場合、例外をスローする。 
        if (this.mutualProductInfo.orderCloseStartTimeFull != null
            && this.mutualProductInfo.orderCloseEndTimeFull != null)
        {
            if (this.mutualProductInfo.orderCloseStartTimeFull.compareTo(
                    this.mutualProductInfo.orderCloseEndTimeFull) > 0)
            {
                log.debug("注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01266,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。");
            }
        }
       
        //18-4) this.銘柄情報.注文受付停止終了時間（平日）!=nullであり、かつ 
        //　@　@”終了時間（平日）＞235959”の場合、例外をスローする。
        if (this.mutualProductInfo.orderCloseEndTimeFull != null)
        {
            if (Integer.parseInt(this.mutualProductInfo.orderCloseEndTimeFull)
                > 2359)
            {
                log.debug("注文受付停止終了時間（平日）が“2359”を超えています。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01267,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文受付停止終了時間（平日）が“2359”を超えています。");
            }
        }
       
        //18-5) this.銘柄情報.注文受付停止開始時間（半日）!=nullであり、
        //かつ値が数字以外の場合、例外をスローする。
        if (this.mutualProductInfo.orderCloseStartTimeHalf != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseStartTimeHalf))
        {
            log.debug("注文受付停止開始時間（半日）が数字以外の値である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01268,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文受付停止開始時間（半日）が数字以外の値である。");
        }
       
        //18-6) this.銘柄情報.注文受付停止終了時間（半日）!=nullであり、
        //  かつ値が数字以外の場合、例外をスローする。
        if (this.mutualProductInfo.orderCloseEndTimeHalf != null
            && !WEB3StringTypeUtility.isDigit(
                this.mutualProductInfo.orderCloseEndTimeHalf))
        {
            log.debug("注文受付停止終了時間（半日）が数字以外の値である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01269,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文受付停止終了時間（半日）が数字以外の値である。");
        }
       
        //18-7) this.銘柄情報.注文受付停止開始時間（半日）!=nullであり、
        //  かつ注文受付停止終了時間（半日）!=nullであり、 
        //  かつ”開始時間＞終了時間”の場合、例外をスローする。 
        if (this.mutualProductInfo.orderCloseStartTimeHalf != null
            && this.mutualProductInfo.orderCloseEndTimeHalf != null)
        {
            if (this.mutualProductInfo.orderCloseStartTimeHalf.compareTo(
                    this.mutualProductInfo.orderCloseEndTimeHalf) > 0)
            {
                log.debug("注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01270,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。");
            }
        }
        
        //18-8) this.銘柄情報.注文受付停止終了時間（半日）!=nullであり、かつ 
        //　@　@”終了時間＞235959”の場合、例外をスローする。 
        if (this.mutualProductInfo.orderCloseEndTimeHalf != null)
        {
            if (Integer.parseInt(this.mutualProductInfo.orderCloseEndTimeHalf)
                > 2359)
            {
                log.debug("注文受付停止終了時間（半日）が“2359”を超えています。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01271,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文受付停止終了時間（半日）が“2359”を超えています。");
            }
        }
        
        //19) オペレーション日付のチェック 
        //  this.銘柄情報.オペレーション日付＝nullの場合、例外をスローする。
        if (this.mutualProductInfo.operationDate == null)
        {
            log.debug("銘柄情報のオペレーション日付が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01272,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄情報のオペレーション日付が未指定です。");
        }
        
        //20)銘柄名（和名）のチェック 
        //  this.銘柄情報.銘柄名（和名）!=nullであり、かつセットされている値に半角カナ文字 
        //  が含まれている場合、例外をスローする。 
        if ((this.mutualProductInfo.jpnProductName != null)
            && (WEB3StringTypeUtility.has1byteKana(this.mutualProductInfo.jpnProductName)))
        {
            log.debug("銘柄情報の銘柄名（和名）の値に半角カナ文字が含まれている。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01996,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄情報の銘柄名（和名）の値に半角カナ文字が含まれている。");
        }
        
        //21)買付制限区分のチェック 
        // this.銘柄情報.買付制限区分!=nullであり、かつセットされている値が以下のいずれか 
        //ではない場合、例外をスローする。 
        //　@　@"買付可" 
        //　@　@"乗換買付のみ可" 
        if (this.mutualProductInfo.buyRestrictionDiv != null)
        {
            if (!WEB3BuyLimitDivDef.BUY_POSSIBLE.equals(this.mutualProductInfo.buyRestrictionDiv) &&
                !WEB3BuyLimitDivDef.ONLY_SWITCHING_BUY_POSSIBLE.equals(this.mutualProductInfo.buyRestrictionDiv))
            {
                log.debug("銘柄情報.買付制限区分の値が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02267,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報.買付制限区分の値が存在しないコード値です。");
            }
        }
        
        //22)受渡方法@のチェック 
        //　@this.銘柄情報,受渡方法@!=nullであり、かつセットされている値が以下のいずれかではない場合、
        //　@例外をスローする。
        //　@　@　@"0：選択指定" 
        //　@　@　@"1：銀行振込" 
        //　@　@　@"2：証券口座入金"
        if(this.mutualProductInfo.deliveryVariation != null)
        {
            if(!WEB3DeliveryMethodDef.SELECT_DESIGNATE.equals(this.mutualProductInfo.deliveryVariation) 
                && !WEB3DeliveryMethodDef.BANK_TRANSFER.equals(this.mutualProductInfo.deliveryVariation) 
                && !WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL.equals(this.mutualProductInfo.deliveryVariation))
            {
                log.debug("銘柄情報.受渡方法@の値が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄情報.受渡方法@の値が存在しないコード値です。");
            }
        }
        
        //23)特定日取引銘柄区分のチェック
        // this.特定日取引銘柄区分!=nullであり、かつセットされている値が以下のいずれかではない場合、
        // 例外をスローする。
        //   "通常銘柄"
        //   "買付のみ"
        //   "解約のみ"
        //   "両方"
        if (this.mutualProductInfo.unitTypeProductDiv != null)
        {
            if ((!WEB3UnitTypeProductDivDef.NORMAL_PRODUCT.equals(this.mutualProductInfo.unitTypeProductDiv)) &&
                (!WEB3UnitTypeProductDivDef.BUY.equals(this.mutualProductInfo.unitTypeProductDiv)) &&
                (!WEB3UnitTypeProductDivDef.OF_A_CONTRACT.equals(this.mutualProductInfo.unitTypeProductDiv)) &&
                (!WEB3UnitTypeProductDivDef.BOTH.equals(this.mutualProductInfo.unitTypeProductDiv)))
            {
                log.debug("特定日取引銘柄区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02679,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "特定日取引銘柄区分の値が存在しないコード値です。");
            }
        }

        //24)募集手数料区分のチェック
        // 　@this.銘柄情報,募集手数料区分!=nullであり、かつセットされている値が以下の
        // 　@いずれかではない場合、例外をスローする。
        // 　@　@　@"0：なし"
        // 　@　@　@"1：内枠"
        // 　@　@　@"2：外枠"
        if (this.mutualProductInfo.applyCommissionDiv != null)
        {
            if ((!WEB3RecruitCommissionDivDef.NOTHING.equals(this.mutualProductInfo.applyCommissionDiv))
                && (!WEB3RecruitCommissionDivDef.WITHIN_THE_LIMIT.equals(this.mutualProductInfo.applyCommissionDiv))
                && (!WEB3RecruitCommissionDivDef.WITHOUT_THE_LIMIT.equals(
                    this.mutualProductInfo.applyCommissionDiv)))
            {
                log.debug("募集手数料区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02949,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "募集手数料区分が存在しないコード値です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF816102A6
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
