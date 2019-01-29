head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨����������N�G�X�g�N���X(WEB3FuturesCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/08/05 ���Ō� (���u) Review�C��
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����w���敨����������N�G�X�g)<BR>
 * �����w���敨����������N�G�X�g�N���X
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesCancelCompleteRequest extends WEB3GenRequest 
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesCancelCompleteRequest.class);  

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_cancelComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407220954L;    
    /**
     * (�h�c)<BR>
     * �����h�c<BR>
     */
    public String id;
    
    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;
    
    /**
     * (�m�F��������)<BR>
     * ��ʂł͔�\���B�������N�G�X�g�ő��M����l�B<BR>
     */
    public Date checkDate;
    
    /**
     * @@roseuid 40F7AE1A0138
     */
    public WEB3FuturesCancelCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00080<BR>
     * @@roseuid 40A20E750325
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�h�c�`�F�b�N
        //this.�h�c��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE1A0157
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FuturesCancelCompleteResponse(this);
    }
}
@
