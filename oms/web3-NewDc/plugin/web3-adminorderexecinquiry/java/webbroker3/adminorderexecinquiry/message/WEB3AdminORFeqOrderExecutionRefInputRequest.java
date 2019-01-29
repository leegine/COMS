head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・外国株式注文約定照会入力リクエスト(WEB3AdminORFeqOrderExecutionRefInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・外国株式注文約定照会入力リクエスト)<BR>
 * 管理者・外国株式注文約定照会入力リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefInputRequest extends WEB3GenRequest
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORFeqOrderExecutionRefInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefInput";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列<BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している<BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * @@roseuid 42D1C8E90399
     */
    public WEB3AdminORFeqOrderExecutionRefInputRequest() 
    {
        
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・外国株式注文約定照会入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF7B460371
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORFeqOrderExecutionRefInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * 　@１－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@１－２）this.部店コード.length == 0の場合、<BR>
     * 　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_02175<BR>
     * @@throws システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
     * @@throws WEB3BaseException
     * @@roseuid 42A65BDE0291
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コードチェック
        //　@１－１）this.部店コード == nullの場合、
        //　@　@　@　@　@「部店コードがnull」の例外をスローする。
        if (this.branchCode == null)
        {
            String l_strMessage = "部店コードがnullです。";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //
        //　@１－２）this.部店コード.length == 0の場合、
        //　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。
        if (this.branchCode.length == 0)
        {
            String l_strMessage = "部店コードの要素数が0です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
