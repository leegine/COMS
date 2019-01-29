head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplicationRequiredService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込要サービス(WEB3SrvRegiApplicationRequiredService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 張学剛 (中訊) 新規作成
Revesion History : 2007/06/05 孟亜南(中訊) 仕様変更モデルNo.252
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (申込要サービス)<BR>
 * 申込要サービスエンティティクラス<BR>
 *                     
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3SrvRegiApplicationRequiredService implements BusinessObject 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiApplicationRequiredService.class);
    
    /**
     * (申込要サービス行)<BR>
     */
    private SrvRegiSetupParams appliRequiredSrvParams;
    
    /**
     * (申込要サービス)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 当オブジェクトを生成し<BR>、
     * 引数.申込要サービスRowをthis.申込要サービス行に設定する。<BR>
     * @@param l_appliRequiredSrvRow - (申込要サービスRow)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 412F0431014F
     */
    protected WEB3SrvRegiApplicationRequiredService(SrvRegiSetupRow l_appliRequiredSrvRow) throws WEB3BaseException 
    {
        this.appliRequiredSrvParams = new SrvRegiSetupParams(l_appliRequiredSrvRow);
    }
    
    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.申込要サービス行を返却する。<BR>
     * @@return Object
     * @@roseuid 413309690342
     */
    public Object getDataSourceObject() 
    {
        return this.appliRequiredSrvParams;
    }
    
    /**
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * 　@this.申込要サービス行をコピーして、同じ内容の<BR>
     * 別インスタンスを作成する（clone）。<BR> 
     * 作成したコピーを自身のthis.申込要サービス行にセットする。<BR>
     * @@roseuid 413309690390
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams(this.appliRequiredSrvParams);
        this.appliRequiredSrvParams = l_srvRegiSetupParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set摘要)<BR>
     * 摘要の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set摘要()をコールする。<BR>
     * [引数]<BR>
     * 　@摘要=引数.摘要<BR>
     * @@param l_strSummary - (摘要)<BR>
     * @@roseuid 412EEBB402F5
     */
    public void setSummary(String l_strSummary) 
    {
        final String STR_METHOD_NAME = " setSummary(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSummary(l_strSummary);
        }       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get摘要)<BR>
     * 摘要を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get摘要()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EEBB40305
     */
    public String getSummary() 
    {
        final String STR_METHOD_NAME = " getSummary()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {   
            String l_strSummary = this.appliRequiredSrvParams.getSummary();
            
            log.exiting(STR_METHOD_NAME);
            return l_strSummary;
        }
        
    }
    
    /**
     * (set抽選設定)<BR>
     * 抽選設定の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set抽選設定()をコールする。<BR>
     * [引数]<BR>
     * 　@抽選設定=引数.抽選設定<BR>
     * @@param l_strLotDiv - (抽選設定)<BR>
     * @@roseuid 412EEBB40314
     */
    public void setLotDiv(String l_strLotDiv) 
    {
        final String STR_METHOD_NAME = " setLotDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setLotDiv(l_strLotDiv);
        }
        
        log.exiting(STR_METHOD_NAME);             
    }
    
    /**
     * (get抽選設定)<BR>
     * 抽選設定を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get抽選設定()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EEBB40324
     */
    public String getLotDiv() 
    {
        final String STR_METHOD_NAME = " getLotDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strLotDiv = this.appliRequiredSrvParams.getLotDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strLotDiv;
        }
    }
    
    /**
     * (set試用期間区分)<BR>
     * 試用期間区分の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set試用期間区分()をコールする。<BR>
     * [引数]<BR>
     * 　@試用期間区分=引数.試用期間区分<BR>
     * @@param l_strTrialPeriodDiv - (試用期間区分)<BR>
     * @@roseuid 412EEBB40334
     */
    public void setTrialPeriodDiv(String l_strTrialPeriodDiv) 
    {
        final String STR_METHOD_NAME = " setTrialPeriodDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setTrialPeriodDiv(l_strTrialPeriodDiv);
        } 
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (get試用期間区分)<BR>
     * 試用期間区分を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get試用期間区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EEBB40343
     */
    public String getTrialPeriodDiv() 
    {
        final String STR_METHOD_NAME = " getTrialPeriodDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strTrialPeriodDiv = this.appliRequiredSrvParams.getTrialPeriodDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strTrialPeriodDiv;
        }
    }
    
    /**
     * (set試用期間)<BR>
     * 試用期間の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set試用期間()をコールする。<BR>
     * [引数]<BR>
     * 　@試用期間=引数.試用期間<BR>
     * @@param l_trialPeriod - (試用期間)<BR>
     * @@roseuid 412EEBB40353
     */
    public void setTrialPeriod(Integer l_trialPeriod) 
    {
        final String STR_METHOD_NAME = " setTrialPeriod(Integer)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setTrialPeriod(l_trialPeriod);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get試用期間)<BR>
     * 試用期間を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get試用期間()の戻り値を返す。<BR>
     * @@return Integer
     * @@roseuid 412EEBB40372
     */
    public Integer getTrialPeriod() 
    {
        final String STR_METHOD_NAME = " getTrialPeriod()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getTrialPeriodIsNull())
            {
                return null;
            }
            Integer l_trialPeriod = new Integer(this.appliRequiredSrvParams.getTrialPeriod());
            
            log.exiting(STR_METHOD_NAME);
            return l_trialPeriod;
        }
    }
    
    /**
     * (set申込可能期間（自）)<BR>
     * 申込可能期間（自）の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set申込可能期間（自）()をコールする。<BR>
     * [引数]<BR>
     * 　@申込可能期間（自）=引数.申込可能期間（自）<BR>
     * @@param l_appliDateFrom - (申込可能期間（自）)<BR>
     * @@roseuid 412EEBB40382
     */
    public void setAppliDateFrom(Integer l_appliDateFrom) 
    {
        final String STR_METHOD_NAME = " setAppliDateFrom(Integer)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setAppliDateFrom(l_appliDateFrom);
        } 
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get申込可能期間（自）)<BR>
     * 申込可能期間（自）を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get申込可能期間（自）()の戻り値を返す。<BR>
     * @@return Integer
     * @@roseuid 412EEBB40391
     */
    public Integer getAppliDateFrom() 
    {
        final String STR_METHOD_NAME = " getAppliDateFrom()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getAppliDateFromIsNull())
            {
                return null;
            }
            Integer l_appliDateFrom= new Integer(this.appliRequiredSrvParams.getAppliDateFrom());
            
            log.exiting(STR_METHOD_NAME);
            return l_appliDateFrom;
        }
    }
    
    /**
     * (set申込可能期間（至）)<BR>
     * 申込可能期間（至）の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set申込可能期間（至）()をコールする。<BR>
     * [引数]<BR>
     * 　@申込可能期間（至）=引数.申込可能期間（至）<BR>
     * @@param l_appliDateTo - (申込可能期間（自）)<BR>
     * @@roseuid 412EEBB403A1
     */
    public void setAppliDateTo(Integer l_appliDateTo) 
    {
        final String STR_METHOD_NAME = " setAppliDateTo(Integer)";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setAppliDateTo(l_appliDateTo);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (get申込可能期間（至）)<BR>
     * 申込可能期間（至）を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get申込可能期間（至）()の戻り値を返す。<BR>
     * @@return Integer
     * @@roseuid 412EEBB403B1
     */
    public Integer getAppliDateTo() 
    {
        final String STR_METHOD_NAME = " getAppliDateTo()";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME); 
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getAppliDateToIsNull())
            {
                return null;
            }
            Integer l_appliDateTo = new Integer(this.appliRequiredSrvParams.getAppliDateTo());
            
            log.exiting(STR_METHOD_NAME); 
            return l_appliDateTo;
        }
    }
    
    /**
     * (setサービス内容)<BR>
     * サービス内容の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.setサービス内容()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス内容=引数.サービス内容<BR>
     * @@param l_strSrvContents - (サービス内容)<BR>
     * @@roseuid 412EEBB403C0
     */
    public void setSrvContents(String l_strSrvContents) 
    {
        final String STR_METHOD_NAME = " setSrvContents(String)";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSrvContents(l_strSrvContents);
        } 
        
        log.exiting(STR_METHOD_NAME);             
    }
    
    /**
     * (getサービス内容)<BR>
     * サービス内容を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.getサービス内容()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EEBB50017
     */
    public String getSrvContents() 
    {
        final String STR_METHOD_NAME = " getSrvContents()";
        log.entering(STR_METHOD_NAME);
         
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME); 
            return null;
        }
        else
        {
            String l_strSrvContents = this.appliRequiredSrvParams.getSrvContents();
            
            log.exiting(STR_METHOD_NAME); 
            return l_strSrvContents;
        }        
    }
    
    /**
     * (setサービス説明URL)<BR>
     * サービス説明URLの設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.setサービス説明URL()をコールする。<BR>
     * [引数]<BR>
     * 　@　@サービス説明URL=引数.サービス説明URL<BR>
     * @@param l_strSrvExplanUrl - (サービス説明URL)<BR>
     * @@roseuid 412EEBB50026
     */
    public void setSrvExplanUrl(String l_strSrvExplanUrl) 
    {
        final String STR_METHOD_NAME = " setSrvExplanUrl(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSrvExplanUrl(l_strSrvExplanUrl);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (getサービス説明URL)<BR>
     * サービス説明URLを返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.getサービス説明URL()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EEBB50036
     */
    public String getSrvExplanUrl() 
    {
        final String STR_METHOD_NAME = " getSrvExplanUrl()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);  
            return null;
        }
        else
        {
            String l_strSrvExplanUrl = this.appliRequiredSrvParams.getSrvExplanUrl();
            
            log.exiting(STR_METHOD_NAME);
            return l_strSrvExplanUrl;
        }
    }
    
    /**
     * (set確認メール)<BR>
     * 確認メールの設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set確認メール()をコールする。<BR>
     * [引数]<BR>
     * 　@確認メール=引数.確認メール<BR>
     * @@param l_strStartMailDiv - (確認メール)<BR>
     * @@roseuid 412EEBB50046
     */
    public void setStartMailDiv(String l_strStartMailDiv) 
    {
        final String STR_METHOD_NAME = " setStartMailDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setStartMailDiv(l_strStartMailDiv);
        }
        
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * (get確認メール)<BR>
     * 確認メールを返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get確認メール()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EEBB50065
     */
    public String getStartMailDiv() 
    {
        final String STR_METHOD_NAME = " getStartMailDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strStartMailDiv = this.appliRequiredSrvParams.getStartMailDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strStartMailDiv;
        }
    }
    
    /**
     * (set契約期限メール)<BR>
     * 契約期限メールの設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.set契約期限メール()をコールする。<BR>
     * [引数]<BR>
     * 　@　@契約期限メール=引数.契約期限メール<BR>
     * @@param l_strEndMailDiv - (契約期限メール)<BR>
     * @@roseuid 412EEBB50074
     */
    public void setEndMailDiv(String l_strEndMailDiv) 
    {
        final String STR_METHOD_NAME = " setEndMailDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setEndMailDiv(l_strEndMailDiv);
        } 
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get契約期限メール)<BR>
     * 契約期限メールを返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.get契約期限メール()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EEBB50084
     */
    public String getEndMailDiv() 
    {
        final String STR_METHOD_NAME = " getEndMailDiv()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            String l_strEndMailDiv = this.appliRequiredSrvParams.getEndMailDiv();
            
            log.exiting(STR_METHOD_NAME);
            return l_strEndMailDiv;
        }
    }
    
    /**
     * (setメール送信日)<BR>
     * メール送信日）の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.setメール送信日()をコールする。<BR>
     * [引数]<BR>
     * 　@　@メール送信日=引数.メール送信日<BR>
     * @@param l_sendMailInterval - (メール送信日)<BR>
     * @@roseuid 412EEBB50094
     */
    public void setSendMailInterval(Integer l_sendMailInterval) 
    {
        final String STR_METHOD_NAME = " setSendMailInterval(Integer)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSendMailInterval(l_sendMailInterval);
        }
        
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (getメール送信日)<BR>
     * メール送信日を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、<BR>
     * 　@this.申込要サービス行.getメール送信日()の戻り値を返す。<BR>
     * @@return Integer
     * @@roseuid 412EEBB500B3
     */
    public Integer getSendMailInterval() 
    {
        final String STR_METHOD_NAME = " getSendMailInterval()";
        log.entering(STR_METHOD_NAME);
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            if(this.appliRequiredSrvParams.getSendMailIntervalIsNull())
            {
                return null;
            }
            
            Integer l_sendMailInterval = new Integer(this.appliRequiredSrvParams.getSendMailInterval());
            
            log.exiting(STR_METHOD_NAME);
            return l_sendMailInterval;
        }
    }
    
    /**
     * (set電子鳩条件設定区分)<BR>
     * 電子鳩条件設定区分の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合<BR>
     *  1-1) 引数.電子鳩条件設定区分=trueの場合<BR>
     * 　@this.申込要サービス行.set電子鳩条件設定区分()をコールする。<BR>
     * [引数]<BR>
     * 　@　@電子鳩条件設定区分="有"<BR>
     * <BR>
     *  1-2) 引数.電子鳩条件設定区分=falseの場合<BR>
     * 　@this.申込要サービス行.set電子鳩条件設定区分()をコールする。<BR>
     * [引数]<BR>
     * 　@　@電子鳩条件設定区分="無"<BR>
     * @@param l_blnElectricIssueDiv - (電子鳩条件設定区分)<BR>
     * @@roseuid 412EEBB500C3
     */
    public void setElectricIssueDiv(boolean l_blnElectricIssueDiv) 
    {
        final String STR_METHOD_NAME = " setElectricIssueDiv(boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams != null)
        {
            if (l_blnElectricIssueDiv == true)
            {
                this.appliRequiredSrvParams.setElectricIssueDiv(WEB3ConditionsValueDivDef.HAVE);
            }
            else
            {
                this.appliRequiredSrvParams.setElectricIssueDiv(WEB3ConditionsValueDivDef.HAVE_NOT);
            }
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (is電子鳩条件設定)<BR>
     * 当該サービスの申込条件に、電子鳩が設定されているかを返却する。<BR>
     * （true:設定されている　@false:設定されていない）<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合<BR>
     * 　@falseを返却する。<BR>
     * <BR>
     * 2) this.申込要サービス行がnullではない場合<BR>
     *  2-1) this.申込要サービス行.get電子鳩条件設定区分( )="無"or"null"の場合<BR>
     * 　@　@　@falseを返却する。<BR>
     *  2-2) this.申込要サービス行.get電子鳩条件設定区分( )="有"の場合<BR>
     * 　@　@　@trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 412EEBB501EB
     */
    public boolean isElectricIssue() 
    {
        final String STR_METHOD_NAME = " isElectricIssue()";
        log.entering(STR_METHOD_NAME);
        
        if (this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
        	//仕様変更　@NO_199
            if (this.appliRequiredSrvParams.getElectricIssueDiv() == null ||
            	WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.appliRequiredSrvParams.getElectricIssueDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        } 
    }
    
    /**
     * (set提供形式)<BR>
     * 1) this.申込要サービス行がnullでない場合、<BR> 
　@   *   this.申込要サービス行.set提供形式()をコールする。<BR>
     * [引数]<BR>
　@   * 提供形式=引数.提供形式<BR>
     * @@param l_strSupplyDiv - (提供形式)<BR>
     */
    public void setSupplyDiv(String l_strSupplyDiv)
    {
        final String STR_METHOD_NAME = " setSupplyDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        if(this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setSupplyDiv(l_strSupplyDiv);
        }
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (get提供形式)<BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR> 
     * 2) this.申込要サービス行がnullでない場合、<BR> 
