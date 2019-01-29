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
filename	WEB3GentradeFinInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金融機@関(WEB3GentradeFinInstitution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.FinInstitutionDao;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

/**
 * (金融機@関) <BR>
 * 証券会社への入金用の金融機@関（口座）を表現する。<BR>
 */
public class WEB3GentradeFinInstitution implements BusinessObject
{
    
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeFinInstitution.class);

    /**
     * 金融機@関行オブジェクト<BR>
     * <BR>
     * ※ DDLより自動生成する。<BR>
     * ※ DBレイアウト「金融機@関テーブル仕様.xls」参照。<BR>
     */
    private FinInstitutionRow finInstitutionRow;

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 引数の条件に一致する金融機@関オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値をキーとして金融機@関テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（金融機@関Row）を<BR>
     * this.金融機@関Rowにセットする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strFinInstitutionCode - 金融機@関コード<BR>
     *  <BR>
     * @@return .WEB3GentradeFinInstitution <BR>
     * @@throws WEB3SystemLayerException <BR>
     * @@roseuid 40EE25870077
     */
    public WEB3GentradeFinInstitution(
        String l_strInstitutionCode,
        String l_strFinInstitutionCode) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3GentradeFinInstitution(String, String)";
        
        try
        {
            this.finInstitutionRow = 
                FinInstitutionDao.findRowByPk(l_strInstitutionCode,l_strFinInstitutionCode);
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
            log.error(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }
    
    }

    /**
     * 金融機@関コードを取得する。
     * @@return java.lang.String
     * @@roseuid 40EE2637025B
     */
    public String getFinInstitutionCode()
    {
        return this.finInstitutionRow.getFinInstitutionCode();
    }

    /**
     * (get金融機@関名（漢字）)<BR>
     * 金融機@関名（漢字）を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 40EE24620384
     */
    public String getFinInstitutionNameKanji()
    {
        return this.finInstitutionRow.getFinInstitutionNameKanji();
    }

    /**
     * (get金融機@関名（カナ）) <BR>
     * 金融機@関名（カナ）を取得する。<BR>
     * @@return java.lang.String
     * @@roseuid 40EE24CF0346
     */
    public String getFinInstitutionNameKana()
    {
        return this.finInstitutionRow.getFinInstitutionNameKana();
    }

    /**
     * @@return Object
     * @@roseuid 41076906007A
     */
    public Object getDataSourceObject()
    {
        return this.finInstitutionRow;
    }
}
@
