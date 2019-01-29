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
filename	WEB3EquityExecutedMailSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��胁�[�����M�T�[�r�X�C���^�t�F�[�X(WEB3EquityExecutedMailSenderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 䈋� (���u) �V�K�쐬
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * �i��胁�[�����M�T�[�r�X�C���^�t�F�[�X�j�B
 * @@version 1.0
 */
public interface WEB3EquityExecutedMailSenderService extends Service 
{
    
    /**
     * ������胁�[�����M�������s���B
     * @@param l_orderUnit - (�����P��)<BR>
     * ���������P�ʃI�u�W�F�N�g
     * @@param l_strReasonCode - �������R�R�[�h�B<BR>
     * ���̏ꍇ��null���w�肷��B
     * @@roseuid 4121548E0234
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode) throws WEB3BaseException;
    
	/**
	 * ������胁�[�����M�������s���B
	 * @@param l_orderUnit - (�����P��)<BR>
	 * ���������P�ʃI�u�W�F�N�g
	 * @@param l_strReasonCode - �������R�R�[�h�B<BR>
	 * ���̏ꍇ��null���w�肷��B
	 * @@param l_blnConfirmAlreadyIns - (�o�^�σ��R�[�h�L���m�F�t���O)<BR>
	 * �o�^�σ��R�[�h�L���m�F�t���O�B<BR>
	 * @@roseuid 4121548E0234
	 */
	public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode, boolean l_blnConfirmAlreadyIns) throws WEB3BaseException;
    
    /**
     * �����̒����P�ʂɊY�������胁�[���i�܂��͎������[���j�𖳌��ɂ���B
     * @@param l_orderUnit - �����P�ʁB
     * @@roseuid 4121548E0253
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
