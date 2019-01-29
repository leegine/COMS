head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金計算サービスインターフェース(WEB3IfoDepositCalcService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/11/08 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * 証拠金計算サービスインターフェース
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoDepositCalcService extends Service
{

    /**
     * (get証拠金計算)<BR>
     * 
     * 証拠金計算を生成し、返却する。<BR>
     * 
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト。
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get証拠金計算)<BR>
     * 
     * 今回の注文が反映された証拠金計算を生成し、返却する。<BR>
     * （新規建余力チェック時に使用）<BR>
     * 
     * @@param l_subAccount - (補助口座) 補助口座オブジェクト。
     * @@param l_ifoNewOrderSpec - 先物OP現注文内容。
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
        throws WEB3SystemLayerException;

    /**
     * (create証拠金計算条件)<BR>
     * <BR>
     * 証拠金計算条件を作成する。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalcCondition
     */
    public WEB3IfoDepositCalcCondition createIfoDepositCalcCondition(WEB3GentradeSubAccount l_subAccount);

}@
