head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文入力リクエストクラス(WEB3MutualBuyInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 周勇 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託買付注文入力リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_input";
    
    /**
     * (ID)<BR>
     * 投信銘柄ID
     */
    public String id;
    
    /**
     * (電子鳩チェックフラグ)<BR>
     * 電子鳩チェックフラグ<BR>
     * <BR>
     * true：チェック要 <BR>
     * false：チェック不要
     */
    public boolean batoCheckFlag;
    
    /**
     * (種別コード)<BR>
     * 種別コード
     */
    public String typeCode;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A87D1E023A
     */
    public WEB3MutualBuyInputRequest()
    {
    }
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信買付注文入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A87D2E0111
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualBuyInputResponse(this);
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyInputRequest.class);
        
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@IDチェック<BR>
     * 　@this.IDがnullの場合、例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_00080 <BR>
     * 
     * @@roseuid 40A87D1900F2
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //this.IDがnullの場合、例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("ＩＤが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ＩＤが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
