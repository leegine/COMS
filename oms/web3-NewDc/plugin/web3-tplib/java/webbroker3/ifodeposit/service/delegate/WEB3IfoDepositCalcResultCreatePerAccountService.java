head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultCreatePerAccountService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositCalcResultCreatePerAccountServiceクラス(WEB3IfoDepositCalcResultCreatePerAccountService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/24 孫(FLJ) 新規作成
*/

package webbroker3.ifodeposit.service.delegate;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (顧客証拠金計算結果作成サービス)<BR>
 * 顧客証拠金計算結果作成サービスインタフェース。<BR>
 * 
 * 
 * @@author 孫(FLJ)
 * @@version 1.0
 */
public interface WEB3IfoDepositCalcResultCreatePerAccountService extends Service
{
    /**
     * 顧客証拠金計算結果作成サービスを実施する。
     * 
     * @@param l_request
     *  - (リクエストデータ)
     * リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public IfoDepositCalcResultParams createIfoDepositCalcResult(WEB3GentradeSubAccount l_subAccount) throws WEB3SystemLayerException;

}
@
