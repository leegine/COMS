head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U��������ύX����ظ���(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/09/11 �Ԑi�@@ (���u) �d�l�ύX�Ǘ�No.123

*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l����p�U��������ύX����ظ���)<BR>
 * �Ǘ��҂��q�l����p�U��������ύX����ظ���<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082141L;
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.class);


    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (��p�U��������폜�t���O)<BR>
     * ��p�U��������폜�t���O<BR>
     * <BR>
     * true�F�@@�폜<BR>
     * false�F�@@�폜�łȂ�<BR>
     */
    public boolean exclusiveTransferAccountDeleteFlag;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�ύX����)<BR>
     * �ύX����<BR>
     */
    public WEB3AccInfoAccountInfo changedAccountInfo;
    
    /**
     * (�����̔ԃt���O)<BR>
     * �����ԍ������̔ԃt���O�B<BR>
     * <BR>
     * true�F�@@�����ԍ��̎����̔Ԃ��s���B<BR>
     * false�F�@@�����ԍ��̎����̔Ԃ��s��Ȃ��B <BR>
     */
    public boolean codeAutoFlag;    

    /**
     * @@roseuid 418F3866035B
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�ύX����C��p�U��������폜�t���O�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�i��p�U��������폜�t���O == false�j�̏ꍇ�A<BR>
     * �ύX���񂪖����͂ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01162<BR>
     * �@@�R�|�Q�j�@@�i��p�U��������폜�t���O == true�j�̏ꍇ�A<BR>
     * �ύX����ɓ��͂�����Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01163<BR>
     * <BR>
     * �S�j�@@�ύX����̃`�F�b�N<BR>
     * �@@�ύX���񂪓��͂���Ă���ꍇ�A�ύX����.validate()���R�[������B<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CC936000D
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if(this.branchCode == null || "".equals(this.branchCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName(),
                " ���X�R�[�h==null�ł���");
        }
        
        // �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if(this.accountCode == null || "".equals(this.accountCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName(),
                " �ڋq�R�[�h��null�ł���");
        }
        
        //�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName(),
                " ������6�łȂ��ꍇ");
        }        
        
        //�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if(!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName(),
                " �ڋq�R�[�h�ɐ����ȊO�̕���������̏ꍇ�G���[�ł���");
        }
        
        //�R�j�@@�ύX����C��p�U��������폜�t���O�̃`�F�b�N<BR>
        //�R�|�P�j�@@�i��p�U��������폜�t���O == false�j�̏ꍇ�A�ύX���񂪖����͂ł���Η�O���X���[����B
        if(this.exclusiveTransferAccountDeleteFlag == false
            && this.changedAccountInfo == null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01162,
                this.getClass().getName(),
                " �i��p�U��������폜�t���O == false�j�̏ꍇ�A�ύX���񂪖����͂ł�");
        }
        
        //�R�|�Q�j�@@�i��p�U��������폜�t���O == true�j�̏ꍇ�A�ύX����ɓ��͂�����Η�O���X���[����
        if(this.exclusiveTransferAccountDeleteFlag == true
            && this.changedAccountInfo != null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01163,
                this.getClass().getName(),
                " �i��p�U��������폜�t���O == true�j�̏ꍇ�A�ύX����ɓ��͂ł�");
        }
        

        
        //�S�j�@@�ύX����̃`�F�b�N
        //�ύX���񂪓��͂���Ă���ꍇ�A�ύX����.validate()���R�[������B
        if(this.changedAccountInfo != null)
        {
            this.changedAccountInfo.validate();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
