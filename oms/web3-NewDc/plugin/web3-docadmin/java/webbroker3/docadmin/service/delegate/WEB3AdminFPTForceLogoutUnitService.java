head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�X(WEB3AdminFPTForceLogoutUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�X
 * 
 * @@author ��
 * @@version 1.0
 */
public interface WEB3AdminFPTForceLogoutUnitService extends Service
{
    
    /**
     * this.���O�C���Z�b�V����Row�̃Z�b�V�����𖳌��ɂ���B
     * 
     * �V�[�P���X�} 
     * �u�i�Ǘ��� ���ʖ����� �������O�A�E�g�ꌏ�T�[�r�X�jlogout�v�Q�ƁB
     * @@param l_loginSessionRow - ���O�C���Z�b�V����Row
     * @@roseuid 47DB263C00BC
     */
    public void logout(LoginSessionRow l_loginSessionRow) throws WEB3BaseException;
}
@
