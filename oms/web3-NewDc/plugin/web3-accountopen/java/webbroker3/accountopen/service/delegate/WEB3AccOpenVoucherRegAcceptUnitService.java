head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[�o�^��tUnitService(WEB3AccOpenVoucherRegAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (�����J�ݓ`�[�o�^��tUnitService)<BR>
 * �����J�ݓ`�[�o�^��t�P���T�[�r�X�C���^�t�F�C�X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3AccOpenVoucherRegAcceptUnitService extends Service 
{
    
    /**
     * (notify�`�[�o�^��t)<BR>
     * �����J�ݓ`�[�o�^��t�P�����������{���A�������ʁi�����ρ^�G���[�j��<BR>
     * �ԋp����B<BR>
     * @@param l_accOpenAcceptParams - �����J�ݓ`�[�o�^��t�L���[<BR>
     * <BR>
     * ���@@�����J�ݓ`�[�o�^��t�L���[Params�N���X�́ADDL��莩����������B<BR>
     * 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41B169B402DE
     */
    public String notifyVoucherRegAccept(HostAccOpenAcceptParams l_accOpenAcceptParams) throws WEB3BaseException;
}
@
