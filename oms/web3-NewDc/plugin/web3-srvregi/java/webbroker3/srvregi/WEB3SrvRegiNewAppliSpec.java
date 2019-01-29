head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiNewAppliSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用新規申込内容(WEB3SrvRegiNewAppliSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用新規申込内容)<BR>
 * サービス利用申込内容クラス <BR>
 * （管理者顧客登録用データクラス）<BR>
 * 管理者顧客データアップロード／管理者顧客登録にて使用<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiNewAppliSpec 
{
    
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiNewAppliSpec.class);
    
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
     * (出金日)<BR>
     */
    private Timestamp paymentDate;
    
    /**
     * （申込抽選区分）<BR>
     */
    private String appliLotDiv;
    
    /**
     * @@roseuid 416F4FED0280
     */
    public WEB3SrvRegiNewAppliSpec() 
    {
     
    }
    
    /**
     * (createサービス利用新規申込内容)<BR>
     * （staticメソッド） <BR>
     * <BR>
     * 1) サービス利用新規申込内容オブジェクトを生成する。<BR>
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
     * 6) this.set適用開始日( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.適用開始日<BR>
     * 7) this.set適用終了日( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.適用終了日<BR>
     * 8) this.set申込日( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.申込日<BR>
     * 9) this.set登録区分( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.登録区分<BR>
     * 10) this.set利用料金( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.利用料金<BR>
     * 11) this.set出金日( )をコールする。<BR>
     * 　@[引数]<BR>
     * 　@　@引数.出金日<BR>
     * <BR>
     * 12) 作成したサービス利用新規申込内容オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 0:有料　@1:無料　@2:全て<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_strAppliLotDiv - （申込抽選区分）<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiNewAppliSpec
     * @@roseuid 4110A3F7039E
     */
    public static WEB3SrvRegiNewAppliSpec createSrvRegiNewAppliSpec(
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        Timestamp l_tsAppliDate, 
        String l_strPaymentDiv, 
        Double l_dblUseAmt, 
        Timestamp l_tsPaymentDate,
		String l_strAppliLotDiv) 
    {
        final String STR_METHOD_NAME = 
            "createSrvRegiNewAppliSpec(" 
            + "String , String , String , String , Timestamp , Timestamp , Timestamp , String , Double , Timestamp, String" 
            + ")";
        log.entering(STR_METHOD_NAME);

        //サービス利用新規申込内容オブジェクトを生成する
        WEB3SrvRegiNewAppliSpec l_spec = new WEB3SrvRegiNewAppliSpec();
        
        //2証券会社コード
        l_spec.setInstitutionCode(l_strInstitutionCode);
        
        //3サービス区分
        l_spec.setSrvDiv(l_strSrvDiv);
        
        //4部店コード
        l_spec.setBranchCode(l_strBranchCode);
        
        //5顧客コード
        l_spec.setAccountCode(l_strAccountCode);
        
        //6適用開始日
        l_spec.setAppliStartDate(l_tsAppliStartDate);
        
        //7適用終了日
        l_spec.setAppliEndDate(l_tsAppliEndDate);
        
        //8申込日
        l_spec.setAppliDate(l_tsAppliDate);
        
        //9登録区分
        l_spec.setPaymentDiv(l_strPaymentDiv);
        
        //10利用料金
        l_spec.setUseAmt(l_dblUseAmt);
        
        //11出金日
        l_spec.setPaymentDate(l_tsPaymentDate);
        
        //12申込抽選区分
        l_spec.setAppliLotDiv(l_strAppliLotDiv);

        log.exiting(STR_METHOD_NAME);
        return l_spec;
    }
    
    /**
     * (get証券会社コード)<BR>
     * this.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 41109DB40033
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードの設定を行う。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@roseuid 41109DBA00C0
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (getサービス区分)<BR>
     * this.サービス区分を返却する。<BR>
     * @@return String
     * @@roseuid 41109DC20311
     */
    public String getSrvDiv() 
    {
        return this.srvDiv;
    }
    
    /**
     * (setサービス区分)<BR>
     * サービス区分の設定を行う。<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@roseuid 41109DC20312
     */
    public void setSrvDiv(String l_strSrvDiv) 
    {
        this.srvDiv = l_strSrvDiv;
    }
    
    /**
     * (get部店コード)<BR>
     * this.部店コードを返却する。<BR>
     * @@return String
     * @@roseuid 41109DC303AE
     */
    public String getBranchCode() 
    {
        return this.branchCode;
    }
    
    /**
     * (set部店コード)<BR>
     * 部店コードの設定を行う。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@roseuid 41109DC303AF
     */
    public void setBranchCode(String l_strBranchCode) 
    {
        this.branchCode = l_strBranchCode;
    }
    
    /**
     * (get顧客コード)<BR>
     * this.顧客コードを返却する。<BR>
     * @@return String
     * @@roseuid 41109DC50062
     */
    public String getAccountCode() 
    {
        return this.accountCode;
    }
    
    /**
     * (set顧客コード)<BR>
     * 顧客コードの設定を行う。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@roseuid 41109DC50063
     */
    public void setAccountCode(String l_strAccountCode) 
    {
        this.accountCode = l_strAccountCode;
    }
    
    /**
     * (get適用開始日)<BR>
     * this.適用開始日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 41109DC600FE
     */
    public Timestamp getAppliStartDate() 
    {
        return this.appliStartDate;
    }
    
    /**
     * (set適用開始日)<BR>
     * 適用開始日の設定を行う。<BR>
     * @@param l_tsAppliStartDate - (適用開始日)<BR>
     * @@roseuid 41109DC6012D
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate) 
    {
        this.appliStartDate = l_tsAppliStartDate;
    }
    
    /**
     * (get適用終了日)<BR>
     * this.適用終了日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 41109EE20217
     */
    public Timestamp getAppliEndDate() 
    {
        return this.appliEndDate;
    }
    
    /**
     * (set適用終了日)<BR>
     * 適用終了日の設定を行う。<BR>
     * @@param l_tsAppliEndDate - (適用終了日)<BR>
     * @@roseuid 41109EE20227
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate) 
    {
        this.appliEndDate = l_tsAppliEndDate;
    }
    
    /**
     * (get申込日)<BR>
     * this.申込日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 41109F17037F
     */
    public Timestamp getAppliDate() 
    {
        return this.appliDate;
    }
    
    /**
     * (set申込日)<BR>
     * 申込日の設定を行う。<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@roseuid 41109F17038E
     */
    public void setAppliDate(Timestamp l_tsAppliDate) 
    {
        this.appliDate = l_tsAppliDate;
    }
    
    /**
     * (get登録区分)<BR>
     * this.登録区分を返却する。<BR>
     * @@return String
     * @@roseuid 41109F3D01F8
     */
    public String getPaymentDiv() 
    {
        return this.paymentDiv;
    }
    
    /**
     * (set登録区分)<BR>
     * 登録区分の設定を行う。<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * @@roseuid 41109F3D0208
     */
    public void setPaymentDiv(String l_strPaymentDiv) 
    {
        this.paymentDiv = l_strPaymentDiv;
    }
    
    /**
     * (get利用料金)<BR>
     * this.利用料金を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double
     * @@roseuid 41109F3E02B4
     */
    public Double getUseAmt() 
    {
        return this.useAmt;
    }
    
    /**
     * (set利用料金)<BR>
     * 利用料金の設定を行う。<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * @@roseuid 41109F3E02B5
     */
    public void setUseAmt(Double l_dblUseAmt) 
    {
       this.useAmt = l_dblUseAmt;
    }
    
    /**
     * (get出金日)<BR>
     * this.出金日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 411745880236
     */
    public Timestamp getPaymentDate() 
    {
        return this.paymentDate;
    }
    
    /**
     * (set出金日)<BR>
     * 出金日の設定を行う。<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@roseuid 411745880255
     */
    public void setPaymentDate(Timestamp l_tsPaymentDate) 
    {
       this.paymentDate = l_tsPaymentDate;
    }

    /**
     * (get申込抽選区分)<BR>
     * this.申込抽選区分を返却する。<BR>
     * @@return
     */
    public String getAppliLotDiv()
    {
        return this.appliLotDiv;
    }

    /**
     * (set申込抽選区分)<BR>
     * 申込抽選区分の設定を行う。<BR>
     * @@param l_strAppliLotDiv - （申込抽選区分）<BR>
     */
    public void setAppliLotDiv(String l_strAppliLotDiv)
    {
        appliLotDiv = l_strAppliLotDiv;
    }

}
@
