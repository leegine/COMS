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
filename	WEB3IfoCloseNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����ʒmUnitService(WEB3IfoCloseNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/14 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * (�敨OP�����ʒmUnitService)<BR>
 * �敨OP�����ʒm�P���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * �P�����Ƃ̎����ʒm���������{����B<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public interface WEB3IfoCloseNotifyUnitService extends Service 
{
    
    /**
     * (notify����)<BR>
     * �������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�����ʒm�jnotify�����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_dblExecutionQuantity - ��萔��
     * @@param l_strCloseReasonCode - �������R�R�[�h
     * @@throws WEB3BaseException
     * @@param l_strCloseNotifyType - �����ʒm�敪<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F�������<BR>
     * @@roseuid 408C9931026A
     */
    public String notifyClose(OrderUnit l_orderUnit, double l_dblExecutionQuantity, String l_strCloseReasonCode, String l_strCloseNotifyType) throws WEB3BaseException;
}
@
