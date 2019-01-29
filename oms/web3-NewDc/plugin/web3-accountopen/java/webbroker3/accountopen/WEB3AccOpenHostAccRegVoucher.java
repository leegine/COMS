head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenHostAccRegVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客登録伝票（Ｇ１１）キュー(WEB3AccOpenHostAccRegVoucher.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 徐宏偉 (中訊) 新規作成
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.accountopen.data.HostAccRegVoucherDao;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAccRegVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (顧客登録伝票（Ｇ１１）キュー)<BR>
 * 顧客登録伝票（Ｇ１１）キュー<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenHostAccRegVoucher 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenHostAccRegVoucher.class);
    
    /**
     * (顧客登録伝票（Ｇ11）キュー行)<BR>
     * 顧客登録伝票（Ｇ11）キュー行<BR>
     * <BR>
     * 顧客登録伝票（Ｇ11）キューParamsクラスはDDLより自動生成する。<BR>
     */
    private HostAccRegVoucherParams hostAccRegVoucherParams;
    
    /**
     * (顧客登録伝票（Ｇ１１）キュー )<BR>
     * 顧客登録伝票（Ｇ１１）キューオブジェクトを取得する。<BR> 
     * <BR>
     * 以下の条件で顧客登録伝票（Ｇ１１）キューテーブルを検索する。<BR> 
     * <BR>
     * [条件] <BR>
     * 顧客登録伝票（Ｇ１１）キュー.識別コード = 引数.識別コード <BR>
     * 顧客登録伝票（Ｇ１１）キュー.証券会社コード = 引数.証券会社コード<BR> 
     * 顧客登録伝票（Ｇ１１）キュー.部店コード = 引数.部店コード <BR>
     * 顧客登録伝票（Ｇ１１）キュー.顧客コード = 引数.顧客コード <BR>
     * 顧客登録伝票（Ｇ１１）キュー.データコード = 引数.データコード <BR>
     * <BR>
     * 検索結果の顧客登録伝票（Ｇ１１）キュー行オブジェクトを <BR>
     * this.顧客登録伝票（Ｇ１１）キュー行にセットする。<BR>
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strRequestCode - (データコード)<BR>
     * データコード<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenHostAccRegVoucher(
        String l_strOrderRequestNumber, 
        String l_strInstitutionCode, 
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strRequestCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenHostAccRegVoucher(" +
            "String, String, String, String ,String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // [条件]
            //顧客登録伝票（Ｇ１１）キュー.識別コード = 引数.識別コード              
            // 顧客登録伝票（Ｇ１１）キュー.証券会社コード = 引数.証券会社コード            
            // 顧客登録伝票（Ｇ１１）キュー.部店コード = 引数.部店コード 
            // 顧客登録伝票（Ｇ１１）キュー.顧客コード = 引数.顧客コード 
            // 顧客登録伝票（Ｇ１１）キュー.データコード = 引数.データコード   
            
            HostAccRegVoucherRow l_row = 
                (HostAccRegVoucherRow)
                    HostAccRegVoucherDao.findRowByOrderRequestNumberRequestCodeInstitutionCodeBranchCodeAccountCode(
                        l_strOrderRequestNumber, 
                        l_strRequestCode, 
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strAccountCode);
            if (l_row == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            //検索結果の顧客登録伝票（Ｇ１１）キュー行オブジェクトを 
            //this.顧客登録伝票（Ｇ１１）キュー行にセットする。
            this.hostAccRegVoucherParams = new HostAccRegVoucherParams(l_row);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);   
        } 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get信託経由区分)<BR>
     * 信託経由区分を取得する。<BR> 
     * <BR>
     * this.顧客登録伝票（Ｇ１１）キュー行.信託経由区分を返却する。<BR>
     * @@return String
     */
    public String getTrustViaDiv()
    {
        final String STR_METHOD_NAME = "getTrustViaDiv()";
        log.entering(STR_METHOD_NAME);
        
        //信託経由区分を取得する。
        //this.顧客登録伝票（Ｇ１１）キュー行.信託経由区分を返却する。
        String l_strTrustViaDiv = this.hostAccRegVoucherParams.getTrustViaDiv();
        
        log.exiting(STR_METHOD_NAME);
        return l_strTrustViaDiv;
    }
    
    /**
     * (get先物OP口座開設区分（東証）)<BR>
     * 先物OP口座開設区分（東証）を取得する。<BR> 
     * <BR>
     * this.顧客登録伝票（Ｇ１１）キュー行.先物OP口座開設区分（東証）を返却する。<BR>
     * @@return String
     */
    public String getIfoAccOpenDivTokyo()
    {
        final String STR_METHOD_NAME = "getIfoAccOpenDivTokyo()";
        log.entering(STR_METHOD_NAME);
        
        //先物OP口座開設区分（東証）を取得する。
        //this.顧客登録伝票（Ｇ１１）キュー行.先物OP口座開設区分（東証）を返却する。
        String l_strIfoAccOpenDivTokyo = 
            this.hostAccRegVoucherParams.getIfoAccOpenDivTokyo();
        
        log.exiting(STR_METHOD_NAME);
        return l_strIfoAccOpenDivTokyo;
    }
    
    /**
     * (get先物OP口座開設区分（大証）)<BR>
     * 先物OP口座開設区分（大証）を取得する。<BR> 
     * <BR>
     * this.顧客登録伝票（Ｇ１１）キュー行.先物OP口座開設区分（大証）を返却する。<BR>
     * @@return String
     */
    public String getIfoAccOpenDivOsaka()
    {
        final String STR_METHOD_NAME = "getIfoAccOpenDivOsaka()";
        log.entering(STR_METHOD_NAME);
        
        //先物OP口座開設区分（大証）を取得する。
        //this.顧客登録伝票（Ｇ１１）キュー行.先物OP口座開設区分（大証）を返却する。
        String l_strIfoAccOpenDivOsaka = 
            this.hostAccRegVoucherParams.getIfoAccOpenDivOsaka();
        
        log.exiting(STR_METHOD_NAME);
        return l_strIfoAccOpenDivOsaka;
    }
    
    /**
     * (get先物OP口座開設区分（名証）)<BR>
     * 先物OP口座開設区分（名証）を取得する。<BR> 
     * <BR>
     * this.顧客登録伝票（Ｇ１１）キュー行.先物OP口座開設区分（名証）を返却する。<BR>
     * @@return String
     */
    public String getIfoAccOpenDivNagoya()
    {
        final String STR_METHOD_NAME = "getIfoAccOpenDivNagoya()";
        log.entering(STR_METHOD_NAME);
        
        //先物OP口座開設区分（名証）を取得する。
        //this.顧客登録伝票（Ｇ１１）キュー行.先物OP口座開設区分（名証）を返却する。
        String l_strIfoAccOpenDivNagoya = 
            this.hostAccRegVoucherParams.getIfoAccOpenDivNagoya();
        
        log.exiting(STR_METHOD_NAME);
        return l_strIfoAccOpenDivNagoya;
    }
    
    /**
     * (getデータコード)<BR>
     * データコード<BR>
     *<BR>
     * @@return String
     */
    public String getRequestCode()
    {
        final String STR_METHOD_NAME = "getRequestCode()";
        log.entering(STR_METHOD_NAME);
        
        //データコードを取得する。
        String l_strRequestCode = this.hostAccRegVoucherParams.getRequestCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestCode;
    }
    
    /**
     * (get口座開設１_保護預り)<BR>
     * 口座開設１(保護預り)<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv1()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv1()";
        log.entering(STR_METHOD_NAME);
        
        //口座開設１(保護預り)を取得する。
        String l_strAccountOpenDiv1 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv1();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv1;
    }
    
    /**
     * (get口座開設２_積立投資)<BR>
     * 口座開設２（積立投資）<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv2()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv2()";
        log.entering(STR_METHOD_NAME);
        
        //口座開設２（積立投資）を取得する。
        String l_strAccountOpenDiv2 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv2();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv2;
    }
    
    /**
     * (get口座開設３_信用取引)<BR>
     * 口座開設３（信用取引）<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv3()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv3()";
        log.entering(STR_METHOD_NAME);
        
        //口座開設３（信用取引）を取得する。
        String l_strAccountOpenDiv3 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv3();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv3;
    }
    
    /**
     * (get口座開設５_外国証券)<BR>
     * 口座開設５（外国証券）<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv5()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv5()";
        log.entering(STR_METHOD_NAME);
        
        //口座開設５（外国証券）を取得する。
        String l_strAccountOpenDiv5 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv5();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv5;
    }
    
    /**
     * (get口座開設１１_株式オプション)<BR>
     * 口座開設１１（株式オプション）<BR> 
     * <BR>
     * @@return String
     */
    public String getAccountOpenDiv11()
    {
        final String STR_METHOD_NAME = "getAccountOpenDiv11()";
        log.entering(STR_METHOD_NAME);
        
        //口座開設１１（株式オプション）を取得する。
        String l_strAccountOpenDiv11 = 
            this.hostAccRegVoucherParams.getAccountOpenDiv11();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountOpenDiv11;
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.hostAccRegVoucherParams;   
    }
}
@
