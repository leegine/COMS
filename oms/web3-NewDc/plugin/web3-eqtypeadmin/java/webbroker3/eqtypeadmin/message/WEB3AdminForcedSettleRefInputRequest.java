head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会入力リクエスト(WEB3AdminForcedSettleRefInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 趙林鵬 (中訊) 新規作成  仕様変更モデルNo.128
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・強制決済注文照会入力リクエスト)<BR>
 * 管理者・強制決済注文照会入力リクエストクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleRefInputRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleRefInputRequest.class);

    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_ref_input";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (部店コード一覧)<BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCodeList;

    /**
     * @@roseuid 462CA4290053
     */
    public WEB3AdminForcedSettleRefInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@部店コードチェック<BR>
     * 　@１－１）　@this.部店コード一覧 == nullの場合、<BR>
     * 　@　@「部店コードがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01429       <BR>
     * <BR>
     * 　@１－２）　@this.部店コード一覧の要素数分以下の処理を行う。<BR>
     * 　@　@１－２－１）　@this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@・部店コード != 数字<BR>
     * 　@　@　@　@・部店コード.length != 3<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00779<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46103B7D02E9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intThree = 3;
        int l_intBranchCodeLength = 0;

        // 当リクエストデータの整合性チェックを行う。
        // （ただし、当クラス内で完結する簡易チェックのみとする。）
        // １）　@部店コードチェック
        // 　@１－１）　@this.部店コード一覧 == nullの場合、
        // 　@　@「部店コードがnull」の例外をスローする。
        if (this.branchCodeList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード一覧が未指定です。");
        }

        // 　@１－２）　@this.部店コード一覧の要素数分以下の処理を行う。
        // 　@　@１－２－１）　@this.部店コードが以下の条件に該当する場合、
        // 　@　@　@「部店コードエラー」の例外をスローする。
        // 　@　@　@　@・部店コード != 数字
        // 　@　@　@　@・部店コード.length != 3
        l_intBranchCodeLength = this.branchCodeList.length;
        for (int i = 0;  i < l_intBranchCodeLength; i++)
        {
            if ((!WEB3StringTypeUtility.isDigit(branchCodeList[i]))
                || (WEB3StringTypeUtility.getByteLength(this.branchCodeList[i]) != l_intThree))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
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
        return new WEB3AdminForcedSettleRefInputResponse(this);
    }
}
@
