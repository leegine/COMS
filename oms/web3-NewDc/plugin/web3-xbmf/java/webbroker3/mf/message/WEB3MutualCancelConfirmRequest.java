head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M������m�F���N�G�X�g�N���X(WEB3MutualCancelConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/24 ���� (���u) ���r���[
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
 * �����M������m�F���N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualCancelConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelConfirmRequest.class);
       
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
        
    /**
     * ����ID
     */
    public String id;
    
    /**
     * (���M����m�F���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40A9A82D01EF
     */
    public WEB3MutualCancelConfirmRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A9A83C002A
     */
    public WEB3GenResponse createResponse() 
    {
        WEB3MutualCancelConfirmResponse l_response = new WEB3MutualCancelConfirmResponse(this);
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
     * @@roseuid 40A9A83C003A
     */
    public void validate()throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
         if (WEB3StringTypeUtility.isEmpty(this.id))
         {
             log.debug("�h�c�����w��ł��B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�h�c�����w��ł��B");
         }
        log.exiting(STR_METHOD_NAME);
    }
}
@
