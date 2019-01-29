head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託海外市場カレンダー登録照会リクエスト(WEB3AdminMutualFrgncalReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 王蘭芬(中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー  
                   2004/12/07 于美麗 (中訊) 残対応
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
 * 投資信託海外市場カレンダー登録照会リクエストクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalReferenceRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_mutual_frgncal_reference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151426L;
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualFrgncalReferenceRequest.class);

    /**
     * 投信銘柄コード<BR>
     * ([銘柄コード]の入力値、または[銘柄名]の選択銘柄の銘柄コード)<BR>
     */
    public String mutualProductCode;
    
    /**
     * 検索年月<BR>
     * (書式:"YYYYMM")<BR>
     */
    public String searchYM;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40CEC6F6007A
     */
    public WEB3AdminMutualFrgncalReferenceRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信海外市場カレンダー登録照会レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40CED01B02AD
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualFrgncalReferenceResponse(this);
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
     * ２)　@検索年月チェック<BR>
     * 　@２－１)　@this.検索年月がnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00429 <BR>
     * 　@２－２)　@this.検索年月が数値以外の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00430 <BR>
     * 　@２－３)　@this.検索年月が6桁以外の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00431 <BR>
     * 　@２－４)　@this.検索年月の下2桁が、01～12までの数値のいずれかではない場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00432 <BR>
     * 例外をスローする。<BR>
     * <BR>
     * @@roseuid 40CED01B02CC
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
        // ２)　@検索年月チェック
        // ２－１)　@this.検索年月がnullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.searchYM))
        {
            log.debug("検索年月が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00429,
                this.getClass().getName() + "." + l_strMethodName,
                "検索年月が未指定です。"
            );
        }
        // ２－２)　@this.検索年月が数値以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.searchYM))
        {
            log.debug("検索年月が数値以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00430,
                this.getClass().getName() + "." + l_strMethodName,
                "検索年月が数値以外の場合"
            );
        }
        // ２－３)　@this.検索年月が6桁以外の場合、例外をスローする。
        if (this.searchYM.length() != 6)
        {
            log.debug("検索年月が6桁以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00431,
                this.getClass().getName() + "." + l_strMethodName,
                "検索年月が6桁以外の場合"
            );
        }
        // ２－４)　@this.検索年月の下2桁が、01～12までの数値以外の場合、例外をスローする。
        int l_intMM = Integer.parseInt(searchYM.substring(4, 6));
        if (l_intMM < 1 || l_intMM > 12)
        {
            log.debug("検索年月の下4桁が、01～12までの数値以外の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00432,
                this.getClass().getName() + "." + l_strMethodName,
                "検索年月の下4桁が、01～12までの数値以外の場合"
            );
        }
        log.exiting(l_strMethodName);
    }
}
@
