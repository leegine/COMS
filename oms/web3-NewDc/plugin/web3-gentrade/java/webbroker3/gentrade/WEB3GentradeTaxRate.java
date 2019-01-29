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
filename	WEB3GentradeTaxRate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税率クラス(WEB3GentradeTaxRate.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.util.WEB3LogUtility;

/**
 * （税率）<BR>
 *<BR>
 * 各種税率を表現する。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public class WEB3GentradeTaxRate implements WEB3GentradeTaxType
{

    /**
     * 日付のフォーマッタ。<BR>
     */
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * where 条件：証券会社コードを指定する場合<BR>
     */
    private final static String WHERE_CLAUSE_1 = 
        "institution_code=? and tax_type=? and (appli_start_date<=? and appli_end_date>=?)";

    /**
     * where 条件：証券会社コードを指定しない場合<BR>
     */
    private final static String WHERE_CLAUSE_2 = 
        "tax_type=? and (appli_start_date<=? and appli_end_date>=?)";

    /**
     * エラーメッセージ：税率テーブルに該当するデータが重複しています<BR>
     */
    private final static String ERROR_MESSAGE_1 = 
        "税率テーブルに該当するデータがありません。";

    /**
     * エラーメッセージ：税率テーブルに該当するデータが重複しています<BR>
     */
    private final static String ERROR_MESSAGE_2 = 
        "税率テーブルに該当するデータが重複しています。";

    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeTaxRate.class);

    /**
     * 税金の種類。<BR>
     */
    private String taxType;

    /**
     * 税種類に対する税率（％）。<BR>
     */
    private double taxRate;

    /**
     * 証券会社コード。<BR>
     */
    private String institutionCode;

    /**
     * 発注日。<BR>
     */
    private Timestamp orderBizDate;

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_strInstitutuinCode 証券会社コード
     * @@param l_strTaxType 税種類
     * @@param l_tsOrderBizDate 発注日
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GentradeTaxRate(String l_strInstitutionCode, String l_strTaxType, Timestamp l_tsOrderBizDate) throws WEB3BaseException
    {
        institutionCode = l_strInstitutionCode;
        taxType = l_strTaxType;
        orderBizDate = l_tsOrderBizDate;

        final String STR_METHOD_NAME = "WEB3GentradeTaxRate(String, String, Timestamp)";

        log.entering(STR_METHOD_NAME);

        List l_list = null;
        int l_intSize = 0;
        TaxRateTableRow l_row = null;

        try
        {
            SimpleDateFormat l_formatter = GtlUtils.getThreadSafeSimpleDateFormat(DATE_FORMAT);
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_list = l_qp.doFindAllQuery(
                TaxRateTableRow.TYPE,
                WHERE_CLAUSE_1,
                new Object[] { institutionCode, taxType, l_formatter.format(orderBizDate), l_formatter.format(orderBizDate) });
        }
        catch (DataQueryException exp)
        {
            // DBアクセスエラー
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                exp.getMessage(), exp);
        }
        catch (DataNetworkException exp)
        {
            // DBアクセスエラー
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                exp.getMessage(), exp);
        }

        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // 該当するデータが無い場合
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                ERROR_MESSAGE_1);
        }
        else if (l_intSize == 1)
        {
            l_row = (TaxRateTableRow)l_list.get(0);
            log.debug("証券会社コード：[" + l_row.getInstitutionCode() + "]");
            log.debug("税種類：[" + l_row.getTaxType() + "]");
            log.debug("適用開始年月日：[" + l_row.getAppliStartDate().toString() + "]");
            log.debug("適用終了年月日：[" + l_row.getAppliEndDate().toString() + "]");
            log.debug("税率：[" + l_row.getTaxRate() + "]");
        }
        else
        {
            // 該当するデータが重複している場合
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                this.getClass().getName() + "." + STR_METHOD_NAME,  
                ERROR_MESSAGE_2);
        }
        taxRate = l_row.getTaxRate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * コンストラクタ。<BR>
     *<BR>
     * @@param l_strTaxType 税種類
     * @@param l_tsOrderBizDate 発注日
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GentradeTaxRate(String l_strTaxType, Timestamp l_tsOrderBizDate) throws WEB3BaseException
    {
        taxType = l_strTaxType;
        orderBizDate = l_tsOrderBizDate;

        final String STR_METHOD_NAME = "WEB3GentradeTaxRate(String, Timestamp)";

        log.entering(STR_METHOD_NAME);

        List l_list = null;
        int l_intSize = 0;
        TaxRateTableRow l_row = null;

        try
        {
            SimpleDateFormat l_formatter = GtlUtils.getThreadSafeSimpleDateFormat(DATE_FORMAT);
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_list = l_qp.doFindAllQuery(
                TaxRateTableRow.TYPE,
                WHERE_CLAUSE_2,
                new Object[] { taxType, l_formatter.format(orderBizDate), l_formatter.format(orderBizDate) });
        }
        catch (DataQueryException dqe)
        {
            // DBアクセスエラー
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                dqe.getMessage(), dqe);
        }
        catch (DataNetworkException dne)
        {
            // DBアクセスエラー
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                dne.getMessage(), dne);
        }

        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // 該当するデータが無い場合
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                ERROR_MESSAGE_1);
        }
        else
        {
            l_row = (TaxRateTableRow)l_list.get(0);
            log.debug("証券会社コード：[" + l_row.getInstitutionCode() + "]");
            log.debug("税種類：[" + l_row.getTaxType() + "]");
            log.debug("適用開始年月日：[" + l_row.getAppliStartDate().toString() + "]");
            log.debug("適用終了年月日：[" + l_row.getAppliEndDate().toString() + "]");
            log.debug("税率：[" + l_row.getTaxRate() + "]");
        }
        taxRate = l_row.getTaxRate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 税率を取得する。<BR>
     *<BR>
     * @@return 税率（％）
     */
    public double getTaxRate()
    {
        return taxRate;
    }

    /**
     * 税種類を取得する。<BR>
     *<BR>
     * @@return 税種類
     */
    public String getTaxType()
    {
        return taxType;
    }

    /**
     * 証券会社コードを取得する。<BR>
     *<BR>
     * @@return 証券会社コード
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * 発注日を取得する。<BR>
     *<BR>
     * @@return 発注日
     */
    public Timestamp getOrderBizDate()
    {
        return orderBizDate;
    }
}
@
