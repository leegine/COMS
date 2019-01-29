head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenCompleteSoapRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
Author Name         : Daiwa Institute of Research
File Name           : FX口座開設完了リクエスト（SOAP接続）(WEB3FXAccOpenCompleteSoapRequest.java)
Revision History    : 2008/04/08 王志葵(中訊) 新規作成 モデルNo.837
Revision History    : 2009/10/27 張騰宇(中訊) 仕様変更 モデルNo.1245
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX口座開設完了リクエスト（SOAP接続）)<BR>
 * FX口座開設完了リクエスト（SOAP接続）クラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3FXAccOpenCompleteSoapRequest extends WEB3FXAccOpenAskingRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "fx_acc_open_complete_soap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804081441L;

    /**
     * (FX暗証番号２)<BR>
     * FX暗証番号２<BR>
     */
    public String fxPassword2;

    /**
     * (FX口座開設完了リクエスト（SOAP接続）)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3FXAccOpenCompleteSoapRequest()
    {

    }

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenCompleteSoapRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@FX暗証番号２チェック<BR>
     * 　@FX暗証番号２がnullじゃない場合以下チェックを行う、<BR>
     * 　@以下のいずれかに当てはまる場合、例外をthrowする。<BR>
     * <BR>
     * 　@１－１）　@this.FX暗証番号２≠半角英数字<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * 　@１－２）　@this.FX暗証番号２＜8桁<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * 　@１－３）　@this.FX暗証番号２＞12桁<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * 　@１－４）　@this.FX暗証番号２＝this.FX暗証番号<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_03185 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String l_strMethodName = "validate()";
        log.entering(l_strMethodName);

        if (this.fxPassword2 != null)
        {
            //　@１－１）　@this.FX暗証番号２≠半角英数字
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX暗証番号２≠半角英数字" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
            }

            //　@１－２）　@this.FX暗証番号２＜8桁
            if (this.fxPassword2.length() < 8)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX暗証番号２＜8桁" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
            }

            //　@１－３）　@this.FX暗証番号２＞12桁
            if (this.fxPassword2.length() > 12)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "リクエストデータ.FX暗証番号２＞12桁" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
            }

            //　@１－４）　@this.FX暗証番号２＝this.FX暗証番号
            if (this.fxPassword2.equals(this.fxPassword))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03185,
                    this.getClass().getName() + "." + l_strMethodName,
                    "FX暗証番号２とFX暗証番号が一致です。" +
                    "リクエストデータ.FX暗証番号２ = " + this.fxPassword2);
            }
        }

        log.exiting(l_strMethodName);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenCompleteSoapResponse(this);
    }
}
@
