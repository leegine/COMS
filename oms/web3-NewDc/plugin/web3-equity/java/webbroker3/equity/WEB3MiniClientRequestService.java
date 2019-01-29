head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MiniClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����~�j�����N���C�A���g���N�G�X�g�T�[�r�X(WEB3MiniClientRequestService)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/10 �����a��(SRA) �V�K�쐬
                      2004/12/29 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * �i�����~�j�����N���C�A���g���N�G�X�g�T�[�r�X�j�B<BR>
 * <BR>
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X(�����~�j�����p)<BR>
 * <BR>
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X�B
 * @@author �����a��(SRA)
 * @@version 1.0
 */
public class WEB3MiniClientRequestService extends WEB3ClientRequestService
{
    
    public WEB3MiniClientRequestService()
    {
    }
    
    /**
     * �iget�⏕�����j�B<BR>
     * <BR>
     * �igetSubAccount�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B<BR>
     * <BR>
     * �P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B<BR>
     * <BR>
     * �Q�j�@@�A�J�E���g�}�l�[�W��.getSubAccount( )�ɂāA<BR>
     * �@@�@@�@@�Y���ڋq�̊����~�j�����p�⏕�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[getSubAccount����]<BR>
     * �@@SubAccountTypeEnum.�����������
     * @@return �⏕����
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3SystemLayerException
    {
        return (WEB3GentradeSubAccount)super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    }
}
@
