head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApply.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント申込(WEB3PointApply.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 張学剛 (中訊) 新規作成
*/
package webbroker3.point;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.point.data.PointApplyParams;
import webbroker3.util.WEB3LogUtility;


/**
 * (ポイント申込)<BR>
 * ポイント申込クラス<BR>
 * 
 * @@author 張学剛
 * @@version 1.0 
 */
public class WEB3PointApply implements BusinessObject 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointApply.class);
    
    /**
     * (申込行)<BR>
     * ポイント申込行オブジェクト<BR>
     */
    private PointApplyParams pointApplyParams;
    
    /**
     * (ポイント申込)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数を、this.申込行にセットする。<BR>
     * @@param l_pointApplyParams - (申込行)<BR>
     * ポイント申込行オブジェクト<BR>
     * 
     * @@roseuid 41A44953039A
     */
    public WEB3PointApply(PointApplyParams l_pointApplyParams) 
    {
        this.pointApplyParams = l_pointApplyParams;
    }
    
    /**
     * (ポイント申込)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）空のポイント申込Paramsオブジェクトを生成し、this.申込行にセットする。<BR>
     * <BR>
     * ２）引数を、this.申込行の各項目にセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@param l_intUsedPoint - (使用ポイント)<BR>
     * 使用ポイント<BR>
     * 
     * @@roseuid 41A449530392
     */
    public WEB3PointApply(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strPremiumNo, int l_intUsedPoint) 
    {
        final String STR_METHOD_NAME = " WEB3PointApply(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        PointApplyParams l_params = new PointApplyParams();
        
        //証券会社コード
        l_params.setInstitutionCode(l_strInstitutionCode);
        
        //部店コード
        l_params.setBranchCode(l_strBranchCode);
        
        //口座コード
        l_params.setAccountCode(l_strAccountCode);
        
        //景品番号
        l_params.setPremiumNo(l_strPremiumNo);
        
        //使用ポイント
        l_params.setUsedPoint(l_intUsedPoint);
        
        this.pointApplyParams = l_params;
        
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (get申込ID)<BR>
     * 申込IDを取得する。<BR>
     * <BR>
     * this.申込行.申込IDを返却する。<BR>
     * @@return long
     * @@roseuid 419C97340372
     */
    public long getApplyId() 
    {
        return this.pointApplyParams.getApplyId();
    }
    
    /**
     * (get部店コード)<BR>
     * 部店コードを取得する。<BR>
     * <BR>
     * this.申込行.部店コードを返却する。<BR>
     * @@return String
     * @@roseuid 419C8F5D01FB
     */
    public String getBranchCode() 
    {
        return this.pointApplyParams.getBranchCode();
    }
    
    /**
     * (get口座コード)<BR>
     * 口座コードを取得する。<BR>
     * <BR>
     * this.申込行.口座コードを返却する。<BR>
     * @@return String
     * @@roseuid 419C9BC401BC
     */
    public String getAccountCode() 
    {
        return this.pointApplyParams.getAccountCode();
    }
    
    /**
     * (get景品番号)<BR>
     * 景品番号を取得する。<BR>
     * <BR>
     * this.申込行.景品番号を返却する。<BR>
     * @@return String
     * @@roseuid 419C95320324
     */
    public String getPremiumNo() 
    {
        return this.pointApplyParams.getPremiumNo();
    }
    
    /**
     * (get申込日時)<BR>
     * 申込日時を取得する。<BR>
     * <BR>
     * this.申込行.申込日時を返却する。<BR>
     * @@return Date
     * @@roseuid 419C9756020A
     */
    public Date getApplyTimestamp() 
    {
        return this.pointApplyParams.getApplyTimestamp();
    }
    
    /**
     * (get申込受付区分)<BR>
     * 申込受付区分を取得する。<BR>
     * <BR>
     * this.申込行.申込受付区分を返却する。<BR>
     * @@return String
     * @@roseuid 419C9779013F
     */
    public String getApplyAcceptDiv() 
    {
        return this.pointApplyParams.getApplyAcceptDiv();
    }
    
    /**
     * this.申込行を返却する。<BR>
     * @@return Object
     * @@roseuid 419C8EAC02A7
     */
    public Object getDataSourceObject() 
    {
        return this.pointApplyParams;
    }
    
    /**
     * this.申込行をコピーして、同じ内容の別インスタンスを作成する（clone）。<BR>
     * 作成したコピーを自身のthis.申込行にセットする。<BR>
     * @@roseuid 419C90D901CC
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        PointApplyParams l_params = new PointApplyParams(this.pointApplyParams);
        this.pointApplyParams = l_params;
        
        log.exiting(STR_METHOD_NAME); 
    }
}
@
