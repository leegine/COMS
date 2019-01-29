head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3DescendRacCtxServiceImpl.java;


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

import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.util.rac.*;
import webbroker3.util.*;

/**
 * RAC Context サービス
 *
 * @@author 劉
 * @@version 1.0
 */
public class WEB3DescendRacCtxServiceImpl
    implements WEB3DescendRacCtxService
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility m_log =
        WEB3LogUtility.getInstance(WEB3DescendRacCtxServiceImpl.class);

    /**
     * 下り処理RAC Contextを設定する
     */
    public void setAccountIdCtx(long accountId)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "MPDS_CTX_NAME",
            ContextDrivenMultiPoolDataSource.CTX_NAME_IN_SINGLE_CONTEXT);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "MPDS_KEY_VALUE",
            new Long(accountId));
        if (m_log.ison())
        {
            m_log.debug("set descend process rac ctx >>" + "MPDS_CTX_NAME=" +
                        ContextDrivenMultiPoolDataSource.CTX_NAME_IN_SINGLE_CONTEXT + "," +
                        "MPDS_KEY_VALUE=" + accountId);
        }

    }

    /**
     * 下り処理RAC Contextをクリアする
     */
    public void clearAccountIdCtx()
    {
        ThreadLocalSystemAttributesRegistry.setAttribute("MPDS_CTX_NAME", null);
        ThreadLocalSystemAttributesRegistry.setAttribute("MPDS_KEY_VALUE", null);
        if (m_log.ison())
        {
            m_log.debug("clear descend process rac ctx.");
        }
    }

}
@
