head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOTermUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO���Ԏw�胁�b�Z�[�W�f�[�^(WEB3IPOTermUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO���Ԏw�胁�b�Z�[�W�f�[�^�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPOTermUnit extends Message
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IPOTermUnit.class);
        
    /**
     * �c�Ɠ����
     */
    public String bizDateUpper;
    
    /**
     * �c�Ɠ�����
     */
    public String bizDateLower;
    
    /**
     * �J�n����
     */
    public Date startDate;
    
    /**
     * �I������
     */
    public Date endDate;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C6763F030A
     */
    public WEB3IPOTermUnit() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@this.�c�Ɠ�����̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00433<BR>
     * <BR>
     * �Q�j�@@this.�c�Ɠ������̃`�F�b�N<BR>
     * �@@�� ���͂�����ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00490<BR>
     * @@roseuid 40C865D3036E
     */
    public void validate()  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("���N�G�X�g�f�[�^�̐������`�F�b�N���s���B: ENTER");
        
        log.debug("�P�j�@@this.�c�Ɠ�����̃`�F�b�N: ENTER");
        //�P�j�@@this.�c�Ɠ�����̃`�F�b�N<BR>
        if (this.bizDateUpper != null & !"".equals(this.bizDateUpper))
        {
            if (!WEB3StringTypeUtility.isNumber(this.bizDateUpper))
            {
                //�@@�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00433, getClass().getName() + "validate");
            }
        }
        log.debug("�P�j�@@this.�c�Ɠ�����̃`�F�b�N: END");
        
        log.debug("�Q�j�@@this.�c�Ɠ������̃`�F�b�N: ENTER");
        if (this.bizDateLower != null & !"".equals(this.bizDateLower))
        {
            if (!WEB3StringTypeUtility.isNumber(this.bizDateLower))
            {
                //�@@�P�|�P�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00490, getClass().getName() + "validate");
            }
        }
        log.debug("�Q�j�@@this.�c�Ɠ������̃`�F�b�N: END");
        
        log.debug("���N�G�X�g�f�[�^�̐������`�F�b�N���s���B: END");
        log.exiting(STR_METHOD_NAME);
    }
}
@
