head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToDifferentTimeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������(WEB3AdminToDifferentTimeUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.admintriggerorder.define.WEB3AdminToDifferentTimeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (��������)<BR>
 * �������ԃN���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToDifferentTimeUnit extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToDifferentTimeUnit.class);

    
    /**
     * (��������From)<BR>
     * ��������From<BR>
     * <BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * 1�F�@@������M����<BR>
     * 2�F�@@�g���K�[�N������<BR>
     */
    public String differentTimeFrom;
    
    /**
     * (��������To)<BR>
     * ��������To<BR>
     * <BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * 2�F�@@�g���K�[�N������<BR>
     * 3�F�@@������������<BR>
     */
    public String differentTimeTo;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * (�b�P�ʂŎw��)<BR>
     */
    public String differentTime;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C70271
     */
    public WEB3AdminToDifferentTimeUnit() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�������ԃ`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�������ԁ�null�̏ꍇ�A<BR>
     * �@@�u�������Ԃ�null�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02360<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�������Ԃ��ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�u�������ԃG���[�v�̗�O���X���[����B<BR>
     * �@@�@@�E�������� �� ����<BR>
     * �@@�@@�E�������� <= 0<BR>
     * �@@�@@�E��������.length > 4<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02361<BR>
     * <BR>
     * �Q�j�@@��������From�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.��������From��null�̏ꍇ�A<BR>
     * �@@�u��������From��null�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02362<BR>
     * <BR>
     * �R�j�@@��������To�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.��������To��null�̏ꍇ�A<BR>
     * �@@�u��������To��null�v�̗�O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02363<BR>
     * <BR>
     * �S�j�@@��������From/To�������`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.��������From/To�����L�ȊO�̏ꍇ�A<BR>
     * �@@�u�������Ԑ������G���[�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�E��������From��"������M����"�A���A��������To��"�g���K�[�N������"<BR>
     * �@@�@@�E��������From��"������M����"�A���A��������To��"������������"<BR>
     * �@@�@@�E��������From��"�g���K�[�N������"�A���A��������To��"������������"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02364<BR>
     * @@roseuid 43DF0A8A03AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�������ԃ`�F�b�N
        //�@@�P�|�P�j�@@this.�������ԁ�null�̏ꍇ�A
        //�@@�u�������Ԃ�null�v�̗�O���X���[����B
        if (this.differentTime == null)
        {
            log.debug("�������Ԃ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02360,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������Ԃ����w��ł��B");
        }
        
        //�P�|�Q�j�@@this.�������Ԃ��ȉ��̏����ɊY������ꍇ�A
        //�u�������ԃG���[�v�̗�O���X���[����B
        //�@@�E�������� �� ����
        //�@@�E�������� <= 0
        //�@@�E��������.length > 4
        if (!WEB3StringTypeUtility.isInteger(this.differentTime)
            || Integer.parseInt(this.differentTime) <= 0
            || this.differentTime.length() > 4)
        {
            log.debug("�������Ԃ̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02361,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������Ԃ̓��͂��s���ł��B");
        }
        
        //�Q�j�@@��������From�`�F�b�N
        //�@@�Q�|�P�j�@@this.��������From��null�̏ꍇ�A
        //�@@�u��������From��null�v�̗�O���X���[����B
        if (this.differentTimeFrom == null)
        {
            log.debug("��������From�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��������From�����w��ł��B");
        }
        
        //�R�j�@@��������To�`�F�b�N
        //�@@�R�|�P�j�@@this.��������To��null�̏ꍇ�A
        //�@@�u��������To��null�v�̗�O���X���[����B
        if (this.differentTimeTo == null)
        {
            log.debug("��������To�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��������To�����w��ł��B");
        }
        
        //�S�j�@@��������From/To�������`�F�b�N
        //�@@�S�|�P�j�@@this.��������From/To�����L�ȊO�̏ꍇ�A
        //�@@�u�������Ԑ������G���[�v�̗�O���X���[����B
        //�@@�@@�E��������From��"������M����"�A���A��������To��"�g���K�[�N������"
        //�@@�@@�E��������From��"������M����"�A���A��������To��"������������"
        //�@@�@@�E��������From��"�g���K�[�N������"�A���A��������To��"������������"
        if (!((WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(this.differentTimeFrom)
                && WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(this.differentTimeTo))
            || (WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(this.differentTimeFrom)
                && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(this.differentTimeTo))
            || (WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(this.differentTimeFrom)
                && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(this.differentTimeTo))))
        {
            log.debug("�������Ԑ������G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02364,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������Ԑ������G���[�B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
