head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序登録完了リクエスト(WEB3AdminMutualDisplayOrderCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 黄建 (中訊) 新規作成 
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
 * (投信管理者銘柄表示順序登録完了リクエスト)<BR>
 * 投資信託管理者銘柄表示順序登録完了リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_display_order_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031047L;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualDisplayOrderCompleteRequest.class);
    
    /**
     * (銘柄表示順序更新値一覧)<BR>
     * 投信管理者銘柄表示順序更新値オブジェクトの配列
     */
    public WEB3MutualDisplayOrderChangeUnit[] displayOrderChangeList;
    
    /**
     * (暗証番号)<BR>
     *  暗証番号
     */
    public String password;
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信管理者銘柄表示順序登録完了レスポンスオブジェクトを返却する。
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4157AA530153
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualDisplayOrderCompleteResponse(this);
    }
    
    /**
     * (validate)<BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）銘柄表示順序更新値一覧のチェック<BR>
     * 　@１－１）this.銘柄表示順序更新値一覧＝nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01273<BR>
     * 　@１－２）this.銘柄表示順序更新値一覧の要素数＝0の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01274<BR>
     * 　@１－３）this.銘柄表示順序更新値一覧の要素数分、以下を繰り返す。<BR>
     * 　@　@１－３－１）銘柄表示順序更新値.表示順＝nullの場合、例外をスローする。<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_01275<BR>
     * 　@　@１－３－２）銘柄表示順序更新値.表示順が数値以外の場合、<BR>
     *                例外をスローする。<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_01276<BR>
     * 　@　@１－３－３）銘柄表示順序更新値.銘柄コード＝nullの場合、<BR>
     *                例外をスローする。<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_01277
     * @@throws WEB3BaseException
     * @@roseuid 4153BB7403A2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）銘柄表示順序更新値一覧のチェック 
        //  １－１）this.銘柄表示順序更新値一覧＝nullの場合、例外をスローする。 
        if (this.displayOrderChangeList == null)
        {
            log.debug("銘柄表示順序更新値一覧が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01273,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄表示順序更新値一覧が未指定です。");
        }
        
        //１－２）this.銘柄表示順序更新値一覧の要素数＝0の場合、例外をスローする。 
        if (this.displayOrderChangeList.length == 0)
        {
            log.debug("銘柄表示順序更新値一覧の要素数が０である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01274,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄表示順序更新値一覧の要素数が０である。");
        }
        
        //１－３）this.銘柄表示順序更新値一覧の要素数分、以下を繰り返す。 
        for (int i = 0; i < this.displayOrderChangeList.length; i++)
        {
            //１－３－１）銘柄表示順序更新値.表示順!=nullであり、かつ数値以外の場合、例外をスローする。 
            if (!WEB3StringTypeUtility.isEmpty(this.displayOrderChangeList[i].displayOrder) &&
                !WEB3StringTypeUtility.isNumber(this.displayOrderChangeList[i].displayOrder))
            {
                log.debug("銘柄表示順序更新値の表示順が数値以外の値である。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01276,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄表示順序更新値の表示順が数値以外の値である。");
            }
            
            //１－３－２）銘柄表示順序更新値.銘柄コード＝nullの場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.displayOrderChangeList[i].mutualProductCode))
            {
                log.debug("銘柄表示順序更新値の銘柄コードが未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01277,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄表示順序更新値の銘柄コードが未指定です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
