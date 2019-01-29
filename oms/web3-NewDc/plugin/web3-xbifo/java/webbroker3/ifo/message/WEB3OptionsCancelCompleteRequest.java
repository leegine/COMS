head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション取消注文完了リクエスト(WEB3OptionsCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数オプション取消注文完了リクエスト)<BR>
 * 株価指数オプション取消注文完了リクエストクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsCancelCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_cancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406102013L;

    /**
     * 注文ＩＤ<BR>
     */
    public String id;

    /**
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * (確認時発注日)<BR>
     * 画面では非表示。完了リクエストで送信する値。<BR>
     */
    public Date checkDate;

    /**
     * @@roseuid 40C075390251
     */
    public WEB3OptionsCancelCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4069633F02B3
     */
    public void validate() throws WEB3BaseException
    {
        //ＩＤチェック
        //this.ＩＤがnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
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
        return new WEB3OptionsCancelCompleteResponse(this);
    }
}
@
