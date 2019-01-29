head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFutOpOrderExecutionRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP注文約定照会レスポンス (WEB3AdminORFutOpOrderExecutionRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・先物OP注文約定照会レスポンス)<BR>
 * <BR>
 * 管理者・先物OP注文約定照会レスポンスクラス<BR>
 * <BR>
 * WEB3AdminORFutOpOrderExecutionRefReferenceResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORFutOpOrderExecutionRefReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_fut_op_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (OP出来終了／注文繰越処理区分)<BR>
     * <BR>
     * OP出来終了／注文繰越処理区分<BR>
     * <BR>
     * 0：　@出来終了済　@1：　@注文繰越処理済<BR>
     * 2：　@注文繰越処理中 9：　@注文繰越処理エラー<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * opCarryoverEndType<BR>
     * 0: Def.NOT_STARTED_PROCESS　@1: Def.COMPLETE_PROCESS<BR>
     * 2: Def.CALL_CARRYOVER_AP 9: Def.ERROR<BR>
     * <BR>
     */
    public String opCarryoverEndType = null;

    /**
     * (先物出来終了／注文繰越処理区分)<BR>
     * <BR>
     * 先物出来終了／注文繰越処理区分<BR>
     * <BR>
     * 0：　@出来終了済<BR>
     * 1：　@注文繰越処理済<BR>
     * 2：　@注文繰越処理中<BR>
     * 9：　@注文繰越処理エラー<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * fuCarryoverEndType<BR>
     * 0: Def.NOT_STARTED_PROCESS<BR>
     * 1: Def.COMPLETE_PROCESS<BR>
     * 2: Def.CALL_CARRYOVER_AP<BR>
     * 9: Def.ERROR<BR>
     * <BR>
     */
    public String fuCarryoverEndType = null;

    /**
     * (総ページ数)<BR>
     * <BR>
     * 総ページ数<BR>
     * <BR>
     * totalPages<BR>
     * <BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * <BR>
     * 総レコード数<BR>
     * <BR>
     * totalRecords<BR>
     * <BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * <BR>
     * 表示ページ番号<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * 詳細画面情報一覧<BR>
     * <BR>
     */
    public WEB3AdminORDetailDispInfoUnit[] detailDispInfoList;

    /**
     * 管理者先物OP注文約定照会Unit一覧<BR>
     * <BR>
     */
    public WEB3AdminORFutOpOrderExecutionRefUnit[] futOpeOrderExecutionRefList;

    /**
     * @@roseuid 4212FBB30111
     */
    public WEB3AdminORFutOpOrderExecutionRefReferenceResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminORFutOpOrderExecutionRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
