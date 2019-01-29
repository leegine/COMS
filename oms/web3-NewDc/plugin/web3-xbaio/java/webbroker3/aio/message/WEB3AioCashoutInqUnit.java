head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInqUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ明細(WEB3AioCashoutInqUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2004/12/10 周勇 (中訊) 残対応
                   2006/06/14 韋念瓊 (中訊) モデルNo.593
                   2006/07/31 徐大方 (中訊) 式樣變更 モデル604
                   2006/09/04 車進 (中訊) 式樣變更 モデルNo.629
*/

package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (出金申込問合せ明細)<BR>
 * 出金申込問合せ明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */
public class WEB3AioCashoutInqUnit extends Message
{    
    /**
     * (注文ID)
     */
    public String orderId;
    
    /**
     * (部店コード)
     */
    public String branchCode;
    
    /**
     * (顧客コード)
     */
    public String accountCode;
    
    /**
     * (顧客名)（漢字）
     */
    public String accountName;
    
    /**
     * (注文経路区分）<BR>
     * <BR>
     * 1： コールセンター<BR>
     * 2： PC<BR>
     * 3： スリングショット<BR>
     * 4： i-mode<BR>
     * 5： Vodafone<BR>
     * 6： AU<BR>
     * 9： HOST<BR>
     * A： 管理者<BR>
     * B： 保証金自動振替バッチ<BR>
     * <BR>
     */
    public String orderRootDiv;
    
    /**
     * (扱者コード）
     */
    public String traderCode;
    
    /**
     * (注文日時)
     */
    public Date orderDate;
    
    /**
     * (受渡日)
     */
    public Date deliveryDate;
    
    /**
     * (出金額)
     */
    public String cashoutAmt;
    
    //============ remain zhou-yong NO.1  begin ==============
    
    /**
     * (注文受付区分)<BR>
     * <BR>
     * 0： 受付未済<BR>
     * 1： 受付済<BR>
     * 2： 受付エラー<BR>
     * 4： 受付中 <BR>
     * <BR>
     */
    public String orderDiv;
    
    //============ remain zhou-yong NO.1  end ==============    
    
    /**
     * (取消区分)<BR>
     * <BR>
     * 0： 初期値<BR>
     * 1： 取消中<BR>
     * 2： 取消済<BR>
     * 3： 取消失敗<BR>
     * 4： エラー<BR>
     * 5： 取消不可<BR>
     */
    public String cancelDiv;
    
    /**
     * (取消日時)
     */
    public Date cancelDate;
    
    /**
     * (出金余力)
     */
    public String paymentPower;
    
    /**
     * (金融機@関コード)
     */
    public String financialInstitutionCode;
    
    /**
     * (出金チェック、取消チェックの結果)<BR>
     * <BR>
     * 0： OK<BR>
     * 1： NG<BR>
     */
    public String checkResult;
    
    /**
     * (振込先支店コード)<BR>
     * 振込先支店コード<BR>
     */
    public String transferBranchCode;
    
    /**
     * (預金区分)<BR>
     * 預金区分<BR>
     * <BR>
     * 1:普通預金<BR>
     * 2:当座預金<BR>
     * 3:その他<BR>
     * 4:貯蓄貯金<BR>
     */
    public String transferAccountDiv;
    
    /**
     * (振込先口座番号)<BR>
     * 振込先口座番号<BR>
     */
    public String transferAccountNumber;
    
    /**
     * (振込先口座名義人)<BR>
     * 振込先口座名義人<BR>
     */
    public String transferAccountName;

    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;    
    
    /**
     * (出金申込問合せ明細)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.aio.message.WEB3AioCashoutInqUnit
     * @@roseuid 40E52E17006F
     */
    public WEB3AioCashoutInqUnit() 
    {
     
    }
}
@
