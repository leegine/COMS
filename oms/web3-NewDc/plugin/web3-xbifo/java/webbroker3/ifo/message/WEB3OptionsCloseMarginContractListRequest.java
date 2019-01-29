head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginContractListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済建玉一覧リクエストクラス(WEB3OptionsCloseMarginContractListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数オプション返済建玉一覧リクエスト)<BR>
 * 株価指数オプション返済建玉一覧リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginContractListRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginContracList";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406110900L;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3OptionsCloseMarginContractListRequest.class);

     /**
     * (ＩＤ)<BR>
     * 注文ＩＤ
     */
    public String id;

    /**
    * @@roseuid 40C0A8E7006D
    */
    public WEB3OptionsCloseMarginContractListRequest()
    {

    }

    /**
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
    * <BR>
    * １）　@ＩＤチェック<BR>
    * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
    *   class: WEB3BusinessLayerException<BR>
    *   tag:   BUSINESS_ERROR_00080<BR>
    * @@throws WEB3BaseException
    * @@roseuid 406A7B540138
    */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）ソートキーのチェック
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginContractListResponse(this);
    }
}
@
