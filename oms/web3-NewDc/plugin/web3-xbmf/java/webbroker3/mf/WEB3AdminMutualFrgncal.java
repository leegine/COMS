head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncal.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信海外市場カレンダ(WEB3AdminMutualFrgncal)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 黄建 (中訊) 新規作成
Revesion History : 2009/01/23 王志葵 (中訊) 仕様変更モデルNo.639
*/
package webbroker3.mf;
import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.data.MutualFundFrgncalRow;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信海外市場カレンダ<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualFrgncal 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualFrgncal.class);

    /**
     * (is休日)<BR>
     * 指定された日付が海外市場の休日かの判定を行う。<BR>
     * <BR>
     * １）　@投信海外市場カレンダテーブルを検索し、発注日に該当するレコード<BR>
     * 　@がないかチェックする。<BR>
     * 　@［検索条件］<BR>
     * 　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@AND 銘柄コード = 引数.銘柄コード<BR>
     * 　@　@AND 休日 = 引数.日付<BR>
     * <BR>
     * ２）　@該当するレコードが存在する場合は true を、そうでない場合は false を<BR>
     * 　@返す。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strMutualProductCode - 銘柄コード
     * @@param l_tsBizDate - 日付
     * @@return boolean
     * @@roseuid 40BB0DC90137
     */
    public boolean isHoliday(
        String l_strInstitutionCode,
        String l_strMutualProductCode,
        Timestamp l_tsBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isHoliday(String l_strInstitutionCode, " +
            "String l_strMutualProductCode, " +
            "Timestamp l_tsBizDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || 
            "".equals(l_strInstitutionCode) || 
            l_strMutualProductCode == null ||
            "".equals(l_strMutualProductCode) ||
            l_tsBizDate == null) 
        {
            log.debug("パラメータNull出来ない with" +
                " (証券会社コード)l_strInstitutionCode =" + 
                    l_strInstitutionCode + 
                " and (銘柄コード)l_strMutualProductCode =" + 
                    l_strMutualProductCode + 
                " and (日付)l_tsBizDate =" + 
                    l_tsBizDate);        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        try
        {
            //１）　@投信海外市場カレンダテーブルを検索し、
            //発注日に該当するレコードがないかチェックする
            //処理対象レコード取得
            QueryProcessor l_processorObject = Processors.getDefaultProcessor();
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード   
            l_sbWhere.append(" and product_code = ? "); //銘柄コード
            l_sbWhere.append(" and holiday = ? ");      //日付
            Object[] l_objMutualFrgncalWhere =
                { l_strInstitutionCode, l_strMutualProductCode, l_tsBizDate };
            /*検索*/
            List l_lisMFFrgncalRows = 
                l_processorObject.doFindAllQuery(
                    MutualFundFrgncalRow.TYPE,
                    l_sbWhere.toString(),
                    l_objMutualFrgncalWhere); 
                    
            // ２）該当するレコードが存在する場合は true を、
             //そうでない場合は false を返す                 
            if(l_lisMFFrgncalRows.isEmpty())
            {  
                return false;   
            }
        } 
        catch (DataNetworkException l_ex) 
        {
            log.error("当投信海外市場カレンダテーブルを検索し時," +
                "DBへのアクセスに失敗しました, ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("当投信海外市場カレンダテーブルを検索し時, " +
                "DBへのアクセスに失敗しました ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;        
    }

    /**
     * （static method)<BR>
     * (get休日一覧)<BR>
     * <BR>
     * 該当銘柄の休日情報を指定月分だけ取得し、返却する。<BR>
     * <BR>
     * １）　@引数:検索条件データコンテナ、検索条件文字列に、以下の条件を<BR>
     * 　@追加する。<BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@[並び替え］<BR>
     * 　@　@　@休日<BR>
     * <BR>
     * ２)　@投信海外市場カレンダーテーブルを以下の条件で検索する。<BR>
     * <BR>
     * ３)　@１)の検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strSearchCondCharacterString - 検索条件文字列
     * @@param l_strSearchCondDataContainer - 検索条件データコンテナ
     * @@return List
     * @@roseuid 40D8281502F6
     */
    public static List getHolidayList(
        String l_strInstitutionCode,
        String l_strSearchCondCharacterString,
        String[] l_strSearchCondDataContainer) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getHolidayList(String l_strInstitutionCode," +
            "String l_strSearchCondCharacterString, " +
            "String[ ] l_strSearchCondDataContainer) ";
        if (l_strInstitutionCode == null || l_strInstitutionCode.length() == 0)
        {
            log.debug(" パラメータNull出来ない");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminMutualFrgncal" + "." + STR_METHOD_NAME);
        }    
        log.entering(STR_METHOD_NAME);
        List l_lisMFFrgncalRows = null;     //の検索結果を返却する
        try 
        {
            //１）　@引数:検索条件データコンテナ、検索条件文字列に、以下の条件を
            
            //処理対象レコード取得
            QueryProcessor l_processorObject = 
                Processors.getDefaultProcessor();
            
            //休日並び替えs
            String l_strSort = " holiday ";        
          
            if (l_strSearchCondCharacterString  != null &&  
                !"".equals(l_strSearchCondCharacterString)) 
            {
                l_strSearchCondCharacterString =
                    l_strSearchCondCharacterString + " and institution_code = ? ";
            }
            else
            {
                l_strSearchCondCharacterString = "institution_code = ?";   
            
            }
           
            //結果を返却する
            String[] l_searchParams = null; 
            int l_intSearchCondDataContainer = 0;
            if (l_strSearchCondDataContainer != null)
            {
                l_intSearchCondDataContainer = 
                    l_strSearchCondDataContainer.length;
                l_searchParams = 
                    new String[l_intSearchCondDataContainer + 1];
                for (int i = 0; i < l_intSearchCondDataContainer; i++)
                {
                    l_searchParams[i] = l_strSearchCondDataContainer[i];
                }
                l_searchParams[l_intSearchCondDataContainer] = l_strInstitutionCode; 
            }
            else
            {
                l_searchParams = new String[1];
                l_searchParams[0] = l_strInstitutionCode;
            }
            //２)　@投信海外市場カレンダーテーブルを以下の条件で検索する。
            /*検索 Begin*/
            l_lisMFFrgncalRows =
                l_processorObject.doFindAllQuery(
                    MutualFundFrgncalRow.TYPE,
                    l_strSearchCondCharacterString,
                    l_strSort,
                    null,
                    l_searchParams);
        }
        catch (DataNetworkException l_ex) 
        {
            log.error("当投信海外市場カレンダー検索時") ;
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMutualFrgncal.class.getName() + "." + STR_METHOD_NAME,   
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("当投信海外市場カレンダー検索時");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminMutualFrgncal.class.getName() + "." + STR_METHOD_NAME,   
                l_ex.getMessage(),
                l_ex);
        }
        
       // ３)　@１)の検索結果を返却する。
        log.exiting(STR_METHOD_NAME); 
        return l_lisMFFrgncalRows;
    }
}

@
