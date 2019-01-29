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
filename	WEB3FuturesChangeCancelNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������ʒmUnitService�C���^�t�F�C�X(WEB3FuturesChangeCancelNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;

/**
 * (�敨��������ʒmUnitService)<BR>
 * �����w���敨��������ʒmUnitService�C���^�t�F�C�X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public interface WEB3FuturesChangeCancelNotifyUnitService extends Service    
{
    
    /**
     * (notify����)<BR>
     * �����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨��������ʒm�jnotify�����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g<BR>
     * @@roseuid 40A8A00A0360
     */
    public void notifyChange(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException, DataQueryException, DataNetworkException, NotFoundException;
    
    /**
     * (notify���)<BR>
     * ����ʒm�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨��������ʒm�jnotify����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_ifoChangeCancelNotifyInterceptor - �敨OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g<BR>
     * @@roseuid 40A8A00A0380
     */
    public String notifyCancel(OrderUnit l_orderUnit, WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyInterceptor) throws WEB3BaseException;
}
@
