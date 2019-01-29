head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistChangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��҃J�e�S���[�ύX���͉�ʃ��N�G�X�g(WEB3AdminMutualCategoryRegistChangeRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�Ǘ��҃J�e�S���[�ύX���͉�ʃ��N�G�X�g)<BR>
 * �����M���Ǘ��҃J�e�S���[�ύX���͉�ʃ��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualCategoryRegistChangeRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_category_regist_change";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412030935L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualCategoryRegistChangeRequest.class);
    /**
     * (���M�����J�e�S���[�R�[�h)<BR>
     * ���M�����J�e�S���[���̂ɑΉ��������M�����J�e�S���[�R�[�h
     */
    public String categoryCode;
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�Ǘ��҃J�e�S���[�ύX���͉�ʃ��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 4153B8100269
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualCategoryRegistChangeResponse(this);
    }
   
    /**
     * (validate)<BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���M�����J�e�S���[�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.���M�����J�e�S���[�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01243<BR>
     * �@@�P�|�Q�jthis.���M�����J�e�S���[�R�[�h�̒l��2Byte�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:BUSINESS_ERROR_01244
     * @@throws WEB3BaseException
     * @@roseuid 415401B703B0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���M�����J�e�S���[�R�[�h�̃`�F�b�N 
        //  �P�|�P�jthis.���M�����J�e�S���[�R�[�h==null�̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.categoryCode))
        {
            log.debug("���M�����J�e�S���[�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01243,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M�����J�e�S���[�R�[�h�����w��ł��B");
        }
        
        //  �P�|�Q�jthis.���M�����J�e�S���[�R�[�h�̒l��2Byte�̏ꍇ�A��O���X���[����B
        if (this.categoryCode.getBytes().length > 2)
        {
            log.debug("���M�����J�e�S���[�R�[�h�̒l������l�𒴂��Ă��܂��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01244,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���M�����J�e�S���[�R�[�h�̒l������l�𒴂��Ă��܂��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