　@   *  this.申込要サービス行.get提供形式()の戻り値を返す。<BR>
     * @@return String
     */
    public String getSupplyDiv()
    {
        final String STR_METHOD_NAME = " getSupplyDiv()";
        log.entering(STR_METHOD_NAME);
        
        String l_strProvidModel = "";
        
        if(this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);  
            return null;
        }
        else
        {   
            l_strProvidModel = this.appliRequiredSrvParams.getSupplyDiv();
        }
        
        log.exiting(STR_METHOD_NAME);  
        return l_strProvidModel;
    }
    
    /**
     * (set基準手数料合計額)<BR>
     * 1) this.申込要サービス行がnullでない場合、<BR> 
　@   *    this.申込要サービス行.set基準手数料合計額()をコールする<BR>。 
     *    [引数]<BR> 
　@   *    基準手数料合計額=引数.基準手数料合計額<BR> 
     * @@param l_dblMinCommAmt - (基準手数料合計額)<BR>
     */
    public void setMinCommAmt(double l_dblMinCommAmt)
    {
        final String STR_METHOD_NAME = " setMinCommAmt(double)";
        log.entering(STR_METHOD_NAME);
        
        if(this.appliRequiredSrvParams != null)
        {
            this.appliRequiredSrvParams.setMinCommAmt((long)l_dblMinCommAmt);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get基準手数料合計額)<BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。<BR> 
     * 2) this.申込要サービス行がnullでない場合、<BR> 
