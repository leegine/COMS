head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformHostReqOrderNumberManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡管理識別コード採番サービスImpl(WEB3InformHostReqOrderNumberManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.inform.data.InformCtrlRequestNumberParams;
import webbroker3.inform.data.InformCtrlRequestNumberRow;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (連絡管理識別コード採番サービスImpl)<BR>
 * 連絡管理識別コード採番サービス実装クラス<BR>
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3InformHostReqOrderNumberManageServiceImpl
    implements WEB3InformHostReqOrderNumberManageService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformHostReqOrderNumberManageServiceImpl.class);

    /**
     * @@roseuid 41EE632B034B
     */
    public WEB3InformHostReqOrderNumberManageServiceImpl()
    {

    }

    /**
     * (get新規識別コード)<BR>
     * 各種連絡の識別コードを自動採番する。<BR>
     * <BR>
     * 識別コードのコード体系は以下の通り。 <BR>
     * <BR>
     * 　@・１～８桁目（index=0～7）：　@日付セクション（yyyyMMdd） <BR>
     * 　@・９～１３桁目（index=8～12）：　@5桁の通番 <BR>
     * 　@（証券会社、連絡種別毎に、各種連絡識別コードテーブルに最終採番値を保存） <BR>
     * <BR>
     * １）　@日付セクション（”yyyyMMdd”取得） <BR>
     * 　@　@TradingSystem.getBizDate() を”yyyyMMdd”にフォーマットした文字列を取得する。 <BR>
     * <BR>
     * ２）　@識別コードテーブルを更新する。 <BR>
     * <BR>
     * 　@各種連絡識別コードテーブルを以下の条件で検索する。 <BR>
     * <BR>
     * 　@　@[条件] <BR>
     * 　@　@証券会社コード = 引数.証券会社コード <BR>
     * 　@　@連絡種別 = 引数.連絡種別 <BR>
     * <BR>
     * 　@－該当データがない場合は、以下の通りレコードを新規登録（insert）し、識別コード最大値を返却する。 <BR>
     * <BR>
     * 　@　@証券会社コード = 引数.証券会社コード <BR>
     * 　@　@連絡種別 = 引数.連絡種別 <BR>
     * 　@　@識別コード最大値 = yyyyMMdd + "00001" <BR>
     * 　@　@作成日時 = TradingSystem.getSystemTimestamp() <BR>
     * 　@　@更新日時 = TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * 　@－該当データがある場合は、以下の通りレコードを更新（update）し、識別コード最大値を返却する。<BR> 
     * <BR>
     * 　@　@識別コード最大値 = yyyyMMdd + （※１） <BR>
     * 　@　@更新日時 = TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * 　@　@（※１）　@識別コード最大値（通番部分）の計算 <BR>
     * <BR>
     * 　@　@○　@既存値と同一日の場合 <BR>
     * 　@　@　@１）で取得した日付セクション文字列と、既存値の日付セクション文字列が同じ場合、 <BR>
     * 　@　@　@既存値の通番＋１の数字を5桁の文字列に編集する。 <BR>
     * <BR>
     * 　@　@○　@既存値と同一日でない場合 <BR>
     * 　@　@　@"00001"を編集する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strInformDiv - (連絡種別)<BR>
     * 連絡種別
     * @@return String
     * @@roseuid 41BD568A02E3
     */
    public String getNewOrderRequestCode(
        String l_strInstitutionCode,
        String l_strInformDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewOrderRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        //１）日付セクション（”yyyyMMdd”取得）
        //TradingSystem.getBizDate() を”yyyyMMdd”にフォーマットした文字列を取得する。
        TradingSystem l_tradingSystem = GtlUtils.getTradingSystem();
        Date l_datBizDate = l_tradingSystem.getBizDate();
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");

        // 識別コードを自動採番する
        String l_strRequestNumber = null;

        try
        {    
            //２）　@識別コードテーブルを更新する。 <BR>
            // 各種連絡識別コードテーブルを以下の条件で検索する。 <BR>
            // [条件] <BR>
            //　@  証券会社コード = 引数.証券会社コード <BR>
            //  　@連絡種別 = 引数.連絡種別 <BR>
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_code = ? ");
            l_strWhere.append(" and inform_div = ? ");
    
            Object[] l_objWhere = {
                l_strInstitutionCode,
                l_strInformDiv};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformCtrlRequestNumberRow.TYPE,
                l_strWhere.toString(),
                null,
                "for update",
                l_objWhere);
    
            int l_intListRecordCnt = 0;
            if (l_lisRecords != null)
            {
                l_intListRecordCnt = l_lisRecords.size();
            }
            log.debug("==========検索の結果:==========" + l_intListRecordCnt);

            //－該当データがない場合は、以下の通りレコードを新規登録（insert）し、
            //  識別コード最大値を返却する。<BR>
            if (l_intListRecordCnt == 0)
            {
                InformCtrlRequestNumberParams l_requestNumberParams = new InformCtrlRequestNumberParams();

                //　@証券会社コード = 引数.証券会社コード <BR>
                l_requestNumberParams.setInstitutionCode(l_strInstitutionCode);

                //　@連絡種別 = 引数.連絡種別 <BR>
                l_requestNumberParams.setInformDiv(l_strInformDiv);

                //　@識別コード最大値 = yyyyMMdd + "00001" <BR>
                l_strRequestNumber = l_strBizDate + "00001";
                l_requestNumberParams.setLastInfoCtrlRequestNumber(l_strRequestNumber);

                //　@作成日時 = TradingSystem.getSystemTimestamp() <BR>
                l_requestNumberParams.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());

                //　@更新日時 = TradingSystem.getSystemTimestamp() <BR>
                l_requestNumberParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

                l_queryProcessor.doInsertQuery(l_requestNumberParams);
            }
            //－該当データがある場合は、以下の通りレコードを更新（update）し、
            //  識別コード最大値を返却する。<BR> 
            else
            {
                InformCtrlRequestNumberRow l_requestNumberRow = (InformCtrlRequestNumberRow)l_lisRecords.get(0);
                InformCtrlRequestNumberParams l_requestNumberParams = new InformCtrlRequestNumberParams(l_requestNumberRow);

                // 識別コードのコード体系は以下の通り。 <BR>
                // ・１～８桁目（index=0～7）：　@日付セクション（yyyyMMdd） <BR>
                String l_strDateSection = l_requestNumberParams.getLastInfoCtrlRequestNumber().substring(0, 8);
                log.debug("識別コードのコード:" + l_requestNumberParams.getLastInfoCtrlRequestNumber());
                log.debug("l_strDateSection:" + l_strDateSection);
 
                // ・９～１３桁目（index=8～12）：　@5桁の通番 <BR>
                String l_strLastNo = l_requestNumberParams.getLastInfoCtrlRequestNumber().substring(8, 13);
                long l_lngLastNo = Long.parseLong(l_strLastNo) + 1;
                log.debug("l_strLastNo:" + l_strLastNo);
 
                //　@識別コード最大値 = yyyyMMdd + （※１） <BR>
                // （※１）　@識別コード最大値（通番部分）の計算 <BR>
                if (l_strBizDate.equals(l_strDateSection))
                {
                    log.debug("既存値と同一日:" + l_strBizDate);
                    //○ 既存値と同一日の場合 <BR>
                    //　@ １）で取得した日付セクション文字列と、既存値の日付セクション文字列が同じ場合、<BR>
                    //　@ 既存値の通番＋１の数字を5桁の文字列に編集する。 <BR>
                    l_strRequestNumber = l_strBizDate + WEB3StringTypeUtility.formatNumber(l_lngLastNo, 5);
                    l_requestNumberParams.setLastInfoCtrlRequestNumber(l_strRequestNumber);
                }
                else
                {
                    log.debug("既存値と同一日でない:" + l_strBizDate);
                    // ○ 既存値と同一日でない場合、"00001"を編集する。
                    l_strRequestNumber = l_strBizDate + "00001";
                    l_requestNumberParams.setLastInfoCtrlRequestNumber(l_strRequestNumber);
                }

                //　@更新日時 = TradingSystem.getSystemTimestamp() <BR>
                l_requestNumberParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_requestNumberParams);
            }
        }
        catch (DataNetworkException l_dnEx)
        {
            log.error("DBへのアクセスに失敗しました。", l_dnEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dnEx.getMessage(),
                l_dnEx);
        }
        catch (DataQueryException l_dqEx)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqEx.getMessage(),
                l_dqEx);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strRequestNumber;
    }
}
@
