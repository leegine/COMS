head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecSendMailService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�m�F���[�����M�T�[�r�X(WEB3SrvRegiExecSendMailService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;


/**
 * (�T�[�r�X���p�m�F���[�����M�T�[�r�X)<BR>
 * @@author ���o��
 * @@version 1.0
 *  
 * �T�[�r�X���p�m�F���[�����M�T�[�r�X�C���^�[�t�F�C�X<BR>
 */
public interface WEB3SrvRegiExecSendMailService extends Service 
{
    
    /**
     * �T�[�r�X���p�\���m�F���[�����M�������s���B<BR>
     * @@param l_serviceRegist - (�T�[�r�X�\���o�^)<BR>
     * �T�[�r�X�\���o�^�I�u�W�F�N�g<BR>
     * @@roseuid 414933E6034E
     */
    public void sendMailProcess(WEB3GentradeSrvRegiApplication l_serviceRegist) throws WEB3BaseException; 
}
@
