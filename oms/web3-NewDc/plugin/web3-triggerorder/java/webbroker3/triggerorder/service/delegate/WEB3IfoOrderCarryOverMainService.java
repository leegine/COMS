head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����J�z���C���T�[�r�X(WEB3IfoOrderCarryOverMainService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/21 ��іQ(���u) �V�K�쐬 ���f��No.669
Revision History : 2007/07/11 ��іQ(���u) ���f��No.774
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�敨OP�����J�z���C���T�[�r�X)<BR>
 * �敨OP�����J�z���C���T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public interface WEB3IfoOrderCarryOverMainService extends WEB3BackBusinessService
{
    /**
     * �敨OP�����J�z���������{����B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;

    /**
     * (create��������)<BR>
     * �����������쐬����B<BR>
     * @@param l_mainAccount  - (�ڋq)<BR>
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
        String l_strCarryoverProcessType) throws WEB3BaseException;
}
@
