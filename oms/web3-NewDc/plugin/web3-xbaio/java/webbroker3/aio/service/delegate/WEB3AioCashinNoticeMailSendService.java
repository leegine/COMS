head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinNoticeMailSendService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�����[�����M�T�[�r�X�C���^�[�t�F�C�X(WEB3AioCashinNoticeMailSendService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ���E (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.DepositInformParams;
import webbroker3.common.WEB3BaseException;


/**
 * (�����A�����[�����M�T�[�r�X)<BR>
 * �����A�����[�����M�T�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public interface WEB3AioCashinNoticeMailSendService extends Service 
{
    
    /**
     * (create���[��)<BR>
     * �����A�����[���s��DB�ɓo�^����B
     * @@param l_depositInformParams - (�����A���s)<BR>
     * �����A���s�I�u�W�F�N�g
     * @@roseuid 40FE5E9303DF
     */
    public void createMail(DepositInformParams l_depositInformParams, String l_strEmailAddress)
        throws WEB3BaseException;;
}@
