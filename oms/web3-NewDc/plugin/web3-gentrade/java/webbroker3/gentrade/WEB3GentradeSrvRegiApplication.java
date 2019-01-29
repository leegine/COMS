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
filename	WEB3GentradeSrvRegiApplication.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  サービス申込登録(WEB3GentradeSrvRegiApplication.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 鄒政 (中訊) 新規作成
Revesion History : 2007/06/15 栄イ (中訊)【共通】仕様変更・モデルNo.250
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.util.WEB3LogUtility;

/**
 * サービス申込登録クラス
 */
public class WEB3GentradeSrvRegiApplication implements BusinessObject
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeSrvRegiApplication.class);

    /**
     * サービス申込登録行
     */
    private SrvRegiApplicationParams srvRegiApplicationParams;

    /**
     * コンストラクタ。<BR>
     * （既存行のインスタンスを取得する際に使用する）<BR>
     * <BR>
     * 1) 引数.サービス申込登録Rowをthis.サービス申込登録行に設定する。<BR>
     * @@param l_srvRegiApplicationParams - サービス申込登録Params
     * @@roseuid 410491E8010A
     */
    public WEB3GentradeSrvRegiApplication(SrvRegiApplicationParams l_srvRegiApplicationParams)
    {
        final String STR_METHOD_NAME = "WEB3GentradeSrvRegiApplication(SrvRegiApplicationParams)";
        if (l_srvRegiApplicationParams == null)
        {
            log.error("サービス申込登録行 = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "サービス申込登録行 = null");
        }
        this.srvRegiApplicationParams = l_srvRegiApplicationParams;
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.サービス申込登録行を返却する。 <BR>
     * @@return Object
     * @@roseuid 4132F15B0064
     */
    public Object getDataSourceObject()
    {
        return this.srvRegiApplicationParams;
    }

    /**
     * (createForUpdateParams) <BR>
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * 　@this.サービス申込登録行をコピーして、<BR>
     * 同じ内容の別インスタンスを作成する（clone）。 <BR>
     * 作成したコピーを自身のthis.サービス申込登録行にセットする。 <BR>
     * @@roseuid 4132F0200093
     */
    public void createForUpdateParams()
    {
        SrvRegiApplicationParams l_srvRegiApplicationParams = 
            new SrvRegiApplicationParams(this.srvRegiApplicationParams);
        this.srvRegiApplicationParams = l_srvRegiApplicationParams;
    }

    /**
     * (createNewサービス申込登録) <BR>
     * 新規のサービス申込登録オブジェクトを生成し、返却する。<BR>
     * <BR>
     * 1) サービス申込登録Paramsを生成する。<BR>
     * <BR>
     * 2) 生成したサービス申込登録Paramsオブジェクトに以下の値をセットする。<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@部店コード=引数.部店コード<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * 　@口座コード=引数.口座コード<BR>
     * 　@申込登録ID=(*1)<BR>
     * 　@有効区分="有効"<BR>
     * 　@取消区分="通常"<BR>
     * <BR>
     * (*1) this.createNew申込登録ID( )の戻り値<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@部店コード=引数.部店コード<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * 　@口座コード=引数.口座コード<BR>
     * <BR>
     * 3) this.コンストラクタをコールし、生成したサービス申込登録<BR>
     *   オブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@サービス申込登録Row=生成したサービス申込登録Params<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strSrvDiv - サービス区分
     * @@param l_strAccountCode - 口座コード
     * @@return WEB3GentradeSrvRegiApplication
     * @@throws WEB3BaseException
     * @@roseuid 4136A139005C
     */
    public static WEB3GentradeSrvRegiApplication createNewSrvRegiApplication(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strSrvDiv,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNewSrvRegiApplication(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス申込登録Paramsを生成する
        SrvRegiApplicationParams l_srvRegiApplicationParams = 
            new SrvRegiApplicationParams();
        
        //createNew申込登録ID
        long l_lngRegistId =
            createNewRegistId(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strSrvDiv,
                l_strAccountCode);
        
        //2) 生成したサービス申込登録Paramsオブジェクトに以下の値をセットする。
        //証券会社コード=引数.証券会社コード
        //部店コード=引数.部店コード
        //サービス区分=引数.サービス区分
        //口座コード=引数.口座コード
        //申込登録ID=createNew申込登録ID( )の戻り値
        //有効区分="有効"
        //取消区分="通常"
        l_srvRegiApplicationParams.setInstitutionCode(l_strInstitutionCode);
        l_srvRegiApplicationParams.setBranchCode(l_strBranchCode);
        l_srvRegiApplicationParams.setSrvDiv(l_strSrvDiv);
        l_srvRegiApplicationParams.setAccountCode(l_strAccountCode);
        l_srvRegiApplicationParams.setRegistId(l_lngRegistId);
        l_srvRegiApplicationParams.setEffectiveDiv(WEB3EffectiveDivDef.EFFECTIVE);
        l_srvRegiApplicationParams.setCancelDiv(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        
        //3) this.コンストラクタをコールし、
        // 生成したサービス申込登録オブジェクトを返却する
        log.exiting(STR_METHOD_NAME);
        return new WEB3GentradeSrvRegiApplication(l_srvRegiApplicationParams);
        
    }

    /**
     * (saveNewサービス申込登録) <BR>
     * サービス申込登録Paramsの内容をデータベースに反映させる。(Insert)<BR>
     * <BR>
     * 1) this.サービス申込登録行オブジェクトに以下の値をセットする。<BR>
     * 　@作成日付 = <BR>
     *      GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付 = <BR>
     *      GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス申込登録行オブジェクトの内容で、<BR>
     * 　@サービス申込登録テーブルを更新（Insert）する。<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4136A13B0108
     */
    public void saveNewSrvRegiApplication()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveNewSrvRegiApplication()";
        log.entering(STR_METHOD_NAME);
        
        // 1) this.サービス申込登録行オブジェクトに以下の値をセットする。
        // 作成日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        //  更新日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        this.srvRegiApplicationParams.setLastUpdatedTimestamp(l_tsSystemTime);
        this.srvRegiApplicationParams.setCreatedTimestamp(l_tsSystemTime);
        
        //2) this.サービス申込登録行オブジェクトの内容で、
        // サービス申込登録テーブルを更新（Insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(this.srvRegiApplicationParams);
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
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (saveサービス申込登録) <BR>
     * サービス申込登録Paramsの内容をデータベースに反映させる。(Update)<BR>
     * <BR>
     * 1) this.サービス申込登録行オブジェクトに以下の値をセットする。<BR>
     * 　@更新日付 = <BR>
     *     GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス申込登録行オブジェクトの内容で、<BR>
     * 　@サービス申込登録テーブルを更新（Update）する。<BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4136A13A00C9
     */
    public void saveSrvRegiApplication()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveSrvRegiApplication()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.サービス申込登録行オブジェクトに以下の値をセットする。
        //更新日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        this.srvRegiApplicationParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.サービス申込登録行オブジェクトの内容で、
        // サービス申込登録テーブルを更新（Update）する
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(this.srvRegiApplicationParams);
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
        
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (createNew申込登録ID)<BR>
     * 口座コード、サービス区分単位で採番される<BR>
     * 新規行用の申込登録IDを返却する。<BR>
     * (staticメソッド)<BR>
     * <BR>
     * 1) 以下の条件でサービス申込登録テーブルを検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@部店コード=引数.部店コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@口座コード=引数.口座コード<BR>
     * [並び順]<BR>
     * 　@申込登録ID　@降順<BR>
     * <BR>
     * 2) 検索結果=0件の場合、0を返却する。<BR>
     * <BR>
     * 3) 検索結果＞0件の場合<BR>
     *  3-1) 検索結果の先頭の要素を取得する。<BR>
     * <BR>
     *  3-2) 以下の値を返却する。<BR>
     * 　@　@　@3-1)で取得したサービス申込登録Params.get申込登録ID( )+1<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strSrvDiv - サービス区分
     * @@param l_strAccountCode - 口座コード
     * @@return long
     * @@throws WEB3SystemLayerException
     * @@roseuid 412F29D601A0
     */
    public static long createNewRegistId(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strSrvDiv,
        String l_strAccountCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createNewRegistId(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //1) 以下の条件でサービス申込登録テーブルを検索する。
        //証券会社コード=引数.証券会社コード and
        //部店コード=引数.部店コード and
        //サービス区分=引数.サービス区分 and
        //口座コード=引数.口座コード
        //[並び順] ： 申込登録ID　@降順
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and account_code = ? ");
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strSrvDiv,
                l_strAccountCode 
            };
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_sbWhere.toString(),
                "regist_id desc",
                null,
                l_objWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeInsider.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        long l_lngNewRegistId;
        //2) 検索結果=0件の場合、0を返却する。
        if(l_lisRecords.size() == 0)
        {
            l_lngNewRegistId = 0;
        }
        else
        {
            SrvRegiApplicationRow l_srvRegiApplicationRow = 
                (SrvRegiApplicationRow)l_lisRecords.get(0);
            l_lngNewRegistId = l_srvRegiApplicationRow.getRegistId() + 1;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lngNewRegistId;
       
    }

    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.サービス申込登録行.get証券会社コード()の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 410491E801F4
     */
    public String getInstitutionCode()
    {
        return this.srvRegiApplicationParams.getInstitutionCode();
    }

    /**
     * (get部店コード)<BR>
     * 部店コードを返す。<BR>
     * <BR>
     * this.サービス申込登録行.get部店コード()の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 410493CD002F
     */
    public String getBranchCode()
    {
        return this.srvRegiApplicationParams.getBranchCode();
    }

    /**
     * (getサービス区分) <BR>
     * サービス区分を返す。<BR>
     * <BR>
     * this.サービス申込登録行.getサービス区分()の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 410491E80223
     */
    public String getSrvDiv()
    {
        return this.srvRegiApplicationParams.getSrvDiv();
    }

    /**
     * (get口座コード) <BR>
     * 口座コードを返す。<BR>
     * <BR>
     * this.サービス申込登録行.get口座コード()の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 410491E80252
     */
    public String getAccountCode()
    {
        return this.srvRegiApplicationParams.getAccountCode();
    }

    /**
     * (get申込登録ID) <BR>
     * 申込登録IDを返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get申込登録ID( )の戻り値を返す。 <BR>
     * @@return long
     * @@roseuid 4111925100C3
     */
    public long getRegistId()
    {
        return this.srvRegiApplicationParams.getRegistId();
    }

    /**
     * (set適用開始日) <BR>
     * 適用開始日の設定を行う。<BR>
     * <BR>
     * 1) this.サービス申込登録行.set適用開始日()をコールする。<BR>
     * [引数]<BR>
     * 　@適用開始日=引数.適用開始日<BR>
     * @@param l_tsAppliStartDate - 適用開始日
     * @@roseuid 4104D9CD0177
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate)
    {
        this.srvRegiApplicationParams.setAppliStartDate(l_tsAppliStartDate);
    }

    /**
     * (get適用開始日) <BR>
     * 適用開始日を返す。<BR>
     * <BR>
     * this.サービス申込登録行.get適用開始日()の戻り値を返す。<BR>
     * @@return java.sql.Timestamp
     * @@roseuid 4104943600DB
     */
    public Timestamp getAppliStartDate()
    {
        return this.srvRegiApplicationParams.getAppliStartDate();
    }

    /**
     * (set適用終了日) <BR>
     * 適用終了日の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set適用終了日()をコールする。 <BR>
     * [引数] <BR>
     * 　@適用終了日=引数.適用終了日 <BR>
     * @@param l_tsAppliEndDate - 適用終了日
     * @@roseuid 410491E802A0
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate)
    {
        this.srvRegiApplicationParams.setAppliEndDate(l_tsAppliEndDate);
    }

    /**
     * (get適用終了日) <BR>
     * 適用終了日を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get適用終了日()の戻り値を返す。 <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 410491E802CF
     */
    public Timestamp getAppliEndDate()
    {
        return this.srvRegiApplicationParams.getAppliEndDate();
    }

    /**
     * (set注文ID) <BR>
     * 注文IDの設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set注文ID( )をコールする。 <BR>
     * [引数] <BR>
     * 　@注文ID=引数.注文ID <BR>
     * @@param l_orderId - 注文ID
     * @@roseuid 4112306C0309
     */
    public void setOrderId(Long l_orderId)
    {
        this.srvRegiApplicationParams.setOrderId(l_orderId);
    }

    /**
     * (get注文ID) <BR>
     * 注文IDを返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get注文ID()の戻り値を返す。 <BR>
     * @@return Long
     * @@roseuid 41073F3F03C1
     */
    public Long getOrderId()
    {
        if(this.srvRegiApplicationParams.getOrderIdIsNull())
        {
            return null;
        }
        else
        {   
            return new Long(this.srvRegiApplicationParams.getOrderId());
        }
    }

    /**
     * (set申込日)<BR>
     * 申込日の設定を行う。<BR>
     * <BR>
     * 1) this.サービス申込登録行.set申込日()をコールする。<BR>
     * [引数]<BR>
     * 　@申込日=引数.申込日<BR>
     * @@param l_tsAppliDate - 申込日
     * @@roseuid 410494DF0139
     */
    public void setAppliDate(Timestamp l_tsAppliDate)
    {
        this.srvRegiApplicationParams.setAppliDate(l_tsAppliDate);
    }

    /**
     * (get申込日) <BR>
     * 申込日を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get申込日()の戻り値を返す。 <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 410494DF0148
     */
    public Timestamp getAppliDate()
    {
        return this.srvRegiApplicationParams.getAppliDate();
    }

    /**
     * (set登録区分) <BR>
     * 登録区分の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set登録区分()をコールする。 <BR>
     * [引数] <BR>
     * 　@登録区分=引数.登録区分 <BR>
     * @@param l_strPaymentDiv - 登録区分  0：無料　@1：有料
     * @@roseuid 410495CC02EE
     */
    public void setPaymentDiv(String l_strPaymentDiv)
    {
        this.srvRegiApplicationParams.setPaymentDiv(l_strPaymentDiv);
    }

    /**
     * (get登録区分) <BR>
     * 登録区分を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get登録区分()の戻り値を返す。 <BR>
     * @@return java.lang.String
     * @@roseuid 410495CC032D
     */
    public String getPaymentDiv()
    {
        return this.srvRegiApplicationParams.getPaymentDiv();
    }

    /**
     * (set申込抽選区分) <BR>
     * 申込抽選区分の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set申込抽選区分()をコールする。 <BR>
     * [引数] <BR>
     * 　@申込抽選区分=引数.申込抽選区分 <BR>
     * @@param l_strAppliLotDiv - 申込抽選区分
     * @@roseuid 410495380158
     */
    public void setAppliLotDiv(String l_strAppliLotDiv)
    {
        this.srvRegiApplicationParams.setAppliLotDiv(l_strAppliLotDiv);
    }

    /**
     * (get申込抽選区分) <BR>
     * 申込抽選区分を返す。<BR>
     * <BR>
     * this.サービス申込登録行.get申込抽選区分()の戻り値を返す。<BR>
     * @@return java.lang.String
     * @@roseuid 4104953801C5
     */
    public String getAppliLotDiv()
    {
        return this.srvRegiApplicationParams.getAppliLotDiv();
    }

    /**
     * (set有効区分) <BR>
     * 有効区分の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set有効区分()をコールする。 <BR>
     * [引数] <BR>
     * 　@有効区分=引数.有効区分 <BR>
     * @@param l_strEffectiveDiv - 有効区分
     * @@return java.lang.String
     * @@roseuid 411192870361
     */
    public void setEffectiveDiv(String l_strEffectiveDiv)
    {
        this.srvRegiApplicationParams.setEffectiveDiv(l_strEffectiveDiv);
    }

    /**
     * (get有効区分) <BR>
     * 有効区分を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get有効区分()の戻り値を返す。 <BR>
     * @@return java.lang.String
     * @@roseuid 4109E72F001B
     */
    public String getEffectiveDiv()
    {
        return this.srvRegiApplicationParams.getEffectiveDiv();
    }

    /**
     * (set取消区分) <BR>
     * 取消区分の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set取消区分()をコールする。 <BR>
     * [引数] <BR>
     * 　@取消区分=引数.取消区分 <BR>
     * @@param l_strCancelDiv - 取消区分
     * @@return java.lang.String
     * @@roseuid 4111B0C002E4
     */
    public void setCancelDiv(String l_strCancelDiv)
    {
        this.srvRegiApplicationParams.setCancelDiv(l_strCancelDiv);
    }

    /**
     * (get取消区分) <BR>
     * 取消区分を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get取消区分()の戻り値を返す。 <BR>
     * @@return java.lang.String
     * @@roseuid 4111B0C002E6
     */
    public String getCancelDiv()
    {
        return this.srvRegiApplicationParams.getCancelDiv();
    }

    /**
     * (set利用料金) <BR>
     * 利用料金の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set利用料金()をコールする。 <BR>
     * [引数] <BR>
     * 　@利用料金=引数.利用料金 <BR>
     * @@param l_useAmt - 利用料金
     * @@roseuid 41049697038A
     */
    public void setUseAmt(Double l_useAmt)
    {
        if(l_useAmt == null)
        {
            this.srvRegiApplicationParams.setUseAmt(null);
        }
        else
        {
            this.srvRegiApplicationParams.setUseAmt(l_useAmt.longValue());
        }
    }

    /**
     * (get利用料金) <BR>
     * 利用料金を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get利用料金()の戻り値を返す。 <BR>
     * @@return Double
     * @@roseuid 4104969703B9
     */
    public Double getUseAmt()
    {
        if(this.srvRegiApplicationParams.getUseAmtIsNull())
        {
            return null;
        }
        else
        {
            return new Double(this.srvRegiApplicationParams.getUseAmt());
        }
    }

    /**
     * (set出金日) <BR>
     * 出金日の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set出金日()をコールする。 <BR>
     * [引数] <BR>
     * 　@出金日=引数.出金日 <BR>
     * @@param l_tsPaymentDate - 出金日
     * @@roseuid 410496FB032D
     */
    public void setPaymentDate(Timestamp l_tsPaymentDate)
    {
        this.srvRegiApplicationParams.setPaymentDate(l_tsPaymentDate);
    }

    /**
     * (get出金日) <BR>
     * 出金日を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get出金日()の戻り値を返す。 <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 410496FB033C
     */
    public Timestamp getPaymentDate()
    {
        return this.srvRegiApplicationParams.getPaymentDate();
    }

    /**
     * (set自動当選取消期限日)<BR>
     * 自動当選取消期限日の設定を行う。<BR>
     * <BR>
     * 1) this.サービス申込登録行.set自動当選取消期限日( )をコールする。<BR>
     * [引数]<BR>
     * 　@自動当選取消期限日=引数.自動当選取消期限日<BR>
     * @@param l_tsCancelLimitDate - 自動当選取消期限日
     * @@roseuid 412AE7F3029B
     */
    public void setCancelLimitDate(Timestamp l_tsCancelLimitDate)
    {
        this.srvRegiApplicationParams.setCancelLimitDate(l_tsCancelLimitDate);
    }

    /**
     * (get自動当選取消期限日)<BR>
     * 自動当選取消期限日を返す。<BR>
     * <BR>
     * this.サービス申込登録行.get自動当選取消期限日( )の戻り値を返す。<BR>
     * @@return java.sql.Timestamp
     * @@roseuid 412AE7F30366
     */
    public Timestamp getCancelLimitDate()
    {
        return this.srvRegiApplicationParams.getCancelLimitDate();
    }

    /**
     * (get最終更新日) <BR>
     * 更新日付を返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get更新日付()の戻り値を返す。 <BR>
     * @@return java.sql.Timestamp
     * @@roseuid 4107A0510139
     */
    public Timestamp getLastUpdatedTimestamp()
    {
        return this.srvRegiApplicationParams.getLastUpdatedTimestamp();
    }

    /**
     * (get最終更新者) <BR>
     * 更新者コードを返す。 <BR>
     *  <BR>
     * this.サービス申込登録行.get更新者コード()の戻り値を返す。 <BR>
     * @@return java.lang.String
     * @@roseuid 4107A057038A
     */
    public String getLastUpdater()
    {
        return this.srvRegiApplicationParams.getLastUpdater();
    }
    
    /**
     * (set最終更新者) <BR>
     * 最終更新者の設定を行う。 <BR>
     *  <BR>
     * 1) this.サービス申込登録行.set更新者コード()をコールする。<BR>
     * [引数] <BR>
     * 更新者コード=引数.最終更新者 <BR>
     * <BR>
     * @@param l_strLastUpdater - (最終更新者)
     * @@return java.lang.String
     * @@roseuid 4107A057038A
     */
    public void setLastUpdater(String l_strLastUpdater)
    {
        this.srvRegiApplicationParams.setLastUpdater(l_strLastUpdater);
    }

    /**
     * (set無料属性申込区分) <BR>
     * 無料属性申込区分の設定を行う。 <BR>
     * <BR>
     * 1) this.サービス申込登録行.set無料属性申込区分()をコールする。 <BR>
     * [引数] <BR>
     * 　@無料属性申込区分=引数.無料属性申込区分 <BR>
     * <BR>
     * @@param l_strFreeSrvDiv - 無料属性申込区分
     */
    public void setFreeSrvDiv(String l_strFreeSrvDiv)
    {
        this.srvRegiApplicationParams.setFreeSrvDiv(l_strFreeSrvDiv);
    }

    /**
     * (get無料属性申込区分) <BR>
     * 無料属性申込区分を返す。 <BR>
     * <BR>
     * this.サービス申込登録行.get無料属性申込区分()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     */
    public String getFreeSrvDiv()
    {
        return this.srvRegiApplicationParams.getFreeSrvDiv();
    }
}
@
