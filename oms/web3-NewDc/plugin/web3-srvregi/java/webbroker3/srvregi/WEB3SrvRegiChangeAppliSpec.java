head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiChangeAppliSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用変更申込内容(WEB3SrvRegiChangeAppliSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用変更申込内容)<BR>
 * サービス利用申込内容クラス <BR>
 * （管理者顧客変更用データクラス）<BR>
 * 管理者顧客データアップロード／管理者顧客登録にて使用<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiChangeAppliSpec 
{

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiChangeAppliSpec.class);

    /**
     * (申込登録ID)<BR>
     */
    private long registId;
    
    /**
     * (証券会社コード)<BR>
     */
    private String institutionCode;
    
    /**
     * (サービス区分)<BR>
     */
    private String srvDiv;
    
    /**
     * (部店コード)<BR>
     */
    private String branchCode;
    
    /**
     * (顧客コード)<BR>
     */
    private String accountCode;
    
    /**
     * (適用開始日)<BR>
     */
    private Timestamp appliStartDate;
    
    /**
     * (適用終了日)<BR>
     */
    private Timestamp appliEndDate;
    
    /**
     * (申込抽選区分)<BR>
     * 0:試用　@1:申込　@2:当選／本申込　@3:落選　@4:取消　@5:自動当選　@6:全て<BR>
     */
    private String appliLotDiv;
    
    /**
     * (申込日)<BR>
     */
    private Timestamp appliDate;
    
    /**
     * (登録区分)<BR>
     * 0:有料　@1:無料<BR>
     */
    private String paymentDiv;
    
    /**
     * (利用料金)<BR>
     */
    private Double useAmt;
    
    /**
     * @@roseuid 416F4FEC035B
     */
    public WEB3SrvRegiChangeAppliSpec() 
    {
     
    }
    
    /**
     * (createサービス利用変更申込内容)<BR>
     * （staticメソッド） <BR>
     * サービス利用変更申込内容オブジェクトを生成し返却する。 <BR>
     * <BR>
     * 1) 当クラスのインスタンスを生成する。<BR>
     * <BR>
     * 2) this.set証券会社コード( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.証券会社コード<BR>
     * 3) this.setサービス区分( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.サービス区分<BR>
     * 4) this.set部店コード( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.部店コード<BR>
     * 5) this.set顧客コード( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.顧客コード<BR>
     * 6)this.set申込登録ID( )をコールする。<BR>
     *     [引数]<BR>
     *     引数.申込登録ID <BR>
     * 7) this.set適用開始日( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.適用開始日<BR>
     * 8) this.set適用終了日( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.適用終了日<BR>
     * 9) this.set申込抽選区分( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.申込抽選区分<BR>
     * 10) this.set申込日( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.申込日<BR>
     * 11) this.set登録区分( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.登録区分<BR>
     * 12) this.set利用料金( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.利用料金<BR>
     * <BR>
     * 13) 作成したサービス利用変更申込内容オブジェクトを返却する。<BR>
     * @@param l_lngRegistId - (申込登録ID)<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@param l_strAppliLotDiv - 1:当選／本申込　@2:落選<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 0:有料　@1:無料　@2:全て<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec
     * @@roseuid 4110A4880091
     */
    public static WEB3SrvRegiChangeAppliSpec createSrvRegiChangeAppliSpec(
        long l_lngRegistId, 
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        String l_strAppliLotDiv, 
        Timestamp l_tsAppliDate, 
        String l_strPaymentDiv, 
        Double l_dblUseAmt) 
    {
        final String STR_METHOD_NAME = 
            "createSrvRegiChangeAppliSpec(" +
            "long , String , String , String , String , Timestamp , Timestamp , " +
            "String , Timestamp , String l_strPaymentDiv, Double" +
            ")";
        log.entering(STR_METHOD_NAME);

        //1当クラスのインスタンスを生成する
        WEB3SrvRegiChangeAppliSpec l_spec = new WEB3SrvRegiChangeAppliSpec();
        
        //2 証券会社コード
        l_spec.setInstitutionCode(l_strInstitutionCode);
        
        //3 サービス区分
        l_spec.setSrvDiv(l_strSrvDiv);
        
        //4 部店コード
        l_spec.setBranchCode(l_strBranchCode);
        
        //5 顧客コード
        l_spec.setAccountCode(l_strAccountCode);
        
        //6 申込登録ID
        l_spec.setRegistId(String.valueOf(l_lngRegistId));
        
        //7 適用開始日
        l_spec.setAppliStartDate(l_tsAppliStartDate);
        
        //8 適用終了日
        l_spec.setAppliEndDate(l_tsAppliEndDate);
        
        //9 申込抽選区分
        l_spec.setAppliLotDiv(l_strAppliLotDiv);
        
        //10 申込日
        l_spec.setAppliDate(l_tsAppliDate);
        
        //11 登録区分
        l_spec.setPaymentDiv(l_strPaymentDiv);
        
        //12 利用料金
        l_spec.setUseAmt(l_dblUseAmt);
        
        log.exiting(STR_METHOD_NAME);
        return l_spec;
    }
    
    /**
     * (get申込登録ID)<BR>
     * this.申込登録IDを返却する。<BR>
     * @@return String
     * @@roseuid 41203EC50358
     */
    public String getRegistId() 
    {
        return String.valueOf(this.registId);
    }
    
    /**
     * (set申込登録ID)<BR>
     * 申込登録IDの設定を行う。<BR>
     * @@param l_strRegistId - (申込登録ID)<BR>
     * @@roseuid 41203EC50368
     */
    public void setRegistId(String l_strRegistId) 
    {
        this.registId = Long.parseLong(l_strRegistId);
    }
    
    /**
     * (get証券会社コード)<BR>
     * this.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 4110A3480091
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードの設定を行う。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@roseuid 4110A34800B0
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (getサービス区分)<BR>
     * this.サービス区分を返却する。<BR>
     * @@return String
     * @@roseuid 4110A34800C0
     */
    public String getSrvDiv() 
    {
        return this.srvDiv;
    }
    
    /**
     * (setサービス区分)<BR>
     * サービス区分の設定を行う。<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@roseuid 4110A34800DF
     */
    public void setSrvDiv(String l_strSrvDiv) 
    {
        this.srvDiv = l_strSrvDiv;
    }
    
    /**
     * (get部店コード)<BR>
     * this.部店コードを返却する。<BR>
     * @@return String
     * @@roseuid 4110A34800EF
     */
    public String getBranchCode() 
    {
        return this.branchCode;
    }
    
    /**
     * (set部店コード)<BR>
     * 部店コードの設定を行う。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@roseuid 4110A348010E
     */
    public void setBranchCode(String l_strBranchCode) 
    {
        this.branchCode = l_strBranchCode;
    }
    
    /**
     * (get顧客コード)<BR>
     * this.顧客コードを返却する。<BR>
     * @@return String
     * @@roseuid 4110A348012D
     */
    public String getAccountCode() 
    {
        return this.accountCode;
    }
    
    /**
     * (set顧客コード)<BR>
     * 顧客コードの設定を行う。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@roseuid 4110A348014C
     */
    public void setAccountCode(String l_strAccountCode) 
    {
        this.accountCode = l_strAccountCode;
    }
    
    /**
     * (get適用開始日)<BR>
     * this.適用開始日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 4110A348015C
     */
    public Timestamp getAppliStartDate() 
    {
        return this.appliStartDate;
    }
    
    /**
     * (set適用開始日)<BR>
     * 適用開始日の設定を行う。<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@roseuid 4110A348017B
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate) 
    {
        this.appliStartDate = l_tsAppliStartDate;
    }
    
    /**
     * (get適用終了日)<BR>
     * this.適用終了日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 4110A348019A
     */
    public Timestamp getAppliEndDate() 
    {
        return this.appliEndDate;
    }
    
    /**
     * (set適用終了日)<BR>
     * 適用終了日の設定を行う。<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@roseuid 4110A34801AA
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate) 
    {
        this.appliEndDate = l_tsAppliEndDate;
    }
    
    /**
     * (get申込日)<BR>
     * this.申込日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 4110A34801C9
     */
    public Timestamp getAppliDate() 
    {
        return this.appliDate;
    }
    
    /**
     * (set申込日)<BR>
     * 申込日の設定を行う。<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@roseuid 4110A34801D9
     */
    public void setAppliDate(Timestamp l_tsAppliDate) 
    {
        this.appliDate = l_tsAppliDate;
    }
    
    /**
     * (get申込抽選区分)<BR>
     * this.申込抽選区分を返却する。<BR>
     * @@return String
     * @@roseuid 4110A34801F8
     */
    public String getAppliLotDiv() 
    {
        return this.appliLotDiv;
    }
    
    /**
     * (set申込抽選区分)<BR>
     * 申込抽選区分の設定を行う。<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * @@roseuid 4110A3480237
     */
    public void setAppliLotDiv(String l_strAppliLotDiv) 
    {
        this.appliLotDiv = l_strAppliLotDiv;
    }
    
    /**
     * (get登録区分)<BR>
     * this.登録区分を返却する。<BR>
     * @@return String
     * @@roseuid 411375F20121
     */
    public String getPaymentDiv() 
    {
        return this.paymentDiv;
    }
    
    /**
     * (set登録区分)<BR>
     * 登録区分の設定を行う。<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 0:有料　@1:無料<BR>
     * @@roseuid 411375F2016F
     */
    public void setPaymentDiv(String l_strPaymentDiv) 
    {
        this.paymentDiv = l_strPaymentDiv;
    }
    
    /**
     * (get利用料金)<BR>
     * this.利用料金を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Double
     * @@roseuid 411375F2025A
     */
    public Double getUseAmt() 
    {
        return this.useAmt;
    }
    
    /**
     * (set利用料金)<BR>
     * 利用料金の設定を行う。<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@roseuid 411375F202E6
     */
    public void setUseAmt(Double l_dblUseAmt) 
    {
        this.useAmt = l_dblUseAmt;
    }
}
@
