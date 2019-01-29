head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・投信累投注文約定照会入力リクエスト (WEB3AdminORMutualRuitoOrderExecutionRefInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・投信累投注文約定照会入力リクエスト)<BR>
 * <BR>
 * 管理者・投信累投注文約定照会入力リクエストクラス<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefInputRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_mutual_ruito_order_execution_ref_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

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
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORMutualRuitoOrderExecutionRefInputRequest.class);

    /**
     * @@roseuid 4212FBFA0334
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * 　@１－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）this.部店コード.length == 0の場合、<BR>
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
     *            Throw the exception "branchCode is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@ 1-2)If this.branchCode.length == 0<BR>
     * 　@　@　@　@　@Throw the exception "The number of the elements of branchCode is
     * 0"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01757<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 41ADC13B0120
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 If this.branchCode = null, throw Exception.
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1-2 If this.branchCode.length = 0, throw Exception.
        if (this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORMutualRuitoOrderExecutionRefInputResponse(this);
    }
}
@
