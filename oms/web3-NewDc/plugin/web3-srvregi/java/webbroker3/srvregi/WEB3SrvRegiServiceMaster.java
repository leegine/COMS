head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービスマスター(WEB3SrvRegiServiceMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 郭英 (中訊) 新規作成
Revesion History : 2009/04/24 車進 (中訊) モデル407、413、414
Revesion History : 2009/05/20 柴双紅 (中訊) モデル420,421
*/

package webbroker3.srvregi;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3SrvStatusDef;
import webbroker3.common.define.WEB3SrvUseKeyTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.mailinfo.WEB3AdminMailInfoManager;
import webbroker3.srvregi.data.SrvRegiChargeRow;
import webbroker3.srvregi.data.SrvRegiCommCondDao;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiConsDocRow;
import webbroker3.srvregi.data.SrvRegiKeyDao;
import webbroker3.srvregi.data.SrvRegiKeyRow;
import webbroker3.srvregi.data.SrvRegiLotInfoRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービスマスター)<BR>
 * サービスマスターエンティティクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3SrvRegiServiceMaster implements BusinessObject 
{
    
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceMaster.class);
    

    /**
     * (サービスマスター行)<BR>
     */
    private SrvRegiMasterParams srvMasterParams;
    
    /**
     * (サービスマスター)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 当オブジェクトを生成し、<BR>
     * 引数.サービスマスターRowをthis.サービスマスター行に設定する。<BR>
     * @@param l_srvMasterRow - (サービスマスターRow)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 412F069E02D6
     */
    protected WEB3SrvRegiServiceMaster(SrvRegiMasterRow l_srvMasterRow) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " WEB3SrvRegiServiceMaster(SrvRegiMasterRow) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvMasterParams = new SrvRegiMasterParams(l_srvMasterRow);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * 　@this.サービスマスター行をコピーして、同じ内容の<BR>
     * 別インスタンスを作成する（clone）。<BR> 
     * 作成したコピーを自身のthis.サービスマスター行にセットする。<BR>
     * @@roseuid 413308C70100
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams() ";
        log.entering(STR_METHOD_NAME);

        SrvRegiMasterParams l_srvMasterParams = new SrvRegiMasterParams(this.srvMasterParams);
        this.srvMasterParams = l_srvMasterParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （getDataSourceObjectの実装）<BR> 
     * <BR> 
     * this.サービスマスター行を返却する。<BR>
     * @@return Object
     * @@roseuid 413308C700D1
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams;
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.サービスマスター行.get証券会社コード( )の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40F6797B03C2
     */
    public String getInstitutionCode() 
    {
        final String STR_METHOD_NAME = " getInstitutionCode() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getInstitutionCode();
    }
    
    /**
     * (getサービス区分)<BR>
     * サービス区分を返す。<BR>
     * <BR>
     * this.サービスマスター行.getサービス区分( )の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40F6799400B4
     */
    public String getSrvDiv() 
    {
        final String STR_METHOD_NAME = " getSrvDiv() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvDiv();
    }
    
    /**
     * (getサービス名称)<BR>
     * サービス名称を返す。<BR>
     * <BR>
     * this.サービスマスター行.getサービス名称( )の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412EF0870343
     */
    public String getSrvName() 
    {
        final String STR_METHOD_NAME = " getSrvName() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvName();
    }
    
    /**
     * (setサービス名称)<BR>
     * サービス名称の設定を行う。<BR>
     * <BR>
     * 1) this.サービスマスター行.setサービス名称( )をコールする。<BR>
     * [引数]<BR>
     * 　@サービス名称=引数.サービス名称<BR>
     * @@param l_strSrvName - (サービス名称)<BR>
     * @@roseuid 412EF0870314
     */
    public void setSrvName(String l_strSrvName) 
    {
        final String STR_METHOD_NAME = " setSrvName(String) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvMasterParams.setSrvName(l_strSrvName);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getステータス)<BR>
     * ステータスを返す。<BR>
     * <BR>
     * this.サービスマスター行.getステータス( )の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 4104A8F7010A
     */
    public String getStatus() 
    {
        final String STR_METHOD_NAME = " getStatus() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvStatus();
    }
    
    /**
     * (setステータス)<BR>
     * ステータスの設定を行う。<BR>
     * <BR>
     * 1) this.サービスマスター行.setステータス( )をコールする。<BR>
     * [引数]<BR>
     * 　@ステータス=引数.ステータス<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * @@roseuid 4104A8F700EA
     */
    public void setStatus(String l_strStatus) 
    {
        final String STR_METHOD_NAME = " setStatus(String) ";
        log.entering(STR_METHOD_NAME);

        this.srvMasterParams.setSrvStatus(l_strStatus);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getサービス利用URL)<BR>
     * サービス利用URLを返す。<BR>
     * <BR>
     * this.サービスマスター行.getサービス利用URL( )の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 4104A94E00BC
     */
    public String getSrvUrl() 
    {
        final String STR_METHOD_NAME = " getSrvUrl() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvMasterParams.getSrvUrl();
    }
    
    /**
     * (setサービス利用URL)<BR>
     * サービス利用URLの設定を行う。<BR>
     * <BR>
     * 1) this.サービスマスター行.setサービス利用URL( )をコールする。<BR>
     * [引数]<BR>
     * 　@サービス利用URL=引数.サービス利用URL<BR>
     * @@param l_strSrvUrl - (サービス利用URL)<BR>
     * @@roseuid 4104A94E00AC
     */
    public void setSrvUrl(String l_strSrvUrl) 
    {
        final String STR_METHOD_NAME = " setSrvUrl(String) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvMasterParams.setSrvUrl(l_strSrvUrl);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set申込区分)<BR>
     * 申込区分の設定を行う。<BR>
     * <BR>
     * 1) this.サービスマスター行.set申込区分( )をコールする。<BR>
     * [引数]<BR>
     * 　@申込区分=引数.申込区分<BR>
     * @@param l_strOfferingDiv - (申込区分)<BR>
     * @@roseuid 412081C6025E
     */
    public void setOfferingDiv(String l_strOfferingDiv) 
    {
        final String STR_METHOD_NAME = " setOfferingDiv(String) ";
        log.entering(STR_METHOD_NAME);

        this.srvMasterParams.setOfferingDiv(l_strOfferingDiv);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getサービス抽選情報)<BR>
     * 引数.通番からサービスに紐付くサービス抽選情報オブジェクトを返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス抽選情報テーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     * 　@通番=引数.通番<BR>
     * <BR>
     * 2) 検索結果=0件の場合、nullを返却する。<BR>
     * <BR>
     * 3) 取得した「サービス抽選情報Params」を引数に、サービス抽選情報の<BR>
     * 　@コンストラクタをコールし、インスタンスを生成する。<BR>
     * [引数]<BR>
     * 　@サービス抽選情報Row=取得したサービス抽選情報Params<BR>
     * <BR>
     * 4) 引数.is行ロック=trueの場合<BR>
     * 　@生成したサービス抽選情報.createForUpdateParams( )をコールする。<BR>
     * <BR>
     * 5) 生成したサービス抽選情報を返却する。<BR>
     * @@param l_lngConsecutiveNumbers - (通番)<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo
     * @@roseuid 412EF0B20288
     */
    public WEB3SrvRegiServiceLotInfo getSrvLotInfo(long l_lngConsecutiveNumbers, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvLotInfo(long, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiServiceLotInfo l_srvLotInfo = null;
        
        
        //according to the QA of WEB3-SEVREGI-1-CD-0013
        try
        {
            //1) 以下の条件で「サービス抽選情報テーブル」を検索する。
            String l_strWhere = " institution_code = ? and srv_div = ? and consecutive_numbers = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                new Long(l_lngConsecutiveNumbers)};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisSrvLotInfoRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiLotInfoRow.TYPE, 
                    l_strWhere,
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisSrvLotInfoRowList = 
                    l_queryProcessor.doFindAllQuery(SrvRegiLotInfoRow.TYPE, 
                    l_strWhere, 
                    l_obj);//DataNetworkException, DataQueryException
            }
            
            //2) 検索結果=0件の場合、nullを返却する。
            if (l_lisSrvLotInfoRowList == null || l_lisSrvLotInfoRowList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
                
            if (l_lisSrvLotInfoRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //3) 取得した「サービス抽選情報Params」を引数に、サービス抽選情報の
            l_srvLotInfo = new 
                WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(0));
            
            //4) 引数.is行ロック=trueの場合
            if (l_blnIsRowLock)
            {
                 log.debug("引数.is行ロック=trueの場合");
                 
                 l_srvLotInfo.createForUpdateParams();
            }
            
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);

        //5) 生成したサービス抽選情報を返却する。
        return l_srvLotInfo;
    }
    
    /**
     * (getサービス抽選情報一覧)<BR>
     * 当サービスのサービス抽選情報の配列を返す。<BR>
     * <BR>
     * 1) サービス抽選情報テーブルを検索し、サービス抽選情報Paramsの<BR>
     * Listを取得する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     * 　@適用終了日>=現在日時(*)の日付部分<BR>
     * [並び替え]<BR>
     * 　@適用開始日で昇順<BR>
     * <BR>
     * (*) GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) 取得したListからサービス抽選情報Paramsをとりだし、サービス<BR>
     * 抽選情報オブジェクトを<BR>
     * 　@生成する。生成したサービス抽選情報オブジェクトを配列に設定して返す。<BR>
     * [引数]<BR>
     * 　@サービス抽選情報Row=取得したサービス抽選情報Params<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo[ ]
     * @@roseuid 412EF067016E
     */
    public WEB3SrvRegiServiceLotInfo[] getSrvLotInfoList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvLotInfoList() ";
        log.entering(STR_METHOD_NAME);
                
        WEB3SrvRegiServiceLotInfo[] l_srvLotInfos = null;
        
        try
        {
            //1) サービス抽選情報テーブルを検索し、サービス抽選情報Paramsの
            String l_strWhere = " institution_code = ? and srv_div = ? and appli_end_date >= ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp())};
               
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE, 
                l_strWhere, 
                " appli_start_date asc ", 
                "",
                l_obj);//DataNetworkException, DataQueryException
                
            if (l_lisSrvLotInfoRowList != null)
            {
                //2) 取得したListからサービス抽選情報Paramsをとりだし、サービス
                int l_intSrvLotInfoRowCnt = l_lisSrvLotInfoRowList.size();
            
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[l_intSrvLotInfoRowCnt];
            
                for (int i = 0; i < l_intSrvLotInfoRowCnt; i++)
                {
                    l_srvLotInfos[i] =  new 
                        WEB3SrvRegiServiceLotInfo((SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(i));
    
                }
            }
            else
            {
                l_srvLotInfos = new WEB3SrvRegiServiceLotInfo[0];
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_srvLotInfos;
    }
    
    /**
     * (getサービス利用期間料金)<BR>
     * 引数.通番からサービスに紐付くサービス利用期間料金オブジェクトを返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用期間料金テーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     * 　@通番=引数.通番<BR>
     * <BR>
     * 2) 検索結果=0件の場合、nullを返却する。<BR>
     * <BR>
     * 3) 取得した「サービス利用期間料金Params」を引数に、サービス<BR>
     * 利用期間料金の<BR>
     * 　@コンストラクタをコールし、インスタンスを生成する。<BR>
     * [引数]<BR>
     * 　@サービス利用期間料金Row=取得したサービス利用期間料金Params<BR>
     * <BR>
     * 4) 引数.is行ロック=trueの場合<BR>
     * 　@生成したサービス利用期間料金.createForUpdateParams( )をコールする。<BR>
     * <BR>
     * 5) 生成したサービス利用期間料金を返却する。<BR>
     * @@param l_lngConsecutive_Numbers - (通番)<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt
     * @@roseuid 412EF0B20249
     */
    public WEB3SrvRegiServiceUsePeriodAmt getSrvUseTermAmt(long l_lngConsecutive_Numbers, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvUseTermAmt(long, boolean)  ";
        log.entering(STR_METHOD_NAME);        
        
        WEB3SrvRegiServiceUsePeriodAmt l_usePeriodAmt = null;
        
        try
        {
            //according to the QA of WEB3-SEVREGI-1-DD-0013
            //1) 以下の条件で「サービス利用期間料金テーブル」を検索する。             
            String l_strWhere = " institution_code = ? and srv_div = ? and consecutive_numbers = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                new Long(l_lngConsecutive_Numbers)};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
                        
            List l_lisUsePeriodAmtRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisUsePeriodAmtRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiChargeRow.TYPE, 
                    l_strWhere, 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisUsePeriodAmtRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiChargeRow.TYPE, 
                    l_strWhere, 
                    l_obj);//DataNetworkException ,DataQueryException
            }
                
            int l_intUsePeriodAmtRowCnt = l_lisUsePeriodAmtRowList.size();
                
            //2) 検索結果=0件の場合、nullを返却する。
            if (l_lisUsePeriodAmtRowList ==null || l_intUsePeriodAmtRowCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
                
            if (l_lisUsePeriodAmtRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //3) 取得した「サービス利用期間料金Params」を引数に、サービス利用期間料金
                l_usePeriodAmt = new 
                    WEB3SrvRegiServiceUsePeriodAmt((SrvRegiChargeRow)l_lisUsePeriodAmtRowList.get(0));
            
            //4) 引数.is行ロック=trueの場合
            if (l_blnIsRowLock)
            {
                log.debug("now isRowLock = true!");
                
                l_usePeriodAmt.createForUpdateParams();
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //5) 生成したサービス利用期間料金を返却する。
        return l_usePeriodAmt;        
    }
    
    /**
     * (getサービス利用期間料金一覧)<BR>
     * 当サービスのサービス利用期間料金の配列を返す。<BR>
     * <BR>
     * 1) サービス利用期間料金テーブルを検索し、サービス利用<BR>
     * 期間料金ParamsのListを取得する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( )<BR>
     * [並び替え]<BR>
     * 　@通番で昇順<BR>
     * <BR>
     * 2) 取得したListからサービス利用期間料金Paramsをとりだし、サービス<BR>
     * 利用期間料金オブジェクトを<BR>
     * 　@生成する。生成したサービス利用期間料金オブジェクトを配列に設定して返す。<BR>
     * [引数]<BR>
     * 　@サービス利用期間料金Row=取得したサービス利用期間料金Params<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt[ ]
     * @@roseuid 412EF0670101
     */
    public WEB3SrvRegiServiceUsePeriodAmt[] getSrvUseTermAmtList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvUseTermAmtList() ";
        log.entering(STR_METHOD_NAME);
                
        WEB3SrvRegiServiceUsePeriodAmt[] l_usePeriodAmts = null;
        
        try
        {
            //1) サービス利用期間料金テーブルを検索し、サービス利用  
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
               
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisUsePeriodAmtRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiChargeRow.TYPE, 
                l_strWhere, 
                " consecutive_numbers asc ",
                "", 
                l_obj);//DataNetworkException, DataQueryException
                
            if (l_lisUsePeriodAmtRowList != null)
            {
                //2) 取得したListからサービス利用期間料金Paramsをとりだし、サービス
                int l_intUsePeriodAmtRowCnt = l_lisUsePeriodAmtRowList.size();
            
                l_usePeriodAmts = new WEB3SrvRegiServiceUsePeriodAmt[l_intUsePeriodAmtRowCnt];
            
                for (int i = 0; i < l_intUsePeriodAmtRowCnt; i++)
                {
                    l_usePeriodAmts[i] =  new 
                        WEB3SrvRegiServiceUsePeriodAmt((SrvRegiChargeRow)l_lisUsePeriodAmtRowList.get(i));
        
                }
            }
            else
            {
                l_usePeriodAmts = new WEB3SrvRegiServiceUsePeriodAmt[0];
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_usePeriodAmts;
    }
    
    /**
     * (getサービス利用キー)<BR>
     * 引数.利用キーIDからサービスに紐付くサービス利用キーオブジェクトを返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用キーテーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and <BR>
     * 　@利用キー種別区分=引数.利用キー種別区分 and <BR>
     * 　@利用キーID=引数.get利用キーID( )<BR>
     * <BR>
     * 2) 検索結果=0件の場合、nullを返却する。<BR>
     * <BR>
     * 3) 取得した「サービス利用キーParams」を引数に、サービス利用キーの<BR>
     * 　@コンストラクタをコールし、インスタンスを生成する。<BR>
     * [引数]<BR>
     * 　@サービス利用キーRow=取得したサービス利用キーParams<BR>
     * <BR>
     * 4) 引数.is行ロック=trueの場合<BR>
     * 　@生成したサービス利用キー.createForUpdateParams( )をコールする。<BR>
     * <BR>
     * 5) 生成したサービス利用キーを返却する。<BR>
     * @@param l_strSrvUseKeyTypeDiv - (利用キー種別区分)<BR>
     * @@param l_lngUseKeyId - (利用キーID)<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceUseKey
     * @@roseuid 4132BA8F02F4
     */
    public WEB3SrvRegiServiceUseKey getSrvUseKey(String l_strSrvUseKeyTypeDiv, long l_lngUseKeyId, boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSrvUseKey(String, long, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiServiceUseKey l_serviceUseKey = null;
        
        try
        {
            //according to the QA of WEB3-SEVREGI-1-DD-0013
            //1) 以下の条件で「サービス利用キーテーブル」を検索する。
            String l_strWhere = " institution_code = ? and srv_div = ? and srv_use_key_type = ? and srv_use_id = ? ";
               
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                l_strSrvUseKeyTypeDiv, 
                new Long(l_lngUseKeyId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisServiceUseKeyRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisServiceUseKeyRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiKeyRow.TYPE, 
                    l_strWhere, 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisServiceUseKeyRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiKeyRow.TYPE, 
                    l_strWhere, 
                    l_obj);//DataNetworkException, DataQueryException
            }
                
            //2) 検索結果=0件の場合、nullを返却する。
            if (l_lisServiceUseKeyRowList == null || l_lisServiceUseKeyRowList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
               
            if (l_lisServiceUseKeyRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            //3) 取得した「サービス利用キーParams」を引数に、サービス利用キーの
            l_serviceUseKey = new WEB3SrvRegiServiceUseKey((SrvRegiKeyRow)l_lisServiceUseKeyRowList.get(0));
            
            //4) 引数.is行ロック=trueの場合
            if (l_blnIsRowLock)
            {
                log.debug("now isRowLock = true!");
                l_serviceUseKey.createForUpdateParams();
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //5) 生成したサービス利用キーを返却する。
        return l_serviceUseKey;
    }    
    
    /**
     * (get確認メール情報)<BR>
     * 当サービスの確認メール情報を返す。<BR>
     * <BR>
     * 1) メール情報管理.getメール( )をコールし、メールオブジェクトを取得して返す。<BR>
     * [引数]<BR>
     * 　@証券会社コード=this.get証券会社コード( )<BR>
     * 　@送信メール区分="サービス利用（確認メール）"<BR>
     * 　@識別ID=this.getサービス区分( )<BR>
     * @@return WEB3GentradeMail
     * @@roseuid 412EF0B201DC
     */
    public WEB3GentradeMailInfo getConfirmMailInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConfirmMailInfo() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return WEB3AdminMailInfoManager.getMail(
            this.getInstitutionCode(), 
            WEB3SendmailDivDef.SRVREGI_CONFIRM_MAIL, 
            this.getSrvDiv());
    }
    
    /**
     * (get契約期限メール情報)<BR>
     * 当サービスの契約期限メール情報を返す。<BR>
     * <BR>
     * 1) メール情報管理.getメール( )をコールし、メールオブジェクトを取得して返す。<BR>
     * [引数]<BR>
     * 　@証券会社コード=this.get証券会社コード( )<BR>
     * 　@送信メール区分="サービス利用（契約期限メール）"<BR>
     * 　@識別ID=this.getサービス区分( )<BR>
     * @@return WEB3GentradeMail
     * @@roseuid 412EF0B2021A
     */
    public WEB3GentradeMailInfo getEndMaiDivInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEndMaiDivInfo() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return WEB3AdminMailInfoManager.getMail(
            this.getInstitutionCode(), 
            WEB3SendmailDivDef.SRVREGI_TERM_MAIL, 
            this.getSrvDiv());
    }
    
    /**
     * (get申込要サービス)<BR>
     * サービスに紐付く申込要サービスオブジェクトを返却する。<BR>
     * <BR>
     * 1) 以下の条件で「申込要サービステーブル」を検索する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@　@サービス区分=this.getサービス区分( ) and<BR>
     * <BR>
     * 2) 検索結果=0件の場合、nullを返却する。<BR>
     * <BR>
     * 3) 取得した申込要サービスParamsを引数に、申込要サービスの<BR>
     * 　@コンストラクタをコールし、取得したオブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@申込要サービスRow=取得した申込要サービスParams<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService
     * @@roseuid 412EF6F1018E
     */
    public WEB3SrvRegiApplicationRequiredService getAppliRequiredSrv(boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAppliRequiredSrv(boolean) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiApplicationRequiredService l_applicationRequiredService = null;
        
        try
        {
            //1)  以下の条件で「申込要サービステーブル」を検索する。
            //according to the QA of WEB3-SEVREGI-1-DD-008
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisApplicationRequiredServiceRowList = null;
            if (l_blnIsRowLock)
            {
                l_lisApplicationRequiredServiceRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiSetupRow.TYPE, 
                    l_strWhere, 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisApplicationRequiredServiceRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiSetupRow.TYPE, 
                    l_strWhere, l_obj);//DataNetworkException, DataQueryException
            }
                
            //2) 検索結果=0件の場合、nullを返却する。
            if (l_lisApplicationRequiredServiceRowList == null || 
                l_lisApplicationRequiredServiceRowList.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
                
            if (l_lisApplicationRequiredServiceRowList.size() > 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //3) 取得した申込要サービスParamsを引数に、申込要サービスの
            l_applicationRequiredService = new WEB3SrvRegiApplicationRequiredService(
                (SrvRegiSetupRow)l_lisApplicationRequiredServiceRowList.get(0));
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_applicationRequiredService;
    }
    
    /**
     * (get同意書文言)<BR>
     * 同意書オブジェクトを取得し、返却する。<BR>
     * <BR>
     * 1) 同意書文言オブジェクトを生成する。<BR>
     * <BR>
     * 2) 同意書文言テーブルを検索し同意書文言ParamsのListを取得する。<BR>
     * （引数.is行ロックがtrueの場合、select for updateで検索を行う。)<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( )<BR>
     * [並び替え]<BR>
     * 　@行番号で昇順<BR>
     * <BR>
     * 3) Listの要素数が0の場合は、生成した同意書文言<BR>
     * オブジェクト.文言にnullを設定する。<BR>
     * <BR>
     * 4) Listの要素数 ＞ 0 の場合、以下の処理を行う。<BR>
     *  4-1) Listから順番に同意書文言Paramsを取り出す。<BR>
     *  4-2) 取り出した同意書文言Params.get文言( )の戻り値を連結して<BR>
     * 　@　@１つの文字列にし、生成した同意書文言オブジェクト.文言に設定する。<BR>
     * <BR>
     * 5) 生成した同意書文言オブジェクトを返却する。<BR>
     * @@param l_blnIsRowLock - (is行ロック)<BR>
     * true:行ロックを行う　@false:行ロックを行わない<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiConsDoc
     * @@roseuid 412EF5980065
     */
    public WEB3SrvRegiConsDoc getConsDoc(boolean l_blnIsRowLock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConsDoc(boolean) ";
        log.entering(STR_METHOD_NAME);
        
        //1) 同意書文言オブジェクトを生成する。
        WEB3SrvRegiConsDoc l_consDoc = new  WEB3SrvRegiConsDoc();
        
        try
        {
            //2) 同意書文言テーブルを検索し同意書文言ParamsのListを取得する。
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
           
            List l_lisConsDocRowList = null;
            
            SrvRegiConsDocRow l_consDocRow = null;
            
            String l_strCons = "";
            
            if (l_blnIsRowLock)
            {
                l_lisConsDocRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiConsDocRow.TYPE, 
                    l_strWhere, 
                    " line_number asc ", 
                    " FOR UPDATE ", 
                    l_obj);//DataNetworkException, DataQueryException
            }
            else
            {
                l_lisConsDocRowList = l_queryProcessor.doFindAllQuery(
                    SrvRegiConsDocRow.TYPE,  
                    l_strWhere, 
                    " line_number asc ",
                    "", 
                    l_obj);//DataNetworkException, DataQueryException
            } 
                
            //3) Listの要素数が0の場合は、生成した同意書文言
            if (l_lisConsDocRowList == null || l_lisConsDocRowList.size() == 0)
            {
                l_consDoc.setCons(null);
            }
            else                 
            //4) Listの要素数 ＞ 0 の場合、以下の処理を行う。
            {
                int l_intConsDocRowCnt = l_lisConsDocRowList.size();
                
                for (int i = 0; i < l_intConsDocRowCnt; i++)
                {
                    //4-1) Listから順番に同意書文言Paramsを取り出す。
                    l_consDocRow = (SrvRegiConsDocRow)l_lisConsDocRowList.get(i);
                    
                    //4-2) 取り出した同意書文言Params.get文言( )の戻り値を連結して
                    l_strCons += l_consDocRow.getLineValue();
                }
                l_consDoc.setCons(l_strCons);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //5) 生成した同意書文言オブジェクトを返却する
        return l_consDoc;
    }
    
    /** 
     * (get手数料条件一覧)<BR>
     * 当該サービスに付加されているサービス利用手数料条件の一覧を返却する。<BR> 
     * <BR>
     * －以下の検索条件で「サービス利用手数料条件テーブル」を検索する。<BR> 
     * [検索条件] <BR>
　@   * 証券会社コード=this.get証券会社コード( ) <BR>
　@   * サービス区分=this.getサービス区分( ) <BR>
     * <BR>
     * －検索結果を「サービス利用手数料条件」のリストにして返却する。<BR>
     * @@return List
     */
    public List getCommCondList() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCommCondList()";
        log.entering(STR_METHOD_NAME);
        
        List l_lisCommCondRows = null;
        try
        {
            //以下の検索条件で「サービス利用手数料条件テーブル」を検索する。
            String l_strWhere = " institution_code = ? and srv_div = ?";
                
            Object[] l_obj = {this.getInstitutionCode(), this.getSrvDiv()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_lisCommCondRows = l_queryProcessor.doFindAllQuery(
                SrvRegiCommCondRow.TYPE, 
                l_strWhere, l_obj);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lisCommCondRows;
    }

    /** 
     * (get手数料条件)<BR>
     * 指定されたサービス利用手数料条件を返却する。<BR> 
     * <BR>
     * －以下の検索条件で「サービス利用手数料条件テーブル」を検索する。<BR> 
     * [検索条件] <BR>
　@   * 証券会社コード=this.get証券会社コード( ) <BR>
　@   * サービス区分=this.getサービス区分( ) <BR>
　@   * 注文受付商品=引数.注文受付商品 <BR>
     * <BR>
     * －検索結果を「サービス利用手数料条件」オブジェクトにして返却する。<BR>
     * @@param l_strOrderAccProduct - (注文受付商品)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiCommCond
     */
    public WEB3SrvRegiCommCond getCommCond(String l_strOrderAccProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getCommCond(String) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCommCond l_commCondition = null;
        
        try
        {
            SrvRegiCommCondRow l_srvRegiCommCondRow = 
                SrvRegiCommCondDao.findRowByInstitutionCodeSrvDivOrderAccProduct(
                    this.getInstitutionCode(),
                    this.getSrvDiv(),
                    l_strOrderAccProduct);//DataNetworkException, DataQueryException
            if (l_srvRegiCommCondRow != null)
            {
                l_commCondition = new WEB3SrvRegiCommCond(this.getInstitutionCode(), this.getSrvDiv(), l_strOrderAccProduct);
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_commCondition;
    }
    
    /**
     * (is申込可能)<BR>
     * 当該サービスが申込可能かどうかを返却する。<BR>
     * <BR>
     * 1) this.サービスマスター行.getステータス()の戻り値="提供中"の場合<BR>
     * 　@trueを返却する。それ以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 412EF0C2017E
     */
    public boolean isAppliPossible() 
    {
        final String STR_METHOD_NAME = " isAppliPossible() ";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3SrvStatusDef.PROVIDING.equals(this.srvMasterParams.getSrvStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is申込要)<BR>
     * 当サービスが申込必要か否かを返す。<BR>
     * <BR>
     * this.サービスマスター行.get申込区分()の戻り値が"要"の場合は true を、<BR>
     * そうでない場合は false を返す。<BR>
     * @@return boolean
     * @@roseuid 40F679A001AE
     */
    public boolean isAppliRequired() 
    {
        final String STR_METHOD_NAME = " isAppliRequired() ";
        log.entering(STR_METHOD_NAME);

        if (WEB3SrvRegiOfferingDivDef.REQUIRE.equals(this.srvMasterParams.getOfferingDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is提供中)<BR>
     * 当該サービスが提供中かどうかを返却する。<BR>
     * <BR>
     * 1) this.サービスマスター行.getステータス()の戻り値="停止中"以外の場合<BR>
     * 　@trueを返却する。それ以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 412EF0C901AD
     */
    public boolean isProviding() 
    {
        final String STR_METHOD_NAME = " isProviding() ";
        log.entering(STR_METHOD_NAME);
        
        if (!WEB3SrvStatusDef.STOP.equals(this.srvMasterParams.getSrvStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (get第２URL)<BR>
     * 当該サービスに紐付く”第２URL”を返却する。<BR> 
     * <BR>
     * 1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR> 
     * [検索条件] <BR>
     *　@証券会社コード=this.get証券会社コード( ) and<BR>
     *　@サービス区分=this.getサービス区分( ) and<BR>
     *　@利用キー種別区分="第２URL" and <BR>
     *　@サービス利用キーID=１（固定値）<BR> 
     * <BR>
     * 2) 戻り値の設定 <BR>
     * 2-1) 1)の検索結果=1件の場合 <BR>
     *  1)で取得したサービス利用キーParams.getサービス利用キー()の <BR>
     *　@ 戻り値を返却する。<BR> 
     * <BR>
     * 2-2) ) 1)の検索結果=0件の場合、nullを返却する。<BR>
     * @@return String
     */
    public String getUrl2() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUrl2() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.URL2,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (getハッシュ計算方式区分)<BR>
     * 当該サービスに紐付く”ハッシュ計算方式区分”を返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * [検索条件] <BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     *　@ 利用キー種別区分="ハッシュ計算方式区分" and <BR> 
     *　@ サービス利用キーID=１（固定値）<BR> 
     * 2) 1)で取得したサービス利用キーParams.getサービス利用キー()の <BR>
     *　@戻り値を返却する。<BR>
     * @@return String
     */
    public String getHashCalHowToDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHashCalHowToDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.HASH_CAL_HOW_TO_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (getハッシュ計算手順区分)<BR>
     * 当該サービスに紐付く”ハッシュ計算手順区分”を返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * [検索条件] <BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     *　@ 利用キー種別区分="ハッシュ計算手順区分" and <BR> 
     *　@ サービス利用キーID=１（固定値）<BR> 
     * 2) 1)で取得したサービス利用キーParams.getサービス利用キー()の <BR>
     *　@戻り値を返却する。<BR>
     * @@return String
     */
    public String getHashCalOrderDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHashCalOrderDiv() ";
        log.entering(STR_METHOD_NAME);
    
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.HASH_CAL_ORDER_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
        
            log.exiting(STR_METHOD_NAME);
        
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
         
            log.exiting(STR_METHOD_NAME);
        
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get送信方法@区分)<BR>
     * 当該サービスに紐付く”送信方法@区分”を返却する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * [検索条件] <BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     *　@ 利用キー種別区分="送信方法@区分" and <BR> 
     *　@ サービス利用キーID=１（固定値）<BR> 
     * 2) 1)で取得したサービス利用キーParams.getサービス利用キー()の <BR>
     *　@戻り値を返却する。<BR>
     * @@return String
     */
    public String getSendHowToDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSendHowToDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.SEND_HOW_TO_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }   
    
    /**
     * (get暗号化顧客コード区分)<BR>
     * 当該サービスに紐付く”暗号化顧客コード区分”を判定する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * [検索条件] <BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     *　@ 利用キー種別区分="暗号化顧客コード区分" and <BR> 
     *　@ サービス利用キーID=１（固定値）<BR> 
     * 2) 1)で取得したサービス利用キーParams.getサービス利用キー()の <BR>
     *　@戻り値を返却する。<BR>
     * @@return String
     */
    public String getCryptAccountCodeDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCryptAccountCodeDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.CRYPT_ACCOUNT_CODE_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get送信パラメータ区分)<BR>
     * 該サービスに紐付く”送信パラメータ区分”を判定する。<BR>
     * <BR>
     * 1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * [検索条件] <BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     *　@ 利用キー種別区分="送信パラメータ区分" and <BR> 
     *　@ サービス利用キーID=１（固定値）<BR> 
     * 2) 1)で取得したサービス利用キーParams.getサービス利用キー()の <BR>
     *　@戻り値を返却する。<BR>
     * @@return String
     */
    public String getSendParamDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSendParamDiv() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SrvRegiKeyRow l_row = SrvRegiKeyDao.findRowByInstitutionCodeSrvDivSrvUseKeyTypeSrvUseId(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.SEND_PARAM_DIV,
                1);//DataNetworkException, DataQueryException
            if (l_row != null)
            {
                return l_row.getSrvUseKey();
            }
            else
            {
                return null;
            }
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }    
    
    /**
     * (getハッシュ値一覧) <BR>
     *当該サービスに紐付くハッシュ値の一覧を返却する。<BR> 
     * <BR>
     *1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR> 
     *[検索条件] <BR>
     *　@証券会社コード=this.get証券会社コード( ) and<BR>
     *　@サービス区分=this.getサービス区分( ) and<BR>
     *　@利用キー種別区分="ハッシュ値" <BR>
     * [並び替え]<BR>
     * 　@サービス利用キーIDで昇順<BR>
     * <BR>
     *2) 1)の検索結果をサービス利用キーの配列として返却する。<BR>
     * @@return サービス利用キー[]
     */
    public WEB3SrvRegiServiceUseKey[] getHashList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHashList() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strWhere = " institution_code = ? and srv_div = ? and srv_use_key_type = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                WEB3SrvUseKeyTypeDef.HSAH_VALUE};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException

            //[並び替え]
            // サービス利用キーIDで昇順
            String l_strSortKey = "srv_use_id ASC";
            List l_lisRows = l_queryProcessor.doFindAllQuery(
                SrvRegiKeyRow.TYPE, 
                l_strWhere,
                l_strSortKey,
                null,
                l_obj);//DataNetworkException, DataQueryException
                
            WEB3SrvRegiServiceUseKey[] l_useKeys = null;
            
            int l_intCnt = l_lisRows.size();
            
            if (l_lisRows != null &&  l_intCnt > 0)
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[l_intCnt];
                for (int i = 0; i < l_intCnt; i++)
                {
                    SrvRegiKeyRow l_row = (SrvRegiKeyRow)l_lisRows.get(i);
                    l_useKeys[i] = new WEB3SrvRegiServiceUseKey(l_row);
                }
            }
            else
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[0];
            }
            return l_useKeys;
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get送信パラメータ一覧 ) <BR>
     *当該サービスに紐付く送信パラメータの一覧を返却する。<BR> 
     * <BR>
     *1) 以下の条件で「サービス利用キー」テーブルを検索する。<BR> 
     *[検索条件] <BR>
     *　@証券会社コード=this.get証券会社コード( ) and<BR>
     *　@サービス区分=this.getサービス区分( ) and<BR>
     *　@利用キー種別区分="送信パラメータ" <BR>
     * <BR>
     *2) 1)の検索結果をサービス利用キーの配列として返却する。<BR>
     * @@return サービス利用キー[]
     */
    public WEB3SrvRegiServiceUseKey[] getParamList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getParamList() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strWhere = " institution_code = ? and srv_div = ? and srv_use_key_type = ? ";
                
            Object[] l_obj = {this.getInstitutionCode(), 
                this.getSrvDiv(), 
                WEB3SrvUseKeyTypeDef.SEND_PARAM};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisRows = l_queryProcessor.doFindAllQuery(
                SrvRegiKeyRow.TYPE, 
                l_strWhere, l_obj);//DataNetworkException, DataQueryException
                
            WEB3SrvRegiServiceUseKey[] l_useKeys = null;
            
            int l_intCnt = l_lisRows.size();
            
            if (l_lisRows != null &&  l_intCnt > 0)
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[l_intCnt];
                for (int i = 0; i < l_intCnt; i++)
                {
                    SrvRegiKeyRow l_row = (SrvRegiKeyRow)l_lisRows.get(i);
                    l_useKeys[i] = new WEB3SrvRegiServiceUseKey(l_row);
                }
            }
            else
            {
                l_useKeys = new WEB3SrvRegiServiceUseKey[0];
            }
            return l_useKeys;
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (createNewサービスマスター)<BR>
     * 新規にサービスマスターオブジェクトを生成して返却する。<BR>
     * <BR>
     * 1) サービスマスターParamsオブジェクトを生成する。<BR>
     * <BR>
     * 2) サービスマスターParams.set証券会社コード()をコールする。<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * 3) サービスマスターParams.setサービス区分()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 4) サービスマスターParams.set申込区分()をコールする。<BR>
     * [引数]<BR>
     * 　@申込区分=引数.申込区分<BR>
     * <BR>
     * 5) サービスマスターParams.setステータス( )をコールする。<BR>
     * [引数]<BR>
     * 　@ステータス="停止中"<BR>
     * <BR>
     * 6) サービスマスターのコンストラクタをコールし、生成した<BR>
     * 　@サービスマスターオブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@サービスマスターRow=作成したサービスマスターParamsオブジェクト<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strOfferingDiv - (申込区分)<BR>
     * 0：不要　@1：要<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceMaster
     * @@throws WEB3BaseException
     * @@roseuid 413E60E601C6
     */
    public static WEB3SrvRegiServiceMaster createNewSrvMaster(String l_strInstitutionCode, String l_strSrvDiv, String l_strOfferingDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvMaster(String, String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //1) サービスマスターParamsオブジェクトを生成する
        SrvRegiMasterParams l_masterParams = new SrvRegiMasterParams();
        
        //2) サービスマスターParams.set証券会社コード()をコールする。
        l_masterParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) サービスマスターParams.setサービス区分()をコールする。
        l_masterParams.setSrvDiv(l_strSrvDiv);
        
        //4) サービスマスターParams.set申込区分()をコールする。
        l_masterParams.setOfferingDiv(l_strOfferingDiv);
        
        //5) サービスマスターParams.setステータス( )をコールする。
        l_masterParams.setSrvStatus(WEB3SrvStatusDef.STOP);
        
        //6) サービスマスターのコンストラクタをコールし、生成した
        WEB3SrvRegiServiceMaster l_serviceMaster = new WEB3SrvRegiServiceMaster(l_masterParams);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_serviceMaster;
    }
    
    /**
     * (saveサービスマスター)<BR>
     * this.サービスマスター行オブジェクトの内容をデータベースに反映させる。(Update)<BR>
     * <BR>
     * 1) this.サービスマスター行オブジェクトに、以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービスマスター行オブジェクトの内容で、<BR>
     * 　@サービスマスターテーブルを更新（Update）する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E60E601E5
     */
    public void saveSrvMaster() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveSrvMaster()  ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.サービスマスター行オブジェクトに、以下の値をセットする。
            this.srvMasterParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvMasterParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        
            //2) this.サービスマスター行オブジェクトの内容で、
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doUpdateQuery(this.srvMasterParams);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (saveNewサービスマスター)<BR>
     * this.サービスマスター行オブジェクトの内容をデータベースに反映させる。(Insert)<BR>
     * <BR>
     * 1) this.サービスマスター行オブジェクトに、以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービスマスター行オブジェクトの内容で、<BR>
     * 　@サービスマスターテーブルを更新（Insert）する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E60E60204
     */
    public void saveNewSrvMaster() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewSrvMaster() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.サービスマスター行オブジェクトに、以下の値をセットする。
            this.srvMasterParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvMasterParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            this.srvMasterParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        
            //this.サービスマスター行オブジェクトの内容で、
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doInsertQuery(srvMasterParams);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceMaster.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * (get付加区分)<BR>
     * 当該サービスに紐付く”付加区分”を返却する。<BR>
     * <BR>
     * 1)　@以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード=this.get証券会社コード( )and<BR>
     * 　@　@サービス区分=this.getサービス区分( ) and <BR>
     * 　@　@利用キー種別区分="付加区分" and<BR>
     * 　@　@サービス利用キーID=１（固定値）<BR>
     * <BR>
     * 2)　@戻り値の設定<BR>
     * 　@2-1)　@1)の検索結果が取得できる場合<BR>
     * 　@　@1)で取得したサービス利用キーParams.getサービス利用キー()の<BR>
     * 　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@2-2)　@1)の検索結果が取得できない場合、nullを返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionDiv()";
        log.entering(STR_METHOD_NAME);

        SrvRegiKeyRow l_srvRegiKeyRow = null;
        //[検索条件]
        //証券会社コード=this.get証券会社コード( )
        //サービス区分=this.getサービス区分( ) and
        //利用キー種別区分="付加区分" and
        //サービス利用キーID=１（固定値)
        try
        {
        	l_srvRegiKeyRow = SrvRegiKeyDao.findRowByPk(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.ADDITION_DIV,
                1l);
        }
        catch (DataFindException l_ex)
        {
            //1)の検索結果が取得できない場合、nullを返却する。
            log.debug("サービス利用キーが取得出来なかった場合");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1)の検索結果が取得できる場合
        //1)で取得したサービス利用キーParams.getサービス利用キー()の
        //戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiKeyRow.getSrvUseKey();
    }

    /**
     * (get付加区分)<BR>
     * 当該サービスに紐付く”付加区分”を返却する。<BR>
     * <BR>
     * 1)　@以下の条件で「サービス利用キー」テーブルを検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=this.get証券会社コード( ) and<BR>
     * 　@サービス区分=this.getサービス区分( ) and<BR>
     * 　@利用キー種別区分="付加区分" and<BR>
     * 　@サービス利用キーID=引数.サービス利用キーID<BR>
     * <BR>
     * 2) 戻り値の設定<BR>
     * 　@2-1)　@1)の検索結果が取得できる場合<BR>
     * 　@　@1)で取得したサービス利用キーParams.getサービス利用キー()の<BR>
     * 　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@2-2)　@1)の検索結果が取得できない場合、nullを返却する。<BR>
     * <BR>
     * @@param l_intSrvUseId - (サービス利用キーID)<BR>
     * サービス利用キーID<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionDiv(int l_intSrvUseId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionDiv(int)";
        log.entering(STR_METHOD_NAME);

        SrvRegiKeyRow l_srvRegiKeyRow = null;
        try
        {
            //以下の条件で「サービス利用キー」テーブルを検索する
            //[検索条件]
            //　@証券会社コード=this.get証券会社コード( ) and
            //　@サービス区分=this.getサービス区分( ) and
            //　@利用キー種別区分="付加区分" and
            //　@サービス利用キーID=引数.サービス利用キーID
            l_srvRegiKeyRow = SrvRegiKeyDao.findRowByPk(
                this.getInstitutionCode(),
                this.getSrvDiv(),
                WEB3SrvUseKeyTypeDef.ADDITION_DIV,
                l_intSrvUseId);
        }
        catch (DataFindException l_ex)
        {
            //検索結果が取得できない場合、nullを返却する
            log.debug("サービス利用キーが取得出来なかった場合");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //検索結果が取得できる場合
        //取得したサービス利用キーParams.getサービス利用キー()の戻り値を返却する
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiKeyRow.getSrvUseKey();
    }
}
@
