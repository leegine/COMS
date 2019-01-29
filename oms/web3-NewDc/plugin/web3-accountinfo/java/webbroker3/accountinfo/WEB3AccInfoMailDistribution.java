head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistribution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 案内メール配信指示クラス(WEB3AccInfoMailDistribution)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.accountinfo.data.InformationMailRequestDao;
import webbroker3.accountinfo.data.InformationMailRequestParams;
import webbroker3.accountinfo.data.InformationMailRequestRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (案内メール配信指示クラス)<BR>
 * 案内メール配信指示クラス<BR>
 *
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoMailDistribution implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailDistribution.class);
    /**
     * (案内メール配信指示行)<BR>
     * 案内メール配信指示行オブジェクト<BR>
     * <BR>
     * ※案内メール配信指示ParamsクラスはDDLより自動生成する。<BR>
     */
    private InformationMailRequestParams informationMailRequestParams;
    
    /**
     * (案内メールの識別ＩＤ)<BR>
     * 案内メールの識別ＩＤ<BR>
     */
    public static final String DISCERNMENTID = "----";
    /**
     * (案内メールの送信メール区分)<BR>
     * 案内メールの送信メール区分<BR>
     */
    public static final String SENDMAILDIV = "0401";
    
    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.案内メール配信指示行を返却する。  <BR>
     * @@return Object
     * @@roseuid 413D6E360076
     */
    public Object getDataSourceObject()
    {
        return this.informationMailRequestParams;
    }
    
    /**
     * (get証券会社コード)<BR>
     * this.案内メール配信指示行.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getInstitutionCode()
    {
        return this.informationMailRequestParams.getInstitutionCode();
    }
    /**
     * (get識別ＩＤ)<BR>
     * this.案内メール配信指示行.識別ＩＤを返却する。<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getDiscernmentId()
    {
        return this.informationMailRequestParams.getDiscernmentId();
    }
    /**
     * (get送信メール区分)<BR>
     * this.案内メール配信指示行.送信メール区分を返却する。<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getSendMailDiv()
    {
        return this.informationMailRequestParams.getSendmailDev();
    }
    /**
     * (is全顧客)<BR>
     * this.案内メール配信指示行.全顧客フラグに対応するboolean値を返却する。<BR>
     * @@return boolean
     * @@roseuid 413D9F040113
     */
    public boolean isAllAccount()
    {
        BooleanEnum l_booleanEnum = this.informationMailRequestParams.getAllAccountFlag();
        if(BooleanEnum.TRUE.equals(l_booleanEnum))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    /**
     * (get配信指示日時)<BR>
     * this.案内メール配信指示行.配信指示日時を返却する。<BR>
     * @@return Date
     * @@roseuid 413D9F040113
     */
    public Date getRequestTimestamp()
    {
        return this.informationMailRequestParams.getRequestTimestamp();
    }
    /**
     * (is配信済)<BR>
     * email配信済みかを判定する。<BR>
     *（this.案内メール配信指示行.配信ステータス == 1：処理済）の場合true<BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 413D9F040113
     */
    public boolean isMailRequestStatus()
    {
        String l_strStatus = this.informationMailRequestParams.getStatus();
        if(WEB3StatusDef.DEALT.equals(l_strStatus))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * (get更新者コード)<BR>
     * this.案内メール配信指示行.更新者コードを返却する。<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getLastUpdater()
    {
        return this.informationMailRequestParams.getLastUpdater();
    }
    /**
     * (get配信顧客数)<BR>
     * this.案内メール配信指示行.配信顧客数を返却する。<BR>
     * @@return long
     * @@roseuid 413D9F040113
     */
    public long getAccountCount()
    {
        return this.informationMailRequestParams.getAccountCount();
    }
    /**
     * (get案内メール件名)<BR>
     * 案内メールの件名を取得する。<BR>
     * 以下の条件でメールテーブルを検索する。<BR>
     * [条件]<BR>
　@   * メールテーブル.証券会社コード = 証券会社コード And<BR>
　@   * メールテーブル.送信メール区分 = 案内メール配信指示.案内メール送信メール区分 And<BR>
　@   * メールテーブル.識別ＩＤ = 案内メール配信指示.案内メール識別ＩＤ<BR>
     * 該当行の件名を返却する。<BR>
     * 該当行が存在しない場合、案内メールがメールテーブルに存在しないと判断し、例外をスローする。<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getMailSubject() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMailSubject()";
        log.entering(STR_METHOD_NAME);
        
        //顧客マスタテーブルを以下の条件で検索する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");        
        l_sbWhere.append(" and sendmail_div = ? ");       
        l_sbWhere.append(" and discernment_id = ? ");       
        
        List l_lisWhere = new ArrayList();
        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strSendMailDiv = WEB3AccInfoMailDistribution.SENDMAILDIV;
        String l_strDiscernId = WEB3AccInfoMailDistribution.DISCERNMENTID;
        l_lisWhere.add(l_strInstitutionCode);
        l_lisWhere.add(l_strSendMailDiv);
        l_lisWhere.add(l_strDiscernId);
        
        Object[] l_objWhere = l_lisWhere.toArray();

        List l_lisRecords = null;
        QueryProcessor l_queryProcessor = null; 
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MailInfoRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_e) 
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        int l_intSize = l_lisRecords.size();
        if (l_intSize != 1)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01379,
                                this.getClass().getName() + STR_METHOD_NAME,
                                "案内メールの件名が存在しない場合 " );

        }

        MailInfoRow[] l_row = new MailInfoRow[l_intSize];
        l_lisRecords.toArray(l_row);

        String l_strSubject = l_row[0].getSubject();
        
        log.exiting(STR_METHOD_NAME);    
        return l_strSubject;
    }
    /**
     * (案内メール配信指示)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の行オブジェクトをプロパティにセットする。 <BR>
     * @@param l_informationMailRequestParams - 案内メール配信指示行オブジェクト<BR>
     * <BR>
     * @@roseuid 413D999E0057
     */
    public WEB3AccInfoMailDistribution(InformationMailRequestParams l_informationMailRequestParams)
    {
        this.informationMailRequestParams = l_informationMailRequestParams;
    }
    /**
     * (案内メール配信指示)<BR>
     * コンストラクタ<BR>
     * 指定した案内メール配信指示ＩＤに該当する行を案内メール配信指示テーブルより検索する。 <BR>
     * 該当行が存在しない場合は、例外をスローする。<BR>
     * 検索結果の案内メール配信指示行オブジェクトを引数に指定して、コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     * @@param l_lngInformationMailRequestId - 案内メール配信指示ＩＤ
     * @@return WEB3AccInfoMailDistribution
     * @@roseuid 413D999E0057
     */
    public WEB3AccInfoMailDistribution(long l_lngInformationMailRequestId) throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccInfoMailDistribution(long)";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.informationMailRequestParams = (InformationMailRequestParams)InformationMailRequestDao.findRowByInformationMailRequestId(l_lngInformationMailRequestId);
            //WEB3AccInfoMailDistribution l_mailDistribution = new WEB3AccInfoMailDistribution();
            
        }
        catch (DataFindException e)
        {
            throw new NotFoundException(
                "No WEB3AccInfoMailDistribution could be found with id : " + l_lngInformationMailRequestId);
        } 
        catch (DataQueryException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                "DBへのアクセスに失敗しました"
                );
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                "DBへのアクセスに失敗しました"
                );
        }
        if (this.informationMailRequestParams == null)
        {
            //該当行がない場合は、例外をスローする
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
           
    }
    /**
     * (get案内メール配信指示ＩＤ)<BR>
     * this.案内メール配信指示.案内メール配信指示ＩＤを返却する。<BR>
     * @@return long
     * @@roseuid 413D9F040113
     */
    public long getInformationMailRequestId()
    {
        return this.informationMailRequestParams.getInformationMailRequestId();
    }
    /**
     * (get最新履歴)<BR>
     * （staticメソッド）<BR>
     * 案内メール配信指示の最新履歴を取得する。<BR>
     * 以下の条件で案内メール配信指示テーブルを検索する。<BR>
　@   * [条件]<BR>
　@   * 案内メール配信指示テーブル.証券会社コード = 証券会社コード And<BR>
　@   * 案内メール配信指示テーブル.送信メール区分 = 案内メール配信指示.案内メール送信メール区分 And<BR>
　@   * 案内メール配信指示テーブル.識別ＩＤ = 案内メール配信指示.案内メール識別ＩＤ<BR>
　@   * [取得順]<BR>
　@   * 配信指示日時 降順（：desc）　@<BR>
     * 検索結果の１番目（index=0）を指定し、案内メール配信指示オブジェクトを生成する。<BR>
     * 生成したオブジェクトを返却する。<BR>
     * 該当行がない場合はnullを返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     *<BR>
     * @@return webbroker3.accountinfo.WEB3AccInfoMailDistribution<BR>
     * @@roseuid 413D9F040113
     */
    public static WEB3AccInfoMailDistribution getLastAction(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastAction(String)";
        log.entering(STR_METHOD_NAME);
        
        //顧客マスタテーブルを以下の条件で検索する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");        
        l_sbWhere.append(" and sendmail_dev = ? ");       
        l_sbWhere.append(" and discernment_id = ? ");       
        
        //取得順（order by）
        StringBuffer l_sbOrderBy = new StringBuffer();
        l_sbOrderBy.append(" request_timestamp desc ");

        
        List l_lisWhere = new ArrayList();
        String l_strSendMailDiv = WEB3AccInfoMailDistribution.SENDMAILDIV;
        
        String l_strDiscernId = WEB3AccInfoMailDistribution.DISCERNMENTID;
        l_lisWhere.add(l_strInstitutionCode);
        l_lisWhere.add(l_strSendMailDiv);
        l_lisWhere.add(l_strDiscernId);
        
        Object[] l_objWhere = l_lisWhere.toArray();

        List l_lisRecords = null;
        QueryProcessor l_queryProcessor = null; 
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformationMailRequestRow.TYPE,
                l_sbWhere.toString(),
                l_sbOrderBy.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_e) 
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);  
            return null;
        }
        else
        {
            InformationMailRequestParams l_params = (InformationMailRequestParams)l_lisRecords.get(0);
            WEB3AccInfoMailDistribution l_mailDistribution = new WEB3AccInfoMailDistribution(l_params);
            log.exiting(STR_METHOD_NAME);    
            return l_mailDistribution;    
        }
        
        
    }
    /**
     * (createNew案内メール配信指示)<BR>
     * （static メソッド）<BR>
     * 案内メール配信指示新規行を生成する。<BR>
     * １）　@行オブジェクト生成<BR>
　@   * 案内メール配信指示Paramsオブジェクトを生成する。<BR>
　@   * ※案内メール配信指示ParamsはDDLより自動生成する。<BR>
     * ２）　@行オブジェクトにプロパティをセットする。 <BR>
　@   * １）で生成した案内メール配信指示Paramsオブジェクトのプロパティに、以下の通りセットを行う。 <BR>
　@   *     案内メール配信指示Params.案内メール配信指示ＩＤ = 新規採番(*1)<BR>
　@   *     案内メール配信指示Params.証券会社コード = 証券会社コード<BR>
　@   *     案内メール配信指示Params.送信メール区分 = 案内メール配信指示.案内メール送信メール区分<BR>
　@   *     案内メール配信指示Params.識別ID = 案内メール配信指示.案内メール識別ＩＤ<BR>
　@   *     案内メール配信指示Params.全顧客フラグ = is全顧客に対応するBooleanEnum値<BR>
　@   *     案内メール配信指示Params.配信顧客数 = 案内メール配信指示.calc配信顧客数(is全顧客)<BR>
　@   *     案内メール配信指示Params.配信指示日時 = TradingSystem.getSystemTimestamp() <BR>
　@   *     案内メール配信指示Params.配信ステータス = 0：未処理（Email未配信）<BR>
　@   *     案内メール配信指示Params.更新者コード = 更新者コード<BR>
　@   *     案内メール配信指示Params.作成日時 = TradingSystem.getSystemTimestamp() <BR>
　@   *     案内メール配信指示Params.更新日時 = TradingSystem.getSystemTimestamp() <BR>
　@   *  (*1) 案内メール配信指示ＩＤの新規採番 <BR>
　@   *        案内メール配信指示DAO.newPkValue()メソッドにて取得する。 <BR>
　@   *        ※ 案内メール配信指示DAOクラスはDDLより自動生成する。 <BR>
     * ３）　@案内メール配信指示オブジェクト返却<BR>
　@   *       行オブジェクトを指定し、案内メール配信指示オブジェクトを生成し返却する。<BR>
　@   *       [コンストラクタの引数]<BR>
　@   *       案内メール配信指示行：　@（２）で生成した行オブジェクト）<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_blnAllAccountFlag - is全顧客<BR>
     * @@param l_strLastUpdater - 更新者コード<BR>
     *<BR>
     * @@return webbroker3.accountinfo.WEB3AccInfoMailDistribution<BR>
     * @@roseuid 413D9F040113
     */
    public static WEB3AccInfoMailDistribution createNewMailDistribution(String l_strInstitutionCode, 
        boolean l_blnAllAccountFlag, String l_strLastUpdater) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNewMailDistribution(String, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        InformationMailRequestParams l_mailRequestParams = new InformationMailRequestParams();
        long l_lngInformationMailRequestId = 0L;
        try
        {
            l_lngInformationMailRequestId = InformationMailRequestDao.newPkValue();
        }
        catch (DataQueryException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        l_mailRequestParams.setInformationMailRequestId(l_lngInformationMailRequestId);
        l_mailRequestParams.setInstitutionCode(l_strInstitutionCode);
        l_mailRequestParams.setSendmailDev(WEB3AccInfoMailDistribution.SENDMAILDIV);
        l_mailRequestParams.setDiscernmentId(WEB3AccInfoMailDistribution.DISCERNMENTID);
        if (l_blnAllAccountFlag)
        {
            l_mailRequestParams.setAllAccountFlag(BooleanEnum.TRUE);
        }
        else 
        {
            l_mailRequestParams.setAllAccountFlag(BooleanEnum.FALSE);
        }
        l_mailRequestParams.setAccountCount(WEB3AccInfoMailDistribution.calcAccountCount(l_strInstitutionCode, l_blnAllAccountFlag));
        Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
        l_mailRequestParams.setRequestTimestamp(l_systemTimestamp);
        l_mailRequestParams.setCreatedTimestamp(l_systemTimestamp);
        l_mailRequestParams.setLastUpdatedTimestamp(l_systemTimestamp);
        l_mailRequestParams.setLastUpdater(l_strLastUpdater);
        l_mailRequestParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
        
        WEB3AccInfoMailDistribution l_mailDistribution = new WEB3AccInfoMailDistribution(l_mailRequestParams);
        
        log.exiting(STR_METHOD_NAME);        
        return l_mailDistribution;
        
    }
    /**
     * (calc配信顧客数)<BR>
     * （static メソッド）<BR>
     * 案内メール配信対象顧客数を計算する。<BR>
     * <BR>
     * 顧客マスタを以下の条件で検索し、一致する行数を返却する。<BR>
     * －全顧客の場合※1<BR>
     * <BR>
     * [条件]<BR>
     * 顧客マスタ.証券会社コード = this.get証券会社コード()<BR>
     * －希望客の場合※2<BR>
     * <BR>
     * [条件]<BR>
     * 顧客マスタ.証券会社コード = this.get証券会社コード()　@And<BR>
     * 顧客マスタ.案内メール送信フラグ = BooleanEnum.TRUE<BR>
     * ※1 全顧客の判定：　@（this.is全顧客() == true）<BR>
     * ※2 希望客の判定：　@（this.is全顧客() == false）<BR>
     * <BR>
     * @@param l_blnAllAccountFlag - is全顧客<BR>
     * <BR>
     * @@return long <BR>
     * @@roseuid 413D9F040113
     */
    public static long calcAccountCount(String l_strInstitutionCode, boolean l_blnAllAccountFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMailSubject()";
        log.entering(STR_METHOD_NAME);
        
        if (l_blnAllAccountFlag)
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");        

            List l_lisWhere = new ArrayList();
            //String l_strInstitutionCode = this.getInstitutionCode();

            l_lisWhere.add(l_strInstitutionCode);
        
            Object[] l_objWhere = l_lisWhere.toArray();

            QueryProcessor l_queryProcessor = null; 
            long l_intSize = 0;
            try 
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_intSize = l_queryProcessor.doGetCountQuery(
                    MainAccountRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataFindException l_e) 
            {
                log.error("テーブルに該当するデータがありません。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }  
            catch (DataQueryException l_e) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            } 
            catch (DataNetworkException l_e) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }

            log.exiting(STR_METHOD_NAME);    
            return l_intSize;
        }
        else 
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");        
            l_sbWhere.append(" and information_mail_flag = ? ");
            
            List l_lisWhere = new ArrayList();
            //String l_strInstitutionCode = this.getInstitutionCode();

            l_lisWhere.add(l_strInstitutionCode);
            l_lisWhere.add(BooleanEnum.TRUE);
        
            Object[] l_objWhere = l_lisWhere.toArray();

            QueryProcessor l_queryProcessor = null; 
            long l_intSize = 0; 
            try 
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_intSize = l_queryProcessor.doGetCountQuery(
                    MainAccountRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataFindException l_e) 
            {
                log.error("テーブルに該当するデータがありません。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }  
            catch (DataQueryException l_e) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            } 
            catch (DataNetworkException l_e) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            }
        

            log.exiting(STR_METHOD_NAME);    
            return l_intSize;
        }

    }
    
    
    
    
}
@
