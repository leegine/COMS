head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�������tUnitService (WEB3RuitoOrderAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
/**
 * (�ݓ�������tUnitService)<BR>
 * �ݓ�������t�P���T�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * ������t�P�����Ƃ̎�t���������{����B<BR>
 */
public interface WEB3RuitoOrderAcceptUnitService extends Service
{
    /**
     * �ݓ�������t���������������Ȃ��B<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F1DC0147
     */
    public void notifyOrderAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException;
    /**
     * �ݓ�������t���s�����������Ȃ��B<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F1DC0166
     */
    public void notifyOrderAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException;
}
@
