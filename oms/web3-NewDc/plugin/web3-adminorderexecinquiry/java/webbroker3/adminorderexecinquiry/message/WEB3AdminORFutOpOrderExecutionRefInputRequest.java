head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP注文約定照会入力リクエスト (WEB3AdminORFutOpOrderExecutionRefInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP注文約定照会・・ﾍリクエスト)<BR>
 * <BR>
 * 管理者・先物OP注文約定照会入力リクエストクラス<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefInputRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_fut_op_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORFutOpOrderExecutionRefInputRequest.class);

    /**
     * (部店コード)<BR>
     * <BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     * An array of branchCode<BR>
     * <BR>
     * ※The handling possible branchCodeList held by PR layer is set when branchCode
     * is not input.<BR>
     * <BR>
     */
    public String[] branchCode;

    /**
     * @@roseuid 4212FB8D00A4
     */
    public WEB3AdminORFutOpOrderExecutionRefInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）・箔Xコードチェック<BR>
     * 　@１－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）this.部店コード.length == 0の場・〟A<BR>
     * 　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     * 　@ 1-1)If this.branchCode == null<BR>
     * Throw the exception "branchCode is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@ 1-2)If (this.branchCode.length == 0l)<BR>
     * 　@　@　@　@　@Throw the exeception "The number of the elements of branchCode is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41ADB13F00B8
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //1-1 if branchCode is null, throw Exception.
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1-2 if length of branchCode is zero, throw Exception.
        if (this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORFutOpOrderExecutionRefInputResponse(this);
    }
}
@
