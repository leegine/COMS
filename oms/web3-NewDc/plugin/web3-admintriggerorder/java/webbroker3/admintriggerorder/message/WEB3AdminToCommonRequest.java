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
filename	WEB3AdminToCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҋ��ʃ��N�G�X�g(WEB3AdminToCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���@@�F(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҋ��ʃ��N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҋ��ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * �����X�����̃`�F�b�N���s���K�v������ꍇ�Ȃǂ́A<BR>
 * �@@���̃��N�G�X�g�N���X���p������B<BR>
 * 
 * @@author ���@@�F
 * @@version 1.0
 */
public class WEB3AdminToCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToCommonRequest.class);
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z�� <BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă��� <BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B<BR>
     */
    public String[] branchCode;
    
    /**
     * @@roseuid 4430D2BF03C8
     */
    public WEB3AdminToCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N <BR>
     * �@@�P�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02174<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.���X�R�[�h�̗v�f�����ȉ��̏������s���B <BR>
     * �@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00779<BR>
     * @@throws WEB3BaseException
     * @@roseuid 440E78630017
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j���X�R�[�h�`�F�b�N 
        // �@@�P�|�P�j�@@this.���X�R�[�h == null�̏ꍇ�A�u���X�R�[�h��null�v�̗�O���X���[����B 
        //          class : WEB3BusinessLayerException
        //         tag : BUSINESS_ERROR_02174
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }
        
        // �P�|�Q�j�@@this.���X�R�[�h�̗v�f�����ȉ��̏������s���B 
        // �@@�@@�P�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A�u���X�R�[�h�G���[�v�̗�O���X���[����B 
        // �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ���� 
        // �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3 
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_00779
        int l_intLen = this.branchCode.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("���X�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̓��͂��s���ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);     
    }
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * null��ԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }   
}
@