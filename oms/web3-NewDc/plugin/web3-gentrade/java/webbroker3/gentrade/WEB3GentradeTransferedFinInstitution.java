head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTransferedFinInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振込先金融機@関(WEB3GentradeTransferedFinInstitution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

/**
 * (振込先金融機@関) <BR>
 * 顧客が出金のために使用する指定口座を表現する。<BR>
 */
public class WEB3GentradeTransferedFinInstitution implements BusinessObject
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTransferedFinInstitution.class);

    /**
     * 振込先金融機@関行オブジェクト<BR>
     * <BR>
     * ※ DDLより自動生成する。<BR>
     * ※ DBレイアウト「振込先金融機@関テーブル仕様.xls」参照。<BR>
     */
    private TransferedFinInstitutionRow transferedFinInstitutionRow;

    /**
     * コンストラクタ<BR>
     * <BR>
     * 引数の条件に一致する振込先金融機@関オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値をキーとして振込先金融機@関テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（振込先金融機@関Row）を<BR>
     * this.振込先金融機@関Rowにセットする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_strAccountCode - 顧客コード（口座番号）<BR>
     * @@param l_strDesignateDiv - 指定区分<BR>
     *  <BR>
     * @@return WEB3GentradeTransferedFinInstitution <BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 40EE2AD002A9
     */
    public WEB3GentradeTransferedFinInstitution(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strDesignateDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeTransferedFinInstitution(String, String, String)";
        
        try
        {
            this.transferedFinInstitutionRow =
                TransferedFinInstitutionDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strDesignateDiv);
        }
        catch(DataFindException dfe)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01937,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe
            );
        }
        catch(DataException de)
        {
            WEB3SystemLayerException l_wse = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de
                );
            log.debug(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }

    }

    /**
     * (get銀行名) <BR>
     * 銀行名を取得する。 <BR>
     * @@return String
     * @@roseuid 40EE2C07025B
     */
    public String getFinInstitutionName()
    {
        return this.transferedFinInstitutionRow.getFinInstitutionName();
    }

    /**
     * (get銀行コード) <BR>
     * 銀行コードを取得する。 <BR>
     * @@return String
     * @@roseuid 40EE2C07025B
     */
    public String getFinInstitutionCode()
    {
        return this.transferedFinInstitutionRow.getFinInstitutionCode();
    }

    /**
     * (get支店名)<BR>
     * 支店名を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 40EE2C1D02F8
     */
    public String getFinBranchName()
    {
        return this.transferedFinInstitutionRow.getFinBranchName();
    }

    /**
     * (get口座番号) <BR>
     * 口座番号を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 40EE2C2C022C
     */
    public String getFinAccountNo()
    {
        return this.transferedFinInstitutionRow.getFinAccountNo();
    }

    /**
     * (get預金区分) <BR>
     * 預金区分を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 40F4CEC602E3
     */
    public String getFinSaveDiv()
    {
        return this.transferedFinInstitutionRow.getFinSaveDiv();
    }
    
    /**
     * (get支店コード) <BR>
     * 支店コードを取得する。<BR>
     * @@return java.lang.String
     */
    public String getFinBranchCode()
    {
        return this.transferedFinInstitutionRow.getFinBranchCode();
    }

    /**
     * @@return Object
     * @@roseuid 410768D502C9
     */
    public Object getDataSourceObject()
    {
        return this.transferedFinInstitutionRow;
    }
}
@
