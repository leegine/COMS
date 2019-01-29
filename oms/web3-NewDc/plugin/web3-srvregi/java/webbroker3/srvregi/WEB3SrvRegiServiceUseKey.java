head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUseKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用キー(WEB3SrvRegiServiceUseKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 張学剛 (中訊) 新規作成
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
import webbroker3.srvregi.data.SrvRegiKeyParams;
import webbroker3.srvregi.data.SrvRegiKeyRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用キー)<BR>
 * サービス利用キーエンティティクラス<BR>                     
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUseKey implements BusinessObject 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiServiceUseKey.class);
       
    /**
     * (サービス利用キー行)<BR>
     */
    private SrvRegiKeyParams srvRegiKeyParams;

    /** 
     * (サービス利用キー)<BR>
     * コンストラクタ<BR>
     * 当オブジェクトを生成し、<BR>
     * 引数.サービス利用キーRowをthis.サービス利用キー行に設定する。<BR>
     * @@param l_srvRegiKeyRow - (サービス利用キーRow)<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3SrvRegiServiceUseKey(SrvRegiKeyRow l_srvRegiKeyRow) throws WEB3BaseException 
    {
        this.srvRegiKeyParams = new SrvRegiKeyParams(l_srvRegiKeyRow);
    }
    
    /**
     * （getDataSourceObjectの実装）<BR> 
     * <BR> 
     * this.サービス利用キー行を返却する。<BR>
     * @@return Object
     * @@roseuid 4133093100F0
     */
    public Object getDataSourceObject() 
    {
        return this.srvRegiKeyParams;
    }
    
    /**
     * 更新行用Paramsのクローン行を生成して返却する。<BR> 
     * <BR> 
     * 　@this.サービス利用キー行をコピーして、同じ内容の<BR> 
     * 別インスタンスを作成する（clone）。<BR>  
     * 作成したコピーを自身のthis.サービス利用キー行にセットする。<BR>
     * @@roseuid 413309310100
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        SrvRegiKeyParams l_srvRegiKeyParams = new SrvRegiKeyParams(this.srvRegiKeyParams);
        this.srvRegiKeyParams = l_srvRegiKeyParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createNew利用キーID)<BR>
     * 新規用のサービス利用キーIDを返却する。<BR>
     * (staticメソッド)<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     *   利用キー種別区分=引数.利用キー種別区分 
     * [並び順]<BR>
     *   サービス利用キーID　@降順 <BR>
     * <BR>
     * 2) 検索結果の件数=0件の場合、1を返却する。<BR>
     * <BR>
     * 3) 検索結果の件数>0件の場合、検索結果の先頭の要素となる<BR>
     * 　@サービス利用キーParams.getサービス利用キーID( )+1を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strSrvUseKeyType - (利用キー種別区分)<BR>
     * @@return long
     * @@roseuid 412F0FBD02B7
     */
    public static long createNewUseKeyId(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewUseKeyId(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType)";
        log.entering(STR_METHOD_NAME);
        
        //1) 以下の条件で「サービス利用キー」テーブルを検索する。
        //[検索条件]
        //証券会社コード=引数.証券会社コード 
        //サービス区分=引数.サービス区分 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and srv_use_key_type = ? ");
        
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strSrvDiv,
                l_strSrvUseKeyType
            };
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiKeyRow.TYPE,
                l_sbWhere.toString(),
                " srv_use_id desc",
                null,
                l_objWhere);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceUseKey.class.getName() + STR_METHOD_NAME);
        }
        
        long l_lngNewUseKeyId;
        //2) 検索結果の件数=0件の場合、1を返却する。
        //サービス利用キーParams.getサービス利用キーID( )+1を返却する。
        if(l_lisRecords.size() == 0)
        {
            l_lngNewUseKeyId = 1;
        }
        else
        {
            SrvRegiKeyRow l_srvRegiKeyRow = (SrvRegiKeyRow)l_lisRecords.get(0);
            l_lngNewUseKeyId = l_srvRegiKeyRow.getSrvUseId() + 1;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lngNewUseKeyId;
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.サービス利用キー行.get証券会社コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40FCC87403D9
     */
    public String getInstitutionCode() 
    {
        String l_strInstitutionCode = this.srvRegiKeyParams.getInstitutionCode();
        return l_strInstitutionCode;
    }
    
    /**
     * (getサービス区分)<BR>
     * サービス区分を返す。<BR>
     * <BR>
     * this.サービス利用キー行.getサービス区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40FCC87500EB
     */
    public String getSrvDiv() 
    {
        String l_strSrvDiv = this.srvRegiKeyParams.getSrvDiv();
        return l_strSrvDiv;
    }
    
    /**
     * (getサービス利用キーID)<BR>
     * サービス利用キーIDを返す。<BR>
     * <BR>
     * this.サービス利用キー行.getサービス利用キーID()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40FCC9E30020
     */
    public long getSrvUseKeyId() 
    {
        long l_lngSrvUseKeyId = this.srvRegiKeyParams.getSrvUseId();
        return l_lngSrvUseKeyId;
    }
    
    /**
     * (setサービス利用キー)<BR>
     * サービス利用キーの設定を行う。<BR>
     * <BR>
     * 1) this.サービス利用キー行.setサービス利用キー()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス利用キー=引数.サービス利用キー<BR>
     * @@param l_strSrvUseKey - (サービス利用キー)<BR>
     * @@roseuid 40FCCA170187
     */
    public void setSrvUseKey(String l_strSrvUseKey) 
    {
        this.srvRegiKeyParams.setSrvUseKey(l_strSrvUseKey);
    }
    
    /**
     * (getサービス利用キー)<BR>
     * サービス利用キーを返す。<BR>
     * <BR>
     * this.サービス利用キー行.getサービス利用キー()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40FCCAB702DF
     */
    public String getSrvUseKey() 
    {
        String l_strSrvUseKey = this.srvRegiKeyParams.getSrvUseKey();
        return l_strSrvUseKey;
    }
    
    /**
     * (get利用キー種別区分)<BR>
     * 利用キー種別区分を返す。<BR> 
     * this.サービス利用キー行.get利用キー種別区分()の戻り値を返す。<BR>
     * @@return String
     */
    public String getSrvUseKeyType()
    {
        String l_strSrvUseKeyType = this.srvRegiKeyParams.getSrvUseKeyType();
        return l_strSrvUseKeyType;
    }
    
    /**
     * (createNewサービス利用キー)<BR>
     * 新規にサービス利用キーオブジェクトを生成して返却する。<BR>
     * <BR>
     * 1) サービス利用キーParamsオブジェクトを生成し、this.サービス<BR>
     * 利用キーParamsに設定する。<BR>
     * <BR>
     * 2) サービス利用キーParams.set証券会社コード()をコールする。<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * 3) サービス利用キーParams.setサービス区分()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 4) サービス利用キーParams.setサービス利用キーID()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス利用キーID=サービス利用キー.createNew利用キーID( )<BR>
     * 　@　@[createNew利用キーIDに渡す引数]<BR>
     * 　@　@　@証券会社コード=引数.証券会社コード<BR>
     *      サービス区分=引数.サービス区分<BR>
     *      利用キー種別区分=引数.利用キー種別区分 <BR>
     * <BR>
     * 5) サービス利用キーParams.set利用キー種別区分()をコールする。 
     * [引数] 
　@   *    利用キー種別区分=引数.利用キー種別区分<BR>
     * <BR>
     * 6) サービス利用キーのコンストラクタをコールし、生成した<BR>
     * 　@サービス利用キーオブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@サービス利用キーRow=生成したサービス利用キーParams<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strSrvUseKeyType - (利用キー種別区分)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUseKey
     * @@throws WEB3BaseException
     * @@roseuid 413E62EE00FB
     */
    public static WEB3SrvRegiServiceUseKey createNewSrvUseKey(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvUseKey(String l_strInstitutionCode, String l_strSrvDiv, String l_strSrvUseKeyType)";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス利用キーParamsオブジェクトを生成し、this.サービス利用キーParamsに設定する。
        SrvRegiKeyParams l_srvRegiKeyParams = new SrvRegiKeyParams();
        
        //2) サービス利用キーParams.set証券会社コード()をコールする。
        l_srvRegiKeyParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) サービス利用キーParams.setサービス区分()をコールする。
        l_srvRegiKeyParams.setSrvDiv(l_strSrvDiv);
        
        //4) サービス利用キーParams.setサービス利用キーID()をコールする。
        long l_lngSrvUseId = createNewUseKeyId(l_strInstitutionCode, l_strSrvDiv, l_strSrvUseKeyType);
        l_srvRegiKeyParams.setSrvUseId(l_lngSrvUseId);
        
        //5)サービス利用キーParams.set利用キー種別区分()をコールする。
        l_srvRegiKeyParams.setSrvUseKeyType(l_strSrvUseKeyType);
        
        //5) サービス利用キーのコンストラクタをコールし、生成したサービス利用キーオブジェクトを返却する。
        WEB3SrvRegiServiceUseKey l_srvRegiServiceUseKey = new WEB3SrvRegiServiceUseKey(l_srvRegiKeyParams);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_srvRegiServiceUseKey;
    }
    
    /**
     * (saveサービス利用キー)<BR>
     * this.サービス利用キー行オブジェクトの<BR>
     * 情報をデータベースに反映させる。(Update)<BR>
     * <BR>
     * 1) this.サービス利用キー行オブジェクトに以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス利用キー行オブジェクトの内容で、<BR>
     * 　@サービス利用キーテーブルを更新（Update）する。<BR>
     * @@roseuid 413E62EE010A
     */
    public void saveSrvUseKey() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveSrvUseKey()";
        log.entering(STR_METHOD_NAME);
        
        //1) this.サービス利用キー行オブジェクトに以下の値をセットする。
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvRegiKeyParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //更新日付
        this.srvRegiKeyParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doUpdateQuery(this.srvRegiKeyParams);//DataNetworkException,DataQueryException
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
     * (saveNewサービス利用キー)<BR>
     * this.サービス利用キー行オブジェクトの情報をデータベースに反映させる。(Insert)<BR>
     * <BR>
     * 1) this.サービス利用キー行オブジェクトに以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス利用キー行オブジェクトの内容で、<BR>
     * 　@サービス利用キーテーブルを更新（Insert）する。<BR>
     * @@roseuid 413E62EE012A
     */
    public void saveNewSrvUseKey() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewSrvUseKey()";
        log.entering(STR_METHOD_NAME);
        
        String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
        this.srvRegiKeyParams.setLastUpdater(l_strAdministratorCode);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp( );
        //作成日付
        this.srvRegiKeyParams.setCreatedTimestamp(l_tsSystemTime);
        //更新日付
        this.srvRegiKeyParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //2) this.サービス利用キー行オブジェクトの内容で、サービス利用キーテーブルを更新（Insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(this.srvRegiKeyParams);//DataNetworkException,DataQueryException
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
     * (removeサービス利用キー)<BR>
     * サービス利用キーの情報をデータベースから削除する。<BR>
     * <BR>
     * 1) 以下を条件に、当該レコードを「サービス利用キーテーブル」より削除する。<BR>
     * [削除条件]<BR>
     * 　@証券会社コード=this.証券会社コード and<BR>
     * 　@サービス区分=this.サービス区分 and<BR>
     *   利用キー種別区分=this.利用キー種別区分 and <BR>
     * 　@利用キーID=this.利用キーID<BR>
     * @@roseuid 413E62EE0139
     */
    public void removeSrvUseKey() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvUseKey()";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and srv_use_key_type = ? ");
        l_sbWhere.append(" and srv_use_id = ? ");
        
        Object[] l_objWhere =
            {
                this.getInstitutionCode(),
                this.getSrvDiv(),
                this.getSrvUseKeyType(),
                new Long(this.getSrvUseKeyId())
            };
                          
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doDeleteAllQuery(SrvRegiKeyRow.TYPE, l_sbWhere.toString(), l_objWhere);//DataNetworkException,DataQueryException
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
