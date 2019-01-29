head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDataManagerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券データマネージャーサービスImpl(WEB3BondDataManagerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 齊珂(中訊) 新規作成         
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.data.CustodianDao;
import webbroker3.bd.data.CustodianRow;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;

/**
 * (債券データマネージャーサービスImpl)<BR>
 * 債券データマネージャーサービスクラスImplクラス
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondDataManagerServiceImpl implements WEB3BondDataManagerService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3BondDataManagerServiceImpl.class);
    
    /**
     * @@roseuid 44E3362B0251
     */
    public WEB3BondDataManagerServiceImpl() 
    {
     
    }
    
    /**
     * (getカストディアン一覧)<BR>
     * カストディアン一覧を取得する。<BR>
     * <BR>
     * １）　@カストディアンマスタテーブルを検索し、検索結果のListを取得して返す。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード <BR>
     * 　@　@［ソート条件］ <BR>
     * 　@　@　@ カストディアンコード　@昇順<BR>
     * <BR>
     * ２）Listを返す。
     * @@param l_institution - 証券会社
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44B768520236
     */
    public List getCustodianList(Institution l_institution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getCustodianList(Institution)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        //証券会社コード = 引数.証券会社コード
        String l_strQueryString = " institution_code = ?  ";
        
        //カストディアンコード　@昇順
        String l_strSortCond = " custodian_code ASC ";
        Object[] l_objBindVars = new Object[1];

        l_objBindVars[0] = l_institution.getInstitutionCode();

        
        //return List
        List l_lisCustodianList = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCustodianList = l_queryProcessor.doFindAllQuery(
                CustodianRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null, 
                l_objBindVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
        return l_lisCustodianList;
    }
    
    /**
     * (getカストディアン)<BR>
     * カストディアンRowを取得する。<BR>
     * <BR>
     * １）以下の条件で、カストディアンテーブルを検索する。 <BR>
     * 　@[検索条件] <BR>
     * 　@　@証券会社コード=引数.証券会社.getInstitutionCode()<BR>
     * 　@　@カストディアンコード=引数.カストディアンコード <BR>
     * 　@　@※Daoを使って検索する。<BR>
     * 　@　@※レコードが存在しない場合、例外「該当カストディアンが存在しません。」をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02547<BR>
     * <BR>
     * ２）検索結果のRowを返す。　@
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strCustodianCode - (カストディアンコード)<BR>
     * カストディアンコード
     * @@return CustodianRow
     * @@throws WEB3BaseException
     * @@roseuid 44D6A42002CE
     */
    public CustodianRow getCustodian(Institution l_institution, String l_strCustodianCode) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getCustodian(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   

        CustodianRow l_custodianRow = null;
        try
        {
            l_custodianRow = 
                CustodianDao.findRowByInstitutionCodeCustodianCode(
                    l_institution.getInstitutionCode(), 
                    l_strCustodianCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
        if (l_custodianRow == null)
        {
            log.debug("該当カストディアンが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02547,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当カストディアンが存在しません。");
        }
        return l_custodianRow;
    }
}
@
