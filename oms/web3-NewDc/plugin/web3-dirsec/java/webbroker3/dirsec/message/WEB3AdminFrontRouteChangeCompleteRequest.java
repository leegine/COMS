head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontRouteChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҕ����o�H�ؑ֊������N�G�X�g (WEB3AdminFrontRouteChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.116
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminFrontServiceStartDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҕ����o�H�ؑ֊������N�G�X�g)<BR>
 * <BR>
 * �Ǘ��Ҕ����o�H�ؑփT�[�r�X�i�����j�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontRouteChangeCompleteRequest extends WEB3AdminFrontRouteChangeCommonRequest {
    

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_front_route_change_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontRouteChangeCompleteRequest.class);
        
    /**
     * �T�[�r�X�N���敪<BR>
     * <BR>
     * 0�F�@@�Ǘ��ҋN��<BR>
     * 1�F�@@�����N��<BR>
     */
    public String serviceStartDiv;
   

    /**
     * �Ïؔԍ�<BR>
     */
    public String password;
   

    /**
     * @@roseuid 42FFFED503AC
     */
    public WEB3AdminFrontRouteChangeCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�T�[�r�X�N���敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis�T�[�r�X�N���敪 == <BR>
     * null�̏ꍇ�A�u�T�[�r�X�N���敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�Ïؔԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis�Ïؔԍ� == null�̏ꍇ�A�u�Ïؔԍ���null�v�̗�O���X���[����B<BR>
     * @@roseuid 42F884D100A5
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �T�[�r�X�N���敪��null�`�F�b�N
        if (this.serviceStartDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02207,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // �Ïؔԍ��`�F�b�N        
        else if (serviceStartDiv.equals(WEB3AdminFrontServiceStartDivDef.ADMINISTRATOR_DIV) && this.password == null) 
        {
            throw new WEB3BusinessLayerException(
            
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        
        // 1-1 super.validate()
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
      * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
      */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFrontRouteChangeCompleteResponse(this);
    }

}
@
