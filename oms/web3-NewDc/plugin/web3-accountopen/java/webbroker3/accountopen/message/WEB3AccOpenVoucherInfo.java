head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 伝票作成情報(WEB3AccOpenVoucherInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
                 : 2006/07/10 黄建 (中訊) 仕様変更 モデル075
                 : 2006/08/14 柴雙紅 (中訊) 仕様変更 モデル087
Revesion History : 2009/08/10 張騰宇 (中訊) 仕様変更 モデル163
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 伝票作成情報
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenVoucherInfo extends Message 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenVoucherInfo.class);   
    
    /**
     * (G11顧客登録)<BR>
     * 伝票作成情報　@G11顧客登録<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] accRegVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1151契約書徴収)<BR>
     * 伝票作成情報　@G1151契約書徴収<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] contMrgVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G26振替申込)<BR>
     * 伝票作成情報　@G26振替申込<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] transVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (GA300保振同意)<BR>
     * 伝票作成情報　@GA300保振同意<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] agreeTransVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G5401有料情報)<BR>
     * 伝票作成情報　@G5401有料情報<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] chargedInfoVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (GI601MRF口座)<BR>
     * 伝票作成情報　@GI601MRF口座<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] mrfAccVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G5511暗証番号)<BR>
     * 伝票作成情報　@G5511暗証番号<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] passwordVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1159重要事項)<BR>
     * 伝票作成情報　@G1159重要事項<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] impConfirmVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1175本人確認)<BR>
     * 伝票作成情報　@G1175本人確認<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] confirmVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (GI311取残・電子交付・特定口座)<BR>
     * 伝票作成情報　@GI311取残・電子交付・特定口座<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] tradeConditionVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G9801内部者)<BR>
     * 伝票作成情報　@G9801内部者<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] insiderVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1220GP条件)<BR>
     * 伝票作成情報　@G1220GP条件<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] gpVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G8610上場外株・株主)<BR>
     * 伝票作成情報　@G8610上場外株・株主<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] listedHolderVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1190顧客正式名称)<BR>
     * 伝票作成情報　@G1190顧客正式名称<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] accRealNameVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G43外貨預金口座登録)<BR>
     * 伝票作成情報　@G43外貨預金口座登録<BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成）<BR>
     * 1：　@作成済<BR>
     * 2：　@送信保留中<BR>
     * 3：　@送信済<BR>
     * 4：　@受信済<BR>
     * 5：　@送信エラー<BR>
     * 6：　@受信エラー<BR>
     */
    public String[] foreignSaveInfoVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};

    /**
     * (GS103機@構通知)<BR>
     * 伝票作成情報　@GS103機@構通知 <BR>
     * <BR>
     * 0：　@DEFAULT（伝票未作成） <BR>
     * 1：　@作成済 <BR>
     * 2：　@送信保留中 <BR>
     * 3：　@送信済 <BR>
     * 4：　@受信済 <BR>
     * 5：　@送信エラー <BR>
     * 6：　@受信エラー <BR>
     */
    public String[] agencyVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};

    /**
     * @@roseuid 41B45E7B00FA
     */
    public WEB3AccOpenVoucherInfo() 
    {
     
    }
    
    /**
     * 引数のオブジェクトが本オブジェクトと同一かを判定する。<BR>
     * <BR>
     * １）　@引数の伝票作成情報を伝票作成情報型にcastする。<BR>
     * ２）　@伝票作成情報の各プロパティと本オブジェクトの各プロパティを比較し、<BR>
     * 　@すべて同じ値がセットされていればtrue、<BR>
     * 　@値の違うプロパティが１つでも存在する場合はfalseを返却する。<BR>
     * @@param l_accOpenVoucherInfo - 伝票作成情報
     * @@return Boolean
     * @@roseuid 4191A4CA0071
     */
    public boolean equals(Object l_accOpenVoucherInfo) 
    {
        final String STR_METHOD_NAME = " equals(Object)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数の伝票作成情報を伝票作成情報型にcastする。
        WEB3AccOpenVoucherInfo l_openVoucherInfo = new WEB3AccOpenVoucherInfo();
        
        if (l_accOpenVoucherInfo instanceof WEB3AccOpenVoucherInfo)
        {
            l_openVoucherInfo = (WEB3AccOpenVoucherInfo)l_accOpenVoucherInfo;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        //２）伝票作成情報の各プロパティと本オブジェクトの各プロパティを比較し、
        //すべて同じ値がセットされていればtrue、
        //値の違うプロパティが１つでも存在する場合はfalseを返却する。
        
        //伝票作成情報　@G11顧客登録        
        boolean l_blnIsVoucherDiv = arrayEquals(l_openVoucherInfo.accRegVoucherDiv, this.accRegVoucherDiv);
        
        //伝票作成情報　@G1151契約書徴収
        boolean l_blnIsContMrgVoucherDiv = arrayEquals(l_openVoucherInfo.contMrgVoucherDiv, this.contMrgVoucherDiv);
        
        //伝票作成情報　@G26振替申込
        boolean l_blnIsTransVoucherDiv = arrayEquals(l_openVoucherInfo.transVoucherDiv, this.transVoucherDiv);
        
        //伝票作成情報　@GA300保振同意
        boolean l_blnIsAgreeTransVoucherDiv = arrayEquals(l_openVoucherInfo.agreeTransVoucherDiv, this.agreeTransVoucherDiv);
        
        //伝票作成情報　@G5401有料情報
        boolean l_blnIsChargedInfoVoucherDiv = arrayEquals(l_openVoucherInfo.chargedInfoVoucherDiv, this.chargedInfoVoucherDiv);
        
        //伝票作成情報　@GI601MRF口座
        boolean l_blnIsMrfAccVoucherDiv = arrayEquals(l_openVoucherInfo.mrfAccVoucherDiv, this.mrfAccVoucherDiv);
        
        //伝票作成情報　@G5511暗証番号
        boolean l_blnIsPasswordVoucherDiv = arrayEquals(l_openVoucherInfo.passwordVoucherDiv, this.passwordVoucherDiv);
        
        //伝票作成情報　@G1159重要事項
        boolean l_blnIsImpConfirmVoucherDiv = arrayEquals(l_openVoucherInfo.impConfirmVoucherDiv, this.impConfirmVoucherDiv);
        
        //伝票作成情報　@G1175本人確認
        boolean l_blnIsConfirmVoucherDiv = arrayEquals(l_openVoucherInfo.confirmVoucherDiv, this.confirmVoucherDiv);
        
        //伝票作成情報　@GI311取残・電子交付・特定口座
        boolean l_blnIsTradeConditionVoucherDiv = arrayEquals(l_openVoucherInfo.tradeConditionVoucherDiv, this.tradeConditionVoucherDiv);
        
        //伝票作成情報　@G9801内部者
        boolean l_blnIsInsiderVoucherDiv = arrayEquals(l_openVoucherInfo.insiderVoucherDiv, this.insiderVoucherDiv);
        
        //伝票作成情報　@G1220GP条件
        boolean l_blnIsGpVoucherDiv = arrayEquals(l_openVoucherInfo.gpVoucherDiv, this.gpVoucherDiv);
        
        //伝票作成情報　@G8610上場外株・株主
        boolean l_blnIsListedHolderVoucherDiv = arrayEquals(l_openVoucherInfo.listedHolderVoucherDiv, this.listedHolderVoucherDiv);
        
        //伝票作成情報　@G1190顧客正式名称
        boolean l_blnIsAccRealNameVoucherDiv = arrayEquals(l_openVoucherInfo.accRealNameVoucherDiv, this.accRealNameVoucherDiv);
        
        //伝票作成情報　@G43外貨預金口座登録
        boolean l_blnIsForeignSaveInfoVoucherDiv = arrayEquals(l_openVoucherInfo.foreignSaveInfoVoucherDiv, this.foreignSaveInfoVoucherDiv);

        // 伝票作成情報　@GS103機@構通知
        boolean l_blnIsAgencyVoucherDiv = arrayEquals(l_openVoucherInfo.agencyVoucherDiv, this.agencyVoucherDiv);
 
        if (l_blnIsVoucherDiv && l_blnIsContMrgVoucherDiv && 
            l_blnIsTransVoucherDiv && l_blnIsAgreeTransVoucherDiv && 
            l_blnIsChargedInfoVoucherDiv && l_blnIsMrfAccVoucherDiv && 
            l_blnIsPasswordVoucherDiv && l_blnIsImpConfirmVoucherDiv && 
            l_blnIsConfirmVoucherDiv && l_blnIsTradeConditionVoucherDiv&&
            l_blnIsInsiderVoucherDiv && l_blnIsGpVoucherDiv &&
            l_blnIsListedHolderVoucherDiv && l_blnIsAccRealNameVoucherDiv &&
            l_blnIsForeignSaveInfoVoucherDiv && l_blnIsAgencyVoucherDiv)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * 二つの配列を比較して、等しい場合に、trueに返却します。等しくない場合に、falseに返却します。
     * 二つの配列は全てnullになる場合に、trueに返却します。
     * 一つの配列はnullで、その他の配列はnullでない場合に、falseに返却します
     * @@param l_strArgsOne 配列オブジェクト
     * @@param l_strArgsTwo 配列オブジェクト
     * @@return Boolean
     */
    private boolean arrayEquals(String[] l_strArgsOne, String[] l_strArgsTwo)
    {
        if (l_strArgsOne == null && l_strArgsTwo == null)
        {
            return true;
        }
        else if (l_strArgsOne == null || l_strArgsTwo == null)
        {
            return false;
        }
        else if(l_strArgsOne.length == 0 && l_strArgsTwo.length == 0)
        {
            return true;
        }
        
        if (l_strArgsOne.length != l_strArgsTwo.length)
        {
            return false;
        }
        
        int l_intArgLength = l_strArgsOne.length;
        
        int l_intKeyOne = this.nullSum(l_strArgsOne);
        int l_intKeyTwo = this.nullSum(l_strArgsTwo);
        
        if (l_intKeyOne != l_intKeyTwo)
        {
            return false;
        }
        
        this.sequence(l_strArgsOne, l_intKeyOne);
        this.sequence(l_strArgsTwo, l_intKeyTwo);
        
        if (l_intKeyOne == 0)
        {
        
            for (int i = 0; i < l_intArgLength; i++)
            {
    
                if (!l_strArgsOne[i].equals(l_strArgsTwo[i]))
                {
                    return false;
                }
    
            }
        }
        else
        {
            for (int i = l_intKeyOne; i < l_intArgLength; i++)
            {
    
                if (!l_strArgsOne[i].equals(l_strArgsTwo[i]))
                {
                    return false;
                }
    
            }
        }
        return true;
    }
    
    /**
     * 配列を小から大への順序で並び替えます。
     * @@param l_strArgs 配列オブジェクト
     * @@return
     */
    private void sequence(String[] l_strArgs, int l_intSum)
    {
        int l_intArgLength = l_strArgs.length;
        String l_strKey = null;
        String[] l_strNews = new String[l_intArgLength];
        String[] l_strNewTwos = null;
        
        l_strNewTwos = new String[l_intArgLength - l_intSum];
        
        int l_intLength = l_strNewTwos.length;
        for (int j = 0; j < l_intLength;)
        {
            for (int i = 0; i < l_intArgLength; i++)
            {
                if (l_strArgs[i] != null)
                {
                    l_strNewTwos[j] = l_strArgs[i];
                    if (j < l_strNewTwos.length)
                    {
                        j = j + 1;
                    }
                }
            }
        }        
        
        int l_intKeySave = 0;
        l_intKeySave = l_intSum;
        
        for (int j = 0; j < l_intLength; j++)
        {
            l_strNews[l_intSum] = l_strNewTwos[j];
            l_intSum = l_intSum + 1;               
        }      
        
        if (l_intSum == 0)
        {
            for (int i = 0; i < l_intArgLength; i++)
            {
                for (int j = i + 1; j < l_intArgLength; j++)
                {                   
                    if (l_strArgs[i].compareTo(l_strArgs[j]) > 0)
                    {
                        l_strKey = l_strArgs[i];
                        l_strArgs[i] = l_strArgs[j];
                        l_strArgs[j] = l_strKey;   
                    }

                }
            }
        }
        else
        {
            for (int i = l_intKeySave; i < l_intArgLength; i++)
            {
                for (int j = i + 1; j < l_intArgLength; j++)
                {                
                    if (l_strNews[i].compareTo(l_strNews[j]) > 0)
                    {
                        l_strKey = l_strNews[i];
                        l_strNews[i] = l_strNews[j];
                        l_strNews[j] = l_strKey;
                    }
                }
            }
            
            for (int i= 0; i < l_strNews.length; i++)
            {
                l_strArgs[i] = l_strNews[i];
            }
        }     
    }
    
    /**
     * 配列中のnullの数を計算します。
     * @@param l_strArgs 配列オブジェクト
     * @@return
     */
    private int nullSum(String[] l_strArgs)
    {
        int l_intArgLength = l_strArgs.length;
        int l_intKey = 0;
        for (int i = 0; i < l_intArgLength; i++)
        {
            if (l_strArgs[i] == null)
            {
                l_intKey = l_intKey + 1;  
            }
        }
        
        return l_intKey;
    }
}@
