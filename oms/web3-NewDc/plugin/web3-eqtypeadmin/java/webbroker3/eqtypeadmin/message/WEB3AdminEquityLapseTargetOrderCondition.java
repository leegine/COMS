head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityLapseTargetOrderCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効対象注文条件(WEB3AdminEquityLapseTargetOrderCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 新規作成
Revesion History : 2007/12/17  趙林鵬(中訊) 仕様変更モデルNo.169
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株式失効対象注文条件)<BR>
 * 株式失効対象注文条件<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */

public class WEB3AdminEquityLapseTargetOrderCondition extends Message 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityLapseTargetOrderCondition.class);
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列 <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している <BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode = null;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コードの一覧
     */
    public String[] marketList;
    
    /**
     * (取引区分一覧)<BR>
     * 取引区分の一覧<BR>
     * <BR>
     * 1：　@現物買付注文<BR>
     * 2：　@現物売付注文<BR>
     * 3：　@新規買建注文<BR>
     * 4：　@新規売建注文<BR>
     * 5：　@買建返済注文<BR>
     * 6：　@売建返済注文<BR>
     * 7：　@現引注文<BR>
     * 8：　@現渡注文<BR>
     */
    public String[] tradingTypeList;
    
    /**
     * (弁済区分一覧)<BR>
     * 弁済区分一覧<BR>
     * <BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
     */
    public String[] repaymentList = null;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode = null;
    
    /**
     * (株式失効対象注文条件)<BR>
     * コンストラクタ
     * @@return WEB3AdminEquityLapseTargetOrderCondition
     * @@roseuid 4469235C0148
     */
    public WEB3AdminEquityLapseTargetOrderCondition() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@部店コードチェック <BR>
     * 　@１－１）　@this.部店コード == nullの場合、 <BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@１－２）　@this.部店コードの要素数分以下の処理を行う。<BR> 
     * 　@　@１－２－１）　@this.部店コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@　@・部店コード != 数字<BR> 
     * 　@　@　@　@　@　@　@・部店コード.length != 3 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）　@銘柄コードチェック <BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。 <BR>
     * 　@２－１）　@this.銘柄コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@「銘柄コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@・銘柄コード != 数字 <BR>
     * 　@　@　@　@・銘柄コード.length != 5 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01067<BR>
     * <BR>
     * ３）　@市場コード一覧チェック<BR>
     * 　@３－１）　@this.市場コード一覧 == nullであれば、<BR>
     * 　@　@「市場コードが未指定です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@３－２）　@this.市場コード一覧に下記の項目以外が設定されていたら、<BR>
     * 　@　@「市場コードが未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・"東京" <BR>
     * 　@　@　@・"大阪" <BR>
     * 　@　@　@・"名古屋" <BR>
     * 　@　@　@・"福岡" <BR>
     * 　@　@　@・"札幌" <BR>
     * 　@　@　@・"NNM" <BR>
     * 　@　@　@・"JASDAQ" <BR>
     * 　@　@　@・"JNX-PTS" <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * ４）　@取引区分一覧チェック<BR>
     * 　@４－１）　@this.取引区分一覧 == nullであれば、<BR>
     * 　@　@「取引区分が未指定です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00601<BR>
     * <BR>
     * 　@４－２）　@this.取引区分一覧に下記の項目以外が設定<BR>
     * 　@　@されていたら、「取引区分が存在しないコード値です。」の<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@・"現物買付注文"<BR>
     * 　@　@　@・"現物売付注文"<BR>
     * 　@　@　@・"新規買建注文"<BR>
     * 　@　@　@・"新規売建注文"<BR>
     * 　@　@　@・"買建返済注文"<BR>
     * 　@　@　@・"売建返済注文"<BR>
     * 　@　@　@・"現引注文"<BR>
     * 　@　@　@・"現渡注文"<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00602<BR>
     * <BR>
     * ５）　@弁済区分一覧チェック<BR>
     * 　@５－１）　@this.取引区分一覧に現物株式の取引区分(*1)のみ<BR>
     * 　@　@存在する場合、this.弁済区分一覧 != nullならば、<BR>
     * 　@　@「現物株式取引は弁済区分の指定不可」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02445<BR>
     * <BR>
     * 　@５－２）　@this.取引区分一覧に信用取引の取引区分(*2)が<BR>
     * 　@　@一つでも含まれる場合、this.弁済区分一覧 == nullならば、<BR>
     * 　@　@「信用取引は弁済区分の指定必須」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02447<BR>
     * <BR>
     * 　@５－３）　@this.弁済区分一覧 != nullの場合　@かつ<BR>
     * 　@　@this.弁済区分一覧に下記の項目以外が設定されていたら、<BR>
     * 　@　@「弁済区分の値が存在しないコード値です。」の<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@・"制度信用"<BR>
     * 　@　@　@・"一般信用"<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00629<BR>
     * 　@<BR>
     * ６）　@顧客コードチェック<BR>
     * 　@this.顧客コード != nullの場合、以下のチェックを行う。<BR>
     * 　@６－１）　@this.顧客コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@「顧客コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@・顧客コード != 数字 <BR>
     * 　@　@　@　@・顧客コード.length != 6 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00780<BR>
     * <BR>
     * (*1)現物株式の取引区分<BR>
     * 　@　@・"現物買付注文"<BR>
     * 　@　@・"現物売付注文"<BR>
     * <BR>
     * (*2)信用取引の取引区分<BR>
     * 　@　@・"新規買建注文"<BR>
     * 　@　@・"新規売建注文"<BR>
     * 　@　@・"買建返済注文"<BR>
     * 　@　@・"売建返済注文"<BR>
     * 　@　@・"現引注文"<BR>
     * 　@　@・"現渡注文"<BR>
     *<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 4469235C0167
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードチェック  
        //　@１－１）　@this.部店コード == nullの場合、  
        //　@　@　@　@　@「部店コードがnull」の例外をスローする。  
        if (this.branchCode == null || this.branchCode.length == 0) 
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        
        //　@１－２）　@this.部店コードの要素数分以下の処理を行う。  
        //　@　@１－２－１）　@this.部店コードが以下の条件に該当する場合、  
        //　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。  
        //　@　@　@　@　@　@　@・部店コード != 数字  
        //　@　@　@　@　@　@　@・部店コード.length != 3  
        for (int i = 0; i < this.branchCode.length; i++) 
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("部店コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
            }
        }
        
        //２）　@銘柄コードチェック  
        //　@this.銘柄コード != nullの場合、以下のチェックを行う。  
        //　@２－１）　@this.銘柄コードが以下の条件に該当する場合、  
        //　@　@　@　@「銘柄コードエラー」の例外をスローする。  
        //　@　@　@　@・銘柄コード != 数字  
        //　@　@　@　@・銘柄コード.length != 5  
        if (this.productCode != null) 
        {
            if (!WEB3StringTypeUtility.isDigit(this.productCode)
                || this.productCode.length() != 5)
            {
                log.debug("銘柄コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄コードの入力が不正です。");
            }
        }
        
        //３）　@市場コード一覧チェック 
        //　@３－１）　@this.市場コード一覧 == nullであれば、 
        //　@　@「市場コードが未指定です。」の例外をスローする。 
        if (this.marketList == null || this.marketList.length == 0) 
        {
            log.debug("市場コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "市場コードが未指定です。");
        }
        
        //　@３－２）　@this.市場コード一覧に下記の項目以外が設定されていたら、 
        //　@　@「市場コードが未定義の値」の例外をスローする。  
        //　@　@　@・"東京"  
        //　@　@　@・"大阪"  
        //　@　@　@・"名古屋"  
        //　@　@　@・"福岡"  
        //　@　@　@・"札幌"  
        //　@　@　@・"NNM"  
        //　@　@　@・"JASDAQ"  
        //　@　@　@・"JNX-PTS" 
        for (int i = 0; i < this.marketList.length; i++) 
        {
            if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketList[i])
                || WEB3MarketCodeDef.OSAKA.equals(this.marketList[i])
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketList[i])
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketList[i])
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketList[i])
                || WEB3MarketCodeDef.NNM.equals(this.marketList[i])
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketList[i])
                || WEB3MarketCodeDef.JNX_PTS.equals(this.marketList[i])))
            {
                log.debug("市場コードが存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "市場コードが存在しないコード値です。");
            }
        }
        
        //４）　@取引区分一覧チェック 
        //　@４－１）　@this.取引区分一覧 == nullであれば、 
        //　@　@「取引区分が未指定です。」の例外をスローする。 
        if (this.tradingTypeList == null || this.tradingTypeList.length == 0)
        {
            log.debug("取引区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00601,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引区分が未指定です。");
        }
        
        //　@４－２）　@this.取引区分一覧に下記の項目以外が設定 
        //　@　@されていたら、「取引区分が存在しないコード値です。」の 
        //　@　@例外をスローする。 
        //　@　@　@・"現物買付注文" 
        //　@　@　@・"現物売付注文" 
        //　@　@　@・"新規買建注文" 
        //　@　@　@・"新規売建注文" 
        //　@　@　@・"買建返済注文" 
        //　@　@　@・"売建返済注文" 
        //　@　@　@・"現引注文" 
        //　@　@　@・"現渡注文" 
        for (int i = 0; i < this.tradingTypeList.length; i++) 
        {
            if (!(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(this.tradingTypeList[i]))) 
            {
                log.debug("取引区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引区分が存在しないコード値です。");                
            }
        
        }
        //５）　@弁済区分一覧チェック 
        //　@５－１）　@this.取引区分一覧に現物株式の取引区分(*1)のみ 
        //　@　@存在する場合、this.弁済区分一覧 != nullならば、 
        //　@　@「現物株式取引は弁済区分の指定不可」の例外をスローする。
        boolean l_blnFlag = false;
        for (int i = 0; i < this.tradingTypeList.length; i++)
        {
            if (!(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(this.tradingTypeList[i])
                || String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(this.tradingTypeList[i])))                                       
            {
                l_blnFlag = true;
                break;
            }
        }
        if (!l_blnFlag)
        {
            if (this.repaymentList != null && this.repaymentList.length != 0)
            {
                log.debug("現物株式取引は弁済区分の指定不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02445,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "現物株式取引は弁済区分の指定不可。"); 
            }
        }
            
        //　@５－２）　@this.取引区分一覧に信用取引の取引区分(*2)が 
        //　@　@一つでも含まれる場合、this.弁済区分一覧 == nullならば、 
        //　@　@「信用取引は弁済区分の指定必須」の例外をスローする。 
        else
        {
            if (this.repaymentList == null || this.repaymentList.length == 0) 
            {
                log.debug("信用取引は弁済区分の指定必須。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02447,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "信用取引は弁済区分の指定必須。"); 
            }
        }
        
        //　@５－３）　@this.弁済区分一覧 != nullの場合　@かつ 
        //　@　@this.弁済区分一覧に下記の項目以外が設定されていたら、 
        //　@　@「弁済区分の値が存在しないコード値です。」の 
        //　@　@例外をスローする。 
        //　@　@　@・"制度信用" 
        //　@　@　@・"一般信用" 
        if (this.repaymentList != null && this.repaymentList.length != 0) 
        {
            for (int i = 0; i < this.repaymentList.length; i++) 
            {
                if (!WEB3MarginTradingDivDef.MARKET_MARGIN.equals(this.repaymentList[i])
                    && !WEB3MarginTradingDivDef.INSTITUTION_MARGIN.equals(this.repaymentList[i])) 
                {
                    log.debug("弁済区分の値が存在しないコード値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00629,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "弁済区分の値が存在しないコード値です。"); 
                }
            }
        }
         
        //６）　@顧客コードチェック 
        //　@this.顧客コード != nullの場合、以下のチェックを行う。 
        //　@６－１）　@this.顧客コードが以下の条件に該当する場合、  
        //　@　@　@　@「顧客コードエラー」の例外をスローする。  
        //　@　@　@　@・顧客コード != 数字  
        //　@　@　@　@・顧客コード.length != 6  
        if (this.accountCode != null) 
        {
            if (!WEB3StringTypeUtility.isDigit(this.accountCode)
                || this.accountCode.length() != 6)
            {
                log.debug("顧客コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
