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
filename	WEB3RuitoCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ�������������N�G�X�g�N���X(WEB3RuitoCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �ݐϓ�������������N�G�X�g�N���X<BR>
 */
public class WEB3RuitoCancelCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;      
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoCancelCompleteRequest.class);

    /**
     * �m�F��������<BR>
     * <BR>
     * �������N�G�X�g�ő��M����l���i�[����B<BR>
     * �i��ʂł͔�\���j<BR>
     */
    public Date checkDate;

    /**
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * ����ID<BR>
     */
    public String id;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922C5B01F3
     */
    public WEB3RuitoCancelCompleteRequest()
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
     * <BR>
     * �Q�j�@@�m�F���������`�F�b�N<BR>
     * �@@this.�m�F����������null�̒l�ł���ꍇ�A��O���X���[����B<BR>
     *   class    : WEB3BusinessLayerException<BR>
     *   tag      : BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4073677D0134
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

        //�m�F����������null�̒l�ł���ꍇ�A��O���X���[����   
        if (this.checkDate == null)
        {
            log.debug("�m�F�������������͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�m�F�������������͂���Ă��܂���B");
        }

		log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ�����������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 408335340003
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3RuitoCancelCompleteResponse(this);
    }
}
@