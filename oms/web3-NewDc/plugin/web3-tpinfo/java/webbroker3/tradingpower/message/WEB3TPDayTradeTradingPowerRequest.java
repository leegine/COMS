head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDayTradeTradingPowerRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 日計り銘柄取引余力試算リクエストクラス(WEB3TPDayTradeTradingPowerRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (日計り銘柄取引余力試算画面表示リクエスト)<BR>
 * 日計り銘柄取引余力試算画面表示リクエストクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPDayTradeTradingPowerRequest extends WEB3GenRequest 
{
    
   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_daytrade_tradingpower";

   /**
     * ログユーティリティ
     */
   private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPDayTradeTradingPowerRequest.class);

   /**
    * (デバッグison)
    */
   private static boolean DBG = log.ison();

   /**
    * 銘柄コード
    */
   public String productCode;
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B5544C02A7
    */
   public WEB3TPDayTradeTradingPowerRequest() 
   {
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    * <BR>
    * @@return レスポンスオブジェクト
    * @@roseuid 41B5544C02D6
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPDayTradeTradingPowerResponse(this);
   }
   
   /**
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
    * <BR>
    * １）　@銘柄コードチェック<BR>
    * <BR>
    * @@roseuid 41B68FAE01F3
    */
   protected void validate() throws WEB3BaseException 
   {
       log.debug("this.productCode = " + productCode);
       
       //銘柄コードNULLの場合、処理終了
       if ( productCode == null )
       {
           if( DBG ) 
           {
               log.debug("銘柄コードがNULLです。");
           }
           return;
       }

       //銘柄コード空文字チェック
       if ( productCode.equals("") )
       {
           if( DBG ) 
           {
               log.debug("銘柄コードが空文字です。");
           }
           throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                    getClass().getName() + ".validate");
       }

       //銘柄コード半角英数字チェック
       if ( !WEB3StringTypeUtility.isLetterOrDigit( productCode ) )
       {
           if( DBG ) 
           {
               log.debug("銘柄コードが半角英数字ではありません。");
           }
           throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                     getClass().getName() + ".validate");
       }

       //銘柄コード桁数チェック
       if ( WEB3StringTypeUtility.getByteLength( productCode ) != 5 )
       {
           if( DBG ) 
           {
               log.debug("銘柄コードが５桁でないです。");
           }
           throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                    getClass().getName() + ".validate");
       }

    }
    
}
@
