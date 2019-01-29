head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqMarketLinkChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式市場連動区分変更完了リクエスト(WEB3AdminFeqMarketLinkChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式市場連動区分変更完了リクエスト)<BR>
 * 管理者外国株式市場連動区分変更完了リクエストクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3AdminFeqMarketLinkChangeCompleteRequest extends WEB3GenRequest
{    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_market_link_change_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3AdminFeqMarketLinkChangeCompleteRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFeqMarketLinkChangeConfirmRequest.class);
    
    /**
     * (外国株式市場連動状況一覧)<BR>
     * 外国株式市場連動状況の配列<BR>
     */
    public WEB3FeqMarketLinkStateUnit[] feqMarketLinkStateList;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR> 
     *（ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     *<BR>
     * １）外国株式市場連動状況一覧チェック<BR> 
     *　@１－１）this.外国株式市場連動状況一覧 == nullの場合、例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_02650<BR> 
     *<BR>
     *　@１－２）this.外国株式市場連動状況一覧の件数分、以下の処理を繰り返す。<BR> 
     *　@　@１－２－１）外国株式市場連動状況.validate()をコールする。 <BR>
     *<BR>
     * ２）暗証番号チェック<BR> 
     *　@this.暗証番号 == nullの場合、「暗証番号がnull」の例外をスローする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_01090<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
       final String STR_METHOD_NAME ="validate()";
       log.entering(STR_METHOD_NAME);
       
       // １）外国株式市場連動状況一覧チェック
       // １－１）this.外国株式市場連動状況一覧 == nullの場合、例外をスローする。
       if (this.feqMarketLinkStateList == null)
       {
           log.debug("外国株式市場連動状況一覧が未指定です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02650,
               this.getClass().getName() + STR_METHOD_NAME,
               "外国株式市場連動状況一覧が未指定です。" + this.feqMarketLinkStateList);      
       }
       
       // １－２）this.外国株式市場連動状況一覧の件数分、以下の処理を繰り返す。
       // １－２－１）外国株式市場連動状況.validate()をコールする。
       int l_intFeqMarketLinkStateListLength = this.feqMarketLinkStateList.length;
       for (int i = 0; i < l_intFeqMarketLinkStateListLength; i++)
       {
           this.feqMarketLinkStateList[i].validate();
       }
       
       // ２）暗証番号チェック
       // this.暗証番号 == nullの場合、「暗証番号がnull」の例外をスローする。
       if (this.password == null)
       {
           log.debug("暗証番号が未指定です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01090,
               this.getClass().getName() + STR_METHOD_NAME,
               "暗証番号が未指定です。" + this.password);      
       }
       log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqMarketLinkChangeCompleteResponse(this);
    }
}
@
