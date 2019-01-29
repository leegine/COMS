head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募入力リクエスト(WEB3BondDomesticApplyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (国内債券応募入力リクエスト)<BR>
 * 国内債券応募入力リクエスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productId;

    /**
     * (電子鳩チェックフラグ)<BR>
     * <BR>
     * true：チェック要<BR>
     * false：チェック不要<BR>
     */
    public boolean batoCheckFlag;

    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String typeCode;

    /**
     * @@roseuid 46A473FD0399
     */
    public WEB3BondDomesticApplyInputRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 国内債券応募入力レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondDomesticApplyInputResponse(this);
    }

    /**
     * 当リクエストデータの整合性をチェックする。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@銘柄IDチェック<BR>
     * 　@　@　@銘柄ID == nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_02229<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46664ACE0359
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@銘柄IDチェック
        //銘柄ID == nullの場合、例外をスローする。
        if (this.productId == null)
        {
            log.debug("銘柄IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄IDが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
