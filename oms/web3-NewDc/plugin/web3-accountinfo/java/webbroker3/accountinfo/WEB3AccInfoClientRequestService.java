head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���N���C�A���g���N�G�X�g�T�[�r�X(WEB3AccInfoClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;


/**
 * (���q�l���N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * ���q�l���N���C�A���g���N�G�X�g�T�[�r�X�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoClientRequestService extends WEB3ClientRequestService 
{
    
    /**
     * @@roseuid 418F38510157
     */
    public WEB3AccInfoClientRequestService() 
    {
     
    }
    
    /**
     * (get�ڋq)<BR>
     * ���O�C���Z�L�����e�B�T�[�r�X���ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B<BR>
     * �Q�j�@@�����h�c�ɊY������ڋq�I�u�W�F�N�g��ԋp����B<BR>
     * @@return MainAccount
     * @@roseuid 413BFDA9032A
     */
    public MainAccount getMainAccount() throws WEB3SystemLayerException
    {
        return super.getMainAccount();
    }
    
    /**
     * �iget�⏕�����j
     * @@return
     * @@throws WEB3SystemLayerException
     */
    public SubAccount getSubAccount()
        throws WEB3SystemLayerException
    {
        return super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    }
    
}
@
