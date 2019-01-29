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
filename	WEB3GentradeInsider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  内部者(WEB3GentradeInsider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.InsiderDao;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.util.WEB3LogUtility;

/**
 * 内部者
 */
public class WEB3GentradeInsider implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeInsider.class);

    /**
     * 内部者行オブジェクト
     * （DAO自動生成クラス）
     */
    private InsiderRow insiderRow;

    /**
     * コンストラクタ<BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、<BR>
     * 引数の行オブジェクトをプロパティにセットする。<BR>
     * @@param l_insiderRow - 内部者行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成する。<BR>
     * @@return webbroker3.会社・顧客エンティティ.WEB3GentradeInsider
     * @@roseuid 4147AEBB0365
     */
    public WEB3GentradeInsider(InsiderRow l_insiderRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeInsider(InsiderRow)";
        if (l_insiderRow == null)
        {
            log.error("内部者行 = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "内部者行 = null");
        }
        this.insiderRow = l_insiderRow;
    }

    /**
     * (getDataSourceObject) <BR>
     * this.内部者Rowを返却する。 <BR>
     * @@return Object
     * @@roseuid 4147AD8A01CF
     */
    public Object getDataSourceObject()
    {
        return this.insiderRow;
    }

    /**
     * (get関係区分) <BR>
     * this.内部者行.関係区分を返却する。 <BR>
     * @@return java.lang.String
     * @@roseuid 4147AF7B027B
     */
    public String getRelationDiv()
    {
        return this.insiderRow.getRelationDiv();
    }

    /**
     * (get役員名) <BR>
     * this.内部者行.役員名を返却する。<BR>
     * @@return java.lang.String
     * @@roseuid 4147AF82028A
     */
    public String getOfficerName()
    {
        return this.insiderRow.getOfficerName();
    }

    /**
     * (get役職名) <BR>
     * this.内部者行.役職名を返却する。<BR>
     * @@return java.lang.String
     * @@roseuid 4147AF8B0029
     */
    public String getPostName()
    {
        return this.insiderRow.getPostName();
    }

    /**
     * (get内部者) <BR>
     * （staticメソッド） <BR>
     *  <BR>
     * 引数の顧客に該当する内部者オブジェクトを全て取得する。 <BR>
     *  <BR>
     * １） DB検索 <BR>
     * 　@内部者テーブルを以下の条件で検索する。 <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 顧客.getInstitution().getInstitutionCode() <BR>
     * 　@部店ＩＤ = 顧客.getBranch().getBranchId() <BR>
     * 　@口座ＩＤ = 顧客.getAccountId() <BR>
     *  <BR>
     * ２） オブジェクト生成 <BR>
     *   検索結果の内部者行オブジェクトを指定し、 <BR>
     *   内部者オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを配列で返却する。<BR>
     * @@param l_genMainAccount - 顧客オブジェクト
     * @@return WEB3GentradeInsider[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 4147ADD10190
     */
    public static WEB3GentradeInsider[] getInsider(WEB3GentradeMainAccount l_genMainAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInsider(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //１） DB検索 
        //内部者テーブルを以下の条件で検索する。 
        //[条件] 
        //証券会社コード = 顧客.getInstitution().getInstitutionCode() 
        //部店ＩＤ = 顧客.getBranch().getBranchId() 
        //口座ＩＤ = 顧客.getAccountId()   
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_id = ? ");
        l_sbWhere.append(" and account_id = ? ");
        Object[] l_objWhere =
            { 
                l_genMainAccount.getInstitution().getInstitutionCode(), //証券会社コード
                new Long(l_genMainAccount.getBranch().getBranchId()), //部店ID
                new Long(l_genMainAccount.getAccountId()) //口座ＩＤ
            };
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InsiderRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２） オブジェクト生成
        int l_intSize = l_lisRecords.size();
        WEB3GentradeInsider[] l_gentradeInsiders = 
            new WEB3GentradeInsider[l_intSize];
        for(int i = 0 ; i < l_intSize; i ++)
        {
            l_gentradeInsiders[i] = 
                new WEB3GentradeInsider((InsiderRow)l_lisRecords.get(i));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_gentradeInsiders;
        
    }

    /**
     * (get内部者) <BR>
     * （staticメソッド）<BR>
     * <BR>
     * 引数の顧客に該当する内部者オブジェクトを全て取得する。<BR>
     * <BR>
     * １） DB検索<BR>
     * 　@内部者テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 顧客.getInstitution().getInstitutionCode()<BR>
     * 　@部店ＩＤ = 顧客.getBranch().getBranchId()<BR>
     * 　@口座ＩＤ = 顧客.getAccountId()<BR>
     * 　@銘柄ＩＤ = 銘柄.getProductId()<BR>
     * <BR>
     * ２） オブジェクト生成<BR>
     * 　@検索結果の内部者行オブジェクトを指定し、<BR>
     *   内部者オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを返却する。<BR>
     * @@param l_genMainAccount - 顧客オブジェクト
     * @@param l_product - 株式銘柄オブジェクト
     * @@return WEB3GentradeInsider
     * @@throws WEB3SystemLayerException
     * @@roseuid 4147AEFB0132
     */
    public static WEB3GentradeInsider getInsider(
        WEB3GentradeMainAccount l_genMainAccount,
        Product l_product)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInsider(WEB3GentradeMainAccount, Product)";
        log.entering(STR_METHOD_NAME);
        
        //１） DB検索
        //内部者テーブルを以下の条件で検索する。
        //[条件]
        //証券会社コード = 顧客.getInstitution().getInstitutionCode()
        //部店ＩＤ = 顧客.getBranch().getBranchId()
        //口座ＩＤ = 顧客.getAccountId()
        //銘柄ＩＤ = 銘柄.getProductId()
        InsiderRow l_insiderRow;
        try
        {
            l_insiderRow =
                InsiderDao.findRowByPk(
                    l_genMainAccount.getInstitution().getInstitutionCode(),
                    l_genMainAccount.getBranch().getBranchId(),
                    l_genMainAccount.getAccountId(),
                    l_product.getProductId());
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２） オブジェクト生成
        log.exiting(STR_METHOD_NAME);
        return new WEB3GentradeInsider(l_insiderRow);
        
    }
    
    /**
     * (get内部者) <BR>
     * （static メソッド） <BR>
     * 指定に該当する内部者オブジェクトのListを取得する。 <BR>
     * １）　@QueryProcessor.doFindAllQuery( )により、<BR>
     *  内部者行オブジェクトのListを取得する。 <BR>
     *  <BR>
     *  [doFindAllQuery()に指定する引数] <BR>
     *  rowType：　@内部者Row.TYPE <BR>
     *  where：　@検索条件文字列 <BR>
     *  orderBy：　@ソート条件 <BR>
     *  conditions：　@null <BR>
     *  bindVars：　@検索条件データコンテナ <BR>
     *  <BR>
     * ２）　@検索結果の行オブジェクトで内部者オブジェクトを生成し、<BR>
     *  Listで返却する。<BR>
     * @@param l_strWhere - 検索条件文字列
     * @@param l_strBindVars - 検索条件データコンテナ
     * @@param l_strOrderBy - ソート条件
     * @@return List
     * @@throws WEB3SystemLayerException
     * @@roseuid 4147AEFB0132
     */
    public static List getInsider(
        String l_strWhere,
        String[] l_strBindVars,
        String l_strOrderBy)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getInsider(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@QueryProcessor.doFindAllQuery( )により、
        // 内部者行オブジェクトのListを取得する。
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                InsiderRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_strBindVars);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）　@検索結果の行オブジェクトで内部者オブジェクトを生成し、
        //Listで返却する。
        int l_intSize = l_lstRecords.size();
        List l_lstGenInsiders = new ArrayList();
        for(int i = 0 ; i < l_intSize; i ++)
        {
            l_lstGenInsiders.add(new WEB3GentradeInsider((InsiderRow)l_lstRecords.get(i)));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lstGenInsiders;
    }
}
@
