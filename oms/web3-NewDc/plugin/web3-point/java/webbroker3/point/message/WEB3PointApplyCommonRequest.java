head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\�����ʃ��N�G�X�g(WEB3PointApplyCommonRequest.java)
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


/**
 * (�|�C���g�\�����ʃ��N�G�X�g)<BR>
 * �|�C���g�\�����ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointApplyCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290010L;
    
    /**
     * (�i�i�ԍ�)<BR>
     * �I�����ꂽ�i�i�ԍ�<BR>
     */
    public String premiumNo;
    
    /**
     * @@roseuid 41D12550032C
     */
    public WEB3PointApplyCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�i�i�ԍ�<BR>
     * <BR>
     *    this.�i�i�ԍ� = null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01727<BR>
     * @@roseuid 418F525D0388
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�i�i�ԍ�
        if (this.premiumNo == null || "".equals(this.premiumNo.trim()))
        {
            String l_strMessage = "�i�i�ԍ� = null";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01727,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12550034B
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
