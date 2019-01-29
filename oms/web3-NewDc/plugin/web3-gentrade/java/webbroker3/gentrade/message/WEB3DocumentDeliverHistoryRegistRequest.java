head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付履歴登録リクエスト(WEB3DocumentDeliverHistoryRegistRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 大澤喜宗@(SRA) 新規作成
Revesion History : 2008/01/23 魏キン(中訊) 仕様変更 モデル310
*/
package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (書面交付履歴登録リクエスト)<BR>
 * <BR>
 * 書面交付履歴登録リクエストクラス<BR>
 * @@author 大澤喜宗@
 * @@version 1.0
 */
public class WEB3DocumentDeliverHistoryRegistRequest extends WEB3GenRequest 
{
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "document_deliver_history_regist";
    
    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200709281829L;
    
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3DocumentDeliverHistoryRegistRequest.class);
    
    /**
     * 銘柄コード<BR>
     * 投信、IPOの場合、銘柄コード <BR>
     * ログイン時の場合のみ、複数の場合あり <BR>
     */
    public String[] productCode;
    
    /**
     * 電子鳩チェックフラグ<BR>
     * true：チェック要<BR>
     * false：チェック不要<BR>
     */
    public boolean eleBatoCheckFlg;
    
    /**
     * 種別コード
     */
    public String typeCode;
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * 以下の項目のいずれかがnullの場合、例外をスローする。<BR>
     * <BR>
     * 　@銘柄コード、種別コード<BR>
     * <BR>
     * 銘柄コード＝nullの場合<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 種別コード＝nullの場合<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag   : BUSINESS_ERROR_02202<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        
        log.entering(STR_METHOD_NAME);

        // 銘柄コードがnullの場合
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄コードが未指定です。");
        }
        if (this.productCode.length  == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                getClass().getName() + "." + STR_METHOD_NAME, 
                "銘柄コードが未指定です。");            
        }    

        // 種別コードがnullの場合
        if (this.typeCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02202, 
                getClass().getName() + "." + STR_METHOD_NAME, 
                "種別コードがnullである");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3DocumentDeliverHistoryRegistResponse(this);
    }

}
@
