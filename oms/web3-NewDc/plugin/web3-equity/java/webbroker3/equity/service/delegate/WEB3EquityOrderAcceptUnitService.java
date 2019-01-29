head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������t�ꌏ�T�[�r�X(WEB3EquityOrderAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 羐� (���u) �V�K�쐬
                   2004/10/22�@@���C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;

/**
 * �i����������t�ꌏ�T�[�r�X�̃C���^�t�F�[�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3EquityOrderAcceptUnitService extends Service
{

    /**
     * (notify������t)<BR>
     * �y����������t�L���[�e�[�u���z������t�̃L���[�f�[�^�ꌏ�ɑ΂���<BR>
     * �������s���B<BR>
     * �i������t���� == �i"������t����"�A"�G���["�j�̏ꍇ�j<BR>
     * @@param l_orderAcceptQueParams - ����������t�L���[Params�B
     * @@roseuid 4100EB5B0323
     */
    public void notifyOrderAccept(HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams)
        throws WEB3BaseException;
    
    /**
     * (notify��t���ԊO)<BR>
     * �y����������t�L���[�e�[�u���z������t�̃L���[�f�[�^�ꌏ�ɑ΂��鏈�����s���B<BR>
     * �i������t���� == "�O���t���ԊO�G���["�̏ꍇ�j
     * @@param l_params - (����������t�L���[Params)<BR>
     * ����������t�L���[Params�B
     * @@throws WEB3BaseException
     */
    public void notifyOrderAcceptOvertime(
        HostEqtypeOrderAcceptParams l_params)
        throws WEB3BaseException;
}
@
