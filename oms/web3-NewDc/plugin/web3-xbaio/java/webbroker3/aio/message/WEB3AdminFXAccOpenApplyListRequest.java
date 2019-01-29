head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座開設申込一覧リクエスト(WEB3AdminFXAccOpenApplyListRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/09 余新敏(中訊) 仕様変更・モデル458
                    2006/02/09 鄭徳懇(中訊) 仕様変更・モデル475
                    2006/02/22 情野(SRA) 仕様変更・モデル500
 Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.866
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.aio.define.WEB3AioFxAccountOpenDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・FX口座開設申込一覧リクエスト) <BR>
 * 管理者・FX口座開設申込一覧リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyListRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_list";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (部店コード) <BR>
     * 画面にて選択された部店コード <BR>
     * ※未入力の場合は、PR層で保持している <BR>
     * 管理者取扱可能部店の一覧をセット。
     */
    public String[] branchCodeList;

    /**
     * (ステータス区分) <BR>
     * 画面にて選択されたステータス <BR>
     * <BR>
     * 0：口座開設中 <BR>
     * 1：口座開設完了 <BR>
     * 2：口座開設エラー <BR>
     * 3：ダウンロード済 <BR>
     * 9：削除<BR>
     * <BR>
     * ※全ステータスの場合は、nullをセット。
     */
    public String statusDiv;

    /**
     * (MRF口座状況区分) <BR>
     * 画面にて選択されたMRF口座状況 <BR>
     * <BR>
     * 1：開設 <BR>
     * 2：未開設 <BR>
     * <BR>
     * ※全ての場合は、nullをセット。
     */
    public String mrfAccountStatusDiv;

    /**
     * (申込日（自）) <BR>
     * 画面にて入力された申込日（自） <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ※null：指定なし
     */
    public String applyHourFrom;

    /**
     * (申込日（至）) <BR>
     * 画面にて入力された申込日（至） <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ※null：指定なし
     */
    public String applyHourTo;

    /**
     * (要求ページ番号) <BR>
     * 要求ページ番号
     */
    public String pageIndex;

    /**
     * (ページ内表示行数) <BR>
     * ページ内表示行数
     */
    public String pageSize;

    /**
     * (約諾書区分)<BR>
     * 画面にて選択された約諾書区分<BR>
     * <BR>
     * 0：未送信<BR>
     * 1：送信済<BR>
     * 2：受領済<BR>
     * <BR>
     * ※全ての場合は、nullをセット。<BR>
     */
    public String agreementDiv;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78FB70177
     */
    public WEB3AdminFXAccOpenApplyListRequest()
    {
    }
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenApplyListRequest.class);
        
    /**
     * (validate) <BR>
     * クエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １） 部店コードのチェック <BR>
     * １－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * １－２） 要素数が0の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01757 <BR>
     * <BR>
     * １－３） 要素数分以下のチェックを行う。 <BR>
     * １－３－１）以下の条件に該当する場合、例外をスローする。 <BR>
     * ・部店コード != 数値 <BR>
     * ・部店コードの桁数 != 3桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01729 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00834 <BR>
     * <BR>
     * ２） ステータス区分のチェック <BR>
     * 未入力でない場合、以下のチェックを行う。 <BR>
     * ２－１） 以下の値以外の場合、例外をスローする。 <BR>
     * ・"口座開設中" <BR>
     * ・"口座開設完了" <BR>
     * ・"口座開設エラー" <BR>
     * ・"ダウンロード済" <BR>
     * ・"削除" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01758 <BR>
     * <BR>
     * ３） MRF口座状況区分のチェック <BR>
     * 未入力でない場合、以下のチェックを行う。 <BR>
     * ３－１） 以下の値以外の場合、例外をスローする。 <BR>
     * ・"開設" <BR>
     * ・"未開設" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01759 <BR>
     * <BR>
     * ４） 申込日(自)のチェック <BR>
     * 未入力でない場合、以下のチェックを行う。 <BR>
     * ４－１） 申込日（自）の日付部分(YYYYMMDD)が <BR>
     * Date型に変換できなかった場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01760 <BR>
     * <BR>
     * ５） 申込日(至)のチェック <BR>
     * 未入力でない場合、以下のチェックを行う。 <BR>
     * ５－１） 申込日（至）の日付部分(YYYYMMDD)が <BR>
     * Date型に変換できなかった場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01761 <BR>
     * <BR>
     * ６） 申込日(自～至)整合性のチェック <BR>
     * 申込日(自)、申込日(至)が共に未入力でない場合、 <BR>
     * 以下のチェックを行う。 <BR>
     * ６－１） 申込日(自) > 申込日(至)の場合、例外をスローする。 <BR>
     * ※上記比較は、文字列比較でよい。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01762 <BR>
     * <BR>
     * ７）要求ページ番号チェック <BR>
     * ７－１）this.要求ページ番号＝nullであった場合、 <BR>
     * 「要求ページ番号がnull」の例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00089 <BR>
     * <BR>
     * ７－２）this.要求ページ番号が数字以外の値であった場合、 <BR>
     * 「要求ページ番号が数字以外」の例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00090 <BR>
     * <BR>
     * ７－３）this.要求ページ番号≦０であった場合、 <BR>
     * 「要求ページ番号が0以下」の例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00616 <BR>
     * <BR>
     * ８）ページ内表示行数チェック <BR>
     * ８－１）this.ページ内表示行数＝nullであった場合、 <BR>
     * 「ページ内表示行数がnull」の例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00091 <BR>
     * <BR>
     * ８－２）this.ページ内表示行数が数字以外の値であった場合、 <BR>
     * 「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00092 <BR>
     * <BR>
     * ８－３）this.ページ内表示行数≦０であった場合、 <BR>
     * 「ページ内表示行数が0以下」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00617 <BR>
     * <BR>
     * ９）約諾書区分のチェック<BR>
     * 　@未入力でない場合、以下のチェックを行う。<BR>
     * 　@９－１）　@以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・"未送信"<BR>
     * 　@　@・"送信済"<BR>
     *     ・"受領済"<BR> 
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02348<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードのチェック 
        //１－１）　@未入力の場合、例外をスローする。
        if (this.branchCodeList == null)
        {
            log.debug("部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        //１－２）　@要素数が0の場合、例外をスローする。 
        if (this.branchCodeList.length == 0)
        {
            log.debug("部店コードの要素数が０です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が０です。");
        }
        
        //１－３）　@要素数分以下のチェックを行う。 
        for (int i = 0; i < this.branchCodeList.length; i++)
        {
            //１－３－１）以下の条件に該当する場合、例外をスローする。 
            // ・部店コード != 数値 
            if (!WEB3StringTypeUtility.isNumber(this.branchCodeList[i]))
            {
                log.debug("部店コードの値が数値以外の値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの値が数値以外の値です。");
            }
            
            // ・部店コードの桁数 != 3桁 
            if (this.branchCodeList[i].getBytes().length != 3)
            {
                log.debug("部店コードのサイズが不正です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }
        }
        
        //２）　@ステータス区分のチェック 
        // 未入力でない場合、以下のチェックを行う。 
        if (!WEB3StringTypeUtility.isEmpty(this.statusDiv))
        {
            //２－１）　@以下の値以外の場合、例外をスローする。 
            // ・"口座開設中" 
            // ・"口座開設完了" 
            // ・"口座開設エラー" 
            // ・"ダウンロード済" 
            // ・"削除" 
            if (!(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.DELETE.equals(this.statusDiv)))
            {
                log.debug("ステータス区分が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ステータス区分が存在しないコード値です。");
            }
        }
        
        //３）　@MRF口座状況区分のチェック 
        // 未入力でない場合、以下のチェックを行う。 
        if (!WEB3StringTypeUtility.isEmpty(this.mrfAccountStatusDiv))
        {
            //３－１）　@以下の値以外の場合、例外をスローする。 
            // ・"開設" 
            // ・"未開設" 
            if (!(WEB3AioFxAccountOpenDivDef.OPEN.equals(this.mrfAccountStatusDiv)
                || WEB3AioFxAccountOpenDivDef.NOT_OPEN.equals(this.mrfAccountStatusDiv)))
            {
                log.debug("MRF口座状況区分が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01759,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "MRF口座状況区分が存在しないコード値です。");
            }
        }
        
        //４）　@申込日(自)のチェック 
        // 未入力でない場合、以下のチェックを行う。 
        if (!WEB3StringTypeUtility.isEmpty(this.applyHourFrom))
        {
            //４－１）　@申込日（自）の日付部分(YYYYMMDD)が 
            // Date型に変換できなかった場合、例外をスローする。 
            if (!WEB3StringTypeUtility.isDateStr(
                (this.applyHourFrom.length() > 8) ? this.applyHourFrom.substring(0, 8) : this.applyHourFrom, "yyyyMMdd"))
            {
                log.debug("申込日（自）の入力値が不正です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01760,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込日（自）の入力値が不正です。");
            }
        }
        
        //５）　@申込日(至)のチェック 
        //　@未入力でない場合、以下のチェックを行う。 
        if (!WEB3StringTypeUtility.isEmpty(this.applyHourTo))
        {
            //５－１）　@申込日（至）の日付部分(YYYYMMDD)が 
            // Date型に変換できなかった場合、例外をスローする。 
            if (!WEB3StringTypeUtility.isDateStr(
                (this.applyHourTo.length() > 8) ? this.applyHourTo.substring(0, 8) : this.applyHourTo, "yyyyMMdd"))
            {
                log.debug("申込日（至）の入力値が不正です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01761,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込日（至）の入力値が不正です。");
            }
        }
        
        //６）　@申込日(自～至)整合性のチェック 
        // 申込日(自)、申込日(至)が共に未入力でない場合、
        // 以下のチェックを行う。 
        if (!WEB3StringTypeUtility.isEmpty(this.applyHourFrom) && 
            !WEB3StringTypeUtility.isEmpty(this.applyHourTo))
        {
            //６－１）　@申込日(自) > 申込日(至)の場合、例外をスローする。 
            // ※上記比較は、文字列比較でよい。 
            if (this.applyHourFrom.compareTo(this.applyHourTo) > 0)
            {
                log.debug("申込日（自）は申込日（至）より大きいです。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01762,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込日（自）は申込日（至）より大きいです。");
            }
        }

        //７）要求ページ番号チェック 
        //７－１）this.要求ページ番号＝nullであった場合、 
        // 「要求ページ番号がnull」の例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //７－２）this.要求ページ番号が数字以外の値であった場合、 
        //「要求ページ番号が数字以外」の例外をスローする。 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //７－３）this.要求ページ番号≦０であった場合、 
        //　@「要求ページ番号が0以下」の例外をスローする。 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }
        
        //８）ページ内表示行数チェック 
        //８－１）this.ページ内表示行数＝nullであった場合、 
        // 「ページ内表示行数がnull」の例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        
        //８－２）this.ページ内表示行数が数字以外の値であった場合、 
        // 「ページ内表示行数が数字以外」の例外をスローする。 
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        //８－３）this.ページ内表示行数≦０であった場合、 
        //「ページ内表示行数が0以下」の例外をスローする。 
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        
        //９）約諾書区分のチェック
        //　@未入力でない場合、以下のチェックを行う。
        //　@９－１）　@以下の値以外の場合、例外をスローする。
        //　@　@・"未送信"
        //　@　@・"送信済"
        //　@　@・"受領済"
        if(this.agreementDiv != null)
        {
            if(!(WEB3AioAgreementDivDef.NOT_SEND.equals(this.agreementDiv) 
                ||WEB3AioAgreementDivDef.SENDED.equals(this.agreementDiv)
                ||WEB3AioAgreementDivDef.RECIEVED.equals(this.agreementDiv)))
            {
                log.debug("約諾書区分の値が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02348,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "約諾書区分の値が不正です。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }


    /**
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX口座開設申込一覧レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E78FB70213
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccOpenApplyListResponse(this);
    }
}@
