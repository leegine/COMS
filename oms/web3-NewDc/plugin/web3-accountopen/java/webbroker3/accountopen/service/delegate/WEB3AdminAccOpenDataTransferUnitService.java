head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設データ移管UnitService(WEB3AdminAccOpenDataTransferUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 孟亞南(中訊) 新規作成 モデル 181  187
Revision History : 2009/08/26 孟亞南(中訊) モデル 190
Revision History : 2009/08/31 武波(中訊) モデル 203
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.common.WEB3BaseException;

/**
 * (管理者口座開設データ移管UnitService)<BR>
 * 管理者口座開設データ移管UnitServiceインタフェース<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public interface WEB3AdminAccOpenDataTransferUnitService extends Service
{
    /**
     * 口座開設移管の処理を行う。<BR>
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A8278DF033C
     */
    public void process(WEB3AccOpenExpAccountOpen l_expAccountOpen) throws WEB3BaseException;
}
@
