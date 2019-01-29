head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistChangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー変更入力画面リクエスト(WEB3AdminMutualCategoryRegistChangeRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
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
 * (投信管理者カテゴリー変更入力画面リクエスト)<BR>
 * 投資信託管理者カテゴリー変更入力画面リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistChangeRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_change";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030935L;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualCategoryRegistChangeRequest.class);
    /**
     * (投信銘柄カテゴリーコード)<BR>
     * 投信銘柄カテゴリー名称に対応した投信銘柄カテゴリーコード
     */
    public String categoryCode;
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信管理者カテゴリー変更入力画面レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 4153B8100269
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualCategoryRegistChangeResponse(this);
    }
   
    /**
     * (validate)<BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）投信銘柄カテゴリーコードのチェック<BR>
     * 　@１－１）this.投信銘柄カテゴリーコード==nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01243<BR>
     * 　@１－２）this.投信銘柄カテゴリーコードの値＞2Byteの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01244
     * @@throws WEB3BaseException
     * @@roseuid 415401B703B0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）投信銘柄カテゴリーコードのチェック 
        //  １－１）this.投信銘柄カテゴリーコード==nullの場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.categoryCode))
        {
            log.debug("投信銘柄カテゴリーコードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01243,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信銘柄カテゴリーコードが未指定です。");
        }
        
        //  １－２）this.投信銘柄カテゴリーコードの値＞2Byteの場合、例外をスローする。
        if (this.categoryCode.getBytes().length > 2)
        {
            log.debug("投信銘柄カテゴリーコードの値が上限値を超えています。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01244,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信銘柄カテゴリーコードの値が上限値を超えています。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
