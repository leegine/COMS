head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託乗換入力リクエストクラス(WEB3MutualSwitchingInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/25 黄建 (中訊) レビュー
                   2005/10/18 韋念瓊 (中訊) フィデリティ対応
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
 * 投資信託乗換入力リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputRequest extends WEB3GenRequest
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingInputRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_switching_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;

    /**
     * (資産ID)<BR>
     * 投信資産ID<BR>
     */
    public String id;
    
    /**
     * (乗換先銘柄ID)<BR>
     * 乗換先銘柄ID<BR>
     */
    public String switchingProductId;
    
    /**
     * (電子鳩チェックフラグ)<BR>
     * 電子鳩チェックフラグ <BR>
     * true：チェック要 <BR>
     * false：チェック不要 <BR>
     */
    public boolean batoCheckFlag;

    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String typeCode;

    /**
     * (投信乗換入力リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8A28A0218
     */
    public WEB3MutualSwitchingInputRequest()
    {

    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信乗換入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A2B800A1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualSwitchingInputResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@資産IDチェック 
     * 　@this.資産ID==nullの場合、例外をスローする。 
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_01919 <BR>
     * <BR>
     * ２)　@乗換先銘柄IDチェック <BR>
     * 　@this.乗換先銘柄ID==nullの場合、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02268 <BR>
     * <BR>
     * @@roseuid 40A8A2AA0043
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("資産IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "資産IDが未指定です。");
        }
        
        if (WEB3StringTypeUtility.isEmpty(this.switchingProductId))
        {
            log.debug("乗換先銘柄IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02268,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乗換先銘柄IDが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
