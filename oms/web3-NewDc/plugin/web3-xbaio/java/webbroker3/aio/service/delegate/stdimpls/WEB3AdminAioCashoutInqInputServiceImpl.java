head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇�����̓T�[�r�X�����N���X(WEB3AdminAioCashoutInqInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ��O�� (���u) �V�K�쐬    
                   2004/10/26 ���� (���u) ���r���[   
                   2006/09/04 �Ԑi (���u) ����̍X ���f��No.628                 
*/

package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.aio.message.WEB3AdminAioCashoutInqInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashoutInqInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���\���⍇�����̓T�[�r�XImpl)<BR>
 * (�o���\���⍇�����̓T�[�r�X�����N���X)
 *
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashoutInqInputServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioCashoutInqInputService 
{   
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqInputServiceImpl.class);
        
    /**
     * �o���\���⍇�����̓T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\���⍇�����́j���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41010350034B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3AdminAioCashoutInqInputResponse l_adminAioCashoutInqInputResponse = null;        
        
        //1.1 �Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
               
        //1.2 ���X�R�[�h���擾����B
        String l_strBranchCode = l_web3Administrator.getBranchCode();
        
        //1.3 �،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.4 �w�肵���،���Ђ��o�^���Ă���ʉ݂̒ʉ݃R�[�h�����ׂĎ擾����B 
        String[] l_strCurrencyCodes = WEB3GentradeCurrency.getCurrencyCodeList(l_strInstitutionCode);     
        
        //1.5 ���X�|���X�f�[�^�𐶐�����B
        l_adminAioCashoutInqInputResponse = (WEB3AdminAioCashoutInqInputResponse) 
            l_request.createResponse();
        
        //1.6  �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���A�v���p�e�B���Z�b�g����B
        //���X�|���X.���X�R�[�h = �Ǘ���.get���X�R�[�h()�̖߂�l
        //���X�|���X.�O�݃R�[�h�ꗗ = get�ʉ݃R�[�h�ꗗ()�̖߂�l
        l_adminAioCashoutInqInputResponse.branchCode = l_strBranchCode;
        l_adminAioCashoutInqInputResponse.foreignCurrencyCodeList = l_strCurrencyCodes;
        
        log.debug("���X�|���X.���X�R�[�h = " + 
                l_adminAioCashoutInqInputResponse.branchCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqInputResponse;
    }
}
@
