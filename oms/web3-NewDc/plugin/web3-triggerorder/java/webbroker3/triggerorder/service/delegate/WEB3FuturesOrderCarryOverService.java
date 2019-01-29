head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����J�z�T�[�r�X�C���^�t�F�C�X(WEB3FuturesOrderCarryOverService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/19 䈋� (���u) �V�K�쐬
Revesion History : 2007/06/28 ���� (���u) ���f��No.671
Revision History : 2007/07/12 ��іQ(���u) ���f��No.775
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�敨�����J�z�T�[�r�X)<BR>
 * �����w���敨�����J�z�T�[�r�X�C���^�t�F�C�X<BR>
 */
public interface WEB3FuturesOrderCarryOverService extends WEB3IfoOrderCarryOverMainService 
{
    
    /**
     * �敨�����J�z�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�T�[�r�X�j�敨�����J�z�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A99EC50289
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
    
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
