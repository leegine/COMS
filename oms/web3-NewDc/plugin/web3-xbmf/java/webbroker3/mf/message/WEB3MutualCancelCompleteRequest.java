head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M������������N�G�X�g�N���X(WEB3MutualCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/24 ���� (���u) ���r���[
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * �����M������������N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelCompleteRequest.class);
  
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
  
    /**
     * ����ID
     */
    public String id;
    
    /**
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * �m�F��������<BR>
     * <BR>
     * �m�F���X�|���X�̏����Ŏg�p�����l���i�[����B<BR>
     */
    public Date checkDate;
    
    /**
     * (���M����������N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40A9A888028C
     */
    public WEB3MutualCancelCompleteRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M����������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A9A897025D
     */
    public WEB3GenResponse createResponse() 
    {
        WEB3MutualCancelCompleteResponse l_response = new WEB3MutualCancelCompleteResponse(this);
        return l_response;
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@ID�`�F�b�N<BR>
     * �@@this.ID��null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00080 <BR>
     * <BR>
     * �Q)�@@�m�F���������̃`�F�b�N<BR>
     * �@@this.�m�F����������null�ł���ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00078 <BR>
     * @@roseuid 40A9A897026C
     */
    public void validate()throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@ID�`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("�h�c�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�h�c�����w��ł��B");            
        }
        
        //�Q)�@@�m�F���������̃`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.checkDate, "yyyyMMdd")))
        {
            log.debug("�m�F�������������͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F�������������͂���Ă��܂���B");            
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
