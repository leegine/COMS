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
filename	WEB3IfoExecutedMailSendService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP��胁�[�����M�T�[�r�X(WEB3IfoExecutedMailSendService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 ����� (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (�敨OP��胁�[�����M)<BR>
 * �敨OP��胁�[�����M�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3IfoExecutedMailSendService extends Service 
{
    
    /**
     * �敨OP��胁�[�����M�������s���B<BR>
     * <BR>
     * ����.�����P�ʂ𔻒肵�A�ȉ��̂����ꂩ�̃��[���f�[�^���쐬��<BR>
     * �敨OP��胁�[�����M�e�[�u���ɍs���쐬����B<BR>
     * <BR>
     * �P�j�@@���i�����j���[���f�[�^�쐬<BR>
     * �@@�������R�R�[�h��null�łȂ��ꍇ�Acreate�������[��()�ɂĎ������[�����쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����P�ʁF�@@�����P�ʃI�u�W�F�N�g<BR>
     * �@@�������R�R�[�h�F�@@�������R�R�[�h<BR>
     * <BR>
     * �@@�i�������R�R�[�h == null�j�̏ꍇ�Acreate��胁�[��()�ɂĖ�胁�[�����쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����P�ʁF�@@�����P�ʃI�u�W�F�N�g<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_strCloseReasonCode - �������R�R�[�h�B<BR>
     * ���̏ꍇ��null���w�肷��B<BR>
     * @@roseuid 408CC76800D3
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strCloseReasonCode) throws WEB3BaseException;
    
    /**
     * �����̒����P�ʂɊY�������胁�[���i�܂��́A�������[���j�𖳌��ɂ���B
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@roseuid 408CC76800D6
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
