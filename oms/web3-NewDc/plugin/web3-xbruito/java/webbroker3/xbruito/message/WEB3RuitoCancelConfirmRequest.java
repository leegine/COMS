head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ�������m�F���N�G�X�g�g�N���X(WEB3RuitoCancelConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬 
*/
package webbroker3.xbruito.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �ݐϓ�������m�F���N�G�X�g�N���X<BR>
 */
public class WEB3RuitoCancelConfirmRequest extends WEB3GenRequest
{
	/**
	* ���O�o�̓��[�e�B���e�B
	*/
   private static WEB3LogUtility log =
	   WEB3LogUtility.getInstance(WEB3RuitoCancelConfirmRequest.class);
	   	
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * ����ID<BR>
     */
    public String id;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922C3F0186
     */
    public WEB3RuitoCancelConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���ꍇ�A��O���X���[����B<BR>
     *   class    : WEB3BusinessLayerException<BR>
     *   tag      : BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407367780338
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

        //�h�c��null�̒l�ł���ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("�h�c�����w��ł��B");              
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�h�c�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ�����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 408334C00265
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoCancelConfirmResponse(this);
    }
}
@
