head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS口座開設申込共通リクエスト(WEB3InformPTSAccOpenApplyCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 謝旋 (中訊) 新規作成 モデルNo.123
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS口座開設申込共通リクエスト)<BR>
 * PTS口座開設申込共通リクエスト
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyCommonRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformPTSAccOpenApplyCommonRequest.class);

    /**
     * (連絡種別)<BR>
     * 連絡種別
     */
    public String informType;

    /**
     * (PTS口座開設区分)<BR>
     * PTS口座開設区分<BR>
     * <BR>
     * 0：未開設<BR>
     * 1：開設
     */
    public String ptsAccOpenDiv;

    /**
     * (PTS取引同意質問情報一覧)<BR>
     * PTS取引同意質問情報一覧
     */
    public WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList;

    /**
     * @@roseuid 47B9271A01D4
     */
    public WEB3InformPTSAccOpenApplyCommonRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@連絡種別チェック<BR>
     * 　@this.連絡種別 == nullの場合、例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01817<BR>
     * <BR>
     * ２）　@PTS口座開設区分チェック<BR>
     * 　@this.PTS口座開設区分 == nullの場合、例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_03020<BR>
     * <BR>
     * ３）　@PTS取引同意質問情報内容チェック<BR>
     * 　@this.PTS取引同意質問情報一覧 != nullの場合、<BR>
     * 　@要素ごとに以下のチェックを行う。<BR>
     * <BR>
     * 　@以下のいずれかに当てはまる場合、例外をthrowする。<BR>
     * <BR>
     * 　@　@・PTS取引同意質問情報.質問番号 == nullの場合<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_03021<BR>
     * <BR>
     * 　@　@・PTS取引同意質問情報.質問回答 == nullの場合<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_03022<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2B642012D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //連絡種別チェック
        // this.連絡種別 == nullの場合、例外をthrowする
        if (this.informType == null)
        {
            log.debug("連絡種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "連絡種別が未指定です。");
        }

        //PTS口座開設区分チェック
        // this.PTS口座開設区分 == nullの場合、例外をthrowする。
        if (this.ptsAccOpenDiv == null)
        {
            log.debug("PTS口座開設区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03020,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "PTS口座開設区分が未指定です。");
        }

        //PTS取引同意質問情報内容チェック
        // this.PTS取引同意質問情報一覧 != nullの場合、
        // 要素ごとに以下のチェックを行う。
        // 以下のいずれかに当てはまる場合、例外をthrowする。
        //  ・PTS取引同意質問情報.質問番号 == nullの場合
        //  ・PTS取引同意質問情報.質問回答 == nullの場合
        if (this.ptsTradeAgreementList != null)
        {
            int l_intCnt = this.ptsTradeAgreementList.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                if (this.ptsTradeAgreementList[i].questionNumber == null)
                {
                    log.debug("質問番号が未指定です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03021,
                        WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                        "質問番号が未指定です。");
                }

                if (this.ptsTradeAgreementList[i].questionAnswer == null)
                {
                    log.debug("質問回答が未指定です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03022,
                        WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                        "質問回答が未指定です。");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
