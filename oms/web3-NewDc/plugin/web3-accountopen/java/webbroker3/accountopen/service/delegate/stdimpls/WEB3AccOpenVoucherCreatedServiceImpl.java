head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票作成サービスImpl(WEB3AccOpenVoucherCreatedServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 張学剛 (中訊) 新規作成
                 : 2006/06/08 徐大方(中訊) 仕様変更・モデル072
                 : 2006/08/15 柴雙紅(中訊) 仕様変更・モデル087
Revesion History : 2009/08/13 武波(中訊) 仕様変更・モデルNo.171
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import webbroker3.accountopen.WEB3AccOpenAccountRegBrokerageFirmVoucher;
import webbroker3.accountopen.WEB3AccOpenAccountRegVoucher;
import webbroker3.accountopen.WEB3AccOpenAgencyInfoRegVoucher;
import webbroker3.accountopen.WEB3AccOpenAgreeTransVoucher;
import webbroker3.accountopen.WEB3AccOpenBankTransVoucher;
import webbroker3.accountopen.WEB3AccOpenChargedInfoVoucher;
import webbroker3.accountopen.WEB3AccOpenContMrgVoucher;
import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenForeignRegVoucher;
import webbroker3.accountopen.WEB3AccOpenGPRegVoucher;
import webbroker3.accountopen.WEB3AccOpenIdConfirmVoucher;
import webbroker3.accountopen.WEB3AccOpenImpConfirmVoucher;
import webbroker3.accountopen.WEB3AccOpenInsiderRegVoucher;
import webbroker3.accountopen.WEB3AccOpenMrfAccountVoucher;
import webbroker3.accountopen.WEB3AccOpenPasswordVoucher;
import webbroker3.accountopen.WEB3AccOpenPostalTransVoucher;
import webbroker3.accountopen.WEB3AccOpenRealNameRegVoucher;
import webbroker3.accountopen.WEB3AccOpenStockHolderRegVoucher;
import webbroker3.accountopen.WEB3AccOpenTradeConditionVoucher;
import webbroker3.accountopen.WEB3AccOpenVoucher;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設伝票作成サービスImpl)<BR>
 * 口座開設伝票作成サービス実装クラス<BR>
 * @@author 張学剛
 * @@version 1.0 
 */
