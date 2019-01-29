head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq̧���޳�۰��ظ���(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq̧���޳�۰��ظ���)<BR>
 * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq̧���޳�۰��ظ���<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_loginPasswordChangeAccountFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082125L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�J�n��)<BR>
     * �J�n���i���j<BR>
     */
    public Date startDate;

    /**
     * (�I����)<BR>
     * �I�����i���j<BR>
     */
    public Date endDate;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;

    /**
     * @@roseuid 418F38560000
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse(this);
    }

    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�J�n���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01149<BR>
     * <BR>
     * �R�j�@@�I�����̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01150<BR>
     * �@@�R�|�Q�j�@@�i�J�n�� > �I�����j�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01151<BR>
     * <BR>
     * �S�j�@@�\�[�g�L�[�̃`�F�b�N  <BR>
     * �@@�S�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * �@@�S�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * �@@�S�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B  <BR>
     * �@@�@@�@@�S�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�S�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A <BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * �@@�@@�@@�@@ �p�X���[�h�ύX�ڋq���.���X�R�[�h<BR>
     * �@@�@@�@@�@@ �p�X���[�h�ύX�ڋq�����.�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@ �p�X���[�h�ύX�ڋq�����.�X�V��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 414FFC6B00C7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N 
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (this.branchCode == null || branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                " ���X�R�[�h�����w��ł�");  
        }
        
        //�Q�j�@@�J�n���̃`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.startDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01149, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �J�n������͂��܂���");  
        }
        
        //�R�j�@@�I�����̃`�F�b�N
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (this.endDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01150, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �I��������͂��܂���");  
        }
        
        //�R�|�Q�j�@@�i�J�n�� > �I�����j�̏ꍇ�A��O���X���[����B 
        if (WEB3DateUtility.compareToDay(this.startDate, this.endDate) > 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01151, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �i�J�n�� > �I�����j�ł�");  
        }

        //�S�j�@@�\�[�g�L�[�̃`�F�b�N 
        //�S�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�����w��ł�");  
        }
        
        //�S�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�̗v�f�����O�ł�");
        }
        
        //�S�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B         
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            
            //�S�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B 
            this.sortKeys[i].validate();
            
            //�S�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B 
            //�p�X���[�h�ύX�ڋq���.���X�R�[�h 
            //�p�X���[�h�ύX�ڋq�����.�ڋq�R�[�h
            //�p�X���[�h�ύX�ڋq�����.�X�V��
            
            if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł�");
            }
        }        
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
