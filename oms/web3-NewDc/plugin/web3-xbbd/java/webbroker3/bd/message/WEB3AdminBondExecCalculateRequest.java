head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCalculateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受渡代金計算リクエスト(WEB3AdminBondExecCalculateRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
                     2006/10/08 周捷 (中訊) 仕様変更・モデル108
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (受渡代金計算リクエスト)<BR>
 * 受渡代金計算リクエストクラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondExecCalculateRequest extends WEB3AdminBondExecInputCommonRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecCalculateRequest.class);

    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_calculate";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;

    /**
     * (入力時発注日)<BR>
     * 入力時発注日
     */
    public Date inpOrderDate;

    /**
     * (受渡代金計算リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 44BDD87E0295
     */
    public WEB3AdminBondExecCalculateRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １)　@注文情報チェック。<BR>
     * 　@this.注文情報.validate()をコールする。 <BR>
     * <BR>
     * ２)　@約定情報チェック  <BR>
     * 　@this.約定情報.validate()をコールする。  <BR>
     * <BR>
     * ３)　@銘柄コード（WEB3）チェック  <BR>
     * 　@this.銘柄コード（WEB3）==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00079<BR>
     * <BR>
     * ４)　@入力時発注日チェック <BR>
     * 　@this.入力時発注日==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00406<BR>
     * <BR>
     * ５)　@部店コードチェック  <BR>
     * 　@this.顧客情報 != nullの場合、以下をチェックする。 <BR>
     * 　@　@this.顧客情報.部店コード !=nullの場合、以下をチェックする。 <BR>
     * 　@　@　@this.顧客情報.部店コードが３桁でない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00834<BR>
     * <BR>
     * 　@　@　@this.顧客情報.部店コードが数値でない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01729<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44BDD87E02D4
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１)　@注文情報チェック。
        //this.注文情報.validate()をコールする。
        this.orderInfo.validate();

        //２)　@約定情報チェック
        //this.約定情報.validate()をコールする。
        this.execInfo.validate();

        //３)　@銘柄コード（WEB3）チェック
        //this.銘柄コード（WEB3）==nullの場合、例外をスローする。
        if (this.productCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが未指定です。");
        }

        //４)　@入力時発注日チェック
        //this.入力時発注日==nullの場合、例外をスローする。
        if (this.inpOrderDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。");
        }

        //５)　@部店コードチェック
        //  this.顧客情報 != nullの場合、以下をチェックする。
        //   this.顧客情報.部店コード !=nullの場合、以下をチェックする。
        //   　@this.顧客情報.部店コードが３桁でない場合、例外をスローする。
        //   　@this.顧客情報.部店コードが数値でない場合、例外をスローする。
        if (this.accountInfo != null)
        {
            if (this.accountInfo.branchCode != null)
            {
                if (this.accountInfo.branchCode.length() != 3)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "部店コードのサイズが不正です。");
                }

                if (!WEB3StringTypeUtility.isNumber(this.accountInfo.branchCode))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "部店コードが数値以外の値です。");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 受渡代金計算レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     * @@roseuid 44BED96701D4
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondExecCalculateResponse(this);
    }
}
@
