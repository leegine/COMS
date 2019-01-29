head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenBatchBranch.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バッチ用部店(WEB3AccOpenBatchBranch.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 徐宏偉 (中訊) 新規作成
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.accountopen.data.BatchBranchDao;
import webbroker3.accountopen.data.BatchBranchParams;
import webbroker3.accountopen.data.BatchBranchRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (バッチ用部店)<BR>
 * バッチ用部店<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenBatchBranch 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenBatchBranch.class);
    
    /**
     * (バッチ用部店行)<BR>
     * バッチ用部店行<BR>
     * <BR>
     * ※ バッチ用部店ParamsクラスはDDLより自動生成する。<BR>
     */
    private BatchBranchParams batchBranchParams;
    
    /**
     * (バッチ用部店)<BR>
     * バッチ用部店オブジェクトを取得する。<BR> 
     * <BR>
     * 以下の条件でバッチ用部店テーブルを検索する。<BR> 
     * <BR>
     * 　@[条件] <BR>
     * 　@バッチ用部店.証券会社コード = 引数.証券会社コード <BR>
     * 　@バッチ用部店.部店コード = 引数.部店コード <BR>
     * <BR>
     * 検索結果のバッチ用部店行オブジェクトを、this.バッチ用部店行にセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenBatchBranch(
        String l_strInstitutionCode, 
        String l_strBranchCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenBatchBranch(String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {        
            // 　@[条件]     
            // 　@バッチ用部店.証券会社コード = 引数.証券会社コード     
            //  　@バッチ用部店.部店コード = 引数.部店コード
            BatchBranchRow l_row = 
                (BatchBranchRow)BatchBranchDao.findRowByInstitutionCodeBranchCode(
                    l_strInstitutionCode, 
                    l_strBranchCode);
            
            if (l_row == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            //検索結果のバッチ用部店行オブジェクトを、this.バッチ用部店行にセットする。
            this.batchBranchParams = new BatchBranchParams(l_row);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);   
        } 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get株式約定メール送信フラグ)<BR>
     * 株式約定メール送信フラグを取得する。<BR> 
     * <BR>
     * this.バッチ用部店.株式約定メール送信フラグを返却する。<BR>
     * @@return String
     */
    public String getEquityOrderExecMailFlag()
    {
        final String STR_METHOD_NAME = "getEquityOrderExecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //株式約定メール送信フラグを取得する。
        //this.バッチ用部店.株式約定メール送信フラグを返却する。
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getEquityOrderExeMailFlag());

        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get株式未約定メール送信フラグ)<BR>
     * 株式未約定メール送信フラグを取得する。<BR> 
     * <BR>
     * this.バッチ用部店.株式未約定メール送信フラグを返却する。<BR>
     * @@return String
     */
    public String getEquityOrderUnexecMailFlag()
    {
        final String STR_METHOD_NAME = "getEquityOrderUnexecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //株式未約定メール送信フラグを取得する。
        //this.バッチ用部店.株式未約定メール送信フラグを返却する。
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getEquityOrderUnexecMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get先物OP約定メール送信フラグ)<BR>
     * 先物OP約定メール送信フラグを取得する。<BR> 
     * <BR>
     * this.バッチ用部店.先物OP約定メール送信フラグを返却する。<BR>
     * @@return String
     */
    public String getIfoOrderExecMailFlag()
    {
        final String STR_METHOD_NAME = "getIfoOrderExecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //先物OP約定メール送信フラグを取得する。
        //this.バッチ用部店.先物OP約定メール送信フラグを返却する。
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getIfoOrderExecMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get先物OP未約定メール送信フラグ)<BR>
     * 先物OP未約定メール送信フラグを取得する。<BR> 
     * <BR>
     * this.バッチ用部店.先物OP未約定メール送信フラグを返却する。<BR>
     * @@return String
     */
    public String getIfoOrderUnexecMailFlag()
    {
        final String STR_METHOD_NAME = "getIfoOrderUnexecMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //先物OP未約定メール送信フラグを取得する。
        //this.バッチ用部店.先物OP未約定メール送信フラグを返却する。
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getIfoOrderUnexecMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get案内メール送信フラグ)<BR>
     * 案内メール送信フラグを取得する。<BR> 
     * <BR>
     * this.バッチ用部店.案内メール送信フラグを返却する。<BR>
     * @@return String
     */
    public String getInformationMailFlag()
    {
        final String STR_METHOD_NAME = "getInformationMailFlag()";
        log.entering(STR_METHOD_NAME);
        
        //案内メール送信フラグを取得する。
        //this.バッチ用部店.案内メール送信フラグを返却する。
        String l_strMailFlag = WEB3StringTypeUtility.formatNumber(
            this.batchBranchParams.getInformationMailFlag());
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailFlag;
    }
    
    /**
     * (get振替可能回数)<BR>
     * 振替可能回数を取得する。<BR> 
     * <BR>
     * this.バッチ用部店.振替可能回数を返却する。<BR>
     * @@return long
     */
    public long getTransferCount()
    {
        final String STR_METHOD_NAME = "getTransferCount()";
        log.entering(STR_METHOD_NAME);
        
        //振替可能回数を取得する。
        //this.バッチ用部店.振替可能回数を返却する。
        long l_lngTransferCount = this.batchBranchParams.getTransferCount();
        
        log.exiting(STR_METHOD_NAME);
        return l_lngTransferCount;
    }    
    
    /**
     * (geｔ先頭画面ＩＤ)<BR>
     * 先頭画面ＩＤを取得する。<BR> 
     * <BR>
     * this.バッチ用部店.先頭画面ＩＤを返却する。<BR>
     * @@return String
     */
    public String getTopPageId()
    {
        final String STR_METHOD_NAME = "getTopPageId()";
        log.entering(STR_METHOD_NAME);
        
        //先頭画面ＩＤを取得する。
        //this.バッチ用部店.先頭画面ＩＤを返却する。
        String l_strTopPageId = this.batchBranchParams.getTopPageId();
        
        log.exiting(STR_METHOD_NAME);
        return l_strTopPageId;
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.batchBranchParams;   
    }
}
@
