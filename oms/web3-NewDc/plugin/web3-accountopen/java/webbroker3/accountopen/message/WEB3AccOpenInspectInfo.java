head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInspectInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設審査情報(WEB3AccOpenInspectInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 張秋穎 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (口座開設審査情報)<BR>
 * 口座開設審査情報
 * 
 * @@author 張秋穎
 * @@version 1.0
 */
public class WEB3AccOpenInspectInfo extends Message
{
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;
    
    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）
     */
    public String accountNameKana;
    
    /**
     * (生年月日年号)<BR>
     * 生年月日年号<BR>
     * <BR>
     * 1：明治　@2：大正　@3：昭和　@4：平成　@9：不明
     */
    public String eraBorn;
    
    /**
     * (生年月日)<BR>
     * 生年月日<BR>
     * <BR>
     * 和暦（YYMMDD）：6桁
     */
    public String bornDate;
    
    /**
     * (電話番号)<BR>
     * 電話番号<BR>
     * <BR>
     * ※　@"-"を含む文字列
     */
    public String telephone;
    
    /**
     * (携帯番号)<BR>
     * 携帯番号<BR>
     * <BR>
     * ※　@"-"を含む文字列
     */
    public String mobileTelephone;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス
     */
    public String mailAddress;
    
    /**
     * (住所１)<BR>
     * 住所１
     */
    public String address1;
    
    /**
     * (住所２)<BR>
     * 住所２
     */
    public String address2;
    
    /**
     * (住所３)<BR>
     * 住所３
     */
    public String address3;
    
    /**
     * (発生日時)<BR>
     * 発生日時
     */
    public Date occurredDate;
    
    /**
     * (審査種別)<BR>
     * 審査種別<BR>
     * <BR>
     * 1：同一顧客チェック　@2：同一顧客（見込）チェック　@3：Y客チェック
     */
    public String reviewCode;
    
    /**
     * (審査区分)<BR>
     * 審査区分<BR>
     * <BR>
     * 0：審査待ち　@1：認可　@2：否認
     */
    public String checkDiv;
    
    /**
     * (審査日時)<BR>
     * 審査日時
     */
    public Date checkDate;
    
    /**
     * (審査担当者)<BR>
     * 審査担当者
     */
    public String checkerCode;
    
    /**
     * (重複区分)<BR>
     * 重複区分
     */
    public String duplicateDiv;
    
    /**
     * (重複部店コード)<BR>
     * 重複部店コード
     */
    public String dupliBranchCode;
    
    /**
     * (重複顧客コード)<BR>
     * 重複顧客コード
     */
    public String dupliAccountCode;
    
    /**
     * (重複顧客名（カナ）)<BR>
     * 重複顧客名（カナ）
     */
    public String dupliAccountNameKana;
    
    /**
     * (重複生年月日年号)<BR>
     * 重複生年月日年号<BR>
     * <BR>
     * 1：明治　@2：大正　@3：昭和　@4：平成　@9：不明
     */
    public String dupliEraBorn;
    
    /**
     * (重複生年月日)<BR>
     * 重複生年月日<BR>
     * <BR>
     * 和暦（YYMMDD）：6桁
     */
    public String dupliBornDate;
    
    /**
     * (重複電話番号)<BR>
     * 重複電話番号<BR>
     * <BR>
     * ※　@"-"を含む文字列
     */
    public String dupliTelephone;
    
    /**
     * (重複携帯番号)<BR>
     * 重複携帯番号<BR>
     * <BR>
     * ※　@"-"を含む文字列
     */
    public String dupliMobileTelephone;
    
    /**
     * (重複メールアドレス)<BR>
     * 重複メールアドレス
     */
    public String dupliMailAddress;
    
    /**
     * (重複住所１)<BR>
     * 重複住所１
     */
    public String dupliAddress1;
    
    /**
     * (重複住所２)<BR>
     * 重複住所２
     */
    public String dupliAddress2;
    
    /**
     * (重複住所３)<BR>
     * 重複住所３
     */
    public String dupliAddress3;
    
    /**
     * (Y客ID)<BR>
     * Y客ID
     */
    public String yellowAccountId;
    
    /**
     * (Y客管理部店コード)<BR>
     * Y客管理部店コード
     */
    public String yAccountBranchCode;
    
    /**
     * (Y客業務区分)<BR>
     * Y客業務区分
     */
    public String yAccountBusinessDiv;
    
    /**
     * (Y客管理No)<BR>
     * Y客管理No
     */
    public String yAccountMngNo;
    
    /**
     * @@roseuid 44912C0E03A9
     */
    public WEB3AccOpenInspectInfo() 
    {
    }
}
@
