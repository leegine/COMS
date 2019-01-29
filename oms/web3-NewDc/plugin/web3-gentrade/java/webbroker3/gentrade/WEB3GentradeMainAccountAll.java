head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMainAccountAll.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客（全部店分）(WEB3GentradeMainAccountAll.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/14 凌建平 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * (顧客（全部店分）) <BR>
 * 顧客（全部店分）クラス<BR>
 * <BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3GentradeMainAccountAll
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3GentradeMainAccountAll.class);

    /**
     * 顧客Rowオブジェクト。<BR>
     */
    private MainAccountAllRow mainAccountAllRow;

    /**
     * コンストラクタ<BR>
     * <BR>
     * 顧客マスタ（全部店分）テーブルより<BR>
     * 証券会社コード、部店コード、顧客コード、顧客コードCDに該当する<BR>
     * 顧客（全部店分）Rowオブジェクトを取得する<BR>
     * <BR>
     * @@param l_strCompCode 証券会社コード
     * @@param l_strBrCode 部店コード
     * @@param l_strCustCode 顧客コード
     * @@param l_strCustCodeCD 顧客コードCD
     * @@throws WEB3BaseException
     * @@roseuid 403496F0022B
     */
    public WEB3GentradeMainAccountAll(
        String l_strCompCode,
        String l_strBrCode,
        String l_strCustCode,
        String l_strCustCodeCD)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeMainAccountAll(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        //顧客マスタ（全部店分）テーブルより 
        //証券会社コード、部店コード、顧客コードに該当する 
        //顧客（全部店分）Rowオブジェクトを取得する  
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" comp_code = ? ");
        l_sbWhere.append(" and br_code = ? ");
        l_sbWhere.append(" and cust_code = ? ");
        l_sbWhere.append(" and cust_code_cd = ? ");

        Object[] l_obWhere = new Object[]
        {
            l_strCompCode,
            l_strBrCode,
            l_strCustCode,
            l_strCustCodeCD
        };

        List l_lstRecords = null;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException l_dexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dexp.getMessage(),
                l_dexp);
        }

        if (l_lstRecords != null && l_lstRecords.size() != 0)
        {
            this.mainAccountAllRow = (MainAccountAllRow) l_lstRecords.get(0);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 顧客マスタ（全部店分）テーブルより<BR>
     * 証券会社コード、部店コード、顧客コードに該当する<BR>
     * 顧客（全部店分）Rowオブジェクトを取得する<BR>
     * <BR>
     * @@param l_strCompCode 証券会社コード
     * @@param l_strBrCode 部店コード
     * @@param l_strCustCode 顧客コード
     * @@throws WEB3BaseException 
     * @@roseuid 403496F000E3
     */
    public WEB3GentradeMainAccountAll(
        String l_strCompCode,
        String l_strBrCode,
        String l_strCustCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeMainAccountAll(String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        //顧客マスタ（全部店分）テーブルより 
        //証券会社コード、部店コード、顧客コードに該当する 
        //顧客（全部店分）Rowオブジェクトを取得する  
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" comp_code = ? ");
        l_sbWhere.append(" and br_code = ? ");
        l_sbWhere.append(" and cust_code = ? ");

        Object[] l_obWhere = new Object[]
        {
            l_strCompCode,
            l_strBrCode,
            l_strCustCode
        };

        List l_lstRecords = null;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException l_dexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dexp.getMessage(),
                l_dexp);
        }

        if (l_lstRecords != null && l_lstRecords.size() != 0)
        {
            this.mainAccountAllRow = (MainAccountAllRow) l_lstRecords.get(0);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get顧客（全部店分）行)<BR>
     *  <BR>
     * this.顧客（全部店分）Rowオブジェクトを返却する。<BR>
     *  <BR>
     * @@return MainAccountAllRow
     */
    public MainAccountAllRow getMainAccountAllRow()
    {
        return this.mainAccountAllRow;
    }
}
@
