head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.18.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseRegistInfoCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報委託手数料コース変更申込情報作成サービス(WEB3AccInfoCommissionCourseRegistInfoCreatedService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.common.WEB3BaseException;

/**
 * (お客様情報委託手数料コース変更申込情報作成サービス)<BR>
 * お客様情報委託手数料コース変更申込情報作成サービスインタフェイス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoCommissionCourseRegistInfoCreatedService extends Service 
{
    
    /**
     * (create手数料コース変更申込情報)<BR>
     * 委託手数料コース変更申込オブジェクトより、<BR>
     * 手数料コース変更申込情報メッセージデータを作成する。<BR>
     * @@param l_commissionCourseRegist - 委託手数料コース変更申込オブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo
     * @@roseuid 41511E740136
     */
    public WEB3AccInfoCommissionCourseChangeInfo createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException ;
    
    /**
     * (create手数料コース変更申込情報)<BR>
     * 委託手数料コース変更申込オブジェクトの配列より、<BR>
     * 手数料コース変更申込情報メッセージデータの配列を作成する。<BR>
     * @@param l_commissionCourseRegists - 委託手数料コース変更申込オブジェクトの配列
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 41511E740145
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) throws WEB3BaseException;
    
    /**
     * (create株式手数料コース変更申込情報)<BR>
     * 委託手数料コース変更申込オブジェクトの配列より、<BR>
     * 上場株式，店頭株式の手数料コース変更申込情報メッセージデータの配列<BR>
     * を作成する。<BR>
     * @@param l_commissionCourseRegists - 委託手数料コース変更申込オブジェクトの配列
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 41511E740147
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createEquityCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) throws WEB3BaseException;
}
@
