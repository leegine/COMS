head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託銘柄条件登録照会リクエスト(WEB3AdminMutualConditionsReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2007/04/09 張騰宇 (中訊) モデル548
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
 * 投資信託銘柄条件登録照会リクエスト<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsReferenceRequest 
    extends WEB3GenRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131230L;
    
    /**
     * (銘柄コード)<BR>
     * 投信銘柄コード<BR>
     * (nullの場合、指定ナシとする)<BR>
     */
    public String mutualProductCode;
    
    /**
     * 投信協会銘柄コード<BR>
     * (nullの場合、指定ナシとする)<BR>
     */
    public String mutualAssocProductCode;
    
    /**
     * 投信銘柄カテゴリーコード<BR>
     * (nullの場合、指定ナシとする)<BR>
     */
    public String categoryCode;
    
    /**
     * 要求ページ番号<BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * (投信・外貨MMF表示区分)<BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分<BR>
     * 0:投信のみ  <BR>
     * 1:外貨MMFのみ  <BR>
     * 2:両方<BR>
     * <BR>
     * ※nullの場合、「2:両方」とする<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * ページ内表示行数<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsReferenceRequest.class);    
    
    /**
     * (投信銘柄条件登録照会リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF7657015E
     */
    public WEB3AdminMutualConditionsReferenceRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信銘柄条件登録照会レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF76EB03C0
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsReferenceResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * 1) 要求ページ番号チェック<BR>
     *  1-1) this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00089 <BR>
     *  1-2) this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * 2) ページ内表示行数チェック<BR>
     *  2-1) this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00091 <BR>
     *  2-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00092 <BR>
     * @@roseuid 40DF76EB03DF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1) 要求ページ番号チェック
        
        //1-1) this.要求ページ番号がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");    
        }
        
        //1-2) this.要求ページ番号が数字以外の値であれば例外をスローする。     
         //文字列の文字種を判断する機@能を実装するユーティリティ
          //・クラス(WEB3StringTypeUtility.java)。
        if (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)
        {
            log.debug("要求ページ番号が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値である。");
        }
       
        //2) ページ内表示行数チェック 
        
        //2-1) this.ページ内表示行数がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00091 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }        
        
        // 2-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (WEB3StringTypeUtility.isNumber(this.pageSize) == false)
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        log.exiting(STR_METHOD_NAME);
     }
 }
@
