head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AsynExecuteService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 非同期実行インターフェース(WEB3AsynExecuteServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/07 劉 新規作成
 */
package webbroker3.system.tune.threadpool;

import com.fitechlabs.xtrade.kernel.boot.*;

/**
 * 非同期実行インターフェース
 *
 * @@author 劉
 * @@version 1.0
 */
public interface WEB3AsynExecuteService
    extends Service
{

    public void execute(Runnable l_runnable);

}
@
