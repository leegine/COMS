head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ē����[���z�M�w���m�F�v��(WEB3AdminAccInfoMailDistributionConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �d�� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���ē����[���z�M�w���m�F�v��)<BR>
 * �Ǘ��҂��q�l���ē����[���z�M�w���m�F�v��<BR>
 */
public class WEB3AdminAccInfoMailDistributionConfirmRequest extends WEB3GenRequest
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailDistributionConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412131135L;

    /**
     * (���M���[���敪)<BR>
     * ���M���[���敪<BR>
     * <BR>
     * �� �u���[�����e�v�����N�쐬�p�����[�^�B<BR>
     * <BR>
	 *	�擪2���͏��i�P�ʂɍ̔Ԃ��A����2���͔C��<BR>
	 *	01�F�T�[�r�X���p�@@02�F�����J�݁@@03�F�⍇���Ǘ��@@04�F�ē����[��<BR>
     *  ���ڍׂ́A�u���[�����M�e�[�u��.���M���[���敪�v�V�[�g�Q�ƁB<BR>
     */
    public String sendMailDiv;
    
    /**
     * (����ID) <BR>
     */
    public String discernId;
    
    /** (�S�ڋq�t���O)<BR>
     *  true�F�@@�S�ڋq�ɔz�M<BR>
     *  false�F�@@�ē����[����]�q�݂̂ɔz�M<BR>
     */
    public boolean allAccountFlag;
    


    /**
     * @@roseuid 418F39F3033C
     */
    public WEB3AdminAccInfoMailDistributionConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailDistributionConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     *<BR>
     * �P�j�@@���M���[���敪�̃`�F�b�N<BR>
�@@   *        �P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
�@@   *        �P�|�Q�j�@@�ē����[���i"0401"�j�ȊO�̒l�ꍇ�A��O���X���[����B<BR>
     *<BR>
     * �Q�j�@@���ʂh�c�̃`�F�b�N<BR>
�@@   *        �Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
�@@   *        �Q�|�Q�j�@@Default�i"----"�j�ȊO�̒l�̏ꍇ�A��O���X���[����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D3E5700F3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);

        if (this.sendMailDiv == null || "".equals(this.sendMailDiv))
        {
            log.debug("[���M���[���敪] = " + sendMailDiv);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00862, 
                getClass().getName() + STR_METHOD_NAME,"���M���[���敪�����͂̏ꍇ");
        }
        if (!WEB3SendmailDivDef.ACCINFO_GUIDE_MAIL.equals(this.sendMailDiv))
        {
            log.debug("[���M���[���敪] = " + sendMailDiv);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01383, 
                getClass().getName() + STR_METHOD_NAME, "�ē����[���i0401�j�ȊO�̒l�ꍇ");
        }
        
        if (this.discernId == null || "".equals(this.discernId))
        {
            log.debug("[���ʂh�c] = " + discernId);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00865, 
                getClass().getName() + STR_METHOD_NAME, "���ʂh�c�����͂̏ꍇ");
        }
        if (!"----".equals(this.discernId))
        {
            log.debug("[���ʂh�c] = " + discernId);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01384, 
                getClass().getName() + STR_METHOD_NAME, "���ʂh�cDefault�i----�j�ȊO�̒l�̏ꍇ");
        }
        log.exiting(STR_METHOD_NAME);
        

    }
}@
