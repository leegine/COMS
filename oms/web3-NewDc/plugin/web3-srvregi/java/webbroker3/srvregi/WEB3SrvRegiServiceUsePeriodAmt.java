head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUsePeriodAmt.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用期間料金(WEB3SrvRegiServiceUsePeriodAmt.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 張学剛 (中訊) 新規作成
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiChargeParams;
import webbroker3.srvregi.data.SrvRegiChargeRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用期間料金)<BR>
 * サービス利用期間料金クラス<BR>                    
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUsePeriodAmt implements BusinessObject 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiServiceUsePeriodAmt.class);
    
    /**
     * (サービス利用期間料金行)<BR>
     */
    private SrvRegiChargeParams srvUsePeriodAmtParams;

    /**
     * (サービス利用期間料金)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 当オブジェクトを生成し<BR>、
     * 引数.サービス利用期間料金Rowをthis.サービス利用期間料金行に設定する。<BR>
     * @@param l_srvRegiChargeRow - (サービス利用期間料金Row)<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3SrvRegiServiceUsePeriodAmt(SrvRegiChargeRow l_srvRegiChargeRow) throws WEB3BaseException 
    {
        this.srvUsePeriodAmtParams = new SrvRegiChargeParams(l_srvRegiChargeRow);
    }
    
    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.サービス利用期間料金行を返却する。<BR>
     * @@return Object
     * @@roseuid 4133094B01AC
     */
    public Object getDataSourceObject() 
    {
        return this.srvUsePeriodAmtParams;
    }
    
    /**
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * 　@this.サービス利用期間料金行をコピーして、同じ内容の<BR>
     * 別インスタンスを作成する（clone）。<BR> 
     * 作成したコピーを自身のthis.サービス利用期間料金行にセットする。<BR>
     * @@roseuid 4133094B01FA
     */
    public void createForUpdateParams() 
    {       
        SrvRegiChargeParams l_srvUsePeriodAmtParams = new SrvRegiChargeParams(this.srvUsePeriodAmtParams);
        this.srvUsePeriodAmtParams = l_srvUsePeriodAmtParams;        
    }
    
    /**
     * (createNew通番)<BR>
     * サービス利用期間料金の新規通番を返却する。<BR>
     * (staticメソッド)<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用期間料金」テーブルを検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * [並び順]<BR>
     * 　@通番　@降順<BR>
     * <BR>
     * 2) 検索結果の件数=0件の場合、1を返却する。<BR>
     * <BR>
     * 3) 検索結果の件数>0件の場合、検索結果の先頭の要素となる<BR>
     * 　@サービス利用期間料金Params.get通番( )+1を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@return long
     * @@roseuid 412F0FAD00A3
     */
    public static long createNewConsecutiveNumbers(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewConsecutiveNumbers(String l_strInstitutionCode, String l_strSrvDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1) 以下の条件で「サービス利用期間料金」テーブルを検索する。
        //[検索条件]
        //証券会社コード=引数.証券会社コード 
        //サービス区分=引数.サービス区分 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strSrvDiv
            };
        List l_lisRecords = null;
        try
        {
            log.debug(SrvRegiChargeRow.TYPE + l_sbWhere.toString());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiChargeRow.TYPE,
                l_sbWhere.toString(),
                " consecutive_numbers desc",
                null,
                l_objWhere);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUsePeriodAmt.class.getName() + STR_METHOD_NAME);
        }
        
        long l_lngConsecutiveNumbers;
        //2) 検索結果の件数=0件の場合、1を返却する。
        if(l_lisRecords.size() == 0)
        {
            l_lngConsecutiveNumbers = 1;
        }
        else
        {
            //3) 検索結果の件数>0件の場合、検索結果の先頭の要素となる サービス利用期間料金Params.get通番( )+1を返却する。
            SrvRegiChargeRow l_srvRegiChargeRow = (SrvRegiChargeRow)l_lisRecords.get(0);
            l_lngConsecutiveNumbers = l_srvRegiChargeRow.getConsecutiveNumbers() + 1;
        }
                          
        log.exiting(STR_METHOD_NAME);
        return l_lngConsecutiveNumbers;
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.サービス利用期間料金行.get証券会社コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 4104846C0129
     */
    public String getInstitutionCode() 
    {
        String l_strInstitutionCode = this.srvUsePeriodAmtParams.getInstitutionCode();
        return l_strInstitutionCode;
    }
    
    /**
     * (getサービス区分)<BR>
     * サービス区分を返す。<BR>
     * <BR>
     * this.サービス利用期間料金行.getサービス区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 4104846C0148
     */
    public String getSrvDiv() 
    {
        String l_strSrvDiv = this.srvUsePeriodAmtParams.getSrvDiv();
        return l_strSrvDiv;
    }
    
    /**
     * (get通番)<BR>
     * 通番を返す。<BR>
     * <BR>
     * this.サービス利用期間料金行.get通番()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 4104846C0187
     */
    public long getConsecutiveNumbers() 
    {
        long l_lngConsecutiveNumbers = this.srvUsePeriodAmtParams.getConsecutiveNumbers();
        return l_lngConsecutiveNumbers;
    }
    
    /**
     * (set利用期間区分)<BR>
     * 利用期間区分の設定を行う。<BR>
     * <BR>
     * 1) this.サービス利用期間料金行.set利用期間区分()をコールする。<BR>
     * [引数]<BR>
     * 　@利用期間区分=引数.利用期間区分<BR>
     * @@param l_strSrvUsePeriodDiv - (利用期間区分)<BR>
     * @@roseuid 4104846C01B6
     */
    public void setSrvUsePeriodDiv(String l_strSrvUsePeriodDiv) 
    {
        this.srvUsePeriodAmtParams.setSrvUsePeriodDiv(l_strSrvUsePeriodDiv);
    }
    
    /**
     * (get利用期間区分)<BR>
     * 利用期間区分を返す。<BR>
     * <BR>
     * this.サービス利用期間料金行.get利用期間区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 4104846C0271
     */
    public String getSrvUsePeriodDiv() 
    {
        String l_strSrvUsePeriodDiv = this.srvUsePeriodAmtParams.getSrvUsePeriodDiv();
        return l_strSrvUsePeriodDiv;
    }
    
    /**
     * (set利用期間)<BR>
     * 利用期間の設定を行う。<BR>
     * <BR>
     * 1) this.サービス利用期間料金行.set利用期間()をコールする。<BR>
     * [引数]<BR>
     * 　@利用期間=引数.利用期間<BR>
     * Q&A WEB3-SRVREGI-A-DD-0017  利用期間：ご指摘の通り、intで構いません<BR>
     * @@param l_intSrvUsePeriod - (利用期間)<BR>
     * @@roseuid 41048600037B
     */
    public void setSrvUsePeriod(int l_intSrvUsePeriod) 
    {
        this.srvUsePeriodAmtParams.setSrvUsePeriod(l_intSrvUsePeriod);
    }
    
    /**
     * (get利用期間)<BR>
     * 利用期間を返す。<BR>
     * <BR>
     * this.サービス利用期間料金行.get利用期間()の戻り値を返す。<BR>
     * @@return int
     * @@roseuid 41048601005E
     */
    public int getSrvUsePeriod() 
    {
        int l_intSrvUsePeriod = this.srvUsePeriodAmtParams.getSrvUsePeriod();
        return l_intSrvUsePeriod;
    }
    
    /**
     * (set利用料金)<BR>
     * 利用料金の設定を行う。<BR>
     * <BR>
     * 1) this.サービス利用期間料金行.set利用料金()をコールする。<BR>
     * [引数]<BR>
     * 　@利用料金=引数.利用料金<BR>
     * @@param l_lngUseAmt - (利用料金)<BR>
     * @@roseuid 410486790177
     */
    public void setUseAmt(long l_lngUseAmt) 
    {
        this.srvUsePeriodAmtParams.setUseAmt(l_lngUseAmt);
    }
    
    /**
     * (get利用料金)<BR>
     * 利用料金を返す。<BR>
     * <BR>
     * this.サービス利用期間料金行.get利用料金()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 410486790233
     */
    public long getUseAmt() 
    {
        long l_lngUseAmt = this.srvUsePeriodAmtParams.getUseAmt();
        return l_lngUseAmt;
    }
    
    /**
     * (createNewサービス利用期間料金)<BR>
     * 新規にサービス利用期間料金オブジェクトを生成して返却する。<BR>
     * <BR>
     * 1) サービス利用期間料金Paramsオブジェクトを生成する。<BR>
     * <BR>
     * 2) サービス利用期間料金Params.set証券会社コード()をコールする。<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * 3) サービス利用期間料金Params.setサービス区分()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 4) this.サービス利用期間料金Params.set通番()をコールする。<BR>
     * [引数]<BR>
     * 　@通番=サービス情報管理.createNew通番( )<BR>
     * 　@　@[createNew通番に渡す引数]<BR>
     * 　@　@　@証券会社コード=引数.証券会社コード<BR>
     * 　@　@　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 5) サービス利用期間料金のコンストラクタをコールし、生成した<BR>
     * 　@サービス利用期間料金オブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@サービス利用期間料金Row=生成したサービス利用期間料金Params<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt
     * @@throws WEB3BaseException
     * @@roseuid 413E6311008D
     */
    public static WEB3SrvRegiServiceUsePeriodAmt createNewSrvUsePeriodAmt(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvUsePeriodAmt(String l_strInstitutionCode, String l_strSrvDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス利用期間料金Paramsオブジェクトを生成する。
        SrvRegiChargeParams l_srvRegiChargeParams = new SrvRegiChargeParams();
        
        //2) サービス利用期間料金Params.set証券会社コード()をコールする。
        l_srvRegiChargeParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) サービス利用期間料金Params.setサービス区分()をコールする。
        l_srvRegiChargeParams.setSrvDiv(l_strSrvDiv);
        
        //4) this.サービス利用期間料金Params.set通番()をコールする。
        long l_lngConsecutiveNumbers;
        l_lngConsecutiveNumbers = createNewConsecutiveNumbers(l_strInstitutionCode, l_strSrvDiv);
        l_srvRegiChargeParams.setConsecutiveNumbers(l_lngConsecutiveNumbers);
        
        //5) サービス利用期間料金のコンストラクタをコールし、生成したサービス利用期間料金オブジェクトを返却する。
        WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt = new WEB3SrvRegiServiceUsePeriodAmt(l_srvRegiChargeParams);

        log.exiting(STR_METHOD_NAME);
        
        return l_srvRegiServiceUsePeriodAmt;
    }
    
    /**
     * (saveサービス利用期間料金)<BR>
     * this.サービス利用期間料金行オブジェクトの<BR>
     * 情報をデータベースに反映させる。(Update)<BR>
     * <BR>
     * 1) this.サービス利用期間料金行に以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス利用期間料金行オブジェクトの内容で、<BR>
     * 　@サービス利用期間料金テーブルを更新（update）する。<BR>
     * @@roseuid 413E631100AD
     */
    public void saveSrvUsePeriodAmt() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveSrvUsePeriodAmt()";
        log.entering(STR_METHOD_NAME);
        
        //s1) this.サービス利用期間料金行に以下の値をセットする。
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvUsePeriodAmtParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //更新日付
        this.srvUsePeriodAmtParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doUpdateQuery(this.srvUsePeriodAmtParams);//DataNetworkException,DataQueryException
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
     * (saveNewサービス利用期間料金)<BR>
     * this.サービス利用期間料金行オブジェクトの<BR>
     * 情報をデータベースに反映させる。(Insert)<BR>
     * <BR>
     * 1) this.サービス利用期間料金行オブジェクトに以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス利用期間料金行オブジェクトの内容で、<BR>
     * 　@サービス利用期間料金テーブルを更新（Insert）する。<BR>
     * @@roseuid 413E631100DC
     */
    public void saveNewSrvUsePeriodAmt() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewSrvUsePeriodAmt()";
        log.entering(STR_METHOD_NAME);
        
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvUsePeriodAmtParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //作成日付
        this.srvUsePeriodAmtParams.setCreatedTimestamp(l_tsSystemTime);
        //更新日付
        this.srvUsePeriodAmtParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2)this.サービス利用期間料金行オブジェクトの内容で、サービス利用期間料金テーブルを更新（Insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(this.srvUsePeriodAmtParams);//DataNetworkException,DataQueryException
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
     * (removeサービス利用期間料金)<BR>
     * サービス利用期間料金の情報をデータベースから削除する。<BR>
     * <BR>
     * 1) 以下を条件に、当該レコードをサービス利用期間料金テーブルより削除する。<BR>
     * [削除条件]<BR>
     * 　@証券会社コード=this.証券会社コード and<BR>
     * 　@サービス区分=this.サービス区分 and<BR>
     * 　@通番=this.通番<BR>
     * @@roseuid 413E631100FB
     */
    public void removeSrvUsePeriodAmt() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvUsePeriodAmts()";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and consecutive_numbers = ? ");
        
        Object[] l_objWhere =
            {
                this.getInstitutionCode(),
                this.getSrvDiv(),
                new Long(this.getConsecutiveNumbers())
            };
                               
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doDeleteAllQuery(SrvRegiChargeRow.TYPE, l_sbWhere.toString(), l_objWhere);//DataNetworkException,DataQueryException
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
}
@
