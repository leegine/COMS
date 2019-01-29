head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryDeleteCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[�폜�������N�G�X�g(WEB3AdminPointCategoryDeleteCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
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
 * (�J�e�S���[�폜�������N�G�X�g)<BR>
 * �J�e�S���[�폜�������N�G�X�g�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryDeleteCompleteRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryDeleteCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryDeleteComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410150118L;
    
    /**
     * (�J�e�S���[�ԍ�)<BR>
     * �w�肳�ꂽ�J�e�S���[�ԍ�<BR>
     */
    public String categoryNo;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D1254601B5
     */
    public WEB3AdminPointCategoryDeleteCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�J�e�S���[�ԍ�<BR>
     * <BR>
     *    this.�J�e�S���[�ԍ� = null or <BR>
     *    this.�J�e�S���[�ԍ� != ���� <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01728<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01884<BR>
     * @@roseuid 418F5FAB01A5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�e�S���[�ԍ�        
        if (this.categoryNo == null || "".equals(this.categoryNo.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01728,
                getClass().getName() + STR_METHOD_NAME,
                "this.�J�e�S���[�ԍ� = null�̏ꍇ�A��O���X���[����");
                
            log.debug("�|�C���g�V�X�e��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;    
        }
        
        if (!WEB3StringTypeUtility.isNumber(this.categoryNo))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01884,
                getClass().getName() + STR_METHOD_NAME,
                "this.�J�e�S���[�ԍ� != �����̏ꍇ�A��O���X���[����");
                
            log.debug("�|�C���g�V�X�e��.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254601D4
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointCategoryDeleteCompleteResponse(this);
    }
}
@
