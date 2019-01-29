head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݊������[�����M���N�G�X�g(WEB3AdminAccOpenCompleteMailSendRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���w�� �V�K�쐬
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ҍ����J�݊������[�����M���N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�݊������[�����M���N�G�X�g<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendRequest extends WEB3GenRequest
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendRequest.class);
    
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_completeMailSend";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081608L;

    /**
     * (���X�R�[�h)<BR>
     * �l�k���M�Ώۂ̕��X�R�[�h�z��<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �l�k���M�Ώۂ̌ڋq�R�[�h�z��<BR>
     */
    public String[] accountCode;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 41B45E750213
     */
    public WEB3AdminAccOpenCompleteMailSendRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenCompleteMailSendResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h[]�C�ڋq�R�[�h[]�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00835<BR>
     * �@@�P�|�Q�j�@@���X�R�[�h�z��ƌڋq�R�[�h�z��ŁA<BR>
     * �v�f��������Ă���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01322<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A5AAD50323
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h[]�C�ڋq�R�[�h[]�̃`�F�b�N
        //�P�|�P�j���X�R�[�h[]�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h[]�����͂̏ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //�P�|�P�j�ڋq�R�[�h[]�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accountCode == null || this.accountCode.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h[]�����͂̏ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //�P�|�Q)���X�R�[�h�z��ƌڋq�R�[�h�z��ŁA�v�f��������Ă���Η�O���X���[����B
        if (this.branchCode.length != this.accountCode.length)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01322,
                getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�z��ƌڋq�R�[�h�z��ŁA�v�f��������Ă���Η�O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}@
