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
filename	WEB3MstkCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j������������m�F���N�G�X�g(WEB3MstkCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j������������m�F���N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j������������m�F���N�G�X�g�N���X
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3MstkCancelConfirmRequest extends WEB3GenRequest 
{
    
    /**         
     * �i���O�o�̓��[�e�B���e�B�j�B        
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelConfirmRequest.class);
        
    /**
     * �iPTYPE�j�B
     */
    public final static  String PTYPE = "mstk_cancelConfirm";
        
    /**
     * �iSerialVersionUID�j�B
     */
    public final static long serialVersionUID = 200410101054L;
    
    /**
     * �iID�j�B<BR>
     * <BR>
     * ����Ώۃf�[�^�̒����h�c
     */
    public String id;
    
    /**
     * �i�����~�j������������m�F���N�G�X�g�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
    public WEB3MstkCancelConfirmRequest() 
    {
    }
    
    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�@@�h�c�`�F�b�N<BR>
     * �@@�@@�@@�@@this.�h�c��null�ł������ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080
     */
     public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�@@�h�c�`�F�b�N
        if(this.id == null)
        {
            
            log.error("�h�c��null�ł������ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                    this.getClass().getName() + "validate()");
                             
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * �icreate���X�|���X�j�B
     * @@return WEB3GenResponse �����~�j������������m�F���X�|���X
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkCancelConfirmResponse(this);
    }
}
@
