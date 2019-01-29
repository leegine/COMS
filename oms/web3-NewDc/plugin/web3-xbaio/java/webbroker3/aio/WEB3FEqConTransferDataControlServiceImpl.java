head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferDataControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替連携データ制御サービスImplクラス(WEB3FEqConTransferDataControlServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 韋念瓊 (中訊) 新規作成       
*/

package webbroker3.aio;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.FeqAccountRow;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.data.UwgAccountOpenStatusRow;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.define.WEB3AioQuestionAnswerDef;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3FeqTransOperationDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株振替連携データ制御サービスImpl)<BR>
 * 外株振替連携データ制御サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferDataControlServiceImpl implements WEB3FEqConTransferDataControlService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferDataControlServiceImpl.class);
    
    /**
     * @@roseuid 423563670271
     */
    public WEB3FEqConTransferDataControlServiceImpl() 
    {
     
    }
    
    /**
     * (get外国株式顧客)<BR>
     * 引数の証券会社コード、部店コード、顧客コードに該当する<BR>
     * 外国株式顧客Paramsを取得する。<BR>
     * <BR>
     * １）外国株式顧客テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@顧客コード = 引数.顧客コード<BR>
     * <BR>
     * ２）検索結果を返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41E4D1CC03CA
     */
    public FeqAccountParams getFeqAccountByAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) 
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getFeqAccountByAccountCode(" +
            "String l_strInstitutionCode, String l_strBranchCode," + 
            "String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        //１）外国株式顧客テーブルを以下の条件で検索する。 
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@顧客コード = 引数.顧客コード 
        List l_lisFeqAccountRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? and account_code = ?";                 
            
            Object l_bindVars[] = {
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode,
                    };   
            
            l_lisFeqAccountRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        
        if(l_lisFeqAccountRows.size() == 0)
        {
            throw new NotFoundException("外国株式顧客テーブルが取得できませんでした");
        }
        
        if(l_lisFeqAccountRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外国株式顧客テーブルに複数件");
        }
        
        //２）検索結果を返却する。 
        FeqAccountParams l_feqAccountParams = (FeqAccountParams)l_lisFeqAccountRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_feqAccountParams;     
    }
    
    /**
     * (get外国株式顧客)<BR>
     * 引数の証券会社コード、部店コード、外株口座番号に該当する<BR>
     * 外国株式顧客Paramsを取得する。<BR>
     * <BR>
     * １）外国株式顧客テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@外国株式口座番号 = 引数.外株口座番号<BR>
     * <BR>
     * ２）検索結果を返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strFeqAccountCode - 外国株式口座番号
     * 
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41EF67C00229
     */
    public FeqAccountParams getFeqAccountByFeqAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strFeqAccountCode) 
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getFeqAccountByFeqAccountCode(" +
            "String l_strInstitutionCode, String l_strBranchCode," + 
            "String l_strFeqAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        //１）外国株式顧客テーブルを以下の条件で検索する。 
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@外国株式口座番号 = 引数.外株口座番号 

        List l_lisFeqAccountRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? and feq_account_code = ?";                 
            
            Object l_bindVars[] = {
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strFeqAccountCode,
                    };   
            
            l_lisFeqAccountRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        
        if(l_lisFeqAccountRows.size() == 0)
        {
            throw new NotFoundException("外国株式顧客テーブルが取得できませんでした");
        }
        
        if(l_lisFeqAccountRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外国株式顧客テーブルに複数件");
        }
        
        //２）検索結果を返却する。 
        FeqAccountParams l_feqAccountParams = (FeqAccountParams)l_lisFeqAccountRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_feqAccountParams;
    }
    
    /**
     * (get外国株式顧客)<BR>
     * 引数の外国株式顧客IDに該当する外国株式顧客Paramsを取得する。<BR>
     * <BR>
     * １）外国株式顧客テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@外国株式顧客ID = 引数.外国株式顧客ID<BR>
     * <BR>
     * ２）検索結果を返却する。
     * @@param l_strFeqAccountId - 外国株式顧客ID
     * 
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41E61DD203D6
     */
    public FeqAccountParams getFeqAccountByAccountId(String l_strFeqAccountId) 
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getFeqAccountByAccountId(" +
                "String l_strFeqAccountId)";
        log.entering(STR_METHOD_NAME);
        
        //１）外国株式顧客テーブルを以下の条件で検索する。 
        //[条件] 
        //外国株式顧客ID = 引数.外国株式顧客ID  
    
        List l_lisFeqAccountRows = null;
        try
        {           
            String l_strWhereClause = "feq_account_id = ?";
            
            Object l_bindVars[] = { new Long(l_strFeqAccountId) };
            
            l_lisFeqAccountRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        if(l_lisFeqAccountRows.size() == 0)
        {
            throw new NotFoundException("外国株式顧客テーブルが取得できませんでした");
        }
        
        if(l_lisFeqAccountRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外国株式顧客テーブルに複数件");
        }
        
        //２）検索結果を返却する。 
        FeqAccountParams l_feqAccountParams = (FeqAccountParams)l_lisFeqAccountRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_feqAccountParams;
    }
    
    /**
     * (get質問)<BR>
     * 引数の証券会社コード、部店コードに一致する<BR>
     * 質問Paramsを返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@質問テーブル(question)を以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@質問区分 = "外国株式振替"<BR>
     * <BR>
     * ２）検索結果を「質問番号」項目の昇順でソートし、返却する。<BR>
     * 　@※検索結果が取得できなかった場合、nullを返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@return QuestionParams[]
     * @@roseuid 41E4B6BE02D0
     */
    public QuestionParams[] getQuestion(
            String l_strInstitutionCode, 
            String l_strBranchCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getQuestion(" +
            "String l_strFeqAccountId)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //　@質問テーブル(question)を以下の条件で検索する。 
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@質問区分 = "外国株式振替" 
        List l_lisQuestionRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? and question_div = ?";                 
            String l_strOrderBy = "to_number(question_no)";
            
            Object l_bindVars[] = { 
                    l_strInstitutionCode,
                    l_strBranchCode, 
                    WEB3QuestionDivDef.FEQ_TRANS};   
            
            l_lisQuestionRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionRow.TYPE, 
                    l_strWhereClause, 
                    l_strOrderBy, 
                    null, 
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        QuestionParams[] l_questionParams = null;
        //２）検索結果を「質問番号」項目の昇順でソートし、返却する。 
        if(l_lisQuestionRows.size() > 0)
        {
            l_questionParams = 
                new QuestionParams[l_lisQuestionRows.size()]; 
            l_lisQuestionRows.toArray(l_questionParams);   
            log.exiting(STR_METHOD_NAME);
            return l_questionParams;        
        }
        //※検索結果が取得できなかった場合、nullを返却する。 
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (get質問回答)<BR>
     * 引数の証券会社コード、部店コード、識別コードに一致する<BR>
     * 質問回答Paramsを返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@質問回答テーブル(question_answer)を以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@質問区分 = "外国株式振替"<BR>
     * 　@識別コード = 引数.識別コード<BR>
     * <BR>
     * ２）検索結果を「質問番号」項目の昇順でソートし、返却する。<BR>
     * 　@※検索結果が取得できなかった場合、nullを返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return QuestionAnswerParams[]
     * @@roseuid 41E4B6BE02DF
     */
    public QuestionAnswerParams[] getQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getQuestionAnswer(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //　@質問回答テーブル(question_answer)を以下の条件で検索する。 
        //　@[条件] 
        //　@証券会社コード = 引数.証券会社コード 
        //　@部店コード = 引数.部店コード 
        //　@質問区分 = "外国株式振替" 
        //　@識別コード = 引数.識別コード 

        List l_lisQuestionAnswerRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? " +
                "and question_div = ? and order_request_number =?";                 
            String l_strOrderBy = "question_no";
            
            Object l_bindVars[] = { 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    WEB3QuestionDivDef.FEQ_TRANS, 
                    l_strRequestNumber 
                    };
            
            l_lisQuestionAnswerRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionAnswerRow.TYPE, 
                    l_strWhereClause, 
                    l_strOrderBy, 
                    null, 
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        QuestionAnswerParams[] l_questionAnswerParams = null;
        //２）検索結果を「質問番号」項目の昇順でソートし、返却する。 
        if(l_lisQuestionAnswerRows.size() > 0)
        {
            l_questionAnswerParams = 
                new QuestionAnswerParams[l_lisQuestionAnswerRows.size()]; 
            l_lisQuestionAnswerRows.toArray(l_questionAnswerParams);   
            log.exiting(STR_METHOD_NAME);
            return l_questionAnswerParams;        
        }
        //※検索結果が取得できなかった場合、nullを返却する。 
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (getUWG口座開設状況)<BR>
     * 引数の証券会社コード、部店コード、識別コードに<BR>
     * 該当するUWG口座開設状況Paramsを返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@以下の条件で、UWG口座開設状況テーブルを検索する。<BR>
     * <BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@識別コード = 引数.識別コード<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）検索結果を返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return UwgAccountOpenStatusParams
     * @@roseuid 41E4B6BE02FE
     */
    public UwgAccountOpenStatusParams getUwgAccountOpenStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getUwgAccountOpenStatus(" + 
            "String l_strInstitutionCode, String l_strBranchCode, " + 
            "String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //以下の条件で、UWG口座開設状況テーブルを検索する。 
        //証券会社コード = 引数.証券会社コード 
        //部店コード = 引数.部店コード 
        //識別コード = 引数.識別コード         
    
        List l_lisRows = null;
        try
        {           
            String l_strWhereClause = "institution_code = ? and " +
                    "branch_code = ? and order_request_number = ?";                 
            
            Object l_bindVars[] = { 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strRequestNumber
                    };   
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgAccountOpenStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //検索結果が取得できなかった場合、nullを返却する。        
        if (l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "UWG口座開設状況テーブルに複数件");
        }
        
        //２）検索結果を返却する。 
        UwgAccountOpenStatusParams l_uwgAccountOpenStatusParams = 
            (UwgAccountOpenStatusParams)l_lisRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_uwgAccountOpenStatusParams;
    }
    
    /**
     * (getUWG口座開設状況)<BR>
     * 引数の条件に該当するUWG口座開設状況Paramsの<BR>
     * 一覧を返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@rowType：　@UWG口座開設状況Row.TYPE<BR>
     * 　@　@where：　@パラメータ.検索条件文字列<BR>
     * 　@　@orderBy：　@パラメータ.ソート条件<BR>
     * 　@　@conditions：　@null<BR>
     * 　@　@bindVars：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）検索結果を返却する。
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return UwgAccountOpenStatusParams[]
     * @@roseuid 41E4B6BE030E
     */
    public UwgAccountOpenStatusParams[] getUwgAccountOpenStatus(
            String l_strQueryString, 
            String[] l_queryContainer, 
            String l_strSortCond) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getUwgAccountOpenStatus(" +
                "String l_strQueryString, String[] l_queryContainer, " + 
                "String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。 
        //[doFindAllQuery()にセットするパラメータ] 
        //rowType：　@UWG口座開設状況Row.TYPE 
        //where：　@パラメータ.検索条件文字列 
        //orderBy：　@パラメータ.ソート条件 
        //conditions：　@null 
        //bindVars：　@パラメータ.検索条件データコンテナ        
    
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgAccountOpenStatusRow.TYPE,
                    l_strQueryString,
                    l_strSortCond, 
                    null,
                    l_queryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }              

        UwgAccountOpenStatusParams[] l_uwgAccountOpenStatusParams = null;
        //２）検索結果を返却する。 
        if(l_lisRows.size() > 0)
        {
            l_uwgAccountOpenStatusParams = 
                new UwgAccountOpenStatusParams[l_lisRows.size()]; 
            
            l_lisRows.toArray(l_uwgAccountOpenStatusParams);   
            
            log.exiting(STR_METHOD_NAME);
            return l_uwgAccountOpenStatusParams;        
        }
        //※検索結果が取得できなかった場合、要素数が0の配列が返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            l_uwgAccountOpenStatusParams = new UwgAccountOpenStatusParams[0];
            return l_uwgAccountOpenStatusParams;
        }      
    }
    
    /**
     * (getUWG振替状況)<BR>
     * 引数の証券会社コード、部店コード、識別コードに<BR>
     * 該当するUWG振替状況Paramsを返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@以下の条件で、UWG振替状況テーブルを検索する。<BR>
     * <BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@部店コード = 引数.部店コード<BR>
     * 　@識別コード = 引数.識別コード<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）検索結果を返却する。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@return UwgTransferStatusParams
     * @@roseuid 41E4B6BE032D
     */
    public UwgTransferStatusParams getUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getUwgTransferStatus(" +
                "String l_strInstitutionCode, String l_strBranchCode, " + 
                "String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //以下の条件で、UWG振替状況テーブルを検索する。 
        //証券会社コード = 引数.証券会社コード 
        //部店コード = 引数.部店コード 
        //識別コード = 引数.識別コード       
    
        List l_lisRows = null;
        try
        {
            String l_strWhereClause = "institution_code = ? and " +
            "branch_code = ? and order_request_number = ?";                 
    
            Object l_bindVars[] = { 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strRequestNumber
                    };   
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgTransferStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        //検索結果が取得できなかった場合、nullを返却する。        
        if (l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "UWG振替状況テーブルに複数件");
        }
        
        //２）検索結果を返却する。 
        UwgTransferStatusParams l_uwgTransferStatusParams = 
            (UwgTransferStatusParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_uwgTransferStatusParams;
    }
    
    /**
     * (insert外国株式顧客)<BR>
     * UWG口座開設状況Paramsの内容より、<BR>
     * 外国株式顧客テーブルに行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「外株口座開設管理_外国株式顧客テーブルDB更新仕様.xls」
     * @@param l_uwgAccOpenStatusParams - UWG口座開設状況Paramsオブジェクト
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B6BE035C
     */
    public void insertFeqAccount(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdaterCode)  throws WEB3BaseException
    {
        String STR_METHOD_NAME ="insertFeqAccount(" +
                "UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, " +
                "String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);

        if(l_uwgAccOpenStatusParams == null )
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqAccountParams l_feqAccountParams = new FeqAccountParams();
        
        //1) 外国株式顧客ID = 外株振替連携データ制御サービスImpl.get新規外株顧客ID(
        //      this.証券会社コード, this.部店コード,this.顧客コード)
        String l_strFeqAccountID =  this.getNewFeqAccountId(
                l_uwgAccOpenStatusParams.getInstitutionCode(),
                l_uwgAccOpenStatusParams.getBranchCode(), 
                l_uwgAccOpenStatusParams.getAccountCode());
            
        l_feqAccountParams.setFeqAccountId(Long.parseLong(l_strFeqAccountID));
        
        //2) 証券会社コード = UWG口座開設状況Params.証券会社コード
        l_feqAccountParams.setInstitutionCode(l_uwgAccOpenStatusParams.getInstitutionCode());
        
        //3) 部店コード = UWG口座開設状況Params.部店コード
        l_feqAccountParams.setBranchCode(l_uwgAccOpenStatusParams.getBranchCode());
        
        //4) 顧客コード = UWG口座開設状況Params.顧客コード
        l_feqAccountParams.setAccountCode(l_uwgAccOpenStatusParams.getAccountCode());
        
        //5) 名前（姓） = UWG口座開設状況Params.名前（姓）
        l_feqAccountParams.setLastName(l_uwgAccOpenStatusParams.getLastName());
        
        //6) 名前（名）= UWG口座開設状況Params.名前（名）
        l_feqAccountParams.setFirstName(l_uwgAccOpenStatusParams.getFirstName());
                   
        //7) 外国株式口座番号 = UWG口座開設状況Params.外国株式口座番号
        l_feqAccountParams.setFeqAccountCode(l_uwgAccOpenStatusParams.getFeqAccountCode());
        
        //8) 口座開設区分 = 1：開設済
        l_feqAccountParams.setAccountOpenDiv(WEB3AioAccountDivDef.OPEN_COMPLETE);
        
        //9) 初回振替フラグ = 0：未実施
        l_feqAccountParams.setFirstTransferFlag(WEB3FeqFirstTransferFlagDef.NOT_TRANSFER);
        
        //10) 更新者コード = 管理者.get管理者コード()
        l_feqAccountParams.setLastUpdater(l_strUpdaterCode);
        
        //11) 作成日付 = 現在時刻
        l_feqAccountParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //12) 更新日付 = 現在時刻
        l_feqAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            //insert FX顧客テーブル
            WEB3DataAccessUtility.insertRow(l_feqAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (insert質問回答)<BR>
     * 外株口座開設質問情報の内容で<BR>
     * 質問回答テーブル(question_answer)に行のinsertを行う。<BR>
     * ※引数.質問情報一覧の要素数分のLoop処理を行い、<BR>
     *    要素ごとに行のinsertを行う。<BR>
     * <BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「外株口座開設_質問回答テーブル.xls」
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_feqAccOpenQuestionInfo - 外株口座開設質問情報の一覧
     * @@roseuid 41E4B6BF0001
     */
    public void insertQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber, 
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertQuestionAnswer(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber, " +
            "WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_feqAccOpenQuestionInfo == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            for (int i = 0 ; i < l_feqAccOpenQuestionInfo.length; i++)
            {
                QuestionAnswerParams l_questionAnswerParams = new QuestionAnswerParams();
                
                //1) 証券会社コード : 引数.証券会社コード
                l_questionAnswerParams.setInstitutionCode(l_strInstitutionCode);
                
                //2) 部店コード : 引数.部店コード
                l_questionAnswerParams.setBranchCode(l_strBranchCode);
                
                //3) 質問区分 : 0002：外国株式振替
                l_questionAnswerParams.setQuestionDiv(WEB3QuestionDivDef.FEQ_TRANS);
                
                //4) 識別コー : 引数.識別コード
                l_questionAnswerParams.setOrderRequestNumber(l_strRequestNumber);
                
                //5) 質問番号 : 引数.質問情報一覧[index].質問番号                
                log.debug("引数.質問情報一覧[index].質問番号" + 
                        l_feqAccOpenQuestionInfo[i].questionNumber);
                
                l_questionAnswerParams.setQuestionNo(l_feqAccOpenQuestionInfo[i].questionNumber);
                
                //6) 質問回答 : 引数.質問情報一覧[index].質問回答
                log.debug("引数.質問情報一覧[index].質問回答" + 
                        l_feqAccOpenQuestionInfo[i].questionAnswer);
                l_questionAnswerParams.setQuestionAnswer(l_feqAccOpenQuestionInfo[i].questionAnswer);
                
                //7) 更新者コード : null
                l_questionAnswerParams.setLastUpdater(null);
                
                //8) 作成日付 : 現在時刻
                l_questionAnswerParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //9) 更新日付 : 現在時刻
                l_questionAnswerParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //insert 質問回答テーブル
                WEB3DataAccessUtility.insertRow(l_questionAnswerParams);
            }    
            
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (insertUWG口座開設状況)<BR>
     * UWG口座開設状況テーブルに行のinsertを行う。<BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「外株口座開設_UWG口座開設状況テーブル.xls」
     * @@param l_mainAccount - 顧客オブジェクト
     * @@param l_strPassword - UWG用パスワード
     * @@param l_strOrderRequestNumber - 識別コード
     * @@roseuid 41E4B6BF0030
     */
    public void insertUwgAccountOpenStatus(
            MainAccount l_mainAccount, 
            String l_strPassword, 
            String l_strOrderRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertUwgAccountOpenStatus(" +
                "MainAccount l_mainAccount, String l_strPassword)";
        log.entering(STR_METHOD_NAME);
        
        if(l_mainAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        UwgAccountOpenStatusParams l_uwgAccountOpenStatusParams = new UwgAccountOpenStatusParams();

        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        //1) 証券会社コード : 引数.顧客.証券会社コード
        l_uwgAccountOpenStatusParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());
        
        //2) 部店コード : 引数.顧客.部店コード
        l_uwgAccountOpenStatusParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        
        //3) 顧客コード : 引数.顧客.顧客コード
        l_uwgAccountOpenStatusParams.setAccountCode(l_mainAccountRow.getAccountCode());
        
        //4) 識別コード : 引数.識別コード
        l_uwgAccountOpenStatusParams.setOrderRequestNumber(l_strOrderRequestNumber);
        log.debug("識別コード :" + l_uwgAccountOpenStatusParams.getOrderRequestNumber());
        
        //5) 名前（姓） : 引数.顧客.名前（苗字）
        l_uwgAccountOpenStatusParams.setLastName(l_mainAccountRow.getFamilyName());
        
        //6) 名前（名） : null
        l_uwgAccountOpenStatusParams.setFirstName(null);
        
        //7) メールアドレス : 引数.顧客.emailアドレス
        l_uwgAccountOpenStatusParams.setMailAddress(l_mainAccountRow.getEmailAddress());
        
        //8) 外国株式口座番号 : 引数.顧客.顧客コードの上6桁
        l_uwgAccountOpenStatusParams.setFeqAccountCode(l_mainAccount.getAccountCode().substring(0,6));
        log.debug("外国株式口座番号 :" + l_uwgAccountOpenStatusParams.getFeqAccountCode());

		//9) パスワード : 引数.パスワードを外株振替連携データ制御サービスImpl.maskパスワード()にて変換したもの
		l_uwgAccountOpenStatusParams.setPassword(this.maskPassword(l_strPassword));
		log.debug("パスワード :" + this.maskPassword(l_strPassword));
        
        //10) 口座開設状況区分 = 0：口座開設中
        l_uwgAccountOpenStatusParams.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING);
             
        //11) 送受信区分 : 0：未送信
        l_uwgAccountOpenStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.NOT_SEND);
        
        //12) 受付結果コード : null
        l_uwgAccountOpenStatusParams.setResultCode(null);
        
        //13) エラー理由コード : null
        l_uwgAccountOpenStatusParams.setErrorReasonCode(null);
        
        //14) 更新者コード : null
        l_uwgAccountOpenStatusParams.setLastUpdater(null);
        
        //15) 作成日付 : 現在時刻
        l_uwgAccountOpenStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //16) 更新日付 : 現在時刻
        l_uwgAccountOpenStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {            
            //insert GFT口座開設状況テーブル
            WEB3DataAccessUtility.insertRow(l_uwgAccountOpenStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (insertUWG振替状況)<BR>
     * UWG振替状況テーブルに行のinsertを行う。<BR>
     * <BR>
     * １）外国株式顧客オブジェクトの取得<BR>
     * <BR>
     *   this.get外国株式顧客()をコールする。<BR>
     * <BR>
     *   [引数]<BR>
     *   証券会社コード： 引数.証券会社コード<BR>
     *   部店コード： 引数.部店コード<BR>
     *   顧客コード： 引数.顧客コード<BR>
     * <BR>
     * ２）DB insert<BR>
     * 挿入する行の内容は下記を参照。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新 <BR>
     * 「外株口座への振替_UWG振替状況テーブル.xls」
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strTransferDate - 受渡予定日
     * @@param l_strRequestNumber - 識別コード
     * @@param l_strMrgTrnRequestNumber - 信用振替用識別コード
     * @@param l_strTransferAmount - 振替金額
     * ※信用口座からの強制振替を行わない場合、null
     * @@roseuid 41E4F8D0010A
     */
    public void insertUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strTransferDate, 
            String l_strRequestNumber, 
            String l_strMrgTrnRequestNumber, 
            String l_strTransferAmount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertUwgTransferStatus(" +
            "String l_strInstitutionCode, String l_strBranchCode" + 
            "String l_strAccountCode, String l_strTransferDate, " + 
            "String l_strRequestNumber, String l_strMrgTrnRequestNumber";        
        log.entering(STR_METHOD_NAME);
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = this.getFeqAccountByAccountCode(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {        
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("外国株式顧客Params = " + l_feqAccountParams);
        
        UwgTransferStatusParams l_uwgTransferStatusParams = new UwgTransferStatusParams();

        //1) 証券会社コード : 引数.証券会社コード
        l_uwgTransferStatusParams.setInstitutionCode(l_strInstitutionCode);
        
        //2) 部店コード : 引数.部店コード
        l_uwgTransferStatusParams.setBranchCode(l_strBranchCode);
        
        //3) 顧客コード : 引数.顧客コード
        l_uwgTransferStatusParams.setAccountCode(l_strAccountCode);
        
        //4) 識別コード : 引数.識別コード
        l_uwgTransferStatusParams.setOrderRequestNumber(l_strRequestNumber);
        
        //5) 処理区分 : 01：証券口座から外国株式口座へ
        l_uwgTransferStatusParams.setOperationDiv(WEB3FeqTransOperationDivDef.TRANSFER_TO_FEQ);
        
        //6) 外国株式口座番号 : 外国株式顧客.外国株式口座番号
        l_uwgTransferStatusParams.setFeqAccountCode(l_feqAccountParams.getFeqAccountCode());
        
        //7) 金額 : 引数.振替金額
        log.debug("引数.振替金額 = " + l_strTransferAmount);
        l_uwgTransferStatusParams.setAmount(Long.parseLong(l_strTransferAmount));
           
        //8) 受渡予定日 : 引数.受渡予定日
        log.debug("引数.受渡予定日 = " + l_strTransferDate);
        l_uwgTransferStatusParams.setTransferDate(l_strTransferDate);
        
        //9) 振替状況区分 = 0：決済中
        l_uwgTransferStatusParams.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESSING);
        
        //10) 送受信区分 : 0：未送信
        l_uwgTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.NOT_SEND);
        
        //11) 受付結果コード : null
        l_uwgTransferStatusParams.setResultCode(null);
        
        //12) エラー理由コード : null
        l_uwgTransferStatusParams.setErrorReasonCode(null);
        
        //13) 処理時間（送信） : null
        l_uwgTransferStatusParams.setSendTime(null);
        
        //14) 処理時間（受信） : null
        l_uwgTransferStatusParams.setReceiveTime(null);
        
        //15) 信用振替用識別コード） : 引数.信用振替用識別コード
        log.debug("引数.信用振替用識別コード = " + l_strMrgTrnRequestNumber);
        l_uwgTransferStatusParams.setMrgTrnOrderRequestNumber(l_strMrgTrnRequestNumber);
        
        //16) 初回振替区分 : 外国株式顧客.初回振替フラグ==”未実施”の場合、1：初回振替
        //                 外国株式顧客.初回振替フラグ==”実施済み”の場合、0：その他
        if (WEB3FeqFirstTransferFlagDef.NOT_TRANSFER.equals(
                l_feqAccountParams.getFirstTransferFlag()))
        {
            log.debug("外国株式顧客.初回振替フラグ==”未実施”の場合");
            l_uwgTransferStatusParams.setFirstTransferDiv(
                    WEB3FeqFirstTransferFlagDef.TRANSFERRED);
        }
        else
        {
            log.debug("外国株式顧客.初回振替フラグ==”実施済み”の場合");
            l_uwgTransferStatusParams.setFirstTransferDiv(
                    WEB3FeqFirstTransferFlagDef.NOT_TRANSFER);
        }
        
        //17) 更新者コード : null
        l_uwgTransferStatusParams.setLastUpdater(null);
        
        //18) 作成日付 : 現在時刻
        l_uwgTransferStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //19) 更新日付 : 現在時刻
        l_uwgTransferStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        try
        {
            //insert UWG振替状況テーブル
            WEB3DataAccessUtility.insertRow(l_uwgTransferStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update外国株式顧客)<BR>
     * 更新後口座開設区分の値で外国株式顧客テーブルを更新する。 <BR>
     * <BR>
     * １）外国株式顧客Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）生成したインスタンスに、パラメータ.外国株式顧客Paramの<BR>
     * 　@プロパティをコピーする。<BR>
     * <BR>
     * ３）生成したインスタンスに更新後のデータをセットする。<BR>
     * 　@更新する行の内容は下記を参照。<BR>
     * <BR>
     * 　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@「外株口座管理_外国株式顧客テーブルDB更新仕様.xls」<BR>
     * <BR>
     * ４）外国株式顧客のupdate<BR>
     *  WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * <BR>
     * 　@[updateRow()にセットするパラメータ]<BR>
     * 　@　@l_row：　@更新後のデータをセットしたインスタンス
     * 　@
     * @@param l_feqAccountParams - 外国株式顧客Paramオブジェクト
     * @@param l_strUpdatedAccOpenDiv - 更新後口座開設区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B6BF009D
     */
    public void updateFeqAccount(
            FeqAccountParams l_feqAccountParams, 
            String l_strUpdatedAccOpenDiv, 
            String l_strUpdaterCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFeqAccount(FeqAccountParams l_feqAccountParams, " + 
            "String l_strUpdatedAccOpenDiv,String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_feqAccountParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）外国株式顧客Paramsインスタンスを生成する。       
        //２）生成したインスタンスに、パラメータ.外国株式顧客Paramのプロパティをコピーする。
        FeqAccountParams l_feqAccountParamsForUpd = new FeqAccountParams(l_feqAccountParams);
        
        //３）生成したインスタンスに更新後のデータをセットする。 
        // 更新する行の内容は下記を参照。 
        //【ｘTrade】補足資料.DB更新 
        //「外株口座管理_外国株式顧客テーブルDB更新仕様.xls」
        
        //口座開設区分 = リクエストデータ.更新後口座開設状況区分("1：開設済" or "9：抹消")
        l_feqAccountParamsForUpd.setAccountOpenDiv(l_strUpdatedAccOpenDiv);
        log.debug("口座開設区分 = " + l_strUpdatedAccOpenDiv);
        
        //更新者コード = 管理者.get管理者コード()
        l_feqAccountParamsForUpd.setLastUpdater(l_strUpdaterCode);
        log.debug("更新者コード = " + l_strUpdaterCode);
        
        //更新日付 = 現在時刻
        l_feqAccountParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
        try
        {            
            //４）外国株式顧客のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //[updateRow()にセットするパラメータ] 
            //　@l_row：　@更新後のデータをセットしたインスタンス 
            WEB3DataAccessUtility.updateRow(l_feqAccountParamsForUpd);        
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (updateUWG口座開設状況)<BR>
     * 更新後の値でUWG口座開設状況テーブルを更新する。 <BR>
     * <BR>
     * １）UWG口座開設状況Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）生成したインスタンスに、パラメータ.UWG口座開設状況Paramの<BR>
     * 　@プロパティをコピーする。<BR>
     * <BR>
     * ３）生成したインスタンスに更新後のデータをセットする。<BR>
     * 　@更新する行の内容は下記を参照。<BR>
     * <BR>
     * 　@　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@　@「外株口座開設管理_UWG口座開設状況テーブルDB更新仕様.xls」<BR>
     * <BR>
     * ４）UWG口座開設状況のupdate<BR>
     *  WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * <BR>
     * 　@[updateRow()にセットするパラメータ]<BR>
     * 　@　@l_row：　@更新後のデータをセットしたインスタンス
     * 　@
     * @@param l_uwgAccOpenStatusParams - UWG口座開設状況Paramsオブジェクト
     * @@param l_strUpdatedStatusDiv - 更新後ステータス区分
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B6BF00DC
     */
    public void updateUwgAccountOpenStatus(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdatedStatusDiv, 
            String l_strUpdaterCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateUwgAccountOpenStatus(" +
            "UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, " +
            "String l_strUpdatedStatusDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_uwgAccOpenStatusParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）UWG口座開設状況Paramsインスタンスを生成する。 
        //２）生成したインスタンスに、パラメータ.UWG口座開設状況Paramの 
        //  プロパティをコピーする。         
        UwgAccountOpenStatusParams l_uwgAccOpenStatusParamsForUpd = 
            new UwgAccountOpenStatusParams(l_uwgAccOpenStatusParams);
            
        //３）生成したインスタンスに更新後のデータをセットする。 
        //更新する行の内容は下記を参照。 
        //　@【ｘTrade】補足資料.DB更新 
        //　@「外株口座開設管理_UWG口座開設状況テーブルDB更新仕様.xls」 

        //口座開設状況区分 = 引数.更新後ステータス区分
        log.debug("口座開設状況区分 = " + l_strUpdatedStatusDiv);
        l_uwgAccOpenStatusParamsForUpd.setAccountOpenStatusDiv(l_strUpdatedStatusDiv);

        //更新者コード = 管理者.get管理者コード()
        log.debug("更新者コード = " + l_strUpdaterCode);
        l_uwgAccOpenStatusParamsForUpd.setLastUpdater(l_strUpdaterCode);
        
        //更新日付 = 現在時刻
        l_uwgAccOpenStatusParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
        try
        { 
            //４）UWG口座開設状況のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //[updateRow()にセットするパラメータ] 
            //l_row：　@更新後のデータをセットしたインスタンス 
            
            WEB3DataAccessUtility.updateRow(l_uwgAccOpenStatusParamsForUpd);            
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (updateUWG振替状況)<BR>
     * 引数のUWG振替状況ParamsでUWG振替状況テーブルを更新する。 <BR>
     * <BR>
     * １）UWG振替状況Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）生成したインスタンスに、パラメータ.UWG振替状況Paramの<BR>
     * 　@プロパティをコピーする。<BR>
     * <BR>
     * ３）生成したインスタンスに更新後のデータをセットする。<BR>
     * 　@更新する行の内容は下記を参照。<BR>
     * <BR>
     * 　@　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@　@「外株口座への振替取消_UWG振替状況テーブルDB更新仕様.xls」<BR>
     * <BR>
     * ４）UWG振替状況のupdate<BR>
     *  WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * <BR>
     * 　@[updateRow()にセットするパラメータ]<BR>
     * 　@　@l_row：　@更新後のデータをセットしたインスタンス
     * @@param l_uwgTransferStatusParams - UWG振替状況Paramsオブジェクト
     * @@param l_strUpdatedTransferStatusDiv - 更新後振替状況区分
     * @@roseuid 41ECE0880270
     */
    public void updateUwgTransferStatus(
            UwgTransferStatusParams l_uwgTransferStatusParams, 
            String l_strUpdatedTransferStatusDiv) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateUwgTransferStatus(" + 
            "UwgTransferStatusParams l_uwgTransferStatusParams, " + 
            "String l_strUpdatedTransferStatusDiv)";
        log.entering(STR_METHOD_NAME);
        
        if(l_uwgTransferStatusParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）UWG振替状況Paramsインスタンスを生成する。 
        //２）生成したインスタンスに、パラメータ.UWG振替状況Paramの 
        //  プロパティをコピーする。 
     
        UwgTransferStatusParams l_uwgTransferStatusParamsForUpd = 
            new UwgTransferStatusParams(l_uwgTransferStatusParams);
            
        //３）生成したインスタンスに更新後のデータをセットする。 
        // 更新する行の内容は下記を参照。 
        //【ｘTrade】補足資料.DB更新 
        //「外株口座への振替取消_UWG振替状況テーブルDB更新仕様.xls」 

        //振替状況区分 = 引数.更新後振替状況区分
        l_uwgTransferStatusParamsForUpd.setTransferStatusDiv(l_strUpdatedTransferStatusDiv);
               
        //更新日付 = 現在時刻
        l_uwgTransferStatusParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
        try
        { 
            //４）UWG振替状況のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //[updateRow()にセットするパラメータ] 
            //  l_row：　@更新後のデータをセットしたインスタンス 
            
            WEB3DataAccessUtility.updateRow(l_uwgTransferStatusParamsForUpd);            
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (update外株口座開設区分)<BR>
     * 顧客マスターテーブルの外国証券口座開設区分をupdateする。<BR>
     * <BR>
     * １）顧客の取得<BR>
     * 　@拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。<BR>
     * 　@<BR>
     * 　@[引数の設定]<BR>
     * 　@証券会社コード：　@引数.証券会社コード<BR>
     * 　@部店コード：　@引数.部店コード<BR>
     * 　@口座コード：　@引数.顧客コード<BR>
     * <BR>
     * 　@取得した顧客.getDataSourceObject()により顧客Paramsを取得する。<BR>
     * <BR>
     * ３）生成したインスタンスに、更新後のデータをセットする。<BR>
     * <BR>
     * 　@更新する行の内容は下記を参照。<BR>
     * <BR>
     * 　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@「外株口座開設管理_顧客マスター.xls」参照<BR>
     * <BR>
     * ４）顧客のupdate<BR>
     *  WEB3DataAccessUtility.updateRow()メソッドをコールする。<BR>
     * <BR>
     * 　@[updateRow()にセットするパラメータ]<BR>
     * 　@　@l_row：　@３）にて更新後のデータをセットしたインスタンス
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strUpdatedFeqAccOpenDiv - 更新後外株口座開設区分
     * 
     * 0：開設
     * 1：未開設
     * 
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 41E4B6BF0159
     */
    public void updateFeqAccountOpenDiv(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strUpdatedFeqAccOpenDiv, 
            String l_strUpdaterCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFeqAccountOpenDiv(String l_strInstitutionCode, " + 
            "String l_strBranchCode, String l_strAccountCode, " + 
            "String l_strUpdatedFeqAccOpenDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        //１）顧客の取得 
        //拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得する。 
        //[引数の設定] 
        //証券会社コード：　@引数.証券会社コード 
        //部店コード：　@引数.部店コード 
        //口座コード：　@引数.顧客コード 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        MainAccount l_mainAccount = 
            l_web3GentradeAccountManager.getMainAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
        
        //取得した顧客.getDataSourceObject()により顧客Paramsを取得する。 
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        MainAccountParams l_mainAccountParamsForUpd = 
            new MainAccountParams(l_mainAccountRow);
            
        //３）生成したインスタンスに、更新後のデータをセットする。 
        // 更新する行の内容は下記を参照。 
        //【ｘTrade】補足資料.DB更新 
        //「外株口座開設管理_顧客マスター.xls」参照 
        
        //外国株式連携口座開設区分 = 引数.更新後外株口座開設区分
        log.debug("外国株式連携口座開設区分 = " + l_strUpdatedFeqAccOpenDiv);
        l_mainAccountParamsForUpd.setFeqConAccOpenDiv(l_strUpdatedFeqAccOpenDiv);
        
        //外国株式連携口座開設区分更新者コード = 引数.更新者コード
        log.debug("外国株式連携口座開設区分更新者コード = " + l_strUpdaterCode);
        l_mainAccountParamsForUpd.setFeqConAccOpenDivUpdater(l_strUpdaterCode);
        
        //外国株式連携口座開設区分更新日時 = 現在時刻
        l_mainAccountParamsForUpd.setFeqConAccOpenTimestamp(GtlUtils.getSystemTimestamp());
              
        //更新日時 = 現在時刻
        l_mainAccountParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        { 
            //４）顧客のupdate 
            //WEB3DataAccessUtility.updateRow()メソッドをコールする。 
            //[updateRow()にセットするパラメータ] 
            //　@l_row：　@３）にて更新後のデータをセットしたインスタンス  
            
            WEB3DataAccessUtility.updateRow(l_mainAccountParamsForUpd);            
        }
        catch (DataException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
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
     * (validate外株口座開設質問)<BR>
     * 外株口座開設質問に対する回答の整合性をチェックする。<BR>
     * <BR>
     * 引数.質問情報一覧の要素ごとのLoop処理にて、<BR>
     * 以下のチェックを行う。<BR>
     * <BR>
     * 外株口座開設質問情報.質問回答≠”1：同意”の場合、例外をthorwする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01949<BR>
     * <BR>
     * @@param l_feqAccOpenQuestionInfo - 外株口座開設質問情報の一覧
     * @@roseuid 41E4B6BF01E5
     */
    public void validateFeqAccountOpenQuestion(
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateFeqAccountOpenQuestion(" + 
            "WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqAccOpenQuestionInfo == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //引数.質問情報一覧の要素ごとのLoop処理にて、 
        //以下のチェックを行う。 
        //外株口座開設質問情報.質問回答≠”1：同意”の場合、例外をthrowする。
        
        for (int i = 0; i < l_feqAccOpenQuestionInfo.length; i++)
        {
            if (!WEB3AioQuestionAnswerDef.AGREE.equals(
                    l_feqAccOpenQuestionInfo[i].questionAnswer))
            {
                log.debug("外株口座開設質問情報.質問回答≠”1：同意”の場合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01949,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "同意されてない質問があります");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get新規外国株式顧客ID)<BR>
     * 外国株式顧客IDを付番して返却する。<BR>
     * <BR>
     * 外国株式顧客IDの付番ルールは<BR>
     * 証券会社ID + 部店コード + 顧客コード(*1)とする。<BR>
     * <BR>
     * 証券会社ID + 引数.部店コード + 引数.顧客コードを<BR>
     * 文字列連結した値を返却する。<BR>
     * <BR>
     * (*1)引数.顧客コード == 7桁の場合、<BR>
     * 　@引数.顧客コードの先頭から6桁目までを使用する
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return java.lang.String
     * @@roseuid 41E633900108
     */
    public String getNewFeqAccountId(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getNewFeqAccountId(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
		//拡張アカウントマネージャ取得する   
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
		WEB3GentradeAccountManager l_accountManager =
			(WEB3GentradeAccountManager) l_finApp.getAccountManager();

		//証券会社IDを取得する
		String l_strInstituionId = null;
		try{
			Institution l_Instituion = l_accountManager.getInstitution(l_strInstitutionCode);
			l_strInstituionId = String.valueOf(l_Instituion.getInstitutionId());
		}
		catch (NotFoundException l_ex) 
		{
			log.error("__NotFoundException__");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		
        //外国株式顧客IDの付番ルールは 
        //証券会社ID + 部店コード + 顧客コードとする。 
        
        String l_strAccountCodeNew = l_strAccountCode;
 
        String l_strNewFeqAccountId = l_strInstituionId + 
            l_strBranchCode + l_strAccountCodeNew;
        
        log.debug("新規外国株式顧客ID = " + l_strNewFeqAccountId);
        log.exiting(STR_METHOD_NAME);
        return l_strNewFeqAccountId;
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strBranchCode
     * @@param l_strAccountCode
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 423563680128
     */
    public FeqAccountParams getFeqAccount(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) 
    {
     return null;
    }
    
    /**
     * @@param l_strFeqAccountId
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 4235636A02FD
     */
    public FeqAccountParams getFeqAccount(String l_strFeqAccountId) 
    {
     return null;
    }
    
	/**
	 * (maskパスワード) <BR>
	 * パスワードにマスクをかける。 <BR>
	 * <BR>
	 * １）空の文字列（＝Aとする。）を生成する。<BR>
	 * ２）以下のとおりに、引数.パスワードの配列を置き換えて、<BR>
	 *      １）の文字列（＝A）にセットする。<BR>
	 * <BR>
	 * ・A[0] = 引数.パスワード[1] <BR>
	 * ・A[1] = 引数.パスワード[4] <BR>
	 *   A[2] = 引数.パスワード[7] <BR>
	 *   A[3] = 引数.パスワード[6] <BR>
	 *   A[4] = 引数.パスワード[3] <BR>
	 *   A[5] = 引数.パスワード[0] <BR>
	 *   A[6] = 引数.パスワード[2] <BR>
	 *   A[7] = 引数.パスワード[5] <BR>
	 * <BR>
	 * ２）Aの文字列を返却する。<BR>
	 * 
	 * @@param l_strMaskPassword  String
	 * @@return String
	 * @@throws WEB3BaseException
	 * @@roseuid 42F8156E0361
	 */
	protected String maskPassword(String l_strMaskPassword)
	   throws WEB3BaseException
	{
	   final String STR_METHOD_NAME = "maskPassword(String[] l_strMaskPassword) ";
	   log.entering(STR_METHOD_NAME);
        
	   if (l_strMaskPassword == null)
	   {
		   log.exiting(STR_METHOD_NAME);
		   return null;
	   }

	   String l_strPasswordModified = l_strMaskPassword;
	   if (l_strMaskPassword.length() < 8)
	   {
		   for (int i = 8; i > l_strMaskPassword.length(); i --)
		   {
			   l_strPasswordModified = l_strPasswordModified + " ";
		   }
	   }
       
	   //１）空の文字列（＝Aとする。）を生成する。
	   StringBuffer A = new StringBuffer();
        
	   //２）以下のとおりに、引数.パスワードの配列を置き換えて、
	   //１）の文字列（＝A）にセットする。
	   //A[0] = 引数.パスワード[1] 
	   //A[1] = 引数.パスワード[4] 
	   //A[2] = 引数.パスワード[7]
	   //A[3] = 引数.パスワード[6] 
	   //A[4] = 引数.パスワード[3] 
	   //A[5] = 引数.パスワード[0] 
	   //A[6] = 引数.パスワード[2] 
	   //A[7] = 引数.パスワード[5] 
	   A.append(l_strPasswordModified.charAt(1));
	   A.append(l_strPasswordModified.charAt(4));
	   A.append(l_strPasswordModified.charAt(7));
	   A.append(l_strPasswordModified.charAt(6));
	   A.append(l_strPasswordModified.charAt(3));
	   A.append(l_strPasswordModified.charAt(0));
	   A.append(l_strPasswordModified.charAt(2));
	   A.append(l_strPasswordModified.charAt(5));
       
	   if (l_strMaskPassword.length() > 8)
	   {
		   A.append(l_strMaskPassword.substring(8, l_strMaskPassword.length()));
	   }
	   log.exiting(STR_METHOD_NAME);
	   return A.toString();
	}

}
@