public class WEB3AccOpenVoucherCreatedServiceImpl implements WEB3AccOpenVoucherCreatedService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenVoucherCreatedServiceImpl.class);

    /**
     * @@roseuid 41B45E71001F
     */
    public WEB3AccOpenVoucherCreatedServiceImpl() 
    {
     
    }
    
    /**
     * (create口座開設伝票)<BR>
     * 指定の口座開設見込客の情報より、口座開設伝票を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（口座開設伝票作成）create口座開設伝票」参照。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@return String[]
     * @@roseuid 4191CD88018B
     */
    public String[] createAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAccOpenVoucher(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);  

        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();

        //1.2 getInstance(口座開設見込客)
        WEB3AccOpenAccountRegVoucher l_accountRegVoucher = 
            WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.3 add(arg0（=顧客登録伝票） : Object)
        l_arrayList.add(l_accountRegVoucher);

        //1.4 getInstance(口座開設見込客)
        WEB3AccOpenContMrgVoucher l_contMrgVoucher = 
            WEB3AccOpenContMrgVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.5 add(arg0（=契約書徴収伝票） : Object)
        l_arrayList.add(l_contMrgVoucher);

        //1.6 getInstance(口座開設見込客)
        WEB3AccOpenBankTransVoucher l_bankTransVoucher = 
            WEB3AccOpenBankTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.7 add(arg0（=振替申込（銀行）伝票） : Object)
        l_arrayList.add(l_bankTransVoucher);

        //1.8 getInstance(口座開設見込客)
        WEB3AccOpenPostalTransVoucher l_postalTransVoucher = 
            WEB3AccOpenPostalTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.9 add(arg0（=振替申込（郵貯）伝票） : Object)
        l_arrayList.add(l_postalTransVoucher);

        //1.10 getInstance(口座開設見込客)
        WEB3AccOpenChargedInfoVoucher l_chargedInfoVoucher = 
            WEB3AccOpenChargedInfoVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.11 add(arg0（=有料情報伝票） : Object)
        l_arrayList.add(l_chargedInfoVoucher);

        //1.12 getInstance(口座開設見込客)
        WEB3AccOpenAgreeTransVoucher l_agreeTransVoucher = 
            WEB3AccOpenAgreeTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.13 add(arg0（=保振同意伝票）
        l_arrayList.add(l_agreeTransVoucher);

        //1.14 getInstance(口座開設見込客)
        WEB3AccOpenMrfAccountVoucher l_mrfAccountVoucher = 
            WEB3AccOpenMrfAccountVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.15 add(arg0（=MRF口座伝票） : Object)
        l_arrayList.add(l_mrfAccountVoucher);

        //1.16 getInstance(口座開設見込客)
        WEB3AccOpenImpConfirmVoucher l_impConfirmVoucher = 
            WEB3AccOpenImpConfirmVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.17 add(arg0（=重要事項確認書） : Object)
        l_arrayList.add(l_impConfirmVoucher);

        //1.18 getInstance(口座開設見込客)
        WEB3AccOpenIdConfirmVoucher l_confirmVoucher = 
            WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);        

        //1.19 add(arg0（=本人確認伝票） : Object)
        l_arrayList.add(l_confirmVoucher);

        //1.20 getInstance(口座開設見込客)
        WEB3AccOpenPasswordVoucher l_passwordVoucher = 
            WEB3AccOpenPasswordVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.21 add(arg0（=暗証番号伝票） : Object)
        l_arrayList.add(l_passwordVoucher);

        //1.22 getInstance(口座開設見込客)
        WEB3AccOpenTradeConditionVoucher l_tradeConditionVoucher = 
            WEB3AccOpenTradeConditionVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.23 add(arg0（=取残・電子交付・特定口座伝票） : Object)
        l_arrayList.add(l_tradeConditionVoucher);
        
        //1.24 getInstance(口座開設見込客)
        WEB3AccOpenInsiderRegVoucher l_insiderRegVoucher = 
            WEB3AccOpenInsiderRegVoucher.getInstance(l_accOpenExpAccountOpen);  
                  
        //1.25 add(arg0（=内部者登録伝票） : Object)
        l_arrayList.add(l_insiderRegVoucher);
        
        //1.26 getInstance(口座開設見込客)
        WEB3AccOpenGPRegVoucher l_gpRegVoucher = 
            WEB3AccOpenGPRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.27 add(arg0（=GP条件登録伝票） : Object)
        l_arrayList.add(l_gpRegVoucher);
        
        //1.28 getInstance(口座開設見込客)
        WEB3AccOpenStockHolderRegVoucher l_stockHolderRegVoucher = 
            WEB3AccOpenStockHolderRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
        //1.29 add(arg0（=上場外株・株主登録伝票） : Object)
        l_arrayList.add(l_stockHolderRegVoucher);
        
        //1.30 getInstance(口座開設見込客)
        WEB3AccOpenRealNameRegVoucher l_realNameRegVoucher = 
            WEB3AccOpenRealNameRegVoucher.getInstance(l_accOpenExpAccountOpen); 
            
        //1.31 add(arg0（=顧客正式名称登録伝票） : Object)
        l_arrayList.add(l_realNameRegVoucher);
        
        //1.32 getInstance(口座開設見込客)
        WEB3AccOpenAccountRegBrokerageFirmVoucher l_accountRegBrokerageFirmVoucher = 
            WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);     
            
        //1.33 add(arg0（=顧客登録（仲介業）伝票） : Object)
        l_arrayList.add(l_accountRegBrokerageFirmVoucher);  
        
        //1.34 getInstance(口座開設見込客)
        WEB3AccOpenForeignRegVoucher l_accOpenforeignSaveRegVoucher = 
            WEB3AccOpenForeignRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.35 add(arg0（=外貨預金口座登録伝票） : Object)
        l_arrayList.add(l_accOpenforeignSaveRegVoucher);       

        //getInstance(口座開設見込客 : 口座開設見込客)
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //add(arg0（=機@構通知情報登録伝票） : Object)
        //arg0（機@構通知情報登録伝票）：　@機@構通知情報登録伝票.getInstance()
        l_arrayList.add(l_accOpenAgencyInfoRegVoucher);

        //1.36 isトリガ発行()
        boolean l_blnIsSubmitMarketTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null);
        log.debug("isトリガ発行:　@" + l_blnIsSubmitMarketTrigger);
        
        //1.37 toArray( )
        WEB3AccOpenVoucher[] l_openVoucher = new WEB3AccOpenVoucher[l_arrayList.size()];
        l_arrayList.toArray(l_openVoucher);

        //1.38 ArrayList()
        ArrayList l_voucherCodeList = new ArrayList();

        //1.39 口座開設伝票[]各要素毎のLOOP処理
        boolean l_blnIsVoucher;

        int l_intLength = l_openVoucher.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("1.27 口座開設伝票[]各要素毎のLOOP処理");
            //1.39.1
            l_blnIsVoucher = l_openVoucher[i].createVoucher();

            //1.39.2 伝票を作成した場合（伝票作成() == true）、処理実施
            if (l_blnIsVoucher)
            {
                log.debug("1.27.2 伝票を作成した場合。伝票コード： " + l_openVoucher[i].getVoucherCode());
                //1.39.2.1 get伝票コード( )
                String l_strVoucherCode = l_openVoucher[i].getVoucherCode();
                
                //1.39.2.2 add(arg0（=伝票コード） : Object)
                l_voucherCodeList.add(l_strVoucherCode);

                //1.39.2.3 オンライン時間帯（isトリガ発行() == true）の場合のみ処理実施
                if (l_blnIsSubmitMarketTrigger)
                {
                    //1.37.2.3.1 伝票送信( )
                    l_openVoucher[i].sendVoucher();
                }
            }  
        }
 
        //1.40 toArray( )
        log.debug("伝票コードListを配列に変換し、返却。配列長： " + l_voucherCodeList.size());
        String[] l_strVoucherCodes = new String[l_voucherCodeList.size()];
        l_voucherCodeList.toArray(l_strVoucherCodes);

        log.exiting(STR_METHOD_NAME);
        return l_strVoucherCodes;
    }
    
    /**
     * (delete口座開設伝票)<BR>
     * 指定の口座開設見込客に関連する、口座開設伝票を削除する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（口座開設伝票作成）delete口座開設伝票」参照。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@return String[]
     * @@roseuid 419C26F200CE
     */
    public String[] deleteAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteAccOpenVoucher(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);

        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();

        //1.2  getInstance(口座開設見込客)
        WEB3AccOpenAccountRegVoucher l_accountRegVoucher = 
            WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.3 add(arg0（=顧客登録伝票） : Object)
        l_arrayList.add(l_accountRegVoucher);

        //1.4 getInstance(口座開設見込客)
        WEB3AccOpenContMrgVoucher l_contMrgVoucher = 
            WEB3AccOpenContMrgVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.5 add(arg0（=契約書徴収伝票） : Object)
        l_arrayList.add(l_contMrgVoucher);

        //1.6 getInstance(口座開設見込客)
        WEB3AccOpenBankTransVoucher l_bankTransVoucher = 
            WEB3AccOpenBankTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.7 add(arg0（=振替申込（銀行）伝票） : Object)
        l_arrayList.add(l_bankTransVoucher);

        //1.8 getInstance(口座開設見込客)
        WEB3AccOpenPostalTransVoucher l_postalTransVoucher = 
            WEB3AccOpenPostalTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.9 add(arg0（=振替申込（郵貯）伝票） : Object)
        l_arrayList.add(l_postalTransVoucher);

        //1.10 getInstance(口座開設見込客)
        WEB3AccOpenChargedInfoVoucher l_chargedInfoVoucher = 
            WEB3AccOpenChargedInfoVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.11 add(arg0（=有料情報伝票） : Object)
        l_arrayList.add(l_chargedInfoVoucher);

        //1.12 getInstance(口座開設見込客)
        WEB3AccOpenAgreeTransVoucher l_agreeTransVoucher = 
            WEB3AccOpenAgreeTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.13 add(arg0（=保振同意伝票）
        l_arrayList.add(l_agreeTransVoucher);

        //1.14 getInstance(口座開設見込客)
        WEB3AccOpenMrfAccountVoucher l_mrfAccountVoucher = 
            WEB3AccOpenMrfAccountVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.15 add(arg0（=MRF口座伝票） : Object)
        l_arrayList.add(l_mrfAccountVoucher);

        //1.16 getInstance(口座開設見込客)
        WEB3AccOpenImpConfirmVoucher l_impConfirmVoucher = 
            WEB3AccOpenImpConfirmVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.17 add(arg0（=重要事項確認書） : Object)
        l_arrayList.add(l_impConfirmVoucher);

        //1.18 getInstance(口座開設見込客)
        WEB3AccOpenIdConfirmVoucher l_confirmVoucher = 
            WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);        

        //1.19 add(arg0（=本人確認伝票） : Object)
        l_arrayList.add(l_confirmVoucher);

        //1.20 getInstance(口座開設見込客)
        WEB3AccOpenPasswordVoucher l_passwordVoucher = 
            WEB3AccOpenPasswordVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.21 add(arg0（=暗証番号伝票） : Object)
        l_arrayList.add(l_passwordVoucher);

        //1.22 getInstance(口座開設見込客)
        WEB3AccOpenTradeConditionVoucher l_tradeConditionVoucher = 
            WEB3AccOpenTradeConditionVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.23 add(arg0（=取残・電子交付・特定口座伝票） : Object)
        l_arrayList.add(l_tradeConditionVoucher);
        
        //1.24 getInstance(口座開設見込客)
        WEB3AccOpenInsiderRegVoucher l_insiderRegVoucher = 
            WEB3AccOpenInsiderRegVoucher.getInstance(l_accOpenExpAccountOpen);  
                  
        //1.25 add(arg0（=内部者登録伝票） : Object)
        l_arrayList.add(l_insiderRegVoucher);
        
        //1.26 getInstance(口座開設見込客)
        WEB3AccOpenGPRegVoucher l_gpRegVoucher = 
            WEB3AccOpenGPRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.27 add(arg0（=GP条件登録伝票） : Object)
        l_arrayList.add(l_gpRegVoucher);
        
        //1.28 getInstance(口座開設見込客)
        WEB3AccOpenStockHolderRegVoucher l_stockHolderRegVoucher = 
            WEB3AccOpenStockHolderRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
        //1.29 add(arg0（=上場外株・株主登録伝票） : Object)
        l_arrayList.add(l_stockHolderRegVoucher);
        
        //1.30 getInstance(口座開設見込客)
        WEB3AccOpenRealNameRegVoucher l_realNameRegVoucher = 
            WEB3AccOpenRealNameRegVoucher.getInstance(l_accOpenExpAccountOpen); 
            
        //1.31 add(arg0（=顧客正式名称登録伝票） : Object)
        l_arrayList.add(l_realNameRegVoucher);
        
        //1.32 getInstance(口座開設見込客)
        WEB3AccOpenAccountRegBrokerageFirmVoucher l_accountRegBrokerageFirmVoucher = 
            WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);     
            
        //1.33 add(arg0（=顧客登録（仲介業）伝票） : Object)
        l_arrayList.add(l_accountRegBrokerageFirmVoucher);    
        
        //1.34 getInstance(口座開設見込客)
        WEB3AccOpenForeignRegVoucher l_accOpenforeignSaveRegVoucher = 
            WEB3AccOpenForeignRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.35 add(arg0（=外貨預金口座登録伝票） : Object)
        l_arrayList.add(l_accOpenforeignSaveRegVoucher);  

        //getInstance(口座開設見込客 : 口座開設見込客)
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //add(arg0（=機@構通知情報登録伝票） : Object)
        //arg0（機@構通知情報登録伝票）：　@機@構通知情報登録伝票.getInstance()
        l_arrayList.add(l_accOpenAgencyInfoRegVoucher);

        //1.36 ArrayList( )
        ArrayList l_voucherCodeList = new ArrayList();
        
        //1.37 toArray( )
        WEB3AccOpenVoucher[] l_openVoucher = new WEB3AccOpenVoucher[l_arrayList.size()];
        l_arrayList.toArray(l_openVoucher);
        
        //1.38 口座開設伝票[]各要素毎のLOOP処理
        int l_intLength = l_openVoucher.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("1.26 口座開設伝票[]各要素毎のLOOP処理");
            //1.38.1  伝票削除( )
            boolean l_blnIsDeleteVoucher = l_openVoucher[i].deleteVoucher();

            //1.38.2 伝票を削除した場合（伝票削除() == true）、処理実施
            String l_strVoucherCode = "";
            if (l_blnIsDeleteVoucher)
            {
                log.debug("1.26.2 伝票を削除した場合（伝票削除() == true）、処理実施");
                //1.38.2.1 get伝票コード( )
                l_strVoucherCode = l_openVoucher[i].getVoucherCode();
                
                //1.38.2.2  add(arg0（=伝票コード） : Object)
                l_voucherCodeList.add(l_strVoucherCode);
            }
        }

        //1.39 toArray( )
        String[] l_strVoucherCodes = new String[l_voucherCodeList.size()];
        l_voucherCodeList.toArray(l_strVoucherCodes);

        log.exiting(STR_METHOD_NAME);
        return l_strVoucherCodes;
    }
    
    /**
     * (get変更不可項目一覧)<BR>
     * 作成済の伝票について、確定済の項目を変更させないための項目名一覧を取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（口座開設伝票作成）get変更不可項目一覧」参照。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@return String[]
     * @@roseuid 419C4540015A
     */
    public String[] getChangedImpossibleItemList(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangedImpossibleItemList(WEB3AccOpenExpAccountOpen)";
        log.entering(STR_METHOD_NAME);  

        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();

        //1.2  getInstance(口座開設見込客)
        WEB3AccOpenAccountRegVoucher l_accountRegVoucher = 
            WEB3AccOpenAccountRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.3 add(arg0（=顧客登録伝票） : Object)
        l_arrayList.add(l_accountRegVoucher);

        //1.4 getInstance(口座開設見込客)
        WEB3AccOpenContMrgVoucher l_contMrgVoucher = 
            WEB3AccOpenContMrgVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.5 add(arg0（=契約書徴収伝票） : Object)
        l_arrayList.add(l_contMrgVoucher);

        //1.6 getInstance(口座開設見込客)
        WEB3AccOpenBankTransVoucher l_bankTransVoucher = 
            WEB3AccOpenBankTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.7 add(arg0（=振替申込（銀行）伝票） : Object)
        l_arrayList.add(l_bankTransVoucher);

        //1.8 getInstance(口座開設見込客)
        WEB3AccOpenPostalTransVoucher l_postalTransVoucher = 
            WEB3AccOpenPostalTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.9 add(arg0（=振替申込（郵貯）伝票） : Object)
        l_arrayList.add(l_postalTransVoucher);

        // 1.10 getInstance(口座開設見込客)
        WEB3AccOpenChargedInfoVoucher l_chargedInfoVoucher = 
            WEB3AccOpenChargedInfoVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.11 add(arg0（=有料情報伝票） : Object)
        l_arrayList.add(l_chargedInfoVoucher);

        //1.12 getInstance(口座開設見込客)
        WEB3AccOpenAgreeTransVoucher l_agreeTransVoucher = 
            WEB3AccOpenAgreeTransVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.13 add(arg0（=保振同意伝票）
        l_arrayList.add(l_agreeTransVoucher);

        //1.14 getInstance(口座開設見込客)
        WEB3AccOpenMrfAccountVoucher l_mrfAccountVoucher = 
            WEB3AccOpenMrfAccountVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.15 add(arg0（=MRF口座伝票） : Object)
        l_arrayList.add(l_mrfAccountVoucher);

        //1.16 getInstance(口座開設見込客)
        WEB3AccOpenImpConfirmVoucher l_impConfirmVoucher = 
            WEB3AccOpenImpConfirmVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.17 add(arg0（=重要事項確認書） : Object)
        l_arrayList.add(l_impConfirmVoucher);

        //1.18 getInstance(口座開設見込客)
        WEB3AccOpenIdConfirmVoucher l_confirmVoucher = 
            WEB3AccOpenIdConfirmVoucher.getInstance(l_accOpenExpAccountOpen);        

        //1.19  add(arg0（=本人確認伝票） : Object)
        l_arrayList.add(l_confirmVoucher);

        //1.20 getInstance(口座開設見込客)
        WEB3AccOpenPasswordVoucher l_passwordVoucher = 
            WEB3AccOpenPasswordVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.21 add(arg0（=暗証番号伝票） : Object)
        l_arrayList.add(l_passwordVoucher);

        //1.22 getInstance(口座開設見込客)
        WEB3AccOpenTradeConditionVoucher l_tradeConditionVoucher = 
            WEB3AccOpenTradeConditionVoucher.getInstance(l_accOpenExpAccountOpen);

        //1.23 add(arg0（=取残・電子交付・特定口座伝票） : Object)
        l_arrayList.add(l_tradeConditionVoucher);
        
        //1.24 getInstance(口座開設見込客)
        WEB3AccOpenInsiderRegVoucher l_insiderRegVoucher = 
            WEB3AccOpenInsiderRegVoucher.getInstance(l_accOpenExpAccountOpen);  
                  
        //1.25 add(arg0（=内部者登録伝票） : Object)
        l_arrayList.add(l_insiderRegVoucher);
        
        //1.26 getInstance(口座開設見込客)
        WEB3AccOpenGPRegVoucher l_gpRegVoucher = 
            WEB3AccOpenGPRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.27 add(arg0（=GP条件登録伝票） : Object)
        l_arrayList.add(l_gpRegVoucher);
        
        //1.28 getInstance(口座開設見込客)
        WEB3AccOpenStockHolderRegVoucher l_stockHolderRegVoucher = 
            WEB3AccOpenStockHolderRegVoucher.getInstance(l_accOpenExpAccountOpen);
            
        //1.29 add(arg0（=上場外株・株主登録伝票） : Object)
        l_arrayList.add(l_stockHolderRegVoucher);
        
        //1.30 getInstance(口座開設見込客)
        WEB3AccOpenRealNameRegVoucher l_realNameRegVoucher = 
            WEB3AccOpenRealNameRegVoucher.getInstance(l_accOpenExpAccountOpen); 
            
        //1.31 add(arg0（=顧客正式名称登録伝票） : Object)
        l_arrayList.add(l_realNameRegVoucher);
        
        //1.32 getInstance(口座開設見込客)
        WEB3AccOpenAccountRegBrokerageFirmVoucher l_accountRegBrokerageFirmVoucher = 
            WEB3AccOpenAccountRegBrokerageFirmVoucher.getInstance(l_accOpenExpAccountOpen);     
            
        //1.33 add(arg0（=顧客登録（仲介業）伝票） : Object)
        l_arrayList.add(l_accountRegBrokerageFirmVoucher);        
        
        //1.34 getInstance(口座開設見込客)
        WEB3AccOpenForeignRegVoucher l_accOpenforeignSaveRegVoucher = 
            WEB3AccOpenForeignRegVoucher.getInstance(l_accOpenExpAccountOpen);
        
        //1.35 add(arg0（=外貨預金口座登録伝票） : Object)
        l_arrayList.add(l_accOpenforeignSaveRegVoucher);  

        //getInstance(口座開設見込客 : 口座開設見込客)
        WEB3AccOpenAgencyInfoRegVoucher l_accOpenAgencyInfoRegVoucher =
            WEB3AccOpenAgencyInfoRegVoucher.getInstance(l_accOpenExpAccountOpen);

        //add(arg0（=機@構通知情報登録伝票） : Object)
        //arg0（機@構通知情報登録伝票）：　@機@構通知情報登録伝票.getInstance()
        l_arrayList.add(l_accOpenAgencyInfoRegVoucher);

        //1.36 Hashtable( )
        Hashtable l_hashtable = new Hashtable();

        //1.37 toArray( )
        WEB3AccOpenVoucher[] l_openVoucher = new WEB3AccOpenVoucher[l_arrayList.size()];
        l_arrayList.toArray(l_openVoucher);

        //1.38 口座開設伝票[]各要素毎のLOOP処理
        int l_intLength = l_openVoucher.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if (!l_openVoucher[i].isTargetVoucher())
            {
                //is対象伝票()の戻り値がfalseの場合、次の要素に処理を移す。
                continue;
            }

            log.debug("口座開設伝票[]各要素毎のLOOP処理");
            //1.38.1 get確定済項目名( )
            String[] l_strConfirmedItemNames = l_openVoucher[i].getConfirmedItemName();
            
            //1.38.2 伝票出力済項目の数分（get確定済項目名()の戻り値）LOOP処理
            int l_intNameLength = l_strConfirmedItemNames.length;
            for (int j = 0; j < l_intNameLength; j++)
            {
                log.debug("伝票出力済項目の数分（get確定済項目名()の戻り値）LOOP処理");
                //1.38.2.1 put(arg0（=伝票出力済項目[index]） : Object, arg1（=伝票出力済項目[index]） : Object)
                l_hashtable.put(l_strConfirmedItemNames[j], l_strConfirmedItemNames[j]);
            }
        }
        
        //1.39 values()
        Collection l_collection = l_hashtable.values();
        
        String[] l_strVoucherCodes = new String[l_collection.size()];
        l_collection.toArray(l_strVoucherCodes);

        log.exiting(STR_METHOD_NAME);
        return l_strVoucherCodes;
    }
}
@
