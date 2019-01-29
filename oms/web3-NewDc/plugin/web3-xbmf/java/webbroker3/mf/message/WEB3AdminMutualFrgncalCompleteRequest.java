head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^�������N�G�X�g(WEB3AdminMutualFrgncalCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 �����(���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[    
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
 * �����M���C�O�s��J�����_�[�o�^�������N�G�X�g�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_frgncal_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151339L;    
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualFrgncalCompleteRequest.class);

    /**
     * ���M�����R�[�h
     */
    public String mutualProductCode;
    
    /**
     * �o�^�X�V���<BR>
     * (�ύX�`�F�b�N�{�b�N�X���`�F�b�N���ꂽ���t���)<BR>
     */
    public WEB3MutualBizDateUnit[] bizDateList;
    
    /**
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C008050113
     */
    public WEB3AdminMutualFrgncalCompleteRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�C�O�s��J�����_�[�o�^�������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40C0080E0152
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualFrgncalCompleteResponse(this);
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
     * �Q)�@@�o�^�X�V���`�F�b�N<BR>
     * �@@�Q�|�P)�@@�o�^�X�V���null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00411 <BR>
     * �@@�Q�|�Q)�@@�o�^�X�V���̗v�f����0���̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00412 <BR>
     * @@roseuid 40C0085A0181
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
        // �Q)�@@�o�^�X�V���`�F�b�N
        // �Q�|�P)�@@�o�^�X�V���null�̏ꍇ�A��O���X���[����B
        if (this.bizDateList == null)
        {
            log.debug("�o�^�X�V��񂪖��w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00411,
                this.getClass().getName() + "." + l_strMethodName,
                "�o�^�X�V��񂪖��w��ł��B"
            );
        }
        // �Q�|�Q)�@@�o�^�X�V���̗v�f����0���̏ꍇ�A��O���X���[����B
        if (this.bizDateList.length == 0)
        {
            log.debug("�o�^�X�V���̗v�f����0���̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00412,
                this.getClass().getName() + "." + l_strMethodName,
                "�o�^�X�V���̗v�f����0���̏ꍇ"
            );
        }
        log.exiting(l_strMethodName);
    }
}
@
