head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�����������T�[�r�X(WEB3ToSuccOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;


/**
 * (�A�����������T�[�r�X)<BR>
 * �A�����������T�[�r�X�C���^�t�F�[�X�B<BR>
 * <BR>
 * @@author ������ <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToSuccOrderService extends Service 
{
    
    /**
     * (execute�A����������)<BR>
     * �A�����������������s���B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_lngSubOrderId - (�\�񒍕��̒���ID)<BR>
     * ����ID�B<BR>
     * �i�����Ώۂ̗\�񒍕��̒���ID�j<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B
     * @@throws WEB3BaseException
     * @@roseuid 4328F6B60346
     */
    public void executeSuccOrder(SubAccount l_subAccount, long l_lngSubOrderId, ProductTypeEnum l_productType) throws WEB3BaseException;
}
@
