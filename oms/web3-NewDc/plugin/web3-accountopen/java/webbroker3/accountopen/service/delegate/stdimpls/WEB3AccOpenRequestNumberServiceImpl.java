head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRequestNumberServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設識別コード採番サービスImpl(WEB3AccOpenRequestNumberServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.accountopen.data.AccOpenRequestNumberParams;
import webbroker3.accountopen.data.AccOpenRequestNumberRow;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRequestNumberService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設識別コード採番サービスImpl)<BR>
 * 口座開設識別コード採番サービス実装クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AccOpenRequestNumberServiceImpl implements WEB3AccOpenRequestNumberService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenRequestNumberServiceImpl.class);

    /**
     * @@roseuid 41B45E730251
     */
    public WEB3AccOpenRequestNumberServiceImpl() 
    {
     
    }
    
    /**
     * (get新規識別コード)<BR>
     * 口座開設見込客の識別コードを自動採番する。<BR>
     * <BR>
     * 識別コードのコード体系は以下の通り。<BR>
     * <BR>
     * 　@・１～８桁目（index=0～7）：　@日付セクション（yyyyMMdd）<BR>
     * 　@・９～１３桁目（index=8～12）：　@5桁の通番<BR>
     * 　@（証券会社毎，口座開設識別コードテーブルに最終採番値を保存）<BR>
     * <BR>
     * １）　@日付セクション（”yyyyMMdd”取得）<BR>
     * 　@　@TradingSystem.getBizDate() を”yyyyMMdd”にフォーマットした<BR>
     * 文字列を取得する。<BR>
     * <BR>
     * ２）　@識別コードテーブルを更新する。<BR>
     * <BR>
     * 　@口座開設識別コードテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@口座開設識別コード.証券会社コード = 証券会社コード<BR>
     * <BR>
     * 　@－該当データがない場合は、以下の通りレコードを新規登録（insert）し、<BR>
     * 識別コード最大値を返却する。<BR>
     * <BR>
     * 　@　@口座開設識別コード.証券会社コード = 証券会社コード<BR>
     * 　@　@口座開設識別コード.識別コード最大値 = yyyyMMdd + "00001"<BR>
     * 　@　@口座開設識別コード.作成日時 = TradingSystem.getSystemTimestamp()<BR>
     * 　@　@口座開設識別コード.更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@－該当データがある場合は、以下の通りレコードを更新（update）し、<BR>
     * 識別コード最大値を返却する。<BR>
     * <BR>
     * 　@　@口座開設識別コード.識別コード最大値 = yyyyMMdd + （※１）<BR>
     * 　@　@口座開設識別コード.更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@（※１）　@識別コード最大値（通番部分）の計算<BR>
     * <BR>
     * 　@　@○　@既存値と同一日の場合<BR>
     * 　@　@　@１）で取得した日付セクション文字列と、既存値の日付セクション文字列が<BR>
     * 同じ場合、<BR>
     * 　@　@　@既存値の通番＋１の数字を5桁の文字列に編集する。<BR>
     * <BR>
     * 　@　@○　@既存値と同一日でない場合<BR>
     * 　@　@　@"00001"を編集する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード。
     * @@return String
     * @@roseuid 41871B5E0080
     */
    public String getNewRequestNumber(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewRequestNumber(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);        

        try
        {
            //１）　@日付セクション（”yyyyMMdd”取得） 
            //TradingSystem.getBizDate() を”yyyyMMdd”にフォーマットした文字列を取得する
            TradingSystem l_tradingSystem = GtlUtils.getTradingSystem();
            String l_strDate = WEB3DateUtility.formatDate(l_tradingSystem.getBizDate(), "yyyyMMdd");
        
            //２）　@識別コードテーブルを更新する。
            String l_strWhere = " institution_code = ? ";
                
            Object[] l_obj = {l_strInstitutionCode};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisRequestNumberRowList = null;

            l_lisRequestNumberRowList = l_queryProcessor.doFindAllQuery(
                AccOpenRequestNumberRow.TYPE, 
                l_strWhere,
                " FOR UPDATE ", 
                l_obj);//DataNetworkException, DataQueryException
                    
            int l_intRowCnt = 0;
            
            if (l_lisRequestNumberRowList != null)
            {
                l_intRowCnt = l_lisRequestNumberRowList.size();
            }
            
            AccOpenRequestNumberRow l_row = null;
            
            if (l_intRowCnt == 1)
            {
                l_row = (AccOpenRequestNumberRow)l_lisRequestNumberRowList.get(0);
            }
            else if (l_intRowCnt > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
           
            String l_strLasterRequestNo = "";
           
            //該当データがない場合は、以下の通りレコードを新規登録（insert）し、
            //識別コード最大値を返却する。
            if (l_row == null)
            {
                log.debug("l_row == null");
                
                AccOpenRequestNumberParams l_params = new AccOpenRequestNumberParams();
                //口座開設識別コード.証券会社コード = 証券会社コード
                l_params.setInstitutionCode(l_strInstitutionCode);
               
                l_strLasterRequestNo = l_strDate + "00001";
               
                //口座開設識別コード.識別コード最大値 = yyyyMMdd + "00001"
                l_params.setLastAccOpenRequestNumber(l_strLasterRequestNo);
               
                //口座開設識別コード.作成日時 = TradingSystem.getSystemTimestamp()
                l_params.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
               
                //口座開設識別コード.更新日時 = TradingSystem.getSystemTimestamp()
                l_params.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());
               
                l_queryProcessor.doInsertQuery(l_params);//DataNetworkException, DataQueryException
            }
            //該当データがある場合は、以下の通りレコードを更新（update）し、<BR>
            //識別コード最大値を返却する
            else
            {
                log.debug("l_row != null");
                
                AccOpenRequestNumberParams l_params = new AccOpenRequestNumberParams(l_row);
               
                String l_strLasterRequestNoBefore = l_params.getLastAccOpenRequestNumber();
                String l_strDateDB = l_strLasterRequestNoBefore.substring(0,8);
               
                if (l_strDateDB.equals(l_strDate))
                {
                    log.debug("same day");
                    
                    String l_strNo = l_strLasterRequestNoBefore.substring(8);
                    l_strLasterRequestNo = 
                        l_strDateDB + WEB3StringTypeUtility.formatNumber(Long.parseLong(l_strNo) + 1, 5);
                }
                else
                {
                    log.debug("not same day");
                    
                    l_strLasterRequestNo = l_strDate + "00001";
                }
                            
                //口座開設識別コード.識別コード最大値 = yyyyMMdd + （※１）
                l_params.setLastAccOpenRequestNumber(l_strLasterRequestNo);
               
                //口座開設識別コード.更新日時 = TradingSystem.getSystemTimestamp()
                l_params.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());
               
                l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException, DataQueryException
            }
           
            log.exiting(STR_METHOD_NAME);
            return l_strLasterRequestNo;
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
    }
}
@
