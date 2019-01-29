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
filename	WEB3GentradeMailInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  メール(WEB3GentradeMailInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.MailInfoDao;
import webbroker3.gentrade.data.MailInfoPK;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.util.WEB3LogUtility;

/**
 * メール　@エンティティクラス
 */
public class WEB3GentradeMailInfo implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMailInfo.class);
        

    /**
     * メール行
     */
    private MailInfoParams mailInfoParams;

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 1) 引数.メールParamsをthis.メール行に設定する。<BR>
     * @@param l_mailInfoParams - メールParams
     * @@roseuid 413C1F1E02EE
     */
    public WEB3GentradeMailInfo(MailInfoParams l_mailInfoParams)
    {
        final String STR_METHOD_NAME = "WEB3GentradeMailInfo(MailInfoParams)";
        if (l_mailInfoParams == null)
        {
            log.error("メール行 = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "メール行 = null");
        }
        this.mailInfoParams = l_mailInfoParams;
    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 1) メールテーブルを検索しメールParamsを取得する。<BR>
     * [検索条件]<BR>
     * 証券会社コード=引数.証券会社コード and<BR>
     * 送信メール区分=引数.送信メール区分 and<BR>
     * 識別ID=引数.識別ID<BR>
     *  <BR>
     * 2) メールParamsが取得できた場合は、以下の処理を行う。<BR>
     * 2-1) 取得したメールParamsをthis.メール行に設定する。<BR>
     *  <BR>
     * 3) 出来なかった場合、例外をスローする。<BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strSendmailDiv - 送信メール区分
     * @@param l_strDiscernmentId - 識別ID
     * @@throws WEB3BaseException
     * @@roseuid 41049A93006D
     */
    public WEB3GentradeMailInfo(
        String l_strInstitutionCode,
        String l_strSendmailDiv,
        String l_strDiscernmentId)
	    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeMailInfo(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) メールテーブルを検索しメールParamsを取得する。
        // [検索条件]
        //  証券会社コード=引数.証券会社コード and
        //  送信メール区分=引数.送信メール区分 and
        //  識別ID=引数.識別ID     
        MailInfoRow l_mailInfoRow = null;
        try
        {
            l_mailInfoRow = MailInfoDao.findRowByPk(
                l_strInstitutionCode,
                l_strSendmailDiv,
                l_strDiscernmentId);
        }
        catch (DataFindException dfe)
        {
            log.debug("メールParamsが取得できなかった場合");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00398,
				this.getClass().getName() + "." + STR_METHOD_NAME,
                "メールテーブルを検索しメールParamsを取得しない");
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        // 取得したメールParamsをthis.メール行に設定する。
        this.mailInfoParams = new MailInfoParams(l_mailInfoRow);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.メール行を返却する。 <BR>
     * @@return Object
     * @@roseuid 413419BF01DD
     */
    public Object getDataSourceObject()
    {
        return this.mailInfoParams;
    }

    /**
     * (createForUpdateParams)<BR>
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * 　@this.メール行をコピーして、同じ内容の別インスタンスを作成する（clone）。<BR> 
     * 作成したコピーを自身のthis.メール行にセットする。<BR>
     * @@roseuid 413419BF01ED
     */
    public void createForUpdateParams()
    {
        MailInfoParams l_mailInfoParams = 
            new MailInfoParams(this.mailInfoParams);
        this.mailInfoParams = l_mailInfoParams;

    }

    /**
     * (createNewメール)<BR>
     * 新しいメールオブジェクトを生成し、返却する。<BR>
     * <BR>
     * 1) メールParamsオブジェクトを生成する。<BR>
     *  <BR>
     * 2) メールParamsオブジェクトの以下のプロパティに値をセットする。<BR>
     * 証券会社コード=引数.証券会社コード<BR>
     * 送信メール区分=引数.送信メール区分<BR>
     * 識別ID=引数.識別ID<BR>
     *  <BR>
     * 3) メールParamsオブジェクトを引数に、コンストラクタをコールし、<BR>
     * 取得したメールオブジェクトを返却する。<BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strSendmailDiv - 送信メール区分
     * @@param l_strDiscernmentId - 識別ID
     * @@return webbroker3.会社・顧客エンティティ.WEB3GentradeMailInfo
     * @@throws WEB3SystemLayerException
     * @@roseuid 413D4138007D
     */
    public static WEB3GentradeMailInfo createNewMail(
        String l_strInstitutionCode,
        String l_strSendmailDiv,
        String l_strDiscernmentId)
    {
        //1) メールParamsオブジェクトを生成する
        MailInfoParams l_mailInfoParams = new MailInfoParams();
        
        //2) メールParamsオブジェクトの以下のプロパティに値をセットする
        l_mailInfoParams.setInstitutionCode(l_strInstitutionCode);
        l_mailInfoParams.setSendmailDiv(l_strSendmailDiv);
        l_mailInfoParams.setDiscernmentId(l_strDiscernmentId);
        
        //3) メールParamsオブジェクトを引数に、コンストラクタをコールし、
        //取得したメールオブジェクトを返却する。
        return new WEB3GentradeMailInfo(l_mailInfoParams);
    }

    /**
     * (saveメール) <BR>
     * 引数.メールの内容をデータベースに反映させる。<BR>
     * <BR>
     * 1) this.メール行オブジェクトに以下の値をセットする。<BR>
     *   更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード()
     * <BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
     * <BR>
     * 2) 生成したメールParamsオブジェクトの内容で、<BR>
     * 　@メールテーブルを更新（Update）する。<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 413D4138009C
     */
    public void saveMail()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveMail()";
        log.entering(STR_METHOD_NAME);
        
        // 更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード()
        String l_strAdministratorCode =  
            WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
		//更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
                        
        //4000バイト対象外の各項目 
        Map l_comMap = new HashMap();
        //メール名称
        	l_comMap.put("mail_name",this.mailInfoParams.getMailName());
        //差出人
        	l_comMap.put("mail_sender", this.mailInfoParams.getMailSender());
        //件名
        	l_comMap.put("subject", this.mailInfoParams.getSubject());      	
        //送信先アドレス
        	l_comMap.put("send_address", this.mailInfoParams.getSendAddress());
        //更新者コード
		l_comMap.put("last_updater", l_strAdministratorCode);
        //更新日付
		l_comMap.put("last_updated_timestamp", l_tsSystemTime);
		
		//BatchedQuery[]の作成用
		ArrayList l_listQuery = new ArrayList();
        
		//4000byte対象外batchedquery作成
		BatchedQuery l_comQuery = BatchedQuery.createUpdateQuery(
				new MailInfoPK( 
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_comMap);
		l_listQuery.add(l_comQuery);
        		
        //4000byte対象項目
        //header
		Map l_header = new HashMap();
		        	
		l_header.put("mail_header", this.mailInfoParams.mail_header);
		this.mailInfoParams.mail_header = null;
			
		BatchedQuery l_updQueryHeader = 
			BatchedQuery.createUpdateQuery(
				new MailInfoPK(
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_header);        	
        	
		l_listQuery.add(l_updQueryHeader);

		//text(body)
		Map l_text = new HashMap();
		        	
		l_text.put("mail_text", this.mailInfoParams.mail_text);
		this.mailInfoParams.mail_text = null;
			
		BatchedQuery l_updQueryText = 
			BatchedQuery.createUpdateQuery(
				new MailInfoPK(
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_text);        	
        	
		l_listQuery.add(l_updQueryText);
	

		//footer
		Map l_footer = new HashMap();
		        	
		l_footer.put("mail_footer", this.mailInfoParams.mail_footer);
		this.mailInfoParams.mail_footer = null;
			
		BatchedQuery l_updQueryFooter = 
			BatchedQuery.createUpdateQuery(
				new MailInfoPK(
					this.mailInfoParams.getInstitutionCode(),
					this.mailInfoParams.getSendmailDiv(),
					this.mailInfoParams.getDiscernmentId()),
				l_footer);        	
        	
		l_listQuery.add(l_updQueryFooter);
                
        BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()]; 
        
        l_listQuery.toArray(l_queries);   
        		
        // メールテーブルを新規登録、更新(BatchedQuery)をする
        try
        {
			QueryProcessor l_processer = Processors.getDefaultProcessor();
			l_processer.doQueries(l_queries); 
        }
        catch (DataException dataEx)
        {
            log.error(STR_METHOD_NAME, dataEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dataEx.getMessage(),
                dataEx);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveNewメール)<BR>
     * 引数.メールの内容をデータベースに反映させる。(Insert)<BR>
     *  <BR>
     * 1) this.メール行オブジェクトに以下の値をセットする。<BR>
     * 　@　@更新者コード = <BR>
     *        管理者.getInstanceFromログイン情報( ).get管理者コード( ) <BR>
     * 　@　@作成日付 = <BR>
     *        GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@　@更新日付 = <BR>
     *        GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     *  <BR>
     * 2) this.メール行オブジェクトの内容で、<BR>
     * 　@メールテーブルを更新（Insert）する。<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 413D881E039A
     */
    public void saveNewMail()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveNewMail()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.メール行オブジェクトに以下の値をセットする。
        //更新者コード = 管理者.getInstanceFromログイン情報( ).get管理者コード( )         
        String l_strAdministratorCode =  
            WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.mailInfoParams.setLastUpdater(l_strAdministratorCode);
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
		//更新日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.mailInfoParams.setLastUpdatedTimestamp(l_tsSystemTime);
		//作成日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.mailInfoParams.setCreatedTimestamp(l_tsSystemTime);
        
		//4000byte対象項目
		Hashtable l_hashTable = new Hashtable();
		//BatchedQuery[]の作成用
		ArrayList l_list = new ArrayList();
       	
		//header入力あり
		if(this.mailInfoParams.mail_header != null){
			l_hashTable.put("mail_header", this.mailInfoParams.mail_header);
			this.mailInfoParams.mail_header = null;
		}
		//text入力あり
		if(this.mailInfoParams.mail_text != null){
			l_hashTable.put("mail_text", this.mailInfoParams.mail_text);
			this.mailInfoParams.mail_text = null;
		}
		//footer入力あり
		if(this.mailInfoParams.mail_footer != null){
			l_hashTable.put("mail_footer", this.mailInfoParams.mail_footer);
			this.mailInfoParams.mail_footer = null;
		}
		
		//BatchedQuery作成
		//ins（4000バイト対象外の各項目)
		BatchedQuery l_insQuery = BatchedQuery.createInsertQuery(this.mailInfoParams);
		l_list.add(l_insQuery);
		
		//upd（4000byte対象）
		Enumeration l_enum = l_hashTable.keys();
		//更新対象ありの場合、一つのマップオブジェクトに対して要素数を１にし
		//BatchedQueryオブジェクトの作成
		while(l_enum.hasMoreElements()){
        	
			String l_key = (String) l_enum.nextElement();
			
			Map l_map = new HashMap();
        	
			l_map.put(l_key, l_hashTable.get(l_key));
			
			BatchedQuery l_updQuery = 
				BatchedQuery.createUpdateQuery(
					new MailInfoPK(
						this.mailInfoParams.getInstitutionCode(),
						this.mailInfoParams.getSendmailDiv(),
						this.mailInfoParams.getDiscernmentId()),
					l_map);        	
        	
			l_list.add(l_updQuery);
        	
		}
		
		BatchedQuery[] l_queries = new BatchedQuery[l_list.size()]; 
        
		l_list.toArray(l_queries);
        
		// メールテーブルを新規登録、更新(BatchedQuery)をする
		try
		{
			QueryProcessor l_processer = Processors.getDefaultProcessor();
			l_processer.doQueries(l_queries); 
		}
		catch (DataException dataEx)
		{
			log.error(STR_METHOD_NAME, dataEx);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				dataEx.getMessage(),
				dataEx);
		}

    }

    /**
     * (removeメール)<BR>
     * メールをデータベースから削除する。<BR>
     * <BR>
     * 1) 以下を条件に、当該レコードをメールテーブルより削除する。<BR>
     * [削除条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@送信メール区分=引数.送信メール区分 and<BR>
     * 　@識別ID=引数.識別ID<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strSendmailDiv - 送信メール区分
     * @@param l_strDiscernmentId - 識別ID
     * @@roseuid 413D413800CB
     */
    public static void removeMail(
        String l_strInstitutionCode,
        String l_strSendmailDiv,
        String l_strDiscernmentId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "removeMail(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) 以下を条件に、当該レコードをメールテーブルより削除する。
        // 証券会社コード=引数.証券会社コード and
        // 送信メール区分=引数.送信メール区分 and
        // 識別ID=引数.識別ID
        try
        {
            MailInfoPK l_mailInfoPK =
                new MailInfoPK(
                    l_strInstitutionCode,
                    l_strSendmailDiv,
                    l_strDiscernmentId);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_mailInfoPK);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailInfo.class + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get証券会社コード) <BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.メール行.get証券会社コード( )の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 41049E48032D
     */
    public String getInstitutionCode()
    {
        return this.mailInfoParams.getInstitutionCode();
    }

    /**
     * (get送信メール区分) <BR>
     * 送信メール区分を返す。<BR>
     * <BR>
     * this.メール行.get送信メール区分( )の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 41049E5E0010
     */
    public String getSendmailDiv()
    {
        return this.mailInfoParams.getSendmailDiv();
    }

    /**
     * (get識別ID) <BR>
     * メールIDを返す。<BR>
     * <BR>
     * this.メール行.get識別ID( )の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 41049E7D010A
     */
    public String getDiscernmentId()
    {
        return this.mailInfoParams.getDiscernmentId();
    }

    /**
     * (setメール名称) <BR>
     * メール名称の設定を行う。<BR>
     * <BR>
     * 1) this.メール行.setメール名称( )をコールする。<BR>
     * [引数]<BR>
     * 　@メール名称=引数.メール名称<BR>
     * @@param l_strMailName - メール名称
     * @@roseuid 413C4B440036
     */
    public void setMailName(String l_strMailName)
    {
        this.mailInfoParams.setMailName(l_strMailName);
    }

    /**
     * (getメール名称) <BR>
     * メール名称を返す。<BR>
     * <BR>
     * this.メール行.getメール名称( )の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 413C4B440064
     */
    public String getMailName()
    {
        return this.mailInfoParams.getMailName();
    }

    /**
     * (set差出人)<BR>
     * 差出人の設定を行う。<BR>
     * <BR>
     * １）　@this.メール行.set差出人()をコールする。<BR>
     * [引数]<BR>
     * 　@差出人=引数.差出人<BR>
     * @@param l_strMailSender - 差出人
     * @@roseuid 41049EB30119
     */
    public void setMailSender(String l_strMailSender)
    {
        this.mailInfoParams.setMailSender(l_strMailSender);
    }

    /**
     * (get差出人) <BR>
     * 差出人を返す。 <BR>
     *  <BR>
     * this.メール行.get差出人( )の戻り値を返す。 <BR>
     * @@return java.lang.String
     * @@roseuid 41049F2E03D8
     */
    public String getMailSender()
    {
        return this.mailInfoParams.getMailSender();
    }

    /**
     * (set件名) <BR>
     * 件名の設定を行う。<BR>
     * <BR>
     * １）　@this.メール行.set件名( )をコールする。<BR>
     * [引数]<BR>
     * 　@件名=引数.件名<BR>
     * @@param l_strSubject - 件名
     * @@roseuid 41049F690158
     */
    public void setSubject(String l_strSubject)
    {
        this.mailInfoParams.setSubject(l_strSubject);
    }

    /**
     * (get件名) <BR>
     * 件名を返す。<BR>
     * <BR>
     * this.メール行.get件名( )の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 41049F690167
     */
    public String getSubject()
    {
        return this.mailInfoParams.getSubject();
    }

    /**
     * (setメールヘッダー) <BR>
     * メールヘッダーの設定を行う。 <BR>
     *  <BR>
     * １）　@this.メール行.setメールヘッダー( )をコールする。 <BR>
     * [引数] <BR> 
     * 　@メールヘッダー=引数.メールヘッダー <BR>
     * @@param l_strMailHeader - メールヘッダー
     * @@roseuid 41049FBF009C
     */
    public void setMailHeader(String l_strMailHeader)
    {
        this.mailInfoParams.setMailHeader(l_strMailHeader);
    }

    /**
     * (getメールヘッダー) <BR>
     * メールヘッダーを返す。<BR>
     * <BR>
     * this.メール行.getメールヘッダー( )の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 41049FBF00AC
     */
    public String getMailHeader()
    {
        return this.mailInfoParams.getMailHeader();
    }

    /**
     * (setメール本文) <BR>
     * メール本文の設定を行う。<BR>
     * <BR>
     * １）　@this.メール行.setメール本文()をコールする。<BR>
     * [引数]<BR>
     * 　@メール本文=引数.メール本文<BR>
     * @@param l_strMailText - メール本文
     * @@roseuid 4104A03B005E
     */
    public void setMailText(String l_strMailText)
    {
        this.mailInfoParams.setMailText(l_strMailText);
    }

    /**
     * (getメール本文) <BR>
     * メール本文を返す。<BR>
     * <BR>
     * this.メール行.getメール本文()の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 4104A03B008D
     */
    public String getMailText()
    {
        return this.mailInfoParams.getMailText();
    }

    /**
     * (setメールフッター) <BR>
     * メールフッターの設定を行う。<BR>
     * <BR>
     * １）　@this.メール行.setメールフッター()をコールする。<BR>
     * [引数]<BR>
     * 　@メールフッター=引数.メールフッター<BR>
     * @@param l_strMailFooter - メールフッター
     * @@roseuid 4104A1A800BC
     */
    public void setMailFooter(String l_strMailFooter)
    {
        this.mailInfoParams.setMailFooter(l_strMailFooter);
    }

    /**
     * (getメールフッター) <BR>
     * メールフッターを返す。 <BR>
     *  <BR>
     * this.メール行.getメールフッター( )の戻り値を返す。 <BR>
     * @@return java.lang.String
     * @@roseuid 4104A1A800BE
     */
    public String getMailFooter()
    {
        return this.mailInfoParams.getMailFooter();
    }
    
    /**
     * (set送信先アドレス) <BR>
     * 送信先アドレスの設定を行う。<BR>
     * <BR>
     * １）　@this.メール行.set送信先アドレス()をコールする。<BR>
     * [引数]<BR>
     * 　@送信先アドレス=引数.送信先アドレス<BR>
     * @@param l_strSendAddress  - 送信先アドレス
     */
    public void setSendAddress(String l_strSendAddress)
    {
        this.mailInfoParams.setSendAddress(l_strSendAddress);
    }

    /**
     * (get送信先アドレス) <BR>
     * 送信先アドレスを返す。 <BR>
     *  <BR>
     * this.メール行.get送信先アドレス( )の戻り値を返す。 <BR>
     * @@return java.lang.String
     */
    public String getSendAddress()
    {
        return this.mailInfoParams.getSendAddress();
    }
}
@
