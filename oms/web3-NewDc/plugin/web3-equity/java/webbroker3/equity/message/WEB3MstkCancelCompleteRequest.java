head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j������������������N�G�X�g(WEB3MstkCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j������������������N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j������������������N�G�X�g�N���X<
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3MstkCancelCompleteRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelCompleteRequest.class);
            
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "mstk_cancelComplete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101054L;
    
    /**
     * (ID)<BR>
     * ����Ώۃf�[�^�̒����h�c<BR>
     */
    public String id;
    
    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;
    
    /**
     * (�m�F��������)<BR>
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4167B04D030D
     */
    public WEB3MstkCancelCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�m�F���������`�F�b�N<BR>
     * �@@�@@�@@�@@this.�m�F��������null�ł������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * �Q�j�@@�@@�h�c�`�F�b�N<BR>
     * �@@�@@�@@�@@this.�h�c��null�ł������ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * @@roseuid 4112F38D0150
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j�@@�m�F���������`�F�b�N
        if(this.checkDate == null)
        {
            
            log.error("�m�F��������null�ł������ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                    this.getClass().getName() + "validate()");
                         
        }
        
        //�Q�j�@@�@@�h�c�`�F�b�N
        else if(this.id == null)
        {
            
            log.error("�h�c��null�ł������ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                    this.getClass().getName() + "validate()");
            
        } 
        log.exiting(STR_METHOD_NAME);   
        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B04D032B
     */
    public WEB3GenResponse createResponse() 
    {
        
        return new WEB3MstkCancelCompleteResponse(this);
        
    }
}
@
