head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\���I�����N�G�X�g(WEB3PointApplyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�|�C���g�\���I�����N�G�X�g)<BR>
 * �|�C���g�\���I�����N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyInputRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointApplyInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290005L;
    
    /**
     * (�J�e�S���[�ԍ�)<BR>
     * �I�����ꂽ�J�e�S���[�ԍ�<BR>
     */
    public String categoryNo;
    
    /**
     * @@roseuid 41D125510232
     */
    public WEB3PointApplyInputRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�J�e�S���[�ԍ�<BR>
     * <BR>
     *    this.�J�e�S���[�ԍ� = null or this.�J�e�S���[�ԍ� != ���� <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>       
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01728<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01884<BR>  
     * @@roseuid 418F51D10359
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�J�e�S���[�ԍ�
        if (this.categoryNo == null 
            || "".equals(this.categoryNo.trim()))
        {
            String l_strMessage = "�J�e�S���[�ԍ� = " + this.categoryNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01728,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(this.categoryNo))
        {
            String l_strMessage = "�J�e�S���[�ԍ� = " + this.categoryNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01884,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125510261
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3PointApplyInputResponse(this);
    }
}
@
