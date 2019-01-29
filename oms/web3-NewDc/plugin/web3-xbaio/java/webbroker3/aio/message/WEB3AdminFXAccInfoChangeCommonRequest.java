head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座情報変更共通リクエスト(WEB3AdminFXAccInfoChangeCommonRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/09 余新敏(中訊) 仕様変更・モデル458
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioFxAccountInfoDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・FX口座情報変更共通リクエスト) <BR>
 * 管理者・FX口座情報変更共通リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoChangeCommonRequest extends WEB3GenRequest
{
    /**
     * (部店コード) <BR>
     * 選択された部店コード
     */
    public String branchCode;

    /**
     * (顧客コード) <BR>
     * 選択された顧客コード
     */
    public String accountCode;

    /**
     * (処理区分) <BR>
     * 0：口座情報変更 <BR>
     * 1：口座開設状況変更
     */
    public String operationDiv;

    /**
     * (更新後メールアドレス) <BR>
     * 更新後のメールアドレス <BR>
     * <BR>
     * 処理区分＝0（口座情報変更）の場合、設定
     */
    public String updatedMailAddress;

    /**
     * (更新後FX口座情報一覧) <BR>
     * 更新後のFX口座情報の一覧 <BR>
     * <BR>
     * 処理区分＝0（口座情報変更）の場合、設定
     */
    public WEB3FXAccInformationUnit[] updatedFxAccInformationList;

    /**
     * (更新後口座開設状況区分) <BR>
     * 更新後の口座開設状況区分<BR>
     * 1：開設済<BR>
     * 2：振替停止<BR>
     * 9：抹消<BR>
     * <BR>
     * 処理区分＝1（口座開設状況変更）の場合、設定
     */
    public String updatedAccountOpenStatusDiv;

    /**
     * @@roseuid 41E78FE3005D
     */
    public WEB3AdminFXAccInfoChangeCommonRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccInfoChangeCommonRequest.class);
        
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
     * １－１） 以下の条件に該当する場合、例外をスローする。 <BR>
     * ・部店コード != 数値 <BR>
     * ・部店コードの桁数 != 3桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01729 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00834 <BR>
     * <BR>
     * ２） 顧客コードのチェック <BR>
     * ２－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00835 <BR>
     * <BR>
     * ２－２） 以下の条件に該当する場合、例外をスローする。 <BR>
     * ・顧客コード != 数値 <BR>
     * ・顧客コードの桁数 != 6桁 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01043 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00836 <BR>
     * <BR>
     * ３） 処理区分のチェック <BR>
     * ３－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01249 <BR>
     * <BR>
     * ３－２） 以下の値以外の場合、例外をスローする。 <BR>
     * ・"口座情報変更" <BR>
     * ・"口座開設状況変更" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01250 <BR>
     * <BR>
     * ４） 更新後メールアドレスのチェック <BR>
     * this.処理区分 == "口座情報変更"の場合、以下のチェックを行う。 <BR>
     * ４－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01767 <BR>
     * <BR>
     * ５） 更新後FX口座情報一覧のチェック <BR>
     * this.処理区分 == "口座情報変更"の場合、以下のチェックを行う。 <BR>
     * ５－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01768 <BR>
     * <BR>
     * ５－２） 要素数が0の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01769 <BR>
     * <BR>
     * ５－３） 更新後FX口座情報一覧の要素数分、 <BR>
     * 以下の処理を繰り返す。 <BR>
     * ５－３－１） FX口座情報.validate()をコールする。 <BR>
     * <BR>
     * ６） 更新後口座開設状況区分のチェック <BR>
     * this.処理区分 == "口座開設状況変更"の場合、以下のチェックを行う。 <BR>
     * ６－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01770 <BR>
     * <BR>
     * ６－２） 以下の値以外の場合、例外をスローする。 <BR>
     * ・"開設済" <BR>
     * ・"振替停止"<BR>
     * ・"抹消" <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01771 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BD4C83025D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コードのチェック 
        //　@１－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        //１－１）　@以下の条件に該当する場合、例外をスローする。 
        //・部店コード != 数値 
        if (!WEB3StringTypeUtility.isNumber(this.branchCode))
        {
            log.debug("部店コードの値が数値以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの値が数値以外の値です。");
        }
        
        //・部店コードの桁数 != 3桁 
        if (this.branchCode.getBytes().length != 3)
        {
            log.debug("部店コードのサイズが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードのサイズが不正です。");
        }
        
        //２）　@顧客コードのチェック 
        //  ２－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            log.debug("顧客コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }
        
        //　@２－２）　@以下の条件に該当する場合、例外をスローする。 
        //・顧客コード != 数値 
        if (!WEB3StringTypeUtility.isNumber(this.accountCode))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }
        
        //・顧客コードの桁数 != 6桁 
        if (this.accountCode.getBytes().length != 6)
        {
            log.debug("顧客コードのサイズが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードのサイズが不正です。");
        }
        
        //３）　@処理区分のチェック 
        //  ３－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.operationDiv))
        {
            log.debug("処理区分が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分が未指定です。");
        }
        
        //　@３－２）　@以下の値以外の場合、例外をスローする。 
        //・"口座情報変更" 
        //・"口座開設状況変更" 
        if (!(WEB3AioFxAccountInfoDivDef.ACCOUNT_INFO.equals(this.operationDiv)
            || WEB3AioFxAccountInfoDivDef.ACCOUNT_OPEN_STATUS.equals(this.operationDiv)))
        {
            log.debug("処理区分の値が存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分の値が存在しないコード値です。");
        }
        
        //４）更新後メールアドレスのチェック 
        //this.処理区分 == "口座情報変更"の場合、以下のチェックを行う。
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_INFO.equals(this.operationDiv))
        {
            //  ４－１）　@未入力の場合、例外をスローする。 
            if (WEB3StringTypeUtility.isEmpty(updatedMailAddress))
            {
                log.debug("処理区分が”口座情報変更”の場合は、" +
                    "更新後メールアドレスが未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01767,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "処理区分が”口座情報変更”の場合は、" +
                    "更新後メールアドレスが未指定です。");
            }
        }
        
        //５）更新後FX口座情報一覧のチェック 
        //this.処理区分 == "口座情報変更"の場合、以下のチェックを行う。
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_INFO.equals(this.operationDiv))
        {
            //  ５－１）　@未入力の場合、例外をスローする。 
            if (this.updatedFxAccInformationList == null)
            {
                log.debug("処理区分が”口座情報変更”の場合は、" +
                    "更新後FX口座情報一覧が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01768,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "処理区分が”口座情報変更”の場合は、" +
                    "更新後FX口座情報一覧が未指定です。");
            }
            
            //５－２）要素数が0の場合、例外をスローする。 
            if (this.updatedFxAccInformationList.length == 0)
            {
                log.debug("処理区分が”口座情報変更”の場合は、" +
                    "更新後FX口座情報一覧の要素数が０です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01769,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "処理区分が”口座情報変更”の場合は、" +
                    "更新後FX口座情報一覧の要素数が０です。");
            }
            
            //  ５－３）　@更新後FX口座情報一覧の要素数分、 
            //     以下の処理を繰り返す。 
            for (int i = 0; i < this.updatedFxAccInformationList.length; i++)
            {
                //５－３－１）　@FX口座情報.validate()をコールする。
                WEB3FXAccInformationUnit l_fXAccInformationUnit = 
                    this.updatedFxAccInformationList[i];
                l_fXAccInformationUnit.validate();
            }
        }

        //６）　@更新後口座開設状況区分のチェック 
        //  this.処理区分 == "口座開設状況変更"の場合、以下のチェックを行う。 
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_OPEN_STATUS.equals(this.operationDiv))
        {
            //  ６－１）　@未入力の場合、例外をスローする。 
            if (WEB3StringTypeUtility.isEmpty(this.updatedAccountOpenStatusDiv))
            {
                log.debug("処理区分が”口座開設状況変更”の場合は、" +
                    "更新後口座開設状況区分が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01770,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "処理区分が”口座開設状況変更”の場合は、" +
                    "更新後口座開設状況区分が未指定です。");
            }
            
            //　@６－２）　@以下の値以外の場合、例外をスローする。 
            //　@ ・"開設済" 
            //　@　@・"振替停止"
            //　@　@・"抹消"
            if (!(WEB3AioAccountDivDef.OPEN_COMPLETE.equals(
                    this.updatedAccountOpenStatusDiv)
                || WEB3AioAccountDivDef.DELETE.equals(
                    this.updatedAccountOpenStatusDiv) 
                || WEB3AioAccountDivDef.TRANSFER_STOP.equals(
                    this.updatedAccountOpenStatusDiv)))
            {
                log.debug("処理区分が”口座開設状況変更”の場合は、" +
                    "更新後口座開設状況区分が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01771,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "処理区分が”口座開設状況変更”の場合は、" +
                    "更新後口座開設状況区分が存在しないコード値です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78FE30109
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}@
