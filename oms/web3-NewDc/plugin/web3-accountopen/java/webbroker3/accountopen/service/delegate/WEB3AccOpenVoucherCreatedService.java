head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[�쐬�T�[�r�X(WEB3AccOpenVoucherCreatedService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 ���w�� (���u) �V�K�쐬
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.common.WEB3BaseException;

/**
 * (�����J�ݓ`�[�쐬�T�[�r�X)<BR>
 * �����J�ݓ`�[�쐬�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author ���w��
 * @@version 1.0 
 */
public interface WEB3AccOpenVoucherCreatedService extends Service 
{
    
    /**
     * (create�����J�ݓ`�[)<BR>
     * �w��̌����J�݌����q�̏����A�����J�ݓ`�[���쐬����B<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 419C58240293
     */
    public String[] createAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException;
    
    /**
     * (delete�����J�ݓ`�[)<BR>
     * �w��̌����J�݌����q�Ɋ֘A����A�����J�ݓ`�[���폜����B<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 419C582402A3
     */
    public String[] deleteAccOpenVoucher(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException;
    
    /**
     * (get�ύX�s���ڈꗗ)<BR>
     * �쐬�ς̓`�[�ɂ��āA�m��ς̍��ڂ�ύX�����Ȃ����߂̍��ږ��ꗗ��<BR>
     * �擾����B<BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 419C582402A5
     */
    public String[] getChangedImpossibleItemList(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) throws WEB3BaseException;
}
@
