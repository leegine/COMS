head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g������t���N�G�X�g(WEB3AdminPointExchangeAcceptRequest.java)
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
 * (�|�C���g������t���N�G�X�g)<BR>
 * �|�C���g������t���N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointExchangeAcceptRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointExchangeAcceptRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_exchangeAccept";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290104L;
    
    /**
     * (�\��ID)<BR>
     * �I�����ꂽ�\��ID�̔z��<BR>
     */
    public String[] applyId;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D1254E0251
     */
    public WEB3AdminPointExchangeAcceptRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�\��ID<BR>
     * <BR>
     *    this.�\��ID = null or<BR>
     *    this.�\��ID.length() = 0 or <BR>
     *    this.�\��ID�̊e�v�f != ���� <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01732<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01733<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01885<BR>
     * @@roseuid 419021C4009E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\��ID
        if (this.applyId == null)
        {
            String l_strMessage = "�\��IDerror! " + this.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01732,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        if (this.applyId.length == 0)
        {
            String l_strMessage = "�\��ID[] length = 0! ";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01733,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        
        int l_intCount = this.applyId.length;
        for (int i = 0; i < l_intCount; i++)
        {
            if (this.applyId[i] == null 
                || "".equals(this.applyId[i].trim()))
            {
                String l_strMessage = "�\��IDerror! " + this.applyId[i];
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01732,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
            if (!WEB3StringTypeUtility.isDigit(this.applyId[i]) )
            {
                String l_strMessage = "�\��IDerror! " + this.applyId[i];
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01885,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage );
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254E0271
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointExchangeAcceptResponse(this);
    }
}
@
