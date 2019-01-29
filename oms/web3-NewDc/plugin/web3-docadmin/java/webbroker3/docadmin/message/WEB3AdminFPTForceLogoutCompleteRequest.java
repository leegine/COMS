head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ʖ����� �������O�A�E�g�������N�G�X�g(WEB3AdminFPTForceLogoutCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 ��(FLJ) �V�K�쐬
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * �Ǘ��� ���ʖ����� �������O�A�E�g�������N�G�X�g
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200803181606L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutCompleteRequest.class);
    
    /**
     * ���ʋ敪�Ǘ����ꗗ
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;
    
    /**
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 47DF467703B1
     */
    public WEB3AdminFPTForceLogoutCompleteRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
     * �P�j ���ʋ敪�Ǘ��ꗗ�`�F�b�N(���ʋ敪�Ǘ��ꗗ�z��S�v�f�ɑ΂��ă`�F�b�N���s��) 
     * 
     *   �P-�P�j this.���ʋ敪�Ǘ����ꗗ == null 
     * �̏ꍇ�A��O�iBUSINESS_ERROR_03007�j���X���[����B
     * 
     *   �P-�Q�j this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h == (null or "") 
     * �̏ꍇ�A��O�iBUSINESS_ERROR_03013�j���X���[����B
     *   �P-�R�j this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h�����p�����ȊO 
     * �̏ꍇ�A��O�iBUSINESS_ERROR_02997�j���X���[����B
     *   �P-�S�j this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h��4���ȏ� 
     * �̏ꍇ�A��O�iBUSINESS_ERROR_02997�j���X���[����B
     * @@roseuid 47CF76AE015B
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.documentDivList == null) 
        {
            log.debug("���ʋ敪�Ǘ���񂪖��w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03007,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʋ敪�Ǘ���񂪖��w��ł��B");
        }
        for(int i=0;i<this.documentDivList.length;i++)
        {
            String l_documentCategory = this.documentDivList[i].documentCategory;
            if (l_documentCategory == null || l_documentCategory.equals(""))
            {
                log.debug("���ʎ�ރR�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h�����w��ł��B");
            }
            if ((!WEB3StringTypeUtility.isNumber(l_documentCategory))
                 || (WEB3StringTypeUtility.getByteLength(l_documentCategory) >= 4))
            {
                log.debug("���ʎ�ރR�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h���s���ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /* (�� Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTForceLogoutCompleteResponse(this);
    }
}
@
