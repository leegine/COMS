head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒmUnitService(WEB3IfoExecuteEndNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 䈋� (���u) �V�K�쐬
Revesion History : 2007/06/08 ��іQ (���u) ���f��No.694
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (�敨OP�o���I���ʒmUnitService)<BR>
 * �敨OP�o���I���ʒmUnitService�C���^�[�t�F�C�X<BR>
 */
public interface WEB3IfoExecuteEndNotifyUnitService extends Service 
{
    
    /**
     * (notify�o���I��)<BR>
     * �������̏o���I�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�o���I���ʒm�jnotify�o���I���v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_strOrderExecutionEndType -(�o���I���敪)<BR>
     * �o���I���敪<BR>
     * @@roseuid 408C94210085
     */
    public void notifyExecuteEnd(OrderUnit l_orderUnit, String l_strOrderExecutionEndType)
        throws WEB3BaseException;
    
}
@
