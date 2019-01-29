head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券自動約定リクエスト(WEB3BondAutoExecRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 徐大方 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券自動約定リクエスト)<BR>
 * 債券自動約定リクエストクラス<BR>
 *
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3BondAutoExecRequest extends WEB3BackRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondAutoExecRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_auto_execution";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610051109L;

    /**
     *(証券会社コード)<BR>
     *証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (from口座ID)<BR>
     * from口座ID<BR>
     */
    public long fromAccountId;

    /**
     * (to口座ID)<BR>
     * to口座ID<BR>
     */
    public long toAccountId;

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@証券会社コードチェック <BR>
     * this.証券会社コード==nullの場合、<BR>
     * 「証券会社コードが未指定です。」の例外をthrowする。<BR>
     *　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@　@tag:   BUSINESS_ERROR_00827<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        if(this.institutionCode == null)
        {
            log.debug("証券会社コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 債券自動約定リクエスト
     */
    public WEB3BondAutoExecRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3BondAutoExecResponse(this);
    }

}
@
