head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPAdvanceCustomerSearchListRequest.java
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
 * 保証金維持率割れ/立替金発生顧客検索一覧画面表示リクエスト
 */
public class WEB3AdminTPAdvanceCustomerSearchListRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admintradingpower_advance_customer_search_list";
       
    /**
      * ログユーティリティ
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPAdvanceCustomerSearchListRequest.class);

    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();

    /**
     * (値洗い区分)
     */
    public String markToMarketDiv;
    
    /**
     * (顧客属性)
     */
    public String customerAttribute;
    
    /**
     * (代用評価低下率)
     */
    public String substituteValuationDropRate;
    
    /**
     * (部店コード)
     */
    public String[] branchCode;
    
    /**
     * (顧客コード)
     */
    public String customerCode;
    
    /**
     * (要求ページ番号)
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)
     */
    public String pageSize;

    /**
     * (コンストラクタ)
     */
    public WEB3AdminTPAdvanceCustomerSearchListRequest()
    {
    }

    /**
     * (createレスポンス)
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminTPAdvanceCustomerSearchListResponse();
    }
    
    /**
     * (validate)
     * 当リクエストデータの整合性チェックを行う。 
     * （ただし、当クラス内で完結する簡易チェックのみとする。） 
     */
    public void validate() throws WEB3BaseException
    {

        //値洗い区分チェック(null不可)
         if( markToMarketDiv == null ) 
         {
             if( DBG ) 
             {
                log.debug("値洗い区分がNULLです。");
             }
             throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        getClass().getName() + ".validate");            
         }
    
        //(顧客属性チェック(null不可)
        if( customerAttribute == null )
        {
            if( DBG ) 
            {
                log.debug("顧客属性がNULLです。");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                    
        }
    
        //代用評価低下率
        if( substituteValuationDropRate != null && !substituteValuationDropRate.equals("") )
        {
            //数字チェック
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
            if( l_intDropRate < 0.0d  || l_intDropRate > 20.0d)
            {
                if( DBG ) 
                {
                    log.debug("0<=代用評価低下率<=20でない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //代用評価低下率が小数点第2位まで可
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
        
        //部店コード(null不可)
        if( branchCode == null )
        {
            if( DBG ) 
            {
                log.debug("部店がNULLです。");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                        
        }
        for(int i=0; i<branchCode.length; i++)
        {
            //null不可
            if(  branchCode[ i ] == null )
            {
                if( DBG ) 
                {
                    log.debug("部店コードがNULLです。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                                        
            }
            //数字のみ可
            if( !WEB3StringTypeUtility.isDigit( branchCode[ i ] ) )
            {
                if( DBG ) 
                {
                    log.debug("部店コードが数字でない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            }             
            //3桁のみ可
            if( !( branchCode[ i ].getBytes().length == 3 ) )
            {
                if( DBG ) 
                {
                    log.debug("部店が3桁でない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            }             
        }
    
        //顧客コード
        if( customerCode != null && !customerCode.equals("") )
        {
            //数字のみ可
            if( !WEB3StringTypeUtility.isDigit( customerCode ) )
            {
                if( DBG ) 
                {
                    log.debug("顧客コードが数字でない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            } 
            //6桁のみ可
            if( !(WEB3StringTypeUtility.getNubmerLength( customerCode ) == 6) )
            {
                if( DBG ) 
                {
                    log.debug("顧客コードが6桁でない。");
                }
                throw new WEB3SystemLayerException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                           getClass().getName() + ".validate");                
            }             
            
        }
    
        //要求ページ番号(null不可)
        if( pageIndex == null )
        {
            if( DBG ) 
            {
                log.debug("要求ページ番号がNULLです。");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                                                
        }

        //ページ内表示行数(null不可)
        if( pageSize == null )
        {
            if( DBG ) 
            {
                log.debug("ページ内表示行数がNULLです。");
            }
            throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                       getClass().getName() + ".validate");                                                
            
        }

    }

}
@
