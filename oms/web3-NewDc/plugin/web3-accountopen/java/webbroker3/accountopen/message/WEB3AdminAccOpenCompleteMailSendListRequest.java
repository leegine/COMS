head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.08.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g(WEB3AdminAccOpenCompleteMailSendListRequest.java)
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
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�݊������[�����M�ꗗ���N�G�X�g<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendListRequest extends WEB3GenRequest
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_completeMailSendList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081609L;

    /**
     * (�\���s��)<BR>
     * �\���s��<BR>
     */
    public String dispSize;

    /**
     * @@roseuid 41B45E7502EE
     */
    public WEB3AdminAccOpenCompleteMailSendListRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenCompleteMailSendListResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�\���s���`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01323<BR>
     * �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01324<BR>
     * �@@�Q�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01325<BR>
     * @@throws WEB3BaseException
     * @@roseuid 418EF71903A8
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\���s���`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (dispSize == null || "".equals(dispSize.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01323,
                getClass().getName() + STR_METHOD_NAME,
                "�\���s�������͂̏ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //�Q�|�Q�j�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(dispSize))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01324,
                getClass().getName() + STR_METHOD_NAME,
                "�\���s�������ȊO�̕������܂܂��ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //�Q�|�R)�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        double l_dblDispSize = Double.valueOf(dispSize).doubleValue();       
        if (l_dblDispSize < 0)
        {            
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01325,
                getClass().getName() + STR_METHOD_NAME,
                "�}�C�i�X�l�̏ꍇ�A��O���X���[����");
                
            log.debug("�����J��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
