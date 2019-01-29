head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座開設ステータス更新共通リクエスト(WEB3AdminFXAccOpenStatusUpdCommonRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/09 余新敏(中訊) 仕様変更・モデル468
                    2006/02/22 情野(SRA) 仕様変更・モデル500
 Revesion History : 2008/10/21 王志葵(中訊) 仕様変更・モデル1077
 Revesion History : 2009/08/25 孟亞南(中訊) 仕様変更・モデル1192
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・FX口座開設ステータス更新共通リクエスト) <BR>
 * 管理者・FX口座開設ステータス更新共通リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdCommonRequest extends WEB3GenRequest
{
    /**
     * (FX検索条件) <BR>
     * FX検索条件
     */
    public WEB3FXSearchConditionUnit fxSearchConditionUnit;

    /**
     * (更新後ステータス区分) <BR>
     * 以下のいずれか。 <BR>
     * <BR>
     * 1：口座開設完了 <BR>
     * 9：削除
     */
    public String updatedStatusDiv;

    /**
     * (FX口座情報一覧) <BR>
     * FX口座情報の一覧 <BR>
     * <BR>
     * 更新後ステータス区分＝1（口座開設完了）の場合、設定
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (更新後約諾書区分)<BR>
     * 以下のいずれか。<BR>
     * <BR>
     * null：未設定<BR>
     * 0：未送信<BR>
     * 1：送信済<BR>
     * 2：受領済<BR>
     */
    public String updatedAgreementDiv;
    
    /**
     * @@roseuid 41E78F6402BF
     */
    public WEB3AdminFXAccOpenStatusUpdCommonRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenStatusUpdCommonRequest.class);
        
    /**
     * (validate) <BR>
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １） FX検索条件のチェック <BR>
     * １－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01748 <BR>
     * <BR>
     * １－２） FX検索条件.validate()メソッドをコールする。 <BR>
     * <BR>
     * ２） 更新後ステータス区分のチェック <BR>
     * ２－１） 未入力の場合、更新後約諾書区分が未設定（=null）ならば、<BR>
     * 例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01749 <BR>
     * <BR>
     * ２－２） 以下の値以外の場合、例外をスローする。 <BR>
     * ・"口座開設完了" <BR>
     * ・"削除" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01750 <BR>
     * <BR>
     * ３）　@更新後約諾書区分のチェック<BR>
     * 　@３－１）　@未設定（=null）でない場合、以下の値以外の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@・"未送信"<BR>
     * 　@　@・"送信済"<BR>
     * 　@　@・"受領済"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02348<BR>
     * <BR>
     * ４） FX口座情報一覧のチェック <BR>
     * 　@※更新後ステータス区分が未設定（=null）の場合はチェックをしない。<BR>
     * ４－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01751 <BR>
     * <BR>
     * ４－２） 要素数が0の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01752 <BR>
     * <BR>
     * ４－３） 要素数分以下の処理を繰り返す。 <BR>
     * ４－３－１） FX口座情報.コース区分が未入力の場合、 例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01753 <BR>
     * <BR>
     * ４－３－２） FX口座情報.コース区分が以下の値以外の場合、 <BR>
     * 例外をスローする。 <BR>
     * ・"DEFAULT" <BR>
     * ・"1万通貨コース" <BR>
     * ・"10万通貨コース" <BR>
     * ・"CFDコース"<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01754 <BR>
     * <BR>
     * ４－３－３） this.更新後ステータス == "口座開設完了"の場合、 <BR>
     * 下記の条件と一致する場合、例外をスローする。 <BR>
     * ・FX口座情報.口座番号 == null  <BR>
     * ・FX口座情報.口座番号.length() > 10 <BR>
     * ・FX口座情報.口座番号 != 数字  <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01755 <BR>
     * <BR>
     * ４－３－４） this.更新後ステータス == "削除"の場合、 <BR>
     * 下記の条件と一致する場合、例外をスローする。<BR>
     * FX口座情報.口座番号 != null<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01756 <BR>
     * this.更新後約諾書区分 != nulll<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02349<BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@FX検索条件のチェック 
        // １－１）　@未入力の場合、例外をスローする。 
        if (fxSearchConditionUnit == null)
        {
            log.debug("FX検索条件が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01748,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX検索条件が未指定です。");
        }
        
        //１－２）　@FX検索条件.validate()メソッドをコールする。 
        fxSearchConditionUnit.validate();
        
        //２）　@更新後ステータス区分のチェック 
        if (WEB3StringTypeUtility.isEmpty(this.updatedStatusDiv))
        {
            //  ２－１）　@未入力の場合、更新後約諾書区分が未設定（=null）ならば、
            //　@　@　@例外をスローする。 
            if(this.updatedAgreementDiv == null){
                log.debug("更新後ステータス区分が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01749,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "更新後ステータス区分が未指定です。");
            }
        }
        else
        {
            //２－２）　@以下の値以外の場合、例外をスローする。 
            //　@　@ ・"口座開設完了" 
            //　@　@ ・"削除" 
            if (!(WEB3AioAccountOpenCompleteDef.DELETE.equals(
                    this.updatedStatusDiv)
                || WEB3AioAccountOpenCompleteDef.OPEN_COMPLETE.equals(
                    this.updatedStatusDiv)))
            {
                log.debug("更新後ステータス区分が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01750,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "更新後ステータス区分が存在しないコード値です。");
            }
        }
        
        //３）　@更新後約諾書区分のチェック
        //　@３－１）　@未設定（=null）でない場合、以下の値以外の場合、例外をスローする。
        //　@　@・"未送信"
        //　@　@・"送信済"
        //　@　@・"受領済"
        if(this.updatedAgreementDiv != null
            && !(WEB3AioAgreementDivDef.NOT_SEND.equals(this.updatedAgreementDiv)
                || WEB3AioAgreementDivDef.SENDED.equals(this.updatedAgreementDiv)
                || WEB3AioAgreementDivDef.RECIEVED.equals(this.updatedAgreementDiv)))
        {
            log.debug("約諾書区分の値が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02348,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "約諾書区分の値が不正です。");
        }
        
        //４）　@FX口座情報一覧のチェック 
        //　@※更新後ステータス区分が未設定（=null）の場合はチェックをしない。
        if(this.updatedStatusDiv != null)
        {
            //  ４－１）　@未入力の場合、例外をスローする。 
            if (this.fxAccInformationList == null)
            {
                log.debug("FX口座情報一覧が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01751,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "FX口座情報一覧が未指定です。");
            }
            
            //４－２）　@要素数が0の場合、例外をスローする。 
            if (this.fxAccInformationList.length == 0)
            {
                log.debug("FX口座情報一覧の要素数が０です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01752,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "FX口座情報一覧の要素数が０です。");
            }
            
            //４－３）　@要素数分以下の処理を繰り返す。 
            for (int i = 0; i < this.fxAccInformationList.length; i++)
            {
                //４－３－１）　@FX口座情報.コース区分が未入力の場合、例外をスローする。
                if (WEB3StringTypeUtility.isEmpty(
                        this.fxAccInformationList[i].fxCourseDiv))
                {
                    log.debug("FX口座情報のコース区分が未指定です。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01753,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "FX口座情報のコース区分が未指定です。");
                }
                
                //４－３－２）　@FX口座情報.コース区分が以下の値以外の場合、 
                // 例外をスローする。 
                //　@・"DEFAULT" 
                //　@・"1万通貨コース" 
                //　@・"10万通貨コース" 
                //　@・"CFDコース"
                if (!(WEB3GftTransStatusCourseDivDef.DEFAULT.equals(
                        this.fxAccInformationList[i].fxCourseDiv)
                    || WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(
                        this.fxAccInformationList[i].fxCourseDiv)
                    || WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(
                        this.fxAccInformationList[i].fxCourseDiv)
                    || WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                        this.fxAccInformationList[i].fxCourseDiv)))
                {
                    log.debug("FX口座情報のコース区分が存在しないコード値です。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01754,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "FX口座情報のコース区分が存在しないコード値です。");
                }
                
                //４－３－３）　@this.更新後ステータス == "口座開設完了"の場合、 
                // ・FX口座情報.口座番号 == null 
                // ・FX口座情報.口座番号.length() > 10
                // ・FX口座情報.口座番号 != 数字  
                if (WEB3AioAccountOpenCompleteDef.OPEN_COMPLETE.equals(
                        this.updatedStatusDiv))
                {
                    if (WEB3StringTypeUtility.isEmpty(
                        this.fxAccInformationList[i].fxAccountCode) ||
                    this.fxAccInformationList[i].fxAccountCode.length() > 10 ||
                    !WEB3StringTypeUtility.isInteger(
                        this.fxAccInformationList[i].fxAccountCode))
                    {
                        log.debug("更新後ステータスが”口座開設完了”の場合は、" +
                            "FX口座情報の口座番号が不正です。");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01755,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "更新後ステータス= [" + this.updatedStatusDiv +  "]," +
                                " FX口座情報.口座番号 = [" + this.fxAccInformationList[i].fxAccountCode +  "]" );
                    }
                }
                
                //４－３－４）　@this.更新後ステータス == "削除"の場合、 
                // 　@下記の条件と一致する場合、例外をスローする。 
                //　@・FX口座情報.口座番号 != null
                //　@・this.更新後約諾書区分 != null
                if (WEB3AioAccountOpenCompleteDef.DELETE.equals(this.updatedStatusDiv))
                {
                    if (!WEB3StringTypeUtility.isEmpty(
                            this.fxAccInformationList[i].fxAccountCode))
                    {
                        log.debug("更新後ステータスが”削除”の場合は、" +
                            "FX口座情報の口座番号が指定不可。");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01756,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "更新後ステータスが”削除”の場合は、" +
                            "FX口座情報の口座番号が指定不可。");
                    }
                    
                    if(this.updatedAgreementDiv != null)
                    {
                        log.debug("更新後ステータスが”削除”の場合は、" +
                            "FX口座情報の口座番号と更新後約諾書区分が同時に指定できない。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02349,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "更新後ステータスが”削除”の場合は、" +
                            "FX口座情報の口座番号と更新後約諾書区分が同時に指定できない。");
                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * （createResponseの実装）<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F64036B
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}@
