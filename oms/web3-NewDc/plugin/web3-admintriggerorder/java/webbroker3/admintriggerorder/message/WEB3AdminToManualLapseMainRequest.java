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
filename	WEB3AdminToManualLapseMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g(WEB3AdminToManualLapseMainRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualLapseMainRequest extends WEB3BackRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToManualLapseMainRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_main";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603170900L;
    
    /**
     * (�،���ЃR�[�h)<BR>
     * �iinstitutionCode�j<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * (�X���b�hNo)<BR>
     * �ithreadNo�j<BR>
     * �X���b�hNo<BR>
     */
    public Long threadNo;
    
    /**
     * (From����ID)<BR>
     * �irangeFrom�j<BR>
     * From����ID<BR>
     */
    public Long rangeFrom;
    
    /**
     * (To����ID)<BR>
     * �irangeTo�j<BR>
     * To����ID<BR>
     */
    public Long rangeTo;
    
    /**
     * (�����Ώے�������)<BR>
     */
    public WEB3AdminToLapseTargetOrderCondition lapseTargetOrderCondition;
    
    /**
     * (�g���K�[�����Ǘ��ҁE�蓮�������C�����N�G�X�g)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 440BF3AA0037
     */
    public WEB3AdminToManualLapseMainRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�،���ЃR�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�u�،���ЃR�[�h�����w��ł��B�v�̗�O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_00827<BR>
     * <BR>
     * �Q�j�@@�X���b�hNo�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�X���b�hNo == null�̏ꍇ�A<BR>
     * �@@�@@�u�X���b�h�ԍ��̎w��Ȃ��B�v�̗�O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01974<BR>
     * <BR>
     * �R�j�@@From����ID�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.From����ID == null�̏ꍇ�A<BR>
     * �@@�@@�uFrom����ID�������́v�̗�O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_02421<BR>
     * <BR>
     * �S�j�@@To����ID�`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.To����ID == null�̏ꍇ�A<BR>
     * �@@�@@�uTo����ID�i���j�������́v�̗�O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_02422<BR>
     * <BR>
     * �T�j�@@�����Ώے��������`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.�����Ώے������� == null�̏ꍇ�A<BR>
     * �@@�@@�u�����Ώے��������������́v�̗�O���X���[����B<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_02420<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.�����Ώے�������.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 440C0370023B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�،���ЃR�[�h�`�F�b�N
        //�@@�P�|�P�j�@@this.�،���ЃR�[�h == null�̏ꍇ�A
        //�@@�@@�u�،���ЃR�[�h�����w��ł��B�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            log.debug("�،���ЃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }
        
        //�Q�j�@@�X���b�hNo�`�F�b�N
        //�@@�Q�|�P�j�@@this.�X���b�hNo == null�̏ꍇ�A
        //�@@�@@�u�X���b�h�ԍ��̎w��Ȃ��B�v�̗�O���X���[����B
        if (this.threadNo == null)
        {
            log.debug("�X���b�h�ԍ��̎w��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X���b�h�ԍ��̎w��Ȃ��B");
        }
        
        //�R�j�@@From����ID�`�F�b�N
        //�@@�R�|�P�j�@@this.From����ID == null�̏ꍇ�A
        //�@@�@@�uFrom����ID�������́v�̗�O���X���[����B
        if (this.rangeFrom == null)
        {
            log.debug("From����ID�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02421,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "From����ID�������͂ł��B");
        }
        
        //�S�j�@@To����ID�`�F�b�N
        //�@@�S�|�P�j�@@this.To����ID == null�̏ꍇ�A
        //�@@�@@�uTo����ID�i���j�������́v�̗�O���X���[����B
        if (this.rangeTo == null)
        {
            log.debug("To����ID�i���j�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02422,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "To����ID�i���j�������͂ł��B");
        }
        
        //�T�j�@@�����Ώے��������`�F�b�N
        //�T�|�P�j�@@this.�����Ώے������� == null�̏ꍇ�A
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
        
        //�T�|�Q�j�@@this.�����Ώے�������.validate()���R�[������B
        this.lapseTargetOrderCondition.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�蓮�������C�����X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AdminToManualLapseMainResponse(this);
    }
    
}
@
