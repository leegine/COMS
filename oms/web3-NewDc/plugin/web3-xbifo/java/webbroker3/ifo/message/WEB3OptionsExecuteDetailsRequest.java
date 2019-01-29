head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション当日注文約定詳細リクエストクラス(WEB3OptionsExecuteDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株価指数オプション当日注文約定詳細リクエスト)<BR>
 * 株価指数オプション当日注文約定詳細リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsExecuteDetailsRequest extends WEB3GenRequest
{
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "options_executeDetails";

    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200406101520L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3OptionsExecuteDetailsRequest.class);

    /**
    * 注文ＩＤ<BR>
    * <BR>
    * 注文約定照会・注文履歴・返済建玉一覧からの遷移の場合設定<BR>
    */
    public String id;

    /**
    * @@roseuid 40C0A8EE033C
    */
    public WEB3OptionsExecuteDetailsRequest()
    {

    }

    /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で簡潔する簡易チェックのみとする。）
    *
    * １）　@ＩＤチェック
    * 　@this.ＩＤがnullの値であれば例外をスローする。
    *   class: WEB3BusinessLayerException<BR>
    *   tag:   BUSINESS_ERROR_00080<BR>
    * @@throws WEB3BaseException
    * @@roseuid 406A5C1403B8
    */
    public void validate() throws WEB3BaseException
    {
        log.entering("execute WEB3OptionsExecuteDetailsRequest.validate()");
        //１）ソートキーのチェック
        log.debug("ソートキーのチェック");
        //ＩＤチェック
        if(WEB3StringTypeUtility.isEmpty(this.id))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "validate",
                "ＩＤがnullの値である。");
         }
     }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsExecuteDetailsResponse(this);
    }
}
@
