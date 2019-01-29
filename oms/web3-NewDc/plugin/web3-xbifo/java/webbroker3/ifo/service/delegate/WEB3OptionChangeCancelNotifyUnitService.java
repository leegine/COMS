head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeCancelNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP��������ʒmUnitService(WEB3OptionChangeCancelNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;

/**
 * (OP��������ʒmUnitService)<BR>
 * �����w���I�v�V������������ʒmUnitService�C���^�t�F�C�X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public interface WEB3OptionChangeCancelNotifyUnitService extends Service 
{
    
    /**
     * (notify����)<BR>
     * �����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP��������ʒm�jnotify�����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g
     * @@roseuid 4084C2EC022B
     */
    public void notifyChange(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException;
    
    /**
     * (notify���)<BR>
     * ����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP��������ʒm�jnotify����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g
     * @@roseuid 4084C3010102
     */
    public String notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException;
}
@
