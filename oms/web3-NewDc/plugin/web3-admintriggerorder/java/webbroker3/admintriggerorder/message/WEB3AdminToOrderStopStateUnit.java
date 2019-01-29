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
filename	WEB3AdminToOrderStopStateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������~��(WEB3AdminToOrderStopStateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���@@�F(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (������~��)<BR>
 * ������~�󋵃N���X<BR>
 * 
 * @@author ���@@�F
 * @@version 1.0
 */
public class WEB3AdminToOrderStopStateUnit extends Message 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToOrderStopStateUnit.class);
    
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
     * (��~�t���O)<BR>
     * ��~�t���O<BR>
     * <BR>
     * false�F�@@�戵�\<BR>
     * true�F�@@��~��<BR>
     */
    public boolean stopFlag = false;
    
    /**
     * (�ύX���~�t���O)<BR>
     * �ύX���~�t���O<BR>
     * <BR>
     * false�F�@@�戵�\<BR>
     * true�F�@@��~��<BR>
     * <BR>
     * ���ύX���͌�̒�~�t���O���Z�b�g����B<BR>
     * �@@�ύX�Ȃ��̏ꍇ�́Athis.��~�t���O�Ɠ����l���Z�b�g����B<BR>
     */
    public boolean aftChangeStopFlag = false;
    
    /**
     * (������~��)<BR>
     * �R���X�g���N�^�B<BR>
     * @@roseuid 440FD84C013B
     */
    public WEB3AdminToOrderStopStateUnit() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@����������ʃ`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.����������ʂɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�@@�@@�ݒ肳��Ă����ꍇ�A<BR>
     * �@@�@@�@@�@@�u����������ʂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E"�A������" <BR>
     * �@@�@@�@@�@@�E"OCO����"<BR>
     * �@@�@@�@@�@@�E"IFD����"<BR>
     * �@@�@@�@@�@@�E"�t�w�l����" <BR>
     * �@@�@@�@@�@@�E"W�w�l����"<BR>
     *         class : WEB3BusinessLayerException<BR>
     *         tag : BUSINESS_ERROR_02397<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44113A0C003C
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@����������ʃ`�F�b�N
        // �@@�P�|�P�jthis.����������ʂɉ��L�̍��ڈȊO���ݒ肳��Ă����ꍇ�A
        // �@@�@@�@@�@@ �u����������ʂ�����`�̒l�v�̗�O���X���[����B 
        // �@@�@@�@@�@@  �E"�A������" 
        // �@@�@@�@@�@@  �E"OCO����"
        // �@@�@@�@@�@@  �E"IFD����"
        // �@@�@@�@@�@@  �E"�t�w�l����" 
        // �@@�@@�@@�@@  �E"W�w�l����"
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_02397
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
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
