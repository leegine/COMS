head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccountOpenVoucherCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 伝票コード 定数定義インタフェイス(WEB3AccountOpenVoucherCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 李頴淵 (中訊) 新規作成
                   2006/07/07 黄建 (中訊) 仕様変更・モデル072
                   2006/08/17 李俊 (中訊) 仕様変更・モデル087
Revesion History : 2009/08/14 武波 (中訊) 仕様変更 モデル171
*/
package webbroker3.accountopen.define;

/**
 * 伝票コード 定数定義インタフェイス
 * 
 * @@author 李頴淵(中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenVoucherCodeDef
{
    /**
     * GI601：　@MRF口座伝票コード
     */
    public static final String MRF_ACCOUNT_VOUCHER_CODE = "GI601";
    
    /**
     * G5511：　@暗証番号伝票コード
     */
    public static final String PASSWORD_VOUCHER_CODE = "G5511";
    
    /**
     * G1151：　@契約書徴収伝票コード
     */
    public static final String CONT_MRG_VOUCHER_CODE = "G1151";
    
    /**
     * G11：　@顧客登録伝票コード
     */
    public static final String ACCOUNT_REG_VOUCHER_CODE = "G11";
    
    /**
     * GI311：　@取残・電子交付・特定口座伝票コード
     */
    public static final String TRADE_CONDITION_VOUCHER_CODE = "GI311";
    
    /**
     * G1159：　@重要事項確認書伝票コード
     */
    public static final String IMP_CONFIRM_VOUCHER_CODE = "G1159";
    
    /**
     * G26：　@振替申込（銀行）伝票コード
     */
    public static final String BANK_TRANS_VOUCHER_CODE = "G26";
    
    /**
     * GA300：　@保振同意伝票コード
     */
    public static final String AGREE_TRANS_VOUCHER_CODE = "GA300";
    
    /**
     * G1175：　@本人確認伝票コード
     */
    public static final String ID_CONFIRM_VOUCHER_CODE = "G1175";
    
    /**
     * G5401：　@有料情報伝票コード
     */
    public static final String CHARGED_INFO_VOUCHER_CODE = "G5401";
    
    /**
     * G9801：　@内部者登録伝票コード
     */
    public static final String INSIDER_REG_VOUCHER_CODE = "G9801";
    
    /**
     * G1220：　@GP条件登録伝票コード
     */
    public static final String GP_REG_VOUCHER_CODE = "G1220";
    
    /**
     * G8610：　@上場外株・株主登録伝票コード
     */
    public static final String STOCK_HOLDER_REG_VOUCHER_CODE = "G8610";
    
    /**
     * G1190：　@顧客正式名称登録伝票コード
     */
    public static final String REAL_NAME_REG_VOUCHER_CODE = "G1190";
    
    /**
     * G11：　@顧客登録（仲介業）伝票コード
     */
    public static final String REG_BROKERAGE_FIRM_VOUCHER_CODE = "G11";
    
    /**
     * G43 :  外貨預金口座登録伝票コード
     */
    public static final String FOREIGN_SAVE_REG_VOUCHER_CODE = "G43";

    /**
     * GS103 : 機@構通知情報登録
     */
    public static final String ACCOPEN_AGENCY_INFO_REGIST = "GS103";
}
@
