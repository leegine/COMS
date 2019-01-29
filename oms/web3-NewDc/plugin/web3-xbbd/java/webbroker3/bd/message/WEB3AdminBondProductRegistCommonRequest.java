head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄登録共通リクエスト(WEB3AdminBondProductRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
                     2006/10/08 周捷 (中訊) 仕様変更・モデル106
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者債券銘柄登録共通リクエスト)<BR>
 * 管理者債券銘柄登録共通リクエストクラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistCommonRequest extends WEB3GenRequest
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_common";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegistCommonRequest.class);

    /**
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)
     */
    public String productCode;

    /**
     * (債券銘柄更新情報)<BR>
     * 債券銘柄更新情報
     */
    public WEB3AdminBondProductUpdateInfo updateInfo;

    /**
     * @@roseuid 44E3363B03D8
     */
    public WEB3AdminBondProductRegistCommonRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）銘柄名チェック<BR>
     * this.債券銘柄更新情報.銘柄名 != nullの場合、以下をチェックする。<BR>
     * ・64バイト以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00441<BR>　@　@
     * <BR>
     * ２）買付単価チェック<BR>
     * this.債券銘柄更新情報.買付単価 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.買付単価が整数４桁以内（＜＝４桁）<BR>
     * 　@　@　@　@＋小数点＋小数6桁以内（＜＝6桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02551<BR>
     * <BR>
     * ・this.債券銘柄更新情報.買付単価が半角数字でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01391<BR>
     * <BR>
     * ・this.債券銘柄更新情報.買付単価が＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01392<BR>
     * <BR>
     * ３）売却単価、チェック<BR>
     * this.債券銘柄更新情報.売却単価 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.売却単価が整数４桁以内（＜＝４桁）<BR>
     * 　@　@　@　@＋小数点＋小数6桁以内（＜＝6桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02551<BR>
     * <BR>
     * ・this.債券銘柄更新情報.売却単価が半角数字でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01391<BR>
     * <BR>
     * ・this.債券銘柄更新情報.売却単価が＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01392<BR>
     * <BR>
     * ４）申込単位チェック<BR>
     * this.債券銘柄更新情報.申込単位 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.申込単位が１１桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02584<BR>
     * <BR>
     * ・this.債券銘柄更新情報.申込単位が整数でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02585<BR>
     * <BR>
     * ・this.債券銘柄更新情報.申込単位＜＝０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02586<BR>
     * <BR>
     * ５）最低額面チェック<BR>
     * this.債券銘柄更新情報.最低額面 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.最低額面が１１桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02587<BR>
     * <BR>
     * ・this.債券銘柄更新情報.最低額面が整数ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02588<BR>
     * <BR>
     * ・this.債券銘柄更新情報.最低額面が＜０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02589<BR>
     * <BR>
     * ６）最高額面チェック<BR>
     * this.債券銘柄更新情報.最高額面 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.最高額面が１１桁以内でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02590<BR>
     * <BR>
     * ・this.債券銘柄更新情報.最高額面が整数ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02591<BR>
     * <BR>
     * ・this.債券銘柄更新情報.最高額面が＜０の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02592<BR>
     *  <BR>
     * ７）買付受渡日移動日数チェック<BR>
     * this.債券銘柄更新情報.買付受渡日移動日数 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.買付受渡日移動日数が１桁以内ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02593<BR>
     * <BR>
     * ・this.債券銘柄更新情報.買付受渡日移動日数が整数ではない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02594<BR>
     * <BR>
     * ・this.債券銘柄更新情報.買付受渡日移動日数が＞＝　@０ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02595<BR>
     * <BR>
     * ８）売却受渡日移動日数チェック<BR>
     * this.債券銘柄更新情報.売却受渡日移動日数 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.売却受渡日移動日数が１桁以内ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02596<BR>
     * <BR>
     * ・this.債券銘柄更新情報.売却受渡日移動日数が整数ではない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02597<BR>
     * <BR>
     * ・this.債券銘柄更新情報.売却受渡日移動日数が＞＝　@０ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02598<BR>
     * <BR>
     * ９)仲介手数料率チェック<BR>
     * this.債券銘柄更新情報.仲介手数料率 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.仲介手数料率整数２桁以内（＜＝２桁）<BR>
     * 　@　@　@　@＋小数点＋小数５桁以内（＜＝５桁）ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02599<BR>
     * <BR>
     * ・this.債券銘柄更新情報.仲介手数料率が半角数字ではない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02600<BR>
     * <BR>
     * ・this.債券銘柄更新情報.仲介手数料率が仲介手数料率　@＞＝　@０　@ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02601<BR>
     * <BR>
     * １０)自動約定枠チェック<BR>
     * this.債券銘柄更新情報.自動約定枠 != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.自動約定枠が12桁以内ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02602<BR>
     * <BR>
     * ・this.債券銘柄更新情報.自動約定枠が整数ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02603<BR>
     * <BR>
     * ・this.債券銘柄更新情報.自動約定枠が＞＝　@０　@ではない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02604<BR>
     * <BR>
     * １１)仕入時の為替レートチェック <BR>
     * this.債券銘柄更新情報.仕入時の為替レート != nullの場合、以下をチェックする。<BR>
     * ・this.債券銘柄更新情報.仕入時の為替レートが整数３桁以内（<BR>
     * ＜＝３桁）＋小数点＋小数４桁以内（＜＝４桁）でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02663<BR>
     * <BR>
     * ・this.債券銘柄更新情報.仕入時の為替レートが数値でない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02664<BR>
     * <BR>
     * ・this.債券銘柄更新情報.仕入時の為替レート＜＝０の場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02665<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D68B0E0128
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）銘柄名チェック
        //this.債券銘柄更新情報.銘柄名 != nullの場合、以下をチェックする。
        //・64バイト以内でない場合、例外をスローする。
        if ((this.updateInfo.productName != null) &&
            (WEB3StringTypeUtility.getByteLength(this.updateInfo.productName) > 64))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00441,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄名のサイズが不正です。");
        }

        //２）買付単価チェック
        //this.債券銘柄更新情報.買付単価 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.買付単価が整数４桁以内（＜＝４桁）
        //＋小数点＋小数6桁以内（＜＝6桁）でない場合、例外をスローする。
        if (this.updateInfo.buyPrice != null)
        {
            if ((WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.buyPrice) > 4) ||
                    (WEB3StringTypeUtility.getFractionDigits(this.updateInfo.buyPrice) > 6))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価の有効桁数が整数部４桁，小数部６桁の範囲外です。");
            }
        }

        //・this.債券銘柄更新情報.買付単価が半角数字でない場合、例外をスローする。
        if (this.updateInfo.buyPrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.updateInfo.buyPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価が数字以外です。");
            }
        }

        //・this.債券銘柄更新情報.買付単価が＜＝０の場合、例外をスローする。
        if (this.updateInfo.buyPrice != null)
        {
            if (Double.parseDouble(this.updateInfo.buyPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01392,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価が0以下です。");
            }
        }

        //３）売却単価、チェック
        //this.債券銘柄更新情報.売却単価 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.売却単価が整数４桁以内（＜＝４桁）
        //＋小数点＋小数6桁以内（＜＝6桁）でない場合、例外をスローする。
        if (this.updateInfo.sellPrice != null)
        {
            if ((WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.sellPrice) > 4) ||
                    (WEB3StringTypeUtility.getFractionDigits(this.updateInfo.sellPrice) > 6))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価は整数部４桁，小数部６桁の範囲外です。");
            }
        }
        //・this.債券銘柄更新情報.売却単価が半角数字でない場合、例外をスローする。
        if (this.updateInfo.sellPrice != null)
        {
            if (!WEB3StringTypeUtility.isSingle(this.updateInfo.sellPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価が数字以外です。");
            }
        }

        //・this.債券銘柄更新情報.売却単価が＜＝０の場合、例外をスローする。
        if (this.updateInfo.sellPrice != null)
        {
            if (Double.parseDouble(this.updateInfo.sellPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01392,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価が0以下です。");
            }
        }

        //４）申込単位チェック
        //this.債券銘柄更新情報.申込単位 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.申込単位が１１桁以内でない場合、例外をスローする。
        if (this.updateInfo.tradeUnit != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.tradeUnit) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02584,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込単位のサイズが不正です。");
            }
        }

        //・this.債券銘柄更新情報.申込単位が整数でない場合、例外をスローする。
        if (this.updateInfo.tradeUnit != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.tradeUnit))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02585,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込単位が整数以外の値です。");
            }
        }

        //・this.債券銘柄更新情報.申込単位＜＝０の場合、例外をスローする。
        if (this.updateInfo.tradeUnit != null)
        {
            if (Long.parseLong(this.updateInfo.tradeUnit) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02586,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込単位が0以下の値です。");
            }
        }

        //５）最低額面チェック<BR>
        //this.債券銘柄更新情報.最低額面 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.最低額面が１１桁以内でない場合、例外をスローする。
        if (this.updateInfo.minFaceAmount != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.minFaceAmount) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02587,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低額面のサイズが不正です。");
            }
        }

        //・this.債券銘柄更新情報.最低額面が整数ではない場合、例外をスローする。
        if (this.updateInfo.minFaceAmount != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.minFaceAmount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02588,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低額面が整数以外の値です。");
            }
        }

        //・this.債券銘柄更新情報.最低額面が＜０の場合、例外をスローする。
        if (this.updateInfo.minFaceAmount != null)
        {
            if (Long.parseLong(this.updateInfo.minFaceAmount) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02589,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最低額面が0より小さい値です。");
            }
        }

        //６）最高額面チェック
        //this.債券銘柄更新情報.最高額面 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.最高額面が１１桁以内でない場合、例外をスローする。
        if (this.updateInfo.maxFaceAmount != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.maxFaceAmount) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02590,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最高額面のサイズが不正です。");
            }
        }

        //・this.債券銘柄更新情報.最高額面が整数ではない場合、例外をスローする。
        if (this.updateInfo.maxFaceAmount != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.maxFaceAmount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02591,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最高額面が整数以外の値です。");
            }
        }

        //・this.債券銘柄更新情報.最高額面が＜０の場合、例外をスローする。
        if (this.updateInfo.maxFaceAmount != null)
        {
            if (Long.parseLong(this.updateInfo.maxFaceAmount) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02592,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最高額面が0より小さい値です。");
            }
        }

        //７）買付受渡日移動日数チェック
        //this.債券銘柄更新情報.買付受渡日移動日数 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.買付受渡日移動日数が１桁以内ではない場合、例外をスローする。
        if (this.updateInfo.buyDeliveryMove != null)
        {
            if (WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.buyDeliveryMove) > 1)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02593,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買付受渡日指定値のサイズが不正です。");
            }
        }

        //・this.債券銘柄更新情報.買付受渡日移動日数が整数ではない場合、例外をスローする。
        if (this.updateInfo.buyDeliveryMove != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.buyDeliveryMove))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02594,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買付受渡日指定値が整数以外の値です。");
            }
        }

        //・this.債券銘柄更新情報.買付受渡日移動日数が＞＝　@０ではない場合、例外をスローする。
        if (this.updateInfo.buyDeliveryMove != null)
        {
            if (Integer.parseInt(this.updateInfo.buyDeliveryMove) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02595,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "買付受渡日指定値が０より小さいです。");
            }
        }

        //８）売却受渡日移動日数チェック<BR>
        //this.債券銘柄更新情報.売却受渡日移動日数 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.売却受渡日移動日数が１桁以内ではない場合、例外をスローする。
        if (this.updateInfo.sellDeliveryMove != null)
        {
            if (WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.sellDeliveryMove) > 1)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02596,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売却受渡日指定値のサイズが不正です。");
            }
        }

        //・this.債券銘柄更新情報.売却受渡日移動日数が整数ではない場合、例外をスローする。
        if (this.updateInfo.sellDeliveryMove != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.sellDeliveryMove))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02597,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売却受渡日指定値が整数以外の値です。");
            }
        }

        //・this.債券銘柄更新情報.売却受渡日移動日数が＞＝　@０ではない場合、例外をスローする。
        if (this.updateInfo.sellDeliveryMove != null)
        {
            if (Integer.parseInt(this.updateInfo.sellDeliveryMove) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02598,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "売却受渡日指定値が０より小さいです。");
            }
        }

        //９)仲介手数料率チェック
        //this.債券銘柄更新情報.仲介手数料率 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.仲介手数料率整数２桁以内（＜＝２桁）
        //＋小数点＋小数５桁以内（＜＝５桁）ではない場合、例外をスローする。
        if (this.updateInfo.mediatorCommissionRate != null)
        {
            if ((WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.mediatorCommissionRate) > 2) ||
                    (WEB3StringTypeUtility.getFractionDigits(this.updateInfo.mediatorCommissionRate) > 5))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02599,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "仲介手数料率の有効桁数が、整数部２桁，小数部５桁の範囲外です。");
            }
        }

        //・this.債券銘柄更新情報.仲介手数料率が半角数字ではない場合、例外をスローする。
        if (this.updateInfo.mediatorCommissionRate != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.updateInfo.mediatorCommissionRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02600,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "仲介手数料率が数値以外の値です。");
            }
        }

        //・this.債券銘柄更新情報.仲介手数料率が仲介手数料率　@＞＝　@０　@ではない場合、例外をスローする。
        if (this.updateInfo.mediatorCommissionRate != null)
        {
            if (Double.parseDouble(this.updateInfo.mediatorCommissionRate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02601,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "仲介手数料率が０より小さいです。");
            }
        }

        //１０)自動約定枠チェック
        //this.債券銘柄更新情報.自動約定枠 != nullの場合、以下をチェックする。
        //・this.債券銘柄更新情報.自動約定枠が12桁以内ではない場合、例外をスローする。
        if (this.updateInfo.autoExecLimit != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.autoExecLimit) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02602,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "自動約定枠のサイズが不正です。");
            }
        }
        //・this.債券銘柄更新情報.自動約定枠が整数ではない場合、例外をスローする。
        if (this.updateInfo.autoExecLimit != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.autoExecLimit))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02603,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "自動約定枠が整数以外の値です。");
            }
        }

        //・this.債券銘柄更新情報.自動約定枠が＞＝　@０　@ではない場合、例外をスローする。
        if (this.updateInfo.autoExecLimit != null)
        {
            if (Long.parseLong(this.updateInfo.autoExecLimit) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02604,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "自動約定枠が0より小さい値です。");
            }
        }
        //１１)仕入時の為替レートチェック
        //this.債券銘柄更新情報.仕入時の為替レート != nullの場合、以下をチェックする。
        if (this.updateInfo.fxRateAtStock != null)
        {
            //・this.債券銘柄更新情報.仕入時の為替レートが数値でない場合、例外をスローする。
            if (!WEB3StringTypeUtility.isNumber(updateInfo.fxRateAtStock))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02664,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "仕入時の為替レートが数値以外の値です。");
            }
            //・this.債券銘柄更新情報.仕入時の為替レート＜＝０の場合、例外をスローする。
            if (Double.parseDouble(this.updateInfo.fxRateAtStock) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02665,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "仕入時の為替レートが0以下の値です。");
            }

            //・this.債券銘柄更新情報.仕入時の為替レートが整数３桁以内
            //（＜＝３桁）＋小数点＋小数４桁以内（＜＝４桁）でない場合、例外をスローする。
            if (WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.fxRateAtStock) > 3 ||
                WEB3StringTypeUtility.getFractionDigits(this.updateInfo.fxRateAtStock) > 4)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02663,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "仕入時の為替レートの有効桁数が、整数部３桁，小数部４桁の範囲外です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 債券銘柄登録完了リクエストを生成し返す。
     * @@return WEB3GenResponse
     * @@roseuid 44B620020375
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
