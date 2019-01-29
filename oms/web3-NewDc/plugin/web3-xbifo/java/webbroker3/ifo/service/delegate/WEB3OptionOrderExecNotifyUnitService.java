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
filename	WEB3OptionOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����o���ʒm�P���T�[�r�X����(WEB3OptionOrderExecNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * (OP�o���ʒmUnitService)<BR>
 * <BR>
 * �����w���I�v�V�����o���ʒm�P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * �P�����Ƃ̏o���ʒm���������{����B<BR>
 *                                                                     
 * @@author 羉s
 * @@version 1.0
 */
public interface WEB3OptionOrderExecNotifyUnitService extends Service 
{
    
    /**
     * (notify���)<BR>
     * <BR>
     * ��菈�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�o���ʒm�jnotify���v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_tsExecDate - ������
     * @@param l_dblExecQuantity - ��萔��
     * @@param l_dblExecPrice - ���P��
     * @@param l_strExecutedNotifyDivision - �o���ʒm�敪
     * @@roseuid 4087A9AB0335
     */
    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecutedNotifyDivision) throws WEB3BaseException; 
    
    /**
     * (notify�����)<BR>
     * <BR>
     * �o���ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�o���ʒm�jnotify������v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_tsExecDate - ������
     * @@param l_dblExecQuantity - ��萔��
     * @@param l_dblExecPrice - ���P��
     * @@roseuid 4087A9AB033B
     */
    public void notifyExecuteCancel(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice) throws WEB3BaseException;
}
@
