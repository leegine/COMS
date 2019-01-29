head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO��������������۰�޴װ�����m�F���N�G�X�g(WEB3AdminFeqUploadErrCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqErrorCancelTargetDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO��������������۰�޴װ�����m�F���N�G�X�g)<BR>
 * �Ǘ��ҊO��������������۰�޴װ�����m�F���N�G�X�g
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelConfirmRequest extends WEB3GenRequest 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqUploadErrCancelConfirmRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_uploadErrCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�G���[�����Ώۋ@@�\�敪)<BR>
     * �G���[�����Ώۋ@@�\�敪<BR>
     * <BR>
     * 1�F�@@������t<BR>
     * 2�F�@@���
     */
    public String errorCancelTargetDiv;
    
    /**
     * @@roseuid 42CE3A03005D
     */
    public WEB3AdminFeqUploadErrCancelConfirmRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�G���[�����Ώۋ@@�\�敪<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02048<BR>
     * �@@�P�|�Q�j�@@�L���ȃR�[�h�l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02049<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42BBB44A00BA
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�G���[�����Ώۋ@@�\�敪
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.errorCancelTargetDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02048,
                this.getClass().getName() + STR_METHOD_NAME,
                " �G���[�����Ώۋ@@�\�敪�������͂ł��B"); 
        }
        //�P�|�Q�j�@@�L���ȃR�[�h�l�łȂ��ꍇ�A��O���X���[����B
        if (!(WEB3FeqErrorCancelTargetDivDef.ORDER_ACCEPT.equals(this.errorCancelTargetDiv) || 
            WEB3FeqErrorCancelTargetDivDef.EXECUTED.equals(this.errorCancelTargetDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02049,
                this.getClass().getName() + STR_METHOD_NAME,
                "�G���[�����Ώۋ@@�\�敪���s���Ȓl�ł�:" + this.errorCancelTargetDiv); 
        }
        
        log.exiting(STR_METHOD_NAME);    
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqUploadErrCancelConfirmResponse(this);
    }
}
@
