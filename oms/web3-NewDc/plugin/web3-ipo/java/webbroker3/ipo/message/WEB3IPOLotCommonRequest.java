head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当共通リクエスト(WEB3IPOLotCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 鄭徳懇 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO抽選割当共通リクエスト)<BR>
 *  管理者IPO抽選割当共通リクエストクラス<BR>
 *
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3IPOLotCommonRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IPOLotCommonRequest.class);

    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_lotCommon";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200512192100L;
    
    /**
     * (画面区分)<BR>
     * 遷移元の画面を示す区分。<BR>
     * <BR>
     * １：登録<BR>
     * ２：参照
     */
    public String displayDiv;
    
    /**
     * (銘柄ID)<BR>
     * IPO銘柄ID。
     */
    public String id;
    
    /**
     * @@roseuid 4112E68202AA
     */
    public WEB3IPOLotCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@画面区分チェック <BR>
     * 　@１－１）this.画面区分 == nullの場合、 <BR>
     * 　@　@　@　@　@「画面区分がnull」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02325<BR>
     * <BR>
     * ２）　@銘柄IDチェック <BR>
     * 　@２－１）this.銘柄ID == nullの場合、 <BR>
     * 　@　@　@　@　@「銘柄IDがnull」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02229<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１－１）this.画面区分 == nullの場合、
        //　@　@　@　@「画面区分がnull」の例外をスローする。
        if (this.displayDiv == null) 
        {
            log.debug("画面区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02325,
                getClass().getName() + "." + STR_METHOD_NAME,
                "画面区分が未指定です。");
        }
        
        //２－１）this.銘柄ID == nullの場合、 
        //　@　@　@　@銘柄IDがnull」の例外をスローする。
        if (this.id == null) 
        {
            log.debug("銘柄IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄IDが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E44A02FD
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOLotCommonResponse(this);
    }
}
@
