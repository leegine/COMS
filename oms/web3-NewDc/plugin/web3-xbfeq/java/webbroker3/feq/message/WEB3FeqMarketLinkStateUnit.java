head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMarketLinkStateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式市場連動状況(WEB3FeqMarketLinkStateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
*/
    
package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FeqOrderRequestDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式市場連動状況)<BR>
 * 外国株式市場連動状況クラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3FeqMarketLinkStateUnit extends Message
{
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3FeqMarketLinkStateUnit.class);
     
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (市場名)<BR>
     * 市場名<BR>
     */
    public String marketName;
    
    /**
     * (外国株式市場連動区分)<BR>
     * 外国株式市場連動区分<BR>
     */
    public String feqMarketLinkDiv;
    
    /**
     * (変更後外国株式市場連動区分)<BR>
     * 変更後外国株式市場連動区分<BR>
     */
    public String afterFeqMarketLinkDiv;

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     *（ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     *<BR>
     * １）市場コードチェック<BR> 
     * 　@this.市場コード == nullの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00443<BR> 
     * <BR>
     * ２）外国株式市場連動区分チェック<BR>
     * 　@２－１）this.外国株式市場連動区分 == nullの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02653<BR>
     *  <BR>
     * 　@２－２）this.外国株式市場連動区分に下記の項目以外が設定されていたら、<BR>
     *　@　@　@　@「外国株式市場連動区分が未定義の値」の例外をスローする。<BR>
     *　@　@　@　@・0：非連動（MAIL）<BR> 
     *　@　@　@　@・1：連動（MQ）<BR> 
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02654<BR> 
     *  <BR>
     * ３）変更後外国株式市場連動区分チェック<BR> 
     * 　@３－１）this.変更後外国株式市場連動区分 == nullの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02653<BR> 
     *  <BR>
     * 　@３－２）this.変更後外国株式市場連動区分に下記の項目以外が設定されていたら、<BR>
     *　@　@　@　@「外国株式市場連動区分が未定義の値」の例外をスローする。<BR>
     *　@　@　@　@・0：非連動（MAIL）  <BR>
     *　@　@　@　@・1：連動（MQ）<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02654<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）市場コードチェック 
        // this.市場コード == nullの場合、例外をスローする。 
        if (this.marketCode == null)
        {
            log.debug("市場コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + STR_METHOD_NAME,
                "市場コードが未指定です。" + this.marketCode);
        }
        
        // ２）外国株式市場連動区分チェック 
        // ２－１）this.外国株式市場連動区分 == nullの場合、例外をスローする。
        if (this.feqMarketLinkDiv == null)
        {
            log.debug("外国株式市場連動区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02653,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式市場連動区分が未指定です。" + this.feqMarketLinkDiv);
        }
        
        //２－２）this.外国株式市場連動区分に下記の項目以外が設定されていたら、 
        //　@　@「外国株式市場連動区分が未定義の値」の例外をスローする。 
        //　@　@・0：非連動（MAIL） 
        //　@　@・1：連動（MQ） 
        if ((!WEB3FeqOrderRequestDivDef.REQUEST_MAIL.equals(this.feqMarketLinkDiv)) 
            &&(!WEB3FeqOrderRequestDivDef.REQUEST_MQ.equals(this.feqMarketLinkDiv)))
        {
            log.debug("外国株式市場連動区分が未定義の値。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02654,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式市場連動区分が未定義の値。" + this.feqMarketLinkDiv);
        }
        
        //３）変更後外国株式市場連動区分チェック 
        //３－１）this.変更後外国株式市場連動区分 == nullの場合、例外をスローする。 
        if (this.afterFeqMarketLinkDiv == null)
        {
            log.debug("外国株式市場連動区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02653,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式市場連動区分が未指定です。" + this.afterFeqMarketLinkDiv);
        }
        
        //　@３－２）this.変更後外国株式市場連動区分に下記の項目以外が設定されていたら、 
        //　@　@「外国株式市場連動区分が未定義の値」の例外をスローする。 
        //　@　@・0：非連動（MAIL） 
        //　@　@・1：連動（MQ）
        if ((!WEB3FeqOrderRequestDivDef.REQUEST_MAIL.equals(this.afterFeqMarketLinkDiv)) 
            &&(!WEB3FeqOrderRequestDivDef.REQUEST_MQ.equals(this.afterFeqMarketLinkDiv)))
        {
            log.debug("外国株式市場連動区分が未定義の値。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02654,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式市場連動区分が未定義の値。" + this.afterFeqMarketLinkDiv);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (外国株式市場連動状況)<BR>
     * コンストラクタ<BR>
     */
    public WEB3FeqMarketLinkStateUnit()
    {
        
    }
}
@
