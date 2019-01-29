head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionChangeAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料変更顧客情報(WEB3AccInfoCommissionChangeAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
Revesion History : 2008/08/22 張少傑 (中訊) 仕様変更・モデルNo.248
Revesion History : 2008/08/26 張少傑 (中訊) 仕様変更・モデルNo.249
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.define.WEB3CommisionProductCodeDef;


/**
 * (手数料変更顧客情報)<BR>
 * 手数料変更顧客情報<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoCommissionChangeAccountInfo extends Message 
{
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     * 商品コード（手数料商品コード）<BR>
     * <BR>
     * 10：　@上場株式<BR>
     * 11：　@店頭株式<BR>
     * 12：　@ミニ株式<BR>
     * 40：　@外国株式<BR>
     * 50：　@先物<BR>
     * 51：　@オプション<BR>
     */
    public String instrumentsCode;
    
    /**
     * (適用開始日)<BR>
     * 適用開始日<BR>
     */
    public Date trialStartDate;
    
    /**
     * (手数料Ｎｏ．)<BR>
     * 手数料Ｎｏ．<BR>
     * <BR>
     * ００～９９：委託手数料顧客条件登録マスターの手数料No.<BR>
     */
    public String commissionNo;
    
    /**
     * (徴収率)<BR>
     * 徴収率<BR>
     */
    public String collectRate;
    
    /**
     * (適用終了日)<BR>
     * 適用終了日<BR>
     */
    public Date trialEndDate;
    
    /**
     * @@roseuid 418F3862002E
     */
    public WEB3AccInfoCommissionChangeAccountInfo() 
    {
     
    }
    
    /**
     * 自身のコピーを作成し返却する。<BR>
     * <BR>
     * 手数料変更顧客情報を生成する。<BR>
     * 生成したオブジェクトに引数の商品コードをセットする。<BR>
     * 以外のプロパティは、自身と同じ値を生成したオブジェクトにセットし返却する。<BR>
     * @@param l_strProductCode - 商品コード
     * @@return Object
     * @@roseuid 4146B01003BB
     */
    public Object clone(String l_strProductCode) 
    {
        
        WEB3AccInfoCommissionChangeAccountInfo l_accInfo = new WEB3AccInfoCommissionChangeAccountInfo();
        l_accInfo.accountCode = this.accountCode;
        l_accInfo.branchCode = this.branchCode;
        l_accInfo.collectRate = this.collectRate;
        l_accInfo.commissionNo = this.commissionNo;
        l_accInfo.trialEndDate = this.trialEndDate;
        l_accInfo.trialStartDate = this.trialStartDate;
        
        if (l_strProductCode.equals(WEB3CommisionProductCodeDef.LISTING_STOCK) ||
            //l_strProductCode.equals(WEB3CommisionProductCodeDef.OTC_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.MINI_STOCK) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.FOREIGN_EQITY) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_FUTURES) ||
            l_strProductCode.equals(WEB3CommisionProductCodeDef.INDEX_OP))
            
        {
            l_accInfo.instrumentsCode = l_strProductCode;
        }
         return l_accInfo;
    }
}
@
