head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.33.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnitCreateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : CC�I�y���[�^�o�^���쐬�T�[�r�X(WEB3AdminMCCCOperatorRegistUnitCreateService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeTrader;

/**
 * (CC�I�y���[�^�o�^���쐬�T�[�r�X)<BR>
 * CC�I�y���[�^�o�^���쐬�T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3AdminMCCCOperatorRegistUnitCreateService extends Service 
{
    /**
     * (createCC�I�y���[�^�o�^���)<BR>
     * ���҃I�u�W�F�N�g���ACC�I�y���[�^�o�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * <BR>
     * @@param l_trader - (����)<BR>
     * �Ǘ��҃^�C�v<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit
     * @@roseuid 417F713B00CF
     */
    public WEB3AdminMCCCOperatorRegistUnit createCCOperatorRegistUnit(WEB3GentradeTrader l_trader) throws WEB3BaseException;
    
    /**
     * (createCC�I�y���[�^�o�^���)<BR>
     * ���҃I�u�W�F�N�g�̔z����ACC�I�y���[�^�o�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g�̔z����쐬����B<BR>
     * <BR>
     * <BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g�̔z��<BR>
     * 
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit[]
     * @@roseuid 417F713B00EE
     */
    public WEB3AdminMCCCOperatorRegistUnit[] createCCOperatorRegistUnit(WEB3GentradeTrader[] l_trader) throws WEB3BaseException;
}
@
