head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyCnfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS口座開設申込確認リクエスト(WEB3InformPTSAccOpenApplyCnfRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 謝旋 (中訊) 新規作成 モデルNo.123、No.125
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS口座開設申込確認リクエスト)<BR>
 * PTS口座開設申込確認リクエスト
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyCnfRequest extends WEB3InformPTSAccOpenApplyCommonRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyCnfRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "inform_pts_acc_open_apply_cnf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181639L;

    /**
     * (電子鳩チェックフラグ)<BR>
     * 電子鳩チェックフラグ<BR>
     * 電子鳩システムへ接続を行うかを設定する。<BR>
     * <BR>
     * true：接続をする。<BR>
     * false：接続をしない。<BR>
     */
    public boolean batoCheckFlag;

    /**
     * (種別コード)<BR>
     * 種別コード
     */
    public String typeCode;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String[] productCode;

    /**
     * @@roseuid 47B9271A0186
     */
    public WEB3InformPTSAccOpenApplyCnfRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@電子鳩チェックフラグ == trueの場合、以下のチェックを行う。<BR>
     * <BR>
     * 　@２－１）　@種別コードチェック<BR>
     * 　@　@this.種別コード == nullの場合、例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02202<BR>
     * <BR>
     * 　@２－２）　@銘柄コードチェック<BR>
     * 　@　@this.銘柄コードの要素数が「0」の場合、例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_03023<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47AA91A600E9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //super.validate()をコールする。
        super.validate();

        //電子鳩チェックフラグ == trueの場合、以下のチェックを行う
        if (this.batoCheckFlag)
        {
            //種別コードチェック
            // this.種別コード == nullの場合、例外をthrowする
            if (this.typeCode == null)
            {
                log.debug("種別コードが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    "種別コードが未指定です。");
            }

            //銘柄コードチェック
            //this.銘柄コードの要素数が「0」の場合、例外をthrowする
            if (this.productCode == null || this.productCode.length == 0)
            {
                log.debug("銘柄コードの要素数が０です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03023,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    "銘柄コードの要素数が０です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3InformPTSAccOpenApplyCnfResponse(this);
    }
}
@
