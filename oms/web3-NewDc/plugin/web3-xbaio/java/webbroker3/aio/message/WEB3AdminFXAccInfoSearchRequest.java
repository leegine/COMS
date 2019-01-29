head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座情報検索リクエスト(WEB3AdminFXAccInfoSearchRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.866
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioInputNumberDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・FX口座情報検索リクエスト) <BR>
 * 管理者・FX口座情報検索リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoSearchRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_search";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (部店コード) <BR>
     * 画面にて選択された部店コード
     */
    public String branchCode;

    /**
     * (入力番号区分)<BR>
     * 以下のいずれか。<BR>
     * <BR>
     * 1：FXログインID 2：FX口座番号 3：顧客コード
     */
    public String inputNumberDiv;

    /**
     * (入力番号) <BR>
     * 入力番号区分＝1（FXログインID）の場合、（FX）ログインID <BR>
     * 入力番号区分＝2（FX口座番号）の場合、（FX）口座番号 <BR>
     * 入力番号区分＝3（顧客コード）の場合、顧客コード
     */
    public String inputNumber;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78FFF001F
     */
    public WEB3AdminFXAccInfoSearchRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccInfoSearchRequest.class);
        
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
     * １－２） 以下の条件に該当する場合、例外をスローする。 <BR>
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
     * ２） 入力番号区分のチェック <BR>
     * ２－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01763 <BR>
     * <BR>
     * ２－２） 以下の値以外の場合、例外をスローする。 <BR>
     * ・"FXログインID" <BR>
     * ・"FX口座番号" <BR>
     * ・"顧客コード" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01764 <BR>
     * <BR>
     * ３） 入力番号のチェック <BR>
     * ３－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01765 <BR>
     * <BR>
     * ３－２） 以下の条件に該当する場合、例外をスローする。 <BR>
     * ・入力番号 != 数値 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01766 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41BD4C83025D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードのチェック 
        // １－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        // １－２）　@以下の条件に該当する場合、例外をスローする。 
        //　@ ・部店コード != 数値 
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
        
        //２）入力番号区分のチェック 
        //２－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.inputNumberDiv))
        {
            log.debug("入力番号区分が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01763,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力番号区分が未指定です。");
        }
        
        //２－２）　@以下の値以外の場合、例外をスローする。 
        // ・"FXログインID" 
        // ・"FX口座番号" 
        // ・"顧客コード" 
        if (!(WEB3AioInputNumberDivDef.FX_LOGINID.equals(this.inputNumberDiv)
            || WEB3AioInputNumberDivDef.FX_ACCOUNT_CODE.equals(this.inputNumberDiv)
            || WEB3AioInputNumberDivDef.ACCOUNT_CODE.equals(this.inputNumberDiv)))
        {
            log.debug("入力番号区分が存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01764,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力番号区分が存在しないコード値です。");
        }
       
        //３）入力番号のチェック 
        //３－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.inputNumber))
        {
            log.debug("入力番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01765,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力番号が未指定です。");
        }
        
        //３－２）　@以下の条件に該当する場合、例外をスローする。 
        //・入力番号 != 数値 
        if (!WEB3StringTypeUtility.isNumber(this.inputNumber))
        {
            log.debug("入力番号が数値以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力番号が数値以外の値です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX口座情報検索レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E78FFF00AB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccInfoSearchResponse(this);
    }
}@
