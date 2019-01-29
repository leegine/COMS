head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCommCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用手数料条件(WEB3SrvRegiCommCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi;

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
import webbroker3.srvregi.data.SrvRegiCommCondParams;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用手数料条件)<BR>
 * サービス利用手数料条件クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3SrvRegiCommCond implements BusinessObject
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiCommCond.class);

    /**
     * (サービス利用手数料条件行)
     */
    private SrvRegiCommCondParams srvCommCondParams;

    /**
     * (サービス利用手数料条件)<BR>
     * コンストラクタ<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strSrvDiv - サービス区分
     * @@param l_strOrderAccProduct - 注文受付商品
     * @@roseuid 4186E39F0339
     */
    protected WEB3SrvRegiCommCond(String l_strInstitutionCode, String l_strSrvDiv, String l_strOrderAccProduct) 
    {
        this.srvCommCondParams = new SrvRegiCommCondParams();
        this.srvCommCondParams.setInstitutionCode(l_strInstitutionCode);
        this.srvCommCondParams.setSrvDiv(l_strSrvDiv);
        this.srvCommCondParams.setOrderAccProduct(l_strOrderAccProduct);     
    }
    
    /**
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * 　@this.サービス利用手数料条件行をコピーして、同じ内容の<BR>
     * 別インスタンスを作成する（clone）。<BR>
     * 作成したコピーを自身のthis.サービス利用手数料条件行にセットする。<BR>
     * @@roseuid 4186E3C1023F
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams() ";
        log.entering(STR_METHOD_NAME);

        SrvRegiCommCondParams l_srvCommCondParams = new SrvRegiCommCondParams(this.srvCommCondParams);
        this.srvCommCondParams = l_srvCommCondParams;
        
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * （getDataSourceObjectの実装）<BR> 
     * <BR>
     * this.サービス利用手数料条件行を返却する。<BR>
     * @@return Object
     * @@roseuid 4186E3C1025E
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvCommCondParams;
    }
    
    /**
     * (get注文受付商品)<BR>
     * this.サービス利用手数料条件マスター行.get注文受付商品( )の<BR>
     * 戻り値を返却する。<BR>
     * @@return String
     * @@roseuid 4187002F0370
     */
    public String getOrderAccProduct() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.srvCommCondParams.getOrderAccProduct();
    }
    
    /**
     * (createNewサービス利用手数料条件)<BR>
     * サービス利用手数料条件オブジェクトを新規に生成する。<BR>
     * <BR>
     * －サービス利用手数料条件のコンストラクタをコールし、取得した<BR>
     * 　@サービス利用手数料条件オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strSrvDiv - サービス区分
     * @@param l_strOrderAccProduct - 注文受付商品
     * @@return サービス利用.（サービス利用）商品エンティティ.WEB3SrvRegiCommCondition
     * @@roseuid 4186E3920126
     */
    public static WEB3SrvRegiCommCond createNewSrvRegiCommCondition(String l_strInstitutionCode, String l_strSrvDiv, String l_strOrderAccProduct) 
    {
        final String STR_METHOD_NAME = " createNewSrvRegiCommCondition(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCommCond l_srvCommCondition = 
            new WEB3SrvRegiCommCond(l_strInstitutionCode, l_strSrvDiv, l_strOrderAccProduct);
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvCommCondition;
    }
    
    /**
     * (saveNewサービス利用手数料条件)<BR>
     * this.サービス利用手数料条件行の内容で、<BR>
     * サービス利用手数料条件テーブルに新規行をINSERTする。<BR>
     * <BR>
     * 1) this.サービス利用手数料条件行の以下の項目に値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス利用手数料条件行の内容で、<BR>
     * サービス利用手数料条件テーブル<BR>
     * 　@にINSERT処理を行う。<BR>
     * @@roseuid 4186E408002C
     */
    public void saveNewSrvRegiCommCondition() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewSrvRegiCommCondition() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.サービス利用手数料条件行の以下の項目に値をセットする。
            this.srvCommCondParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvCommCondParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            this.srvCommCondParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //2) this.サービス利用手数料条件行の内容で、サービス利用手数料条件テーブル
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doInsertQuery(this.srvCommCondParams);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
            
        log.exiting(STR_METHOD_NAME);                    
    }
    
    /**
     * (removeサービス利用手数料条)<BR>
     * サービス利用手数料条件テーブルより当該行を削除する。<BR>
     * <BR>
     * 1) 以下の条件でサービス利用手数料条件テーブルに削除処理を行う。<BR>
     * [削除条件]<BR>
     * 　@証券会社コード=this.証券会社コード<BR>
     * 　@サービス区分=this.サービス区分<BR>
     * 　@注文受付商品=this.注文受付商品<BR>
     * <BR>
     * 2) this.サービス利用手数料条件行のインスタンスを破棄する。<BR>
     * @@roseuid 4186E439004B
     */
    public void removeSrvRegiCommCondition() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvRegiCommCondition() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) 以下の条件でサービス利用手数料条件テーブルを検索する。
            String l_strWhere = " institution_code = ? and srv_div = ? and order_acc_product = ? ";
                
            Object[] l_obj = {this.srvCommCondParams.getInstitutionCode(),
                this.srvCommCondParams.getSrvDiv(),
                this.srvCommCondParams.getOrderAccProduct()};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiCommCondRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);    
    }
}
@
