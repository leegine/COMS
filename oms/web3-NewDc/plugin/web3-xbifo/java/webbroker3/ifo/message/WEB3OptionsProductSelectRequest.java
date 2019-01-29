head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文銘柄選択画面リクエスト(WEB3OptionsProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数オプション新規建注文銘柄選択画面リクエスト)<BR>
 * 株価指数オプション新規建注文銘柄選択画面リクエストクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionsProductSelectRequest extends WEB3GenRequest
{

    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsProductSelectRequest.class);
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141514L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_productSelect";

    /**
     * 建区分<BR>
     * 1：買建　@2：売建<BR>
     */
    public String contractType;

    /**
     * @@roseuid 40C0A8EC007D
     */
    public WEB3OptionsProductSelectRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@建区分チェック<BR>
     * 　@１－１）this.建区分がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00263<BR>
     * 　@１－２）this.建区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”1：買建”<BR>
     * 　@　@　@　@・”2：売建”<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00264<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407DED33021B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".validate()";
                
        log.entering(STR_METHOD_NAME);
        
        //建区分チェック
        //this.建区分がnullの値であれば例外をスローする。
        log.debug("建区分チェック");
        log.debug("this.contractType = " + this.contractType);
        if (this.contractType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263,
                getClass().getName() + "validate",
                "建区分がnullである");
        }

        //建区分チェック
        //this.建区分が”1：買建””2：売建"以外の場合例外をスローする。
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType)
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264,
                getClass().getName() + "validate",
                "建区分が”1：買建”、”2：売建”以外である");
        }
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsProductSelectResponse(this);
    }
}
@
