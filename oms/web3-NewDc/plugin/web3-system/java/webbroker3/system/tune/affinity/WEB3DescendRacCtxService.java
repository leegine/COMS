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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :RAC Context �T�[�r�X�N���X(WEB3DescendRacCtxService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.boot.*;

/**
 * RAC Context �T�[�r�X
 *
 * @@author ��
 * @@version 1.0
 */
public interface WEB3DescendRacCtxService
    extends Service
{

    /**
     * ���菈��RAC Context��ݒ肷��
     */
    public void setAccountIdCtx(long accountId);

    /**
     * ���菈��RAC Context���N���A����
     */
    public void clearAccountIdCtx();

}
@
