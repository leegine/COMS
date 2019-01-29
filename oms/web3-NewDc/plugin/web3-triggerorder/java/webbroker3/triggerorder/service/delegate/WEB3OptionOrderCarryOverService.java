head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����J�z�T�[�r�X(WEB3OptionOrderCarryOverService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/12 ���� (���u) �V�K�쐬
Revesion History : 2007/06/21 ���� (���u) ���f��670
Revision History : 2007/07/12 ��іQ(���u) ���f��No.775
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (OP�����J�z�T�[�r�X)<BR>
 * OP�����J�z�T�[�r�X�C���^�[�t�F�C�X<BR>
 *                                                                    
 * @@author ����
 * @@version 1.0
 */
public interface WEB3OptionOrderCarryOverService extends WEB3IfoOrderCarryOverMainService
{

    /**
     * OP�����J�z�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�T�[�r�X�j�I�v�V���������J�z�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 409B12C00159
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
    
    /**
     * (create��������)<BR>
     * �����������쐬����B<BR>
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * @@param l_strCarryoverProcessType - (�����J�z�����敪)<BR>
     * �����J�z�����敪<BR>
     * @@throws WEB3BaseException
     */
    public void createNextOrder(
        MainAccount l_mainAccount,
        String l_strFutureOptionDiv,
        String l_strCarryoverProcessType)
        throws WEB3BaseException;
}
@
