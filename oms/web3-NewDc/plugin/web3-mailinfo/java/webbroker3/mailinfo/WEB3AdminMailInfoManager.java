head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.16.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報管理(WEB3AdminMailInfoManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
Revesion History : 2004/10/19  王亞洲(中訊) 作成
*/
package webbroker3.mailinfo;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (メール情報管理)<BR>
 * メール情報管理クラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoManager 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMailInfoManager.class); 
    
    /**
     * @@roseuid 416CDE9101C5
     */
    public WEB3AdminMailInfoManager() 
    {
     
    }
    
    /**
     * (getメール)<BR>
     * 指定された引数からメールオブジェクトを取得し、返却する。<BR>
     * <BR>
     * 1) メールのコンストラクタをコールする。<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@送信メール区分=引数.送信メール区分<BR>
     * 　@識別ID=引数.識別ID<BR>
     * <BR>
     * 2) メールのコンストラクタから何かしらの例外が返された場合、<BR>
     * 　@例外を無視し、nullを返却する。<BR>
     * <BR>
     * 3) 生成したメールオブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strSendMailDiv - (送信メール区分)<BR>
     * 送信メール区分ID<BR>
     * @@param l_strDiscernmentId - (識別ID)<BR>
     * 識別ID<BR>
     * @@return webbroker3.gentrade.WEB3GentradeMailInfo
     * @@roseuid 413C1CEA005D
     */
    public static WEB3GentradeMailInfo getMail(String l_strInstitutionCode, String l_strSendMailDiv, String l_strDiscernmentId)
    throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getMail(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || l_strSendMailDiv == null || l_strDiscernmentId == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.exiting(STR_METHOD_NAME);
            return null;
        }   
        
        //メールオブジェクトの結果     
        WEB3GentradeMailInfo l_returnMail = null;
        try
        {
            //メールのコンストラクタをコールする。
            l_returnMail = new WEB3GentradeMailInfo(l_strInstitutionCode, l_strSendMailDiv, l_strDiscernmentId);
        }
        catch (WEB3BaseException l_ex)
        { 
            log.debug("例外を無視し、nullを返却する");            
            log.exiting(STR_METHOD_NAME);
            return null;
        }      
        
        log.exiting(STR_METHOD_NAME);
        return l_returnMail;
    }
    
    /**
     * (getメール一覧)<BR>
     * 指定された証券会社に紐付くメールを検出する。<BR>
     * <BR>
     * 1) 以下の条件で「メールテーブル」を検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * [並び順]<BR>
     * 　@引数.ソート条件<BR>
     * <BR>
     * 2) 検索結果の件数分、以下を繰り返す。<BR>
     *  2-1) メールのコンストラクタをコールし、メールオブジェクトを生成する。<BR>
     * [引数]<BR>
     * 　@メールRow=検索結果のメールParams<BR>
     *  2-2) メールオブジェクトを配列に追加する。<BR>
     * <BR>
     * 3) 2)で作成した配列を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return webbroker3.gentrade.WEB3GentradeMailInfo[ ]
     * @@roseuid 413C1CF3000F
     */
    public static WEB3GentradeMailInfo[] getMailList(String l_strInstitutionCode, String l_strSortCond) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getMailList(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if(l_strInstitutionCode == null)
        {
            log.error(STR_METHOD_NAME + "パラメータ.証券会社コードNull出来ない。" + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME,
                "パラメータ.証券会社コードNull出来ない。");            
        }
        
        //検索結果のメールParams
        List l_lstReturnRecord = null;
        try
        {
            //[検索条件]
            //証券会社コード=引数.証券会社コード
            StringBuffer l_sbWhere = new StringBuffer();            
            l_sbWhere.append("institution_code=?");
            
            Object[] l_objWhere = {l_strInstitutionCode};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //指定された証券会社に紐付くメールを検出する。
            l_lstReturnRecord = l_queryProcessor.doFindAllQuery(
                MailInfoRow.TYPE,
                l_sbWhere.toString(),
                l_strSortCond,
                null,
                l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminMailInfoManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminMailInfoManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminMailInfoManager" + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //メールオブジェクト配列
        int l_intMailInfoCnt = l_lstReturnRecord.size();        
        WEB3GentradeMailInfo[] l_returnMailInfos = new WEB3GentradeMailInfo[l_intMailInfoCnt];
        for(int i=0; i<l_intMailInfoCnt; i++)
        {            
            l_returnMailInfos[i] = new WEB3GentradeMailInfo((MailInfoParams)l_lstReturnRecord.get(i));
        }                

        log.exiting(STR_METHOD_NAME);
        return l_returnMailInfos;
    }
}
@
