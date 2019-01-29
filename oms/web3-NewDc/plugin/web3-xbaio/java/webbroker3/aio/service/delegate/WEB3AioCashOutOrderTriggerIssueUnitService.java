head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashOutOrderTriggerIssueUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金注文トリガー発行UnitService(WEB3AioCashOutOrderTriggerIssueUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/09 何文敏 (中訊) 新規作成 仕様変更モデルNo.720
Revision History : 2007/04/10 何文敏 (中訊) 仕様変更モデルNo.721
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * (出金注文トリガー発行UnitService)<BR>
 * 出金注文トリガー発行UnitServiceインターフェイス<BR>
 * <BR>
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public interface WEB3AioCashOutOrderTriggerIssueUnitService extends Service
{
    /**
     * 出金注文トリガー発行処理を行う。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@throws WEB3BaseException
     */
    public void execute(String l_strInstitutionCode) throws WEB3BaseException;
}
@
