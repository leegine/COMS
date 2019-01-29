head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託海外市場カレンダー登録完了リクエスト(WEB3AdminMutualFrgncalCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 王蘭芬(中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー    
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
 * 投資信託海外市場カレンダー登録完了リクエストクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_frgncal_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151339L;    
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualFrgncalCompleteRequest.class);

    /**
     * 投信銘柄コード
     */
    public String mutualProductCode;
    
    /**
     * 登録更新情報<BR>
     * (変更チェックボックスをチェックされた日付情報)<BR>
     */
    public WEB3MutualBizDateUnit[] bizDateList;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40C008050113
     */
    public WEB3AdminMutualFrgncalCompleteRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信海外市場カレンダー登録完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40C0080E0152
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualFrgncalCompleteResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@銘柄コードチェック<BR>
     * 　@this.銘柄コードがnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * ２)　@登録更新情報チェック<BR>
     * 　@２－１)　@登録更新情報がnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00411 <BR>
     * 　@２－２)　@登録更新情報の要素数が0件の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00412 <BR>
     * @@roseuid 40C0085A0181
     */
    public void validate() throws WEB3BaseException
    {
        final String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        // １)　@銘柄コードチェック
        // this.銘柄コードがnullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode))
        {
            log.debug("銘柄コードを入力してください。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + l_strMethodName,
                "銘柄コードを入力してください。"
            );
        }
        // ２)　@登録更新情報チェック
        // ２－１)　@登録更新情報がnullの場合、例外をスローする。
        if (this.bizDateList == null)
        {
            log.debug("登録更新情報が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00411,
                this.getClass().getName() + "." + l_strMethodName,
                "登録更新情報が未指定です。"
            );
        }
        // ２－２)　@登録更新情報の要素数が0件の場合、例外をスローする。
        if (this.bizDateList.length == 0)
        {
            log.debug("登録更新情報の要素数が0件の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00412,
                this.getClass().getName() + "." + l_strMethodName,
                "登録更新情報の要素数が0件の場合"
            );
        }
        log.exiting(l_strMethodName);
    }
}
@
