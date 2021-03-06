head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引明細リクエスト(WEB3HistoryTradeDetailRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  温 顕 法@(中訊) 新規作成
*/


package webbroker3.tradehistory.message;

import java.util.Date;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (取引明細リクエスト)
 * 取引明細リクエストクラス
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
 
public class WEB3HistoryTradeDetailRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistoryTradeDetailRequest.class); 
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221708L;
        
    /**
     * (明細管理番号)<BR>
     * 明細管理番号<BR>
     */
    public String detailsManageNo;
    
    /**
     * (翻訳摘要名)<BR>
     * 翻訳摘要名<BR>
     */
    public String remarkName;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;    
    /**
     * @@roseuid 41789C4A030D
     */
    public WEB3HistoryTradeDetailRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@明細管理番号チェック<BR>
     * 　@　@　@this.明細管理番号 == nullの場合は、<BR>
     * 　@　@　@「明細管理番号がnull」の例外をスローする。<BR>
     *        class         :  WEB3BusinessLayerException           <BR>
     *        tag            :  BUSINESS_ERROR_01060              <BR>
     * <BR>
     * ２）　@翻訳摘要名チェック<BR>
     * 　@　@　@this.翻訳摘要名 == nullの場合は、<BR>
     * 　@　@　@「翻訳摘要名がnull」の例外をスローする。<BR>
     *        class         :  WEB3BusinessLayerException           <BR>
     *        tag            :  BUSINESS_ERROR_01061              <BR>
     * <BR>
     * ３）　@銘柄名チェック<BR>
     * 　@　@　@this.銘柄名 == nullの場合は、<BR>
     * 　@　@　@「銘柄名がnull」の例外をスローする。<BR>
     *        class         :  WEB3BusinessLayerException           <BR>
     *        tag            :  BUSINESS_ERROR_01062              <BR>
     * <BR>
     * 4）　@受渡日チェック<BR>
     * 　@　@　@this.受渡日 == nullの場合は、<BR>
     * 　@　@　@「受渡日がnull」の例外をスローする。<BR>
     *        class         :  WEB3BusinessLayerException           <BR>
     *        tag            :  BUSINESS_ERROR_01079        
     * <BR>
     * @@roseuid 413410E703BC
     */
    public void validate() throws  WEB3BaseException
    {
        
        // 当リクエストデータの整合性チェックを行う。
        // （ただし、当クラス内で完結する簡易チェックのみとする。）
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@明細管理番号チェック
        // 　@　@　@this.明細管理番号 == nullの場合は、
        // 　@　@　@「明細管理番号がnull」の例外をスローする。
        if (this.detailsManageNo== null)
        {
            //例外
            log.error("「明細管理番号がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01060, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }

        // ２）　@翻訳摘要名チェック
        // 　@　@　@this.翻訳摘要名 == nullの場合は、
        // 　@　@　@「翻訳摘要名がnull」の例外をスローする。
        if (this.remarkName== null)
        {
            //例外
            log.error("「翻訳摘要名がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01061, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }         
        // ３）　@銘柄名チェック
        // 　@　@　@this.銘柄名 == nullの場合は、
        // 　@　@　@「銘柄名がnull」の例外をスローする。
        if (this.productName== null)
        {
            //例外  
            log.error("「銘柄名がnull」の例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01062, 
                this.getClass().getName() + STR_METHOD_NAME); 
        }   
        //    受渡日チェック
        //   「受渡日がnull」の例外をスローする
        if (this.deliveryDate == null)
        {
             //例外
             log.error("「受渡日がnull」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01079, 
                this.getClass().getName() + STR_METHOD_NAME); 
           }      
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41789C4A037A
     */
    public WEB3GenResponse createResponse() 
    {
        
      return new WEB3HistoryTradeDetailResponse(this);
     
    }
}
@
