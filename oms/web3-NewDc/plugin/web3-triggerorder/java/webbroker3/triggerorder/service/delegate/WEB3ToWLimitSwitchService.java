head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitSwitchService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : W�w�l�����ؑ֏����T�[�r�X(WEB3ToWLimitSwitchService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;


/**
 * (W�w�l�����ؑ֏����T�[�r�X)<BR>
 * W�w�l�����ؑ֏����T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public interface WEB3ToWLimitSwitchService extends Service
{
    
    /**
     * (executeW�w�l�����ؑ�)<BR>
     * W�w�l�����ؑ֏������s���B
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�֑ؑΏۂ̒����̒���ID�j<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923CC2024E
     */
    public void executeWLimitSwitch(
        SubAccount l_subAccount, 
        long l_lngOrderId, 
        ProductTypeEnum l_productType) throws WEB3BaseException;
}
@
