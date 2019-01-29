head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付銘柄条件登録確認リクエスト(WEB3MutualFixedBuyConditionConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 安陽(中訊) 新規作成 モデル605
Revision History : 2008/07/31 武波(中訊) 仕様変更 モデル622
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (投信定時定額買付銘柄条件登録確認リクエスト)<BR>
 * 投信定時定額買付銘柄条件登録確認リクエスト<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101426L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionConfirmRequest.class);

    /**
     * (投信定時定額買付積立登録内容)<BR>
     * 投信定時定額買付積立登録内容<BR>
     */
    public WEB3MutualFixedBuyCommonUnit[] conditionList;

    /**
     * (投信定時定額買付新規追加内容)<BR>
     * 投信定時定額買付新規追加<BR>
     */
    public WEB3MutualFixedBuyCommonUnit[] addConditionList;

    /**
     * (投信定時定額銘柄条件登録確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4848EE9302CD
     */
    public WEB3MutualFixedBuyConditionConfirmRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * （createResponseの実装）<BR>
     * <BR>
     * 投信定時定額買付銘柄条件登録確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4848EF5A017C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualFixedBuyConditionConfirmResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）投信定時定額買付積立登録内容のチェック<BR>
     * <BR>
     * 　@　@１－１)　@投信定時定額買付積立登録内容の要素数分繰り返してチェックを行う。<BR>
     * <BR>
     * 　@　@　@　@１－１－１）銘柄コード == null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(銘柄コード未指定エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@　@　@　@1－１－２）買付金額（月々） ≠ null の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@１－１－２－１）買付金額（月々）が数字以外の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@（数字チェックエラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02476<BR>
     * <BR>
     * 　@　@　@　@１－１－２－２）買付金額（月々） ≧ 8桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@（買付金額（月々）桁数エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02477<BR>
     * <BR>
     * 　@　@　@　@1－１－３）買付金額（積み増し） ≠ null の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@　@　@1－１－３－１）買付金額（積み増し）が数字以外の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@（数字チェックエラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02478<BR>
     * <BR>
     * 　@　@　@　@　@　@1－１－３－２）買付金額（積み増し） ≧ 9桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@（買付金額（積み増し）桁数エラー）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@当リクエストデータの整合性チェックを行う。 <BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02479<BR>
     * <BR>
     * 　@　@　@　@１－１－４）変更区分 == null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(変更区分未指定エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03102<BR>
     * <BR>
     * 　@　@　@　@１－１－５）適用開始年月 == null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(適用開始年月未指定エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03103<BR>
     * <BR>
     * <BR>
     * ２）投信定時定額買付新規追加内容のチェック<BR>
     * <BR>
     * 　@　@２－１)　@投信定時定額買付新規追加内容の要素数分繰り返してチェックを行う。<BR>
     * <BR>
     * 　@　@　@　@２－１－１）銘柄コード == null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(銘柄コード未指定エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@　@　@　@２－１－２）買付金額（月々） == null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@（買付金額（月々）未入力エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02475<BR>
     * <BR>
     * 　@　@　@　@２－１－３）買付金額（月々）が数字以外の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@（数字チェックエラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02476<BR>
     * <BR>
     * 　@　@　@　@２－１－４）買付金額（月々） ≧ 8桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@（買付金額（月々）桁数エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02477<BR>
     * <BR>
     * 　@　@　@　@２－１－５）買付金額（積み増し） ≠ null の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@　@　@　@　@２－１－５－１）買付金額（積み増し）が数字以外の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@（数字チェックエラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02478<BR>
     * <BR>
     * 　@　@　@　@　@　@２－１－５－２）買付金額（積み増し） ≧ 9桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@（買付金額（積み増し）桁数エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02479<BR>
     * <BR>
     * 　@　@　@　@２－１－６）変更区分 == null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(変更区分未指定エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03102<BR>
     * <BR>
     * 　@　@　@　@２－１－７）適用開始年月 == null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@(適用開始年月未指定エラー）<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_03103<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4858ACEC022E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）投信定時定額買付積立登録内容のチェック
        if (this.conditionList != null)
        {
            //　@１－１)　@投信定時定額買付積立登録内容の要素数分繰り返してチェックを行う。
            int l_intConditionListLength = this.conditionList.length;
            for (int i = 0; i < l_intConditionListLength; i++)
            {
                //１－１－１）銘柄コード == null の場合、例外をスローする。
                String l_strMutualProductCode = this.conditionList[i].mutualProductCode;
                if (l_strMutualProductCode == null)
                {
                    log.debug("銘柄コードが未指定です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "銘柄コードが未指定です。");
                }
    
                //1－１－２）買付金額（月々） ≠ null の場合、以下のチェックを行なう。
                String l_strMonthlyBuyAmount = this.conditionList[i].monthlyBuyAmount;
                if (!WEB3StringTypeUtility.isEmpty(l_strMonthlyBuyAmount))
                {
                    //１－１－２－１）買付金額（月々）が数字以外の場合、例外をスローする。
                    if (!WEB3StringTypeUtility.isDigit(l_strMonthlyBuyAmount))
                    {
                        log.debug("買付金額（月々）が数字以外の値です。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02476,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "買付金額（月々）が数字以外の値です。");
                    }
    
                    //１－１－２－２）買付金額（月々） ≧ 8桁の場合、例外をスローする。
                    int l_intMonthlyBuyAmoutLength = l_strMonthlyBuyAmount.length();
                    if (l_intMonthlyBuyAmoutLength >= 8)
                    {
                        log.debug("買付金額（月々）桁数エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02477,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "買付金額（月々）桁数エラー。");
                    }
                }
    
                //1－１－３）買付金額（積み増し） ≠ null の場合、以下のチェックを行なう。
                String l_strIncreaseBuyAmount = this.conditionList[i].increaseBuyAmount;
                if (!WEB3StringTypeUtility.isEmpty(l_strIncreaseBuyAmount))
                {
                    //1－１－３－１）買付金額（積み増し）が数字以外の場合、例外をスローする。
                    if (!WEB3StringTypeUtility.isDigit(l_strIncreaseBuyAmount))
                    {
                        log.debug("買付金額（積み増し）が数字以外の値です。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02478,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "買付金額（積み増し）が数字以外の値です。");
                    }
    
                    //1－１－３－２）買付金額（積み増し） ≧ 9桁の場合、例外をスローする。
                    int l_intIncreaseBuyAmountLength = l_strIncreaseBuyAmount.length();
                    if (l_intIncreaseBuyAmountLength >= 9)
                    {
                        log.debug("買付金額（積み増し）桁数エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02479,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "買付金額（積み増し）桁数エラー。");
                    }
                }
    
                //１－１－４）変更区分 == null の場合、例外をスローする。
                String l_strChangeDiv = this.conditionList[i].changeDiv;
                if (l_strChangeDiv == null)
                {
                    log.debug("変更区分未指定エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03102,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "変更区分未指定エラー。");
                }
    
                //１－１－５）適用開始年月 == null の場合、例外をスローする。
                Date l_datValidStartDate = this.conditionList[i].validStartDate;
                if (l_datValidStartDate == null)
                {
                    log.debug("適用開始年月未指定エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03103,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "適用開始年月未指定エラー。");
                }
    
            }
        }

        //２）投信定時定額買付新規追加内容のチェック
        if (this.addConditionList != null)
        {
            //　@２－１)　@投信定時定額買付新規追加内容の要素数分繰り返してチェックを行う
            int l_intAddConditionListLength = this.addConditionList.length;
            for (int i = 0; i < l_intAddConditionListLength; i++)
            {
                //２－１－１）銘柄コード == null の場合、例外をスローする。
                String l_strMutualProductCode = this.addConditionList[i].mutualProductCode;
                if (l_strMutualProductCode == null)
                {
                    log.debug("銘柄コードが未指定です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "銘柄コードが未指定です。");
                }
    
                //２－１－２）買付金額（月々） == null の場合、例外をスローする。
                String l_strMonthlyBuyAmount = this.addConditionList[i].monthlyBuyAmount;
                if (l_strMonthlyBuyAmount == null)
                {
                    log.debug("買付金額入力エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02475,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "買付金額入力エラー。");
                }
    
                //２－１－３）買付金額（月々）が数字以外の場合、例外をスローする。
                if (!WEB3StringTypeUtility.isDigit(l_strMonthlyBuyAmount))
                {
                    log.debug("買付金額（月々）が数字以外の値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02476,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "買付金額（月々）が数字以外の値です。");
                }
    
                //２－１－４）買付金額（月々） ≧ 8桁の場合、例外をスローする。
                int l_intMonthlyBuyAmoutLength = l_strMonthlyBuyAmount.length();
                if (l_intMonthlyBuyAmoutLength >= 8)
                {
                    log.debug("買付金額（月々）桁数エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02477,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "買付金額（月々）桁数エラー。");
                }
    
                //２－１－５）買付金額（積み増し） ≠ null の場合、以下のチェックを行なう。
                String l_strIncreaseBuyAmount = this.addConditionList[i].increaseBuyAmount;
                if (!WEB3StringTypeUtility.isEmpty(l_strIncreaseBuyAmount))
                {
                    //２－１－５－１）買付金額（積み増し）が数字以外の場合、例外をスローする。
                    if (!WEB3StringTypeUtility.isDigit(l_strIncreaseBuyAmount))
                    {
                        log.debug("買付金額（積み増し）が数字以外の値です。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02478,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "買付金額（積み増し）が数字以外の値です。");
                    }
    
                    //２－１－５－２）買付金額（積み増し） ≧ 9桁の場合、例外をスローする。
                    int l_intIncreaseBuyAmountLength = l_strIncreaseBuyAmount.length();
                    if (l_intIncreaseBuyAmountLength >= 9)
                    {
                        log.debug("買付金額（積み増し）桁数エラー。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02479,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "買付金額（積み増し）桁数エラー。");
                    }
                }
    
                //２－１－６）変更区分 == null の場合、例外をスローする。
                String l_strChangeDiv = this.addConditionList[i].changeDiv;
                if (l_strChangeDiv == null)
                {
                    log.debug("変更区分未指定エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03102,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "変更区分未指定エラー。");
                }
    
                //２－１－７）適用開始年月 == null の場合、例外をスローする。
                Date l_datValidStartDate = this.addConditionList[i].validStartDate;
                if (l_datValidStartDate == null)
                {
                    log.debug("適用開始年月未指定エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03103,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "適用開始年月未指定エラー。");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
