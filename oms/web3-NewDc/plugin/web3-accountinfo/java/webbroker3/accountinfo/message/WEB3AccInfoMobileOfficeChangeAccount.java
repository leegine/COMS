head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeChangeAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 携帯番号・勤務先情報変更申込顧客(WEB3AccInfoMobileOfficeChangeAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
Revesion History : 2006/12/14 周捷 (中訊) モデルNo.153
Revesion History : 2007/01/17 何文敏 (中訊) モデルNo.160
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (携帯番号・勤務先情報変更申込顧客)<BR>
 * 携帯番号・勤務先情報変更申込顧客メッセージ<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeChangeAccount extends Message 
{
    
    /**
     * (申込日)<BR>
     * 申込日<BR>
     */
    public Date applyDate;
    
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
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (申込者コード)<BR>
     * 申込者コード<BR>
     */
    public String updaterCode;
    
    /**
     * (判定日)<BR>
     * 判定日<BR>
     */
    public Date judgementDate;
    
    /**
     * (判定者コード)<BR>
     * 判定者コード<BR>
     */
    public String judgeCode;
    
    /**
     * (申込状況区分)<BR>
     * 申込状況区分<BR>
     * <BR>
     * 0：　@判定待ち<BR>
     * 1：　@判定待ち（確認中）<BR>
     * 2：　@判定済み<BR>
     */
    public String applyStateDiv;
    
    /**
     * (判定結果)<BR>
     * 判定結果<BR>
     * <BR>
     * 1：承認<BR>
     * 2：不可<BR>
     */
    public String judgmentResultDiv;

    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：個人口座<BR>
     * 1：法@人口座<BR>
     */
    public String accountType;

    /**
     * (携帯電話・勤務先情報)<BR>
     */
    public WEB3AccInfoMobileOfficeInfo mobileOfficeInfo;

    /**
     * (受付結果)<BR>
     * 受付結果<BR>
     * <BR>
     * 1：受付完了<BR>
     * 9：エラー<BR>
     */
    public String acceptedResult;

    /**
     * (携帯番号・勤務先情報変更申込顧客)<BR>
     * 携帯番号・勤務先情報変更申込顧客<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeChangeAccount
     * @@roseuid 414982D80319
     */
    public WEB3AccInfoMobileOfficeChangeAccount() 
    {
     
    }
}
@
