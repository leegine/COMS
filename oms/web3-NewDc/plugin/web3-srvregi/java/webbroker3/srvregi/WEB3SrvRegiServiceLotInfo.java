head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceLotInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス抽選情報(WEB3SrvRegiServiceLotInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 郭英 (中訊) 新規作成
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
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiLotInfoParams;
import webbroker3.srvregi.data.SrvRegiLotInfoRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス抽選情報)<BR>
 * サービス抽選情報クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3SrvRegiServiceLotInfo implements BusinessObject 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceLotInfo.class);
    
    /**
     * (サービス抽選情報行)<BR>
     */
    private SrvRegiLotInfoParams srvLotInfoParams;
    
    /**
     * (サービス抽選情報)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 当オブジェクトを生成し、<BR>
     * 引数.サービス抽選情報Rowをthis.サービス抽選情報行にセットする。<BR>
     * @@param l_srvLotInfoRow - (サービス抽選情報Row)<BR>
     * @@roseuid 412F0ADD0334
     */
    protected WEB3SrvRegiServiceLotInfo(SrvRegiLotInfoRow l_srvLotInfoRow) 
    {
        final String STR_METHOD_NAME = " WEB3SrvRegiServiceLotInfo(SrvRegiLotInfoRow) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams = new SrvRegiLotInfoParams(l_srvLotInfoRow);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （getDataSourceObjectの実装）<BR> 
     * <BR>
     * this.サービス抽選情報行を返却する。<BR>
     * @@return Object
     * @@roseuid 4133090903BF
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvLotInfoParams;
    }
    
    /**
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * 　@this.サービス抽選情報行をコピーして、同じ内容の別インスタンスを<BR>
     * 作成する（clone）。<BR> 
     * 作成したコピーを自身のthis.サービス抽選情報行にセットする。<BR>
     * @@roseuid 4133090A0006
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams() ";
        log.entering(STR_METHOD_NAME);

        SrvRegiLotInfoParams l_srvLotInfoParams = new SrvRegiLotInfoParams(this.srvLotInfoParams);
        this.srvLotInfoParams = l_srvLotInfoParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (createNew通番)<BR>
     * サービス抽選情報の新規通番を返却する。<BR>
     * (staticメソッド)<BR>
     * <BR>
     * 1) 以下の条件で「サービス抽選情報」テーブルを検索する。<BR>
     * [検索条件]<BR>
     * 　@　@証券会社コード=引数.証券会社コード and<BR>
     * 　@　@サービス区分=引数.サービス区分<BR>
     * [並び順]<BR>
     * 　@　@通番　@降順<BR>
     * <BR>
     * 2) 検索結果の件数=0件の場合、1を返却する。<BR>
     * <BR>
     * 3) 検索結果の件数>0件の場合、検索結果の先頭の要素となる<BR>
     * 　@サービス抽選情報Params.get通番( )+1を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@return long
     * @@roseuid 412F0F900362
     */
    public static long createNewConsecutiveNumbers(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewConsecutiveNumbers(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) 以下の条件で「サービス抽選情報」テーブルを検索する。
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {l_strInstitutionCode, l_strSrvDiv};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE, 
                l_strWhere, 
                " consecutive_numbers desc ",
                "", 
                l_obj);//DataNetworkException, DataQueryException
                                  
            int l_intSrvLotInfoRowCnt = l_lisSrvLotInfoRowList.size();
            
            //2) 検索結果の件数=0件の場合、1を返却する。
            if (l_intSrvLotInfoRowCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            
            //3) 検索結果の件数>0件の場合、検索結果の先頭の要素となる
            long l_lngConsecutiveNumbers = 
                ((SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(0)).getConsecutiveNumbers()+1;
            log.exiting(STR_METHOD_NAME);
            
            return l_lngConsecutiveNumbers;
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
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get証券会社コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 4104884A0271
     */
    public String getInstitutionCode() 
    {
        final String STR_METHOD_NAME = " getInstitutionCode() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getInstitutionCode();
    }
    
    /**
     * (getサービス区分)<BR>
     * サービス区分を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.getサービス区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 4104884A02EE
     */
    public String getSrvDiv() 
    {
        final String STR_METHOD_NAME = " String getSrvDiv() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getSrvDiv();
    }
    
    /**
     * (get通番)<BR>
     * 通番を返す。<BR>
     * <BR>
     * this.サービス利用期間料金行.get通番()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 4122B6630152
     */
    public long getConsecutiveNumbers() 
    {
        final String STR_METHOD_NAME = " getConsecutiveNumbers() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getConsecutiveNumbers();
    }
    
    /**
     * (set申込期間（自）)<BR>
     * 申込期間（自）の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set申込期間（自）()をコールする。<BR>
     * [引数]<BR>
     * 　@申込期間（自）=引数.申込期間（自）<BR>
     * @@param l_tsAppliDateFrom - (申込期間（自）)<BR>
     * @@roseuid 4133CF420074
     */
    public void setAppliDateFrom(Timestamp l_tsAppliDateFrom) 
    {
        final String STR_METHOD_NAME = " setAppliDateFrom(Timestamp) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setAppliDateFrom(l_tsAppliDateFrom);
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (get申込期間（自）)<BR>
     * 申込期間（自）を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get申込期間（自）()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 4104884A034C
     */
    public Timestamp getAppliDateFrom() 
    {
        final String STR_METHOD_NAME = " getAppliDateFrom() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getAppliDateFrom();
    }
    
    /**
     * (set申込期間（至）)<BR>
     * 申込期間（至）の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set申込期間（至）()をコールする。<BR>
     * [引数]<BR>
     * 　@申込期間（至）=引数.申込期間（至）<BR>
     * @@param l_tsAppliDateTo - (申込期間（至）)<BR>
     * @@roseuid 4104884A037B
     */
    public void setAppliDateTo(Timestamp l_tsAppliDateTo) 
    {        
        final String STR_METHOD_NAME = " setAppliDateTo(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setAppliDateTo(l_tsAppliDateTo);
        log.exiting(STR_METHOD_NAME);
 
    }
    
    /**
     * (get申込期間（至）)<BR>
     * 申込期間（至）を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get申込期間（至）()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 4104884A03C9
     */
    public Timestamp getAppliDateTo() 
    {
        final String STR_METHOD_NAME = " getAppliDateTo() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getAppliDateTo();
    }
    
    /**
     * (set抽選日)<BR>
     * 抽選日の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set抽選日()をコールする。<BR>
     * [引数]<BR>
     * 　@抽選日=引数.抽選日<BR>
     * @@param l_tsLotDate - (抽選日)<BR>
     * @@roseuid 41048C2401B6
     */
    public void setLotDate(Timestamp l_tsLotDate) 
    {
        final String STR_METHOD_NAME = " setLotDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setLotDate(l_tsLotDate);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get抽選日)<BR>
     * 抽選日を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get抽選日()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 41048C240271
     */
    public Timestamp getLotDate() 
    {
        final String STR_METHOD_NAME = " getLotDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvLotInfoParams.getLotDate();
    }
    
    /**
     * (set適用開始日)<BR>
     * 適用開始日の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set適用開始日()をコールする。<BR>
     * [引数]<BR>
     * 　@適用開始日=引数.適用開始日<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@roseuid 41048CAA0290
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate) 
    {
        final String STR_METHOD_NAME = " setAppliStartDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setAppliStartDate(l_tsAppliStartDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get適用開始日)<BR>
     * 適用開始日を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get適用開始日()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 41048CAA02FE
     */
    public Timestamp getAppliStartDate() 
    {
        final String STR_METHOD_NAME = " getAppliStartDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getAppliStartDate();
    }
    
    /**
     * (set適用終了日)<BR>
     * 適用終了日の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set適用終了日()をコールする。<BR>
     * [引数]<BR>
     * 　@適用終了日=引数.適用終了日<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@roseuid 41048CFD0177
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate) 
    {
        final String STR_METHOD_NAME = " setAppliEndDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setAppliEndDate(l_tsAppliEndDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get適用終了日)<BR>
     * 適用終了日を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get適用終了日()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 41048CFD0223
     */
    public Timestamp getAppliEndDate() 
    {
        final String STR_METHOD_NAME = " getAppliEndDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvLotInfoParams.getAppliEndDate();
    }
    
    /**
     * (set利用料金)<BR>
     * 利用料金の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set利用料金()をコールする。<BR>
     * [引数]<BR>
     * 　@利用料金=引数.利用料金<BR>
     * @@param l_lngUseAmt - (利用料金)<BR>
     * @@roseuid 4104884B00DB
     */
    public void setUseAmt(long l_lngUseAmt) 
    {
        final String STR_METHOD_NAME = " setUseAmt(long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setUseAmt(l_lngUseAmt);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (get利用料金)<BR>
     * 利用料金を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get利用料金()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 4104884B0139
     */
    public long getUseAmt() 
    {
        final String STR_METHOD_NAME = " getUseAmt() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getUseAmt();
    }
    
    /**
     * (set入札単位)<BR>
     * 入札単位の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set入札単位()をコールする。<BR>
     * [引数]<BR>
     * 　@入札単位=引数.入札単位<BR>
     * @@param l_lngBiddingPrice - (入札単位)<BR>
     * @@roseuid 41048DDA0213
     */
    public void setBiddingPrice(Long l_lngBiddingPrice) 
    {
        final String STR_METHOD_NAME = " setBiddingPrice(Long) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setBiddingPrice(l_lngBiddingPrice); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get入札単位)<BR>
     * 入札単位を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get入札単位()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 41048DDA02B0
     */
    public Long getBiddingPrice() 
    {
        final String STR_METHOD_NAME = " getBiddingPrice() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.srvLotInfoParams.getBiddingPriceIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getBiddingPrice());
        }
    }
    
    /**
     * (set出金日)<BR>
     * 出金日の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set出金日()をコールする。<BR>
     * [引数]<BR>
     * 　@出金日=引数.出金日<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@roseuid 41048E2D03C9
     */
    public void setPaymentDate(Timestamp l_tsPaymentDate) 
    {
        final String STR_METHOD_NAME = " setPaymentDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setPaymentDate(l_tsPaymentDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get出金日)<BR>
     * 出金日を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get出金日()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 41048E2E001F
     */
    public Timestamp getPaymentDate() 
    {
        final String STR_METHOD_NAME = " getPaymentDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getPaymentDate();
    }
    
    /**
     * (set募集枠)<BR>
     * 募集枠の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set募集枠()をコールする。<BR>
     * [引数]<BR>
     * 　@募集枠=引数.募集枠<BR>
     * @@param l_lngPublicOfferingQty - (募集枠)<BR>
     * @@roseuid 41048F1C01F4
     */
    public void setPublicOfferingQty(Long l_lngPublicOfferingQty) 
    {
        final String STR_METHOD_NAME = " setPublicOfferingQty(Long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setPublicOfferingQty(l_lngPublicOfferingQty); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get募集枠)<BR>
     * 募集枠を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get募集枠()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 41048F1C0252
     */
    public Long getPublicOfferingQty() 
    {
        final String STR_METHOD_NAME = " getPublicOfferingQty() ";
        log.entering(STR_METHOD_NAME);
        
        
        if (this.srvLotInfoParams.getPublicOfferingQtyIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getPublicOfferingQty());
        }
    }
    
    /**
     * (set最高落札額)<BR>
     * 最高落札額の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set最高落札額()をコールする。<BR>
     * [引数]<BR>
     * 　@最高落札額=引数.最高落札額<BR>
     * @@param l_lngHighSuccessBid - (最高落札額)<BR>
     * @@roseuid 41048F6B02A0
     */
    public void setHighSuccessBid(Long l_lngHighSuccessBid) 
    {
        final String STR_METHOD_NAME = " setHighSuccessBid(Long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setHighSuccessBid(l_lngHighSuccessBid); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get最高落札額)<BR>
     * 最高落札額を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get最高落札額()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 41048F6B02EE
     */
    public Long getHighSuccessBid() 
    {
        final String STR_METHOD_NAME = " getHighSuccessBid() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.srvLotInfoParams.getHighSuccessBidIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getHighSuccessBid());
        }
    }
    
    /**
     * (set最低落札額)<BR>
     * 最低落札額の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set最低落札額()をコールする。<BR>
     * [引数]<BR>
     * 　@最低落札額=引数.最低落札額<BR>
     * @@param l_lngLowSuccesBid - (最低落札額)<BR>
     * @@roseuid 41048FC800FA
     */
    public void setLowSuccessBid(Long l_lngLowSuccesBid) 
    {
        final String STR_METHOD_NAME = "setLowSuccessBid(Long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setLowSuccessBid(l_lngLowSuccesBid); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get最低落札額)<BR>
     * 最低落札額を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get最低落札額()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 41048FC80167
     */
    public Long getLowSuccessBid() 
    {
        final String STR_METHOD_NAME = " getLowSuccessBid() ";
        log.entering(STR_METHOD_NAME);

        if (this.srvLotInfoParams.getLowSuccessBidIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getLowSuccessBid());
        }
    }
    
    /**
     * (set加重平均額)<BR>
     * 加重平均額の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set加重平均額()をコールする。<BR>
     * [引数]<BR>
     * 　@加重平均額=引数.加重平均額<BR>
     * @@param l_lngAverageSuccessBid - (加重平均額)<BR>
     * @@roseuid 4104900F0233
     */
    public void setAverageSuccessBid(Long l_lngAverageSuccessBid) 
    {
        final String STR_METHOD_NAME = " setAverageSuccessBid(Long) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setAverageSuccessBid(l_lngAverageSuccessBid);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get加重平均額)<BR>
     * 加重平均額を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get加重平均額()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 4104900F02A0
     */
    public Long getAverageSuccessBid() 
    {
        final String STR_METHOD_NAME = " getAverageSuccessBid() ";
        log.entering(STR_METHOD_NAME);


        if (this.srvLotInfoParams.getAverageSuccessBidIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getAverageSuccessBid());
        } 
    }
    
    /**
     * (set運用区分)<BR>
     * 運用区分の設定を行う。<BR>
     * <BR>
     * 1) this.サービス抽選情報行.set運用区分()をコールする。<BR>
     * [引数]<BR>
     * 　@運用区分=引数.運用区分<BR>
     * @@param l_strInvestDiv - (運用区分)<BR>
     * @@roseuid 412AE771023D
     */
    public void setInvestDiv(String l_strInvestDiv) 
    {
        final String STR_METHOD_NAME = " setInvestDiv(String) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setInvestDiv(l_strInvestDiv); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get運用区分)<BR>
     * 運用区分を返す。<BR>
     * <BR>
     * this.サービス抽選情報行.get運用区分()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 412AE7710318
     */
    public String getInvestDiv() 
    {
        final String STR_METHOD_NAME = " getInvestDiv() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getInvestDiv();
    }
    
    /**
     * (isオークション設定)<BR>
     * オークションの有無を返却する。<BR>
     * <BR>
     * 1) this.get運用区分( )="通常運用（抽選有オークション）"の場合<BR>
     * 　@trueを返却する。<BR>
     * <BR>
     * 2) 上記以外の場合、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 412F020E0334
     */
    public boolean isAuctionSetting() 
    {
        final String STR_METHOD_NAME = " isAuctionSetting() ";
        log.entering(STR_METHOD_NAME);

        // 1) this.get運用区分( )="通常運用（抽選有オークション）"の場合trueを返却する。
        if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(this.getInvestDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //2) 上記以外の場合、falseを返却する。
        return false;
    }
    
    /**
     * (_createNewサービス抽選情報)<BR>
     * 新規にサービス抽選情報オブジェクトを生成して返却する。<BR>
     * <BR>
     * 1) サービス抽選情報Paramsオブジェクトを生成する。<BR>
     * <BR>
     * 2) サービス抽選情報Params.set証券会社コード()をコールする。<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * 3) サービス抽選情報Params.setサービス区分()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 4) サービス抽選情報Params.set通番()をコールする。<BR>
     * [引数]<BR>
     * 　@通番=サービス情報管理.createNew通番( )<BR>
     * 　@　@[createNew通番に渡す引数]<BR>
     * 　@　@　@証券会社コード=引数.証券会社コード<BR>
     * 　@　@　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 5) サービス抽選情報のコンストラクタをコールし、生成した<BR>
     * 　@サービス抽選情報オブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@サービス抽選情報Row=生成したサービス抽選情報Params<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo
     * @@throws WEB3BaseException
     * @@roseuid 413E62C40197
     */
    public static WEB3SrvRegiServiceLotInfo createNewSrvLotInfo(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvLotInfo(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス抽選情報Paramsオブジェクトを生成する。
        SrvRegiLotInfoParams l_srvLotInfoParams = new SrvRegiLotInfoParams();
        
        //2) サービス抽選情報Params.set証券会社コード()をコールする。
        l_srvLotInfoParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) サービス抽選情報Params.setサービス区分()をコールする。
        l_srvLotInfoParams.setSrvDiv(l_strSrvDiv);
        
        //4) サービス抽選情報Params.set通番()をコールする。
        l_srvLotInfoParams.setConsecutiveNumbers(
            WEB3SrvRegiServiceLotInfo.createNewConsecutiveNumbers(
                l_strInstitutionCode, 
                l_strSrvDiv));
        
        //5) サービス抽選情報のコンストラクタをコールし、生成したサービス抽選情報オブジェクトを返却する。
        WEB3SrvRegiServiceLotInfo l_srvLotInfo = 
            new  WEB3SrvRegiServiceLotInfo(l_srvLotInfoParams);
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvLotInfo;
    }
    
    /**
     * (saveサービス抽選情報)<BR>
     * this.サービス抽選情報行オブジェクトの<BR>
     * 情報をデータベースに反映させる。(Update)<BR>
     * <BR>
     * 1) this.サービス抽選情報行オブジェクトに以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の<BR>
     * 戻り値<BR>
     * <BR>
     * 2) this.サービス抽選情報行オブジェクトの内容で、<BR>
     * 　@サービス抽選情報テーブルを更新（Update）する。<BR>
     * @@roseuid 413E62C401A7
     */
    public void saveSrvLotInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveSrvLotInfo() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.サービス抽選情報行オブジェクトに以下の値をセットする。
            this.srvLotInfoParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvLotInfoParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        
            //2) this.サービス抽選情報行オブジェクトの内容で、
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doUpdateQuery(this.srvLotInfoParams);//DataNetworkException, DataQueryException
              
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
     * (saveNewサービス抽選情報)<BR>
     * this.サービス抽選情報行オブジェクトの情報をデータベースに反映させる。(Insert)<BR>
     * <BR>
     * 1) this.サービス抽選情報行オブジェクトに以下の値をセットする。<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 2) this.サービス抽選情報行オブジェクトの内容で、<BR>
     * 　@サービス抽選情報テーブルを更新（Insert）する。<BR>
     * @@roseuid 413E62C401C6
     */
    public void saveNewSrvLotInfo() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewSrvLotInfo() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.サービス抽選情報行オブジェクトに以下の値をセットする。
            this.srvLotInfoParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvLotInfoParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            this.srvLotInfoParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //2) this.サービスマスター行オブジェクトの内容で、
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doInsertQuery(this.srvLotInfoParams);//DataNetworkException, DataQueryException
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
     * (removeサービス抽選情報)<BR>
     * サービス抽選情報の情報をデータベースから削除する。<BR>
     * <BR>
     * 1) 以下を条件に、当該レコードをサービス抽選情報テーブルより削除する。<BR>
     * [削除条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分 and<BR>
     * 　@通番=引数.通番<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_lngConsecutiveNumbers - (通番)<BR>
     * @@roseuid 413E62C401E5
     */
    public void removeSrvLotInfo(String l_strInstitutionCode, String l_strSrvDiv, long l_lngConsecutiveNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvLotInfo(String, String, long) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) 以下の条件で「サービス抽選情報」テーブルを検索する。
            String l_strWhere = " institution_code = ? and srv_div = ? and consecutive_numbers = ? ";
                
            Object[] l_obj = {l_strInstitutionCode, 
                    l_strSrvDiv, 
                    new Long(l_lngConsecutiveNumbers)};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
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
