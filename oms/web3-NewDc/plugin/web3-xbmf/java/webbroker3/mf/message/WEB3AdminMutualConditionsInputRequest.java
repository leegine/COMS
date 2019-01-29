head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録入力リクエスト(WEB3AdminMutualConditionsInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託銘柄条件登録入力リクエスト<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsInputRequest extends WEB3GenRequest
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131430L;    
    
    /**
     * (ID)<BR>
     * 投信銘柄ID<BR>
     */
    public String id;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsInputRequest.class);
    
    /**
     * (投信銘柄条件登録入力リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF7B2003DF
     */
    public WEB3AdminMutualConditionsInputRequest()
    {
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信銘柄条件登録入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF7B460371
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualConditionsInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * 1) IDがnullの場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00080 <BR>
     * @@roseuid 40DF7B460381
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("ＩＤが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ＩＤが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
