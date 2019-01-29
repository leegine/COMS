head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l���������T�[�r�X(WEB3ToStopOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;


/**
 * (�t�w�l���������T�[�r�X)<BR>
 * �t�w�l���������T�[�r�X�C���^�[�t�F�C�X<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3ToStopOrderService extends Service 
{
    
    /**
     * (execute�t�w�l��������)<BR>
     * �t�w�l���������������s���B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�����Ώۂ̒����̒���ID�j<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C78F00343
     */
    public void executeStopOrder(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType) throws WEB3BaseException;
}
@
