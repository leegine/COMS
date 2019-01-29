head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPAdvanceCustomerDetailRequest.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 保証金維持率割れ/立替金発生顧客詳細画面表示リクエスト
 */
public class WEB3AdminTPAdvanceCustomerDetailRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_detail";

    /**
      * ログユーティリティ
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPAdvanceCustomerDetailRequest.class);

    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();

    /**
     * (顧客属性)
     */
    public String customerAttribute;

    /**
     * (余力計算結果ID)
     */
    public String calcResultId;
       
    /**
     * (代用評価低下率)
     */
    public String substituteValuationDropRate;

    /**
     * コンストラクタ
     */
    public WEB3AdminTPAdvanceCustomerDetailRequest()
    {
    }

    /**
     * (createレスポンス)
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminTPAdvanceCustomerDetailResponse();
    }
    
    /**
     * (validate)
     * 当リクエストデータの整合性チェックを行う。 
     * （ただし、当クラス内で完結する簡易チェックのみとする。） 
     */
    public void validate() throws WEB3BaseException
    {
        
        //顧客属性チェック(null不可)
        if ( customerAttribute == null )
        {
            if( DBG )
            {
                log.debug("顧客属性がNULLです。");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");
        }
        
        //余力計算結果IDチェック(null不可)
        if( calcResultId == null )
        {
            if( DBG )
            {
                log.debug("顧客属性がNULLです。");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");
        }
       
        //代用評価低下率チェック
        if( substituteValuationDropRate != null && !substituteValuationDropRate.equals("") )
        {
            //数字のみ
            if( !WEB3StringTypeUtility.isNumber( substituteValuationDropRate ) )
            {
                if( DBG )
                {
                    log.debug("代用評価低下率が数字でない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //0<=代用評価低下率<=20まで可
            double l_intDropRate = Double.parseDouble( substituteValuationDropRate );
            if( l_intDropRate < 0d  || l_intDropRate > 20)
            {
                if( DBG )
                {
                    log.debug("代用評価低下率が数字でない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //小数点第2位まで可
            if( WEB3StringTypeUtility.getFractionDigits( substituteValuationDropRate ) > 2 )
            {
                if( DBG )
                {
                    log.debug("代用評価低下率が小数点第2位まででない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                               
            }
            
        }

    }

}
@
