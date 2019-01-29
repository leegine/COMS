head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�����m�F���N�G�X�g(WEB3AdminToManualLapseConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�����m�F���N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�����m�F���N�G�X�g<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualLapseConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToManualLapseConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;
    
    /**
     * (�����Ώے�������)<BR>
     */
    public WEB3AdminToLapseTargetOrderCondition lapseTargetOrderCondition;
    
    /**
     * @@roseuid 44192EED007D
     */
    public WEB3AdminToManualLapseConfirmRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�����Ώے��������̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�����Ώے������� == null�̏ꍇ�A<BR>
     * �@@�@@�u�����Ώے��������������́v�̗�O���X���[����B<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02420<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�����Ώے�������.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44051D140399
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����Ώے��������̃`�F�b�N
        //�@@�P�|�P�j�@@this.�����Ώے������� == null�̏ꍇ�A
        //�@@�@@�u�����Ώے��������������́v�̗�O���X���[����B
        if (this.lapseTargetOrderCondition == null)
        {
            log.debug("�����Ώے��������������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����Ώے��������������͂ł��B");
        }
        
        //�P�|�Q�j�@@this.�����Ώے�������.validate()���R�[������B
        this.lapseTargetOrderCondition.validate();

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����m�F���X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToManualLapseConfirmResponse(this);
    }
}
@
