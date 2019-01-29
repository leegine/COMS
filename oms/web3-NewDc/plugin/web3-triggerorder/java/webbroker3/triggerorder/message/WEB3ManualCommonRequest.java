head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ManualCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �蓮�������ʃ��N�G�X�g(WEB3ManualCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.triggerorder.define.WEB3ToProductTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�蓮�������ʃ��N�G�X�g)<BR>
 * �蓮�������ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3ManualCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ManualCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "manual_common";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602162240L;
    
    /**
     * (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * <BR>
     * 1�F�@@����<BR>
     * 6�F�@@�敨�I�v�V����<BR>
     */
    public String productType;
    
    /**
     * (�����������)<BR>
     * �����������<BR>
     * <BR>
     * 1�F�@@�A������<BR>
     * 2�F�@@OCO����<BR>
     * 3�F�@@IFD����<BR>
     * 4�F�@@�t�w�l����<BR>
     * 5�F�@@W�w�l����<BR>
     */
    public String triggerOrderType;
    
    /**
     * (����ID)<BR>
     * ����ID�̔z��<BR>
     * �@@���Ǘ��҃��N�G�X�g�̏ꍇ�͂P�`������<BR>
     * �@@�����[�U�[���N�G�X�g�̏ꍇ��1��<BR>
     */
    public String[] orderId;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     * <BR>
     * ���Ǘ��҂͕K�{<BR>
     */
    public String[] branchCode = null;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F4889200AB
     */
    public WEB3ManualCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�����^�C�v�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�����^�C�v == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����^�C�v��null�v�̗�O���X���[����B<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02394<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�����^�C�v���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����^�C�v������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h6�F�w���敨�E�I�v�V�����h<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02395<BR>
     * <BR>
     * �Q�j�@@����������ʃ`�F�b�N<BR>
     * �@@�Q�|�P�jthis.����������� == nul�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u����������ʂ�null�v�̗�O���X���[����B<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02396<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.����������ʂ��ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u����������ʂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�A�������h<BR>
     * �@@�@@�@@�@@�E�h2�FOCO�����h<BR>
     * �@@�@@�@@�@@�E�h3�FIFD�����h<BR>
     * �@@�@@�@@�@@�E�h4�F�t�w�l�h<BR>
     * �@@�@@�@@�@@�E�h5�FW�w�l�h<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_02397<BR>
     * <BR>
     * �R�j�@@����ID�`�F�b�N<BR>
     * �@@�R�|�P�jthis.����ID�̗v�f�� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_00600<BR>
     * <BR>
     * �@@�R�|�Q�jthis.����ID�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�@@�E����ID[���Ԗڂ̗v�f] == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B<BR>
     * class :WEB3BusinessLayerException<BR>
     * tag   :BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E733C80275
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����^�C�v�`�F�b�N
        //�P�|�P�jthis.�����^�C�v == null�̏ꍇ�A
        //�@@�@@�@@�@@�u�����^�C�v��null�v�̗�O���X���[����B
        if (this.productType == null)
        {
            log.debug("�����^�C�v�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02394,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�C�v�����w��ł��B");
        }
        
        //�P�|�Q�jthis.�����^�C�v���ȉ��̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�u�����^�C�v������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E�h1�F�����h
        //�@@�@@�@@�@@�E�h6�F�w���敨�E�I�v�V�����h
        if (!(WEB3ToProductTypeDef.EQUITY.equals(this.productType)
            || WEB3ToProductTypeDef.FUTURE_OPTION.equals(this.productType)))
        {
            log.debug("�����^�C�v������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02395,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����^�C�v������`�̒l�ł��B");
        }
        
        //�Q�j�@@����������ʃ`�F�b�N
        //�Q�|�P�jthis.����������� == nul�̏ꍇ�A
        //�@@�@@�@@�@@�u����������ʂ�null�v�̗�O���X���[����B
        if (this.triggerOrderType == null)
        {
            log.debug("����������ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02396,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ����w��ł��B");
        }
        
        //�Q�|�Q�jthis.����������ʂ��ȉ��̒l�ȊO�̏ꍇ�A
        //�@@�@@�@@�@@�u����������ʂ�����`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�E�h1�F�A�������h
        //�@@�@@�@@�E�h2�FOCO�����h
        //�@@�@@�@@�E�h3�FIFD�����h
        //�@@�@@�@@�E�h4�F�t�w�l�h
        //�@@�@@�@@�E�h5�FW�w�l�h
        if (!(WEB3TriggerOrderTypeDef.SUCC.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.OCO.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.IFD.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.STOP.equals(this.triggerOrderType)
            || WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType)))
        {
            log.debug("����������ʂ�����`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02397,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����������ʂ�����`�̒l�ł��B");
        }
        
        //�R�j�@@����ID�`�F�b�N
        //�R�|�P�jthis.����ID�̗v�f�� == null�̏ꍇ�A
        //�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B
        if (this.orderId == null || this.orderId.length == 0)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        
        //�R�|�Q�jthis.����ID�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
        //�@@�@@�E����ID[���Ԗڂ̗v�f] == null�̏ꍇ�A
        //�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B
        int l_intLen = this.orderId.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (this.orderId[i] == null || this.orderId[i].length() == 0)
            {
                log.debug("����ID�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID�����w��ł��B");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createResponse�̎���)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return null;        
    }
}
@
