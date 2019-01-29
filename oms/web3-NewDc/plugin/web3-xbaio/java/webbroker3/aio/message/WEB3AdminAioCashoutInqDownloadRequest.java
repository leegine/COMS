head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���_�E�����[�h���N�G�X�g(WEB3AdminAioCashoutInqDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/31 ����� (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioInputDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o���\���⍇���_�E�����[�h���N�G�X�g)<BR>
 * �o���\���⍇���_�E�����[�h���N�G�X�g�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminAioCashoutInqDownloadRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */ 
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_download";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607311248L;   
    
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h
     */
    public String[] branchCode;
    
    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��
     */
    public Date deliveryDate;
    
    /**
     * (������t�敪)<BR>
     * ��ʂɂđI�����ꂽ������t�敪<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t��<BR>
     * 2�F ��t�G���[<BR>
     * 3�F �S��<BR>
     */
    public String orderDiv;
    
    /**
     * (���͋敪)<BR>
     * ��ʂɂđI�����ꂽ���͋敪<BR>
     * <BR>
     * 0�F�S��<BR>
     * 1�F�ڋq<BR>
     * 2�FSONAR<BR>
     */
    public String inputDiv;   
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     * <BR>
     * �P�|�P�j<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0 or <BR>
     * ���N�G�X�g�f�[�^.���X�R�[�h = null<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00833<BR>
     * <BR>   
     * �P�|�Q�j�z��̊e�v�f�ɂ���<BR>
     * <BR>
     * �P�|�Q�|�P�j<BR>
     * <BR>
     * �e�v�f.length() != 3<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * �P�|�Q�|�Q�j<BR>
     * <BR>
     * �e�v�f�ɐ����ȊO�̕���������<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q�j������t�敪�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.������t�敪 != 0�i��t���ρj and <BR>
     * ���N�G�X�g�f�[�^.������t�敪 != 1�i��t�ρj and<BR>
     * ���N�G�X�g�f�[�^.������t�敪 != 2�i��t�G���[�j and <BR>
     * ���N�G�X�g�f�[�^.������t�敪 != 3�i�S�āj<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00790<BR>
     * <BR>
     * �R�j���͋敪�`�F�b�N<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���͋敪 != 0�i�S�āj and <BR>
     * ���N�G�X�g�f�[�^.���͋敪 != 1�i�ڋq�j and <BR>
     * ���N�G�X�g�f�[�^.���͋敪 != 2�iSONAR�j<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02522<BR>
     * <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 40E53E8A0254
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���X�R�[�h�`�F�b�N 
        //�P�|�P�j���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0 or 
        //       ���N�G�X�g�f�[�^.���X�R�[�h = null �̏ꍇ�A��O���X���[����B             
        if (this.branchCode == null ||
            this.branchCode.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }      

        //�P�|�Q�j�z��̊e�v�f�ɂ��� 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            //�P�|�Q�|�P�j�e�v�f.length() != 3 �̏ꍇ�A��O���X���[����B 
            if (this.branchCode[i].length() != 3)
            {                
                log.exiting(STR_METHOD_NAME);                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834 ,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
            
            //�P�|�Q�|�Q�j�e�v�f�ɐ����ȊO�̕���������̏ꍇ�A��O���X���[����B 
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.exiting(STR_METHOD_NAME);                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }
              
        //�Q�j������t�敪�`�F�b�N 
        //���N�G�X�g�f�[�^.������t�敪 != 0�i��t���ρj and 
        //���N�G�X�g�f�[�^.������t�敪 != 1�i��t�ρj and 
        //���N�G�X�g�f�[�^.������t�敪 != 2�i��t�G���[�j and 
        //���N�G�X�g�f�[�^.������t�敪 != 3�i�S�āj               
        if (!WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ALL.equals(this.orderDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00790,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������t�敪�����݂��Ȃ��R�[�h�l�ł��B " +
                "���N�G�X�g�f�[�^.������t�敪 = " + this.orderDiv);
        }
        
        //3�j���͋敪�`�F�b�N 
        //���N�G�X�g�f�[�^.���͋敪 != 0�i�S�āj and 
        //���N�G�X�g�f�[�^.���͋敪 != 1�i�ڋq�j and 
        //���N�G�X�g�f�[�^.���͋敪 != 2�iSONAR�j 
        if (!WEB3AioInputDivDef.ALL.equals(this.inputDiv) &&
            !WEB3AioInputDivDef.CUSTOMER.equals(this.inputDiv) &&
            !WEB3AioInputDivDef.SONAR.equals(this.inputDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02522,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���͋敪�����݂��Ȃ��R�[�h�l�ł��B " +
                "���N�G�X�g�f�[�^.���͋敪 = " + this.inputDiv);
        }
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqDownloadResponse(this);
    }
}
@
