head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3Faq.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ(WEB3Faq)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.faq.data.FaqDao;
import webbroker3.faq.data.FaqParams;
import webbroker3.faq.data.FaqRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (問合せ)<BR>
 * 問合せ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3Faq implements BusinessObject 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3Faq.class);
    
    /**
     * (問合せ行)<BR>
     * 問合せ行オブジェクト<BR>
     * <BR>
     * ※問合せParamsクラスはDDLより自動生成する。<BR>
     */
    private FaqParams faqParams;
    
    /**
     * (問合せ)<BR>
     * コンストラクタ。<BR>
     * 問合せオブジェクトを生成する。<BR>
     * <BR>
     * １）　@行オブジェクト取得<BR>
     * 　@以下の条件で、問合せテーブルを検索する。<BR>
     * 　@該当データがない場合は、例外をスローする。<BR>
     *          class: NotFoundException<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@問合せ.証券会社コード = 証券会社コード And<BR>
     * 　@問合せ.問合せコード = 問合せコード<BR>
     * <BR>
     * ２）　@問合せ行プロパティのセット<BR>
     * 　@検索結果の行オブジェクトをオブジェクトのプロパティにセットし、返却する。<BR>
     * <BR>
     * ※ 問合せParamsクラスはDDLより自動生成する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strFaqNumber - (問合せコード)<BR>
     * 問合せコード<BR>
     * @@return webbroker3.faq.WEB3Faq
     * @@roseuid 41AC128B0270
     */
    public WEB3Faq(String l_strInstitutionCode, String l_strFaqNumber) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3Faq(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null || l_strFaqNumber == null)
        {
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);          
        }
        
        try
        {
            //以下の条件で、問合せテーブルを検索する。
            //[条件]
            //問合せ.証券会社コード = 証券会社コード And 
            //問合せ.問合せコード = 問合せコード 
            FaqParams l_faqParams = (FaqParams)FaqDao.findRowByPk(
                l_strFaqNumber,
                l_strInstitutionCode);
                
            this.faqParams = l_faqParams;
        }
        catch (DataFindException l_ex)
        {
            //該当データがない場合は、例外をスローする。 
            throw new NotFoundException(
                "該当データが存在しない。" + 
                "「証券会社コード」 = " + l_strInstitutionCode +
                "「問合せコード」 = " + l_strFaqNumber);
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
    }
    
    /**
     * (問合せ)<BR>
     * コンストラクタ。<BR>
     * 問合せオブジェクトを生成する。<BR>
     * <BR>
     * 引数の行オブジェクトのプロパティにセットする。<BR>
     * <BR>
     * ※ 問合せParamsクラスはDDLより自動生成する。<BR>
     * @@param l_faqParams - (問合せ行)<BR>
     * 問合せ行オブジェクト<BR>
     * <BR>
     * ※問合せParamsクラスはDDLより自動生成する。<BR>
     * 
     * @@return webbroker3.faq.WEB3Faq
     * @@roseuid 41AC11DC035A
     */
    public WEB3Faq(FaqParams l_faqParams) 
    {
        this.faqParams = l_faqParams;
    }
    
    /**
     * (問合せ)<BR>
     * デフォルトコンストラクタ<BR>
     * <BR>
     * this.問合せ行に空のオブジェクトを生成しセットする。<BR>
     * @@return webbroker3.faq.WEB3Faq
     * @@roseuid 41AC0A34012E
     */
    public WEB3Faq() 
    {
        this.faqParams = new FaqParams();
    }
    
    /**
     * this.問合せ管理行を返却する。<BR>
     * @@return Object
     * @@roseuid 41ABF65201BB
     */
    public Object getDataSourceObject() 
    {
        return this.faqParams;
    }
    
    /**
     * (set証券会社コード)<BR>
     * this.問合せ行.証券会社コードに証券会社コードをセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@roseuid 41ABF87702C4
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.faqParams.setInstitutionCode(l_strInstitutionCode);
    }
    
    /**
     * (set部店コード)<BR>
     * this.問合せ行.部店コードに部店コードをセットする。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@roseuid 41ABF8BC01CA
     */
    public void setBranchCode(String l_strBranchCode) 
    {
        this.faqParams.setBranchCode(l_strBranchCode);
    }
    
    /**
     * (set口座コード)<BR>
     * this.問合せ行.口座コードに口座コードをセットする。<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@roseuid 41ABF8D1016D
     */
    public void setAccountCode(String l_strAccountCode) 
    {
        this.faqParams.setAccountCode(l_strAccountCode);
    }
    
    /**
     * (set顧客名（漢字）)<BR>
     * this.問合せ行.顧客名（漢字）に顧客名（漢字）をセットする。<BR>
     * @@param l_strName - (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     * @@roseuid 41ABF8F000D0
     */
    public void setName(String l_strName) 
    {
        this.faqParams.setName(l_strName);
    }
    
    /**
     * (setメールアドレス)<BR>
     * this.問合せ行.emailアドレスにメールアドレスをセットする。<BR>
     * @@param l_strEmailAddress - (メールアドレス)<BR>
     * メールアドレス<BR>
     * @@roseuid 41ABF9200228
     */
    public void setEmailAddress(String l_strEmailAddress) 
    {
        this.faqParams.setEmailAddress(l_strEmailAddress);
    }
    
    /**
     * (set件名)<BR>
     * this.問合せ行.件名に件名をセットする。<BR>
     * @@param l_strSubject - (件名)<BR>
     * 件名<BR>
     * @@roseuid 41ABF950015D
     */
    public void setSubject(String l_strSubject) 
    {
        this.faqParams.setSubject(l_strSubject);
    }
    
    /**
     * (set問合せ内容)<BR>
     * this.問合せ行.問合せ内容に問合せ内容をセットする。<BR>
     * @@param l_strFaqText - (問合せ内容)<BR>
     * 問合せ内容<BR>
     * @@roseuid 41ABF978038F
     */
    public void setFaqText(String l_strFaqText) 
    {
        this.faqParams.setFaqText(l_strFaqText);
    }
    
    /**
     * (set機@能ＩＤ)<BR>
     * this.問合せ行.機@能ＩＤに機@能ＩＤをセットする。<BR>
     * @@param l_strTransactionId - (機@能ＩＤ)<BR>
     * 機@能ＩＤ<BR>
     * @@roseuid 41AC169C03C8
     */
    public void setTransactionId(String l_strTransactionId) 
    {
        this.faqParams.setTransactionId(l_strTransactionId);
    }
    
    /**
     * (get証券会社コード)<BR>
     * this.問合せ行.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 41ABF9A0013E
     */
    public String getInstitutionCode() 
    {
        return this.faqParams.getInstitutionCode();
    }
    
    /**
     * (get部店コード)<BR>
     * this.問合せ行.部店コードを返却する。<BR>
     * @@return String
     * @@roseuid 41ABF9BE0303
     */
    public String getBranchCode() 
    {
        return this.faqParams.getBranchCode();
    }
    
    /**
     * (get口座コード)<BR>
     * this.問合せ行.口座コードを返却する。<BR>
     * @@return String
     * @@roseuid 41ABF9CB010F
     */
    public String getAccountCode() 
    {
        return this.faqParams.getAccountCode();
    }
    
    /**
     * (get顧客名（漢字）)<BR>
     * this.問合せ行.顧客名（漢字）を返却する。<BR>
     * @@return String
     * @@roseuid 41ABF9D402D4
     */
    public String getName() 
    {
        return this.faqParams.getName();
    }
    
    /**
     * (getメールアドレス)<BR>
     * this.問合せ行.emailアドレスを返却する。<BR>
     * @@return String
     * @@roseuid 41ABF9E80312
     */
    public String getEmailAddress() 
    {
        return this.faqParams.getEmailAddress();
    }
    
    /**
     * (get件名)<BR>
     * this.問合せ行.件名を返却する。<BR>
     * @@return String
     * @@roseuid 41ABF9F601BB
     */
    public String getSubject() 
    {
        return this.faqParams.getSubject();
    }
    
    /**
     * (get問合せ内容)<BR>
     * this.問合せ行.問合せ内容を返却する。<BR>
     * @@return String
     * @@roseuid 41ABFA0403CE
     */
    public String getFaqText() 
    {
        return this.faqParams.getFaqText();
    }
    
    /**
     * (get機@能ＩＤ)<BR>
     * this.問合せ行.機@能ＩＤを返却する。<BR>
     * @@return String
     * @@roseuid 41AC168C0186
     */
    public String getTransactionId() 
    {
        return this.faqParams.getTransactionId();
    }
    
    /**
     * (get問合せ日時)<BR>
     * this.問合せ行.作成日時を返却する。<BR>
     * @@return Date
     * @@roseuid 41AC2C1600C2
     */
    public Date getFaqDatetime() 
    {
        return this.faqParams.getCreatedTimestamp();
    }
    
    /**
     * (get問合せコード)<BR>
     * this.問合せ行.問合せコードを返却する。<BR>
     * @@return String
     * @@roseuid 41AC369701FB
     */
    public String getFaqNumber() 
    {
        return this.faqParams.getFaqNumber();
    }
    
    /**
     * (saveNew問合せ)<BR>
     * 問合せテーブルを更新する。<BR>
     * <BR>
     * １） 問合せ行オブジェクト取得 <BR>
     * 　@getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ２） 更新情報をセットする。 <BR>
     * 　@問合せ行の入力データ以外の項目に値をセットする。 <BR>
     * <BR>
     * 　@問合せコード = 問合せコード<BR>
     * 　@作成日時 = TradingSystem.getSystemTimestamp()<BR>
     * 　@更新日時 = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * ３） DB更新 <BR>
     * 　@問合せ行オブジェクトの内容で、問合せテーブルを更新（insert）する。 <BR>
     * @@param l_strFaqNumber - (問合せコード)<BR>
     * 問合せコード<BR>
     * @@roseuid 41ABF6880247
     */
    public void saveNewFaq(String l_strFaqNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewFaq(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strFaqNumber == null)
        {
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);          
        }
        
        //問合せ行の入力データ以外の項目に値をセットする。 
        //問合せコード = 問合せコード
        this.faqParams.setFaqNumber(l_strFaqNumber);
        
        //作成日時 = TradingSystem.getSystemTimestamp(l_tsProcessDate) 
        Timestamp l_tsProcessDate = GtlUtils.getSystemTimestamp();
        this.faqParams.setCreatedTimestamp(l_tsProcessDate);
        
        //更新日時 = TradingSystem.getSystemTimestamp(l_tsProcessDate)
        this.faqParams.setLastUpdatedTimestamp(l_tsProcessDate);
        
        log.info("============= faqParams:" + this.faqParams);
        
        //DB更新
        //問合せ行オブジェクトの内容で、問合せテーブルを更新（insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(faqParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
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
    }
    
    /**
     * (get問合せ)<BR>
     * （static メソッド） <BR>
     * 指定に該当する問合せオブジェクトを取得する。 <BR>
     * <BR>
     * QueryProcessor.doFindAllQuery( )により、問合せ行オブジェクトのListを取得する。 <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数] <BR>
     * 　@　@rowType：　@問合せRow.TYPE <BR>
     * 　@　@where：　@検索条件文字列 <BR>
     * 　@　@orderBy：　@ソート条件 <BR>
     * 　@　@conditions：　@null <BR>
     * 　@　@bindVars：　@検索条件データコンテナ <BR>
     * <BR>
     * 該当データがない場合、例外をスローする。<BR>
     * <BR>
     * 取得結果の各行オブジェクトについて、問合せオブジェクトを生成し、<BR>
     * 配列にて返却する。<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_queryContainer - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return webbroker3.faq.WEB3Faq[]
     * @@roseuid 41AC228603E7
     */
    public static WEB3Faq[] getFaq(String l_strQueryString, Object[] l_queryContainer, String l_strSortCond)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getFaq(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_strQueryString == null || l_queryContainer == null)
        {
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);          
        }

        List l_lisRecords = null;
        try
        {
            //QueryProcessor.doFindAllQuery( )により、問合せ行オブジェクトのListを取得する。 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FaqRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3Faq.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Faq.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Faq.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("データがない");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                WEB3Faq.class.getName() + STR_METHOD_NAME);
        }
        
        int l_intSize = l_lisRecords.size();
        log.info("l_intSize = " + l_intSize);

        WEB3Faq[] l_faqs = new WEB3Faq[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_faqs[i] = new WEB3Faq((FaqParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_faqs;
    }
}
@