　@   *    this.申込要サービス行.get基準手数料合計額()の戻り値を返す。<BR>
     * @@return String
     */
    public String getMinCommAmt()
    {
        final String STR_METHOD_NAME = " getMinCommAmt()";
        log.entering(STR_METHOD_NAME);
        
        String l_strMinCommAmt = "";
        if(this.appliRequiredSrvParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            l_strMinCommAmt = String.valueOf(this.appliRequiredSrvParams.getMinCommAmt());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strMinCommAmt;
    }
    
    
    /**
     * (createNew申込要サービス)<BR>
     * 新規に申込要サービスオブジェクトを生成して返却する。<BR>
     * <BR>
     * 1) 申込要サービスParamsオブジェクトを生成する。<BR>
     * <BR>
     * 2) 申込要サービスParams.set証券会社コード( )をコールする。<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * 3) 申込要サービスParams.setサービス区分( )をコールする。<BR>
     * [引数]<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 4) 申込要サービスのコンストラクタをコールし、生成した<BR>
     * 　@申込要サービスオブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@申込要サービスRow=生成した申込要サービスParams<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService
     * @@roseuid 413E6349033D
     */
    public static WEB3SrvRegiApplicationRequiredService createNewAppliRequiredSrv(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewAppliRequiredSrv(String l_strInstitutionCode, String l_strSrvDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1) 申込要サービスParamsオブジェクトを生成する。
        SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
        
        //2) 申込要サービスParams.set証券会社コード( )をコールする。
        l_srvRegiSetupParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) 申込要サービスParams.setサービス区分( )をコールする。
        l_srvRegiSetupParams.setSrvDiv(l_strSrvDiv);
        
        //4) 申込要サービスのコンストラクタをコールし、生成した申込要サービスオブジェクトを返却する。
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService = new WEB3SrvRegiApplicationRequiredService(l_srvRegiSetupParams);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_srvRegiApplicationRequiredService;
    }
    
    /**
     * (save申込要サービス)<BR>
     * this.申込要サービス行オブジェクトの<BR>
     * 内容をデータベースに反映させる。(Update)<BR>
     * <BR>
     * 1) this.申込要サービス行オブジェクトに、以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.申込要サービス行オブジェクトの内容で、<BR>
     * 　@申込要サービステーブルを更新（Update）する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E6349035C
     */
    public void saveAppliRequiredSrv() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveAppliRequiredSrv()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.申込要サービス行オブジェクトに、以下の値をセットする。
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.appliRequiredSrvParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //更新日付
        this.appliRequiredSrvParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.申込要サービス行オブジェクトの内容で、申込要サービステーブルを更新（Update）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doUpdateQuery(this.appliRequiredSrvParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     * (saveNew申込要サービス)<BR>
     * this.申込要サービス行オブジェクトの<BR>
     * 内容をデータベースに反映させる。(Insert)<BR>
     * <BR>
     * 1) this.申込要サービス行オブジェクトに、以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.申込要サービス行オブジェクトの内容で、<BR>
     * 　@申込要サービステーブルを更新（Insert）する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E6349036C
     */
    public void saveNewAppliRequiredSrv() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewAppliRequiredSrv()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.申込要サービス行オブジェクトに、以下の値をセットする。
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.appliRequiredSrvParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //作成日付
        this.appliRequiredSrvParams.setCreatedTimestamp(l_tsSystemTime);
        //更新日付
        this.appliRequiredSrvParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.申込要サービス行オブジェクトの内容で、申込要サービステーブルを更新（Insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(this.appliRequiredSrvParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * (set無料対象期間)<BR>
     * 無料対象期間の設定を行う。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullでない場合、 <BR>
     * this.申込要サービス行.set無料対象期間()をコールする。<BR>
     * [引数] <BR>
     * 無料対象期間=引数.無料対象期間<BR>
     * <BR>
     * @@param l_strFreeTargetPeriod - (無料対象期間)<BR>
     */
    public void setFreeTargetPeriod(String l_strFreeTargetPeriod)
    {
        final String STR_METHOD_NAME = " setFreeTargetPeriod(String)";
        log.entering(STR_METHOD_NAME);

        // this.申込要サービス行がnullでない場合、
        if (this.appliRequiredSrvParams != null)
        {
        	//引数.無料対象期間 == nullの場合
        	if (l_strFreeTargetPeriod == null)
        	{
        		this.appliRequiredSrvParams.setFreeCoverageLength(null);	
        	}
        	else
        	{
        		this.appliRequiredSrvParams.setFreeCoverageLength(Integer.parseInt(l_strFreeTargetPeriod));	
        	}
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get無料対象期間)<BR>
     * 無料対象期間を返す。<BR>
     * <BR>
     * 1) this.申込要サービス行がnullの場合、nullを返す。 <BR>
     * <BR>
     * 2) this.申込要サービス行がnullでない場合、 <BR>
     * this.申込要サービス行.get無料対象期間()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     */
    public String getFreeTargetPeriod()
    {
        final String STR_METHOD_NAME = " getFreeTargetPeriod()";
        log.entering(STR_METHOD_NAME);

        String l_strFreeTargetPeriod = null;

        //this.申込要サービス行がnullでない場合
        if (this.appliRequiredSrvParams != null)
        {
            if (!this.appliRequiredSrvParams.getFreeCoverageLengthIsNull())
            {
                int l_intFreeCoverageLength = this.appliRequiredSrvParams.getFreeCoverageLength();
                l_strFreeTargetPeriod = String.valueOf(l_intFreeCoverageLength); 
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strFreeTargetPeriod;
    }
}
@
