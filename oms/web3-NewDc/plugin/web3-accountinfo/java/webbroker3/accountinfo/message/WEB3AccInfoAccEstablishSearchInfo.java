head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccEstablishSearchInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規口座開設・口座移管・ログインロック顧客情報(WEB3AccInfoAccEstablishSearchInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  何文敏(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (新規口座開設・口座移管・ログインロック顧客情報)<BR>
 * 新規口座開設・口座移管・ログインロック顧客情報<BR>
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AccInfoAccEstablishSearchInfo extends Message
{
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (扱者コード)<BR>
     * 扱者コード<BR>
     */
    public String traderCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (口座種別)<BR>
     * 口座種別<BR>
     * <BR>
     * 1：　@個人客<BR>
     * 2：　@法@人客<BR>
     */
    public String accountTypeCode;

    /**
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;

    /**
     * (顧客名（カナ))<BR>
     * 顧客名（カナ)<BR>
     */
    public String accountNameKana;

    /**
     * (口座開設日)<BR>
     * 口座開設日<BR>
     */
    public Date accountOpenDate;

    /**
     * (入金先銀行)<BR>
     * 入金先銀行<BR>
     */
    public String payFinancialInstitution;

    /**
     * (銀行支店名)<BR>
     * 銀行支店名<BR>
     */
    public String financialBranchName;

    /**
     * (科目)<BR>
     * 科目<BR>
     */
    public String item;

    /**
     * (銀行番号)<BR>
     * 銀行番号<BR>
     */
    public String financialInstitutionNumber;

    /**
     * (銀行支店番号)<BR>
     * 銀行支店番号<BR>
     */
    public String financialBranchCode;

    /**
     * (銀行口座番号)<BR>
     * 銀行口座番号<BR>
     */
    public String financialAccountCode;

    /**
     * (顧客住所（郵便番号）)<BR>
     * 顧客住所（郵便番号）<BR>
     */
    public String zipCode;

    /**
     * (顧客住所（住所1))<BR>
     * 顧客住所（住所1）<BR>
     */
    public String address1;

    /**
     * (顧客住所（住所2）)<BR>
     * 顧客住所（住所2）<BR>
     */
    public String address2;

    /**
     * (顧客住所（住所3）)<BR>
     * 顧客住所（住所3）<BR>
     */
    public String address3;

    /**
     * (ログインロックフラグ )<BR>
     * ログインロックフラグ <BR>
     * <BR>
     * true：　@ログインロック中<BR>
     * false：　@ログインロック解除中<BR>
     */
    public boolean loginLockFlag;

    /**
     * (ログインエラー回数)<BR>
     * ログインエラー回数<BR>
     */
    public String loginErrorCount;
}
@
