head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3DescendRacCtxService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :RAC Context サービスクラス(WEB3DescendRacCtxService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.boot.*;

/**
 * RAC Context サービス
 *
 * @@author 劉
 * @@version 1.0
 */
public interface WEB3DescendRacCtxService
    extends Service
{

    /**
     * 下り処理RAC Contextを設定する
     */
    public void setAccountIdCtx(long accountId);

    /**
     * 下り処理RAC Contextをクリアする
     */
    public void clearAccountIdCtx();

}
@
