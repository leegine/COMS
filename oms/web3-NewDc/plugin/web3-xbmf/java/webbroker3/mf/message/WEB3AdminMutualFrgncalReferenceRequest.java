head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^�Ɖ�N�G�X�g(WEB3AdminMutualFrgncalReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 �����(���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[  
                   2004/12/07 ������ (���u) �c�Ή�
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
 * �����M���C�O�s��J�����_�[�o�^�Ɖ�N�G�X�g�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalReferenceRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_mutual_frgncal_reference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151426L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualFrgncalReferenceRequest.class);

    /**
     * ���M�����R�[�h<BR>
     * ([�����R�[�h]�̓��͒l�A�܂���[������]�̑I������̖����R�[�h)<BR>
     */
    public String mutualProductCode;
    
    /**
     * �����N��<BR>
     * (����:"YYYYMM")<BR>
     */
    public String searchYM;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40CEC6F6007A
     */
    public WEB3AdminMutualFrgncalReferenceRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�C�O�s��J�����_�[�o�^�Ɖ�X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40CED01B02AD
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualFrgncalReferenceResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h��null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * �Q)�@@�����N���`�F�b�N<BR>
     * �@@�Q�|�P)�@@this.�����N����null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00429 <BR>
     * �@@�Q�|�Q)�@@this.�����N�������l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00430 <BR>
     * �@@�Q�|�R)�@@this.�����N����6���ȊO�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00431 <BR>
     * �@@�Q�|�S)�@@this.�����N���̉�2�����A01�`12�܂ł̐��l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00432 <BR>
     * ��O���X���[����B<BR>
     * <BR>
     * @@roseuid 40CED01B02CC
     */
    public void validate() throws WEB3BaseException
    {
        final String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        // �P)�@@�����R�[�h�`�F�b�N
        // this.�����R�[�h��null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode))
        {
            log.debug("�����R�[�h����͂��Ă��������B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + l_strMethodName,
                "�����R�[�h����͂��Ă��������B"
            );
        }
        // �Q)�@@�����N���`�F�b�N
        // �Q�|�P)�@@this.�����N����null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.searchYM))
        {
            log.debug("�����N�������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00429,
                this.getClass().getName() + "." + l_strMethodName,
                "�����N�������w��ł��B"
            );
        }
        // �Q�|�Q)�@@this.�����N�������l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.searchYM))
        {
            log.debug("�����N�������l�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00430,
                this.getClass().getName() + "." + l_strMethodName,
                "�����N�������l�ȊO�̏ꍇ"
            );
        }
        // �Q�|�R)�@@this.�����N����6���ȊO�̏ꍇ�A��O���X���[����B
        if (this.searchYM.length() != 6)
        {
            log.debug("�����N����6���ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00431,
                this.getClass().getName() + "." + l_strMethodName,
                "�����N����6���ȊO�̏ꍇ"
            );
        }
        // �Q�|�S)�@@this.�����N���̉�2�����A01�`12�܂ł̐��l�ȊO�̏ꍇ�A��O���X���[����B
        int l_intMM = Integer.parseInt(searchYM.substring(4, 6));
        if (l_intMM < 1 || l_intMM > 12)
        {
            log.debug("�����N���̉�4�����A01�`12�܂ł̐��l�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00432,
                this.getClass().getName() + "." + l_strMethodName,
                "�����N���̉�4�����A01�`12�܂ł̐��l�ȊO�̏ꍇ"
            );
        }
        log.exiting(l_strMethodName);
    }
}
@
