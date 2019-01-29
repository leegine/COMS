head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX����ظ���(WEB3AdminAccInfoCampaignAccOpenInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
Revision History : 2007/2/1  ���f��No.165
Revision History : 2007/2/5  ���f��No.179
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX����ظ���)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX����ظ���<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenInputRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312033L;
    
    /**
     * (�萔����������߰ݏ���ID)<BR>
     * �萔����������߰ݏ���ID<BR>
     */
    public String campaignId;
    
    /**
     * (�X�V�����t���O)<BR>
     * �X�V�����t���O<BR>
     * <BR>
     * 0�F�o�^����<BR>
     * 1�F�X�V����<BR>
     */
    public String updateFlag;
    
    /**
     * @@roseuid 45C087600034
     */
    public WEB3AdminAccInfoCampaignAccOpenInputRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignAccOpenInputResponse(this);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j �X�V�����t���O�̃`�F�b�N<BR>
     *   �P-�P�j �X�V�����t���O != (0 or 1) �̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02721<BR>
     * <BR>
     * �Q�j �X�V�t���O�� 1�i�ύX�j �̏ꍇ�A�ȉ��̏������s���B<BR>
     *   �Q-�P�j �萔�������L�����y�[������ID�̃`�F�b�N<BR>
     *     �Q-�P-�P�j �萔�������L�����y�[������ID�������͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02716<BR>
     * @@roseuid 45B59CA90341
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j �X�V�����t���O�̃`�F�b�N 
        //  �P-�P�j �X�V�����t���O != (0 or 1) �̏ꍇ�A��O���X���[����B 
        if (this.updateFlag != null)
        {
            if (!(WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag) 
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag)))
            {
                log.debug("�X�V�����t���O��'0' �� '1'�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02721,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�X�V�����t���O��'0' �� '1'�ȊO�̒l�ł��B");  
            }
        }

        //�Q�j �X�V�t���O�� 1�i�ύX�j �̏ꍇ�A�ȉ��̏������s���B 
        //  �Q-�P�j �萔�������L�����y�[������ID�̃`�F�b�N
        //    �Q-�P-�P�j �萔�������L�����y�[������ID�������͂̏ꍇ�A��O���X���[����B
        if (this.updateFlag != null)
        {
            if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag))
            {
                if (this.campaignId == null)
                {
                    log.debug("�萔�������L�����y�[������ID�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02716,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�萔�������L�����y�[������ID�����w��ł��B");
                }
            }
        }
    }
}
@
