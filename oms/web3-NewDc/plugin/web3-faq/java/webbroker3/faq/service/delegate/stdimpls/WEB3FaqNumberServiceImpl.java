head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqNumberServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ管理問合せコード採番サービスImpl(WEB3FaqNumberServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.faq.data.FaqNumberDao;
import webbroker3.faq.data.FaqNumberParams;
import webbroker3.faq.data.FaqNumberRow;
import webbroker3.faq.service.delegate.WEB3FaqNumberService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (問合せ管理問合せコード採番サービスImpl)<BR>
 * 問合せ管理問合せコード採番サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3FaqNumberServiceImpl implements WEB3FaqNumberService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqNumberServiceImpl.class);
           
    /**
     * @@roseuid 41C25C8C008C
     */
    public WEB3FaqNumberServiceImpl() 
    {
     
    }
    
    /**
     * (get新規問合せコード)<BR>
     * 問合せコードを採番する。<BR>
     * <BR>
     * 問合せコードのコード体系は以下の通り。<BR>
     * <BR>
     * 　@・１～８桁目（index=0～7）：　@日付セクション（yyyyMMdd）<BR>
     * 　@・９～１３桁目（index=8～12）：　@5桁の通番<BR>
     * 　@（証券会社毎，問合せコードコードテーブルに最終採番値を保存）<BR>
     * <BR>
     * １）　@日付セクション（”yyyyMMdd”取得）<BR>
     * 　@　@TradingSystem.getBizDate() を”yyyyMMdd”にフォーマットした<BR>
     * 文字列を取得する。<BR>
     * <BR>
     * ２）　@問合せコードテーブルを更新する。<BR>
     * <BR>
     * 　@問合せコードテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@問合せコード.証券会社コード = 証券会社コード<BR>
     * <BR>
     * 　@－該当データがない場合は、以下の通りレコードを新規登録（insert）し、<BR>
     * 識別コード最大値を返却する。<BR>
     * <BR>
     * 　@　@問合せコード.証券会社コード = 証券会社コード<BR>
     * 　@　@問合せコード.識別コード最大値 = yyyyMMdd + "00001"<BR>
     * 　@　@問合せコード.作成日時 = TradingSystem.getSystemTimestamp()<BR>
     * 　@　@問合せコード.更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@－該当データがある場合は、以下の通りレコードを更新（update）し、<BR>
     * 問合せコード最大値を返却する。<BR>
     * <BR>
     * 　@　@問合せコード.問合せコード最大値 = yyyyMMdd + （※１）<BR>
     * 　@　@問合せコード.更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@（※１）　@問合せコード最大値（通番部分）の計算<BR>
     * <BR>
     * 　@　@○　@既存値と同一日の場合<BR>
     * 　@　@　@１）で取得した日付セクション文字列と、既存値の日付セクション文字列が<BR>
     * 同じ場合、<BR>
     * 　@　@　@既存値の通番＋１の数字を5桁の文字列に編集する。<BR>
     * <BR>
     * 　@　@○　@既存値と同一日でない場合<BR>
     * 　@　@"00001"を編集する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return String
     * @@roseuid 41ABF27902F3
     */
    public String getNewFaqNumber(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewFaqNumber(String)";
        log.entering(STR_METHOD_NAME);        
        
        //日付セクション（”yyyyMMdd”取得） 
        //TradingSystem.getBizDate() を”yyyyMMdd”にフォーマットした文字列を取得する。
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        String l_strBizDateYMD = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        List l_lisFaqNumberParams = null;
        FaqNumberParams l_faqNumberParams = null;
        try
        {   
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            try
            {
                //問合せコードテーブルを以下の条件で検索する。
                //[条件]
                //問合せコード.証券会社コード = 証券会社コード 
                String l_strQuery = " institution_code = ? ";
                Object[] l_objContainer = {l_strInstitutionCode};

                l_lisFaqNumberParams = l_queryProcessor.doFindAllQuery(
                        FaqNumberRow.TYPE, 
                        l_strQuery, 
                        null, 
                        "for update", 
                        l_objContainer);                                
            }
            catch (DataFindException l_ex)
            {
                //該当データがない場合
                log.debug("該当データがない。[証券会社コード] = " + l_strInstitutionCode); 
            }
                        
            if (l_lisFaqNumberParams == null || l_lisFaqNumberParams.isEmpty())
            {
                //該当データがない場合は、以下の通りレコードを新規登録（insert）し、
                //識別コード最大値を返却する。
                
                l_faqNumberParams = new FaqNumberParams();
                
                //問合せコード.証券会社コード = 証券会社コード
                l_faqNumberParams.setInstitutionCode(l_strInstitutionCode);
                
                //問合せコード.識別コード最大値 = yyyyMMdd + "00001"
                l_faqNumberParams.setLastFaqNumber(l_strBizDateYMD + "00001");

                //問合せコード.作成日時 = TradingSystem.getSystemTimestamp()
                l_faqNumberParams.setCreatedTimestamp(l_tsProcessDate);
                 
                //問合せコード.更新日時 = TradingSystem.getSystemTimestamp()
                l_faqNumberParams.setLastUpdatedTimestamp(l_tsProcessDate);
                
                l_queryProcessor.doInsertQuery(l_faqNumberParams);
            }
            else
            {
                l_faqNumberParams = (FaqNumberParams)l_lisFaqNumberParams.get(0);
                
                //該当データがある場合は、以下の通りレコードを更新（update）し、
                //問合せコード最大値を返却する。                
                DecimalFormat df = new DecimalFormat("00000");
                
                //問合せコード最大値（通番部分）の計算
                String l_strMaxNumber = null;
                String l_strLastFaqNumber = l_faqNumberParams.getLastFaqNumber();
                
                //日付セクション文字列
                if (l_strLastFaqNumber.length() < 8)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + "." + STR_METHOD_NAME); 
                }
                String l_strDateYMD = l_strLastFaqNumber.substring(0, 8);
                if (l_strBizDateYMD.equals(l_strDateYMD))
                {
                    // ○　@既存値と同一日の場合
                    //１）で取得した日付セクション文字列と、既存値の日付セクション文字列が同じ場合、
                    //既存値の通番＋１の数字を5桁の文字列に編集する。
                    
                    String l_strNumber = l_strLastFaqNumber.substring(8);
                    int l_intNumber;
                    try
                    {
                        l_intNumber = df.parse(l_strNumber).intValue();
                        l_strMaxNumber = df.format(++l_intNumber);
                    }
                    catch (ParseException e)
                    {
                        log.debug("問合せコード最大値（通番部分）のフォーマットエラー。[通番部分] = " + l_strNumber);
                        l_strMaxNumber = "00001";
                    }
                }
                else
                {
                    // ○　@既存値と同一日でない場合 
                    //"00001"を編集する。 
                    l_strMaxNumber = "00001";
                }
                
                l_faqNumberParams = new FaqNumberParams(l_faqNumberParams);
                
                //問合せコード.問合せコード最大値 = yyyyMMdd + 通番部分
                l_faqNumberParams.setLastFaqNumber(l_strBizDateYMD + l_strMaxNumber);
                
                //問合せコード.更新日時 = TradingSystem.getSystemTimestamp()
                l_faqNumberParams.setLastUpdatedTimestamp(l_tsProcessDate);
                
                l_queryProcessor.doUpdateQuery(l_faqNumberParams);
            }

        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_faqNumberParams.getLastFaqNumber();
    }   

}
@
