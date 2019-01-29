head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginTradingPowerDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 信用新規建余力詳細画面表示リクエストクラス(WEB3TPMarginTradingPowerDetailRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

import java.util.Date;

/**
 * (信用新規建余力詳細画面表示リクエスト)<BR>
 * 信用新規建余力詳細画面表示リクエストクラス。<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPMarginTradingPowerDetailRequest extends WEB3GenRequest 
{

   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_margin_tradingpower_detail";

   /**
    * ログユーティリティ
    */
   private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPMarginTradingPowerDetailRequest.class);

    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();

   /**
    * 余力計算結果ID
    */
   public String calcResultId;
   
   /**
    * 日付
    */
   public Date bizDate;
   
   /**
    * (コンストラクタ)<BR>
    * @@roseuid 41B5838A026A
    */
   public WEB3TPMarginTradingPowerDetailRequest() 
   {
   }
   
   /**
    * (createレスポンス)<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B5838A0289
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPMarginTradingPowerDetailResponse(this);
   }
   
   /**
    * (validate)<BR>
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
    * <BR>
    * １）　@余力計算結果IDチェック<BR>
    * ２）　@日付チェック<BR>
    * <BR>
    * @@roseuid 41B6B44800F9
    */
   protected void validate() throws WEB3BusinessLayerException 
   {
   
       //余力計算結果IDNULLチェック
       if ( bizDate == null )
       {
           if( DBG )
           {
               log.debug("余力計算結果IDがNULLです。");
           }
           throw new WEB3BusinessLayerException(
                      WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                      getClass().getName() + ".validate");
       }   
   
        //日付NULLチェック
        if ( bizDate == null )
        {
            if( DBG )
            {
               log.debug("日付がNULLです。");
            }
            throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");
        }

   }
   
}
@
