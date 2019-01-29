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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :RAC Context �T�[�r�X�N���X(WEB3DescendRacCtxService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.util.rac.*;
import webbroker3.util.*;

/**
 * RAC Context �T�[�r�X
 *
 * @@author ��
 * @@version 1.0
 */
public class WEB3DescendRacCtxServiceImpl
    implements WEB3DescendRacCtxService
{

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility m_log =
        WEB3LogUtility.getInstance(WEB3DescendRacCtxServiceImpl.class);

    /**
     * ���菈��RAC Context��ݒ肷��
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
     * ���菈��RAC Context���N���A����
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
